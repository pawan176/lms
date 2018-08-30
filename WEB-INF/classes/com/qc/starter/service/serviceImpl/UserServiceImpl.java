package com.qc.starter.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qc.starter.dao.UserDao;
import com.qc.starter.entity.UserEntity;
import com.qc.starter.service.UserService;

@Service
public class UserServiceImpl implements UserService{	

	@Autowired UserDao userDao;	
	public String getUser(){
		return userDao.getUser();
	}
	public void logOutExit(UserEntity userEntity,String ipAddress) {
		userDao.logOutExit(userEntity,ipAddress);
		
	}

}
