package com.qc.starter.dao.daoImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.qc.starter.basic.ConnectionFromSession;
import com.qc.starter.dao.UserDao;
import com.qc.starter.entity.UserEntity;

@Repository
public class UserDaoImpl implements UserDao{
	private static final Logger logger = Logger.getLogger(UserDaoImpl.class.getName());
	@Autowired SessionFactory sessionFactory;

	@Transactional
	public String getUser(){
		logger.info("UserDaoImpl | getUser() | :- START ");
		List list=null;
		Session session=null;
		try{
			session  = sessionFactory.openSession();
			Query query = session.createQuery("From UserEntity user");
			list = query.list();
		}catch(Exception e){
			logger.error("UserDaoImpl | getUser() | :- ERROR:::"+e.getMessage());
			e.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
		logger.info("UserDaoImpl | getUser() | :- END");
		return "Total column in user table = "+list.size();
	}

	@Transactional
	public void logOutExit(UserEntity userEntity,String ipAddress) {
		logger.info("UserDaoImpl | logOutExit() | :- START ");
		Connection con=null;
		CallableStatement cstmt = null;
		Session session=null;
		try{
			session = sessionFactory.openSession();		
			con = ConnectionFromSession.getJavaSqlConnectionFromHibernateSession(session);				
			cstmt=con.prepareCall("{call QC_USER_AUTH.PKG_AUTH.PR_LOGOUTUSER(?,?,?, ?,?,?)}");
			//VAPT Change
			cstmt.setString(1, userEntity.getLogin1()+userEntity.getLogin2());
			cstmt.setString(2, ipAddress);
			cstmt.registerOutParameter(3, java.sql.Types.VARCHAR);
			cstmt.registerOutParameter(4, java.sql.Types.VARCHAR);
			cstmt.registerOutParameter(5, java.sql.Types.VARCHAR);
			cstmt.setString(6,"");
			cstmt.registerOutParameter(6, java.sql.Types.VARCHAR);
			cstmt.executeUpdate();			
		}catch(Exception e){
			logger.error("UserDaoImpl | logOutExit() | :- ERROR:::"+e.getMessage());
			e.printStackTrace();
		}finally{
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
				logger.error("UserDaoImpl | logOutExit() | :- ERROR:::"+e2.getMessage());
				e2.printStackTrace();
			} 
		}
	}
}
