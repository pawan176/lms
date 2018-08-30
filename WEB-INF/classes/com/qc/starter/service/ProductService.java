package com.qc.starter.service;

import java.util.List;

import com.qc.starter.dto.ConvertToCustomerDto;
import com.qc.starter.dto.ProductDto;
import com.qc.starter.entity.ExistingFacilityEntity;

public interface ProductService {

	public ProductDto fetchProduct(String leadid);
	public List<ExistingFacilityEntity> getFacilityHistory(String leadid);
	public List<ProductDto> getEligibilityCalcData(ProductDto productDto);
	//-----Added by Deepak on 05 march -2016------------
	public String getProductCategory(String productId);
	public String convertToCustomer(String caseId,int userId,String branchId);
	public ConvertToCustomerDto getApplicantId(String leadid);	
	public boolean isLeadConvertToCustomer(String leadid);
	
}
