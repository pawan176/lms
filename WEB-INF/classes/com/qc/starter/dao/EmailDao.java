package com.qc.starter.dao;

import java.util.List;

import com.qc.starter.entity.EmailEntity;

public interface EmailDao {

	public List<EmailEntity> getListOfEmail(Integer caseId);

	public void addEmail(EmailEntity emailEntity);

	public void addDefaultEmail(EmailEntity emailEntity);

	public void deleteEmail(List<String> toDelete);

	public void updateEmailList(List<EmailEntity> toUpdate);

	public void addEmailList(List<EmailEntity> toInsert);

}
