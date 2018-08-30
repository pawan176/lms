package com.qc.starter.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="QT_ADDRESS", schema="QC_PROSPECT")
public class PinCodeEntity {
	  @Id
	  @Column(name="PINCODE")
	  private String pincode;
	  @Column(name="DIVISIONNAME")
	  private String divisionName;
		
    public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getDivisionName() {
		return divisionName;
	}
	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}
	  
	

	
}
