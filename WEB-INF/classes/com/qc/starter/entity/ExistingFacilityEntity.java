package com.qc.starter.entity;

import java.sql.Date;

/*import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicUpdate;*/

//@Entity
//@Table(name="QT_EXISTING_FAC", schema="QC_PROSPECT")
//@DynamicUpdate
public class ExistingFacilityEntity {
	
	//@Transient
	private String checkRow;
	//@Id
	////@Column(name="EXITING_FAC_ID")			
	private	String	exitingFacId	;
	
	//@Transient
	private String facility;
	
	////@Column(name="BANK_ID")			
	private	String	bankId	;
	
	//@Transient
	private String bank;
	
	////@Column(name="LOAN_CC_AMT")			
	private	String	loanCcAmt	;
	////@Column(name="EMI_CC_OUTSTANDING")			
	private	String	emiCcOutstanding	;
	////@Column(name="TENOR_BAL_MONTHS")			
	private	String	tenorBalMonths	;
	private	String	remarks;	
	////@Column(name="PERSONAL_DTL_ID")			
	private	String	personalDtlId	;
	////@Column(name="CASE_ID")			
	private	String	caseId	;
	////@Column(name="PROD_ID")			
	private	String	prodId	;
	////@Column(name="TENOR_BAL_YEARS")			
	private	String	tenorBalYears	;	
	////@Column(name="CREATED_BY")			
	private	String	createdBy	;
	////@Column(name="CREATED_DATE")			
	private	Date	createdDate	;
	////@Column(name="CREATED_SYS_DATE")			
	private	Date	createdSysDate	;
	////@Column(name="UPDATED_BY")			
	private	String	updatedBy	;
	////@Column(name="UPDATED_DATE")			
	private	Date	updatedDate	;
	////@Column(name="UPDATED_SYS_DATE")			
	private	Date	updatedSysDate	;
	/**
	 * @return the exitingFacId
	 */
	public String getExitingFacId() {
		return exitingFacId;
	}
	/**
	 * @param exitingFacId the exitingFacId to set
	 */
	public void setExitingFacId(String exitingFacId) {
		this.exitingFacId = exitingFacId;
	}
	/**
	 * @return the personalDtlId
	 */
	public String getPersonalDtlId() {
		return personalDtlId;
	}
	/**
	 * @param personalDtlId the personalDtlId to set
	 */
	public void setPersonalDtlId(String personalDtlId) {
		this.personalDtlId = personalDtlId;
	}
	/**
	 * @return the caseId
	 */
	public String getCaseId() {
		return caseId;
	}
	/**
	 * @param caseId the caseId to set
	 */
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	/**
	 * @return the prodId
	 */
	public String getProdId() {
		return prodId;
	}
	/**
	 * @param prodId the prodId to set
	 */
	public void setProdId(String prodId) {
		this.prodId = prodId;
	}
	/**
	 * @return the bankId
	 */
	public String getBankId() {
		return bankId;
	}
	/**
	 * @param bankId the bankId to set
	 */
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	/**
	 * @return the loanCcAmt
	 */
	public String getLoanCcAmt() {
		return loanCcAmt;
	}
	/**
	 * @param loanCcAmt the loanCcAmt to set
	 */
	public void setLoanCcAmt(String loanCcAmt) {
		this.loanCcAmt = loanCcAmt;
	}
	/**
	 * @return the emiCcOutstanding
	 */
	public String getEmiCcOutstanding() {
		return emiCcOutstanding;
	}
	/**
	 * @param emiCcOutstanding the emiCcOutstanding to set
	 */
	public void setEmiCcOutstanding(String emiCcOutstanding) {
		this.emiCcOutstanding = emiCcOutstanding;
	}
	/**
	 * @return the tenorBalYears
	 */
	public String getTenorBalYears() {
		return tenorBalYears;
	}
	/**
	 * @param tenorBalYears the tenorBalYears to set
	 */
	public void setTenorBalYears(String tenorBalYears) {
		this.tenorBalYears = tenorBalYears;
	}
	/**
	 * @return the tenorBalMonths
	 */
	public String getTenorBalMonths() {
		return tenorBalMonths;
	}
	/**
	 * @param tenorBalMonths the tenorBalMonths to set
	 */
	public void setTenorBalMonths(String tenorBalMonths) {
		this.tenorBalMonths = tenorBalMonths;
	}
	/**
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}
	/**
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}
	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}
	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	/**
	 * @return the createdSysDate
	 */
	public Date getCreatedSysDate() {
		return createdSysDate;
	}
	/**
	 * @param createdSysDate the createdSysDate to set
	 */
	public void setCreatedSysDate(Date createdSysDate) {
		this.createdSysDate = createdSysDate;
	}
	/**
	 * @return the updatedBy
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}
	/**
	 * @param updatedBy the updatedBy to set
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	/**
	 * @return the updatedDate
	 */
	public Date getUpdatedDate() {
		return updatedDate;
	}
	/**
	 * @param updatedDate the updatedDate to set
	 */
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	/**
	 * @return the updatedSysDate
	 */
	public Date getUpdatedSysDate() {
		return updatedSysDate;
	}
	/**
	 * @param updatedSysDate the updatedSysDate to set
	 */
	public void setUpdatedSysDate(Date updatedSysDate) {
		this.updatedSysDate = updatedSysDate;
	}
	public String getCheckRow() {
		return checkRow;
	}
	public void setCheckRow(String checkRow) {
		this.checkRow = checkRow;
	}
	public String getFacility() {
		return facility;
	}
	public void setFacility(String facility) {
		this.facility = facility;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}	
}