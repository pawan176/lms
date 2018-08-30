package com.qc.starter.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="QM_ADDRESSTYPE", schema="QC_PROSPECT_MASTER")
public class AddressTypeEntity {
	
	@Id
	private	String	addressTypeId	;
	private	String	addressTypeCode	;
	private	String	addressTypeName	;
	private	String	addressTypeDisplayName	;
	private	String	description	;
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
	private	String	makerSysDate	;
	@Column(name="AUTH_ID")			
	private	String	authId	;
	@Column(name="AUTH_DATE")			
	private	String	authDate	;
	@Column(name="AUTH_REMARK")			
	private	String	authRemark	;
	@Column(name="AUTH_SYSDATE")			
	private	String	authSysDate	;
	@Column(name="COMPANY_ID")			
	private	String	companyId	;
	/**
	 * @return the addressTypeId
	 */
	public String getAddressTypeId() {
		return addressTypeId;
	}
	/**
	 * @param addressTypeId the addressTypeId to set
	 */
	public void setAddressTypeId(String addressTypeId) {
		this.addressTypeId = addressTypeId;
	}
	/**
	 * @return the addressTypeCode
	 */
	public String getAddressTypeCode() {
		return addressTypeCode;
	}
	/**
	 * @param addressTypeCode the addressTypeCode to set
	 */
	public void setAddressTypeCode(String addressTypeCode) {
		this.addressTypeCode = addressTypeCode;
	}
	/**
	 * @return the addressTypeName
	 */
	public String getAddressTypeName() {
		return addressTypeName;
	}
	/**
	 * @param addressTypeName the addressTypeName to set
	 */
	public void setAddressTypeName(String addressTypeName) {
		this.addressTypeName = addressTypeName;
	}
	/**
	 * @return the addressTypeDisplayName
	 */
	public String getAddressTypeDisplayName() {
		return addressTypeDisplayName;
	}
	/**
	 * @param addressTypeDisplayName the addressTypeDisplayName to set
	 */
	public void setAddressTypeDisplayName(String addressTypeDisplayName) {
		this.addressTypeDisplayName = addressTypeDisplayName;
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
	 * @return the makerSysDate
	 */
	public String getMakerSysDate() {
		return makerSysDate;
	}
	/**
	 * @param makerSysDate the makerSysDate to set
	 */
	public void setMakerSysDate(String makerSysDate) {
		this.makerSysDate = makerSysDate;
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
	 * @return the authSysDate
	 */
	public String getAuthSysDate() {
		return authSysDate;
	}
	/**
	 * @param authSysDate the authSysDate to set
	 */
	public void setAuthSysDate(String authSysDate) {
		this.authSysDate = authSysDate;
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
