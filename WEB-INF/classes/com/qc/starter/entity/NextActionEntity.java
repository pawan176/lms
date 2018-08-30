package com.qc.starter.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="QM_CASE_NEXT_ACTION", schema="QC_PROSPECT_MASTER")
public class NextActionEntity {
	
	@Id
	@Column(name="NEXTACTION_ID")
	String nextActionId;
	
	@Column(name="NEXTACTION_CODE")
	String nextActionCode;
	
	@Column(name="NEXTACTION_NAME")
	String nextActionName;
	
	@Column(name="PARENT_ACTION_ID")
	String parentActionId;	
	
	@Column(name="ACTION_TYPE")
	String actionType;
	
	String weight;
	
	@Column(name="DISPOSITION_ACTION")
	String dispositionAction;
		
	String days;
	
	@Column(name="ACTION_STAGE")
	String actionStage;
	
	@Column(name="COMPANY_ID")
	String companyId;
	
	String active;
	
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

	public String getNextActionId() {
		return nextActionId;
	}

	public void setNextActionId(String nextActionId) {
		this.nextActionId = nextActionId;
	}

	public String getNextActionCode() {
		return nextActionCode;
	}

	public void setNextActionCode(String nextActionCode) {
		this.nextActionCode = nextActionCode;
	}

	public String getNextActionName() {
		return nextActionName;
	}

	public void setNextActionName(String nextActionName) {
		this.nextActionName = nextActionName;
	}

	public String getParentActionId() {
		return parentActionId;
	}

	public void setParentActionId(String parentActionId) {
		this.parentActionId = parentActionId;
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

	public String getDispositionAction() {
		return dispositionAction;
	}

	public void setDispositionAction(String dispositionAction) {
		this.dispositionAction = dispositionAction;
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

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
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