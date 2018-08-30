package com.qc.starter.controllar;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.qc.starter.basic.CommonUtils;
import com.qc.starter.dto.ProductDto;
import com.qc.starter.entity.CaseXSellEntity;
import com.qc.starter.entity.ExistingFacilityEntity;
import com.qc.starter.entity.LeadEntity;
import com.qc.starter.entity.UserEntity;
import com.qc.starter.service.CustomerService;
import com.qc.starter.service.ExistingFacilityService;
import com.qc.starter.service.FacilityRequestedService;
import com.qc.starter.service.XSellService;

@Controller
public class ProductHeaderController {
	@Autowired XSellService xSellService;
	@Autowired CustomerService customerService;
	@Autowired ExistingFacilityService existingFacilityService;
	@Autowired FacilityRequestedService facilityRequestedService;
	private static ResourceBundle resource1=ResourceBundle.getBundle("ApplicationResource");

	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value={"productSaveAndExit"})	
	public String productSaveAndExit(@ModelAttribute ProductDto productDto,@RequestParam("action")String action,HttpSession session,Model model){

		boolean status1 = false;
		UserEntity userEntity = null;
		LeadEntity leadEntity;
		try{

			userEntity = (UserEntity) session.getAttribute("UserDetails");
			if (userEntity == null) {
				session.invalidate();
				return "login";
			}
			
		}catch(Exception e){
			//logger.info("Error in catch due to-->" + e.getMessage());
			e.printStackTrace();
		}
		//logger.info("FacilityController | upadteFacilityRequested() | :- END");
		String insertStatus = "";
		String updateStatus = "";
		List<CaseXSellEntity> listXSell = productDto.getListXSell();

		int caseId = 0;
		leadEntity = (LeadEntity)session.getAttribute("leadDetails");		
		
		productDto.setUserId(userEntity.getUserid()+"");
		productDto.setCompyId(userEntity.getCompanyId());
		
		if(CommonUtils.toString(productDto.getHiddenLeadId()).equals("")){
			productDto.setHiddenLeadId(leadEntity.getCaseId());
		}
		status1 = facilityRequestedService.updateExistingFacility(productDto);
		
		
		caseId=Integer.parseInt(leadEntity.getCaseId());


		userEntity= (UserEntity)session.getAttribute("UserDetails");
		if(userEntity==null){
			session.invalidate();
		
			return "redirect:login.do";
		}
		String userId = userEntity.getUserid()+"";
		if(listXSell!=null){ try{
			List<CaseXSellEntity> updateList = new ArrayList<CaseXSellEntity>();
			List<CaseXSellEntity> insertList = new ArrayList<CaseXSellEntity>();
			for(CaseXSellEntity xSellEntity : listXSell){
				if(xSellEntity!=null && xSellEntity.getXsellId() != null && !(xSellEntity.getXsellId().trim().equalsIgnoreCase("insert"))){
					updateList.add(xSellEntity);
				}
				else if(xSellEntity!=null && xSellEntity.getXsellId() != null && (xSellEntity.getXsellId().trim().equalsIgnoreCase("insert"))){
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
			//logger.info("Error in catch blok due to ::::-->"+e.getMessage());
			e.printStackTrace();
			return "error";
		}}
		List<ExistingFacilityEntity> existingFacilityHistorylist = productDto.getExistingFacilityHistory();
		List<ExistingFacilityEntity> existingFacilityHistoryUpdate = new ArrayList<ExistingFacilityEntity>();
		List<ExistingFacilityEntity> existingFacilityHistoryInsert = new ArrayList<ExistingFacilityEntity>();
		String personDtlId = customerService.getPersonalDetailId(caseId+"");
		if(existingFacilityHistorylist != null){
			for(ExistingFacilityEntity existingFacilityEntity: existingFacilityHistorylist){
				if( existingFacilityEntity.getCheckRow() != null && !(existingFacilityEntity.getCheckRow().trim().equals("")) && !(existingFacilityEntity.getCheckRow().trim().equals("insert")))   {
					existingFacilityHistoryUpdate.add(existingFacilityEntity);
				}
				if( existingFacilityEntity.getCheckRow() != null && existingFacilityEntity.getCheckRow().trim().equals("insert"))   {				
					existingFacilityEntity.setCaseId(caseId+"");
					existingFacilityEntity.setPersonalDtlId(personDtlId);
					existingFacilityHistoryInsert.add(existingFacilityEntity);
				}
			}
		}
		String status = "";
		String Istatus = "";
		try{
			if(!existingFacilityHistoryUpdate.isEmpty())
				status = existingFacilityService.updateExistingFacility(existingFacilityHistoryUpdate);
			if(!existingFacilityHistoryInsert.isEmpty())
				Istatus = facilityRequestedService.addExistingFacility(userEntity, existingFacilityHistoryInsert);
			
			if(Istatus == null){
				return "error";
			}
		}catch(Exception e){
			//logger.info("Error in catch due to-->" + e.getMessage());
			e.printStackTrace();
			return "error";
		}
		if(action.equalsIgnoreCase("saveAndExit")){
			return "workList";
		}
		return "redirect:product.do";
	}
}
