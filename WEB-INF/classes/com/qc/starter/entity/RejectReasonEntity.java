package com.qc.starter.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="QM_REJECT_REASON", schema="QC_PROSPECT_MASTER")
public class RejectReasonEntity {
	@Id
	private	String	rejectreasonId	;
	private	String	rejectreasonCode	;
	private	String	rejectreasonName	;
	private	String	displayName	;	
	private	String	active	;
	public String getRejectreasonId() {
		return rejectreasonId;
	}
	public void setRejectreasonId(String rejectreasonId) {
		this.rejectreasonId = rejectreasonId;
	}
	public String getRejectreasonCode() {
		return rejectreasonCode;
	}
	public void setRejectreasonCode(String rejectreasonCode) {
		this.rejectreasonCode = rejectreasonCode;
	}
	public String getRejectreasonName() {
		return rejectreasonName;
	}
	public void setRejectreasonName(String rejectreasonName) {
		this.rejectreasonName = rejectreasonName;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	
	
	
}
