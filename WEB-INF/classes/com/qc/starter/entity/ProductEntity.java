package com.qc.starter.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="QM_PRODUCT", schema="QC_PROSPECT_MASTER")
public class ProductEntity {

	@Id
	@Column(name="PRODUCTID")
	private	int	productId	;
	private	String	prodCode	;
	private	String	prodName	;
	private	int	prodTypeid	;
	private	String	description	;
	private	int	parentProdid	;
	private	int	intrestType	;
	private	long	intrestOffset	;
	@Column(name="INT_RATE")			
	private	long	intRate	;
	@Column(name="MIN_LOAN_TENURE")	
	private	long	minLoanTenure	;
	@Column(name="MAX_LOAN_TENURE")			
	private	long	maxLoanTenure	;
	@Column(name="MIN_LOANTOVALUE")			
	private	long	minLoantovalue	;
	@Column(name="MAX_LOANTOVALUE")			
	private	long	maxLoantovalue	;
	@Column(name="LOANTOVALUE_APPLY")			
	private	String	loantovalueApply	;
	private	int	createdby	;
	@Column(name="CREATED_DATETIME")			
	private	Date	createdDatetime	;
	private	int	updatedby	;
	@Column(name="UPDATE_DATETIME")			
	private	Date	updateDatetime	;
	@Column(name="MIN_LOANVALUE")			
	private	long	minLoanvalue	;
	@Column(name="MAX_LOANVALUE")			
	private	long	maxLoanvalue	;
	private	String	active	;
	@Column(name="MIN_AGE")			
	private	int	minAge	;
	@Column(name="MAX_AGE")			
	private	int	max_Age	;
	private	String	intType	;
	@Column(name="SHOW_PREEMI")			
	private	String	showPreemi	;
	@Column(name="SHOW_EXCESSINT")			
	private	String	showExcessint	;
	@Column(name="SHOW_PREEMI_CHARGE")			
	private	String	showPreemiCharge	;
	@Column(name="WORKFLOW_PRODUCTID")			
	private	int	workflowProductid	;
	@Column(name="SHOW_INCOMEDETAIL")			
	private	String	showIncomedetail	;
	@Column(name="SHOW_ASSETDETAIL")			
	private	String	showAssetdetail	;
	@Column(name="EFF_DATE_FROM")			
	private	Date	effDateFrom	;
	@Column(name="EXPIRED_ON")			
	private	Date	expiredOn	;
	@Column(name="LTV_DEV")			
	private	int	ltvDev	;
	@Column(name="DBR_DEV")			
	private	int	dbrDev	;
	@Column(name="GRACE_AMOUNT")			
	private	long	graceAmount	;
	@Column(name="CHANGE_T_E")			
	private	char	changeTE	;
	@Column(name="RESCHEDULE_FLAG")			
	private	String	rescheduleFlag	;
	@Column(name="RESCHEDULE_EFF_DATE_FLAG")			
	private	String	rescheduleEffDateFlag	;
	@Column(name="RESCH_EFF_DATE")			
	private	Date	reschEffDate	;
	@Column(name="RESCHEDULECHARGE_FIXED")			
	private	char	reschedulechargeFixed	;
	@Column(name="RESCHEDULECHARGE_AMT")			
	private	long	reschedulechargeAmt	;
	@Column(name="RESCHEDULECHARGE_PERCENT")			
	private	long	reschedulechargePercent	;
	@Column(name="MAX_PREPAYMENT_FIXED")			
	private	char	maxPrepaymentFixed	;
	@Column(name="MAX_PREPAYMENT_AMT")			
	private	long	maxPrepaymentAmt	;
	@Column(name="MAX_PREPAYMENT_PERCENT")			
	private	long	maxPrepaymentPercent	;
	@Column(name="MAX_RESCHEDULEINYEAR")			
	private	long	maxRescheduleinyear	;
	@Column(name="MAKER_ID")			
	private	int	makerId	;
	@Column(name="MAKER_DATE")			
	private	Date	makerDate	;
	@Column(name="MAKER_REMARKS")			
	private	String	makerRemarks	;
	@Column(name="MAKER_SYSDATE")			
	private	Date	makerSysdate	;
	@Column(name="AUTH_ID")			
	private	int	authId	;
	@Column(name="AUTH_DATE")			
	private	Date	authDate	;
	@Column(name="AUTH_REMARK")			
	private	String	authRemark	;
	@Column(name="AUTH_SYSDATE")			
	private	Date	authSysdate	;
	private	int	productCategoryId	;
	@Column(name="TRANCHE_GRACE_PERIOD")			
	private	long	trancheGracePeriod	;
	@Column(name="GOLD_RATE")			
	private	long	goldRate	;
	@Column(name="MIN_LTV")			
	private	long	minLtv	;
	@Column(name="MAX_LTV")			
	private	long	maxLtv	;
	@Column(name="MIN_IRR")			
	private	long	min_Irr	;
	@Column(name="MAX_IRR")			
	private	long	maxIrr	;
	@Column(name="PENAL_ROI")			
	private	long	penalRoi	;
	@Column(name="ADVANCE_ROI")			
	private	long	advanceRoi	;
	@Column(name="INT_REST_PERIOD")			
	private	String	intRestPeriod	;
	@Column(name="SCHEME_STARTDATE")			
	private	Date	schemeStartdate	;
	@Column(name="SCHEME_ENDDATE")			
	private	Date	schemeEnddate	;
	@Column(name="MORATORIUM_REQ")			
	private	String	moratoriumReq	;
	@Column(name="MIN_MORATORIUM")			
	private	long	minMoratorium	;
	@Column(name="MAX_MORATORIUM")			
	private	long	maxMoratorium	;
	@Column(name="NATURE_OF_PROD")			
	private	int	natureOfProd	;
	@Column(name="SHOW_PLEDGECARD")			
	private	String	showPledgecard	;
	@Column(name="SHOW_DENOMINATION")			
	private	String	showDenomination	;
	@Column(name="EMI_CALC_START_FREQ")			
	private	String	emiCalcStartFreq	;
	@Column(name="IIRCAP_PER")			
	private	long	iircapPer	;
	@Column(name="CAM_FLAG")			
	private	String	camFlag	;
	@Column(name="MULTILEVEL_CAM")			
	private	double	multilevelCam	;
	@Column(name="GOLDLOAN_FLAG")			
	private	String	goldloanFlag	;
	@Column(name="CR_RATING_REQ")			
	private	String	crRatingReq	;
	@Column(name="DAYS_ALLOWED_TO_CANCEL")			
	private	long	daysAllowedToCancel	;
	@Column(name="ALLOW_CANCEL_AFTER_ACCRUAL")			
	private	String	allowCancelAfterAccrual	;
	@Column(name="PAYMENT_FLAG")			
	private	String	paymentFlag	;
	@Column(name="FR_FLAG")			
	private	String	frFlag	;
	@Column(name="REVOLVING_FLAG")			
	private	String	revolvingFlag	;
	@Column(name="ESCROW_APPLICABLE")			
	private	char	escrowApplicable	;
	@Column(name="ROUND_WHAT")			
	private	String	roundWhat	;
	@Column(name="ROUND_TYPE")			
	private	String	roundType	;
	@Column(name="ROUND_TO")			
	private	long	roundTo	;
	@Column(name="DAY_DIFF_LOGIC")			
	private	long	dayDiffLogic	;
	@Column(name="INT_RATE_DIVISOR")			
	private	long	intRateDivisor	;
	@Column(name="PRE_EMI_FLAG")			
	private	String	preEmiFlag	;
	@Column(name="EXCESS_INTEREST_FLAG")			
	private	String	excessInterestFlag	;
	@Column(name="UPFRONT_INTEREST")			
	private	String	upfrontInterest	;
	@Column(name="HOLIDAYS_IMPACT_BILLING")			
	private	char	holidaysImpactBilling	;
	@Column(name="EARLY_FIRST_DUE")			
	private	char	earlyFirstDue	;
	@Column(name="COMPANY_ID")			
	private	int	companyId	;
	@Column(name="INSTALLMENT_DAY")			
	private	long	installmentDay	;
	/**
	 * @return the productId
	 */
	public int getProductId() {
		return productId;
	}
	/**
	 * @param productId the productId to set
	 */
	public void setProductId(int productId) {
		this.productId = productId;
	}
	/**
	 * @return the prodCode
	 */
	public String getProdCode() {
		return prodCode;
	}
	/**
	 * @param prodCode the prodCode to set
	 */
	public void setProdCode(String prodCode) {
		this.prodCode = prodCode;
	}
	/**
	 * @return the prodName
	 */
	public String getProdName() {
		return prodName;
	}
	/**
	 * @param prodName the prodName to set
	 */
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	/**
	 * @return the prodTypeid
	 */
	public int getProdTypeid() {
		return prodTypeid;
	}
	/**
	 * @param prodTypeid the prodTypeid to set
	 */
	public void setProdTypeid(int prodTypeid) {
		this.prodTypeid = prodTypeid;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the parentProdid
	 */
	public int getParentProdid() {
		return parentProdid;
	}
	/**
	 * @param parentProdid the parentProdid to set
	 */
	public void setParentProdid(int parentProdid) {
		this.parentProdid = parentProdid;
	}
	/**
	 * @return the intrestType
	 */
	public int getIntrestType() {
		return intrestType;
	}
	/**
	 * @param intrestType the intrestType to set
	 */
	public void setIntrestType(int intrestType) {
		this.intrestType = intrestType;
	}
	/**
	 * @return the intrestOffset
	 */
	public long getIntrestOffset() {
		return intrestOffset;
	}
	/**
	 * @param intrestOffset the intrestOffset to set
	 */
	public void setIntrestOffset(long intrestOffset) {
		this.intrestOffset = intrestOffset;
	}
	/**
	 * @return the intRate
	 */
	public long getIntRate() {
		return intRate;
	}
	/**
	 * @param intRate the intRate to set
	 */
	public void setIntRate(long intRate) {
		this.intRate = intRate;
	}
	/**
	 * @return the minLoanTenure
	 */
	public long getMinLoanTenure() {
		return minLoanTenure;
	}
	/**
	 * @param minLoanTenure the minLoanTenure to set
	 */
	public void setMinLoanTenure(long minLoanTenure) {
		this.minLoanTenure = minLoanTenure;
	}
	/**
	 * @return the maxLoanTenure
	 */
	public long getMaxLoanTenure() {
		return maxLoanTenure;
	}
	/**
	 * @param maxLoanTenure the maxLoanTenure to set
	 */
	public void setMaxLoanTenure(long maxLoanTenure) {
		this.maxLoanTenure = maxLoanTenure;
	}
	/**
	 * @return the minLoantovalue
	 */
	public long getMinLoantovalue() {
		return minLoantovalue;
	}
	/**
	 * @param minLoantovalue the minLoantovalue to set
	 */
	public void setMinLoantovalue(long minLoantovalue) {
		this.minLoantovalue = minLoantovalue;
	}
	/**
	 * @return the maxLoantovalue
	 */
	public long getMaxLoantovalue() {
		return maxLoantovalue;
	}
	/**
	 * @param maxLoantovalue the maxLoantovalue to set
	 */
	public void setMaxLoantovalue(long maxLoantovalue) {
		this.maxLoantovalue = maxLoantovalue;
	}
	/**
	 * @return the loantovalueApply
	 */
	public String getLoantovalueApply() {
		return loantovalueApply;
	}
	/**
	 * @param loantovalueApply the loantovalueApply to set
	 */
	public void setLoantovalueApply(String loantovalueApply) {
		this.loantovalueApply = loantovalueApply;
	}
	/**
	 * @return the createdby
	 */
	public int getCreatedby() {
		return createdby;
	}
	/**
	 * @param createdby the createdby to set
	 */
	public void setCreatedby(int createdby) {
		this.createdby = createdby;
	}
	/**
	 * @return the createdDatetime
	 */
	public Date getCreatedDatetime() {
		return createdDatetime;
	}
	/**
	 * @param createdDatetime the createdDatetime to set
	 */
	public void setCreatedDatetime(Date createdDatetime) {
		this.createdDatetime = createdDatetime;
	}
	/**
	 * @return the updatedby
	 */
	public int getUpdatedby() {
		return updatedby;
	}
	/**
	 * @param updatedby the updatedby to set
	 */
	public void setUpdatedby(int updatedby) {
		this.updatedby = updatedby;
	}
	/**
	 * @return the updateDatetime
	 */
	public Date getUpdateDatetime() {
		return updateDatetime;
	}
	/**
	 * @param updateDatetime the updateDatetime to set
	 */
	public void setUpdateDatetime(Date updateDatetime) {
		this.updateDatetime = updateDatetime;
	}
	/**
	 * @return the minLoanvalue
	 */
	public long getMinLoanvalue() {
		return minLoanvalue;
	}
	/**
	 * @param minLoanvalue the minLoanvalue to set
	 */
	public void setMinLoanvalue(long minLoanvalue) {
		this.minLoanvalue = minLoanvalue;
	}
	/**
	 * @return the maxLoanvalue
	 */
	public long getMaxLoanvalue() {
		return maxLoanvalue;
	}
	/**
	 * @param maxLoanvalue the maxLoanvalue to set
	 */
	public void setMaxLoanvalue(long maxLoanvalue) {
		this.maxLoanvalue = maxLoanvalue;
	}
	/**
	 * @return the active
	 */
	public String getActive() {
		return active;
	}
	/**
	 * @param active the active to set
	 */
	public void setActive(String active) {
		this.active = active;
	}
	/**
	 * @return the minAge
	 */
	public int getMinAge() {
		return minAge;
	}
	/**
	 * @param minAge the minAge to set
	 */
	public void setMinAge(int minAge) {
		this.minAge = minAge;
	}
	/**
	 * @return the max_Age
	 */
	public int getMax_Age() {
		return max_Age;
	}
	/**
	 * @param max_Age the max_Age to set
	 */
	public void setMax_Age(int max_Age) {
		this.max_Age = max_Age;
	}
	/**
	 * @return the intType
	 */
	public String getIntType() {
		return intType;
	}
	/**
	 * @param intType the intType to set
	 */
	public void setIntType(String intType) {
		this.intType = intType;
	}
	/**
	 * @return the showPreemi
	 */
	public String getShowPreemi() {
		return showPreemi;
	}
	/**
	 * @param showPreemi the showPreemi to set
	 */
	public void setShowPreemi(String showPreemi) {
		this.showPreemi = showPreemi;
	}
	/**
	 * @return the showExcessint
	 */
	public String getShowExcessint() {
		return showExcessint;
	}
	/**
	 * @param showExcessint the showExcessint to set
	 */
	public void setShowExcessint(String showExcessint) {
		this.showExcessint = showExcessint;
	}
	/**
	 * @return the showPreemiCharge
	 */
	public String getShowPreemiCharge() {
		return showPreemiCharge;
	}
	/**
	 * @param showPreemiCharge the showPreemiCharge to set
	 */
	public void setShowPreemiCharge(String showPreemiCharge) {
		this.showPreemiCharge = showPreemiCharge;
	}
	/**
	 * @return the workflowProductid
	 */
	public int getWorkflowProductid() {
		return workflowProductid;
	}
	/**
	 * @param workflowProductid the workflowProductid to set
	 */
	public void setWorkflowProductid(int workflowProductid) {
		this.workflowProductid = workflowProductid;
	}
	/**
	 * @return the showIncomedetail
	 */
	public String getShowIncomedetail() {
		return showIncomedetail;
	}
	/**
	 * @param showIncomedetail the showIncomedetail to set
	 */
	public void setShowIncomedetail(String showIncomedetail) {
		this.showIncomedetail = showIncomedetail;
	}
	/**
	 * @return the showAssetdetail
	 */
	public String getShowAssetdetail() {
		return showAssetdetail;
	}
	/**
	 * @param showAssetdetail the showAssetdetail to set
	 */
	public void setShowAssetdetail(String showAssetdetail) {
		this.showAssetdetail = showAssetdetail;
	}
	/**
	 * @return the effDateFrom
	 */
	public Date getEffDateFrom() {
		return effDateFrom;
	}
	/**
	 * @param effDateFrom the effDateFrom to set
	 */
	public void setEffDateFrom(Date effDateFrom) {
		this.effDateFrom = effDateFrom;
	}
	/**
	 * @return the expiredOn
	 */
	public Date getExpiredOn() {
		return expiredOn;
	}
	/**
	 * @param expiredOn the expiredOn to set
	 */
	public void setExpiredOn(Date expiredOn) {
		this.expiredOn = expiredOn;
	}
	/**
	 * @return the ltvDev
	 */
	public int getLtvDev() {
		return ltvDev;
	}
	/**
	 * @param ltvDev the ltvDev to set
	 */
	public void setLtvDev(int ltvDev) {
		this.ltvDev = ltvDev;
	}
	/**
	 * @return the dbrDev
	 */
	public int getDbrDev() {
		return dbrDev;
	}
	/**
	 * @param dbrDev the dbrDev to set
	 */
	public void setDbrDev(int dbrDev) {
		this.dbrDev = dbrDev;
	}
	/**
	 * @return the graceAmount
	 */
	public long getGraceAmount() {
		return graceAmount;
	}
	/**
	 * @param graceAmount the graceAmount to set
	 */
	public void setGraceAmount(long graceAmount) {
		this.graceAmount = graceAmount;
	}
	/**
	 * @return the changeTE
	 */
	public char getChangeTE() {
		return changeTE;
	}
	/**
	 * @param changeTE the changeTE to set
	 */
	public void setChangeTE(char changeTE) {
		this.changeTE = changeTE;
	}
	/**
	 * @return the rescheduleFlag
	 */
	public String getRescheduleFlag() {
		return rescheduleFlag;
	}
	/**
	 * @param rescheduleFlag the rescheduleFlag to set
	 */
	public void setRescheduleFlag(String rescheduleFlag) {
		this.rescheduleFlag = rescheduleFlag;
	}
	/**
	 * @return the rescheduleEffDateFlag
	 */
	public String getRescheduleEffDateFlag() {
		return rescheduleEffDateFlag;
	}
	/**
	 * @param rescheduleEffDateFlag the rescheduleEffDateFlag to set
	 */
	public void setRescheduleEffDateFlag(String rescheduleEffDateFlag) {
		this.rescheduleEffDateFlag = rescheduleEffDateFlag;
	}
	/**
	 * @return the reschEffDate
	 */
	public Date getReschEffDate() {
		return reschEffDate;
	}
	/**
	 * @param reschEffDate the reschEffDate to set
	 */
	public void setReschEffDate(Date reschEffDate) {
		this.reschEffDate = reschEffDate;
	}
	/**
	 * @return the reschedulechargeFixed
	 */
	public char getReschedulechargeFixed() {
		return reschedulechargeFixed;
	}
	/**
	 * @param reschedulechargeFixed the reschedulechargeFixed to set
	 */
	public void setReschedulechargeFixed(char reschedulechargeFixed) {
		this.reschedulechargeFixed = reschedulechargeFixed;
	}
	/**
	 * @return the reschedulechargeAmt
	 */
	public long getReschedulechargeAmt() {
		return reschedulechargeAmt;
	}
	/**
	 * @param reschedulechargeAmt the reschedulechargeAmt to set
	 */
	public void setReschedulechargeAmt(long reschedulechargeAmt) {
		this.reschedulechargeAmt = reschedulechargeAmt;
	}
	/**
	 * @return the reschedulechargePercent
	 */
	public long getReschedulechargePercent() {
		return reschedulechargePercent;
	}
	/**
	 * @param reschedulechargePercent the reschedulechargePercent to set
	 */
	public void setReschedulechargePercent(long reschedulechargePercent) {
		this.reschedulechargePercent = reschedulechargePercent;
	}
	/**
	 * @return the maxPrepaymentFixed
	 */
	public char getMaxPrepaymentFixed() {
		return maxPrepaymentFixed;
	}
	/**
	 * @param maxPrepaymentFixed the maxPrepaymentFixed to set
	 */
	public void setMaxPrepaymentFixed(char maxPrepaymentFixed) {
		this.maxPrepaymentFixed = maxPrepaymentFixed;
	}
	/**
	 * @return the maxPrepaymentAmt
	 */
	public long getMaxPrepaymentAmt() {
		return maxPrepaymentAmt;
	}
	/**
	 * @param maxPrepaymentAmt the maxPrepaymentAmt to set
	 */
	public void setMaxPrepaymentAmt(long maxPrepaymentAmt) {
		this.maxPrepaymentAmt = maxPrepaymentAmt;
	}
	/**
	 * @return the maxPrepaymentPercent
	 */
	public long getMaxPrepaymentPercent() {
		return maxPrepaymentPercent;
	}
	/**
	 * @param maxPrepaymentPercent the maxPrepaymentPercent to set
	 */
	public void setMaxPrepaymentPercent(long maxPrepaymentPercent) {
		this.maxPrepaymentPercent = maxPrepaymentPercent;
	}
	/**
	 * @return the maxRescheduleinyear
	 */
	public long getMaxRescheduleinyear() {
		return maxRescheduleinyear;
	}
	/**
	 * @param maxRescheduleinyear the maxRescheduleinyear to set
	 */
	public void setMaxRescheduleinyear(long maxRescheduleinyear) {
		this.maxRescheduleinyear = maxRescheduleinyear;
	}
	/**
	 * @return the makerId
	 */
	public int getMakerId() {
		return makerId;
	}
	/**
	 * @param makerId the makerId to set
	 */
	public void setMakerId(int makerId) {
		this.makerId = makerId;
	}
	/**
	 * @return the makerDate
	 */
	public Date getMakerDate() {
		return makerDate;
	}
	/**
	 * @param makerDate the makerDate to set
	 */
	public void setMakerDate(Date makerDate) {
		this.makerDate = makerDate;
	}
	/**
	 * @return the makerRemarks
	 */
	public String getMakerRemarks() {
		return makerRemarks;
	}
	/**
	 * @param makerRemarks the makerRemarks to set
	 */
	public void setMakerRemarks(String makerRemarks) {
		this.makerRemarks = makerRemarks;
	}
	/**
	 * @return the makerSysdate
	 */
	public Date getMakerSysdate() {
		return makerSysdate;
	}
	/**
	 * @param makerSysdate the makerSysdate to set
	 */
	public void setMakerSysdate(Date makerSysdate) {
		this.makerSysdate = makerSysdate;
	}
	/**
	 * @return the authId
	 */
	public int getAuthId() {
		return authId;
	}
	/**
	 * @param authId the authId to set
	 */
	public void setAuthId(int authId) {
		this.authId = authId;
	}
	/**
	 * @return the authDate
	 */
	public Date getAuthDate() {
		return authDate;
	}
	/**
	 * @param authDate the authDate to set
	 */
	public void setAuthDate(Date authDate) {
		this.authDate = authDate;
	}
	/**
	 * @return the authRemark
	 */
	public String getAuthRemark() {
		return authRemark;
	}
	/**
	 * @param authRemark the authRemark to set
	 */
	public void setAuthRemark(String authRemark) {
		this.authRemark = authRemark;
	}
	/**
	 * @return the authSysdate
	 */
	public Date getAuthSysdate() {
		return authSysdate;
	}
	/**
	 * @param authSysdate the authSysdate to set
	 */
	public void setAuthSysdate(Date authSysdate) {
		this.authSysdate = authSysdate;
	}
	/**
	 * @return the productCategoryId
	 */
	public int getProductCategoryId() {
		return productCategoryId;
	}
	/**
	 * @param productCategoryId the productCategoryId to set
	 */
	public void setProductCategoryId(int productCategoryId) {
		this.productCategoryId = productCategoryId;
	}
	/**
	 * @return the trancheGracePeriod
	 */
	public long getTrancheGracePeriod() {
		return trancheGracePeriod;
	}
	/**
	 * @param trancheGracePeriod the trancheGracePeriod to set
	 */
	public void setTrancheGracePeriod(long trancheGracePeriod) {
		this.trancheGracePeriod = trancheGracePeriod;
	}
	/**
	 * @return the goldRate
	 */
	public long getGoldRate() {
		return goldRate;
	}
	/**
	 * @param goldRate the goldRate to set
	 */
	public void setGoldRate(long goldRate) {
		this.goldRate = goldRate;
	}
	/**
	 * @return the minLtv
	 */
	public long getMinLtv() {
		return minLtv;
	}
	/**
	 * @param minLtv the minLtv to set
	 */
	public void setMinLtv(long minLtv) {
		this.minLtv = minLtv;
	}
	/**
	 * @return the maxLtv
	 */
	public long getMaxLtv() {
		return maxLtv;
	}
	/**
	 * @param maxLtv the maxLtv to set
	 */
	public void setMaxLtv(long maxLtv) {
		this.maxLtv = maxLtv;
	}
	/**
	 * @return the min_Irr
	 */
	public long getMin_Irr() {
		return min_Irr;
	}
	/**
	 * @param min_Irr the min_Irr to set
	 */
	public void setMin_Irr(long min_Irr) {
		this.min_Irr = min_Irr;
	}
	/**
	 * @return the maxIrr
	 */
	public long getMaxIrr() {
		return maxIrr;
	}
	/**
	 * @param maxIrr the maxIrr to set
	 */
	public void setMaxIrr(long maxIrr) {
		this.maxIrr = maxIrr;
	}
	/**
	 * @return the penalRoi
	 */
	public long getPenalRoi() {
		return penalRoi;
	}
	/**
	 * @param penalRoi the penalRoi to set
	 */
	public void setPenalRoi(long penalRoi) {
		this.penalRoi = penalRoi;
	}
	/**
	 * @return the advanceRoi
	 */
	public long getAdvanceRoi() {
		return advanceRoi;
	}
	/**
	 * @param advanceRoi the advanceRoi to set
	 */
	public void setAdvanceRoi(long advanceRoi) {
		this.advanceRoi = advanceRoi;
	}
	/**
	 * @return the intRestPeriod
	 */
	public String getIntRestPeriod() {
		return intRestPeriod;
	}
	/**
	 * @param intRestPeriod the intRestPeriod to set
	 */
	public void setIntRestPeriod(String intRestPeriod) {
		this.intRestPeriod = intRestPeriod;
	}
	/**
	 * @return the schemeStartdate
	 */
	public Date getSchemeStartdate() {
		return schemeStartdate;
	}
	/**
	 * @param schemeStartdate the schemeStartdate to set
	 */
	public void setSchemeStartdate(Date schemeStartdate) {
		this.schemeStartdate = schemeStartdate;
	}
	/**
	 * @return the schemeEnddate
	 */
	public Date getSchemeEnddate() {
		return schemeEnddate;
	}
	/**
	 * @param schemeEnddate the schemeEnddate to set
	 */
	public void setSchemeEnddate(Date schemeEnddate) {
		this.schemeEnddate = schemeEnddate;
	}
	/**
	 * @return the moratoriumReq
	 */
	public String getMoratoriumReq() {
		return moratoriumReq;
	}
	/**
	 * @param moratoriumReq the moratoriumReq to set
	 */
	public void setMoratoriumReq(String moratoriumReq) {
		this.moratoriumReq = moratoriumReq;
	}
	/**
	 * @return the minMoratorium
	 */
	public long getMinMoratorium() {
		return minMoratorium;
	}
	/**
	 * @param minMoratorium the minMoratorium to set
	 */
	public void setMinMoratorium(long minMoratorium) {
		this.minMoratorium = minMoratorium;
	}
	/**
	 * @return the maxMoratorium
	 */
	public long getMaxMoratorium() {
		return maxMoratorium;
	}
	/**
	 * @param maxMoratorium the maxMoratorium to set
	 */
	public void setMaxMoratorium(long maxMoratorium) {
		this.maxMoratorium = maxMoratorium;
	}
	/**
	 * @return the natureOfProd
	 */
	public int getNatureOfProd() {
		return natureOfProd;
	}
	/**
	 * @param natureOfProd the natureOfProd to set
	 */
	public void setNatureOfProd(int natureOfProd) {
		this.natureOfProd = natureOfProd;
	}
	/**
	 * @return the showPledgecard
	 */
	public String getShowPledgecard() {
		return showPledgecard;
	}
	/**
	 * @param showPledgecard the showPledgecard to set
	 */
	public void setShowPledgecard(String showPledgecard) {
		this.showPledgecard = showPledgecard;
	}
	/**
	 * @return the showDenomination
	 */
	public String getShowDenomination() {
		return showDenomination;
	}
	/**
	 * @param showDenomination the showDenomination to set
	 */
	public void setShowDenomination(String showDenomination) {
		this.showDenomination = showDenomination;
	}
	/**
	 * @return the emiCalcStartFreq
	 */
	public String getEmiCalcStartFreq() {
		return emiCalcStartFreq;
	}
	/**
	 * @param emiCalcStartFreq the emiCalcStartFreq to set
	 */
	public void setEmiCalcStartFreq(String emiCalcStartFreq) {
		this.emiCalcStartFreq = emiCalcStartFreq;
	}
	/**
	 * @return the iircapPer
	 */
	public long getIircapPer() {
		return iircapPer;
	}
	/**
	 * @param iircapPer the iircapPer to set
	 */
	public void setIircapPer(long iircapPer) {
		this.iircapPer = iircapPer;
	}
	/**
	 * @return the camFlag
	 */
	public String getCamFlag() {
		return camFlag;
	}
	/**
	 * @param camFlag the camFlag to set
	 */
	public void setCamFlag(String camFlag) {
		this.camFlag = camFlag;
	}
	/**
	 * @return the multilevelCam
	 */
	public double getMultilevelCam() {
		return multilevelCam;
	}
	/**
	 * @param multilevelCam the multilevelCam to set
	 */
	public void setMultilevelCam(double multilevelCam) {
		this.multilevelCam = multilevelCam;
	}
	/**
	 * @return the goldloanFlag
	 */
	public String getGoldloanFlag() {
		return goldloanFlag;
	}
	/**
	 * @param goldloanFlag the goldloanFlag to set
	 */
	public void setGoldloanFlag(String goldloanFlag) {
		this.goldloanFlag = goldloanFlag;
	}
	/**
	 * @return the crRatingReq
	 */
	public String getCrRatingReq() {
		return crRatingReq;
	}
	/**
	 * @param crRatingReq the crRatingReq to set
	 */
	public void setCrRatingReq(String crRatingReq) {
		this.crRatingReq = crRatingReq;
	}
	/**
	 * @return the daysAllowedToCancel
	 */
	public long getDaysAllowedToCancel() {
		return daysAllowedToCancel;
	}
	/**
	 * @param daysAllowedToCancel the daysAllowedToCancel to set
	 */
	public void setDaysAllowedToCancel(long daysAllowedToCancel) {
		this.daysAllowedToCancel = daysAllowedToCancel;
	}
	/**
	 * @return the allowCancelAfterAccrual
	 */
	public String getAllowCancelAfterAccrual() {
		return allowCancelAfterAccrual;
	}
	/**
	 * @param allowCancelAfterAccrual the allowCancelAfterAccrual to set
	 */
	public void setAllowCancelAfterAccrual(String allowCancelAfterAccrual) {
		this.allowCancelAfterAccrual = allowCancelAfterAccrual;
	}
	/**
	 * @return the paymentFlag
	 */
	public String getPaymentFlag() {
		return paymentFlag;
	}
	/**
	 * @param paymentFlag the paymentFlag to set
	 */
	public void setPaymentFlag(String paymentFlag) {
		this.paymentFlag = paymentFlag;
	}
	/**
	 * @return the frFlag
	 */
	public String getFrFlag() {
		return frFlag;
	}
	/**
	 * @param frFlag the frFlag to set
	 */
	public void setFrFlag(String frFlag) {
		this.frFlag = frFlag;
	}
	/**
	 * @return the revolvingFlag
	 */
	public String getRevolvingFlag() {
		return revolvingFlag;
	}
	/**
	 * @param revolvingFlag the revolvingFlag to set
	 */
	public void setRevolvingFlag(String revolvingFlag) {
		this.revolvingFlag = revolvingFlag;
	}
	/**
	 * @return the escrowApplicable
	 */
	public char getEscrowApplicable() {
		return escrowApplicable;
	}
	/**
	 * @param escrowApplicable the escrowApplicable to set
	 */
	public void setEscrowApplicable(char escrowApplicable) {
		this.escrowApplicable = escrowApplicable;
	}
	/**
	 * @return the roundWhat
	 */
	public String getRoundWhat() {
		return roundWhat;
	}
	/**
	 * @param roundWhat the roundWhat to set
	 */
	public void setRoundWhat(String roundWhat) {
		this.roundWhat = roundWhat;
	}
	/**
	 * @return the roundType
	 */
	public String getRoundType() {
		return roundType;
	}
	/**
	 * @param roundType the roundType to set
	 */
	public void setRoundType(String roundType) {
		this.roundType = roundType;
	}
	/**
	 * @return the roundTo
	 */
	public long getRoundTo() {
		return roundTo;
	}
	/**
	 * @param roundTo the roundTo to set
	 */
	public void setRoundTo(long roundTo) {
		this.roundTo = roundTo;
	}
	/**
	 * @return the dayDiffLogic
	 */
	public long getDayDiffLogic() {
		return dayDiffLogic;
	}
	/**
	 * @param dayDiffLogic the dayDiffLogic to set
	 */
	public void setDayDiffLogic(long dayDiffLogic) {
		this.dayDiffLogic = dayDiffLogic;
	}
	/**
	 * @return the intRateDivisor
	 */
	public long getIntRateDivisor() {
		return intRateDivisor;
	}
	/**
	 * @param intRateDivisor the intRateDivisor to set
	 */
	public void setIntRateDivisor(long intRateDivisor) {
		this.intRateDivisor = intRateDivisor;
	}
	/**
	 * @return the preEmiFlag
	 */
	public String getPreEmiFlag() {
		return preEmiFlag;
	}
	/**
	 * @param preEmiFlag the preEmiFlag to set
	 */
	public void setPreEmiFlag(String preEmiFlag) {
		this.preEmiFlag = preEmiFlag;
	}
	/**
	 * @return the excessInterestFlag
	 */
	public String getExcessInterestFlag() {
		return excessInterestFlag;
	}
	/**
	 * @param excessInterestFlag the excessInterestFlag to set
	 */
	public void setExcessInterestFlag(String excessInterestFlag) {
		this.excessInterestFlag = excessInterestFlag;
	}
	/**
	 * @return the upfrontInterest
	 */
	public String getUpfrontInterest() {
		return upfrontInterest;
	}
	/**
	 * @param upfrontInterest the upfrontInterest to set
	 */
	public void setUpfrontInterest(String upfrontInterest) {
		this.upfrontInterest = upfrontInterest;
	}
	/**
	 * @return the holidaysImpactBilling
	 */
	public char getHolidaysImpactBilling() {
		return holidaysImpactBilling;
	}
	/**
	 * @param holidaysImpactBilling the holidaysImpactBilling to set
	 */
	public void setHolidaysImpactBilling(char holidaysImpactBilling) {
		this.holidaysImpactBilling = holidaysImpactBilling;
	}
	/**
	 * @return the earlyFirstDue
	 */
	public char getEarlyFirstDue() {
		return earlyFirstDue;
	}
	/**
	 * @param earlyFirstDue the earlyFirstDue to set
	 */
	public void setEarlyFirstDue(char earlyFirstDue) {
		this.earlyFirstDue = earlyFirstDue;
	}
	/**
	 * @return the companyId
	 */
	public int getCompanyId() {
		return companyId;
	}
	/**
	 * @param companyId the companyId to set
	 */
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	/**
	 * @return the installmentDay
	 */
	public long getInstallmentDay() {
		return installmentDay;
	}
	/**
	 * @param installmentDay the installmentDay to set
	 */
	public void setInstallmentDay(long installmentDay) {
		this.installmentDay = installmentDay;
	}
	
	
	
}
