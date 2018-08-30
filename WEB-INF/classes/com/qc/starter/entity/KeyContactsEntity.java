package com.qc.starter.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="QT_KEY_CONTACTS",schema="QC_PROSPECT")
public class KeyContactsEntity {

	@Id
	@Column(name="KEY_CONTACT_ID")			
	private	String	keyContactId	;
	@Column(name="PERSONAL_DTL_ID")			
	private	String	personalDtlId	;
	@Column(name="CASE_ID")
	private String caseId;
	@Column(name="CONTACT_TYPE_ID")			
	private	String	contactTypeId	;
	private	String	fname	;
	private	String	mname	;
	private	String	lname	;
	@Column(name="FIRM_NAME")			
	private	String	firmName	;
	private	String	address	;
	private	String	flatNo	;
	private	String	floorNo	;
	private	String	buildingName	;
	private	String	locality	;
	private	String	landmark	;
	private	String	city	;
	private	String	state	;
	private	String	zipcode	;
	@Column(name="STD_ISD")			
	private	String	stdIsd	;
	private	String	phone1	;
	private	String	ext1	;
	private	String	phone2	;
	private	String	ext2	;
	private	String	mobile	;
	private	String	email	;
	@Column(name="CREATED_BY")			
	private	String	createdBy	;
	@Column(name="CREATED_DATE")			
	private	Date	createdDate	;
	@Column(name="CREATED_SYS_DATE")			
	private	Date	createdSysDate	;
	@Column(name="UPDATED_BY")			
	private	String	updatedBy	;
	@Column(name="UPDATED_DATE")			
	private	Date	updatedDate	;
	@Column(name="UPDATED_SYS_DATE")			
	private	Date	updatedSysDate	;
	/**
	 * @return the keyContactId
	 */
	public String getKeyContactId() {
		return keyContactId;
	}
	/**
	 * @param keyContactId the keyContactId to set
	 */
	public void setKeyContactId(String keyContactId) {
		this.keyContactId = keyContactId;
	}
	
	/**
	 * @return the personalDtlId
	 */
	public String getPersonalDtlId() {
		return personalDtlId;
	}
	/**
	 * @param personalDtlId the personalDtlId to set
	 */
	public void setPersonalDtlId(String personalDtlId) {
		this.personalDtlId = personalDtlId;
	}
	/**
	 * @return the contactTypeId
	 */
	public String getContactTypeId() {
		return contactTypeId;
	}
	/**
	 * @param contactTypeId the contactTypeId to set
	 */
	public void setContactTypeId(String contactTypeId) {
		this.contactTypeId = contactTypeId;
	}
	/**
	 * @return the fname
	 */
	public String getFname() {
		return fname;
	}
	/**
	 * @param fname the fname to set
	 */
	public void setFname(String fname) {
		this.fname = fname;
	}
	/**
	 * @return the mname
	 */
	public String getMname() {
		return mname;
	}
	/**
	 * @param mname the mname to set
	 */
	public void setMname(String mname) {
		this.mname = mname;
	}
	/**
	 * @return the lname
	 */
	public String getLname() {
		return lname;
	}
	/**
	 * @param lname the lname to set
	 */
	public void setLname(String lname) {
		this.lname = lname;
	}
	/**
	 * @return the firmName
	 */
	public String getFirmName() {
		return firmName;
	}
	/**
	 * @param firmName the firmName to set
	 */
	public void setFirmName(String firmName) {
		this.firmName = firmName;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the flatNo
	 */
	public String getFlatNo() {
		return flatNo;
	}
	/**
	 * @param flatNo the flatNo to set
	 */
	public void setFlatNo(String flatNo) {
		this.flatNo = flatNo;
	}
	/**
	 * @return the floorNo
	 */
	public String getFloorNo() {
		return floorNo;
	}
	/**
	 * @param floorNo the floorNo to set
	 */
	public void setFloorNo(String floorNo) {
		this.floorNo = floorNo;
	}
	/**
	 * @return the buildingName
	 */
	public String getBuildingName() {
		return buildingName;
	}
	/**
	 * @param buildingName the buildingName to set
	 */
	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}
	/**
	 * @return the locality
	 */
	public String getLocality() {
		return locality;
	}
	/**
	 * @param locality the locality to set
	 */
	public void setLocality(String locality) {
		this.locality = locality;
	}
	/**
	 * @return the landmark
	 */
	public String getLandmark() {
		return landmark;
	}
	/**
	 * @param landmark the landmark to set
	 */
	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return the zipcode
	 */
	public String getZipcode() {
		return zipcode;
	}
	/**
	 * @param zipcode the zipcode to set
	 */
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	/**
	 * @return the stdIsd
	 */
	public String getStdIsd() {
		return stdIsd;
	}
	/**
	 * @param stdIsd the stdIsd to set
	 */
	public void setStdIsd(String stdIsd) {
		this.stdIsd = stdIsd;
	}
	/**
	 * @return the phone1
	 */
	public String getPhone1() {
		return phone1;
	}
	/**
	 * @param phone1 the phone1 to set
	 */
	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}
	/**
	 * @return the ext1
	 */
	public String getExt1() {
		return ext1;
	}
	/**
	 * @param ext1 the ext1 to set
	 */
	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}
	/**
	 * @return the phone2
	 */
	public String getPhone2() {
		return phone2;
	}
	/**
	 * @param phone2 the phone2 to set
	 */
	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}
	/**
	 * @return the ext2
	 */
	public String getExt2() {
		return ext2;
	}
	/**
	 * @param ext2 the ext2 to set
	 */
	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}
	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
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
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}
	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	/**
	 * @return the createdSysDate
	 */
	public Date getCreatedSysDate() {
		return createdSysDate;
	}
	/**
	 * @param createdSysDate the createdSysDate to set
	 */
	public void setCreatedSysDate(Date createdSysDate) {
		this.createdSysDate = createdSysDate;
	}
	/**
	 * @return the updatedBy
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}
	/**
	 * @param updatedBy the updatedBy to set
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	/**
	 * @return the updatedDate
	 */
	public Date getUpdatedDate() {
		return updatedDate;
	}
	/**
	 * @param updatedDate the updatedDate to set
	 */
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	/**
	 * @return the updatedSysDate
	 */
	public Date getUpdatedSysDate() {
		return updatedSysDate;
	}
	/**
	 * @param updatedSysDate the updatedSysDate to set
	 */
	public void setUpdatedSysDate(Date updatedSysDate) {
		this.updatedSysDate = updatedSysDate;
	}
	/**
	 * @return the caseId
	 */
	public String getCaseId() {
		return caseId;
	}
	/**
	 * @param caseId the caseId to set
	 */
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	
	
	
}
