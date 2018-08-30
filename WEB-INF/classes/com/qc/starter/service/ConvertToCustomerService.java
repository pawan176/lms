package com.qc.starter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qc.starter.dao.ContactDao;
import com.qc.starter.dao.ConvertToCustomerDao;
import com.qc.starter.dto.ContactDto;


public interface ConvertToCustomerService {
	 
	public boolean convertLeadToLoan(String leadid,String userId,String companyId);
	

}
