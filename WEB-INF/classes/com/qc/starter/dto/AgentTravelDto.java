package com.qc.starter.dto;

import java.util.List;

public class AgentTravelDto {
private String userId;	
private String userName;
private String noOfCustomer;
private String loginTime;
private String logoutTime;
private String distanceTravelled;
private String actionDate;
private String actionMonth;
public List<DailyActivityDto> locationList;
public List<DailyActivityDto> getLocationList() {
	return locationList;
}
public void setLocationList(List<DailyActivityDto> locationList) {
	this.locationList = locationList;
}
public String getUserId() {
	return userId;
}
public void setUserId(String userId) {
	this.userId = userId;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getNoOfCustomer() {
	return noOfCustomer;
}
public void setNoOfCustomer(String noOfCustomer) {
	this.noOfCustomer = noOfCustomer;
}
public String getLoginTime() {
	return loginTime;
}
public void setLoginTime(String loginTime) {
	this.loginTime = loginTime;
}
public String getLogoutTime() {
	return logoutTime;
}
public void setLogoutTime(String logoutTime) {
	this.logoutTime = logoutTime;
}
public String getDistanceTravelled() {
	return distanceTravelled;
}
public void setDistanceTravelled(String distanceTravelled) {
	this.distanceTravelled = distanceTravelled;
}
public String getActionDate() {
	return actionDate;
}
public void setActionDate(String actionDate) {
	this.actionDate = actionDate;
}
public String getActionMonth() {
	return actionMonth;
}
public void setActionMonth(String actionMonth) {
	this.actionMonth = actionMonth;
}
}
