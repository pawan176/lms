package com.qc.starter.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="QT_CASE_EMAIL", schema="QC_PROSPECT")
public class EmailEntity {

	@Id
	@Column(name="CASE_EMAIL_ID")			
	private	String	caseEmailId	;
	@Column(name="CASE_ID")			
	private	String	caseId	;
	@Column(name="CONTACT_TYPE_ID")			
	private	String	emailContactTypeId	;
	private	String	email	;
	@Column(name="PRIMARY_EMAIL")			
	private	String	primaryEmail	;
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
	@Column(name="UPDATED_SYS_DATE ")			
	private	Date	updatedSysDate	;
	/**
	 * @return the caseEmailId
	 */
	public String getCaseEmailId() {
		return caseEmailId;
	}
	/**
	 * @param caseEmailId the caseEmailId to set
	 */
	public void setCaseEmailId(String caseEmailId) {
		this.caseEmailId = caseEmailId;
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
	public String getEmailContactTypeId() {
		return emailContactTypeId;
	}
	/**
	 * @param contactTypeId the contactTypeId to set
	 */
	public void setEmailContactTypeId(String emailContactTypeId) {
		this.emailContactTypeId = emailContactTypeId;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the primaryEmail
	 */
	public String getPrimaryEmail() {
		return primaryEmail;
	}
	/**
	 * @param primaryEmail the primaryEmail to set
	 */
	public void setPrimaryEmail(String primaryEmail) {
		this.primaryEmail = primaryEmail;
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
