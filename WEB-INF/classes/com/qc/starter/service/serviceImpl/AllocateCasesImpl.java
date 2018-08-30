package com.qc.starter.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;


import com.qc.starter.dao.AllocateCasesDaoInf;
import com.qc.starter.dto.PersonListDto;
import com.qc.starter.service.AllocateCases;


public class AllocateCasesImpl implements AllocateCases {

	@Autowired AllocateCasesDaoInf allocateCases;
	@Override
	public void allocateCases(PersonListDto persondto) {		
		allocateCases.allocate_cases(persondto);
	}

}
