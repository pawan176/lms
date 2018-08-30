package com.qc.starter.service;

import java.util.List;

import com.qc.starter.dto.NotificationDto;

public interface NotificationService {
	public List<NotificationDto> getNotigicationDetails(String userId,String companyId);
	public String dismissnotificationcase(String notificationId);
	public String dismissAllNotification();
}
