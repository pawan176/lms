package com.qc.starter.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="QM_PRODUCTTYPE", schema="QC_PROSPECT_MASTER")
public class ProductTypeEntity {

	@Id
	@Column(name="PRODTYPEID")			
	private	String	prodTypeId	;
	@Column(name="PRODTYPECODE ")			
	private	String	prodTypeCode	;
	@Column(name="PRODTYPE ")			
	private	String	prodTypeName	;
	@Column(name="PRODTYPEDISPLAYNAME")			
	private	String	displayName	;
	//private	String	remarks	;
	private	String	createdBy	;
	@Column(name="CREATED_DATETIME")			
	private	String	createdDatetime	;
	private	String	updateBy	;
	@Column(name="UPDATE_DATETIME")			
	private	String	updateDatetime	;
	private	String	active	;
	@Column(name="COMPANY_ID")			
	private	String	companyId	;
	@Column(name="MAKER_ID")			
	private	String	makerId	;
	@Column(name="MAKER_DATE")			
	private	String	makerDate	;
	@Column(name="MAKER_SYSDATE")			
	private	String	makerSysdate	;
	@Column(name="MAKER_REMARKS")			
	private	String	makerRemarks	;
	@Column(name="AUTH_ID")			
	private	String	authId	;
	@Column(name="AUTH_DATE")			
	private	String	authDate	;
	@Column(name="AUTH_SYSDATE")			
	private	String	authSysdate	;
	@Column(name="AUTH_REMARK")			
	private	String	authRemark	;
	public String getProdTypeId() {
		return prodTypeId;
	}
	public void setProdTypeId(String prodTypeId) {
		this.prodTypeId = prodTypeId;
	}
	public String getProdTypeCode() {
		return prodTypeCode;
	}
	public void setProdTypeCode(String prodTypeCode) {
		this.prodTypeCode = prodTypeCode;
	}
	public String getProdTypeName() {
		return prodTypeName;
	}
	public void setProdTypeName(String prodTypeName) {
		this.prodTypeName = prodTypeName;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	/*public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}*/
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getCreatedDatetime() {
		return createdDatetime;
	}
	public void setCreatedDatetime(String createdDatetime) {
		this.createdDatetime = createdDatetime;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public String getUpdateDatetime() {
		return updateDatetime;
	}
	public void setUpdateDatetime(String updateDatetime) {
		this.updateDatetime = updateDatetime;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getMakerId() {
		return makerId;
	}
	public void setMakerId(String makerId) {
		this.makerId = makerId;
	}
	public String getMakerDate() {
		return makerDate;
	}
	public void setMakerDate(String makerDate) {
		this.makerDate = makerDate;
	}
	public String getMakerSysdate() {
		return makerSysdate;
	}
	public void setMakerSysdate(String makerSysdate) {
		this.makerSysdate = makerSysdate;
	}
	public String getMakerRemarks() {
		return makerRemarks;
	}
	public void setMakerRemarks(String makerRemarks) {
		this.makerRemarks = makerRemarks;
	}
	public String getAuthId() {
		return authId;
	}
	public void setAuthId(String authId) {
		this.authId = authId;
	}
	public String getAuthDate() {
		return authDate;
	}
	public void setAuthDate(String authDate) {
		this.authDate = authDate;
	}
	public String getAuthSysdate() {
		return authSysdate;
	}
	public void setAuthSysdate(String authSysdate) {
		this.authSysdate = authSysdate;
	}
	public String getAuthRemark() {
		return authRemark;
	}
	public void setAuthRemark(String authRemark) {
		this.authRemark = authRemark;
	}

	
}
