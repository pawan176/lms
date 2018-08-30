package com.qc.starter.service;

import java.util.List;

import com.qc.starter.dto.DocumentDto;
import com.qc.starter.entity.DocumentEntity;

public interface DocumentService {
	//public List<DocumentDto> getDocumentHistory(String string,String syncDate);
	public List<DocumentDto> getDocumentHistory(String string);
	public List<DocumentDto> getDocumentName(String docType);
	public List<DocumentDto> saveUpdateDocument(List<DocumentDto> list,int caseId);
	public List<DocumentEntity> getDocumentBlob(String docId,String docTypeId);
	public String deleteDocument(List<DocumentDto> list,int caseId);
	public String deleteDocumentBlob(List<DocumentDto> list);
	//
}