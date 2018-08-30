package com.qc.starter.dao;

import java.util.List;

import com.qc.starter.dto.CustomerDto;
import com.qc.starter.dto.LeadHeaderDto;
import com.qc.starter.dto.LeadsSerchDto;

public interface WorkListDao {

	public List<LeadHeaderDto> getLeadDetails(LeadsSerchDto leadsSerchDto);
	public List<LeadHeaderDto> getLeadsSearchResult(LeadsSerchDto leadsSerchDto);
	public String saveAllocatedLead(List inpList,String remark, String queueId,String allocatedId);
	public String getLeadStatelist(String disposition);
	public int getLeadDetailsListSize(LeadsSerchDto leadsSerchDto);	
	public String allocationFromMobile(CustomerDto customerDto);
}