package com.qc.starter.basic;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.qc.starter.entity.UserEntity;

public class LeadManager {
	private static final Logger logger = Logger.getLogger(LeadManager.class.getName());
	static LeadManager leadManager;
	static String userName;
	private static ResourceBundle resource=ResourceBundle.getBundle("multipleUserConfigration");
	private LeadManager(){		
	}

	private static HashMap<String, String> leadMap = new HashMap<String, String>();  
	private static HashMap<String, String> sessionMap = new HashMap<String, String>(); 
	public static LeadManager getInstance(){
		if (leadManager == null){  
			synchronized(LeadManager.class){  
				if (leadManager == null){  
					leadManager = new LeadManager();//instance will be created at request time  
				}  
			}              
		}  
		return leadManager;  
	}

	public void setLeadId(String leadId,UserEntity userEntity,String sessionId) {
		logger.info("LeadManager | setLeadId() | :-Start ");
		if(!leadMap.containsKey(leadId)){
			if(leadMap.containsValue(userEntity.getUserid()+"")){
				String value=userEntity.getUserid()+"";
				String key= null;
				for(Map.Entry entry: leadMap.entrySet()){
					if(value.equals(entry.getValue())){
						key = (String) entry.getKey();
						break; //breaking because its one to one map
					}
				}
				leadMap.remove(key);
				sessionMap.remove(key);
				leadMap.put(leadId, userEntity.getUserid()+"");
				sessionMap.put(leadId, sessionId);
				userName=userEntity.getUserfname()+" "+(userEntity.getUserlname()!=null?userEntity.getUserlname()+" ":"");
			}else{
				leadMap.put(leadId,userEntity.getUserid()+"");
				sessionMap.put(leadId,sessionId);
				userName=userEntity.getUserfname()+" "+(userEntity.getUserlname()!=null?userEntity.getUserlname()+" ":"");
			}
		}
		//leadMap.put(leadEntity.getCaseId(),userEntity.getUserid().toString());
		//userLeadMap.put(userEntity.getUserid().toString(),leadEntity.getCaseId());
		//System.out.println("leads in list =======" + leadMap.size());
		logger.info("LeadManager | setLeadId() | :-END ");
	}

	public String checkLeadId(String leadId,String userId, String sessionId){
		String multiUser=resource.getString("lms.multiple.user.forSingelAccount");
		//System.out.println(">>>>>>>>>"+userId);
		String status="notExist";
		String user=leadMap.get(leadId);
		Boolean leadStatus=leadMap.containsKey(leadId);
		if(leadStatus){
			if(userId.equalsIgnoreCase(user))
			{
				String extSession=sessionMap.get(leadId);
				if(extSession.equalsIgnoreCase(sessionId)){
					status="ok";
				}else{
					status="error~"+userName;
				}
			}else{
				status="error~"+userName;
			}
		}
		return status;
	}

	public boolean removeLead(String value,String sessionId){   
		logger.info("LeadManager | removeLead() | :-Start ");
		//String value=userEntity.getUserid().toString();
		String key= null;
		Object obj=null;
		for(Map.Entry entry: leadMap.entrySet()){
			if(value.equals(entry.getValue())){
				key = (String) entry.getKey();
				break; //breaking because its one to one map
			}
		}
		String oldSession=sessionMap.get(key);
		if(sessionId.equalsIgnoreCase(oldSession)){
			obj = leadMap.remove(key);
			String status=sessionMap.remove(key);
			userName=null;
		}
		logger.info("LeadManager | removeLead() | :-END ");
		return obj == null ? false : true; 

	}
public HashMap<String, String> getSessionMapList(){
	return sessionMap;
}
}