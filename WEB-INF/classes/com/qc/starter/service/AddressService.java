package com.qc.starter.service;

import java.util.List;

import com.qc.starter.entity.AddressEntity;
import com.qc.starter.entity.UserEntity;

public interface AddressService {
	
	public AddressEntity getResAddress(String personalDetailId);
	
	public AddressEntity getOffAddress(String personalDetailId);

	public boolean updateResAddress(AddressEntity addressEntity);

	public List<AddressEntity> getAddressList(String personalDetailId);

	public int updateAddressList(List<AddressEntity> toUpdate, String caseId,Integer userId);

	public int insertAddress(List<AddressEntity> toInsert, String caseId, Integer userId);

	public int deleteAddressList(List<AddressEntity> list);

	public boolean addUpdateAddress(List<AddressEntity> list,String userId, String caseId, String personalDetailId);
	
	public List<AddressEntity> addUpdateAddressMob(List<AddressEntity> list,String userId, String caseId, String personalDetailId);
	
	public List<AddressEntity> saveAddressFromProcedure(List<AddressEntity> list,String userId, String caseId);
	

}