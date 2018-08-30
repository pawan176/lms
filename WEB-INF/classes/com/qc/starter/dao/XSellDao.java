package com.qc.starter.dao;

import java.util.List;

import com.qc.starter.entity.CaseXSellEntity;

public interface XSellDao {

	public List<CaseXSellEntity> getXSellList(Integer integer);

	public boolean addXSell(CaseXSellEntity caseXSellEntity);

	public boolean deleteXSell(List<CaseXSellEntity> list);

	public String updateXSell(List<CaseXSellEntity> list);

	public String insertXSell(List<CaseXSellEntity> list);

	public boolean convertXLead(String param);

	public CaseXSellEntity getXSellDetail(String xSellId);

}
