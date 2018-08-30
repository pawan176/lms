package com.qc.starter.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="QT_PROPERTY",schema="QC_PROSPECT")
public class PropertyEntity {

	@Id
	@Column(name="PROPERTY_ID")			
	private	String	propertyId	;
	@Column(name="CASE_ID")
	private String caseId;
	@Column(name="PERSONAL_DTL_ID")			
	private	String	personalDtlId	;
	@Column(name="PROP_TYPE_ID")			
	private	String	propTypeId	;
	@Column(name="DEVELOPER_NAME")			
	private	String	developerName	;
	@Column(name="DEVELOPER_ID")			
	private	String	devloperId	;
	@Column(name="PROJECT_ID")			
	private	String	projectId	;
	@Column(name="PROJECT_NAME")			
	private	String	projectName	;
	@Column(name="ESTIMATED_VALUE")			
	private	String	estimatedValue	;
	private	String	address	;
	@Column(name="LAND_MARK")			
	private	String	landMark	;
	@Column(name="PROP_STATUS")			
	private	String	propStatus	;
	private	String	remarks	;
	private	String	city	;
	private	String	state	;
	private	String	zipcode	;
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
	//----------Added by Deepak ----------
	@Column(name="OTHER_DEVELOPER_NAME")	
	private	String	otherDeploperName	;
	@Column(name="OTHER_PROJECT_NAME")
	private	String	otherProjectName	;
	@Column(name="OCCUPANCY_STATUS")
	private String occupancyStatus;
	@Transient
	private	String	cityName	;
	//----------------- ,
	
	
	
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getDevloperId() {
		return devloperId;
	}
	public String getOccupancyStatus() {
		return occupancyStatus;
	}
	public void setOccupancyStatus(String occupancyStatus) {
		this.occupancyStatus = occupancyStatus;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getOtherDeploperName() {
		return otherDeploperName;
	}
	public void setOtherDeploperName(String otherDeploperName) {
		this.otherDeploperName = otherDeploperName;
	}
	public String getOtherProjectName() {
		return otherProjectName;
	}
	public void setOtherProjectName(String otherProjectName) {
		this.otherProjectName = otherProjectName;
	}
	public void setDevloperId(String devloperId) {
		this.devloperId = devloperId;
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
	/**
	 * @return the propertyId
	 */
	public String getPropertyId() {
		return propertyId;
	}
	/**
	 * @param propertyId the propertyId to set
	 */
	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
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
	 * @return the propTypeId
	 */
	public String getPropTypeId() {
		return propTypeId;
	}
	/**
	 * @param propTypeId the propTypeId to set
	 */
	public void setPropTypeId(String propTypeId) {
		this.propTypeId = propTypeId;
	}
	/**
	 * @return the developerName
	 */
	public String getDeveloperName() {
		return developerName;
	}
	/**
	 * @param developerName the developerName to set
	 */
	public void setDeveloperName(String developerName) {
		this.developerName = developerName;
	}
	/**
	 * @return the projectName
	 */
	public String getProjectName() {
		return projectName;
	}
	/**
	 * @param projectName the projectName to set
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	/**
	 * @return the estimatedValue
	 */
	public String getEstimatedValue() {
		return estimatedValue;
	}
	/**
	 * @param estimatedValue the estimatedValue to set
	 */
	public void setEstimatedValue(String estimatedValue) {
		this.estimatedValue = estimatedValue;
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
	 * @return the landMark
	 */
	public String getLandMark() {
		return landMark;
	}
	/**
	 * @param landMark the landMark to set
	 */
	public void setLandMark(String landMark) {
		this.landMark = landMark;
	}
	/**
	 * @return the propStatus
	 */
	public String getPropStatus() {
		return propStatus;
	}
	/**
	 * @param propStatus the propStatus to set
	 */
	public void setPropStatus(String propStatus) {
		this.propStatus = propStatus;
	}
	/**
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}
	/**
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	
	
	
}
