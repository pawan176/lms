package com.qc.starter.dao.daoImpl;

import java.util.ArrayList;
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

import com.qc.starter.dao.KeyContactDao;
import com.qc.starter.entity.KeyContactsEntity;

@Repository
public class KeyContactDaoImpl implements KeyContactDao {
	private static final Logger logger = Logger.getLogger(KeyContactDaoImpl.class.getName());
	@Autowired SessionFactory sessionFactory;
	@Autowired HttpSession httpSession;

	@Transactional
	public List<KeyContactsEntity> getKeyContactListOfCClient(String personalDetailId) {
		logger.info("KeyContactDaoImpl || getKeyContactListOfCClient() || :-START");
		List<KeyContactsEntity> list = new ArrayList<KeyContactsEntity>();
		Session session=null;
		SQLQuery query=null;
		try{
			session=sessionFactory.openSession();
			String sql = "SELECT * FROM QC_PROSPECT.QT_KEY_CONTACTS WHERE PERSONAL_DTL_ID ='"+personalDetailId+"'";
			query=session.createSQLQuery(sql);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List result = query.list();
			for (Object o : result) {	
				Map map = (Map) o;
				KeyContactsEntity key = new KeyContactsEntity();
				key.setKeyContactId(map.get("KEY_CONTACT_ID")!=null?map.get("KEY_CONTACT_ID").toString():"");
				key.setContactTypeId(map.get("CONTACT_TYPE_ID")!=null?map.get("CONTACT_TYPE_ID").toString():"");
				key.setFname(map.get("FNAME")!=null?map.get("FNAME").toString():"");
				key.setMname(map.get("MNAME")!=null?map.get("MNAME").toString():"");
				key.setLname(map.get("LNAME")!=null?map.get("LNAME").toString():"");
				key.setFirmName(map.get("FIRM_NAME")!=null?map.get("FIRM_NAME").toString():"");
				key.setAddress(map.get("ADDRESS")!=null?map.get("ADDRESS").toString():"");
				key.setMobile(map.get("MOBILE")!=null?map.get("MOBILE").toString():"");
				key.setEmail(map.get("EMAIL")!=null?map.get("EMAIL").toString():"");
				list.add(key);
				key = null;
			}
		}catch(Exception e){
			logger.error("KeyContactDaoImpl || getKeyContactListOfCClient() "+e.getMessage()+" || :-END");
			e.printStackTrace();
			return null;
		}finally {
			if(session!=null)
				session.close();
		}
		logger.info("KeyContactDaoImpl || getKeyContactListOfCClient() || :-END");
		return list;
	}

	@Transactional
	public int updateKeyContactList(List<KeyContactsEntity> contacts) {
		logger.info("KeyContactDaoImpl | updateKeyContactList() | :-START");
		Session session= null;
		Query query=null;
		int pass = 0;
		try{
			session=sessionFactory.openSession();
			query = session.createQuery("update KeyContactsEntity set contactTypeId=:contactTypeId, fname=:fname,firmName=:firmName,"
					+ "mobile=:mobile, email=:email, address=:address, updatedBy=:updatedBy, updatedDate=:updatedDate, updatedSysDate=:updatedSysDate where keyContactId=:keyContactId ");
			for (KeyContactsEntity key : contacts) {
				query.setParameter("contactTypeId",key.getContactTypeId().equals("-1") ? "-1" : key.getContactTypeId())
				.setParameter("fname",key.getFname() == null ? "" : key.getFname())
				.setParameter("firmName",key.getFirmName() == null ? "" : key.getFirmName())
				.setParameter("mobile",key.getMobile() == null ? "0" :key.getMobile())
				.setParameter("email",key.getEmail() == null ? "" : key.getEmail())
				.setParameter("address",key.getAddress() == null ? "" : key.getAddress())
				.setParameter("updatedBy", key.getUpdatedBy())
				.setParameter("updatedDate", key.getUpdatedDate())
				.setParameter("updatedSysDate",key.getUpdatedSysDate())
				.setParameter("keyContactId", key.getKeyContactId());
				pass += query.executeUpdate();
			}
		}catch(Exception e){
			logger.error("KeyContactDaoImpl || updateKeyContactList() || error:::"+e.getMessage());
			e.printStackTrace();
			return 0;
		}finally {
			if(session!=null)
				session.close();
		}
		logger.info("KeyContactDaoImpl || updateKeyContactList() ||  :-END");
		return pass;
	}

	@Transactional
	public int deleteContact(List<KeyContactsEntity> list) {
		logger.info("KeyContactDaoImpl | deleteContact() | :-START");
		Session session=null;
		Query query=null;
		int status=0;
		try{
			session=sessionFactory.openSession();
			query = session.createQuery("delete from KeyContactsEntity where keyContactId = :keyContactId");
			for (KeyContactsEntity s : list) {
				query.setParameter("keyContactId", s.getKeyContactId());
				status += query.executeUpdate();
			}
		}catch(Exception e){
			logger.error("KeyContactDaoImpl | deleteContact() "+e.getMessage()+" | :-END");
			e.printStackTrace();
			return 0;
		}finally {
			if(session!=null)
				session.close();
		}
		logger.info("KeyContactDaoImpl | deleteContact() | :-END");
		return status;
	}

	@Transactional
	public int addKeyContactList(List<KeyContactsEntity> toInsert) {
		logger.info("KeyContactDaoImpl | addKeyContactList() | :-START");
		int status=0;
		Session session=null;
		SQLQuery query=null;
		try{
			session=sessionFactory.openSession();
			query = session.createSQLQuery("INSERT INTO QC_PROSPECT.QT_KEY_CONTACTS(KEY_CONTACT_ID, " +
					" PERSONAL_DTL_ID, CASE_ID, CONTACT_TYPE_ID, FNAME,FIRM_NAME, MOBILE," +
					" EMAIL, ADDRESS, CREATED_BY, CREATED_DATE, CREATED_SYS_DATE)"
					+ " VALUES (QC_PROSPECT.SEQ_KEYCONTACT.NEXTVAL,:personalDtlId," +
					" :caseId,:contactTypeId,:fname,:firmName,:mobile,:email,:address," +
					" :createdBy,:createdDate, :createdSysDate)");
			for(KeyContactsEntity key : toInsert) {
				query.setParameter("personalDtlId", key.getPersonalDtlId())
				.setParameter("caseId", key.getCaseId())
				.setParameter("contactTypeId",key.getContactTypeId().equals("-1") ? "-1" : key.getContactTypeId())
				.setParameter("fname",key.getFname() == null ? "" : key.getFname())
				.setParameter("firmName",key.getFirmName() == null ? "" : key.getFirmName())
				.setParameter("mobile",key.getMobile() == null ? "0" : key.getMobile())
				.setParameter("email",key.getEmail() == null ? "" : key.getEmail())
				.setParameter("address",key.getAddress() == null ? "" : key.getAddress())
				.setParameter("createdBy", key.getCreatedBy())
				.setDate("createdDate", key.getCreatedDate())
				.setDate("createdSysDate", key.getCreatedSysDate());
				status += query.executeUpdate();
			}
		}catch(Exception e){
			logger.error("KeyContactDaoImpl | addKeyContactList() |  error:::"+e.getMessage());
			e.printStackTrace();
			return 0;
		}finally {
			if(session!=null)
				session.close();
		}
		logger.info("KeyContactDaoImpl | addKeyContactList() || :-END");
		return status;
	}
}
