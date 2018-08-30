package com.qc.starter.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="QM_CUSTENTITYTYPE", schema="QC_PROSPECT_MASTER")
public class EntityTypeEntity {
	@Id
	private	String	custEntityTypeId	;
	private	String	custEntityTypeCode	;
	private	String	custEntityTypeName	;
	private	String	displayName	;	
	private	String	active	;
	
	public String getCustEntityTypeId() {
		return custEntityTypeId;
	}
	public void setCustEntityTypeId(String custEntityTypeId) {
		this.custEntityTypeId = custEntityTypeId;
	}
	public String getCustEntityTypeCode() {
		return custEntityTypeCode;
	}
	public void setCustEntityTypeCode(String custEntityTypeCode) {
		this.custEntityTypeCode = custEntityTypeCode;
	}
	public String getCustEntityTypeName() {
		return custEntityTypeName;
	}
	public void setCustEntityTypeName(String custEntityTypeName) {
		this.custEntityTypeName = custEntityTypeName;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	
	
	
}
