package com.qc.starter.dao.daoImpl;

import org.springframework.transaction.annotation.Transactional;

import com.qc.starter.dao.AllocateCasesDaoInf;
import com.qc.starter.dto.PersonListDto;

public class AllocateCasesDaoImpl implements AllocateCasesDaoInf{

	@Override
	@Transactional
	public void allocate_cases(PersonListDto persondto) {

	}

}
