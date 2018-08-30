package com.qc.starter.controllar;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

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

import com.qc.starter.dto.CustomerDto;
import com.qc.starter.entity.EmailEntity;
import com.qc.starter.entity.MobileEntity;
import com.qc.starter.entity.UserEntity;
import com.qc.starter.service.EmailService;
import com.qc.starter.service.MobileService;

@Controller
public class CustomerContactController {
	private static final Logger logger = Logger.getLogger(CustomerContactController.class.getName());
//	private static ResourceBundle resource1=ResourceBundle.getBundle("ApplicationResource");
	@Autowired MobileService mobileService;
	@Autowired EmailService emailService;
	@Autowired HttpSession httpSession;

	@InitBinder
	public void dateBinder(WebDataBinder binder){
		logger.info("CustomerController | dateBinder() | :- START");
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		dateFormat.setLenient(true);
		binder.registerCustomEditor(Date.class,"dob", new CustomDateEditor(dateFormat, true));
		binder.registerCustomEditor(Date.class,"incorporationDate", new CustomDateEditor(dateFormat, true));
		logger.info("CustomerController | dateBinder() | :- END");
	}
	@RequestMapping(value={"auContact"}, method=RequestMethod.POST)
	public String addUpdateContact(@ModelAttribute CustomerDto customerDto, @RequestParam String caseId, @RequestParam String requestType,Model model){
		logger.info("CustomerContactController | aucontact() | :-START");

		UserEntity userEntity;
		List<MobileEntity> listMobile;
		List<EmailEntity> listEmail;
		boolean status = false;
		try{
			listMobile = customerDto.getListMobile();
			listEmail = customerDto.getListEmail();
			userEntity = (UserEntity)httpSession.getAttribute("UserDetails");
			if(userEntity==null){
				httpSession.invalidate();
				
				return "redirect:login.do";
			}
			if(listMobile!=null && listMobile.size()>0 && requestType.equalsIgnoreCase("mobile")){
				status = mobileService.addUpdateContact(listMobile, caseId, userEntity.getUserid()+"");

			}
			if(listEmail!=null && listEmail.size()>0 && requestType.equalsIgnoreCase("email")){
				status = emailService.addUpdateContact(listEmail, caseId, userEntity.getUserid()+"");
			}

		}catch(Exception e){
			e.printStackTrace();
			logger.error("CustomerContactController | auContact() | "+e.getMessage()+" | :-END");
		}finally{
			listMobile =null;
			listEmail = null;
		}
		logger.info("CustomerContactController | aucontact() | :-END");
		return "redirect:leadDetail.do";
	}

	@RequestMapping(value={"addContact"}, method=RequestMethod.POST)
	public String addContact(@ModelAttribute MobileEntity mobileEntity, @ModelAttribute EmailEntity emailEntity, @RequestParam String caseId){
		logger.info("CustomerContactController | addContact() | :- START");
		try{
			if(mobileEntity!=null && !mobileEntity.getContactNo().equals("")){
				mobileEntity.setCaseId(caseId);
				mobileService.addMobile(mobileEntity);
			}
			if(emailEntity!=null && !emailEntity.getEmail().equals("")){
				emailEntity.setCaseId(caseId);
				emailService.addEmail(emailEntity);

			}
		}catch(Exception e){
			logger.error("CustomerContactController | addContact() | "+e.getMessage()+" | :-END");
		}finally{
			mobileEntity = null;
		}

		logger.info("CustomerContactController | addContact() | :- END");
		return "redirect:leadDetail.do";
	}
	@InitBinder
	public void dateBinderForDelete(WebDataBinder binder){
		logger.info("CustomerController | dateBinder() | :- START");
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		dateFormat.setLenient(true);
		binder.registerCustomEditor(Date.class,"dob", new CustomDateEditor(dateFormat, true));
		binder.registerCustomEditor(Date.class,"incorporationDate", new CustomDateEditor(dateFormat, true));
		logger.info("CustomerController | dateBinder() | :- END");
	}

	@RequestMapping(value={"deleteContact"}, method=RequestMethod.POST)
	public String deleteContact(@ModelAttribute CustomerDto customerDto, @RequestParam String caseId, @RequestParam String requestType){
		logger.info("CustomerContactController | deleteContact() | :- START");
		List<MobileEntity> list;
		List<EmailEntity> list1;
		try{
			list = customerDto.getListMobile();

			list1= customerDto.getListEmail();

			if(list!=null && list.size()>0 && requestType.equalsIgnoreCase("Mobile")){
				mobileService.deleteMobile(list);
			}
			if(list1!=null && list1.size()>0 && requestType.equalsIgnoreCase("Email")){
				emailService.deleteEmail(list1);
			}
		}catch(Exception e){
			logger.error("CustomerContactController | deleteContact() | "+e.getMessage()+" | :-END");
		}finally{
			list = null;
			list1 = null;
		}

		logger.info("CustomerContactController | deleteContact() | :- END");
		return "redirect:leadDetail.do";
	}

	@RequestMapping(value={"updateContact"}, method=RequestMethod.POST)
	public String updateContact(@ModelAttribute CustomerDto customerDto, @RequestParam String caseId){
		logger.info("CustomerContactController | updateContact() | :- START");
		List<MobileEntity> list;
		List<EmailEntity> list1;

		try{
			list= customerDto.getListMobile();

			list1= customerDto.getListEmail();

			if(list!=null && list.size()>0){
				mobileService.updateMobile(list, caseId);
			}
			if(list1!=null && list1.size()>0){
				emailService.updateEmail(list1, caseId);
			}
		}catch(Exception e){
			logger.error("CustomerContactController | updateContact() | "+e.getMessage()+" | :-END");
		}finally{
			list = null;
			list1 = null;
		}

		logger.info("CustomerContactController | updateContact() | :- END");
		return "redirect:leadDetail.do";
	}
}
