package com.qc.starter.controllar;

import java.util.ArrayList;

import java.util.List;

import javax.servlet.http.HttpSession;


import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.qc.starter.basic.CommonUtils;
import com.qc.starter.dao.daoImpl.MasterForMobileDaoImpl;
import com.qc.starter.dto.AgentTravelDto;
import com.qc.starter.dto.DailyActivityDto;
import com.qc.starter.entity.UserEntity;
import com.qc.starter.service.MasterForMobileService;


@Controller
public class TravelSummaryController {
	/* New  Added by Anuj on 18-jan-2017 */
	private static final Logger logger = Logger.getLogger(MasterForMobileDaoImpl.class.getName());
	@Autowired  MasterForMobileService forMobileService;
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value="travelSummary")
	public String getWorklistPage(HttpSession session,Model model){
		UserEntity userEntity= (UserEntity)session.getAttribute("UserDetails");	
		if(userEntity==null){
			session.invalidate();

			return "redirect:login.do";		
		}

		return "travelsummary";
	}
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value="getSummary")
	public 	@ResponseBody 
	String getTravelSummary(HttpSession session,Model model,
			@RequestParam("fromDate")String fromDate,
			@RequestParam("toDate")String toDate,@RequestParam("actionName")String actionName){
		logger.info("controller | TravelSummaryController | getTravelSummary() | :- START");
		UserEntity userEntity= (UserEntity)session.getAttribute("UserDetails");	
		String responseJson;
		JSONObject object;
		List<AgentTravelDto> list=new ArrayList<AgentTravelDto>();
		AgentTravelDto agentTravelDto=null;
		if(userEntity==null){
			session.invalidate();

			return "redirect:login.do";		
		}
		String userId=userEntity.getUserid().toString(); 
		responseJson="{\"status\":\"F\",\"message\":\"PROCESS FAIL\"}";
		String requestJson="{\"identifier\":\"\",\"userId\":\""+userId+"\",\"actionName\":\""+actionName+"\",\"actionFromDate\":\""+fromDate+"\",\"actionToDate\":\""+toDate+"\"}";

		try {
			responseJson= forMobileService.getAgentSummary(requestJson);
			JSONObject obj=new JSONObject(responseJson);
			String status=obj.getString("status");
			if(status.equalsIgnoreCase("S")){
				object= obj.getJSONObject("MONTHLY");
				agentTravelDto=new AgentTravelDto();
				agentTravelDto.setUserName(object.getString("USERNAME"));
				agentTravelDto.setDistanceTravelled(CommonUtils.decimarlFormaterUpto2Places(object.get("DISTANCE_TRAVELLED")));
				agentTravelDto.setActionMonth(object.getString("ACTION_MONTH"));
				list.add(agentTravelDto);
				}
			Gson gsonObj=new Gson();
			responseJson=gsonObj.toJson(list);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("controller | TravelSummaryController | getTravelSummary() |  | Exception | " + e.getMessage() );
		}
		logger.info("controller | TravelSummaryController | getTravelSummary() |  :- END");
		return responseJson;
	}
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value="dailyActivity")
	public String getDailyPage(HttpSession session,Model model){
		UserEntity userEntity= (UserEntity)session.getAttribute("UserDetails");	
		if(userEntity==null){
			session.invalidate();

			return "redirect:login.do";		
		}

		return "dailyactivity";
	}
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value="getActivity")
	public 	@ResponseBody 
	String getDailyActivity(HttpSession session,Model model){
		logger.info("controller | TravelSummaryController | getDailyActivity() | :- START");
		UserEntity userEntity= (UserEntity)session.getAttribute("UserDetails");	
		String responseJson;
		String responsefortable="";
		String response="";
		AgentTravelDto agentTravelDto=null;
		List<AgentTravelDto> list=null;
		DailyActivityDto activityDto=null;
		List<DailyActivityDto> dailylist=null;
		if(userEntity==null){
			session.invalidate();

			return "redirect:login.do";		
		}
		String userId=userEntity.getUserid().toString(); 
		responseJson="{\"status\":\"F\",\"message\":\"PROCESS FAIL\"}";
		String requestJson="{\"identifier\":\"\",\"userId\":\""+userId+"\",\"actionName\":\"DAILY\",\"actionFromDate\":\"\",\"actionToDate\":\"\"}";

		try {
			responseJson= forMobileService.getAgentSummary(requestJson);
			JSONObject obj=new JSONObject(responseJson);
			String status=obj.getString("status");
			if(status.equalsIgnoreCase("S"))
			{
				agentTravelDto=new AgentTravelDto();
				list=new ArrayList<AgentTravelDto>();
				dailylist=new ArrayList<DailyActivityDto>();
				JSONArray data= obj.getJSONArray("dailyAgentSummary");
				for(int k=0;k<data.length();k++){
					JSONObject object=data.getJSONObject(k);
					agentTravelDto=new AgentTravelDto();
					agentTravelDto.setUserId(object.getString("USER_ID"));
					agentTravelDto.setUserName(object.getString("USER_NAME"));
					agentTravelDto.setNoOfCustomer(object.getString("NO_OF_CUSTOMER"));
					agentTravelDto.setLoginTime(object.getString("LOGINTIME"));
					agentTravelDto.setLogoutTime(object.getString("LOGOUTTIME"));
					agentTravelDto.setDistanceTravelled(CommonUtils.decimarlFormaterUpto2Places(object.getString("DISTANCE_TRAVELLED"))+" KM");
					agentTravelDto.setActionDate(object.getString("ACTION_DATE"));
					JSONArray array=object.getJSONArray("dailyTravelSummary");

					for(int i=0;i<array.length();i++){
						JSONObject arrayObject=array.getJSONObject(i);
						activityDto=new DailyActivityDto();						
						activityDto.setUserId(object.getString("USER_ID"));
						activityDto.setCustomerName(arrayObject.getString("CUSTOMER_NAME"));
						activityDto.setDispositionName(arrayObject.getString("DISPOSITION_NAME"));
						activityDto.setActionName(arrayObject.getString("ACTION_NAME"));
						activityDto.setActionLongitude(arrayObject.get("ACTION_LONGITUDE").toString());
						activityDto.setActionLatitude(arrayObject.get("ACTION_LATITUDE").toString());
						activityDto.setCaseId(arrayObject.get("CASE_ID").toString());
						dailylist.add(activityDto);
					}		    
					list.add(agentTravelDto);
				}
				session.setAttribute("locationList", dailylist);
				Gson gsonObj=new Gson();
				responsefortable=gsonObj.toJson(list);


			}
		} catch (Exception e) {
			e.printStackTrace();
			Gson gsonObj=new Gson();
			responsefortable=gsonObj.toJson(list);
			logger.error("controller | TravelSummaryController | getDailyActivity() |  | Exception | " + e.getMessage() );
		}
		logger.info("controller | TravelSummaryController | getDailyActivity() |  :- END");
		return responsefortable;
	}

	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value="getMap")
	public String getMap(HttpSession session,Model model,@RequestParam("userId")String userId){
		UserEntity userEntity= (UserEntity)session.getAttribute("UserDetails");	
		if(userEntity==null){
			session.invalidate();

			return "redirect:login.do";		
		}
		List<DailyActivityDto> list=(List<DailyActivityDto>) session.getAttribute("locationList");		
		List<DailyActivityDto> agentList = new ArrayList<DailyActivityDto>();		
		for(DailyActivityDto dailyuserData : list){
			if(CommonUtils.toString(dailyuserData.getUserId()).equals(userId)){
				agentList.add(dailyuserData);
			}
		}		
		model.addAttribute("location",agentList);		
		return "mapForActivity";
	}

}
