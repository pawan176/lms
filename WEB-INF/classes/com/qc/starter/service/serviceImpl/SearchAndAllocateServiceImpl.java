package com.qc.starter.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qc.starter.dao.SearchAndAllocateDao;
import com.qc.starter.dto.SearchAndAllocateDto;
import com.qc.starter.entity.UserEntity;
import com.qc.starter.service.SearchAndAllocateService;

@Service
public class SearchAndAllocateServiceImpl implements SearchAndAllocateService{
	
	@Autowired SearchAndAllocateDao searchAndAllocateDao;
	
	public List<SearchAndAllocateDto> getLeadsToAllocate(UserEntity userEntity,String type, String userId){
		return searchAndAllocateDao.getLeadsToAllocate(userEntity,type, userId);
	}

	@Override
	public void allotcases(List<SearchAndAllocateDto> s, UserEntity userEntity,	String flag) {
		searchAndAllocateDao.allotcases(s, userEntity, flag);
		
	}

}
