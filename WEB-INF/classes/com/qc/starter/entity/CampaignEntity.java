package com.qc.starter.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="QM_CAMPAIGN", schema="QC_PROSPECT_MASTER")
public class CampaignEntity {
	
	@Id
	@Column(name="CAMPAIGN_ID")			
	private	String	campaignId	;
	@Column(name="CAMPAIGN_CD")			
	private	String	campaignCd	;
	@Column(name="CAMPAIGN_NAME")			
	private	String	campaignName	;
	@Column(name="CAMPAIGN_START_DT")			
	private	String campaignStartDt	;
	@Column(name="CAMPAIGN_END_DT")			
	private	String campaignEndDt	;
	private	String	remarks	;
	private	String active	;
	@Column(name="COMPANY_ID")			
	private	String	companyId	;
	private	String	createdBy	;
	@Column(name="CREATED_DATETIME")			
	private	String	createdDatetime	;
	private	String	updateBy	;
	@Column(name="UPDATE_DATETIME")			
	private	String	UpdateDatetime	;
	@Column(name="MAKER_ID")			
	private	String	MakerId	;
	@Column(name="MAKER_DATE")			
	private	String	MakerDate	;
	@Column(name="MAKER_SYSDATE")			
	private	String	MakerSysdate	;
	@Column(name="MAKER_REMARKS")			
	private	String	MakerRemarks	;
	@Column(name="AUTH_ID")			
	private	String	AuthId	;
	@Column(name="AUTH_DATE")			
	private	String	AuthDate	;
	@Column(name="AUTH_SYSDATE")			
	private	String	AuthSysdate	;
	@Column(name="AUTH_REMARK")			
	private	String	AuthRemark	;
	/**
	 * @return the campaignId
	 */
	public String getCampaignId() {
		return campaignId;
	}
	/**
	 * @param campaignId the campaignId to set
	 */
	public void setCampaignId(String campaignId) {
		this.campaignId = campaignId;
	}
	/**
	 * @return the campaignCd
	 */
	public String getCampaignCd() {
		return campaignCd;
	}
	/**
	 * @param campaignCd the campaignCd to set
	 */
	public void setCampaignCd(String campaignCd) {
		this.campaignCd = campaignCd;
	}
	/**
	 * @return the campaignName
	 */
	public String getCampaignName() {
		return campaignName;
	}
	/**
	 * @param campaignName the campaignName to set
	 */
	public void setCampaignName(String campaignName) {
		this.campaignName = campaignName;
	}
	/**
	 * @return the campaignStartDt
	 */
	public String getCampaignStartDt() {
		return campaignStartDt;
	}
	/**
	 * @param campaignStartDt the campaignStartDt to set
	 */
	public void setCampaignStartDt(String campaignStartDt) {
		this.campaignStartDt = campaignStartDt;
	}
	/**
	 * @return the campaignEndDt
	 */
	public String getCampaignEndDt() {
		return campaignEndDt;
	}
	/**
	 * @param campaignEndDt the campaignEndDt to set
	 */
	public void setCampaignEndDt(String campaignEndDt) {
		this.campaignEndDt = campaignEndDt;
	}
	/**
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}
	/**
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
		return UpdateDatetime;
	}
	/**
	 * @param updateDatetime the updateDatetime to set
	 */
	public void setUpdateDatetime(String updateDatetime) {
		UpdateDatetime = updateDatetime;
	}
	/**
	 * @return the makerId
	 */
	public String getMakerId() {
		return MakerId;
	}
	/**
	 * @param makerId the makerId to set
	 */
	public void setMakerId(String makerId) {
		MakerId = makerId;
	}
	/**
	 * @return the makerDate
	 */
	public String getMakerDate() {
		return MakerDate;
	}
	/**
	 * @param makerDate the makerDate to set
	 */
	public void setMakerDate(String makerDate) {
		MakerDate = makerDate;
	}
	/**
	 * @return the makerSysdate
	 */
	public String getMakerSysdate() {
		return MakerSysdate;
	}
	/**
	 * @param makerSysdate the makerSysdate to set
	 */
	public void setMakerSysdate(String makerSysdate) {
		MakerSysdate = makerSysdate;
	}
	/**
	 * @return the makerRemarks
	 */
	public String getMakerRemarks() {
		return MakerRemarks;
	}
	/**
	 * @param makerRemarks the makerRemarks to set
	 */
	public void setMakerRemarks(String makerRemarks) {
		MakerRemarks = makerRemarks;
	}
	/**
	 * @return the authId
	 */
	public String getAuthId() {
		return AuthId;
	}
	/**
	 * @param authId the authId to set
	 */
	public void setAuthId(String authId) {
		AuthId = authId;
	}
	/**
	 * @return the authDate
	 */
	public String getAuthDate() {
		return AuthDate;
	}
	/**
	 * @param authDate the authDate to set
	 */
	public void setAuthDate(String authDate) {
		AuthDate = authDate;
	}
	/**
	 * @return the authSysdate
	 */
	public String getAuthSysdate() {
		return AuthSysdate;
	}
	/**
	 * @param authSysdate the authSysdate to set
	 */
	public void setAuthSysdate(String authSysdate) {
		AuthSysdate = authSysdate;
	}
	/**
	 * @return the authRemark
	 */
	public String getAuthRemark() {
		return AuthRemark;
	}
	/**
	 * @param authRemark the authRemark to set
	 */
	public void setAuthRemark(String authRemark) {
		AuthRemark = authRemark;
	}
	
	


}
