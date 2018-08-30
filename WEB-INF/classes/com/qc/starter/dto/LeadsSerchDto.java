package com.qc.starter.dto;

public class LeadsSerchDto {
	String mobile;
	String email;                    
	String name;                     
	String caseId;                  
	String leadState;                                                   
	String escalationRef;                                    	
	String userId;
	String requestType;
	String queue;
	String subqueue;
	String disposition;
	String actionId;
	String allocate;
	String amountTo;
	String amountFrom;
	String source;
	String sort1;
	String sort2;
	String sort3;
	String sortOrder;
	String caseCode;
	String company;
	String id;
	String campaign;
	String team;
	String syncDate;
	String fromFollowupDate;
	String toFollowupDate;
	

	public String getFromFollowupDate() {
		return fromFollowupDate;
	}
	public void setFromFollowupDate(String fromFollowupDate) {
		this.fromFollowupDate = fromFollowupDate;
	}
	public String getToFollowupDate() {
		return toFollowupDate;
	}
	public void setToFollowupDate(String toFollowupDate) {
		this.toFollowupDate = toFollowupDate;
	}
	public String getSyncDate() {
		return syncDate;
	}
	public void setSyncDate(String syncDate) {
		this.syncDate = syncDate;
	}
	private int maxResult;
	private int currentPosition;


	public int getMaxResult() {
		return maxResult;
	}
	public void setMaxResult(int maxResult) {
		this.maxResult = maxResult;
	}
	public int getCurrentPosition() {
		return currentPosition;
	}
	public void setCurrentPosition(int currentPosition) {
		this.currentPosition = currentPosition;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCampaign() {
		return campaign;
	}
	public void setCampaign(String campaign) {
		this.campaign = campaign;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getCaseCode() {
		return caseCode;
	}
	public void setCaseCode(String caseCode) {
		this.caseCode = caseCode;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCaseId() {
		return caseId;
	}
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	public String getLeadState() {
		return leadState;
	}
	public void setLeadState(String leadState) {
		this.leadState = leadState;
	}
	public String getEscalationRef() {
		return escalationRef;
	}
	public void setEscalationRef(String escalationRef) {
		this.escalationRef = escalationRef;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRequestType() {
		return requestType;
	}
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	public String getQueue() {
		return queue;
	}
	public void setQueue(String queue) {
		this.queue = queue;
	}
	public String getSubqueue() {
		return subqueue;
	}
	public void setSubqueue(String subqueue) {
		this.subqueue = subqueue;
	}
	public String getDisposition() {
		return disposition;
	}
	public void setDisposition(String disposition) {
		this.disposition = disposition;
	}
	public String getActionId() {
		return actionId;
	}
	public void setActionId(String actionId) {
		this.actionId = actionId;
	}
	public String getAllocate() {
		return allocate;
	}
	public void setAllocate(String allocate) {
		this.allocate = allocate;
	}
	public String getAmountTo() {
		return amountTo;
	}
	public void setAmountTo(String amountTo) {
		this.amountTo = amountTo;
	}
	public String getAmountFrom() {
		return amountFrom;
	}
	public void setAmountFrom(String amountFrom) {
		this.amountFrom = amountFrom;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getSort1() {
		return sort1;
	}
	public void setSort1(String sort1) {
		this.sort1 = sort1;
	}
	public String getSort2() {
		return sort2;
	}
	public void setSort2(String sort2) {
		this.sort2 = sort2;
	}
	public String getSort3() {
		return sort3;
	}
	public void setSort3(String sort3) {
		this.sort3 = sort3;
	}
	public String getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}	
}