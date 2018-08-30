package com.qc.starter.service.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qc.starter.dao.MobileDao;
import com.qc.starter.entity.MobileEntity;
import com.qc.starter.entity.UserEntity;
import com.qc.starter.service.MobileService;

@Service
public class MobileServiceImpl implements MobileService {
	private static final Logger logger =  Logger.getLogger(MobileServiceImpl.class.getName()); 
	@Autowired
	MobileDao mobileDao;

	@Override
	public List<MobileEntity> getListOfMobile(int personalDetailId) {
		return mobileDao.getListOfMobile(personalDetailId);
	}

	@Override
	public void addMobile(MobileEntity mobileEntity) {
		if(mobileEntity!=null){
			
			if (mobileEntity.getPrimaryContact().equals("Y")) {
				mobileDao.addDefaultMobile(mobileEntity);
			} else {
				mobileDao.addMobile(mobileEntity);
			}
		}
	}

	@Override
	public void deleteMobile(List<MobileEntity> list) {
		if(list!=null){
			
			List<String> toDelete = new ArrayList<String>();
			for (MobileEntity mob : list) {
				if (mob.getCaseMobileId() != null && !mob.getCaseMobileId().equalsIgnoreCase("Active") && !mob.getPrimaryContact().equalsIgnoreCase("Y"))
					toDelete.add(mob.getCaseMobileId());
			}
			if (toDelete.size() > 0)
				mobileDao.deleteMobile(toDelete);
		}
		
	}

	@Override
	public void updateMobile(List<MobileEntity> list, String caseId) {
		if(list!=null){
			
			List<MobileEntity> toUpdate = new ArrayList<MobileEntity>();
			for (MobileEntity mob : list) {
				if (mob.getCaseMobileId() != null) {
					mob.setCaseId(caseId);
					toUpdate.add(mob);
				}
			}
			if (toUpdate.size() > 0)
				mobileDao.updateMobile(toUpdate);
		}
		

	}

	@Override
	public boolean addUpdateContact(List<MobileEntity> listMobile,String caseId, String userId) {
		logger.info("MobileServiceImpl | addUpdateContact() | :-START");
		List<MobileEntity> toUpdate = new ArrayList<MobileEntity>();
		List<MobileEntity> toInsert = new ArrayList<MobileEntity>();
		String flag="NotUpdateAllMobile";
		try{
			for(MobileEntity mobile : listMobile){
				if(mobile!=null && mobile.getCaseMobileId()!=null){
					if(mobile.getCaseMobileId().equalsIgnoreCase("Active") && mobile.getContactNo()!=null && !mobile.getContactNo().equalsIgnoreCase("")){
						mobile.setCaseMobileId("");
						mobile.setCaseId(caseId);
						mobile.setCreatedBy(userId);
						mobile.setCreatedDate(new Date());
						mobile.setCreatedSysDate(new Date());
						toInsert.add(mobile);
						if(mobile.getPrimaryContact().equalsIgnoreCase("Y")){
							flag="updateAllMobile";
						}
					}else{
						if(mobile.getPrimaryContact().equalsIgnoreCase("Y")){
							flag="updateAllMobile";
						}
						mobile.setCaseId(caseId);
						mobile.setUpdatedBy(userId);
						mobile.setUpdatedDate(new Date());
						mobile.setUpdatedSysDate(new Date());
						toUpdate.add(mobile);
					}
				}
			}
			if(flag.equalsIgnoreCase("updateAllMobile")){
				mobileDao.updateAllMobileList(caseId);
			}
			mobileDao.addMobileList(toInsert);
			mobileDao.updateMobileList(toUpdate);
		
		}catch(Exception e){
			e.printStackTrace();
			logger.error("MobileServiceImpl | addUpdateContact() | "+e.getMessage()+" | :-END");
		}finally{
			toUpdate = null;
			toInsert = null;
		}
		logger.info("MobileServiceImpl | addUpdateContact() | :-END");
		return true;
	}

}
