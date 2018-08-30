package com.qc.starter.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.qc.starter.entity.AddressEntity;
import com.qc.starter.entity.EmailEntity;
import com.qc.starter.entity.KeyContactsEntity;
import com.qc.starter.entity.MobileEntity;

public class NewLeadDto {

	/* personal detail */
	private String title;
	private String newleadType;
	private String firstName;
	private String lastName;
	private String middleName;
	private String maritalStatus;
	private String dob;
	private String gender;
	private String nationality;
	private String noOfDependents;
	private String pan;
	private String adhaarNumber;

	/* Occupation detail */
	private String occupationType;
	private String companyType;
	private String companyName;
	private String otherCompanyName;
	private String designation;
	private String modeOfSalary;
	private String yearOfCurrJob;
	private String workExperiance;
	private String annualIncome;
	private String grossMonthlyIncome;
	private String netMonthlyIncome;
	private String otherMonthlyIncome;
	private String monthlyRentalIncome;
	private String annualSalesTurnOver;
	private String grossProfit;
	private String netProfitAtTax;
	private String otherAnnualIncome;
	private String annualRentalIncome;
	private String dateOfIncorparation;
	private String netWorth;
	private String corpSalaryAcount;
	/*private MultipartFile customerPhoto;*/
	/* Address Entities */
	private List<AddressEntity> listAddress;
	/* Key Contacts Enitites */
	private List<KeyContactsEntity> listKeyContact;

	


	/* PRODUCT DETAIL */
	private String productId;
	private String queueId;
	private String source;
	private String campaign;
	private String allocateTo;
	private String userId;
	private String companyId;
	
	// Added by Sumit on 11-July-2017
	private String typeOfBusiness;
	private String typeOfBusinessForNI;
	private String cluster;
	private String clusterForNI;
	private String loanAmount;
	private String tenure;
	private String branch;
	private String scheme;
	private String purposeOfLoan;
	
	

	private List<MobileEntity> listMobile;
	private List<EmailEntity> listEmail;

	// --------Added by deepak on 03 march 2016-----------------------
	private String bonusIncentive;
	private String depreciation;
	private String directorSalary;
	// --------Added by deepak on 16 march 2016-----------------------
	private String interesrPaidOnLoan;
	// ----------Added by Tripti 29-Sep-16
	private String entityType;
	private String constitution;
	private String customerCategory;
	private String authSignatoryFName;
	private String authSignatoryMName;
	private String authSignatoryLName;
	private String industry;
	private String industryForNI;
	private String companyPanNo;
	// --------Added by Anuj on 30-dec-2016-----
	private String sector;
	private String stage;
	private String sectorForNI;
	private String stageForNI;
	// --------Added by Sumit on 30-Jan-2018----
	private String affordableEmi;
	private String referenceName;
	private String referenceNumber;
	private String appName;
	
	

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getReferenceName() {
		return referenceName;
	}

	public void setReferenceName(String referenceName) {
		this.referenceName = referenceName;
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public String getAffordableEmi() {
		return affordableEmi;
	}

	public void setAffordableEmi(String affordableEmi) {
		this.affordableEmi = affordableEmi;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getScheme() {
		return scheme;
	}

	public void setScheme(String scheme) {
		this.scheme = scheme;
	}

	public String getPurposeOfLoan() {
		return purposeOfLoan;
	}

	public void setPurposeOfLoan(String purposeOfLoan) {
		this.purposeOfLoan = purposeOfLoan;
	}

	public String getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(String loanAmount) {
		this.loanAmount = loanAmount;
	}

	public String getTenure() {
		return tenure;
	}

	public void setTenure(String tenure) {
		this.tenure = tenure;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	// --------Added by Sumit on 11-July-2017-----
	public String getTypeOfBusiness() {
		return typeOfBusiness;
	}

	public void setTypeOfBusiness(String typeOfBusiness) {
		this.typeOfBusiness = typeOfBusiness;
	}

	public String getTypeOfBusinessForNI() {
		return typeOfBusinessForNI;
	}

	public void setTypeOfBusinessForNI(String typeOfBusinessForNI) {
		this.typeOfBusinessForNI = typeOfBusinessForNI;
	}

	public String getCluster() {
		return cluster;
	}

	public void setCluster(String cluster) {
		this.cluster = cluster;
	}

	public String getClusterForNI() {
		return clusterForNI;
	}

	public void setClusterForNI(String clusterForNI) {
		this.clusterForNI = clusterForNI;
	}
	// --------End Added by Sumit on 11-July-2017-----

	public String getSectorForNI() {
		return sectorForNI;
	}

	public void setSectorForNI(String sectorForNI) {
		this.sectorForNI = sectorForNI;
	}

	public String getStageForNI() {
		return stageForNI;
	}

	public void setStageForNI(String stageForNI) {
		this.stageForNI = stageForNI;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public String getCompanyPanNo() {
		return companyPanNo;
	}

	public void setCompanyPanNo(String companyPanNo) {
		this.companyPanNo = companyPanNo;
	}

	public String getIndustryForNI() {
		return industryForNI;
	}

	public void setIndustryForNI(String industryForNI) {
		this.industryForNI = industryForNI;
	}

	public String getEntityType() {
		return entityType;
	}

	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	public String getConstitution() {
		return constitution;
	}

	public void setConstitution(String constitution) {
		this.constitution = constitution;
	}

	public String getCustomerCategory() {
		return customerCategory;
	}

	public void setCustomerCategory(String customerCategory) {
		this.customerCategory = customerCategory;
	}

	public String getAuthSignatoryFName() {
		return authSignatoryFName;
	}

	public void setAuthSignatoryFName(String authSignatoryFName) {
		this.authSignatoryFName = authSignatoryFName;
	}

	public String getAuthSignatoryMName() {
		return authSignatoryMName;
	}

	public void setAuthSignatoryMName(String authSignatoryMName) {
		this.authSignatoryMName = authSignatoryMName;
	}

	public String getAuthSignatoryLName() {
		return authSignatoryLName;
	}

	public void setAuthSignatoryLName(String authSignatoryLName) {
		this.authSignatoryLName = authSignatoryLName;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	// --------------------------------------------------------------
	public String getInteresrPaidOnLoan() {
		return interesrPaidOnLoan;
	}

	public void setInteresrPaidOnLoan(String interesrPaidOnLoan) {
		this.interesrPaidOnLoan = interesrPaidOnLoan;
	}

	public String getBonusIncentive() {
		return bonusIncentive;
	}

	public void setBonusIncentive(String bonusIncentive) {
		this.bonusIncentive = bonusIncentive;
	}

	public String getDepreciation() {
		return depreciation;
	}

	public void setDepreciation(String depreciation) {
		this.depreciation = depreciation;
	}

	public String getDirectorSalary() {
		return directorSalary;
	}

	public void setDirectorSalary(String directorSalary) {
		this.directorSalary = directorSalary;
	}

	/**
	 * @return the firstName
	 */

	public String getFirstName() {
		return firstName;
	}

	public String getNewleadType() {
		return newleadType;
	}

	public void setNewleadType(String newleadType) {
		this.newleadType = newleadType;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * @param middleName
	 *            the middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * @return the maritalStatus
	 */
	public String getMaritalStatus() {
		return maritalStatus;
	}

	/**
	 * @param maritalStatus
	 *            the maritalStatus to set
	 */
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	/**
	 * @return the dob
	 */
	public String getDob() {
		return dob;
	}

	/**
	 * @param dob
	 *            the dob to set
	 */
	public void setDob(String dob) {
		this.dob = dob;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender
	 *            the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the nationality
	 */
	public String getNationality() {
		return nationality;
	}

	/**
	 * @param nationality
	 *            the nationality to set
	 */
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	/**
	 * @return the noOfDependents
	 */
	public String getNoOfDependents() {
		return noOfDependents;
	}

	/**
	 * @param noOfDependents
	 *            the noOfDependents to set
	 */
	public void setNoOfDependents(String noOfDependents) {
		this.noOfDependents = noOfDependents;
	}

	/**
	 * @return the pan
	 */
	public String getPan() {
		return pan;
	}

	/**
	 * @param pan
	 *            the pan to set
	 */
	public void setPan(String pan) {
		this.pan = pan;
	}

	/**
	 * @return the adhaarNumber
	 */
	public String getAdhaarNumber() {
		return adhaarNumber;
	}

	/**
	 * @param adhaarNumber
	 *            the adhaarNumber to set
	 */
	public void setAdhaarNumber(String adhaarNumber) {
		this.adhaarNumber = adhaarNumber;
	}

	/**
	 * @return the occupationType
	 */
	public String getOccupationType() {
		return occupationType;
	}

	/**
	 * @param occupationType
	 *            the occupationType to set
	 */
	public void setOccupationType(String occupationType) {
		this.occupationType = occupationType;
	}

	/**
	 * @return the companyType
	 */
	public String getCompanyType() {
		return companyType;
	}

	/**
	 * @param companyType
	 *            the companyType to set
	 */
	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * @return the otherCompanyName
	 */
	public String getOtherCompanyName() {
		return otherCompanyName;
	}

	/**
	 * @param otherCompanyName
	 *            the otherCompanyName to set
	 */
	public void setOtherCompanyName(String otherCompanyName) {
		this.otherCompanyName = otherCompanyName;
	}

	/**
	 * @return the designation
	 */
	public String getDesignation() {
		return designation;
	}

	/**
	 * @param designation
	 *            the designation to set
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}

	/**
	 * @return the modeOfSalary
	 */
	public String getModeOfSalary() {
		return modeOfSalary;
	}

	/**
	 * @param modeOfSalary
	 *            the modeOfSalary to set
	 */
	public void setModeOfSalary(String modeOfSalary) {
		this.modeOfSalary = modeOfSalary;
	}

	/**
	 * @return the yearOfCurrJob
	 */
	public String getYearOfCurrJob() {
		return yearOfCurrJob;
	}

	/**
	 * @param yearOfCurrJob
	 *            the yearOfCurrJob to set
	 */
	public void setYearOfCurrJob(String yearOfCurrJob) {
		this.yearOfCurrJob = yearOfCurrJob;
	}

	/**
	 * @return the workExperiance
	 */
	public String getWorkExperiance() {
		return workExperiance;
	}

	/**
	 * @param workExperiance
	 *            the workExperiance to set
	 */
	public void setWorkExperiance(String workExperiance) {
		this.workExperiance = workExperiance;
	}

	/**
	 * @return the annualIncome
	 */
	public String getAnnualIncome() {
		return annualIncome;
	}

	/**
	 * @param annualIncome
	 *            the annualIncome to set
	 */
	public void setAnnualIncome(String annualIncome) {
		this.annualIncome = annualIncome;
	}

	/**
	 * @return the grossMonthlyIncome
	 */
	public String getGrossMonthlyIncome() {
		return grossMonthlyIncome;
	}

	/**
	 * @param grossMonthlyIncome
	 *            the grossMonthlyIncome to set
	 */
	public void setGrossMonthlyIncome(String grossMonthlyIncome) {
		this.grossMonthlyIncome = grossMonthlyIncome;
	}

	/**
	 * @return the netMonthlyIncome
	 */
	public String getNetMonthlyIncome() {
		return netMonthlyIncome;
	}

	/**
	 * @param netMonthlyIncome
	 *            the netMonthlyIncome to set
	 */
	public void setNetMonthlyIncome(String netMonthlyIncome) {
		this.netMonthlyIncome = netMonthlyIncome;
	}

	/**
	 * @return the otherMonthlyIncome
	 */
	public String getOtherMonthlyIncome() {
		return otherMonthlyIncome;
	}

	/**
	 * @param otherMonthlyIncome
	 *            the otherMonthlyIncome to set
	 */
	public void setOtherMonthlyIncome(String otherMonthlyIncome) {
		this.otherMonthlyIncome = otherMonthlyIncome;
	}

	/**
	 * @return the monthlyRentalIncome
	 */
	public String getMonthlyRentalIncome() {
		return monthlyRentalIncome;
	}

	/**
	 * @param monthlyRentalIncome
	 *            the monthlyRentalIncome to set
	 */
	public void setMonthlyRentalIncome(String monthlyRentalIncome) {
		this.monthlyRentalIncome = monthlyRentalIncome;
	}

	/**
	 * @return the annualSalesTurnOver
	 */
	public String getAnnualSalesTurnOver() {
		return annualSalesTurnOver;
	}

	/**
	 * @param annualSalesTurnOver
	 *            the annualSalesTurnOver to set
	 */
	public void setAnnualSalesTurnOver(String annualSalesTurnOver) {
		this.annualSalesTurnOver = annualSalesTurnOver;
	}

	/**
	 * @return the grossProfit
	 */
	public String getGrossProfit() {
		return grossProfit;
	}

	/**
	 * @param grossProfit
	 *            the grossProfit to set
	 */
	public void setGrossProfit(String grossProfit) {
		this.grossProfit = grossProfit;
	}

	/**
	 * @return the netProfitAtTax
	 */
	public String getNetProfitAtTax() {
		return netProfitAtTax;
	}

	/**
	 * @param netProfitAtTax
	 *            the netProfitAtTax to set
	 */
	public void setNetProfitAtTax(String netProfitAtTax) {
		this.netProfitAtTax = netProfitAtTax;
	}

	/**
	 * @return the otherAnnualIncome
	 */
	public String getOtherAnnualIncome() {
		return otherAnnualIncome;
	}

	/**
	 * @param otherAnnualIncome
	 *            the otherAnnualIncome to set
	 */
	public void setOtherAnnualIncome(String otherAnnualIncome) {
		this.otherAnnualIncome = otherAnnualIncome;
	}

	/**
	 * @return the annualRentalIncome
	 */
	public String getAnnualRentalIncome() {
		return annualRentalIncome;
	}

	/**
	 * @param annualRentalIncome
	 *            the annualRentalIncome to set
	 */
	public void setAnnualRentalIncome(String annualRentalIncome) {
		this.annualRentalIncome = annualRentalIncome;
	}

	/**
	 * @return the dateOfIncorparation
	 */
	public String getDateOfIncorparation() {
		return dateOfIncorparation;
	}

	/**
	 * @param dateOfIncorparation
	 *            the dateOfIncorparation to set
	 */
	public void setDateOfIncorparation(String dateOfIncorparation) {
		this.dateOfIncorparation = dateOfIncorparation;
	}

	/**
	 * @return the netWorth
	 */
	public String getNetWorth() {
		return netWorth;
	}

	/**
	 * @param netWorth
	 *            the netWorth to set
	 */
	public void setNetWorth(String netWorth) {
		this.netWorth = netWorth;
	}

	/**
	 * @return the corpSalaryAcount
	 */
	public String getCorpSalaryAcount() {
		return corpSalaryAcount;
	}

	/**
	 * @param corpSalaryAcount
	 *            the corpSalaryAcount to set
	 */
	public void setCorpSalaryAcount(String corpSalaryAcount) {
		this.corpSalaryAcount = corpSalaryAcount;
	}

	/**
	 * @return the listAddress
	 */
	public List<AddressEntity> getListAddress() {
		return listAddress;
	}

	/**
	 * @param listAddress
	 *            the listAddress to set
	 */
	public void setListAddress(List<AddressEntity> listAddress) {
		this.listAddress = listAddress;
	}

	/**
	 * @return the listKeyContact
	 */
	public List<KeyContactsEntity> getListKeyContact() {
		return listKeyContact;
	}

	/**
	 * @param listKeyContact
	 *            the listKeyContact to set
	 */
	public void setListKeyContact(List<KeyContactsEntity> listKeyContact) {
		this.listKeyContact = listKeyContact;
	}

	/**
	 * @return the productId
	 */
	public String getProductId() {
		return productId;
	}

	/**
	 * @param productId
	 *            the productId to set
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}

	/**
	 * @return the queueId
	 */
	public String getQueueId() {
		return queueId;
	}

	/**
	 * @param queueId
	 *            the queueId to set
	 */
	public void setQueueId(String queueId) {
		this.queueId = queueId;
	}

	/**
	 * @return the source
	 */
	public String getSource() {
		return source;
	}

	/**
	 * @param source
	 *            the source to set
	 */
	public void setSource(String source) {
		this.source = source;
	}

	/**
	 * @return the campaign
	 */
	public String getCampaign() {
		return campaign;
	}

	/**
	 * @param campaign
	 *            the campaign to set
	 */
	public void setCampaign(String campaign) {
		this.campaign = campaign;
	}

	/**
	 * @return the allocateTo
	 */
	public String getAllocateTo() {
		return allocateTo;
	}

	/**
	 * @param allocateTo
	 *            the allocateTo to set
	 */
	public void setAllocateTo(String allocateTo) {
		this.allocateTo = allocateTo;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the companyId
	 */
	public String getCompanyId() {
		return companyId;
	}

	/**
	 * @param companyId
	 *            the companyId to set
	 */
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	/**
	 * @return the listMobile
	 */
	public List<MobileEntity> getListMobile() {
		return listMobile;
	}

	/**
	 * @param listMobile
	 *            the listMobile to set
	 */
	public void setListMobile(List<MobileEntity> listMobile) {
		this.listMobile = listMobile;
	}

	/**
	 * @return the listEmail
	 */
	public List<EmailEntity> getListEmail() {
		return listEmail;
	}

	/**
	 * @param listEmail
	 *            the listEmail to set
	 */
	public void setListEmail(List<EmailEntity> listEmail) {
		this.listEmail = listEmail;
	}

}
