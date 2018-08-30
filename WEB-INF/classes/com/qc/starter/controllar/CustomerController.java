package com.qc.starter.controllar;

import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.qc.starter.dto.CustomerDto;
import com.qc.starter.dto.LovDto;
import com.qc.starter.dto.UserDto;
import com.qc.starter.entity.CityEntity;
import com.qc.starter.entity.LeadEntity;
import com.qc.starter.entity.UserEntity;
import com.qc.starter.service.AddressService;
import com.qc.starter.service.CustomerService;
import com.qc.starter.service.EmailService;
import com.qc.starter.service.KeyContactService;
import com.qc.starter.service.LeadHeaderService;
import com.qc.starter.service.LeadService;
import com.qc.starter.service.MasterService;
import com.qc.starter.service.MobileService;
import com.qc.starter.service.PropertyService;

@Controller
public class CustomerController {
	private static final Logger logger = Logger.getLogger(CustomerController.class.getName());
	//private static ResourceBundle resource1=ResourceBundle.getBundle("ApplicationResource");
	@Autowired	CustomerService customerService;
	@Autowired	LeadService leadService;
	@Autowired	AddressService addressService;
	@Autowired	PropertyService propertyService;
	@Autowired	MasterService masterService;
	@Autowired	HttpSession session;
	@Autowired	KeyContactService keyContactService;
	@Autowired	LeadHeaderService leadHeaderService;
	@Autowired 	MobileService mobileService;
	@Autowired 	EmailService emailService;

	@InitBinder
	public void dateBinderForSave(WebDataBinder binder){
		logger.info("CustomerController | dateBinder() | :- START");
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class,"dob", new CustomDateEditor(dateFormat, true));
		binder.registerCustomEditor(Date.class,"incorporationDate", new CustomDateEditor(dateFormat, true));
		logger.info("CustomerController | dateBinder() | :- END");
	}
	
	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST }, value = "getDependentMaster")
	public @ResponseBody
	/*String getDependentMaster(HttpSession session, Model model,
			@RequestParam("idColumnName") String idColumnName,@RequestParam("valueColumnName") String valueColumnName,
			@RequestParam("dependentTableName") String dependentTableName,@RequestParam("crossTableName") String crossTableName,
			@RequestParam("crossTableDependentId") String crossTableDependentId,@RequestParam("crossTableMasterId") String crossTableMasterId,
			@RequestParam("masterValue") String masterValue) {*/
		String getDependentMaster(HttpSession session, Model model,@RequestBody String json){
		String responseJson="";
		UserEntity userEntity = (UserEntity) session.getAttribute("UserDetails");
		if (userEntity == null) {
			session.invalidate();			
			return "redirect:login.do?error=null";
		} else {
			
			try {
				
				String idColumnName="", valueColumnName ="", dependentTableName="", crossTableName="", crossTableDependentId="", crossTableMasterId="",masterValue="";
							
				String[] fields = json.split("&");
				String[] kv;
				HashMap<String, String> things = new HashMap<String, String>();
				for (int i = 0; i < fields.length; ++i)
				{
					kv = fields[i].split("=");
				    if (2 == kv.length)
				    {
				        things.put(kv[0], kv[1]); 
				    }
				}								
				List<LovDto> list = customerService.getMasterList(things.get("idColumnName"),things.get("valueColumnName"),things.get("dependentTableName"),things.get("crossTableName"),things.get("crossTableDependentId"),things.get("crossTableMasterId"),things.get("masterValue"));
				Gson gsonObj = new Gson();
				responseJson = gsonObj.toJson(list);
				return responseJson;				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}	
		return responseJson;
	}
	
	

	@RequestMapping(method=RequestMethod.POST, value={"sCustomer", "seCustomer"})
	public String saveCustomer(@ModelAttribute CustomerDto customerDto, @RequestParam String requestType,Model model){
		logger.info("CustomerController | saveCustomer() | :-START");
		UserEntity userEntity;
		try{
			userEntity = (UserEntity) session.getAttribute("UserDetails");
			if (userEntity == null) {
				session.invalidate();
			
				return "login";
			}
			//added by tripti
			if(customerDto.getCustEntityTypeId().equalsIgnoreCase("1000000002")){
				customerDto.setIndustryId(customerDto.getIndustryForNI());
				customerDto.setPan(customerDto.getCompanyPanNo());
				customerDto.setOccupationType(customerDto.getConstitution());
				customerDto.setSectorId(customerDto.getSectorForNI());
				customerDto.setStageId(customerDto.getStageForNI());
			}
			//---
			customerService.updateLeadDetails(customerDto);
			customerService.updateCustomerInfo(customerDto);
			customerService.updateOccupationInfo(customerDto);
			if(customerDto.getListMobile()!=null && customerDto.getListMobile().size()>0)mobileService.addUpdateContact(customerDto.getListMobile(), customerDto.getCaseId(), userEntity.getUserid()+"");
			if(customerDto.getListEmail()!=null && customerDto.getListEmail().size()>0)emailService.addUpdateContact(customerDto.getListEmail(), customerDto.getCaseId(), userEntity.getUserid()+"");
			
			//if(customerDto.getListAddress()!=null && customerDto.getListAddress().size()>0)addressService.addUpdateAddress(customerDto.getListAddress(), userEntity.getUserid()+"", customerDto.getCaseId(), customerDto.getPersonalDetailId());
			addressService.saveAddressFromProcedure(customerDto.getListAddress(), userEntity.getUserid()+"", customerDto.getCaseId());
			
			if(customerDto.getKeyContacts()!=null && customerDto.getKeyContacts().size()>0) keyContactService.addUpdateKeyContactList(customerDto.getKeyContacts(), userEntity, customerDto.getCaseId(), customerDto.getPersonalDetailId());
			if(customerDto.getListProperty()!=null && customerDto.getListProperty().size()>0)propertyService.addUpdatePropertyList(customerDto.getListProperty(), customerDto.getCaseId(), customerDto.getPersonalDetailId(), userEntity);
		}catch(Exception e){
			e.printStackTrace();
			logger.error("CustomerController | saveCustomer() | "+e.getMessage()+" | :-END");
		}
		logger.info("CustomerController | saveCustomer() | :-END");
		if(requestType.equalsIgnoreCase("save"))
			return "redirect:leadDetail.do";
		else return "workList";
	}


	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST }, value = "customer")
	public String showCustomer(@ModelAttribute(value = "user") UserDto userDto,
			BindingResult result, HttpSession session, Model model) {
		logger.info("CustomerController | showCustomer() | :- START");
		UserEntity userEntity;
		LeadEntity leadEntity;
		int caseId = 0;
		try{
			userEntity = (UserEntity) session
					.getAttribute("UserDetails");
			if (userEntity == null) {
				session.invalidate();
			
				return "login";
			}

			leadEntity = (LeadEntity) session.getAttribute("leadDetails");
			caseId = Integer.parseInt(leadEntity==null?"1000001":leadEntity.getCaseId());

		}catch(Exception e){
			logger.error("CustomerController | showCustomer() | "+ e.getMessage()+":- END");
		}finally{
			userEntity = null;
			leadEntity = null;
		}
		logger.info("CustomerController | showCustomer() | :- END");
		return "redirect:leadDetail.do?caseId=" + caseId;
	}

	@InitBinder
	public void dateBinder(WebDataBinder binder){
		logger.info("CustomerController | dateBinder() | :- START");
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class,"dob", new CustomDateEditor(dateFormat, true));
		logger.info("CustomerController | dateBinder() | :- END");
	}

	@RequestMapping(method = RequestMethod.POST, value = { "updateCustomerInfo" })
	public String upadteCustomerInfo(@ModelAttribute CustomerDto customerDto) {
		logger.info("CustomerController | upadteCustomerInfo() | :- START");
		boolean status = false;
		status = customerService.updateCustomerInfo(customerDto);
		logger.info("CustomerController | upadteCustomerInfo() | :- END");
		if (status == true) {

			return "redirect:leadDetail.do";
		}
		return "error";
	}

	@InitBinder
	public void dateBindeing(WebDataBinder binder){
		logger.info("CustomerController | dateBindeing() | :- START");
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, "incorperationDate" ,new CustomDateEditor(dateFormat, true));
		logger.info("CustomerController | dateBindeing() | :- END");
	}

	@RequestMapping(method = RequestMethod.POST, value = { "updateOccupationInfo" })
	public String upadteOccupationInfo(
			@ModelAttribute CustomerDto customerDto, ModelMap modelMap) {
		logger.info("CustomerController | upadteOccupationInfo() | :- START");
		boolean status = false;
		status = customerService.updateOccupationInfo(customerDto);
		logger.info("CustomerController | upadteOccupationInfo() | :- END");
		if (status == true) {

			return "redirect:leadDetail.do";
		}
		return "error";
	}

	@RequestMapping(method = RequestMethod.GET, value = "get_country_list" , headers="Accept=*/*")
	public @ResponseBody
	List<String> getLeadStateValue(HttpSession session,@RequestParam("term") String query){
		List<String> companyList = customerService.getCompanyList(query);
		return companyList;
	}



	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST }, value = "getCitiesByState")
	public @ResponseBody
	String getCitiesByState(HttpSession session,@RequestParam(value="stateId") String stateId) {

		logger.info("WorkListController | getCitiesByState() | :- START");
		String city = "";
		List<CityEntity> cityList;
		try {
			cityList = customerService.getCitiesByState(stateId);
			Gson gson = new Gson();
			city = gson.toJson(cityList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("WorklistController |" + e.getMessage() + "| :-END");
		} finally {
			session = null;
		}
		logger.info("WorkListController | getCitiesByState() | :- END");
		return city;
	}


}