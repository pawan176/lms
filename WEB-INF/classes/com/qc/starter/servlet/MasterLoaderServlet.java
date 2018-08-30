package com.qc.starter.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qc.starter.basic.MasterLoaderContext;
import com.qc.starter.dao.MasterDao;
import com.qc.starter.dao.daoImpl.MasterDaoImpl;
import com.qc.starter.dto.MasterDto;

public class MasterLoaderServlet extends HttpServlet{
	
	public void init(ServletConfig config) throws ServletException {
		
		/*MasterDao masterDao = new MasterDaoImpl();
		MasterDto master = masterDao.getMasters("CityEntity~MaritalStatusEntity~GenderEntity~NationalityEntity~OccupationEntity~CompanyTypeEntity~SalaryModeEntity~StateMasterEntity~OccupancyStatusEntity~CaseContactEntity~PropertyTypeEntity~PropertyStatusEntity~AddressTypeEntity~ContactTypeEntity-CONTACT_CATEGORY='EMAIL'~ContactTypeEntity-CONTACT_CATEGORY='MOBILE'");
		MasterLoaderContext masterLoaderContext = MasterLoaderContext.getInstance();
		masterLoaderContext.setMaster(master);*/
		 
	}
	
}
