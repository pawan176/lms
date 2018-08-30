package com.qc.starter.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qc.starter.dao.CustomerDao;
import com.qc.starter.dto.CustomerDto;
import com.qc.starter.dto.LovDto;
import com.qc.starter.entity.CityEntity;
import com.qc.starter.entity.CustomerEntity;
import com.qc.starter.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired CustomerDao customerDao;

	/*@Override
	public String getTotalCustomer() {
		return customerDao.getTotalCustomer();
	}
*/
	@Override
	public CustomerEntity getCustomerFromLeadId(Integer leadId) {
		return customerDao.getCustomerFromLeadId(leadId);
	}

	@Override
	public boolean updateCustomerInfo(CustomerDto customerDto) {
		
		return customerDao.updateCustomerInfo(customerDto);
	}

	@Override
	public boolean updateOccupationInfo(CustomerDto customerDto) {
		// TODO Auto-generated method stub
		return customerDao.updateOccupationInfo(customerDto);
	}
	
	public String getPersonalDetailId(String caseId){
		return customerDao.getPersonalDetailId(caseId);
	}
	
	public List<String> getCompanyList(String query){
		return customerDao.getCompanyList(query);
	}
	
	public String getCompanyNameById(String companyId){
		return customerDao.getCompanyNameById(companyId);
	}
	
	
	public List<CityEntity> getCitiesByState(String stateId){
		return customerDao.getCitiesByState(stateId);
	}
	
	public String getCompanyIdByName(String companyId){
		return customerDao.getCompanyNameById(companyId);
	}
	
	public List<LovDto> getMasterList(String idColumnName,String valueColumnName,String dependentTableName,String crossTableName,String crossTableDependentId,String crossTableMasterId,String masterValue){
		return customerDao.getMasterList(idColumnName,valueColumnName,dependentTableName,crossTableName,crossTableDependentId,crossTableMasterId,masterValue);
	}

	@Override
	public boolean updateLeadDetails(CustomerDto customerDto) {
		// TODO Auto-generated method stub
		return customerDao.updateLeadDetails(customerDto);
	}

}