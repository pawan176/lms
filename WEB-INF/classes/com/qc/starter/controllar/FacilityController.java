package com.qc.starter.controllar;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.qc.starter.dto.ProductDto;
import com.qc.starter.entity.ExistingFacilityEntity;
import com.qc.starter.entity.LeadEntity;
import com.qc.starter.entity.UserEntity;
import com.qc.starter.form.ExistingFacilityForm;
import com.qc.starter.service.CustomerService;
import com.qc.starter.service.ExistingFacilityService;
import com.qc.starter.service.FacilityRequestedService;

@Controller
public class FacilityController {
	private static final Logger logger = Logger.getLogger(FacilityController.class.getName());
	private static ResourceBundle resource1=ResourceBundle.getBundle("ApplicationResource");
	@Autowired FacilityRequestedService facilityRequestedService;
	@Autowired ExistingFacilityService existingFacilityService;
	@Autowired CustomerService customerService;

	@RequestMapping(method = RequestMethod.POST, value={"dExFacility"})
	public String ManageExistingFacility(@ModelAttribute("existingFacilityForm") ExistingFacilityForm existingFacilityForm){	
		logger.info("FacilityController | ManageExistingFacility() | :- START");
		List<ExistingFacilityEntity> existingFacilityHistorylist = existingFacilityForm.getExistingFacilityHistory();

		List<ExistingFacilityEntity> existingFacilityHistoryNew = new ArrayList<ExistingFacilityEntity>();

		for(ExistingFacilityEntity existingFacilityEntity: existingFacilityHistorylist){
			if( existingFacilityEntity.getCheckRow() != null && !(existingFacilityEntity.getCheckRow().trim().equals("")))   {
				existingFacilityHistoryNew.add(existingFacilityEntity);
			}  
		}
		String status = "";
		try{
			status = existingFacilityService.deleteExistingFacility(existingFacilityHistoryNew);
		}catch(Exception e){
			logger.info("Error in catch due to-->" + e.getMessage());
			e.printStackTrace();
		}
		logger.info("FacilityController | ManageExistingFacility() | :- END");
		if(status!=null)
			return  "redirect:product.do" ;
		else
			return "error";

	}

	@RequestMapping(value={"uExFacility"}, method=RequestMethod.POST)
	public String updateExistingFacilities(@ModelAttribute("existingFacilityForm") ExistingFacilityForm existingFacilityForm,HttpSession session,Model model){
		logger.info("FacilityController | updateExistingFacilities() | :- START");
		List<ExistingFacilityEntity> existingFacilityHistorylist = existingFacilityForm.getExistingFacilityHistory();

		List<ExistingFacilityEntity> existingFacilityHistoryUpdate = new ArrayList<ExistingFacilityEntity>();
		List<ExistingFacilityEntity> existingFacilityHistoryInsert = new ArrayList<ExistingFacilityEntity>();

		LeadEntity leadEntity;
		int caseId = 0;
		leadEntity = (LeadEntity)session.getAttribute("leadDetails");		
		caseId=Integer.parseInt(leadEntity.getCaseId());



		String personDtlId = customerService.getPersonalDetailId(caseId+"");
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

		UserEntity userEntity= (UserEntity)session.getAttribute("UserDetails");
		if(userEntity==null){
			session.invalidate();
			
			return "redirect:login.do";
		}

		String status = "";
		String Istatus = "";
		try{
			status = existingFacilityService.updateExistingFacility(existingFacilityHistoryUpdate);
			Istatus = facilityRequestedService.addExistingFacility(userEntity, existingFacilityHistoryInsert);
			if(Istatus == null){
				return "error";
			}
		}catch(Exception e){
			logger.info("Error in catch due to-->" + e.getMessage());
			e.printStackTrace();
			return "error";
		}
		logger.info("FacilityController | updateExistingFacilities() | :- END");
		if(status!=null)
			return "redirect:product.do";
		else
			return "error";
	}

	@RequestMapping(method = RequestMethod.POST, value={"updateFacilityRequested"})
	public String upadteFacilityRequested(@ModelAttribute ProductDto productDto, ModelMap modelMap,HttpSession session){
		logger.info("FacilityController | upadteFacilityRequested() | :- START");
		boolean status = false;
		try{
			status = facilityRequestedService.updateExistingFacility(productDto);
		}catch(Exception e){
			logger.info("Error in catch due to-->" + e.getMessage());
			e.printStackTrace();
		}
		logger.info("FacilityController | upadteFacilityRequested() | :- END");
		if(status == true){
			return "redirect:product.do";
		}					
		return "error";
	}

	/*@RequestMapping(method=RequestMethod.POST, value={"aExFacility"})
	public String addExistingFacility(@ModelAttribute ExistingFacilityEntity existingFacilityEntity){
		if(facilityRequestedService.addExistingFacility(existingFacilityEntity))
			return "forward:/product.do";
		else
		return "error";

	}*/
}
