package com.qc.starter.controllar;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
import com.qc.starter.entity.AddressEntity;
import com.qc.starter.entity.UserEntity;
import com.qc.starter.service.AddressService;
import com.qc.starter.service.CustomerService;
import com.qc.starter.service.LeadService;
import com.qc.starter.service.MasterService;
import com.qc.starter.service.PropertyService;

@Controller
public class AddressController {
	private static final Logger logger = Logger.getLogger(AddressController.class.getName());
	//private static ResourceBundle resource1=ResourceBundle.getBundle("ApplicationResource");
	@Autowired AddressService addressService;
	@Autowired LeadService leadService;
	@Autowired CustomerService customerService;
	@Autowired PropertyService propertyService;
	@Autowired MasterService masterService;

	@RequestMapping(method=RequestMethod.POST, value={"updateAddressInfo"})
	public String updateResAddress(@ModelAttribute AddressEntity addressEntity, Model model,HttpSession session){
		logger.info("AddressController | updateResAddress() | :- START");
		boolean status = false;
		status = addressService.updateResAddress(addressEntity);		
		UserEntity userEntity= (UserEntity)session.getAttribute("UserDetails");
		logger.info("AddressController | updateResAddress() | :- END");
		if(userEntity==null){
			session.invalidate();			
			return "redirect:login.do";
		}
		else{
			if((status==true)){				
				return "redirect:leadDetail.do";
			}
		}
		return "error";
	}

	@InitBinder
	public void databind(WebDataBinder binder){
		logger.info("AddressController | DataBinding | :- START");
		SimpleDateFormat dateFormat;
		try{
			dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			dateFormat.setLenient(false);
			binder.registerCustomEditor(Date.class,"dob", new CustomDateEditor(dateFormat, true));
			binder.registerCustomEditor(Date.class,"incorporationDate", new CustomDateEditor(dateFormat, true));
		}catch(Exception e){
			//e.printStackTrace();
			logger.error("AddressController | DataBinding | " + e.getMessage() + " | :-END");
		}finally{
			dateFormat = null;
		}
		logger.info("AddressController | DataBinding | :- END");
	}
	@RequestMapping(method=RequestMethod.POST,  value={"auAddress"})
	public String updateAddAddress(@ModelAttribute CustomerDto customerDto, HttpSession session, ModelMap model, @RequestParam String caseId, @RequestParam String personalDetailId){
		logger.info("AddressController | updateAddAddress() | :- START");
		UserEntity userEntity;
		List<AddressEntity> list;
		String through="WEB";
		try{
			userEntity = (UserEntity)session.getAttribute("UserDetails");
			if(userEntity==null){
				session.invalidate();				
				return "redirect:login.do";
			}
			list = customerDto.getListAddress();
			if(list!=null && list.size()>0)addressService.addUpdateAddress(list, userEntity.getUserid()+"", caseId, personalDetailId);

		}catch(Exception e){
			//e.printStackTrace();
			logger.error("AddressController | UpdateAddAddress | "+e.getMessage()+" |:-END");
		}finally{
			userEntity=null;
			list=null;
		}
		logger.info("AddressController | updateAddAddress() | :- END");
		return "redirect:leadDetail.do";
	}

	@RequestMapping(method=RequestMethod.POST, value={"delAddress"})
	public String deleteAddressList(@ModelAttribute CustomerDto customerDto, ModelMap model, HttpSession session){
		logger.info("AddressController | deleteAddressList() | :- START");
		UserEntity userEntity;
		List<AddressEntity> list; 
		try{
			userEntity = (UserEntity)session.getAttribute("UserDetails");
			if(userEntity==null){
				session.invalidate();				
				return "redirect:login.do";
			}
			list = customerDto.getListAddress();
			if(list!=null && list.size()>0)addressService.deleteAddressList(list);

		}catch(Exception e){
			logger.error("AddressController | DeleteAddress | "+e.getMessage()+" | :-END");
		}
		finally{
			list = null;
			userEntity = null;
		}
		logger.info("AddressController | deleteAddressList() | :- END");
		return "redirect:leadDetail.do";
	}

}
