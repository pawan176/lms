package com.qc.starter.service;

import java.util.List;

import com.qc.starter.entity.LeadEntity;
import com.qc.starter.entity.UserEntity;

public interface LeadService {



	public List<LeadEntity> getUserLeadList(
			Integer userId);

	public LeadEntity getLead(int caseId,String companyId);

	public boolean checkLeadAvail(String caseId);

	public LeadEntity getLeadCode(String caseCode);

	public boolean getEscalateReferInfo(LeadEntity leadEntity,UserEntity userEntity);
	//-----Added by Deepak on 19 October -2016 for Mobile Application-------------------
    public String updateLeadDetails(String requestJson);
    //-------------------------------------    
    public String getBusinessDate();

}
