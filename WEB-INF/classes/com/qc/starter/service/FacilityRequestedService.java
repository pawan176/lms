package com.qc.starter.service;

import java.util.List;

import com.qc.starter.dto.ProductDto;
import com.qc.starter.entity.ExistingFacilityEntity;
import com.qc.starter.entity.UserEntity;

public interface FacilityRequestedService {
	
	public boolean updateExistingFacility(ProductDto productDto);

	public String addExistingFacility(UserEntity userEntity,List<ExistingFacilityEntity> existingFacilityHistoryInsert);

}
