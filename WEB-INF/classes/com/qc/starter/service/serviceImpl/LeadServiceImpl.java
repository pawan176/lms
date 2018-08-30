package com.qc.starter.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qc.starter.dao.LeadDao;
import com.qc.starter.entity.LeadEntity;
import com.qc.starter.entity.UserEntity;
import com.qc.starter.service.LeadService;


@Service
public class LeadServiceImpl implements LeadService {

	@Autowired LeadDao leadDao;
	
	
	public List<LeadEntity> getUserLeadList(Integer userId) {
		
		return leadDao.getUserLeadList(userId);
	}

	
	public LeadEntity getLead(int caseId,String companyId) {
		
		return leadDao.getLead(caseId,companyId);
	}

	
	public boolean checkLeadAvail(String caseId) {
		
		return leadDao.checkLeadAvail(caseId)>0?true:false;
	}

	
	public LeadEntity getLeadCode(String caseCode) {
		return leadDao.getLeadCode(caseCode);
	}
	
	
	public boolean getEscalateReferInfo(LeadEntity leadEntity,UserEntity userEntity){
		return leadDao.getEscalateReferInfo(leadEntity, userEntity);
	}
	//-----Added by Deepak on 19 October -2016 for Mobile Application-------------------
	public String updateLeadDetails(String requestJson) {
		return leadDao.updateLeadDetails(requestJson);
	}
	//-----------------------------------------------------------------------
	
	public String getBusinessDate(){
		return leadDao.getBusinessDate();	
	}

}
