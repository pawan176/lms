package com.qc.starter.service.serviceImpl;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qc.starter.basic.CommonUtils;
import com.qc.starter.dao.CustomerDao;
import com.qc.starter.dao.NewLeadDao;
import com.qc.starter.dto.NewLeadDto;
import com.qc.starter.entity.AddressEntity;
import com.qc.starter.entity.EmailEntity;
import com.qc.starter.entity.KeyContactsEntity;
import com.qc.starter.entity.MobileEntity;
import com.qc.starter.service.NewLeadService;

@Service
public class NewLeadServiceImpl implements NewLeadService {

	private static final Logger logger = Logger.getLogger(NewLeadServiceImpl.class.getName());
	@Autowired
	NewLeadDao newLeadDao;
	@Autowired
	CustomerDao customerDao;
	@Autowired
	HttpSession httpSession;

	public String getLeadId() {
		return newLeadDao.getLeadId();
	}

	public String createLead(NewLeadDto newLeadDto) {
		// UserEntity userEntity = (UserEntity)
		// httpSession.getAttribute("UserDetails");

		if (newLeadDto.getEntityType() != null && newLeadDto.getEntityType().equalsIgnoreCase("1000000001")
				&& (!newLeadDto.getEntityType().equalsIgnoreCase(""))) {
			if (newLeadDto.getFirstName() == null || newLeadDto.getFirstName().equalsIgnoreCase("")) {
				return "#" + null + "# Parsing Error # First Name Can't be left Blank #";
			}
		}

		String param = "";

		String passer = "<OCCUPATION><SALUTATION>~<FNAME>~<MNAME>~<LNAME>~<MARITALSTATUS>~<DOB>~<GENDER>~<NATIONALITY>~<DEPENDENTS>~<PAN>~<ADHAAR>~"
				+ "<OCCUP_TYPE>~<TYPE_COMPANY>~<COMPANY_NAME>~<OTHER_COMP_NAME>~<DESIG>~<SALARY_MODE>~<YRS_CURR_JOB>~<WORK_EXP>~"
				+ "<ANNUAL_INCOME>~<GROSS_MONTHLY_INC>~<NET_MONTHLY_INC>~<OTHER_MONTHLY_INC>~<MONTH_RENTAL_INC>~<ANNUAL_SALES_TURN>~"
				+ "<GROSS_PROFIT>~<NET_PROFIT_AFTER_TAX>~<ANNUAL_RENTAL_INC>~<OTHER_ANNUAL_INC>~<DATE_OF_INCORP>~<NET_WORTH>~<CORP_SAL_ACC>~"
				+ "<MONTHALY_BONUS>~<DEPRECIANTION>~<DIRECTOR_SALARY>~<INTEREST_PAID_ON_LOAN>~" +
				// --------------- start Added by tripti 29-Sep
				"<ENTITYTYPE>~<CUSTOMERCATEGORY>~<AUTHSIGNFNAME>~<AUTHSIGNMNAME>~<AUTHSIGNLNAME>~<INDUSTRY>~<TYPEOFBUSINESS>~<CLUSTER>~<SECTORID>~<STAGEID><OCCUPATION>";

			//"<ENTITYTYPE>~<CUSTOMERCATEGORY>~<AUTHSIGNFNAME>~<AUTHSIGNMNAME>~<AUTHSIGNLNAME>~<INDUSTRY>~<SECTORID>~<STAGEID><OCCUPATION>";

		passer = passer.replaceAll("<ENTITYTYPE>", (newLeadDto.getEntityType() == null || newLeadDto.getEntityType().equals("-1")) ? "" : newLeadDto.getEntityType());
		
		passer = passer.replaceAll("<SALUTATION>",(newLeadDto.getTitle() == null || newLeadDto.getTitle().equals("-1")) ? "" : newLeadDto.getTitle());
		
		passer = passer.replaceAll("<CUSTOMERCATEGORY>", (newLeadDto.getCustomerCategory() == null || newLeadDto.getCustomerCategory().equals("-1")) ? "" : newLeadDto.getCustomerCategory());
		
		passer = passer.replaceAll("<AUTHSIGNFNAME>",
				newLeadDto.getAuthSignatoryFName() == null ? "" : newLeadDto.getAuthSignatoryFName());
		passer = passer.replaceAll("<AUTHSIGNMNAME>",
				newLeadDto.getAuthSignatoryMName() == null ? "" : newLeadDto.getAuthSignatoryMName());
		passer = passer.replaceAll("<AUTHSIGNLNAME>",
				newLeadDto.getAuthSignatoryLName() == null ? "" : newLeadDto.getAuthSignatoryLName());
		/* <!--TEMPORARY --> */
		// passer =
		// passer.replaceAll("<SECTORID>",newLeadDto.getSectorId()==null?
		// "":newLeadDto.getSectorId());
		// passer = passer.replaceAll("<STAGEID>",newLeadDto.getStageId()==null?
		// "":newLeadDto.getStageId());

		if (newLeadDto.getEntityType() != null && (!(newLeadDto.getEntityType().equalsIgnoreCase("")))) {
			if (newLeadDto.getEntityType().equals("1000000001")) {
				passer = passer.replaceAll("<OCCUP_TYPE>",
						(newLeadDto.getOccupationType() == null || newLeadDto.getOccupationType().equals("-1")) ? ""
								: newLeadDto.getOccupationType());
				passer = passer.replaceAll("<INDUSTRY>",
						(newLeadDto.getIndustry() == null || newLeadDto.getIndustry().equals("-1")) ? ""
								: newLeadDto.getIndustry());

				passer = passer.replaceAll("<TYPEOFBUSINESS>",
						(newLeadDto.getTypeOfBusiness() == null || newLeadDto.getTypeOfBusiness().equals("-1")) ? ""
								: newLeadDto.getTypeOfBusiness());
				passer = passer.replaceAll("<CLUSTER>",
						(newLeadDto.getCluster() == null || newLeadDto.getCluster().equals("-1")) ? ""
								: newLeadDto.getCluster());

				passer = passer.replaceAll("<PAN>", newLeadDto.getPan() == null ? "" : newLeadDto.getPan());
				passer = passer.replaceAll("<STAGEID>",
						(newLeadDto.getStage() == null || newLeadDto.getStage().equals("-1")) ? ""
								: newLeadDto.getStage());
				passer = passer.replaceAll("<SECTORID>",
						(newLeadDto.getSector() == null || newLeadDto.getSector().equals("-1")) ? ""
								: newLeadDto.getSector());

			} else {
				passer = passer.replaceAll("<OCCUP_TYPE>",
						(newLeadDto.getConstitution() == null || newLeadDto.getConstitution().equals("-1")) ? ""
								: newLeadDto.getConstitution());
				passer = passer.replaceAll("<INDUSTRY>",
						(newLeadDto.getIndustryForNI() == null || newLeadDto.getIndustryForNI().equals("-1")) ? ""
								: newLeadDto.getIndustry());

				passer = passer.replaceAll("<TYPEOFBUSINESS>",
						(newLeadDto.getTypeOfBusinessForNI() == null
								|| newLeadDto.getTypeOfBusinessForNI().equals("-1")) ? ""
										: newLeadDto.getTypeOfBusinessForNI());
				passer = passer.replaceAll("<CLUSTER>",
						(newLeadDto.getClusterForNI() == null || newLeadDto.getClusterForNI().equals("-1")) ? ""
								: newLeadDto.getClusterForNI());

				passer = passer.replaceAll("<PAN>",
						newLeadDto.getCompanyPanNo() == null ? "" : newLeadDto.getCompanyPanNo());
				passer = passer.replaceAll("<STAGEID>",
						(newLeadDto.getStageForNI() == null || newLeadDto.getStageForNI().equals("-1")) ? ""
								: newLeadDto.getStageForNI());
				passer = passer.replaceAll("<SECTORID>",
						(newLeadDto.getSectorForNI() == null || newLeadDto.getSectorForNI().equals("-1")) ? ""
								: newLeadDto.getSectorForNI());

			}
		} else {
			passer = passer.replaceAll("<OCCUP_TYPE>",(newLeadDto.getConstitution() == null || newLeadDto.getConstitution().equals("-1")) ? "": newLeadDto.getConstitution());
			passer = passer.replaceAll("<INDUSTRY>", "");
			passer = passer.replaceAll("<PAN>", newLeadDto.getPan() == null ? "" : newLeadDto.getPan());
			passer = passer.replaceAll("<STAGEID>", "");
			passer = passer.replaceAll("<SECTORID>", "");		
			passer = passer.replaceAll("<TYPEOFBUSINESS>", "");
			passer = passer.replaceAll("<CLUSTER>", "");		
		}
		if (newLeadDto.getEntityType() != null && (!(newLeadDto.getEntityType().equalsIgnoreCase("")))) {
			if (newLeadDto.getEntityType().equals("1000000001")) {
				passer = passer.replaceAll("<FNAME>",
						newLeadDto.getFirstName() == null ? "" : newLeadDto.getFirstName());
			} else {
				passer = passer.replaceAll("<FNAME>",
						newLeadDto.getCompanyName() == null ? "" : newLeadDto.getCompanyName());
			}
		} else {
			passer = passer.replaceAll("<FNAME>", newLeadDto.getFirstName() == null ? "" : newLeadDto.getFirstName());
		}
		// -------------- end Added by tripti 29-Sep

		passer = passer.replaceAll("<MNAME>", newLeadDto.getMiddleName() == null ? "" : newLeadDto.getMiddleName());
		passer = passer.replaceAll("<LNAME>", newLeadDto.getLastName() == null ? "" : newLeadDto.getLastName());

		passer = passer.replaceAll("<MARITALSTATUS>",
				(newLeadDto.getMaritalStatus() == null || newLeadDto.getMaritalStatus().equals("-1")) ? ""
						: newLeadDto.getMaritalStatus());
		passer = passer.replaceAll("<DOB>", newLeadDto.getDob() == null ? "" : newLeadDto.getDob());
		passer = passer.replaceAll("<GENDER>",
				(newLeadDto.getGender() == null || newLeadDto.getGender().equals("-1")) ? "" : newLeadDto.getGender());
		passer = passer.replaceAll("<NATIONALITY>",
				(newLeadDto.getNationality() == null || newLeadDto.getNationality().equals("-1")) ? ""
						: newLeadDto.getNationality());
		passer = passer.replaceAll("<DEPENDENTS>",
				newLeadDto.getNoOfDependents() == null || newLeadDto.getNoOfDependents().equals("-1") ? ""
						: newLeadDto.getNoOfDependents());

		passer = passer.replaceAll("<ADHAAR>",
				newLeadDto.getAdhaarNumber() == null ? "" : newLeadDto.getAdhaarNumber());
		passer = passer.replaceAll("<TYPE_COMPANY>",
				(newLeadDto.getCompanyType() == null || newLeadDto.getCompanyType().equals("-1")) ? ""
						: newLeadDto.getCompanyType());

		String company = newLeadDto.getCompanyName() == null ? "" : newLeadDto.getCompanyName();

		String CompanyId = customerDao.getCompanyIdByName(company);

		passer = passer.replaceAll("<COMPANY_NAME>", CompanyId);

		passer = passer.replaceAll("<OTHER_COMP_NAME>",
				newLeadDto.getCompanyName() == null ? "" : newLeadDto.getCompanyName());
		passer = passer.replaceAll("<DESIG>", newLeadDto.getDesignation() == null ? "" : newLeadDto.getDesignation());
		passer = passer.replaceAll("<SALARY_MODE>",
				(newLeadDto.getModeOfSalary() == null || newLeadDto.getModeOfSalary().equals("-1")) ? ""
						: newLeadDto.getModeOfSalary());
		passer = passer.replaceAll("<YRS_CURR_JOB>",
				(newLeadDto.getYearOfCurrJob() == null || newLeadDto.getYearOfCurrJob().equals("")) ? ""
						: newLeadDto.getYearOfCurrJob());
		passer = passer.replaceAll("<WORK_EXP>",
				(newLeadDto.getWorkExperiance() == null || newLeadDto.getWorkExperiance().equals("")) ? ""
						: newLeadDto.getWorkExperiance());
		passer = passer.replaceAll("<ANNUAL_INCOME>",
				(newLeadDto.getAnnualIncome() == null || newLeadDto.getAnnualIncome().equals("")) ? ""
						: newLeadDto.getAnnualIncome());
		passer = passer.replaceAll("<GROSS_MONTHLY_INC>",
				(newLeadDto.getGrossMonthlyIncome() == null || newLeadDto.getGrossMonthlyIncome().equals("")) ? ""
						: newLeadDto.getGrossMonthlyIncome());
		passer = passer.replaceAll("<NET_MONTHLY_INC>",
				(newLeadDto.getNetMonthlyIncome() == null || newLeadDto.getNetMonthlyIncome().equals("")) ? ""
						: newLeadDto.getNetMonthlyIncome());
		passer = passer.replaceAll("<OTHER_MONTHLY_INC>",
				(newLeadDto.getOtherMonthlyIncome() == null || newLeadDto.getOtherMonthlyIncome().equals("")) ? ""
						: newLeadDto.getOtherMonthlyIncome());
		passer = passer.replaceAll("<MONTH_RENTAL_INC>",
				(newLeadDto.getMonthlyRentalIncome() == null || newLeadDto.getMonthlyRentalIncome().equals("")) ? ""
						: newLeadDto.getMonthlyRentalIncome());
		passer = passer.replaceAll("<ANNUAL_SALES_TURN>",
				(newLeadDto.getAnnualSalesTurnOver() == null || newLeadDto.getAnnualSalesTurnOver().equals("")) ? ""
						: newLeadDto.getAnnualSalesTurnOver());
		passer = passer.replaceAll("<GROSS_PROFIT>",
				(newLeadDto.getGrossProfit() == null || newLeadDto.getGrossProfit().equals("")) ? ""
						: newLeadDto.getGrossProfit());
		passer = passer.replaceAll("<NET_PROFIT_AFTER_TAX>",
				(newLeadDto.getNetProfitAtTax() == null || newLeadDto.getNetProfitAtTax().equals("")) ? ""
						: newLeadDto.getNetProfitAtTax());
		passer = passer.replaceAll("<ANNUAL_RENTAL_INC>",
				(newLeadDto.getAnnualRentalIncome() == null || newLeadDto.getAnnualRentalIncome().equals("")) ? ""
						: newLeadDto.getAnnualRentalIncome());
		passer = passer.replaceAll("<OTHER_ANNUAL_INC>",
				(newLeadDto.getOtherAnnualIncome() == null || newLeadDto.getOtherAnnualIncome().equals("")) ? ""
						: newLeadDto.getOtherAnnualIncome());
		passer = passer.replaceAll("<DATE_OF_INCORP>",
				newLeadDto.getDateOfIncorparation() == null ? "" : newLeadDto.getDateOfIncorparation());
		passer = passer.replaceAll("<NET_WORTH>",
				(newLeadDto.getNetWorth() == null || newLeadDto.getNetWorth().equals("")) ? ""
						: newLeadDto.getNetWorth());
		passer = passer.replaceAll("<CORP_SAL_ACC>",
				(newLeadDto.getCorpSalaryAcount() == null || newLeadDto.getCorpSalaryAcount().equals("-1")) ? "N"
						: newLeadDto.getCorpSalaryAcount());
		// -----------Added by deepak on 03 march 2016----
		passer = passer.replaceAll("<MONTHALY_BONUS>",
				newLeadDto.getBonusIncentive() == null ? "" : newLeadDto.getBonusIncentive());
		passer = passer.replaceAll("<DEPRECIANTION>",
				newLeadDto.getDepreciation() == null ? "" : newLeadDto.getDepreciation());
		passer = passer.replaceAll("<DIRECTOR_SALARY>",
				newLeadDto.getDirectorSalary() == null ? "" : newLeadDto.getDirectorSalary());
		// -----------Added by deepak on 16 march 2016----
		passer = passer.replaceAll("<INTEREST_PAID_ON_LOAN>",
				newLeadDto.getInteresrPaidOnLoan() == null ? "" : newLeadDto.getInteresrPaidOnLoan());
		// -------------------------------
		if (newLeadDto.getListAddress() != null) {
			param = "<ADDRESS>";
			passer += param;
			for (int i = 0; i < newLeadDto.getListAddress().size(); i++) {
				AddressEntity add = newLeadDto.getListAddress().get(i);
			param = "<ADDRESS_TYPE>~<AddressValue>~<STATE>~<CITY>~<PIN>~<LANDMARK>~<LANDLINE>~<EXTENSION>~<LIVING_SINCE_YY>"
						+ "~<LIVING_SINCE_MM>~<OCCUPANCY_ST>~<MAILING_ADDRESS>~<Street_Name>~<Area_Name>~<COMPANY_NAME>~<LOCALITY>~<LANDLINE2>~<FAX>~<OLD_ADDRESS>~<GSTIN_NO>~<MarketValue>~<CURRENT_AREA_YY>~<BUSSINESS_ESTB_YY>~<DESTINATION_ADDRESS>~<MOBILE1>~<MOBILE2>~<EMAIL>";
				/*param = "<ADDRESS_TYPE>~<AddressValue>~<Street_Name>~<Area_Name>~<COMPANY_NAME>~<LOCALITY>~<STATE>~<CITY>~<PIN>~<LANDMARK>~<LANDLINE1>~<LANDLINE2>~<FAX>~<EXTENSION>~<LIVING_SINCE_YY>"
						+ "~<LIVING_SINCE_MM>~<CURRENT_AREA_YY>~<BUSSINESS_ESTB_YY>~<OCCUPANCY_ST>~<MAILING_ADDRESS>~<OLD_ADDRESS>~<GSTIN_NO>~<DISTRICT>";*/
				
				if (add != null) {
					if (add.getAddressType() == null) {
						param = param.replaceAll("<ADDRESS_TYPE>", "1000000001");
						param = param.replaceAll("<AddressValue>", add.getAddress() == null ? "" : add.getAddress());
						param = param.replaceAll("<STATE>", add.getState().equals("-1") ? "" : add.getState());
						param = param.replaceAll("<CITY>",
								(add.getCity() == null || add.getCity().equals("-1")) ? "" : add.getCity());
						param = param.replaceAll("<PIN>", add.getZipcode() == null ? "" : add.getZipcode());										
						param = param.replaceAll("<LANDMARK>", add.getLandmark() == null ? "" : add.getLandmark());						
						param = param.replaceAll("<LANDLINE>", add.getPhone1() == null ? "" : add.getPhone1());						
						param = param.replaceAll("<EXTENSION>", add.getExt1() == null ? "" : add.getExt1());
						param = param.replaceAll("<LIVING_SINCE_YY>",	add.getOccupancyYr() == null ? "" : add.getOccupancyYr());			
						param = param.replaceAll("<LIVING_SINCE_MM>",
								add.getOccupancyMm() == null ? "" : add.getOccupancyMm());
						param = param.replaceAll("<OCCUPANCY_ST>",
								add.getOccupancyStatus().equals("-1") ? "" : add.getOccupancyStatus());
						param = param.replaceAll("<MAILING_ADDRESS>", add.getMailingAddress() == null ? "N" : add.getMailingAddress());
						
						param = param.replaceAll("<Street_Name>", add.getFlatNo() == null ? "" : add.getFlatNo());
						param = param.replaceAll("<Area_Name>", add.getFloorNo() == null ? "" : add.getFloorNo());
						param = param.replaceAll("<COMPANY_NAME>", add.getCompany_name() == null ? "" : add.getCompany_name());
						param = param.replaceAll("<LOCALITY>", add.getLocality() == null ? "" : add.getLocality());
						param = param.replaceAll("<LANDLINE2>", add.getPhone2() == null ? "" : add.getPhone2());
						param = param.replaceAll("<FAX>", CommonUtils.toString(""));						
						param = param.replaceAll("<FAX>", add.getFax() == null ? "" : add.getFax());
						param = param.replaceAll("<OLD_ADDRESS>", add.getOldaddress() == null ? "" : add.getOldaddress());
						param = param.replaceAll("<GSTIN_NO>", add.getGstinno() == null ? "" : add.getGstinno());
						param = param.replaceAll("<MarketValue>", add.getMarketvalue() == null ? "" : add.getMarketvalue());
						param = param.replaceAll("<CURRENT_AREA_YY>",	add.getCurrentareaYr() == null ? "" : add.getCurrentareaYr());
						param = param.replaceAll("<BUSSINESS_ESTB_YY>",	add.getBussinessestbyr() == null ? "" : add.getBussinessestbyr());
						param = param.replaceAll("<DESTINATION_ADDRESS>",	add.getDestinationAddress() == null ? "" : add.getDestinationAddress());
						param = param.replaceAll("<MOBILE1>",	add.getMobile_no1() == null ? "" : add.getMobile_no1());
						param = param.replaceAll("<MOBILE2>",	add.getMobile_no2() == null ? "" : add.getMobile_no2());
						param = param.replaceAll("<EMAIL>",	add.getEmail()== null ? "" : add.getEmail());
						
						
						
					
						if (newLeadDto.getListAddress().size() > 1 && i < newLeadDto.getListAddress().size() - 1)
							param += "!";
						passer = passer + param;
					} else {
						param = param.replaceAll("<ADDRESS_TYPE>",
								(add.getAddressType() == null || add.getAddressType().equals("-1")) ? "1000000010": add.getAddressType());
						param = param.replaceAll("<AddressValue>", add.getAddress() == null ? "" : add.getAddress());					
						param = param.replaceAll("<STATE>",
								(add.getState() == null || add.getState().equals("-1")) ? "" : add.getState());
						param = param.replaceAll("<CITY>",
								(add.getCity() == null || add.getCity().equals("-1")) ? "" : add.getCity());
						param = param.replaceAll("<PIN>", add.getZipcode() == null ? "" : add.getZipcode());	
						param = param.replaceAll("<LANDMARK>", add.getLandmark() == null ? "" : add.getLandmark());	
						param = param.replaceAll("<LANDLINE>", add.getPhone1() == null ? "" : add.getPhone1());		
						param = param.replaceAll("<EXTENSION>", add.getExt1() == null ? "" : add.getExt1());
						param = param.replaceAll("<LIVING_SINCE_YY>",
								add.getOccupancyYr() == null ? "" : add.getOccupancyYr()).replace(",","");
						param = param.replaceAll("<LIVING_SINCE_MM>",
								add.getOccupancyMm() == null ? "" : add.getOccupancyMm());				
						param = param.replaceAll("<OCCUPANCY_ST>",
								add.getOccupancyStatus() == null ? "" : add.getOccupancyStatus());					
						param = param.replaceAll("<MAILING_ADDRESS>", add.getMailingAddress() == null ? "N" : add.getMailingAddress()).replace("on","Y");
						param = param.replaceAll("<Street_Name>", add.getFlatNo() == null ? "" : add.getFlatNo());
						param = param.replaceAll("<Area_Name>", add.getFloorNo() == null ? "" : add.getFloorNo());
						param = param.replaceAll("<COMPANY_NAME>", add.getCompany_name() == null ? "" : add.getCompany_name());
						param = param.replaceAll("<LOCALITY>", add.getLocality() == null ? "" : add.getLocality());
						param = param.replaceAll("<LANDLINE2>", add.getPhone2() == null ? "" : add.getPhone2());
						param = param.replaceAll("<FAX>", add.getFax() == null ? "" : add.getFax());
						param = param.replaceAll("<OLD_ADDRESS>", add.getOldaddress() == null ? "" : add.getOldaddress());
						param = param.replaceAll("<GSTIN_NO>", add.getGstinno() == null ? "" : add.getGstinno());
						param = param.replaceAll("<MarketValue>", add.getMarketvalue() == null ? "" : add.getMarketvalue());
						param = param.replaceAll("<CURRENT_AREA_YY>",	add.getCurrentareaYr() == null ? "" : add.getCurrentareaYr());
						param = param.replaceAll("<BUSSINESS_ESTB_YY>",	add.getBussinessestbyr() == null ? "" : add.getBussinessestbyr()).replace("!","");;
						param = param.replaceAll("<DESTINATION_ADDRESS>",	add.getDestinationAddress() == null ? "" : add.getDestinationAddress());
						param = param.replaceAll("<EXTENSION2>",	add.getExt2() == null ? "" : add.getExt2());
						param = param.replaceAll("<MOBILE1>",	add.getMobile_no1() == null ? "" : add.getMobile_no1());
						param = param.replaceAll("<MOBILE2>",	add.getMobile_no2() == null ? "" : add.getMobile_no2());   
						param = param.replaceAll("<EMAIL>",	add.getEmail()== null ? "" : add.getEmail());
						
						if (newLeadDto.getListAddress().size() > 1 && i < newLeadDto.getListAddress().size() - 1)
							param += "!";
						passer = passer + param;
					}
				}
			}
			param = "<ADDRESS>";
			passer += param;

		}else{
			param = "<ADDRESS>1000000002~~~~~~~~~~~Y~~~~~~~~~~~~~"+newLeadDto.getListMobile().get(0).getContactNo()+"~~!1000000001~~~~~~~~~~~N~~~~~~~~~~~~~~~!1000000003~~~~~~~~~~~N~~~~~~~~~~~~~~~<ADDRESS>";

			passer += param;
		}
		if (newLeadDto.getListKeyContact() != null) {
			param = "<KEY>";
			passer += param;
			for (int i = 0; i < newLeadDto.getListKeyContact().size(); i++) {
				KeyContactsEntity key = newLeadDto.getListKeyContact().get(i);
				param = "<CONTACT_TYPE>~<NAME>~<FIRM_NAME>~<MOBILE>~<EMAIL_ID>~<KeyADDRESS>";
				if (key.getContactTypeId() != null && !key.getContactTypeId().equals("-1")) {
					param = param.replaceAll("<CONTACT_TYPE>", key.getContactTypeId());
					param = param.replaceAll("<NAME>", key.getFname());
					param = param.replaceAll("<FIRM_NAME>", key.getFirmName());
					param = param.replaceAll("<MOBILE>", key.getMobile());
					param = param.replaceAll("<EMAIL_ID>", key.getEmail());
					param = param.replaceAll("<KeyADDRESS>", key.getAddress());
					if (newLeadDto.getListKeyContact().size() > 1 && i < newLeadDto.getListKeyContact().size() - 1)
						param += "!";
					passer = passer + param;
				}
			}
			param = "<KEY>";
			passer += param;
		}

		if (newLeadDto.getListMobile() != null) {
			param = "<MOBILE>";
			passer += param;
			for (int i = 0; i < newLeadDto.getListMobile().size(); i++) {
				MobileEntity mobile = newLeadDto.getListMobile().get(i);
				param = "<MOB_TYPE>~<MOB_NO>~<SET_DEFALT>";
				if (mobile.getMobileContactTypeId() != null && !mobile.getMobileContactTypeId().equals("-1")) {
					param = param.replaceAll("<MOB_TYPE>", mobile.getMobileContactTypeId());
					param = param.replaceAll("<MOB_NO>", mobile.getContactNo());
					
					if(newLeadDto.getListMobile().size()==1){
						param = param.replaceAll("<SET_DEFALT>",mobile.getPrimaryContact() == null ? "Y" : mobile.getPrimaryContact());
					}else{
						param = param.replaceAll("<SET_DEFALT>",mobile.getPrimaryContact() == null ? "N" : mobile.getPrimaryContact());
					}
					
					
					if (newLeadDto.getListMobile().size() > 1 && i < newLeadDto.getListMobile().size() - 1)
						param += "!";
					passer = passer + param;
				}
			}
			param = "<MOBILE>";
			passer += param;
		}
		if (newLeadDto.getListEmail() != null) {
			param = "<EMAIL>";
			passer += param;
			for (int i = 0; i < newLeadDto.getListEmail().size(); i++) {
				EmailEntity email = newLeadDto.getListEmail().get(i);
				param = "<EMAIL_TYPE>~<EMAILID>~<SET_DEFALT>";
				if (email.getEmailContactTypeId() != null && !email.getEmailContactTypeId().equals("-1")) {
					param = param.replaceAll("<EMAIL_TYPE>", email.getEmailContactTypeId());
					param = param.replaceAll("<EMAILID>", email.getEmail());
					param = param.replaceAll("<SET_DEFALT>",
							email.getPrimaryEmail() == null ? "N" : email.getPrimaryEmail());
					if (newLeadDto.getListEmail().size() > 1 && i < newLeadDto.getListEmail().size() - 1)
						param += "!";
					passer = passer + param;
				}
			}
			param = "<EMAIL>";
			passer += param;
		}
		param = "<CASE><PRODUCT>~<SUB_QUEUE>~<SOURCE>~<CAMPAIGN>~<ALLOCATE_TO>~<USERID>~<COMPANYID>~<BRANCH>~<PURPOSEOFLOAN>~<SCHEME>~<LOANAMOUNT>~<LOANTENURE>~<MAXEMI>~<REFERENCE_NAME>~<REFERENCE_NUMBER>~<APP_NAME><CASE>";

		//param = "<CASE><PRODUCT>~<SUB_QUEUE>~<SOURCE>~<CAMPAIGN>~<ALLOCATE_TO>~<USERID>~<COMPANYID><CASE>";

		param = param.replaceAll("<PRODUCT>", CommonUtils.toString(newLeadDto.getProductId()).equals("-1") ? "" : newLeadDto.getProductId());
/*
		param = param.replaceAll("<SUB_QUEUE>", ( CommonUtils.toString(newLeadDto.getQueueId()).equals("-1") || CommonUtils.toString(newLeadDto.getQueueId()).equals("") ) ? "" : newLeadDto.getQueueId());
=======*/
		param = param.replaceAll("<SUB_QUEUE>", ( CommonUtils.toString(newLeadDto.getQueueId()).equals("") ||  CommonUtils.toString(newLeadDto.getQueueId()).equals("-1")) ? "" : newLeadDto.getQueueId() );

		param = param.replaceAll("<SOURCE>", CommonUtils.toString(newLeadDto.getSource()).equals("-1") ? "" : newLeadDto.getSource());
		param = param.replaceAll("<CAMPAIGN>", CommonUtils.toString(newLeadDto.getCampaign()).equals("-1") ? "" : newLeadDto.getCampaign());
		param = param.replaceAll("<ALLOCATE_TO>",CommonUtils.toString(newLeadDto.getAllocateTo()).equals("-1") ? "" : newLeadDto.getAllocateTo());
		param = param.replaceAll("<USERID>", newLeadDto.getUserId() == null ? "" : newLeadDto.getUserId().toString());
		param = param.replaceAll("<COMPANYID>", newLeadDto.getCompanyId());
		
		param = param.replaceAll("<BRANCH>", ( CommonUtils.toString(newLeadDto.getBranch()).equals("") || CommonUtils.toString(newLeadDto.getBranch()).equals("-1")) ? "" : newLeadDto.getBranch());
		param = param.replaceAll("<PURPOSEOFLOAN>",( CommonUtils.toString(newLeadDto.getPurposeOfLoan()).equals("") ||  CommonUtils.toString(newLeadDto.getPurposeOfLoan()).equals("-1")) ? "" : newLeadDto.getPurposeOfLoan());
		param = param.replaceAll("<SCHEME>",( CommonUtils.toString(newLeadDto.getScheme()).equals("") ||  CommonUtils.toString(newLeadDto.getScheme()).equals("-1")) ? "" : newLeadDto.getScheme());
		param = param.replaceAll("<LOANAMOUNT>", CommonUtils.toString(newLeadDto.getLoanAmount()));
		param = param.replaceAll("<LOANTENURE>", CommonUtils.toString(newLeadDto.getTenure()));
		param = param.replaceAll("<MAXEMI>", CommonUtils.toString(newLeadDto.getAffordableEmi()));

		param = param.replaceAll("<REFERENCE_NAME>", CommonUtils.toString(newLeadDto.getReferenceName()));
		param = param.replaceAll("<REFERENCE_NUMBER>", CommonUtils.toString(newLeadDto.getReferenceNumber()));
		param = param.replaceAll("<APP_NAME>", CommonUtils.toString(newLeadDto.getAppName()));
		
		
		passer += param;
		
		passer=passer.replace(",", "");
		// System.out.println("passer :-"+passer);
		logger.info("NewLeadController | createLead() | :- END param " + passer);
		return newLeadDao.createLead(passer);
	}
	

}
