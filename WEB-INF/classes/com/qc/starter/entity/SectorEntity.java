package com.qc.starter.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;




@Entity
@Table(name="QM_CASE_SECTOR", schema="QC_PROSPECT_MASTER")
public class SectorEntity {
@Id
private String sectorId;
private String sectorCode;
private String sectorName;
private String companyId;
private String active;
public String getSectorId() {
	return sectorId;
}
public void setSectorId(String sectorId) {
	this.sectorId = sectorId;
}
public String getSectorCode() {
	return sectorCode;
}
public void setSectorCode(String sectorCode) {
	this.sectorCode = sectorCode;
}
public String getSectorName() {
	return sectorName;
}
public void setSectorName(String sectorName) {
	this.sectorName = sectorName;
}
public String getCompanyId() {
	return companyId;
}
public void setCompanyId(String companyId) {
	this.companyId = companyId;
}
public String getActive() {
	return active;
}
public void setActive(String active) {
	this.active = active;
}


}
