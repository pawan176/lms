package com.qc.starter.mobileAction;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.qc.starter.dto.ProductDto;
import com.qc.starter.entity.CaseXSellEntity;
import com.qc.starter.entity.ExistingFacilityEntity;

public class ProductJsonParser {

	public ProductDto getJsonToProductDto(String requestJson){ 
		ProductDto productDto=null;
		try{
			JSONObject jsonObject=new JSONObject(requestJson);
			productDto=new ProductDto();
			productDto.setFacilityRequested(jsonObject.getString("queuId"));
			productDto.setFacilityRequestedBank(jsonObject.getString("bankid"));
			productDto.setFacilityRequestedLoanAmount(jsonObject.getString("requestedLoanAmount"));
			productDto.setFacilityRequestedRateOfIntrest(jsonObject.getString("requestedRateOfIntrest"));
			productDto.setFacilityRequestedTenor(jsonObject.getString("requestedTenor"));
			productDto.setFacilityRequestedEmi(jsonObject.getString("requestedEmi"));
			productDto.setFacilityRequestedReCalEmi(jsonObject.getString("requestedReCalEmi"));
			productDto.setUserId(jsonObject.getString("userId"));
			productDto.setCompyId(jsonObject.getString("companyId"));
			productDto.setEligibilityId(jsonObject.getString("eligibilityId"));
			productDto.setMaxLoanAmount(jsonObject.getString("maxLoanAmt"));
			productDto.setTermCond(jsonObject.getString("termCondition"));
			productDto.setPurposeOfLoanId(jsonObject.getString("purposeOfLoanId"));
			productDto.setHiddenLeadId(jsonObject.getString("caseId"));
			//--------JsonList for caseXSellList-------------------
			if(!jsonObject.isNull("caseXSellList")){
			jsonObject.isNull("caseXSellList");
			JSONArray jsonArray1=jsonObject.getJSONArray("caseXSellList");
			
			List<CaseXSellEntity> listXSell=new LinkedList<CaseXSellEntity>();
			for(int i=0;i<jsonArray1.length();i++){
				JSONObject caseXSellJson=jsonArray1.getJSONObject(i);
				CaseXSellEntity caseXSell=new CaseXSellEntity();
				caseXSell.setXsellId(caseXSellJson.getString("xsellId"));
				caseXSell.setCaseId(caseXSellJson.getString("caseId"));
				caseXSell.setFacilityReqId(caseXSellJson.getString("facilityReqId"));
				caseXSell.setBankId(caseXSellJson.getString("bankId"));
				caseXSell.setAmount(caseXSellJson.getString("amount"));
				caseXSell.setRemarks(caseXSellJson.getString("remarks"));
				caseXSell.setUpdatedBy(caseXSellJson.getString("userId"));
				listXSell.add(caseXSell);
			}
			productDto.setListXSell(listXSell);
			}
			//--------JsonList for existingFacilityHistoryList-------------------
			if(!jsonObject.isNull("existingFacilityHistoryList")){
			JSONArray jsonArray2=jsonObject.getJSONArray("existingFacilityHistoryList");
			List<ExistingFacilityEntity> existingFacilityHistory=new LinkedList<ExistingFacilityEntity>();
			for(int i=0;i<jsonArray2.length();i++){
				JSONObject existingFacilityHistoryJson=jsonArray2.getJSONObject(i);	
				ExistingFacilityEntity existingFacility=new ExistingFacilityEntity();
				existingFacility.setCheckRow(existingFacilityHistoryJson.getString("checkRow"));
				existingFacility.setPersonalDtlId(existingFacilityHistoryJson.getString("personalDtlId"));
				existingFacility.setCaseId(existingFacilityHistoryJson.getString("caseId"));
				existingFacility.setProdId(existingFacilityHistoryJson.getString("productId"));
				existingFacility.setBankId(existingFacilityHistoryJson.getString("bankId"));
				existingFacility.setLoanCcAmt(existingFacilityHistoryJson.getString("loanCCAmount"));
				existingFacility.setEmiCcOutstanding(existingFacilityHistoryJson.getString("emiCcOutstanding"));
				existingFacility.setTenorBalYears(existingFacilityHistoryJson.getString("tenorBalYears"));
				existingFacility.setTenorBalMonths(existingFacilityHistoryJson.getString("tenorBalMonths"));
				existingFacility.setRemarks(existingFacilityHistoryJson.getString("remarks"));
				existingFacility.setUpdatedBy(existingFacilityHistoryJson.getString("userId"));
				existingFacility.setCreatedDate(getDateFromString(existingFacilityHistoryJson.getString("createdDate")));
				existingFacility.setCreatedSysDate(getDateFromString(existingFacilityHistoryJson.getString("createdSysDate")));
				existingFacilityHistory.add(existingFacility);
			}
			productDto.setExistingFacilityHistory(existingFacilityHistory);
		}
			//-------------------------------------------------------
		}catch(Exception e){
			e.printStackTrace();
		}
		return productDto;
	}
	public Date getDateFromString(String dateStr){
		Date sqlDate=null;
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MMM-yyyy");
		if(dateStr!=null && !dateStr.equalsIgnoreCase("")){
			try {			
			java.util.Date date= sdf.parse(dateStr);
			sqlDate=new Date(date.getTime());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return sqlDate;
	}
}
