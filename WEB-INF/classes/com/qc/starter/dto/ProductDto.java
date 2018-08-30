package com.qc.starter.dto;

import java.util.Date;
import java.util.List;

import com.qc.starter.entity.CaseXSellEntity;
import com.qc.starter.entity.ExistingFacilityEntity;

public class ProductDto {

	/*Case Header*/
	private String hiddenLeadId;
	private String leadId;
	private String status;

	/*Facility Requested*/
	private String facilityReqId;
	private String facilityRequested;
	private String schemeId;
	private String facilityRequestedBank;
	private String facilityRequestedLoanAmount;
	private String facilityRequestedRateOfIntrest;
	private String facilityRequestedTenor;
	private String facilityRequestedEmi;
	private String facilityRequestedProperty;
	private String facilityRequestedSchemeName;
	private String termCond;

	/*Product Referrel (Cross Sell)*/

	private String facilityPrdReferral;
	private String bankPrdReferral;
	private String loanAmountPrdReferral;
	private String remarkPrdReferral;

	/*For eligibility calculator*/
	private String schemeName;
	private String processingFee;
	private String reward;
	private String joiningFee;
	private String annualFee;
	private String cardName;

	/*For eligility calculator*/
	private String eligibilityId;
	private Date dob;
	private String grossMonthlyIncome;
	private String netMonthlyincome;
	private String yearInjob;
	private String workingExp;
	private String corpSalaryAc;
	private String compyId;
	private String preClosureFee;
	private String prePaymentFee;
	private String applicationFee;
	private String others;
	private String rateType;
	private String minLoanAmount;
	private String maxLoanAmount;
	private String minTenor;
	private String maxTenor;
	private String bankId;
	private String bankName;
	private String roi;
	private String obligationAmt;
	private String prodId;
	private String prodName;
	private String text;
	private String msg;
	private String renewalFee;	
	private String fixedFloatingSemi;

	//added by sandeep sat11-0ct
	private String facilityRequestedReCalEmi;

	private String occupationType;
	private String grossProfit;
	private String netProfitAftertax; 
	private String annualSales; 
	private String homeLoanValidation;

	//added by tripti 
	private String ltv;
	//added by tripti on 4 oct
	private String purposeOfLoanId;
	private String branchId;
	//--------addred By Deepak on 10-oct -2016
	private String userId;
	
	// Added by Sumit on 11-July-2017
	/*private String typeOfBusiness;
	private String typeOfBusinessForNI;
	private String cluster;
	private String clusterForNI;*/
	//private String loanAmount;
	//private String tenure;
	//private String branch;

	
	public String getStatus() {
		return status;
	}
	public String getSchemeId() {
		return schemeId;
	}
	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getBranchId() {
		return branchId;
	}
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	public String getPurposeOfLoanId() {
		return purposeOfLoanId;
	}
	public void setPurposeOfLoanId(String purposeOfLoanId) {
		this.purposeOfLoanId = purposeOfLoanId;
	}
	public String getLtv() {
		return ltv;
	}
	public void setLtv(String ltv) {
		this.ltv = ltv;
	}
	public String getHomeLoanValidation() {
		return homeLoanValidation;
	}
	public void setHomeLoanValidation(String homeLoanValidation) {
		this.homeLoanValidation = homeLoanValidation;
	}
	public String getGrossProfit() {
		return grossProfit;
	}
	public void setGrossProfit(String grossProfit) {
		this.grossProfit = grossProfit;
	}
	public String getNetProfitAftertax() {
		return netProfitAftertax;
	}
	public void setNetProfitAftertax(String netProfitAftertax) {
		this.netProfitAftertax = netProfitAftertax;
	}
	public String getAnnualSales() {
		return annualSales;
	}
	public void setAnnualSales(String annualSales) {
		this.annualSales = annualSales;
	}
	public String getOccupationType() {
		return occupationType;
	}
	public void setOccupationType(String occupationType) {
		this.occupationType = occupationType;
	}
	public String getFacilityRequestedSchemeName() {
		return facilityRequestedSchemeName;
	}
	public void setFacilityRequestedSchemeName(String facilityRequestedSchemeName) {
		this.facilityRequestedSchemeName = facilityRequestedSchemeName;
	}
	public String getFacilityRequestedReCalEmi() {
		return facilityRequestedReCalEmi;
	}
	public void setFacilityRequestedReCalEmi(String facilityRequestedReCalEmi) {
		this.facilityRequestedReCalEmi = facilityRequestedReCalEmi;
	}
	public String getFixedFloatingSemi() {
		return fixedFloatingSemi;
	}
	public void setFixedFloatingSemi(String fixedFloatingSemi) {
		this.fixedFloatingSemi = fixedFloatingSemi;
	}
	public String getTermCond() {
		return termCond;
	}
	public void setTermCond(String termCond) {
		this.termCond = termCond;
	}
	public String getRenewalFee() {
		return renewalFee;
	}
	public void setRenewalFee(String renewalFee) {
		this.renewalFee = renewalFee;
	}
	public String getEligibilityId() {
		return eligibilityId;
	}
	public void setEligibilityId(String eligibilityId) {
		this.eligibilityId = eligibilityId;
	}
	List<ExistingFacilityEntity> existingFacilityHistory;

	private List<CaseXSellEntity> listXSell;


	/**
	 * @return the listXSell
	 */
	public List<CaseXSellEntity> getListXSell() {
		return listXSell;
	}
	/**
	 * @param listXSell the listXSell to set
	 */
	public void setListXSell(List<CaseXSellEntity> listXSell) {
		this.listXSell = listXSell;
	}
	public String getFacilityReqId() {
		return facilityReqId;
	}
	public void setFacilityReqId(String facilityReqId) {
		this.facilityReqId = facilityReqId;
	}

	public List<ExistingFacilityEntity> getExistingFacilityHistory() {
		return existingFacilityHistory;
	}
	public void setExistingFacilityHistory(
			List<ExistingFacilityEntity> existingFacilityHistory) {
		this.existingFacilityHistory = existingFacilityHistory;
	}		
	public String getHiddenLeadId() {
		return hiddenLeadId;
	}
	public void setHiddenLeadId(String hiddenLeadId) {
		this.hiddenLeadId = hiddenLeadId;
	}
	public String getLeadId() {
		return leadId;
	}
	public void setLeadId(String leadId) {
		this.leadId = leadId;
	}

	public String getFacilityRequested() {
		return facilityRequested;
	}
	public void setFacilityRequested(String facilityRequested) {
		this.facilityRequested = facilityRequested;
	}
	public String getFacilityRequestedBank() {
		return facilityRequestedBank;
	}
	public void setFacilityRequestedBank(String facilityRequestedBank) {
		this.facilityRequestedBank = facilityRequestedBank;
	}
	public String getFacilityRequestedLoanAmount() {
		return facilityRequestedLoanAmount;
	}
	public void setFacilityRequestedLoanAmount(String facilityRequestedLoanAmount) {
		this.facilityRequestedLoanAmount = facilityRequestedLoanAmount;
	}
	public String getFacilityRequestedRateOfIntrest() {
		return facilityRequestedRateOfIntrest;
	}
	public void setFacilityRequestedRateOfIntrest(String facilityRequestedRateOfIntrest) {
		this.facilityRequestedRateOfIntrest = facilityRequestedRateOfIntrest;
	}
	public String getFacilityRequestedTenor() {
		return facilityRequestedTenor;
	}
	public void setFacilityRequestedTenor(String facilityRequestedTenor) {
		this.facilityRequestedTenor = facilityRequestedTenor;
	}
	public String getFacilityRequestedEmi() {
		return facilityRequestedEmi;
	}
	public void setFacilityRequestedEmi(String facilityRequestedEmi) {
		this.facilityRequestedEmi = facilityRequestedEmi;
	}
	public String getFacilityRequestedProperty() {
		return facilityRequestedProperty;
	}
	public void setFacilityRequestedProperty(String facilityRequestedProperty) {
		this.facilityRequestedProperty = facilityRequestedProperty;
	}
	public String getFacilityPrdReferral() {
		return facilityPrdReferral;
	}
	public void setFacilityPrdReferral(String facilityPrdReferral) {
		this.facilityPrdReferral = facilityPrdReferral;
	}
	public String getBankPrdReferral() {
		return bankPrdReferral;
	}
	public void setBankPrdReferral(String bankPrdReferral) {
		this.bankPrdReferral = bankPrdReferral;
	}
	public String getLoanAmountPrdReferral() {
		return loanAmountPrdReferral;
	}
	public void setLoanAmountPrdReferral(String loanAmountPrdReferral) {
		this.loanAmountPrdReferral = loanAmountPrdReferral;
	}
	public String getRemarkPrdReferral() {
		return remarkPrdReferral;
	}
	public void setRemarkPrdReferral(String remarkPrdReferral) {
		this.remarkPrdReferral = remarkPrdReferral;
	}
	public String getSchemeName() {
		return schemeName;
	}
	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}
	public String getProcessingFee() {
		return processingFee;
	}
	public void setProcessingFee(String processingFee) {
		this.processingFee = processingFee;
	}
	public String getReward() {
		return reward;
	}
	public void setReward(String reward) {
		this.reward = reward;
	}
	public String getJoiningFee() {
		return joiningFee;
	}
	public void setJoiningFee(String joiningFee) {
		this.joiningFee = joiningFee;
	}
	public String getAnnualFee() {
		return annualFee;
	}
	public void setAnnualFee(String annualFee) {
		this.annualFee = annualFee;
	}
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getGrossMonthlyIncome() {
		return grossMonthlyIncome;
	}
	public void setGrossMonthlyIncome(String grossMonthlyIncome) {
		this.grossMonthlyIncome = grossMonthlyIncome;
	}
	public String getNetMonthlyincome() {
		return netMonthlyincome;
	}
	public void setNetMonthlyincome(String netMonthlyincome) {
		this.netMonthlyincome = netMonthlyincome;
	}
	public String getYearInjob() {
		return yearInjob;
	}
	public void setYearInjob(String yearInjob) {
		this.yearInjob = yearInjob;
	}
	public String getWorkingExp() {
		return workingExp;
	}
	public void setWorkingExp(String workingExp) {
		this.workingExp = workingExp;
	}
	public String getCorpSalaryAc() {
		return corpSalaryAc;
	}
	public void setCorpSalaryAc(String corpSalaryAc) {
		this.corpSalaryAc = corpSalaryAc;
	}
	public String getCompyId() {
		return compyId;
	}
	public void setCompyId(String compyId) {
		this.compyId = compyId;
	}
	public String getPreClosureFee() {
		return preClosureFee;
	}
	public void setPreClosureFee(String preClosureFee) {
		this.preClosureFee = preClosureFee;
	}
	public String getPrePaymentFee() {
		return prePaymentFee;
	}
	public void setPrePaymentFee(String prePaymentFee) {
		this.prePaymentFee = prePaymentFee;
	}
	public String getApplicationFee() {
		return applicationFee;
	}
	public void setApplicationFee(String applicationFee) {
		this.applicationFee = applicationFee;
	}
	public String getOthers() {
		return others;
	}
	public void setOthers(String others) {
		this.others = others;
	}
	public String getRateType() {
		return rateType;
	}
	public void setRateType(String rateType) {
		this.rateType = rateType;
	}
	public String getMinLoanAmount() {
		return minLoanAmount;
	}
	public void setMinLoanAmount(String minLoanAmount) {
		this.minLoanAmount = minLoanAmount;
	}
	public String getMaxLoanAmount() {
		return maxLoanAmount;
	}
	public void setMaxLoanAmount(String maxLoanAmount) {
		this.maxLoanAmount = maxLoanAmount;
	}
	public String getMinTenor() {
		return minTenor;
	}
	public void setMinTenor(String minTenor) {
		this.minTenor = minTenor;
	}
	public String getMaxTenor() {
		return maxTenor;
	}
	public void setMaxTenor(String maxTenor) {
		this.maxTenor = maxTenor;
	}
	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getRoi() {
		return roi;
	}
	public void setRoi(String roi) {
		this.roi = roi;
	}
	public String getObligationAmt() {
		return obligationAmt;
	}
	public void setObligationAmt(String obligationAmt) {
		this.obligationAmt = obligationAmt;
	}
	public String getProdId() {
		return prodId;
	}
	public void setProdId(String prodId) {
		this.prodId = prodId;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}
	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}


}

