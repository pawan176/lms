package com.qc.starter.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="QM_CONTACT_TYPE", schema="QC_PROSPECT_MASTER")
public class ContactTypeEntity {
	
	@Id
	@Column(name="CONTACT_TYPE_ID")			
	private	Integer	contactTypeId	;
	@Column(name="CONTACT_TYPE_CODE")			
	private	String	contactTypeCode	;
	@Column(name="CONTACT_TYPE_NAME")			
	private	String	contactTypeName	;
	@Column(name="CONTACT_CATEGORY")			
	private	String	contactCategory	;
	private	Integer	createdBy	;
	@Column(name="CREATED_DATETIME")			
	private	Date	createdDatetime	;
	private	Integer	updateBy	;
	@Column(name="UPDATE_DATETIME")			
	private	Date	updateDatetime	;
	private	char	active	;
	@Column(name="COMPANY_ID")			
	private	Integer	companyId	;
	@Column(name="MAKER_ID")			
	private	Integer	makerId	;
	@Column(name="MAKER_DATE")			
	private	Date	makerDate	;
	@Column(name="MAKER_SYSDATE")			
	private	Date	makerSysdate	;
	@Column(name="MAKER_REMARKS")			
	private	String	makerRemarks	;
	@Column(name="AUTH_ID")			
	private	Integer	authId	;
	@Column(name="AUTH_DATE")			
	private	Date	authDate	;
	@Column(name="AUTH_SYSDATE")			
	private	Date	authSysdate	;
	@Column(name="AUTH_REMARK")			
	private	String	authRemark	;
	/**
	 * @return the contactTypeId
	 */
	public Integer getContactTypeId() {
		return contactTypeId;
	}
	/**
	 * @param contactTypeId the contactTypeId to set
	 */
	public void setContactTypeId(Integer contactTypeId) {
		this.contactTypeId = contactTypeId;
	}
	/**
	 * @return the contactTypeCode
	 */
	public String getContactTypeCode() {
		return contactTypeCode;
	}
	/**
	 * @param contactTypeCode the contactTypeCode to set
	 */
	public void setContactTypeCode(String contactTypeCode) {
		this.contactTypeCode = contactTypeCode;
	}
	/**
	 * @return the contactTypeName
	 */
	public String getContactTypeName() {
		return contactTypeName;
	}
	/**
	 * @param contactTypeName the contactTypeName to set
	 */
	public void setContactTypeName(String contactTypeName) {
		this.contactTypeName = contactTypeName;
	}
	/**
	 * @return the contactCategory
	 */
	public String getContactCategory() {
		return contactCategory;
	}
	/**
	 * @param contactCategory the contactCategory to set
	 */
	public void setContactCategory(String contactCategory) {
		this.contactCategory = contactCategory;
	}
	/**
	 * @return the createdBy
	 */
	public Integer getCreatedBy() {
		return createdBy;
	}
	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(Integer createdBy) {
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
	public Integer getUpdateBy() {
		return updateBy;
	}
	/**
	 * @param updateBy the updateBy to set
	 */
	public void setUpdateBy(Integer updateBy) {
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
	public char getActive() {
		return active;
	}
	/**
	 * @param active the active to set
	 */
	public void setActive(char active) {
		this.active = active;
	}
	/**
	 * @return the companyId
	 */
	public Integer getCompanyId() {
		return companyId;
	}
	/**
	 * @param companyId the companyId to set
	 */
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	/**
	 * @return the makerId
	 */
	public Integer getMakerId() {
		return makerId;
	}
	/**
	 * @param makerId the makerId to set
	 */
	public void setMakerId(Integer makerId) {
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
	public Integer getAuthId() {
		return authId;
	}
	/**
	 * @param authId the authId to set
	 */
	public void setAuthId(Integer authId) {
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
