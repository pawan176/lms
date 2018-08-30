package com.qc.starter.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qc.starter.dao.XSellDao;
import com.qc.starter.entity.CaseXSellEntity;
import com.qc.starter.entity.UserEntity;
import com.qc.starter.service.XSellService;
@Service
public class XSellServiceImpl implements XSellService {

	@Autowired XSellDao xSellDao;
	@Autowired HttpSession httpSession;

	@Override
	public List<CaseXSellEntity> getXSellList(int leadid) {
		return xSellDao.getXSellList(new Integer(leadid));
	}

	@Override
	public boolean addXSell(CaseXSellEntity caseXSellEntity) {
		// TODO Auto-generated method stub
		return xSellDao.addXSell(caseXSellEntity);
	}

	@Override
	public boolean deleteXSell(List<CaseXSellEntity> listXSell) {
		List<CaseXSellEntity> list = new ArrayList<CaseXSellEntity>();
		for(CaseXSellEntity xSellEntity : listXSell){
			if(xSellEntity.getXsellId()!=null){
				list.add(xSellEntity);
			}
		}
		return xSellDao.deleteXSell(list);

	}

	@Override
	public String updateXSell(List<CaseXSellEntity> listXSell) {
		return xSellDao.updateXSell(listXSell);
	}

	@Override
	public boolean convertXLead(String sellCode) {
		CaseXSellEntity xSellEntity = xSellDao.getXSellDetail(sellCode);
		UserEntity userEntity = (UserEntity)httpSession.getAttribute("UserDetails");

		String param = "<XSELL_ID>~<CASE_ID>~<FACILITY_REQ_ID>~<BANK_ID>~<AMOUNT>~<REMARKS>~<USER_ID>~<COMPANY_ID>";
		param = param.replaceAll("<XSELL_ID>",xSellEntity.getXsellId() == null ? "" : xSellEntity.getXsellId());
		param = param.replaceAll("<CASE_ID>",xSellEntity.getParentCaseId() == null ? "" : xSellEntity.getParentCaseId());
		param = param.replaceAll("<FACILITY_REQ_ID>",xSellEntity.getFacilityReqId() == null ? "" : xSellEntity.getFacilityReqId());
		param = param.replaceAll("<BANK_ID>",xSellEntity.getBankId() == null ? "" : xSellEntity.getBankId());
		param = param.replaceAll("<AMOUNT>",xSellEntity.getAmount() == null ? "" : xSellEntity.getAmount());
		param = param.replaceAll("<REMARKS>",xSellEntity.getRemarks() == null ? "" : xSellEntity.getRemarks());
		param = param.replaceAll("<USER_ID>",userEntity.getUserid().toString() == null ? "" : userEntity.getUserid().toString());
		param = param.replaceAll("<COMPANY_ID>",userEntity.getCompanyId());
		return xSellDao.convertXLead(param);
	}

	public String insertXSell(List<CaseXSellEntity> listXSell) {
		return xSellDao.insertXSell(listXSell);
	}

}
