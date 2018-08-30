package com.qc.starter.controllar;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qc.starter.dto.CustomerDto;
import com.qc.starter.dto.LeadHeaderDto;
import com.qc.starter.dto.MasterDto;
import com.qc.starter.entity.AddressEntity;
import com.qc.starter.entity.CustomerEntity;
import com.qc.starter.entity.EmailEntity;
import com.qc.starter.entity.KeyContactsEntity;
import com.qc.starter.entity.LeadEntity;
import com.qc.starter.entity.MobileEntity;
import com.qc.starter.entity.PropertyEntity;
import com.qc.starter.entity.UserEntity;
import com.qc.starter.service.AddressService;
import com.qc.starter.service.CustomerService;
import com.qc.starter.service.EmailService;
import com.qc.starter.service.KeyContactService;
import com.qc.starter.service.LeadHeaderService;
import com.qc.starter.service.LeadService;
import com.qc.starter.service.MasterService;
import com.qc.starter.service.MobileService;
import com.qc.starter.service.ProductService;
import com.qc.starter.service.PropertyService;

@Controller
public class LeadController {

	private static final Logger logger = Logger.getLogger(LeadController.class.getName());
	private static ResourceBundle resource1=ResourceBundle.getBundle("ApplicationResource");
	@Autowired	CustomerService customerService;
	@Autowired	LeadService leadService;
	@Autowired	HttpSession session;
	@Autowired AddressService addressService;
	@Autowired KeyContactService keyContactService;
	@Autowired PropertyService propertyService;
	@Autowired MasterService masterService;
	@Autowired ProductService productService;
	@Autowired LeadHeaderService leadHeaderService;
	@Autowired MobileService mobileService;
	@Autowired EmailService emailService;


	@RequestMapping(method = {RequestMethod.GET,RequestMethod.POST}, value = { "leadDetail" })	
	public String getLeadDetail( Model model) {
		logger.info("LeadController | getLeadDetail() | :- START  :Time "+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()));
		UserEntity userEntity= (UserEntity)session.getAttribute("UserDetails");
		LeadEntity leadEntity;
		int caseId = 0;
		leadEntity = (LeadEntity)session.getAttribute("leadDetails");
		
		if(leadEntity!=null)
			leadEntity = leadService.getLead(Integer.parseInt(leadEntity.getCaseId()),userEntity.getCompanyId());
		
		LeadHeaderDto leadHeaderDto = leadHeaderService.getLeadHeader(leadEntity.getCaseId());
		if(leadHeaderDto == null){
			return "error";
		}
		caseId=Integer.parseInt(leadEntity.getCaseId());
		CustomerDto customerDto = new CustomerDto();
		customerDto.setCaseId(new Integer(caseId).toString());
		if(leadHeaderDto!=null){		
			CustomerEntity customerEntity = (CustomerEntity) customerService.getCustomerFromLeadId(caseId);
			if(customerEntity!=null){
				//String companyname = customerService.getCompanyNameById(customerEntity.getCompanyId());
				//customerEntity.setCompanyId(companyname);							
				List<AddressEntity> listAddress = addressService.getAddressList(customerEntity.getPersonalDetailId());				
				List<KeyContactsEntity> listKeyContacts = keyContactService.getKeyContactOfClient(customerEntity.getPersonalDetailId());
				List<PropertyEntity> listProperty = propertyService.getListOfProperty(customerEntity.getPersonalDetailId(),userEntity.getCompanyId()) ;
				List<MobileEntity> listMobile = mobileService.getListOfMobile(caseId);
				List<EmailEntity> listEmail = emailService.getListOfEmail(caseId);
				customerDto = masterService.setEntityValues(customerDto,customerEntity,listAddress,listKeyContacts,listProperty,listMobile,listEmail);
				customerDto.setSource(leadEntity.getSourceId());
				customerDto.setReferenceNumber(leadEntity.getReferenceNum());
				customerDto.setReferenceName(leadEntity.getReferenceName());								
				//MasterLoaderContext masterLoaderContext = MasterLoaderContext.getInstance();
				//MasterDto masterDto = masterLoaderContext.getMaster();
				MasterDto masterDto = masterService.getMasters("MaritalStatusEntity~GenderEntity~NationalityEntity~OccupationEntity~CompanyTypeEntity~SalaryModeEntity~StateMasterEntity~OccupancyStatusEntity~CaseContactEntity~PropertyTypeEntity~PropertyStatusEntity~AddressTypeEntity~DevloperEntity~ContactTypeEntity-CONTACT_CATEGORY='EMAIL'~ContactTypeEntity-CONTACT_CATEGORY='MOBILE'~EntityType~Industry~CustomerCategory~SectorEntity~StageEntity~PurposeOfLoan~TypeOfBusinessEntity~ClusterEntity~TitleEntity~SourceEntity");
				MasterDto property_OccupancyStatusEntity = masterService.getMasters("OccupancyStatusEntity");
				String businessDate = leadService.getBusinessDate();
				model.addAttribute("leadHeaderDetail", leadHeaderDto);
				model.addAttribute("customerMasterDetail", masterDto);
				model.addAttribute("customerDetail",customerDto);
				model.addAttribute("OccupancyStatusForProperty",property_OccupancyStatusEntity);
				model.addAttribute("businessDate",businessDate);
				logger.info("LeadController | getLeadDetail() | :- END  :Time "+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()));
				return (productService.isLeadConvertToCustomer(caseId+"")) ? "customerViewer" : "customerTile";				
			}
			else{
				return "error";
			}
		}
		else return "error";
	}

	@RequestMapping(method = {RequestMethod.GET,RequestMethod.POST}, value = { "leadDetailCode" })
	public String getLeadDetailCode(@RequestParam String caseCode, Model model) {
		logger.info("LeadController | getLeadDetail() | :- START");
		UserEntity userEntity= (UserEntity)session.getAttribute("UserDetails");
		if(userEntity==null){
			session.invalidate();
			return "redirect:login.do";
		}

		LeadEntity leadEntity = null;
		if(caseCode == null){
			leadEntity = (LeadEntity)session.getAttribute("leadDetails");
		}
		else{
			session.removeAttribute("leadDetails");
			leadEntity = leadService.getLeadCode(caseCode);
			session.setAttribute("leadDetails", leadEntity);

		}

		LeadHeaderDto leadHeaderDto = leadHeaderService.getLeadHeader(leadEntity.getCaseId());
		if(leadHeaderDto == null){
			return "error";
		}		
		CustomerDto customerDto = new CustomerDto();
		customerDto.setCaseId(leadEntity.getCaseId());
		if(leadHeaderDto!=null){		
			CustomerEntity customerEntity = (CustomerEntity) customerService.getCustomerFromLeadId(Integer.parseInt(leadEntity.getCaseId()));
			if(customerEntity!=null){
				List<AddressEntity> listAddress = addressService.getAddressList(customerEntity.getPersonalDetailId());
				List<KeyContactsEntity> listKeyContacts = keyContactService.getKeyContactOfClient(customerEntity.getPersonalDetailId());
				List<PropertyEntity> listProperty = propertyService.getListOfProperty(customerEntity.getPersonalDetailId(),leadEntity.getCompanyId()) ;
				List<MobileEntity> listMobile = mobileService.getListOfMobile(Integer.parseInt(leadEntity.getCaseId()));
				List<EmailEntity> listEmail = emailService.getListOfEmail(Integer.parseInt(leadEntity.getCaseId()));
				customerDto = masterService.setEntityValues(customerDto,customerEntity,listAddress,listKeyContacts,listProperty,listMobile,listEmail);
				//MasterDto masterDto = masterService.getMasters("MaritalStatusEntity~GenderEntity~NationalityEntity~OccupationEntity~CompanyTypeEntity~CompanyEntity~SalaryModeEntity~StateMasterEntity~CityEntity~OccupancyStatusEntity~CaseContactEntity~PropertyTypeEntity~PropertyStatusEntity~AddressTypeEntity~ContactTypeEntity-contactCategory='EMAIL'~ContactTypeEntity-contactCategory='MOBILE'");
				MasterDto masterDto = masterService.getMasters("MaritalStatusEntity~GenderEntity~NationalityEntity~OccupationEntity~CompanyTypeEntity~SalaryModeEntity~StateMasterEntity~OccupancyStatusEntity~CaseContactEntity~PropertyTypeEntity~PropertyStatusEntity~AddressTypeEntity~DevloperEntity~ContactTypeEntity-CONTACT_CATEGORY='EMAIL'~ContactTypeEntity-CONTACT_CATEGORY='MOBILE'~EntityType~Industry~CustomerCategory~SectorEntity~StageEntity~TypeOfBusinessEntity~ClusterEntity~TitleEntity");
				MasterDto property_OccupancyStatusEntity = masterService.getMasters("OccupancyStatusEntity");
				model.addAttribute("leadHeaderDetail", leadHeaderDto);
				model.addAttribute("customerMasterDetail", masterDto);
				model.addAttribute("customerDetail",customerDto);
				model.addAttribute("OccupancyStatusForProperty",property_OccupancyStatusEntity);
				logger.info("LeadController | getLeadDetail() | :- END");			
				return (productService.isLeadConvertToCustomer(leadEntity.getCaseId()+"")) ? "customerViewer" : "customerTile";			
			}
			else{
				return "error";
			}
		}
		else return "error";
	}

	@RequestMapping(method = {RequestMethod.GET,RequestMethod.POST}, value = { "previousNextLead" })	
	public String getpreviousLead(@RequestParam String lead, Model model,HttpServletRequest request,@CookieValue(value = "worklistLeadsCookie", defaultValue = "NoLeads") String worklistLeads) {
		logger.info("LeadController | getpreviousLead() | :- START");
		try{
			UserEntity userEntity= (UserEntity)session.getAttribute("UserDetails");
			LeadEntity leadEntity = (LeadEntity) session.getAttribute("leadDetails");

			if(userEntity==null){
				session.invalidate();
				
				return "redirect:login.do";
			}		

			// Coding for local storage in the worklist leads ....

			if(worklistLeads != null && !(worklistLeads.equals("NoLeads")) ){
				String currentlead = leadEntity.getCaseId();			
				String[] leads = worklistLeads.split(",");

				int i=0;
				int indexOfCurrentLead=-12;

				for(i=0;i<leads.length;i++)
				{
					if(leads[i].equals(currentlead)){
						indexOfCurrentLead = i;
					}
				}

				String leadIds = "0";			
				if(indexOfCurrentLead != -12){

					if(lead.equals("next")){
						if(leads.length > indexOfCurrentLead+1)
							leadIds = leads[indexOfCurrentLead+1];
					}
					if(lead.equals("previous")){
						if(indexOfCurrentLead > 0)
							leadIds = leads[indexOfCurrentLead-1];
					}			
				}			
				if(!(leadIds.equals("0"))){
					leadEntity = leadService.getLead(Integer.parseInt(leadIds),userEntity.getCompanyId());
					session.setAttribute("leadDetails", leadEntity);
					return "redirect:contact.do?caseId=" + leadIds;
				}else{
					return "redirect:contact.do?caseId=" + leadEntity.getCaseId();
				}
			}
			// End here .. 
			else{
				return "redirect:contact.do?caseId=0";
			}			
		}catch(Exception e){			
			e.printStackTrace();
			return "redirect:contact.do?caseId=0";
		}finally{

		}

	}

	@RequestMapping(method=RequestMethod.GET, value={"checkLeadAvail"})	
	public @ResponseBody String checkLeadAvail(@RequestParam String caseId){
		logger.info("LeadController | checkLeadAvail() | :- START");
		Boolean status = leadService.checkLeadAvail(caseId);
		logger.info("LeadController | checkLeadAvail() | :- END");
		return status.toString();
	}
	@RequestMapping(method=RequestMethod.POST, value={"getProjectNameList"})	
	public @ResponseBody String getDataForPrjectName(@RequestParam String developerId){
		logger.info("LeadController | getDataForPrjectNameSelect() | :- START");
		String json=null;
		try{
			UserEntity userEntity= (UserEntity)session.getAttribute("UserDetails");
			if(userEntity==null){
				session.invalidate();
				return "redirect:login.do";
			}	
			json=propertyService.getListOfProject(developerId, userEntity.getCompanyId());
		}catch(Exception e){
			e.printStackTrace();
			logger.info("Error in LeadController | getDataForPrjectNameSelect() | Due to ----->>>>>>"+e.getMessage());
		}
		if(json==null){
			json="{\"PROJECT_ID\":\"\":\"PROJECT_NAME\":\"\"}";
		}
		logger.info("LeadController | getDataForPrjectNameSelect() | :- END");
		return json;
	}

}
