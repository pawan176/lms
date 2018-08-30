package com.qc.starter.dao.daoImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import com.qc.starter.dao.EmailDao;
import com.qc.starter.entity.EmailEntity;

@Repository
public class EmailDaoImpl implements EmailDao {
	private static final Logger logger = Logger.getLogger(EmailDaoImpl.class.getName());
	@Autowired SessionFactory sessionFactory;
	@Autowired HttpSession httpSession;

	@Transactional
	public List<EmailEntity> getListOfEmail(Integer caseId) {
		logger.info("EmailDaoImpl | getListOfEmail() | START ");
		Session session =null;
		SQLQuery query =null;
		List list =null;
		List<EmailEntity> listEmail = new ArrayList<EmailEntity>();
		try{
			session = sessionFactory.openSession();
			String sql = "SELECT * FROM QC_PROSPECT.QT_CASE_EMAIL WHERE CASE_ID = "+caseId.toString();
			query = session.createSQLQuery(sql);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			list = query.list();
			for (Object o : list) {
				Map map = (Map) o;
				EmailEntity emailEntity = new EmailEntity();
				emailEntity.setCaseEmailId(map.get("CASE_EMAIL_ID")!=null?map.get("CASE_EMAIL_ID").toString():"");
				emailEntity.setEmailContactTypeId(map.get("CONTACT_TYPE_ID")!=null?map.get("CONTACT_TYPE_ID").toString():"");
				emailEntity.setEmail(map.get("EMAIL")!=null?map.get("EMAIL").toString():"");
				emailEntity.setPrimaryEmail(map.get("PRIMARY_EMAIL")!=null?map.get("PRIMARY_EMAIL").toString():"");
				listEmail.add(emailEntity);
			}
		}catch(Exception e){
			logger.error("EmailDaoImpl || getListOfEmail() || "+e.getMessage()+" || :-END");
			e.printStackTrace();
			return null;
		}finally {
			if(session!=null)
				session.close();
		}
		logger.info("EmailDaoImpl || getListOfEmail()  :-END");
		return listEmail;
	}

	@Transactional
	public void addEmail(EmailEntity emailEntity) {/*
		Session session = sessionFactory.openSession();
		UserEntity userEntity = (UserEntity)httpSession.getAttribute("UserDetails");

		SQLQuery query = session
				.createSQLQuery("INSERT INTO QC_PROSPECT.QT_CASE_EMAIL"
						+ "(CASE_EMAIL_ID,CASE_ID,CONTACT_TYPE_ID,EMAIL,PRIMARY_EMAIL,CREATED_BY,CREATED_DATE,CREATED_SYS_DATE)"
						+ " VALUES (QC_PROSPECT.SEQ_EMAIL.NEXTVAL,:CASE_ID,:CONTACT_TYPE_ID,:EMAIL,:PRIMARY_EMAIL,:CREATED_BY,:CREATED_DATE,:CREATED_SYS_DATE)");
		query.setParameter("CASE_ID", 	emailEntity.getCaseId())
				.setParameter("CONTACT_TYPE_ID", emailEntity.getEmailContactTypeId())
				.setParameter("EMAIL", emailEntity.getEmail())
				.setParameter("PRIMARY_EMAIL", "N")
				.setParameter("CREATED_BY", userEntity.getUserid())
				.setDate("CREATED_DATE", new Date())
				.setDate("CREATED_SYS_DATE", new Date());
		try{
			query.executeUpdate();
		}catch(HibernateException e){
			e.printStackTrace();
		}
	 */}

	@Transactional
	public void addDefaultEmail(EmailEntity emailEntity) {
	}

	@Transactional
	public void deleteEmail(List<String> toDelete) {
		logger.info("EmailDaoImpl || deleteEmail() || START");
		Session session = sessionFactory.openSession();
		Query query =null;
		try{
			session = sessionFactory.openSession();
			query = session.createQuery("delete from EmailEntity where caseEmailId = :caseEmailId");
			for (String caseEmailId : toDelete) {
				query.setParameter("caseEmailId",caseEmailId).executeUpdate();
			}
		}
		catch(Exception e){
			logger.error("EmailDaoImpl || deleteEmail() || "+e.getMessage()+" || :-END");
			e.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
		logger.info("EmailDaoImpl || deleteEmail() || END");
	}

	@Transactional
	public void updateEmailList(List<EmailEntity> toUpdate) {
		logger.info("EmailDaoImpl | updateEmailList() | :-START");
		Session session = null;
		Query query=null;
		try{
			session = sessionFactory.openSession();
			String sql = "update EmailEntity SET emailContactTypeId = :emailContactTypeId, email = :email, primaryEmail=:primaryEmail" +
					",updatedBy=:updatedBy, updatedDate=:updatedDate, updatedSysDate=:updatedSysDate WHERE caseEmailId=:caseEmailId";
			query = session.createQuery(sql);
			for(EmailEntity entity : toUpdate){
				query.setParameter("emailContactTypeId", entity.getEmailContactTypeId().equalsIgnoreCase("-1")?"":entity.getEmailContactTypeId())
				.setParameter("email", entity.getEmail()==null?"":entity.getEmail())
				.setParameter("primaryEmail",entity.getPrimaryEmail())
				.setParameter("updatedBy",entity.getUpdatedBy())
				.setParameter("updatedDate",new Date())
				.setParameter("updatedSysDate",new Date())
				.setParameter("caseEmailId", entity.getCaseEmailId());
				query.executeUpdate();
			}	
		}catch(Exception e){
			e.printStackTrace();
			logger.error("EmailDaoImpl || updateEmailList() || "+e.getMessage()+" || :-END");
		}finally {
			if(session!=null)
				session.close();
		}
		logger.info("EmailDaoImpl | updateEmailList() | :-END");
	}

	@Transactional
	public void addEmailList(List<EmailEntity> toInsert) {
		logger.info("EmailDaoImpl | addEmailList() | :-START");
		Session session = null;
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MMM-yyyy");
		String today=sdf.format(new java.sql.Date(new Date().getTime()));
		SQLQuery query=null;
		try{
			session = sessionFactory.openSession();
			for(EmailEntity entity : toInsert){
				String sql = " INSERT INTO QC_PROSPECT.QT_CASE_EMAIL"
						+ " (CASE_EMAIL_ID,CASE_ID,CONTACT_TYPE_ID,EMAIL,PRIMARY_EMAIL,CREATED_BY,CREATED_DATE,CREATED_SYS_DATE)"
						+ " VALUES (QC_PROSPECT.SEQ_EMAIL.NEXTVAL,"+entity.getCaseId()+","+entity.getEmailContactTypeId()+"," 
						+ "'"+entity.getEmail().trim()+"','"+entity.getPrimaryEmail().trim()+"',"+entity.getCreatedBy()+"," 
						+ "'"+today+"','"+today+"')";
				query = session.createSQLQuery(sql);
				query.executeUpdate();			
			}
		}
		catch(Exception e){
			logger.error("EmailDaoImpl || addEmailList() || errro:::"+e.getMessage());
			e.printStackTrace();			
		}finally {
			if(session!=null)
				session.close();
		}
		logger.info("EmailDaoImpl | addEmailList() | :-END");
	}

}
