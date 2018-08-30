package com.qc.starter.dao;

import java.util.List;

import com.qc.starter.dto.ProductDto;
import com.qc.starter.entity.ExistingFacilityEntity;
import com.qc.starter.entity.UserEntity;

public interface FacilityRequestedDao {

	public ProductDto getFacilitiesRequested(String caseID);

	public boolean updateExistingFacility(ProductDto productDto);

	public String addExistingFacility(UserEntity userEntity ,List<ExistingFacilityEntity> existingFacilityHistoryInsert);

}
