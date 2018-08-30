package com.qc.starter.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qc.starter.dao.MasterForMobileDao;
import com.qc.starter.service.MasterForMobileService;

@Service
public class MasterForMobileServiceimpl implements MasterForMobileService{

	@Autowired MasterForMobileDao mobileDao;
	public String getAllMasters(String value) {
		return mobileDao.getAllMasters(value);
	}
	public String saveAgentSummary(String requestJson) {
		return mobileDao.saveAgentSummary(requestJson);
	}
	public String getAgentSummary(String requestJson) {
		return mobileDao.getAgentSummary(requestJson);
	}
}
