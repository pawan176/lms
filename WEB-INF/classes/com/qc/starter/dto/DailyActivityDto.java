package com.qc.starter.dto;

public class DailyActivityDto {
	public String customerName;
	public String dispositionName;
	public String actionName;
	public String actionLatitude;
	public String actionLongitude;
	public String caseId;
	public String userId;
	
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getDispositionName() {
		return dispositionName;
	}

	public void setDispositionName(String dispositionName) {
		this.dispositionName = dispositionName;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getActionLatitude() {
		return actionLatitude;
	}

	public void setActionLatitude(String actionLatitude) {
		this.actionLatitude = actionLatitude;
	}

	public String getActionLongitude() {
		return actionLongitude;
	}

	public void setActionLongitude(String actionLongitude) {
		this.actionLongitude = actionLongitude;
	}

	public String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
}
