package com.qc.starter.controllar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.qc.starter.basic.CommonUtils;
import com.qc.starter.basic.ManageUser;
import com.qc.starter.dto.AuthorizedProcDto;
import com.qc.starter.dto.MasterDto;
import com.qc.starter.dto.PasswordChangeDto;
import com.qc.starter.dto.PersonListDto;
import com.qc.starter.dto.UserDto;
import com.qc.starter.entity.UserEntity;
import com.qc.starter.service.ContactService;
import com.qc.starter.service.DashboardService;
import com.qc.starter.service.LeadService;
import com.qc.starter.service.LoginService;
import com.qc.starter.service.MasterService;
import com.qc.starter.service.UserService;
//import com.qc.starter.dto.AuthorizedProcDto;

@Controller
public class LoginController {
	private static final Logger logger = Logger.getLogger(LoginController.class.getName());
	@Autowired	LoginService loginService;
	@Autowired	LeadService leadService;
	@Autowired	HttpSession session;
	@Autowired	MasterService masterService;
	@Autowired	ContactService contactService;
	@Autowired	UserService userService;
	@Autowired ManageUser manageUser;
	@Autowired DashboardService dashboardService;

	//private static ResourceBundle resource=ResourceBundle.getBundle("multipleUserConfigration");
	private static ResourceBundle resource1=ResourceBundle.getBundle("ApplicationResource");

	@RequestMapping(method = RequestMethod.GET, value = { "login" })
	public String showLoginPage(Model model,HttpSession session) {
		logger.info("LoginController | showLoginPage() | :- START");
		logger.info("LoginController | showLoginPage() | :- END");	
		return "login";
	}

	@RequestMapping(method = {RequestMethod.GET,RequestMethod.POST}, value = { "logout" })
	public String logOut(HttpServletRequest request,Model model){
		logger.info("LoginController | logOut() | :- START");
		String ipAddress=request.getRemoteAddr() == null ? "" : request.getRemoteAddr().toString();
		HttpSession session = request.getSession();
		UserEntity userEntity=(UserEntity)session.getAttribute("UserDetails");
		try{
			userService.logOutExit(userEntity, ipAddress);				
			manageUser.removeUser(userEntity);
		}catch(Exception e){
			logger.error("Logout Controller | "+ e.getMessage() + ":-END");
		}finally{
			userEntity = null;
		}
		session.invalidate();
		logger.info("LoginController | logOut() | :- END");
		return "redirect:login.do";
	}

	@RequestMapping(method = {RequestMethod.POST,RequestMethod.GET}, value = { "loginAutentication" })
	public String loginAutentication(@ModelAttribute UserDto userDto, Model model,HttpServletRequest request) {
		logger.info("LoginController | loginAutentication() | :- START  ::  WITH USerDto::"+userDto);
		HttpSession session = request.getSession();
		MasterDto masterDto;
		List<PersonListDto> peerAndreportee;
		UserEntity userEntity;
		try{
			Object isOld = session.getAttribute( "sessionId" );
			if ( isOld != null )
			{
				session.invalidate( ); // Recreate session
				session = request.getSession();
			}

			if(CommonUtils.toString(userDto.getPassword()).trim().equals("")){
				model.addAttribute("errors","Username and Password should be Specified!");				
				return "login";
			}
			
			if(CommonUtils.toString(userDto.getUserName()).trim().equals("")){
				model.addAttribute("errors","Username and Password should be Specified!");				
				return "login";
			}

			if(loginService == null){
				
				return "login";
			}
			//----------procChanges---------------------
			userDto.setIpaddress(request.getRemoteAddr() == null ? "" : request.getRemoteAddr().toString());
			AuthorizedProcDto  authorizedProcDto=loginService.userAthentication(userDto);
			if(authorizedProcDto.getAuthStatus() == null || authorizedProcDto.getAuthStatus().equalsIgnoreCase("N")){
				model.addAttribute("errors","UserName or Password do not Match");				
				return "login";
			}
			/*if(!(authorizedProcDto.getAppError().equalsIgnoreCase("Success")) && authorizedProcDto.getAuthStatus().equalsIgnoreCase("Y")){
				model.addAttribute("ExpireMsg",authorizedProcDto.getAppError());
			}*/
			//--------------------------------------------
			userEntity = loginService.userLogin(userDto);
			if (userEntity != null) {
				String loginName1="";
				String loginName2="";
				String userName=userEntity.getLoginname().trim();
				if(!userName.equals("")){
					int userNameLength=userName.length();
					userEntity.setLoginname("");
					loginName1=userName.substring(0,userNameLength/2);
					loginName2=userName.substring(userNameLength/2,userNameLength);
					userEntity.setLogin1(loginName1);
					userEntity.setLogin2(loginName2);					
				}

				int notificationCount=loginService.countNotification(userEntity.getUserid()+"", userEntity.getCompanyId());
				session.setAttribute( "notificationCount", notificationCount);
				session.setAttribute( "sessionId", session.getId() );
				session.setAttribute("UserDetails", userEntity);

				MasterDto quickLeadMasters = masterService.getMasters("ProductMasterEntity~CampaignEntity~SourceEntity~SubQueueEntity");
				session.setAttribute("quickLeadMasters",quickLeadMasters);
				List<PersonListDto> allocatedToNewLead =  contactService.getAllPerson("",userEntity.getUserid()+"","NEWLEADALLOCATE");
				session.setAttribute("allocatedToNewLead",allocatedToNewLead);
				List<UserEntity> myTeamList=dashboardService.getMyTeamList(userEntity.getUserid());

				if( manageUser.getUsers(userEntity) ){
					model.addAttribute("errors","RequestNewSession");						
					return "login";
				}else{
					manageUser.setUsers(userEntity,session);
				}	

				//Changes by Deepak on 18 Oct 2016--------------
				Map<String,String> menuList=loginService.getMenuItems(userEntity.getUserid()+"");
				session.setAttribute("menuList",new ArrayList<String>(menuList.values()));
				session.setAttribute( "sessionId", session.getId() );	
				masterDto = masterService.getMasters("ProductMasterEntity~SubQueueEntity~DispositionMaster~CaseActionEntity-ACTION_TYPE = '1' and COMPANY_ID = '"+userEntity.getCompanyId()+"'~SourceEntity~CampaignEntity-active='A'");
				//masterDto = masterService.getMasters("ProductMasterEntity~SubQueueEntity~DispositionMaster~CaseActionEntity-actionType = '1' and companyId = '"+userEntity.getCompanyId()+"'~SourceEntity~CampaignEntity-active='A'");
				session.setAttribute("UserId",userEntity.getUserid());
				peerAndreportee =  contactService.getAllPerson("",userEntity.getUserid()+"","peerAndreportee");
				model.addAttribute("peerAndreportee",peerAndreportee);
				model.addAttribute("myTeamList",myTeamList);
				model.addAttribute("masterDetail", masterDto);


				logger.info("LoginController | loginAutentication() | :- END");
				return "workList";
			} else {
				model.addAttribute("errors","UserName or Password do not Match");				
				return "login";
			}
		}catch(Exception e){
			logger.error("LoginController | error Occurred |" + e.getMessage() +" | :-END");
			//e.printStackTrace();			
			return "login";
		}finally{
			masterDto = null;
			peerAndreportee = null;
			userEntity = null;
			userDto = null;
		}		
	}



	@RequestMapping(method = {RequestMethod.POST,RequestMethod.GET}, value = { "openNewSession" })
	public String openNewSession(Model model,HttpServletRequest request,HttpSession session) {
		logger.info("LoginController | openNewSession() | :- START  ::  WITH USerDto::");
		List<PersonListDto> peerAndreportee;
		UserEntity userEntity ;
		MasterDto masterDto;
		try{
			userEntity = (UserEntity) session.getAttribute("UserDetails");

			boolean destroy = manageUser.destroyOldSession(userEntity);
			if(destroy){
				manageUser.removeUser(userEntity);	
			}			
			manageUser.setUsers(userEntity,session);

			if (userEntity != null) {
				//added by sandeep
				//Changes by Deepak on 26 Dec 2016--------------
				Map<String,String> menuList=loginService.getMenuItems(userEntity.getUserid()+"");
				session.setAttribute("menuList",new ArrayList<String>(menuList.values()));
				//-------------------------------------
				session.setAttribute( "sessionId", session.getId() );
				session.setAttribute("UserId",userEntity.getUserid());
				//end
				List<UserEntity> myTeamList=dashboardService.getMyTeamList(userEntity.getUserid());
				masterDto = masterService.getMasters("ProductMasterEntity~SubQueueEntity~DispositionMaster~CaseActionEntity-actionType = '1' and companyId = '"+userEntity.getCompanyId()+"'~SourceEntity~CampaignEntity-active='A'");
				peerAndreportee =  contactService.getAllPerson("",userEntity.getUserid()+"","peerAndreportee");
				model.addAttribute("peerAndreportee",peerAndreportee);
				model.addAttribute("myTeamList",myTeamList);
				model.addAttribute("masterDetail", masterDto);

			}
		}catch(Exception e){
			logger.error("LoginController | openNewSession() | error Occurred |" + e.getMessage() +" | :-END");
			e.printStackTrace();		
			return "login";
		}finally{
			userEntity = null;
		}
		return "workList";
	}
	@RequestMapping(method = {RequestMethod.GET,RequestMethod.POST}, value = { "changepwd" })
	public String ChangePwd(HttpServletRequest request,Model model){
		logger.info("LoginController | logOut() | :- START");
		String ipAddress=request.getRemoteAddr() == null ? "" : request.getRemoteAddr().toString();
		try{			
			return "changepassword";		
		}catch(Exception e){
			logger.error("LoginController | ChangePwd() "+ e.getMessage() + ":-END");
		}finally{
		}
		logger.info("LoginController | ChangePwd() | :- END");
		return "changepassword";
	}

	@RequestMapping(method = {RequestMethod.GET,RequestMethod.POST}, value = { "changepwdaction" })
	public String ChangePwdAction(@ModelAttribute PasswordChangeDto passwordChangeDto,HttpServletRequest request,Model model){
		logger.info("LoginController | logOut() | :- START");
		String ipAddress=request.getRemoteAddr() == null ? "" : request.getRemoteAddr().toString();
		try{			


			HttpSession session = request.getSession();
			UserEntity userEntity=(UserEntity)session.getAttribute("UserDetails");
			if (userEntity == null) {
				session.invalidate();			
				return "redirect:login.do";

			}
						
			
			String status = loginService.changePassword(userEntity, passwordChangeDto);
			
			if(status.equals("FAILURE")){
				model.addAttribute("Failure","Password Can not be changed, As your current password is not valid ");
				return "changepassword";
			}
			
			String[] result = status.split("~");
			String statusFlag = result[0];
			String message = result[1];	
			if(statusFlag.equals("F")){
				model.addAttribute("Failure","Password Can not be changed due to "+message);
				return "changepassword";
			}else{
				model.addAttribute("ChangePassMsg","Password has been changed, Please Login again with New Password");
				return "login";
			}
		
			
			
			}
			
		
		
			
			catch(Exception e){
			logger.error("LoginController | ChangePwd() "+ e.getMessage() + ":-END");
		}finally{
		}
		logger.info("LoginController | ChangePwd() | :- END");
		return "changepassword";
	}


}