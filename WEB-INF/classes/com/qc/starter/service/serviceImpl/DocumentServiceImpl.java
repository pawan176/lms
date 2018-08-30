package com.qc.starter.service.serviceImpl;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qc.starter.dao.DocumentDao;
import com.qc.starter.dto.DocumentDto;
import com.qc.starter.entity.DocumentEntity;
import com.qc.starter.service.DocumentService;

@Service
public class DocumentServiceImpl implements DocumentService {

	@Autowired
	DocumentDao documentDao;

	// extra add
	@Override
	public List<DocumentDto> getDocumentHistory(String leadId) {
		//public List<DocumentDto> getDocumentHistory(String leadId,String syncDate) {
		List<DocumentDto> lst = documentDao.getDocumentHistory(leadId);
		return lst;
	}

	@Override
	public List<DocumentDto> getDocumentName(String docType) {
		List<DocumentDto> list = documentDao.getDocumentName(docType);
		return list;
	}

	public List<DocumentDto> saveUpdateDocument(List<DocumentDto> list, int caseId) {
		// extra
		/*
		 * List<DocumentDto> toUpdate = new ArrayList<DocumentDto>();
		 * List<DocumentDto> toInsert = new ArrayList<DocumentDto>(); try{
		 * for(DocumentDto doc : list){ if(doc!=null &&
		 * doc.getDocumentId()!=null){
		 * if(doc.getDocumentId().equalsIgnoreCase("insert") &&
		 * doc.getDocumentId()!=null){ toInsert.add(doc); }else{
		 * toUpdate.add(doc); } } } String
		 * status=documentDao.saveUpdateDocument(toInsert); String
		 * status1=documentDao.saveUpdateDocument(toUpdate);
		 * 
		 * }catch(Exception e){ e.printStackTrace(); }finally{ toUpdate = null;
		 * toInsert = null; }
		 */

		String status = "";
		try {
			list = documentDao.saveUpdateDocument(list, caseId);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public List<DocumentEntity> getDocumentBlob(String docId,String docTypeId) {
		List<DocumentEntity> list = documentDao.getDocumentBlob(docId,docTypeId);
		return list;
	}


	public String deleteDocument(List<DocumentDto> list,int caseId) {
		String status = documentDao.deleteDocument(list,caseId);
		return status;
	}


	public String deleteDocumentBlob(List<DocumentDto> list) {
		String status = documentDao.deleteDocumentBlob(list);
		return status;
	}

	/*public String deleteDocument(List<DocumentDto> list,int caseId);
	public String deleteDocumentBlob(List<DocumentDto> list);*/
	
	
	
}