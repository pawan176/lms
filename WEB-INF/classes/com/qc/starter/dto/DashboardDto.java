package com.qc.starter.dto;

public class DashboardDto {

	private String actionStage;
	private String leadCount;

	private String Date;
	private String Time;
	private String leadId;
	private String customerName;
	private String mobileNo;
	private String actionType;


	public String getActionStage() {
		return actionStage;
	}
	public void setActionStage(String actionStage) {
		this.actionStage = actionStage;
	}
	public String getLeadCount() {
		return leadCount;
	}
	public void setLeadCount(String leadCount) {
		this.leadCount = leadCount;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	public String getTime() {
		return Time;
	}
	public void setTime(String time) {
		Time = time;
	}
	public String getLeadId() {
		return leadId;
	}
	public void setLeadId(String leadId) {
		this.leadId = leadId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getActionType() {
		return actionType;
	}
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}




}
