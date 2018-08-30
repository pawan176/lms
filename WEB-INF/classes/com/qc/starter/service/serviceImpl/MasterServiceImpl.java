package com.qc.starter.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qc.starter.dao.MasterDao;
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
import com.qc.starter.service.MasterService;

@Service
public class MasterServiceImpl implements MasterService {

	@Autowired MasterDao masterDao;
	public MasterDto getAllMasters() {
		MasterDto masterDto = new MasterDto();
		List<List<Object>> list = masterDao.getMasters();
		masterDto.setMaritalStatus((List)list.get(0));
		masterDto.setGender((List)list.get(1));
		masterDto.setNationality((List)list.get(2));
		masterDto.setOccupationType((List)list.get(3));
		masterDto.setCompanyType((List)list.get(4));
		masterDto.setCompanyName((List)list.get(5));
		masterDto.setSalaryMode((List)list.get(6));
		masterDto.setCity((List)list.get(7));
		masterDto.setOccupancyStatus((List)list.get(8));
		masterDto.setContact((List)list.get(9));
		masterDto.setPropType((List)list.get(10));
		masterDto.setPropStatus((List)list.get(11));
		masterDto.setProductMaster((List)list.get(12));
		masterDto.setBankMaster((List)list.get(13));
		masterDto.setCampaignMaster((List)list.get(14));
		masterDto.setSourceMaster((List)list.get(15));
		masterDto.setSubQueueMaster((List)list.get(16));
		masterDto.setContactTypeMobile((List)list.get(17));
		masterDto.setContactTypeEmail((List)list.get(18));
		masterDto.setCaseActionMaster((List)list.get(19));
		masterDto.setDispositionMaster((List)list.get(20));
		masterDto.setActionMaster((List)list.get(21));
		masterDto.setAddressType((List)list.get(22));
		masterDto.setStateList((List)list.get(23));
		return masterDto;
	}
	
	
	public CustomerDto setEntityValues(CustomerDto customerDto,
			CustomerEntity customerEntity, List<AddressEntity> list,
			List<KeyContactsEntity> listKeyContacts,
			List<PropertyEntity> listProperty,
			List<MobileEntity> listMobile,
			List<EmailEntity> listEmail) {
		customerDto.setCustomerValues(customerEntity);
		if(list!=null && list.size()>0)customerDto.setListAddress(list);
		if(listKeyContacts!=null)customerDto.setKeyContacts(listKeyContacts);
		if(listProperty!=null)customerDto.setListProperty(listProperty);
		if(listMobile!=null)customerDto.setListMobile(listMobile);
		if(listEmail!=null)customerDto.setListEmail(listEmail);
		return customerDto;
	}

	public MasterDto getMasters(String value) {
		return masterDao.getMasters(value);
	}
	
	public List<DocumentEntity> getDocumentTypeList(int companyId,String productId){
		return masterDao.getDocumentTypeList(companyId, productId);
	}
	
	//public List<DocumentEntity> getDocumentCreatedByList();
	//public List<DocumentEntity> getDocumentUpdatedByList();
	
	public List<DocumentEntity> getDocumentStatusList(){
		return masterDao.getDocumentStatusList();
	}
	
	public List<DocumentEntity> getDocumentCreatedByList(){
		return masterDao.getDocumentCreatedByList();
	}
	
	public List<DocumentEntity> getDocumentUpdatedByList(){
		return masterDao.getDocumentUpdatedByList();
	}
	@Override
	public List<PinCodeEntity> getPinCode(String pinCode) {
		return masterDao.getPinCode(pinCode);
	}
	

}