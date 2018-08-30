package com.qc.starter.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "QT_ADDRESS", schema = "QC_PROSPECT")
public class AddressEntity {
	@Id
	@Column(name = "ADDRESS_ID")
	private String addressId;
	@Column(name = "PERSONAL_DTL_ID")
	private String personalDtlId;
	@Column(name = "CASE_ID")
	private String caseId;
	@Column(name = "ADDRESS_TYPE")
	private String addressType;
	private String address;
	private String city;
	private String buildingName;
	private String landmark;
	private String state;
	private String zipcode;
	@Column(name = "EMAIL")
	private String email;
	private String sameasres;
	private String postoffice;
	private String panchayat;
	private String policestation;
	private String block;
	private String sameasper;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "STD_ISD")
	private String stdIsd;
	@Column(name = "MOBILE1")
	private String mobile_no1;
	@Column(name = "MOBILE2")
	private String mobile_no2;

	private String phone1;
	private String ext1;
	private String ext2;
	private String fax;
	private String mailingAddress;
	@Column(name = "IS_DEST_ADD")
	private String destinationAddress;
	@Column(name = "OCCUPANCY_MM")
	private String occupancyMm;
	@Column(name = "OCCUPANCY_YR")
	private String occupancyYr;
	@Column(name = "OCCUPANCY_STATUS")
	private String occupancyStatus;
	private String sameas;
	private String active;
	@Column(name = "CREATED_BY")
	private String createdBy;
	@Column(name = "CREATED_DATE")
	private Date createdDate;
	@Column(name = "CREATED_SYS_DATE")
	private Date createdSysDate;
	@Column(name = "UPDATED_BY")
	private String updatedBy;
	@Column(name = "UPDATED_DATE")
	private Date updatedDate;
	@Column(name = "UPDATED_SYS_DATE")
	private Date updatedSysDate;
	@Transient
	private String cityName;
	@Column(name = "BUSINESS_EST_YR")
	private String bussinessestbyr;
	@Column(name = "OLD_ADDRESS")
	private String oldaddress;
	@Column(name = "PROP_MARKET_VALUE")
	private String marketvalue;
	@Column(name = "GSTIN_NO")
	private String gstinno;
	@Column(name = "YRS_IN_AREA")
	private String currentareaYr;
	@Column(name = "PHONE2")
	private String phone2;
	@Column(name = "FLATNO")
	private String flatNo;
	@Column(name = "FLOORNO")
	private String floorNo;
	@Column(name = "LOCALITY")
	private String locality;
	@Column(name = "COMPANY_NAME")
	private String company_name;
	private String mobile1_Id1;
	private String mobile1_Id2;
	private String email_Id;

	public String getMobile1_Id1() {
		return mobile1_Id1;
	}

	public void setMobile1_Id1(String mobile1_Id1) {
		this.mobile1_Id1 = mobile1_Id1;
	}

	public String getMobile1_Id2() {
		return mobile1_Id2;
	}

	public void setMobile1_Id2(String mobile1_Id2) {
		this.mobile1_Id2 = mobile1_Id2;
	}

	public String getEmail_Id() {
		return email_Id;
	}

	public void setEmail_Id(String email_Id) {
		this.email_Id = email_Id;
	}

	public String getDestinationAddress() {
		return destinationAddress;
	}

	public void setDestinationAddress(String destinationAddress) {
		this.destinationAddress = destinationAddress;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	/**
	 * @return the addressId
	 */
	public String getAddressId() {
		return addressId;
	}

	/**
	 * @param addressId
	 *            the addressId to set
	 */
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	/**
	 * @return the personalDtlId
	 */
	public String getPersonalDtlId() {
		return personalDtlId;
	}

	/**
	 * @param personalDtlId
	 *            the personalDtlId to set
	 */
	public void setPersonalDtlId(String personalDtlId) {
		this.personalDtlId = personalDtlId;
	}

	/**
	 * @return the caseId
	 */
	public String getCaseId() {
		return caseId;
	}

	/**
	 * @param caseId
	 *            the caseId to set
	 */
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	/**
	 * @return the addressType
	 */
	public String getAddressType() {
		return addressType;
	}

	/**
	 * @param addressType
	 *            the addressType to set
	 */
	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
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
	 * @param flatNo
	 *            the flatNo to set
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
	 * @param floorNo
	 *            the floorNo to set
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
	 * @param buildingName
	 *            the buildingName to set
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
	 * @param locality
	 *            the locality to set
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
	 * @param landmark
	 *            the landmark to set
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
	 * @param city
	 *            the city to set
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
	 * @param state
	 *            the state to set
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
	 * @param zipcode
	 *            the zipcode to set
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
	 * @param stdIsd
	 *            the stdIsd to set
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
	 * @param phone1
	 *            the phone1 to set
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
	 * @param ext1
	 *            the ext1 to set
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
	 * @param phone2
	 *            the phone2 to set
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
	 * @param ext2
	 *            the ext2 to set
	 */
	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}

	/**
	 * @return the dndFlag
	 */

	/**
	 * @return the fax
	 */
	public String getFax() {
		return fax;
	}

	/**
	 * @param fax
	 *            the fax to set
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}

	/**
	 * @return the mailingAddress
	 */
	public String getMailingAddress() {
		return mailingAddress;
	}

	/**
	 * @param mailingAddress
	 *            the mailingAddress to set
	 */
	public void setMailingAddress(String mailingAddress) {
		this.mailingAddress = mailingAddress;
	}

	/**
	 * @return the occupancyMm
	 */
	public String getOccupancyMm() {
		return occupancyMm;
	}

	/**
	 * @param occupancyMm
	 *            the occupancyMm to set
	 */
	public void setOccupancyMm(String occupancyMm) {
		this.occupancyMm = occupancyMm;
	}

	/**
	 * @return the occupancyYr
	 */
	public String getOccupancyYr() {
		return occupancyYr;
	}

	/**
	 * @param occupancyYr
	 *            the occupancyYr to set
	 */
	public void setOccupancyYr(String occupancyYr) {
		this.occupancyYr = occupancyYr;
	}

	/**
	 * @return the occupancyStatus
	 */
	public String getOccupancyStatus() {
		return occupancyStatus;
	}

	/**
	 * @param occupancyStatus
	 *            the occupancyStatus to set
	 */
	public void setOccupancyStatus(String occupancyStatus) {
		this.occupancyStatus = occupancyStatus;
	}

	/**
	 * @return the sameas
	 */
	public String getSameas() {
		return sameas;
	}

	/**
	 * @param sameas
	 *            the sameas to set
	 */
	public void setSameas(String sameas) {
		this.sameas = sameas;
	}

	/**
	 * @return the active
	 */
	public String getActive() {
		return active;
	}

	/**
	 * @param active
	 *            the active to set
	 */
	public void setActive(String active) {
		this.active = active;
	}

	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy
	 *            the createdBy to set
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
	 * @param createdDate
	 *            the createdDate to set
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
	 * @param createdSysDate
	 *            the createdSysDate to set
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
	 * @param updatedBy
	 *            the updatedBy to set
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
	 * @param updatedDate
	 *            the updatedDate to set
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
	 * @param updatedSysDate
	 *            the updatedSysDate to set
	 */
	public void setUpdatedSysDate(Date updatedSysDate) {
		this.updatedSysDate = updatedSysDate;
	}

	public String getSameasres() {
		return sameasres;
	}

	public void setSameasres(String sameasres) {
		this.sameasres = sameasres;
	}

	public String getGstinno() {
		return gstinno;
	}

	public void setGstinno(String gstinno) {
		this.gstinno = gstinno;
	}

	public String getMobile_no1() {
		return mobile_no1;
	}

	public void setMobile_no1(String mobile_no1) {
		this.mobile_no1 = mobile_no1;
	}

	public String getMobile_no2() {
		return mobile_no2;
	}

	public void setMobile_no2(String mobile_no2) {
		this.mobile_no2 = mobile_no2;
	}

	public String getCurrentareaYr() {
		return currentareaYr;
	}

	public void setCurrentareaYr(String currentareaYr) {
		this.currentareaYr = currentareaYr;
	}

	public String getBussinessestbyr() {
		return bussinessestbyr;
	}

	public void setBussinessestbyr(String bussinessestbyr) {
		this.bussinessestbyr = bussinessestbyr;
	}

	public String getOldaddress() {
		return oldaddress;
	}

	public void setOldaddress(String oldaddress) {
		this.oldaddress = oldaddress;
	}

	public String getMarketvalue() {
		return marketvalue;
	}

	public void setMarketvalue(String marketvalue) {
		this.marketvalue = marketvalue;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getPostoffice() {
		return postoffice;
	}

	public void setPostoffice(String postoffice) {
		this.postoffice = postoffice;
	}

	public String getPolicestation() {
		return policestation;
	}

	public void setPolicestation(String policestation) {
		this.policestation = policestation;

	}

	public String getPanchayat() {
		return panchayat;
	}

	public void setPanchayat(String panchayat) {
		this.panchayat = panchayat;
	}

	public String getBlock() {
		return block;
	}

	public void setBlock(String block) {
		this.block = block;
	}

	public String getSameasper() {
		return sameasper;
	}

	public void setSameasper(String sameasper) {
		this.sameasper = sameasper;
	}
}
