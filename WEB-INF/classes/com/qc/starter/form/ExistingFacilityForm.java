package com.qc.starter.form;

import java.util.List;

import com.qc.starter.entity.ExistingFacilityEntity;


public class ExistingFacilityForm {

	private List<ExistingFacilityEntity> existingFacilityHistory;

	public List<ExistingFacilityEntity> getExistingFacilityHistory() {
		return existingFacilityHistory;
	}

	public void setExistingFacilityHistory(List<ExistingFacilityEntity> contacts) {
		this.existingFacilityHistory = contacts;
	}
}
