package com.qc.starter.service.serviceImpl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qc.starter.dao.FacilityRequestedDao;
import com.qc.starter.dto.ProductDto;
import com.qc.starter.entity.ExistingFacilityEntity;
import com.qc.starter.entity.UserEntity;
import com.qc.starter.service.FacilityRequestedService;


@Service
public class FacilityRequestedServiceImpl implements FacilityRequestedService{
	
	@Autowired  FacilityRequestedDao facilityRequestedDao;
	@Autowired HttpSession httpSession;
	
	public boolean updateExistingFacility(ProductDto productDto){
		
		return facilityRequestedDao.updateExistingFacility(productDto);
		
	}

	@Override
	public String addExistingFacility(UserEntity userEntity, List<ExistingFacilityEntity> existingFacilityHistoryInsert) {
		
		return facilityRequestedDao.addExistingFacility(userEntity,existingFacilityHistoryInsert);
	}

}
