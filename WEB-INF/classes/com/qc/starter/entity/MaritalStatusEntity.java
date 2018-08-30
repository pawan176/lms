package com.qc.starter.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="QM_MARITALSTATUS", schema="QC_PROSPECT_MASTER")
public class MaritalStatusEntity {
	
	@Id
	private	String	maritalStatusid	;
	private	String	maritalStatuscode	;
	private	String	maritalStatusname	;
	private	String	displayName	;
	private	String	description	;
	private	String	createdBy	;
	@Column(name="CREATED_DATETIME")			
	private	Date	createdDatetime	;
	private	String	updateBy	;
	@Column(name="UPDATE_DATETIME")			
	private	Date	updateDatetime	;
	private	String	active	;
	@Column(name="MAKER_ID")			
	private	String	makerId	;
	@Column(name="MAKER_DATE")			
	private	Date	makerDate	;
	@Column(name="MAKER_REMARKS")			
	private	String	makerRemarks	;
	@Column(name="MAKER_SYSDATE")			
	private	Date	makerSysdate	;
	@Column(name="AUTH_ID")			
	private	String	authId	;
	@Column(name="AUTH_DATE")			
	private	Date	authDate	;
	@Column(name="AUTH_REMARK")			
	private	String	authRemark	;
	@Column(name="AUTH_SYSDATE")			
	private	Date	auth_Sysdate	;
	/**
	 * @return the maritalStatusid
	 */
	public String getMaritalStatusid() {
		return maritalStatusid;
	}
	/**
	 * @param maritalStatusid the maritalStatusid to set
	 */
	public void setMaritalStatusid(String maritalStatusid) {
		this.maritalStatusid = maritalStatusid;
	}
	/**
	 * @return the maritalStatuscode
	 */
	public String getMaritalStatuscode() {
		return maritalStatuscode;
	}
	/**
	 * @param maritalStatuscode the maritalStatuscode to set
	 */
	public void setMaritalStatuscode(String maritalStatuscode) {
		this.maritalStatuscode = maritalStatuscode;
	}
	/**
	 * @return the maritalStatusname
	 */
	public String getMaritalStatusname() {
		return maritalStatusname;
	}
	/**
	 * @param maritalStatusname the maritalStatusname to set
	 */
	public void setMaritalStatusname(String maritalStatusname) {
		this.maritalStatusname = maritalStatusname;
	}
	/**
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}
	/**
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
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
	 * @return the updateBy
	 */
	public String getUpdateBy() {
		return updateBy;
	}
	/**
	 * @param updateBy the updateBy to set
	 */
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
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
	 * @return the auth_Sysdate
	 */
	public Date getAuth_Sysdate() {
		return auth_Sysdate;
	}
	/**
	 * @param auth_Sysdate the auth_Sysdate to set
	 */
	public void setAuth_Sysdate(Date auth_Sysdate) {
		this.auth_Sysdate = auth_Sysdate;
	}
	
	
}
