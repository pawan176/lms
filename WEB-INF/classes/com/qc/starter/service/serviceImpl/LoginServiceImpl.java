package com.qc.starter.service.serviceImpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qc.starter.basic.PasswordEncryption;
import com.qc.starter.dao.LoginDao;
import com.qc.starter.dto.AuthorizedProcDto;
import com.qc.starter.dto.PasswordChangeDto;
import com.qc.starter.dto.UserDto;
import com.qc.starter.entity.UserEntity;
import com.qc.starter.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	LoginDao loginDao;

	public UserDto getDetails(String username) {
		return loginDao.getDetails(username);
	}

	public UserEntity userLogin(UserDto user) {
		return loginDao.userLogin(user);
	}

	public AuthorizedProcDto userAthentication(UserDto user) {
		PasswordEncryption passwordEncryption = new PasswordEncryption();
		String encryptEnteredPassword = passwordEncryption.hash(user.getPassword().getBytes());
		user.setPassword(encryptEnteredPassword);
		return loginDao.userAthentication(user);
	}
	
	public int countNotification(String useId, String companyId) {
		return loginDao.countNotification(useId, companyId);
	}
	
	public Map<String,String> getMenuItems(String userId) {
		return loginDao.getMenuItems(userId);
	}
	public String changePassword(UserEntity userEntity,PasswordChangeDto passwordChangeDto){
	
		
		PasswordEncryption passwordEncryption = new PasswordEncryption();
		String encryptEnteredPassword = passwordEncryption.hash(passwordChangeDto.getCurrentpwd().getBytes());
		passwordChangeDto.setEncconfirmnewpwd(encryptEnteredPassword);
	  if(userEntity.getPassword().equals(encryptEnteredPassword)){      
		
		   return loginDao.changePassword(userEntity, passwordChangeDto);
		}
		
		return "FAILURE";
	}
	
	
	/*public AuthorizedProcDto changePasswordverify(UserEntity userEntity,PasswordChangeDto passwordChangeDto){		
		UserDto userDto = new UserDto();
		userDto.setIpaddress("127.0.0.1");
		String login1=userEntity.getLogin1();
		String login2=userEntity.getLogin2();
		String loginname=login1 +login2;
		userDto.setUserName(loginname);
		userDto.setPassword(userEntity.getPassword());		
		return loginDao.userAthentication(userDto); 
	}*/
	
}
