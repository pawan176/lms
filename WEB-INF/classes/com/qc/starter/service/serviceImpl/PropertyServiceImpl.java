package com.qc.starter.service.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.qc.starter.dao.PropertyDao;
import com.qc.starter.entity.ProjectEntity;
import com.qc.starter.entity.PropertyEntity;
import com.qc.starter.entity.UserEntity;
import com.qc.starter.service.PropertyService;


@Service
public class PropertyServiceImpl implements PropertyService {
	private static final Logger logger = Logger.getLogger(PropertyServiceImpl.class.getName());
	@Autowired PropertyDao propertyDao;
	@Autowired HttpSession session;
	
	@Override
	public List<PropertyEntity> getListOfProperty(String personalDetailId,String companyId) {
		
		return propertyDao.getListOfProperty(personalDetailId,companyId);
	}

	@Override
	public boolean addProperty(PropertyEntity propertyEntity) {
		UserEntity user = (UserEntity)session.getAttribute("UserDetails");
		propertyEntity.setCreatedBy(user.getUserid().toString());
		propertyEntity.setCreatedDate(new Date());
		propertyEntity.setCreatedSysDate(new Date());
		return propertyDao.addProperty(propertyEntity);
	}

	@Override
	public int deleteProperty(List<PropertyEntity> listProperty) {
		logger.info("PropertyServiceImpl | deleteProperty() | :-START");
		List<PropertyEntity> list = new ArrayList<PropertyEntity>();
		for(PropertyEntity p : listProperty){
			if(p.getPropertyId()!=null && !p.getPropertyId().equalsIgnoreCase("active")){
				list.add(p);
			}
		}
		logger.info("PropertyServiceImpl | deleteProperty() | :-END");
		return propertyDao.deleteProperty(list);
	}

	@Override
	public boolean updateProperty(List<PropertyEntity> listProperty) {
		List<PropertyEntity> list = new ArrayList<PropertyEntity>();
		for(PropertyEntity p : listProperty){
			if(p.getPropertyId()!=null){
				list.add(p);
			}
		}
		return propertyDao.updateProperty(list);
	}

	@Override
	public int addPropertyList(List<PropertyEntity> toInsert) {
		
		return propertyDao.addPropertyList(toInsert);
	}

	@Override
	public int updatePropertyList(List<PropertyEntity> toUpdate) {
		
		return propertyDao.updatePropertyList(toUpdate);
	}

	@Override
	public void addUpdatePropertyList(List<PropertyEntity> list, String caseId,
			String personalDetailId, UserEntity userEntity) {
		logger.info("PropertyServiceImpl | addUpdatePropertyList() | :-START");
		List<PropertyEntity> toInsert = new ArrayList<PropertyEntity>();
		List<PropertyEntity> toUpdate = new ArrayList<PropertyEntity>();
		try{
			for(PropertyEntity prop : list){
				if(prop!=null){
					if(prop.getPropertyId()!=null){
						if(!prop.getPropertyId().equalsIgnoreCase("active")){
							prop.setUpdatedBy(userEntity.getUserid().toString());
							prop.setUpdatedDate(new Date());
							prop.setUpdatedSysDate(new Date());
							toUpdate.add(prop);
						}else{
							prop.setCreatedBy(userEntity.getUserid().toString());
							prop.setCreatedDate(new Date());
							prop.setCreatedSysDate(new Date());
							prop.setCaseId(caseId);
							prop.setPersonalDtlId(personalDetailId);
							toInsert.add(prop);
						}
					}
				}
			}
			if(toInsert.size()>0)
			propertyDao.addPropertyList(toInsert);
			if(toUpdate.size()>0)
			propertyDao.updatePropertyList(toUpdate);
		}catch(Exception e){
			logger.error("PropertyServiceImpl | addUpdatePropertyList() | "+e.getMessage()+" | :-END");
		}finally{
			toInsert = null;
			toUpdate = null;
		}
		logger.info("PropertyServiceImpl | addUpdatePropertyList() | :-END");
	}

	public String getListOfProject(String devloperId,String companyId) {
		logger.info("PropertyServiceImpl | getListOfProject() | :-START");
		  String json=null;
		try{
			List<ProjectEntity> projectList=propertyDao.getListOfProject(devloperId, companyId);
		    json=new Gson().toJson(projectList);
		}catch(Exception e){
			e.printStackTrace();
			logger.info("Error in PropertyServiceImpl | getListOfProject() |"+	e.getMessage());
		}
		logger.info("PropertyServiceImpl | getListOfProject() | :-END");
		return json;
	}

}
