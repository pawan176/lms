package com.qc.starter.controllar;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.qc.starter.dto.DataTablesTO;
import com.qc.starter.dto.LeadHeaderDto;
import com.qc.starter.dto.LeadsSerchDto;
import com.qc.starter.dto.MasterDto;
import com.qc.starter.dto.PersonListDto;
import com.qc.starter.entity.LeadEntity;
import com.qc.starter.entity.UserEntity;
import com.qc.starter.service.ContactService;
import com.qc.starter.service.DashboardService;
import com.qc.starter.service.MasterService;
import com.qc.starter.service.WorkListService;

@Controller
public class WorkListController {
	private static final Logger logger = Logger.getLogger(WorkListController.class.getName());
	//private static ResourceBundle resource1=ResourceBundle.getBundle("ApplicationResource");

	@Autowired	MasterService masterService;
	@Autowired	WorkListService workListService;
	@Autowired	ContactService contactService;
	@Autowired  DashboardService dashboardService;

	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST }, value = "workList")
	public String getWorklistPage(HttpSession session, Model model) {
		logger.info("WorkListController | getWorklistPage() | :- START");
		UserEntity userEntity;
		MasterDto masterDto;
		List<PersonListDto> peerAndreportee;
		try {
			userEntity = (UserEntity) session.getAttribute("UserDetails");
			if (userEntity == null) {
				session.invalidate();

				return "redirect:login.do";
			}
			LeadEntity leadEntity = (LeadEntity)session.getAttribute("leadDetails");			
			List<UserEntity> myTeamList=dashboardService.getMyTeamList(userEntity.getUserid());
			masterDto = masterService.getMasters("ProductMasterEntity~SubQueueEntity~DispositionMaster~CaseActionEntity~SourceEntity~CampaignEntity-active='A'~StageEntity");
			peerAndreportee = contactService.getAllPerson("",userEntity.getUserid() + "", "peerAndreportee");
			model.addAttribute("masterDetail", masterDto);
			model.addAttribute("myTeamList",myTeamList);
			model.addAttribute("peerAndreportee", peerAndreportee);
		} catch (Exception e) {
			//e.printStackTrace();
			logger.error("WorklistController | " + e.getMessage() + "| :-END");
			return "error";
		}finally{
			userEntity = null;
			masterDto = null;
			peerAndreportee = null;
			session = null;
		}
		logger.info("WorkListController | getWorklistPage() | :- END");
		return "workList";
	}

	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST }, value = "getLeadsDetails")
	public @ResponseBody
	String getMyLeadDetails(HttpSession session,Model model,@ModelAttribute LeadsSerchDto leadsSerchDto,
			@RequestParam int iDisplayStart, @RequestParam int iDisplayLength, @RequestParam int sEcho) {
		logger.info("WorkListController | getMyLeadDetails() | :- START");
		String responseJson = null;
		DataTablesTO<LeadHeaderDto> dt = new DataTablesTO<LeadHeaderDto>();
		Gson gsonObj;
		UserEntity userEntity;
		List<LeadHeaderDto> list;
		int sizeOfList = 0;
		try {
			userEntity = (UserEntity) session.getAttribute("UserDetails");
			if (userEntity == null) {
				session.invalidate();

				return "redirect:login.do";
			} else {
				leadsSerchDto.setUserId(userEntity.getUserid().toString());
				leadsSerchDto.setCompany(userEntity.getCompanyId().toString());
				leadsSerchDto.setMaxResult(iDisplayStart);
				leadsSerchDto.setCurrentPosition(iDisplayLength);
				list = workListService.getLeadDetails(leadsSerchDto);
				if(leadsSerchDto.getRequestType().trim().equals("advancesearch") || leadsSerchDto.getRequestType().trim().equals("search") ){
					if(list!=null && list.size()>0){
						sizeOfList=list.get(0).getSizeOfList();
					}
				}else{
					sizeOfList = workListService.getLeadDetailsListSize(leadsSerchDto);
				}
				dt.setAaData(list); // this is the dataset reponse to client
				dt.setiTotalDisplayRecords(sizeOfList); // the total data in db for datatables to calculate page no. and position
				dt.setiTotalRecords(sizeOfList); // the total data in db for datatables to calculate page no.
				dt.setsEcho(sEcho);
				gsonObj=new Gson();
				responseJson=gsonObj.toJson(dt);
				//String jsonData=toJson1(dt);
				return responseJson;
			}
		} catch (Exception e) {
			//e.printStackTrace();
			logger.error("WorklistController |" + e.getMessage() + "| :-END");
		} finally {
			leadsSerchDto = null; gsonObj = null; userEntity = null; list = null;
			session = null;
		}
		logger.info("WorkListController | getMyLeadDetails() | :- END");
		return responseJson;
	}
	/*private String toJson1(DataTablesTO<?> dt) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String s=mapper.writeValueAsString(dt);  
			return mapper.writeValueAsString(dt);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}*/


	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST }, value = "saveAllocatedLead")
	public String saveAllocatedLead(HttpSession session, Model model,
			@RequestParam(value = "myArray") List inpList,
			@RequestParam("allocatedId") String allocatedId,
			@RequestParam("remark") String remark,
			@RequestParam("queueId") String queueId) {
		logger.info("WorkListController | saveAllocatedLead() | :- START");
		UserEntity userEntity;
		try{
			userEntity = (UserEntity) session
					.getAttribute("UserDetails");
			if (userEntity == null) {
				session.invalidate();

				return "redirect:login.do";
			}
			String isUpdate = workListService.saveAllocatedLead(inpList, remark,
					queueId, allocatedId);
			if(isUpdate==null){
				return "error";
			}

		}catch(Exception e){
			logger.error("WorklistController | "+e.getMessage()+" | :-END");
		}finally{
			inpList = null;	allocatedId = null; remark = null; queueId = null;
			userEntity = null; session = null;
		}

		logger.info("WorkListController | saveAllocatedLead() | :- END");
		return "workList";
	}
	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST }, value = "setData")
	public @ResponseBody
	String setData(HttpSession session,@ModelAttribute LeadsSerchDto leadsSerchDto,Model model) {
		logger.info("WorkListController | setData() | :- START");
		UserEntity userEntity;
		try{
			userEntity = (UserEntity) session
					.getAttribute("UserDetails");
			if (userEntity == null) {
				session.invalidate();

				return "redirect:login.do";
			}
			String dashboardData="queue="+leadsSerchDto.getQueue()+"~subqueue="+leadsSerchDto.getSubqueue()+"" +
					"~allocate="+leadsSerchDto.getTeam()+"~source="+leadsSerchDto.getSource()+"" +
					"~campaign="+leadsSerchDto.getCampaign()+"";

			session.setAttribute("dashboardRequest","requestType=advancesearch&mobile=&email=&name=&caseCode=&queue="
					+leadsSerchDto.getQueue()+"&subqueue="+leadsSerchDto.getSubqueue()
					+"&disposition=&allocate="+leadsSerchDto.getTeam()+"&amountTo=&amountFrom=&source="
					+leadsSerchDto.getSource()+"&sort1=&sort2=&sort3=&sortOrder=&escalationRef=MYLEAD&campaign="+leadsSerchDto.getCampaign());
			session.setAttribute("dashboardRequest1",dashboardData);

		}catch(Exception e){
			logger.error("WorklistController | "+e.getMessage()+" | :-END");
		}finally{

			userEntity = null; session = null;
		}

		return null;

	}
	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST }, value = "getLeadsDetailsFromDashboard")
	public
	String getLeadsDetailsFromDashboard(HttpSession session,@RequestParam( value="id") String id,Model model) {

		logger.info("WorkListController | getLeadsDetailsFromDashboard() | :- START");			
		UserEntity userEntity;		
		MasterDto masterDto;
		List<PersonListDto> peerAndreportee;		
		try {
			userEntity = (UserEntity) session.getAttribute("UserDetails");
			if (userEntity == null) {
				session.invalidate();

				return "redirect:login.do";
			} else {

				String dashboardRequest=(String) session.getAttribute("dashboardRequest");
				String dashboardRequest1=(String) session.getAttribute("dashboardRequest1");
				dashboardRequest=dashboardRequest+"&actionId="+"'"+id+"'";
				session.setAttribute("dashboardRequest",dashboardRequest);
				dashboardRequest1=dashboardRequest1+"~actionId="+"'"+id+"'";
				session.setAttribute("dashboardRequest1",dashboardRequest1);
				List<UserEntity> myTeamList=dashboardService.getMyTeamList(userEntity.getUserid());				
				//masterDto = masterService.getMasters("ProductMasterEntity~SubQueueEntity~DispositionMaster~CaseActionEntity-actionType = '1' and companyId = '1000000001'~SourceEntity~CampaignEntity-active='A'");
				masterDto = masterService.getMasters("ProductMasterEntity~SubQueueEntity~DispositionMaster~CaseActionEntity-ACTION_TYPE = '1' and COMPANY_ID = '1000000001'~SourceEntity~CampaignEntity-active='A'~StageEntity");
				peerAndreportee = contactService.getAllPerson("",userEntity.getUserid() + "", "peerAndreportee");
				model.addAttribute("masterDetail", masterDto);
				model.addAttribute("peerAndreportee", peerAndreportee);
				model.addAttribute("dataFromDashboard","Dashboard");
				model.addAttribute("dashboardRequest",dashboardRequest);
				model.addAttribute("myTeamList",myTeamList);
			}
		} catch (Exception e) {
			logger.error("WorklistController |" + e.getMessage() + "| :-END");
		} finally {
			userEntity = null;
			session = null;
		}
		logger.info("WorkListController | getLeadsDetailsFromDashboard() | :- END");
		return "workList";
	}

	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST }, value = "getLeadStateValue")
	public @ResponseBody
	String getLeadStateValue(HttpSession session,@RequestParam(value="disposition") String disposition,Model model) {

		logger.info("WorkListController | getLeadStateValue() | :- START");

		UserEntity userEntity;
		String leadStates = null;
		try {
			userEntity = (UserEntity) session.getAttribute("UserDetails");
			if (userEntity == null) {
				session.invalidate();

				return "redirect:login.do";
			} else {
				leadStates = workListService.getLeadStatelist(disposition);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("WorklistController |" + e.getMessage() + "| :-END");
		} finally {
			userEntity = null;
			session = null;
		}
		logger.info("WorkListController | getMyLeadDetails() | :- END");
		return leadStates;
	}

}
