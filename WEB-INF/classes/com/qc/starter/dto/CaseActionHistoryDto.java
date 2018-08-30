package com.qc.starter.dto;

public class CaseActionHistoryDto {

	String action;
	String actionBy;
	String actionDate;
	String remarks;
	String caseId;
	//--Added by Deepak for mobile on 18 Oct-2016-------
    private String dispositionId;
    private String actionId;
	//--------------------------------------------------

    
    
    //Added by Sumit For aye Action history changes on 22-Jan-2017
    String followupAction;
    String followupActionDate;
    String leadStage;
    String potential;
    String purpose;

    String rejectReason;
    
    
    
    
    
    

	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

   
    
     

	public String getRejectReason() {
		return rejectReason;
	}
	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}



	public String getFollowupAction() {
		return followupAction;
	}
	public void setFollowupAction(String followupAction) {
		this.followupAction = followupAction;
	}
	public String getFollowupActionDate() {
		return followupActionDate;
	}
	public void setFollowupActionDate(String followupActionDate) {
		this.followupActionDate = followupActionDate;
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
	public String getCaseId() {
		return caseId;
	}
	public String getDispositionId() {
		return dispositionId;
	}
	public void setDispositionId(String dispositionId) {
		this.dispositionId = dispositionId;
	}
	public String getActionId() {
		return actionId;
	}
	public void setActionId(String actionId) {
		this.actionId = actionId;
	}
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getActionBy() {
		return actionBy;
	}
	public void setActionBy(String actionBy) {
		this.actionBy = actionBy;
	}
	public String getActionDate() {
		return actionDate;
	}
	public void setActionDate(String actionDate) {
		this.actionDate = actionDate;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}		
}