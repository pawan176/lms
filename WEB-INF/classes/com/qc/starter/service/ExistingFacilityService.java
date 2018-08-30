package com.qc.starter.service;

import java.util.List;
import com.qc.starter.entity.ExistingFacilityEntity;

public interface ExistingFacilityService {
	
	public String updateExistingFacility(List<ExistingFacilityEntity> existingFacilityHistorylist);
	
	public String deleteExistingFacility(List<ExistingFacilityEntity> existingFacilityHistorylist);

}
