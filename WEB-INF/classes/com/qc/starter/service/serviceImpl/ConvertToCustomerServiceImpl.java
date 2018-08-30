package com.qc.starter.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qc.starter.dao.ConvertToCustomerDao;
import com.qc.starter.dto.ContactDto;
import com.qc.starter.service.ConvertToCustomerService;

@Service
public class ConvertToCustomerServiceImpl implements ConvertToCustomerService{
	
	@Autowired ConvertToCustomerDao convertToCustomerDao;

	@Override
	public boolean convertLeadToLoan(String leadid,String userId,String companyId) {
		// TODO Auto-generated method stub
		return false;
	}

}
