package com.qc.starter.dao;

import java.util.Map;

import com.qc.starter.dto.AuthorizedProcDto;
import com.qc.starter.dto.PasswordChangeDto;
import com.qc.starter.dto.UserDto;
import com.qc.starter.entity.UserEntity;

public interface LoginDao {

	public UserDto getDetails(String username);
	public int countNotification(String useId,String companyId);
	public UserEntity userLogin(UserDto user);
	public AuthorizedProcDto userAthentication(UserDto user);
	public Map<String,String> getMenuItems(String userId);
	public String changePassword(UserEntity userEntity,PasswordChangeDto passwordChangeDto);
}
