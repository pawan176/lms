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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.qc.starter.dto.CustomerDto;
import com.qc.starter.entity.KeyContactsEntity;
import com.qc.starter.entity.UserEntity;
import com.qc.starter.service.KeyContactService;

@Controller
public class KeyContactController {
	private static final Logger logger = Logger.getLogger(KeyContactController.class.getName());
	private static ResourceBundle resource1=ResourceBundle.getBundle("ApplicationResource");
	@Autowired
	KeyContactService keyContactService;

	/*@RequestMapping(value = { "updateKeyContacts" }, method = RequestMethod.POST)
	public String updateKeyConatacts(@ModelAttribute CustomerDto customerDto,
			Model model) {
		List<KeyContactsEntity> contacts = customerDto.getKeyContacts();
		boolean status = false;
		status = keyContactService.updateKeyContactList(contacts);
		if (status == true) {
			return "forward:/leadDetail.do";
		}
		return "error";
	}*/
	@InitBinder
	public void databind(WebDataBinder binder){
		logger.info("KeyContactController | databind() | :- START");
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class,"dob", new CustomDateEditor(dateFormat, true));
		binder.registerCustomEditor(Date.class,"incorporationDate", new CustomDateEditor(dateFormat, true));
		logger.info("KeyContactController | databind() | :- END");
	}
	@RequestMapping(value={"auKeyContact"}, method=RequestMethod.POST)
	public String addUpdateKeyContact(@ModelAttribute CustomerDto customerDto,HttpSession session, ModelMap model, @RequestParam String caseId){
		logger.info("KeyContactController | addUpdateKeyContact() | :- START");
		UserEntity userEntity= (UserEntity)session.getAttribute("UserDetails");
		if(userEntity==null){
			session.invalidate();
			
			return "redirect:login.do";
		}
		List<KeyContactsEntity> list = customerDto.getKeyContacts();
		if(list!=null && list.size()>0) keyContactService.addUpdateKeyContactList(list, userEntity, caseId, customerDto.getPersonalDetailId());
		logger.info("KeyContactController | addUpdateKeyContact() | :- END");
		return "redirect:leadDetail.do";
	}

	@RequestMapping(value = { "delKeyContact" }, method = RequestMethod.POST)
	public String deleteContacts(@ModelAttribute CustomerDto customerDto,
			Model model) {
		logger.info("KeyContactController | deleteContacts() | :- START");
		List<KeyContactsEntity> contacts = customerDto.getKeyContacts();

		int status = 0;
		status = 	keyContactService.deleteContact(contacts);
		model.addAttribute("deletedRows", status);
		logger.info("KeyContactController | deleteContacts() | :- END");
		return "redirect:leadDetail.do";		
	}

}