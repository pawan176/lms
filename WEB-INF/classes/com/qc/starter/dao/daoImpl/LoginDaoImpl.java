package com.qc.starter.dao.daoImpl;

import java.io.StringReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import oracle.jdbc.driver.OracleTypes;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.xml.sax.InputSource;

import com.qc.starter.basic.ConnectionFromSession;
import com.qc.starter.basic.XmlSaxparshingHandler;
import com.qc.starter.dao.LoginDao;
import com.qc.starter.dto.AuthorizedProcDto;
import com.qc.starter.dto.PasswordChangeDto;
import com.qc.starter.dto.UserDto;
import com.qc.starter.entity.UserEntity;

@Repository
public class LoginDaoImpl implements LoginDao {
	private static final Logger logger = Logger.getLogger(LoginDaoImpl.class.getName());
	@Autowired	SessionFactory sessionFactory;

	@Transactional
	public UserDto getDetails(String username) {
		logger.info("LoginDaoImpl | getDetails() | :- START");
		UserDto userDto = null;
		Session session = null;
		try{
			session = sessionFactory.openSession();
			Query query=session.createQuery("from UserEntity where loginName = :loginName")
					.setParameter("loginName",username);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List list = query.list();
			for(Object o : list){	
				Map rt = (Map) o;
				userDto.setUserName(rt.get("LOGINNAME")+"");
				userDto.setPassword(rt.get("PASSWORD")+"");
			}
		}
		catch(Exception e){
			logger.error("Error in catch block due to ::: --> "+ e.getMessage());
			e.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
		logger.info("LoginDaoImpl | getDetails() | :- END");
		return userDto;
	}
	@Transactional
	public UserEntity userLogin(UserDto user) {
		logger.info("LoginDaoImpl | userLogin() | :- START :: Request param UserDto ::"+user);
		UserEntity userEntity=null;
		ResultSet rt=null;
		Session session = null;
		try{
			session = sessionFactory.openSession();
			//String sqlstr = "SELECT * from QC_USER_AUTH.QM_USER where LOGINNAME = upper('"+user.getUserName()+"')";
			String hql = "from UserEntity where loginName = '"+user.getUserName().toUpperCase()+"'";
			Query query = session.createQuery(hql);
			List userList = query.list();
			if (userList.size() > 0) {
				userEntity = (UserEntity) userList.get(0);
				userEntity.setCompanyId("1000000001");
			}
		}catch(Exception e){
			logger.error("Error in catch block due to ::: --> "+ e.getMessage());
			e.printStackTrace();	
		}finally {
			if(session!=null)
				session.close();
		}
		if (userEntity!=null) {
			logger.info("LoginDaoImpl | userLogin() | :- END :: Response UserEntity:: null"+userEntity);
			return userEntity;
		} else {
			logger.info("LoginDaoImpl | userLogin() | :- END :: Response :: null");
			return null;
		}
	}


	public String changePassword(UserEntity userEntity,PasswordChangeDto passwordChangeDto){
		logger.info("LoginDaoImpl | changePassword() | :- START");
		logger.info("LoginDaoImpl | userAthentication() | :- START");
		Session session = sessionFactory.openSession();		
		Connection con = null;				
		CallableStatement cstmt = null;
		String message = "";
		String status = "";
		
		try {
			con = ConnectionFromSession.getJavaSqlConnectionFromHibernateSession(session);
			cstmt=con.prepareCall("{call QC_USER_AUTH.PKG_AUTH.PR_UPDATE_PASSWORD(?,?,?, ?,?)}");
			logger.info("{call QC_USER_AUTH.PKG_AUTH.PR_UPDATE_PASSWORD(?,?,?, ?,?,)}");
			cstmt.setInt(1,userEntity.getUserid());
			logger.info("1 type=in value="+userEntity.getUserid());
			cstmt.setString(2,userEntity.getPassword());
			logger.info("2 type=in value="+userEntity.getPassword());
			cstmt.setString(3,passwordChangeDto.getEncconfirmnewpwd());
			logger.info("3 type=in value="+passwordChangeDto.getEncconfirmnewpwd());
			/*cstmt.setString(3,passwordChangeDto.getConfirmnewpwd());		*/	
			cstmt.registerOutParameter(4, java.sql.Types.VARCHAR);
			logger.info("4 type=out");
			cstmt.registerOutParameter(5, java.sql.Types.VARCHAR);
			logger.info("5 type=out");
			/*cstmt.setString(6,passwordChangeDto.getEncconfirmnewpwd());	*/	
			//cstmt.setString(6,passwordChangeDto.getConfirmnewpwd());
			cstmt.executeUpdate();			
			message = cstmt.getString(4);
			logger.info("message="+message);
			status = cstmt.getString(5);			

		} catch (SQLException e) {	
			logger.error("Error in catch block due to ::: --> "+ e.getMessage());
			e.printStackTrace();
		}
		finally{
			try{
				if(session!=null){
					session.close();
				}
				if(cstmt!=null){
					cstmt.close();
					cstmt=null;
				}
				if(con!=null){
					con.close();
					con=null;
				}
			}catch(Exception e){
				logger.error("Error in catch block due to ::: --> "+ e.getMessage());
				e.printStackTrace();
			}
		}
		logger.info("LoginDaoImpl | changePassword() | :- End :: Respose AuthorizedProcDto ::");
		return message+"~"+status;
	}


	public AuthorizedProcDto userAthentication(UserDto user) {
		logger.info("LoginDaoImpl | userAthentication() | :- START");
		Session session = sessionFactory.openSession();		
		Connection con = null;		
		CallableStatement cstmt = null;
		ResultSet rs=null;
		String leadid = "";
		String error = "";
		String message1 ="",message2="",message3="";
		AuthorizedProcDto authorizeDto=new AuthorizedProcDto();
		try {
			con = ConnectionFromSession.getJavaSqlConnectionFromHibernateSession(session);
			cstmt=con.prepareCall("{call QC_USER_AUTH.PKG_AUTH.PR_VALIDATEUSER(?,?,?,?,?,?,?,?,?)}");
			cstmt.setString(1, user.getUserName());
			cstmt.setString(2, user.getPassword());
			cstmt.setString(3, user.getIpaddress());
			cstmt.registerOutParameter(4, java.sql.Types.VARCHAR);
			cstmt.registerOutParameter(5, OracleTypes.CURSOR);
			cstmt.registerOutParameter(6, java.sql.Types.VARCHAR);
			cstmt.registerOutParameter(7, java.sql.Types.VARCHAR);
			cstmt.setNull(8,java.sql.Types.VARCHAR);
			cstmt.registerOutParameter(8, java.sql.Types.VARCHAR);
			cstmt.setNull(9, java.sql.Types.VARCHAR);
			cstmt.executeUpdate();			
			authorizeDto.setAuthStatus(cstmt.getString(4));
			authorizeDto.setUserInfo(cstmt.getString(5));
			authorizeDto.setOraError(cstmt.getString(6));	
			authorizeDto.setAppError(cstmt.getString(7));
			authorizeDto.setExecutionStatus(cstmt.getString(8));
			rs=(ResultSet)cstmt.getObject(5);
		} catch (SQLException e) {	
			logger.error("Error in catch block due to ::: --> "+ e.getMessage());
			e.printStackTrace();
		}
		finally{
			try{
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
			}catch(Exception e){
				logger.error("Error in catch block due to ::: --> "+ e.getMessage());
				e.printStackTrace();
			}
		}
		logger.info("LoginDaoImpl | userAthentication() | :- End :: Respose AuthorizedProcDto ::"+authorizeDto);
		return authorizeDto;
	}
	@Transactional
	public Map<String,String> getMenuItems(String userId) {
		logger.info("LoginDaoImpl | getMenuItems() | :- START : With request Param:: userId ::"+userId);
		Map<String,String> menuList=new LinkedHashMap<String,String>();
		Connection con = null;		
		CallableStatement cstmt = null;
		Session session = null;		
		try {
			session = sessionFactory.openSession();
			con = ConnectionFromSession.getJavaSqlConnectionFromHibernateSession(session);
			cstmt=con.prepareCall("{call  QC_USER_AUTH.PKG_APP_CONFIG.pr_get_vertical_menu(?,?,?,?)}");
			cstmt.setString("P_USER_INFO", "<INPUT_PARAM><USER_ID>"+userId+"</USER_ID></INPUT_PARAM>");
			cstmt.registerOutParameter("P_ACTION_DETAILS",oracle.jdbc.OracleTypes.CLOB);
			cstmt.registerOutParameter("P_XML_STATUS",oracle.jdbc.OracleTypes.CLOB);
			cstmt.setString("P_AD_REQUIRED", "N");
			cstmt.executeUpdate();
			String smldata=cstmt.getString("P_ACTION_DETAILS");
			//---------sax Parser-------------------
			SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
			SAXParser saxParser = saxParserFactory.newSAXParser();
			XmlSaxparshingHandler handler = new XmlSaxparshingHandler();
			saxParser.parse(new InputSource(new StringReader(smldata)), handler);
			menuList = handler.getMenuList();
		} catch (Exception e) {
			logger.error("Error in catch block due to ::: --> "+ e.getMessage());
			e.printStackTrace();
		}
		finally{
			try {
				if(session!=null){
					session.close();
				}
				if(cstmt!=null){
					cstmt.close();
					cstmt=null;
				}
				if(con!=null){
					con.close();
					con=null;
				}
			} catch (Exception e2) {
				logger.error("Error in catch block due to ::: --> "+ e2.getMessage());
				e2.printStackTrace();
			}
		}
		logger.info("LoginDaoImpl | getMenuItems() | :- END :: Response List<String> :::"+menuList);
		return menuList;
	}

	@Transactional
	public int countNotification(String useId, String companyId) {
		int notificationCount=0;
		Session session = sessionFactory.openSession();
		try{
			
			String queryStr = "SELECT COUNT(1) FROM QC_PROSPECT.QT_CASE QC, QC_PROSPECT.QT_CASE_NOTIFICATION CN, QC_PROSPECT.QT_CASE_X_USER CXU, "
					+ "QC_PROSPECT.QT_PERSONAL_DETAILS PD WHERE QC.CASE_ID = CN.CASE_ID AND QC.CASE_ID = CXU.CASE_ID AND PD.CASE_ID = QC.CASE_ID "
					+ "AND QC.COMPANY_ID = "+companyId+" AND CN.DISMISS_FLAG = 'N' AND CN.ACTIVE = 1 AND CXU.ALLOCATED_TO = "+useId+" AND "
					+ "CXU.ALLOCATED_END_DATE IS NULL AND TRUNC(CN.FOLLOWUP_DATETIME) >= (TRUNC(SYSDATE) - 1 ) AND TRUNC(CN.FOLLOWUP_DATETIME) <= (TRUNC(SYSDATE) + 1 )";
			
			/*String queryStr = "SELECT COUNT(1) FROM QC_PROSPECT.QT_CASE QC, QC_PROSPECT.QT_CASE_NOTIFICATION CN, QC_PROSPECT.QT_CASE_X_USER "
					+ "  CXU WHERE QC.CASE_ID = CN.CASE_ID  AND QC.CASE_ID = CXU.CASE_ID  AND QC.COMPANY_ID = "+companyId+"  AND CN.DISMISS_FLAG = 'N' "
					+ " AND CN.ACTIVE = 1  AND CXU.ALLOCATED_TO = "+useId+"  AND CXU.ALLOCATED_END_DATE IS NULL  AND CN.FOLLOWUP_DATETIME < (TRUNC(SYSDATE) + 1)";*/
			/*String queryStr="SELECT COUNT(1) FROM  QC_PROSPECT.QT_CASE QC, QC_PROSPECT.QT_CASE_NOTIFICATION  CN," +
					" QC_PROSPECT.QT_CASE_X_USER CXU WHERE QC.CASE_ID     = CN.CASE_ID AND   QC.ACTION_ID   = CN.ACTION_ID"
					+" AND   QC.CASE_ID     = CXU.CASE_ID AND   QC.COMPANY_ID  ="+companyId+""
					+" AND CN.DISMISS_FLAG  = 'N' AND   CN.ACTIVE     =1 AND   QC.ACTION_ID IN (SELECT ACTION_ID " +
					" FROM QC_PROSPECT_MASTER.QM_CASE_ACTION WHERE ACTION_NAME IN " +
					" ('CALL_BACK','FOLLOW_UP','APPOINTMENT_FIXED','DOCUMENT_PICKED') AND ACTIVE='A' AND COMPANY_ID ="+companyId+") "
					+" AND CXU.ALLOCATED_TO =  "+useId+" AND CXU.ALLOCATED_END_DATE IS NULL "
					+" AND CN.FOLLOWUP_DATETIME < (TRUNC(SYSDATE)+1)";*/
			SQLQuery  query = session.createSQLQuery(queryStr);
			List countList = query.list();
			if(countList.size()>0){
				notificationCount=Integer.valueOf(countList.get(0)+"");
			}
		}catch(Exception e){
			logger.error("Error in catch block due to ::: --> "+ e.getMessage());
			e.printStackTrace();
		}	finally {
			if(session!=null)
				session.close();
		}	
		return notificationCount;
	}
	
	
	
	
	
	
	
}

