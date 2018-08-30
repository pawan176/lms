package com.qc.starter.dao.daoImpl;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import oracle.jdbc.OracleTypes;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.qc.starter.basic.CommonUtils;
import com.qc.starter.basic.ConnectionFromSession;
import com.qc.starter.dao.MasterForMobileDao;

@Repository
public class MasterForMobileDaoImpl implements MasterForMobileDao {
	private static final Logger logger = Logger.getLogger(MasterForMobileDaoImpl.class.getName());
	@Autowired
	SessionFactory sessionFactory;


	@Transactional
	public String getAllMasters(String requestString) {
		logger.info("MasterForMobileDaoImpl | getAllMasters() | :- START with Request Json -:-:- "+requestString);
		String resJson=null;
		Session session=null;

		try {
			DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
			String lastSyncDate=dateFormat.format(new Date());
			StringBuffer responsJson=new StringBuffer("{\"receive\":{\"status\":\"S\",\"syncDate\":\""+lastSyncDate+"\",\"singleMasterData\":{\"QM_ADDRESS_TYPE\":[");
			JSONObject requestJson=new JSONObject(requestString);
			String companyId=requestJson.getString("companyId");
			String syncDate=requestJson.getString("syncDate");
			session=sessionFactory.openSession();
			String strQuery="Select ADDRESSTYPEID,ADDRESSTYPEDISPLAYNAME,ACTIVE from QC_PROSPECT_MASTER.QM_ADDRESSTYPE where COMPANY_ID='"+companyId+"' AND ACTIVE='A' AND Update_Datetime >=TO_DATE('"+syncDate+"','dd-MM-yyyy')";
			List result =null;
			try {
				SQLQuery query=session.createSQLQuery(strQuery);
				query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
				result = query.list();	
				if(result != null && result.size() > 0){
					for(Object o : result){				
						Map add = (Map) o;
						responsJson.append("{\"key\":\""+add.get("ADDRESSTYPEID")+"\",\"value\": \""+add.get("ADDRESSTYPEDISPLAYNAME")+"\",\"active\": \""+add.get("ACTIVE")+"\"},");
					}
					responsJson.deleteCharAt(responsJson.length() - 1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			responsJson.append("],\"QM_BANK_MASTER\":[");
			try {
				SQLQuery query=session.createSQLQuery("SELECT BANK_ID,BANK_NAME,ACTIVE FROM QC_PROSPECT_MASTER.QM_BANK WHERE ACTIVE='A' AND Update_Datetime >=TO_DATE('"+syncDate+"','dd-MM-yyyy')");
				query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
				result = query.list();	
				if(result != null && result.size() > 0){
					for(Object o : result){				
						Map add = (Map) o;
						responsJson.append("{\"key\":\""+add.get("BANK_ID")+"\",\"value\": \""+add.get("BANK_NAME")+"\",\"active\": \""+add.get("ACTIVE")+"\"},");
					}
					responsJson.deleteCharAt(responsJson.length() - 1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			responsJson.append("],\"QM_REJECT_REGION\":[");
			try {
				SQLQuery query=session.createSQLQuery("SELECT REJECT_REGION_ID ,REJECT_REGION_NAME ,ACTIVE FROM QC_PROSPECT_MASTER.QM_REJECT_REASON  WHERE ACTIVE='A' AND Update_Datetime >=TO_DATE('"+syncDate+"','dd-MM-yyyy')");
				query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
				result = query.list();	
				if(result != null && result.size() > 0){
					for(Object o : result){				
						Map add = (Map) o;
						responsJson.append("{\"key\":\""+add.get("REJECT_REGION_ID")+"\",\"value\": \""+add.get("REJECT_REGION_NAME")+"\",\"active\": \""+add.get("ACTIVE")+"\"},");
					}
					responsJson.deleteCharAt(responsJson.length() - 1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			/*responsJson.append("],\"QM_CASE_STAGE\":[");
			try {
				SQLQuery query=session.createSQLQuery("SELECT STAGE_ID,STAGE_NAME,ACTIVE FROM QC_PROSPECT_MASTER.QM_CASE_STAGE WHERE ACTIVE ='A' AND Update_Datetime >=TO_DATE('"+syncDate+"','dd-MM-yyyy') ");
				query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
				result = query.list();	
				if(result != null && result.size() > 0){
					for(Object o : result){				
						Map add = (Map) o;
						responsJson.append("{\"key\":\""+add.get("STAGE_ID")+"\",\"value\": \""+add.get("STAGE_NAME")+"\",\"active\": \""+add.get("ACTIVE")+"\"},");
					}
					responsJson.deleteCharAt(responsJson.length() - 1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}*/
			
			
			
			responsJson.append("],\"QM_CAMPAIGN_MASTER\":[");
			try {
				SQLQuery query=session.createSQLQuery("select CAMPAIGN_ID,CAMPAIGN_NAME,ACTIVE from QC_PROSPECT_MASTER.QM_CAMPAIGN where COMPANY_ID='"+companyId+"' AND ACTIVE='A' AND Update_Datetime >=TO_DATE('"+syncDate+"','dd-MM-yyyy')");
				query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
				result = query.list();	
				if(result != null && result.size() > 0){
					for(Object o : result){				
						Map add = (Map) o;
						responsJson.append("{\"key\":\""+add.get("CAMPAIGN_ID")+"\",\"value\": \""+add.get("CAMPAIGN_NAME")+"\",\"active\": \""+add.get("ACTIVE")+"\"},");
					}
					responsJson.deleteCharAt(responsJson.length() - 1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			responsJson.append("],\"QM_CASE_ACTION_MASTER\":[");
			try {
				SQLQuery query=session.createSQLQuery("select ACTION_ID,DISPLAY_ON_WEB,ACTIVE from QC_PROSPECT_MASTER.QM_CASE_ACTION where company_id='"+companyId+"' AND ACTIVE='A' AND ACTION_TYPE = '1' AND Update_Datetime >=TO_DATE('"+syncDate+"','dd-MM-yyyy')");
				query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
				result = query.list();	
				if(result != null && result.size() > 0){
					for(Object o : result){				
						Map add = (Map) o;
						responsJson.append("{\"key\":\""+add.get("ACTION_ID")+"\",\"value\": \""+add.get("DISPLAY_ON_WEB")+"\",\"active\": \""+add.get("ACTIVE")+"\"},");
					}
					responsJson.deleteCharAt(responsJson.length() - 1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			responsJson.append("],\"QM_CASE_CONTACT_MASTER\":[");
			try {
				SQLQuery query=session.createSQLQuery("select CONTACT_TYPE_ID,CONTACT_TYPE,ACTIVE from QC_PROSPECT_MASTER.QM_CASE_CONTACT_TYPE where COMPANY_ID='"+companyId+"' AND ACTIVE='A' AND Update_Datetime >=TO_DATE('"+syncDate+"','dd-MM-yyyy')");
				query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
				result = query.list();	
				if(result != null && result.size() > 0){
					for(Object o : result){				
						Map add = (Map) o;
						responsJson.append("{\"key\":\""+add.get("CONTACT_TYPE_ID")+"\",\"value\": \""+add.get("CONTACT_TYPE")+"\",\"active\": \""+add.get("ACTIVE")+"\"},");
					}
					responsJson.deleteCharAt(responsJson.length() - 1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			responsJson.append("],\"QM_COMPANY\":[");
			try {
				SQLQuery query=session.createSQLQuery("select CASE_COMPANY_ID,DISPLAY_NAME,ACTIVE from QC_PROSPECT_MASTER.QM_CASE_COMPANY WHERE COMPANY_ID='"+companyId+"' AND ACTIVE='A' AND Update_Datetime =TO_DATE('"+syncDate+"','dd-MM-yyyy')");
				query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
				result = query.list();	
				if(result != null && result.size() > 0){
					for(Object o : result){				
						Map add = (Map) o;
						responsJson.append("{\"key\":\""+add.get("CASE_COMPANY_ID")+"\",\"value\": \""+add.get("DISPLAY_NAME")+"\",\"active\": \""+add.get("ACTIVE")+"\"},");
					}
					responsJson.deleteCharAt(responsJson.length() - 1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			responsJson.append("],\"QM_GENDER\":[");
			try {
				SQLQuery query=session.createSQLQuery("select GENDERID,GENDERNAME,ACTIVE from QC_PROSPECT_MASTER.QM_GENDER WHERE ACTIVE='A' AND Update_Datetime >=TO_DATE('"+syncDate+"','dd-MM-yyyy')");
				query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
				result = query.list();	
				if(result != null && result.size() > 0){
					for(Object o : result){				
						Map add = (Map) o;
						responsJson.append("{\"key\":\""+add.get("GENDERID")+"\",\"value\": \""+add.get("GENDERNAME")+"\",\"active\": \""+add.get("ACTIVE")+"\"},");
					}
					responsJson.deleteCharAt(responsJson.length() - 1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			responsJson.append("],\"QM_MARITAL_STATUS_MASTER\":[");
			try {
				SQLQuery query=session.createSQLQuery("select MARITALSTATUSID,MARITALSTATUSNAME,ACTIVE from QC_PROSPECT_MASTER.QM_MARITALSTATUS where ACTIVE='A' AND Update_Datetime >=TO_DATE('"+syncDate+"','dd-MM-yyyy')");
				query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
				result = query.list();	
				if(result != null && result.size() > 0){
					for(Object o : result){				
						Map add = (Map) o;
						responsJson.append("{\"key\":\""+add.get("MARITALSTATUSID")+"\",\"value\": \""+add.get("MARITALSTATUSNAME")+"\",\"active\": \""+add.get("ACTIVE")+"\"},");
					}
					responsJson.deleteCharAt(responsJson.length() - 1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			responsJson.append("],\"QM_NATIONALITY_MASTER\":[");
			try {
				SQLQuery query=session.createSQLQuery("select NATIONALITY_ID,NATION_NAME,ACTIVE from QC_PROSPECT_MASTER.QM_NATIONALITY where COMPANY_ID='"+companyId+"' and ACTIVE='A' AND Update_Datetime >=TO_DATE('"+syncDate+"','dd-MM-yyyy')");
				query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
				result = query.list();	
				if(result != null && result.size() > 0){
					for(Object o : result){				
						Map add = (Map) o;
						responsJson.append("{\"key\":\""+add.get("NATIONALITY_ID")+"\",\"value\": \""+add.get("NATION_NAME")+"\",\"active\": \""+add.get("ACTIVE")+"\"},");
					}
					responsJson.deleteCharAt(responsJson.length() - 1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			//OccupationEntity:
			responsJson.append("],\"QM_OCCUPATION_MASTER\":[");
			try {
				SQLQuery query=session.createSQLQuery("select OCCUPATIONID,OCCUPATIONNAME,ACTIVE from QC_PROSPECT_MASTER.QM_OCCUPATION where COMPANY_ID='"+companyId+"' and ACTIVE='A' AND Update_Datetime >=TO_DATE('"+syncDate+"','dd-MM-yyyy')");
				query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
				result = query.list();	
				if(result != null && result.size() > 0){
					for(Object o : result){				
						Map add = (Map) o;
						responsJson.append("{\"key\":\""+add.get("OCCUPATIONID")+"\",\"value\": \""+add.get("OCCUPATIONNAME")+"\",\"active\": \""+add.get("ACTIVE")+"\"},");
					}
					responsJson.deleteCharAt(responsJson.length() - 1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			//PropertyStatusEntity: 
			responsJson.append("],\"QM_PROPERTY_STATUS_MASTER\":[");
			try {
				SQLQuery query=session.createSQLQuery("select PROP_STATUS_ID,PROP_STATUS_NAME,ACTIVE from QC_PROSPECT_MASTER.QM_PROPERTY_STATUS where ACTIVE='A' and COMPANY_ID='"+companyId+"' AND Update_Datetime >=TO_DATE('"+syncDate+"','dd-MM-yyyy')");
				query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
				result = query.list();	
				if(result != null && result.size() > 0){
					for(Object o : result){				
						Map add = (Map) o;
						responsJson.append("{\"key\":\""+add.get("PROP_STATUS_ID")+"\",\"value\": \""+add.get("PROP_STATUS_NAME")+"\",\"active\": \""+add.get("ACTIVE")+"\"},");
					}
					responsJson.deleteCharAt(responsJson.length() - 1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			//PropertyTypeEntity: 
			responsJson.append("],\"QM_PROPERTY_TYPE_MASTER\":[");
			try {
				SQLQuery query=session.createSQLQuery("select PROP_TYPE_ID,PROP_TYPE_NAME,ACTIVE from QC_PROSPECT_MASTER.QM_PROPERTY_TYPE where ACTIVE='A' and COMPANY_ID='"+companyId+"' AND Update_Datetime >=TO_DATE('"+syncDate+"','dd-MM-yyyy')");
				query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
				result = query.list();	
				if(result != null && result.size() > 0){
					for(Object o : result){				
						Map add = (Map) o;
						responsJson.append("{\"key\":\""+add.get("PROP_TYPE_ID")+"\",\"value\": \""+add.get("PROP_TYPE_NAME")+"\",\"active\": \""+add.get("ACTIVE")+"\"},");
					}
					responsJson.deleteCharAt(responsJson.length() - 1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			//SalaryModeEntity: 
			responsJson.append("],\"QM_SALARY_MODE_MASTER\":[");
			try {
				SQLQuery query=session.createSQLQuery("select SALARY_MODE_ID,SALARY_MODE,ACTIVE from QC_PROSPECT_MASTER.QM_SALARY_MODE where ACTIVE='A' and COMPANY_ID='"+companyId+"' AND Update_Datetime >=TO_DATE('"+syncDate+"','dd-MM-yyyy')");
				query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
				result = query.list();	
				if(result != null && result.size() > 0){
					for(Object o : result){				
						Map add = (Map) o;
						responsJson.append("{\"key\":\""+add.get("SALARY_MODE_ID")+"\",\"value\": \""+add.get("SALARY_MODE")+"\",\"active\": \""+add.get("ACTIVE")+"\"},");
					}
					responsJson.deleteCharAt(responsJson.length() - 1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			//SourceEntity: 
			responsJson.append("],\"QM_SOURCE\":[");
			try {
				SQLQuery query=session.createSQLQuery("SELECT CASE_SOURCE_ID,SOURCE_NAME,ACTIVE FROM QC_PROSPECT_MASTER.QM_SOURCE WHERE ACTIVE='A' and COMPANY_ID ='"+companyId+"' AND Update_Datetime >=TO_DATE('"+syncDate+"','dd-MM-yyyy')");
				query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
				result = query.list();	
				if(result != null && result.size() > 0){
					for(Object o : result){				
						Map add = (Map) o;
						responsJson.append("{\"key\":\""+add.get("CASE_SOURCE_ID")+"\",\"value\": \""+add.get("SOURCE_NAME")+"\",\"active\": \""+add.get("ACTIVE")+"\"},");
					}
					responsJson.deleteCharAt(responsJson.length() - 1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			//StateMasterEntity: 
			responsJson.append("],\"QM_STATE\":[");
			try {
				SQLQuery query=session.createSQLQuery("select STATEMASTERID,STATEMASTERNAME,ACTIVE from QC_PROSPECT_MASTER.QM_STATEMASTER WHERE ACTIVE='A' AND COMPANY_ID='"+companyId+"' AND Update_Datetime >=TO_DATE('"+syncDate+"','dd-MM-yyyy') order by DISPLAYNAME ");
				query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
				result = query.list();	
				if(result != null && result.size() > 0){
					for(Object o : result){				
						Map add = (Map) o;
						responsJson.append("{\"key\":\""+add.get("STATEMASTERID")+"\",\"value\": \""+add.get("STATEMASTERNAME")+"\",\"active\": \""+add.get("ACTIVE")+"\"},");
					}
					responsJson.deleteCharAt(responsJson.length() - 1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			//ActionMaster: 
			responsJson.append("],\"QM_ACTION_MASTER\":[");
			try {
				SQLQuery query=session.createSQLQuery("select ACTION_ID,ACTION_NAME,ACTIVE from QC_PROSPECT_MASTER.QM_CASE_ACTION where ACTIVE='A' and company_id='"+companyId+"' and ACTION_TYPE='2' AND Update_Datetime >=TO_DATE('"+syncDate+"','dd-MM-yyyy')");
				query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
				result = query.list();	
				if(result != null && result.size() > 0){
					for(Object o : result){				
						Map add = (Map) o;
						responsJson.append("{\"key\":\""+add.get("ACTION_ID")+"\",\"value\": \""+add.get("ACTION_NAME")+"\",\"active\": \""+add.get("ACTIVE")+"\"},");
					}
					responsJson.deleteCharAt(responsJson.length() - 1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			responsJson.append("],\"QM_DISPOSITION\":[");
			try {
				SQLQuery query=session.createSQLQuery("select ACTION_ID,ACTION_NAME,ACTIVE from QC_PROSPECT_MASTER.QM_CASE_ACTION where company_id='"+companyId+"'	and ACTION_TYPE in (select ACTION_TYPE_ID from QC_PROSPECT_MASTER.QM_CASE_ACTION_TYPE WHERE ACTION_TYPE_NAME ='DISPOSITION' AND COMPANY_ID ='"+companyId+"' ) AND ACTION_NAME <>'NEW LEAD' AND ACTIVE='A' AND UPDATE_DATETIME >=To_Date('"+syncDate+"','dd-MM-yyyy') ORDER BY ACTION_NAME");
				query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
				result = query.list();	
				if(result != null && result.size() > 0){
					for(Object o : result){				
						Map add = (Map) o;
						responsJson.append("{\"key\":\""+add.get("ACTION_ID")+"\",\"value\": \""+add.get("ACTION_NAME")+"\",\"active\": \""+add.get("ACTIVE")+"\"},");
					}
					responsJson.deleteCharAt(responsJson.length() - 1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			responsJson.append("],\"QM_LEADSTATE\":[");
			try {
				SQLQuery query=session.createSQLQuery("select ACTION_ID,ACTION_STAGE,ACTIVE  from QC_PROSPECT_MASTER.QM_CASE_ACTION where ACTIVE='A' and company_id='"+companyId+"' and Update_Datetime >=To_Date('"+syncDate+"','dd-MM-yyyy') and ACTION_TYPE IN (select ACTION_TYPE_ID from QC_PROSPECT_MASTER.QM_CASE_ACTION_TYPE WHERE ACTION_TYPE_NAME ='DISPOSITION' AND COMPANY_ID ='"+companyId+"' )");
				query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
				result = query.list();	
				if(result != null && result.size() > 0){
					for(Object o : result){				
						Map add = (Map) o;
						responsJson.append("{\"key\":\""+add.get("ACTION_ID")+"\",\"value\": \""+add.get("ACTION_STAGE")+"\",\"active\": \""+add.get("ACTIVE")+"\"},");
					}
					responsJson.deleteCharAt(responsJson.length() - 1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			//DevloperEntity: 
			responsJson.append("],\"QM_DEVELOPER_MASTER\":[");
			try {
				SQLQuery query=session.createSQLQuery("SELECT DEVELOPER_ID,DEVELOPER_NAME,ACTIVE FROM QC_PROSPECT_MASTER.QM_DEVELOPER WHERE ACTIVE ='A' AND COMPANY_ID ='"+companyId+"' AND Update_Datetime >=TO_DATE('"+syncDate+"','dd-MM-yyyy')");
				query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
				result = query.list();	
				if(result != null && result.size() > 0){
					for(Object o : result){				
						Map add = (Map) o;
						responsJson.append("{\"key\":\""+add.get("DEVELOPER_ID")+"\",\"value\": \""+add.get("DEVELOPER_NAME")+"\",\"active\": \""+add.get("ACTIVE")+"\"},");
					}
					responsJson.deleteCharAt(responsJson.length() - 1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			//EntityType: 
			responsJson.append("],\"QM_ENTITY_TYPE\":[");
			try {
				SQLQuery query=session.createSQLQuery("SELECT CUSTENTITYTYPEID,DISPLAYNAME,ACTIVE FROM QC_PROSPECT_MASTER.QM_CUSTENTITYTYPE WHERE ACTIVE ='A' AND COMPANY_ID ='"+companyId+"' AND Update_Datetime >=TO_DATE('"+syncDate+"','dd-MM-yyyy')");
				query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
				result = query.list();	
				if(result != null && result.size() > 0){
					for(Object o : result){				
						Map add = (Map) o;
						responsJson.append("{\"key\":\""+add.get("CUSTENTITYTYPEID")+"\",\"value\": \""+add.get("DISPLAYNAME")+"\",\"active\": \""+add.get("ACTIVE")+"\"},");
					}
					responsJson.deleteCharAt(responsJson.length() - 1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			//CustomerCategory: 
			responsJson.append("],\"QM_CUSTOMER_CATEGORY\":[");
			try {
				SQLQuery query=session.createSQLQuery("SELECT CUSTENTITYTYPEID,DISPLAYNAME,ACTIVE FROM QC_PROSPECT_MASTER.QM_CUSTENTITYTYPE WHERE ACTIVE ='A' AND Update_Datetime >=TO_DATE('"+syncDate+"','dd-MM-yyyy')");
				query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
				result = query.list();	
				if(result != null && result.size() > 0){
					for(Object o : result){				
						Map add = (Map) o;
						responsJson.append("{\"key\":\""+add.get("CUSTENTITYTYPEID")+"\",\"value\": \""+add.get("DISPLAYNAME")+"\",\"active\": \""+add.get("ACTIVE")+"\"},");
					}
					responsJson.deleteCharAt(responsJson.length() - 1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			//Industry: 
			responsJson.append("],\"QM_INDUSTRY\":[");
			try {
				SQLQuery query=session.createSQLQuery("SELECT INDUSTRYID,DISPLAYNAME,ACTIVE FROM QC_PROSPECT_MASTER.QM_INDUSTRY WHERE ACTIVE ='A' AND Update_Datetime >=TO_DATE('"+syncDate+"','dd-MM-yyyy')");
				query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
				result = query.list();	
				if(result != null && result.size() > 0){
					for(Object o : result){				
						Map add = (Map) o;
						responsJson.append("{\"key\":\""+add.get("INDUSTRYID")+"\",\"value\": \""+add.get("DISPLAYNAME")+"\",\"active\": \""+add.get("ACTIVE")+"\"},");
					}
					responsJson.deleteCharAt(responsJson.length() - 1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			//BranchMaster: 
			responsJson.append("],\"BRANCH_MST\":[");
			try {
				SQLQuery query=session.createSQLQuery("SELECT GEOID,GEONAME,ACTIVE FROM QC_PROSPECT_MASTER.QM_GEO WHERE ACTIVE ='A' AND GEOTYPEID=1000000006 AND Update_Datetime >=TO_DATE('"+syncDate+"','dd-MM-yyyy') order by GEONAME");
				query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
				result = query.list();	
				if(result != null && result.size() > 0){
					for(Object o : result){				
						Map add = (Map) o;
						responsJson.append("{\"key\":\""+add.get("GEOID")+"\",\"value\": \""+add.get("GEONAME")+"\",\"active\": \""+add.get("ACTIVE")+"\"},");
					}
					responsJson.deleteCharAt(responsJson.length() - 1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			//	ContactTypeEntity:
			responsJson.append("],\"QM_CONTACT_TYPE_MOBILE\":[");
			try {
				SQLQuery query=session.createSQLQuery("Select ADDRESSTYPEID,ADDRESSTYPENAME,ACTIVE from QC_PROSPECT_MASTER.QM_ADDRESSTYPE where active = 'A' AND Update_Datetime >=TO_DATE('"+syncDate+"','dd-MM-yyyy') ");
				query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
				result = query.list();	
				if(result != null && result.size() > 0){
					for(Object o : result){				
						Map add = (Map) o;
						responsJson.append("{\"key\":\""+add.get("ADDRESSTYPEID")+"\",\"value\": \""+add.get("ADDRESSTYPENAME")+"\",\"active\": \""+add.get("ACTIVE")+"\"},");
					}
					responsJson.deleteCharAt(responsJson.length() - 1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			//	ContactTypeEntity:
			responsJson.append("],\"QM_CONTACT_TYPE_EMAIL\":[");
			try {
				SQLQuery query=session.createSQLQuery("SELECT CONTACT_TYPE_ID,CONTACT_TYPE_NAME,ACTIVE from QC_PROSPECT_MASTER.QM_CONTACT_TYPE where COMPANY_ID='"+companyId+"' AND ACTIVE='A' AND CONTACT_CATEGORY='EMAIL' AND Update_Datetime >=TO_DATE('"+syncDate+"','dd-MM-yyyy')");
				query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
				result = query.list();	
				if(result != null && result.size() > 0){
					for(Object o : result){				
						Map add = (Map) o;
						responsJson.append("{\"key\":\""+add.get("CONTACT_TYPE_ID")+"\",\"value\": \""+add.get("CONTACT_TYPE_NAME")+"\",\"active\": \""+add.get("ACTIVE")+"\"},");
					}
					responsJson.deleteCharAt(responsJson.length() - 1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			//CompanyTypeEntity:
			responsJson.append("],\"QM_COMPANY_TYPE_MASTER\":[");
			try {
				SQLQuery query=session.createSQLQuery("select COMP_TYPE_ID,COMPANY_TYPE,ACTIVE from QC_PROSPECT_MASTER.QM_COMPANY_TYPE WHERE COMPANY_ID='"+companyId+"' AND ACTIVE='A' AND Update_Datetime >=TO_DATE('"+syncDate+"','dd-MM-yyyy')");
				query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
				result = query.list();	
				if(result != null && result.size() > 0){
					for(Object o : result){				
						Map add = (Map) o;
						responsJson.append("{\"key\":\""+add.get("COMP_TYPE_ID")+"\",\"value\": \""+add.get("COMPANY_TYPE")+"\",\"active\": \""+add.get("ACTIVE")+"\"},");
					}
					responsJson.deleteCharAt(responsJson.length() - 1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			responsJson.append("],\"QM_DOCSTATUS\":[");
			try {
				SQLQuery query=session.createSQLQuery("SELECT DOCUMENTSTATUSID,DOCUMENTSTATUSNAME,ACTIVE   from QC_PROSPECT_MASTER.QM_DOCUMENTSTATUS WHERE ACTIVE='A' AND COMPANY_ID='"+companyId+"' AND UPDATE_DATETIME >=TO_DATE('"+syncDate+"','dd-MM-yyyy')");
				query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
				result = query.list();	
				if(result != null && result.size() > 0){
					for(Object o : result){				
						Map add = (Map) o;
						responsJson.append("{\"key\":\""+add.get("DOCUMENTSTATUSID")+"\",\"value\": \""+add.get("DOCUMENTSTATUSNAME")+"\",\"active\": \""+add.get("ACTIVE")+"\"},");
					}
					responsJson.deleteCharAt(responsJson.length() - 1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			responsJson.append("],\"QM_EMPLOYMENTTYPE\":[");
			try {
				SQLQuery query=session.createSQLQuery("SELECT EMPLOYMENTTYPEID,EMPLOYMENTTYPENAME,ACTIVE  from QC_PROSPECT_MASTER.QM_EMPLOYMENTTYPE where COMPANY_ID='"+companyId+"' AND ACTIVE='A' AND Update_Datetime >=TO_DATE('"+syncDate+"','dd-MM-yyyy')");
				query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
				result = query.list();	
				if(result != null && result.size() > 0){
					for(Object o : result){				
						Map add = (Map) o;
						responsJson.append("{\"key\":\""+add.get("EMPLOYMENTTYPEID")+"\",\"value\": \""+add.get("EMPLOYMENTTYPENAME")+"\",\"active\": \""+add.get("ACTIVE")+"\"},");
					}
					responsJson.deleteCharAt(responsJson.length() - 1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			//CityEntity
			responsJson.append("]},\"multiMasterData\": {\"CITY_MST\": [");
			try {
				SQLQuery query=session.createSQLQuery("select CITYMASTERID,DISPLAYNAME,STATEID,ACTIVE from QC_PROSPECT_MASTER.QM_CITYMASTER WHERE ACTIVE='A' AND Update_Datetime >=TO_DATE('"+syncDate+"','dd-MM-yyyy')");
				query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
				result = query.list();	
				if(result != null && result.size() > 0){
					for(Object o : result){				
						Map add = (Map) o;
						responsJson.append("{\"key\":\""+add.get("CITYMASTERID")+"\",\"value\": \""+add.get("DISPLAYNAME")+"\",\"parentKey\":"+add.get("STATEID")+",\"active\": \""+add.get("ACTIVE")+"\"},");
					}
					responsJson.deleteCharAt(responsJson.length() - 1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			//QM_CASE_NEXT_ACTION
			responsJson.append("],\"QM_CASE_NEXT_ACTION\":[");
			try {
				SQLQuery query=session.createSQLQuery("SELECT NEXTACTION_ID,NEXTACTION_NAME,ACTION_ID,ACTIVE FROM QC_PROSPECT_MASTER.QM_CASE_NEXT_ACTION WHERE ACTIVE = 'A' ");
				query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
				result = query.list();	
				if(result != null && result.size() > 0){
					for(Object o : result){				
						Map add = (Map) o;
						responsJson.append("{\"key\":\""+add.get("NEXTACTION_ID")+"\",\"value\": \""+add.get("NEXTACTION_NAME")+"\",\"parentKey\":"+add.get("ACTION_ID")+",\"active\": \""+add.get("ACTIVE")+"\"},");
					}
					responsJson.deleteCharAt(responsJson.length() - 1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			//OccupancyStatusEntity
			responsJson.append("],\"QM_OCCUPANCY_STATUS\":[");
			try {
				SQLQuery query=session.createSQLQuery("select OCCUPANCY_ST_ID,OCCUPANCY_ST_NAME,OCCUPANCY_TYPE,ACTIVE from QC_PROSPECT_MASTER.QM_OCCUPANCY_STATUS where COMPANY_ID='"+companyId+"' and ACTIVE='A' AND Update_Datetime >=TO_DATE('"+syncDate+"','dd-MM-yyyy')");
				query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
				result = query.list();	
				if(result != null && result.size() > 0){
					for(Object o : result){				
						Map add = (Map) o;
						responsJson.append("{\"key\":\""+add.get("OCCUPANCY_ST_ID")+"\",\"value\": \""+add.get("OCCUPANCY_ST_NAME")+"\",\"parentKey\":"+add.get("OCCUPANCY_TYPE")+",\"active\": \""+add.get("ACTIVE")+"\"},");
					}
					responsJson.deleteCharAt(responsJson.length() - 1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			//existingFacility:  
			responsJson.append("],\"QM_EXIST_FACILITY\":[");
			try {
				SQLQuery query=session.createSQLQuery("select PROD_ID,PROD_NAME,PROD_CAT_ID,ACTIVE from QC_PROSPECT_MASTER.QM_CASE_PRODUCT where COMPANY_ID='"+companyId+"' and ACTIVE='A' and PROD_TYPE_ID IN (select PROD_TYPE_ID from QC_PROSPECT_MASTER.QM_CASE_PROD_TYPE where ACTIVE='A' and COMPANY_ID ='"+companyId+"' and PROD_TYPE_NAME='AVAILED PRODUCT') AND Update_Datetime >=TO_DATE('"+syncDate+"','dd-MM-yyyy')");
				query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
				result = query.list();	
				if(result != null && result.size() > 0){
					for(Object o : result){				
						Map add = (Map) o;
						responsJson.append("{\"key\":\""+add.get("PROD_ID")+"\",\"value\": \""+add.get("PROD_NAME")+"\",\"parentKey\":"+add.get("PROD_CAT_ID")+",\"active\": \""+add.get("ACTIVE")+"\"},");
					}
					responsJson.deleteCharAt(responsJson.length() - 1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			//PurposeOfLoan: 
			responsJson.append("],\"QM_PURPOSE_OF_LOAN\":[");
			try {
				SQLQuery query=session.createSQLQuery("SELECT PURPOSEOFLOANID,PURPOSEOFLOANNAME,PRODUCTID,ACTIVE from QC_PROSPECT_MASTER.QM_PURPOSEOFLOAN where ACTIVE='A' AND Update_Datetime >=TO_DATE('"+syncDate+"','dd-MM-yyyy')");
				query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
				result = query.list();	
				if(result != null && result.size() > 0){
					for(Object o : result){				
						Map add = (Map) o;
						responsJson.append("{\"key\":\""+add.get("PURPOSEOFLOANID")+"\",\"value\": \""+add.get("PURPOSEOFLOANNAME")+"\",\"parentKey\":"+add.get("PRODUCTID")+",\"active\": \""+add.get("ACTIVE")+"\"},");
					}
					responsJson.deleteCharAt(responsJson.length() - 1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			//ProductMasterEntity:
			responsJson.append("],\"QM_PRODUCT\":[");
			try {
				SQLQuery query=session.createSQLQuery("select  CP.PRODUCTID as PRODUCT_ID,CP.PRODNAME,CP.PRODTYPEID,CP.ACTIVE  from QC_PROSPECT_MASTER.QM_PRODUCT CP where CP.COMPANY_ID='"+companyId+"' and ACTIVE='A' and CP.PRODTYPEID IN (select PD.PRODUCTTYPEID as PD_PRODTYPEId from QC_PROSPECT_MASTER.QM_PRODUCTTYPE PD where ACTIVE='A' and PD.COMPANY_ID ='"+companyId+"' and PD.PRODUCTTYPE='Scheme') AND Update_Datetime >=TO_DATE('"+syncDate+"','dd-MM-yyyy')");
				query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
				result = query.list();	
				if(result != null && result.size() > 0){
					for(Object o : result){				
						Map add = (Map) o;
						responsJson.append("{\"key\":\""+add.get("PRODUCT_ID")+"\",\"value\": \""+add.get("PRODNAME")+"\",\"parentKey\":"+add.get("PRODTYPEID")+",\"active\": \""+add.get("ACTIVE")+"\"},");
					}
					responsJson.deleteCharAt(responsJson.length() - 1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			responsJson.append("],\"QM_DOCTYPE\":[");
			try {
				SQLQuery query=session.createSQLQuery("SELECT DOCUMENTTYPEID,DISPLAYNAME,DOCUMENTCATEGORYID,ACTIVE FROM QC_PROSPECT_MASTER.QM_DOCUMENTTYPE WHERE  ACTIVE ='A' AND COMPANY_ID  ='"+companyId+"' AND Update_Datetime >=TO_DATE('"+syncDate+"','dd-MM-yyyy')");
				query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
				result = query.list();	
				if(result != null && result.size() > 0){
					for(Object o : result){				
						Map add = (Map) o;
						responsJson.append("{\"key\":\""+add.get("DOCUMENTTYPEID")+"\",\"value\": \""+add.get("DISPLAYNAME")+"\",\"parentKey\":"+add.get("DOCUMENTCATEGORYID")+",\"active\": \""+add.get("ACTIVE")+"\"},");
					}
					responsJson.deleteCharAt(responsJson.length() - 1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			String sql="";
			responsJson.append("],\"TYPE_OF_BUSINESS\":[");
			try {
				sql = "SELECT IDX_TYP.INDUSTRYID PARENTKEY, TYPOFBUS.ID AS KEY, TYPOFBUS.TYPE_OF_BUSINESS AS VALUE, TYPOFBUS.ACTIVE FROM QC_PROSPECT_MASTER.QM_TYPE_OF_BUSINESS TYPOFBUS, QC_PROSPECT_MASTER.QM_INDUSTRY_X_TYPEOFBUS IDX_TYP WHERE TYPOFBUS.ID=IDX_TYP.NATUREOFBUS_ID AND TYPOFBUS.ACTIVE = 'A' AND TRUNC(NVL(TYPOFBUS.UPDATE_DATETIME, TYPOFBUS.CREATED_DATETIME)) >= '"+syncDate+"'";
				SQLQuery query=session.createSQLQuery(sql);
				query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
				result = query.list();	
				if(result != null && result.size() > 0){
					for(Object o : result){				
						Map add = (Map) o;
						responsJson.append("{\"key\":\""+add.get("KEY")+"\",\"value\": \""+add.get("VALUE")+"\",\"parentKey\":"+add.get("PARENTKEY")+",\"active\": \""+add.get("ACTIVE")+"\"},");
					}
					responsJson.deleteCharAt(responsJson.length() - 1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			responsJson.append("],\"CLUSTER_VAL\":[");
			try {
				sql = "SELECT CLUSXBUS.QM_IA_INDUSTRY_TYPE_ID AS PARENTKEY,CLUS.CLUSTERID AS KEY, CLUS.DISPLAYNAME AS VALUE, CLUS.ACTIVE FROM QC_PROSPECT_MASTER.QM_CLUSTER CLUS, QC_PROSPECT_MASTER.QM_IA_CLUSTER_X_IND  CLUSXBUS WHERE CLUS.CLUSTERID=CLUSXBUS.QM_CLUSTER_ID AND CLUS.ACTIVE ='A' AND TRUNC(NVL(CLUS.UPDATE_DATETIME, CLUS.CREATED_DATETIME)) >= '"+syncDate+"'";
				SQLQuery query=session.createSQLQuery(sql);
				query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
				result = query.list();	
				if(result != null && result.size() > 0){
					for(Object o : result){				
						Map add = (Map) o;
						responsJson.append("{\"key\":\""+add.get("KEY")+"\",\"value\": \""+add.get("VALUE")+"\",\"parentKey\":"+add.get("PARENTKEY")+",\"active\": \""+add.get("ACTIVE")+"\"},");
					}
					responsJson.deleteCharAt(responsJson.length() - 1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			responsJson.append("],\"USER_LOCATION_INFO\":[");			
			try {		
				SQLQuery query = session.createSQLQuery("SELECT USERID, GEOID, ACTIVE, USERLOCATIONID FROM QC_MASTER.QM_USERLOCATION WHERE TRUNC(NVL(UPDATE_DATETIME, CREATED_DATETIME)) >=  '"+syncDate+"'");
				query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
				result = query.list();	
				if(result != null && result.size() > 0){
					for(Object o : result){				
						Map add = (Map) o;
						responsJson.append("{\"KEY\":\""+add.get("USERID")+"\",\"VALUE\":\""+add.get("GEOID")+"\",\"ACTIVE\": \""+add.get("ACTIVE")+"\",\"USERLOCATIONID\":\""+add.get("USERLOCATIONID")+"\"},");
					}
					responsJson.deleteCharAt(responsJson.length() - 1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			
			
			responsJson.append("],\"QM_DOCNAME\":[");
			try {
				SQLQuery query=session.createSQLQuery("SELECT DOCUMENTID,DISPLAYNAME,DOCUMENTTYPEID,ACTIVE FROM QC_PROSPECT_MASTER.QM_DOCUMENT WHERE COMPANY_ID='"+companyId+"' AND ACTIVE  ='A' AND UPDATE_DATETIME >=TO_DATE('"+syncDate+"','dd-MM-yyyy')");
				query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
				result = query.list();	
				if(result != null && result.size() > 0){
					for(Object o : result){				
						Map add = (Map) o;
						responsJson.append("{\"key\":\""+add.get("DOCUMENTID")+"\",\"value\": \""+add.get("DISPLAYNAME")+"\",\"parentKey\":"+add.get("DOCUMENTTYPEID")+",\"active\": \""+add.get("ACTIVE")+"\"},");
					}
					responsJson.deleteCharAt(responsJson.length() - 1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			responsJson.append("]},\"OTHERMASTER\": {\"QM_CONFIGURATION\": [");
			
			try {
				SQLQuery query=session.createSQLQuery("SELECT CONFIGID,APPLICATIONNAME,PARAMID,PARAMNAME,PARAMVALUE,STATUS FROM QC_PROSPECT_MASTER.QM_SYS_CONFIGURATION_LEAD WHERE STATUS ='A' AND COMPANY_ID  ='"+companyId+"' AND Update_Datetime >=TO_DATE('"+syncDate+"','dd-MM-yyyy')");
				query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
				result = query.list();	
				if(result != null && result.size() > 0){
					for(Object o : result){				
						Map add = (Map) o;
						responsJson.append("{\"key\":\""+add.get("CONFIGID")+"\",\"PARAMID\":\""+add.get("PARAMID")+"\",\"paramname\": \""+add.get("PARAMNAME")+"\",\"paramvalue\":\""+add.get("PARAMVALUE")+"\",\"active\": \""+add.get("STATUS")+"\"},");
					}
					responsJson.deleteCharAt(responsJson.length() - 1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			//QM_CASE_STAGE
			responsJson.append("],\"QM_CASE_STAGE\":[");
			try {
				SQLQuery query=session.createSQLQuery("SELECT STAGE_ID, STAGE_NAME,DASHBOARD_SEQ, IS_DEFAULT,ACTIVE FROM QC_PROSPECT_MASTER.QM_CASE_STAGE WHERE ACTIVE = 'A' AND Update_Datetime >= TO_DATE('"+syncDate+"', 'dd-MM-yyyy') ");
				query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
				result = query.list();	
				if(result != null && result.size() > 0){
					for(Object o : result){				
						Map add = (Map) o;
						responsJson.append("{\"key\":\""+add.get("STAGE_ID")+"\",\"value\": \""+add.get("STAGE_NAME")+"\",\"dashboardSeq\":"+add.get("DASHBOARD_SEQ")+",\"isDefault\":\""+add.get("IS_DEFAULT")+"\", \"active\": \""+add.get("ACTIVE")+"\"},");
					}
					responsJson.deleteCharAt(responsJson.length() - 1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			//SubQueueEntity: 
			responsJson.append("],\"QM_SUBQUEUE_MASTER\":[");
			try {
				SQLQuery query=session.createSQLQuery("select SUB_QUEUE_ID,SUB_QUEUE,ACTIVE,MIN_DAYS,MAX_DAYS from QC_PROSPECT_MASTER.QM_SUB_QUEUE WHERE ACTIVE='A' and PARENT_SUB_QUEUE IS NULL and COMPANY_ID ='"+companyId+"' AND Update_Datetime >=TO_DATE('"+syncDate+"','dd-MM-yyyy')");
				query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
				result = query.list();	
				if(result != null && result.size() > 0){
					for(Object o : result){				
						Map add = (Map) o;
						responsJson.append("{\"key\":\""+add.get("SUB_QUEUE_ID")+"\",\"value\": \""+add.get("SUB_QUEUE")+"\",\"active\": \""+add.get("ACTIVE")+"\",\"minDays\": \""+add.get("MIN_DAYS")+"\",\"maxDays\": \""+add.get("MAX_DAYS")+"\"},");
					}
					responsJson.deleteCharAt(responsJson.length() - 1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			responsJson.append("],\"QM_PURPOSE\":[");
			try {
				SQLQuery query=session.createSQLQuery("SELECT PURPOSE_ID ,PURPOSE_NAME ,IS_DEFAULT,ACTIVE FROM QC_PROSPECT_MASTER.QM_PURPOSE WHERE ACTIVE = 'A' AND Update_Datetime >= TO_DATE('"+syncDate+"', 'dd-MM-yyyy') ");
				query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
				result = query.list();	
				if(result != null && result.size() > 0){
					for(Object o : result){				
						Map add = (Map) o;
						responsJson.append("{\"key\":\""+add.get("PURPOSE_ID")+"\",\"value\": \""+add.get("PURPOSE_NAME")+"\",\"isDefault\":\""+add.get("IS_DEFAULT")+"\", \"active\": \""+add.get("ACTIVE")+"\"},");
					}
					responsJson.deleteCharAt(responsJson.length() - 1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			responsJson.append("],\"PRD_MST\":[");
			
			try {
				//SQLQuery query=session.createSQLQuery("SELECT CONFIGID,APPLICATIONNAME,PARAMID,PARAMNAME,PARAMVALUE,STATUS FROM QC_MASTER_FINAL.DBO.QM_SYS_CONFIGURATION_LEAD WHERE STATUS ='A' AND COMPANY_ID  ='"+companyId+"' AND Update_Datetime >= format(cast('26-SEP-2016' as date), 'MM/dd/yyyy')");
				SQLQuery query = session.createSQLQuery("SELECT QP.PRODUCTID,QP.PRODNAME,QP.PARENTPRODID,QP.PRODTYPEID,QP.ACTIVE,QP.INT_RATE,QP.INTRESTOFFSET,QP.MAX_LOANVALUE,QP.MIN_LOANVALUE,QP.MAX_AGE,QP.MAX_LOANTOVALUE,QP.MIN_AGE,QP.MIN_LOANTOVALUE,QP.MIN_LOAN_TENURE,QP.MAX_LOAN_TENURE FROM QC_PROSPECT_MASTER.QM_PRODUCT QP where QP.ACTIVE = 'A' AND Update_Datetime >=TO_DATE('"+syncDate+"','dd-MM-yyyy') ");
				query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
				result = query.list();	
				if(result != null && result.size() > 0){
					for(Object o : result){				
						Map add = (Map) o;
						responsJson.append("{\"key\":\""+add.get("PRODUCTID")+"\",\"parentkey\":\""+add.get("PARENTPRODID")+"\",\"value\": \""+add.get("PRODNAME")+"\",\"active\":\""+add.get("ACTIVE")+"\",\"prodtypeid\": \""+add.get("PRODTYPEID")+"\",\"interestoffset\":\""+add.get("INTRESTOFFSET")+"\",\"intrate\":\""+add.get("INT_RATE")+"\",\"minltv\": \""+add.get("MIN_LTV")+"\",\"maxloanvalue\":\""+add.get("MAX_LOANVALUE")+"\",\"maxage\": \""+add.get("MAX_AGE")+"\",\"maxloantovalue\":\""+add.get("MAX_LOANTOVALUE")+"\",\"minloanvalue\":\""+add.get("MIN_LOANVALUE")+"\",\"minage\": \""+add.get("MIN_AGE")+"\",\"minloantovalue\":\""+add.get("MIN_LOANTOVALUE")+"\",\"maxltv\": \""+add.get("MAX_LTV")+"\",\"minloantenure\":\""+add.get("MIN_LOAN_TENURE")+"\",\"maxloantenure\":\""+add.get("MAX_LOAN_TENURE")+"\"},");
					}
					responsJson.deleteCharAt(responsJson.length() - 1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			responsJson.append("],\"QM_USER\":[");
		
			try {
				//SQLQuery query=session.createSQLQuery("SELECT CONFIGID,APPLICATIONNAME,PARAMID,PARAMNAME,PARAMVALUE,STATUS FROM QC_MASTER_FINAL.DBO.QM_SYS_CONFIGURATION_LEAD WHERE STATUS ='A' AND COMPANY_ID  ='"+companyId+"' AND Update_Datetime >= format(cast('26-SEP-2016' as date), 'MM/dd/yyyy')");
				SQLQuery query = session.createSQLQuery("select QU.USERID AS USERID, QU.USERFNAME AS FIRSTNAME, QU.USERMNAME AS MIDDLENAME, QU.USERLNAME AS LASTNAME, QU.LOGINNAME AS LOGINNAME, QU.SUPERVISORID AS SUPERVISORID, QU.DESIGNATION AS DESIGNATION, QU.ACTIVE AS ACTIVE  from QC_USER_AUTH.QM_USER QU where QU.ACTIVE = 'A'   AND QU.Update_Datetime >=TO_DATE('"+syncDate+"','dd-MM-yyyy') ");
				query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
				result = query.list();	
				if(result != null && result.size() > 0){
					for(Object o : result){				
						Map add = (Map) o;
						responsJson.append("{\"USERID\":\""+add.get("USERID")+"\",\"FIRSTNAME\":\""+add.get("FIRSTNAME")+"\",\"MIDDLENAME\": \""+add.get("MIDDLENAME")+"\",\"LASTNAME\":\""+add.get("LASTNAME")+"\",\"LOGINNAME\": \""+add.get("LOGINNAME")+"\",\"SUPERVISORID\":\""+add.get("SUPERVISORID")+"\",\"DESIGNATION\":\""+add.get("DESIGNATION")+"\",\"ACTIVE\": \""+add.get("ACTIVE")+"\"},");
					}
					responsJson.deleteCharAt(responsJson.length() - 1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			
			
			responsJson.append("]}}}");
			resJson = responsJson.toString().replaceAll("\\\\", "");

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("MasterForMobileDaoImpl | getAllMasters | :- error -:- " + e.getMessage());
		}finally{
			if(session!=null){
				session.close();
			}
		}
		logger.info("MasterForMobileDaoImpl | getAllMasters() | :- END with Response Json -:-:- "+resJson);
		return resJson;
	}

	@Transactional
	public String saveAgentSummary(String requestJson) {
		logger.info("MasterForMobileDaoImpl | saveAgentSummary() | :- START");
		String  resJson="{\"status\":\"F\",\"message\":\"Fail to save agentSummary\"}";

		CallableStatement cstmt = null;
		Connection con=null;

		Session session=null;
		try {
			JSONObject jobj = new JSONObject(requestJson);
			/*DateFormat dateFormatMDY = new SimpleDateFormat("dd-MMM-yy hh.mm.ss a");//SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			Date now = new Date();
			String vDateMDY = dateFormatMDY.format(now);
			 String vDateMDYSQL =  vDateMDY ;
			 java.sql.Date date = new java.sql.Date(0000-00-00);*/
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			//String formattedDate = dateFormat.format(new Date().toString());
			String formattedDate = dateFormat.format(dateFormat.parse(jobj.getString("actionDateAndTime")));


			session = sessionFactory.openSession();
			con = ConnectionFromSession.getJavaSqlConnectionFromHibernateSession(session);				
			cstmt = con.prepareCall("{call QC_PROSPECT.PR_SAVE_ACTIVITY_LOG_DATA(?,?,?,TO_TIMESTAMP(?, 'DD-MM-YYYY HH24:MI:SS'),?,?, ?,?,?, ?,?,?, ?,?)}");
			if(jobj.getString("userId")!=null || !jobj.getString("userId").equalsIgnoreCase("")){
				cstmt.setInt(1, Integer.parseInt(jobj.getString("userId")));
			}else{
				cstmt.setNull(1, java.sql.Types.INTEGER);
			}
			logger.info("1 type=in value="+jobj.getString("userId"));
			if(jobj.getString("leadId")!=null && !jobj.getString("leadId").equalsIgnoreCase("")){
				cstmt.setInt(2, Integer.parseInt(jobj.getString("leadId")));
			}else{
				cstmt.setNull(2, java.sql.Types.INTEGER);
			}
			logger.info("2 type=in value="+jobj.getString("leadId"));
			if(jobj.getString("actionId")!=null && !jobj.getString("actionId").equalsIgnoreCase("")){
				cstmt.setInt(3, Integer.parseInt(jobj.getString("actionId")));
			}else{
				cstmt.setNull(3, java.sql.Types.INTEGER);
			}
			logger.info("3 type=in value="+jobj.getString("actionId"));
			//docList.getReceivingDate().toString()

			cstmt.setString(4, formattedDate);
			//cstmt.setString(4,dateFormat.format(new Date()));
			logger.info("4 type=in value="+jobj.getString("actionDateAndTime"));


			if(jobj.getString("latitude")!=null && !jobj.getString("latitude").equalsIgnoreCase("")){
				cstmt.setBigDecimal(5, new BigDecimal(jobj.getString("latitude")));
			}else{
				cstmt.setNull(5, java.sql.Types.DOUBLE);
			}
			logger.info("5 type=in value="+jobj.getString("latitude"));
			if(jobj.getString("longitude")!=null && !jobj.getString("longitude").equalsIgnoreCase("")){
				cstmt.setBigDecimal(6, new BigDecimal(jobj.getString("longitude")));
			}else{
				cstmt.setNull(6, java.sql.Types.DOUBLE);
			}
			logger.info("6 type=in value="+jobj.getString("longitude"));
			if(jobj.getString("distanceTravelled")!=null && !jobj.getString("distanceTravelled").equalsIgnoreCase("")){
				cstmt.setBigDecimal(7, new BigDecimal(jobj.getString("distanceTravelled")));
			}else{
				cstmt.setNull(7, java.sql.Types.DOUBLE);
			}
			logger.info("7 type=in value="+jobj.getString("distanceTravelled"));

			if(jobj.getString("preLatitude")!=null && !jobj.getString("preLatitude").equalsIgnoreCase("")){
				cstmt.setBigDecimal(8, new BigDecimal(jobj.getString("preLatitude")));
			}else{
				cstmt.setNull(8, java.sql.Types.DOUBLE);
			}
			logger.info("8 type=in value="+jobj.getString("preLatitude"));

			if(jobj.getString("preLongitude")!=null && !jobj.getString("preLongitude").equalsIgnoreCase("")){
				cstmt.setBigDecimal(9, new BigDecimal(jobj.getString("preLongitude")));
			}else{
				cstmt.setNull(9, java.sql.Types.DOUBLE);
			}
			logger.info("9 type=in value="+jobj.getString("preLongitude"));
			cstmt.setString(10,jobj.getString("dispositionName"));
			logger.info("10 type=in value="+jobj.getString("dispositionName"));
			cstmt.setString(11, jobj.getString("active"));
			logger.info("11 type=in value="+jobj.getString("active"));
			cstmt.registerOutParameter(12, java.sql.Types.VARCHAR);
			logger.info("12 type=out");
			cstmt.registerOutParameter(13, java.sql.Types.VARCHAR);
			logger.info("13 type=out");
			cstmt.setString(14, jobj.getString("actionName"));
			logger.info("14 type=in value="+jobj.getString("actionName"));
			cstmt.execute();

			resJson="{\"status\":\""+cstmt.getString(12)+"\",\"message\":\""+cstmt.getString(13)+"\"}";
		} catch (Exception e) {
			e.printStackTrace();
			resJson="{\"status\":\"F\",\"message\":\""+e.getMessage()+"\"}";
			logger.error("MasterForMobileDaoImpl | saveAgentSummary() | :- error -:- " + e.getMessage());
		}finally{
			try {
				if(cstmt!=null){
					cstmt.close();
					cstmt=null;				
				}
				if(con!=null){
					con.close();
					con=null;
				}if(session!=null){
					session.close();
					session=null;
				}
			} catch (SQLException e) {
				logger.error("MasterForMobileDaoImpl | saveAgentSummary() | :- error -:- " + e.getMessage());
				//	e.printStackTrace();
			}
		}
		logger.info("MasterForMobileDaoImpl | saveAgentSummary() | :- END with Response Json -:-:- "+resJson);
		return resJson;
	}	


	@Transactional
	public String getAgentSummary(String requestJson) {
		logger.info("MasterForMobileDaoImpl | getAgentSummary() | :- START ");
		String responseJson="{\"status\":\"F\",\"message\":\"records not found\"}";
		Session session = null;		
		Connection con = null;		
		ResultSet set =null;
		CallableStatement cstmt = null;	
		try {
			JSONObject restJson=new JSONObject(requestJson);
			String startDate ="";
			String endDate ="";
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			if(restJson.getString("actionName").equalsIgnoreCase("Monthly")){
				startDate = dateFormat.format(dateFormat.parse(restJson.getString("actionFromDate")));
				endDate = dateFormat.format(dateFormat.parse(restJson.getString("actionToDate")));
			}
			//	int count=0;
			StringBuffer buffer=new StringBuffer();
			session=sessionFactory.openSession();
			con = ConnectionFromSession.getJavaSqlConnectionFromHibernateSession(session);
			cstmt=con.prepareCall("{call QC_PROSPECT.PR_GET_ACTIVITY_LOG_DATA(?,?,?, TO_TIMESTAMP(?, 'DD-MM-YYYY'),TO_TIMESTAMP(?, 'DD-MM-YYYY'),?, ?,?,?)}");
			cstmt.setString(1, restJson.getString("identifier"));
			logger.info("1 type=in value="+restJson.getString("identifier"));
			cstmt.setInt(2, Integer.parseInt(restJson.getString("userId")));
			logger.info("2 type=in value="+Integer.parseInt(restJson.getString("userId")));
			cstmt.setString(3, restJson.getString("actionName").toUpperCase());
			logger.info("3 type=in value="+restJson.getString("actionName"));
			/*if(formattedDate1!=null || !formattedDate1.equalsIgnoreCase("")){*/
			cstmt.setString(4, startDate);
			/*}else{
				cstmt.setNull(4, java.sql.Types.DATE);
			}*/
			logger.info("4 type=in value="+startDate);
			/*if(formattedDate2!=null || !formattedDate2.equalsIgnoreCase("")){*/
			cstmt.setString(5, endDate);
			/*}else{
					cstmt.setNull(5, java.sql.Types.DATE);
				}*/
			logger.info("5 type=in value="+endDate);
			cstmt.registerOutParameter(6, java.sql.Types.VARCHAR);
			logger.info("6 type=out value="+java.sql.Types.VARCHAR);
			cstmt.registerOutParameter(7, java.sql.Types.VARCHAR);
			logger.info("7 type=out value="+java.sql.Types.VARCHAR);
			cstmt.registerOutParameter(8, java.sql.Types.CLOB);
			logger.info("8 type=out value="+java.sql.Types.CLOB);
			cstmt.registerOutParameter(9, OracleTypes.CURSOR);
			logger.info("9 type=out value="+OracleTypes.CURSOR);
			cstmt.execute();
			String status=cstmt.getString(6);
			String message=cstmt.getString(7);
			System.out.println("status -:- "+status);
			System.out.println("status -:- "+message);
			if(status!=null && !status.equalsIgnoreCase("F") && restJson.getString("actionName").equalsIgnoreCase("DAILY")){
				set = (ResultSet) cstmt.getObject(9);
			}	
			while(set!=null && set.next()){
				//count=1;
				String temp="";
				buffer.append("{\"USER_ID\":\""+set.getString("USER_ID")+"\",\"USER_NAME\":\""+set.getString("USER_NAME")+"\",\"NO_OF_CUSTOMER\":\""+set.getString("NO_OF_CUSTOMER")+"\",\"LOGINTIME\":\""+set.getString("LOGINTIME")+"\",\"LOGOUTTIME\":\""+set.getString("LOGOUTTIME")+"\",\"DISTANCE_TRAVELLED\":\""+set.getString("DISTANCE_TRAVELLED")+"\",\"ACTION_DATE\":\""+set.getString("ACTION_DATE")+"\",\"dailyTravelSummary\": [");
				if(set.getString("dailyTravelSummary")!=null && !set.getString("dailyTravelSummary").equalsIgnoreCase("")){
					
					String count = set.getString("DAILYTRAVELSUMMARY_COUNT");
					
					if(!CommonUtils.toString(count).equalsIgnoreCase("1")){
						temp=XML.toJSONObject(set.getString("dailyTravelSummary"))+"";
						if(temp!=null && temp.length()>0){
							temp=temp.substring(18, temp.length()-3);
						}	
					}else{
						temp=XML.toJSONObject(set.getString("dailyTravelSummary"))+"";
						if(temp!=null && temp.length()>0){
							temp=temp.substring(17, temp.length()-2);
						}
					}						
				}
				buffer.append(temp+"]},");
			}
			if(buffer.length()>0 && "DAILY".equalsIgnoreCase(restJson.getString("actionName"))){
				buffer.deleteCharAt(buffer.length() - 1);	
				responseJson="{\"status\":\""+cstmt.getString(6)+"\",\"message\":\""+cstmt.getString(7)+"\",\"dailyAgentSummary\":["+buffer+"]}";
			}else if("DAILY".equalsIgnoreCase(restJson.getString("actionName"))){
				responseJson="{\"status\":\""+cstmt.getString(6)+"\",\"message\":\""+cstmt.getString(7)+"\",\"dailyAgentSummary\":[]}";
			}else{
				String monthlyDetails=cstmt.getString(8);
				String temp="{}";
				if(monthlyDetails !=null && monthlyDetails.contains("<ROW>") && monthlyDetails.contains("</ROW>")){
					temp=monthlyDetails.substring(monthlyDetails.indexOf("<ROW>")+5,monthlyDetails.indexOf("</ROW>"));
					temp=XML.toJSONObject(temp)+"";
				}
				responseJson ="{\"status\": \""+cstmt.getString(6)+"\",\"message\": \""+cstmt.getString(7)+"\",\"MONTHLY\":"+temp+"}"; 
			}
		} catch (Exception e) {
			logger.error("MasterForMobileDaoImpl | getAgentSummary() | :- error -:- " + e.getMessage());
			e.printStackTrace();
			responseJson="{\"status\":\"F\",\"message\":\""+e.getMessage()+"\"}";
		}
		finally{
			try {
				if(set!=null){
					set.close();
				}
				if(cstmt!=null){
					cstmt.close();
					cstmt=null;				
				}
				if(con!=null){
					con.close();
					con=null;
				}if(session!=null){
					session.close();
					session=null;
				}
			} catch (SQLException e) {
				logger.error("MasterForMobileDaoImpl | getAgentSummary() | :- error -:- " + e.getMessage());
				//	e.printStackTrace();
			}
		}
		logger.info("MasterForMobileDaoImpl | getAgentSummary() | :- END ");
		return responseJson;
	}


	public String getlatiAndLongiTude(Session session,JSONObject restJson) {
		logger.info("MasterForMobileDaoImpl | getlatiAndLongiTude() | :- START ");
		StringBuffer customerDeatils=new StringBuffer();
		try {
			//String queryStr="SELECT DTL.PRE_ACTION_LATITUDE,DTL.PRE_ACTION_LONGITUDE,DTL.ACTION_LONGITUDE,DTL.ACTION_LATITUDE,DTL.ACTION_NAME,DTL.DISPOSITION_CODE,AC.DISPLAY_ON_WEB,DTL.LEAD_ID,NVL(PDE.FNAME, '') || NVL(' ' || PDE.MNAME, '') || NVL(' ' || PDE.LNAME, '') CUSTOMER_NAME FROM QC_PROSPECT.QT_LOG_TRANS_DTL DTL,QC_PROSPECT.QT_PERSONAL_DETAILS PDE,QC_PROSPECT_MASTER.QM_CASE_ACTION AC WHERE DTL.USER_ID='"+restJson.getString("userId")+"' AND PDE.CASE_ID=DTL.LEAD_ID  AND AC.ACTION_ID=DTL.DISPOSITION_CODE AND TO_CHAR(DTL.ACTION_DATE_TIME,'YYYY-MM-DD')='"+restJson.getString("actionFromDate")+"'";
			String queryStr="SELECT DTL.PRE_ACTION_LATITUDE,DTL.PRE_ACTION_LONGITUDE,DTL.ACTION_LONGITUDE,DTL.ACTION_LATITUDE,DTL.ACTION_NAME,DTL.DISPOSITION_CODE,AC.DISPLAY_ON_WEB,DTL.LEAD_ID,NVL(PDE.FNAME, '') || NVL(' ' || PDE.MNAME, '') || NVL(' ' || PDE.LNAME, '') CUSTOMER_NAME FROM QC_PROSPECT.QT_LOG_TRANS_DTL DTL LEFT OUTER JOIN QC_PROSPECT.QT_PERSONAL_DETAILS PDE ON (TO_CHAR(PDE.CASE_ID) = DTL.LEAD_ID) JOIN QC_PROSPECT_MASTER.QM_CASE_ACTION AC ON (TO_CHAR(AC.ACTION_ID) = DTL.DISPOSITION_CODE) WHERE DTL.USER_ID ='1000000023' AND DTL.ACTIVE='Y' AND TO_CHAR(DTL.ACTION_DATE_TIME,'YYYY-MM-DD')='"+restJson.getString("actionFromDate")+"'";
			Query query=session.createSQLQuery(queryStr);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List list=query.list();
			if(list.size()<=0){
				//queryStr="SELECT DTL.PRE_ACTION_LATITUDE,DTL.PRE_ACTION_LONGITUDE,DTL.ACTION_LONGITUDE,DTL.ACTION_LATITUDE,DTL.ACTION_NAME,DTL.DISPOSITION_CODE,AC.DISPLAY_ON_WEB,DTL.LEAD_ID,NVL(PDE.FNAME, '') || NVL(' ' || PDE.MNAME, '') || NVL(' ' || PDE.LNAME, '') CUSTOMER_NAME FROM QC_PROSPECT.QT_LOG_TRANS_DTL DTL,QC_PROSPECT.QT_PERSONAL_DETAILS PDE,QC_PROSPECT_MASTER.QM_CASE_ACTION AC WHERE DTL.USER_ID='"+restJson.getString("userId")+"' AND PDE.CASE_ID=DTL.LEAD_ID  AND AC.ACTION_ID=DTL.DISPOSITION_CODE AND TO_CHAR(DTL.ACTION_DATE_TIME,'YYYY-MM-DD')=(SELECT TO_CHAR(MAX(ACTION_DATE_TIME),'YYYY-MM-DD') FROM QC_PROSPECT.QT_LOG_TRANS_DTL WHERE USER_ID ='"+restJson.getString("userId")+"')";
				queryStr="SELECT DTL.PRE_ACTION_LATITUDE,DTL.PRE_ACTION_LONGITUDE,DTL.ACTION_LONGITUDE,DTL.ACTION_LATITUDE,DTL.ACTION_NAME,DTL.DISPOSITION_CODE,AC.DISPLAY_ON_WEB,DTL.LEAD_ID,NVL(PDE.FNAME, '') || NVL(' ' || PDE.MNAME, '') || NVL(' ' || PDE.LNAME, '') CUSTOMER_NAME FROM QC_PROSPECT.QT_LOG_TRANS_DTL DTL LEFT OUTER JOIN QC_PROSPECT.QT_PERSONAL_DETAILS PDE ON (TO_CHAR(PDE.CASE_ID) = DTL.LEAD_ID) JOIN QC_PROSPECT_MASTER.QM_CASE_ACTION AC ON (TO_CHAR(AC.ACTION_ID) = DTL.DISPOSITION_CODE) WHERE DTL.USER_ID ='"+restJson.getString("userId")+"' AND DTL.ACTIVE='Y' AND TO_CHAR(DTL.ACTION_DATE_TIME,'DD-MON-YYYY')=(SELECT TO_CHAR(MAX(DTL1.ACTION_DATE_TIME),'DD-MON-YYYY') FROM QC_PROSPECT.QT_LOG_TRANS_DTL DTL1 WHERE DTL1.USER_ID ='"+restJson.getString("userId")+"')"; 
				Query query1=session.createSQLQuery(queryStr);
				query1.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
				list=query1.list();		
			}
			Iterator<Map<String, String>> itr=list.iterator();
			int count=0;
			while(itr.hasNext()){
				count++;
				Map<String, String> map=itr.next();
				customerDeatils.append("{\"actionName\":\""+map.get("ACTION_NAME")+"\",\"leadId\":\""+map.get("LEAD_ID")+"\",\"dispositionId\":\""+map.get("DISPOSITION_CODE")+"\",\"dispositionName\":\""+map.get("DISPLAY_ON_WEB")+"\",\"customerName\":\""+map.get("CUSTOMER_NAME")+"\",\"preGeoLocation\":\"("+map.get("PRE_ACTION_LATITUDE")+" "+map.get("PRE_ACTION_LONGITUDE")+")\",\"geoLocation\":\"("+map.get("ACTION_LATITUDE")+" "+map.get("ACTION_LONGITUDE")+")\"},");
			}
			if(count>0){
				customerDeatils.deleteCharAt(customerDeatils.length() - 1);
			}else{
				return "{\"actionName\": \"LOGIN\",\"leadId\": \"\",\"dispositionId\": \"\",\"dispositionName\": \"\",\"customerName\": \"\",\"preGeoLocation\": \"(null null)\",\"geoLocation\": \"(28.612712 77.3877973)\"},{\"actionName\": \"LEADUPDATE\",\"leadId\": \"1001013653\",\"dispositionId\": \"8\",\"dispositionName\": \"DOCUMENT_PICKED\",\"customerName\": \"AIMS PROMOTERS PVT LTD \",\"preGeoLocation\": \"(null null)\",\"geoLocation\": \"(28.612712 77.3877973)\"},{\"actionName\": \"NEW LEAD\",\"leadId\": \"1000316204\",\"dispositionId\": \"19\",\"dispositionName\": \"NEW LEAD\",\"customerName\": \"M P MANOJ\",\"preGeoLocation\": \"(null null)\",\"geoLocation\": \"(28.612712 77.3877973)\"},{\"actionName\": \"LOGOFF\",\"leadId\": \"\",\"dispositionId\": \"\",\"dispositionName\": \"\",\"customerName\": \" \",\"preGeoLocation\": \"(null null)\",\"geoLocation\": \"(28.612663 77.3877524)\"}";
			}
			//System.out.println(customerDeatils+"");
		} catch (Exception e) {
			logger.error("MasterForMobileDaoImpl | getlatiAndLongiTude() | :- error -:- " + e.getMessage());
			//e.printStackTrace();
			return "{\"actionName\": \"LOGIN\",\"leadId\": \"\",\"dispositionId\": \"\",\"dispositionName\": \"\",\"customerName\": \"\",\"preGeoLocation\": \"(null null)\",\"geoLocation\": \"(28.612712 77.3877973)\"},{\"actionName\": \"LEADUPDATE\",\"leadId\": \"1001013653\",\"dispositionId\": \"8\",\"dispositionName\": \"DOCUMENT_PICKED\",\"customerName\": \"AIMS PROMOTERS PVT LTD \",\"preGeoLocation\": \"(null null)\",\"geoLocation\": \"(28.612712 77.3877973)\"},{\"actionName\": \"NEW LEAD\",\"leadId\": \"1000316204\",\"dispositionId\": \"19\",\"dispositionName\": \"NEW LEAD\",\"customerName\": \"M P MANOJ\",\"preGeoLocation\": \"(null null)\",\"geoLocation\": \"(28.612712 77.3877973)\"},{\"actionName\": \"LOGOFF\",\"leadId\": \"\",\"dispositionId\": \"\",\"dispositionName\": \"\",\"customerName\": \" \",\"preGeoLocation\": \"(null null)\",\"geoLocation\": \"(28.612663 77.3877524)\"}";
		}
		logger.info("MasterForMobileDaoImpl | getlatiAndLongiTude() | :- END  with Response Json -:-:- "+customerDeatils);
		return customerDeatils+"";
	}
}