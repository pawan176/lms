package com.qc.starter.dao.daoImpl;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import oracle.jdbc.OracleTypes;

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
import com.qc.starter.basic.ConnectionFromSession;
import com.qc.starter.dao.WorkListDao;
import com.qc.starter.dto.CustomerDto;
import com.qc.starter.dto.LeadHeaderDto;
import com.qc.starter.dto.LeadsSerchDto;
import com.qc.starter.entity.UserEntity;

@Repository
public class WorkListDaoImpl implements WorkListDao {
	private static final Logger logger = Logger.getLogger(WorkListDaoImpl.class.getName());
	@Autowired
	SessionFactory sessionFactory;
	@Autowired HttpSession httpSession;

	@Transactional
	public List<LeadHeaderDto> getLeadDetails(LeadsSerchDto leadsSerchDto) {
		logger.info("WorkListDaoImpl | getLeadDetails() | :- START ");
		Session session = sessionFactory.openSession();
		//UserEntity userEntity = (UserEntity) httpSession.getAttribute("UserDetails");
		String imgImage="";
		
		String syncDate="";
		List<LeadHeaderDto> leadlist = new ArrayList<LeadHeaderDto>();
		int endResult=leadsSerchDto.getCurrentPosition()+leadsSerchDto.getMaxResult();
		String numOfRows=" where r > "+leadsSerchDto.getMaxResult()+" and r <= "+endResult+"";
		String convertedCondition="";
		if(leadsSerchDto.getRequestType().equalsIgnoreCase("MOBILE")){
			numOfRows="";
			convertedCondition="";
			if(CommonUtils.toString(leadsSerchDto.getSyncDate()).equals("")){
				syncDate="01-JAN-2017";	
			}else{
				syncDate=leadsSerchDto.getSyncDate();
			}			
			//syncDate="01-JAN-2017";
			//syncDate=" AND Qc.Updated_Date >=TO_DATE('"+leadsSerchDto.getSyncDate()+"','dd-MM-yyyy')";
		}else{
			syncDate="01-JAN-2017";
			convertedCondition = "AND QC.ACTION_ID NOT IN (30) AND (QC.PROSPECTID IS NULL OR QC.PROSPECTID = '')";
		}
		/*String sqlStr = "select * from  (SELECT a.*, rownum r FROM (SELECT  CXU.CASE_ID,QC.ALLOCATED_TO, QC.CASE_CODE, QC.QUEUE_ID, (SELECT PRODNAME FROM " +
				" QC_PROSPECT_MASTER.QM_PRODUCT  P WHERE P.PRODTYPEID in (SELECT PT.PRODUCTTYPEID FROM QC_PROSPECT_MASTER.QM_PRODUCTTYPE PT WHERE " +
				" PT.COMPANY_ID = "+leadsSerchDto.getCompany()+" AND PT.ACTIVE = 'A' AND PT.PRODUCTTYPE = 'Scheme') AND P.ACTIVE = 'A' " +
				" AND PRODUCTID = QC.QUEUE_ID) PRODUCT, (SELECT SQ.SUB_QUEUE FROM QC_PROSPECT_MASTER.QM_SUB_QUEUE SQ WHERE SQ.COMPANY_ID " +
				" = "+leadsSerchDto.getCompany()+" AND SQ.ACTIVE = 'A' AND SQ.SUB_QUEUE_ID = QC.SUB_QUEUE_ID) SUB_QUEUE, " +
				" (SELECT TRIM(REPLACE(PD.FNAME || ' ' || PD.MNAME || ' ' || PD.LNAME, '  ', ' ')) FROM QC_PROSPECT.QT_PERSONAL_DETAILS " +
				" PD WHERE PD.CASE_ID = QC.CASE_ID) CUSTOMER_NAME, QC.AMOUNT, (SELECT ACTION_NAME FROM QC_PROSPECT_MASTER.QM_CASE_ACTION CA " +
				" WHERE CA.COMPANY_ID = "+leadsSerchDto.getCompany()+" AND CA.ACTIVE = 'A' AND CA.ACTION_ID = QC.ACTION_ID) ACTION, " +
				" (SELECT CA.WEIGHT FROM QC_PROSPECT_MASTER.QM_CASE_ACTION CA WHERE CA.COMPANY_ID = "+leadsSerchDto.getCompany()+" AND CA.ACTIVE = 'A' " +
				" AND CA.ACTION_ID = QC.ACTION_ID) WEIGTH, (SELECT BANK_NAME FROM QC_PROSPECT_MASTER.QM_BANK B WHERE B.ACTIVE = 'A' AND B.BANK_ID = " +
				" QC.BANK_ID) BANK, QC.ESCALATE, QC.REFER, QC.CO_ALLOCATE, QC.GENERATION_DT ," +
				" (select ENT.CUSTENTITYTYPENAME from QC_PROSPECT_MASTER.qm_custentitytype ent where ent.custentitytypeid = PD.CUSTENTITYTYPEID AND ENT.ACTIVE ='A') CUSTENTITYTYPENAME, " +
				" PD.CUSTENTITYTYPEID, PD.CASE_COMPANY_ID , " +
		 		" (SELECT c1.company_name from QC_PROSPECT_MASTER.QM_CASE_COMPANY c1 where c1.case_company_id=PD.CASE_COMPANY_ID) COMPANY_NAME, " +
		 		" (select MO.CONTACT_NO from   QC_PROSPECT.QT_CASE_MOBILE MO where MO.CASE_ID=QC.CASE_ID and mo.primary_contact = 'Y') MOBILE_NO," +
		 		" (select  Adrs.ext1  FROM QC_PROSPECT.QT_ADDRESS Adrs where ADDRESS_TYPE='1000000002' AND Adrs.CASE_ID=QC.CASE_ID ) Extention,"+
		 		" (select  Adrs.PHONE1  FROM QC_PROSPECT.QT_ADDRESS Adrs where ADDRESS_TYPE='1000000002' AND Adrs.CASE_ID=QC.CASE_ID ) PHONE_NO "+
				" FROM QC_PROSPECT.QT_CASE_X_USER CXU, " +
				" QC_PROSPECT.QT_CASE QC WHERE CXU.CASE_ID = QC.CASE_ID AND CXU.ALLOCATED_TO = "+leadsSerchDto.getUserId()+" AND " +
				" QC.COMPANY_ID = "+leadsSerchDto.getCompany()+" AND CXU.ALLOCATED_END_DATE IS NULL ORDER BY WEIGTH, PRODUCT, SUB_QUEUE, " +
				" QC.GENERATION_DT desc) a) "+numOfRows;*/
/*		 String sqlStr = "select * from (SELECT a.*, rownum r FROM (SELECT CXU.CASE_ID,QC.ALLOCATED_TO,QC.CASE_CODE,QC.QUEUE_ID,(SELECT PRODNAME FROM " +
 		         " QC_PROSPECT_MASTER.QM_PRODUCT P WHERE P.PRODTYPEID in (SELECT PT.PRODUCTTYPEID FROM QC_PROSPECT_MASTER.QM_PRODUCTTYPE PT WHERE " +
 		         " PT.COMPANY_ID = "+leadsSerchDto.getCompany()+" AND PT.ACTIVE = 'A' AND PT.PRODUCTTYPE = 'SCHEME') AND P.ACTIVE = 'A' AND PROD_ID = QC.QUEUE_ID) PRODUCT," +
 		         " (SELECT SQ.SUB_QUEUE FROM QC_PROSPECT_MASTER.QM_SUB_QUEUE SQ WHERE SQ.COMPANY_ID = "+leadsSerchDto.getCompany()+" AND SQ.ACTIVE = 'A'  " +
 		         " AND SQ.SUB_QUEUE_ID = QC.SUB_QUEUE_ID) SUB_QUEUE,(SELECT TRIM(REPLACE(PD.FNAME || ' ' || PD.MNAME || ' ' || PD.LNAME,'  ',' '))" +
 		         " FROM QC_PROSPECT.QT_PERSONAL_DETAILS PD WHERE PD.CASE_ID = QC.CASE_ID) CUSTOMER_NAME,QC.AMOUNT,(SELECT ACTION_NAME " +
 		         " FROM QC_PROSPECT_MASTER.QM_CASE_ACTION CA WHERE CA.COMPANY_ID = "+leadsSerchDto.getCompany()+" AND CA.ACTIVE = 'A' AND CA.ACTION_ID = QC.ACTION_ID) ACTION," +
 		         " (SELECT CA.WEIGHT FROM QC_PROSPECT_MASTER.QM_CASE_ACTION CA WHERE CA.COMPANY_ID = "+leadsSerchDto.getCompany()+" AND CA.ACTIVE = 'A' AND CA.ACTION_ID = QC.ACTION_ID) WEIGTH," +
 		         " (SELECT BANK_NAME FROM QC_PROSPECT_MASTER.QM_BANK B WHERE B.ACTIVE = 'A' AND B.BANK_ID = QC.BANK_ID) BANK,QC.ESCALATE,QC.REFER,QC.CO_ALLOCATE,QC.GENERATION_DT," +
 		         " (select ENT.CUSTENTITYTYPENAME from QC_PROSPECT_MASTER.qm_custentitytype ent where ent.custentitytypeid = PD.CUSTENTITYTYPEID AND ENT.ACTIVE ='A') " +
 		         " CUSTENTITYTYPENAME,PD.CUSTENTITYTYPEID, PD.CASE_COMPANY_ID , " +
 		         " (SELECT c1.company_name from QC_PROSPECT_MASTER.QM_CASE_COMPANY c1 where c1.case_company_id=PD.CASE_COMPANY_ID) COMPANY_NAME, " +
 		         " (select MO.CONTACT_NO from   QC_PROSPECT.QT_CASE_MOBILE MO where MO.CASE_ID=QC.CASE_ID and mo.primary_contact = 'Y') MOBILE_NO," +
 		         " (select  Adrs.ext1  FROM QC_PROSPECT.QT_ADDRESS Adrs where ADDRESS_TYPE='1000000002' AND Adrs.CASE_ID=QC.CASE_ID ) Extention,"+
 		         " (select  Adrs.PHONE1  FROM QC_PROSPECT.QT_ADDRESS Adrs where ADDRESS_TYPE='1000000002' AND Adrs.CASE_ID=QC.CASE_ID ) PHONE_NO "+
 		         " FROM QC_PROSPECT.QT_CASE_X_USER CXU, QC_PROSPECT.QT_CASE QC, QC_PROSPECT.QT_PERSONAL_DETAILS PD " +
 		         " WHERE CXU.CASE_ID = QC.CASE_ID AND QC.CASE_ID = PD.CASE_ID AND CXU.ALLOCATED_TO = "+leadsSerchDto.getUserId()+" AND QC.COMPANY_ID = "+leadsSerchDto.getCompany()+" " +
 		         " AND CXU.ALLOCATED_END_DATE IS NULL "+syncDate+" ORDER BY WEIGTH, PRODUCT, SUB_QUEUE, QC.GENERATION_DT desc) a) "+numOfRows;*/

		 /*String sqlStr = " select * from (SELECT a.*, rownum r FROM (SELECT CXU.CASE_ID,QC.ALLOCATED_TO,QC.PROSPECTID,QC.CASE_CODE,QC.QUEUE_ID,(SELECT PRODNAME FROM "+
		                 " QC_PROSPECT_MASTER.QM_PRODUCT P WHERE P.PRODTYPEID in (SELECT PT.PRODUCTTYPEID FROM QC_PROSPECT_MASTER.QM_PRODUCTTYPE PT WHERE  "+
		                 " PT.COMPANY_ID = '"+leadsSerchDto.getCompany()+"' AND PT.ACTIVE = 'A' AND UPPER(PT.PRODUCTTYPE) = 'SCHEME') AND P.ACTIVE = 'A'  "+
                         " AND P.PRODUCTID = QC.QUEUE_ID) PRODUCT, "+
		                 " (SELECT SQ.SUB_QUEUE FROM QC_PROSPECT_MASTER.QM_SUB_QUEUE SQ WHERE SQ.COMPANY_ID = '"+leadsSerchDto.getCompany()+"' "+
                         " AND SQ.ACTIVE = 'A'   "+
		                 " AND SQ.SUB_QUEUE_ID = QC.SUB_QUEUE_ID) SUB_QUEUE,(SELECT TRIM(REPLACE(PD.FNAME || ' ' || PD.MNAME || ' ' || PD.LNAME,'  ',' ')) "+
		                 " FROM QC_PROSPECT.QT_PERSONAL_DETAILS PD WHERE PD.CASE_ID = QC.CASE_ID) CUSTOMER_NAME,QC.AMOUNT,(SELECT ACTION_NAME  "+
		                 " FROM QC_PROSPECT_MASTER.QM_CASE_ACTION CA WHERE CA.COMPANY_ID = '"+leadsSerchDto.getCompany()+"' AND CA.ACTIVE = 'A'  "+
                         " AND CA.ACTION_ID = QC.ACTION_ID) ACTION, "+
		                 " (SELECT CA.WEIGHT FROM QC_PROSPECT_MASTER.QM_CASE_ACTION CA WHERE CA.COMPANY_ID = '"+leadsSerchDto.getCompany()+"' "+
                         " AND CA.ACTIVE = 'A' AND CA.ACTION_ID = QC.ACTION_ID) WEIGTH, "+
		                 " (SELECT BANK_NAME FROM QC_PROSPECT_MASTER.QM_BANK B WHERE B.ACTIVE = 'A' AND B.BANK_ID = QC.BANK_ID) BANK, "+
                         " QC.ESCALATE,QC.REFER,QC.CO_ALLOCATE,to_char(QC.GENERATION_DT,'DD-MON-YYYY HH24:MI') GENERATION_DT , "+
		                 " (select ENT.CUSTENTITYTYPENAME from QC_PROSPECT_MASTER.qm_custentitytype ent where ent.custentitytypeid = PD.CUSTENTITYTYPEID "+
                         " AND ENT.ACTIVE ='A')  "+
		                 " CUSTENTITYTYPENAME,PD.CUSTENTITYTYPEID, PD.CASE_COMPANY_ID ,  "+
		                 " (SELECT c1.company_name from QC_PROSPECT_MASTER.QM_CASE_COMPANY c1 where c1.case_company_id=PD.CASE_COMPANY_ID) COMPANY_NAME, "+
		                 " MO.CONTACT_NO  MOBILE_NO, "+
		                 " (select  Adrs.ext1  FROM QC_PROSPECT.QT_ADDRESS Adrs where MAILINGADDRESS='Y' AND Adrs.CASE_ID=QC.CASE_ID AND ROWNUM = 1) EXTENTION, "+
		                 " (select  Adrs.PHONE1  FROM QC_PROSPECT.QT_ADDRESS Adrs where MAILINGADDRESS='Y' AND Adrs.CASE_ID=QC.CASE_ID AND ROWNUM = 1) PHONE_NO,  (SELECT ACTION_ID from qc_prospect.qt_case_user_action UA WHERE UA.CASE_ID=QC.CASE_ID AND CREATED_DATE=(SELECT MAX(CREATED_DATE) FROM qc_prospect.qt_case_user_action WHERE CASE_ID=QC.CASE_ID)) FOLLOW_ACTION_ID, (SELECT FOLLOW_DT_TIME from qc_prospect.qt_case_user_action UA WHERE UA.CASE_ID=QC.CASE_ID AND CREATED_DATE=(SELECT MAX(CREATED_DATE) FROM qc_prospect.qt_case_user_action WHERE CASE_ID=QC.CASE_ID)) FOLLOW_DATE_TIME, (SELECT CASE   WHEN (UA.FOLLOW_DT_TIME > SYSDATE) THEN 'Y'  ELSE 'N' END AS FOLLOW_FLAG from qc_prospect.qt_case_user_action UA WHERE UA.CASE_ID=QC.CASE_ID AND CREATED_DATE=(SELECT MAX(CREATED_DATE) FROM qc_prospect.qt_case_user_action WHERE CASE_ID=QC.CASE_ID)) FOLLOW_FLAG, (SELECT ACTION_NAME FROM QC_PROSPECT_MASTER.qm_case_action CA,qc_prospect.qt_case_user_action UA  WHERE CA.ACTION_ID=UA.ACTION_ID AND UA.CASE_ID=QC.CASE_ID AND UA.CREATED_DATE=(SELECT MAX(CREATED_DATE) FROM qc_prospect.qt_case_user_action WHERE CASE_ID=QC.CASE_ID))   ACTION_NAME "+
		                 " FROM QC_PROSPECT.QT_CASE_X_USER CXU, QC_PROSPECT.QT_CASE QC, QC_PROSPECT.QT_PERSONAL_DETAILS PD ,QC_PROSPECT.QT_CASE_MOBILE MO "+
		                 " WHERE CXU.CASE_ID = QC.CASE_ID AND QC.CASE_ID = PD.CASE_ID   AND QC.CASE_ID(+)=Mo.Case_Id AND Mo.PRIMARY_CONTACT='Y' "+ // REmoved this condition AND Mo.PRIMARY_CONTACT='Y' 
                         " AND Pd.Fname IS NOT NULL  AND Qc.Queue_Id IS NOT NULL "+
                         " AND CXU.ALLOCATED_TO = '"+leadsSerchDto.getUserId()+"' AND ( QC.CREATED_SYS_DATE >= TO_DATE('"+syncDate+"','dd-MM-yyyy') OR QC.UPDATED_DATE >= TO_DATE('"+syncDate+"','dd-MM-yyyy') )   AND QC.COMPANY_ID ='"+leadsSerchDto.getCompany()+"'  "+
                         convertedCondition+"  AND CXU.ALLOCATED_END_DATE IS NULL ORDER BY WEIGTH, PRODUCT, SUB_QUEUE, QC.GENERATION_DT desc) a) "+numOfRows;
		                // " AND CXU.ALLOCATED_END_DATE IS NULL  "+syncDate+"  ORDER BY WEIGTH, PRODUCT, SUB_QUEUE, QC.GENERATION_DT desc) a) "+numOfRows;
		 */
		 
		
		 String sqlStr = "SELECT *  FROM (SELECT A.*, ROWNUM R  FROM (SELECT QC.CASE_ID, QC.ALLOCATED_TO,QC.PROSPECTID,QC.CASE_CODE,  "+
                         " QC.QUEUE_ID,(SELECT PRODNAME  FROM QC_PROSPECT_MASTER.QM_PRODUCT P  WHERE P.PRODTYPEID IN (SELECT PT.PRODUCTTYPEID FROM QC_PROSPECT_MASTER.QM_PRODUCTTYPE PT "+
                         " WHERE PT.COMPANY_ID = '1000000001'  AND PT.ACTIVE = 'A' AND UPPER(PT.PRODUCTTYPE) = 'PRODUCT') AND P.ACTIVE = 'A'  AND P.PRODUCTID = QC.QUEUE_ID) PRODUCT,(SELECT SQ.SUB_QUEUE "+
                         " FROM QC_PROSPECT_MASTER.QM_SUB_QUEUE SQ  WHERE SQ.COMPANY_ID = '1000000001' AND SQ.ACTIVE = 'A' AND SQ.SUB_QUEUE_ID = QC.SUB_QUEUE_ID) SUB_QUEUE,(SELECT TRIM(REPLACE(PD.FNAME || ' ' || PD.MNAME || ' ' || PD.LNAME, ' ', ' '))  "+
                         " FROM QC_PROSPECT.QT_PERSONAL_DETAILS PD  WHERE PD.CASE_ID = QC.CASE_ID) CUSTOMER_NAME,QC.LOAN_AMOUNT AMOUNT,(SELECT ACTION_NAME FROM QC_PROSPECT_MASTER.QM_CASE_ACTION CA  WHERE CA.COMPANY_ID = '1000000001'  AND CA.ACTIVE = 'A' AND CA.ACTION_ID = QC.ACTION_ID) ACTION, "+
                         " (SELECT CA.WEIGHT  FROM QC_PROSPECT_MASTER.QM_CASE_ACTION CA  WHERE CA.COMPANY_ID = '1000000001'  AND CA.ACTIVE = 'A'  AND CA.ACTION_ID = QC.ACTION_ID) WEIGTH,(SELECT BANK_NAME  FROM QC_PROSPECT_MASTER.QM_BANK B   WHERE B.ACTIVE = 'A'  AND B.BANK_ID = QC.BANK_ID) BANK, "+
                         " QC.ESCALATE,QC.REFER, QC.CO_ALLOCATE,TO_CHAR(QC.GENERATION_DT, 'DD-MON-YYYY HH24:MI') GENERATION_DT,(SELECT ENT.CUSTENTITYTYPENAME  FROM QC_PROSPECT_MASTER.QM_CUSTENTITYTYPE ENT   WHERE ENT.CUSTENTITYTYPEID = PD.CUSTENTITYTYPEID  AND ENT.ACTIVE = 'A') CUSTENTITYTYPENAME,  "+
                         "  PD.CUSTENTITYTYPEID, PD.CASE_COMPANY_ID,(SELECT C1.COMPANY_NAME FROM QC_PROSPECT_MASTER.QM_CASE_COMPANY C1  WHERE C1.CASE_COMPANY_ID = PD.CASE_COMPANY_ID) COMPANY_NAME, MO.CONTACT_NO MOBILE_NO, (SELECT ADRS.EXT1  FROM QC_PROSPECT.QT_ADDRESS ADRS  WHERE MAILINGADDRESS = 'Y'  "+
                         " AND ADRS.CASE_ID = QC.CASE_ID  AND ROWNUM = 1) EXTENTION,(SELECT ADRS.PHONE1  FROM QC_PROSPECT.QT_ADDRESS ADRS   WHERE MAILINGADDRESS = 'Y'  AND ADRS.CASE_ID = QC.CASE_ID   AND ROWNUM = 1) PHONE_NO,(SELECT ACTION_ID   FROM QC_PROSPECT.QT_CASE_USER_ACTION UA  WHERE UA.CASE_ID = QC.CASE_ID "+
                         "  AND FOLLOW_DT_TIME =(SELECT MAX(FOLLOW_DT_TIME) FROM QC_PROSPECT.QT_CASE_USER_ACTION   WHERE CASE_ID = QC.CASE_ID)  AND ROWNUM = 1) FOLLOW_ACTION_ID,(SELECT FOLLOW_DT_TIME  FROM QC_PROSPECT.QT_CASE_USER_ACTION UA  WHERE UA.CASE_ID = QC.CASE_ID   AND FOLLOW_DT_TIME = (SELECT MAX(FOLLOW_DT_TIME) "+
                         "  FROM QC_PROSPECT.QT_CASE_USER_ACTION  WHERE CASE_ID = QC.CASE_ID)  AND ROWNUM = 1) FOLLOW_DATE_TIME, (SELECT CASE  WHEN (TRUNC(SYSDATE) > TRUNC(UA.FOLLOW_DT_TIME)) THEN   CASE   WHEN ((SELECT TRUNC(MAX(ACTION_DT_TIME))  FROM QC_PROSPECT.QT_CASE_USER_ACTION  WHERE CASE_ID = QC.CASE_ID) >=  TRUNC(UA.FOLLOW_DT_TIME)) THEN "+
                         " 'N'  ELSE   'Y'   END ELSE 'N' END AS FOLLOW_FLAG  FROM QC_PROSPECT.QT_CASE_USER_ACTION UA  WHERE UA.CASE_ID = QC.CASE_ID  AND FOLLOW_DT_TIME = (SELECT MAX(FOLLOW_DT_TIME)FROM QC_PROSPECT.QT_CASE_USER_ACTION   WHERE CASE_ID = QC.CASE_ID)) FOLLOW_FLAG, (SELECT ACTION_NAME FROM QC_PROSPECT_MASTER.QM_CASE_ACTION CA, QC_PROSPECT.QT_CASE_USER_ACTION   UA" +
                         "  WHERE CA.ACTION_ID = UA.ACTION_ID  AND UA.CASE_ID = QC.CASE_ID AND UA.FOLLOW_DT_TIME =(SELECT MAX(FOLLOW_DT_TIME) FROM QC_PROSPECT.QT_CASE_USER_ACTION  WHERE CASE_ID = QC.CASE_ID) AND ROWNUM = 1) ACTION_NAME  FROM  QC_PROSPECT.QT_CASE  QC, QC_PROSPECT.QT_PERSONAL_DETAILS PD, " +
                         "  QC_PROSPECT.QT_CASE_MOBILE      MO   WHERE QC.CASE_ID = PD.CASE_ID   AND QC.CASE_ID(+) = MO.CASE_ID  AND MO.PRIMARY_CONTACT = 'Y'  AND PD.FNAME IS NOT NULL  AND QC.QUEUE_ID IS NOT NULL  AND EXISTS (SELECT 1  FROM QC_PROSPECT.QT_CASE_X_USER CXU WHERE CXU.CASE_ID = QC.CASE_ID  " +
                         "  AND CXU.ALLOCATED_TO IN (SELECT USERID  FROM QC_USER_AUTH.QM_USER QU  WHERE ACTIVE = 'A'  CONNECT BY PRIOR QU.USERID = QU.SUPERVISORID  START WITH QU.USERID = '"+leadsSerchDto.getUserId()+"') ) AND (QC.CREATED_SYS_DATE >= TO_DATE('01-JAN-2017', 'dd-MM-yyyy') OR   QC.UPDATED_DATE >= TO_DATE('01-JAN-2017', 'dd-MM-yyyy'))" +
                         "  AND QC.COMPANY_ID = '1000000001'  AND QC.ACTION_ID NOT IN (30)  AND (QC.PROSPECTID IS NULL OR QC.PROSPECTID = '') AND (QC.PROSPECTID IS NULL OR QC.PROSPECTID = '')  ORDER BY WEIGTH, PRODUCT, SUB_QUEUE, QC.GENERATION_DT DESC) A) "+numOfRows;
		 		
		
		
		/* String sqlStr = "select * from (SELECT a.*, rownum r FROM (SELECT CXU.CASE_ID, QC.ALLOCATED_TO, QC.PROSPECTID, QC.CASE_CODE, QC.QUEUE_ID, "
		 		+ "(SELECT PRODNAME FROM QC_PROSPECT_MASTER.QM_PRODUCT P WHERE P.PRODTYPEID in (SELECT PT.PRODUCTTYPEID FROM QC_PROSPECT_MASTER.QM_PRODUCTTYPE "
		 		+ "PT WHERE PT.COMPANY_ID = '"+leadsSerchDto.getCompany()+"' AND PT.ACTIVE = 'A' AND UPPER(PT.PRODUCTTYPE) = 'SCHEME') AND P.ACTIVE = 'A' AND P.PRODUCTID = QC.QUEUE_ID) "
		 		+ "PRODUCT, (SELECT SQ.SUB_QUEUE FROM QC_PROSPECT_MASTER.QM_SUB_QUEUE SQ WHERE SQ.COMPANY_ID = '"+leadsSerchDto.getCompany()+"' AND SQ.ACTIVE = 'A' "
		 		+ "AND SQ.SUB_QUEUE_ID = QC.SUB_QUEUE_ID) SUB_QUEUE, (SELECT TRIM(REPLACE(PD.FNAME || ' ' || PD.MNAME || ' ' || PD.LNAME, ' ', ' ')) "
		 		+ "FROM QC_PROSPECT.QT_PERSONAL_DETAILS PD WHERE PD.CASE_ID = QC.CASE_ID) CUSTOMER_NAME, QC.AMOUNT, (SELECT ACTION_NAME FROM "
		 		+ "QC_PROSPECT_MASTER.QM_CASE_ACTION CA WHERE CA.COMPANY_ID = '"+leadsSerchDto.getCompany()+"' AND CA.ACTIVE = 'A' AND CA.ACTION_ID = QC.ACTION_ID) "
		 		+ "ACTION, (SELECT CA.WEIGHT FROM QC_PROSPECT_MASTER.QM_CASE_ACTION CA WHERE CA.COMPANY_ID = '"+leadsSerchDto.getCompany()+"' AND CA.ACTIVE = 'A' AND CA.ACTION_ID "
		 		+ "= QC.ACTION_ID) WEIGTH, (SELECT BANK_NAME FROM QC_PROSPECT_MASTER.QM_BANK B WHERE B.ACTIVE = 'A' AND B.BANK_ID = QC.BANK_ID) BANK, "
		 		+ "QC.ESCALATE, QC.REFER, QC.CO_ALLOCATE, to_char(QC.GENERATION_DT, 'DD-MON-YYYY HH24:MI') GENERATION_DT, (select ENT.CUSTENTITYTYPENAME from "
		 		+ "QC_PROSPECT_MASTER.qm_custentitytype ent where ent.custentitytypeid = PD.CUSTENTITYTYPEID AND ENT.ACTIVE = 'A') CUSTENTITYTYPENAME, "
		 		+ "PD.CUSTENTITYTYPEID, PD.CASE_COMPANY_ID, (SELECT c1.company_name from QC_PROSPECT_MASTER.QM_CASE_COMPANY c1 where c1.case_company_id ="
		 		+ " PD.CASE_COMPANY_ID) COMPANY_NAME, MO.CONTACT_NO MOBILE_NO, (select Adrs.ext1 FROM QC_PROSPECT.QT_ADDRESS Adrs where MAILINGADDRESS = 'Y' "
		 		+ "AND Adrs.CASE_ID = QC.CASE_ID AND ROWNUM = 1) EXTENTION, (select Adrs.PHONE1 FROM QC_PROSPECT.QT_ADDRESS Adrs where MAILINGADDRESS = 'Y' "
		 		+ "AND Adrs.CASE_ID = QC.CASE_ID AND ROWNUM = 1) PHONE_NO, (SELECT ACTION_ID from qc_prospect.qt_case_user_action UA WHERE UA.CASE_ID = QC.CASE_ID "
		 		+ "AND CREATED_DATE = (SELECT MAX(FOLLOW_DT_TIME) FROM qc_prospect.qt_case_user_action WHERE CASE_ID = QC.CASE_ID) AND ROWNUM=1 ) FOLLOW_ACTION_ID, "
		 		+ "(SELECT FOLLOW_DT_TIME from qc_prospect.qt_case_user_action UA WHERE UA.CASE_ID = QC.CASE_ID AND CREATED_DATE = (SELECT MAX(FOLLOW_DT_TIME) "
		 		+ "FROM qc_prospect.qt_case_user_action WHERE CASE_ID = QC.CASE_ID) AND ROWNUM=1) FOLLOW_DATE_TIME, (SELECT CASE WHEN (UA.FOLLOW_DT_TIME > SYSDATE) "
		 		+ "THEN 'Y' ELSE 'N' END AS FOLLOW_FLAG from qc_prospect.qt_case_user_action UA WHERE UA.CASE_ID = QC.CASE_ID AND CREATED_DATE = (SELECT"
		 		+ " MAX(FOLLOW_DT_TIME) FROM qc_prospect.qt_case_user_action WHERE CASE_ID = QC.CASE_ID) AND ROWNUM=1) FOLLOW_FLAG, (SELECT ACTION_NAME FROM"
		 		+ " QC_PROSPECT_MASTER.qm_case_action CA, qc_prospect.qt_case_user_action UA WHERE CA.ACTION_ID = UA.ACTION_ID AND UA.CASE_ID = QC.CASE_ID AND"
		 		+ " UA.CREATED_DATE = (SELECT MAX(FOLLOW_DT_TIME) FROM qc_prospect.qt_case_user_action WHERE CASE_ID = QC.CASE_ID) AND ROWNUM=1) ACTION_NAME"
		 		+ " FROM QC_PROSPECT.QT_CASE_X_USER CXU, QC_PROSPECT.QT_CASE QC, QC_PROSPECT.QT_PERSONAL_DETAILS PD, QC_PROSPECT.QT_CASE_MOBILE MO WHERE "
		 		+ "CXU.CASE_ID = QC.CASE_ID AND QC.CASE_ID = PD.CASE_ID AND QC.CASE_ID(+) = Mo.Case_Id AND Mo.PRIMARY_CONTACT = 'Y' AND Pd.Fname IS NOT "
		 		+ "NULL AND Qc.Queue_Id IS NOT NULL AND QC.ALLOCATED_TO IN ( SELECT USERID FROM QC_USER_AUTH.QM_USER QU WHERE ACTIVE ='A' CONNECT BY"
		 		+ " PRIOR USERID = SUPERVISORID START WITH USERID = '"+leadsSerchDto.getUserId()+"' ) AND (QC.CREATED_SYS_DATE >= TO_DATE('"+syncDate+"', 'dd-MM-yyyy') OR "
		 		+ "QC.UPDATED_DATE >= TO_DATE('"+syncDate+"', 'dd-MM-yyyy')) AND QC.COMPANY_ID = '"+leadsSerchDto.getCompany()+"' "+convertedCondition+" AND "
		 		+ "(QC.PROSPECTID IS NULL OR QC.PROSPECTID = '') AND CXU.ALLOCATED_END_DATE IS NULL ORDER BY WEIGTH, PRODUCT, SUB_QUEUE, QC.GENERATION_DT "
		 		+ "desc) a) "+numOfRows;*/
		 
		 
		
		if(leadsSerchDto.getRequestType().equalsIgnoreCase("refered")){
			sqlStr = "SELECT * FROM (SELECT rownum r,RE.CASE_ID,QC.ALLOCATED_TO, QC.PROSPECTID,QC.CASE_CODE, (SELECT PRODNAME FROM QC_PROSPECT_MASTER.QM_PRODUCT  P " +
					"WHERE P.COMPANY_ID  = '"+leadsSerchDto.getCompany()+"' AND P.PRODTYPEID  = 1 " +
					" AND P.ACTIVE = 'A' AND PRODUCTID = QC.QUEUE_ID) PRODUCT, " +
					" (SELECT SQ.SUB_QUEUE FROM QC_PROSPECT_MASTER.QM_SUB_QUEUE  SQ " +
					" WHERE SQ.COMPANY_ID = '"+leadsSerchDto.getCompany()+"' AND SQ.ACTIVE = 'A' " +
					" AND SQ.SUB_QUEUE_ID = QC.SUB_QUEUE_ID) SUB_QUEUE, " +
					"(SELECT TRIM(REPLACE(PD.FNAME||' '||PD.MNAME||' '||PD.LNAME,'  ',' '))" +
					" FROM  QC_PROSPECT.QT_PERSONAL_DETAILS PD WHERE  PD.CASE_ID= QC.CASE_ID) " +
					" CUSTOMER_NAME, QC.AMOUNT, (SELECT ACTION_NAME FROM QC_PROSPECT_MASTER.QM_CASE_ACTION CA " +
					" WHERE CA.COMPANY_ID = '"+leadsSerchDto.getCompany()+"' AND  CA.ACTIVE      ='A' AND  " +
					" CA.ACTION_ID   =  QC.ACTION_ID) ACTION, (SELECT BANK_NAME FROM" +
					" QC_PROSPECT_MASTER.QM_BANK B WHERE  B.ACTIVE  ='A' AND    B.BANK_ID = QC.BANK_ID) BANK," +
					" QC.ESCALATE, QC.REFER, QC.CO_ALLOCATE FROM QC_PROSPECT.QT_CASE_X_REFF_ESC RE, " +
					" QC_PROSPECT.QT_CASE QC WHERE RE.CASE_ID   = QC.CASE_ID AND  " +
					" RE.ALLOCATED_TO = '"+leadsSerchDto.getUserId()+"' AND  " +
					" QC.COMPANY_ID   = '"+leadsSerchDto.getCompany()+"' AND RE.ACTION_ID  IN (SELECT ACTION_ID FROM " +
					"QC_PROSPECT_MASTER.QM_CASE_ACTION   WHERE ACTION_NAME = 'REFER')" +
					"  AND   RE.RESOLVED_DATE IS NULL) where r>"+leadsSerchDto.getMaxResult()+" and r<="+endResult+"";				
		}
		else if(leadsSerchDto.getRequestType().equalsIgnoreCase("escalated")){
			sqlStr = "SELECT * FROM (SELECT a.*,rownum r FROM (SELECT RE.CASE_ID,QC.ALLOCATED_TO, QC.PROSPECTID,QC.CASE_CODE, (SELECT PRODNAME  FROM QC_PROSPECT_MASTER.QM_PRODUCT  P " +
					" WHERE P.COMPANY_ID = '"+leadsSerchDto.getCompany()+"' AND P.PRODTYPEID  = 1 AND P.ACTIVE = 'A' " +
					" AND PRODUCTID = QC.QUEUE_ID) PRODUCT, (SELECT SQ.SUB_QUEUE FROM QC_PROSPECT_MASTER.QM_SUB_QUEUE  " +
					" SQ WHERE SQ.COMPANY_ID = '"+leadsSerchDto.getCompany()+"' AND SQ.ACTIVE = 'A' " +
					" AND SQ.SUB_QUEUE_ID = QC.SUB_QUEUE_ID) SUB_QUEUE," +
					" (SELECT TRIM(REPLACE(PD.FNAME||' '||PD.MNAME||' '||PD.LNAME,'  ',' ')) " +
					" FROM  QC_PROSPECT.QT_PERSONAL_DETAILS PD WHERE  PD.CASE_ID= QC.CASE_ID) " +
					" CUSTOMER_NAME, QC.AMOUNT, (SELECT ACTION_NAME  FROM QC_PROSPECT_MASTER.QM_CASE_ACTION CA " +
					" WHERE CA.COMPANY_ID = '"+leadsSerchDto.getCompany()+"' AND  CA.ACTIVE ='A' AND" +
					" CA.ACTION_ID =QC.ACTION_ID) ACTION, (SELECT BANK_NAME FROM  QC_PROSPECT_MASTER.QM_BANK B" +
					" WHERE  B.ACTIVE  ='A' AND    B.BANK_ID = QC.BANK_ID) BANK, QC.ESCALATE,QC.REFER," +
					" QC.CO_ALLOCATE FROM QC_PROSPECT.QT_CASE_X_REFF_ESC  RE," +
					" QC_PROSPECT.QT_CASE QC WHERE RE.CASE_ID   = QC.CASE_ID AND  " +
					" RE.ALLOCATED_TO = '"+leadsSerchDto.getUserId()+"' AND" +
					" QC.COMPANY_ID   =  '"+leadsSerchDto.getCompany()+"' AND " +
					" RE.ACTION_ID  IN (SELECT ACTION_ID FROM QC_PROSPECT_MASTER.QM_CASE_ACTION WHERE ACTION_NAME ='ESCALATE') " +
					" AND RE.RESOLVED_DATE IS NULL)a ORDER BY ACTION, PRODUCT, SUB_QUEUE)" +
					" where r>"+leadsSerchDto.getMaxResult()+" and r<="+endResult+"";

		}
		try {
			SQLQuery query=session.createSQLQuery(sqlStr);
			System.out.println(query);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List result = query.list();	
			if(result != null && result.size() > 0){
				for(Object o : result){				
					Map lead = (Map) o;
					LeadHeaderDto leadHeaderDto = new LeadHeaderDto();						
					leadHeaderDto.setTemp("");
					leadHeaderDto.setLeadId(lead.get("CASE_ID")!=null? lead.get("CASE_ID").toString():" ");
					leadHeaderDto.setGenerationDate(lead.get("GENERATION_DT")!=null? lead.get("GENERATION_DT").toString():"");
					leadHeaderDto.setLeadCode(lead.get("CASE_CODE")!=null? lead.get("CASE_CODE").toString():" ");
					leadHeaderDto.setQueueId(lead.get("QUEUE_ID")!=null? lead.get("QUEUE_ID").toString():" ");
					leadHeaderDto.setQueue(lead.get("PRODUCT")!=null? lead.get("PRODUCT").toString():" ");
					leadHeaderDto.setSubQueue(lead.get("SUB_QUEUE")!=null? lead.get("SUB_QUEUE").toString():" ");				
					leadHeaderDto.setCustomerName( lead.get("CUSTOMER_NAME")!=null? lead.get("CUSTOMER_NAME").toString():" ");
					leadHeaderDto.setAmount(lead.get("AMOUNT")!=null? lead.get("AMOUNT").toString():" ");
					leadHeaderDto.setActionName(lead.get("ACTION")!=null? lead.get("ACTION").toString():" ");
					leadHeaderDto.setBankName(lead.get("BANK")!=null? lead.get("BANK").toString():" ");
					leadHeaderDto.setEscalated(lead.get("ESCALATE")!=null? lead.get("ESCALATE").toString():" ");
					leadHeaderDto.setRefer(lead.get("REFER")!=null? lead.get("REFER").toString():" ");
					leadHeaderDto.setCoAllocate(lead.get("CO_ALLOCATE")!=null? lead.get("CO_ALLOCATE").toString():" ");
					//sandeep changed for reallocation
					leadHeaderDto.setAllocatedTo(lead.get("ALLOCATED_TO")!=null? lead.get("ALLOCATED_TO").toString():" ");
					leadHeaderDto.setFollowUp_actionId(lead.get("ACTION_NAME") !=null ? lead.get("ACTION_NAME")+"" : " ");					
					leadHeaderDto.setFollowUpDate(lead.get("FOLLOW_DATE_TIME") !=null ? lead.get("FOLLOW_DATE_TIME")+"" : " ");
					leadHeaderDto.setFollowUp_Flag(lead.get("FOLLOW_FLAG") !=null ? lead.get("FOLLOW_FLAG")+"" : " ");
					//--------Added by Deepak On 12 oct 2016-----------
					if(leadsSerchDto.getRequestType().equalsIgnoreCase("MOBILE")){
						leadHeaderDto.setCustomerEntityName(lead.get("CUSTENTITYTYPENAME")!=null? lead.get("CUSTENTITYTYPENAME")+"":" ");
						leadHeaderDto.setCustomerEntityId(lead.get("CUSTENTITYTYPEID")!=null? lead.get("CUSTENTITYTYPEID")+"":" ");
						leadHeaderDto.setCompanyId(lead.get("CASE_COMPANY_ID")!=null? lead.get("CASE_COMPANY_ID")+"":" ");
						leadHeaderDto.setCompanyName(lead.get("COMPANY_NAME")!=null? lead.get("COMPANY_NAME")+"":" ");//
						leadHeaderDto.setExtention(lead.get("EXTENTION")!=null? lead.get("EXTENTION")+"":" ");
						leadHeaderDto.setPhoneNumber(lead.get("PHONE_NO")!=null? lead.get("PHONE_NO").toString():" ");
						leadHeaderDto.setCustomerMobile(lead.get("MOBILE_NO")!=null? lead.get("MOBILE_NO")+"":" ");						
						leadHeaderDto.setFollowUp_actionId(lead.get("FOLLOW_ACTION_ID") !=null ? lead.get("FOLLOW_ACTION_ID")+"" : " ");					
						leadHeaderDto.setFollowUpDate(lead.get("FOLLOW_DATE_TIME") !=null?lead.get("FOLLOW_DATE_TIME")+"" : " ");
						leadHeaderDto.setFollowUp_Flag(lead.get("FOLLOW_FLAG") !=null ? lead.get("FOLLOW_FLAG")+"" : " ");					
					}
					
					
					if(leadHeaderDto.getFollowUp_Flag().equalsIgnoreCase("Y"))
					{
						imgImage=imgImage+"<img src=\"resources/images/exclamation4.png\"/> ";	
					}
					
					if(leadHeaderDto.getEscalated().equalsIgnoreCase("Y"))
					{
						imgImage=imgImage+"<img src=\"resources/images/exclamation.png\"/> ";	
					}
					if(leadHeaderDto.getRefer().equalsIgnoreCase("Y"))
					{
						imgImage=imgImage+"<img src=\"resources/images/exclamation1.png\"/> ";		
					}	
					if(leadHeaderDto.getCoAllocate().equalsIgnoreCase("Y"))
					{
						imgImage=imgImage+"<img src=\"resources/images/exclamation2.png\"/>";	
					}
					
					if(lead.get("PROSPECTID")!=null && !CommonUtils.toString(lead.get("PROSPECTID").toString()).equals("") ){
						leadHeaderDto.setProspectId("Converted");
						imgImage=imgImage+"<img src=\"resources/images/exclamation3.png\"/>";
						
						if(!CommonUtils.toString(leadsSerchDto.getRequestType()).equalsIgnoreCase("MOBILE") ){
							leadHeaderDto = null;
							imgImage="";
							continue;							
						}
						
					}else{
						leadHeaderDto.setProspectId("Blank");
					}
						
					leadHeaderDto.setImageTag(imgImage);
					leadlist.add(leadHeaderDto);
					leadHeaderDto = null;
					imgImage="";
				}
			}
		} catch (Exception e) {
			logger.error("WorkListDaoImpl | getLeadDetails() | :- ERROR:::"+e.getMessage());
			e.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
		logger.info("WorkListDaoImpl | getLeadDetails() | :- END:::");
		return leadlist;
	}

	@Transactional
	public java.util.List<LeadHeaderDto> getLeadsSearchResult(LeadsSerchDto leadsSerchDto) {
		logger.info("WorkListDaoImpl | getLeadsSearchResult() | :- START:::");
		Connection connection =null;
		CallableStatement cstmt = null;
		ResultSet rs = null;
		List<LeadHeaderDto> leadList=new ArrayList<LeadHeaderDto>();
		Map prospectMap;
		String imgImage="";
		Session session =null;
		try {
			session = sessionFactory.openSession();
			String sqlStr = "";
			connection = ConnectionFromSession.getJavaSqlConnectionFromHibernateSession(session);	

			int endResult=leadsSerchDto.getCurrentPosition()+leadsSerchDto.getMaxResult();
			int startResult=leadsSerchDto.getMaxResult()+1;
			LeadHeaderDto lead = null;
			cstmt = connection.prepareCall("{call QC_PROSPECT.PR_GET_WORKLIST_MYWORKLIST(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			logger.info("{call QC_PROSPECT.PR_GET_WORKLIST_MYWORKLIST(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			logger.info("P_MOBILE"+" type=in value="+leadsSerchDto.getMobile());
			cstmt.setString("P_MOBILE", leadsSerchDto.getMobile()==null ? "":leadsSerchDto.getMobile());
			logger.info("P_EMAIL"+" type=in value="+leadsSerchDto.getEmail());
			cstmt.setString("P_EMAIL", leadsSerchDto.getEmail()==null?"":leadsSerchDto.getEmail());
			logger.info("P_NAME"+" type=in value="+leadsSerchDto.getName());
			cstmt.setString("P_NAME", leadsSerchDto.getName()==null?"":leadsSerchDto.getName());
			logger.info("P_LEAD_ID"+" type=in value="+leadsSerchDto.getCaseCode());
			cstmt.setString("P_LEAD_ID", leadsSerchDto.getCaseCode()==null?"":leadsSerchDto.getCaseCode());
			logger.info("P_QUEUE_ID"+" type=in value="+leadsSerchDto.getQueue());
			cstmt.setString("P_QUEUE_ID", leadsSerchDto.getQueue().equals("")?"":leadsSerchDto.getQueue());
			logger.info("P_SUB_QUEUE_ID"+" type=in value="+leadsSerchDto.getSubqueue());
			cstmt.setString("P_SUB_QUEUE_ID",leadsSerchDto.getSubqueue().equals("")?"":leadsSerchDto.getSubqueue());
			logger.info("P_DISPOSITION_STATUS"+" type=in value="+leadsSerchDto.getDisposition());
			cstmt.setString("P_DISPOSITION_STATUS",leadsSerchDto.getDisposition().equals("")?"":leadsSerchDto.getDisposition());
			logger.info("P_LEAD_STATE"+" type=in value="+leadsSerchDto.getActionId());
			cstmt.setString("P_LEAD_STATE", leadsSerchDto.getActionId().equals("")?"":leadsSerchDto.getActionId());
			logger.info("P_CAMPAIGN"+" type=in value="+leadsSerchDto.getCampaign());
			cstmt.setString("P_CAMPAIGN", leadsSerchDto.getCampaign().equals("")?"": leadsSerchDto.getCampaign());	
			logger.info("P_ALLOCATED_TO"+" type=in value="+leadsSerchDto.getAllocate());
			cstmt.setString("P_ALLOCATED_TO", leadsSerchDto.getAllocate().equals("")?"":leadsSerchDto.getAllocate());
			logger.info("P_AMOUNT_FROM"+" type=in value="+leadsSerchDto.getAmountFrom());
			cstmt.setString("P_AMOUNT_FROM", leadsSerchDto.getAmountFrom()==null?"":leadsSerchDto.getAmountFrom());			
			logger.info("P_AMOUNT_TO"+" type=in value="+ leadsSerchDto.getAmountTo());
			cstmt.setString("P_AMOUNT_TO", leadsSerchDto.getAmountTo()==null?"":leadsSerchDto.getAmountTo());			
			logger.info("P_FOLLOW_UP_FROM"+" type=in value="+ CommonUtils.getDateStringinddMMMYYYfromddMMyyyy(leadsSerchDto.getFromFollowupDate()));
			cstmt.setString("P_FOLLOW_UP_FROM", leadsSerchDto.getFromFollowupDate()==null?"":CommonUtils.getDateStringinddMMMYYYfromddMMyyyy(leadsSerchDto.getFromFollowupDate()));
			logger.info("P_FOLLOW_UP_TO"+" type=in value="+ CommonUtils.getDateStringinddMMMYYYfromddMMyyyy(leadsSerchDto.getToFollowupDate()));
			cstmt.setString("P_FOLLOW_UP_TO", leadsSerchDto.getToFollowupDate()==null?"":CommonUtils.getDateStringinddMMMYYYfromddMMyyyy(leadsSerchDto.getToFollowupDate()));			
			logger.info("P_SOURCE_ID"+" type=in value="+leadsSerchDto.getSource());
			cstmt.setString("P_SOURCE_ID", leadsSerchDto.getSource().equals("")?"":leadsSerchDto.getSource());
			logger.info("P_SORT_BY1"+" type=in value="+leadsSerchDto.getSort1());
			cstmt.setString("P_SORT_BY1", leadsSerchDto.getSort1().equals("-1")?"":leadsSerchDto.getSort1());
			logger.info("P_SORT_BY2"+" type=in value="+leadsSerchDto.getSort2());
			cstmt.setString("P_SORT_BY2", leadsSerchDto.getSort2().equals("-1")?"":leadsSerchDto.getSort2());
			logger.info("P_SORT_BY2"+" type=in value="+leadsSerchDto.getSort3());
			cstmt.setString("P_SORT_BY3", leadsSerchDto.getSort3().equals("-1")?"":leadsSerchDto.getSort3());
			logger.info("P_SORT_ORDER"+" type=in value="+leadsSerchDto.getSortOrder());
			cstmt.setString("P_SORT_ORDER", leadsSerchDto.getSortOrder().equals("-1")?"":leadsSerchDto.getSortOrder());
			cstmt.setString("P_ESC_REF",  leadsSerchDto.getEscalationRef());
			logger.info("P_ESC_REF"+" type=in value="+leadsSerchDto.getEscalationRef());
			cstmt.setString("P_USER_ID", leadsSerchDto.getUserId());
			logger.info("P_USER_ID"+" type=in value="+leadsSerchDto.getUserId());
			cstmt.setString("P_SCREEN", "WORKLIST");
			logger.info("P_SCREEN"+" type=in value=WORKLIST");
			cstmt.setString("P_FROM", startResult+"");
			logger.info("P_FROM"+" type=in value="+startResult);
			cstmt.setString("P_TO", endResult+"");
			logger.info("P_TO"+" type=in value="+endResult);
			cstmt.registerOutParameter("P_CNT", OracleTypes.INTEGER);
			logger.info("P_CNT"+" type=out");
			cstmt.registerOutParameter("P_REF_CUR", OracleTypes.CURSOR);
			logger.info("P_EXEC_STATUS"+" type=out");
			logger.info("P_REF_CUR"+" type=out");
			cstmt.registerOutParameter("P_EXEC_STATUS", java.sql.Types.CLOB);
			logger.info("P_MESSAGE"+" type=out");
			cstmt.registerOutParameter("P_MESSAGE", java.sql.Types.CLOB);
			cstmt.executeUpdate();
			String status = cstmt.getString("P_EXEC_STATUS");
			String msg = cstmt.getString("P_MESSAGE");
			rs = (ResultSet) cstmt.getObject("P_REF_CUR");
			int sizeOfList=(Integer) cstmt.getObject("P_CNT");
			if(rs!=null){
				while(rs.next()){
					lead = new LeadHeaderDto();
					lead.setSizeOfList(sizeOfList);
					lead.setTemp("");
					lead.setLeadId(rs.getString("LEAD_ID")==null?"":rs.getString("LEAD_ID"));
					lead.setLeadCode(rs.getString("LEAD_CODE")==null?"":rs.getString("LEAD_CODE"));
					lead.setQueue(rs.getString("QUEUE")==null?"":rs.getString("QUEUE"));
					lead.setSubQueue(rs.getString("SUB_QUEUE")==null?"":rs.getString("SUB_QUEUE"));
					lead.setCustomerName(rs.getString("CUSTOMERNAME")==null?"":rs.getString("CUSTOMERNAME"));
					lead.setAmount(rs.getString("AMOUNT")==null?"":rs.getString("AMOUNT"));
					lead.setActionName(rs.getString("LEADSTATE")==null?"":rs.getString("LEADSTATE"));
					lead.setBankName(rs.getString("BANK_NAME")==null?"":rs.getString("BANK_NAME"));
					lead.setEscalated(rs.getString("ESCALATE")==null?"":rs.getString("ESCALATE"));
					lead.setRefer(rs.getString("REFER")==null?"":rs.getString("REFER"));
					lead.setCoAllocate(rs.getString("CO_ALLOCATE")==null?"":rs.getString("CO_ALLOCATE"));
					lead.setAllocatedTo(rs.getString("ALLOCATED_TO")==null?"":rs.getString("ALLOCATED_TO"));					
					
					lead.setFollowUp_actionId(rs.getString("ACTION_NAME")==null?" ":rs.getString("ACTION_NAME"));
					//lead.setFollowUp_actionId(rs.getString("FOLLOW_ACTION_ID")==null?" ":rs.getString("FOLLOW_ACTION_ID"));					
					lead.setFollowUpDate(rs.getString("FOLLOW_DATE_TIME")==null?" ":rs.getString("FOLLOW_DATE_TIME"));
					lead.setFollowUp_Flag(rs.getString("FOLLOW_FLAG")==null?" ":rs.getString("FOLLOW_FLAG"));
					
					if(lead.getFollowUp_Flag().equalsIgnoreCase("Y"))
					{
						imgImage=imgImage+"<img src=\"resources/images/exclamation4.png\"/> ";	
					}
					
					if(rs.getString("PROSPECTID")!=null && !CommonUtils.toString(rs.getString("PROSPECTID").toString()).equals("") ){
						lead.setProspectId("Converted");
						imgImage=imgImage+"<img src=\"resources/images/exclamation3.png\"/>";
					}else{
						lead.setProspectId("Blank");
					}

					if(lead.getEscalated().equalsIgnoreCase("Y"))
					{
						imgImage=imgImage+"<img src=\"resources/images/exclamation.png\"/> ";	
					}
					if(lead.getRefer().equalsIgnoreCase("Y"))
					{
						imgImage=imgImage+"<img src=\"resources/images/exclamation1.png\"/> ";		
					}	
					if(lead.getCoAllocate().equalsIgnoreCase("Y"))
					{
						imgImage=imgImage+"<img src=\"resources/images/exclamation2.png\"/>";	
					}
					
					
					
					lead.setImageTag(imgImage);
					leadList.add(lead);
					lead=null;
					imgImage="";
				}						
			}
		}
		catch(Exception e) {		
			logger.error("WorkListDaoImpl | getLeadsSearchResult() | :- ERROR:::"+e.getMessage());
			e.printStackTrace();
			return null;
		}finally{
			if(session!=null){
				session.close();
			}
			if(connection!=null){
				try {
					if(rs!=null)
						rs.close();
					cstmt.close();					
					connection.close();					

				} catch (Exception e) {
					logger.error("WorkListDaoImpl | getLeadsSearchResult() | :- ERROR:::"+e.getMessage());
					e.printStackTrace();
				}
			}
		}
		logger.info("WorkListDaoImpl | getLeadsSearchResult() | :- END:::");
		return leadList;
	}

	@Transactional
	public String saveAllocatedLead(List inpList, String remark, String queueId,String allocatedId) {
		logger.info("WorkListDaoImpl | saveAllocatedLead() | :- START:::");
		String flag = "";
		Session session=null;
		try{
			session = sessionFactory.openSession();
			UserEntity userEntity = (UserEntity) httpSession.getAttribute("UserDetails");
			for(int i=0;i<inpList.size();i++)
			{
				String caseIdAndAllocatedTo=(String)inpList.get(i);
				String caseArray[]=caseIdAndAllocatedTo.split("~");
				String caseid=null;
				String userId=null;
				if(caseArray.length>1){
					caseid = caseArray[0];
					userId=caseArray[1];
				}else {
					caseid = "";
					userId="";
				}
				String sql = "UPDATE QC_PROSPECT.QT_CASE_X_USER CXU SET CXU.ALLOCATED_END_DATE = SYSDATE WHERE CXU.CASE_ID = " + caseid
						+" AND CXU.ALLOCATED_TO = "+userId+" and allocated_end_date is null";
				Query query = session.createSQLQuery(sql);

				try{
					flag = flag + query.executeUpdate()+"~";
				}catch(Exception e){
					logger.error("WorkListDaoImpl | saveAllocatedLead() | :- ERROR:::"+e.getMessage());
				}
			}

			for(int i=0;i<inpList.size();i++)
			{
				String caseIdAndAllocatedTo=(String)inpList.get(i);
				String caseArray[]=caseIdAndAllocatedTo.split("~");
				String caseid=null;
				String userId=null;
				if(caseArray.length>1){
					caseid = caseArray[0];
					//it is allocatedTo
					userId=caseArray[1];
				}else {
					caseid = "";
					userId="";
				}
				String insert = "INSERT INTO QC_PROSPECT.QT_CASE_X_USER( CASEXUSER_ID, CASE_ID ,  ALLOCATED_TO, ALLOCATED_BY,  " +
						" ALLOCATED_DATE, ALLOCATED_SYS_DATE,REMARKS) VALUES (QC_PROSPECT.SEQ_CASE_X_USER.NEXTVAL, :caseId, :allocateTo, " +
						" :allocateFrom, SYSDATE,  SYSDATE,:remark)";		
				Query query = session.createSQLQuery(insert);
				query.setParameter("caseId",caseid)
				.setParameter("allocateTo",allocatedId)
				.setParameter("allocateFrom",userEntity.getUserid())
				.setParameter("remark",remark);
				int update = query.executeUpdate();		       
				try{
					flag = flag + update+"~";
				}catch(Exception e){
					logger.error("WorkListDaoImpl | saveAllocatedLead() | :- ERROR:::"+e.getMessage());
				}				
			}		
		}catch(Exception e){
			logger.error("WorkListDaoImpl | saveAllocatedLead() | :- ERROR:::"+e.getMessage());
			return null;
		}finally {
			if(session!=null)
				session.close();
		}
		logger.info("WorkListDaoImpl | saveAllocatedLead() | :- END:::");
		return flag;
	}


	@Transactional
	public String getLeadStatelist(String disposition) {
		logger.info("WorkListDaoImpl | getLeadStatelist() | :- START:::");
		Session session = sessionFactory.openSession();
		String leadStates="";
		if( disposition!=null && (!disposition.equalsIgnoreCase("") )){
			String sqlStr="SELECT distinct ACTION_STAGE FROM QC_PROSPECT_MASTER.QM_CASE_ACTION QC WHERE ACTION_ID IN ("+disposition+")";
			try {
				SQLQuery query=session.createSQLQuery(sqlStr);
				List result = query.list();
				Iterator itr =result.iterator();
				int c=0;
				while(itr.hasNext()){
					String obj=(String)itr.next();
					if(c==0){
						leadStates=obj!=null? obj+"":"";
						c++;
					}else{
						leadStates=leadStates+"~"+(obj!=null? obj+"":"");
					}					

				}
			} catch (Exception e) {
				logger.error("WorkListDaoImpl | getLeadStatelist() | :- ERROR:::"+e.getMessage());
			}finally {
				if(session!=null)
					session.close();
			}
		}
		logger.info("WorkListDaoImpl | getLeadStatelist() | :- END:::");
		return leadStates;
	}

	@Transactional
	public int getLeadDetailsListSize(LeadsSerchDto leadsSerchDto) {
		logger.info("WorkListDaoImpl | getLeadDetailsListSize() | :- START:::");
		Session session = sessionFactory.openSession();
		int size = 0;
		UserEntity userEntity = (UserEntity) httpSession.getAttribute("UserDetails");
		String imgImage="";
		List<LeadHeaderDto> leadlist = new ArrayList<LeadHeaderDto>();
		/*String sqlStr = "SELECT count(*) count FROM QC_PROSPECT.QT_CASE_X_USER CXU, QC_PROSPECT.QT_CASE    " +
				" QC WHERE CXU.CASE_ID= QC.CASE_ID      " +
				" AND CXU.ALLOCATED_TO = "+userEntity.getUserid()+" AND QC.COMPANY_ID   = "+userEntity.getCompanyId()+"" +
				" AND CXU.ALLOCATED_END_DATE IS NULL  ";*/
		String sqlStr = "SELECT COUNT(*) COUNT FROM QC_PROSPECT.QT_PERSONAL_DETAILS PD,QC_PROSPECT.QT_CASE_MOBILE      MO,QC_PROSPECT.QT_CASE   " +
				        "QC WHERE  EXISTS (   SELECT 1  FROM QC_PROSPECT.QT_CASE_X_USER CXU  WHERE CXU.CASE_ID = QC.CASE_ID  AND CXU.ALLOCATED_TO IN " +
				        "(SELECT USERID  FROM QC_USER_AUTH.QM_USER QU   WHERE ACTIVE = 'A'  CONNECT BY PRIOR QU.USERID = QU.SUPERVISORID  START WITH QU.USERID = '1000000456'))" +
				        " AND QC.COMPANY_ID = 1000000001  AND QC.CASE_ID = PD.CASE_ID  AND QC.CASE_ID(+) = MO.CASE_ID  AND MO.PRIMARY_CONTACT = 'Y'  AND PD.FNAME IS NOT NULL" +
				        " AND QC.ACTION_ID NOT IN (30)  AND (QC.PROSPECTID IS NULL OR QC.PROSPECTID = '')  AND QC.QUEUE_ID IS NOT NULL" ;
		if(leadsSerchDto.getRequestType().equalsIgnoreCase("refered")){
			sqlStr = "SELECT count (*) count FROM QC_PROSPECT.QT_CASE_X_REFF_ESC RE, " +
					" QC_PROSPECT.QT_CASE QC WHERE RE.CASE_ID   = QC.CASE_ID AND  " +
					" RE.ALLOCATED_TO = '"+userEntity.getUserid()+"' AND  " +
					" QC.COMPANY_ID   = '"+userEntity.getCompanyId()+"' AND RE.ACTION_ID  IN (SELECT ACTION_ID FROM " +
					" QC_PROSPECT_MASTER.QM_CASE_ACTION   WHERE ACTION_NAME = 'REFER')" +
					" AND   RE.RESOLVED_DATE IS NULL";				
		}
		else if(leadsSerchDto.getRequestType().equalsIgnoreCase("escalated")){
			sqlStr = "SELECT count(*) count FROM QC_PROSPECT.QT_CASE_X_REFF_ESC  RE," +
					" QC_PROSPECT.QT_CASE QC WHERE RE.CASE_ID   = QC.CASE_ID AND  " +
					" RE.ALLOCATED_TO = '"+userEntity.getUserid()+"' AND" +
					" QC.COMPANY_ID   =  '"+userEntity.getCompanyId()+"' AND " +
					" RE.ACTION_ID  IN (SELECT ACTION_ID FROM QC_PROSPECT_MASTER.QM_CASE_ACTION WHERE ACTION_NAME ='ESCALATE') " +
					" AND RE.RESOLVED_DATE IS NULL";
		}
		try {
			System.out.println("sqlStr Lead Count -:-"+sqlStr);
			SQLQuery query=session.createSQLQuery(sqlStr);
			List result = query.list();
			Iterator itr =result.iterator();
			size=((BigDecimal)result.get(0)).intValue();
		} catch (Exception e) {
			logger.error("WorkListDaoImpl | getLeadDetailsListSize() | :- ERROR:::"+e.getMessage());
			e.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}				
		logger.info("WorkListDaoImpl | getLeadDetailsListSize() | :- END:::");
		return size;
	}
	
	@Transactional
	public String allocationFromMobile(CustomerDto customerDto){
		Session session = sessionFactory.openSession();
		session = sessionFactory.openSession();
		String flag = null;
		try{			
			
			String caseIdAndAllocatedTo=customerDto.getAllocateTo();
			String caseid=customerDto.getCaseId();
			String userId=customerDto.getUserId();
			
			
			String sql = "UPDATE QC_PROSPECT.QT_CASE_X_USER CXU SET CXU.ALLOCATED_END_DATE = SYSDATE WHERE CXU.CASE_ID = " + caseid
					+" AND CXU.ALLOCATED_TO = "+userId+" AND ( CXU.ALLOCATED_END_DATE IS NULL OR CXU.ALLOCATED_END_DATE = '' ) ";
			
			Query query = session.createSQLQuery(sql);
			
			try{
				flag =   query.executeUpdate()+"~";
			}catch(Exception e){
				e.printStackTrace();
				logger.error("WorkListDaoImpl | saveAllocatedLead() | :- ERROR:::"+e.getMessage());
			}
			
			
			sql = "UPDATE QC_PROSPECT.QT_CASE SET ALLOCATED_TO = "+caseIdAndAllocatedTo+" WHERE CASE_ID = " + caseid;
			
			query = session.createSQLQuery(sql);
			
			try{
				flag =   query.executeUpdate()+"~";
			}catch(Exception e){
				e.printStackTrace();
				logger.error("WorkListDaoImpl | saveAllocatedLead() | :- ERROR:::"+e.getMessage());
			}
			
			
								
			String insert = "INSERT INTO QC_PROSPECT.QT_CASE_X_USER( CASEXUSER_ID, CASE_ID ,  ALLOCATED_TO, ALLOCATED_BY,  " +
					" ALLOCATED_DATE, ALLOCATED_SYS_DATE,REMARKS) VALUES (QC_PROSPECT.SEQ_CASE_X_USER.NEXTVAL, :caseId, :allocateTo, " +
					" :allocateFrom, SYSDATE,  SYSDATE,:remark)";		
			query = session.createSQLQuery(insert);
			query.setParameter("caseId",caseid)
			.setParameter("allocateTo",caseIdAndAllocatedTo)
			.setParameter("allocateFrom",userId)
			.setParameter("remark","Reallocated From Mobile");
			int update = query.executeUpdate();		       
			try{
				flag = flag + update+"~";
			}catch(Exception e){
				logger.error("WorkListDaoImpl | saveAllocatedLead() | :- ERROR:::"+e.getMessage());
			}
					
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(session!=null)
				session.close();
		}
		return flag;
	}

}
