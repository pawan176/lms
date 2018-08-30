package com.qc.starter.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qc.starter.dao.NotificationDao;
import com.qc.starter.dto.NotificationDto;
import com.qc.starter.service.NotificationService;
@Service
public class NotificationServiceImpl implements NotificationService{

	@Autowired
	NotificationDao notificationDao;
	
	public List<NotificationDto> getNotigicationDetails(String userId,String companyId) {
		return notificationDao.getNotigicationDetails(userId,companyId);
	}

	
	public String dismissnotificationcase(String notificationId) {
		return notificationDao.dismissnotificationcase(notificationId);
	}
	public String dismissAllNotification() {
		return null;
	}

}
