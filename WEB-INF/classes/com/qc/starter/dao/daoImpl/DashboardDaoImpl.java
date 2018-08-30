package com.qc.starter.dao.daoImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import oracle.jdbc.OracleTypes;

import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.qc.starter.basic.CommonUtils;
import com.qc.starter.basic.ConnectionFromSession;
import com.qc.starter.dao.DashboardDao;
import com.qc.starter.dto.DashboardDto;
import com.qc.starter.dto.LeadsSerchDto;
import com.qc.starter.entity.CampaignEntity;
import com.qc.starter.entity.ProductEntity;
import com.qc.starter.entity.UserEntity;

@Repository
public class DashboardDaoImpl implements DashboardDao {
	private static final Logger logger = Logger.getLogger(DashboardDaoImpl.class.getName());
	@Autowired	SessionFactory sessionFactory;
	@Autowired HttpSession httpSession;

	@Transactional
	public List<DashboardDto> getMyleadListState(String produtId, String userid) {
		logger.info("DashboardDaoImpl || getMyleadListState() || :-START");
		Session session =null;
		List<DashboardDto> list=new ArrayList<DashboardDto>();
		try{
			session = sessionFactory.openSession();
			String sqlStr="SELECT DISTINCT CA.ACTION_STAGE,CA.ACTION_STAGE_WEIGHT,COUNT(C1.CASE_ID) LEADCOUNT " +
					" FROM QC_PROSPECT_MASTER.QM_CASE_ACTION CA,(SELECT C.CASE_ID,C.DISPOSITION_ID " +
					" FROM QC_PROSPECT.QT_CASE C,QC_PROSPECT_MASTER.QM_PRODUCT  CP " +
					" WHERE CP.PRODUCTID = C.PRODUCTID " +
					" AND CP.PARENT_PRODUCTID ="+produtId+" " +
					" AND C.ALLOCATED_TO IN (SELECT USERID FROM QC_USER_AUTH.QM_USER" +
					" WHERE USERID = "+userid+" UNION ALL " +
					" SELECT USERID FROM QC_USER_AUTH.QM_USER " +
					" WHERE SUPERVISORID = "+userid+")) C1 " +
					" WHERE CA.ACTION_ID = C1.DISPOSITION_ID(+) " +
					"AND CA.ACTION_TYPE = 1 GROUP BY CA.ACTION_STAGE,CA.ACTION_STAGE_WEIGHT " +
					"ORDER BY CA.ACTION_STAGE_WEIGHT ASC,LEADCOUNT DESC";
			SQLQuery query=session.createSQLQuery(sqlStr);
			List result = query.list();
			Iterator it =result.iterator();
			while(it.hasNext())
			{
				DashboardDto object=new DashboardDto();	
				Object obj[] = (Object[])it.next();		
				object.setActionStage(obj[0]==null?"":obj[0]+"");
				object.setLeadCount(obj[1]==null?"":obj[1]+"");			
				list.add(object);
			}
		} catch (Exception e) {
			logger.error("DashboardDaoImpl || getMyleadListState() || :-ERROR:::"+e.getMessage());
			e.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
		logger.info("DashboardDaoImpl || getMyleadListState() || :-END");
		return list;
	}

	@Transactional
	public List<UserEntity> getMyTeamList(Integer userid) {
		logger.info("DashboardDaoImpl || getMyTeamList() || :-START");
		Session session =null;
		List<UserEntity> list=new ArrayList<UserEntity>();	
		try {
			session = sessionFactory.openSession();
			String sqlStr = "SELECT USERID,TRIM(REPLACE(QU.USERFNAME||' '||QU.USERMNAME||' '||QU.USERLNAME,'  ',' ')) ," +
					" SUPERVISORID,level FROM QC_PROSPECT_MASTER.QM_USER QU WHERE ACTIVE ='A' " +
					" CONNECT BY PRIOR USERID = SUPERVISORID START WITH USERID ="+userid+" " +
					" ORDER BY LEVEL";
			SQLQuery query=session.createSQLQuery(sqlStr);
			List result = query.list();
			Iterator it =result.iterator();
			while(it.hasNext())
			{	
				UserEntity object=new UserEntity();						
				Object obj[] = (Object[])it.next();
				object.setUserid(obj[0]==null?"":obj[0]+"");
				object.setUserfname(obj[1]==null?"":obj[1]+"");
				object.setLoginname(obj[1]==null?"":obj[1]+"");
				list.add(object);						
			}
		}catch (Exception e) {
			logger.error("DashboardDaoImpl || getMyTeamList() || :-ERROR:::"+e.getMessage());
			e.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
		logger.info("DashboardDaoImpl || getMyTeamList() || :-END");
		return list;
	}

	@Transactional
	public List<ProductEntity> getproductList(String userid) {
		logger.info("DashboardDaoImpl || getproductList() || :-START");
		Session session = null;
		List<ProductEntity> list=new ArrayList<ProductEntity>();
		try {
			session = sessionFactory.openSession();
			String sqlStr="SELECT QCP.PRODUCTID ID,QCP.PRODNAME QUEUE " +
					" FROM QC_PROSPECT_MASTER.QM_PRODUCT  QCP,QC_PROSPECT.QT_CASE_USER_X_PRODUCT QCUP " +
					"WHERE QCP.PRODUCTID = QCUP.PROD_ID AND QCP.PRODTYPEID = 1 AND QCUP.USER_ID ="+userid;
			SQLQuery query=session.createSQLQuery(sqlStr);
			List result = query.list();
			Iterator it =result.iterator();
			while(it.hasNext())
			{
				ProductEntity object=new ProductEntity();	
				Object obj[] = (Object[])it.next();
				object.setProductId(obj[0]==null?0:Integer.parseInt(obj[0]+""));
				object.setProdName(obj[1]==null?"":obj[1]+"");		
				list.add(object);
				object=null;
			}
		}catch(Exception e) {
			logger.error("DashboardDaoImpl || getproductList() || :-Error:::"+e.getMessage());
			e.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
		logger.info("DashboardDaoImpl || getproductList() || :-END");
		return list;
	}

	@Transactional
	public List<DashboardDto> getMyTeamListState(String productId, String userId) {
		logger.info("DashboardDaoImpl || getMyTeamListState() || :-START");
		Session session = null;
		List<DashboardDto> list=new ArrayList<DashboardDto>();
		try {
			session = sessionFactory.openSession();
			String sqlStr="SELECT DISTINCT CA.ACTION_STAGE,COUNT(C1.CASE_ID) LEADCOUNT " +
					" FROM QC_PROSPECT_MASTER.QM_CASE_ACTION CA,(SELECT C.CASE_ID,C.DISPOSITION_ID " +
					" FROM QC_PROSPECT.QT_CASE C,QC_PROSPECT_MASTER.QM_PRODUCT  CP " +
					"WHERE CP.PRODUCTID = C.PRODUCTID AND CP.PARENT_PRODUCTID ="+productId+" " +
					" AND C.ALLOCATED_TO ="+userId+") C1 " +
					" WHERE CA.ACTION_ID = C1.DISPOSITION_ID(+) AND CA.ACTION_TYPE = 1 " +
					"GROUP BY CA.ACTION_STAGE ORDER BY LEADCOUNT DESC";
			SQLQuery query=session.createSQLQuery(sqlStr);
			List result = query.list();
			Iterator it =result.iterator();
			while(it.hasNext())
			{
				DashboardDto object=new DashboardDto();	
				Object obj[] = (Object[])it.next();
				object.setActionStage(obj[0]==null?"":obj[0]+"("+obj[1]+")");			
				list.add(object);
				object=null;
			}
		}catch(Exception e) {
			logger.info("DashboardDaoImpl || getMyTeamListState() || :-ERROR::::"+e.getMessage());
			e.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
		logger.info("DashboardDaoImpl || getMyTeamListState() || :-END");
		return list;
	}

	@Transactional
	public List<DashboardDto> getDayDataForLead(String userId, String date) {
		return null;
	}

	@Transactional
	public List<DashboardDto> getMonthDataForLead(String userId, String date) {
		return null;
	}

	@Transactional
	public List<DashboardDto> getWeekDataForLead(String userId, String date) {
		return null;
	}

	@Transactional
	public List<CampaignEntity> getCompaignList() {
		logger.info("DashboardDaoImpl || getCompaignList() || :-START");
		Session session = null;
		List<CampaignEntity> list=new ArrayList<CampaignEntity>();
		try{
			session = sessionFactory.openSession();
			String sqlStr="select * from QC_PROSPECT_MASTER.Qm_Campaign where active='A' AND " +
					" COMPANY_ID = 1000000001";	
			SQLQuery query=session.createSQLQuery(sqlStr);
			List result = query.list();
			Iterator it =result.iterator();		
			while(it.hasNext())
			{
				CampaignEntity object=new CampaignEntity();	
				Object obj[] = (Object[])it.next();
				object.setCampaignId(obj[0]==null?"":obj[0]+"");
				object.setCampaignName(obj[2]==null?"":obj[2]+"");			
				list.add(object);
				object=null;
			}
		}catch(Exception e){
			logger.error("DashboardDaoImpl || getCompaignList() || :-ERROR:::"+e.getMessage());
			e.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
		logger.info("DashboardDaoImpl || getCompaignList() || :-END");
		return list;
	}

	@Transactional
	public List<DashboardDto> getdashboardData(LeadsSerchDto serchDto) {
		logger.info("DashboardDaoImpl || getdashboardData() || :-START");
		List<DashboardDto> leadList=new ArrayList<DashboardDto>();
		CallableStatement cstmt = null;
		Connection con=null;
		ResultSet rs = null;
		Session session=null;
		try {
			session = sessionFactory.openSession();
			String sqlStr = "";
			con = ConnectionFromSession.getJavaSqlConnectionFromHibernateSession(session);				
			DashboardDto lead = null;
			String s="MYLEAD";
			cstmt = con.prepareCall("{call QC_PROSPECT.PR_GET_WORKLIST_MYWORKLIST(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			logger.info("{call QC_PROSPECT.dbo.PR_GET_WORKLIST_MYWORKLIST(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");		
			logger.info("P_MOBILE"+" type=in value=null");
			cstmt.setString("P_EMAIL", "");
			logger.info("P_EMAIL"+" type=in value=");
			cstmt.setString("P_MOBILE", "");			
			cstmt.setString("P_NAME", "");
			logger.info("P_NAME"+" type=in value=");
			cstmt.setString("P_LEAD_ID", "");
			logger.info("P_LEAD_ID"+" type=in value=");			
			cstmt.setString("P_QUEUE_ID", serchDto.getQueue().equals("")?"":serchDto.getQueue());
			logger.info("P_QUEUE_ID"+" type=in value="+serchDto.getQueue());
			cstmt.setString("P_SUB_QUEUE_ID",serchDto.getSubqueue().equals("")?"":serchDto.getSubqueue());
			logger.info("P_SUB_QUEUE_ID"+" type=in value="+serchDto.getSubqueue());
			cstmt.setString("P_DISPOSITION_STATUS","");
			logger.info("P_DISPOSITION_STATUS"+" type=in value=");			
			cstmt.setString("P_LEAD_STATE", "");
			logger.info("P_LEAD_STATE"+" type=in value=");
			cstmt.setString("P_CAMPAIGN", serchDto.getName().equals("")?"":serchDto.getName());
			logger.info("P_CAMPAIGN"+" type=in value="+serchDto.getCampaign());
			cstmt.setString("P_ALLOCATED_TO", serchDto.getAllocate().equals("")?"":serchDto.getAllocate());
			logger.info("P_ALLOCATED_TO"+" type=in value="+serchDto.getAllocate());
			cstmt.setString("P_AMOUNT_FROM", "");
			logger.info("P_AMOUNT_FROM"+" type=in value=null");
			cstmt.setString("P_AMOUNT_TO","");
			logger.info("P_AMOUNT_TO"+" type=in value=null");			
			logger.info("P_FOLLOW_UP_FROM"+" type=in value="+ CommonUtils.getDateStringinddMMMYYYfromddMMyyyy(serchDto.getFromFollowupDate()));
			cstmt.setString("P_FOLLOW_UP_FROM", serchDto.getFromFollowupDate()==null?"":CommonUtils.getDateStringinddMMMYYYfromddMMyyyy(serchDto.getFromFollowupDate()));
			logger.info("P_FOLLOW_UP_TO"+" type=in value="+ CommonUtils.getDateStringinddMMMYYYfromddMMyyyy(serchDto.getToFollowupDate()));
			cstmt.setString("P_FOLLOW_UP_TO", serchDto.getToFollowupDate()==null?"":CommonUtils.getDateStringinddMMMYYYfromddMMyyyy(serchDto.getToFollowupDate()));						
			cstmt.setString("P_SOURCE_ID", serchDto.getSource().equals("")?"":serchDto.getSource());
			logger.info("P_SOURCE_ID"+" type=in value="+serchDto.getSource());
			cstmt.setString("P_SORT_BY1","");
			logger.info("P_SORT_BY1"+" type=in value=");
			cstmt.setString("P_SORT_BY2","");
			logger.info("P_SORT_BY2"+" type=in value=");
			cstmt.setString("P_SORT_BY3","");
			logger.info("P_SORT_BY3"+" type=in value=");
			cstmt.setString("P_SORT_ORDER","");
			logger.info("P_SORT_ORDER"+" type=in value=");
			cstmt.setNull("P_FROM", java.sql.Types.INTEGER);
			logger.info("P_FROM"+" type=in value=null");
			cstmt.setNull("P_TO", java.sql.Types.INTEGER);
			logger.info("P_TO"+" type=in value=null");
			cstmt.registerOutParameter("P_CNT", OracleTypes.INTEGER);
			logger.info("P_CNT"+" type=out");
			cstmt.setString("P_ESC_REF",s);
			logger.info("P_ESC_REF"+" type=in value="+s);
			cstmt.setString("P_USER_ID", serchDto.getUserId());
			logger.info("P_USER_ID"+" type=in value="+serchDto.getUserId());			
			cstmt.setString("P_SCREEN", serchDto.getRequestType());
			logger.info("P_SCREEN"+" type=in value="+serchDto.getRequestType());
			cstmt.registerOutParameter("P_REF_CUR", OracleTypes.CURSOR);
			logger.info("P_REF_CUR"+" type=out");
			cstmt.registerOutParameter("P_EXEC_STATUS", java.sql.Types.CLOB);
			logger.info("P_EXEC_STATUS"+" type=out");
			cstmt.registerOutParameter("P_MESSAGE", java.sql.Types.CLOB);
			logger.info("P_MESSAGE"+" type=out");
			cstmt.executeUpdate();
			rs = (ResultSet) cstmt.getObject("P_REF_CUR");
			while(rs.next()){
				lead = new DashboardDto();
				String action=rs.getString("ACTION_STAGE")==null?"":rs.getString("ACTION_STAGE");
				String action1=rs.getString("CNT")==null?"":rs.getString("CNT");
				lead.setActionStage(action+"("+action1+")");		
				leadList.add(lead);
				lead=null;
			}
		}
		catch(Exception e) {		
			logger.error("DashboardDaoImpl || getdashboardData() || :-ERROR:::"+e.getMessage());
			e.printStackTrace();
			return null;
		}finally{
			try {
				if(rs!=null){
					rs.close();
				}
				if(cstmt!=null){
					cstmt.close();
					cstmt=null;
				}
				if(con!=null){
					con.close();
					con=null;
				}
				if(session!=null){
					session.close();
				}
			} catch (SQLException e) {
				logger.error("DashboardDaoImpl || getdashboardData() || :-ERROR2:::"+e.getMessage());
				e.printStackTrace();
			}
		}
		logger.info("DashboardDaoImpl || getdashboardData() || :-END");
		return leadList;
	}
}