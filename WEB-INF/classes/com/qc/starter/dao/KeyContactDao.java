package com.qc.starter.dao;

import java.util.List;

import com.qc.starter.entity.KeyContactsEntity;

public interface KeyContactDao {

	public List<KeyContactsEntity> getKeyContactListOfCClient(String personalDetailId);

	public int updateKeyContactList(List<KeyContactsEntity> contacts);

	public int deleteContact(List<KeyContactsEntity> list);

	public int addKeyContactList(List<KeyContactsEntity> toInsert);

}
