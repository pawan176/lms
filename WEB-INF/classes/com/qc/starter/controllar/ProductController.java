package com.qc.starter.controllar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.qc.starter.dto.ConvertToCustomerDto;
import com.qc.starter.dto.LeadHeaderDto;
import com.qc.starter.dto.MasterDto;
import com.qc.starter.dto.ProductDto;
import com.qc.starter.entity.CaseXSellEntity;
import com.qc.starter.entity.ExistingFacilityEntity;
import com.qc.starter.entity.LeadEntity;
import com.qc.starter.entity.UserEntity;
import com.qc.starter.service.CustomerService;
import com.qc.starter.service.ExistingFacilityService;
import com.qc.starter.service.LeadHeaderService;
import com.qc.starter.service.LeadService;
import com.qc.starter.service.MasterService;
import com.qc.starter.service.ProductService;
import com.qc.starter.service.XSellService;

@Controller
public class ProductController {
	private static final Logger logger = Logger.getLogger(ProductController.class.getName());
	//private static ResourceBundle resource1=ResourceBundle.getBundle("ApplicationResource");
	@Autowired ProductService productService;
	@Autowired MasterService masterService;
	@Autowired LeadHeaderService leadHeaderService;
	@Autowired ExistingFacilityService existingFacilityService; 
	@Autowired XSellService xSellService;
	@Autowired CustomerService customerService;
	@Autowired	LeadService leadService;

	@RequestMapping(method = {RequestMethod.GET,RequestMethod.POST}, value = { "product" })
	public String showProduct(HttpSession session,Model model) {
		logger.info("ProductController | showProduct() | :- START");
		UserEntity userEntity= (UserEntity)session.getAttribute("UserDetails");    	    	
		if(userEntity==null){
			session.invalidate();
		
			return "redirect:login.do";
		}		
		LeadEntity leadEntity = (LeadEntity) session.getAttribute("leadDetails");
		int leadid = leadEntity == null ? 1000001 : Integer.parseInt(leadEntity.getCaseId()); 
		LeadEntity oldLeadEntity = (LeadEntity) session.getAttribute("leadDetails");
		session.removeAttribute("leadDetails");
		oldLeadEntity  = leadService .getLead(Integer.parseInt(oldLeadEntity.getCaseId()),userEntity.getCompanyId());
		session.setAttribute("leadDetails", oldLeadEntity);					
			ProductDto productDto = productService.fetchProduct(""+leadid);	
			List<ExistingFacilityEntity> existingFacilityHistory = productService.getFacilityHistory(leadid+"");
			String personalDtlId = customerService.getCustomerFromLeadId(leadid).getPersonalDetailId();
			productDto.setExistingFacilityHistory(existingFacilityHistory);			
			model.addAttribute("productDetail",productDto);			
			MasterDto productMasterDto = masterService.getMasters("ProductMasterEntity~BankEntity~existingFacility~BranchMaster~PurposeOfLoan");
			List<CaseXSellEntity> xSellList = xSellService.getXSellList(leadid);
			model.addAttribute("productMasterDetail",productMasterDto);			
			LeadHeaderDto leadHeaderDto = leadHeaderService.getLeadHeader(""+leadid); 
			//---added by deepak on 5 march--2016--------------
			String productCatId=productService.getProductCategory(leadHeaderDto.getQueueId());
			model.addAttribute("productCategory", productCatId);
			//-----added by tripti on 4 oct
			ConvertToCustomerDto dto=productService.getApplicantId(leadid+"");
			String businessDate = leadService.getBusinessDate();
			model.addAttribute("dto", dto);
			model.addAttribute("personalDtlId", personalDtlId);
			model.addAttribute("leadHeaderDetail",leadHeaderDto);
			model.addAttribute("xSellList", xSellList);
			model.addAttribute("businessDate",businessDate);
			logger.info("ProductController | showProduct() | :- END");
			
			return (productService.isLeadConvertToCustomer(leadid+"")) ? "productViewer" : "product"; 
			
			//return "product";             
		
	}


	//Added for Calculate Eligibility 
	@RequestMapping(method={RequestMethod.POST},value="calcEligibility")
	public @ResponseBody
	String calcEligibility(HttpSession session,Model model,
			@RequestParam("loanAmount") String loanAmount,@RequestParam("tenor") String tenor,
			@RequestParam("dob") String dob,@RequestParam("grossSalary") String grossSalary,
			@RequestParam("netSalary") String netSalary,@RequestParam("yrJob") String yrJob,
			@RequestParam("workingExp") String workingExp,@RequestParam("corparateAc") String corparateAc,
			@RequestParam("leadId") String leadId,@RequestParam("facilityId") String facilityId) {
		logger.info("ProductController | calcEligibility() | :- START");
		UserEntity userEntity= (UserEntity)session.getAttribute("UserDetails");
		if(userEntity==null){
			session.invalidate();
		
			return "redirect:login.do";
		}
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			if(dob!=null && !dob.equalsIgnoreCase("")){
				date = sdf.parse(dob);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<ProductDto> myList=new ArrayList<ProductDto>();

		ProductDto productDto=new ProductDto();
		productDto.setFacilityRequestedLoanAmount(loanAmount.replaceAll(",", ""));
		productDto.setFacilityRequestedTenor(tenor);
		productDto.setDob(date);
		productDto.setGrossMonthlyIncome(grossSalary);
		productDto.setNetMonthlyincome(netSalary);
		productDto.setYearInjob(yrJob);
		productDto.setWorkingExp(workingExp);
		productDto.setCorpSalaryAc(corparateAc);
		productDto.setCompyId(userEntity.getCompanyId());
		productDto.setFacilityReqId(facilityId);
		productDto.setLeadId(leadId);
		myList=productService.getEligibilityCalcData(productDto);
		String response="";

		Gson gsonObj=new Gson();
		response=gsonObj.toJson(myList);



		logger.info("ProductController | calcEligibility() | :- END");

		return response;		
	}
	
	//Added By Deepak on 05 march-2016 for show popup on product category-- 
		@RequestMapping(method={RequestMethod.POST},value="getProductCategory")
		public @ResponseBody
		String getProductCategory(HttpSession session,Model model,
				@RequestParam("productId") String productId) {
			logger.info("ProductController | getProductCategory() | :- START");
			String response="";
			try{
			UserEntity userEntity= (UserEntity)session.getAttribute("UserDetails");
			if(userEntity==null){
				session.invalidate();
				
				return "redirect:login.do";
			}
			response=productService.getProductCategory(productId);
			}catch(Exception e){
				logger.info("Error in >>>>> | ProductController | getProductCategory() | :-"+e.getMessage());
				e.printStackTrace();
			}
			logger.info("ProductController | getProductCategory() | :- END");
			return response;		
		}
		
	//added by tripti 4oct
		@RequestMapping(value={"convertToCustomer"}, method=RequestMethod.POST)
		
		public String convertToCustomer(HttpSession session,Model model,HttpServletRequest request, 
				@RequestParam("branchId1") String branchId){
			logger.info("ProductController | convertToCustomer() | :- START");
			UserEntity userEntity= (UserEntity)session.getAttribute("UserDetails");
			if(userEntity==null){
				session.invalidate();
				
				return "login";
			}
			LeadEntity leadEntity = (LeadEntity) session.getAttribute("leadDetails");
			String status = "";
			try{
				status = productService.convertToCustomer(leadEntity.getCaseId(),userEntity.getUserid(),branchId);
				if(status.split("~")[0].equalsIgnoreCase("F")){
					session.setAttribute("msgForConvertToCust", status.split("~")[1]);
				}
			}catch(Exception e){
				logger.info("Error in catch blok due to ::::-->"+e.getMessage());
				e.printStackTrace();
			}
			logger.info("XSellController | convertXLead() | :- END");
			return "redirect:product.do";
			   
			
		}
	
	
}