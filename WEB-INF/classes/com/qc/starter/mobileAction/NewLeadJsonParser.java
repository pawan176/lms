package com.qc.starter.mobileAction;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.qc.starter.basic.CommonUtils;
import com.qc.starter.dto.NewLeadDto;
import com.qc.starter.entity.AddressEntity;
import com.qc.starter.entity.EmailEntity;
import com.qc.starter.entity.KeyContactsEntity;
import com.qc.starter.entity.MobileEntity;

public class NewLeadJsonParser {
	public NewLeadDto getJsonToNewLeadDto(JSONObject jsonObj){
		NewLeadDto newLeadDto=null;
		try {
			newLeadDto=new NewLeadDto();
			List<AddressEntity> listAddress=new ArrayList<AddressEntity>();
			List<KeyContactsEntity> listKeyContact=new ArrayList<KeyContactsEntity>();
			List<MobileEntity> listMobile=new ArrayList<MobileEntity>();
			List<EmailEntity> listEmail=new ArrayList<EmailEntity>();
			//newLeadDto.setpe 1000000042
			newLeadDto.setNewleadType(jsonObj.getString("newleadType"));
			newLeadDto.setTitle(jsonObj.getString("title"));
			newLeadDto.setFirstName(jsonObj.getString("firstName"));
			newLeadDto.setLastName(jsonObj.getString("lastName"));
			newLeadDto.setMiddleName(jsonObj.getString("middleName"));
			newLeadDto.setMaritalStatus(jsonObj.getString("maritalStatus"));
			newLeadDto.setDob(jsonObj.getString("dob"));
			newLeadDto.setGender(jsonObj.getString("gender"));
			newLeadDto.setNationality(jsonObj.getString("nationality"));
			newLeadDto.setNoOfDependents(jsonObj.getString("noOfDependents"));
			newLeadDto.setPan(jsonObj.getString("pan"));
			newLeadDto.setAdhaarNumber(jsonObj.getString("adhaarNumber"));
			newLeadDto.setCompanyType(jsonObj.getString("companyType"));
			newLeadDto.setCompanyName(jsonObj.getString("companyName"));
			newLeadDto.setOtherCompanyName(jsonObj.getString("otherCompanyName"));
			
			if(CommonUtils.toString(jsonObj.getString("otherCompanyName")).equals("")){
				newLeadDto.setOtherCompanyName(jsonObj.getString("companyName"));
			}
			
			newLeadDto.setDesignation(jsonObj.getString("designation"));
			newLeadDto.setModeOfSalary(jsonObj.getString("modeOfSalary"));
			newLeadDto.setYearOfCurrJob(jsonObj.getString("yearOfCurrJob"));
			newLeadDto.setWorkExperiance(jsonObj.getString("workExperiance"));
			newLeadDto.setAnnualIncome(jsonObj.getString("annualIncome"));
			newLeadDto.setGrossMonthlyIncome(jsonObj.getString("grossMonthlyIncome"));
			newLeadDto.setNetMonthlyIncome(jsonObj.getString("netMonthlyIncome"));
			newLeadDto.setOtherMonthlyIncome(jsonObj.getString("otherMonthlyIncome"));
			newLeadDto.setMonthlyRentalIncome(jsonObj.getString("monthlyRentalIncome"));			
			/*if(jsonObj.isNull("annualSalesTurnOver")){
				newLeadDto.setAnnualSalesTurnOver("");
			}else{
				newLeadDto.setAnnualSalesTurnOver(jsonObj.getString("annualSalesTurnOver"));
			}*/
			newLeadDto.setAnnualSalesTurnOver(jsonObj.getString("annualSalesTurnOver"));
			newLeadDto.setGrossProfit(jsonObj.getString("grossProfit"));
			newLeadDto.setNetProfitAtTax(jsonObj.getString("netProfitAtTax"));
			newLeadDto.setOtherAnnualIncome(jsonObj.getString("otherAnnualIncome"));
			newLeadDto.setAnnualRentalIncome(jsonObj.getString("annualRentalIncome"));
			newLeadDto.setDateOfIncorparation(jsonObj.getString("dateOfIncorparation"));
			newLeadDto.setNetWorth(jsonObj.getString("netWorth"));
			newLeadDto.setCorpSalaryAcount(jsonObj.getString("corpSalaryAcount"));
			if(jsonObj.getString("productId").equalsIgnoreCase("")){
			jsonObj.put("productId", "1000000002");
			}
			newLeadDto.setProductId(jsonObj.getString("productId"));
			
			if(jsonObj.getString("source").equalsIgnoreCase("")){
				newLeadDto.setQueueId("0");				
			}else{
				newLeadDto.setQueueId(jsonObj.getString("source"));
				newLeadDto.setSource(jsonObj.getString("source"));
			}
			
			
			newLeadDto.setReferenceName(jsonObj.getString("referenceName"));
			newLeadDto.setReferenceNumber(jsonObj.getString("referenceNum"));
			
			newLeadDto.setCampaign(jsonObj.getString("campaign"));
			newLeadDto.setAllocateTo(jsonObj.getString("allocateTo"));
			newLeadDto.setUserId(jsonObj.getString("userId"));
			newLeadDto.setCompanyId(jsonObj.getString("companyId"));
			newLeadDto.setBonusIncentive(jsonObj.getString("bonusIncentive"));
			newLeadDto.setDepreciation(jsonObj.getString("depreciation"));
			newLeadDto.setDirectorSalary(jsonObj.getString("directorSalary"));
			newLeadDto.setInteresrPaidOnLoan(jsonObj.getString("interesrPaidOnLoan"));

			newLeadDto.setEntityType(jsonObj.getString("entityType"));

			newLeadDto.setCustomerCategory(jsonObj.getString("customerCategory"));
			newLeadDto.setAuthSignatoryFName(jsonObj.getString("authSignatoryFName"));
			newLeadDto.setAuthSignatoryMName(jsonObj.getString("authSignatoryMName"));
			newLeadDto.setAuthSignatoryLName(jsonObj.getString("authSignatoryLName"));

			//if("1000000001".equalsIgnoreCase(jsonObj.getString("entityType"))){
			newLeadDto.setOccupationType(jsonObj.getString("constitution"));//------------------Occupation not comming
			newLeadDto.setConstitution(jsonObj.getString("constitution"));
			
			newLeadDto.setIndustry(jsonObj.getString("industry"));
			newLeadDto.setBranch(CommonUtils.toString(jsonObj.getString("branch")));
			newLeadDto.setPurposeOfLoan(CommonUtils.toString(jsonObj.getString("purposeOfLoan")));
			newLeadDto.setScheme(CommonUtils.toString(jsonObj.getString("scheme")));
			newLeadDto.setLoanAmount(CommonUtils.toString(jsonObj.getString("loanAmount")));
			newLeadDto.setTenure(CommonUtils.toString(jsonObj.getString("loanTenure")));
			newLeadDto.setTypeOfBusiness(CommonUtils.toString(jsonObj.getString("typeOfBusiness")));
			newLeadDto.setCluster(CommonUtils.toString(jsonObj.getString("cluster")));			
			//}else{
			newLeadDto.setCompanyPanNo(jsonObj.getString("pan"));
			newLeadDto.setIndustryForNI(jsonObj.getString("industry"));
			//}
			JSONArray addressJsonArr=jsonObj.getJSONArray("addressDeatils"); 
			for(int i=0;i<addressJsonArr.length();i++){
				JSONObject addressJsonObj=addressJsonArr.getJSONObject(i);
				AddressEntity addressEntity=new AddressEntity();
				addressEntity.setAddressId(addressJsonObj.getString("addressId"));
				addressEntity.setPersonalDtlId(addressJsonObj.getString("personalDtlId"));
				addressEntity.setCaseId(addressJsonObj.getString("caseId"));
				addressEntity.setAddressType(addressJsonObj.getString("addressType"));
				addressEntity.setAddress(addressJsonObj.getString("address"));
				addressEntity.setFlatNo(addressJsonObj.getString("flatNo"));
				addressEntity.setFloorNo(addressJsonObj.getString("floorNo"));
				addressEntity.setBuildingName(addressJsonObj.getString("buildingName"));
				addressEntity.setLocality(addressJsonObj.getString("locality"));
				addressEntity.setLandmark(addressJsonObj.getString("landmark"));
				addressEntity.setCity(addressJsonObj.getString("city"));
				addressEntity.setState(addressJsonObj.getString("state"));
				addressEntity.setZipcode(addressJsonObj.getString("zipcode"));
				addressEntity.setStdIsd(addressJsonObj.getString("stdIsd"));
				addressEntity.setPhone1(addressJsonObj.getString("phone1"));
				addressEntity.setExt1(addressJsonObj.getString("ext1"));
				addressEntity.setPhone2(addressJsonObj.getString("phone2"));
				addressEntity.setExt2(addressJsonObj.getString("ext2"));
				addressEntity.setFax(addressJsonObj.getString("fax"));
				
				
				addressEntity.setMailingAddress(addressJsonObj.getString("mailingAddress"));
				addressEntity.setCompany_name(addressJsonObj.getString("company_name"));
				
				addressEntity.setEmail_Id(addressJsonObj.getString("emailid_id"));
				addressEntity.setEmail(addressJsonObj.getString("email_id"));
				addressEntity.setMobile1_Id1(addressJsonObj.getString("mobile_no1_id"));
				addressEntity.setMobile1_Id2(addressJsonObj.getString("mobile_no2_id"));
				addressEntity.setMobile_no1(addressJsonObj.getString("mobile_no1"));
				addressEntity.setMobile_no2(addressJsonObj.getString("mobile_no2"));
			    addressEntity.setBussinessestbyr(addressJsonObj.getString("bussinessestbyr"));  			    
			    addressEntity.setDestinationAddress(addressJsonObj.getString("destinationAddress"));
			    
			    
				addressEntity.setOccupancyMm(addressJsonObj.getString("occupancyMm"));
				addressEntity.setOccupancyYr(addressJsonObj.getString("occupancyYr"));
				addressEntity.setOccupancyStatus(addressJsonObj.getString("occupancyStatus"));
				addressEntity.setSameas(addressJsonObj.getString("sameas"));
				addressEntity.setActive(addressJsonObj.getString("active"));
				addressEntity.setCreatedBy(addressJsonObj.getString("createdBy"));
				addressEntity.setCreatedDate(getDateFromString(addressJsonObj.getString("createdDate")));
				addressEntity.setCreatedSysDate(getDateFromString(addressJsonObj.getString("createdSysDate")));
				addressEntity.setUpdatedBy(addressJsonObj.getString("updatedBy"));
				addressEntity.setUpdatedDate(getDateFromString(addressJsonObj.getString("updatedDate")));
				addressEntity.setUpdatedSysDate(getDateFromString(addressJsonObj.getString("updatedSysDate")));
				addressEntity.setCityName(addressJsonObj.getString("cityName"));
				
				
			    
			    
				listAddress.add(addressEntity);
			}
			newLeadDto.setListAddress(listAddress);
			/*JSONArray keyContactsJsonArray=jsonObj.getJSONArray("keyContactsDetails"); 
			for(int i=0;i<keyContactsJsonArray.length();i++){
				JSONObject keyContactsJsonObj=keyContactsJsonArray.getJSONObject(i);
				KeyContactsEntity keyContactsEntity=new KeyContactsEntity();
				keyContactsEntity.setKeyContactId(keyContactsJsonObj.getString("keyContactId"));
				keyContactsEntity.setPersonalDtlId(keyContactsJsonObj.getString("personalDtlId"));
				keyContactsEntity.setCaseId(keyContactsJsonObj.getString("caseId"));
				keyContactsEntity.setContactTypeId(keyContactsJsonObj.getString("contactTypeId"));
				keyContactsEntity.setFname(keyContactsJsonObj.getString("fname"));
				keyContactsEntity.setMname(keyContactsJsonObj.getString("mname"));
				keyContactsEntity.setLname(keyContactsJsonObj.getString("lname"));
				keyContactsEntity.setFirmName(keyContactsJsonObj.getString("firmName"));
				keyContactsEntity.setAddress(keyContactsJsonObj.getString("address"));
				keyContactsEntity.setFlatNo(keyContactsJsonObj.getString("flatNo"));
				keyContactsEntity.setFloorNo(keyContactsJsonObj.getString("floorNo"));
				keyContactsEntity.setBuildingName(keyContactsJsonObj.getString("buildingName"));
				keyContactsEntity.setLocality(keyContactsJsonObj.getString("locality"));
				keyContactsEntity.setLandmark(keyContactsJsonObj.getString("landmark"));
				keyContactsEntity.setCity(keyContactsJsonObj.getString("city"));
				keyContactsEntity.setState(keyContactsJsonObj.getString("state"));
				keyContactsEntity.setZipcode(keyContactsJsonObj.getString("zipcode"));
				keyContactsEntity.setStdIsd(keyContactsJsonObj.getString("stdIsd"));
				keyContactsEntity.setPhone1(keyContactsJsonObj.getString("phone1"));
				keyContactsEntity.setExt1(keyContactsJsonObj.getString("ext1"));
				keyContactsEntity.setPhone2(keyContactsJsonObj.getString("phone2"));
				keyContactsEntity.setExt2(keyContactsJsonObj.getString("ext2"));
				keyContactsEntity.setMobile(keyContactsJsonObj.getString("mobile"));
				keyContactsEntity.setEmail(keyContactsJsonObj.getString("email"));
				keyContactsEntity.setCreatedBy(keyContactsJsonObj.getString("createdBy"));
				keyContactsEntity.setCreatedDate(getDateFromString(keyContactsJsonObj.getString("createdDate")));
				keyContactsEntity.setCreatedSysDate(getDateFromString(keyContactsJsonObj.getString("createdSysDate")));
				keyContactsEntity.setUpdatedBy(keyContactsJsonObj.getString("updatedBy"));
				keyContactsEntity.setUpdatedDate(getDateFromString(keyContactsJsonObj.getString("updatedDate")));
				keyContactsEntity.setUpdatedSysDate(getDateFromString(keyContactsJsonObj.getString("updatedSysDate")));
				listKeyContact.add(keyContactsEntity);
			}*/
			newLeadDto.setListKeyContact(listKeyContact);
			JSONArray mobileJsonArray=jsonObj.getJSONArray("mobilesDetails"); 
			for(int i=0;i<mobileJsonArray.length();i++){
				JSONObject mobileJsonObj=mobileJsonArray.getJSONObject(i);
				MobileEntity mobileEntity=new MobileEntity();
				mobileEntity.setCaseMobileId(mobileJsonObj.getString("caseMobileId"));
				mobileEntity.setCaseId(mobileJsonObj.getString("caseId"));
				//mobileEntity.setMobileContactTypeId(mobileJsonObj.getString("mobileContactTypeId"));
				mobileEntity.setMobileContactTypeId("1");
				mobileEntity.setContactNo(mobileJsonObj.getString("contactNo"));
				//mobileEntity.setPrimaryContact(mobileJsonObj.getString("primaryContact"));
				mobileEntity.setPrimaryContact("Y");
				mobileEntity.setDndFlag(mobileJsonObj.getString("dndFlag"));
				mobileEntity.setCreatedBy(mobileJsonObj.getString("createdBy"));
				mobileEntity.setCreatedDate(getDateFromString(mobileJsonObj.getString("createdDate")));
				mobileEntity.setCreatedSysDate(getDateFromString(mobileJsonObj.getString("createdSysDate")));
				mobileEntity.setUpdatedBy(mobileJsonObj.getString("updatedBy"));
				mobileEntity.setUpdatedDate(getDateFromString(mobileJsonObj.getString("updatedDate")));
				mobileEntity.setUpdatedSysDate(getDateFromString(mobileJsonObj.getString("updatedSysDate"))); 
				listMobile.add(mobileEntity);
			}
			
			newLeadDto.setListMobile(listMobile);
			JSONArray emailJsonArray=jsonObj.getJSONArray("emailDetails"); 
			for(int i=0;i<emailJsonArray.length();i++){
				JSONObject emailJsonObj=emailJsonArray.getJSONObject(i);
				EmailEntity emailEntity=new EmailEntity();
				emailEntity.setCaseEmailId(emailJsonObj.getString("caseEmailId"));
				emailEntity.setCaseId(emailJsonObj.getString("caseId"));
				//emailEntity.setEmailContactTypeId(emailJsonObj.getString("emailContactTypeId"));
				emailEntity.setEmailContactTypeId("5");
				emailEntity.setEmail(emailJsonObj.getString("email"));
				//emailEntity.setPrimaryEmail(emailJsonObj.getString("primaryEmail"));
				emailEntity.setPrimaryEmail("Y");
				emailEntity.setCreatedBy(emailJsonObj.getString("createdBy"));
				emailEntity.setCreatedDate(getDateFromString(emailJsonObj.getString("createdDate")));
				emailEntity.setCreatedSysDate(getDateFromString(emailJsonObj.getString("createdSysDate")));
				emailEntity.setUpdatedBy(emailJsonObj.getString("updatedBy"));
				emailEntity.setUpdatedDate(getDateFromString(emailJsonObj.getString("updatedDate")));
				emailEntity.setUpdatedSysDate(getDateFromString(emailJsonObj.getString("updatedSysDate"))); 
				listEmail.add(emailEntity);
			}
			newLeadDto.setListEmail(listEmail);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newLeadDto;
	}
	public Date getDateFromString(String dateStr){
		Date date=null;
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MMM-yyyy");
		if(dateStr!=null && !dateStr.equalsIgnoreCase("")){
			try {			
				date = sdf.parse(dateStr);			       
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return date;
	}
}
