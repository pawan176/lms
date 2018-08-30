package com.qc.starter.dao;

import java.text.ParseException;
import java.util.List;

import com.qc.starter.dto.DocumentDto;
import com.qc.starter.entity.DocumentEntity;


public interface DocumentDao {

	//public List<DocumentDto> getDocumentHistory(String string,String syncDate);
	public List<DocumentDto> getDocumentHistory(String string);
	List<DocumentDto> getDocumentName(String docType);
	public List<DocumentDto> saveUpdateDocument(List<DocumentDto> list,int caseId) throws ParseException;
	public List<DocumentEntity> getDocumentBlob(String docId,String docTypeId);

	public String deleteDocument(List<DocumentDto> list,int caseId);
	public String deleteDocumentBlob(List<DocumentDto> list);
}