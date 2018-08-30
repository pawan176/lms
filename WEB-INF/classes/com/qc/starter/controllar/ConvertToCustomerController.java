package com.qc.starter.controllar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.qc.starter.entity.LeadEntity;
import com.qc.starter.entity.UserEntity;
import com.qc.starter.service.ContactService;
import com.qc.starter.service.ConvertToCustomerService;

@Controller
public class ConvertToCustomerController {
	
	private static final Logger logger = Logger.getLogger(ContactController.class.getName());
	@Autowired	ConvertToCustomerService convertToCustomerService;
	UserEntity userEntity;
	
	/*@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value={"convertToCustomer"})
	public String convertLeadToLoan(HttpSession session,@RequestParam("helpType") String helpType,Model model){
		logger.info("ConvertToCustomerController | convertLeadToLoan() | helpType::"+helpType+" | :- START");
		UserEntity userEntity;
		LeadEntity leadEntity;
		int caseId;
		try{			
			userEntity= (UserEntity)session.getAttribute("UserDetails");
			leadEntity= (LeadEntity)session.getAttribute("leadDetails");
			caseId= Integer.parseInt(leadEntity.getCaseId());
			convertToCustomerService.convertLeadToLoan(caseId+"",userEntity.getUserid()+"", userEntity.getCompanyId());
			
		}catch(Exception e){
			logger.error("ConvertToCustomerController | convertLeadToLoan |" +e.getMessage() +  " | :- END");
			e.printStackTrace();
		}finally{
			return "workList";
		}
		
		
	}*/

}
