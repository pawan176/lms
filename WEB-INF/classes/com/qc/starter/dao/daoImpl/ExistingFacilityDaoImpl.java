package com.qc.starter.dao.daoImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.qc.starter.dao.ExistingFacilityDao;
import com.qc.starter.entity.ExistingFacilityEntity;

@Repository
public class ExistingFacilityDaoImpl implements ExistingFacilityDao {
	private static final Logger logger = Logger
			.getLogger(ExistingFacilityDaoImpl.class.getName());
	@Autowired
	SessionFactory sessionFactory;
	@Autowired
	HttpSession httpSession;

	@Override
	@Transactional
	public List<ExistingFacilityEntity> getFacilitiesExisting(String leadid) {
		logger.info("ExistingFacilityDaoImpl | getFacilitiesExisting() | START");
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		List<ExistingFacilityEntity> existingFacilityHistoryList = new ArrayList<ExistingFacilityEntity>();
		SQLQuery query = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			String sqlquery = "SELECT QEF.EXITING_FAC_ID,  QEF.PROD_ID FACILITY_ID, (SELECT QB.BANK_NAME FROM QC_PROSPECT_MASTER.QM_BANK QB WHERE QB.BANK_ID =QEF.BANK_ID and QB.ACTIVE='A') BANK , "
					+ "QEF.BANK_ID BANK_ID,TO_CHAR(QEF.LOAN_CC_AMT,'99,99,99,99,99,999') LOAN_CC_AMT,"
					+ "TO_CHAR(QEF.EMI_CC_OUTSTANDING,'99,99,99,999') EMI_CC_OUTSTANDING,  QEF.TENOR_BAL_MONTHS, QEF.REMARKS " 
					//+ ",(SELECT OCCUPATIONNAME FROM QC_PROSPECT_MASTER.QM_OCCUPATION  WHERE OCCUPATIONID=   qpd.OCCUPATION_type) OCCUPATION "
					+ "FROM QC_PROSPECT.QT_CASE QC, QC_PROSPECT.QT_PERSONAL_DETAILS QPD , QC_PROSPECT.QT_EXISTING_FAC QEF WHERE QPD.CASE_ID= QC.CASE_ID AND QPD.PERSONAL_DTL_ID= QEF.PERSONAL_DTL_ID AND QC.CASE_ID ="
					+ leadid;
			query = session.createSQLQuery(sqlquery);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List result = query.list();
			for (Object o : result) {
				Map map = (Map) o;
				ExistingFacilityEntity existingFacilityEntity = new ExistingFacilityEntity();
				existingFacilityEntity.setExitingFacId(map.get("EXITING_FAC_ID") != null ? map.get("EXITING_FAC_ID").toString() : " ");
				existingFacilityEntity.setProdId(map.get("FACILITY_ID") != null ? map.get("FACILITY_ID").toString() : " ");
				existingFacilityEntity.setBankId(map.get("BANK_ID") != null ? map.get("BANK_ID").toString() : " ");
				existingFacilityEntity.setBank(map.get("BANK") != null ? map.get("BANK").toString() : " ");
				existingFacilityEntity.setLoanCcAmt(map.get("LOAN_CC_AMT") != null ? map.get("LOAN_CC_AMT").toString().trim() : " ");
				existingFacilityEntity.setEmiCcOutstanding(map.get("EMI_CC_OUTSTANDING") != null ? map.get("EMI_CC_OUTSTANDING").toString().trim() : " ");
				existingFacilityEntity.setTenorBalMonths(map.get("TENOR_BAL_MONTHS") != null ? map.get("TENOR_BAL_MONTHS").toString() : " ");
				existingFacilityEntity.setRemarks(map.get("REMARKS") != null ? map.get("REMARKS").toString() : " ");
				existingFacilityHistoryList.add(existingFacilityEntity);
				existingFacilityEntity = null;
			}
			return existingFacilityHistoryList;
		} catch (Exception e) {
			logger.error("ExistingFacilityDaoImpl | getFacilitiesExisting() | error::::"
					+ e.getMessage());
			e.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
		logger.info("ExistingFacilityDaoImpl | getFacilitiesExisting() | END ");
		return existingFacilityHistoryList;
	}

	@Transactional
	public String updateExistingFacility(
			List<ExistingFacilityEntity> existingFacilityHistorylist) {
		logger.info("ExistingFacilityDaoImpl | updateExistingFacility() | START ");
		String status = "";
		int updateFlag = 0;
		Session session = null;
		SQLQuery query = null;
		try {
			session = sessionFactory.openSession();
			String hql = "UPDATE QC_PROSPECT.QT_EXISTING_FAC SET PRODUCTID = :productId , BANK_ID = :bankId, "
						+"LOAN_CC_AMT = :loanAmount, EMI_CC_OUTSTANDING = :outStandingAmount, TENOR_BAL_MONTHS = :tenorBalMonth,"
						+"REMARKS = :remarks WHERE EXITING_FAC_ID = :faciltyId";
			query = session.createSQLQuery(hql);
			for (ExistingFacilityEntity existingFacilityEntity : existingFacilityHistorylist) {
				String loan = existingFacilityEntity.getLoanCcAmt().trim().replaceAll(",", "");
				query.setParameter("productId",existingFacilityEntity.getProdId())
				.setParameter("bankId",existingFacilityEntity.getBankId())
				.setParameter("loanAmount", loan.trim()!=""?loan.trim():null)
				.setParameter("outStandingAmount",existingFacilityEntity.getEmiCcOutstanding().trim().replaceAll(",", ""))
				.setParameter("tenorBalMonth",existingFacilityEntity.getTenorBalMonths().trim()==""?null:existingFacilityEntity.getTenorBalMonths().trim())
				.setParameter("remarks",existingFacilityEntity.getRemarks())
				.setParameter("faciltyId",existingFacilityEntity.getCheckRow());
				updateFlag = updateFlag + query.executeUpdate();
			}
			if (updateFlag > 0) {
				status = "true";
			} else {
				status = "false";
			}
		} catch (Exception e) {
			logger.error("ExistingFacilityDaoImpl | updateExistingFacility() | ERROR:::"
					+ e.getMessage());
			e.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
		logger.info("ExistingFacilityDaoImpl | updateExistingFacility() | END:::");
		return status;
	}

	@Transactional
	public String deleteExistingFacility(
			List<ExistingFacilityEntity> existingFacilityHistorylist) {
		logger.info("ExistingFacilityDaoImpl | deleteExistingFacility() | START");
		String status = "";
		Session session = null;
		SQLQuery query = null;
		int update = 0;
		try {
			session = sessionFactory.openSession();
			String hql = "delete QC_PROSPECT.QT_EXISTING_FAC where EXITING_FAC_ID =:exitingFacId";
			query = session.createSQLQuery(hql);
			for (ExistingFacilityEntity existingFacilityEntity : existingFacilityHistorylist) {
				query.setParameter("exitingFacId",
						existingFacilityEntity.getCheckRow());
				update = update + query.executeUpdate();
			}
			if (update > 0)
				status = "" + update;
		} catch (Exception e) {
			logger.error("ExistingFacilityDaoImpl | deleteExistingFacility() | Error:::"
					+ e.getMessage());
			e.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
		logger.info("ExistingFacilityDaoImpl | deleteExistingFacility() | END");
		return status;
	}
}
