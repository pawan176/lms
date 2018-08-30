package com.qc.starter.controllar;

import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.qc.starter.dto.DashboardDto;
import com.qc.starter.dto.LeadsSerchDto;
import com.qc.starter.dto.MasterDto;
import com.qc.starter.entity.CampaignEntity;
import com.qc.starter.entity.ProductEntity;
import com.qc.starter.entity.UserEntity;
import com.qc.starter.service.DashboardService;
import com.qc.starter.service.MasterService;

@Controller
public class DashboardController {
	private static final Logger logger = Logger.getLogger(DashboardController.class.getName());
	private static ResourceBundle resource1=ResourceBundle.getBundle("ApplicationResource");

	@Autowired
	DashboardService dashboardService;

	@Autowired	MasterService masterService;

	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value="dashboard")
	public String getDashboardPage(HttpSession session,Model model) {
		logger.info("DashboardController | getDashboardPage() | :- START");
		UserEntity userEntity= (UserEntity)session.getAttribute("UserDetails");
		MasterDto masterDto;
		if(userEntity==null){
			session.invalidate();
		
			return "redirect:login.do";
		}

		List<ProductEntity> productList=dashboardService.getproductList(userEntity.getUserid()+"");
		model.addAttribute("productMasterDetail",productList);	
		//masterDto = masterService.getMasters("ProductMasterEntity~SubQueueEntity~DispositionMaster~CaseActionEntity-actionType = '1' and companyId = '1000000001'~SourceEntity");
		masterDto = masterService.getMasters("ProductMasterEntity~SubQueueEntity~DispositionMaster~CaseActionEntity-ACTION_TYPE = '1' and COMPANY_ID = '1000000001'~SourceEntity");
		model.addAttribute("masterDetail", masterDto);

		List<CampaignEntity> companignList=dashboardService.getCompaignList();
		List<UserEntity> myTeamList=dashboardService.getMyTeamList(userEntity.getUserid());
		model.addAttribute("myTeamList",myTeamList);

		model.addAttribute("companignList",companignList);
		logger.info("DashboardController | getDashboardPage() | :- END");
		return "dashboard";

	}
	@RequestMapping(method={RequestMethod.POST},value="getMyleadListState1")
	public @ResponseBody
	String getMyleadListState1(HttpSession session,Model model,
			@RequestParam("requestType") String requestType,@RequestParam("source") String sourceMultiple,
			@RequestParam("subqueue") String subqueueMultiple,@RequestParam("queue") String queueMultiple,
			@RequestParam("campaign") String compaignMultiple,@RequestParam("team") String teamMultiple) {
		logger.info("DashboardController | getMyleadListState1() | :- START");
		UserEntity userEntity= (UserEntity)session.getAttribute("UserDetails");
		if(userEntity==null){
			session.invalidate();
		
			return "redirect:login.do";
		}

		LeadsSerchDto serchDto=new LeadsSerchDto();
		serchDto.setRequestType(requestType);
		serchDto.setQueue(queueMultiple);
		serchDto.setSubqueue(subqueueMultiple);
		serchDto.setSource(sourceMultiple);
		serchDto.setAllocate(teamMultiple);
		serchDto.setName(compaignMultiple);
		serchDto.setUserId(userEntity.getUserid().toString());


		List<DashboardDto> myLeadList=dashboardService.getdashboardData(serchDto);	

		String response="";
		int c=0;
		for(DashboardDto obj:myLeadList){
			if(c==0){
				response=obj.getActionStage();
				c++;
			}else
				response=response+"~"+obj.getActionStage();
		}
		/*Gson gsonObj=new Gson();
		String responseJson=gsonObj.toJson(myLeadList);
		 */
		logger.info("DashboardController | getMyleadListState1() | :- END");

		return response;		
	}
	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.POST, value = { "getMyTeamProductList" })
	public @ResponseBody
	String getMyTeamProductList(@RequestParam(value = "teamId") String teamId)
	{
		logger.info("DashboardController | getMyTeamProductList() | :- START");
		List<ProductEntity> productList=dashboardService.getproductList(teamId);			
		Gson gson = new Gson();
		String responseJson = gson.toJson(productList);	
		logger.info("DashboardController | getMyTeamProductList() | :- END");
		return responseJson;
	}

	@RequestMapping(method={RequestMethod.POST},value="getMyTeamLeadList")
	public @ResponseBody
	String getMyTeamLeadList(HttpSession session,Model model,
			@RequestParam("teamId") String teamId,@RequestParam("productId") String productId) {
		logger.info("DashboardController | getMyTeamLeadList() | :- START");
		UserEntity userEntity= (UserEntity)session.getAttribute("UserDetails");
		if(userEntity==null){
			session.invalidate();
		
			return "redirect:login.do";
		}				
		List<DashboardDto> myLeadList=dashboardService.getMyTeamListState(productId,teamId);
		Gson gsonObj=new Gson();
		String responseJson=gsonObj.toJson(myLeadList);
		logger.info("DashboardController | getMyTeamLeadList() | :- END");
		return responseJson;		
	}
	@RequestMapping(method={RequestMethod.POST},value="getDayDataForDashboard")
	public @ResponseBody
	String getDayDataForDashboard(HttpSession session,Model model,
			@RequestParam("date") String date) {
		logger.info("DashboardController | getDayDataForDashboard() | :- START");
		UserEntity userEntity= (UserEntity)session.getAttribute("UserDetails");
		if(userEntity==null){
			session.invalidate();
			
			return "redirect:login.do";
		}				
		List<DashboardDto> dayList=dashboardService.getDayDataForLead(userEntity.getUserid()+"",date);
		Gson gsonObj=new Gson();
		String responseJson=gsonObj.toJson(dayList);
		logger.info("DashboardController | getDayDataForDashboard() | :- END");
		return responseJson;		
	}
	@RequestMapping(method={RequestMethod.POST},value="getMonthDataForDashboard")
	public @ResponseBody
	String getMonthDataForDashboard(HttpSession session,Model model,
			@RequestParam("date") String date) {
		logger.info("DashboardController | getMonthDataForDashboard() | :- START");
		UserEntity userEntity= (UserEntity)session.getAttribute("UserDetails");
		if(userEntity==null){
			session.invalidate();
		
			return "redirect:login.do";
		}				
		List<DashboardDto> dayList=dashboardService.getMonthDataForLead(userEntity.getUserid()+"",date);
		Gson gsonObj=new Gson();
		String responseJson=gsonObj.toJson(dayList);
		logger.info("DashboardController | getMonthDataForDashboard() | :- END");
		return responseJson;		
	}
	@RequestMapping(method={RequestMethod.POST},value="getWeekDataForDashboard")
	public @ResponseBody
	String getWeekDataForDashboard(HttpSession session,Model model,@RequestParam("date") String date) {
		logger.info("DashboardController | getWeekDataForDashboard() | :- START");
		UserEntity userEntity= (UserEntity)session.getAttribute("UserDetails");
		if(userEntity==null){
			session.invalidate();
		
			return "redirect:login.do";
		}				
		List<DashboardDto> dayList=dashboardService.getWeekDataForLead(userEntity.getUserid()+"",date);
		Gson gsonObj=new Gson();
		String responseJson=gsonObj.toJson(dayList);
		logger.info("DashboardController | getWeekDataForDashboard() | :- END");
		return responseJson;		
	}

}