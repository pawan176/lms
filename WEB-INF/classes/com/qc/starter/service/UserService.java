package com.qc.starter.service;

import com.qc.starter.entity.UserEntity;

public interface UserService {
	
	public String getUser();
    public void logOutExit(UserEntity userEntity,String ipAddress);
}
