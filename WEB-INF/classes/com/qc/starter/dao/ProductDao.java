package com.qc.starter.dao;

import java.util.List;

import com.qc.starter.dto.ContactDto;
import com.qc.starter.dto.ConvertToCustomerDto;
import com.qc.starter.dto.ProductDto;

public interface ProductDao {

	public List fetchProduct(String leadid);
	public List<ProductDto> getEligibilityCalcData(ProductDto productDto);
	//-----Added by Deepak on 05 march -2016------------
	public String getProductCategory(String productId);
	public String convertToCustomer(String caseId,int userId,String branchId);
	public ConvertToCustomerDto getApplicantId(String string);
	public boolean isLeadConvertToCustomer(String leadid);
	public boolean addActionForConvertToCustomerLead(ContactDto contactDto);
}
