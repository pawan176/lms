package com.qc.starter.dao.daoImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.qc.starter.dao.FacilityRequestedDao;
import com.qc.starter.dto.ProductDto;
import com.qc.starter.entity.ExistingFacilityEntity;
import com.qc.starter.entity.UserEntity;

@Repository
public class FacilityRequestedDaoImpl implements FacilityRequestedDao {
	private static final Logger logger = Logger.getLogger(ExistingFacilityDaoImpl.class.getName());
	@Autowired	SessionFactory sessionFactory;
	@Autowired HttpSession httpSession;

	@Transactional
	public ProductDto getFacilitiesRequested(String caseId) {
		logger.info("FacilityRequestedDaoImpl  | getFacilitiesRequested() | START ");	
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		Session session=null;
		SQLQuery query=null;
		ProductDto productDto = new ProductDto();
		try{			
			session = sessionFactory.openSession();
			UserEntity userEntity = (UserEntity) httpSession.getAttribute("UserDetails");
			String sqlquery=" SELECT QC.ELIGI_ID,QC.CASE_ID LEAD_ID,QC.CASE_CODE LEAD_CODE,QC.FACILITY_REQ_ID,QC.SCHEME_ID,"+
			" (SELECT QCP.PRODNAME FROM QC_PROSPECT_MASTER.QM_PRODUCT  QCP "+
			" WHERE QCP.PRODTYPEID = 2 AND QCP.PRODUCTID = QC.FACILITY_REQ_ID AND QCP.ACTIVE = 'A' "+
			" AND QCP.COMPANY_ID = "+userEntity.getCompanyId()+") FACILITY_REQUESTED,QC.BANK_ID,"+
			" (SELECT QB.BANK_NAME FROM QC_PROSPECT_MASTER.QM_BANK QB WHERE QB.BANK_ID = QC.BANK_ID "+
			" AND QB.ACTIVE = 'A') BANK,TO_CHAR(QC.AMOUNT, '99,99,99,99,99,999') AMOUNT,"+
			" TO_CHAR(QC.EMI, '99,99,99,999') EMI,TO_CHAR(QC.LOAN_AMOUNT, '99,99,99,99,99,999') LOAN_AMOUNT ,TO_CHAR(QC.MAX_EMI, '99,99,99,999') MAX_EMI,"+
			" QC.ROI,QC.TENOR_MONTH,QC.LOAN_TENURE,QPD.Dob,qpd.GROSS_MONTHLY_INC,qpd.net_monthly_inc,"+
			" qpd.work_exp,qpd.years_current_job,qpd.corperate_sal_acc,TO_CHAR(QC.MAX_LOAN_AMT, '99,99,99,999') MAX_LOAN_AMT,"+
			" QC.TERMS_CONDITION,PE.PROC_ANNUAL_FEE,PE.PREP_JOIN_FEE,PE.PART_PAYMENT,PE.RENEWAL_FEE,"+
			" (SELECT X.CATEGORY_NAME FROM QC_PROSPECT_MASTER.QM_CASE_COMPANY_CATEGORY X,QC_PROSPECT_MASTER.QM_BANK_X_COMPANY_CAT    BC "+
			" WHERE X.CATEGORY_ID = BC.CATEGORY_ID AND BC.BANK_CAT_ID = IM.BANK_CAT_ID) CARD_NAME "+
			" ,(SELECT OCCUPATIONNAME FROM QC_PROSPECT_MASTER.QM_OCCUPATION  WHERE OCCUPATIONID=   qpd.OCCUPATION_type) OCCUPATION "+
			" , QPD.GROSS_PROFIT, QPD.NET_PROFIT_AFTR_TAX, QPD.ANNUAL_SALES," +
			" QC.PURPOSEOFLOANID ,QC.BRANCHID "+ 
			" FROM QC_PROSPECT.QT_CASE QC "+
			" LEFT OUTER JOIN QC_PROSPECT.QT_PERSONAL_DETAILS QPD ON (QPD.CASE_ID =QC.CASE_ID) "+
			" LEFT OUTER JOIN QC_PROSPECT_MASTER.QM_ELIGIBLITY PE ON (QC.ELIGI_ID =PE.ELIGI_ID) "+
			" LEFT OUTER JOIN QC_PROSPECT_MASTER.QM_BANK_CAT_INC IM ON (IM.BNK_INC_ID = PE.BNK_INC_ID) "+                                                        
			" WHERE QPD.CASE_ID = "+caseId+" AND QC.COMPANY_ID = "+userEntity.getCompanyId();
			query=session.createSQLQuery(sqlquery);	
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List result = query.list();
			if(result.size()>0){	
				Map map = (Map) result.get(0);
				productDto.setHiddenLeadId(map.get("LEAD_ID") == null ? "" : map.get("LEAD_ID")+"");
				productDto.setFacilityReqId(map.get("FACILITY_REQ_ID") == null ? "" : map.get("FACILITY_REQ_ID")+"");				
				productDto.setFacilityRequested(map.get("FACILITY_REQUESTED") == null ? "" : map.get("FACILITY_REQUESTED")+"");
				productDto.setFacilityRequestedBank(map.get("BANK") == null ? "" : map.get("BANK")+"");
				productDto.setFacilityRequestedLoanAmount(map.get("LOAN_AMOUNT") == null ? "" : (map.get("LOAN_AMOUNT")+"").trim());
				productDto.setFacilityRequestedRateOfIntrest(map.get("ROI") == null ? "" : map.get("ROI")+"");
				productDto.setFacilityRequestedTenor(map.get("LOAN_TENURE") == null ? "" : map.get("LOAN_TENURE")+"");
				productDto.setFacilityRequestedEmi(map.get("MAX_EMI") == null ? "" : (map.get("MAX_EMI")+"").trim());
				productDto.setFacilityRequestedReCalEmi(map.get("EMI") == null ? "" :(map.get("EMI")+"").trim());
				productDto.setSchemeId(map.get("SCHEME_ID") == null ? "" :(map.get("SCHEME_ID")+"").trim());
				/*Added for Eligibility Calculator*/
				if(map.get("DOB")!=null && !map.get("DOB").equals("")){
					productDto.setDob((Date) map.get("DOB"));
				}/*else
					productDto.setDob();*/
				productDto.setEligibilityId(map.get("ELIGI_ID") == null ? "" : map.get("ELIGI_ID")+"");
				productDto.setGrossMonthlyIncome(map.get("GROSS_MONTHLY_INC") == null ? "" : map.get("GROSS_MONTHLY_INC")+"");
				productDto.setNetMonthlyincome(map.get("NET_MONTHLY_INC") == null ? "" : map.get("NET_MONTHLY_INC")+"");
				productDto.setWorkingExp(map.get("WORK_EXP") == null ? "" : map.get("WORK_EXP")+"");
				productDto.setYearInjob(map.get("YEARS_CURRENT_JOB") == null ? "" : map.get("YEARS_CURRENT_JOB")+"");
				productDto.setCorpSalaryAc(map.get("CORPERATE_SAL_ACC") == null ? "" : map.get("CORPERATE_SAL_ACC")+"");
				if(productDto.getFacilityReqId().equalsIgnoreCase("6")){
					productDto.setMaxLoanAmount("");	
				}else
					productDto.setMaxLoanAmount(map.get("MAX_LOAN_AMT") == null ? "" : (map.get("MAX_LOAN_AMT")+"").trim());
				if(map.get("TERMS_CONDITION") == null || map.get("TERMS_CONDITION").toString().equalsIgnoreCase("null"))
					productDto.setTermCond("");
				else
					productDto.setTermCond(map.get("TERMS_CONDITION").toString());	
				if(productDto.getFacilityReqId().equalsIgnoreCase("6")){
					productDto.setCardName(map.get("CARD_NAME") == null ? "" : map.get("CARD_NAME")+"");
				}else
					productDto.setCardName("");
				productDto.setOccupationType(map.get("OCCUPATION")==null?"":map.get("OCCUPATION")+"");
				productDto.setGrossProfit(map.get("GROSS_PROFIT")==null?"":map.get("GROSS_PROFIT")+"");
				productDto.setNetProfitAftertax(map.get("NET_PROFIT_AFTR_TAX")==null?"":map.get("NET_PROFIT_AFTR_TAX")+"");
				productDto.setAnnualSales(map.get("ANNUAL_SALES")==null?"":map.get("ANNUAL_SALES")+"");
				//added by tripti on 4 oct
				productDto.setPurposeOfLoanId(map.get("PURPOSEOFLOANID")==null?"":map.get("PURPOSEOFLOANID")+"");
				productDto.setBranchId(map.get("BRANCHID")==null?"":map.get("BRANCHID")+"");
			} 
			sqlquery="SELECT CASE  WHEN PROP_TYPE_ID IS NOT NULL AND PROP_STATUS  IS NOT NULL AND" +
					" ESTIMATED_VALUE  IS NOT NULL AND CITY  IS NOT NULL AND DEVELOPER_ID  IS NOT NULL " +
					" AND PROJECT_ID  IS NOT NULL AND   STATE  IS NOT NULL  THEN 'Y' ELSE 'N' END CASE" +
					" FROM QC_prospect.QT_PROPERTY WHERE CASE_ID="+caseId;
				query=session.createSQLQuery(sqlquery);	
				query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
				List resul = query.list();
				String homeLoanValidation="";
				if (resul.size()>0) {
					Map map = (Map) resul.get(0);
					homeLoanValidation=map.get("CASE")==null?"":map.get("CASE").toString();
				}
				productDto.setHomeLoanValidation(homeLoanValidation);
			return productDto;
		}catch(Exception e){
			logger.error("FacilityRequestedDaoImpl | getFacilitiesRequested() | ERROR::::"+e.getMessage());
			e.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
		logger.info("FacilityRequestedDaoImpl | getFacilitiesRequested() | END ");
		return productDto;
	}

	@Transactional
	public boolean updateExistingFacility(ProductDto productDto) {
		logger.info("FacilityRequestedDaoImpl  | updateExistingFacility() | START ");
		Session session=null;
		SQLQuery query=null;
		int update = 0;
		try{			
			session=sessionFactory.openSession();
			//UserEntity userEntity = (UserEntity) httpSession.getAttribute("UserDetails");
			String bankid;
			String facilityRequiredId;
			String schemeId;
			String amount;
			String ROI;
			String tenorMonth;
			String EMI;
			String calEMI;
			String caseId;
			String companyId = productDto.getCompyId();
			String eligibilityId;
			String maxLoanAmt;
			String term;
			String purposeOfLoan;
			String branch;
			
			if(productDto.getPurposeOfLoanId() != null && !(productDto.getPurposeOfLoanId().trim().equals("")))
				purposeOfLoan = productDto.getPurposeOfLoanId();
			else
				purposeOfLoan = null;
			
			if(productDto.getFacilityRequestedBank() != null && !(productDto.getFacilityRequestedBank().trim().equals("")))
				bankid = productDto.getFacilityRequestedBank();
			else
				bankid = null;

			if(productDto.getFacilityRequested() != null && !(productDto.getFacilityRequested().trim().equals("")))
				facilityRequiredId = productDto.getFacilityRequested();
			else
				facilityRequiredId = null;
			
			if(productDto.getSchemeId() != null && !(productDto.getSchemeId().trim().equals("")))
				schemeId = productDto.getSchemeId();
			else
				schemeId = null;
			
			if(productDto.getFacilityRequestedLoanAmount() != null && !(productDto.getFacilityRequestedLoanAmount().trim().equals("")))
				amount = productDto.getFacilityRequestedLoanAmount().replaceAll(",", "");
			else
				amount = null;
			if(productDto.getFacilityRequestedRateOfIntrest() != null && !(productDto.getFacilityRequestedRateOfIntrest().trim().equals("")))
				ROI = productDto.getFacilityRequestedRateOfIntrest();
			else
				ROI = null;
			if(productDto.getFacilityRequestedTenor() != null && !(productDto.getFacilityRequestedTenor().trim().equals("")))
				tenorMonth = productDto.getFacilityRequestedTenor().replaceAll(",", "");	
			else
				tenorMonth = null;
			if(productDto.getFacilityRequestedEmi() != null && !(productDto.getFacilityRequestedEmi().trim().equals("")) )
				EMI = productDto.getFacilityRequestedEmi().replaceAll(",", "");			
			else
				EMI = null;
			if(productDto.getHiddenLeadId() != null && !(productDto.getHiddenLeadId().trim().equals("")))
				caseId = productDto.getHiddenLeadId();
			else
				caseId = null;	
			if(productDto.getEligibilityId()!=null && !(productDto.getEligibilityId().trim().equals(""))){
				eligibilityId = productDto.getEligibilityId();
			}
			else
				eligibilityId = null;
			if(productDto.getMaxLoanAmount()!=null && !(productDto.getMaxLoanAmount().trim().equals(""))){
				maxLoanAmt = productDto.getMaxLoanAmount().replaceAll(",", "");
			}
			else
				maxLoanAmt = null;
			if(productDto.getTermCond()!=null && !(productDto.getTermCond().trim().equals(""))){
				term = productDto.getTermCond();
			}
			else
				term = null;
			if(productDto.getFacilityRequestedReCalEmi() != null && !(productDto.getFacilityRequestedReCalEmi().trim().equals("")))
				calEMI = productDto.getFacilityRequestedReCalEmi().replaceAll(",", "");			
			else
				calEMI= null;
			
			if(productDto.getBranchId() != null && !(productDto.getBranchId().trim().equals("")))
				branch = productDto.getBranchId();
			else
				branch = null;
		
			String sql = "UPDATE QC_PROSPECT.QT_CASE QC	SET QC.FACILITY_REQ_ID ="+facilityRequiredId+", QC.SCHEME_ID = "+schemeId+", BRANCHID="+branch+", QC.PROD_ID ="+facilityRequiredId+" ,QC.QUEUE_ID ="+facilityRequiredId+", QC.BANK_ID = "
					+bankid+", QC.AMOUNT = "+amount+" , QC.LOAN_AMOUNT = "+amount+" , QC.ROI = "+ROI+ ",QC.TENOR_MONTH ="+tenorMonth+ ",QC.LOAN_TENURE ="+tenorMonth+ " , QC.MAX_EMI = "+EMI+",QC.EMI = "+calEMI+"" +
					", QC.ELIGI_ID="+eligibilityId+",QC.MAX_LOAN_AMT="+maxLoanAmt+",QC.TERMS_CONDITION='"+term+"'," +
					" QC.UPDATED_BY="+productDto.getUserId()+",QC.UPDATED_DATE=SYSDATE," +
					" QC.UPDATED_SYS_DATE=SYSDATE,PURPOSEOFLOANID="+purposeOfLoan+"" +
					" where  QC.CASE_ID ="+caseId+" and QC.COMPANY_ID = "+companyId;
			query = session.createSQLQuery(sql);
			update=query.executeUpdate();
		}catch(Exception e){
			logger.error("FacilityRequestedDaoImpl | updateExistingFacility() | ERROR:::"+e.getMessage());
			e.printStackTrace();
			return false;
		}finally {
			if(session!=null)
				session.close();
		}
		logger.info("FacilityRequestedDaoImpl | updateExistingFacility() | END ");
		if ( update > 0) {
			return true;
		}
		return false;
	}

	@Transactional
	public String addExistingFacility(UserEntity userEntity,List<ExistingFacilityEntity> existingFacilityHistoryInsert) {
		logger.info("FacilityRequestedDaoImpl | addExistingFacility() | END ");
		String update = "";
		Session session=null;
		SQLQuery query=null;
		int updatestatus=0;
		try{
			session = sessionFactory.openSession();
			String sql = "INSERT INTO QC_PROSPECT.QT_EXISTING_FAC" +
					"(EXITING_FAC_ID,PERSONAL_DTL_ID,CASE_ID,PROD_ID,BANK_ID,LOAN_CC_AMT,EMI_CC_OUTSTANDING,TENOR_BAL_YEARS,TENOR_BAL_MONTHS,REMARKS,CREATED_BY,CREATED_DATE,CREATED_SYS_DATE) " +
					" VALUES (QC_PROSPECT.SEQ_CASE_EXISTING_FAC.NEXTVAL,:personalDtlId,:caseId," +
					" :prodId,:bankId,:loanCCAmount,:emiCCOutStanding,:tenorBalYear,:tenorBalMonth," +
					" :remark,:userId,:createdDate,:createdSysDate)";
			query = session.createSQLQuery(sql);
			for(ExistingFacilityEntity existingFacilityEntity : existingFacilityHistoryInsert){
				String emiCCOutstanding=existingFacilityEntity.getEmiCcOutstanding();
				String loanCCAmount=existingFacilityEntity.getLoanCcAmt();
				if(emiCCOutstanding.contains(",")){
					emiCCOutstanding=emiCCOutstanding.replaceAll(",","");
				}
				if(loanCCAmount.contains(",")){
					loanCCAmount=loanCCAmount.replaceAll(",","");
				}
				query.setParameter("personalDtlId",existingFacilityEntity.getPersonalDtlId()!=null?existingFacilityEntity.getPersonalDtlId():"")
				.setParameter("caseId", existingFacilityEntity.getCaseId()!=null?existingFacilityEntity.getCaseId():"")
				.setParameter("prodId", existingFacilityEntity.getProdId()!=null?existingFacilityEntity.getProdId():"")
				.setParameter("bankId", existingFacilityEntity.getBankId()!=null?existingFacilityEntity.getBankId():"")
				.setParameter("loanCCAmount", loanCCAmount!=null?loanCCAmount:"")
				.setParameter("emiCCOutStanding",emiCCOutstanding!=null?emiCCOutstanding:"")
				.setParameter("tenorBalYear", existingFacilityEntity.getTenorBalYears()!=null?existingFacilityEntity.getTenorBalYears():"")
				.setParameter("tenorBalMonth", existingFacilityEntity.getTenorBalMonths()!=null?existingFacilityEntity.getTenorBalMonths():"")
				.setParameter("remark", existingFacilityEntity.getRemarks()!=null?existingFacilityEntity.getRemarks():"")
				.setParameter("userId", userEntity.getUserid())
				.setDate("createdDate", existingFacilityEntity.getCreatedDate())
				.setDate("createdSysDate", existingFacilityEntity.getCreatedSysDate());													
				updatestatus=query.executeUpdate();
			}
			if(updatestatus> 0)
				update =""+ updatestatus;
		}catch(Exception e){
			logger.error("FacilityRequestedDaoImpl | addExistingFacility() | ERROR:::"+e.getMessage());
			e.printStackTrace();
			return null;
		}	finally {
			if(session!=null)
				session.close();
		}	
		logger.info("FacilityRequestedDaoImpl | addExistingFacility() | END");
		return update;
	}
}
