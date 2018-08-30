package com.qc.starter.dto;

import java.util.List;

public class ContactDto {	
	
	List<CaseEscalationHistoryDto> caseEscalationHistory;
	List<CaseActionHistoryDto> caseActionHistory;
	List<CaseAllocationHistoryDto> caseAllocationHistory;
	String caseId;
	String lastUpdateDate;
	String disposition;
	String UserId;	
	String caseRefEscId;
	
	String actionId;
	String actionDate;
	String actionTime;	
	String followupAction;
	String followupDate;
	String followupTime;
	String leadStage;
	String potential;
	String remarks;
	String rejectReason;
	String purpose;
	
	//Action --> actionId  
	//ActionDte --> followupDate
	//ActionTime --> followupTime	
	//New FollowupAction --> disposition 
	//New Follow up Date --> followupDate
	//New Follow up Time --> followupTime
	//New LeadStage     --> actionId
	//New Potential    -->   actionId
	//remarks  -->  remarks
	
	
	//for escalated tab
	String helpType;
	String actionName;
	String initiatedBy;
	String initiatedDateTime;
	String initialRemarks;
	String resolutionCheck;	
	String resolvedRemarks;
	String initiatedTo;
	String helpRemarks;	
	String companyId;

	

	
	
	public String getRejectReason() {
		return rejectReason;
	}
	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public String getActionDate() {
		return actionDate;
	}
	public void setActionDate(String actionDate) {
		this.actionDate = actionDate;
	}
	public String getActionTime() {
		return actionTime;
	}
	public void setActionTime(String actionTime) {
		this.actionTime = actionTime;
	}
	public String getFollowupAction() {
		return followupAction;
	}
	public void setFollowupAction(String followupAction) {
		this.followupAction = followupAction;
	}
	public String getLeadStage() {
		return leadStage;
	}
	public void setLeadStage(String leadStage) {
		this.leadStage = leadStage;
	}
	public String getPotential() {
		return potential;
	}
	public void setPotential(String potential) {
		this.potential = potential;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public List<CaseAllocationHistoryDto> getCaseAllocationHistory() {
		return caseAllocationHistory;
	}
	public void setCaseAllocationHistory(
			List<CaseAllocationHistoryDto> caseAllocationHistory) {
		this.caseAllocationHistory = caseAllocationHistory;
	}
	public String getHelpType() {
		return helpType;
	}
	public void setHelpType(String helpType) {
		this.helpType = helpType;
	}
	public String getInitiatedTo() {
		return initiatedTo;
	}
	public void setInitiatedTo(String initiatedTo) {
		this.initiatedTo = initiatedTo;
	}
	public String getHelpRemarks() {
		return helpRemarks;
	}
	public void setHelpRemarks(String helpRemarks) {
		this.helpRemarks = helpRemarks;
	}
	public String getResolvedRemarks() {
		return resolvedRemarks;
	}
	public void setResolvedRemarks(String resolvedRemarks) {
		this.resolvedRemarks = resolvedRemarks;
	}
	public String getResolutionCheck() {
		return resolutionCheck;
	}
	public void setResolutionCheck(String resolutionCheck) {
		this.resolutionCheck = resolutionCheck;
	}
	public String getCaseRefEscId() {
		return caseRefEscId;
	}
	public void setCaseRefEscId(String caseRefEscId) {
		this.caseRefEscId = caseRefEscId;
	}
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public String getInitiatedBy() {
		return initiatedBy;
	}
	public void setInitiatedBy(String initiatedBy) {
		this.initiatedBy = initiatedBy;
	}
	public String getInitiatedDateTime() {
		return initiatedDateTime;
	}
	public void setInitiatedDateTime(String initiatedDateTime) {
		this.initiatedDateTime = initiatedDateTime;
	}
	public String getInitialRemarks() {
		return initialRemarks;
	}
	public void setInitialRemarks(String initialRemarks) {
		this.initialRemarks = initialRemarks;
	}
	public String getDisposition() {
		return disposition;
	}
	public void setDisposition(String disposition) {
		this.disposition = disposition;
	}
	public String getUserId() {
		return UserId;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}
	public String getActionId() {
		return actionId;
	}
	public void setActionId(String actionId) {
		this.actionId = actionId;
	}
	public String getCaseId() {
		return caseId;
	}
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	public String getFollowupTime() {
		return followupTime;
	}
	public void setFollowupTime(String followupTime) {
		this.followupTime = followupTime;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getFollowupDate() {
		return followupDate;
	}
	public void setFollowupDate(String followupDate) {
		this.followupDate = followupDate;
	}
	public String getLastUpdateDate() {
		return lastUpdateDate;
	}
	public void setLastUpdateDate(String lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	public List<CaseEscalationHistoryDto> getCaseEscalationHistory() {
		return caseEscalationHistory;
	}
	public void setCaseEscalationHistory(
			List<CaseEscalationHistoryDto> caseEscalationHistory) {
		this.caseEscalationHistory = caseEscalationHistory;
	}
	public List<CaseActionHistoryDto> getCaseActionHistory() {
		return caseActionHistory;
	}
	public void setCaseActionHistory(List<CaseActionHistoryDto> caseActionHistory) {
		this.caseActionHistory = caseActionHistory;
	}
}