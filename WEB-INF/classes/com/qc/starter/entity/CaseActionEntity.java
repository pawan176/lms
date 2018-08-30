package com.qc.starter.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="QM_CASE_ACTION", schema="QC_PROSPECT_MASTER")
public class CaseActionEntity {
	@Id
	@Column(name="ACTION_ID")
	String actionId;
	@Column(name="ACTION_CODE")
	String actionCode;
	@Column(name="ACTION_NAME")
	String actionName;
	@Column(name="ACTION_TYPE")
	String actionType;
	String weight;
	String action;
	String days;
	@Column(name="ACTION_STAGE")
	String actionStage;
	String active;
	@Column(name="COMPANY_ID")
	String companyId;
	String createdby;
	@Column(name="CREATED_DATETIME")
	Date createdDatetime;
	String updateby;	
	@Column(name="UPDATE_DATETIME")
	Date updateDatetime;
	@Column(name="MAKER_ID")
	String makerId;
	@Column(name="MAKER_DATE")
	Date makerDate;
	@Column(name="MAKER_SYSDATE")
	Date makerSysdate;
	@Column(name="MAKER_REMARKS")
	String makerRemarks;
	@Column(name="AUTH_ID")
	String authId;
	@Column(name="AUTH_DATE")
	Date authDate;
	@Column(name="AUTH_SYSDATE")
	Date authSysdate;
	@Column(name="AUTH_REMARK")
	String authRemark;
	
	public String getActionId() {
		return actionId;
	}
	public void setActionId(String actionId) {
		this.actionId = actionId;
	}
	public String getActionCode() {
		return actionCode;
	}
	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public String getActionType() {
		return actionType;
	}
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
	public String getActionStage() {
		return actionStage;
	}
	public void setActionStage(String actionStage) {
		this.actionStage = actionStage;
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
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
	public Date getCreatedDatetime() {
		return createdDatetime;
	}
	public void setCreatedDatetime(Date createdDatetime) {
		this.createdDatetime = createdDatetime;
	}
	public String getUpdateby() {
		return updateby;
	}
	public void setUpdateby(String updateby) {
		this.updateby = updateby;
	}
	public Date getUpdateDatetime() {
		return updateDatetime;
	}
	public void setUpdateDatetime(Date updateDatetime) {
		this.updateDatetime = updateDatetime;
	}
	public String getMakerId() {
		return makerId;
	}
	public void setMakerId(String makerId) {
		this.makerId = makerId;
	}
	public Date getMakerDate() {
		return makerDate;
	}
	public void setMakerDate(Date makerDate) {
		this.makerDate = makerDate;
	}
	public Date getMakerSysdate() {
		return makerSysdate;
	}
	public void setMakerSysdate(Date makerSysdate) {
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
	public Date getAuthDate() {
		return authDate;
	}
	public void setAuthDate(Date authDate) {
		this.authDate = authDate;
	}
	public Date getAuthSysdate() {
		return authSysdate;
	}
	public void setAuthSysdate(Date authSysdate) {
		this.authSysdate = authSysdate;
	}
	public String getAuthRemark() {
		return authRemark;
	}
	public void setAuthRemark(String authRemark) {
		this.authRemark = authRemark;
	}
}