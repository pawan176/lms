package com.qc.starter.controllar;

import java.util.ResourceBundle;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.qc.starter.entity.UserEntity;
import com.qc.starter.service.UserService;

@Controller
public class UserController {
	private static final Logger logger = Logger.getLogger(UserController.class.getName());
	private static ResourceBundle resource1=ResourceBundle.getBundle("ApplicationResource");
	@Autowired UserService userService;

	@RequestMapping(method=RequestMethod.GET,value="userlogin")
	public String MangeUser(HttpSession session,Model model){
		logger.info("UserController | MangeUser() | :- START");
		//String response = userService.getUser();
		UserEntity userEntity= (UserEntity)session.getAttribute("UserDetails");
		logger.info("UserController | MangeUser() | :- END");
		if(userEntity==null){
			session.invalidate();
			
			return "redirect:login.do";
		}
		else{
			
			return "login";
		}
	}

}