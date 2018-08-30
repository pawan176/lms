package com.qc.starter.controllar;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.qc.starter.basic.CommonUtils;
import com.qc.starter.dto.AuthorizedProcDto;
import com.qc.starter.dto.ContactDto;
import com.qc.starter.dto.CustomerDto;
import com.qc.starter.dto.DocumentDto;
import com.qc.starter.dto.LeadHeaderDto;
import com.qc.starter.dto.LeadsSerchDto;
import com.qc.starter.dto.NewLeadDto;
import com.qc.starter.dto.ProductDto;
import com.qc.starter.dto.UserDto;
import com.qc.starter.entity.AddressEntity;
import com.qc.starter.entity.CaseXSellEntity;
import com.qc.starter.entity.CustomerEntity;
import com.qc.starter.entity.DocumentEntity;
import com.qc.starter.entity.EmailEntity;
import com.qc.starter.entity.LeadEntity;
import com.qc.starter.entity.MobileEntity;
import com.qc.starter.entity.UserEntity;
import com.qc.starter.mobileAction.Base64;
import com.qc.starter.mobileAction.ContactJsonParser;
import com.qc.starter.mobileAction.CustomerJsonParser;
import com.qc.starter.mobileAction.DocumentJsonParser;
import com.qc.starter.mobileAction.NewLeadJsonParser;
import com.qc.starter.mobileAction.ProductJsonParser;
import com.qc.starter.service.ActionService;
import com.qc.starter.service.AddressService;
import com.qc.starter.service.ContactService;
import com.qc.starter.service.CustomerService;
import com.qc.starter.service.DashboardService;
import com.qc.starter.service.DocumentService;
import com.qc.starter.service.EmailService;
import com.qc.starter.service.FacilityRequestedService;
import com.qc.starter.service.KeyContactService;
import com.qc.starter.service.LeadService;
import com.qc.starter.service.LoginService;
import com.qc.starter.service.MasterForMobileService;
import com.qc.starter.service.MasterService;
import com.qc.starter.service.MobileService;
import com.qc.starter.service.NewLeadService;
import com.qc.starter.service.ProductService;
import com.qc.starter.service.PropertyService;
import com.qc.starter.service.WorkListService;
//import com.qc.starter.dto.LoginDtoForMobile;
import com.qc.starter.service.XSellService;

@Controller
@RequestMapping(value = "/lms")
public class RequestHandlerOfMobile {
	private static final Logger logger = Logger
			.getLogger(RequestHandlerOfMobile.class.getName());
	@Autowired
	LoginService loginService;
	@Autowired
	NewLeadService newLeadService;
	@Autowired
	ActionService actionService;
	@Autowired
	ContactService contactService;
	@Autowired
	CustomerService customerService;
	@Autowired
	MobileService mobileService;
	@Autowired
	EmailService emailService;
	@Autowired
	AddressService addressService;
	@Autowired
	KeyContactService keyContactService;
	@Autowired
	PropertyService propertyService;
	@Autowired
	MasterService masterService;
	@Autowired
	WorkListService workListService;
	@Autowired
	LeadService leadService;
	@Autowired
	ProductService productService;
	@Autowired
	DocumentService documentService;
	@Autowired
	DashboardService dashboardService;
	@Autowired
	FacilityRequestedService facilityRequestedService;
	@Autowired
	MasterForMobileService forMobileService;
	@Autowired
	XSellService xSellService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody
	String login(@RequestBody String requestJson) {
		logger.info("WebService | RequestHandlerOfMobile | login() | :- START");
		String syncDate = CommonUtils.getCurrentDate();
		String responseJson = "{\"status\":\"F\",\"syncDate\":\"" + syncDate
				+ "\",\"message\":\"PROCESS FAIL\"}";
		try {
			JSONObject jobj = new JSONObject(requestJson);
			UserDto userDto = new UserDto();
			userDto.setUserName(jobj.getString("userName"));
			userDto.setPassword(jobj.getString("password"));
			userDto.setIpaddress(jobj.getString("ipAddress"));
			AuthorizedProcDto authorizedProcDto = loginService
					.userAthentication(userDto);
			if (authorizedProcDto.getAuthStatus() != null
					&& authorizedProcDto.getAuthStatus().equalsIgnoreCase("N")) {
				responseJson = "{\"status\":\"F\",\"syncDate\":\"" + syncDate+ "\",\"message\":\"" + authorizedProcDto.getAppError()+ "\"}";
			} else {
				UserEntity userEntity = loginService.userLogin(userDto);
				if (userEntity == null) {
					userEntity = new UserEntity();
				}
				List<UserEntity> myTeamList = dashboardService
						.getMyTeamList(userEntity.getUserid());
				Gson gson = new GsonBuilder().serializeNulls().create();
				Map<String, String> menuList = loginService
						.getMenuItems(userEntity.getUserid() + "");
				responseJson = "{\"status\":\"S\",\"syncDate\":\""
						+ syncDate
						+ "\",\"message\":\"Login Successfully\",\"menuList\":["
						+ parceMenuMapToJson(menuList) + "],\"userDetails\":"
						+ gson.toJson(userEntity) + ",\"teamDetails\":"
						+ gson.toJson(myTeamList) + "}";
			}
		} catch (Exception e) {
			responseJson = "{\"status\":\"F\",\"syncDate\":\"" + syncDate
					+ "\",\"message\":\"" + e.getMessage() + "\"}";
			logger.error("AWebService | RequestHandlerOfMobile | login() | Exception | "
					+ e.getMessage());
			e.printStackTrace();
		}
		logger.info("WebService | RequestHandlerOfMobile | login() | :- END");
		return responseJson;
	}

	@RequestMapping(value = "/generateNewLead", method = RequestMethod.POST)
	public @ResponseBody
	String generateNewLead(@RequestBody String requestJson) {
		String syncDate = CommonUtils.getCurrentDate();
		String responseJson = "{\"status\":\"F\",\"syncDate\":\"" + syncDate
				+ "\",\"message\":\"Error while Proceesing\"}";
		logger.info("WebService | RequestHandlerOfMobile | generateNewLead() | :- START request"+requestJson);
		try {
			JSONObject jsonObj = new JSONObject(requestJson);
			JSONArray list ;
			
			Gson gson = new GsonBuilder().serializeNulls().create();
			NewLeadDto newLeadDto = new NewLeadJsonParser().getJsonToNewLeadDto(jsonObj);			
			newLeadDto.setAppName("MOBILE");
			
			
			String leadDetail = newLeadService.createLead(newLeadDto);
			String leadDetailArr[] = leadDetail.split("#");
			
			String addressid = leadDetailArr[5];
			
			List<AddressEntity> addresslist = new ArrayList<AddressEntity>();;
			
			if(!CommonUtils.toString(addressid).equals("")){
				AddressEntity addObj = new AddressEntity();
				String addressIds[]= addressid.split("~");
				
				int lengthAddressIds = addressIds.length;
				
				for(int i=0;i<lengthAddressIds;i++){
					//addressEntity
					addObj = new AddressEntity();
					
					if(lengthAddressIds < 2){
						addObj.setAddressType("1000000002");
					}else{
						if(i==0){
							addObj.setAddressType("1000000001");	
						}else if(i==1){
							addObj.setAddressType("1000000002");
						}else{
							addObj.setAddressType("1000000003");
						}	
					}
										
					addObj.setAddressId(addressIds[i]);
					addObj.setMobile1_Id1("");				
					addObj.setMobile1_Id2("");		
					addObj.setEmail_Id("");
					addresslist.add(addObj);
					addObj = null;
				}				
			}
			
			list = new JSONArray(addresslist);

			if (leadDetailArr[2].equalsIgnoreCase("F")) {
				responseJson = "{\"leadCode\":\"\",\"syncDate\":\"" + syncDate
						+ "\",\"status\":\"" + leadDetailArr[2]
						+ "\",\"message\":\"" + leadDetailArr[3]
						+ "\",\"leadId\":\"\", \"syncDate\":\"" + syncDate
						+ "\"}";
			} else {
				ProductDto productDto = new ProductDto();
				productDto.setFacilityRequested(jsonObj.getString("productId"));
				productDto.setPurposeOfLoanId(jsonObj
						.getString("purposeOfLoan"));
				productDto.setFacilityRequestedLoanAmount(jsonObj
						.getString("loanAmount"));
				productDto.setCompyId(jsonObj.getString("companyId"));
				productDto.setUserId(jsonObj.getString("userId"));
				productDto.setHiddenLeadId(leadDetailArr[4]);
				boolean status1 = facilityRequestedService
						.updateExistingFacility(productDto);

				responseJson = "{\"leadCode\":\"" + leadDetailArr[1]
						+ "\",\"syncDate\":\"" + syncDate + "\",\"status\":\""
						+ leadDetailArr[2] + "\",\"message\":\""
						+ leadDetailArr[3] + "\",\"leadId\":\""						
						+ leadDetailArr[4] + "\",\"addresslist\":"								
						+ list + "}";
			}
		} catch (Exception e) {
			responseJson = "{\"status\":\"F\",\"syncDate\":\"" + syncDate
					+ "\",\"message\":\"" + e.getMessage() + "\"}";
			logger.error("AWebService | RequestHandlerOfMobile | generateNewLead() | Exception | "
					+ e.getMessage());
			e.printStackTrace();
		}
		logger.info("WebService | RequestHandlerOfMobile | generateNewLead() | :- END");
		return responseJson;
	}

	@RequestMapping(value = "/getLeadLists", method = RequestMethod.POST)
	public @ResponseBody
	String getLeadLists(@RequestBody String requestJson) {
		logger.info("WebService | RequestHandlerOfMobile | getLeadLists() | :- START");

		String lastSyncDate = CommonUtils.getCurrentDate();

		String responseJson = "{\"status\":\"S\",\"syncDate\":\""
				+ lastSyncDate + "\",\"message\":\"SUCCESS\"}";
		try {
			JSONObject jsonObject = new JSONObject(requestJson);
			LeadsSerchDto leadsSerchDto = new LeadsSerchDto();
			leadsSerchDto.setCompany(jsonObject.getString("companyId"));
			leadsSerchDto.setUserId(jsonObject.getString("userId"));
			leadsSerchDto.setRequestType("MOBILE");
			leadsSerchDto.setSyncDate(jsonObject.getString("syncDate"));
			List<LeadHeaderDto> leadlist = workListService
					.getLeadDetails(leadsSerchDto);
			if (leadlist.size() > 0) {
				responseJson = "{\"status\":\"S\",\"syncDate\":\""
						+ lastSyncDate
						+ "\",\"message\":\"SUCCESS\",\"leadLists\":"
						+ new GsonBuilder().serializeNulls().create()
								.toJson(leadlist) + "}";
			}
		} catch (Exception e) {
			e.printStackTrace();
			responseJson = "{\"status\":\"F\",\"syncDate\":\"" + lastSyncDate
					+ "\",\"message\":\"" + e.getMessage() + "\"}";
			logger.error("AWebService | RequestHandlerOfMobile | getLeadLists() | Exception | "
					+ e.getMessage());
		}
		logger.info("WebService | RequestHandlerOfMobile | getLeadLists() | :- END");
		return responseJson;
	}

	@RequestMapping(value = "/getLeadDetails", method = RequestMethod.POST)
	public @ResponseBody
	String getLeadDetails(@RequestBody String requestJson) {
		logger.info("WebService | RequestHandlerOfMobile | getLeadDetails() | :- START");
		String syncDate = CommonUtils.getCurrentDate();
		String responseJson = "{\"status\":\"F\",\"syncDate\":\"" + syncDate
				+ "\",\"message\":\"NO DATA FOUND\"}";
		try {
			JSONObject jsonObject = new JSONObject(requestJson);

			LeadEntity leadEntity = leadService.getLead(
					Integer.parseInt(jsonObject.getString("caseId")),
					jsonObject.getString("companyId"));

			String date = leadEntity.getGenerationDate();	
			
			String referenceName = leadEntity.getReferenceName();
			leadEntity.setReferenceName(referenceName);
			
			CustomerEntity customerEntity = (CustomerEntity) customerService
					.getCustomerFromLeadId(jsonObject.getInt("caseId"));
			List<AddressEntity> listAddress = null;

			if (customerEntity != null) {
				listAddress = addressService.getAddressList(customerEntity
						.getPersonalDetailId());
			}
			List<MobileEntity> listMobile = mobileService
					.getListOfMobile(jsonObject.getInt("caseId"));
			List<EmailEntity> listEmail = emailService
					.getListOfEmail(jsonObject.getInt("caseId"));
			Gson gson = new GsonBuilder().serializeNulls().create();
			if (leadEntity != null && leadEntity.getCaseId() != null) {
				String addressDetails = (listAddress == null ? "[]" : gson
						.toJson(listAddress));
				String mobiledetails = (listMobile == null ? "[]" : gson
						.toJson(listMobile));
				String emailDetails = (listEmail == null ? "[]" : gson
						.toJson(listEmail));
				// leadEntity.setApplicantType(customerEntity.getCompanyType());
				// leadEntity.setConstitution(customerEntity.getOccupationType());
				// leadEntity.setCompanyId(customerEntity.getCompanyId());
				leadEntity.setStage("");
				leadEntity.setSector("");

				/*leadEntity.setGenerationDate(CommonUtils
						.getFormatedStringDateFromEntity(leadEntity
								.getGenerationDate()));
				leadEntity.setCreatedSystemDate(CommonUtils
						.getFormatedStringDateFromEntity(leadEntity
								.getCreatedSystemDate()));*/

				// leadEntity.setCustEntityTypeId(customerEntity.getCustEntityTypeId());
				// leadEntity.setCustCategory(customerEntity.getCustCategory());
				// leadEntity.setAuthSignatoryFName(customerEntity.getAuthSignatoryFName());
				// leadEntity.setAuthSignatoryMName(customerEntity.getAuthSignatoryMName());
				// leadEntity.setAuthSignatoryLName(customerEntity.getAuthSignatoryLName());
				// leadEntity.setPan(customerEntity.getPan());
				// leadEntity.setIndustryId(customerEntity.getIndustryId());
				// leadEntity.setIncorperationDate(customerEntity.getIncorperationDate());
				for (MobileEntity mobileEntity : listMobile) {
					if ("Y".equalsIgnoreCase(mobileEntity.getPrimaryContact())) {
						customerEntity.setCaseMobileId(mobileEntity
								.getCaseMobileId());
						customerEntity.setMobileNo(mobileEntity.getContactNo());
					}
				}
				for (EmailEntity emailEntity : listEmail) {
					if ("Y".equalsIgnoreCase(emailEntity.getPrimaryEmail())) {
						customerEntity.setCaseEmailId(emailEntity
								.getCaseEmailId());
						customerEntity.setEmailId(emailEntity.getEmail());
					}
				}
				responseJson = "{\"status\":\"S\",\"syncDate\":\"" + syncDate
						+ "\",\"message\":\"SUCCESS\",\"leadsDetails\":"
						+ gson.toJson(leadEntity) + ",\"customerDetails\":"
						+ gson.toJson(customerEntity) + ",\"addressDetails\":"
						+ addressDetails + ",\"mobiledetails\":"
						+ mobiledetails + ",\"emailDetails\":" + emailDetails
						+ "}";
			}
		} catch (Exception e) {
			e.printStackTrace();
			responseJson = "{\"status\":\"F\",\"syncDate\":\"" + syncDate
					+ "\",\"message\":\"" + e.getMessage() + "\"}";
			logger.error("AWebService | RequestHandlerOfMobile | getLeadDetails() | Exception | "
					+ e.getMessage());
		}
		logger.info("WebService | RequestHandlerOfMobile | getLeadDetails() | :- END");
		return responseJson;
	}

	@RequestMapping(value = "/saveActionHistory", method = RequestMethod.POST)
	public @ResponseBody
	String saveActionHistory(@RequestBody String requestJson) {
		logger.info("WebService | RequestHandlerOfMobile | saveActionHistory() | :- START");
		String responseJson = "{\"status\":\"F\",\"message\":\"ACTION NOT SAVE SUCCESSFULLY\",\"actionId\":\"\"}";
		String syncDate = CommonUtils.getCurrentDate();
		try {
			ContactDto contactDto = new ContactJsonParser()
					.getJsonToContactDto(requestJson);
			String currActionId = actionService.saveCaseAction(contactDto);
			if (currActionId != null) {
				responseJson = "{\"status\":\"S\",\"syncDate\":\""
						+ syncDate
						+ "\",\"message\":\"ACTION SAVE SUCCESSFULLY\",\"actionId\":\""
						+ currActionId + "\"}";
			}
		} catch (Exception e) {
			responseJson = "{\"status\":\"F\",\"syncDate\":\"" + syncDate
					+ "\",\"message\":\"" + e.getMessage()
					+ "\",\"actionId\":\"\"}";
			logger.error("AWebService | RequestHandlerOfMobile | saveActionHistory() | Exception | "
					+ e.getMessage());
			e.printStackTrace();
		}
		logger.info("WebService | RequestHandlerOfMobile | saveActionHistory() | :- END");
		return responseJson;
	}

	@RequestMapping(value = "/getActionHistory", method = RequestMethod.POST)
	public @ResponseBody
	String getActionHistory(@RequestBody String requestJson) {
		logger.info("WebService | RequestHandlerOfMobile | getActionHistory() | :- START");
		String syncDate = CommonUtils.getCurrentDate();
		String responseJson = "{\"status\":\"S\",\"syncDate\":\"" + syncDate
				+ "\",\"message\":\"Synced Successfully\"}";
		try {
			JSONObject jsonObject = new JSONObject(requestJson);
			ContactDto contactDto = contactService.getContactDetail(
					jsonObject.getString("caseId"),
					jsonObject.getString("historyName"),
					jsonObject.getString("userId"),
					jsonObject.getString("companyId"),
					jsonObject.getString("requestType"));
			if (contactDto != null
					&& contactDto.getCaseActionHistory().size() > 0) {
				responseJson = "{\"status\":\"S\",\"syncDate\":\""
						+ syncDate
						+ "\",\"message\":\"SUCCESSS\",\"actionHistory\":"
						+ new GsonBuilder().serializeNulls().create()
								.toJson(contactDto.getCaseActionHistory())
						+ "}";
			}
		} catch (Exception e) {
			e.printStackTrace();
			responseJson = "{\"status\":\"F\",\"syncDate\":\"" + syncDate
					+ "\",\"message\":\"" + e.getMessage() + "\"}";
			logger.error("AWebService | RequestHandlerOfMobile | getActionHistory() | Exception | "
					+ e.getMessage());
		}
		logger.info("WebService | RequestHandlerOfMobile | getActionHistory() | :- END");
		return responseJson;
	}

	@RequestMapping(value = "/saveCustomerDetails", method = RequestMethod.POST)
	public @ResponseBody
	String saveCustomerDeatils(@RequestBody String requestJson) {
		logger.info("WebService | RequestHandlerOfMobile | saveCustomerDetails() | :- START");
		String syncDate = CommonUtils.getCurrentDate();
		String responseJson = "{\"status\":\"F\",\"syncDate\":\"" + syncDate
				+ "\",\"message\":\"NO DATA FOUND\"}";
		boolean updateCustomerFlag = false;
		boolean updateEmailFlag =false;
		boolean updateContactFlag=false;
		boolean updateAddressFlag = false;
		boolean updateCustomerOccupationFlag = false;
		List<AddressEntity> addresslist=null;
		JSONArray list=null;
		String result="";
		try {
			
			JSONObject jsonObject = new JSONObject(requestJson);
			CustomerDto customerDto = new CustomerJsonParser().getJsonToCustomerDto(jsonObject);
			
			if (jsonObject.getString("customerUpdateFlag").equalsIgnoreCase("Y")) {
				
				updateCustomerFlag = customerService.updateCustomerInfo(customerDto);
				String response = leadService.updateLeadDetails(requestJson);
				updateCustomerOccupationFlag = customerService.updateOccupationInfo(customerDto);
				
			}
			if (jsonObject.getString("occupationUpdateFlag").equalsIgnoreCase("Y")) {
				
				updateCustomerOccupationFlag = customerService.updateOccupationInfo(customerDto);
				
				if (jsonObject.getString("allocationFlag") !=null && jsonObject.getString("allocationFlag").equalsIgnoreCase("Y")){
					String updateString = workListService.allocationFromMobile(customerDto);
				}
				
			}
			if (customerDto.getListMobile() != null
					&& customerDto.getListMobile().size() > 0) {
				 updateContactFlag = mobileService.addUpdateContact(
						customerDto.getListMobile(), customerDto.getCaseId(),
						customerDto.getUserId());
			}
			if (customerDto.getListEmail() != null
					&& customerDto.getListEmail().size() > 0) {
				 updateEmailFlag = emailService.addUpdateContact(
						customerDto.getListEmail(), customerDto.getCaseId(),
						customerDto.getUserId());
			}
			if (customerDto.getListAddress() != null
					&& customerDto.getListAddress().size() > 0) {
				
				addresslist = addressService.saveAddressFromProcedure(customerDto.getListAddress(), customerDto.getUserId(), customerDto.getCaseId()); 
				
				/*addresslist = addressService.addUpdateAddressMob(
						customerDto.getListAddress(), customerDto.getUserId(),
						customerDto.getCaseId(),
						customerDto.getPersonalDetailId());*/
				 list = new JSONArray(addresslist);
				 	 
				/* List<MobileEntity> listMobiles=UpdateMobileList.getMobileList();*/
				
			}
			if (list != null || list != null || updateContactFlag || updateEmailFlag || updateCustomerFlag || updateAddressFlag || updateCustomerOccupationFlag)  {
				responseJson = "{\"status\":\"S\",\"syncDate\":\"" + syncDate + "\",\"addresslist\":"+list +",\"message\":\"SAVE DATA SUCCESSFULLY\"}";
			} else {
				responseJson = "{\"status\":\"F\",\"syncDate\":\"" + syncDate
						+ "\",\"message\":\"Fail to save Data\"}";
			}
		} catch (Exception e) {
			e.printStackTrace();
			responseJson = "{\"status\":\"F\",\"syncDate\":\"" + syncDate
					+ "\",\"message\":\"" + e.getMessage() + "\"}";
			logger.error("AWebService | RequestHandlerOfMobile | saveCustomerDetails() | Exception | "
					+ e.getMessage());
		}
		logger.info("WebService | RequestHandlerOfMobile | saveCustomerDetails() | :- END");
		return responseJson;
	}// , headers="content-type=multipart/form-data"

	@RequestMapping(value = "/documentUpload", headers = "content-type=multipart/form-data", method = RequestMethod.POST)
	public @ResponseBody
	String uploadDocument(@RequestParam("jsonMeta") String jsonMeta,
			@RequestParam("file") MultipartFile[] files) {
		logger.info("WebService | RequestHandlerOfMobile | uploadDocument() | :- START");
		String syncDate = CommonUtils.getCurrentDate();
		String responseJson = "{\"status\":\"F\",\"syncDate\":\"" + syncDate
				+ "\",\"message\":\"Fail to upload File\"}";
		try {
			JSONObject reqJson = new JSONObject(jsonMeta);
			List<DocumentDto> documentDtos = new DocumentJsonParser()
					.getJsonToDocumentDto(reqJson, files);
			List<DocumentDto> list = documentService
					.saveUpdateDocument(documentDtos,
							Integer.parseInt(reqJson.getString("caseId")));
			if (list.size() > 0) {
				responseJson = list.get(0).getStatus();
			}
		} catch (Exception e) {
			responseJson = "{\"status\":\"F\",\"syncDate\":\"" + syncDate
					+ "\",\"message\":\"" + e.getMessage() + "\"}";
			logger.error("AWebService | RequestHandlerOfMobile | uploadDocument() | Exception | "
					+ e.getMessage());
			e.printStackTrace();
		}
		logger.info("WebService | RequestHandlerOfMobile | uploadDocument() | :- END");
		return responseJson;
	}

	@RequestMapping(value = "/getDocumentDetails", method = RequestMethod.POST)
	public @ResponseBody
	String getDocumentDetails(@RequestBody String requestJson) {
		logger.info("WebService | RequestHandlerOfMobile | getDocumentDetails() | :- START");
		String syncDate = CommonUtils.getCurrentDate();
		String responseJson = "{\"status\":\"F\",\"syncDate\":\"" + syncDate
				+ "\",\"message\":\"NO DATA FOUND\"}";
		try {
			JSONObject reqJson = new JSONObject(requestJson);
			// List<DocumentDto> documentList =
			// documentService.getDocumentHistory(reqJson.getString("caseId"),reqJson.getString("syncDate"));
			List<DocumentDto> documentList = documentService
					.getDocumentHistory(reqJson.getString("caseId"));
			if (documentList.size() > 0) {
				responseJson = "{\"status\":\"S\",\"message\":\"SUCCESS\",\"documentList\":"
						+ new GsonBuilder().serializeNulls().create()
								.toJson(documentList) + "}";
			}
		} catch (Exception e) {
			responseJson = "{\"status\":\"F\",\"syncDate\":\"" + syncDate
					+ "\",\"message\":\"" + e.getMessage() + "\"}";
			logger.error("AWebService | RequestHandlerOfMobile | getDocumentDetails() | Exception | "
					+ e.getMessage());
			e.printStackTrace();
		}
		logger.info("WebService | RequestHandlerOfMobile | getDocumentDetails() | :- END");
		return responseJson;
	}

	/*
	 * @RequestMapping(value="/downloadDocument",
	 * method={RequestMethod.POST,RequestMethod.GET }) public
	 * 
	 * @ResponseBody String downloadDocument(
	 * 
	 * @RequestParam("docId") String docId, HttpServletResponse response) {
	 * //@RequestBody String requestJson,HttpServletResponse response){
	 * logger.info
	 * ("WebService | RequestHandlerOfMobile | downloadDocument() | :- START");
	 * String
	 * responseJson="{\"status\":\"F\",\"message\":\"Downloading fail\"}";
	 * OutputStream out =null; InputStream inputStream =null; try { //JSONObject
	 * reqJson=new JSONObject(requestJson); //List<DocumentEntity>
	 * documentEntities =
	 * documentService.getDocumentBlob(reqJson.getString("docId"
	 * ),reqJson.getString("documentTypeId")); List<DocumentEntity>
	 * documentEntities = documentService.getDocumentBlob(docId,""); String
	 * filePath = documentEntities.get(0).getDocumentCode(); filePath =
	 * filePath.replaceAll("\\\\", "//"); FileInputStream fileInputStream = new
	 * FileInputStream(filePath); inputStream = (InputStream) fileInputStream;
	 * response.setHeader("Content-Disposition", "inline;filename=\""+
	 * documentEntities.get(0).getDocumentName() + "\""); out =
	 * response.getOutputStream();
	 * response.setContentType("application/octet-stream");
	 * IOUtils.copy(inputStream, out); out.flush(); out.close();
	 * responseJson="{\"status\":\"S\",\"message\":\"File download successfully\"}"
	 * ; } catch (Exception e) {
	 * responseJson="{\"status\":\"F\",\"message\":\""+e.getMessage()+"\"}";
	 * logger.error(
	 * "AWebService | RequestHandlerOfMobile | downloadDocument() | Exception | "
	 * + e.getMessage() ); e.printStackTrace(); } logger.info(
	 * "WebService | RequestHandlerOfMobile | downloadDocument() | :- END");
	 * return responseJson; }
	 */
	@RequestMapping(value = "/getDocument", method = RequestMethod.POST)
	public @ResponseBody
	String getDocument(@RequestBody String requestJson) {
		logger.info("WebService | RequestHandlerOfMobile | getDocument() | :- START");
		String syncDate = CommonUtils.getCurrentDate();
		String responseJson = "{\"status\":\"F\",\"syncDate\":\"" + syncDate
				+ "\",\"message\":\"NO DATA FOUND\"}";
		try {
			JSONObject reqJson = new JSONObject(requestJson);
			List<DocumentEntity> documentEntities = documentService
					.getDocumentBlob(reqJson.getString("docId"), "");
			if (documentEntities.size() > 0) {
				File file = new File(documentEntities.get(0).getDocumentCode()
						.replaceAll("\\\\", "//"));
				if (file.exists()) {
					String fileByteStreams = Base64.encodeBytes(FileUtils
							.readFileToByteArray(file));
					responseJson = "{\"status\":\"S\",\"syncDate\":\""
							+ syncDate
							+ "\",\"message\":\"SUCCESS\",\"fileName\":\""
							+ documentEntities.get(0).getDocumentName()
							+ "\",\"documentStream\":\"" + fileByteStreams
							+ "\"}";
				}
			}
		} catch (Exception e) {
			responseJson = "{\"status\":\"F\",\"syncDate\":\"" + syncDate
					+ "\",\"message\":\"" + e.getMessage() + "\"}";
			logger.error("AWebService | RequestHandlerOfMobile | getDocument() | Exception | "
					+ e.getMessage());
			e.printStackTrace();
		}
		logger.info("WebService | RequestHandlerOfMobile | getDocument() | :- END");
		return responseJson;
	}

	@RequestMapping(value = "/saveLeadDetails", method = RequestMethod.POST)
	public @ResponseBody
	String saveLeadDetails(@RequestBody String requestJson) {
		logger.info("WebService | RequestHandlerOfMobile | saveLeadDetails() | :- START");
		String syncDate = CommonUtils.getCurrentDate();
		String responseJson = "{\"status\":\"F\",\"syncDate\":\"" + syncDate
				+ "\",\"message\":\"Error while saveing Data\"}";
		try {
			// JSONObject reqJson=new JSONObject(requestJson);
			responseJson = leadService.updateLeadDetails(requestJson);
		} catch (Exception e) {
			responseJson = "{\"status\":\"F\",\"syncDate\":\"" + syncDate
					+ "\",\"message\":\"" + e.getMessage() + "\"}";
			logger.error("AWebService | RequestHandlerOfMobile | saveLeadDetails() | Exception | "
					+ e.getMessage());
			e.printStackTrace();
		}
		logger.info("WebService | RequestHandlerOfMobile | saveLeadDetails() | :- END");
		return responseJson;
	}

	@RequestMapping(value = "/getMasters", method = RequestMethod.POST)
	public @ResponseBody
	String getMasters(@RequestBody String requestJson) {
		logger.info("WebService | RequestHandlerOfMobile | getMasters() | :- START");
		String syncDate = CommonUtils.getCurrentDate();
		String responseJson = "{\"status\":\"F\",\"syncDate\":\"" + syncDate
				+ "\",\"message\":\"NO DATA FOUND\"}";
		try {
			responseJson = forMobileService.getAllMasters(requestJson);
			// responseJson=new GsonBuilder().toJson(response);

		} catch (Exception e) {
			e.printStackTrace();
			responseJson = "{\"status\":\"F\",\"syncDate\":\"" + syncDate
					+ "\",\"message\":\"" + e.getMessage() + "\"}";
			logger.error("AWebService | RequestHandlerOfMobile | getMasters() | Exception | "
					+ e.getMessage());
		}
		logger.info("WebService | RequestHandlerOfMobile | getMasters() | :- END");
		return responseJson;
	}

	/*
	 * @RequestMapping(value="/eligibilityCalculator", method=RequestMethod.POST
	 * ) public
	 * 
	 * @ResponseBody String eligibilityCalculator(@RequestBody String
	 * requestJson){ logger.info(
	 * "WebService | RequestHandlerOfMobile | eligibilityCalculator() | :- START"
	 * ); String syncDate = CommonUtils.getCurrentDate(); String
	 * responseJson="{\"status\":\"F\",\"syncDate\":\""
	 * +syncDate+"\",\"message\":\"Error while saveing Data\"}"; try {
	 * 
	 * List<ProductDto>
	 * prodList=productService.getEligibilityCalcData(calculatorParser
	 * .getJsonToDto(requestJson));
	 * responseJson=calculatorParser.getDtotoJson(prodList); } catch (Exception
	 * e) {
	 * responseJson="{\"status\":\"F\",\"syncDate\":\""+syncDate+"\",\"message\":\""
	 * +e.getMessage()+"\"}"; logger.error(
	 * "AWebService | RequestHandlerOfMobile | eligibilityCalculator() | Exception | "
	 * + e.getMessage() ); e.printStackTrace(); } logger.info(
	 * "WebService | RequestHandlerOfMobile | eligibilityCalculator() | :- END"
	 * ); return responseJson; }
	 */
	// --------------------------------
	@RequestMapping(value = "/saveAgentTravelSummary", method = RequestMethod.POST)
	public @ResponseBody
	String saveAgentTravelSummary(@RequestBody String requestJson) {
		logger.info("WebService | RequestHandlerOfMobile | saveAgentTravelSummary() | :- START");
		String syncDate = CommonUtils.getCurrentDate();
		String responseJson = "{\"status\":\"F\",\"syncDate\":\"" + syncDate
				+ "\",\"message\":\"PROCESS FAIL\"}";
		try {
			responseJson = forMobileService.saveAgentSummary(requestJson);
		} catch (Exception e) {
			e.printStackTrace();
			responseJson = "{\"status\":\"F\",\"syncDate\":\"" + syncDate
					+ "\",\"message\":\"" + e.getMessage() + "\"}";
			logger.error("WebService | RequestHandlerOfMobile | saveAgentTravelSummary() | Exception | "
					+ e.getMessage());
		}
		logger.info("WebService | RequestHandlerOfMobile | saveAgentTravelSummary() | :- END");
		return responseJson;
	}

	@RequestMapping(value = "/getAgentTravelSummary", method = RequestMethod.POST)
	public @ResponseBody
	String getAgentTravelSummary(@RequestBody String requestJson) {
		logger.info("WebService | RequestHandlerOfMobile | getAgentTravelSummary() | :- START");
		String responseJson = "{\"status\":\"F\",\"message\":\"PROCESS FAIL\"}";
		try {
			responseJson = forMobileService.getAgentSummary(requestJson);

		} catch (Exception e) {
			e.printStackTrace();
			responseJson = "{\"status\":\"F\",\"message\":\"" + e.getMessage()
					+ "\"}";
			logger.error("WebService | RequestHandlerOfMobile | getAgentTravelSummary() | Exception | "
					+ e.getMessage());
		}
		logger.info("WebService | RequestHandlerOfMobile | getAgentTravelSummary() | :- END");
		return responseJson;
	}

	@RequestMapping(value = "/saveFacilityRequested", method = RequestMethod.POST)
	public @ResponseBody
	String saveFacilityRequested(@RequestBody String requestJson) {
		logger.info("WebService | RequestHandlerOfMobile | saveFacilityRequested() | :- START");
		String responseJson = "{\"status\":\"F\",\"message\":\"PROCESS FAIL\"}";
		try {
			ProductDto productDto = new ProductJsonParser()
					.getJsonToProductDto(requestJson);
			boolean status = facilityRequestedService
					.updateExistingFacility(productDto);
			if (status) {
				responseJson = "{\"status\":\"S\",\"message\":\"Facility requested has successfully saved\"}";
			} else {
				responseJson = "{\"status\":\"F\",\"message\":\"Failed to save facility requested\"}";
			}
		} catch (Exception e) {
			e.printStackTrace();
			responseJson = "{\"status\":\"F\",\"message\":\"" + e.getMessage()
					+ "\"}";
			logger.error("WebService | RequestHandlerOfMobile | saveFacilityRequested() | Exception | "
					+ e.getMessage());
		}
		logger.info("WebService | RequestHandlerOfMobile | saveFacilityRequested() | :- END");
		return responseJson;
	}

	// --------------------------------Develop on 20-Jan-2017------------------
	@RequestMapping(value = "/saveProductHistory", method = RequestMethod.POST)
	public @ResponseBody
	String saveProductHistory(@RequestBody String requestJson) {
		logger.info("WebService | RequestHandlerOfMobile | saveProductHistory() | :- START");
		String responseJson = "{\"status\":\"F\",\"message\":\"PROCESS FAIL\"}";
		try {
			String insertStatus = "";
			String updateStatus = "";
			ProductDto productDto = new ProductJsonParser()
					.getJsonToProductDto(requestJson);
			boolean status1 = facilityRequestedService
					.updateExistingFacility(productDto);
			List<CaseXSellEntity> listXSell = productDto.getListXSell();
			if (listXSell != null) {
				try {
					List<CaseXSellEntity> updateList = new ArrayList<CaseXSellEntity>();
					List<CaseXSellEntity> insertList = new ArrayList<CaseXSellEntity>();
					for (CaseXSellEntity xSellEntity : listXSell) {
						if (xSellEntity != null
								&& xSellEntity.getXsellId() != null
								&& !(xSellEntity.getXsellId().trim()
										.equalsIgnoreCase("insert"))) {
							updateList.add(xSellEntity);
						} else if (xSellEntity != null
								&& xSellEntity.getXsellId() != null
								&& (xSellEntity.getXsellId().trim()
										.equalsIgnoreCase("insert"))) {
							insertList.add(xSellEntity);
						}
					}
					if (insertList.size() > 0) {
						insertStatus = xSellService.insertXSell(insertList);
						if (insertStatus == null)
							return "error";
					}
					if (updateList.size() > 0) {
						insertStatus = xSellService.updateXSell(updateList);
						if (updateStatus == null)
							return "error";
					}
				} catch (Exception e) {
					// logger.info("Error in catch blok due to ::::-->"+e.getMessage());
					e.printStackTrace();
					return "error";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			responseJson = "{\"status\":\"F\",\"message\":\"" + e.getMessage()
					+ "\"}";
			logger.error("WebService | RequestHandlerOfMobile | saveProductHistory() | Exception | "
					+ e.getMessage());
		}
		logger.info("WebService | RequestHandlerOfMobile | saveProductHistory() | :- END");
		return responseJson;
	}

	public String parceMenuMapToJson(Map<String, String> map) {
		String menuList = "";
		try {
			int count = 0;
			Set<String> set = map.keySet();
			for (String menuId : set) {
				count = 1;
				menuList = menuList + "{\"menuId\":\"" + menuId
						+ "\",\"menuName\":\"" + map.get(menuId) + "\"},";
			}
			if (count == 1) {
				menuList = menuList.substring(0, menuList.length() - 1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return menuList;
	}
}
