package com.qc.starter.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="QM_CASE_STAGE", schema="QC_PROSPECT_MASTER")
public class StageEntity {
@Id	
private String stageId;
private String stageCode;
private String stageName;
private String company;
private String active;
public String getStageId() {
	return stageId;
}
public void setStageId(String stageId) {
	this.stageId = stageId;
}
public String getStageCode() {
	return stageCode;
}
public void setStageCode(String stageCode) {
	this.stageCode = stageCode;
}
public String getStageName() {
	return stageName;
}
public void setStageName(String stageName) {
	this.stageName = stageName;
}
public String getCompany() {
	return company;
}
public void setCompany(String company) {
	this.company = company;
}
public String getActive() {
	return active;
}
public void setActive(String active) {
	this.active = active;
}

}
