package com.qc.starter.controllar;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.qc.starter.basic.CommonUtils;
import com.qc.starter.dto.ContactDto;
import com.qc.starter.dto.MasterDto;
import com.qc.starter.dto.NewLeadDto;
import com.qc.starter.dto.PersonListDto;
import com.qc.starter.entity.PinCodeEntity;
import com.qc.starter.entity.UserEntity;
import com.qc.starter.service.ActionService;
import com.qc.starter.service.ContactService;
import com.qc.starter.service.LeadService;
import com.qc.starter.service.LoginService;
import com.qc.starter.service.MasterService;
import com.qc.starter.service.NewLeadService;

@Controller
public class NewLeadController {

	private static final Logger logger = Logger.getLogger(NewLeadController.class.getName());
	@Autowired NewLeadService newLeadService;
	@Autowired MasterService masterService;
	@Autowired ContactService contactService;
	@Autowired ActionService actionService;
	@Autowired LeadService leadService;
	
	@RequestMapping(method = {RequestMethod.GET,RequestMethod.POST},value ="newlead")
	public String showNewLead(HttpSession session,Model model) {		
		logger.info("NewLeadController | showNewLead() | :- START");
		UserEntity userEntity= (UserEntity)session.getAttribute("UserDetails");    	
		if(userEntity==null){
			session.invalidate();			
			return "redirect:login.do";
		}
		else{			
			List<PersonListDto> peerAndreportee =  contactService.getAllPerson("",userEntity.getUserid()+"","NEWLEADALLOCATE");
			MasterDto masterDto = masterService.getMasters("MaritalStatusEntity~GenderEntity~NationalityEntity~OccupationEntity~CompanyTypeEntity~SalaryModeEntity~StateMasterEntity~OccupancyStatusEntity~CaseContactEntity~PropertyTypeEntity~PropertyStatusEntity~AddressTypeEntity~ContactTypeEntity-CONTACT_CATEGORY='EMAIL'~ContactTypeEntity-CONTACT_CATEGORY='MOBILE'~ProductMasterEntity~CampaignEntity~SourceEntity~SubQueueEntity-SUB_QUEUE_ID NOT IN(4,9)~EntityType~Industry~CustomerCategory~SectorEntity~StageEntity~PurposeOfLoan~BranchMaster~TitleEntity");
			String businessDate = leadService.getBusinessDate();
			model.addAttribute("customerMasterDetail", masterDto);
			model.addAttribute("allocateList",peerAndreportee);
			model.addAttribute("businessDate",businessDate);			
			logger.info("NewLeadController | showNewLead() | :- END");
			return "newlead";  
		}
	}

	@InitBinder
	public void dateBinderForSave(WebDataBinder binder){
		logger.info("CustomerController | dateBinder() | :- START");
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class,"dob", new CustomDateEditor(dateFormat, true));
		binder.registerCustomEditor(Date.class,"incorporationDate", new CustomDateEditor(dateFormat, true));
		logger.info("CustomerController | dateBinder() | :- END");
	}

	@RequestMapping(method = {RequestMethod.GET,RequestMethod.POST},value ={"manageNewLead", "seNewLead"})
	public String manageNewLead(@ModelAttribute NewLeadDto newLeadDto,HttpSession session,Model model,HttpServletRequest request, @RequestParam String requestType) {		
		logger.info("NewLeadController | manageNewLead() | :- START");
		UserEntity userEntity = (UserEntity)session.getAttribute("UserDetails");
		if(userEntity==null){
			session.invalidate();
			
			return "redirect:login.do";
		}
		else{
			try{
				newLeadDto.setUserId(userEntity.getUserid()+"");
				newLeadDto.setCompanyId(userEntity.getCompanyId());
				newLeadDto.setAppName("WEB");
				String leadResponse = newLeadService.createLead(newLeadDto);
				String[] detail=null;
				String leadid=null;
				String error=null;
				String message=null;

				
				if(leadResponse !=null && !(leadResponse.equals("")))
					detail = leadResponse.split("#");
				if(detail !=null && detail.length > 0)
					leadid = detail[1];
				if(detail !=null && detail.length > 1)
					error  = detail[2];
				if(detail !=null && detail.length > 2)
					message = detail[3];
				
				String contactLead = leadid.replaceAll("PR","10");
				ContactDto contactDto = new ContactDto();
				contactDto.setCaseId(contactLead);
				contactDto.setActionId("19");
				contactDto.setDisposition("19");
				contactDto.setFollowupAction("1000000001");
				contactDto.setLeadStage("1000000001");
				contactDto.setPotential(newLeadDto.getQueueId());
				contactDto.setActionDate(CommonUtils.getCurrentDateInDDMMMYYY());
				contactDto.setActionTime("12:00AM");
				contactDto.setUserId(userEntity.getUserid()+"");
				contactDto.setRemarks("New Lead is generated From Web");
				
				String newCaseId = actionService.saveCaseAction(contactDto);
				
				
				
				

				model.addAttribute("leadid",leadid);
				model.addAttribute("error",error);
				model.addAttribute("errorMessage",message);
				//request.setAttribute("leadid",leadid);
				session.setAttribute("leadid",leadid);
				session.setAttribute("error",error);
				session.setAttribute("errorMessage",message);

				MasterDto masterDto = masterService.getMasters("MaritalStatusEntity~GenderEntity~NationalityEntity~OccupationEntity~CompanyTypeEntity~SalaryModeEntity~StateMasterEntity~OccupancyStatusEntity~CaseContactEntity~PropertyTypeEntity~PropertyStatusEntity~AddressTypeEntity~ContactTypeEntity-CONTACT_CATEGORY='EMAIL'~ContactTypeEntity-CONTACT_CATEGORY='MOBILE'~ProductMasterEntity~CampaignEntity~SourceEntity~SubQueueEntity-SUB_QUEUE_ID NOT IN(4,9)~EntityType~Industry~CustomerCategory~TypeOfBusinessEntity~ClusterEntity~PurposeOfLoan");
				List<PersonListDto> peerAndreportee =  contactService.getAllPerson("",userEntity.getUserid()+"","peerAndreportee");
				model.addAttribute("customerMasterDetail", masterDto);
				model.addAttribute("allocateList",peerAndreportee);
				logger.info("NewLeadController | manageNewLead() | :- END");

				if(requestType != null && requestType.equalsIgnoreCase("save"))
					return "redirect:newlead.do";

				if(newLeadDto!=null && newLeadDto.getNewleadType()!=null && newLeadDto.getNewleadType().equals("quicklead"))
					return "redirect:workList.do";

				return "redirect:workList.do";
			}catch(Exception e){

				logger.error("NewLeadController | " + e.getMessage() + "| :-END");
				e.printStackTrace();
				return "error";

			}
		}
	}
	
	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST }, value = "getPinCode")
	public @ResponseBody
	String getPinCode(HttpSession session,@RequestParam(value="pinCode") String pinCode) {

		logger.info("WorkListController | getCitiesByState() | :- START");
		String pinCodeJson = "";
		List<PinCodeEntity> pinCodeList;
		try {
			pinCodeList = masterService.getPinCode(pinCode);
			Gson gson = new Gson();
			pinCodeJson = gson.toJson(pinCodeList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("WorklistController |" + e.getMessage() + "| :-END");
		} finally {
			session = null;
		}
		logger.info("WorkListController | getCitiesByState() | :- END");
		return pinCodeJson;
	}


}