package com.qc.starter.dao.daoImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.qc.starter.basic.ConnectionFromSession;
import com.qc.starter.dao.XSellDao;
import com.qc.starter.entity.CaseXSellEntity;
@Repository
public class XSellDaoImpl implements XSellDao {
	private static final Logger logger = Logger.getLogger(XSellDaoImpl.class.getName());
	@Autowired SessionFactory sessionFactory;
	  
	@Transactional
	public CaseXSellEntity getXSellDetail(String xSellId){
		logger.info("XSellDaoImpl | getXSellDetail() | :- START:::");
		Session session = sessionFactory.openSession();
		CaseXSellEntity xSellEntity=new CaseXSellEntity();
		try{
			xSellEntity = (CaseXSellEntity)session.createQuery("from CaseXSellEntity where xsellId = :xsellId").setParameter("xsellId", xSellId).list().get(0);
		}catch(Exception e){
			logger.error("XSellDaoImpl | getXSellDetail() | :- ERROR:::"+e.getMessage());
		}finally {
			if(session!=null)
				session.close();
		}
		logger.info("XSellDaoImpl | getXSellDetail() | :- END:::");
		return xSellEntity;
	}

	@Transactional
	public List<CaseXSellEntity> getXSellList(Integer leadid) {
		logger.info("XSellDaoImpl | getXSellList() | :- START:::");
		Session session = sessionFactory.openSession();
		String status = "";
		List<CaseXSellEntity> caseXsellEntityList = new ArrayList<CaseXSellEntity>();
		try{
			String sqlquery = "SELECT  QCX.XSELL_ID,  QCX.IS_XSOLD,  QCX.PARENT_CASE_ID,  QCX.FACILITY_REQ_ID," +
					"  QCX.BANK_ID,TO_CHAR(QCX.AMOUNT,'99,99,99,99,99,999') AMOUNT  ,  QCX.REMARKS,  (select QC.CASE_CODE CASE_CD from " +
					" QC_PROSPECT.QT_CASE QC where QC.CASE_ID = QCX.CASE_ID ) CASE_CD FROM  QC_PROSPECT.QT_CASE_XSELL " +
					"QCX WHERE  QCX.PARENT_CASE_ID = "+leadid;
			SQLQuery query=session.createSQLQuery(sqlquery);
			List result = query.list();
			Iterator itr=result.iterator();
			while(itr.hasNext()){
				Object[] obj=(Object[])itr.next();
				CaseXSellEntity caseXSellEntity = new CaseXSellEntity();
				caseXSellEntity.setXsellId(obj[0]!=null? obj[0]+"":"");
				caseXSellEntity.setIsXsold( obj[1]!=null? obj[1]+"":"");
				caseXSellEntity.setParentCaseId(obj[2]!=null? obj[2]+"":"");
				caseXSellEntity.setFacilityReqId(obj[3]!=null? obj[3]+"":"");
				caseXSellEntity.setBankId(obj[4]!=null? obj[4]+"":"");
				caseXSellEntity.setAmount((obj[5]!=null? obj[5]+"":"").trim());
				caseXSellEntity.setRemarks(obj[6]!=null? obj[6]+"":"");
				caseXSellEntity.setCaseId(obj[7]!=null? obj[7]+"":"");
				caseXsellEntityList.add(caseXSellEntity);
				caseXSellEntity=null;
			}
		}catch(Exception e){
			logger.error("XSellDaoImpl | getXSellList() | :- ERROR:::"+e.getMessage());
		}finally {
			if(session!=null)
				session.close();
		}
		logger.info("XSellDaoImpl | getXSellList() | :- END:::");
		return caseXsellEntityList;
	}

	@Transactional
	public boolean addXSell(CaseXSellEntity caseXSellEntity) {
		logger.info("XSellDaoImpl | addXSell() | :- START:::");
		boolean status = false;
		Session session=null;
		try{
			session = sessionFactory.openSession();
			Query query = session.createSQLQuery("INSERT INTO QC_PROSPECT.QT_CASE_XSELL(XSELL_ID,PARENT_CASE_ID,FACILITY_REQ_ID,BANK_ID,AMOUNT,IS_XSOLD,REMARKS,CREATED_BY,CREATED_SYS_DATE) " +
					"VALUES (QC_PROSPECT.SEQ_CASE_XSELL.NEXTVAL,:parentCaseId,:facilityReqId,:bankId,:amount,:isXSold,:remarks,:createdBy,:createdDate)");
			query.setParameter("parentCaseId", caseXSellEntity.getParentCaseId())
			.setParameter("facilityReqId", caseXSellEntity.getFacilityReqId())
			.setParameter("bankId", caseXSellEntity.getBankId())
			.setParameter("amount", caseXSellEntity.getAmount())
			.setParameter("remarks", caseXSellEntity.getRemarks())
			.setParameter("createdBy", caseXSellEntity.getCreatedBy())
			.setCharacter("isXSold", 'N')
			.setDate("createdDate", caseXSellEntity.getCreatedSysDate()!=null?caseXSellEntity.getCreatedSysDate():null);
			if(query.executeUpdate()>0)
				return true;	
		}catch(Exception e){
			logger.error("XSellDaoImpl | addXSell() | :- ERROR:::"+e.getMessage());
		}finally {
			if(session!=null)
				session.close();
		}
		logger.info("XSellDaoImpl | addXSell() | :- END:::");
		return status;

	}

	@Transactional
	public boolean deleteXSell(List<CaseXSellEntity> list) {
		logger.info("XSellDaoImpl | deleteXSell() | :- START:::");
		boolean flag = false;
		Session session=null;
		try{
			session = sessionFactory.openSession();
			Query query = session.createQuery("delete from CaseXSellEntity where xsellId=:xsellId");
			for(CaseXSellEntity xSellEntity : list){
				query.setParameter("xsellId", xSellEntity.getXsellId())
				.executeUpdate();
			}
			return true;		
		}catch(Exception e){
			logger.error("XSellDaoImpl | deleteXSell() | :- ERROR:::"+e.getMessage());
		}finally {
			if(session!=null)
				session.close();
		}
		logger.info("XSellDaoImpl | deleteXSell() | :- END:::");
		return flag;
	}

	@Transactional
	public String updateXSell(List<CaseXSellEntity> list) {
		logger.info("XSellDaoImpl | updateXSell() | :- START:::");
		String updateStatus="";
		Session session = sessionFactory.openSession();
		try{
			Query query = session.createQuery("update CaseXSellEntity set facilityReqId = :facilityReqId, bankId = :bankId, amount=:amount, remarks=:remarks,updatedDate=:updatedDate,updatedSysDate=:updatedDate where xsellId=:xsellId ");
			for(CaseXSellEntity xSellEntity : list){
				String amount=xSellEntity.getAmount();
				if(amount.contains(",")){
					amount=amount.replaceAll(",", "");
				}
				query.setParameter("facilityReqId", xSellEntity.getFacilityReqId())
				.setParameter("bankId",xSellEntity.getBankId())
				.setParameter("amount",amount!=null?amount:"0")
				.setParameter("remarks", xSellEntity.getRemarks()!=null?xSellEntity.getRemarks():"")
				.setParameter("updatedDate", new Date())
				.setParameter("xsellId", xSellEntity.getXsellId()!=null?xSellEntity.getXsellId():"");
				
				
				updateStatus=updateStatus + query.executeUpdate()+"~";
			}
		}catch(Exception e){
			logger.error("XSellDaoImpl | updateXSell() | :- error:::"+e.getMessage());
			return null;
		}finally {
			if(session!=null)
				session.close();
		}
		logger.info("XSellDaoImpl | updateXSell() | :- END:::");
		return updateStatus;
	}

	@Transactional
	public boolean convertXLead(String param) {
		logger.info("XSellDaoImpl | convertXLead() | :- START:::");
		Connection con  = null;
		CallableStatement cstmt = null;
		Session session = sessionFactory.openSession();
		try{
			con = ConnectionFromSession.getJavaSqlConnectionFromHibernateSession(session);
			String leadid = "";
			String error = "";
			String message ="";
			cstmt  = con.prepareCall("{call QC_PROSPECT.PR_XSELL(?,?,?,?)}");
			cstmt.setString(1,param);
			cstmt.registerOutParameter(2, java.sql.Types.NUMERIC);
			cstmt.registerOutParameter(3, java.sql.Types.CLOB);
			cstmt.registerOutParameter(4, java.sql.Types.CLOB);
			cstmt.executeUpdate();		
			leadid = cstmt.getString(2);
			error = cstmt.getString(3);
			message = cstmt.getString(4);
		}catch(Exception e){
			logger.error("XSellDaoImpl | convertXLead() | :- error:::"+e.getMessage());
			e.printStackTrace();
			return false;
		}finally{
			try{
				if(session!=null)
					session.close();
				cstmt.close();
				con.close();
			}catch(Exception e){
				logger.info("XSellDaoImpl | convertXLead() | :- error:::"+e.getMessage());
				e.printStackTrace();
			}
		}
		logger.info("XSellDaoImpl | convertXLead() | :- end:::");
		return true;
	}

	@Transactional
	public String insertXSell(List<CaseXSellEntity> list) {
		logger.info("XSellDaoImpl | insertXSell() | :- START:::");
		String insertStatus="";
		Session session = null;
		try{
			String insert = "INSERT INTO QC_PROSPECT.QT_CASE_XSELL(XSELL_ID,PARENT_CASE_ID,FACILITY_REQ_ID,BANK_ID,AMOUNT,IS_XSOLD,REMARKS,CREATED_BY,CREATED_SYS_DATE)  " +
					" VALUES (QC_PROSPECT.SEQ_CASE_XSELL.NEXTVAL,?,?,?,?,?,?,?,?) ";
			session = sessionFactory.openSession();
			Query query = session.createSQLQuery("INSERT INTO QC_PROSPECT.QT_CASE_XSELL(XSELL_ID,PARENT_CASE_ID,FACILITY_REQ_ID,BANK_ID,AMOUNT,IS_XSOLD,REMARKS,CREATED_BY,CREATED_SYS_DATE) " +
					"VALUES (QC_PROSPECT.SEQ_CASE_XSELL.NEXTVAL,:parentCaseId,:facilityReqId,:bankId,:amount,:isXSold,:remarks,:createdBy,:createdDate)");			
			for(CaseXSellEntity xSellEntity : list){
				query.setParameter("parentCaseId", xSellEntity.getParentCaseId())
				.setParameter("facilityReqId", xSellEntity.getFacilityReqId())
				.setParameter("bankId", xSellEntity.getBankId())
				.setParameter("amount", xSellEntity.getAmount())
				.setParameter("remarks", xSellEntity.getRemarks())
				.setParameter("createdBy", xSellEntity.getCreatedBy())
				.setCharacter("isXSold", 'N')
				.setDate("createdDate",new Date());
				insertStatus=insertStatus+query.executeUpdate()+"~";
			}
		}catch(Exception e){
			logger.info("XSellDaoImpl | insertXSell() | :- ERROR:::"+e.getMessage());
			return null;
		}finally {
			if(session!=null)
				session.close();
		}
		logger.info("XSellDaoImpl | insertXSell() | :- END:::");
		return insertStatus;
	}
}
