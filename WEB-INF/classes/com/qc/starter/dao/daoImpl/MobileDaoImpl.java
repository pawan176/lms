package com.qc.starter.dao.daoImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.qc.starter.dao.MobileDao;
import com.qc.starter.entity.MobileEntity;

@Repository
public class MobileDaoImpl implements MobileDao {
	private static final Logger logger = Logger.getLogger(MobileDaoImpl.class.getName());
	@Autowired
	SessionFactory sessionFactory;
	@Autowired
	HttpSession httpSession;

	@SuppressWarnings("unchecked")
	@Transactional
	public List<MobileEntity> getListOfMobile(int personalDetailId) {
		logger.info("MobileDaoImpl || getListOfMobile() || :-START");
		List<MobileEntity> list = new ArrayList<MobileEntity>();
		Session session = sessionFactory.openSession();
		try {
			list = (List<MobileEntity>) session.createQuery("from MobileEntity where caseId = :caseId")
					.setParameter("caseId", new Integer(personalDetailId).toString()).list();
		} catch (Exception e) {
			logger.error("MobileDaoImpl || getListOfMobile() || " + e.getMessage() + " || :-END");
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		logger.info("MobileDaoImpl || getListOfMobile() || :-END");
		return list;
	}

	@Transactional
	public void addDefaultMobile(MobileEntity mobileEntity) {
		/*
		 * Session session = sessionFactory.openSession(); UserEntity userEntity
		 * = (UserEntity) httpSession .getAttribute("UserDetails"); Query query
		 * = session .createQuery(
		 * "update MobileEntity set primaryContact = :primaryContact where caseId = :caseId"
		 * ) .setParameter("primaryContact", "N") .setParameter("caseId",
		 * mobileEntity.getCaseId()); query.executeUpdate(); SQLQuery query2 =
		 * session .createSQLQuery("INSERT INTO QC_PROSPECT.QT_CASE_MOBILE " +
		 * "(CASE_MOBILE_ID, CASE_ID, CONTACT_TYPE_ID, CONTACT_NO, PRIMARY_CONTACT, DND_FLAG, CREATED_BY, CREATED_DATE, CREATED_SYS_DATE) "
		 * +
		 * "VALUES (QC_PROSPECT.SEQ_MOBILE.NEXTVAL,:caseId,:contactTypeId,:contactNo,:primaryContact,:dnd,:createdBy,:createdDate,:createdSysDate)"
		 * ); query2.setParameter("caseId", mobileEntity.getCaseId())
		 * .setParameter("contactTypeId", mobileEntity.getMobileContactTypeId())
		 * .setParameter("contactNo", mobileEntity.getContactNo())
		 * .setParameter("primaryContact", "Y") .setParameter("dnd", "N")
		 * .setParameter("createdBy", userEntity.getUserid())
		 * .setDate("createdDate", new Date()) .setDate("createdSysDate", new
		 * Date());
		 * 
		 * 
		 * try{ query2.executeUpdate(); }catch(HibernateException e){
		 * e.printStackTrace(); }
		 */}

	@Transactional
	public void addMobile(MobileEntity mobileEntity) {
		/*
		 * Session session = sessionFactory.openSession(); UserEntity userEntity
		 * = (UserEntity) httpSession .getAttribute("UserDetails"); SQLQuery
		 * query2 = session
		 * .createSQLQuery("INSERT INTO QC_PROSPECT.QT_CASE_MOBILE " +
		 * "(CASE_MOBILE_ID, CASE_ID, CONTACT_TYPE_ID, CONTACT_NO, PRIMARY_CONTACT, DND_FLAG, CREATED_BY, CREATED_DATE, CREATED_SYS_DATE) "
		 * +
		 * "VALUES (QC_PROSPECT.SEQ_MOBILE.NEXTVAL,:caseId,:contactTypeId,:contactNo,:primaryContact,:dnd,:createdBy,:createdDate,:createdSysDate)"
		 * ); query2.setParameter("caseId", mobileEntity.getCaseId())
		 * .setParameter("contactTypeId", mobileEntity.getMobileContactTypeId())
		 * .setParameter("contactNo", mobileEntity.getContactNo())
		 * .setParameter("primaryContact", "N") .setParameter("dnd", "N")
		 * .setParameter("createdBy", userEntity.getUserid())
		 * .setDate("createdDate", new Date()) .setDate("createdSysDate", new
		 * Date());
		 * 
		 * 
		 * try{ query2.executeUpdate(); }catch(HibernateException e){
		 * e.printStackTrace(); }
		 * 
		 */}

	@Transactional
	public void deleteMobile(List<String> toDelete) {
		logger.info("MobileDaoImpl || deleteMobile || :-START");
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Query query = session.createQuery("delete from MobileEntity where caseMobileId = :caseMobileId");
			for (String caseMobileId : toDelete) {
				query.setParameter("caseMobileId", caseMobileId).executeUpdate();
			}
		} catch (Exception e) {
			logger.error("MobileDaoImpl || deleteMobile() || " + e.getMessage() + " || :-END");
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		logger.info("MobileDaoImpl || deleteMobile || :-END");
	}

	@Transactional
	public void updateMobile(List<MobileEntity> toUpdate) {
		logger.info("MobileDaoImpl || updateMobile() || :-START");
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Query query = session.createQuery(
					"update MobileEntity set mobileContactTypeId = :contactTypeId, contactNo = :contactNo, primaryContact = :primaryContact, updatedDate=:updatedDate,updatedSysDate:updatedDate where caseMobileId = :caseMobileId");
			for (MobileEntity mob : toUpdate) {
				query.setParameter("contactTypeId", mob.getMobileContactTypeId())
						.setParameter("contactNo", mob.getContactNo())
						.setParameter("primaryContact", mob.getPrimaryContact())
						.setParameter("caseMobileId", mob.getCaseMobileId()).setParameter("updatedDate", new Date())
						.executeUpdate();
			}
		} catch (Exception e) {
			logger.info("MobileDaoImpl || updateMobile() || :-ERROR::" + e.getMessage());
		} finally {
			if (session != null)
				session.close();
		}
		logger.info("MobileDaoImpl || updateMobile() || :-END");
	}

	@Transactional
	public void addMobileList(List<MobileEntity> toInsert) {
		logger.info("MobileDaoImpl | addMobileList() | :-START");
		Session session = sessionFactory.openSession();
		SQLQuery query2 = session.createSQLQuery("INSERT INTO QC_PROSPECT.QT_CASE_MOBILE "
				+ "(CASE_MOBILE_ID, CASE_ID, CONTACT_TYPE_ID, CONTACT_NO, PRIMARY_CONTACT, DND_FLAG, CREATED_BY, CREATED_DATE, CREATED_SYS_DATE) "
				+ "VALUES (QC_PROSPECT.SEQ_MOBILE.NEXTVAL,:caseId,:contactTypeId,:contactNo,:primaryContact,:dnd,:createdBy,:createdDate,:createdSysDate)");
		try {
			for (MobileEntity mobileEntity : toInsert) {

				query2.setParameter("caseId", mobileEntity.getCaseId())
						.setParameter("contactTypeId", mobileEntity.getMobileContactTypeId())
						.setParameter("contactNo", mobileEntity.getContactNo())
						.setParameter("primaryContact", mobileEntity.getPrimaryContact()).setParameter("dnd", "N")
						.setParameter("createdBy", mobileEntity.getCreatedBy())
						.setDate("createdDate", mobileEntity.getCreatedDate())
						.setDate("createdSysDate", mobileEntity.getCreatedSysDate());
				query2.executeUpdate();
			}
		} catch (Exception e) {
			logger.error("MobileDaoImpl | addMobileList() |" + e.getMessage() + " :-END");
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		logger.info("MobileDaoImpl | addMobileList() | :-END");
	}

	@Transactional
	public void updateMobileList(List<MobileEntity> toUpdate) {
		logger.info("MobileDaoImpl | updateMobileList() | :-START");
		Session session = sessionFactory.openSession();
		Query query = session.createQuery(
				"update MobileEntity set mobileContactTypeId = :contactTypeId, contactNo = :contactNo, primaryContact = :primaryContact,updatedBy=:updatedBy,updatedDate=:updatedDate,updatedSysDate=:updatedSysDate where caseMobileId = :caseMobileId");
		try {
			for (MobileEntity mob : toUpdate) {
				query.setParameter("contactTypeId", mob.getMobileContactTypeId())
						.setParameter("contactNo", mob.getContactNo())
						.setParameter("primaryContact", mob.getPrimaryContact())
						.setParameter("caseMobileId", mob.getCaseMobileId())
						.setParameter("updatedBy", mob.getUpdatedBy()).setParameter("updatedDate", mob.getUpdatedDate())
						.setParameter("updatedSysDate", mob.getUpdatedDate()).executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("MobileDaoImpl | updateMobileList() | " + e.getMessage() + " | :-END");
		} finally {
			if (session != null)
				session.close();
		}
		logger.info("MobileDaoImpl | updateMobileList() | :-START");
	}

	@Transactional
	public void updateAllMobileList(String caseId) {
		logger.info("MobileDaoImpl || updateAllMobileList() || :-START");
		Session session = sessionFactory.openSession();
		Query query = session
				.createQuery("update MobileEntity set primaryContact =:primaryContact where caseId = :caseId");
		try {
			query.setParameter("primaryContact", "N").setParameter("caseId", caseId).executeUpdate();
		} catch (Exception e) {
			logger.info("MobileDaoImpl || updateMobile() " + e.getMessage() + " || :-END");
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		logger.info("MobileDaoImpl || updateAllMobileList() || :-END");

	}

}
