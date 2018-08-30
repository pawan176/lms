package com.qc.starter.controllar;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.qc.starter.basic.LeadManager;
import com.qc.starter.dto.ContactDto;
import com.qc.starter.dto.LeadHeaderDto;
import com.qc.starter.dto.MasterDto;
import com.qc.starter.dto.PersonListDto;
import com.qc.starter.entity.LeadEntity;
import com.qc.starter.entity.UserEntity;
import com.qc.starter.service.ActionService;
import com.qc.starter.service.ContactService;
import com.qc.starter.service.LeadHeaderService;
import com.qc.starter.service.LeadService;
import com.qc.starter.service.LoginService;
import com.qc.starter.service.MasterService;
import com.qc.starter.service.ProductService;

@Controller
public class ContactController {	
	private static final Logger logger = Logger.getLogger(ContactController.class.getName());
//	private static ResourceBundle resource1=ResourceBundle.getBundle("ApplicationResource");
	@Autowired ContactService contactService;
	@Autowired LeadHeaderService leadHeaderService;
	@Autowired MasterService masterService;
	@Autowired	LoginService loginService;
	@Autowired ActionService actionService;
	@Autowired	LeadService leadService;
	@Autowired ProductService productService;

	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value={"contact"})	
	public String showContact(@RequestParam int caseId,HttpServletRequest request,HttpServletResponse response,HttpSession session,Model model,@CookieValue(value = "worklistLeadsCookie", defaultValue = "NoLeads") String worklistLeads){
		logger.info("ContactController | showContact() | :- START");
		UserEntity userEntity;
		LeadEntity leadEntity;
		//int caseId;
		int userid;
		boolean isEscalate = false;
		LeadHeaderDto leadHeaderDto;
		ContactDto contactDto;
		MasterDto masterDto;
		try{
			userEntity= (UserEntity)session.getAttribute("UserDetails");
			if(userEntity==null){
				session.invalidate();				
				return "redirect:login.do";
			}
			if(caseId == 0){
				leadEntity = (LeadEntity)session.getAttribute("leadDetails");
			}
			else{
				session.removeAttribute("leadDetails");
				leadEntity = leadService.getLead(caseId,userEntity.getCompanyId());
				session.setAttribute("leadDetails", leadEntity);			
			}
			boolean isblock = leadService.getEscalateReferInfo(leadEntity, userEntity); 
			if(isblock){
				model.addAttribute("msgForEscalateBlock","You can not work on this lead, As this is escalated / referred");
				return "workList";
			}



			//--------------------Lead Locking----------------------------
			LeadManager leadManager = LeadManager.getInstance();
			String status=leadManager.checkLeadId(caseId+"",userEntity.getUserid()+"",session.getId());
			if(status.equalsIgnoreCase("notExist")){
				leadManager.setLeadId(leadEntity.getCaseId(), userEntity,session.getId());
				status=null;
			}
			else if(status.contains("error~")){

				String stutusMessage[]=status.split("~");
				String user = "";	

				if(stutusMessage[1] != null && !(stutusMessage[1].toString().trim().equals("")) ){
					user = stutusMessage[1];
				}else{
					user = "Someone";
				}

				model.addAttribute("msgForleadLocking",stutusMessage[1]+" is already working on this lead!");

				status=null;
				stutusMessage=null;
				return "workList";
			}

			leadEntity= (LeadEntity)session.getAttribute("leadDetails");
			caseId= Integer.parseInt(leadEntity.getCaseId());
			userid= userEntity.getUserid();
			leadHeaderDto= leadHeaderService.getLeadHeader(new Integer(caseId).toString());
			String currentlead = caseId+"";

			// Coding for local storage in the worklist leads ....
			int indexOfCurrentLead=-12;			
			if(worklistLeads != null && !(worklistLeads.equals("NoLeads")) ){
				String[] leads = worklistLeads.split(",");
				for(int i=0;i<leads.length;i++)
				{
					if(leads[i].equals(currentlead)){
						indexOfCurrentLead = i;
					}
				}				
				if(indexOfCurrentLead == -12 || indexOfCurrentLead == 0 || indexOfCurrentLead == leads.length){
					String leadstotal = contactService.getAllWorklistLeads(caseId+""); 			
					response.addCookie(new Cookie("worklistLeadsCookie",leadstotal));
				}				
			}

			if(worklistLeads.equals("NoLeads")){
				String leadstotal = contactService.getAllWorklistLeads(caseId+""); 			
				response.addCookie(new Cookie("worklistLeadsCookie",leadstotal));
			}

			// End here for local storage


			model.addAttribute("leadHeaderDetail",leadHeaderDto);
			isEscalate= contactService.getLeadStatus(caseId+"",userid+"");
			contactDto= new ContactDto();
			if(!(isEscalate)){
				contactDto = contactService.getContactDetail(caseId+"","action",userid+"",userEntity.getCompanyId(),"WEB");
			}
			if(isEscalate){
				contactDto = contactService.getContactDetail(caseId+"","escalate",userid+"",userEntity.getCompanyId(),"WEB");
			}
			masterDto= masterService.getMasters("CaseActionEntity~DispositionMaster~ActionMaster~SubQueueEntity~StageEntity~PurposeEntity~RejectReasonEntity");
			model.addAttribute("masterDetail",masterDto);
			model.addAttribute("contactDetail",contactDto);	
		}catch(Exception e){
			//e.printStackTrace();
			logger.error("ContactController | showContact() | "+e.getMessage()+" | :- END");
		}finally{
			userEntity = null;
			leadEntity = null;
			leadHeaderDto = null;
			contactDto = null;
			masterDto = null;
		}		
		logger.info("ContactController | showContact() | :- END");
		
		if(isEscalate){
			return "contactEscalate";
		}else{
			return (productService.isLeadConvertToCustomer(caseId+"")) ? "contactViewer" : "contact";
		}
	}

	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value={"leadAction"})	
	public String manageContact(@ModelAttribute ContactDto contactDto,HttpServletRequest request,HttpSession session,Model model){
		logger.info("ContactController | LeadActionChange | :- START");
		LeadEntity leadEntity;
		int caseId;
		UserEntity userEntity;
		//boolean isSaved = false;
		String  actionId = null;
		try{
			leadEntity = (LeadEntity)session.getAttribute("leadDetails");
			caseId= Integer.parseInt(leadEntity.getCaseId());
			userEntity= (UserEntity)session.getAttribute("UserDetails");
			if(userEntity==null){
				session.invalidate();			
				return "redirect:login.do";
			}
			contactDto.setCaseId(""+caseId);
			contactDto.setUserId(userEntity.getUserid().toString());
			contactDto.setCompanyId(userEntity.getCompanyId());
			actionId = actionService.saveCaseAction(contactDto);
			int notificationCount=loginService.countNotification(userEntity.getUserid()+"", userEntity.getCompanyId());
			session.setAttribute( "notificationCount", notificationCount);
		}catch(Exception e){
			logger.error("ContactController | LeadActionChange |" +e.getMessage() +  " | :- END");
		}finally{
			contactDto = null;
			leadEntity=null;
			userEntity=null;
		}
		logger.info("ContactController | LeadActionChange | :- END");
		if(actionId==null)
			return "error";
		else return "redirect:contact.do?caseId=0";
	}

	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value={"seContact"})	
	public String saveExitContact(@ModelAttribute ContactDto contactDto,HttpServletRequest request,HttpSession session,Model model){
		logger.info("ContactController | saveExitContact | :- START");
		LeadEntity leadEntity;
		int caseId;
		UserEntity userEntity;
		//boolean isSaved = false;
		String  actionId = null;
		try{
			leadEntity = (LeadEntity)session.getAttribute("leadDetails");
			caseId= Integer.parseInt(leadEntity.getCaseId());
			userEntity= (UserEntity)session.getAttribute("UserDetails");
			if(userEntity==null){
				session.invalidate();			
				return "redirect:login.do";
			}
			contactDto.setCaseId(""+caseId);
			contactDto.setUserId(userEntity.getUserid().toString());
			contactDto.setCompanyId(userEntity.getCompanyId());
			actionId = actionService.saveCaseAction(contactDto);
			int notificationCount=loginService.countNotification(userEntity.getUserid()+"", userEntity.getCompanyId());
			session.setAttribute( "notificationCount", notificationCount);
		}catch(Exception e){
			logger.error("ContactController | LeadActionChange |" +e.getMessage() +  " | :- END");
		}finally{
			contactDto = null;
			leadEntity=null;
			userEntity=null;
		}
		logger.info("ContactController | LeadActionChange | :- END");
		if(actionId==null)
			return "error";
		else return "workList";
	}


	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value={"seleadEscalation"})	
	public String saveExitEscalation(@ModelAttribute ContactDto contactDto,HttpServletRequest request,HttpSession session,Model model){
		logger.info("ContactController | leadEscalation() | :- START");
		UserEntity userEntity;
		boolean isUpdate = false;
		LeadEntity leadEntity;
		try{
			userEntity = (UserEntity)session.getAttribute("UserDetails");
			if(userEntity==null){
				session.invalidate();				
				return "redirect:login.do";
			}
			leadEntity = (LeadEntity)session.getAttribute("leadDetails");
			isUpdate = contactService.saveResolution(contactDto,leadEntity.getCaseId()+"");
		}catch(Exception e){
			logger.error("ContactController | LeadEscalation | " + e.getMessage() + " | :-END");
		}finally{
			userEntity =null;
			contactDto = null;
		}
		logger.info("ContactController | leadEscalation() | :- END");
		if(!(isUpdate))
			return "error";
		return "workList";
	}

	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value={"leadEscalation"})	
	public String leadEscalation(@ModelAttribute ContactDto contactDto,HttpServletRequest request,HttpSession session,Model model){
		logger.info("ContactController | leadEscalation() | :- START");
		UserEntity userEntity;
		boolean isUpdate = false;
		LeadEntity leadEntity;
		try{
			userEntity = (UserEntity)session.getAttribute("UserDetails");
			if(userEntity==null){
				session.invalidate();					
				return "redirect:login.do";
			}
			leadEntity = (LeadEntity)session.getAttribute("leadDetails");
			isUpdate = contactService.saveResolution(contactDto,leadEntity.getCaseId()+"");
		}catch(Exception e){
			logger.error("ContactController | LeadEscalation | " + e.getMessage() + " | :-END");
		}finally{
			userEntity =null;
			contactDto = null;
		}
		logger.info("ContactController | leadEscalation() | :- END");
		if(!(isUpdate))
			return "error";
		return "redirect:contact.do?caseId=0";
	}


	@RequestMapping(method = {RequestMethod.GET,RequestMethod.POST},value ="help")
	public String  openHelp(HttpSession session,@RequestParam("helpType") String helpType,Model model) {
		logger.info("ContactController | openHelp() | helpType::"+helpType+" | :- START");
		UserEntity userEntity;
		LeadEntity leadEntity;
		int caseId;
		int userid;
		try{
			userEntity= (UserEntity)session.getAttribute("UserDetails");
			leadEntity= (LeadEntity)session.getAttribute("leadDetails");
			caseId= Integer.parseInt(leadEntity.getCaseId());
			userid= userEntity.getUserid();
			if(helpType.equals("escalate")){
				List<PersonListDto> managerList =  contactService.getAllPerson(caseId+"",userid+"","ESCALATECONTACT");
				model.addAttribute("managerList",managerList);
			}
			if(helpType.equals("refer")){
				List<PersonListDto> peerAndreportee =  contactService.getAllPerson(caseId+"",userid+"","REFERCONTACT");
				model.addAttribute("peerAndreportee",peerAndreportee);
			}
			if(helpType.equals("allocate")){
				List<PersonListDto> peerAndreportee =  contactService.getAllPerson(caseId+"",userid+"","ALLOCATECONTACT");
				model.addAttribute("peerAndreportee",peerAndreportee);
			}


		}catch(Exception e){
			logger.error("ContactController | openHelp() | "+e.getMessage()+" | :-END");
		}finally{
			userEntity = null;
			leadEntity = null;
		}
		logger.info("ContactController | openHelp() | helpType::"+helpType+" | :- END");
		return helpType;
	}	

	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value={"referCase"})	
	public String takeHelp(@ModelAttribute ContactDto contactDto,HttpServletRequest request,HttpSession session,Model model){
		logger.info("ContactController | takeHelp() | :- START");
		LeadEntity leadEntity;
		int caseId;
		UserEntity userEntity;
		boolean isUpdate = false;
		try{
			leadEntity= (LeadEntity)session.getAttribute("leadDetails");

			caseId= Integer.parseInt(leadEntity.getCaseId());

			userEntity= (UserEntity)session.getAttribute("UserDetails");
			if(userEntity==null){
				session.invalidate();				
				return "redirect:login.do";
			}
			contactDto.setCaseId(caseId+"");
			contactDto.setUserId(userEntity.getUserid()+"");

			isUpdate= contactService.saveHelpRequested(contactDto);
		}catch(Exception e){
			logger.error("ContactController | takeHelp() | " + e.getMessage() + " | :-END"); 
		}finally{
			leadEntity = null;
			userEntity = null;
		}
		logger.info("ContactController | takeHelp() | :- END");
		if(!(isUpdate))
			return "error";
		return "redirect:contact.do?caseId=0";
	}




	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value={"cescalate","ccoallocate","crefer"})	
	public String cancelHelp(HttpServletRequest request,HttpSession session,Model model){
		logger.info("ContactController | cancelHelp() | :- START");		
		logger.info("ContactController | cancelHelp() | :- END");
		return  "redirect:contact.do?caseId=0";			
	}

}