package com.qc.starter.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="QM_GEO",schema="QC_PROSPECT_MASTER")
public class PurposeOfLoan {
	
	@Id
	private	String	purposeOfLoanId	;
	private	String	purposeOfLoanCode	;
	private	String	purposeOfLoanName	;
	private	String	displayName	;
	private	String	description	;
	private	String	productId;
	public String getPurposeOfLoanId() {
		return purposeOfLoanId;
	}
	public void setPurposeOfLoanId(String purposeOfLoanId) {
		this.purposeOfLoanId = purposeOfLoanId;
	}
	public String getPurposeOfLoanCode() {
		return purposeOfLoanCode;
	}
	public void setPurposeOfLoanCode(String purposeOfLoanCode) {
		this.purposeOfLoanCode = purposeOfLoanCode;
	}
	public String getPurposeOfLoanName() {
		return purposeOfLoanName;
	}
	public void setPurposeOfLoanName(String purposeOfLoanName) {
		this.purposeOfLoanName = purposeOfLoanName;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	                 
}
