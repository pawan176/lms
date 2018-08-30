package com.qc.starter.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "QC_PROSPECT_MASTER", name="QM_DOCUMENT" )
public class DocumentEntity {
	
	@Id
	@Column(name="DOCUMENTID")
	private	String	documentId;	
	@Column(name="DOCUMENTCODE")
	private	String documentCode;
	@Column(name="DOCUMENTNAME")
	private	String documentName;
	@Column(name="DOCUMENTTYPEID")
	private	String documentTypeId;
	@Column(name="DISPLAYNAME")
	private	String documentTypeName;
	@Column(name="DESCRIPTION")
	private	String remarks;
	@Column(name="PRODUCTID")
	private String productId;
	@Column(name="CREATEDBY")
	private	String docCreatedByName;
	@Column(name="CREATED_DATETIME")
	private	Date	createdDate;
	@Column(name="UPDATEBY")
	private	String	docUpdatedByName;
	@Column(name="UPDATE_DATETIME")	
	private	Date updatedDate;	
	@Column(name="MAKER_ID")
	private	String	makerId;
	@Column(name="MAKER_DATE")
	private	Date	makerDate;
	@Column(name="MAKER_SYSDATE")
	private	Date	makerSysDate;
	@Column(name="MAKER_REMARKS")
	private	String	makerRemarks;
	private	String	active;
	@Column(name="AUTH_ID")
	private	String	authId;
	@Column(name="AUTH_DATE")
	private	Date authDate;
	@Column(name="AUTH_SYSDATE")
	private	Date authSysDate;
	@Column(name="AUTH_REMARK")
	private	String	authRemark;
	@Column(name="COMPANY_ID")
	private	String	companyId;
	@Column(name="P_DOCCOLLECTIONSTATUSID")
	private	String status;
	@Column(name="DOCUMENTSTATUSID")
	private	String docStatusId;
	@Column(name="DOCUMENTSTATUSNAME")
	private	String docStatusName;
	



	//need to clarify
	@Column(name="P_RECIEVEDDATE")
	private	Date receivingDate;


	public String getDocumentId() {
		return documentId;
	}


	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}


	public String getDocumentCode() {
		return documentCode;
	}


	public void setDocumentCode(String documentCode) {
		this.documentCode = documentCode;
	}


	public String getDocumentName() {
		return documentName;
	}


	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}


	public String getDocumentTypeId() {
		return documentTypeId;
	}


	public void setDocumentTypeId(String documentTypeId) {
		this.documentTypeId = documentTypeId;
	}

	public String getDocumentTypeName() {
		return documentTypeName;
	}


	public void setDocumentTypeName(String documentTypeName) {
		this.documentTypeName = documentTypeName;
	}


	public String getRemarks() {
		return remarks;
	}


	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}


	public String getProductId() {
		return productId;
	}


	public void setProductId(String productId) {
		this.productId = productId;
	}


	public String getDocCreatedByName() {
		return docCreatedByName;
	}


	public void setDocCreatedByName(String docCreatedByName) {
		this.docCreatedByName = docCreatedByName;
	}


	public Date getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}


	public String getDocUpdatedByName() {
		return docUpdatedByName;
	}


	public void setDocUpdatedByName(String docUpdatedByName) {
		this.docUpdatedByName = docUpdatedByName;
	}


	public Date getUpdatedDate() {
		return updatedDate;
	}


	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}


	public String getMakerId() {
		return makerId;
	}


	public void setMakerId(String makerId) {
		this.makerId = makerId;
	}


	public Date getMakerDate() {
		return makerDate;
	}


	public void setMakerDate(Date makerDate) {
		this.makerDate = makerDate;
	}


	public Date getMakerSysDate() {
		return makerSysDate;
	}


	public void setMakerSysDate(Date makerSysDate) {
		this.makerSysDate = makerSysDate;
	}


	public String getMakerRemarks() {
		return makerRemarks;
	}


	public void setMakerRemarks(String makerRemarks) {
		this.makerRemarks = makerRemarks;
	}


	public String getActive() {
		return active;
	}


	public void setActive(String active) {
		this.active = active;
	}


	public String getAuthId() {
		return authId;
	}


	public void setAuthId(String authId) {
		this.authId = authId;
	}


	public Date getAuthDate() {
		return authDate;
	}


	public void setAuthDate(Date authDate) {
		this.authDate = authDate;
	}


	public Date getAuthSysDate() {
		return authSysDate;
	}


	public void setAuthSysDate(Date authSysDate) {
		this.authSysDate = authSysDate;
	}


	public String getAuthRemark() {
		return authRemark;
	}


	public void setAuthRemark(String authRemark) {
		this.authRemark = authRemark;
	}


	public String getCompanyId() {
		return companyId;
	}


	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getDocStatusId() {
		return docStatusId;
	}


	public void setDocStatusId(String docStatusId) {
		this.docStatusId = docStatusId;
	}


	public String getDocStatusName() {
		return docStatusName;
	}


	public void setDocStatusName(String docStatusName) {
		this.docStatusName = docStatusName;
	}


	public Date getReceivingDate() {
		return receivingDate;
	}


	public void setReceivingDate(Date receivingDate) {
		this.receivingDate = receivingDate;
	}	
	
}