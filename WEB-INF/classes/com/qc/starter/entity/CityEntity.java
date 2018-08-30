package com.qc.starter.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="QM_CITYMASTER",schema="QC_PROSPECT_MASTER")
public class CityEntity {
	
	@Id
	private	String	cityMasterId	;
	private	String	cityMasterCode	;
	private	String	cityMasterName	;
	private	String	displayName	;
	private	String	description	;
	private	String	stateId	;
	private	String	createdBy	;
	@Column(name="CREATED_DATETIME")			
	private	String	createdDatetime	;
	private	String	updateBy	;
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
	@Column(name="AUTH_SYSDATE")			
	private	String	auth_Sysdate	;
	/**
	 * @return the cityMasterId
	 */
	public String getCityMasterId() {
		return cityMasterId;
	}
	/**
	 * @param cityMasterId the cityMasterId to set
	 */
	public void setCityMasterId(String cityMasterId) {
		this.cityMasterId = cityMasterId;
	}
	/**
	 * @return the cityMasterCode
	 */
	public String getCityMasterCode() {
		return cityMasterCode;
	}
	/**
	 * @param cityMasterCode the cityMasterCode to set
	 */
	public void setCityMasterCode(String cityMasterCode) {
		this.cityMasterCode = cityMasterCode;
	}
	/**
	 * @return the cityMasterName
	 */
	public String getCityMasterName() {
		return cityMasterName;
	}
	/**
	 * @param cityMasterName the cityMasterName to set
	 */
	public void setCityMasterName(String cityMasterName) {
		this.cityMasterName = cityMasterName;
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
	 * @return the stateId
	 */
	public String getStateId() {
		return stateId;
	}
	/**
	 * @param stateId the stateId to set
	 */
	public void setStateId(String stateId) {
		this.stateId = stateId;
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
	 * @return the auth_Sysdate
	 */
	public String getAuth_Sysdate() {
		return auth_Sysdate;
	}
	/**
	 * @param auth_Sysdate the auth_Sysdate to set
	 */
	public void setAuth_Sysdate(String auth_Sysdate) {
		this.auth_Sysdate = auth_Sysdate;
	}
	
	
}
