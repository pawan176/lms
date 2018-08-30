package com.qc.starter.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="QM_SOURCE", schema="QC_PROSPECT_MASTER")
public class SourceEntity {
	
	@Id
	@Column(name="CASE_SOURCE_ID")			
	private	String	caseSourceId	;
	@Column(name="SOURCE_CODE")			
	private	String	sourceCode	;
	@Column(name="SOURCE_NAME")			
	private	String	sourceName	;
	private	String	createdby	;
	@Column(name="CREATED_DATETIME")			
	private	Date	createdDatetime	;
	private	String	updateby	;
	@Column(name="UPDATE_DATETIME")			
	private	Date	updateDatetime	;
	private	String	active	;
	@Column(name="COMPANY_ID")			
	private	String	companyId	;
	@Column(name="MAKER_ID")			
	private	String	makerId	;
	@Column(name="MAKER_DATE")			
	private	Date	makerDate	;
	@Column(name="MAKER_SYSDATE")			
	private	Date	makerSysdate	;
	@Column(name="MAKER_REMARKS")			
	private	String	makerRemarks	;
	@Column(name="AUTH_ID")			
	private	String	authId	;
	@Column(name="AUTH_DATE")			
	private	Date	authDate	;
	@Column(name="AUTH_SYSDATE")			
	private	Date	authSysdate	;
	@Column(name="AUTH_REMARK")			
	private	String	authRemark	;
	/**
	 * @return the caseSourceId
	 */
	public String getCaseSourceId() {
		return caseSourceId;
	}
	/**
	 * @param caseSourceId the caseSourceId to set
	 */
	public void setCaseSourceId(String caseSourceId) {
		this.caseSourceId = caseSourceId;
	}
	/**
	 * @return the sourceCode
	 */
	public String getSourceCode() {
		return sourceCode;
	}
	/**
	 * @param sourceCode the sourceCode to set
	 */
	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}
	/**
	 * @return the sourceName
	 */
	public String getSourceName() {
		return sourceName;
	}
	/**
	 * @param sourceName the sourceName to set
	 */
	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}
	/**
	 * @return the createdby
	 */
	public String getCreatedby() {
		return createdby;
	}
	/**
	 * @param createdby the createdby to set
	 */
	public void setCreatedby(String createdby) {
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
	 * @return the updateby
	 */
	public String getUpdateby() {
		return updateby;
	}
	/**
	 * @param updateby the updateby to set
	 */
	public void setUpdateby(String updateby) {
		this.updateby = updateby;
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
	 * @return the companyId
	 */
	public String getCompanyId() {
		return companyId;
	}
	/**
	 * @param companyId the companyId to set
	 */
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	/**
	 * @return the makerId
	 */
	public String getMakerId() {
		return makerId;
	}
	/**
	 * @param makerId the makerId to set
	 */
	public void setMakerId(String makerId) {
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
	 * @return the authId
	 */
	public String getAuthId() {
		return authId;
	}
	/**
	 * @param authId the authId to set
	 */
	public void setAuthId(String authId) {
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
	
	

}
