package com.qc.starter.controllar;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.qc.starter.dto.ProductDto;
import com.qc.starter.entity.CaseXSellEntity;
import com.qc.starter.entity.LeadEntity;
import com.qc.starter.entity.UserEntity;
import com.qc.starter.service.XSellService;

@Controller
public class XSellController {
	private static final Logger logger = Logger.getLogger(XSellController.class.getName());
	private static ResourceBundle resource1=ResourceBundle.getBundle("ApplicationResource");
	@Autowired XSellService xSellService;
	@Autowired HttpSession httpSession;

	@RequestMapping(value={"addXSell"}, method=RequestMethod.POST)
	public String addXSell(@ModelAttribute CaseXSellEntity caseXSellEntity){
		logger.info("XSellController | addXSell() | :- START");
		caseXSellEntity.setParentCaseId(httpSession.getAttribute("leadId").toString());
		UserEntity userEntity = (UserEntity)httpSession.getAttribute("UserDetails");
		caseXSellEntity.setCreatedBy(userEntity.getUserid().toString());
		boolean status = false;
		try{
			status = xSellService.addXSell(caseXSellEntity);
		}catch(Exception e){
			logger.error("Error in catch blok due to ::::-->"+e.getMessage());
			e.printStackTrace();
		}
		logger.info("XSellController | addXSell() | :- END");
		if(status)
			return "forward:/product.do";
		return "error";
	}

	@RequestMapping(value={"deleteXSell"}, method=RequestMethod.POST)
	public String deleteXSell(@ModelAttribute ProductDto productDto){
		List<CaseXSellEntity> listXSell =  productDto.getListXSell();
		logger.info("XSellController | deleteXSell() | :- START");
		boolean status = false;
		try{
			if(listXSell != null){
				status = xSellService.deleteXSell(listXSell);
			}else{
				status = true;
			}
		}catch(Exception e){
			logger.info("Error in catch blok due to ::::-->"+e.getMessage());
			e.printStackTrace();
		}
		logger.info("XSellController | deleteXSell() | :- END");
		if(status)
			return "forward:/product.do";
		else
			return "error";
	}

	@RequestMapping(value={"updateXSell"}, method=RequestMethod.POST)
	public String updateXSell(@ModelAttribute ProductDto productDto,HttpSession session,Model model){
		logger.info("XSellController | updateXSell() | :- START");
		List<CaseXSellEntity> listXSell = productDto.getListXSell();
		String insertStatus = "";
		String updateStatus = "";
		
		LeadEntity leadEntity;
		int caseId = 0;
		leadEntity = (LeadEntity)session.getAttribute("leadDetails");		
		caseId=Integer.parseInt(leadEntity.getCaseId());
		
		
		UserEntity userEntity= (UserEntity)session.getAttribute("UserDetails");
		if(userEntity==null){
			 session.invalidate();
			
			 return "redirect:login.do";
		}
		String userId = userEntity.getUserid()+"";
		try{
			List<CaseXSellEntity> updateList = new ArrayList<CaseXSellEntity>();
			List<CaseXSellEntity> insertList = new ArrayList<CaseXSellEntity>();
			
			if(listXSell == null){
				return "forward:/product.do";
			}
				
			for(CaseXSellEntity xSellEntity : listXSell){
				if(xSellEntity.getXsellId() != null && !(xSellEntity.getXsellId().trim().equalsIgnoreCase("insert"))){
					updateList.add(xSellEntity);
				}
				else if(xSellEntity.getXsellId() != null && (xSellEntity.getXsellId().trim().equalsIgnoreCase("insert"))){
					xSellEntity.setParentCaseId(caseId+"");
					xSellEntity.setCreatedBy(userId);
					insertList.add(xSellEntity);	
				}
			}
			if(insertList.size()>0){
				insertStatus=xSellService.insertXSell(insertList);
				if(insertStatus==null)
					return "error";
			}
			if(updateList.size()>0){
				insertStatus = xSellService.updateXSell(updateList);
				if(updateStatus==null)
					return "error";
			}
		}catch(Exception e){
			logger.info("Error in catch blok due to ::::-->"+e.getMessage());
			e.printStackTrace();
			return "error";
		}
		logger.info("XSellController | updateXSell() | :- END");
		return "forward:/product.do";
	}

	@RequestMapping(value={"convertXLead"}, method=RequestMethod.GET)
	public String convertXLead(@RequestParam String sellCode,Model model){
		logger.info("XSellController | convertXLead() | :- START");
		UserEntity userEntity= (UserEntity)httpSession.getAttribute("UserDetails");
		if(userEntity==null){
			httpSession.invalidate();
			
			return "login";
		}
		boolean status = false;
		try{
			status = xSellService.convertXLead(sellCode);
		}catch(Exception e){
			logger.info("Error in catch blok due to ::::-->"+e.getMessage());
			e.printStackTrace();
		}
		logger.info("XSellController | convertXLead() | :- END");
		if(status)
			return "forward:/product.do";
		else
			return "error";
	}

}
