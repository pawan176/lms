package com.qc.starter.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//Created By Sumit on 12-July-2017
@Entity
@Table(name="QM_TYPE_OF_BUSINESS", schema="QC_PROSPECT_MASTER")
public class TypeOfBusinessEntity {
	@Id
	private	String	id	;
	private String code;
	
	@Column(name="TYPE_OF_BUSINESS")
	private String typeOfBusiness;
	
	private String active;
	
	@Column(name="CREATED_DATETIME")
	private String createdDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTypeOfBusiness() {
		return typeOfBusiness;
	}

	public void setTypeOfBusiness(String typeOfBusiness) {
		this.typeOfBusiness = typeOfBusiness;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	
	

}
