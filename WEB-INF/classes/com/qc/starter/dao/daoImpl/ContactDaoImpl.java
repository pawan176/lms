package com.qc.starter.dao.daoImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.qc.starter.basic.CommonUtils;
import com.qc.starter.dao.ContactDao;
import com.qc.starter.dto.CaseActionHistoryDto;
import com.qc.starter.dto.CaseAllocationHistoryDto;
import com.qc.starter.dto.CaseEscalationHistoryDto;
import com.qc.starter.dto.ContactDto;
import com.qc.starter.dto.PersonListDto;
import com.qc.starter.entity.UserEntity;

@Repository
public class ContactDaoImpl implements ContactDao {
	private static final Logger logger = Logger.getLogger(ContactDaoImpl.class
			.getName());
	@Autowired
	SessionFactory sessionFactory;
	@Autowired
	HttpSession httpSession;

	@Transactional
	public String getAllWorklistLeads(String caseId){
		logger.info("ContactDaoImpl | getAllWorklistLeads() | :- START  ::: WITH Request caseId::" +caseId);
		Session session = null;
		StringBuffer leads = new StringBuffer(""); 
		UserEntity userEntity = null;
		SQLQuery query=null;
		try{
			userEntity = (UserEntity) httpSession.getAttribute("UserDetails");
			session = sessionFactory.openSession();
			String leadPosition = "select * from  (SELECT a.*, rownum r FROM (SELECT  CXU.CASE_ID, QC.CASE_CODE, QC.QUEUE_ID, (SELECT PRODNAME FROM " +
					" QC_PROSPECT_MASTER.QM_PRODUCT  P WHERE P.PRODTYPEID in (SELECT PT.PRODUCTTYPEID FROM QC_PROSPECT_MASTER.QM_PRODUCTTYPE PT WHERE " +
					" PT.COMPANY_ID = "+userEntity.getCompanyId()+" AND PT.ACTIVE = 'A' AND PT.PRODUCTTYPE = 'SCHEME') AND P.ACTIVE = 'A' " +
					" AND PRODUCTID = QC.QUEUE_ID) PRODUCT, (SELECT SQ.SUB_QUEUE FROM QC_PROSPECT_MASTER.QM_SUB_QUEUE SQ WHERE SQ.COMPANY_ID " +
					" = "+userEntity.getCompanyId()+" AND SQ.ACTIVE = 'A' AND SQ.SUB_QUEUE_ID = QC.SUB_QUEUE_ID) SUB_QUEUE, " +
					" (SELECT TRIM(REPLACE(PD.FNAME || ' ' || PD.MNAME || ' ' || PD.LNAME, '  ', ' ')) FROM QC_PROSPECT.QT_PERSONAL_DETAILS " +
					" PD WHERE PD.CASE_ID = QC.CASE_ID) CUSTOMER_NAME, QC.AMOUNT, (SELECT ACTION_NAME FROM QC_PROSPECT_MASTER.QM_CASE_ACTION CA " +
					" WHERE CA.COMPANY_ID = "+userEntity.getCompanyId()+" AND CA.ACTIVE = 'A' AND CA.ACTION_ID = QC.ACTION_ID) ACTION, " +
					" (SELECT CA.WEIGHT FROM QC_PROSPECT_MASTER.QM_CASE_ACTION CA WHERE CA.COMPANY_ID = "+userEntity.getCompanyId()+" AND CA.ACTIVE = 'A' " +
					" AND CA.ACTION_ID = QC.ACTION_ID) WEIGTH, (SELECT BANK_NAME FROM QC_PROSPECT_MASTER.QM_BANK B WHERE B.ACTIVE = 'A' AND B.BANK_ID = " +
					" QC.BANK_ID) BANK, QC.ESCALATE, QC.REFER, QC.CO_ALLOCATE, QC.GENERATION_DT FROM QC_PROSPECT.QT_CASE_X_USER CXU, " +
					" QC_PROSPECT.QT_CASE QC WHERE CXU.CASE_ID = QC.CASE_ID AND CXU.ALLOCATED_TO = "+userEntity.getUserid()+" AND " +
					" QC.COMPANY_ID = "+userEntity.getCompanyId()+" AND CXU.ALLOCATED_END_DATE IS NULL ORDER BY WEIGTH, PRODUCT, SUB_QUEUE, " +
					" QC.GENERATION_DT desc) a) where CASE_ID = "+caseId;
			int leadposition = 0;
			query=session.createSQLQuery(leadPosition);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List result = query.list();
			for(Object o: result){	
				Map map = (Map) o;
				if(map.get("R") !=null && !(map.get("R").equals(""))){
					leadposition = Integer.parseInt(map.get("R").toString());				
				}									 
			}
          query=null;
		  int leadStorageStart = leadposition -25;
		  int leadStorageEnd = leadposition + 25;
		  String sqlStr = "select * from  (SELECT a.*, rownum r FROM (SELECT  CXU.CASE_ID, QC.CASE_CODE, QC.QUEUE_ID, (SELECT PRODNAME FROM " +
					" QC_PROSPECT_MASTER.QM_PRODUCT  P WHERE P.PRODTYPEID in (SELECT PT.PRODUCTTYPEID FROM QC_PROSPECT_MASTER.QM_PRODUCTTYPE PT WHERE " +
					" PT.COMPANY_ID = "+userEntity.getCompanyId()+" AND PT.ACTIVE = 'A' AND PT.PRODUCTTYPE = 'SCHEME') AND P.ACTIVE = 'A' " +
					" AND PRODUCTID = QC.QUEUE_ID) PRODUCT, (SELECT SQ.SUB_QUEUE FROM QC_PROSPECT_MASTER.QM_SUB_QUEUE SQ WHERE SQ.COMPANY_ID " +
					" = "+userEntity.getCompanyId()+" AND SQ.ACTIVE = 'A' AND SQ.SUB_QUEUE_ID = QC.SUB_QUEUE_ID) SUB_QUEUE, " +
					" (SELECT TRIM(REPLACE(PD.FNAME || ' ' || PD.MNAME || ' ' || PD.LNAME, '  ', ' ')) FROM QC_PROSPECT.QT_PERSONAL_DETAILS " +
					" PD WHERE PD.CASE_ID = QC.CASE_ID) CUSTOMER_NAME, QC.AMOUNT, (SELECT ACTION_NAME FROM QC_PROSPECT_MASTER.QM_CASE_ACTION CA " +
					" WHERE CA.COMPANY_ID = "+userEntity.getCompanyId()+" AND CA.ACTIVE = 'A' AND CA.ACTION_ID = QC.ACTION_ID) ACTION, " +
					" (SELECT CA.WEIGHT FROM QC_PROSPECT_MASTER.QM_CASE_ACTION CA WHERE CA.COMPANY_ID = "+userEntity.getCompanyId()+" AND CA.ACTIVE = 'A' " +
					" AND CA.ACTION_ID = QC.ACTION_ID) WEIGTH, (SELECT BANK_NAME FROM QC_PROSPECT_MASTER.QM_BANK B WHERE B.ACTIVE = 'A' AND B.BANK_ID = " +
					" QC.BANK_ID) BANK, QC.ESCALATE, QC.REFER, QC.CO_ALLOCATE, QC.GENERATION_DT FROM QC_PROSPECT.QT_CASE_X_USER CXU, " +
					" QC_PROSPECT.QT_CASE QC WHERE CXU.CASE_ID = QC.CASE_ID AND CXU.ALLOCATED_TO = "+userEntity.getUserid()+" AND " +
					" QC.COMPANY_ID = "+userEntity.getCompanyId()+" AND CXU.ALLOCATED_END_DATE IS NULL ORDER BY WEIGTH, PRODUCT, SUB_QUEUE, " +
					" QC.GENERATION_DT desc) a) where r between "+leadStorageStart+" and "+leadStorageEnd;
		  query=session.createSQLQuery(sqlStr);
		  query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		  List result1 = query.list();
			for(Object o: result1){	
				Map map = (Map) o;
				if(map.get("CASE_ID") !=null && !(map.get("CASE_ID").equals(""))){
					leads.append(map.get("CASE_ID")+",");				
				}									 
			}
		}catch(Exception e){
			logger.error("ContactDaoImpl | getAllWorklistLeads() | :- Error:::::" +e.getMessage());
		}finally {
			 if(session!=null)
				session.close();
		}
		logger.info("ContactDaoImpl | getAllWorklistLeads() | :- END::");
		return leads.toString();
	}
	
	@Transactional
	public List<CaseActionHistoryDto> caseActionHistory(String caseId,String companyId,String requestType) {
		logger.info("ContactDaoImpl | caseActionHistory() | :- START  ::: WITH Request caseId::" +caseId);
		String status = "";
		List contactlist=null;
		List<CaseActionHistoryDto> caseActionHistoryList = new ArrayList<CaseActionHistoryDto>();
		Session session=null;
		try{
	//	UserEntity userEntity = (UserEntity) httpSession.getAttribute("UserDetails");
		session = sessionFactory.openSession();
		

		/*String sql = "SELECT CASE_ID,CASEXUSER_ID,ACTION_ID,FOLLOW_ACTION_ID,CASE_STAGE_ID,SUB_QUEUE_ID,PURPOSE_ID, REJECT_REGION_ID, (SELECT QCA.ACTION_ID FROM QC_PROSPECT_MASTER.QM_CASE_ACTION QCA WHERE QCA.Action_Type" +*/

		String sql = "SELECT CASE_ID, CASEXUSER_ID, ACTION_ID, FOLLOW_ACTION_ID, CASE_STAGE_ID, SUB_QUEUE_ID, PURPOSE_ID, REJECT_REGION_ID, (SELECT QCA.ACTION_ID "
				+ "FROM QC_PROSPECT_MASTER.QM_CASE_ACTION QCA WHERE QCA.Action_Type = (SELECT ACTION_TYPE_ID FROM QC_PROSPECT_MASTER.QM_CASE_ACTION_TYPE "
				+ " WHERE ACTION_TYPE_NAME = 'DISPOSITION' AND COMPANY_ID = "+companyId+" AND ACTIVE = 'A') AND QCA.ACTION_ID = CUA.ACTION_ID) AS DISPOSITIONID, "
				+ " (SELECT QCA.ACTION_NAME FROM QC_PROSPECT_MASTER.QM_CASE_ACTION QCA WHERE QCA.Action_Type = (SELECT ACTION_TYPE_ID FROM "
				+ " QC_PROSPECT_MASTER.QM_CASE_ACTION_TYPE WHERE ACTION_TYPE_NAME = 'DISPOSITION' AND COMPANY_ID = "+companyId+" AND ACTIVE = 'A') "
				+ " AND QCA.ACTION_ID = CUA.ACTION_ID) AS ACTION, (SELECT TRIM(REPLACE(QU.USERFNAME || ' ' || QU.USERMNAME || ' ' || QU.USERLNAME, ' ', ' ')) "
				+ " FROM QC_PROSPECT_MASTER.QM_USER QU WHERE QU.USERID = CUA.CASEXUSER_ID) AS ACTION_BY, TO_CHAR(CUA.ACTION_DT_TIME, 'DD-MON-RRRR HH24:MI') AS"
				+ "  ACTION_DATE, CASEUSERACTION_ID, REMARKS, (SELECT QCNA.NEXTACTION_NAME FROM QC_PROSPECT_MASTER.QM_CASE_NEXT_ACTION QCNA WHERE "
				+ " QCNA.NEXTACTION_ID = CUA.FOLLOW_ACTION_ID) AS FOLLOWUP_ACTION, TO_CHAR(CUA.FOLLOW_DT_TIME, 'DD-MON-RRRR HH24:MI') AS FOLLOWUP_DATE,"
				+ " (SELECT QSB.SUB_QUEUE FROM QC_PROSPECT_MASTER.QM_SUB_QUEUE QSB WHERE QSB.SUB_QUEUE_ID = CUA.SUB_QUEUE_ID) AS POTENTIAL, "
				+ "(SELECT QSG.STAGE_NAME FROM QC_PROSPECT_MASTER.QM_CASE_STAGE QSG WHERE QSG.STAGE_ID = CUA.CASE_STAGE_ID) AS LEAD_STAGE,"
				+ " (SELECT RR.REJECT_REGION_NAME FROM QC_PROSPECT_MASTER.QM_REJECT_REASON RR WHERE RR.REJECT_REGION_ID = CUA.REJECT_REGION_ID) AS"
				+ "  REJECTREASON, (SELECT PP.PURPOSE_NAME FROM QC_PROSPECT_MASTER.QM_PURPOSE PP WHERE PP.PURPOSE_ID = CUA.PURPOSE_ID) AS PURPOSE "
				+ " FROM QC_PROSPECT.QT_CASE_USER_ACTION CUA WHERE CUA.CASE_ID = "+ caseId + " and action_id <> (SELECT ACTION_ID FROM QC_PROSPECT_MASTER.QM_CASE_ACTION "
				+ " WHERE ACTION_NAME = 'CO-ALLOCATE' AND ACTIVE = 'A' AND COMPANY_ID = "+companyId+") ORDER BY CREATED_SYS_DATE DESC";
		
		
		/*String sql = "SELECT CASE_ID,CASEXUSER_ID,ACTION_ID,FOLLOW_ACTION_ID,CASE_STAGE_ID,SUB_QUEUE_ID, (SELECT QCA.ACTION_ID FROM QC_PROSPECT_MASTER.QM_CASE_ACTION QCA WHERE QCA.Action_Type" +
>>>>>>> WEBCHANGES
				"  = (SELECT ACTION_TYPE_ID FROM QC_PROSPECT_MASTER.QM_CASE_ACTION_TYPE WHERE ACTION_TYPE_NAME = 'DISPOSITION' " +
				" AND COMPANY_ID = "+companyId+" AND ACTIVE = 'A') AND QCA.ACTION_ID = CUA.ACTION_ID) AS DISPOSITIONID, " +
				" (SELECT QCA.ACTION_NAME FROM QC_PROSPECT_MASTER.QM_CASE_ACTION QCA WHERE QCA.Action_Type = (SELECT ACTION_TYPE_ID " +
				" FROM QC_PROSPECT_MASTER.QM_CASE_ACTION_TYPE WHERE ACTION_TYPE_NAME = 'DISPOSITION' AND COMPANY_ID = "+companyId+" AND " +
				" ACTIVE = 'A') AND QCA.ACTION_ID = CUA.ACTION_ID) AS ACTION, (SELECT TRIM(REPLACE(QU.USERFNAME || ' ' || " +
				" QU.USERMNAME || ' ' || QU.USERLNAME, '  ', ' ')) FROM QC_PROSPECT_MASTER.QM_USER QU WHERE QU.USERID = CUA.CASEXUSER_ID) " +
				" AS ACTION_BY, TO_CHAR(CUA.ACTION_DT_TIME, 'DD-MON-RRRR HH24:MI') AS ACTION_DATE, CASEUSERACTION_ID, REMARKS, " +
				" (SELECT QCNA.NEXTACTION_NAME FROM QC_PROSPECT_MASTER.QM_CASE_NEXT_ACTION QCNA WHERE QCNA.NEXTACTION_ID = CUA.FOLLOW_ACTION_ID) " +
				" AS FOLLOWUP_ACTION, TO_CHAR(CUA.FOLLOW_DT_TIME, 'DD-MON-RRRR HH24:MI') AS FOLLOWUP_DATE, (SELECT QSB.SUB_QUEUE " +
				" FROM QC_PROSPECT_MASTER.QM_SUB_QUEUE QSB WHERE QSB.SUB_QUEUE_ID = CUA.SUB_QUEUE_ID) AS POTENTIAL, " +
				" (SELECT QSG.STAGE_NAME FROM QC_PROSPECT_MASTER.QM_CASE_STAGE QSG WHERE QSG.STAGE_ID = CUA.CASE_STAGE_ID) " +
				" AS LEAD_STAGE     FROM QC_PROSPECT.QT_CASE_USER_ACTION CUA WHERE CUA.CASE_ID = "+ caseId + " and action_id <> " +
				" (SELECT ACTION_ID FROM QC_PROSPECT_MASTER.QM_CASE_ACTION WHERE ACTION_NAME = 'CO-ALLOCATE' AND ACTIVE = 'A' " +
				" AND COMPANY_ID = "+companyId+") ORDER BY CREATED_SYS_DATE DESC";*/
		
		
		
		
		
		/*String sql = "SELECT  CASE_ID, (SELECT  QCA.ACTION_ID FROM QC_PROSPECT_MASTER.QM_CASE_ACTION QCA WHERE QCA.Action_Type =" +
				" (SELECT ACTION_TYPE_ID FROM QC_PROSPECT_MASTER.QM_CASE_ACTION_TYPE WHERE ACTION_TYPE_NAME ='DISPOSITION' " +
				"  AND COMPANY_ID = '1000000001' AND ACTIVE='A') AND QCA.ACTION_ID =CUA.ACTION_ID ) DISPOSITIONID, " +
				" (SELECT  QCA.ACTION_NAME FROM QC_PROSPECT_MASTER.QM_CASE_ACTION QCA WHERE   QCA.Action_Type = " +
				" (SELECT ACTION_TYPE_ID FROM QC_PROSPECT_MASTER.QM_CASE_ACTION_TYPE WHERE ACTION_TYPE_NAME ='DISPOSITION' " +
				" AND COMPANY_ID = "+companyId+" AND ACTIVE='A')  AND     QCA.ACTION_ID =CUA.ACTION_ID ) ACTION," +
				" (SELECT TRIM(REPLACE(QU.USERFNAME||' '||QU.USERMNAME||' '||QU.USERLNAME,'  ',' '))  FROM QC_PROSPECT_MASTER.QM_USER QU " +
				" WHERE QU.USERID= CUA.CASEXUSER_ID) ACTION_BY, TO_CHAR(CUA.CREATED_SYS_DATE,'DD-MON-RRRR HH24:MI') ACTION_DATE,CASEUSERACTION_ID,REMARKS FROM  " +
				" QC_PROSPECT.QT_CASE_USER_ACTION CUA WHERE CUA.CASE_ID = "+ caseId + "  and " +
				" action_id <> (SELECT ACTION_ID FROM QC_PROSPECT_MASTER.QM_CASE_ACTION WHERE " +
				" ACTION_NAME = 'CO-ALLOCATE' AND ACTIVE = 'A' AND COMPANY_ID="+companyId+")" +
				" ORDER BY CREATED_SYS_DATE DESC";*/
		
		
		Query query = session.createSQLQuery(sql);
		//System.out.println(query);
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		contactlist = query.list();
		if(contactlist != null && contactlist.size() > 0){				
			for(Object o : contactlist){					
			Map resultSet = (Map) o;
			
			//Changes on 23-jan-2018 as requested by mobile team
			CaseActionHistoryDto caseActionHistoryDto = new CaseActionHistoryDto();
			
			
			
			caseActionHistoryDto.setCaseId(resultSet.get("CASE_ID")!=null ? resultSet.get("CASE_ID").toString() : "" );			
			caseActionHistoryDto.setActionBy(resultSet.get("ACTION_BY")!=null ? resultSet.get("ACTION_BY").toString() : "" );
			caseActionHistoryDto.setActionDate(resultSet.get("ACTION_DATE")!=null ? resultSet.get("ACTION_DATE").toString() : "" );
			caseActionHistoryDto.setRemarks(resultSet.get("REMARKS")!=null ? resultSet.get("REMARKS").toString() : "" );
			caseActionHistoryDto.setDispositionId(resultSet.get("DISPOSITIONID")!=null ? resultSet.get("DISPOSITIONID")+"" : "" );			
			caseActionHistoryDto.setFollowupActionDate(resultSet.get("FOLLOWUP_DATE")!=null ? resultSet.get("FOLLOWUP_DATE")+"" : "" );
			caseActionHistoryDto.setActionId(resultSet.get("CASEUSERACTION_ID")!=null ? resultSet.get("CASEUSERACTION_ID")+"" : "" );
			caseActionHistoryDto.setPurpose(resultSet.get("PURPOSE_ID")!=null ? resultSet.get("PURPOSE_ID")+"" : "" );
			caseActionHistoryDto.setRejectReason(resultSet.get("REJECT_REGION_ID")!=null ? resultSet.get("REJECT_REGION_ID")+"" : "" );
			
			if( CommonUtils.toString(requestType).equalsIgnoreCase("MOBILE") ){
				caseActionHistoryDto.setAction(resultSet.get("ACTION_ID")!=null ? resultSet.get("ACTION_ID").toString() : "" );
				caseActionHistoryDto.setFollowupAction(resultSet.get("FOLLOW_ACTION_ID")!=null ? resultSet.get("FOLLOW_ACTION_ID")+"" : "" );
				caseActionHistoryDto.setPotential(resultSet.get("SUB_QUEUE_ID")!=null ? resultSet.get("SUB_QUEUE_ID")+"" : "" );
				caseActionHistoryDto.setLeadStage(resultSet.get("CASE_STAGE_ID")!=null ? resultSet.get("CASE_STAGE_ID")+"" : "" );
				caseActionHistoryDto.setRejectReason(resultSet.get("REJECT_REGION_ID")!=null ? resultSet.get("REJECT_REGION_ID")+"" : "" );
				caseActionHistoryDto.setPurpose(resultSet.get("PURPOSE_ID")!=null ? resultSet.get("PURPOSE_ID")+"" : "" );
			}else{
				caseActionHistoryDto.setAction(resultSet.get("ACTION")!=null ? resultSet.get("ACTION").toString() : "" );				
				caseActionHistoryDto.setFollowupAction(resultSet.get("FOLLOWUP_ACTION")!=null ? resultSet.get("FOLLOWUP_ACTION")+"" : "" );
				caseActionHistoryDto.setPotential(resultSet.get("POTENTIAL")!=null ? resultSet.get("POTENTIAL")+"" : "" );
				caseActionHistoryDto.setLeadStage(resultSet.get("LEAD_STAGE")!=null ? resultSet.get("LEAD_STAGE")+"" : "" );
				caseActionHistoryDto.setRejectReason(resultSet.get("REJECTREASON")!=null ? resultSet.get("REJECTREASON")+"" : "" );
				caseActionHistoryDto.setPurpose(resultSet.get("PURPOSE")!=null ? resultSet.get("PURPOSE")+"" : "" );
			}			
			
			caseActionHistoryList.add(caseActionHistoryDto);
		}
		}
		}catch(Exception e){
			logger.error("ContactDaoImpl | caseActionHistory() | :- Error::::"+e.getMessage());
			e.printStackTrace();
		}finally {
			 if(session!=null)
				session.close();
		}
		logger.info("ContactDaoImpl | caseActionHistory() | :- END  ::: WITH Response List::"
				+ caseActionHistoryList);
		return caseActionHistoryList;
	}

	@Transactional
	public List<CaseEscalationHistoryDto> caseEscalationHistory(String caseId,String companyId) {
		logger.info("ContactDaoImpl | caseEscalationHistory() | :- START  ::: WITH Request caseId::"+ caseId);
		String status = "";
		List<CaseEscalationHistoryDto> caseActionHistoryList = new ArrayList<CaseEscalationHistoryDto>();
		Session session =null;
		try{
			session = sessionFactory.openSession();
			//UserEntity userEntity = (UserEntity) httpSession.getAttribute("UserDetails");
			String sql =  "SELECT   QEC.CASE_REF_ESC_ID,     (SELECT  QCA.ACTION_NAME   FROM    QC_PROSPECT_MASTER.QM_CASE_ACTION QCA   WHERE  " +
					" QCA.Action_Type =  (SELECT ACTION_TYPE_ID FROM QC_PROSPECT_MASTER.QM_CASE_ACTION_TYPE WHERE ACTION_TYPE_NAME ='MOVEMENT' AND " +
					"COMPANY_ID = "+companyId+" AND ACTIVE='A')    AND     QCA.COMPANY_ID = "+companyId+"  AND " +
					"    QCA.ACTION_ID =QEC.ACTION_ID) ACTION_NAME,   " +
					" (SELECT TRIM(REPLACE(QU.USERFNAME||' '||QU.USERMNAME||' '||QU.USERLNAME,'  ',' '))  FROM QC_PROSPECT_MASTER.QM_USER QU  " +
					"WHERE QU.USERID= QEC.CREATED_BY  AND QU.COMPANY_ID= "+companyId+") INITIATED_BY, " +
					"(SELECT TRIM(REPLACE(QU.USERFNAME||' '||QU.USERMNAME||' '||QU.USERLNAME,'  ',' '))  " +
					"FROM QC_PROSPECT_MASTER.QM_USER QU  WHERE QU.USERID = QEC.ALLOCATED_TO AND QU.COMPANY_ID= "+companyId+") " +
					" INITIATED_TO,  (SELECT TRIM(REPLACE(QU.USERFNAME   ||' '   ||QU.USERMNAME   ||' '    ||QU.USERLNAME,'  ',' ')) " +
					" FROM QC_PROSPECT_MASTER.QM_USER QU WHERE QU.USERID  = decode(QEC.RESOLVED_DATE,NULL,NULL,QEC.ALLOCATED_TO) AND " +
					"QU.COMPANY_ID= "+companyId+") " +
					" RESOLVED_BY,  TO_CHAR(QEC.CREATED_SYS_DATE,'DD-MON-RRRR HH24:MI') INITIATED_DATE_TIME,   QEC.INITIAL_REMARKS,  " +
					"QEC.RESOLVED_REMARKS, TO_CHAR(RESOLVED_DATE,'DD-MON-RRRR HH24:MI') RESOLVE_DT_TIME FROM  " +
					" QC_PROSPECT.QT_CASE_X_REFF_ESC QEC   WHERE QEC.CASE_ID = "+caseId+" ORDER BY QEC.CREATED_SYS_DATE ";
			Query query = session.createSQLQuery(sql);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List contact = query.list();		
			if(contact != null && contact.size() > 0){				
				for(Object o : contact){					
				Map resultSet = (Map) o;
				CaseEscalationHistoryDto caseEscalationHistoryDto = new CaseEscalationHistoryDto();
				caseEscalationHistoryDto.setActionName(resultSet.get("ACTION_NAME")!=null ? resultSet.get("ACTION_NAME").toString() : "" );
				caseEscalationHistoryDto.setInitiatedBy(resultSet.get("INITIATED_BY")!=null ? resultSet.get("INITIATED_BY").toString() : "" );
				caseEscalationHistoryDto.setInitiatedTo(resultSet.get("INITIATED_TO")!=null ? resultSet.get("INITIATED_TO").toString() : "" );
				caseEscalationHistoryDto.setInitiatedDateTime(resultSet.get("INITIATED_DATE_TIME")!=null ? resultSet.get("INITIATED_DATE_TIME").toString() : "" );
				caseEscalationHistoryDto.setInitialRemarks(resultSet.get("INITIAL_REMARKS")!=null ? resultSet.get("INITIAL_REMARKS").toString() : "" );
				caseEscalationHistoryDto.setResolvedBy(resultSet.get("RESOLVED_BY")!=null ? resultSet.get("RESOLVED_BY").toString() : "" );
				caseEscalationHistoryDto.setResolveDtTime(resultSet.get("RESOLVE_DT_TIME")!=null ? resultSet.get("RESOLVE_DT_TIME").toString() : "" );				
				caseEscalationHistoryDto.setResolvedRemarks(resultSet.get("RESOLVED_REMARKS")!=null ? resultSet.get("RESOLVED_REMARKS").toString() : "" );								
				caseActionHistoryList.add(caseEscalationHistoryDto);
				}
			}
		}
		catch(Exception e){
			logger.error("ContactDaoImpl | caseEscalationHistory() | :- eror:::"+e.getMessage());
			e.printStackTrace();
		}finally {
			 if(session!=null)
				session.close();
		}
		logger.info("ContactDaoImpl | caseEscalationHistory() | :- END  ::: WITH Response List::");
		return caseActionHistoryList;		
	}
	
	@Transactional
	public List<PersonListDto> getSearchPerson(){
		logger.info("ContactDaoImpl | getAllPerson() | :- START  ::: WITH Request caseId::");
		List<PersonListDto> personList = new ArrayList<PersonListDto>();
		Session session = null;
		try{
			UserEntity userEntity = (UserEntity) httpSession.getAttribute("UserDetails");
			session = sessionFactory.openSession();
			String sql = "SELECT USERID, TRIM(REPLACE(QU.USERFNAME||' '||QU.USERMNAME||' '||QU.USERLNAME,'  ',' ')) USERNAME , SUPERVISORID, " +
					" level FROM QC_PROSPECT_MASTER.QM_USER QU WHERE ACTIVE ='A' CONNECT BY PRIOR USERID = SUPERVISORID START " +
					" WITH USERID ='"+ userEntity.getUserid() +"' ORDER BY LEVEL";
			Query query = session.createSQLQuery(sql);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List result = query.list();
			for (Object o : result) {
				Map map = (Map) o;
				PersonListDto person = new PersonListDto();
				person.setUserId(map.get("USERID").toString());
				person.setUserName(map.get("USERNAME").toString());
				person.setDesignationName(map.get("SUPERVISORID")!=null?map.get("SUPERVISORID").toString():"");
				personList.add(person);
			}			
		}catch(Exception e){
			logger.error("ContactDaoImpl | getLeadEscalateDetail() | :- error::::"+e.getMessage());
			e.printStackTrace();
		}finally {
			 if(session!=null)
				session.close();
		}
		logger.info("ContactDaoImpl | getLeadEscalateDetail() | :- END  ::: WITH Response List<PersonListDto>::"
				+ personList);
		return personList;
	}

	@Transactional
	public String getLastupdateTime(String caseId) {
		logger.info("ContactDaoImpl | getLastupdateTime() | :- START  ::: WITH Request caseId::"+ caseId);
		String time = "";
		Session session=null;
		try{
			//UserEntity userEntity = (UserEntity) httpSession.getAttribute("UserDetails");
			session = sessionFactory.openSession();
			String sql = "SELECT " +
					" TO_CHAR(QC.UPDATED_SYS_DATE,'DD-MM-RRRR') UPDATED_SYS_DATE FROM  QC_PROSPECT.QT_CASE QC WHERE  QC.CASE_ID= "+caseId;
			Query query = session.createSQLQuery(sql);
			List list = query.list();
			if(list.size()>0){
				time=list.get(0) != null ? list.get(0).toString() : "";
			}
			logger.info("ContactDaoImpl | getLastupdateTime() | :- END  ::: WITH Response ::"+ time);
			return time;
		}catch(Exception e){
			logger.info("ContactDaoImpl | getLastupdateTime() | :- Error:::"+e.getMessage());
			e.printStackTrace();
		}finally {
			 if(session!=null)
				session.close();
		}
		return null;		
	}

	@Transactional
	public boolean getLeadStatus(String caseId, String userId) {
		logger.info("ContactDaoImpl | getLeadStatus() | :- START  ::: WITH Request caseId::"
				+ caseId + ":::userId ::" + userId);
		String time = "";
		boolean flag = false;
		Session session=null;
		try{
					session = sessionFactory.openSession();
					String sql = "SELECT COUNT(1) FROM QC_PROSPECT.QT_CASE_X_REFF_ESC QCE WHERE  QCE.ACTION_ID IN (22,23) AND QCE.CASE_ID = "
							+ caseId + "  AND QCE.ALLOCATED_TO =" + userId;
					Query query = session.createSQLQuery(sql);
					List list = query.list();
					String result = (list.get(0) != null ? list.get(0).toString() : "");
					logger.info("ContactDaoImpl | getLeadStatus() | :- END");
					return (result.trim().equals("0") ? false : true);
		}catch(Exception e){
			logger.error("ContactDaoImpl | getLeadStatus() | :- error:::"+e.getMessage());
			e.printStackTrace();
		}finally {
			 if(session!=null)
				session.close();
		}
		return flag;
	}

	@Transactional
	public List getLeadEscalateDetail(String caseId,String userId) {
		logger.info("ContactDaoImpl | getLeadEscalateDetail() | :- START  ::: WITH Request caseId::"+ caseId);
		List<String> list = new ArrayList<String>();
		Session session=null;
		try{
			//UserEntity userEntity = (UserEntity) httpSession.getAttribute("UserDetails");
			session = sessionFactory.openSession();			
			String sql = "SELECT QEC.CASE_REF_ESC_ID CASE_REF_ESC_ID,  (SELECT ACTION_NAME  FROM QC_PROSPECT_MASTER.QM_CASE_ACTION  WHERE " +
					"ACTION_ID = QEC.ACTION_ID  ) ACTION_NAME,  (SELECT TRIM(REPLACE(QU.USERFNAME    || ' '    || QU.USERMNAME   " +
					" || ' '    || QU.USERLNAME,'  ',' '))  FROM QC_PROSPECT_MASTER.QM_USER QU  WHERE QU.USERID = QEC.CREATED_BY  ) " +
					" INITIATED_BY,  CREATED_SYS_DATE INITIATED_DATE_TIME,  INITIAL_REMARKS FROM QC_PROSPECT.QT_CASE_X_REFF_ESC QEC " +
					" WHERE QEC.CASE_ID      = " +caseId+" AND QEC.ALLOCATED_TO = "+userId+" AND QEC.RESOLVED_DATE IS NULL ";
			SQLQuery query = session.createSQLQuery(sql);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List result = query.list();
			for (Object o : result) {
				Map resultSet = (Map) o;									
				list.add(resultSet.get("CASE_REF_ESC_ID")!=null ? resultSet.get("CASE_REF_ESC_ID").toString() : "" );
				list.add(resultSet.get("ACTION_NAME")!=null ? resultSet.get("ACTION_NAME").toString() : "" );
				list.add(resultSet.get("INITIATED_BY")!=null ? resultSet.get("INITIATED_BY").toString() : "" );
				list.add(resultSet.get("INITIATED_DATE_TIME")!=null ? resultSet.get("INITIATED_DATE_TIME").toString() : "" );
				list.add(resultSet.get("INITIAL_REMARKS")!=null ? resultSet.get("INITIAL_REMARKS").toString() : "" );
			}
			logger.info("ContactDaoImpl | getLeadEscalateDetail() | :- END  ::: WITH Response List::"
					+ list);
			return list;
		}catch(Exception e){
			logger.info("ContactDaoImpl | getLeadEscalateDetail() | :- Error:::"+ e.getMessage());
			e.printStackTrace();
		}finally {
			 if(session!=null)
				session.close();
		}
		return list;
	}
	
	
	
	


	@Transactional
	public List<PersonListDto> getAllPerson(String caseId, String userId,String personType) {
		logger.info("ContactDaoImpl | getAllPerson() | :- START  ::: WITH Request caseId::"
				+ caseId
				+ ":::userId ::"
				+ userId
				+ " :::personType :::"
				+ personType);
		Session session = sessionFactory.openSession();
		UserEntity userEntity = (UserEntity) httpSession.getAttribute("UserDetails");
		List<PersonListDto> personList = new ArrayList<PersonListDto>();
		String sql = "";
		if (personType.trim().equals("ESCALATECONTACT")){
			sql = "SELECT  USERID,  DESIG||' : '|| NAME USERNAME, DESIG DESIGNATION_NAME FROM (SELECT USERID, TRIM(REPLACE(QU.USERFNAME||' " +
					" '||QU.USERMNAME||' '||QU.USERLNAME,'  ',' ')) NAME ,  SUPERVISORID,       (SELECT UPPER(DESIGNATION_NAME)  " +
					" FROM QC_PROSPECT_MASTER.QM_DESIGNATION B  WHERE B.DESIGNATION_ID =QU.DESIGNATION  AND   B.COMPANY_ID = "+userEntity.getCompanyId()+") DESIG, " +
					" level RL FROM QC_PROSPECT_MASTER.QM_USER QU WHERE ACTIVE ='A'  AND QU.COMPANY_ID = "+userEntity.getCompanyId()+" AND USERID NOT IN (SELECT ALLOCATED_TO  FROM " +
					" QC_PROSPECT.QT_CASE_X_REFF_ESC CRE, QC_PROSPECT_MASTER.QM_CASE_ACTION CA1  WHERE CA1.ACTION_ID = CRE.ACTION_ID  AND   " +
					" CA1.ACTION_NAME = 'ESCALATE'  AND   CRE.CASE_ID = "+caseId+" AND CRE.RESOLVED_DATE IS NULL)  CONNECT BY PRIOR  " +
					" SUPERVISORID=USERID START WITH USERID ="+userEntity.getUserid()+" ORDER BY LEVEL) WHERE RL=2";
		}
		else if (personType.trim().equals("REFERCONTACT")){			
			sql = "SELECT USERID,  DESIG||' : '|| NAME USERNAME, DESIG DESIGNATION_NAME FROM (SELECT USERID, TRIM(REPLACE(QU.USERFNAME||'" +
					" '||QU.USERMNAME||' '||QU.USERLNAME,'  ',' ')) NAME,  SUPERVISORID,  (SELECT UPPER(DESIGNATION_NAME) FROM " +
					"QC_PROSPECT_MASTER.QM_DESIGNATION B WHERE B.DESIGNATION_ID =QU.DESIGNATION AND   B.COMPANY_ID = "+userEntity.getCompanyId()+") DESIG, " +
					" NULL RL FROM QC_PROSPECT_MASTER.QM_USER QU WHERE DESIGNATION = (SELECT DESIGNATION FROM QC_PROSPECT_MASTER.QM_USER WHERE USERID ="+userEntity.getUserid()+")" +
					" AND ACTIVE ='A' and USERID <> "+userEntity.getUserid()+" AND QU.COMPANY_ID = "+userEntity.getCompanyId()+" AND USERID NOT IN  (SELECT ALLOCATED_TO  FROM QC_PROSPECT.QT_CASE_X_REFF_ESC CRE," +
					" QC_PROSPECT_MASTER.QM_CASE_ACTION CA1  WHERE CA1.ACTION_ID = CRE.ACTION_ID  AND   CA1.ACTION_NAME = 'REFER'  AND   " +
					" CRE.CASE_ID = "+caseId+" AND CRE.RESOLVED_DATE IS NULL) ) ORDER BY 2 ";
		}
		else if(personType.trim().equals("ALLOCATECONTACT")){
			sql = "SELECT USERID,  DESIG||' : '|| NAME USERNAME , DESIG DESIGNATION_NAME FROM  (SELECT USERID, " +
				   " TRIM(REPLACE(QU.USERFNAME||' '||QU.USERMNAME||' '||QU.USERLNAME,'  ',' ')) NAME , SUPERVISORID," +
				   " (SELECT UPPER(DESIGNATION_NAME) FROM QC_PROSPECT_MASTER.QM_DESIGNATION B  WHERE B.DESIGNATION_ID =QU.DESIGNATION AND " +
				   " B.COMPANY_ID = "+userEntity.getCompanyId()+") DESIG, level FROM QC_PROSPECT_MASTER.QM_USER QU WHERE QU.COMPANY_ID = "+userEntity.getCompanyId()+" AND ACTIVE ='A' CONNECT BY " +
				   " PRIOR USERID = SUPERVISORID START WITH USERID = "+userEntity.getUserid()+" ORDER BY LEVEL) WHERE USERID <> "+userEntity.getUserid()+
				   " AND USERID NOT IN (SELECT ALLOCATED_TO  FROM " +
				   " QC_PROSPECT.QT_CASE_X_USER  WHERE CASE_ID = "+caseId+" AND ALLOCATED_END_DATE IS NULL) ORDER BY 2";
		}
		else if(personType.trim().equals("ALLOCATEWORKLIST")){
			sql = "SELECT USERID,  DESIG||' : '|| NAME USERNAME , DESIG DESIGNATION_NAME FROM  (SELECT USERID, " +
				   "TRIM(REPLACE(QU.USERFNAME||' '||QU.USERMNAME||' '||QU.USERLNAME,'  ',' ')) NAME , SUPERVISORID," +
				   " (SELECT UPPER(DESIGNATION_NAME) FROM QC_PROSPECT_MASTER.QM_DESIGNATION B  WHERE B.DESIGNATION_ID =QU.DESIGNATION AND " +
				   " B.COMPANY_ID = "+userEntity.getCompanyId()+") DESIG, level FROM QC_PROSPECT_MASTER.QM_USER QU WHERE ACTIVE ='A' CONNECT BY " +
				   " PRIOR USERID = SUPERVISORID START WITH USERID = "+userEntity.getUserid()+" ORDER BY LEVEL) WHERE USERID <> "+userEntity.getUserid()+" AND" +
				   " USERID NOT IN (SELECT ALLOCATED_TO  FROM QT_CASE_X_USER  WHERE CASE_ID = "+caseId+" AND ALLOCATED_END_DATE IS NULL) ORDER BY 2";
		}
		else if (personType.trim().equals("NEWLEADALLOCATE")){
			sql = "SELECT  USERID,DESIG||' : '||USER_NAME USERNAME , DESIG DESIGNATION_NAME FROM  (SELECT USERID, TRIM(REPLACE(QU.USERFNAME||' '||QU.USERMNAME||'" +
					" '||QU.USERLNAME,'  ',' ')) USER_NAME , SUPERVISORID, level, (SELECT UPPER(DESIGNATION_NAME)	 " +
					" FROM QC_PROSPECT_MASTER.QM_DESIGNATION B  WHERE B.DESIGNATION_ID =QU.DESIGNATION AND   B.COMPANY_ID = "+userEntity.getCompanyId()+") " +
					" DESIG FROM QC_PROSPECT_MASTER.QM_USER QU WHERE ACTIVE ='A' CONNECT BY PRIOR USERID = SUPERVISORID START WITH " +
					" USERID ="+userEntity.getUserid()+") ORDER BY 2";
		}
		else if (personType.trim().equals("peerAndreportee")){
			sql = "SELECT USERID,  DESIG||' : '|| NAME USERNAME , DESIG DESIGNATION_NAME FROM  (SELECT USERID, " +
					   "TRIM(REPLACE(QU.USERFNAME||' '||QU.USERMNAME||' '||QU.USERLNAME,'  ',' ')) NAME , SUPERVISORID," +
					   " (SELECT UPPER(DESIGNATION_NAME) FROM QC_PROSPECT_MASTER.QM_DESIGNATION B  WHERE B.DESIGNATION_ID =QU.DESIGNATION AND " +
					   "  B.COMPANY_ID = "+userEntity.getCompanyId()+") DESIG, level FROM QC_PROSPECT_MASTER.QM_USER QU WHERE QU.COMPANY_ID = "+userEntity.getCompanyId()+" AND ACTIVE ='A' CONNECT BY " +
					   " PRIOR USERID = SUPERVISORID START WITH USERID = "+userEntity.getUserid()+" ORDER BY LEVEL) WHERE USERID <> "+userEntity.getUserid()+" ORDER BY 2";
		}
		System.out.println("SQL ::}}]] " +sql );
		try{
		Query query = session.createSQLQuery(sql);
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		List result = query.list();
		for (Object o : result) {
			Map map = (Map) o;
			PersonListDto person = new PersonListDto();
			person.setUserId(map.get("USERID").toString());
			person.setUserName(map.get("USERNAME").toString());
			person.setDesignationName(map.get("DESIGNATION_NAME")!=null?map.get("DESIGNATION_NAME").toString():"");
			personList.add(person);
		}
		}catch(Exception ex){
			logger.error("ContactDaoImpl | getLeadEscalateDetail() | :- ERROR:::::"
					+ ex.getMessage());
			ex.printStackTrace();
		}finally {
			 if(session!=null)
				session.close();
		}
		logger.info("ContactDaoImpl | getLeadEscalateDetail() | :- END  ::: WITH Response List<PersonListDto>::"
				+ personList);
		return personList;
	}

	@Transactional
	public boolean saveHelpRequested(ContactDto contactDto) {
		logger.info("ContactDaoImpl | saveHelpRequested() | :- START  ::: WITH Request ContactDto::"+contactDto);
		String sqlQuery2="";
		boolean flag = false;
		String sqlQuery;
		UserEntity userEntity = (UserEntity) httpSession.getAttribute("UserDetails");
		BigDecimal bigi;
		Session session=null;
		try {
				session = sessionFactory.openSession();				
				String sql = "INSERT INTO  QC_PROSPECT.QT_CASE_X_REFF_ESC (CASE_REF_ESC_ID,CASE_ID,ACTION_ID,ALLOCATED_TO, INITIAL_REMARKS, CREATED_BY,CREATED_DATE,CREATED_SYS_DATE)"
						+ "VALUES(QC_PROSPECT.SEQ_REF_ESC.NEXTVAL,:caseId,:actionId,:allocatedTo,:remarks,:createdBy,SYSDATE,SYSDATE)";
				String actionID = "";
				if(contactDto.getHelpType()!=null && !contactDto.getHelpType().trim().equals("")){
					if(contactDto.getHelpType().equalsIgnoreCase("escalate")) {						
						bigi = (BigDecimal) session.createSQLQuery("SELECT ACTION_ID FROM QC_PROSPECT_MASTER.QM_CASE_ACTION CA WHERE " +
									" CA.ACTION_NAME = 'ESCALATE' AND CA.ACTION_TYPE =  (SELECT ACTION_TYPE FROM " +
									" QC_PROSPECT_MASTER.QM_CASE_ACTION_TYPE WHERE ACTION_TYPE_NAME ='MOVEMENT')").uniqueResult();
						actionID = bigi.toString();
					}
					else if(contactDto.getHelpType().equalsIgnoreCase("coallocate")) {
						bigi = (BigDecimal) session.createSQLQuery("SELECT ACTION_ID FROM QC_PROSPECT_MASTER.QM_CASE_ACTION CA WHERE " +
								" CA.ACTION_NAME = 'CO-ALLOCATE'    AND CA.ACTION_TYPE = (SELECT ACTION_TYPE FROM " +
								" QC_PROSPECT_MASTER.QM_CASE_ACTION_TYPE WHERE ACTION_TYPE_NAME ='MOVEMENT')").uniqueResult();
						actionID = bigi.toString();
					}
					else if(contactDto.getHelpType().equalsIgnoreCase("refer")) {
						bigi = (BigDecimal) session.createSQLQuery("SELECT ACTION_ID FROM QC_PROSPECT_MASTER.QM_CASE_ACTION CA WHERE " +
								" CA.ACTION_NAME = 'REFER'    AND CA.ACTION_TYPE = (SELECT ACTION_TYPE FROM " +
								" QC_PROSPECT_MASTER.QM_CASE_ACTION_TYPE WHERE ACTION_TYPE_NAME ='MOVEMENT')").uniqueResult();
						actionID = bigi.toString();
					}
				}
				if (contactDto.getHelpType() != null && !(contactDto.getHelpType().trim().equals("")) && contactDto.getHelpType().trim().equals("coallocate")) {
					sql = "INSERT INTO QC_PROSPECT.QT_CASE_X_USER (CASEXUSER_ID,CASE_ID,ALLOCATED_TO,REMARKS,ALLOCATED_BY,  ALLOCATED_DATE,ALLOCATED_SYS_DATE) VALUES (QC_PROSPECT.SEQ_CASE_X_USER.NEXTVAL,:caseId,:allocatedTo,:remarks,:createdBy,SYSDATE,SYSDATE)";
					actionID = contactDto.getInitiatedTo();
					sqlQuery2 =" INSERT INTO QC_PROSPECT.QT_CASE_USER_ACTION (CASEUSERACTION_ID, CASE_ID,CASEXUSER_ID,ACTION_ID," +
							" REMARKS,CREATED_DATE,CREATED_SYS_DATE,CREATED_BY) VALUES(QC_PROSPECT.SEQ_CASE_ACTION.NEXTVAL,:caseId,:allocatedTo," +
							" (SELECT ACTION_ID FROM QC_PROSPECT_MASTER.QM_CASE_ACTION WHERE ACTION_NAME = 'CO-ALLOCATE' AND ACTIVE = 'A'" +
							" AND COMPANY_ID ="+userEntity.getCompanyId()+"),:remarks,SYSDATE,SYSDATE,:createdBy)";
				}
				if (contactDto.getHelpType() != null && !(contactDto.getHelpType().trim().equals("")) && contactDto.getHelpType().trim().equals("coallocate")) {
					Query query = session.createSQLQuery(sqlQuery2);
					query.setParameter("caseId", contactDto.getCaseId())
					.setParameter("allocatedTo",contactDto.getInitiatedTo())
					.setParameter("remarks", contactDto.getRemarks())
					.setParameter("createdBy", contactDto.getUserId());
					int update2 = query.executeUpdate();
					if(update2 > 0)
						flag = true;
					query=null;
				}
				Query query = session.createSQLQuery(sql);
				if (contactDto.getHelpType() != null && !(contactDto.getHelpType().trim().equals("")) && contactDto.getHelpType().trim().equals("coallocate")) {
					query.setParameter("caseId", contactDto.getCaseId())
					.setParameter("allocatedTo",contactDto.getInitiatedTo())
					.setParameter("remarks", contactDto.getRemarks())
					.setParameter("createdBy", contactDto.getUserId());
				} else {
					query.setParameter("caseId", contactDto.getCaseId())
					.setParameter("actionId", actionID)
					.setParameter("allocatedTo",contactDto.getInitiatedTo())
					.setParameter("remarks", contactDto.getRemarks())
					.setParameter("createdBy", contactDto.getUserId());
				}
				System.out.println("Before insert script ");
				int update = query.executeUpdate();
				query = null;
				System.out.println("Before update script ");
				if(contactDto.getHelpType()!=null && !contactDto.getHelpType().trim().equals("")){
					if(contactDto.getHelpType().equalsIgnoreCase("escalate")) {
						update = session.createSQLQuery("UPDATE QC_PROSPECT.QT_CASE QC	SET QC.ESCALATE  = 'Y' WHERE QC.CASE_ID = "+contactDto.getCaseId()+"").executeUpdate();
					}
					else if(contactDto.getHelpType().equalsIgnoreCase("coallocate")) {
						update = session.createSQLQuery("UPDATE QC_PROSPECT.QT_CASE QC	SET QC.CO_ALLOCATE  = 'Y'	WHERE QC.CASE_ID = "+contactDto.getCaseId()+"").executeUpdate();
					}
					else if(contactDto.getHelpType().equalsIgnoreCase("refer")) {
						update = session.createSQLQuery("UPDATE QC_PROSPECT.QT_CASE QC	SET QC.REFER  = 'Y'	WHERE QC.CASE_ID = "+contactDto.getCaseId()+"").executeUpdate();
					}					
				}					
				if (update > 0)
					flag = true;
			} catch (Exception e) {
				logger.info("Error in catch block due to::::->" + e.getMessage());
				e.printStackTrace();
				return false;
			}finally {
				 if(session!=null)
						session.close();
				}
			logger.info("ContactDaoImpl | saveHelpRequested() | :- END  ::: WITH Response flag::"
					+ flag);
			return flag;
	}
	
	@Transactional
	public List<CaseAllocationHistoryDto> caseAllocationHistory(String caseId,String companyId) {
		logger.info("ContactDaoImpl | caseAlocationHistory() | :- START  ::: WITH Request caseId::"+ caseId);
		String status = "";
		List<CaseAllocationHistoryDto> caseAllocationHistoryList = new ArrayList<CaseAllocationHistoryDto>();
		Session session=null;
		SQLQuery query=null;
		try{
			session=sessionFactory.openSession();
		//	UserEntity userEntity = (UserEntity) httpSession.getAttribute("UserDetails");
			String sqlquery = "SELECT ACTION,ALLOCATED_TO, ALLOCATED_BY,ALLOCATED_DATE_TIME,REMARKS " +
							" FROM (SELECT 'Allocated' ACTION,(SELECT TRIM ( REPLACE " +
							" ( QU.USERFNAME ||' '||QU.USERMNAME||' '||QU.USERLNAME,' ',' ')) FROM qc_user_auth.qm_user QU " +
							" WHERE QU.USERID= QC.ALLOCATED_TO) ALLOCATED_TO,(SELECT TRIM ( REPLACE " +
							" ( QU.USERFNAME ||' '||QU.USERMNAME||' '||QU.USERLNAME,' ',' ')) FROM qc_user_auth.qm_user QU " +
							" WHERE QU.USERID= QC.ALLOCATED_BY) ALLOCATED_BY,TO_CHAR(QC.ALLOCATED_SYS_DATE,'DD-MM-YYY HH:MI:SS') " +
							" ALLOCATED_DATE_TIME,QC.ALLOCATED_SYS_DATE ALLOCATED_DATE_TIME1,QC.REMARKS FROM QC_PROSPECT.qt_case_X_user QC " +
							" WHERE QC.CASE_ID="+caseId+" AND QC.ALLOCATED_TO NOT IN (SELECT CASEXUSER_ID FROM " +
							" QC_PROSPECT.QT_CASE_USER_ACTION WHERE CASE_ID="+caseId+" AND ACTION_ID IN " +
							" (SELECT ACTION_ID FROM QC_PROSPECT_MASTER.QM_CASE_ACTION WHERE ACTION_NAME = 'CO-ALLOCATE' " +
							" AND ACTIVE= 'A' AND COMPANY_ID="+companyId+")) UNION ALL " +
							" SELECT 'Co-Allocated',(SELECT TRIM ( REPLACE ( QU.USERFNAME ||' ' ||QU.USERMNAME ||' '||QU.USERLNAME,' ',' ')) " +
							" FROM qc_user_auth.qm_user QU WHERE QU.USERID= b.casexuser_id) ALLOCATED_TO,(SELECT TRIM " +
							" (REPLACE ( QU.USERFNAME||' '||QU.USERMNAME||' '||QU.USERLNAME,' ',' ')) FROM " +
							" qc_user_auth.qm_user QU WHERE QU.USERID= b.CREATED_BY) ALLOCATED_BY," +
							" TO_CHAR(B.CREATED_SYS_DATE,'DD-MM-YYY HH:MI:SS') ALLOCATED_DATE_TIME,B.CREATED_SYS_DATE " +
							" ALLOCATED_DATE_TIME1,B.REMARKS FROM QC_PROSPECT.QT_CASE_USER_ACTION b WHERE CASE_ID="+caseId+" " +
							" AND ACTION_ID IN(SELECT ACTION_ID FROM QC_PROSPECT_MASTER.QM_CASE_ACTION WHERE ACTION_NAME = 'CO-ALLOCATE' " +
							" AND ACTIVE= 'A' AND COMPANY_ID="+companyId+")) ORDER BY ALLOCATED_DATE_TIME1 DESC";			
			query=session.createSQLQuery(sqlquery);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List result=query.list();
			for(Object o:result){
				Map map=(Map)o;
				CaseAllocationHistoryDto caseAllocationHistoryDto = new CaseAllocationHistoryDto();
				caseAllocationHistoryDto.setAction(map.get("ACTION")!=null ? map.get("ACTION").toString() : "" );
				caseAllocationHistoryDto.setAllocatedBy(map.get("ALLOCATED_BY")!=null ? map.get("ALLOCATED_BY").toString() : "" );
				caseAllocationHistoryDto.setAllocatedTo(map.get("ALLOCATED_TO")!=null ? map.get("ALLOCATED_TO").toString() : "" );
				caseAllocationHistoryDto.setAllocatedDate(map.get("ALLOCATED_DATE_TIME")!=null ? map.get("ALLOCATED_DATE_TIME").toString() : "" );
				caseAllocationHistoryDto.setRemark(map.get("REMARKS")!=null ? map.get("REMARKS").toString() : "" );
				caseAllocationHistoryList.add(caseAllocationHistoryDto);
			}
		}catch(Exception e){
			logger.info("ContactDaoImpl | caseAllocationHistory() | ERROR:::"+e.getMessage());
			e.printStackTrace();
		}finally {
			 if(session!=null)
				session.close();
		}
		logger.info("ContactDaoImpl | caseAlocationHistory() | END:");
		return caseAllocationHistoryList;		
	}
	
}