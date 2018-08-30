package com.qc.starter.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="QM_GEO",schema="QC_PROSPECT_MASTER")
public class BranchEntity {
	
	
	@Id
	private	String	geoId	;
	private	String	geoCode	;
	private	String	geoName	;
	private	String	geoTypeId;
	private	String	description	;
	private	String	createdBy;
	
	public String getGeoId() {
		return geoId;
	}
	public void setGeoId(String geoId) {
		this.geoId = geoId;
	}
	public String getGeoCode() {
		return geoCode;
	}
	public void setGeoCode(String geoCode) {
		this.geoCode = geoCode;
	}
	public String getGeoName() {
		return geoName;
	}
	public void setGeoName(String geoName) {
		this.geoName = geoName;
	}
	public String getGeoTypeId() {
		return geoTypeId;
	}
	public void setGeoTypeId(String geoTypeId) {
		this.geoTypeId = geoTypeId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	
	

}
