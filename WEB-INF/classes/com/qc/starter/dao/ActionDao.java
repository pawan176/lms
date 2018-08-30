package com.qc.starter.dao;

import com.qc.starter.dto.ContactDto;

public interface ActionDao {

	public String saveCaseAction(ContactDto contactDto);
	public boolean saveResolution(ContactDto contactDto,String caseId);

}
