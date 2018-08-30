package com.qc.starter.service;

import com.qc.starter.dto.NewLeadDto;

public interface NewLeadService {
	
	public String getLeadId();
	public String createLead(NewLeadDto newLeadDto);
	//public String createLeadMob(NewLeadDto newLeadDto);

}
