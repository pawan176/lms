package com.qc.starter.dto;

public class CaseAllocationHistoryDto {

	String action;
	String allocatedBy;
	String allocatedTo;
	String allocatedDate;;
	String remark;

	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getAllocatedBy() {
		return allocatedBy;
	}
	public void setAllocatedBy(String allocatedBy) {
		this.allocatedBy = allocatedBy;
	}
	public String getAllocatedTo() {
		return allocatedTo;
	}
	public void setAllocatedTo(String allocatedTo) {
		this.allocatedTo = allocatedTo;
	}
	public String getAllocatedDate() {
		return allocatedDate;
	}
	public void setAllocatedDate(String allocatedDate) {
		this.allocatedDate = allocatedDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}	

}
