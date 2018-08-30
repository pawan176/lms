package com.qc.starter.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qc.starter.dao.WorkListDao;
import com.qc.starter.dto.CustomerDto;
import com.qc.starter.dto.LeadHeaderDto;
import com.qc.starter.dto.LeadsSerchDto;
import com.qc.starter.service.WorkListService;

@Service
public class WorkListServiceImpl implements WorkListService{

	@Autowired	WorkListDao workListDao;
	public List<LeadHeaderDto> getLeadDetails(LeadsSerchDto leadsSerchDto) {
		if(leadsSerchDto.getRequestType().trim().equals("advancesearch") || leadsSerchDto.getRequestType().trim().equals("search") ){
			return workListDao.getLeadsSearchResult(leadsSerchDto);
		}
		else{

			List<LeadHeaderDto> leadlist = workListDao.getLeadDetails(leadsSerchDto);
			return leadlist;
		}		
	}
		
	public String allocationFromMobile(CustomerDto customerDto){
		return workListDao.allocationFromMobile(customerDto);
	}

	public String saveAllocatedLead(List inpList,String remark,String queueId,String allocatedId) {
		return workListDao.saveAllocatedLead(inpList,remark,queueId,allocatedId);

	}

	public String getLeadStatelist(String disposition) {
		return workListDao.getLeadStatelist(disposition);
	}

	public int getLeadDetailsListSize(LeadsSerchDto leadsSerchDto) {
		return workListDao.getLeadDetailsListSize(leadsSerchDto);
	}	
}