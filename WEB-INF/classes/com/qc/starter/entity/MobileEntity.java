package com.qc.starter.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="QT_CASE_MOBILE", schema="QC_PROSPECT")
public class MobileEntity {
	
	@Id
	@Column(name="CASE_MOBILE_ID")			
	private	String	caseMobileId	;
	@Column(name="CASE_ID")			
	private	String	caseId	;
	@Column(name="CONTACT_TYPE_ID")			
	private	String	mobileContactTypeId	;
	@Column(name="CONTACT_NO")			
	private	String	contactNo	;
	@Column(name="PRIMARY_CONTACT")			
	private	String	primaryContact	;
	@Column(name="DND_FLAG")			
	private	String	dndFlag	;
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
	/**
	 * @return the caseMobileId
	 */
	public String getCaseMobileId() {
		return caseMobileId;
	}
	/**
	 * @param caseMobileId the caseMobileId to set
	 */
	public void setCaseMobileId(String caseMobileId) {
		this.caseMobileId = caseMobileId;
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
	 * @return the contactTypeId
	 */
	public String getMobileContactTypeId() {
		return mobileContactTypeId;
	}
	/**
	 * @param contactTypeId the contactTypeId to set
	 */
	public void setMobileContactTypeId(String mobileContactTypeId) {
		this.mobileContactTypeId = mobileContactTypeId;
	}
	/**
	 * @return the contactNo
	 */
	public String getContactNo() {
		return contactNo;
	}
	/**
	 * @param contactNo the contactNo to set
	 */
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	/**
	 * @return the primaryContact
	 */
	public String getPrimaryContact() {
		return primaryContact;
	}
	/**
	 * @param primaryContact the primaryContact to set
	 */
	public void setPrimaryContact(String primaryContact) {
		this.primaryContact = primaryContact;
	}
	/**
	 * @return the dndFlag
	 */
	public String getDndFlag() {
		return dndFlag;
	}
	/**
	 * @param dndFlag the dndFlag to set
	 */
	public void setDndFlag(String dndFlag) {
		this.dndFlag = dndFlag;
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
