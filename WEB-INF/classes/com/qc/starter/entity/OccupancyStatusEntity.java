package com.qc.starter.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="QM_RESITYPE",schema="QC_PROSPECT_MASTER")
public class OccupancyStatusEntity {

	
	@Id
	@Column(name="RESITYPEID")			
	private	String	occupancyStId	;
	
	@Column(name="RESITYPECODE")			
	private	String	occupancyStCode	;
	
	@Column(name="DISPLAYNAME")			
	private	String	occupancyStName	;
	
	private	String	createdby;
	
	@Column(name="CREATED_DATETIME")	
	private	Date	createdDatetime	;
	
	private	String	updateby;
	
	@Column(name="UPDATE_DATETIME")			
	private	Date	updateDatetime	;
	
	private	String	active	;
	
	@Column(name="COMPANY_ID")			
	private	String	companyId	;

	public String getOccupancyStId() {
		return occupancyStId;
	}

	public void setOccupancyStId(String occupancyStId) {
		this.occupancyStId = occupancyStId;
	}

	public String getOccupancyStCode() {
		return occupancyStCode;
	}

	public void setOccupancyStCode(String occupancyStCode) {
		this.occupancyStCode = occupancyStCode;
	}

	public String getOccupancyStName() {
		return occupancyStName;
	}

	public void setOccupancyStName(String occupancyStName) {
		this.occupancyStName = occupancyStName;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public Date getCreatedDatetime() {
		return createdDatetime;
	}

	public void setCreatedDatetime(Date createdDatetime) {
		this.createdDatetime = createdDatetime;
	}

	public String getUpdateby() {
		return updateby;
	}

	public void setUpdateby(String updateby) {
		this.updateby = updateby;
	}

	public Date getUpdateDatetime() {
		return updateDatetime;
	}

	public void setUpdateDatetime(Date updateDatetime) {
		this.updateDatetime = updateDatetime;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}	
	

}