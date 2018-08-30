package com.qc.starter.basic;

import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.qc.starter.entity.UserEntity;

@Service
public class ManageUser{	
	private static final Logger logger = Logger.getLogger(ManageUser.class.getName());
    private static HashMap userList = new HashMap<String,HttpSession>();  
    private static ManageUser instance = null;    
    
    private ManageUser(){    
    }    
    
    public void setUsers(UserEntity userEntity,HttpSession session) {
    	logger.info("ManageUser | setUsers() | :-Start ");
    	userList.put(userEntity.getUserid(),session);
    	logger.info("ManageUser | setUsers() | :-END ");
    }    
    public boolean getUsers(UserEntity userEntity){    	
    	try{
    		HttpSession session = (HttpSession)userList.get(userEntity.getUserid());
    		
    		if(session == null){
    			return false;
    		}
    		
    		Date lastAccessDate = new Date(session.getLastAccessedTime()); 
    		Date currentDate = new Date();    		
    		long secs = ( currentDate.getTime() - lastAccessDate.getTime()) / 1000;
    		    		
    		if(secs < 1800){
    			return true;
    		}    		
    	}catch(Exception e){
    		e.printStackTrace();
    		return false;
    	}    	
    	return false;
    }    
    public boolean removeUser(UserEntity userEntity){   
    	logger.info("ManageUser | removeUser() | :-Start ");
    	Object obj = userList.remove(userEntity.getUserid());
    	logger.info("ManageUser | removeUser() | :-END ");
    	return obj == null ? false : true; 
 
    }
    
    public boolean destroyOldSession(UserEntity userEntity){
    	HttpSession session = (HttpSession)userList.get(userEntity.getUserid());
    	try{
    		session.invalidate();
    		return true;
    	}catch(Exception e){
    		e.printStackTrace();
    		return false;
    	}
    }
    
    public ManageUser getInstance(){
    	if(instance == null) {
            instance = new ManageUser();
         }
    	return instance;
    }    
}