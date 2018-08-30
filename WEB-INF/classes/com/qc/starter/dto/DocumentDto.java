package com.qc.starter.dto;

import org.springframework.web.multipart.MultipartFile;

public class DocumentDto {

	private String document;
	private String documentTypeId;
	private String documentTypeName;
	private String documentStatusId;
	private String documentStatusName;
	private String receivingDate;
	private String remarks;
	private String status;
	private String targetDate;
	private String createdDate;
	private String docCreatedByName;
	private String docUpdatedByName;
	private String updatedDate;
	private String documentName;
	private String documentId;
	private MultipartFile documentBlob;
	//private CommonsMultipartFile documentBlob;
	private String docUpdatedById;
	private String docCreatedById;
	private	String docStatusId;
	private	String docStatusName;
	private	String createdBy;
	private	String updatedBy;
	private String makerId;
	private String docId;
	private String PID;
	//----------added by Deepak on 17----------------
		private String fileName;




	public String getFileName() {
			return fileName;
		}
		public void setFileName(String fileName) {
			this.fileName = fileName;
		}
	public String getPID() {
		return PID;
	}
	public void setPID(String pID) {
		PID = pID;
	}
	public String getDocId() {
		return docId;
	}
	public void setDocId(String docId) {
		this.docId = docId;
	}
	public String getMakerId() {
		return makerId;
	}
	public void setMakerId(String makerId) {
		this.makerId = makerId;
	}
	public String getDocument() {
		return document;
	}
	public void setDocument(String document) {
		this.document = document;
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
	public String getDocumentStatusId() {
		return documentStatusId;
	}
	public void setDocumentStatusId(String documentStatusId) {
		this.documentStatusId = documentStatusId;
	}
	public String getDocumentStatusName() {
		return documentStatusName;
	}
	public void setDocumentStatusName(String documentStatusName) {
		this.documentStatusName = documentStatusName;
	}
	public String getReceivingDate() {
		return receivingDate;
	}
	public void setReceivingDate(String receivingDate) {
		this.receivingDate = receivingDate;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTargetDate() {
		return targetDate;
	}
	public void setTargetDate(String targetDate) {
		this.targetDate = targetDate;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getDocCreatedByName() {
		return docCreatedByName;
	}
	public void setDocCreatedByName(String docCreatedByName) {
		this.docCreatedByName = docCreatedByName;
	}
	public String getDocUpdatedByName() {
		return docUpdatedByName;
	}
	public void setDocUpdatedByName(String docUpdatedByName) {
		this.docUpdatedByName = docUpdatedByName;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}
	public String getDocumentName() {
		return documentName;
	}
	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}
	public String getDocumentId() {
		return documentId;
	}
	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}

	public MultipartFile getDocumentBlob() {
		return documentBlob;
	}
	public void setDocumentBlob(MultipartFile documentBlob) {
		this.documentBlob = documentBlob;
	}
	public String getDocUpdatedById() {
		return docUpdatedById;
	}
	public void setDocUpdatedById(String docUpdatedById) {
		this.docUpdatedById = docUpdatedById;
	}
	public String getDocCreatedById() {
		return docCreatedById;
	}
	public void setDocCreatedById(String docCreatedById) {
		this.docCreatedById = docCreatedById;
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
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}






}