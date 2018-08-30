package com.qc.starter.mobileAction;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import com.qc.starter.dto.DocumentDto;

public class DocumentJsonParser {

	public List<DocumentDto> getJsonToDocumentDto(JSONObject requestJson,MultipartFile[] files){
		List<DocumentDto> documentList=null;
		try{
			documentList=new ArrayList<DocumentDto>();
		
			JSONArray docArr=requestJson.getJSONArray("documentsList");
			for(int i=0;i<docArr.length();i++){
				DocumentDto documentDto=new DocumentDto();
				JSONObject jsonObject=docArr.getJSONObject(i);
				//documentDto.setDocument(jsonObject.getString("document"));
				documentDto.setDocumentTypeId(jsonObject.getString("documentTypeId"));
				//documentDto.setDocumentTypeName(jsonObject.getString("documentTypeName"));
				//documentDto.setDocumentStatusId(jsonObject.getString("documentStatusId"));
				//documentDto.setDocumentStatusName(jsonObject.getString("documentStatusName"));
				documentDto.setReceivingDate(jsonObject.getString("receivingDate"));
				documentDto.setRemarks(jsonObject.getString("remarks"));
				//documentDto.setStatus(jsonObject.getString("status"));
				documentDto.setTargetDate(jsonObject.getString("targetDate"));
				//documentDto.setCreatedDate(jsonObject.getString("createdDate"));
				//documentDto.setDocCreatedByName(jsonObject.getString("docCreatedByName"));
				//documentDto.setDocUpdatedByName(jsonObject.getString("docUpdatedByName"));
				//documentDto.setUpdatedDate(jsonObject.getString("updatedDate"));
				//documentDto.setDocumentName(jsonObject.getString("documentName"));
				documentDto.setDocumentId(jsonObject.getString("documentNameId"));
				documentDto.setDocumentBlob(files[i]);
				//documentDto.setDocUpdatedById(jsonObject.getString("docUpdatedById"));
				//documentDto.setDocCreatedById(jsonObject.getString("docCreatedById"));
				documentDto.setDocStatusId(jsonObject.getString("docStatusId"));
				//documentDto.setDocStatusName(jsonObject.getString("docStatusName"));
				//documentDto.setCreatedBy(jsonObject.getString("createdBy"));
				//documentDto.setUpdatedBy(jsonObject.getString("updatedBy"));
				documentDto.setMakerId(jsonObject.getString("userId"));
				documentDto.setDocId(jsonObject.getString("docAction"));
				//documentDto.setPID(jsonObject.getString("pID"));
				documentList.add(documentDto);
			}

		}catch(Exception e){
       e.printStackTrace();
		}
		return documentList;
	}
}
