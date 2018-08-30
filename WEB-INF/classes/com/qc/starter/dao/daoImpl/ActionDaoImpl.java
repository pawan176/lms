package com.qc.starter.dao.daoImpl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.qc.starter.basic.CommonUtils;
import com.qc.starter.dao.ActionDao;
import com.qc.starter.dto.ContactDto;

@Repository
public class ActionDaoImpl implements ActionDao{
	private static final Logger logger = Logger.getLogger(ActionDaoImpl.class.getName());
	@Autowired SessionFactory sessionFactory;
	@Autowired
	HttpSession httpSession;

	@Transactional
	public String saveCaseAction(ContactDto contactDto){
		logger.info("ActionDaoImpl | saveCaseAction() | :- START:::caseId-"+contactDto.getCaseId()+"userId"+contactDto.getUserId()
				+"Action id-"+contactDto.getActionId()+"Remark-"+contactDto.getRemarks()+""+"");
		String update = "";
		boolean status = false;
		Session session=null;
		String currActionId=null;
		try{
			logger.info("ActionDaoImpl | saveCaseAction() | :- START  ::: WITH Request ContactDto::"+contactDto);					
			//UserEntity userEntity = (UserEntity) httpSession.getAttribute("UserDetails");
			session = sessionFactory.openSession();
			/*String sql = "INSERT INTO  QC_PROSPECT.QT_CASE_USER_ACTION (CASEUSERACTION_ID, CASE_ID,CASEXUSER_ID,ACTION_ID,REMARKS,CREATED_DATE,CREATED_SYS_DATE)"+
					" VALUES(QC_PROSPECT.SEQ_CASE_ACTION.NEXTVAL ,:caseId,:userId,:actionId,:remark,SYSDATE,SYSDATE)";
			Query query = session.createSQLQuery(sql)
					.setParameter("caseId",Integer.parseInt(contactDto.getCaseId()))
					.setParameter("userId",Integer.parseInt(contactDto.getUserId()))	
					.setParameter("actionId",contactDto.getActionId())						
					.setParameter("remark",contactDto.getRemarks());
			update = update + query.executeUpdate();*/
			
			/*String sql = "INSERT INTO  QC_PROSPECT.QT_CASE_USER_ACTION (CASEUSERACTION_ID, CASE_ID,CASEXUSER_ID,ACTION_ID,REMARKS,CREATED_DATE,CREATED_SYS_DATE,"+
					" ACTION_DT_TIME, FOLLOW_ACTION_ID, FOLLOW_DT_TIME, CASE_STAGE_ID, SUB_QUEUE_ID)"+
					" VALUES (QC_PROSPECT.SEQ_CASE_ACTION.NEXTVAL ,:caseId,:userId,:actionId,:remark" +
					",SYSDATE,SYSDATE,TO_DATE('"+contactDto.getFollowupDate()+" "+contactDto.getFollowupTime()+"','DD-mm-RRRR HH12:MI am'),:followupaction,TO_DATE('"+contactDto.getFollowupDate()+" "+contactDto.getFollowupTime()+"','DD-mm-RRRR HH12:MI am'),:caseStageId,:subqueueid)";*/
			String actionDate,actionTime,FollowupDate,followupTime;
			String actionDateTime="",followupDateTime="";
			
			actionDate = CommonUtils.toString(contactDto.getActionDate()).equals("") ? "" : CommonUtils.toString(contactDto.getActionDate());
			actionTime = CommonUtils.toString(contactDto.getActionTime()).equals("") ? "" : CommonUtils.toString(contactDto.getActionTime());
			FollowupDate = CommonUtils.toString(contactDto.getFollowupDate()).equals("") ? "" : CommonUtils.toString(contactDto.getFollowupDate());
			followupTime = CommonUtils.toString(contactDto.getFollowupTime()).equals("") ? "" : CommonUtils.toString(contactDto.getFollowupTime()) ;
			
			if(actionDate.equals("") || actionTime.equals("")){
				actionDateTime = "NULL";	
			}else{
				actionDateTime = "TO_DATE('"+actionDate+" "+actionTime+"','DD-mm-RRRR HH12:MI am')";	
			}
			
			if(FollowupDate.equals("") || followupTime.equals("")){
				followupDateTime = "NULL";	
			}else{
				followupDateTime = "TO_DATE('"+FollowupDate+" "+followupTime+"','DD-mm-RRRR HH12:MI am')";		
			}
			 
			String sql = "INSERT INTO  QC_PROSPECT.QT_CASE_USER_ACTION (CASEUSERACTION_ID, CASE_ID,CASEXUSER_ID,ACTION_ID,REMARKS,CREATED_DATE,CREATED_SYS_DATE,"+
					" FOLLOW_ACTION_ID, CASE_STAGE_ID, SUB_QUEUE_ID,ACTION_DT_TIME,FOLLOW_DT_TIME,PURPOSE_ID,REJECT_REGION_ID)"+
					" VALUES (QC_PROSPECT.SEQ_CASE_ACTION.NEXTVAL ,:caseId,:userId,:actionId,:remark" +
					",SYSDATE,SYSDATE,:followupaction,:caseStageId,:subqueueid," +
					actionDateTime +
					" , "+followupDateTime+",:purpose,:rejectReason)";

			Query query = session.createSQLQuery(sql)
					.setParameter("caseId",Integer.parseInt(contactDto.getCaseId()))
					.setParameter("userId",Integer.parseInt(contactDto.getUserId()))	
					.setParameter("actionId",contactDto.getActionId())						
					.setParameter("remark",contactDto.getRemarks())
					//.setParameter("actionDate",contactDto.getRemarks())
					.setParameter("followupaction",contactDto.getFollowupAction())
					//.setParameter("followupDate",contactDto.getRemarks())
					.setParameter("caseStageId",contactDto.getLeadStage())
					.setParameter("subqueueid",contactDto.getPotential())
					.setParameter("purpose",CommonUtils.toStringDatabaseNull(contactDto.getPurpose()))
					.setParameter("rejectReason",CommonUtils.toStringDatabaseNull(contactDto.getRejectReason()));
					
			update = update + query.executeUpdate();
			
			
			
			String actionId = contactDto.getActionId();
			String nextActionId = contactDto.getFollowupAction();
			
			if(CommonUtils.toString(actionId).trim().equals("")){
				actionId = contactDto.getDisposition();
			}
			
			try{
				List list=session.createSQLQuery("select max(CASEUSERACTION_ID) from QC_PROSPECT.QT_CASE_USER_ACTION").list();
				currActionId=list.size()>0?list.get(0)+"":null;
			}catch(Exception e){
				e.printStackTrace();
			}
			logger.info("ActionDaoImpl | after action Id save actionId-"+actionId);
			logger.info("ActionDaoImpl | update case table parameter action Id-"+actionId+"caseId-"+contactDto.getCaseId());
			query = null;
			
			//String sqlupdate = "UPDATE QC_PROSPECT.QT_CASE QC SET QC.DISPOSITION_ID=:dispositionId,QC.ACTION_ID=:actionId, QC.UPDATED_SYS_DATE= SYSDATE WHERE QC.CASE_ID =:caseId";
			
			String sqlupdate = "UPDATE QC_PROSPECT.QT_CASE QC SET QC.DISPOSITION_ID=:dispositionId,QC.ACTION_ID=:actionId, " +
					" QC.STAGE_ID=:stageId,QC.SUB_QUEUE_ID=:subqueueid ,QC.UPDATED_SYS_DATE= SYSDATE WHERE QC.CASE_ID =:caseId";
			
			
			query = session.createSQLQuery(sqlupdate)	
					.setParameter("dispositionId",actionId)
					.setParameter("actionId",actionId)
					.setParameter("stageId",contactDto.getLeadStage())
					.setParameter("subqueueid",contactDto.getPotential())
					.setParameter("caseId",contactDto.getCaseId());

			update = update + query.executeUpdate();
			logger.info("ActionDaoImpl | update case table successfully ");
			query =  null;
			/*String sqlactionBox  = "select ACTION_ID from QC_PROSPECT_MASTER.qm_case_action where action_name in" +
					" ('CALL_BACK','FOLLOW_UP','APPOINTMENT_FIXED','DOCUMENT_PICKED')" +
					"	and company_id = "+contactDto.getCompanyId();
			query = session.createSQLQuery(sqlactionBox);
			List stList = query.list();
			for(Object string:stList){
				if(nextActionId.equalsIgnoreCase(string.toString())){*/
				
					if( !CommonUtils.toString(contactDto.getFollowupAction()).equals("") && !CommonUtils.toString(contactDto.getFollowupAction()).equals("-1") && !CommonUtils.toString(followupDateTime).equals("") && !CommonUtils.toString(followupDateTime).equals("NULL") ){
						String sql3 = "UPDATE QC_PROSPECT.QT_CASE_NOTIFICATION CN set CN.ACTIVE =0,UPDATED_SYS_DATE =SYSDATE " +
								" where  CN.CASE_ID = :caseId "; 
						query = null;
						query = session.createSQLQuery(sql3)
								.setParameter("caseId",Integer.parseInt(contactDto.getCaseId()));
						update = update + query.executeUpdate();
						query = null;
						String sql2 = "INSERT INTO QC_PROSPECT.QT_CASE_NOTIFICATION   (NOTIFICATION_ID,ACTION_ID," +
								" CASE_ID,ACTIVE, FOLLOWUP_DATETIME, DISMISS_FLAG, SNOOZE_DATETIME, REMARKS," +
								" CREATED_DATE, CREATED_SYS_DATE, UPDATED_DATE, UPDATED_SYS_DATE)" +
								" VALUES( QC_PROSPECT.SEQ_NOTIFICATION.NEXTVAL,:actionId,:caseId,1," +followupDateTime+
								"  ,'N',:snooze_time,:remark,SYSDATE,SYSDATE,:date1,:date2)";
						query = session.createSQLQuery(sql2)
								.setParameter("actionId",Integer.parseInt(nextActionId))
								.setParameter("caseId",Integer.parseInt(contactDto.getCaseId()))
								.setParameter("snooze_time",new java.sql.Date(new Date().getTime()))
								.setParameter("remark",contactDto.getRemarks())
								.setParameter("date1","")
								.setParameter("date2","");				
						update = update + query.executeUpdate();
					}
			
						

			/*	}
			}*/
			if(update !=null && !(update.equals("")))
				status = true;
		}catch(Exception e){
			logger.error("ActionDaoImpl | saveCaseAction() | :- error:::"+e.getMessage());
			e.printStackTrace();
		}finally {

			if(session!=null)
				session.close();
		}
		logger.info("ActionDaoImpl | saveCaseAction() | :- END :::");
		return currActionId;
	}

	@Transactional
	public boolean saveResolution(ContactDto contactDto,String caseId){		
		logger.info("ActionDaoImpl | saveResolution() | :- START  ::: WITH Request ContactDto::"+contactDto);
		boolean flag=false;
		Session session = null;
		try{
			session = sessionFactory.openSession();
			String sql = "UPDATE QC_PROSPECT.QT_CASE_X_REFF_ESC QEC  SET QEC.RESOLVED_REMARKS = :remark WHERE ACTION_ID = " +
					"(SELECT ACTION_ID FROM QC_PROSPECT_MASTER.QM_CASE_ACTION WHERE ACTION_NAME = '"+contactDto.getActionName()+"' ) " +
					" AND  QEC.CASE_REF_ESC_ID = "+contactDto.getCaseRefEscId();
			if(contactDto.getResolutionCheck() !=null && contactDto.getResolutionCheck().trim().equals("resolve")){
				sql = "UPDATE QC_PROSPECT.QT_CASE_X_REFF_ESC QEC SET QEC.RESOLVED_DATE = SYSDATE, QEC.RESOLVED_REMARKS =:remark , " +
						" QEC.STATUS = (SELECT QS.STATUS_ID FROM  QC_PROSPECT_MASTER.QM_CASE_STATUS QS WHERE STATUS_NAME ='RESOLVED')" +
						" WHERE ACTION_ID = (SELECT ACTION_ID  FROM QC_PROSPECT_MASTER.QM_CASE_ACTION WHERE ACTION_NAME = '"+contactDto.getActionName()+"' ) " +
						" AND QEC.CASE_REF_ESC_ID = "+contactDto.getCaseRefEscId();
			}
			Query query = session.createSQLQuery(sql)
					.setParameter("remark",contactDto.getResolvedRemarks());
			int update = query.executeUpdate();
			if(update > 0){
				flag = true;
			}			
			query=null;
			String sqlRemoveFlag = "";
			if(contactDto.getActionName() !=null && contactDto.getActionName().trim().equals("ESCALATE"))
				sqlRemoveFlag = "UPDATE QC_PROSPECT.QT_CASE QC	SET QC.ESCALATE  = 'N', QC.UPDATED_SYS_DATE =SYSDATE WHERE QC.CASE_ID = "+caseId;
			if(contactDto.getActionName() !=null && contactDto.getActionName().trim().equals("REFER"))		
				sqlRemoveFlag = "UPDATE QC_PROSPECT.QT_CASE QC	SET QC.REFER  = 'N'	WHERE QC.CASE_ID = "+caseId;

			if(contactDto.getResolutionCheck() !=null && contactDto.getResolutionCheck().trim().equals("resolve")){
				query=session.createSQLQuery(sqlRemoveFlag);
				int removeflag = query.executeUpdate();
			}			
		}catch(Exception e){
			logger.error("ActionDaoImpl | saveResolution() | :- ERROR::::"+e.getMessage());
			e.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
		logger.info("ActionDaoImpl | saveResolution() | :- END");
		return flag;
	}
}
