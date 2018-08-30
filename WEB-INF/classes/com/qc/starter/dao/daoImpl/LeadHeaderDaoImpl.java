package com.qc.starter.dao.daoImpl;

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

import com.qc.starter.dao.LeadHeaderDao;
import com.qc.starter.dto.LeadHeaderDto;
import com.qc.starter.entity.UserEntity;

@Repository
public class LeadHeaderDaoImpl implements LeadHeaderDao{
	private static final Logger logger = Logger.getLogger(LeadHeaderDaoImpl.class.getName());

	@Autowired
	SessionFactory sessionFactory;
	@Autowired HttpSession httpSession;

	@Transactional
	public LeadHeaderDto getLeadHeader(String leadId) {
		logger.info("LeadHeaderDaoImpl | getLeadHeader() | :- START::: WITH Request leadId::"+leadId);
		List  list = null;
		LeadHeaderDto leadHeaderDto = new LeadHeaderDto();
		Session session=null;
		try{
			UserEntity userEntity = (UserEntity)httpSession.getAttribute("UserDetails");
			session = sessionFactory.openSession();
			String sql="SELECT QC.CASE_ID LEAD_ID,QC.CASE_CODE LEAD_CODE,(SELECT QCP.PRODNAME FROM QC_PROSPECT_MASTER.QM_PRODUCT  QCP WHERE " +
					" QCP.PRODUCTID =  QC.QUEUE_ID) QUEUE,QC.QUEUE_ID," +
					" (SELECT QCP.PRODNAME FROM QC_PROSPECT_MASTER.QM_PRODUCT  QCP WHERE QCP.PRODTYPEID = (SELECT PRODUCTTYPEID FROM " +
					" QC_PROSPECT_MASTER.QM_PRODUCTTYPE QPT WHERE QPT.COMPANY_ID = "+userEntity.getCompanyId()+" AND   QPT.PRODUCTTYPE ='Scheme') " +
					" AND QCP.PRODUCTID = QC.FACILITY_REQ_ID) FACILITY_REQUESTED, (SELECT QCP.PRODNAME FROM QC_PROSPECT_MASTER.QM_PRODUCT  QCP " +
					" WHERE QCP.PRODTYPEID = (SELECT PRODTYPEID FROM QC_PROSPECT_MASTER.QM_PRODUCTTYPE QPT WHERE QPT.COMPANY_ID = "
					+userEntity.getCompanyId()+" AND   QPT.PRODUCTTYPE ='PRODUCT') AND  QCP.PRODUCTID = QC.PROD_ID) PRODUCT, " +
					" TRIM(REPLACE(QPD.FNAME || ' ' || QPD.MNAME || ' ' || QPD.LNAME,'  ',' ')) CUSTOMER_NAME, QC.GENERATION_DT, " +
					" (SELECT SQ.SUB_QUEUE FROM QC_PROSPECT_MASTER.QM_SUB_QUEUE SQ WHERE SQ.SUB_QUEUE_ID = QC.SUB_QUEUE_ID AND SQ.COMPANY_ID = "
					+userEntity.getCompanyId()+") SUB_QUEUE, QC.LOAN_AMOUNT AMOUNT, (SELECT CM.CONTACT_NO FROM QC_PROSPECT.QT_CASE_MOBILE CM WHERE " +
					" CM.CASE_ID = QC.CASE_ID AND CM.PRIMARY_CONTACT = 'Y') AS MOBILE, (SELECT TRIM(REPLACE(QU.USERFNAME || ' ' || " +
					" QU.USERMNAME || ' ' || QU.USERMNAME,'  ',' ')) FROM QC_PROSPECT_MASTER.QM_USER QU WHERE QU.USERID = QC.ALLOCATED_TO AND " +
					" QU.COMPANY_ID = "+userEntity.getCompanyId()+") ALLOCATED_TO, (SELECT QS.SOURCE_NAME FROM QC_PROSPECT_MASTER.QM_SOURCE QS WHERE " +
					" QS.CASE_SOURCE_ID = QC.SOURCE_ID AND QS.COMPANY_ID = "+userEntity.getCompanyId()+" ) SOURCE, (SELECT QB.BANK_NAME " +
					" FROM QC_PROSPECT_MASTER.QM_BANK QB WHERE QB.BANK_ID = QC.BANK_ID AND QB.COMPANY_ID = "+userEntity.getCompanyId()+") BANK, " +
					" QC.BANK_ID BANK_ID , QC.FACILITY_REQ_ID FACILITY_REQ_ID , " +
					" (SELECT QC1.DISPLAYNAME  FROM QC_PROSPECT.QT_ADDRESS QA, QC_PROSPECT_MASTER.QM_CITYMASTER QC1 WHERE QA.PERSONAL_DTL_ID = " +
					" QPD.PERSONAL_DTL_ID AND QC1.CITYMASTERID = QA.CITY AND    QA.MAILINGADDRESS ='Y') CITY,  (SELECT QCA.CAMPAIGN_NAME " +
					" FROM QC_PROSPECT_MASTER.QM_CAMPAIGN QCA WHERE QCA.CAMPAIGN_ID = QC.CAMPAIGN_ID AND QCA.COMPANY_ID = "+userEntity.getCompanyId()+") " +
					" CAMPAIGN,QC.ROI,(SELECT QOC.EMPLOYMENTTYPENAME FROM QC_PROSPECT_MASTER.QM_EMPLOYMENTTYPE QOC WHERE QOC.EMPLOYMENTTYPEID = QPD.OCCUPATION_TYPE " +
					" AND  QOC.COMPANY_ID = "+userEntity.getCompanyId()+") OCCUPATION,"+
					"( SELECT QA.ACTION_NAME FROM QC_PROSPECT_MASTER.QM_CASE_ACTION QA WHERE QA.ACTION_ID = QC.ACTION_ID AND QA.COMPANY_ID = "+userEntity.getCompanyId()+" ) DISPOSITION,"+
					"( SELECT QA.ACTION_STAGE FROM QC_PROSPECT_MASTER.QM_CASE_ACTION QA WHERE QA.ACTION_ID = QC.ACTION_ID AND QA.COMPANY_ID = "+userEntity.getCompanyId()+" ) LEAD_STAGE ,"
					+ " QC.TAG_INFO_A, QC.TAG_INFO_B FROM QC_PROSPECT.QT_CASE QC, " +
					" QC_PROSPECT.QT_PERSONAL_DETAILS QPD WHERE QPD.CASE_ID = QC.CASE_ID AND QC.CASE_ID = " + leadId;
			SQLQuery query = session.createSQLQuery(sql);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			list = query.list();	
			if(list != null && list.size() > 0){				
				for(Object o : list){					
					Map leadMap = (Map) o;
					leadHeaderDto.setLeadId( leadMap.get("LEAD_ID")!=null? leadMap.get("LEAD_ID").toString():" ");
					leadHeaderDto.setLeadCode(leadMap.get("LEAD_CODE")!=null? leadMap.get("LEAD_CODE").toString():" ");
					leadHeaderDto.setQueue(leadMap.get("QUEUE")!=null? leadMap.get("QUEUE").toString():" ");
					leadHeaderDto.setQueueId(leadMap.get("QUEUE_ID")!=null? leadMap.get("QUEUE_ID")+"":" ");
					leadHeaderDto.setFacilityReq(leadMap.get("FACILITY_REQUESTED")!=null? leadMap.get("FACILITY_REQUESTED").toString():" ");
					leadHeaderDto.setProduct(leadMap.get("PRODUCT")!=null? leadMap.get("PRODUCT").toString():" ");
					leadHeaderDto.setCustomerName(leadMap.get("CUSTOMER_NAME")!=null? leadMap.get("CUSTOMER_NAME").toString():" ");
					leadHeaderDto.setGenerationDate(leadMap.get("GENERATION_DT")!=null? leadMap.get("GENERATION_DT").toString():" ");
					leadHeaderDto.setSubQueue(leadMap.get("SUB_QUEUE")!=null? leadMap.get("SUB_QUEUE").toString():" ");
					leadHeaderDto.setAmount(leadMap.get("AMOUNT")!=null? leadMap.get("AMOUNT").toString():" ");
					leadHeaderDto.setCustomerMobile(leadMap.get("MOBILE")!=null? leadMap.get("MOBILE").toString():"");
					leadHeaderDto.setAllocatedTo(leadMap.get("ALLOCATED_TO")!=null? leadMap.get("ALLOCATED_TO").toString():" ");
					leadHeaderDto.setSource(leadMap.get("SOURCE")!=null? leadMap.get("SOURCE").toString():" ");
					leadHeaderDto.setBankName(leadMap.get("BANK")!=null? leadMap.get("BANK").toString():" ");
					leadHeaderDto.setBankId(leadMap.get("BANK_ID")!=null? leadMap.get("BANK_ID").toString():" ");
					leadHeaderDto.setFacilityReqId(leadMap.get("FACILITY_REQ_ID")!=null? leadMap.get("FACILITY_REQ_ID").toString():" ");
					leadHeaderDto.setCustomerResCity(leadMap.get("CITY")!=null? leadMap.get("CITY").toString():" ");
					leadHeaderDto.setCampaign(leadMap.get("CAMPAIGN")!=null? leadMap.get("CAMPAIGN").toString():" ");
					leadHeaderDto.setRoi(leadMap.get("ROI")!=null? leadMap.get("ROI").toString():" ");
					leadHeaderDto.setOccuType(leadMap.get("OCCUPATION")!=null? leadMap.get("OCCUPATION").toString():" ");
					leadHeaderDto.setDisposition(leadMap.get("DISPOSITION")!=null? leadMap.get("DISPOSITION").toString():" ");
					leadHeaderDto.setLeadStage(leadMap.get("LEAD_STAGE")!=null? leadMap.get("LEAD_STAGE").toString():" ");					
					leadHeaderDto.setTagA(leadMap.get("TAG_INFO_A")!=null? leadMap.get("TAG_INFO_A").toString():" ");
					leadHeaderDto.setTagB(leadMap.get("TAG_INFO_B")!=null? leadMap.get("TAG_INFO_B").toString():" ");
					leadHeaderDto.setHiddenLeadId(leadMap.get("LEAD_ID")!=null? leadMap.get("LEAD_ID").toString():" ");									
				}
			}
		}catch(Exception e){	
			logger.error("LeadHeaderDaoImpl | getLeadHeader() | :- error");
			e.printStackTrace();
			return null;
		}finally {
			if(session!=null)
				session.close();
		}
		logger.info("LeadHeaderDaoImpl | getLeadHeader() | :- END");
		return leadHeaderDto;
	}

	@Transactional
	public String getpreviousOrNextLead(String leadId, Integer userid,String lead) {
		logger.info("LeadHeaderDaoImpl | getpreviousOrNextLead() | :- START  ::: WITH Request leadId::"+leadId+"userid::"+userid);
		String leadid="";
		Session session=null;
		try{
			session = sessionFactory.openSession();
			UserEntity userEntity = (UserEntity)httpSession.getAttribute("UserDetails");
			String sql = "SELECT * FROM (SELECT X.CASE_ID, LAG(X.CASE_ID, 1) over(ORDER BY X.RN)" +
					" PREV_CASE, LEAD(X.CASE_ID, 1) OVER(ORDER BY X.RN) NEXT_CASE FROM " +
					" (SELECT ROWNUM RN, X1.* FROM (SELECT CXU.CASE_ID, CXU.ALLOCATED_SYS_DATE DT, " +
					" (SELECT CA.WEIGHT FROM QC_PROSPECT_MASTER.QM_CASE_ACTION CA WHERE CA.COMPANY_ID = " +userEntity.getCompanyId()+
					"  AND CA.ACTIVE = 'A' AND CA.ACTION_ID = QC.ACTION_ID) WEIGTH,(SELECT PRODNAME" +
					" FROM QC_PROSPECT_MASTER.QM_PRODUCT  P WHERE P.PRODTYPEID = 2 AND P.ACTIVE = 'A' " +
					" AND PRODUCTID = QC.QUEUE_ID) PRODUCT, (SELECT SQ.SUB_QUEUE FROM " +
					" QC_PROSPECT_MASTER.QM_SUB_QUEUE SQ WHERE SQ.COMPANY_ID = "+userEntity.getCompanyId()+" AND SQ.ACTIVE = 'A' " +
					" AND SQ.SUB_QUEUE_ID = QC.SUB_QUEUE_ID) SUB_QUEUE, qc.generation_dt " +
					" FROM QC_PROSPECT.QT_CASE_X_USER CXU, QC_PROSPECT.QT_CASE QC WHERE " +
					" CXU.ALLOCATED_TO = "+userEntity.getUserid()+" AND CXU.CASE_ID = QC.CASE_ID AND " +
					" CXU.ALLOCATED_END_DATE IS NULL ORDER BY 3, 4, 5,6 desc) X1) X) " +
					" WHERE CASE_ID = "+leadId;
			SQLQuery query = session.createSQLQuery(sql);
			List list = query.list();
			Object[] objArr = (Object[])list.get(0);
			if(lead.equalsIgnoreCase("previous")){
				leadid=objArr[1]==null?"":objArr[1]+"";						
			}else{
				leadid=objArr[2]==null?"":objArr[2]+"";
			}
		}catch(Exception e){			
			logger.info("Error in catch block due to::::->"+e.getMessage());
			e.printStackTrace();
			return null;
		}finally {
			if(session!=null)
				session.close();
		}
		logger.info("LeadHeaderDaoImpl | getpreviousOrNextLead() | :- END");
		return leadid;
	}

}