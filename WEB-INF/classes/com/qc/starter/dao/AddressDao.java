package com.qc.starter.dao;

import java.util.List;

import com.qc.starter.entity.AddressEntity;

public interface AddressDao {

	public AddressEntity getResAddress(String personalDetailId);

	public AddressEntity getOffAddress(String personalDetailId);

	public boolean updateResAddress(AddressEntity addressEntity);

	public List<AddressEntity> listAddress(String personalDetailId);

	public int updateAddressList(List<AddressEntity> toUpdate);

	public int insertAddressList(List<AddressEntity> toInsert);

	public int deleteAddressList(List<AddressEntity> toDelete);

	public boolean updateAllAddressList(String caseId);
	public List<AddressEntity> addressListdetail(String caseid);
	
	public List<AddressEntity> saveAddressFromProcedure(String insertString,String updateString, String caseId);
}