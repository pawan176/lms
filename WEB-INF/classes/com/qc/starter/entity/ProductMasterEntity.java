package com.qc.starter.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="QM_PRODUCT", schema="QC_PROSPECT_MASTER")
public class ProductMasterEntity {

	@Id
	@Column(name = "PRODUCTID")
	private String prodId;
	@Column(name = "PRODCODE")
	private String prodCode;
	@Column(name = "PRODNAME")
	private String prodName;
	@Column(name = "PARENTPRODID")
	private String parentProdId;
	@Column(name = "PRODTYPEID")
	private String prodTypeId;
	private String createdby;
	@Column(name = "CREATED_DATETIME")
	private String createdDatetime;
	private String updateby;
	@Column(name = "UPDATE_DATETIME")
	private String updateDatetime;
	private String active;
	@Column(name = "MAKER_ID")
	private String makerId;
	@Column(name = "MAKER_DATE")
	private String makerDate;
	@Column(name = "MAKER_REMARKS")
	private String makerRemarks;
	@Column(name = "MAKER_SYSDATE")
	private String makerSysdate;
	@Column(name = "AUTH_ID")
	private String authId;
	@Column(name = "AUTH_DATE")
	private String authDate;
	@Column(name = "AUTH_REMARK")
	private String authRemark;
	@Column(name = "AUTH_SYSDATE ")
	private String authSysdate;
	@Column(name = "COMPANY_ID")
	private String companyId;
	@Column(name="MIN_LOAN_TENURE")	
	private	String	minLoanTenure	;
	@Column(name="MAX_LOAN_TENURE")			
	private	String	maxLoanTenure	;
	@Column(name="MIN_LOANTOVALUE")			
	private	String	minLoantovalue	;
	@Column(name="MAX_LOANTOVALUE")			
	private	String	maxLoantovalue	;	
	@Column(name="MIN_LOANVALUE")			
	private	String	minLoanvalue	;	
	@Column(name="MAX_LOANVALUE")			
	private	String maxLoanvalue;
	
	public String getMinLoanvalue() {
		return minLoanvalue;
	}
	public void setMinLoanvalue(String minLoanvalue) {
		this.minLoanvalue = minLoanvalue;
	}
	public String getMaxLoanvalue() {
		return maxLoanvalue;
	}
	public void setMaxLoanvalue(String maxLoanvalue) {
		this.maxLoanvalue = maxLoanvalue;
	}
	public String getMinLoanTenure() {
		return minLoanTenure;
	}
	public void setMinLoanTenure(String minLoanTenure) {
		this.minLoanTenure = minLoanTenure;
	}
	public String getMaxLoanTenure() {
		return maxLoanTenure;
	}
	public void setMaxLoanTenure(String maxLoanTenure) {
		this.maxLoanTenure = maxLoanTenure;
	}
	public String getMinLoantovalue() {
		return minLoantovalue;
	}
	public void setMinLoantovalue(String minLoantovalue) {
		this.minLoantovalue = minLoantovalue;
	}
	public String getMaxLoantovalue() {
		return maxLoantovalue;
	}
	public void setMaxLoantovalue(String maxLoantovalue) {
		this.maxLoantovalue = maxLoantovalue;
	}
	/**
	 * @return the prodId
	 */
	public String getProdId() {
		return prodId;
	}
	/**
	 * @param prodId the prodId to set
	 */
	public void setProdId(String prodId) {
		this.prodId = prodId;
	}
	/**
	 * @return the prodCode
	 */
	public String getProdCode() {
		return prodCode;
	}
	/**
	 * @param prodCode the prodCode to set
	 */
	public void setProdCode(String prodCode) {
		this.prodCode = prodCode;
	}
	/**
	 * @return the prodName
	 */
	public String getProdName() {
		return prodName;
	}
	/**
	 * @param prodName the prodName to set
	 */
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	/**
	 * @return the parentProdId
	 */
	public String getParentProdId() {
		return parentProdId;
	}
	/**
	 * @param parentProdId the parentProdId to set
	 */
	public void setParentProdId(String parentProdId) {
		this.parentProdId = parentProdId;
	}
	/**
	 * @return the prodTypeId
	 */
	public String getProdTypeId() {
		return prodTypeId;
	}
	/**
	 * @param prodTypeId the prodTypeId to set
	 */
	public void setProdTypeId(String prodTypeId) {
		this.prodTypeId = prodTypeId;
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
