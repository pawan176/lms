package com.qc.starter.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qc.starter.dao.ActionDao;
import com.qc.starter.dto.ContactDto;
import com.qc.starter.service.ActionService;

@Service
public class ActionServiceImpl implements ActionService {
	
	@Autowired ActionDao actionDao;
	
	public String saveCaseAction(ContactDto contactDto){
		//CaseActionHistoryEntity caseActionHistoryEntity = new CaseActionHistoryEntity();
		return actionDao.saveCaseAction(contactDto);
	}

}
