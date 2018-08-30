package com.qc.starter.dao.daoImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.qc.starter.basic.ConnectionFromSession;
import com.qc.starter.dao.NewLeadDao;

@Repository
public class NewLeadDaoImpl implements NewLeadDao{
	private static final Logger logger = Logger.getLogger(NewLeadDaoImpl.class.getName());
	@Autowired
	SessionFactory sessionFactory;

	@Transactional
	public String getLeadId(){
		return "1";
	}

	@Transactional
	public String createLead(String param){
		logger.info("NewLeadController | createLead() | :- START param "+param);
		Session session = sessionFactory.openSession();		
		Connection con = null;
		CallableStatement cstmt = null;
		String leadid = "";
		String error = "";
		String message ="";
		String leadCode="";
		String addressIds="";
		try {
			con = ConnectionFromSession.getJavaSqlConnectionFromHibernateSession(session);
			cstmt  = con.prepareCall("{call QC_PROSPECT.PR_LEAD_GENERATE(?,?,?,?,?)}");
			logger.info("{call QC_PROSPECT.PR_LEAD_GENERATE(?,?,?,?)}");
			cstmt.setString(1,param);
			logger.info("1 type=in value="+param);
			cstmt.registerOutParameter(2, java.sql.Types.CLOB);
			logger.info("2 type=out");
			cstmt.registerOutParameter(3, java.sql.Types.CLOB);
			logger.info("3 type=out");
			cstmt.registerOutParameter(4, java.sql.Types.CLOB);
			logger.info("4 type=out");
			cstmt.registerOutParameter(5, java.sql.Types.VARCHAR);
			logger.info("4 type=out");
			cstmt.executeUpdate();			
			leadCode = cstmt.getString(2);
			error = cstmt.getString(3);
			message = cstmt.getString(4);
			addressIds = cstmt.getString(5);
			
			leadid=(leadCode!=null?leadCode.replaceAll("PR","10"):"");
		} catch (SQLException e) {		
			logger.error("NewLeadController | createLead() | :- ERROR::::"+e.getMessage());
			e.printStackTrace();
		}finally{
			try{
				if(session!=null){
					session.flush();
					session.close();
				}
				cstmt.close();
				if(con!=null){
					con.commit();
				    con.close();
				}
			}catch(Exception e){
				logger.error("NewLeadController | createLead() | :- ERROR::::"+e.getMessage());
				e.printStackTrace();
			}
		}
		logger.info("NewLeadController | createLead() | :- END::::");
		//return "#"+leadid+"#"+error+"#"+message+"#";
		return "#"+leadCode+"#"+error+"#"+message+"#"+leadid+"#"+addressIds;
	}	
}
