package com.qc.starter.mobileAction;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.qc.starter.basic.CommonUtils;
import com.qc.starter.dto.ProductDto;

@Component
public class EligibilityCalculatorParser {
public ProductDto getJsonToDto(String jsonRequest){
	ProductDto productDto=null;
	try {
		productDto=new ProductDto();
		JSONObject jsonObject=new JSONObject(jsonRequest);
		/*Date date = null;
		try {
			if(!jsonObject.getString("dob").equalsIgnoreCase("")){
				date = new SimpleDateFormat("yyyy-MM-dd").parse(jsonObject.getString("dob"));
			}
		} catch (ParseException e) {
			e.printStackTrace();
			
		}*/
		if(jsonObject.getString("facilityReqId").equalsIgnoreCase("17")){
		productDto.setFacilityRequestedLoanAmount(jsonObject.getString("loanAmount").replaceAll(",", ""));
		productDto.setFacilityRequestedTenor(jsonObject.getString("tenor"));
		}
		//productDto.setDob(date);
		//productDto.setGrossMonthlyIncome(jsonObject.getString("grossSalary"));
		//productDto.setNetMonthlyincome(jsonObject.getString("netSalary"));
		//productDto.setYearInjob(jsonObject.getString("yearJob"));
		//productDto.setWorkingExp(jsonObject.getString("workingExprence"));
		//productDto.setCorpSalaryAc(jsonObject.getString("corparateAccount"));
		productDto.setCompyId(jsonObject.getString("companyId"));
		productDto.setFacilityReqId(jsonObject.getString("facilityReqId"));
	//	productDto.setLeadId(jsonObject.getString("leadId"));1001012220
		productDto.setLeadId("1001012220");
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	return productDto;
}
public String getDtotoJson(List<ProductDto> prodList){
			StringBuffer responseJson=new StringBuffer();
			String syncDate = CommonUtils.getCurrentDate();
			try {
				if(prodList!=null && prodList.size()>0){
					responseJson.append("{\"status\":\"S\",\"syncDate\":\""+syncDate+"\",\"message\":\"SUCCESS\",\"elgCalculatorList\":[");
					for(ProductDto dto:prodList){
						responseJson.append("{\"eligibilityId\":\""+dto.getEligibilityId()+"\",\"minLoanAmount\":\""+dto.getMinLoanAmount()+"\",\"maxLoanAmount\":\""+dto.getMaxLoanAmount()+"\",\"minTenor\":\""+dto.getMinTenor()+"\",\"maxTenor\":\""+dto.getMaxTenor()+"\",\"bankId\":\""+dto.getBankId()+"\",");
						responseJson.append("\"bankName\":\""+dto.getBankName()+"\",\"facilityRequestedRateOfIntrest\":\""+dto.getFacilityRequestedRateOfIntrest()+"\",\"facilityRequestedBank\":\""+dto.getFacilityRequestedBank()+"\",\"ltv\":\""+dto.getLtv()+"\",\"obligationAmt\":\""+dto.getObligationAmt()+"\",\"facilityRequestedLoanAmount\":\""+dto.getFacilityRequestedLoanAmount()+"\",");
						responseJson.append("\"facilityRequestedTenor\":\""+dto.getFacilityRequestedTenor()+"\",\"prodId\":\""+dto.getProdId()+"\",\"schemeName\":\""+dto.getSchemeName()+"\",\"processingFee\":\""+dto.getProcessingFee()+"\",\"prePaymentFee\":\""+dto.getPrePaymentFee()+"\",\"renewalFee\":\""+dto.getRenewalFee()+"\",\"fixedFloatingSemi\":\""+dto.getFixedFloatingSemi()+"\",");
						responseJson.append("\"applicationFee\":\""+dto.getApplicationFee()+"\",\"renewalFee\":\""+dto.getRenewalFee()+"\",\"text\":\""+dto.getText()+"\",\"facilityRequestedEmi\":\""+dto.getFacilityRequestedEmi()+"\"},");						
					}
					responseJson.deleteCharAt(responseJson.length()-1);
				}else{
					responseJson.append("{\"status\":\"F\",\"syncDate\":\""+syncDate+"\",\"message\":\"FAILURE\",\"elgCalculatorList\":[");						
				}
				responseJson.append("]}");
			} catch (Exception e) {
				e.printStackTrace();
			}
	return responseJson+"";
}
}
