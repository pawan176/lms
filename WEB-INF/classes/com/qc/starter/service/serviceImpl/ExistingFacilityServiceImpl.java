package com.qc.starter.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qc.starter.dao.ExistingFacilityDao;
import com.qc.starter.entity.ExistingFacilityEntity;
import com.qc.starter.service.ExistingFacilityService;

@Service
public class ExistingFacilityServiceImpl implements ExistingFacilityService{

	@Autowired ExistingFacilityDao existingFacilityDao;
	
	@Override
	public String updateExistingFacility(List<ExistingFacilityEntity> existingFacilityHistorylist) {
		return existingFacilityDao.updateExistingFacility(existingFacilityHistorylist);
	}
	
	public String deleteExistingFacility(List<ExistingFacilityEntity> existingFacilityHistorylist){
		return existingFacilityDao.deleteExistingFacility(existingFacilityHistorylist);
	}

}
