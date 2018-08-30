package com.qc.starter.dto;

import java.util.Date;
import java.util.List;

import com.qc.starter.entity.AddressEntity;
import com.qc.starter.entity.CustomerEntity;
import com.qc.starter.entity.EmailEntity;
import com.qc.starter.entity.KeyContactsEntity;
import com.qc.starter.entity.MobileEntity;
import com.qc.starter.entity.PropertyEntity;

public class CustomerDto {

	private String caseId;

	private String personalDetailId;

	private List<MobileEntity> listMobile;
	private List<EmailEntity> listEmail;

	private String tagA;
	private String tagB;
	private String fName;
	private String mName;
	private String lName;
	private String maritalStatus;
	private String title;
	private String gender;
	private Date dob;
	private String nationality;
	private String noOfDependents;
	private String pan;
	private String adhaarNumber;
	private String votersId;
	private String drivingLicenseNo;
	private String passportNo;
	private String occupationType;
	private String companyType;
	private String companyName;
	private String desig;
	private String salaryMode;
	private String yearsCurrentJob;
	private String workExp;
	private String annualIncome;
	private String grossMonthlyIncome;
	private String netMonthlyIncome;
	private String otherMonthlyIncome;
	private String monthlyRentalIncome;
	private String annualSalesTurnover;
	private String grossProfit;
	private String netProfitAfterTax;
	private String annualRentalIncome;
	private String otherAnnualIncome;
	private Date incorporationDate;
	private String netWorth;
	private String corporateSalaryAccount;
	private String otherCompanyName;
	// ---------------added by Deepak on 01 march 2016
	private String directorSalary;
	private String depreciation;
	private String bonusIncentive;
	private String constitution;
	private String interesrPaidOnLoan;
	// ---------------added by tripti
	private String custEntityTypeId;
	private String authSignatoryFName;
	private String authSignatoryMName;
	private String authSignatoryLName;
	private String industryId;

	private String typeOfBusiness;
	private String typeOfBusinessForNI;
	private String cluster;
	private String clusterForNI;

	private String custCategory;
	private String industryForNI;
	private String companyPanNo;
	private String userId;
	private String customerUpdateFlag;
	// ------Added by Anuj on 30-dec-2016
	private String sectorId;
	private String stageId;
	private String sectorForNI;
	private String stageForNI;
	private String result;
	private String source;
	private String referenceName;
	private String referenceNumber;
	private String allocateTo;
	
	public String getAllocateTo() {
		return allocateTo;
	}

	public void setAllocateTo(String allocateTo) {
		this.allocateTo = allocateTo;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
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

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public String getSectorId() {
		return sectorId;
	}

	public void setSectorId(String sectorId) {
		this.sectorId = sectorId;
	}

	public String getStageId() {
		return stageId;
	}

	public void setStageId(String stageId) {
		this.stageId = stageId;
	}

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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCustomerUpdateFlag() {
		return customerUpdateFlag;
	}

	public void setCustomerUpdateFlag(String customerUpdateFlag) {
		this.customerUpdateFlag = customerUpdateFlag;
	}

	public String getCompanyPanNo() {
		return companyPanNo;
	}

	public void setCompanyPanNo(String companyPanNo) {
		this.companyPanNo = companyPanNo;
	}

	public String getCustCategory() {
		return custCategory;
	}

	public void setCustCategory(String custCategory) {
		this.custCategory = custCategory;
	}

	public String getCustEntityTypeId() {
		return custEntityTypeId;
	}

	public void setCustEntityTypeId(String custEntityTypeId) {
		this.custEntityTypeId = custEntityTypeId;
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

	public String getIndustryId() {
		return industryId;
	}

	public void setIndustryId(String industryId) {
		this.industryId = industryId;
	}

	private List<AddressEntity> listAddress;

	private List<KeyContactsEntity> keyContacts;

	private List<PropertyEntity> listProperty;

	public String getInteresrPaidOnLoan() {
		return interesrPaidOnLoan;
	}

	public void setInteresrPaidOnLoan(String interesrPaidOnLoan) {
		this.interesrPaidOnLoan = interesrPaidOnLoan;
	}

	public String getConstitution() {
		return constitution;
	}

	public void setConstitution(String constitution) {
		this.constitution = constitution;
	}

	public String getDirectorSalary() {
		return directorSalary;
	}

	public void setDirectorSalary(String directorSalary) {
		this.directorSalary = directorSalary;
	}

	public String getDepreciation() {
		return depreciation;
	}

	public void setDepreciation(String depreciation) {
		this.depreciation = depreciation;
	}

	public String getBonusIncentive() {
		return bonusIncentive;
	}

	public void setBonusIncentive(String bonusIncentive) {
		this.bonusIncentive = bonusIncentive;
	}

	/**
	 * @return the caseId
	 */
	public String getCaseId() {
		return caseId;
	}

	/**
	 * @param caseId
	 *            the caseId to set
	 */
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	public String getOtherCompanyName() {
		return otherCompanyName;
	}

	public void setOtherCompanyName(String otherCompanyName) {
		this.otherCompanyName = otherCompanyName;
	}

	/**
	 * @return the personalDetailId
	 */
	public String getPersonalDetailId() {
		return personalDetailId;
	}

	/**
	 * @param personalDetailId
	 *            the personalDetailId to set
	 */
	public void setPersonalDetailId(String personalDetailId) {
		this.personalDetailId = personalDetailId;
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

	/**
	 * @return the tagA
	 */
	public String getTagA() {
		return tagA;
	}

	/**
	 * @param tagA
	 *            the tagA to set
	 */
	public void setTagA(String tagA) {
		this.tagA = tagA;
	}

	/**
	 * @return the tagB
	 */
	public String getTagB() {
		return tagB;
	}

	/**
	 * @param tagB
	 *            the tagB to set
	 */
	public void setTagB(String tagB) {
		this.tagB = tagB;
	}

	/**
	 * @return the fName
	 */
	public String getfName() {
		return fName;
	}

	/**
	 * @param fName
	 *            the fName to set
	 */
	public void setfName(String fName) {
		this.fName = fName;
	}

	/**
	 * @return the mName
	 */
	public String getmName() {
		return mName;
	}

	/**
	 * @param mName
	 *            the mName to set
	 */
	public void setmName(String mName) {
		this.mName = mName;
	}

	/**
	 * @return the lName
	 */
	public String getlName() {
		return lName;
	}

	/**
	 * @param lName
	 *            the lName to set
	 */
	public void setlName(String lName) {
		this.lName = lName;
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
	 * @return the dob
	 */
	public Date getDob() {
		return dob;
	}

	/**
	 * @param dob
	 *            the dob to set
	 */
	public void setDob(Date dob) {
		this.dob = dob;
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
	 * @return the votersId
	 */
	public String getVotersId() {
		return votersId;
	}

	/**
	 * @param votersId
	 *            the votersId to set
	 */
	public void setVotersId(String votersId) {
		this.votersId = votersId;
	}

	/**
	 * @return the drivingLicenseNo
	 */
	public String getDrivingLicenseNo() {
		return drivingLicenseNo;
	}

	/**
	 * @param drivingLicenseNo
	 *            the drivingLicenseNo to set
	 */
	public void setDrivingLicenseNo(String drivingLicenseNo) {
		this.drivingLicenseNo = drivingLicenseNo;
	}

	/**
	 * @return the passportNo
	 */
	public String getPassportNo() {
		return passportNo;
	}

	/**
	 * @param passportNo
	 *            the passportNo to set
	 */
	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
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

	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @param companyName
	 *            the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * @return the desig
	 */
	public String getDesig() {
		return desig;
	}

	/**
	 * @param desig
	 *            the desig to set
	 */
	public void setDesig(String desig) {
		this.desig = desig;
	}

	/**
	 * @return the salaryMode
	 */
	public String getSalaryMode() {
		return salaryMode;
	}

	/**
	 * @param salaryMode
	 *            the salaryMode to set
	 */
	public void setSalaryMode(String salaryMode) {
		this.salaryMode = salaryMode;
	}

	/**
	 * @return the yearsCurrentJob
	 */
	public String getYearsCurrentJob() {
		return yearsCurrentJob;
	}

	/**
	 * @param yearsCurrentJob
	 *            the yearsCurrentJob to set
	 */
	public void setYearsCurrentJob(String yearsCurrentJob) {
		this.yearsCurrentJob = yearsCurrentJob;
	}

	/**
	 * @return the workExp
	 */
	public String getWorkExp() {
		return workExp;
	}

	/**
	 * @param workExp
	 *            the workExp to set
	 */
	public void setWorkExp(String workExp) {
		this.workExp = workExp;
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
	 * @return the annualSalesTurnover
	 */
	public String getAnnualSalesTurnover() {
		return annualSalesTurnover;
	}

	/**
	 * @param annualSalesTurnover
	 *            the annualSalesTurnover to set
	 */
	public void setAnnualSalesTurnover(String annualSalesTurnover) {
		this.annualSalesTurnover = annualSalesTurnover;
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
	 * @return the netProfitAfterTax
	 */
	public String getNetProfitAfterTax() {
		return netProfitAfterTax;
	}

	/**
	 * @param netProfitAfterTax
	 *            the netProfitAfterTax to set
	 */
	public void setNetProfitAfterTax(String netProfitAfterTax) {
		this.netProfitAfterTax = netProfitAfterTax;
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
	 * @return the incorporationDate
	 */
	public Date getIncorporationDate() {
		return incorporationDate;
	}

	/**
	 * @param incorporationDate
	 *            the incorporationDate to set
	 */
	public void setIncorporationDate(Date incorporationDate) {
		this.incorporationDate = incorporationDate;
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
	 * @return the corporateSalaryAccount
	 */
	public String getCorporateSalaryAccount() {
		return corporateSalaryAccount;
	}

	/**
	 * @param corporateSalaryAccount
	 *            the corporateSalaryAccount to set
	 */
	public void setCorporateSalaryAccount(String corporateSalaryAccount) {
		this.corporateSalaryAccount = corporateSalaryAccount;
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
	 * @return the keyContacts
	 */
	public List<KeyContactsEntity> getKeyContacts() {
		return keyContacts;
	}

	/**
	 * @param keyContacts
	 *            the keyContacts to set
	 */
	public void setKeyContacts(List<KeyContactsEntity> keyContacts) {
		this.keyContacts = keyContacts;
	}

	/**
	 * @return the listProperty
	 */
	public List<PropertyEntity> getListProperty() {
		return listProperty;
	}

	/**
	 * @param listProperty
	 *            the listProperty to set
	 */
	public void setListProperty(List<PropertyEntity> listProperty) {
		this.listProperty = listProperty;
	}

	public void setCustomerValues(CustomerEntity customerEntity) {

		// this.setCustomerName(customerEntity.getfName()+" " +
		// customerEntity.getlName());
		this.setPersonalDetailId(customerEntity.getPersonalDetailId());
		this.setfName(customerEntity.getfName() != null ? customerEntity.getfName() : "");
		this.setmName(customerEntity.getmName() != null ? customerEntity.getmName() : "");
		this.setlName(customerEntity.getlName() != null ? customerEntity.getlName() : "");
		this.setMaritalStatus(customerEntity.getMaritalStatus() != null ? customerEntity.getMaritalStatus() : "");
		this.setDob(customerEntity.getDob() != null ? customerEntity.getDob() : null);		
		this.setTitle(customerEntity.getTitle() != null ? customerEntity.getTitle() : null);		
		this.setGender(customerEntity.getGender() != null ? customerEntity.getGender() : "");
		this.setNationality(customerEntity.getNationality() != null ? customerEntity.getNationality() : "");
		this.setNoOfDependents(customerEntity.getNoOfDependents() != null ? customerEntity.getNoOfDependents() : "");
		this.setPan(customerEntity.getPan() != null ? customerEntity.getPan() : "");
		this.setAdhaarNumber(customerEntity.getAdhaarNumber() != null ? customerEntity.getAdhaarNumber() : "");
		this.setOccupationType(customerEntity.getOccupationType() != null ? customerEntity.getOccupationType() : "");
		this.setCompanyType(customerEntity.getCompanyType() != null ? customerEntity.getCompanyType() : "");
		this.setCompanyName(customerEntity.getCompanyId() != null ? customerEntity.getCompanyId() : "");
		this.setDesig(customerEntity.getDesig() != null ? customerEntity.getDesig() : "");
		this.setSalaryMode(customerEntity.getSalaryMode() != null ? customerEntity.getSalaryMode() : "");
		this.setYearsCurrentJob(customerEntity.getYearsCurrentJob() != null ? customerEntity.getYearsCurrentJob() : "");
		this.setWorkExp(customerEntity.getWorkExp() != null ? customerEntity.getWorkExp() : "");
		this.setAnnualIncome(customerEntity.getAnnualIncome() != null ? customerEntity.getAnnualIncome() : "");
		this.setGrossMonthlyIncome(
				customerEntity.getGrossMonthlyInc() != null ? customerEntity.getGrossMonthlyInc() : "");
		this.setNetMonthlyIncome(customerEntity.getNetMonthlyInc() != null ? customerEntity.getNetMonthlyInc() : "");
		this.setOtherMonthlyIncome(
				customerEntity.getOtherMonthlyInc() != null ? customerEntity.getOtherMonthlyInc() : "");
		this.setMonthlyRentalIncome(
				customerEntity.getMothlyRentalInc() != null ? customerEntity.getMothlyRentalInc() : "");
		this.setAnnualRentalIncome(
				customerEntity.getAnnualRentalInc() != null ? customerEntity.getAnnualRentalInc() : "");
		this.setAnnualSalesTurnover(customerEntity.getAnnualSales() != null ? customerEntity.getAnnualSales() : "");
		this.setGrossProfit(customerEntity.getGrossProfit() != null ? customerEntity.getGrossProfit() : "");
		this.setNetProfitAfterTax(
				customerEntity.getNetProfitAfterTax() != null ? customerEntity.getNetProfitAfterTax() : "");
		this.setOtherAnnualIncome(customerEntity.getOtherAnnualInc() != null ? customerEntity.getOtherAnnualInc() : "");
		this.setIncorporationDate(
				customerEntity.getIncorperationDate() != null ? customerEntity.getIncorperationDate() : null);
		this.setNetWorth(customerEntity.getNetWorth() != null ? customerEntity.getNetWorth() : "");
		this.setCorporateSalaryAccount(
				customerEntity.getCorporateSalAcc() != null ? customerEntity.getCorporateSalAcc() : "");
		if (customerEntity.getOtherCompanyName() == null) {
			this.setOtherCompanyName("");
		} else {
			this.setOtherCompanyName(customerEntity.getOtherCompanyName());
		}
		// -------------added by deepak on 01 march 2016
		this.setDirectorSalary(customerEntity.getDirectorSalary().trim());
		this.setDepreciation(customerEntity.getDepreciation().trim());
		this.setConstitution(customerEntity.getConstitution().trim());
		this.setBonusIncentive(customerEntity.getBonusIncentive().trim());
		// -------------added by deepak on 16 march 2016
		this.setInteresrPaidOnLoan(customerEntity.getInteresrPaidOnLoan().trim());
		// --------------------------------
		// this.setOtherCompanyName(customerEntity.getOtherCompanyName()!=null?customerEntity.getOtherCompanyName():"");
		// -----------------added by tripti 29sep
		this.setCustEntityTypeId(customerEntity.getCustEntityTypeId());
		this.setAuthSignatoryFName(customerEntity.getAuthSignatoryFName());
		this.setAuthSignatoryMName(customerEntity.getAuthSignatoryMName());
		this.setAuthSignatoryLName(customerEntity.getAuthSignatoryLName());
		this.setIndustryId(customerEntity.getIndustryId());
		this.setCustCategory(customerEntity.getCustCategory());
		this.setIndustryForNI(customerEntity.getIndustryForNI());
		this.setCompanyPanNo(customerEntity.getCompanyPanNo());
		// ----------Added by Anuj on 30dec2016
		this.setSectorId(customerEntity.getSectorId());
		this.setStageId(customerEntity.getStageId());
		this.setSectorForNI(customerEntity.getSectorForNI());
		this.setStageForNI(customerEntity.getStageForNI());
		this.setCluster(customerEntity.getClusterCase());
		this.setClusterForNI(customerEntity.getClusterForNI());
		this.setTypeOfBusiness(customerEntity.getTypeOfBusiness());
		this.setTypeOfBusinessForNI(customerEntity.getTypeOfBusinessForNI());
	}

	public String getIndustryForNI() {
		return industryForNI;
	}

	public void setIndustryForNI(String industryForNI) {
		this.industryForNI = industryForNI;
	}

}
