package com.qc.starter.service;

import java.util.List;

import com.qc.starter.entity.EmailEntity;
import com.qc.starter.entity.UserEntity;

public interface EmailService {

	public List<EmailEntity> getListOfEmail(int string);

	public void addEmail(EmailEntity emailEntity);

	public void deleteEmail(List<EmailEntity> list1);

	public void updateEmail(List<EmailEntity> list1, String caseId);

	public boolean addUpdateContact(List<EmailEntity> listEmail, String caseId,String userId);

}
