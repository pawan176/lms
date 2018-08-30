package com.qc.starter.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author tripti
 *
 */
@Entity
@Table(name = "QM_USER", schema="QC_USER_AUTH")
public class UserEntity {
	
	@Id
	private String userId;
	private String userCode;
	private String userfName;
	private String usermName;
	private String userlName;
	private String loginname;
	private String password;
	private String password1;
	private String password2;
	private String password3;
	private String password4;
	private String password5;
	private String password6;
	private String password7;
	private String password8;
	@Column(name="PASSWORD_EXPIRED_ON")
	private String passwordExpiredOn;
	@Column(name="PASSWORD_LASTUPDATED")
	private String passwordLastupdated;
	private String locationId;
	private String departmentId;
	private String designation;
	private String supervisorId;
	private String dob;
	private String martialStatus;
	private String gender;
	private String noOfDependents;
	private String fatherfName;
	private String fathermName;
	private String fatherlName;
	private String spousefName;
	private String spousemName;
	private String spouselName;
	private String nationality;
	private String nationalId;
	private String nlfName;
	private String nlmName;
	private String nllName;
	private String votersId;
	private String panNo;
	private String drivingLicenseNo;
	private String passportno;
	@Column(name="MAKER_REMARKS")
	private String makerRemarks;
	@Column(name="MAKER_SYSDATETIME")
	private String makerSysdatetime;
	@Column(name="MAKER_DATE")
	private String makerDate;
	@Column(name="MAKER_ID")
	private String makerId;
	@Column(name="SENT_TO_AUTHOR_DATE")
	private String sentToAuthorDate;
	@Column(name="AUTH_SYSDATETIME")
	private String authSysDatetime;
	@Column(name="AUTH_DATE")
	private String authDate;
	@Column(name="AUTH_ID")
	private String authId;
	@Column(name="AUTH_REMARK")
	private String authRemark;
	@Column(name="AUTH_STATUS")
	private String authStatus;
	@Column(name="LASTUPDATE_DATETIME")
	private String lastUpdateDatetime;
	@Column(name="ACTIVE")
	private String isActive;
	private String createdBy;
	@Column(name="CREATED_DATETIME")
	private String createdDatetime;
	private String updateBy;
	@Column(name="UPDATE_DATETIME")
	private String updateDatetime;
	private String email;
	private String area;
	private String locality;
	private String landmark;
	private String state;
	private String city;
	private String zipcode;
	@Column(name="FIRST_LOGIN")
	private String firstLogin;
	@Column(name="ADMIN_FLAG")
	private String adminFlag;
	@Column(name="COMPANY_ID")
	private String companyId;
	private String martialstatus;
	@Transient
	private String login1;
	@Transient
	private String login2;
	@Transient
	private String ipaddress;
	
	
	
	public String getIpaddress() {
		return "127.0.0.1";
	}
	public void setIpaddress(String ipaddress) {
		this.ipaddress = "127.0.0.1";
	}
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public String getLogin1() {
		return login1;
	}
	public void setLogin1(String login1) {
		this.login1 = login1;
	}
	public String getLogin2() {
		return login2;
	}
	public void setLogin2(String login2) {
		this.login2 = login2;
	}
	public Integer getUserid() {
		return Integer.parseInt(userId);
	}
	public void setUserid(String userid) {
		this.userId = userid;
	}
	public String getUsercode() {
		return userCode;
	}
	public void setUsercode(String usercode) {
		this.userCode = usercode;
	}
	public String getUserfname() {
		return userfName;
	}
	public void setUserfname(String userfname) {
		this.userfName = userfname;
	}
	public String getUsermname() {
		return usermName;
	}
	public void setUsermname(String usermname) {
		this.usermName = usermname;
	}
	public String getUserlname() {
		return userlName;
	}
	public void setUserlname(String userlname) {
		this.userlName = userlname;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword1() {
		return password1;
	}
	public void setPassword1(String password1) {
		this.password1 = password1;
	}
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	public String getPassword3() {
		return password3;
	}
	public void setPassword3(String password3) {
		this.password3 = password3;
	}
	public String getPassword4() {
		return password4;
	}
	public void setPassword4(String password4) {
		this.password4 = password4;
	}
	public String getPassword5() {
		return password5;
	}
	public void setPassword5(String password5) {
		this.password5 = password5;
	}
	public String getPassword6() {
		return password6;
	}
	public void setPassword6(String password6) {
		this.password6 = password6;
	}
	public String getPassword7() {
		return password7;
	}
	public void setPassword7(String password7) {
		this.password7 = password7;
	}
	public String getPassword8() {
		return password8;
	}
	public void setPassword8(String password8) {
		this.password8 = password8;
	}
	public String getPasswordExpiredOn() {
		return passwordExpiredOn;
	}
	public void setPasswordExpiredOn(String passwordExpiredOn) {
		this.passwordExpiredOn = passwordExpiredOn;
	}
	public String getPasswordLastupdated() {
		return passwordLastupdated;
	}
	public void setPasswordLastupdated(String passwordLastupdated) {
		this.passwordLastupdated = passwordLastupdated;
	}
	public String getLocationid() {
		return locationId;
	}
	public void setLocationid(String locationid) {
		this.locationId = locationid;
	}
	public String getDepartmentid() {
		return departmentId;
	}
	public void setDepartmentid(String departmentid) {
		this.departmentId = departmentid;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getSupervisorid() {
		return supervisorId;
	}
	public void setSupervisorid(String supervisorid) {
		this.supervisorId = supervisorid;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getMartialstatus() {
		return martialStatus;
	}
	public void setMartialstatus(String martialstatus) {
		this.martialStatus = martialstatus;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Integer getNoofdependents() {
		return Integer.parseInt(noOfDependents);
	}
	public void setNoofdependents(String noofdependents) {
		this.noOfDependents = noofdependents;
	}
	public String getFatherfname() {
		return fatherfName;
	}
	public void setFatherfname(String fatherfname) {
		this.fatherfName = fatherfname;
	}
	public String getFathermname() {
		return fathermName;
	}
	public void setFathermname(String fathermname) {
		this.fathermName = fathermname;
	}
	public String getFatherlname() {
		return fatherlName;
	}
	public void setFatherlname(String fatherlname) {
		this.fatherlName = fatherlname;
	}
	public String getSpousefname() {
		return spousefName;
	}
	public void setSpousefname(String spousefname) {
		this.spousefName = spousefname;
	}
	public String getSpousemname() {
		return spousemName;
	}
	public void setSpousemname(String spousemname) {
		this.spousemName = spousemname;
	}
	public String getSpouselname() {
		return spouselName;
	}
	public void setSpouselname(String spouselname) {
		this.spouselName = spouselname;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getNationalid() {
		return nationalId;
	}
	public void setNationalid(String nationalid) {
		this.nationalId = nationalid;
	}
	public String getNlfname() {
		return nlfName;
	}
	public void setNlfname(String nlfname) {
		this.nlfName = nlfname;
	}
	public String getNlmname() {
		return nlmName;
	}
	public void setNlmname(String nlmname) {
		this.nlmName = nlmname;
	}
	public String getNllname() {
		return nllName;
	}
	public void setNllname(String nllname) {
		this.nllName = nllname;
	}
	public String getVotersid() {
		return votersId;
	}
	public void setVotersid(String votersid) {
		this.votersId = votersid;
	}
	public String getPanno() {
		return panNo;
	}
	public void setPanno(String panno) {
		this.panNo = panno;
	}
	public String getDrivinglicenseno() {
		return drivingLicenseNo;
	}
	public void setDrivinglicenseno(String drivinglicenseno) {
		this.drivingLicenseNo = drivinglicenseno;
	}
	public String getPassportno() {
		return passportno;
	}
	public void setPassportno(String passportno) {
		this.passportno = passportno;
	}
	public String getMakerRemarks() {
		return makerRemarks;
	}
	public void setMakerRemarks(String makerRemarks) {
		this.makerRemarks = makerRemarks;
	}
	public String getMakerSysdatetime() {
		return makerSysdatetime;
	}
	public void setMakerSysdatetime(String makerSysdatetime) {
		this.makerSysdatetime = makerSysdatetime;
	}
	public String getMakerDate() {
		return makerDate;
	}
	public void setMakerDate(String makerDate) {
		this.makerDate = makerDate;
	}
	public String getMakerId() {
		return makerId;
	}
	public void setMakerId(String makerId) {
		this.makerId = makerId;
	}
	public String getSentToAuthorDate() {
		return sentToAuthorDate;
	}
	public void setSentToAuthorDate(String sentToAuthorDate) {
		this.sentToAuthorDate = sentToAuthorDate;
	}
	public String getAuthSysdatetime() {
		return authSysDatetime;
	}
	public void setAuthSysdatetime(String authSysdatetime) {
		this.authSysDatetime = authSysdatetime;
	}
	public String getAuthDate() {
		return authDate;
	}
	public void setAuthDate(String authDate) {
		this.authDate = authDate;
	}
	public String getAuthId() {
		return authId;
	}
	public void setAuthId(String authId) {
		this.authId = authId;
	}
	public String getAuthRemark() {
		return authRemark;
	}
	public void setAuthRemark(String authRemark) {
		this.authRemark = authRemark;
	}
	public String getAuthStatus() {
		return authStatus;
	}
	public void setAuthStatus(String authStatus) {
		this.authStatus = authStatus;
	}
	public String getLastupdateDatetime() {
		return lastUpdateDatetime;
	}
	public void setLastupdateDatetime(String lastupdateDatetime) {
		this.lastUpdateDatetime = lastupdateDatetime;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String getCreatedby() {
		return createdBy;
	}
	public void setCreatedby(String createdby) {
		this.createdBy = createdby;
	}
	public String getCreatedDatetime() {
		return createdDatetime;
	}
	public void setCreatedDatetime(String createdDatetime) {
		this.createdDatetime = createdDatetime;
	}
	public String getUpdateby() {
		return updateBy;
	}
	public void setUpdateby(String updateby) {
		this.updateBy = updateby;
	}
	public String getUpdateDatetime() {
		return updateDatetime;
	}
	public void setUpdateDatetime(String updateDatetime) {
		this.updateDatetime = updateDatetime;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getLocality() {
		return locality;
	}
	public void setLocality(String locality) {
		this.locality = locality;
	}
	public String getLandmark() {
		return landmark;
	}
	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getFirstLogin() {
		return firstLogin;
	}
	public void setFirstLogin(String firstLogin) {
		this.firstLogin = firstLogin;
	}
	public String getAdminFlag() {
		return adminFlag;
	}
	public void setAdminFlag(String adminFlag) {
		this.adminFlag = adminFlag;
	}
	public String getCompanyId() {
		return "1000000001";
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getMaritalstatus() {
		return martialstatus;
	}
	public void setMaritalstatus(String martialstatus) {
		this.martialstatus = martialstatus;
	}
}
