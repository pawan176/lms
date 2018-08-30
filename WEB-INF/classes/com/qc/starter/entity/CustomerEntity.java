package com.qc.starter.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "QT_PERSONAL_DETAILS", schema="QC_PROSPECT" )
public class CustomerEntity {
	
	@Id
	@Column(name="PERSONAL_DTL_ID")
	private	String	personalDetailId;
	@Column(name="CASE_ID")
	private	String caseId;
	private	String title;
	private	String fName;
	private	String mName;
	private	String lName;
	private	Date dob;
	@Column(name="MARITAL_STATUS")
	private	String	maritalStatus;
	private	String	gender;
	private	String	nationality;
	private	String	noOfDependents;
	private	String	pan;
	@Column(name="ADHAAR_NO")
	private	String	adhaarNumber;
	private	String	votersId;
	private	String	drivingLicenseNo;
	private	String	passportNo;
	@Column(name="OCCUPATION_TYPE")
	private	String	occupationType;
	private	String	fatherTitle;
	private	String	fatherFName;
	private	String	fatherMName;
	private	String	fatherLName;
	private	String	motherTitle;
	private	String	motherFName;
	private	String	motherMName;
	private	String	motherLName;
	private	String	spouseTitle;
	private	String	spouseFName;
	private	String	spouseMName;
	private	String	spouseLName;
	private	String	spouseOccupation;
	private	String	spouseCompanyId;
	private	String	constitution;
	private	String	custCategory;
	private	String	eduQualification;
	private	String	highestQualification;
	private	String	degree;
	private	String	collegeLastAttended;
	@Column(name="COMPANY_TYPE")
	private	String	companyType;
	@Column(name="CASE_COMPANY_ID")
	private	String	companyId;
	private	String	desig;
	@Column(name="SALARY_MODE")
	private	String	salaryMode;
	@Column(name="YEARS_CURRENT_JOB")
	private	String	yearsCurrentJob;
	@Column(name="WORK_EXP")
	private	String	workExp;
	private	String	basic;
	private	String	da;
	private	String	hra;	
	private	String	spAllowances;
	@Column(name="MEDICAL_LTA")
	private	String	medicalLta;
	@Column(name="BONUS_INCENTIVE")
	private	String	bonusIncentive;
	private	String	others;
	@Column(name="ANNUAL_INCOME	")
	private	String	annualIncome;
	@Column(name="GROSS_MONTHLY_INC	")
	private	String	grossMonthlyInc;
	@Column(name="GROSS_ANNUAL_INC")
	private	String	grossAnnualInc;
	@Column(name="NET_MONTHLY_INC")
	private	String	netMonthlyInc;
	@Column(name="OTHER_MONTHLY_INC")
	private	String	otherMonthlyInc;
	@Column(name="OTHER_ANNUAL_INC")
	private	String	otherAnnualInc;
	@Column(name="MONTHLY_RENTAL_INC")
	private	String	mothlyRentalInc;
	@Column(name="ANNUAL_SALES")
	private	String	annualSales;
	@Column(name="GROSS_PROFIT")
	private	String	grossProfit;
	@Column(name="NET_PROFIT_AFTR_TAX")
	private	String	netProfitAfterTax;
	@Column(name="ANNUAL_RENTAL_INC")
	private	String	annualRentalInc;
	@Column(name="INCORPERATION_DT")
	private	Date incorperationDate;
	@Column(name="NET_WORTH")
	private	String	netWorth;
	@Column(name="CORPERATE_SAL_ACC")
	private	String	corporateSalAcc;
	@Column(name="CREATED_BY")
	private	String	createdBy;
	@Column(name="CREATED_DATE")
	private	Date	createdDate;
	@Column(name="CREATED_SYS_DATE")
	private	Date	createdSystemDate;
	@Column(name="UPDATED_BY")
	private	String	updatedBy;
	@Column(name="UPDATED_DATE")
	private	Date updatedDate;
	@Column(name="UPDATED_SYS_DATE")
	private	Date updatedSystemDate;

	@Column(name="OTHER_COMPANY_NAME")	
	private String otherCompanyName;
//----Added by Deepak on 01 march 2016---------------------------------------------
	@Column(name="DIRECTOR_SALARY")
	private String directorSalary;
	@Column(name="Depreciation")
	private String depreciation;
	//----Added by Deepak on 16 march 2016---------------------------------------------
	@Column(name="INT_PAID_ON_LOAN")
	private String interesrPaidOnLoan;
//---------------added by tripti
	private String custEntityTypeId;
	private String authSignatoryFName;
	private String authSignatoryMName;
	private String authSignatoryLName;
	private String industryId;
	private String typeOfBusiness;
	private String clusterCase;
	
	@Transient
	private String industryForNI;
	@Transient
	private String typeOfBusinessForNI;
	@Transient
	private String clusterForNI;
	
	
	@Transient String companyPanNo;
	//------------Added by Deepak on 20 Oct 2016----------------------
		@Transient
		private String caseEmailId;
		@Transient
		private String emailId;
		@Transient
		private String caseMobileId;
		@Transient
		private String mobileNo;
	//Added by Anuj-----
		@Column(name="SECTOR_ID")
		private String sectorId;
		@Column(name="STAGE_ID")
		private String stageId;
		@Transient
		private String sectorForNI;
		@Transient
		private String stageForNI;
		
		
		
		
	public String getTypeOfBusinessForNI() {
			return typeOfBusinessForNI;
		}

		public void setTypeOfBusinessForNI(String typeOfBusinessForNI) {
			this.typeOfBusinessForNI = typeOfBusinessForNI;
		}

		public String getClusterForNI() {
			return clusterForNI;
		}

		public void setClusterForNI(String clusterForNI) {
			this.clusterForNI = clusterForNI;
		}

	public String getTypeOfBusiness() {
			return typeOfBusiness;
		}

		public void setTypeOfBusiness(String typeOfBusiness) {
			this.typeOfBusiness = typeOfBusiness;
		}

		public String getClusterCase() {
			return clusterCase;
		}

		public void setClusterCase(String clusterCase) {
			this.clusterCase = clusterCase;
		}

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

	public String getCaseEmailId() {
			return caseEmailId;
		}

		public void setCaseEmailId(String caseEmailId) {
			this.caseEmailId = caseEmailId;
		}

		public String getEmailId() {
			return emailId;
		}

		public void setEmailId(String emailId) {
			this.emailId = emailId;
		}

		public String getCaseMobileId() {
			return caseMobileId;
		}

		public void setCaseMobileId(String caseMobileId) {
			this.caseMobileId = caseMobileId;
		}

		public String getMobileNo() {
			return mobileNo;
		}

		public void setMobileNo(String mobileNo) {
			this.mobileNo = mobileNo;
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

	public String getPersonalDetailId() {
		return personalDetailId;
	}

	public String getInteresrPaidOnLoan() {
		return interesrPaidOnLoan;
	}

	public void setInteresrPaidOnLoan(String interesrPaidOnLoan) {
		this.interesrPaidOnLoan = interesrPaidOnLoan;
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
	public void setPersonalDetailId(String personalDetailId) {
		this.personalDetailId = personalDetailId;
	}

	public String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getNoOfDependents() {
		return noOfDependents;
	}

	public void setNoOfDependents(String noOfDependents) {
		this.noOfDependents = noOfDependents;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getAdhaarNumber() {
		return adhaarNumber;
	}

	public void setAdhaarNumber(String adhaarNumber) {
		this.adhaarNumber = adhaarNumber;
	}

	public String getVotersId() {
		return votersId;
	}

	public void setVotersId(String votersId) {
		this.votersId = votersId;
	}

	public String getDrivingLicenseNo() {
		return drivingLicenseNo;
	}

	public void setDrivingLicenseNo(String drivingLicenseNo) {
		this.drivingLicenseNo = drivingLicenseNo;
	}

	public String getPassportNo() {
		return passportNo;
	}

	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
	}

	public String getOccupationType() {
		return occupationType;
	}

	public void setOccupationType(String occupationType) {
		this.occupationType = occupationType;
	}

	public String getFatherTitle() {
		return fatherTitle;
	}

	public void setFatherTitle(String fatherTitle) {
		this.fatherTitle = fatherTitle;
	}

	public String getFatherFName() {
		return fatherFName;
	}

	public void setFatherFName(String fatherFName) {
		this.fatherFName = fatherFName;
	}

	public String getFatherMName() {
		return fatherMName;
	}

	public void setFatherMName(String fatherMName) {
		this.fatherMName = fatherMName;
	}

	public String getFatherLName() {
		return fatherLName;
	}

	public void setFatherLName(String fatherLName) {
		this.fatherLName = fatherLName;
	}

	public String getMotherTitle() {
		return motherTitle;
	}

	public void setMotherTitle(String motherTitle) {
		this.motherTitle = motherTitle;
	}

	public String getMotherFName() {
		return motherFName;
	}

	public void setMotherFName(String motherFName) {
		this.motherFName = motherFName;
	}

	public String getMotherMName() {
		return motherMName;
	}

	public void setMotherMName(String motherMName) {
		this.motherMName = motherMName;
	}

	public String getMotherLName() {
		return motherLName;
	}

	public void setMotherLName(String motherLName) {
		this.motherLName = motherLName;
	}

	public String getSpouseTitle() {
		return spouseTitle;
	}

	public void setSpouseTitle(String spouseTitle) {
		this.spouseTitle = spouseTitle;
	}

	public String getSpouseFName() {
		return spouseFName;
	}

	public void setSpouseFName(String spouseFName) {
		this.spouseFName = spouseFName;
	}

	public String getSpouseMName() {
		return spouseMName;
	}

	public void setSpouseMName(String spouseMName) {
		this.spouseMName = spouseMName;
	}

	public String getSpouseLName() {
		return spouseLName;
	}

	public void setSpouseLName(String spouseLName) {
		this.spouseLName = spouseLName;
	}

	public String getSpouseOccupation() {
		return spouseOccupation;
	}

	public void setSpouseOccupation(String spouseOccupation) {
		this.spouseOccupation = spouseOccupation;
	}

	public String getSpouseCompanyId() {
		return spouseCompanyId;
	}

	public void setSpouseCompanyId(String spouseCompanyId) {
		this.spouseCompanyId = spouseCompanyId;
	}

	public String getConstitution() {
		return constitution;
	}

	public void setConstitution(String constitution) {
		this.constitution = constitution;
	}

	public String getCustCategory() {
		return custCategory;
	}

	public void setCustCategory(String custCategory) {
		this.custCategory = custCategory;
	}

	public String getEduQualification() {
		return eduQualification;
	}

	public void setEduQualification(String eduQualification) {
		this.eduQualification = eduQualification;
	}

	public String getHighestQualification() {
		return highestQualification;
	}

	public void setHighestQualification(String highestQualification) {
		this.highestQualification = highestQualification;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getCollegeLastAttended() {
		return collegeLastAttended;
	}

	public void setCollegeLastAttended(String collegeLastAttended) {
		this.collegeLastAttended = collegeLastAttended;
	}

	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getDesig() {
		return desig;
	}

	public void setDesig(String desig) {
		this.desig = desig;
	}

	public String getSalaryMode() {
		return salaryMode;
	}

	public void setSalaryMode(String salaryMode) {
		this.salaryMode = salaryMode;
	}

	public String getYearsCurrentJob() {
		return yearsCurrentJob;
	}

	public void setYearsCurrentJob(String yearsCurrentJob) {
		this.yearsCurrentJob = yearsCurrentJob;
	}

	public String getWorkExp() {
		return workExp;
	}

	public void setWorkExp(String workExp) {
		this.workExp = workExp;
	}

	public String getBasic() {
		return basic;
	}

	public void setBasic(String basic) {
		this.basic = basic;
	}

	public String getDa() {
		return da;
	}

	public void setDa(String da) {
		this.da = da;
	}

	public String getHra() {
		return hra;
	}

	public void setHra(String hra) {
		this.hra = hra;
	}

	public String getSpAllowances() {
		return spAllowances;
	}

	public void setSpAllowances(String spAllowances) {
		this.spAllowances = spAllowances;
	}

	public String getMedicalLta() {
		return medicalLta;
	}

	public void setMedicalLta(String medicalLta) {
		this.medicalLta = medicalLta;
	}

	public String getBonusIncentive() {
		return bonusIncentive;
	}

	public void setBonusIncentive(String bonusIncentive) {
		this.bonusIncentive = bonusIncentive;
	}

	public String getOthers() {
		return others;
	}

	public void setOthers(String others) {
		this.others = others;
	}

	public String getAnnualIncome() {
		return annualIncome;
	}

	public void setAnnualIncome(String annualIncome) {
		this.annualIncome = annualIncome;
	}

	public String getGrossMonthlyInc() {
		return grossMonthlyInc;
	}

	public void setGrossMonthlyInc(String grossMonthlyInc) {
		this.grossMonthlyInc = grossMonthlyInc;
	}

	public String getGrossAnnualInc() {
		return grossAnnualInc;
	}

	public void setGrossAnnualInc(String grossAnnualInc) {
		this.grossAnnualInc = grossAnnualInc;
	}

	public String getNetMonthlyInc() {
		return netMonthlyInc;
	}

	public void setNetMonthlyInc(String netMonthlyInc) {
		this.netMonthlyInc = netMonthlyInc;
	}

	public String getOtherMonthlyInc() {
		return otherMonthlyInc;
	}

	public void setOtherMonthlyInc(String otherMonthlyInc) {
		this.otherMonthlyInc = otherMonthlyInc;
	}

	public String getOtherAnnualInc() {
		return otherAnnualInc;
	}

	public void setOtherAnnualInc(String otherAnnualInc) {
		this.otherAnnualInc = otherAnnualInc;
	}

	public String getMothlyRentalInc() {
		return mothlyRentalInc;
	}

	public void setMothlyRentalInc(String mothlyRentalInc) {
		this.mothlyRentalInc = mothlyRentalInc;
	}

	public String getAnnualSales() {
		return annualSales;
	}

	public void setAnnualSales(String annualSales) {
		this.annualSales = annualSales;
	}

	public String getGrossProfit() {
		return grossProfit;
	}

	public void setGrossProfit(String grossProfit) {
		this.grossProfit = grossProfit;
	}

	public String getNetProfitAfterTax() {
		return netProfitAfterTax;
	}

	public void setNetProfitAfterTax(String netProfitAfterTax) {
		this.netProfitAfterTax = netProfitAfterTax;
	}

	public String getAnnualRentalInc() {
		return annualRentalInc;
	}

	public void setAnnualRentalInc(String annualRentalInc) {
		this.annualRentalInc = annualRentalInc;
	}

	public Date getIncorperationDate() {
		return incorperationDate;
	}

	public void setIncorperationDate(Date incorperationDate) {
		this.incorperationDate = incorperationDate;
	}

	public String getNetWorth() {
		return netWorth;
	}

	public void setNetWorth(String netWorth) {
		this.netWorth = netWorth;
	}

	public String getCorporateSalAcc() {
		return corporateSalAcc;
	}

	public void setCorporateSalAcc(String corporateSalAcc) {
		this.corporateSalAcc = corporateSalAcc;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getCreatedSystemDate() {
		return createdSystemDate;
	}

	public void setCreatedSystemDate(Date createdSystemDate) {
		this.createdSystemDate = createdSystemDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Date getUpdatedSystemDate() {
		return updatedSystemDate;
	}

	public void setUpdatedSystemDate(Date updatedSystemDate) {
		this.updatedSystemDate = updatedSystemDate;
	}

	public String getOtherCompanyName() {
		return otherCompanyName;
	}

	public void setOtherCompanyName(String otherCompanyName) {
		this.otherCompanyName = otherCompanyName;
	}
	
	
	
	
	
}