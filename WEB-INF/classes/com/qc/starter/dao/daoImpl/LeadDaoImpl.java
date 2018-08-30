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
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.qc.starter.basic.CommonUtils;
import com.qc.starter.dao.LeadDao;
import com.qc.starter.entity.LeadEntity;
import com.qc.starter.entity.UserEntity;

@Repository
public class LeadDaoImpl implements LeadDao {
	private static final Logger logger = Logger.getLogger(LeadDaoImpl.class.getName());
	@Autowired
	SessionFactory sessionFactory;
	@Autowired
	HttpSession httpSession;

	@Transactional
	public List getLeadDetail(Integer leadId) {
		logger.info("LeadDaoImpl | getLeadDetail() | START ");
		/*
		 * Connection con=null; PreparedStatement stmt=null; ResultSet rt=null;
		 * // Session session = sessionFactory.openSession(); try{ Map
		 * prospectMap=ConnectionUtil.dbConnectionMapProspect; con =
		 * DBConnection.getConnection(prospectMap); String sql =
		 * "SELECT QC.CASE_ID LEAD_ID,  QC.CASE_CODE LEAD_CODE,  (SELECT QCP.PRODNAME  FROM QC_PROSPECT_MASTER.QM_CASE_PRODUCT QCP  WHERE QCP.PRODTYPEID=1  AND QCP.PROD_ID       = QC.QUEUE_ID  ) QUEUE,  (SELECT QCP.PRODNAME  FROM QC_PROSPECT_MASTER.QM_CASE_PRODUCT QCP  WHERE QCP.PRODTYPEID=2  AND QCP.PROD_ID       = QC.FACILITY_REQ_ID  ) FACILITY_REQUESTED,  TRIM(REPLACE(QPD.FNAME  ||' '  ||QPD.MNAME  ||' '  ||QPD.LNAME,'  ',' ')) CUSTOMER_NAME,  QC.GENERATION_DT,  (SELECT SQ.SUB_QUEUE  FROM QC_PROSPECT_MASTER.QM_SUB_QUEUE SQ  WHERE SQ.SUB_QUEUE_ID =QC.SUB_QUEUE_ID  ) SUB_QUEUE,  QC.AMOUNT,  (SELECT TRIM(REPLACE(QU.USERFNAME    ||' '    ||QU.USERMNAME    ||' '    ||QU.USERMNAME,'  ',' '))  FROM QC_PROSPECT_MASTER.QM_USER QU  WHERE QU.USERID= QC.ALLOCATED_TO  ) ALLOCATED_TO ,  (SELECT QS.SOURCE_NAME  FROM QC_PROSPECT_MASTER.QM_SOURCE QS  WHERE QS.CASE_SOURCE_ID= QC.SOURCE_ID  ) SOURCE,  (SELECT QB.BANK_NAME FROM QC_PROSPECT_MASTER.QM_BANK QB WHERE QB.BANK_ID =QC.BANK_ID  ) BANK ,  (SELECT    (SELECT QC1.CITYMASTERNAME    FROM QC_PROSPECT_MASTER.QM_CITYMASTER QC1    WHERE QC1.CITYMASTERID= QA.CITY    )  FROM QC_PROSPECT.QT_ADDRESS QA  WHERE QA.PERSONAL_DTL_ID= QPD.PERSONAL_DTL_ID  AND ADDRESS_TYPE        = 1000000001  ) CITY,  (SELECT QCA.CAMPAIGN_NAME  FROM QC_PROSPECT_MASTER.QM_CAMPAIGN QCA  WHERE QCA.CAMPAIGN_ID=QC.CAMPAIGN_ID  ) CAMPAIGN,  QC.ROI ,  (SELECT QOC.OCCUPATIONNAME  FROM QC_PROSPECT_MASTER.QM_OCCUPATION QOC  WHERE QOC.OCCUPATIONID= QPD.OCCUPATION_TYPE  ) OCCUPATION ,  QC.TAG_INFO_A,  QC.TAG_INFO_B FROM QC_PROSPECT.QT_CASE QC,  QC_PROSPECT.QT_PERSONAL_DETAILS QPD WHERE QPD.CASE_ID= QC.CASE_ID AND QC.CASE_ID = ?"
		 * ; //+ leadId; stmt=con.prepareStatement(sql); stmt.setInt(1, leadId);
		 * rt=stmt.executeQuery(); while(rt.next()){ //
		 * LEAD_ID,LEAD_CODE,QUEUE,FACILITY_REQUESTED,CUSTOMER_NAME,
		 * GENERATION_DT,SUB_QUEUE,AMOUNT,ALLOCATED_TO,SOURCE,BANK,CITY,CAMPAIGN
		 * ,ROI,OCCUPATION,TAG_INFO_A,TAG_INFO_B } //SQLQuery query =
		 * session.createSQLQuery(sql);
		 * //query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP); //List
		 * list = query.list(); if(session!=null){ session.close(); }
		 * }catch(Exception e){ e.printStackTrace(); } if (list.size() != 0)
		 * return list; else return null;
		 */
		logger.info("LeadDaoImpl | getLeadDetail() | END");
		return null;
	}

	@Transactional
	public List<LeadEntity> getUserLeadList(Integer userId) {
		logger.info("LeadDaoImpl | getUserLeadList() | START ");
		Session session = null;
		Query query = null;
		List<LeadEntity> leadEntities = new ArrayList<LeadEntity>();
		try {
			session = sessionFactory.openSession();
			UserEntity userEntity = (UserEntity) httpSession.getAttribute("UserDetails");
			String hql = "from LeadEntity where allocatedTo = :allocatedTo and companyId=:companyId";
			query = session.createQuery(hql);
			query.setParameter("allocatedTo", userId.toString()).setParameter("companyId", userEntity.getCompanyId());
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List result = query.list();
			for (Object o : result) {
				Map map = (Map) o;
				LeadEntity leadEntity = new LeadEntity();
				leadEntity.setCaseId(map.get("CASE_ID").toString());
				leadEntity.setCaseCode(map.get("CASE_CODE").toString());
				leadEntity.setQueueId(map.get("QUEUE_ID").toString());
				leadEntity.setSubQueueId(map.get("SUB_QUEUE_ID").toString());
				leadEntity.setProductId(map.get("PROD_ID").toString());
				leadEntity.setGenerationDate(map.get("GENERATION_DT").toString());
				leadEntity.setAmount(map.get("AMOUNT").toString());
				leadEntity.setAllocatedTo(map.get("ALLOCATED_TO").toString());
				leadEntity.setFacilityRequiredId(map.get("FACILITY_REQ_ID").toString());
				leadEntity.setSourceId(map.get("SOURCE_ID").toString());
				leadEntity.setBankId(map.get("BANK_ID").toString());
				leadEntity.setCampaignId(map.get("CAMPAIGN_ID").toString());
				leadEntity.setROI(map.get("ROI").toString());
				leadEntity.setTenorYear(map.get("TENOR_YEAR").toString());
				leadEntity.setTenorMonth(map.get("TENOR_MONTH").toString());
				leadEntity.setEMI(map.get("EMI").toString());
				leadEntity.setTagInfoA(map.get("TAG_INFO_A").toString());
				leadEntity.setTagInfoB(map.get("TAG_INFO_B").toString());
				leadEntity.setEscalate(map.get("ESCALATE").toString());
				leadEntity.setRefer(map.get("REFER").toString());
				leadEntity.setCoAllocate(map.get("CO_ALLOCATE").toString());
				leadEntity.setStatus(map.get("STATUS").toString());
				leadEntity.setCompanyId(map.get("COMPANY_ID").toString());
				leadEntity.setXsellCaseId(map.get("XSELL_CASE_ID").toString());
				leadEntity.setXsellby(map.get("XSELL_BY").toString());
				leadEntity.setRemarks(map.get("REMARKS").toString());
				leadEntity.setCreatedBy(map.get("CREATED_BY").toString());
				leadEntity.setCreatedDate(map.get("CREATED_DATE").toString());
				leadEntity.setCreatedSystemDate(map.get("CREATED_SYS_DATE").toString());
				leadEntity.setUpdatedBy(map.get("UPDATED_BY").toString());
				leadEntity.setUpdateDate(map.get("UPDATED_DATE").toString());
				leadEntity.setDispositionId(map.get("DISPOSITION_ID").toString());
				leadEntities.add(leadEntity);
				leadEntity = null;
			}
			if (session != null) {
				session.close();
			}
		} catch (Exception e) {
			logger.error("LeadDaoImpl | getUserLeadList() | Error:::" + e.getMessage());
			e.printStackTrace();
		}
		logger.info("LeadDaoImpl | getUserLeadList() | END");
		return leadEntities;
	}

	@Transactional
	public LeadEntity getLead(int caseId, String companyId) {
		logger.info("LeadDaoImpl | getLead() | START");
		Session session = null;
		Query query = null;
		LeadEntity leadEntity = new LeadEntity();
		try {
			session = sessionFactory.openSession();
			// UserEntity userEntity = (UserEntity)
			// httpSession.getAttribute("UserDetails");
			query = session.createQuery("from LeadEntity where caseId = " + caseId + " and companyId=" + companyId);
			List<LeadEntity> list = query.list();
			if (list.size() > 0) {
				leadEntity = list.get(0);
			}
			if (session != null) {
				session.close();
			}
		} catch (Exception e) {
			logger.error("LeadDaoImpl | getLead() | error:::" + e.getMessage());
			e.printStackTrace();
		}
		logger.info("LeadDaoImpl | getLead() | END");
		return leadEntity;
	}

	@Transactional
	public int checkLeadAvail(String caseId) {
		logger.info("LeadDaoImpl | checkLeadAvail() | START");
		Session session = null;
		int count = 0;
		Query query = null;
		try {
			session = sessionFactory.openSession();
			UserEntity userEntity = (UserEntity) httpSession.getAttribute("UserDetails");
			query = session.createQuery("from LeadEntity where caseCode = " + "'" + caseId.toString() + "'"
					+ " and companyId=" + userEntity.getCompanyId() + "");
			List<LeadEntity> list = query.list();
			count = list.size();
		} catch (Exception e) {
			logger.error("LeadDaoImpl | checkLeadAvail() | error::::" + e.getMessage());
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		logger.info("LeadDaoImpl | checkLeadAvail() | END");
		if (count > 0)
			return 1;
		else
			return 0;

	}

	@Transactional
	public LeadEntity getLeadCode(String caseCode) {
		logger.info("LeadDaoImpl | getLeadCode() | START");
		Session session = null;
		LeadEntity leadEntity = new LeadEntity();
		SQLQuery query = null;
		try {
			session = sessionFactory.openSession();
			UserEntity userEntity = (UserEntity) httpSession.getAttribute("UserDetails");
			String sql = "SELECT * FROM QC_PROSPECT.QT_CASE WHERE CASE_CODE=" + "'" + caseCode + "'"
					+ " AND COMPANY_ID=" + userEntity.getCompanyId() + "";
			query = session.createSQLQuery(sql);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List result = query.list();
			if (result.size() > 0) {
				for (Object o : result) {
					Map map = (Map) o;
					leadEntity.setCaseId(map.get("CASE_ID") + "");
					leadEntity.setCaseCode(map.get("CASE_CODE") + "");
					leadEntity.setQueueId(map.get("QUEUE_ID") != null ? map.get("QUEUE_ID") + "" : "");
					leadEntity.setSubQueueId(map.get("SUB_QUEUE_ID") != null ? map.get("SUB_QUEUE_ID") + "" : "");
					leadEntity.setProductId(map.get("PROD_ID") != null ? map.get("PROD_ID") + "" : "");
					leadEntity.setGenerationDate(map.get("GENERATION_DT") != null ? map.get("GENERATION_DT") + "" : "");
					leadEntity.setAmount(map.get("AMOUNT") != null ? map.get("AMOUNT") + "" : "");
					leadEntity.setAllocatedTo(map.get("ALLOCATED_TO") != null ? map.get("ALLOCATED_TO") + "" : "");
					leadEntity.setFacilityRequiredId(
							map.get("FACILITY_REQ_ID") != null ? map.get("FACILITY_REQ_ID") + "" : "");
					leadEntity.setSourceId(map.get("SOURCE_ID") != null ? map.get("SOURCE_ID") + "" : "");
					leadEntity.setBankId(map.get("BANK_ID") != null ? map.get("BANK_ID") + "" : "");
					leadEntity.setCampaignId(map.get("CAMPAIGN_ID") != null ? map.get("CAMPAIGN_ID") + "" : "");
					leadEntity.setROI(map.get("ROI") != null ? map.get("ROI") + "" : "");
					leadEntity.setTenorYear(map.get("TENOR_YEAR") != null ? map.get("TENOR_YEAR") + "" : "");
					leadEntity.setTenorMonth(map.get("TENOR_MONTH") != null ? map.get("TENOR_MONTH") + "" : "");
					leadEntity.setEMI(map.get("EMI") != null ? map.get("EMI") + "" : "");
					leadEntity.setTagInfoA(map.get("TAG_INFO_A") != null ? map.get("TAG_INFO_A") + "" : "");
					leadEntity.setTagInfoB(map.get("TAG_INFO_B") != null ? map.get("TAG_INFO_B") + "" : "");
					leadEntity.setEscalate(map.get("ESCALATE") != null ? map.get("ESCALATE") + "" : "");
					leadEntity.setRefer(map.get("REFER") != null ? map.get("REFER") + "" : "");
					leadEntity.setCoAllocate(map.get("CO_ALLOCATE") != null ? map.get("CO_ALLOCATE") + "" : "");
					leadEntity.setStatus(map.get("STATUS") != null ? map.get("STATUS") + "" : "");
					leadEntity.setCompanyId(map.get("COMPANY_ID") != null ? map.get("COMPANY_ID") + "" : "");
					leadEntity.setXsellCaseId(map.get("XSELL_CASE_ID") != null ? map.get("XSELL_CASE_ID") + "" : "");
					leadEntity.setXsellby(map.get("XSELL_BY") != null ? map.get("XSELL_BY") + "" : "");
					leadEntity.setRemarks(map.get("REMARKS") != null ? map.get("REMARKS") + "" : "");
					leadEntity.setCreatedBy(map.get("CREATED_BY") + "");
					leadEntity.setCreatedDate(map.get("CREATED_DATE") + "");
					leadEntity.setCreatedSystemDate(map.get("CREATED_SYS_DATE") + "");
					leadEntity.setUpdatedBy(map.get("UPDATED_BY") != null ? map.get("UPDATED_BY") + "" : "");
					leadEntity.setUpdateDate(map.get("UPDATED_DATE") != null ? map.get("UPDATED_DATE") + "" : "");
					leadEntity
							.setDispositionId(map.get("DISPOSITION_ID") != null ? map.get("DISPOSITION_ID") + "" : "");
				}
			}
		} catch (Exception e) {
			logger.info("LeadDaoImpl | getLeadCode() | ERROR:::" + e.getMessage());
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		logger.info("LeadDaoImpl | getLeadCode() | END");
		return leadEntity;
	}

	@Transactional
	public boolean getEscalateReferInfo(LeadEntity leadEntity, UserEntity userEntity) {
		logger.info("LeadDaoImpl | getEscalateReferInfo() | START");
		String result = "";
		boolean escalate = false;
		boolean refer = false;
		boolean isBlock = false;
		Session session = null;
		SQLQuery query = null;
		try {
			session = sessionFactory.openSession();
			String sql = "SELECT NVL(ESCALATE,'N') as ESCALATE, NVL(REFER,'N') as REFER FROM QC_PROSPECT.QT_CASE "
					+ " WHERE CASE_ID = " + leadEntity.getCaseId();
			query = session.createSQLQuery(sql);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List list1 = query.list();
			if (list1.size() > 0) {
				Map map = (Map) list1.get(0);
				if (map.get("ESCALATE").toString().equals("Y")) {
					escalate = true;
				}
				if (map.get("REFER").toString().equals("Y")) {
					refer = true;
				}
			}
			query = null;
			String escalatesql = "";
			String refersql = "";
			if (escalate) {
				escalatesql = "SELECT COUNT(1) COUNT FROM QC_PROSPECT.QT_CASE_X_REFF_ESC Y WHERE RESOLVED_DATE "
						+ "IS NULL AND CASE_ID =  " + leadEntity.getCaseId()
						+ " AND  ACTION_ID = (SELECT ACTION_ID FROM "
						+ "QC_PROSPECT_MASTER.QM_CASE_ACTION WHERE ACTION_NAME ='ESCALATE' AND ACTIVE ='A' "
						+ "AND COMPANY_ID = " + userEntity.getCompanyId() + " AND ACTION_TYPE =2) AND ALLOCATED_TO = "
						+ userEntity.getUserid();
				query = session.createSQLQuery(escalatesql);
				query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
				List list2 = query.list();
				if (list2.size() > 0) {
					Map map = (Map) list2.get(0);
					if (map.get("COUNT").toString().trim().equals("1"))
						isBlock = false;
					else
						isBlock = true;
				}
				query = null;
			}
			if (refer) {
				refersql = "SELECT COUNT(1) COUNT FROM QC_PROSPECT.QT_CASE_X_REFF_ESC Y WHERE RESOLVED_DATE IS "
						+ "NULL AND CASE_ID =  " + leadEntity.getCaseId() + " AND  ACTION_ID = (SELECT ACTION_ID "
						+ " FROM QC_PROSPECT_MASTER.QM_CASE_ACTION WHERE ACTION_NAME ='REFER' AND ACTIVE ='A' "
						+ " AND COMPANY_ID = " + userEntity.getCompanyId() + " AND ACTION_TYPE =2) AND ALLOCATED_TO = "
						+ userEntity.getUserid();
				query = session.createSQLQuery(refersql);
				query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
				List list3 = query.list();
				if (list3.size() > 0) {
					Map map = (Map) list3.get(0);
					if (map.get("COUNT").toString().trim().equals("1"))
						isBlock = false;
					else
						isBlock = true;
				}
				query = null;
			}
		} catch (Exception e) {
			logger.error("LeadDaoImpl | getEscalateReferInfo() | error:::" + e.getMessage());
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		logger.info("LeadDaoImpl | getEscalateReferInfo() | END");
		return isBlock;
	}

	@Transactional
	public String updateLeadDetails(String requestJson) {
		logger.info("LeadDaoImpl | updateLeadDetails() | START");
		String syncDate = CommonUtils.getCurrentDate();
		String responseJson = "{\"status\":\"F\",\"syncDate\":\""+syncDate+"\",\"message\":\"Lead Details can not updat\"}";
		Session session = null;		
		try {
			int status = 0;
			JSONObject jsonObject = new JSONObject(requestJson);
			session = sessionFactory.openSession();
			try {
				Query query = session.createSQLQuery("UPDATE QC_PROSPECT.QT_CASE QC set"
						+ " QC.QUEUE_ID=:product,QC.PROD_ID=:product ,QC.FACILITY_REQ_ID=:product,QC.SOURCE_ID=:source,QC.REFERENCE_NAME=:referenceName ,QC.REFERENCE_NUMBER=:referenceNumber,QC.SCHEME_ID=:schemeId ,QC.LOAN_TENURE=:loanTenure,QC.LOAN_AMOUNT=:loanAmount, QC.SUB_QUEUE_ID=:potential,QC.BRANCHID=:branch,QC.PURPOSEOFLOANID=:puposeOfLoan,APPLICANTID=:applicantid ,PROSPECTID=:prospectid,QC.UPDATED_BY=:userId,QC.UPDATED_SYS_DATE =sysdate, qc.updated_date =:actionDate where QC.CASE_ID=:caseId");
				query.setParameter("product", jsonObject.getString("productId"));
				
				query.setParameter("source", jsonObject.getString("source"));
				query.setParameter("referenceName", jsonObject.getString("referenceName"));
				query.setParameter("referenceNumber", jsonObject.getString("referenceNum"));
				
				query.setParameter("schemeId", jsonObject.getString("scheme"));
				query.setParameter("loanTenure", jsonObject.getString("loanTenure"));
				query.setParameter("loanAmount", jsonObject.getString("loanAmount"));								
				query.setParameter("potential", jsonObject.getString("potential"));
				// query.setParameter("loanAmount",
				// jsonObject.getString("loanAmount"));
				query.setParameter("branch", jsonObject.getString("branchId"));
				query.setParameter("puposeOfLoan", jsonObject.getString("puposeOfLoanId"));
				query.setParameter("applicantid", jsonObject.getString("applicantid"));
				query.setParameter("prospectid", jsonObject.getString("prospectid"));				
				query.setParameter("userId", jsonObject.getString("userId"));
				query.setParameter("actionDate", jsonObject.getString("actionDate"));				
				query.setParameter("caseId", jsonObject.getString("caseId"));
				
				status += query.executeUpdate();

			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				/*
				 * Query query=session.
				 * createSQLQuery("UPDATE QC_PROSPECT.QT_PERSONAL_DETAILS PD SET PD.CUSTENTITYTYPEID=:entityType,PD.COMPANY_TYPE=:applicantType,PD.CONSTITUTION=:constitution,PD.OCCUPATION_TYPE=:occupation,PD.INDUSTRYID=:industry,PD.CASE_COMPANY_ID=:nameOfCompany,PD.CUSTCATEGORY=:customerCategory,PD.AUTHSIGNATORYFNAME=:authSignatoryFirstName,PD.AUTHSIGNATORYMNAME=:authSignatoryMiddleName,PD.AUTHSIGNATORYLNAME=:authSignatoryLastName,PD.INCORPERATION_DT=:dateOfIncorporation,PD.PAN=:companyPan,UPDATED_BY=:userId,CREATED_SYS_DATE=sysdate,UPDATED_DATE=:actionDate WHERE CASE_ID=:caseId"
				 * ); query.setParameter("entityType",
				 * jsonObject.getString("entityType"));
				 * query.setParameter("applicantType",
				 * jsonObject.getString("applicantType"));
				 * query.setParameter("constitution",
				 * jsonObject.getString("constitution"));
				 * query.setParameter("occupation",
				 * jsonObject.getString("occupation"));
				 * query.setParameter("industry",
				 * jsonObject.getString("industry"));
				 * query.setParameter("nameOfCompany",
				 * jsonObject.getString("nameOfCompany"));
				 * query.setParameter("customerCategory",
				 * jsonObject.getString("customerCategory"));
				 * query.setParameter("authSignatoryFirstName",
				 * jsonObject.getString("authSignatoryFirstName"));
				 * query.setParameter("authSignatoryMiddleName",
				 * jsonObject.getString("authSignatoryMiddleName"));
				 * query.setParameter("authSignatoryLastName",
				 * jsonObject.getString("authSignatoryLastName"));
				 * query.setParameter("dateOfIncorporation",
				 * jsonObject.getString("dateOfIncorporation"));
				 * query.setParameter("companyPan",
				 * jsonObject.getString("companyPan"));
				 * query.setParameter("userId", jsonObject.getString("userId"));
				 * query.setParameter("actionDate",
				 * jsonObject.getString("actionDate"));
				 * query.setParameter("caseId", jsonObject.getString("caseId"));
				 * status+=query.executeUpdate();
				 */} catch (Exception e) {
				e.printStackTrace();
			}
			if (status > 0) {
				responseJson = "{\"status\":\"S\",\"syncDate\":\""+syncDate+"\",\"message\":\"" + jsonObject.getString("caseId")
						+ " successfully update\"}";
			}
		} catch (Exception e) {
			responseJson = "{\"status\":\"F\",\"syncDate\":\""+syncDate+"\",\"message\":\"" + e.getMessage() + "\"}";
			logger.error("LeadDaoImpl | updateLeadDetails() | error:::" + e.getMessage());
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		logger.info("LeadDaoImpl | updateLeadDetails() | END");
		return responseJson;
	}
	
	
	
	
	
	@Transactional
	public String getBusinessDate() {
		String businessDate="";
		Session session = sessionFactory.openSession();
		try{
			
			String queryStr = "SELECT QC_LOS.fn_getbusinessdate(NULL,NULL) FROM DUAL";
			SQLQuery  query = session.createSQLQuery(queryStr);
			List countList = query.list();
			if(countList.size()>0){
				businessDate=countList.get(0)+"";
			}
		}catch(Exception e){
			logger.error("Error in catch block due to ::: --> "+ e.getMessage());
			e.printStackTrace();
		}	finally {
			if(session!=null)
				session.close();
		}	
		return businessDate;
	}
	
	
}
