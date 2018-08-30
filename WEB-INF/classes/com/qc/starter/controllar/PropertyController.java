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

import com.qc.starter.dto.CustomerDto;
import com.qc.starter.entity.PropertyEntity;
import com.qc.starter.entity.UserEntity;
import com.qc.starter.service.PropertyService;

@Controller
public class PropertyController {
	private static final Logger logger = Logger.getLogger(PropertyController.class.getName());
	private static ResourceBundle resource1=ResourceBundle.getBundle("ApplicationResource");
	@Autowired PropertyService propertyService;

	@RequestMapping(value={"addProperty"}, method= RequestMethod.POST)
	public String addProperty(@ModelAttribute PropertyEntity propertyEntity){
		logger.info("PropertyController | addProperty() | :- START");
		boolean status  = false;
		
		status = propertyService.addProperty(propertyEntity);
		logger.info("PropertyController | addProperty() | :- END");
		if(status==true)
			return "redirect:leadDetail.do";	
		else
			return "error";
	}
	@InitBinder
	public void databind(WebDataBinder binder){
		logger.info("PropertyController | databind() | :- START");
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class,"dob", new CustomDateEditor(dateFormat, true));
		binder.registerCustomEditor(Date.class,"incorporationDate", new CustomDateEditor(dateFormat, true));
		logger.info("PropertyController | databind() | :- END");
	}
	@RequestMapping(value={"auProperty"}, method=RequestMethod.POST)
	public String addUpdateProperty(@ModelAttribute CustomerDto customerDto, ModelMap model, HttpSession session){
		logger.info("PropertyController | addUpdateProperty() | :- START");
		UserEntity userEntity= (UserEntity)session.getAttribute("UserDetails");
    	if(userEntity==null){
			 session.invalidate();
			
			 return "redirect:login.do";
		}
    	List<PropertyEntity> list = customerDto.getListProperty();
		
		if(list!=null && list.size()>0) 
			propertyService.addUpdatePropertyList(list, customerDto.getCaseId(), customerDto.getPersonalDetailId(), userEntity);
		logger.info("PropertyController | addUpdateProperty() | :- END");
		return "redirect:leadDetail.do";
	}
	
	@RequestMapping(value={"delProperty"}, method=RequestMethod.POST)
	public String deleteProperty(@ModelAttribute CustomerDto customerDto, Model model){
		logger.info("PropertyController | deleteProperty() | :- START");
		int status = 0;
		List<PropertyEntity> list = customerDto.getListProperty();
		status = propertyService.deleteProperty(list);
		model.addAttribute("deletedRows", status);
		logger.info("PropertyController | deleteProperty() | :- END");
		return "redirect:leadDetail.do";
		
	}
	
	@RequestMapping(value={"updateProperty"}, method=RequestMethod.POST)
	public String updateProperty(@ModelAttribute CustomerDto customerDto){
		logger.info("PropertyController | updateProperty() | :- START");
		boolean status = false;
		status = propertyService.updateProperty(customerDto.getListProperty());
		logger.info("PropertyController | updateProperty() | :- END");
		if(status==true)
			return "redirect:leadDetail.do";
		else
			return "error";
	}
}
