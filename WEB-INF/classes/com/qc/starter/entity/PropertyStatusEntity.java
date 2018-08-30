package com.qc.starter.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="QM_PROPERTY_STATUS", schema="QC_PROSPECT_MASTER")
public class PropertyStatusEntity {
	
	@Id
	@Column(name="PROP_STATUS_ID")
	private int propStatusId;
	@Column(name="PROP_STATUS_CODE")
	private String propStatusCode;
	@Column(name="PROP_STATUS_NAME")
	private String propStatusName;
	private	String	Createdby	;
	@Column(name="CREATED_DATETIME")			
	private	String	createdDatetime	;
	private	String	Updateby	;
	@Column(name="UPDATE_DATETIME")			
	private	String	updateDatetime	;
	private	String	active	;
	@Column(name="MAKER_ID")			
	private	String	makerId	;
	@Column(name="MAKER_DATE")			
	private	String	makerDate	;
	@Column(name="MAKER_REMARKS")			
	private	String	makerRemarks	;
	@Column(name="MAKER_SYSDATE")			
	private	String	makerSysdate	;
	@Column(name="AUTH_ID")			
	private	String	authId	;
	@Column(name="AUTH_DATE")			
	private	String	authDate	;
	@Column(name="AUTH_REMARK")			
	private	String	authRemark	;
	@Column(name="AUTH_SYSDATE ")			
	private	String	authSysdate	;
	@Column(name="COMPANY_ID")			
	private	String	companyId	;
	/**
	 * @return the propStatusId
	 */
	public int getPropStatusId() {
		return propStatusId;
	}
	/**
	 * @param propStatusId the propStatusId to set
	 */
	public void setPropStatusId(int propStatusId) {
		this.propStatusId = propStatusId;
	}
	/**
	 * @return the propStatusCode
	 */
	public String getPropStatusCode() {
		return propStatusCode;
	}
	/**
	 * @param propStatusCode the propStatusCode to set
	 */
	public void setPropStatusCode(String propStatusCode) {
		this.propStatusCode = propStatusCode;
	}
	/**
	 * @return the propStatusName
	 */
	public String getPropStatusName() {
		return propStatusName;
	}
	/**
	 * @param propStatusName the propStatusName to set
	 */
	public void setPropStatusName(String propStatusName) {
		this.propStatusName = propStatusName;
	}
	/**
	 * @return the createdby
	 */
	public String getCreatedby() {
		return Createdby;
	}
	/**
	 * @param createdby the createdby to set
	 */
	public void setCreatedby(String createdby) {
		Createdby = createdby;
	}
	/**
	 * @return the createdDatetime
	 */
	public String getCreatedDatetime() {
		return createdDatetime;
	}
	/**
	 * @param createdDatetime the createdDatetime to set
	 */
	public void setCreatedDatetime(String createdDatetime) {
		this.createdDatetime = createdDatetime;
	}
	/**
	 * @return the updateby
	 */
	public String getUpdateby() {
		return Updateby;
	}
	/**
	 * @param updateby the updateby to set
	 */
	public void setUpdateby(String updateby) {
		Updateby = updateby;
	}
	/**
	 * @return the updateDatetime
	 */
	public String getUpdateDatetime() {
		return updateDatetime;
	}
	/**
	 * @param updateDatetime the updateDatetime to set
	 */
	public void setUpdateDatetime(String updateDatetime) {
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
	public String getMakerDate() {
		return makerDate;
	}
	/**
	 * @param makerDate the makerDate to set
	 */
	public void setMakerDate(String makerDate) {
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
	public String getMakerSysdate() {
		return makerSysdate;
	}
	/**
	 * @param makerSysdate the makerSysdate to set
	 */
	public void setMakerSysdate(String makerSysdate) {
		this.makerSysdate = makerSysdate;
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
	public String getAuthDate() {
		return authDate;
	}
	/**
	 * @param authDate the authDate to set
	 */
	public void setAuthDate(String authDate) {
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
	public String getAuthSysdate() {
		return authSysdate;
	}
	/**
	 * @param authSysdate the authSysdate to set
	 */
	public void setAuthSysdate(String authSysdate) {
		this.authSysdate = authSysdate;
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
	
	

}
