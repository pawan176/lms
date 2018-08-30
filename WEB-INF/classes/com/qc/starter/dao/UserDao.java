package com.qc.starter.dao;

import com.qc.starter.entity.UserEntity;

public interface UserDao {

	public String getUser();
	public void logOutExit(UserEntity userEntity,String ipAddress);
}
