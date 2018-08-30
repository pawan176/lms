package com.qc.starter.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qc.starter.dao.LeadHeaderDao;
import com.qc.starter.dto.LeadHeaderDto;
import com.qc.starter.service.LeadHeaderService;

@Service
public class LeadHeaderServiceImpl implements LeadHeaderService {
	
	@Autowired LeadHeaderDao leadHeaderDao;
	public LeadHeaderDto getLeadHeader(String leadId){
		
		/*LeadHeaderDto leadHeaderDto = new LeadHeaderDto();
		List leadInfo = leadHeaderDao.getLeadHeader(leadId);
				
		if(leadInfo != null && leadInfo.size() > 0){
			
			for(Object o : leadInfo){
				
				Map leadMap = (Map) o;
				leadHeaderDto.setLeadId( leadMap.get("LEAD_ID")!=null? leadMap.get("LEAD_ID").toString():" ");
				leadHeaderDto.setFacilityReqId(leadMap.get("FACILITY_REQ_ID")!=null? leadMap.get("FACILITY_REQ_ID").toString():" ");
				leadHeaderDto.setHiddenLeadId(leadMap.get("LEAD_ID")!=null? leadMap.get("LEAD_ID").toString():" ");
				leadHeaderDto.setLeadCode(leadMap.get("LEAD_CODE")!=null? leadMap.get("LEAD_CODE").toString():" ");
				leadHeaderDto.setQueue(leadMap.get("QUEUE")!=null? leadMap.get("QUEUE").toString():" ");
				leadHeaderDto.setQueueId(leadMap.get("QUEUE_ID")!=null? leadMap.get("QUEUE_ID").toString():" ");
				leadHeaderDto.setFacilityReq(leadMap.get("FACILITY_REQUESTED")!=null? leadMap.get("FACILITY_REQUESTED").toString():" ");
				leadHeaderDto.setCustomerName(leadMap.get("CUSTOMER_NAME")!=null? leadMap.get("CUSTOMER_NAME").toString():" ");
				leadHeaderDto.setGenerationDate(leadMap.get("GENERATION_DT")!=null? leadMap.get("GENERATION_DT").toString():" ");
				leadHeaderDto.setSubQueue(leadMap.get("SUB_QUEUE")!=null? leadMap.get("SUB_QUEUE").toString():" ");
				leadHeaderDto.setAmount(leadMap.get("AMOUNT")!=null? leadMap.get("AMOUNT").toString():" ");
				leadHeaderDto.setCustomerMobile(leadMap.get("MOBILE")!=null? leadMap.get("MOBILE").toString():"");
				leadHeaderDto.setAllocatedTo(leadMap.get("ALLOCATED_TO")!=null? leadMap.get("ALLOCATED_TO").toString():" ");
				leadHeaderDto.setSource(leadMap.get("SOURCE")!=null? leadMap.get("SOURCE").toString():" ");
				leadHeaderDto.setBankName(leadMap.get("BANK")!=null? leadMap.get("BANK").toString():" ");
				leadHeaderDto.setBankId(leadMap.get("BANK_ID")!=null? leadMap.get("BANK_ID").toString():" ");
				leadHeaderDto.setCustomerResCity(leadMap.get("CITY")!=null? leadMap.get("CITY").toString():" ");
				leadHeaderDto.setCampaign(leadMap.get("CAMPAIGN")!=null? leadMap.get("CAMPAIGN").toString():" ");
				leadHeaderDto.setRoi(leadMap.get("ROI")!=null? leadMap.get("ROI").toString():" ");
				leadHeaderDto.setOccuType(leadMap.get("OCCUPATION")!=null? leadMap.get("OCCUPATION").toString():" ");
				leadHeaderDto.setTagA(leadMap.get("TAG_INFO_A")!=null? leadMap.get("TAG_INFO_A").toString():" ");
				leadHeaderDto.setTagB(leadMap.get("TAG_INFO_B")!=null? leadMap.get("TAG_INFO_B").toString():" ");
			}
		}*/
		return leadHeaderDao.getLeadHeader(leadId);
	}
	@Override
	public String getpreviousOrNextLead(String leadId, Integer userid,String lead) {
		return leadHeaderDao.getpreviousOrNextLead(leadId, userid,lead);
	}

}
