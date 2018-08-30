package com.qc.starter.dao;

import java.util.List;

import com.qc.starter.dto.NotificationDto;

public interface NotificationDao {
	public List<NotificationDto> getNotigicationDetails(String userId,String companyId);
	public String dismissnotificationcase(String notificationId);
	public String dismissAllNotification();
}
