package com.qc.starter.controllar;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qc.starter.basic.LeadManager;
import com.qc.starter.entity.UserEntity;
@Controller
public class FlushLeadLockController {
	private static final Logger logger = Logger.getLogger(FlushLeadLockController.class.getName());
	//private static ResourceBundle resource1=ResourceBundle.getBundle("ApplicationResource");
	@RequestMapping(method = {RequestMethod.GET,RequestMethod.POST}, value = { "flushLeadLock" })
	public
	@ResponseBody
	String flushLeadLock(HttpSession session){
		logger.info("FlushLeadLockingController | flushLeadLock() | :- START");
		UserEntity userEntity= (UserEntity)session.getAttribute("UserDetails");
		if(userEntity==null){
			session.invalidate();
			return "redirect:login.do";
		}
		//LeadEntity leadEntity = (LeadEntity)session.getAttribute("leadDetails");
		LeadManager leadManager = LeadManager.getInstance();
		boolean flag=leadManager.removeLead(userEntity.getUserid()+"",session.getId());
		logger.info("FlushLeadLockingController | flushLeadLock() | :- END");
		return "";
	}
}