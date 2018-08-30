package com.qc.starter.service;

import com.qc.starter.dto.LeadHeaderDto;

public interface LeadHeaderService {
	
	public LeadHeaderDto getLeadHeader(String leadId);

	public String getpreviousOrNextLead(String leadId, Integer userid,String lead);

}