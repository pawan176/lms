package com.qc.starter.dao;

import java.util.List;

import com.qc.starter.dto.MasterDto;
import com.qc.starter.entity.DocumentEntity;
import com.qc.starter.entity.PinCodeEntity;

public interface MasterDao {

	public List<List<Object>> getMasters();

	public MasterDto getMasters(String value);

	public List<String> getProdTypeMasterIdList(String prodTypeName);

	public List<DocumentEntity> getDocumentTypeList(int companyId,String productId);

	public List<DocumentEntity> getDocumentStatusList();

	public List<DocumentEntity> getDocumentCreatedByList();

	public List<DocumentEntity> getDocumentUpdatedByList();	
	 
    public List<PinCodeEntity> getPinCode(String pinCode);
}
