package com.qc.starter.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="QM_QUEUE", schema="QC_PROSPECT_MASTER")
public class QueueEntity {
	
	@Id
	@Column(name="QUEUE_ID")			
	private	String	queueId	;
	
	@Column(name="PARENT_QUEUE_ID")			
	private	String	parentQueueId	;
	
	@Column(name="QUEUE_OWNER_ID")			
	private	String	queueOwnerId	;
	
	@Column(name="WORKFLOW_ID")			
	private	String	workflowId	;
	
	private	String	priority	;
	
	@Column(name="QUEUE_NAME")			
	private	String	queueName	;
	
	@Column(name="QUEUE_SHORT_NAME")			
	private	String	queueShortName	;
	
	@Column(name="RULE_SET_ID")			
	private	String	ruleSetId	;
	
	@Column(name="MAX_CASES")			
	private	String	maxCases	;
	
	@Column(name="TOTAL_AMT_CAP")			
	private	String	totalAmtCap	;
	
	private	String	coallocation;
	
	@Column(name="IS_ACTIVE")			
	private	String	isActive	;
	
	@Column(name="MAKER_ID")			
	private	String	makerId	;
	
	@Column(name="MAKER_DATE")			
	private	Date	makerDate	;
	
	@Column(name="MAKER_REMARKS")
	private	String	makerRemarks	;
	
	@Column(name="MAKER_SYSDATE")			
	private	Date	makerSysDate	;
	
	@Column(name="AUTH_ID")			
	private	String	authId	;
	
	@Column(name="AUTH_DATE")			
	private	Date	authDate	;
	
	@Column(name="AUTH_REMARK")			
	private	String	authRemark	;
	
	/**
	 * @return the queueId
	 */
	public String getQueueId() {
		return queueId;
	}

	/**
	 * @param queueId the queueId to set
	 */
	public void setQueueId(String queueId) {
		this.queueId = queueId;
	}

	/**
	 * @return the parentQueueId
	 */
	public String getParentQueueId() {
		return parentQueueId;
	}

	/**
	 * @param parentQueueId the parentQueueId to set
	 */
	public void setParentQueueId(String parentQueueId) {
		this.parentQueueId = parentQueueId;
	}

	/**
	 * @return the queueOwnerId
	 */
	public String getQueueOwnerId() {
		return queueOwnerId;
	}

	/**
	 * @param queueOwnerId the queueOwnerId to set
	 */
	public void setQueueOwnerId(String queueOwnerId) {
		this.queueOwnerId = queueOwnerId;
	}

	/**
	 * @return the workflowId
	 */
	public String getWorkflowId() {
		return workflowId;
	}

	/**
	 * @param workflowId the workflowId to set
	 */
	public void setWorkflowId(String workflowId) {
		this.workflowId = workflowId;
	}

	/**
	 * @return the priority
	 */
	public String getPriority() {
		return priority;
	}

	/**
	 * @param priority the priority to set
	 */
	public void setPriority(String priority) {
		this.priority = priority;
	}

	/**
	 * @return the queueName
	 */
	public String getQueueName() {
		return queueName;
	}

	/**
	 * @param queueName the queueName to set
	 */
	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

	/**
	 * @return the queueShortName
	 */
	public String getQueueShortName() {
		return queueShortName;
	}

	/**
	 * @param queueShortName the queueShortName to set
	 */
	public void setQueueShortName(String queueShortName) {
		this.queueShortName = queueShortName;
	}

	/**
	 * @return the ruleSetId
	 */
	public String getRuleSetId() {
		return ruleSetId;
	}

	/**
	 * @param ruleSetId the ruleSetId to set
	 */
	public void setRuleSetId(String ruleSetId) {
		this.ruleSetId = ruleSetId;
	}

	/**
	 * @return the maxCases
	 */
	public String getMaxCases() {
		return maxCases;
	}

	/**
	 * @param maxCases the maxCases to set
	 */
	public void setMaxCases(String maxCases) {
		this.maxCases = maxCases;
	}

	/**
	 * @return the totalAmtCap
	 */
	public String getTotalAmtCap() {
		return totalAmtCap;
	}

	/**
	 * @param totalAmtCap the totalAmtCap to set
	 */
	public void setTotalAmtCap(String totalAmtCap) {
		this.totalAmtCap = totalAmtCap;
	}

	/**
	 * @return the coallocation
	 */
	public String getCoallocation() {
		return coallocation;
	}

	/**
	 * @param coallocation the coallocation to set
	 */
	public void setCoallocation(String coallocation) {
		this.coallocation = coallocation;
	}

	/**
	 * @return the isActive
	 */
	public String getIsActive() {
		return isActive;
	}

	/**
	 * @param isActive the isActive to set
	 */
	public void setIsActive(String isActive) {
		this.isActive = isActive;
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
	 * @return the makerSysDate
	 */
	public Date getMakerSysDate() {
		return makerSysDate;
	}

	/**
	 * @param makerSysDate the makerSysDate to set
	 */
	public void setMakerSysDate(Date makerSysDate) {
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
	 * @return the authSysDate
	 */
	public Date getAuthSysDate() {
		return authSysDate;
	}

	/**
	 * @param authSysDate the authSysDate to set
	 */
	public void setAuthSysDate(Date authSysDate) {
		this.authSysDate = authSysDate;
	}

	@Column(name="AUTH_SYSDATE")			
	private	Date	authSysDate	;


}
