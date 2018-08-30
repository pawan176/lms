package com.qc.starter.service;

import java.util.Map;

import com.qc.starter.dto.AuthorizedProcDto;
import com.qc.starter.dto.PasswordChangeDto;
import com.qc.starter.dto.UserDto;
import com.qc.starter.entity.UserEntity;

public interface LoginService {
	
	public UserDto getDetails(String username);
	public UserEntity userLogin(UserDto user);
	public AuthorizedProcDto userAthentication(UserDto user);
	public int countNotification(String useId,String companyId);
	public Map<String,String> getMenuItems(String userId);
	public String changePassword(UserEntity userEntity,PasswordChangeDto passwordChangeDto);
	/*public AuthorizedProcDto changePasswordverify(UserEntity userEntity,PasswordChangeDto passwordChangeDto);*/
}
