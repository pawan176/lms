package com.qc.starter.mobileAction;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.qc.starter.dto.CustomerDto;
import com.qc.starter.entity.AddressEntity;
import com.qc.starter.entity.EmailEntity;
import com.qc.starter.entity.MobileEntity;


public class CustomerJsonParser {
	public CustomerDto getJsonToCustomerDto(JSONObject jsonObject){
		CustomerDto customerDto=null;
		try {
			customerDto=new CustomerDto();
			List<AddressEntity> listAddress=new ArrayList<AddressEntity>();
			List<MobileEntity> listMobile=new ArrayList<MobileEntity>();
			List<EmailEntity> listEmail=new ArrayList<EmailEntity>();
			//if(jsonObject.getString("customerUpdateFlag").equalsIgnoreCase("Y")){
				//String[] customerName=(jsonObject.getString("customerName")+"").split(" ");
				//if(customerName.length==3){
				customerDto.setfName(jsonObject.getString("customerFName"));
				customerDto.setmName(jsonObject.getString("customerMName"));
				customerDto.setlName(jsonObject.getString("customerLName"));
				//}else if(customerName.length==2){
				//		customerDto.setfName(customerName[0]);
				//		customerDto.setmName(customerName[1]);
				//	}else{
				//		customerDto.setfName(customerName[0]);
				//	}
				customerDto.setCaseId(jsonObject.getString("caseId")); 
				customerDto.setUserId(jsonObject.getString("userId"));
				customerDto.setPersonalDetailId(jsonObject.getString("personalDetailId"));
				customerDto.setGender(jsonObject.getString("gender"));
				customerDto.setMaritalStatus(jsonObject.getString("maritalStatus"));
				customerDto.setDob(getDateFromString(jsonObject.getString("dob")));			
				customerDto.setCustEntityTypeId(jsonObject.getString("custEntityTypeId"));
				customerDto.setAuthSignatoryFName(jsonObject.getString("authSignatoryFName"));
				customerDto.setAuthSignatoryMName(jsonObject.getString("authSignatoryMName"));
				customerDto.setAuthSignatoryLName(jsonObject.getString("authSignatoryLName"));
				customerDto.setIndustryId(jsonObject.getString("industryId"));			
				customerDto.setTypeOfBusiness(jsonObject.getString("typeOfBusiness"));
				customerDto.setCluster(jsonObject.getString("cluster"));
				customerDto.setTitle(jsonObject.getString("title"));
				customerDto.setCustCategory(jsonObject.getString("custCategory"));
				customerDto.setAllocateTo(jsonObject.getString("allocateTo"));
				//-------------------------------------------------------
				
			customerDto.setNationality(jsonObject.getString("nationality"));
			customerDto.setNoOfDependents(jsonObject.getString("noOfDependents"));
			customerDto.setPan(jsonObject.getString("pan"));
			customerDto.setAdhaarNumber(jsonObject.getString("adhaarNumber"));
			customerDto.setVotersId(jsonObject.getString("votersId"));
			customerDto.setDrivingLicenseNo(jsonObject.getString("drivingLicenseNo"));
			customerDto.setPassportNo(jsonObject.getString("passportNo"));
			customerDto.setOccupationType(jsonObject.getString("occupationType"));
			customerDto.setCompanyType(jsonObject.getString("companyType"));
			customerDto.setCompanyName(jsonObject.getString("companyName"));
			customerDto.setDesig(jsonObject.getString("desig"));
			customerDto.setSalaryMode(jsonObject.getString("salaryMode"));
			customerDto.setYearsCurrentJob(jsonObject.getString("yearsCurrentJob"));
			customerDto.setWorkExp(jsonObject.getString("workExp"));
			customerDto.setAnnualIncome(jsonObject.getString("annualIncome"));
			customerDto.setGrossMonthlyIncome(jsonObject.getString("grossMonthlyIncome"));
			customerDto.setNetMonthlyIncome(jsonObject.getString("netMonthlyIncome"));
			customerDto.setOtherMonthlyIncome(jsonObject.getString("otherMonthlyIncome"));
			customerDto.setMonthlyRentalIncome(jsonObject.getString("monthlyRentalIncome"));
			customerDto.setAnnualSalesTurnover(jsonObject.getString("annualSalesTurnover"));
			customerDto.setGrossProfit(jsonObject.getString("grossProfit"));
			customerDto.setNetProfitAfterTax(jsonObject.getString("netProfitAfterTax"));
			customerDto.setAnnualRentalIncome(jsonObject.getString("annualRentalIncome"));
			customerDto.setOtherAnnualIncome(jsonObject.getString("otherAnnualIncome"));
			customerDto.setIncorporationDate(getDateFromString(jsonObject.getString("incorporationDate")));
			customerDto.setNetWorth(jsonObject.getString("netWorth"));
			customerDto.setCorporateSalaryAccount(jsonObject.getString("corporateSalaryAccount"));
			customerDto.setOtherCompanyName(jsonObject.getString("otherCompanyName"));
			customerDto.setDirectorSalary(jsonObject.getString("directorSalary"));
			customerDto.setDepreciation(jsonObject.getString("depreciation"));
			customerDto.setBonusIncentive(jsonObject.getString("bonusIncentive"));
			customerDto.setConstitution(jsonObject.getString("constitution"));
			customerDto.setInteresrPaidOnLoan(jsonObject.getString("interesrPaidOnLoan"));
				//-----------------------------------------------------------
			//}
			JSONArray addressJsonArr=jsonObject.getJSONArray("addressDeatils"); 
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
				addressEntity.setCompany_name(addressJsonObj.getString("company_name"));
				addressEntity.setMobile_no1(addressJsonObj.getString("mobile_no1"));
				addressEntity.setMobile_no2(addressJsonObj.getString("mobile_no2"));
				addressEntity.setLocality(addressJsonObj.getString("locality"));
				addressEntity.setDestinationAddress(addressJsonObj.getString("destinationAddress"));
				addressEntity.setEmail(addressJsonObj.getString("email_id"));
				addressEntity.setBussinessestbyr(addressJsonObj.getString("bussinessestbyr"));
				
				
				listAddress.add(addressEntity);
			}
			customerDto.setListAddress(listAddress);
			//JSONArray mobileJsonArray=jsonObject.getJSONArray("mobilesDetails"); 
			//for(int i=0;i<mobileJsonArray.length();i++){
			//JSONObject mobileJsonObj=mobileJsonArray.getJSONObject(i);
			MobileEntity mobileEntity=new MobileEntity();
			mobileEntity.setCaseMobileId(jsonObject.getString("caseMobileId"));
			//mobileEntity.setCaseId(jsonObject.getString("caseId"));
			mobileEntity.setMobileContactTypeId(jsonObject.getString("mobileContactTypeId")); //1
			mobileEntity.setContactNo(jsonObject.getString("contactNo"));
			mobileEntity.setPrimaryContact("Y");
			//mobileEntity.setDndFlag(mobileJsonObj.getString("dndFlag"));
			//mobileEntity.setCreatedBy(mobileJsonObj.getString("createdBy"));
			//mobileEntity.setCreatedDate(getDateFromString(mobileJsonObj.getString("createdDate")));
			//mobileEntity.setCreatedSysDate(getDateFromString(mobileJsonObj.getString("createdSysDate")));
			mobileEntity.setUpdatedBy(jsonObject.getString("userId"));
			mobileEntity.setUpdatedDate(new java.sql.Date(new Date().getTime()));
			//mobileEntity.setUpdatedSysDate(getDateFromString(mobileJsonObj.getString("updatedSysDate"))); 
			listMobile.add(mobileEntity);
			//}
			customerDto.setListMobile(listMobile);
			//	JSONArray emailJsonArray=jsonObject.getJSONArray("emailDetails"); 
			//	for(int i=0;i<emailJsonArray.length();i++){
			//	JSONObject emailJsonObj=emailJsonArray.getJSONObject(i);
			EmailEntity emailEntity=new EmailEntity();
			emailEntity.setCaseEmailId(jsonObject.getString("caseEmailId"));
			//emailEntity.setCaseId(jsonObject.getString("caseId"));
			emailEntity.setEmailContactTypeId(jsonObject.getString("emailContactTypeId")); //5
			emailEntity.setEmail(jsonObject.getString("email"));
			emailEntity.setPrimaryEmail("Y");
			//emailEntity.setCreatedBy(emailJsonObj.getString("createdBy"));
			//	emailEntity.setCreatedDate(getDateFromString(emailJsonObj.getString("createdDate")));
			//	emailEntity.setCreatedSysDate(getDateFromString(emailJsonObj.getString("createdSysDate")));
			emailEntity.setUpdatedBy(jsonObject.getString("userId"));
			emailEntity.setUpdatedDate(new java.sql.Date(new Date().getTime()));
			//	emailEntity.setUpdatedSysDate(getDateFromString(emailJsonObj.getString("updatedSysDate"))); 
			listEmail.add(emailEntity);
			//	}
			customerDto.setListEmail(listEmail);
		}catch(Exception e){
			e.printStackTrace();
		}
		return customerDto;
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
	
	
	
	
	{
		//list<MobileEntity> listMobile; 
		
		/*//Loop{
			MobileEntity  mobile = null;
			
			{
				mobile.setMobileNumber(addressEntity.getMobile);
				
				if(addressEntity.getAddressType().equ"1000000002"){
					mobile.setPrimaryContact("Y");
				}
				listMobile.add(mobile);
			}
		//} // till length of addressentity*/
		
	}
	
	
}
