package com.qc.starter.dao;

import com.qc.starter.dto.ContactDto;

public interface ConvertToCustomerDao {
	
	public boolean convertLeadToLoan(String leadid,String userId,String companyId);

}
