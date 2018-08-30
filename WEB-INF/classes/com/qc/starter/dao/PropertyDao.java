package com.qc.starter.dao;

import java.util.List;

import com.qc.starter.entity.ProjectEntity;
import com.qc.starter.entity.PropertyEntity;

public interface PropertyDao {

	public List<PropertyEntity> getListOfProperty(String personalDetailId,String CompanyId);

	public boolean addProperty(PropertyEntity propertyEntity);

	public int deleteProperty(List<PropertyEntity> list);

	public boolean updateProperty(List<PropertyEntity> list);

	public int addPropertyList(List<PropertyEntity> toInsert);

	public int updatePropertyList(List<PropertyEntity> toUpdate);

	//------------added by deepak on march--2016---
	public List<ProjectEntity> getListOfProject(String devloperId,String companyId);
}
