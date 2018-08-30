package com.qc.starter.service;

import java.util.List;

import com.qc.starter.entity.PropertyEntity;
import com.qc.starter.entity.UserEntity;

public interface PropertyService {

	public List<PropertyEntity> getListOfProperty(String personalDetailId,String companyId);

	public boolean addProperty(PropertyEntity propertyEntity);

	public int deleteProperty(List<PropertyEntity> listProperty);

	public boolean updateProperty(List<PropertyEntity> listProperty);

	public int addPropertyList(List<PropertyEntity> toInsert);

	public int updatePropertyList(List<PropertyEntity> toUpdate);

	public void addUpdatePropertyList(List<PropertyEntity> list, String caseId,
			String personalDetailId, UserEntity userEntity);
	//------------added by deepak on 03 march--2016---
	public String getListOfProject(String devloperId,String companyId);

}
