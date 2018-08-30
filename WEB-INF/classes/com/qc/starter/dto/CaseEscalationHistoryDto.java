package com.qc.starter.dto;

public class CaseEscalationHistoryDto {

	String actionName;
	String initiatedBy;
	String initiatedTo;
	String initiatedDateTime;
	String initialRemarks;
	String resolvedBy;
	String resolveDtTime;
	String resolvedRemarks;

	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public String getInitiatedBy() {
		return initiatedBy;
	}
	public void setInitiatedBy(String initiatedBy) {
		this.initiatedBy = initiatedBy;
	}
	public String getInitiatedTo() {
		return initiatedTo;
	}
	public void setInitiatedTo(String initiatedTo) {
		this.initiatedTo = initiatedTo;
	}
	public String getInitiatedDateTime() {
		return initiatedDateTime;
	}
	public void setInitiatedDateTime(String initiatedDateTime) {
		this.initiatedDateTime = initiatedDateTime;
	}
	public String getInitialRemarks() {
		return initialRemarks;
	}
	public void setInitialRemarks(String initialRemarks) {
		this.initialRemarks = initialRemarks;
	}
	public String getResolvedBy() {
		return resolvedBy;
	}
	public void setResolvedBy(String resolvedBy) {
		this.resolvedBy = resolvedBy;
	}
	public String getResolveDtTime() {
		return resolveDtTime;
	}
	public void setResolveDtTime(String resolveDtTime) {
		this.resolveDtTime = resolveDtTime;
	}
	public String getResolvedRemarks() {
		return resolvedRemarks;
	}
	public void setResolvedRemarks(String resolvedRemarks) {
		this.resolvedRemarks = resolvedRemarks;
	}
}
