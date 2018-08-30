package com.qc.starter.mobileAction;


import org.json.JSONObject;

import com.qc.starter.dto.ContactDto;

public class ContactJsonParser {



	public	ContactDto getJsonToContactDto(String requestJson){


		ContactDto contactDto=null;
		try{
			JSONObject jsonObject=new JSONObject(requestJson);
			contactDto=new ContactDto();
			
			contactDto.setCaseId(jsonObject.getString("caseId"));
			contactDto.setLastUpdateDate(jsonObject.getString("lastUpdateDate"));
			contactDto.setRemarks(jsonObject.getString("remarks"));
			contactDto.setUserId(jsonObject.getString("userId"));
			contactDto.setCompanyId(jsonObject.getString("companyId"));
			contactDto.setLeadStage(jsonObject.getString("stage"));
			contactDto.setPotential(jsonObject.getString("potential"));
			contactDto.setActionId(jsonObject.getString("action"));
			contactDto.setActionDate(jsonObject.getString("actionDate"));
			contactDto.setActionTime(jsonObject.getString("actionTime"));
			contactDto.setFollowupAction(jsonObject.getString("followupAction"));
			contactDto.setFollowupDate(jsonObject.getString("followupActionDate"));
			contactDto.setFollowupTime(jsonObject.getString("followupActionTime"));			
			contactDto.setPurpose(jsonObject.getString("purpose"));
			contactDto.setRejectReason(jsonObject.getString("rejectReason"));
			
			
			//{"caseId":"1000001767","lastUpdateDate":"","remarks":"ok","userId":"1000000456","companyId":"1000000001",
			//"stage":"2","potential":"18","action":"26","actionDate":"22-Jan-2018","actionTime":"","followupAction":"","followupActionDate":"22-Jan-2018","followupActionTime":"21:57","actionIdServer":""}
			
			/*contactDto.setCaseId(jsonObject.getString("caseId"));
			contactDto.setLastUpdateDate(jsonObject.getString("lastUpdateDate"));
			contactDto.setFollowupDate(jsonObject.getString("followupDate"));
			contactDto.setFollowupTime(jsonObject.getString("followupTime"));
			contactDto.setRemarks(jsonObject.getString("remarks"));
			contactDto.setActionId(jsonObject.getString("actionId"));
			contactDto.setDisposition(jsonObject.getString("disposition"));
			contactDto.setUserId(jsonObject.getString("userId"));
			contactDto.setCompanyId(jsonObject.getString("companyId"));
			contactDto.setCaseRefEscId(jsonObject.getString("caseRefEscId"));
			contactDto.setHelpType(jsonObject.getString("helpType"));
			contactDto.setActionName(jsonObject.getString("actionName"));
			contactDto.setInitiatedBy(jsonObject.getString("initiatedBy"));
			contactDto.setInitiatedDateTime(jsonObject.getString("initiatedDateTime"));
			contactDto.setInitialRemarks(jsonObject.getString("initialRemarks"));
			contactDto.setResolutionCheck(jsonObject.getString("resolutionCheck"));
			contactDto.setResolvedRemarks(jsonObject.getString("resolvedRemarks"));
			contactDto.setInitiatedTo(jsonObject.getString("initiatedTo"));
			contactDto.setHelpRemarks(jsonObject.getString("helpRemarks"));*/

	}catch(Exception e){
		e.printStackTrace();
	}
	return contactDto;
}
}
