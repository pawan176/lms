package com.qc.starter.dao;

import java.util.List;

import com.qc.starter.entity.MobileEntity;

public interface MobileDao {

	public List<MobileEntity> getListOfMobile(int personalDetailId);

	public void addDefaultMobile(MobileEntity mobileEntity);

	public void addMobile(MobileEntity mobileEntity);

	public void deleteMobile(List<String> toDelete);

	public void updateMobile(List<MobileEntity> toUpdate);

	public void addMobileList(List<MobileEntity> toInsert);

	public void updateMobileList(List<MobileEntity> toUpdate);

	public void updateAllMobileList(String caseId);
}
