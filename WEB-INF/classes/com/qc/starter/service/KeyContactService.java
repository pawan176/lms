package com.qc.starter.service;

import java.util.List;
import com.qc.starter.entity.KeyContactsEntity;
import com.qc.starter.entity.UserEntity;

public interface KeyContactService {
	
	public List<KeyContactsEntity> getKeyContactOfClient(String personalDetailId);

	public int updateKeyContactList(List<KeyContactsEntity> contacts);

	public int deleteContact(List<KeyContactsEntity> list);

	public int addKeyContactList(List<KeyContactsEntity> toInsert, String personalDtlId, String caseId, Integer userId);

	public void addUpdateKeyContactList(List<KeyContactsEntity> list,
			UserEntity userEntity, String caseId, String personalDetailId);


}
