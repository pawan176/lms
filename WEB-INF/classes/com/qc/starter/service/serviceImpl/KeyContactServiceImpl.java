package com.qc.starter.service.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qc.starter.dao.KeyContactDao;
import com.qc.starter.entity.KeyContactsEntity;
import com.qc.starter.entity.UserEntity;
import com.qc.starter.service.KeyContactService;

@Service
public class KeyContactServiceImpl implements KeyContactService {
	private static final Logger logger = Logger
			.getLogger(KeyContactServiceImpl.class.getName());
	@Autowired
	KeyContactDao keyContactDao;

	@Override
	public List<KeyContactsEntity> getKeyContactOfClient(String personalDetailId) {
		return keyContactDao.getKeyContactListOfCClient(personalDetailId);
	}

	@Override
	public int updateKeyContactList(List<KeyContactsEntity> contacts) {

		return keyContactDao.updateKeyContactList(contacts);
	}

	@Override
	public int deleteContact(List<KeyContactsEntity> list) {
		logger.info("KeyContactServiceImpl | deleteContact() | :-START");
		List<KeyContactsEntity> contacts = new ArrayList<KeyContactsEntity>();
		for (KeyContactsEntity key : list) {
			if (key.getKeyContactId() != null
					&& !key.getKeyContactId().equalsIgnoreCase("active")) {
				contacts.add(key);
			}
		}
		logger.info("KeyContactServiceImpl | deleteContact() | :-END");
		return keyContactDao.deleteContact(contacts);

	}

	@Override
	public int addKeyContactList(List<KeyContactsEntity> toInsert,
			String personalDtlId, String caseId, Integer userId) {
		for (KeyContactsEntity key : toInsert) {
			key.setPersonalDtlId(personalDtlId);
			key.setCaseId(caseId);
			key.setCreatedBy(userId.toString());
		}
		return keyContactDao.addKeyContactList(toInsert);
	}

	@Override
	public void addUpdateKeyContactList(List<KeyContactsEntity> list,
			UserEntity userEntity, String caseId, String personalDetailId) {
		logger.info("KeyContactServiceImpl | addUpdateKeyContactList | :-START");
		List<KeyContactsEntity> toUpdate = new ArrayList<KeyContactsEntity>();
		List<KeyContactsEntity> toInsert = new ArrayList<KeyContactsEntity>();
		try {
			for (KeyContactsEntity key : list) {
				if (key != null) {
					if (key.getKeyContactId() != null) {
						if (key.getKeyContactId().equalsIgnoreCase("Active")) {
							if (key.getFname() != null) {
								key.setCaseId(caseId);
								key.setPersonalDtlId(personalDetailId);
								key.setCreatedBy(userEntity.getUserid()
										.toString());
								key.setCreatedDate(new Date());
								key.setCreatedSysDate(new Date());
								toInsert.add(key);
							}
						} else {
							key.setCaseId(caseId);
							key.setPersonalDtlId(personalDetailId);
							key.setUpdatedBy(userEntity.getUserid().toString());
							key.setUpdatedDate(new Date());
							key.setUpdatedSysDate(new Date());
							toUpdate.add(key);
						}
					}
				}
			}
			if(toInsert.size()>0)
			keyContactDao.addKeyContactList(toInsert);
			if(toUpdate.size()>0)
			keyContactDao.updateKeyContactList(toUpdate);
		} catch (Exception e) {
			logger.error("KeyContactServiceImpl | addUpdateKeyContactList |"
					+ e.getMessage() + " :-END");
		} finally {
			toUpdate = null;
			toInsert = null;
		}
		logger.info("KeyContactServiceImpl | addUpdateKeyContactList | :-END");

	}

}
