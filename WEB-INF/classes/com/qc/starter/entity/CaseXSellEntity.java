package com.qc.starter.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="QT_CASE_XSELL", schema="QC_PROSPECT")
public class CaseXSellEntity {	
	@Id
	@Column(name="XSELL_ID")			
	private	String	xsellId	;
	@Column(name="PARENT_CASE_ID")			
	private	String	parentCaseId	;
	@Column(name="FACILITY_REQ_ID")			
	private	String	facilityReqId	;
	@Column(name="BANK_ID")			
	private	String	bankId	;
	private	String	amount	;
	@Column(name="IS_XSOLD")			
	private	String	isXsold	;
	private	String	remarks	;
	@Column(name="CREATED_BY")			
	private	String	createdBy	;
	@Column(name="CREATED_DATE")			
	private	Date	createdDate	;
	@Column(name="CREATED_SYS_DATE")			
	private	Date	createdSysDate	;
	@Column(name="UPDATED_BY")			
	private	String	updatedBy	;
	@Column(name="UPDATED_DATE")			
	private	Date	updatedDate	;
	@Column(name="UPDATED_SYS_DATE")			
	private	Date	updatedSysDate	;
	@Column(name="CASE_ID")
	private String caseId;
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
	 * @return the xsellId
	 */
	public String getXsellId() {
		return xsellId;
	}
	/**
	 * @param xsellId the xsellId to set
	 */
	public void setXsellId(String xsellId) {
		this.xsellId = xsellId;
	}
	/**
	 * @return the parentCaseId
	 */
	public String getParentCaseId() {
		return parentCaseId;
	}
	/**
	 * @param parentCaseId the parentCaseId to set
	 */
	public void setParentCaseId(String parentCaseId) {
		this.parentCaseId = parentCaseId;
	}
	/**
	 * @return the facilityReqId
	 */
	public String getFacilityReqId() {
		return facilityReqId;
	}
	/**
	 * @param facilityReqId the facilityReqId to set
	 */
	public void setFacilityReqId(String facilityReqId) {
		this.facilityReqId = facilityReqId;
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
	 * @return the amount
	 */
	public String getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(String amount) {
		this.amount = amount;
	}
	/**
	 * @return the isXsold
	 */
	public String getIsXsold() {
		return isXsold;
	}
	/**
	 * @param isXsold the isXsold to set
	 */
	public void setIsXsold(String isXsold) {
		this.isXsold = isXsold;
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
}