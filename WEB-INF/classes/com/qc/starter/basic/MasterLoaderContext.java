package com.qc.starter.basic;

import org.springframework.stereotype.Service;

import com.qc.starter.dto.MasterDto;

@Service
public class MasterLoaderContext {
	
	private static MasterLoaderContext instance = null;
	
	private MasterLoaderContext(){    
    }
	
	
	public static MasterLoaderContext getInstance(){
    	System.out.println("Context comes to get an object");
    	if(instance == null) {
            instance = new MasterLoaderContext();
         }
    	return instance;
    }
	
	private MasterDto master;

	public MasterDto getMaster() {
		return master;
	}


	public void setMaster(MasterDto master) {
		this.master = master;
	}
	
	

	
}
