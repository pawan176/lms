package com.qc.starter.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qc.starter.dao.ActionDao;
import com.qc.starter.dao.ContactDao;
import com.qc.starter.dto.CaseActionHistoryDto;
import com.qc.starter.dto.CaseAllocationHistoryDto;
import com.qc.starter.dto.CaseEscalationHistoryDto;
import com.qc.starter.dto.ContactDto;
import com.qc.starter.dto.PersonListDto;
import com.qc.starter.service.ContactService;

@Service
public class ContactServiceImpl implements ContactService{

	@Autowired ContactDao contactdao;
	@Autowired ActionDao actionDao;

	public ContactDto getContactDetail(String caseId,String tab,String userId,String companyId,String requestType){
		ContactDto contactDto = new ContactDto();

		/*List actionList = contactdao.caseActionHistory(caseId);
		List<CaseActionHistoryDto> caseActionHistoryList = new ArrayList<CaseActionHistoryDto>();		
		if(actionList != null){
			for(Object object : actionList ){
				Map map = (Map)object;
				CaseActionHistoryDto caseActionHistoryDto = new CaseActionHistoryDto();
				caseActionHistoryDto.setCaseId(map.get("CASE_ID")!=null ? map.get("CASE_ID").toString() : "" );
				caseActionHistoryDto.setAction(map.get("ACTION")!=null ? map.get("ACTION").toString() : "" );
				caseActionHistoryDto.setActionBy(map.get("ACTION_BY")!=null ? map.get("ACTION_BY").toString() : "" );
				caseActionHistoryDto.setActionDate(map.get("ACTION_DATE")!=null ? map.get("ACTION_DATE").toString() : "" );
				caseActionHistoryDto.setRemarks(map.get("REMARKS")!=null ? map.get("REMARKS").toString() : "" );
				caseActionHistoryList.add(caseActionHistoryDto);
			}
		}*/
		//List escalationList = contactdao.caseEscalationHistory(caseId);
		//List<CaseEscalationHistoryDto> caseEscalationHistorylist = new ArrayList<CaseEscalationHistoryDto>();		
		/*if(escalationList != null){
			for(Object object : escalationList ){
				Map map = (Map)object;
				CaseEscalationHistoryDto caseEscalationHistoryDto = new CaseEscalationHistoryDto();
				caseEscalationHistoryDto.setActionName(map.get("ACTION_NAME")!=null ? map.get("ACTION_NAME").toString() : "" );
				caseEscalationHistoryDto.setInitiatedBy(map.get("INITIATED_BY")!=null ? map.get("INITIATED_BY").toString() : "" );
				caseEscalationHistoryDto.setInitiatedTo(map.get("INITIATED_TO")!=null ? map.get("INITIATED_TO").toString() : "" );
				caseEscalationHistoryDto.setInitiatedDateTime(map.get("INITIATED_DATE_TIME")!=null ? map.get("INITIATED_DATE_TIME").toString() : "" );
				caseEscalationHistoryDto.setInitialRemarks(map.get("INITIAL_REMARKS")!=null ? map.get("INITIAL_REMARKS").toString() : "" );
				caseEscalationHistoryDto.setResolvedBy(map.get("RESOLVED_BY")!=null ? map.get("RESOLVED_BY").toString() : "" );
				caseEscalationHistoryDto.setResolveDtTime(map.get("RESOLVE_DT_TIME")!=null ? map.get("RESOLVE_DT_TIME").toString() : "" );				
				caseEscalationHistoryDto.setResolvedRemarks(map.get("RESOLVED_REMARKS")!=null ? map.get("RESOLVED_REMARKS").toString() : "" );								
				caseEscalationHistorylist.add(caseEscalationHistoryDto);
			}
		}*/
		List<CaseActionHistoryDto> caseActionHistoryList = contactdao.caseActionHistory(caseId,companyId,requestType);
		List<CaseEscalationHistoryDto> caseEscalationHistorylist = contactdao.caseEscalationHistory(caseId,companyId);

		//added on 30Nov
		List<CaseAllocationHistoryDto> caseAllocationHistorylist = contactdao.caseAllocationHistory(caseId,companyId);		

		contactDto.setCaseActionHistory(caseActionHistoryList);
		contactDto.setCaseEscalationHistory(caseEscalationHistorylist);
		contactDto.setCaseAllocationHistory(caseAllocationHistorylist);

		if(tab.trim().equals("action")){
			String lastUpdatedTime =  contactdao.getLastupdateTime(caseId);
			contactDto.setLastUpdateDate(lastUpdatedTime);
		}
		if(tab.trim().equals("escalate")){
			List<String> list = contactdao.getLeadEscalateDetail(caseId,userId);
			if(list!=null && list.size() > 0 ){
				contactDto.setCaseRefEscId(list.get(0)!=null ? list.get(0).toString() : "");
				contactDto.setActionName(list.get(1)!=null ? list.get(1).toString() : "");
				contactDto.setInitiatedBy(list.get(2)!=null ? list.get(2).toString() : "");
				contactDto.setInitiatedDateTime(list.get(3)!=null ? list.get(3).toString() : "");
				contactDto.setInitialRemarks(list.get(4)!=null ? list.get(4).toString() : "");
			}
		}		
		return contactDto;
	}

	public boolean getLeadStatus(String caseId,String userId){
		return contactdao.getLeadStatus(caseId,userId);
	}

	public List<PersonListDto> getAllPerson(String caseId,String userId,String personType){
		return contactdao.getAllPerson(caseId,userId,personType);
	}

	public boolean saveResolution(ContactDto contactDto,String caseId){
		return actionDao.saveResolution(contactDto,caseId);
	}

	public boolean saveHelpRequested(ContactDto contactDto){
		return contactdao.saveHelpRequested(contactDto);
	}

	@Override
	public List<PersonListDto> getSearchPerson() {

		return contactdao.getSearchPerson();
	}

	public String getAllWorklistLeads(String caseId){
		return contactdao.getAllWorklistLeads(caseId);
	}

}