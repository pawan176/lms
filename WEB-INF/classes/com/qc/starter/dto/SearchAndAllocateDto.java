package com.qc.starter.dto;

public class SearchAndAllocateDto {

	String rowNo;
	String queue;
	String queueId;
	String subQueue;
	String subQueueId;
	String dnd;
	String sourceId;
	String source;
	String campaignId;
	String campaign;
	String noOfCase;
	String allocate;
	String leftToAllocate;
	String temp;
	private String allotedList;




	public String getAllotedList() {
		return allotedList;
	}
	public void setAllotedList(String allotedList) {
		this.allotedList = allotedList;
	}
	public String getTemp() {
		return temp;
	}
	public void setTemp(String temp) {
		this.temp = temp;
	}
	public String getRowNo() {
		return rowNo;
	}
	public void setRowNo(String rowNo) {
		this.rowNo = rowNo;
	}
	public String getCampaignId() {
		return campaignId;
	}
	public void setCampaignId(String campaignId) {
		this.campaignId = campaignId;
	}
	public String getSourceId() {
		return sourceId;
	}
	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}
	public String getQueueId() {
		return queueId;
	}
	public void setQueueId(String queueId) {
		this.queueId = queueId;
	}
	public String getSubQueueId() {
		return subQueueId;
	}
	public void setSubQueueId(String subQueueId) {
		this.subQueueId = subQueueId;
	}
	public String getQueue() {
		return queue;
	}
	public void setQueue(String queue) {
		this.queue = queue;
	}
	public String getSubQueue() {
		return subQueue;
	}
	public void setSubQueue(String subQueue) {
		this.subQueue = subQueue;
	}
	public String getDnd() {
		return dnd;
	}
	public void setDnd(String dnd) {
		this.dnd = dnd;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getCampaign() {
		return campaign;
	}
	public void setCampaign(String campaign) {
		this.campaign = campaign;
	}
	public String getNoOfCase() {
		return noOfCase;
	}
	public void setNoOfCase(String noOfCase) {
		this.noOfCase = noOfCase;
	}
	public String getAllocate() {
		return allocate;
	}
	public void setAllocate(String allocate) {
		this.allocate = allocate;
	}
	public String getLeftToAllocate() {
		return leftToAllocate;
	}
	public void setLeftToAllocate(String leftToAllocate) {
		this.leftToAllocate = leftToAllocate;
	}




}
