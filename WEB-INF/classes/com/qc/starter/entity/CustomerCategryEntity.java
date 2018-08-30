package com.qc.starter.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="QM_CUSTOMERCATEGORY", schema="QC_PROSPECT_MASTER")
public class CustomerCategryEntity {
	
	
	@Id
	private	String	customercategortId	;
	private	String	customercategortCode	;
	private	String	customercategortName	;
	private	String	displayName	;	
	private	String	active	;
	public String getCustomercategortId() {
		return customercategortId;
	}
	public void setCustomercategortId(String customercategortId) {
		this.customercategortId = customercategortId;
	}
	public String getCustomercategortCode() {
		return customercategortCode;
	}
	public void setCustomercategortCode(String customercategortCode) {
		this.customercategortCode = customercategortCode;
	}
	public String getCustomercategortName() {
		return customercategortName;
	}
	public void setCustomercategortName(String customercategortName) {
		this.customercategortName = customercategortName;
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
