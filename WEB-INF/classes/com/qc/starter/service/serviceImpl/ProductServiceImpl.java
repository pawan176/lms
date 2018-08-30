package com.qc.starter.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qc.starter.dao.ExistingFacilityDao;
import com.qc.starter.dao.FacilityRequestedDao;
import com.qc.starter.dao.MasterDao;
import com.qc.starter.dao.ProductDao;
import com.qc.starter.dto.ConvertToCustomerDto;
import com.qc.starter.dto.ProductDto;
import com.qc.starter.entity.ExistingFacilityEntity;
import com.qc.starter.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired ProductDao productDao;
	@Autowired MasterDao masterDao;
	@Autowired FacilityRequestedDao facilityRequestedDao;
	@Autowired ExistingFacilityDao existingFacilityDao;

	public ProductDto fetchProduct(String leadid){
		return facilityRequestedDao.getFacilitiesRequested(leadid);
	}

	@Override
	public List<ExistingFacilityEntity> getFacilityHistory(String leadid){				
		List<ExistingFacilityEntity> existingFacilityHistoryList = new ArrayList<ExistingFacilityEntity>();		
		existingFacilityHistoryList = existingFacilityDao.getFacilitiesExisting(leadid);		
		return existingFacilityHistoryList;
	}

	@Override
	public List<ProductDto> getEligibilityCalcData(ProductDto productDto) {
		return  productDao.getEligibilityCalcData(productDto);
	}

	public String getProductCategory(String productId) {
		return productDao.getProductCategory(productId);
	}

	@Override
	public String convertToCustomer(String caseId,int userId,String branchId) {
		// TODO Auto-generated method stub
		return productDao.convertToCustomer(caseId,userId,branchId);
	}

	@Override
	public ConvertToCustomerDto getApplicantId(String leadid) {
		return productDao.getApplicantId(leadid);	 
	}
	
	@Override
	public boolean isLeadConvertToCustomer(String leadid) {
		return productDao.isLeadConvertToCustomer(leadid);	 
	}

}
