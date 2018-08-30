package com.qc.starter.dao;

import java.util.List;

import com.qc.starter.dto.CustomerDto;
import com.qc.starter.dto.LovDto;
import com.qc.starter.entity.CityEntity;
import com.qc.starter.entity.CustomerEntity;

public interface CustomerDao {

	//public String getTotalCustomer();

	public CustomerEntity getCustomerFromLeadId(Integer leadId);

	public String getPersonalDetailId(String caseId);

	public boolean updateCustomerInfo(CustomerDto customerDto);

	public boolean updateOccupationInfo(CustomerDto customerDto);

	public List<String> getCompanyList(String query);

	public String getCompanyNameById(String companyId);

	public List<CityEntity> getCitiesByState(String stateId);

	public String getCompanyIdByName(String companyId);
	
	public List<LovDto> getMasterList(String idColumnName,String valueColumnName,String dependentTableName,String crossTableName,String crossTableDependentId,String crossTableMasterId,String masterValue);
	
	public boolean updateLeadDetails(CustomerDto customerDto);
	
	
}