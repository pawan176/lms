package com.qc.starter.dao;

import java.util.List;

import com.qc.starter.dto.ContactDto;
import com.qc.starter.dto.PersonListDto;

public interface ContactDao {	
	public List caseActionHistory(String caseId,String companyId,String requestType);
	public List caseEscalationHistory(String leadid,String companyId);
	public String getLastupdateTime(String leadid);
	public boolean getLeadStatus(String caseId,String userId);
	public List<PersonListDto> getAllPerson(String caseId,String userId,String personType);
	public List getLeadEscalateDetail(String caseId,String userId);	
	public boolean saveHelpRequested(ContactDto contactDto);
	public List<PersonListDto> getSearchPerson();	
	public String getAllWorklistLeads(String caseId);
	public List caseAllocationHistory(String leadid,String companyId);


}
