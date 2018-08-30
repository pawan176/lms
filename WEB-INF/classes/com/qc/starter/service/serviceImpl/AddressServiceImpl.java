package com.qc.starter.service.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qc.starter.basic.CommonUtils;
import com.qc.starter.dao.AddressDao;
import com.qc.starter.entity.AddressEntity;
import com.qc.starter.entity.UserEntity;
import com.qc.starter.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {
	private static final Logger logger = Logger.getLogger(AddressServiceImpl.class.getName());
	@Autowired
	AddressDao addressDao;
	
	@Override
	public AddressEntity getResAddress(String personalDetailId) {		
		return addressDao.getResAddress(personalDetailId);
	}

	@Override
	public AddressEntity getOffAddress(String personalDetailId) {		
		return addressDao.getOffAddress(personalDetailId);
	}

	@Override
	public boolean updateResAddress(AddressEntity addressEntity) {		
		return addressDao.updateResAddress(addressEntity);
	}

	@Override
	public List<AddressEntity> getAddressList(String personalDetailId) {		
		return addressDao.listAddress(personalDetailId);		
	}

	@Override
	public int updateAddressList(List<AddressEntity> toUpdate, String caseId, Integer userId) {
		for(AddressEntity add : toUpdate){
			add.setCaseId(caseId);
			add.setUpdatedBy(userId.toString());
		}
		return addressDao.updateAddressList(toUpdate);
	}

	@Override
	public int insertAddress(List<AddressEntity> toInsert, String caseId, Integer userid) {
		for(AddressEntity add:toInsert){
			add.setPersonalDtlId(caseId);
			add.setCreatedBy(userid.toString());
		}		
		return addressDao.insertAddressList(toInsert);
	}

	@Override
	public int deleteAddressList(List<AddressEntity> list) {
		List<AddressEntity> toDelete ;
		try{
			toDelete = new ArrayList<AddressEntity>();
			for(AddressEntity add : list){
				if(add.getAddressId()!=null && !add.getAddressId().equalsIgnoreCase("Active")){
					toDelete.add(add);
				}
			}
			if(toDelete.size()>0) addressDao.deleteAddressList(toDelete);
		}catch(Exception e){
			logger.error("AddressController | DeleteAddress | "+e.getMessage()+" | :-END");
		}
		finally{
			list = null;
			toDelete = null;
		}
		return 1;
	}

	@Override
	public boolean addUpdateAddress(List<AddressEntity> list,String userId, String caseId, String personalDetailId) {
		logger.info("AddressServiceImpl | addUpdateAddress() | :-START");
		List<AddressEntity> toUpdate = new ArrayList<AddressEntity>();
		List<AddressEntity> toInsert = new ArrayList<AddressEntity>();
		String flag="NotUpdateAllAddress";
		boolean flage=false;
		try{
			for(AddressEntity add : list){
	    		if(add!=null){
	    			if(add.getAddressId()!=null){
	    				if( (add.getAddressId().equalsIgnoreCase("Active") || add.getAddressId().equalsIgnoreCase("") ) && add.getAddress()!=null && !add.getAddress().equals("")){
	    	    			add.setAddressId("");
	    	    			add.setPersonalDtlId(personalDetailId);
	    	    			add.setCreatedBy(userId);
	    	    			add.setCreatedDate(new Date());
	    	    			add.setCreatedSysDate(new Date());
	    	    			add.setCaseId(caseId);
	    	    			if(add.getMailingAddress() != null){
	    	    				flag="updateAllAddress";
	    	    			}
	    	    			toInsert.add(add);
	    	    				    	    			
	    	    		}else{
	    	    			add.setCaseId(caseId);
	    	    			add.setPersonalDtlId(personalDetailId);
	    	    			add.setUpdatedBy(userId);
	    	    			add.setUpdatedDate(new Date());
	    	    			add.setUpdatedSysDate(new Date());
	    	    			if(add.getMailingAddress() != null){
	    	    				flag="updateAllAddress";
	    	    			}
	    	    			toUpdate.add(add);
	    	    		}	    				
	    			}		    		
	    		}	    		
	    	}
			if(flag.equalsIgnoreCase("updateAllAddress")){
				flage=addressDao.updateAllAddressList(caseId);
			}
			int insCount=addressDao.insertAddressList(toInsert);
    		int upCount=addressDao.updateAddressList(toUpdate);
    		if(insCount>0 || upCount>0){
    			flage=true;
    		}
		}catch(Exception e){
			e.printStackTrace();
			logger.error("AddressServiceImpl | addUpdateAddress() | "+e.getMessage()+" | :-END");
		}finally{
			toUpdate = null;
			toInsert = null;
		}
		logger.info("AddressServiceImpl | addUpdateAddress() | :-END"); 
    	return flage;	
	}

	@Override
	public List<AddressEntity> addUpdateAddressMob(List<AddressEntity> list, String userId,
			String caseId, String personalDetailId) {
		logger.info("AddressServiceImpl | addUpdateAddress() | :-START");
		List<AddressEntity> toUpdate = new ArrayList<AddressEntity>();
		List<AddressEntity> toInsert = new ArrayList<AddressEntity>();
		String flag="NotUpdateAllAddress";
		List<AddressEntity> addresslist = new ArrayList<AddressEntity>();
		boolean flage=false;
		try{
			for(AddressEntity add : list){
	    		if(add!=null){
	    			if(add.getAddressId()!=null){
	    				if( (add.getAddressId().equalsIgnoreCase("Active") || add.getAddressId().equalsIgnoreCase("") ) && add.getAddress()!=null && !add.getAddress().equals("")){
	    	    			add.setAddressId("");
	    	    			add.setPersonalDtlId(personalDetailId);
	    	    			add.setCreatedBy(userId);
	    	    			add.setCreatedDate(new Date());
	    	    			add.setCreatedSysDate(new Date());
	    	    			add.setCaseId(caseId);
	    	    			if(add.getMailingAddress() != null){
	    	    				flag="updateAllAddress";
	    	    			}
	    	    			toInsert.add(add);	    	    				    	    			
	    	    		}else{
	    	    			add.setCaseId(caseId);
	    	    			add.setPersonalDtlId(personalDetailId);
	    	    			add.setUpdatedBy(userId);
	    	    			add.setUpdatedDate(new Date());
	    	    			add.setUpdatedSysDate(new Date());
	    	    			if(add.getMailingAddress() != null){
	    	    				flag="updateAllAddress";
	    	    			}
	    	    			toUpdate.add(add);
	    	    		}	    				
	    			}		    		
	    		}	    		
	    	}
			if(flag.equalsIgnoreCase("updateAllAddress")){
				flage=addressDao.updateAllAddressList(caseId);
			}
			int insCount=addressDao.insertAddressList(toInsert);
    		int upCount=addressDao.updateAddressList(toUpdate);
    		if(insCount>0 || upCount>0){
    			flage=true;
    			addresslist=addressDao.addressListdetail(caseId);
    		}
		}catch(Exception e){
			e.printStackTrace();
			logger.error("AddressServiceImpl | addUpdateAddress() | "+e.getMessage()+" | :-END");
		}finally{
			toUpdate = null;
			toInsert = null;
		}
		logger.info("AddressServiceImpl | addUpdateAddress() | :-END"); 
    	return addresslist;	
	}
	
	
	public List<AddressEntity> saveAddressFromProcedure(List<AddressEntity> list,String userId, String caseId){
				
		boolean isInsertAddressPresent=false,isUpdateAddressPresent=false;		
		String finalInsertString = "<ADDRESS>";
		String finalUpdateString = "<ADDRESS>";
		
		try{
			if (list != null) {
				
				for (int i = 0; i < list.size(); i++) {				
					AddressEntity add = list.get(i);			
					/*param = "<ADDRESS_TYPE>~<AddressValue>~<Street_Name>~<Area_Name>~<COMPANY_NAME>~<LOCALITY>~<STATE>~<CITY>~<PIN>~<LANDMARK>~<LANDLINE1>~<LANDLINE2>~<FAX>~<EXTENSION>~<LIVING_SINCE_YY>"
							+ "~<LIVING_SINCE_MM>~<CURRENT_AREA_YY>~<BUSSINESS_ESTB_YY>~<OCCUPANCY_ST>~<MAILING_ADDRESS>~<OLD_ADDRESS>~<GSTIN_NO>~<DISTRICT>";*/				
					if (add != null) {
						if (add.getAddressId() != null && ( add.getAddressId().equals("")  || add.getAddressId().equals("Active"))   ) {
							
							String insertString = "<ADDRESS_ID>~<ADDRESS_TYPE>~<AddressValue>~<STATE>~<CITY>~<PIN>~<LANDMARK>~<LANDLINE>~<EXTENSION>~<LIVING_SINCE_YY>"
									+ "~<LIVING_SINCE_MM>~<OCCUPANCY_ST>~<MAILING_ADDRESS>~<Street_Name>~<Area_Name>~<COMPANY_NAME>~<LOCALITY>~<LANDLINE2>~<FAX>~<OLD_ADDRESS>~<GSTIN_NO>~<MarketValue>~<CURRENT_AREA_YY>~<BUSSINESS_ESTB_YY>~<DESTINATION_ADDRESS>~<MOBILE1>~<MOBILE2>~<EMAIL>";
							
							isInsertAddressPresent = true;
							insertString = insertString.replaceAll("<ADDRESS_TYPE>",(add.getAddressType() == null || add.getAddressType().equals("-1")) ? "1000000001" : add.getAddressType());
							insertString = insertString.replaceAll("<ADDRESS_ID>", "");
							insertString = insertString.replaceAll("<AddressValue>", add.getAddress() == null ? "" : add.getAddress());
							insertString = insertString.replaceAll("<STATE>", add.getState().equals("-1") ? "" : add.getState());
							insertString = insertString.replaceAll("<CITY>",(add.getCity() == null || add.getCity().equals("-1")) ? "" : add.getCity());
							insertString = insertString.replaceAll("<PIN>", add.getZipcode() == null ? "" : add.getZipcode());										
							insertString = insertString.replaceAll("<LANDMARK>", add.getLandmark() == null ? "" : add.getLandmark());						
							insertString = insertString.replaceAll("<LANDLINE>", add.getPhone1() == null ? "" : add.getPhone1());						
							insertString = insertString.replaceAll("<EXTENSION>", add.getExt1() == null ? "" : add.getExt1());
							insertString = insertString.replaceAll("<LIVING_SINCE_YY>",	add.getOccupancyYr() == null ? "" : add.getOccupancyYr().replaceAll(",",""));			
							insertString = insertString.replaceAll("<LIVING_SINCE_MM>", add.getOccupancyMm() == null ? "" : add.getOccupancyMm());
							insertString = insertString.replaceAll("<OCCUPANCY_ST>", add.getOccupancyStatus().equals("-1") ? "" : add.getOccupancyStatus());						
							insertString = insertString.replaceAll("<MAILING_ADDRESS>", ( CommonUtils.toString(add.getMailingAddress()).equalsIgnoreCase("") || CommonUtils.toString(add.getMailingAddress()).equalsIgnoreCase("N") ) ? "N" : "Y");
							insertString = insertString.replaceAll("<Street_Name>", add.getFlatNo() == null ? "" : add.getFlatNo());
							insertString = insertString.replaceAll("<Area_Name>", add.getFloorNo() == null ? "" : add.getFloorNo());
							insertString = insertString.replaceAll("<COMPANY_NAME>", add.getCompany_name() == null ? "" : add.getCompany_name());
							insertString = insertString.replaceAll("<LOCALITY>", add.getLocality() == null ? "" : add.getLocality());
							insertString = insertString.replaceAll("<LANDLINE2>", add.getPhone2() == null ? "" : add.getPhone2());
							insertString = insertString.replaceAll("<FAX>", CommonUtils.toString(""));						
							insertString = insertString.replaceAll("<FAX>", add.getFax() == null ? "" : add.getFax());
							insertString = insertString.replaceAll("<OLD_ADDRESS>", add.getOldaddress() == null ? "" : add.getOldaddress());
							insertString = insertString.replaceAll("<GSTIN_NO>", add.getGstinno() == null ? "" : add.getGstinno());
							insertString = insertString.replaceAll("<MarketValue>", add.getMarketvalue() == null ? "" : add.getMarketvalue());
							insertString = insertString.replaceAll("<CURRENT_AREA_YY>",	add.getCurrentareaYr() == null ? "" : add.getCurrentareaYr());
							insertString = insertString.replaceAll("<BUSSINESS_ESTB_YY>",	add.getBussinessestbyr() == null ? "" : add.getBussinessestbyr());
							insertString = insertString.replaceAll("<DESTINATION_ADDRESS>",	add.getDestinationAddress() == null ? "" : add.getDestinationAddress());
							insertString = insertString.replaceAll("<MOBILE1>",	add.getMobile_no1() == null ? "" : add.getMobile_no1());
							insertString = insertString.replaceAll("<MOBILE2>",	add.getMobile_no2() == null ? "" : add.getMobile_no2());
							insertString = insertString.replaceAll("<EMAIL>",	add.getEmail()== null ? "" : add.getEmail().replaceAll(",",""));
						
							if (list.size() > 1 && i < list.size() - 1)
								insertString += "!";
							finalInsertString = finalInsertString +insertString;
							
						} else if(add.getAddressId() !=null && !add.getAddressId().equals("") && !add.getAddressId().equals("Active")){
							
							String updateString = "<ADDRESS_ID>~<ADDRESS_TYPE>~<AddressValue>~<STATE>~<CITY>~<PIN>~<LANDMARK>~<LANDLINE>~<EXTENSION>~<LIVING_SINCE_YY>"
									+ "~<LIVING_SINCE_MM>~<OCCUPANCY_ST>~<MAILING_ADDRESS>~<Street_Name>~<Area_Name>~<COMPANY_NAME>~<LOCALITY>~<LANDLINE2>~<FAX>~<OLD_ADDRESS>~<GSTIN_NO>~<MarketValue>~<CURRENT_AREA_YY>~<BUSSINESS_ESTB_YY>~<DESTINATION_ADDRESS>~<MOBILE1>~<MOBILE2>~<EMAIL>";
							
							isUpdateAddressPresent = true;
							updateString = updateString.replaceAll("<ADDRESS_TYPE>", (add.getAddressType() == null || add.getAddressType().equals("-1")) ? "1000000001" : add.getAddressType());
							updateString = updateString.replaceAll("<ADDRESS_ID>", add.getAddressId() == null ? "" : add.getAddressId());
							updateString = updateString.replaceAll("<AddressValue>", add.getAddress() == null ? "" : add.getAddress());					
							updateString = updateString.replaceAll("<STATE>", (add.getState() == null || add.getState().equals("-1")) ? "" : add.getState());
							updateString = updateString.replaceAll("<CITY>", (add.getCity() == null || add.getCity().equals("-1")) ? "" : add.getCity());
							updateString = updateString.replaceAll("<PIN>", add.getZipcode() == null ? "" : add.getZipcode());	
							updateString = updateString.replaceAll("<LANDMARK>", add.getLandmark() == null ? "" : add.getLandmark());	
							updateString = updateString.replaceAll("<LANDLINE>", add.getPhone1() == null ? "" : add.getPhone1());		
							updateString = updateString.replaceAll("<EXTENSION>", add.getExt1() == null ? "" : add.getExt1());
							updateString = updateString.replaceAll("<LIVING_SINCE_YY>",add.getOccupancyYr() == null ? "" : add.getOccupancyYr()).replaceAll(",","");
							updateString = updateString.replaceAll("<LIVING_SINCE_MM>",add.getOccupancyMm() == null ? "" : add.getOccupancyMm());				
							updateString = updateString.replaceAll("<OCCUPANCY_ST>",add.getOccupancyStatus() == null ? "" : add.getOccupancyStatus());					
							updateString = updateString.replaceAll("<MAILING_ADDRESS>", ( CommonUtils.toString(add.getMailingAddress()).equalsIgnoreCase("") || CommonUtils.toString(add.getMailingAddress()).equalsIgnoreCase("N") ) ? "N" : "Y");
							updateString = updateString.replaceAll("<Street_Name>", add.getFlatNo() == null ? "" : add.getFlatNo());
							updateString = updateString.replaceAll("<Area_Name>", add.getFloorNo() == null ? "" : add.getFloorNo());
							updateString = updateString.replaceAll("<COMPANY_NAME>", add.getCompany_name() == null ? "" : add.getCompany_name());
							updateString = updateString.replaceAll("<LOCALITY>", add.getLocality() == null ? "" : add.getLocality());
							updateString = updateString.replaceAll("<LANDLINE2>", add.getPhone2() == null ? "" : add.getPhone2());
							updateString = updateString.replaceAll("<FAX>", add.getFax() == null ? "" : add.getFax());
							updateString = updateString.replaceAll("<OLD_ADDRESS>", add.getOldaddress() == null ? "" : add.getOldaddress());
							updateString = updateString.replaceAll("<GSTIN_NO>", add.getGstinno() == null ? "" : add.getGstinno());
							updateString = updateString.replaceAll("<MarketValue>", add.getMarketvalue() == null ? "" : add.getMarketvalue());
							updateString = updateString.replaceAll("<CURRENT_AREA_YY>",	add.getCurrentareaYr() == null ? "" : add.getCurrentareaYr());
							updateString = updateString.replaceAll("<BUSSINESS_ESTB_YY>",	add.getBussinessestbyr() == null ? "" : add.getBussinessestbyr()).replace("!","");;
							updateString = updateString.replaceAll("<DESTINATION_ADDRESS>",	add.getDestinationAddress() == null ? "" : add.getDestinationAddress());
							updateString = updateString.replaceAll("<EXTENSION2>",	add.getExt2() == null ? "" : add.getExt2());
							updateString = updateString.replaceAll("<MOBILE1>",	add.getMobile_no1() == null ? "" : add.getMobile_no1());
							updateString = updateString.replaceAll("<MOBILE2>",	add.getMobile_no2() == null ? "" : add.getMobile_no2());   
							updateString = updateString.replaceAll("<EMAIL>",	add.getEmail()== null ? "" : add.getEmail().replaceAll(",", ""));
							
							if (list.size() > 1 && i < list.size() - 1)
								updateString += "!";
							
							finalUpdateString = finalUpdateString + updateString;
						}
					}
				}

			}
			
			if(!isInsertAddressPresent){
				finalInsertString = "";
			}else{
				finalInsertString = finalInsertString + "<ADDRESS>";
				finalInsertString = finalInsertString.contains("!<ADDRESS>") ? finalInsertString.replaceAll("!<ADDRESS>","<ADDRESS>") : finalInsertString; 
			}
			if(!isUpdateAddressPresent){
				finalUpdateString = "";
			}else{
				finalUpdateString = finalUpdateString + "<ADDRESS>";
				finalUpdateString = finalUpdateString.contains("!<ADDRESS>") ? finalUpdateString.replaceAll("!<ADDRESS>","<ADDRESS>") : finalUpdateString;
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.error("AddressServiceImpl | addUpdateAddress() | "+e.getMessage()+" | :-END");
		}	
		return addressDao.saveAddressFromProcedure(finalInsertString, finalUpdateString, caseId);
	}


}