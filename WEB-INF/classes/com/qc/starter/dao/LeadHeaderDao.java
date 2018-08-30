package com.qc.starter.dao;

import com.qc.starter.dto.LeadHeaderDto;

public interface LeadHeaderDao {

	LeadHeaderDto getLeadHeader(String leadId);
	public String getpreviousOrNextLead(String leadId, Integer userid,String lead);

}
