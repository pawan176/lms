package com.qc.starter.service;

import java.util.List;

import com.qc.starter.entity.MobileEntity;
import com.qc.starter.entity.UserEntity;

public interface MobileService {

	public List<MobileEntity> getListOfMobile(int caseId);

	public void addMobile(MobileEntity mobileEntity);

	public void deleteMobile(List<MobileEntity> list);

	public void updateMobile(List<MobileEntity> list, String caseId);

	public boolean addUpdateContact(List<MobileEntity> listMobile,String caseId, String userId);

}
