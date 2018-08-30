package com.qc.starter.dao.daoImpl;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qc.starter.dao.ConvertToCustomerDao;
import com.qc.starter.dto.ContactDto;

@Repository
public class ConvertToCustomerDaoImpl implements ConvertToCustomerDao{
	
	private static final Logger logger = Logger.getLogger(LoginDaoImpl.class.getName());
	@Autowired	SessionFactory sessionFactory;
	
	public boolean convertLeadToLoan(String leadid,String userId,String companyId){
		return false;
	}

}
