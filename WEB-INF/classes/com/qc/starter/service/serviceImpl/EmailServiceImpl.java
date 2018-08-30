package com.qc.starter.service.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qc.starter.dao.EmailDao;
import com.qc.starter.entity.EmailEntity;
import com.qc.starter.entity.UserEntity;
import com.qc.starter.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService {
	private static final Logger logger = Logger.getLogger(EmailServiceImpl.class.getName());
	@Autowired
	EmailDao emailDao;

	@Override
	public List<EmailEntity> getListOfEmail(int caseId) {

		return emailDao.getListOfEmail(caseId);
	}

	@Override
	public void addEmail(EmailEntity emailEntity) {

		if (emailEntity != null) {

			if (emailEntity.getPrimaryEmail().equals("Y")) {
				emailDao.addDefaultEmail(emailEntity);
			} else {
				emailDao.addEmail(emailEntity);
			}

		}

	}

	@Override
	public void deleteEmail(List<EmailEntity> list1) {

		if (list1 != null) {
			List<String> toDelete = new ArrayList<String>();
			for (EmailEntity email : list1) {
				if (email.getCaseEmailId() != null && !email.getCaseEmailId().equalsIgnoreCase("Active") && !email.getPrimaryEmail().equalsIgnoreCase("Y")) {
					toDelete.add(email.getCaseEmailId());
				}
			}
			if (toDelete.size() > 0)
				emailDao.deleteEmail(toDelete);
		}

	}

	@Override
	public void updateEmail(List<EmailEntity> list1, String caseId) {

		if (list1 != null) {
			List<EmailEntity> toUpdate = new ArrayList<EmailEntity>();
			for (EmailEntity email : list1) {
				if (email.getCaseEmailId() != null) {
					email.setCaseId(caseId);
					toUpdate.add(email);
				}
			}
			if (toUpdate.size() > 0)
				emailDao.updateEmailList(toUpdate);
		}

	}

	@Override
	public boolean addUpdateContact(List<EmailEntity> listEmail, String caseId,String userId) {
		logger.info("EmailServiceImpl | addUpdateContact() | :-START");
		List<EmailEntity> toUpdate = new ArrayList<EmailEntity>();
		List<EmailEntity> toInsert = new ArrayList<EmailEntity>();
		try{
			for(EmailEntity email : listEmail){
				if(email!=null && email.getCaseEmailId()!=null){
					if(email.getCaseEmailId().equalsIgnoreCase("Active") && email.getEmail()!=null && !email.getEmail().equalsIgnoreCase("")){
						email.setCaseEmailId("");
						email.setCaseId(caseId);
						email.setCreatedBy(userId);
						email.setCreatedDate(new Date());
						email.setCreatedSysDate(new Date());
						toInsert.add(email);
					}else{
						email.setCaseId(caseId);
						email.setUpdatedBy(userId);
						email.setUpdatedDate(new Date());
						email.setUpdatedSysDate(new Date());
						toUpdate.add(email);
					}
				}
			}
			emailDao.addEmailList(toInsert);
			emailDao.updateEmailList(toUpdate);
		}catch(Exception e){
			logger.error("EmailServiceImpl | addUpdateContact() | "+e.getMessage()+" | :-END");
		}finally{
			toUpdate = null;
			toInsert = null;
		}
		logger.info("EmailServiceImpl | addUpdateContact() | :-END");
		return true;
	}

}
