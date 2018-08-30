package com.qc.starter.service;

import java.util.List;

import com.qc.starter.dto.SearchAndAllocateDto;
import com.qc.starter.entity.UserEntity;

public interface SearchAndAllocateService {
	
	public List<SearchAndAllocateDto> getLeadsToAllocate(UserEntity userEntity,String type, String userId);
	public void allotcases(List<SearchAndAllocateDto> s,UserEntity userEntity,String flag);
	

}
