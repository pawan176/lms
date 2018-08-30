package com.qc.starter.controllar;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.qc.starter.dto.NotificationDto;
import com.qc.starter.entity.UserEntity;
import com.qc.starter.service.NotificationService;

@Controller
public class NotificationController {
	@Autowired
	NotificationService notificationService;

	@RequestMapping(method={RequestMethod.POST},value="getNotififactionDetails")
	public
	@ResponseBody String getNotiFicationDetails(HttpSession session,@RequestParam("notificationId") String notificationId){
		UserEntity userEntity=(UserEntity)session.getAttribute("UserDetails");
		List<NotificationDto> notification=notificationService.getNotigicationDetails(userEntity.getUserid()+"",userEntity.getCompanyId());	
		Gson json=new Gson();
		String jsonResponse=json.toJson(notification);
		return jsonResponse;
	}

	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value="dismissnotification")
	public
	@ResponseBody String dismissNotification(HttpSession session,@RequestParam("notificationId") String notificationId){

		String status = notificationService.dismissnotificationcase(notificationId);		
		UserEntity userEntity=(UserEntity)session.getAttribute("UserDetails");

		List<NotificationDto> notification=notificationService.getNotigicationDetails(userEntity.getUserid()+"",userEntity.getCompanyId());	
		Gson json=new Gson();
		String jsonResponse=json.toJson(notification);
		return jsonResponse;
	}


	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value="dismissAllnotification")
	public
	@ResponseBody String dismissAllNotification(HttpSession session,@RequestParam("notificationId") String notificationId){

		//notificationService.dismissAllNotification();
		/*String status = notificationService.dismissnotificationcase(notificationId);		
		UserEntity userEntity=(UserEntity)session.getAttribute("UserDetails");

		List<NotificationDto> notification=notificationService.getNotigicationDetails(userEntity.getUserid()+"",userEntity.getCompanyId());	
		Gson json=new Gson();
		String jsonResponse=json.toJson(notification);
		 * */
		return null;
	}

}
