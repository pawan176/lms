package com.qc.starter.dao.daoImpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.qc.starter.dao.NotificationDao;
import com.qc.starter.dto.NotificationDto;
@Repository
public class NotificationDaoImpl implements NotificationDao{
	private static final Logger logger = Logger.getLogger(NotificationDaoImpl.class.getName());
	@Autowired
	SessionFactory sessionFactory;

	@Transactional
	public List<NotificationDto> getNotigicationDetails(String userId,String companyId) {
		logger.info("NotificationDaoImpl | getNotigicationDetails() | :- START : With request Param:: userId ::"+userId +" companyId ::"+companyId);
		List<NotificationDto> notificationList=new ArrayList<NotificationDto>();
		Session session = sessionFactory.openSession();
		try{
			
			
			String queryStr = "SELECT CN.NOTIFICATION_ID, QC.CASE_CODE, QC.CASE_ID, (SELECT CA.NEXTACTION_NAME FROM QC_PROSPECT_MASTER.QM_CASE_NEXT_ACTION CA "
					+ "WHERE CA.COMPANY_ID = "+companyId+" AND CA.ACTIVE = 'A' AND CA.NEXTACTION_ID = (SELECT FOLLOW_ACTION_ID from qc_prospect.qt_case_user_action "
					+ "UA WHERE UA.CASE_ID = QC.CASE_ID AND FOLLOW_DT_TIME = (SELECT MAX(FOLLOW_DT_TIME) FROM qc_prospect.qt_case_user_action WHERE CASE_ID = "
					+ "QC.CASE_ID) AND ROWNUM=1)) NOTIFICATION, TO_CHAR(CN.FOLLOWUP_DATETIME, 'DD-MON-RRRR') NOTIFICATION_DATE, TO_CHAR(CN.FOLLOWUP_DATETIME,"
					+ " 'HH24:MI') NOTIFICATION_TIME, TRIM(REPLACE(PD.FNAME || ' ' || PD.MNAME || ' ' || PD.LNAME, ' ', ' ')) Customer_Name FROM QC_PROSPECT.QT_CASE "
					+ "QC, QC_PROSPECT.QT_CASE_NOTIFICATION CN, QC_PROSPECT.QT_CASE_X_USER CXU, QC_PROSPECT.QT_PERSONAL_DETAILS PD WHERE QC.CASE_ID = CN.CASE_ID"
					+ " AND QC.CASE_ID = CXU.CASE_ID AND PD.CASE_ID = QC.CASE_ID AND QC.COMPANY_ID = "+companyId+" AND CN.DISMISS_FLAG = 'N' AND CN.ACTIVE = 1 "
					+ "AND CXU.ALLOCATED_TO = "+userId+" AND CXU.ALLOCATED_END_DATE IS NULL AND TRUNC(CN.FOLLOWUP_DATETIME) >= (TRUNC(SYSDATE) - 1 ) AND "
					+ "TRUNC(CN.FOLLOWUP_DATETIME) <= (TRUNC(SYSDATE) + 1 ) ORDER BY (CN.FOLLOWUP_DATETIME)";
			
			
			/*String queryStr = "SELECT CN.NOTIFICATION_ID, QC.CASE_CODE, QC.CASE_ID, (SELECT CA.NEXTACTION_NAME   "
					+ "FROM QC_PROSPECT_MASTER.QM_CASE_NEXT_ACTION CA  WHERE CA.COMPANY_ID = "+companyId+"   "
					+ "AND CA.ACTIVE = 'A'   AND CA.NEXTACTION_ID = QUC.FOLLOW_ACTION_ID) NOTIFICATION, "
					+ "TO_CHAR(CN.FOLLOWUP_DATETIME, 'DD-MON-RRRR') NOTIFICATION_DATE, TO_CHAR(CN.FOLLOWUP_DATETIME, 'HH24:MI')"
					+ " NOTIFICATION_TIME, TRIM(REPLACE(PD.FNAME || ' ' || PD.MNAME || ' ' || PD.LNAME, ' ', ' ')) "
					+ "Customer_Name  FROM QC_PROSPECT.QT_CASE    QC, QC_PROSPECT.QT_CASE_USER_ACTION QUC, "
					+ "QC_PROSPECT.QT_CASE_NOTIFICATION CN, QC_PROSPECT.QT_CASE_X_USER CXU, QC_PROSPECT.QT_PERSONAL_DETAILS "
					+ "PD WHERE QC.CASE_ID = CN.CASE_ID  AND QC.CASE_ID = CXU.CASE_ID  AND PD.CASE_ID = QC.CASE_ID "
					+ " AND QC.CASE_ID = QUC.CASE_ID  AND QC.COMPANY_ID = "+companyId+"  AND CN.DISMISS_FLAG = 'N'  "
					+ "AND CXU.ALLOCATED_TO = "+userId+"  AND CN.ACTIVE = 1  AND CXU.ALLOCATED_END_DATE IS NULL "
					+ " AND CN.FOLLOWUP_DATETIME >= SYSDATE  AND CN.FOLLOWUP_DATETIME < (TRUNC(SYSDATE) + 1) ORDER BY CN.FOLLOWUP_DATETIME ";*/
			
			/*String queryStr="SELECT CN.NOTIFICATION_ID,QC.CASE_CODE,QC.CASE_ID,(SELECT CA.ACTION_NAME " +
					" FROM QC_PROSPECT_MASTER.QM_CASE_ACTION CA  WHERE CA.COMPANY_ID = "+companyId+""
					+" AND   CA.ACTIVE     = 'A' AND   CA.ACTION_ID = QC.ACTION_ID) NOTIFICATION," +
					" TO_CHAR (CN.FOLLOWUP_DATETIME, 'DD-MON-RRRR') NOTIFICATION_DATE,"
					+" TO_CHAR (CN.FOLLOWUP_DATETIME, 'HH24:MI') NOTIFICATION_TIME," +
					" TRIM(REPLACE(PD.FNAME||' '||PD.MNAME||' '||PD.LNAME,'  ',' ')) Customer_Name "  
					+" FROM  QC_PROSPECT.QT_CASE QC, QC_PROSPECT.QT_CASE_NOTIFICATION  CN," +
					" QC_PROSPECT.QT_CASE_X_USER CXU, QC_PROSPECT.QT_PERSONAL_DETAILS PD " 
					+" WHERE QC.CASE_ID= CN.CASE_ID AND  QC.CASE_ID = CXU.CASE_ID AND PD.CASE_ID = QC.CASE_ID AND" +
					" QC.COMPANY_ID  = "+companyId+" AND   CN.DISMISS_FLAG  = 'N' AND   QC.ACTION_ID" +
					" IN (SELECT ACTION_ID  FROM QC_PROSPECT_MASTER.QM_CASE_ACTION WHERE ACTION_NAME IN" +
					" ('CALL_BACK','FOLLOW_UP','APPOINTMENT_FIXED','DOCUMENT_PICKED') AND ACTIVE ='A' " +
					" AND COMPANY_ID ="+companyId+") AND   CXU.ALLOCATED_TO = "+userId+" AND  CN.ACTIVE=1 AND " +
					"  CN.ACTION_ID = QC.ACTION_ID AND CXU.ALLOCATED_END_DATE IS NULL "
					+" AND CN.FOLLOWUP_DATETIME >= SYSDATE AND CN.FOLLOWUP_DATETIME < (TRUNC(SYSDATE)+1)  " +
					" ORDER BY CN.FOLLOWUP_DATETIME";*/
			
			SQLQuery query = session.createSQLQuery(queryStr);
			List list=query.list();	
			Iterator itr=list.iterator();
			while(itr.hasNext()){
				NotificationDto notificationDto=new NotificationDto(); 
				Object[] obj=(Object[])itr.next();
				notificationDto.setNotificationId(obj[0]+"");
				notificationDto.setCaseCode(obj[1]+"");
				notificationDto.setCaseid(obj[2]+"");
				notificationDto.setNotification(obj[3]+"");
				notificationDto.setNotificationDate(obj[4]+"");
				notificationDto.setNotificationTime(obj[5]+"");
				notificationDto.setCustomerName(obj[6]+"");
				notificationDto.setFlag("today");					
				notificationList.add(notificationDto);
			}
		}catch(Exception e){
			logger.error("Error in catch block due to ::: --> "+ e.getMessage());
			e.printStackTrace();
		}
		/*try{
			String queryString="SELECT CN.NOTIFICATION_ID,QC.CASE_CODE,QC.CASE_ID,(SELECT CA.ACTION_NAME FROM QC_PROSPECT_MASTER.QM_CASE_ACTION CA" +
					" WHERE CA.COMPANY_ID = 1000000001 AND CA.ACTIVE= 'A' AND CA.ACTION_ID = QC.ACTION_ID) NOTIFICATION," +
					" TO_CHAR (CN.FOLLOWUP_DATETIME, 'DD-MON-RRRR') NOTIFICATION_DATE, TO_CHAR (CN.FOLLOWUP_DATETIME, 'HH24:MI')" +
					" NOTIFICATION_TIME,TRIM(REPLACE(PD.FNAME||' '||PD.MNAME||' '||PD.LNAME,'  ',' ')) Customer_Name" +
					" FROM  QC_PROSPECT.QT_CASE QC, QC_PROSPECT.QT_CASE_NOTIFICATION  CN, QC_PROSPECT.QT_CASE_X_USER CXU," +
					" QC_PROSPECT.QT_PERSONAL_DETAILS PD WHERE QC.CASE_ID = CN.CASE_ID AND QC.CASE_ID= CXU.CASE_ID" +
					" AND   PD.CASE_ID= QC.CASE_ID AND QC.COMPANY_ID  = "+companyId+" AND CN.DISMISS_FLAG  = 'N'" +
					" AND   QC.ACTION_ID   IN (SELECT ACTION_ID  FROM QC_PROSPECT_MASTER.QM_CASE_ACTION" +
					"  WHERE ACTION_NAME IN ('CALL_BACK','FOLLOW_UP','APPOINTMENT_FIXED','DOCUMENT_PICKED')" +
					" AND ACTIVE    ='A' AND COMPANY_ID =1000000001) AND COMPANY_ID ="+companyId+" " +
					" AND   CXU.ALLOCATED_TO = "+userId+" AND   CN.ACTIVE=1  AND   CN.ACTION_ID = QC.ACTION_ID  " +
					" AND   CXU.ALLOCATED_END_DATE IS NULL  AND CN.FOLLOWUP_DATETIME <  SYSDATE ORDER BY CN.FOLLOWUP_DATETIME DESC";
			SQLQuery query = session.createSQLQuery(queryString);
			List list=query.list();	
			Iterator itr=list.iterator();
			while(itr.hasNext()){
				NotificationDto notificationDto=new NotificationDto(); 
				Object[] obj=(Object[])itr.next();
				notificationDto.setNotificationId(obj[0]+"");
				notificationDto.setCaseCode(obj[1]+"");
				notificationDto.setCaseid(obj[2]+"");
				notificationDto.setNotification(obj[3]+"");
				notificationDto.setNotificationDate(obj[4]+"");
				notificationDto.setNotificationTime(obj[5]+"");
				notificationDto.setCustomerName(obj[6]+"");				
				notificationDto.setFlag("previous");
				notificationList.add(notificationDto);
			}
		}catch(Exception e){
			logger.error("Error in catch block due to ::: --> "+ e.getMessage());
			e.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}*/
		logger.info("NotificationDaoImpl | getNotigicationDetails() | :- END :");
		return notificationList;
	}

	@Transactional
	public String dismissnotificationcase(String notificationId) {
		logger.info("NotificationDaoImpl | dismissnotificationcase() | :- START : With request Param:: notificationId ::"+notificationId);
		String dismisStatus="False";
		Session session=null;
		SQLQuery query=null;
		try {
			session=sessionFactory.openSession();
			String queryStr="UPDATE QC_PROSPECT.QT_CASE_NOTIFICATION QCN SET QCN.DISMISS_FLAG ='Y',QCN.UPDATED_SYS_DATE= SYSDATE,QCN.UPDATED_DATE= SYSDATE WHERE  QCN.NOTIFICATION_ID ="+notificationId;
			query=session.createSQLQuery(queryStr);
			int status=query.executeUpdate();
			if(status>0)
				dismisStatus="Successfully dismiss Notification";
		} catch (Exception e) {
			logger.error("Error in catch block due to ::: --> "+ e.getMessage());
			e.printStackTrace();
		}	finally {
			if(session!=null)
				session.close();
		}	
		logger.info("NotificationDaoImpl | dismissnotificationcase() | :- END : With Response Param:: dismisStatus ::"+dismisStatus);
		return dismisStatus;
	}


	@Override
	@Transactional
	public String dismissAllNotification() {
		/*logger.info("NotificationDaoImpl | dismissAllNotification() | :- START ");
		String companyId="";
		String userId="";
		List<NotificationDto> notificationList=new ArrayList<NotificationDto>();
		Connection con=null;
		PreparedStatement stmt=null;
		ResultSet rt=null;
		try{
			//Session session = sessionFactory.openSession();
			Map prospectMap=ConnectionUtil.dbConnectionMapProspect;
			con = DBConnection.getConnection(prospectMap);
			String queryStr="SELECT CN.NOTIFICATION_ID,QC.CASE_CODE,(SELECT CA.ACTION_NAME  FROM QC_PROSPECT_MASTER.QM_CASE_ACTION CA  WHERE CA.COMPANY_ID = ?"
					+" AND   CA.ACTIVE     = 'A' AND   CA.ACTION_ID = QC.ACTION_ID) NOTIFICATION,TO_CHAR (CN.FOLLOWUP_DATETIME, 'DD-MON-RRRR') NOTIFICATION_DATE,"
					+" TO_CHAR (CN.FOLLOWUP_DATETIME, 'HH24:MI') NOTIFICATION_TIME, TRIM(REPLACE(PD.FNAME||' '||PD.MNAME||' '||PD.LNAME,'  ',' ')) Customer_Name "  
					+" FROM  QC_PROSPECT.QT_CASE QC, QC_PROSPECT.QT_CASE_NOTIFICATION  CN, QC_PROSPECT.QT_CASE_X_USER CXU, QC_PROSPECT.QT_PERSONAL_DETAILS PD " 
					+" WHERE QC.CASE_ID     = CN.CASE_ID AND   QC.CASE_ID     = CXU.CASE_ID AND   PD.CASE_ID     = QC.CASE_ID AND   QC.COMPANY_ID  = ?"
					+" AND   CN.DISMISS_FLAG  = 'N' AND   QC.ACTION_ID   IN (5,6,7,8) AND   CXU.ALLOCATED_TO =  ? AND   CXU.ALLOCATED_END_DATE IS NULL "
					+" AND   CN.FOLLOWUP_DATETIME <=SYSDATE+1/24 AND CN.FOLLOWUP_DATETIME BETWEEN TRUNC(SYSDATE)-1 AND TRUNC(SYSDATE)+1";
			stmt=con.prepareStatement(queryStr);
			stmt.setString(1, companyId);
			stmt.setString(2, companyId);
			stmt.setString(3, userId);
			rt=stmt.executeQuery();

			while(rt.next()){
				NotificationDto notificationDto=new NotificationDto(); 
				notificationDto.setNotificationId(rt.getString("NOTIFICATION_ID"));
				notificationDto.setCaseCode(rt.getString("CASE_CODE"));
				notificationDto.setNotification(rt.getString("NOTIFICATION"));
				notificationDto.setNotificationDate(rt.getString("NOTIFICATION_DATE"));
				notificationDto.setNotificationTime(rt.getString("NOTIFICATION_TIME"));
				notificationDto.setCustomerName(rt.getString("Customer_Name"));
				notificationList.add(notificationDto);
			}

	for(NotificationDto n:notificationList)
	{
		//update details;
	}

	}catch(Exception e)
	{
		logger.error("Error in catch block due to ::: --> "+ e.getMessage());
	e.printStackTrace();	
	}
	finally{
		try {
			if(rt!=null){
				rt.close();
				rt=null;
			}
			if(stmt!=null){
				stmt.close();
				stmt=null;
			}
			if(con!=null){
				con.close();
				con=null;
			}
		}catch (Exception e2) {
			logger.error("Error in catch block due to ::: --> "+ e2.getMessage());
			e2.printStackTrace();
		}		
	}
	logger.info("NotificationDaoImpl | dismissAllNotification() | :- END ");*/
		return null;
	}
}
