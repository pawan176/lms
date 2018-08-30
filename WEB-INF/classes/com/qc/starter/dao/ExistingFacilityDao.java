package com.qc.starter.dao;

import java.util.List;

import com.qc.starter.entity.ExistingFacilityEntity;

public interface ExistingFacilityDao {

	public List getFacilitiesExisting(String leadid);

	public String updateExistingFacility(List<ExistingFacilityEntity> existingFacilityHistorylist);

	public String deleteExistingFacility(List<ExistingFacilityEntity> existingFacilityHistorylist);

}
