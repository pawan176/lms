package com.qc.starter.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="QT_CASE_USER_ACTION", schema="QC_PROSPECT")
public class CaseActionHistoryEntity {
	@Id
	@Column(name="CASEUSERACTION_ID")
	String caseuseractionId;
	@Column(name="CASE_ID")
	String caseId;
	@Column(name="CASEXUSER_ID")
	String casexuserId;
	@Column(name="ACTION_ID")
	String actionId;
	@Column(name="FOLLOW_DT_TIME")
	String followDtTime;
	@Column(name="REMARKS")
	String remarks;
	@Column(name="CREATED_DATE")
	Date createdDate;
	@Column(name="CREATED_SYS_DATE")
	Date createdSysDate;
	public String getCaseuseractionId() {
		return caseuseractionId;
	}
	public void setCaseuseractionId(String caseuseractionId) {
		this.caseuseractionId = caseuseractionId;
	}
	public String getCaseId() {
		return caseId;
	}
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	public String getCasexuserId() {
		return casexuserId;
	}
	public void setCasexuserId(String casexuserId) {
		this.casexuserId = casexuserId;
	}
	public String getActionId() {
		return actionId;
	}
	public void setActionId(String actionId) {
		this.actionId = actionId;
	}
	public String getFollowDtTime() {
		return followDtTime;
	}
	public void setFollowDtTime(String followDtTime) {
		this.followDtTime = followDtTime;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getCreatedSysDate() {
		return createdSysDate;
	}
	public void setCreatedSysDate(Date createdSysDate) {
		this.createdSysDate = createdSysDate;
	}
	
	
	

}
