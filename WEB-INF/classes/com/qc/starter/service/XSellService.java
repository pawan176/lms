package com.qc.starter.service;

import java.util.List;

import com.qc.starter.entity.CaseXSellEntity;

public interface XSellService {

	public List<CaseXSellEntity> getXSellList(int leadid);

	public boolean addXSell(CaseXSellEntity caseXSellEntity);

	public boolean deleteXSell(List<CaseXSellEntity> listXSell);

	public String updateXSell(List<CaseXSellEntity> listXSell);
	
	public String insertXSell(List<CaseXSellEntity> listXSell);

	public boolean convertXLead(String sellCode);
	
}
