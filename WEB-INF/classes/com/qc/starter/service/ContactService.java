package com.qc.starter.service;

import java.util.List;

import com.qc.starter.dto.ContactDto;
import com.qc.starter.dto.PersonListDto;

public interface ContactService {
	
	public ContactDto getContactDetail(String leadid,String tab,String userId,String companyId,String requestType);
	public boolean getLeadStatus(String caseId,String userId);
	public List<PersonListDto> getAllPerson(String caseId,String userId,String personType);
	public boolean saveResolution(ContactDto contactDto,String caseId);
	public boolean saveHelpRequested(ContactDto contactDto);
	public List<PersonListDto> getSearchPerson();
	public String getAllWorklistLeads(String caseId);
}
