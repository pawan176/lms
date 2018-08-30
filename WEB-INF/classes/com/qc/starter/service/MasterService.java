package com.qc.starter.service;

import java.util.List;

import com.qc.starter.dto.CustomerDto;
import com.qc.starter.dto.MasterDto;
import com.qc.starter.entity.AddressEntity;
import com.qc.starter.entity.CustomerEntity;
import com.qc.starter.entity.DocumentEntity;
import com.qc.starter.entity.EmailEntity;
import com.qc.starter.entity.KeyContactsEntity;
import com.qc.starter.entity.MobileEntity;
import com.qc.starter.entity.PinCodeEntity;
import com.qc.starter.entity.PropertyEntity;

public interface MasterService {

	public MasterDto getAllMasters();

	public CustomerDto setEntityValues(CustomerDto customerDto,
			CustomerEntity customerEntity, List<AddressEntity> list,
			List<KeyContactsEntity> listKeyContacts,
			List<PropertyEntity> listProperty,
			List<MobileEntity> listMobile,
			List<EmailEntity> listEmail);

	public MasterDto getMasters(String value);
	public List<DocumentEntity> getDocumentTypeList(int companyId,String productId);
	public List<DocumentEntity> getDocumentStatusList();
	public List<DocumentEntity> getDocumentCreatedByList();
	public List<DocumentEntity> getDocumentUpdatedByList();	
	public List<PinCodeEntity> getPinCode(String pinCode);
	
}