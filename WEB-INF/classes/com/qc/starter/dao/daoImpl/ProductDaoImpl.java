package com.qc.starter.dao.daoImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import oracle.jdbc.OracleTypes;

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
import com.qc.starter.basic.ConnectionFromSession;
import com.qc.starter.basic.SimpleHttpConnection;
import com.qc.starter.dao.ProductDao;
import com.qc.starter.dto.ContactDto;
import com.qc.starter.dto.ConvertToCustomerDto;
import com.qc.starter.dto.LeadHeaderDto;
import com.qc.starter.dto.ProductDto;
import com.qc.starter.mobileAction.JDBCConnectionUtil;
import com.qc.starter.service.LeadService;
import com.qc.starter.service.ProductService;

@Repository
public class ProductDaoImpl implements ProductDao {
	private static final Logger logger = Logger.getLogger(ProductDaoImpl.class.getName());
	@Autowired
	SessionFactory sessionFactory;
	private static ResourceBundle resource = ResourceBundle.getBundle("ApplicationResource");
	@Autowired
	ProductService productService;
	@Autowired
	LeadService leadService;
	
	@Transactional
	public List fetchProduct(String leadid) {
		logger.info("ProductDaoImpl | fetchProduct() | :- START : With request Param:: leadId::" + leadid);
		Session session = null;
		List products = null;
		try {
			session = sessionFactory.openSession();
			String leadsql = "select QC.CASE_ID LEAD_ID, QC.CASE_CODE LEAD_CODE,(SELECT QCP.PRODNAME"
					+ " FROM QC_PROSPECT_MASTER.QM_PRODUCT  QCP WHERE QCP.PRODTYPEID=1 AND QCP.PRODUCTID = QC.QUEUE_ID) QUEUE,"
					+ " QC.QUEUE_ID QUEUE_ID , (SELECT QCP.PRODNAME FROM QC_PROSPECT_MASTER.QM_PRODUCT  QCP WHERE QCP.PRODTYPEID=2"
					+ " AND QCP.PRODUCTID = QC.FACILITY_REQ_ID) FACILITY_REQUESTED, QC.FACILITY_REQ_ID FACILITY_REQ_ID ,"
					+ " TRIM(REPLACE(QPD.FNAME||' '||QPD.MNAME||' '||QPD.LNAME,'  ',' ')) CUSTOMER_NAME, QC.GENERATION_DT,"
					+ " (SELECT SQ.SUB_QUEUE FROM QC_PROSPECT_MASTER.QM_SUB_QUEUE SQ WHERE SQ.SUB_QUEUE_ID =QC.SUB_QUEUE_ID) SUB_QUEUE,"
					+ " QC.AMOUNT, (SELECT QA.MOBILE1 FROM QC_PROSPECT.QT_ADDRESS  QA WHERE ADDRESS_TYPE = 1000000001 AND"
					+ " QA.PERSONAL_DTL_ID =QPD.PERSONAL_DTL_ID) MOBILE,"
					+ " (SELECT TRIM(REPLACE(QU.USERFNAME||' '||QU.USERMNAME||' '||QU.USERMNAME,'  ',' ')) FROM"
					+ " QC_PROSPECT_MASTER.QM_USER QU WHERE QU.USERID= QC.ALLOCATED_TO) ALLOCATED_TO, (SELECT QS.SOURCE_NAME"
					+ " FROM QC_PROSPECT_MASTER.QM_SOURCE QS WHERE QS.SOURCE_ID= QC.SOURCE_ID) SOURCE, (SELECT QB.BANK_NAME"
					+ " FROM QC_PROSPECT_MASTER.QM_BANK QB WHERE QB.BANK_ID =QC.BANK_ID) BANK    , QC.BANK_ID BANK_ID ,(SELECT"
					+ "  (SELECT QC1.CITYMASTERNAME FROM QC_PROSPECT_MASTER.QM_CITYMASTER QC1 WHERE QC1.CITYMASTERID= QA.CITY)"
					+ " FROM QC_PROSPECT.QT_ADDRESS QA WHERE QA.PERSONAL_DTL_ID= QPD.PERSONAL_DTL_ID AND ADDRESS_TYPE = 1000000001)"
					+ " CITY, (SELECT QCA.CAMPAIGN_NAME FROM QC_PROSPECT_MASTER.QM_CAMPAIGN QCA WHERE QCA.CAMPAIGN_ID=QC.CAMPAIGN_ID) CAMPAIGN,"
					+ " QC.ROI , (SELECT QOC.OCCUPATIONNAME FROM QC_PROSPECT_MASTER.QM_OCCUPATION QOC"
					+ " WHERE QOC.OCCUPATIONID= QPD.OCCUPATION_TYPE) OCCUPATION , QC.TAG_INFO_A, QC.TAG_INFO_B"
					+ " from QC_PROSPECT.QT_CASE QC, QC_PROSPECT.QT_PERSONAL_DETAILS QPD WHERE QPD.CASE_ID= QC.CASE_ID"
					+ " and QC.CASE_ID = " + leadid;
			SQLQuery query = session.createSQLQuery(leadsql);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			products = query.list();
		} catch (Exception e) {
			logger.error("ProductDaoImpl | fetchProduct() | :- error:::::" + e.getMessage());
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		logger.info("ProductDaoImpl | fetchProduct() | :- END:::");
		return products;
	}

	public String convertAmount(String amountString) {
		String amount;
		String flotingValue = "";
		try {
			DecimalFormat format = new DecimalFormat("##,##");
			if (amountString.indexOf(".") > 0) {
				flotingValue = amountString.substring(amountString.indexOf("."));
				amount = amountString.substring(0, amountString.indexOf("."));
			} else {
				amount = amountString;
			}
			if (amount != null && amount.length() > 3) {
				String lastDigit = amount.substring(amount.length() - 1);
				String firstDigit = amount.substring(0, amount.length() - 1);
				amount = format.format(Double.valueOf(firstDigit)) + lastDigit + flotingValue;
			} else {
				amount = amountString;
			}
		} catch (Exception e) {
			e.printStackTrace();
			amount = amountString;
		}
		return amount;
	}

	@Transactional
	public String getProductCategory(String productId) {
		logger.info("ProductDaoImpl | getProductCategory() | :- START:::");
		String productCategory = "";
		Session session = null;
		try {
			session = sessionFactory.openSession();
			String sql = "SELECT QCP.PRODUCTCATEGORYID FROM QC_PROSPECT_MASTER.QM_PRODUCT QCP WHERE QCP.ACTIVE ='A' AND QCP.PRODUCTID ="
					+ productId;
			Query query = session.createSQLQuery(sql);
			List list = query.list();
			if (list.size() > 0) {
				productCategory = list.get(0) == null ? "" : list.get(0) + "";
			}
		} catch (Exception e) {
			logger.info("ERROR IN -->>>>>ProductDaoImpl | getProductCategory() | :-:" + e.getMessage());
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		logger.info("ProductDaoImpl | getProductCategory() | :- END:::");
		return productCategory;
	}

	// @Transactional
	public List<ProductDto> getEligibilityCalcData(ProductDto productDto) {
		logger.info("ProductDaoImpl | getEligibilityCalcData() | :- START:::");
		Connection con = null;
		CallableStatement cstmt = null;
		ResultSet rs = null;
		List<ProductDto> leadList = new ArrayList<ProductDto>();
		Session session = null;
		try {
			// session = sessionFactory.openSession();
			// con =
			// ConnectionFromSession.getJavaSqlConnectionFromHibernateSession(session);
			con = JDBCConnectionUtil.getConnection();
			ProductDto lead = null;
			cstmt = con.prepareCall("{call QC_PROSPECT.pkg_quote.PR_GET_QUOTE(?,?,?,?, ?,?,?,?)}");
			logger.info("{call QC_PROSPECT.pkg_quote.PR_GET_QUOTE(?,?,?,?, ?,?,?,?)}");
			if (productDto.getFacilityReqId() != null && !(productDto.getFacilityReqId().equals(""))) {
				cstmt.setInt("PN_PRODUCT", Integer.parseInt(productDto.getFacilityReqId()));
				logger.info("PN_PRODUCT type=in value=" + productDto.getFacilityReqId());
			} else {
				logger.info("PN_PRODUCT type=in value=null");
				cstmt.setNull("PN_PRODUCT", java.sql.Types.INTEGER);
			}
			if (productDto.getLeadId() != null && !(productDto.getLeadId().equals(""))) {
				cstmt.setInt("PN_CASE_ID", Integer.parseInt(productDto.getLeadId()));
				logger.info("PN_CASE_ID type=in value=" + productDto.getLeadId());
			} else {
				cstmt.setNull("PN_CASE_ID", java.sql.Types.INTEGER);
				logger.info("PN_CASE_ID type=in value=null");
			}
			if (productDto.getFacilityRequestedLoanAmount() != null
					&& !(productDto.getFacilityRequestedLoanAmount().equals(""))) {
				cstmt.setInt("P_LOAN_AMOUNT",
						Integer.parseInt(productDto.getFacilityRequestedLoanAmount().replaceAll(",", "")));
				logger.info("P_LOAN_AMOUNT type=in value="
						+ productDto.getFacilityRequestedLoanAmount().replaceAll(",", ""));
			} else {
				cstmt.setNull("P_LOAN_AMOUNT", java.sql.Types.DOUBLE);
				logger.info("P_LOAN_AMOUNT type=in value=null");
			}

			if (productDto.getFacilityRequestedTenor() != null
					&& !(productDto.getFacilityRequestedTenor().equals(""))) {
				logger.info("PN_TENOR type=in value=" + productDto.getFacilityRequestedTenor());
				cstmt.setInt("PN_TENOR", Integer.parseInt(productDto.getFacilityRequestedTenor()));
			} else {
				cstmt.setNull("PN_TENOR", java.sql.Types.INTEGER);
				logger.info("PN_TENOR type=in value=null");
			}
			if (productDto.getCompyId() != null && !(productDto.getCompyId().equals(""))) {
				cstmt.setInt("PN_COMPANY_ID", Integer.parseInt(productDto.getCompyId()));
				logger.info("PN_COMPANY_ID type=in value=" + productDto.getCompyId());
			} else {
				cstmt.setNull("PN_COMPANY_ID", java.sql.Types.INTEGER);
				logger.info("PN_COMPANY_ID type=in value=null");
			}
			cstmt.registerOutParameter("P_OUT", OracleTypes.CURSOR);
			logger.info("P_OUT type=out");
			cstmt.registerOutParameter("P_EXEC_STATUS", java.sql.Types.VARCHAR);
			logger.info("P_EXEC_STATUS type=out");
			cstmt.registerOutParameter("P_MESSAGE", java.sql.Types.VARCHAR);
			logger.info("P_MESSAGE type=out");
			cstmt.executeUpdate();
			String status = cstmt.getString("P_EXEC_STATUS");
			String message = cstmt.getString("P_MESSAGE");
			logger.info("MESSAGE =" + message);
			if (status.equalsIgnoreCase("S")) {
				rs = (ResultSet) cstmt.getObject("P_OUT");
				while (rs.next()) {
					lead = new ProductDto();
					lead.setEligibilityId(rs.getString("ELIGI_ID") == null ? "" : rs.getString("ELIGI_ID"));
					lead.setMinLoanAmount(rs.getString("MIN_LOAN_AMT") == null ? "" : rs.getString("MIN_LOAN_AMT"));
					lead.setMaxLoanAmount(rs.getString("MAX_LOAN_AMT") == null ? "" : rs.getString("MAX_LOAN_AMT"));
					lead.setMinTenor(rs.getString("MIN_TENOR_MONTHS") == null ? "" : rs.getString("MIN_TENOR_MONTHS"));
					lead.setMaxTenor(rs.getString("MAX_TENOR_MONTHS") == null ? "" : rs.getString("MAX_TENOR_MONTHS"));
					lead.setBankId(rs.getString("BANK_ID") == null ? "" : rs.getString("BANK_ID"));
					lead.setBankName(rs.getString("BANK_NAME") == null ? "" : rs.getString("BANK_NAME"));
					lead.setFacilityRequestedRateOfIntrest(rs.getString("ROI") == null ? "" : rs.getString("ROI"));
					lead.setFacilityRequestedBank(rs.getString("BANK_NAME") == null ? "" : rs.getString("BANK_NAME"));
					lead.setLtv(rs.getString("LTV") == null ? "" : rs.getString("LTV"));
					if (rs.getString("OBLIG_AMIT") == null)
						lead.setObligationAmt("");
					else {

						lead.setObligationAmt(convertAmount(rs.getString("OBLIG_AMIT")));
					}
					lead.setFacilityRequestedLoanAmount(
							rs.getString("LOAN_AMT") == null ? "" : rs.getString("LOAN_AMT"));
					lead.setFacilityRequestedTenor(
							rs.getString("REQ_TENOR_YR_FR") == null ? "" : rs.getString("REQ_TENOR_YR_FR"));
					lead.setProdId(rs.getString("PRODUCT_ID") == null ? "" : rs.getString("PRODUCT_ID"));
					lead.setSchemeName(rs.getString("PROD_NAME") == null ? "" : rs.getString("PROD_NAME").trim());
					if (lead.getProdId().equalsIgnoreCase("6"))
						lead.setProcessingFee(
								rs.getString("PROC_FEE") == null ? "" : convertAmount(rs.getString("PROC_FEE")));
					else
						lead.setProcessingFee(rs.getString("PROC_FEE") == null ? "" : rs.getString("PROC_FEE"));
					if (lead.getProdId().equalsIgnoreCase("6"))
						lead.setPrePaymentFee(
								rs.getString("PREP_FEE") == null ? "" : convertAmount(rs.getString("PREP_FEE")));
					else
						lead.setPrePaymentFee(rs.getString("PREP_FEE") == null ? "" : rs.getString("PREP_FEE"));
					if (lead.getProdId().equalsIgnoreCase("6")) {
						lead.setRenewalFee(
								rs.getString("RENEWAL_FEE") == null ? "" : convertAmount(rs.getString("RENEWAL_FEE")));
					} else if (lead.getProdId().equalsIgnoreCase("3") || lead.getProdId().equalsIgnoreCase("23")) {
						lead.setFixedFloatingSemi(
								rs.getString("FIX_FLOAT_SEMI") == null ? "" : rs.getString("FIX_FLOAT_SEMI"));
						lead.setApplicationFee(
								rs.getString("APPLICATION_FEE") == null ? "" : rs.getString("APPLICATION_FEE"));
					} else {
						lead.setRenewalFee(rs.getString("PART_PAYMENT") == null ? "" : rs.getString("PART_PAYMENT"));
					}
					lead.setText(rs.getString("TEXT") == null ? "" : rs.getString("TEXT"));
					lead.setMsg("");
					if (rs.getString("OBLIG_AMIT") != null && !(rs.getString("OBLIG_AMIT").equalsIgnoreCase(""))) {
						double r = Double.parseDouble(lead.getFacilityRequestedRateOfIntrest());
						double p = Double.parseDouble(rs.getString("OBLIG_AMIT"));
						double n = Double.parseDouble(lead.getFacilityRequestedTenor());
						double R = (r / 12) / 100;
						// R = Math.round(R * 100.0)/100.0;
						double e = (p * R * (Math.pow((1 + R), n)) / ((Math.pow((1 + R), n)) - 1));
						double totalInt = Math.round((e * n) - p);
						double intPerMonth = Math.round(totalInt / n);
						double s = (Math.round((e) - intPerMonth) + Math.round(intPerMonth));

						// s = Math.round(s * 100.0)/100.0;
						/*
						 * if(s>Double.parseDouble(productDto.getObligationAmt()
						 * ) ){ emiFlag="notOk~"+productDto.getObligationAmt();
						 * }
						 */
						lead.setFacilityRequestedEmi(convertAmount(Double.toString(s)));
					} else {
						lead.setFacilityRequestedEmi("");
					}
					leadList.add(lead);
					lead = null;
				}
			} else if (status.equalsIgnoreCase("I")) {
				lead = new ProductDto();
				lead.setMsg(message);
				leadList.add(lead);
			}
		} catch (Exception e) {
			logger.error("ProductDaoImpl | getEligibilityCalcData() | :- error:::" + e.getMessage());
			e.printStackTrace();
			return null;
		} finally {
			if (session != null) {
				session.close();
			}
			if (con != null) {
				try {
					if (rs != null) {
						rs.close();
						rs = null;
					}
					if (cstmt != null) {
						cstmt.close();
						cstmt = null;
					}
					con.close();
				} catch (Exception e) {
					logger.error("ProductDaoImpl | getEligibilityCalcData() | :- error:::" + e.getMessage());
					e.printStackTrace();
				}
			}
		}
		logger.info("ProductDaoImpl | getEligibilityCalcData() | :- end:::");
		return leadList;
	}

	// added by tripti 4oct
	@Override
	public String convertToCustomer(String caseId, int userId, String branchId) {
		logger.info("ProductDaoImpl | convertToCustomer() | :- START:::");

		Session session = null;
		List list = null;
		String status = "";
		String custValidationMSg = "";
		String prodvalidationMSg = "", entityType = "";
		int flag = 0;
		try {
			session = sessionFactory.openSession();
			String sql = "SELECT c.QUEUE_ID,  c.TENOR_MONTH,c.LOAN_TENURE,c.LOAN_AMOUNT,c.SCHEME_ID,c.BRANCHID,  c.TENOR_YEAR,  c.AMOUNT,"
					+ " (select c.COMPANY_NAME from qc_prospect_master.qm_case_company c where c.CASE_COMPANY_ID=p.CASE_COMPANY_ID ) COMPNAME,"
					+ " p.FNAME,  p.MNAME,  p.LNAME, to_char(p.DOB, 'dd-Mon-yyyy') as DOB ,p.TITLE as TITLE, p.CONSTITUTION AS CONSTITUTION, p.ADHAAR_NO, to_char(p.INCORPERATION_DT, 'dd-Mon-yyyy')  AS DOI "
					+ " , p.MARITAL_STATUS,  p.GENDER,p.TYPEOFBUSINESS,p.CLUSTERCASE,p.INDUSTRYID,  p.other_company_name as COMPANY ,p.NOOFDEPENDENTS,  p.OCCUPATION_TYPE,  p.GROSS_ANNUAL_INC,"
					+ "  p.GROSS_MONTHLY_INC,  p.CUSTCATEGORY,  p.CUSTENTITYTYPEID,  cm.CONTACT_NO,  OFFADD.EMAIL AS EMAIL,"
					+ "  RESADD.FLATNO RESFLATNO,RESADD.ADDRESS RESHOMENAME,RESADD.FLOORNO RESFLOORNO,RESADD.COMPANY_NAME RESCOMPANY,RESADD.BUILDINGNAME RESBUILDINGNAME,"
					+ "  RESADD.LOCALITY RESLOCALITY,RESADD.LANDMARK RESLANDMARK,RESADD.CITY RESCITY,RESADD.STATE RESSTATE,"
					+ " RESADD.ZIPCODE RESZIPCODE, RESADD.OLD_ADDRESS RESOLDADDRESS , RESADD.PROP_MARKET_VALUE RESIMARKETVALUE,RESADD.PHONE1 RESPHONE1,"
					+ "  RESADD.EXT1 RESEXT,   RESADD.phone2  RESPHONE2,   RESADD.MOBILE1  RESMOBILE1,  RESADD.MOBILE2 RESMOBILE2,RESADD.EMAIL  RESEMAIL, RESADD.MAILINGADDRESS RESMAILINGADDRESS,  RESADD.OCCUPANCY_MM RESOCCUPANCY_MM,"
					+ "  RESADD.OCCUPANCY_YR RESOCCUPANCY_YR,  RESADD.OCCUPANCY_STATUS RESOCCUPANCY_STATUS,"
					+ "   PERADD.FLATNO PERFLATNO,PERADD.ADDRESS PERHOMENAME,  PERADD.FLOORNO PERFLOORNO,PERADD.COMPANY_NAME PERCOMPANY,  PERADD.BUILDINGNAME PERBUILDINGNAME,"
					+ "  PERADD.LOCALITY PERLOCALITY,  PERADD.LANDMARK PERLANDMARK,  PERADD.CITY PERCITY,  PERADD.STATE PERSTATE,"
					+ "  PERADD.ZIPCODE PERZIPCODE,PERADD.OLD_ADDRESS PEROLDADDRESS,PERADD.PROP_MARKET_VALUE PERMARKETVALUE,  PERADD.PHONE1 PERPHONE1, PERADD.phone2  PERPHONE2, PERADD.MOBILE1  PERMOBILE1, PERADD.MOBILE2  PERMOBILE2,  PERADD.EMAIL  PEREMAIL, PERADD.EXT1 PEREXT,"
					+ "  PERADD.MAILINGADDRESS PERMAILINGADDRESS,  PERADD.OCCUPANCY_MM PEROCCUPANCY_MM,"
					+ "  PERADD.OCCUPANCY_YR PEROCCUPANCY_YR," + "  PERADD.OCCUPANCY_STATUS PEROCCUPANCY_STATUS,"
					+ "  OFFADD.FLATNO OFFFLATNO,OFFADD.ADDRESS OFFHOMENAME  ,OFFADD.FLOORNO OFFFLOORNO,OFFADD.COMPANY_NAME OFFCOMPANY,  OFFADD.BUILDINGNAME OFFBUILDINGNAME, "
					+ " OFFADD.LOCALITY OFFLOCALITY,"
					+ "  OFFADD.LANDMARK OFFLANDMARK,  OFFADD.CITY OFFCITY,  OFFADD.STATE OFFSTATE,  OFFADD.ZIPCODE OFFZIPCODE, OFFADD.OLD_ADDRESS OFFOLDADDRESS,OFFADD.PROP_MARKET_VALUE OFFMARKETVALUE,"
					+ "  OFFADD.PHONE1 OFFPHONE1,OFFADD.phone2    OFFPHONE2, OFFADD.FAX   OFFFAX,  OFFADD.BUSINESS_EST_YR  OFFYRS_BUSSINESS_EST, OFFADD.MOBILE1   OFFMOBILE1,OFFADD.MOBILE2  OFFMOBILE2, OFFADD.EMAIL  OFFEMAIL, OFFADD.EXT1 OFFEXT,"
					+ "  OFFADD.MAILINGADDRESS OFFMAILINGADDRESS,  OFFADD.OCCUPANCY_MM OFFOCCUPANCY_MM, "
					+ " OFFADD.OCCUPANCY_YR OFFOCCUPANCY_YR,  OFFADD.OCCUPANCY_STATUS OFFOCCUPANCY_STATUS,p.PAN," + "  "
					+ "   C.PURPOSEOFLOANID "
					+ "  FROM qc_prospect.qt_case c,  qc_prospect.qt_personal_details p,  (SELECT qaa.*  FROM"
					+ " QC_PROSPECT_MASTER.QM_ADDRESSTYPE qa,   qc_prospect.qt_address qaa  WHERE UPPER(ADDRESSTYPENAME)"
					+ " LIKE '%RESIDENCE ADDRESS%'  AND qa.addresstypeid = qaa.address_type   ) RESADD,  (SELECT qaa.*"
					+ "  FROM QC_PROSPECT_MASTER.QM_ADDRESSTYPE qa,    qc_prospect.qt_address qaa  WHERE UPPER(ADDRESSTYPENAME)"
					+ " LIKE '%PER%'  AND qa.addresstypeid = qaa.address_type    ) PERADD,  (SELECT qaa.*  FROM"
					+ " QC_PROSPECT_MASTER.QM_ADDRESSTYPE qa,    qc_prospect.qt_address qaa"
					+ "  WHERE UPPER(ADDRESSTYPENAME) LIKE '%OFFICE%'  AND qa.addresstypeid = qaa.address_type  ) OFFADD,"
					+ "  qc_prospect.qt_case_mobile cm WHERE c.case_id   = p.case_id"
					+ " AND c.case_id = RESADD.case_id(+) AND c.case_id = PERADD.case_id(+) AND c.case_id   = OFFADD.case_id(+)"
					+ " AND c.case_id  = cm.case_id(+) AND NVL(cm.primary_contact,'Y')='Y' AND c.case_id =" + caseId;

			SQLQuery query = session.createSQLQuery(sql);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			list = query.list();

			ConvertToCustomerDto dto = new ConvertToCustomerDto();

			if (list != null && list.size() > 0) {
				for (Object o : list) {
					Map obj = (Map) o;
					dto.setProduct(obj.get("QUEUE_ID") != null ? obj.get("QUEUE_ID") + "" : "");
					entityType = obj.get("CUSTENTITYTYPEID") + "";

					if (entityType.equalsIgnoreCase("1000000001")) {

						dto.setFname(obj.get("FNAME") != null ? obj.get("FNAME") + "" : "");
						dto.setMname(obj.get("MNAME") != null ? obj.get("MNAME") + "" : "");
						dto.setLname(obj.get("LNAME") != null ? obj.get("LNAME") + "" : "");
						dto.setTitle(obj.get("TITLE") != null ? obj.get("TITLE") + "" : "");

						if (!(CommonUtils.toString(obj.get("DOB")).equals(""))) {
							dto.setDateofBirth(obj.get("DOB") + "");
						} else {
							custValidationMSg = custValidationMSg + "DATE OF BIRTH,";
						}
						if (obj.get("RESZIPCODE") != null)
							dto.setResiZip(obj.get("RESZIPCODE") + "");
						else
							custValidationMSg = custValidationMSg + "RESIDENCE PINCODE,";

						if (obj.get("RESCITY") != null) {
							String city = obj.get("RESCITY") + "";
							if (city.equalsIgnoreCase("-1")) {
								custValidationMSg = custValidationMSg + "RESIDENCE CITY,";
							} else
								dto.setResiCity(obj.get("RESCITY") + "");
						} else
							custValidationMSg = custValidationMSg + "RESIDENCE CITY,";
						if (obj.get("RESPHONE1") != null)
							dto.setResiPhone1(obj.get("RESPHONE1") + "");
						/*
						 * else custValidationMSg=custValidationMSg+
						 * "RESIDANCE PHONE,";
						 */
						if (obj.get("RESSTATE") != null) {
							String state = obj.get("RESSTATE") + "";
							if (state.equalsIgnoreCase("-1")) {
								custValidationMSg = custValidationMSg + "RESIDENCE STATE,";
							} else
								dto.setResiState(obj.get("RESSTATE") + "");
						} else
							custValidationMSg = custValidationMSg + "RESIDENCE STATE,";

						if (obj.get("RESOCCUPANCY_MM") != null)
							dto.setResiLivingHereSinceOccupancyMonths(obj.get("RESOCCUPANCY_MM") + "");
						/*
						 * else custValidationMSg=custValidationMSg+
						 * "RESIDENCE NO OF MONTH,";
						 */

						if (obj.get("RESMOBILE1") != null)
							dto.setResimob1(obj.get("RESMOBILE1") + "");
						if (obj.get("RESMOBILE2") != null)
							dto.setResimob2(obj.get("RESMOBILE2") + "");

						if (obj.get("RESEMAIL") != null)
							dto.setResiEmail(obj.get("RESEMAIL") + "");

						if (obj.get("PERMOBILE1") != null)
							dto.setPermmob1(obj.get("PERMOBILE1") + "");
						if (obj.get("PERMOBILE2") != null)
							dto.setPermmob2(obj.get("PERMOBILE2") + "");

						if (obj.get("PEREMAIL") != null)
							dto.setPermEmail(obj.get("PEREMAIL") + "");

						if (obj.get("OFFMOBILE1") != null)
							dto.setOfficemob1(obj.get("OFFMOBILE1") + "");

						if (obj.get("OFFMOBILE2") != null)
							dto.setOfficemob2(obj.get("OFFMOBILE2") + "");

						if (obj.get("OFFEMAIL") != null)
							dto.setOfficeEmail(obj.get("OFFEMAIL") + "");

						/*
						 * if(obj.get("OFFFAX")!=null)
						 * dto.setFax(fax);(obj.get("OFFFAX") + "");
						 */

						/*
						 * if(obj.get("OFFYRS_BUSSINESS_EST")!=null)
						 * dto.setOff(obj.get("OFFYRS_BUSSINESS_EST") + "");
						 */

						if (obj.get("RESOCCUPANCY_YR") != null)
							dto.setResiLivingHereSinceOccupancyYear(obj.get("RESOCCUPANCY_YR") + "");
						else
							custValidationMSg = custValidationMSg + " RESIDENCE NO OF YEAR,";

						/*
						 * if(obj.get("RESOCCUPANCY_STATUS")!=null){ String
						 * ops=obj.get("RESOCCUPANCY_STATUS")+"";
						 * if(ops.equalsIgnoreCase("-1")){
						 * custValidationMSg=custValidationMSg+
						 * "RESIDENCE OCCUPANCY_STATUS,"; }else
						 * dto.setResiaAccomodationType(obj.get(
						 * "RESOCCUPANCY_STATUS") + "");
						 * dto.setResiOwnerShip(obj.get("RESOCCUPANCY_STATUS") +
						 * ""); }
						 */
						/*
						 * else custValidationMSg=custValidationMSg+
						 * "RESIDENCE OCCUPANCY_STATUS,";
						 */

						// changed on 13th Oct
						//dto.setResiMobile(obj.get("CONTACT_NO") + "");
						//dto.setResiEmail(obj.get("EMAIL") != null ? "" : obj.get("EMAIL") + "");

						if (obj.get("GENDER") != null) {
							String gender = obj.get("GENDER") + "";
							if (gender.equalsIgnoreCase("-1")) {
								custValidationMSg = custValidationMSg + "GENDER,";
							} else
								dto.setGender(obj.get("GENDER") + "");
						} else
							custValidationMSg = custValidationMSg + "GENDER,";

						if (obj.get("MARITAL_STATUS") != null) {
							String ms = obj.get("GENDER") + "";
							if (ms.equalsIgnoreCase("-1")) {
								custValidationMSg = custValidationMSg + "MARITAL STATUS,";
							} else
								dto.setMaritalStatus(obj.get("MARITAL_STATUS") + "");
						} else
							custValidationMSg = custValidationMSg + "MARITAL STATUS,";

						if (obj.get("OFFZIPCODE") != null)
							dto.setOfficeZip(obj.get("OFFZIPCODE") + "");
						else
							custValidationMSg = custValidationMSg + "OFFICE PINCODE,";
						if (obj.get("OFFCITY") != null) {
							String city = obj.get("OFFCITY") + "";
							if (city.equalsIgnoreCase("-1")) {
								custValidationMSg = custValidationMSg + "OFFICE CITY,";
							} else
								dto.setOfficeCity(obj.get("OFFCITY") + "");
						} else
							custValidationMSg = custValidationMSg + "OFFICE CITY,";
						if (obj.get("OFFMOBILE1") != null)
							dto.setOfficemob1(obj.get("OFFMOBILE1") + "");
						else
							custValidationMSg = custValidationMSg + "OFFICE MOBILE 1,";
						if (obj.get("OFFSTATE") != null) {
							String state = obj.get("OFFSTATE") + "";
							if (state.equalsIgnoreCase("-1")) {
								custValidationMSg = custValidationMSg + "OFFICE STATE,";
							} else
								dto.setOfficeState(obj.get("OFFSTATE") + "");
						} else
							custValidationMSg = custValidationMSg + "OFFICE STATE,";

						if (obj.get("OFFOCCUPANCY_MM") != null)
							dto.setOfficeLivingHereSincenceOccupancyMonths(obj.get("OFFOCCUPANCY_MM") + "");
						/*
						 * else custValidationMSg=custValidationMSg+
						 * "OFFICE NO OF MONTH,";
						 */
						if (obj.get("OFFOCCUPANCY_YR") != null)
							dto.setOfficeLivingHereSincenceOccupancyYear(obj.get("OFFOCCUPANCY_YR") + "");
						else
							custValidationMSg = custValidationMSg + " OFFICE NO OF YEAR,";
						if (obj.get("OFFOCCUPANCY_STATUS") != null) {
							String ops = obj.get("OFFOCCUPANCY_STATUS") + "";
							if (ops.equalsIgnoreCase("-1")) {
							} else
								dto.setOfficeOwnerShip(obj.get("OFFOCCUPANCY_STATUS") + "");
						}

					} else if (entityType.equalsIgnoreCase("1000000002")) {
						if (obj.get("AUTH_FNAME") != null)
							dto.setAuthSignatoryFName(obj.get("AUTH_FNAME") + "");
						if (obj.get("AUTH_MNAME") != null)
							dto.setAuthSignatoryMName(obj.get("AUTH_MNAME") + "");
						if (obj.get("AUTH_LNAME") != null)
							dto.setAuthSignatoryLName(obj.get("AUTH_LNAME") + "");

						if (obj.get("COMPANY") != null)
							dto.setFname(obj.get("COMPANY") + "");
						else {
							custValidationMSg = custValidationMSg + "COMPANY NAME,";
						}

						if (obj.get("DOI") != null)
							dto.setDateofBirth(obj.get("DOI") + "");
						else
							custValidationMSg = custValidationMSg + "DATE OF INCORPORATION, ";
	
						if (obj.get("OFFZIPCODE") != null)
							dto.setOfficeZip(obj.get("OFFZIPCODE") + "");
						else
							custValidationMSg = custValidationMSg + "OFFICE PINCODE,";

						if (obj.get("OFFCITY") != null) {
							String city = obj.get("OFFCITY") + "";
							if (city.equalsIgnoreCase("-1")) {
								custValidationMSg = custValidationMSg + "OFFICE CITY,";
							} else
								dto.setOfficeCity(obj.get("OFFCITY") + "");
						} else
							custValidationMSg = custValidationMSg + "OFFICE CITY,";
						if (obj.get("OFFPHONE1") != null)
							dto.setOfficePhone1(obj.get("OFFPHONE1") + "");
						else
							custValidationMSg = custValidationMSg + "OFFICE PHONE,";
						if (obj.get("OFFSTATE") != null) {
							String state = obj.get("OFFSTATE") + "";
							if (state.equalsIgnoreCase("-1")) {
								custValidationMSg = custValidationMSg + "OFFICE STATE,";
							} else
								dto.setOfficeState(obj.get("OFFSTATE") + "");
						} else
							custValidationMSg = custValidationMSg + "OFFICE STATE,";
						if (obj.get("OFFOCCUPANCY_MM") != null)
							dto.setOfficeLivingHereSincenceOccupancyMonths(obj.get("OFFOCCUPANCY_MM") + "");
					
						if (obj.get("OFFOCCUPANCY_YR") != null)
							dto.setOfficeLivingHereSincenceOccupancyYear(obj.get("OFFOCCUPANCY_YR") + "");
						else
							custValidationMSg = custValidationMSg + " OFFICE NO OF YEAR,";
						if (obj.get("OFFOCCUPANCY_STATUS") != null) {
							String ops = obj.get("OFFOCCUPANCY_STATUS") + "";			
							dto.setOfficeOwnerShip(obj.get("OFFOCCUPANCY_STATUS") + "");
						}
						
						if (obj.get("OFFMOBILE1") != null)
							dto.setOfficemob1(obj.get("OFFMOBILE1") + "");

						if (obj.get("OFFMOBILE2") != null)
							dto.setOfficemob2(obj.get("OFFMOBILE2") + "");

						if (obj.get("OFFEMAIL") != null)
							dto.setOfficeEmail(obj.get("OFFEMAIL") + "");

						
				
						if (obj.get("OFFZIPCODE") != null)
							dto.setOfficeZip(obj.get("OFFZIPCODE") + "");
						else
							custValidationMSg = custValidationMSg + "OFFICE PINCODE,";
						if (obj.get("OFFCITY") != null) {
							String city = obj.get("OFFCITY") + "";
							if (city.equalsIgnoreCase("-1")) {
								custValidationMSg = custValidationMSg + "OFFICE CITY,";
							} else
								dto.setOfficeCity(obj.get("OFFCITY") + "");
						} else
							custValidationMSg = custValidationMSg + "OFFICE CITY,";
						if (obj.get("OFFMOBILE1") != null)
							dto.setOfficemob1(obj.get("OFFMOBILE1") + "");
						else
							custValidationMSg = custValidationMSg + "OFFICE MOBILE 1,";
						if (obj.get("OFFSTATE") != null) {
							String state = obj.get("OFFSTATE") + "";
							if (state.equalsIgnoreCase("-1")) {
								custValidationMSg = custValidationMSg + "OFFICE STATE,";
							} else
								dto.setOfficeState(obj.get("OFFSTATE") + "");
						} else
							custValidationMSg = custValidationMSg + "OFFICE STATE,";

						if (obj.get("OFFOCCUPANCY_MM") != null)
							dto.setOfficeLivingHereSincenceOccupancyMonths(obj.get("OFFOCCUPANCY_MM") + "");
					
						if (obj.get("OFFOCCUPANCY_YR") != null)
							dto.setOfficeLivingHereSincenceOccupancyYear(obj.get("OFFOCCUPANCY_YR") + "");
						else
							custValidationMSg = custValidationMSg + " OFFICE NO OF YEAR,";
				
						if (obj.get("OFFOCCUPANCY_STATUS") != null) {
							String ops = obj.get("OFFOCCUPANCY_STATUS") + "";
							if (ops.equalsIgnoreCase("-1")) {
							} else
								dto.setOfficeOwnerShip(obj.get("OFFOCCUPANCY_STATUS") + "");
						}
					

						
					} else
						custValidationMSg = custValidationMSg + "ENTITY TYPE,";

					if (obj.get("RESOCCUPANCY_YR") != null)
						dto.setResiLivingHereSinceOccupancyYear(obj.get("RESOCCUPANCY_YR") + "");
					
					
					
					if (obj.get("RESZIPCODE") != null)
						dto.setResiZip(obj.get("RESZIPCODE") + "");
					
					if (obj.get("RESCITY") != null) {
						String city = obj.get("RESCITY") + "";
						dto.setResiCity(obj.get("RESCITY") + "");
					}

					if (obj.get("RESPHONE1") != null)
						dto.setResiPhone1(obj.get("RESPHONE1") + "");

					if (obj.get("RESSTATE") != null) {
						String state = obj.get("RESSTATE") + "";
						dto.setResiState(obj.get("RESSTATE") + "");
					} 

					if (obj.get("RESOCCUPANCY_MM") != null)
						dto.setResiLivingHereSinceOccupancyMonths(obj.get("RESOCCUPANCY_MM") + "");


					if (obj.get("RESOCCUPANCY_YR") != null)
						dto.setResiLivingHereSinceOccupancyYear(obj.get("RESOCCUPANCY_YR") + "");
					

					if (obj.get("RESOCCUPANCY_STATUS") != null) {
						String ops = obj.get("RESOCCUPANCY_STATUS") + "";							
						dto.setResiOwnerShip(obj.get("RESOCCUPANCY_STATUS") + "");
					}


					if (obj.get("RESMOBILE1") != null)
						dto.setResimob1(obj.get("RESMOBILE1") + "");
					if (obj.get("RESMOBILE2") != null)
						dto.setResimob2(obj.get("RESMOBILE2") + "");

					if (obj.get("RESEMAIL") != null)
						dto.setResiEmail(obj.get("RESEMAIL") + "");

					if (obj.get("PERMOBILE1") != null)
						dto.setPermmob1(obj.get("PERMOBILE1") + "");
					if (obj.get("PERMOBILE2") != null)
						dto.setPermmob2(obj.get("PERMOBILE2") + "");

					if (obj.get("PEREMAIL") != null)
						dto.setPermEmail(obj.get("PEREMAIL") + "");

					
					if (obj.get("RESMAILINGADDRESS") != null && obj.get("RESMAILINGADDRESS").equals("Y"))
						dto.setMailingAddress("R");
					
					if (obj.get("OFFMAILINGADDRESS") != null && obj.get("OFFMAILINGADDRESS").equals("Y"))
						dto.setMailingAddress("O");
					
					if (obj.get("PERMAILINGADDRESS") != null && obj.get("PERMAILINGADDRESS").equals("Y"))
						dto.setMailingAddress("P");

					if (CommonUtils.toString(dto.getMailingAddress()).equals("")) {
						dto.setMailingAddress("R");
					}
					
					if (obj.get("RESOCCUPANCY_YR") != null)
						dto.setResiLivingHereSinceOccupancyYear(obj.get("RESOCCUPANCY_YR") + "");
					
					if (obj.get("PEROCCUPANCY_YR") != null)
						dto.setPermLivingHereSinceOccupancyYear(obj.get("PEROCCUPANCY_YR") + "");
					
					if (obj.get("OFFOCCUPANCY_YR") != null)
						dto.setOfficeLivingHereSincenceOccupancyYear(obj.get("OFFOCCUPANCY_YR") + "");
					
					if (obj.get("RESOLDADDRESS") != null)
						dto.setResiOldAddress(obj.get("RESOLDADDRESS") + "");
					
					if (obj.get("PEROLDADDRESS") != null)
						dto.setPermOldAddress(obj.get("PEROLDADDRESS") + "");
					
					if (obj.get("OFFOLDADDRESS") != null)
						dto.setOfficeOldAddress(obj.get("OFFOLDADDRESS") + "");
					
					
					
					if (obj.get("RESIMARKETVALUE") != null)
						dto.setResiMarketValue(obj.get("RESIMARKETVALUE") + "");
					
					if (obj.get("PERMARKETVALUE") != null)
						dto.setPermMarketValue(obj.get("PERMARKETVALUE") + "");
					
					if (obj.get("OFFMARKETVALUE") != null)
						dto.setOfficeMarketValue(obj.get("OFFMARKETVALUE") + "");
					
					
					
					 if(obj.get("OFFFAX")!=null)
						 dto.setOfficeFax(obj.get("OFFFAX") + "");
					 
					 

					dto.setIndustryType(obj.get("INDUSTRYID") != null ? obj.get("INDUSTRYID") + "" : "");
					dto.setTypeOfBusiness(obj.get("TYPEOFBUSINESS") != null ? obj.get("TYPEOFBUSINESS") + "" : "");
					dto.setCluster(obj.get("CLUSTERCASE") != null ? obj.get("CLUSTERCASE") + "" : "");
					dto.setConstitution(obj.get("CONSTITUTION") != null ? obj.get("CONSTITUTION") + "" : "");
					
					dto.setAddhar_card_no(obj.get("ADHAAR_NO") != null ? obj.get("ADHAAR_NO") + "" : "");
					

					dto.setResiHomeName(obj.get("RESHOMENAME") != null ? obj.get("RESHOMENAME") + "" : "");
					dto.setPermHomeName(obj.get("PERHOMENAME") != null ? obj.get("PERHOMENAME") + "" : "");
					dto.setOfficeHomeName(obj.get("OFFHOMENAME") != null ? obj.get("OFFHOMENAME") + "" : "");
					
					dto.setResiOwnerShip(obj.get("RESOCCUPANCY_STATUS") != null ? obj.get("RESOCCUPANCY_STATUS") + "" : "");
					dto.setPermOwnerShip(obj.get("PEROCCUPANCY_STATUS") != null ? obj.get("PEROCCUPANCY_STATUS") + "" : "");
					dto.setOfficeOwnerShip(obj.get("OFFOCCUPANCY_STATUS") != null ? obj.get("OFFOCCUPANCY_STATUS") + "" : "");
					
					if (obj.get("RESPHONE1") != null)
						dto.setResiPhone1(obj.get("RESPHONE1") + "");
					
					if (obj.get("RESPHONE2") != null)
						dto.setResiPhone2(obj.get("RESPHONE2") + "");
					
					if (obj.get("PERPHONE1") != null)
						dto.setPermPhone1(obj.get("PERPHONE1") + "");
					
					if (obj.get("PERPHONE2") != null)
						dto.setPermPhone2(obj.get("PERPHONE2") + "");
					
					if (obj.get("OFFPHONE1") != null)
						dto.setOfficePhone1(obj.get("OFFPHONE1") + "");
					
					if (obj.get("OFFPHONE2") != null)
						dto.setOfficePhone2(obj.get("OFFPHONE2") + "");
										
					if(obj.get("OFFYRS_BUSSINESS_EST")!=null)
						 dto.setOfficeBusinessEstablishYear(obj.get("OFFYRS_BUSSINESS_EST") + "");							

					dto.setOfficeCompanyName(obj.get("OFFCOMPANY") != null ? obj.get("OFFCOMPANY") + "" : "");

					if (obj.get("PAN") == null) {
						custValidationMSg = custValidationMSg + "PAN,";
					} else
						dto.setPanno(obj.get("PAN") + "");
					if (obj.get("OCCUPATION_TYPE") == null) {
						dto.setOccupation("");
						custValidationMSg = custValidationMSg + "CONSTITUTION,";
					} else {
						dto.setOccupation(obj.get("OCCUPATION_TYPE") + "");
					}

					dto.setNoofDependent(obj.get("NOOFDEPENDENTS") != null ? obj.get("NOOFDEPENDENTS") + "" : "");
					dto.setGrossIncome(obj.get("GROSS_ANNUAL_INC") != null ? obj.get("GROSS_ANNUAL_INC") + "" : "");
					String custcategory = obj.get("CUSTCATEGORY") + "";
					if (!(custcategory.equalsIgnoreCase("-1"))) {
						dto.setCustCategory(obj.get("CUSTCATEGORY") + "");
					}

					dto.setCustentityType(obj.get("CUSTENTITYTYPEID") != null ? obj.get("CUSTENTITYTYPEID") + "" : "");
					dto.setResiFlanto(obj.get("RESFLATNO") != null ? obj.get("RESFLATNO") + "" : "");
					dto.setResiFloorno(obj.get("RESFLOORNO") != null ? obj.get("RESFLOORNO") + "" : "");
					dto.setResiLocality(obj.get("RESLOCALITY") != null ? obj.get("RESLOCALITY") + "" : "");
					dto.setResiLandmark(obj.get("RESLANDMARK") != null ? obj.get("RESLANDMARK") + "" : "");

					if (!CommonUtils.toString(obj.get("PERZIPCODE")).equals("")) {
						dto.setPermZip(obj.get("PERZIPCODE") != null ? obj.get("PERZIPCODE") + "" : "");
					} 
					if (!CommonUtils.toString(obj.get("PERCITY")).equals("")) {
						dto.setPermCity(obj.get("PERCITY") != null ? obj.get("PERCITY") + "" : "");
					}
					if (!CommonUtils.toString(obj.get("PERSTATE")).equals("")) {
						dto.setPermState(obj.get("PERSTATE") != null ? obj.get("PERSTATE") + "" : "");
					} 
					if (!CommonUtils.toString(obj.get("PERPHONE1")).equals("")) {
						dto.setPermPhone1(obj.get("PERPHONE1") != null ? obj.get("PERPHONE1") + "" : "");
					} 
					if (!CommonUtils.toString(obj.get("PEROCCUPANCY_STATUS")).equals("")) {
						dto.setPermOwnerShip(obj.get("PEROCCUPANCY_STATUS") != null ? obj.get("PEROCCUPANCY_STATUS") + "" : "");
					}

					dto.setOfficeFlanto(obj.get("OFFFLATNO") != null ? obj.get("OFFFLATNO") + "" : "");
					dto.setOfficeFloorno(obj.get("OFFFLOORNO") != null ? obj.get("OFFFLOORNO") + "" : "");
					dto.setOfficeLocality(obj.get("OFFLOCALITY") != null ? obj.get("OFFLOCALITY") + "" : "");
					dto.setOfficeLandmark(obj.get("OFFLANDMARK") != null ? obj.get("OFFLANDMARK") + "" : "");					
					dto.setPermFlanto(obj.get("PERFLATNO") != null ? obj.get("PERFLATNO") + "" : "");
					dto.setPermFloorno(obj.get("PERFLOORNO") != null ? obj.get("PERFLOORNO") + "" : "");
					dto.setPermLocality(obj.get("PERLOCALITY") != null ? obj.get("PERLOCALITY") + "" : "");
					dto.setPermLandmark(obj.get("PERLANDMARK") != null ? obj.get("PERLANDMARK") + "" : "");
					

					if (obj.get("LOAN_TENURE") == null) {
						prodvalidationMSg = prodvalidationMSg + "LOAN_TENURE,";
					} else
						dto.setLoanTenure(obj.get("LOAN_TENURE") + "");

					if (obj.get("SCHEME_ID") == null) {
						prodvalidationMSg = prodvalidationMSg + "SCHEME_ID,";
					} else
						dto.setSchemeId(obj.get("SCHEME_ID") + "");

					if (obj.get("PURPOSEOFLOANID") == null)
						prodvalidationMSg = prodvalidationMSg + "PURPOSE OF LOAN ,";
					else {
						String loanid = obj.get("PURPOSEOFLOANID") + "";
						if (loanid.equalsIgnoreCase("-1"))
							prodvalidationMSg = prodvalidationMSg + "PURPOSE OF LOAN ,";
						else
							dto.setPurposeofLoan(obj.get("PURPOSEOFLOANID") + "");
					}

					if (obj.get("LOAN_AMOUNT") == null) {
						prodvalidationMSg = prodvalidationMSg + "LOAN_AMOUNT,";
					} else
						dto.setLoanAmount(obj.get("LOAN_AMOUNT") + "");


				}
			}
			session.flush();
			if (prodvalidationMSg.equalsIgnoreCase("") && custValidationMSg.equalsIgnoreCase("")) {
				if (dto != null) {
					status = convertLeadToCustomer(dto, userId, session, branchId);
					// status = convertLeadToCustomerService(dto, userId,
					// session,branchId,entityType);
				}
				session.flush();
				if (status.split("~")[0].equalsIgnoreCase("S")) {

					String sqlupdate = "UPDATE QC_PROSPECT.QT_CASE SET STAGE_ID=:stage, PROSPECTID =:prospectId, APPLICANTID=:appId"
							+ " WHERE CASE_ID=" + caseId;
					Query queryCase = session.createSQLQuery(sqlupdate)
							.setParameter("stage", "1000000005")
							.setParameter("prospectId", status.split("~")[2])
							.setParameter("appId", status.split("~")[3]);
					flag = queryCase.executeUpdate();

					ContactDto contactDto = new ContactDto();
					contactDto.setCaseId(caseId);
					contactDto.setActionId("30");
					contactDto.setDisposition("30");
					contactDto.setUserId(userId + "");
					contactDto.setLeadStage("1000000005");
					contactDto.setFollowupAction("-1");
					contactDto.setPotential("-1");
					contactDto.setActionDate(CommonUtils.getCurrentDateInDDMMMYYY());
					contactDto.setActionTime("12:00AM");

					if (status.split("~")[2] != null)
						contactDto.setRemarks("Lead has been converted to customer prospectId " + status.split("~")[2]);
					else
						contactDto.setRemarks("Lead has been converted to customer prospectId");

					boolean isActionAdded = addActionForConvertToCustomerLead(contactDto);

				}
				if (status.split("~")[0].equalsIgnoreCase("F")) {
					return "F~" + "There is some problem while converting Lead to Loan" + status.split("~")[1];
				}
			} else {
				// int c=0;
				if (!(custValidationMSg.equalsIgnoreCase(""))) {
					String cString = custValidationMSg.substring(0, custValidationMSg.length() - 1);
					// custValidationMSg=cString+" ARE MANDATORY FIELDS IN
					// CUSTOMER SCREEN\"\n";
					custValidationMSg = cString + " ARE MANDATORY FIELDS IN CUSTOMER SCREEN    ";
				}
				if (!(prodvalidationMSg.equalsIgnoreCase(""))) {
					String cString = prodvalidationMSg.substring(0, prodvalidationMSg.length() - 1);
					// prodvalidationMSg="\""+cString+" ARE MANDATORY FIELDS IN
					// PRODUCT SCREEN";
					prodvalidationMSg = cString + " ARE MANDATORY FIELDS IN PRODUCT SCREEN";
				}
				String finalStr = custValidationMSg + prodvalidationMSg;
				return "F~" + finalStr;

			}

		} catch (Exception e) {
			logger.error("ProductDaoImpl | fetchProduct() | :- error:::::" + e.getMessage());
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		logger.info("ProductDaoImpl | fetchProduct() | :- END:::");
		// return products;

		return status.split("~")[0] + "~" + status.split("~")[4];
	}

	private String dateParser(String startDate) {
		// SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");

		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MMM-yyyy");

		String logDate = "";

		// String date = null;
		try {
			// date = sdf1.parse(startDate);
			logDate = sdf2.format(sdf1.parse(startDate));

			// date=sdf1.format(startDate);
			// sqlStartDate = new java.sql.Date(date.getTime());
		} catch (Exception e) {

		}
		return logDate;
	}

	private String convertLeadToCustomer(ConvertToCustomerDto dto, int userId, Session session, String branchId) {

		logger.info("ProductDaoImpl | convertLeadToCustomer() | :- START:::");
		Connection con = null;
		CallableStatement cstmt = null;
		ResultSet rs = null;

		String finalMsg = "";
		try {
			con = ConnectionFromSession.getJavaSqlConnectionFromHibernateSession(session);
			cstmt = con.prepareCall("{call QC_LOS.PKG_LOS_NEWPROSPECT.SAVENEWPROSPECTVALUE(?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,? ,?,?,?,?,?, ?,?,?,?,? ,?,?,?,?,? ,?,?,?,?,? ,?,?,?,?,? ,?,?,?,?,? ,?,?,?,?,? ,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,?,?,?)}");
			// cstmt =
			// con.prepareCall("{call
			// QC_LOS.PKG_LOS_NEWPROSPECT.SAVENEWPROSPECTVALUE(?,?,?,?,?,
			// ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?,
			// ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?,
			// ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?,
			// ?,?,?,?,?, ?,?)}");
			logger.info("{call QC_LOS.PKG_LOS_NEWPROSPECT.SAVENEWPROSPECTVALUE(?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,? ,?,?,?,?,?, ?,?,?,?,? ,?,?,?,?,? ,?,?,?,?,? ,?,?,?,?,? ,?,?,?,?,? ,?,?,?,?,? ,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,?,?)}");
			// logger.info("{call
			// QC_LOS.PKG_LOS_NEWPROSPECT.SAVENEWPROSPECTVALUE(?,?,?,?,?,
			// ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?,
			// ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?,
			// ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?,
			// ?,?,?,?,?, ?,?}");

			logger.info("P_PRODUCT " + " type=in value=" + dto.getProduct());
			if (dto.getProduct() != null)
				cstmt.setInt(1, Integer.parseInt(dto.getProduct()));
			else
				cstmt.setNull(1, java.sql.Types.INTEGER);
			
			logger.info("P_SRCTYPE " + " type=in value=null");
			cstmt.setNull(2, java.sql.Types.INTEGER);
			
			logger.info("P_SRCAGENCY  " + " type=in value=null");
			cstmt.setNull(3, java.sql.Types.INTEGER);
			
			logger.info("P_SRCAGENT  " + " type=in value=null");
			cstmt.setNull(4, java.sql.Types.INTEGER);
			
			logger.info("P_FFLTYPE  " + " type=in value=null");
			cstmt.setNull(5, java.sql.Types.INTEGER);
			
			logger.info("P_FFLAGENCY  " + " type=in value=null");
			cstmt.setNull(6, java.sql.Types.INTEGER);
			logger.info("P_FFLAGENT  " + " type=in value=null");
			cstmt.setNull(7, java.sql.Types.INTEGER);
			// if(dto.getEntity_type().equalsIgnoreCase("1000000001"))
			logger.info("P_FNAME  " + " type=in value=" + dto.getFname());
			cstmt.setString(8, dto.getFname() == null ? "" : dto.getFname());
			logger.info("P_MNAME  " + " type=in value=" + dto.getMname());
			cstmt.setString(9, dto.getMname() == null ? "" : dto.getMname());
			logger.info("P_LNAME  " + " type=in value=" + dto.getLname());
			cstmt.setString(10, dto.getLname() == null ? "" : dto.getLname());
			logger.info("P_FATHERFNAME  " + " type=in value=null");
			cstmt.setNull(11, java.sql.Types.VARCHAR);
			logger.info("P_FATHERMNAME  " + " type=in value=null");
			cstmt.setNull(12, java.sql.Types.VARCHAR);
			logger.info("P_FATHERLNAME  " + " type=in value=null");
			cstmt.setNull(13, java.sql.Types.VARCHAR);
			logger.info("P_MOTHERFNAME  " + " type=in value=null");
			cstmt.setNull(14, java.sql.Types.VARCHAR);
			logger.info("P_MOTHERMNAME  " + " type=in value=null");
			cstmt.setNull(15, java.sql.Types.VARCHAR);
			logger.info("P_MOTHERLNAME  " + " type=in value=null");
			cstmt.setNull(16, java.sql.Types.VARCHAR);
			logger.info("P_MARITALSTATUS  " + " type=in value=" + dto.getMaritalStatus());
			if (dto.getMaritalStatus() != null && (!dto.getMaritalStatus().equalsIgnoreCase(""))
					&& (!dto.getMaritalStatus().equalsIgnoreCase("-1")))
				cstmt.setInt(17, Integer.parseInt(dto.getMaritalStatus()));
			else
				cstmt.setNull(17, java.sql.Types.INTEGER);

			logger.info("P_DATEOFBIRTH  " + "type=in value=" + dto.getDateofBirth());
			if (dto.getDateofBirth() != null) {
				cstmt.setDate(18, CommonUtils.getSqlDateFromString(dto.getDateofBirth()));
			} else {
				cstmt.setNull(18, java.sql.Types.DATE);
			}

			logger.info("P_GENDER  " + " type=in value=" + dto.getGender());
			if (dto.getGender() != null && (!dto.getGender().equalsIgnoreCase(""))
					&& (!dto.getGender().equalsIgnoreCase("-1")))
				cstmt.setString(19, dto.getGender());
			else
				cstmt.setNull(19, java.sql.Types.VARCHAR);

			logger.info("P_RESISTATUS  " + " type=in value=null");
			cstmt.setNull(20, java.sql.Types.VARCHAR);
			logger.info("P_EDUQULIFICATION  " + " type=in value=null");
			cstmt.setNull(21, java.sql.Types.INTEGER);
			logger.info("P_HIGHQUALIFICTAION  " + " type=in value=null");
			cstmt.setNull(22, java.sql.Types.INTEGER);
			logger.info("P_CUSTCATEGORY  " + " type=in value=null");
			cstmt.setNull(23, java.sql.Types.INTEGER);
			/*
			 * cstmt.setString(23, dto.getCustCategory() == null ? "" :
			 * dto.getCustCategory());
			 */
			logger.info("P_RELIGION  " + " type=in value=null");
			cstmt.setNull(24, java.sql.Types.VARCHAR);
			logger.info("P_RESIFLATNO  " + " type=in value=" + dto.getResiHomeName());
			cstmt.setString(25, dto.getResiHomeName() == null ? "" : dto.getResiHomeName());
			logger.info("P_RESIFLOORNO  " + " type=in value=" + dto.getResiFloorno());
			cstmt.setString(26, dto.getResiFloorno() == null ? "" : dto.getResiFloorno());
			logger.info("P_RESIBULDINGNAME  " + " type=in value=" + dto.getResiFlanto());
			cstmt.setString(27, dto.getResiFlanto() == null ? "" : dto.getResiFlanto());
			logger.info("P_RESILOCALITY" + " type=in value=" + dto.getResiLocality());
			cstmt.setString(28, dto.getResiLocality() == null ? "" : dto.getResiLocality());
			logger.info("P_RESILANDMARK  " + " type=in value=" + dto.getResiLandmark());
			cstmt.setString(29, dto.getResiLandmark() == null ? "" : dto.getResiLandmark());
			logger.info("P_RESICITY  " + " type=in value=" + dto.getResiCity());
			cstmt.setString(30, dto.getResiCity() == null ? "" : dto.getResiCity());
			logger.info("P_RESISTATE  " + " type=in value=" + dto.getResiState());
			cstmt.setString(31, dto.getResiState() == null ? "" : dto.getResiState());
			logger.info("P_RESIZIP  " + " type=in value=" + dto.getResiZip());
			cstmt.setString(32, dto.getResiZip() == null ? "" : dto.getResiZip());
			logger.info("P_RESIMOBILE  " + " type=in value=" + dto.getResimob1());
			cstmt.setString(33, dto.getResimob1());
			logger.info("P_RESIPHONE  " + " type=in value=" + dto.getResiPhone1());
			cstmt.setString(34, dto.getResiPhone1() == null ? "" : dto.getResiPhone1());
			logger.info("P_RESIEMAIL  " + " type=in value=" + dto.getResiEmail());
			cstmt.setString(35, dto.getResiEmail() == null ? "" : dto.getResiEmail());
			logger.info("P_RESIOWNERSHIP  " + " type=in value=" + dto.getResiOwnerShip());
			cstmt.setString(36, dto.getResiOwnerShip() == null ? null : dto.getResiOwnerShip());

			logger.info("P_YEARSATRESI  " + " type=in value="+CommonUtils.getTotalNumberOfYearsFromDateYear(dto.getResiLivingHereSinceOccupancyYear())+"");
			cstmt.setString(37, CommonUtils.getTotalNumberOfYearsFromDateYear(dto.getResiLivingHereSinceOccupancyYear())+"");
			/*
			 * if (dto.getYearsatresi() != null &&
			 * (!(dto.getYearsatresi().equalsIgnoreCase("")))) cstmt.setInt(37,
			 * 2); else
			 */

			logger.info("P_MONTHSATRESI  " + " type=in value=null");
			cstmt.setNull(38, java.sql.Types.INTEGER);
			/*
			 * if (dto.getMonthSathresi() != null &&
			 * (!(dto.getMonthSathresi().equalsIgnoreCase(""))))
			 * cstmt.setInt(38,2); else
			 */

			logger.info("P_PERMFLATNO  " + " type=in value=" + dto.getPermHomeName());
			cstmt.setString(39, dto.getPermFlanto() == null ? "" : dto.getPermHomeName());
			logger.info("P_PERMFLOORNO  " + " type=in value=" + dto.getPermFloorno());
			cstmt.setString(40, dto.getPermFloorno() == null ? "" : dto.getPermFloorno());
			logger.info("P_PERMBULDINGNAME  " + " type=in value=" + dto.getPermFlanto());
			cstmt.setString(41, dto.getPermFlanto() == null ? "" : dto.getPermFlanto());
			logger.info("P_PERMLOCALITY  " + " type=in value=" + dto.getPermLocality());
			cstmt.setString(42, dto.getPermLocality() == null ? "" : dto.getPermLocality());
			logger.info("P_PERMLANDMARK  " + " type=in value=" + dto.getPermLandmark());
			cstmt.setString(43, dto.getPermLandmark() == null ? "" : dto.getPermLandmark());
			logger.info("P_PERMCITY  " + " type=in value=" + dto.getPermCity());
			cstmt.setString(44, dto.getPermCity() == null ? "" : dto.getPermCity());
			logger.info("P_PERMSTATE  " + " type=in value=" + dto.getPermState());
			cstmt.setString(45, dto.getPermState() == null ? "" : dto.getPermState());
			logger.info("P_PERMZIP  " + " type=in value=" + dto.getPermZip());
			cstmt.setString(46, dto.getPermZip() == null ? "" : dto.getPermZip());
			logger.info("P_PERMMOBILE  " + " type=in value="+dto.getPermmob1());
			cstmt.setString(47, dto.getPermmob1() == null ? "" : dto.getPermmob1());
			logger.info("P_PERMPHONE  " + " type=in value=" + dto.getPermPhone1());
			cstmt.setString(48, dto.getPermPhone1() == null ? "" : dto.getPermPhone1());
			logger.info("P_PERMEMAIL" + " type=in value="+dto.getPermEmail());
			cstmt.setString(49, dto.getPermEmail());
			logger.info("P_PERMYEARSATRESI  " + " type=in value=null");
			cstmt.setNull(50, java.sql.Types.INTEGER);
			/*
			 * if (dto.getPermYearSatresi() != null &&
			 * (!(dto.getPermYearSatresi().equalsIgnoreCase(""))))
			 * cstmt.setInt(50, Integer.parseInt(dto.getPermYearSatresi()));
			 * else
			 */
			logger.info("P_PERMMONTHSATRESI  " + " type=in value=null");
			cstmt.setNull(51, java.sql.Types.INTEGER);
			/*
			 * if (dto.getPermMonthSatresi() != null &&
			 * (!(dto.getPermMonthSatresi().equalsIgnoreCase(""))))
			 * cstmt.setInt(51, Integer.parseInt(dto.getPermMonthSatresi()));
			 * else
			 */
			//cstmt.setNull(51, java.sql.Types.INTEGER);
			logger.info("P_LOCATIONCATEGORY  " + " type=in value=null");
			cstmt.setNull(52, java.sql.Types.VARCHAR);
			logger.info("P_NOOFDEPENDANT  " + " type=in value=" + dto.getNoofDependent());

			if (dto.getNoofDependent() != null && (!(dto.getNoofDependent().equalsIgnoreCase(""))))
				cstmt.setInt(53, Integer.parseInt(dto.getNoofDependent()));
			else
				cstmt.setNull(53, java.sql.Types.INTEGER);

			logger.info("P_OCCUPATION  " + " type=in value=" + dto.getOccupation());
			cstmt.setString(54, dto.getOccupation() == null ? "" : dto.getOccupation());

			logger.info("P_GROSSINCOME  " + " type=in value=" + dto.getGrossIncome());
			if (dto.getGrossIncome() != null && (!(dto.getGrossIncome().equalsIgnoreCase(""))))
				cstmt.setInt(55, Integer.parseInt(dto.getGrossIncome()));
			else
				cstmt.setNull(55, java.sql.Types.INTEGER);

			logger.info("P_ISPANNO  " + " type=in value=null");
			cstmt.setNull(56, java.sql.Types.VARCHAR);

			logger.info("P_PANNO  " + " type=in value=" + dto.getPanno());
			cstmt.setString(57, dto.getPanno() == null ? "" : dto.getPanno());

			logger.info("P_DECLARATION  " + " type=in value=null");
			cstmt.setNull(58, java.sql.Types.VARCHAR);

			logger.info("P_LOANAMOUNT  " + " type=in value=" + dto.getLoanAmount());

			if (dto.getLoanAmount() != null && (!(dto.getLoanAmount().equalsIgnoreCase(""))))
				cstmt.setInt(59, Integer.parseInt(dto.getLoanAmount()));
			else
				cstmt.setNull(59, java.sql.Types.INTEGER);

			logger.info("P_TENOR  " + " type=in value=" + dto.getLoanTenure());
			if (dto.getLoanTenure() != null && (!(dto.getLoanTenure().equalsIgnoreCase(""))))
				cstmt.setInt(60, Integer.parseInt(dto.getLoanTenure()));
			else
				cstmt.setNull(60, java.sql.Types.INTEGER);

			logger.info("P_PURPOSEOFLOAN  " + " type=in value=" + dto.getPurposeofLoan());
			if (dto.getPurposeofLoan() != null && (!(dto.getPurposeofLoan().equalsIgnoreCase(""))))
				cstmt.setInt(61, Integer.parseInt(dto.getPurposeofLoan()));
			else
				cstmt.setNull(61, java.sql.Types.INTEGER);

			logger.info("P_USERID" + " type=in value=" + userId);
			cstmt.setInt(62, userId);

			logger.info("P_PROSPECTID type=out");
			cstmt.registerOutParameter(63, java.sql.Types.INTEGER);

			logger.info("P_PROSPECTNO type=out");
			cstmt.registerOutParameter(64, java.sql.Types.VARCHAR);

			logger.info("V_RETURN type=out");
			cstmt.registerOutParameter(65, java.sql.Types.VARCHAR);

			logger.info("P_MESSAGE  type=out");
			cstmt.registerOutParameter(66, java.sql.Types.VARCHAR);

			logger.info("P_BRANCH" + " type=in value=" + branchId);
			if (branchId != null && (!(branchId.equalsIgnoreCase(""))))
				cstmt.setInt(67, Integer.parseInt(branchId));
			else
				cstmt.setNull(67, java.sql.Types.INTEGER);

			logger.info("P_SCHEME " + " type=in value=" + dto.getSchemeId());
			if (dto.getSchemeId() != null && (!(dto.getSchemeId().equalsIgnoreCase(""))))
				cstmt.setInt(68, Integer.parseInt(dto.getSchemeId()));
			else
				cstmt.setNull(68, java.sql.Types.INTEGER);

			logger.info("P_APPLICANTID  type=in value=null");
			cstmt.setNull(69, java.sql.Types.INTEGER);

			logger.info("P_APPLICANTID  type=out");
			cstmt.registerOutParameter(69, java.sql.Types.INTEGER);

			logger.info("P_HASINSURANCE" + " type=in value=null");
			cstmt.setNull(70, java.sql.Types.VARCHAR);

			logger.info("P_SUMASSURED" + " type=in value=null");
			cstmt.setNull(71, java.sql.Types.INTEGER);

			logger.info("P_PREMIUMAMT " + " type=in value=null");
			cstmt.setNull(72, java.sql.Types.INTEGER);

			logger.info("P_INSURANCETENOR " + " type=in value=null");
			cstmt.setNull(73, java.sql.Types.INTEGER);

			logger.info("P_APPLICATIONDATE" + " type=in value="+leadService.getBusinessDate());
			cstmt.setString(74, leadService.getBusinessDate());

			logger.info("P_FORMNUMBER " + " type=in value=null");
			cstmt.setNull(75, java.sql.Types.VARCHAR);

			logger.info("P_CUSTENTITYTYPE " + " type=in value=" + dto.getCustentityType());
			if (dto.getCustentityType() != null && (!(dto.getCustentityType().equalsIgnoreCase("")))
					&& (!(dto.getCustentityType().equalsIgnoreCase("-1"))))
				cstmt.setInt(76, Integer.parseInt(dto.getCustentityType()));
			// cstmt.setInt(76, Integer.parseInt("1000000002"));
			else
				cstmt.setNull(76, java.sql.Types.INTEGER);

			logger.info("P_OFFFLATNO " + " type=in value=" + dto.getOfficeHomeName());
			cstmt.setString(77, dto.getOfficeFlanto() == null ? "" : dto.getOfficeHomeName());

			logger.info("P_OFFFLOORNO  " + " type=in value=" + dto.getOfficeFloorno());
			cstmt.setString(78, dto.getOfficeFloorno() == null ? "" : dto.getOfficeFloorno());

			logger.info("P_OFFBULDINGNAME " + " type=in value=" + dto.getOfficeFlanto());
			cstmt.setString(79, dto.getOfficeFlanto() == null ? "" : dto.getOfficeFlanto());

			logger.info("P_OFFLOCALITY " + " type=in value=" + dto.getOfficeLocality());
			cstmt.setString(80, dto.getOfficeLocality() == null ? "" : dto.getOfficeLocality());

			logger.info("P_OFFLANDMARK " + " type=in value=" + dto.getOfficeLandmark());
			cstmt.setString(81, dto.getOfficeLandmark() == null ? "" : dto.getOfficeLandmark());

			logger.info("P_OFFCITY " + " type=in value=" + dto.getOfficeCity());
			cstmt.setString(82, dto.getOfficeCity() == null ? "" : dto.getOfficeCity());

			logger.info("P_OFFSTATE " + " type=in value=" + dto.getOfficeState());
			cstmt.setString(83, dto.getOfficeState() == null ? "" : dto.getOfficeState());

			logger.info("P_OFFZIP " + " type=in value=" + dto.getOfficeZip());
			cstmt.setString(84, dto.getOfficeZip() == null ? "" : dto.getOfficeZip());

			logger.info("P_OFFMOBILE" + " type=in value=" + dto.getOfficemob1());
			cstmt.setString(85, dto.getOfficemob1());

			logger.info("P_OFFPHONE" + " type=in value=" + dto.getOfficePhone1());
			cstmt.setString(86, dto.getOfficePhone1() == null ? "" : dto.getOfficePhone1());

			logger.info("P_OFFEMAIL" + " type=in value=" + dto.getOfficeEmail());
			cstmt.setString(87, dto.getOfficeEmail() == null ? "" : dto.getOfficeEmail());

			logger.info("P_OFFLOCATIONCATEGORY" + " type=in value="+dto.getOfficeOwnerShip());
			cstmt.setString(88, dto.getOfficeOwnerShip());

			logger.info("P_OFFCOMPANYNAME  " + " type=in value=" + dto.getOfficeCompanyName());
			cstmt.setString(89, dto.getOfficeCompanyName() == null ? "" : dto.getOfficeCompanyName());

			logger.info("P_OFFYEARSATRESI  " + " type=in value="+CommonUtils.getTotalNumberOfYearsFromDateYear(dto.getOfficeLivingHereSincenceOccupancyYear())+"");
			cstmt.setString(90, CommonUtils.getTotalNumberOfYearsFromDateYear(dto.getOfficeLivingHereSincenceOccupancyYear())+"");
			/*
			 * if (dto.getOffYearSatersi() != null &&
			 * (!(dto.getOffYearSatersi().equalsIgnoreCase(""))))
			 * cstmt.setInt(90, 2); else
			 */

			logger.info("P_OFFMONTHSATRESI  " + " type=in value=null");
			cstmt.setNull(91, java.sql.Types.INTEGER);
			/*
			 * if (dto.getOffMonthSatresi() != null &&
			 * (!dto.getOffMonthSatresi().equalsIgnoreCase("")))
			 * cstmt.setInt(91, 2); else
			 */

			logger.info("P_MAILINGADDRESS " + " type=in value=" + (CommonUtils.toString(dto.getMailingAddress()).equals("") ? "R" : CommonUtils.toString(dto.getMailingAddress())));
			// cstmt.setString(92, dto.getMailingAddress() == null ? "" :
			// dto.getMailingAddress());
			cstmt.setString(92, CommonUtils.toString(dto.getMailingAddress()).equals("") ? "R"
					: CommonUtils.toString(dto.getMailingAddress()));

			logger.info("P_PROMOTIONSCHEME" + " type=in value=null");
			cstmt.setNull(93, java.sql.Types.INTEGER);

			logger.info("P_BRANCHMANAGER" + " type=in value=null");
			cstmt.setNull(94, java.sql.Types.INTEGER);

			logger.info("P_SALESMANAGER" + " type=in value=null");
			cstmt.setNull(95, java.sql.Types.INTEGER);

			logger.info("P_FAX" + " type=in value="+dto.getOfficeFax());
			cstmt.setString(96,dto.getOfficeFax());

			logger.info("P_EXTENSION " + " type=in value=null");
			cstmt.setNull(97, java.sql.Types.INTEGER);

			/**/
			logger.info("98" + " value=null");
			cstmt.setNull(98, java.sql.Types.INTEGER);
			logger.info("99" + " value=null");
			cstmt.setNull(99, java.sql.Types.VARCHAR);
			logger.info("100" + " value=null");
			cstmt.setNull(100, java.sql.Types.VARCHAR);
			logger.info("101" + " value=null");
			cstmt.setNull(101, java.sql.Types.VARCHAR);
			logger.info("102" + " value=null");
			cstmt.setNull(102, java.sql.Types.VARCHAR);

			/**/

			logger.info("P_VERIFICATIONCODE  " + " type=in value=null");
			cstmt.setNull(103, java.sql.Types.VARCHAR);

			logger.info("P_VERIFICATIONMSG   " + " type=in value=null");
			cstmt.setNull(104, java.sql.Types.VARCHAR);

			/**/
			logger.info("105" + " value=null");
			cstmt.setNull(105, java.sql.Types.VARCHAR);
			logger.info("106" + " value=null");
			cstmt.setNull(106, java.sql.Types.BLOB);
			logger.info("107" + " value=null");
			cstmt.setNull(107, java.sql.Types.VARCHAR);
			logger.info("108" + " value=null");
			cstmt.setNull(108, java.sql.Types.VARCHAR);
			logger.info("109" + " value=null");
			cstmt.setNull(109, java.sql.Types.INTEGER);
			logger.info("110" + " value=null");
			cstmt.setNull(110, java.sql.Types.VARCHAR);
			logger.info("111" + " value=null");
			cstmt.setNull(111, java.sql.Types.VARCHAR);
			logger.info("P_LEGAL_FORM_TYPE CONSTITUTION value" + " type=in value=" + dto.getConstitution());
			if (dto.getConstitution() != null && (!(dto.getConstitution().equalsIgnoreCase(""))))
				cstmt.setInt(112, Integer.parseInt(dto.getConstitution()));
			else
				cstmt.setNull(112, java.sql.Types.INTEGER);
			logger.info("113" + " value=null");
			cstmt.setNull(113, java.sql.Types.VARCHAR);
			logger.info("114" + " value=null");
			cstmt.setNull(114, java.sql.Types.INTEGER);
			logger.info("115" + " value=null");
			cstmt.setNull(115, java.sql.Types.INTEGER);
			logger.info("116" + " value=null");
			cstmt.setNull(116, java.sql.Types.DATE);
			logger.info("117" + " value=null");
			cstmt.setNull(117, java.sql.Types.VARCHAR);
			logger.info("118" + " value=null");
			cstmt.setNull(118, java.sql.Types.VARCHAR);
			logger.info("119" + " value=null");
			cstmt.setNull(119, java.sql.Types.INTEGER);
			logger.info("120" + " value=null");
			cstmt.setNull(120, java.sql.Types.INTEGER);
			logger.info("121" + " value=null");
			cstmt.setNull(121, java.sql.Types.VARCHAR);
			/**/
			logger.info("P_RESIACCOMODATIONTYPE  " + " type=in value=" + dto.getResiOwnerShip());
			cstmt.setString(122, dto.getResiOwnerShip());

			logger.info("P_PERMACCOMODATIONTYPE  " + " type=in value="+dto.getPermOwnerShip());
			cstmt.setString(123, dto.getPermOwnerShip());

			logger.info("P_OFFACCOMODATIONTYPE  " + " type=in value=" + dto.getOfficeOwnerShip());
			cstmt.setString(124, dto.getOfficeOwnerShip());
			// NEW
			logger.info("P_LOANTYPE   " + " type=in value=null");
			cstmt.setNull(125, java.sql.Types.VARCHAR);

			logger.info("P_TITLE  " + " type=in value=" + dto.getTitle());
			cstmt.setString(126, dto.getTitle());

			logger.info("P_ADHAAR_CARD_NO " + " type=in value=" + dto.getAddhar_card_no());
			cstmt.setString(127, dto.getAddhar_card_no());

			logger.info("P_SPOUSEFNAME  " + " type=in value=null");
			cstmt.setNull(128, java.sql.Types.VARCHAR);

			logger.info(" P_SPOUSEMNAME  " + " type=in value=null");
			cstmt.setNull(129, java.sql.Types.VARCHAR);

			logger.info("P_SPOUSELNAME  " + " type=in value=null");
			cstmt.setNull(130, java.sql.Types.VARCHAR);

			logger.info("P_YEARS_OF_STAY_AT_RESI  " + " type=in value=null");
			cstmt.setNull(131, java.sql.Types.VARCHAR);
			/* cstmt.setString(131, dto.getPermYearSatresi()); */

			logger.info("P_YEARS_OF_STAY_AT_OFFICE  " + " type=in value=null");
			cstmt.setNull(132, java.sql.Types.VARCHAR);
			/* cstmt.setString(132, dto.getOffYearSatersi()); */

			logger.info("P_YEARS_OF_STAY_AT_PERMADDRESS  " + " type=in value=null");
			cstmt.setNull(133, java.sql.Types.VARCHAR);

			logger.info("P_RELATIONSHIP1  " + " type=in value=null");
			cstmt.setNull(134, java.sql.Types.VARCHAR);

			logger.info("P_RELATIONSHIP2  " + " type=in value=null");
			cstmt.setNull(135, java.sql.Types.VARCHAR);

			logger.info("P_NICK_NAME  " + " type=in value=null");
			cstmt.setNull(136, java.sql.Types.VARCHAR);

			logger.info("P_APPLICANT_AGE  " + " type=in value="+CommonUtils.getAgeFromDob(dto.getDateofBirth(),leadService.getBusinessDate())+"");
			cstmt.setString(137, CommonUtils.getAgeInYearsAndMonthsFromDOB("year",dto.getDateofBirth(),leadService.getBusinessDate())+"" );

			logger.info("P_NO_OF_OTHER_DEPENDENTS  " + " type=in value=" + dto.getNoofDependent());
			cstmt.setString(138, dto.getNoofDependent());

			logger.info("P_TYPE_OF_BUSINESS  " + " type=in value=" + dto.getTypeOfBusiness());
			cstmt.setString(139, dto.getTypeOfBusiness());

			logger.info(" P_INDUSTRY  " + " type=in value=" + dto.getIndustryType());
			cstmt.setString(140, dto.getIndustryType());

			logger.info("P_CLUSTER_VAL  " + " type=in value=" + dto.getCluster());
			cstmt.setString(141, dto.getCluster());

			logger.info("P_MOBILE2  " + " type=in value=" + dto.getResimob2());
			cstmt.setString(142, dto.getResimob2());

			logger.info("PC_RESI_OLD_ADDRESS  " + " type=in value="+dto.getResiOldAddress());
			cstmt.setString(143, dto.getResiOldAddress());

			logger.info("PC_OLD_APPLICATIONID  " + " type=in value=null");
			cstmt.setNull(144, java.sql.Types.VARCHAR);

			logger.info("PN_AFFORD_EMI  " + " type=in value=null");
			cstmt.setNull(145, java.sql.Types.VARCHAR);			
			
			logger.info("PN_CASTE_CATEGORY  " + " type=in value=null");
			cstmt.setNull(146, java.sql.Types.VARCHAR);

			logger.info("PC_AUTH_SIGNATORIES  " + " type=in value=null");
			cstmt.setNull(147, java.sql.Types.VARCHAR);

			logger.info("PN_RESI_MARKET_VALUE  " + " type=in value="+dto.getResiMarketValue());
			cstmt.setString(148, dto.getResiMarketValue());

			logger.info("PN_OFFC_MARKET_VALUE   " + " type=in value="+dto.getOfficeMarketValue());
			cstmt.setString(149, dto.getOfficeMarketValue());

			logger.info("PC_RESIPHONE2   " + " type=in value="+dto.getResiPhone2());
			cstmt.setString(150, dto.getResiPhone2());

			logger.info("PC_OFFC_YEAROFBUS_EST  " + " type=in value=" + CommonUtils.getTotalNumberOfYearsFromDateYear(dto.getOfficeBusinessEstablishYear())+"");
			cstmt.setString(151, CommonUtils.getTotalNumberOfYearsFromDateYear(dto.getOfficeBusinessEstablishYear())+"");

			logger.info("PC_OFFC_OLD_ADDRESS " + " type=in value="+dto.getOfficeOldAddress());
			cstmt.setString(152, dto.getOfficeOldAddress());

			logger.info("PC_PERMOWNERSHIP  " + " type=in value="+dto.getPermOwnerShip());
			cstmt.setString(153, dto.getPermOwnerShip());

			logger.info("PC_OFFCOWNERSHIP  " + " type=in value="+dto.getOfficeOwnerShip());
			cstmt.setString(154, dto.getOfficeOwnerShip());
						
			logger.info("PN_AGE_MONTH " + " type=in value=null");
			cstmt.setString(155, CommonUtils.getAgeInYearsAndMonthsFromDOB("months",dto.getDateofBirth(),leadService.getBusinessDate())+"");

			logger.info("PN_STAY_IN_AREA_MNTHS " + " type=in value=null");
			cstmt.setNull(156, java.sql.Types.VARCHAR);

			logger.info("PN_OFFC_STAY_IN_AREA_MNTHS " + " type=in value=null");
			cstmt.setNull(157, java.sql.Types.VARCHAR);

			logger.info("P_GSTINNO  " + " type=in value=null");
			cstmt.setNull(158, java.sql.Types.VARCHAR);

			logger.info("P_OFF_GSTINNO  " + " type=in value=null");
			cstmt.setNull(159, java.sql.Types.VARCHAR);

			logger.info("P_PERM_GSTINNO  " + " type=in value=null");
			cstmt.setNull(160, java.sql.Types.VARCHAR);

			logger.info("P_DESTINATION_ADDRESS  " + " type=in value=null");
			cstmt.setNull(161, java.sql.Types.VARCHAR);

			logger.info("P_NAME_AS_PER_AADHAAR  " + " type=in value=null");
			cstmt.setNull(162, java.sql.Types.VARCHAR);

			logger.info("P_CLUSTER_DESCRIPTION  " + " type=in value=null");
			cstmt.setNull(163, java.sql.Types.VARCHAR);

            logger.info("PN_PREFERRED_LANGUAGE  " + " type=in value=null");
			cstmt.setNull(164, java.sql.Types.INTEGER);

			logger.info("P_INDUSTRYTYPE  " + " type=in value=null");
			cstmt.setNull(165, java.sql.Types.VARCHAR);

			logger.info("P_LONGITUDE  " + " type=in value=null");
			cstmt.setNull(166, java.sql.Types.VARCHAR);

			logger.info("P_LATITUDE  " + " type=in value=null");
			cstmt.setNull(167, java.sql.Types.VARCHAR);

			logger.info("P_LOCATION_NETWORK_TYPE  " + " type=in value=null");
			cstmt.setNull(168, java.sql.Types.VARCHAR);

			logger.info("P_LOCATION_DATETIME  " + " type=in value=null");
			cstmt.setNull(169, java.sql.Types.VARCHAR);

			logger.info("P_DEVICE_ID  " + " type=in value=null");
			cstmt.setNull(170, java.sql.Types.VARCHAR);

			logger.info("P_APP_NAME  " + " type=in value=LEAD-WEB");
			cstmt.setString(171,"LEAD-WEB");

			cstmt.executeUpdate();
			int prospectId = cstmt.getInt(63);
			String prospectCode = cstmt.getString(64);
			String returnMsg = cstmt.getString(65);
			String appicantId = cstmt.getString(69);
			String message = cstmt.getString(66);

			// logger.info("P_MESSAGE value="+message);
			if (returnMsg.equalsIgnoreCase("S")) {
				finalMsg = returnMsg + "~" + prospectId + "~" + prospectCode + "~" + appicantId + "~" + message;
			}

		} catch (Exception e) {
			logger.error("ProductDaoImpl | convertLeadToCustomer() | :- error:::" + e.getMessage());
			e.printStackTrace();
			return null;
		} finally {
		}
		logger.info("ProductDaoImpl | getEligibilityCalcData() | :- end:::");
		return finalMsg;

	}

	@Override
	public ConvertToCustomerDto getApplicantId(String leadid) {
		logger.info("ProductDaoImpl | getApplicantId() | :- START : With request Param:: leadId::" + leadid);
		Session session = null;
		ConvertToCustomerDto dto = new ConvertToCustomerDto();
		try {
			session = sessionFactory.openSession();
			String sql = "select APPLICANTID,PROSPECTID from qc_prospect.qt_case where case_id=" + leadid;
			SQLQuery query = session.createSQLQuery(sql);
			List list = query.list();
			if (list.size() > 0) {
				Object[] obj = (Object[]) list.get(0);
				dto.setApplicantId(obj[0] == null ? "" : obj[0] + "");
				dto.setProspectId(obj[1] == null ? "" : obj[1] + "");
			}

			String sql1 = "select CONFIGID,PARAMID,PARAMNAME,PARAMVALUE from QC_PROSPECT_MASTER.QM_SYS_CONFIGURATION_LEAD";
			SQLQuery query1 = session.createSQLQuery(sql1);
			query1.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List list1 = query1.list();

			if (list1 != null && list1.size() > 0) {
				for (Object o : list1) {
					Map object = (Map) o;

					if (object.get("PARAMID") != null
							&& CommonUtils.toString(object.get("PARAMID").toString()).equals("1000000001")) {
						dto.setConvertToCustomerEnable(object.get("PARAMVALUE").toString());
					}
					if (object.get("PARAMID") != null
							&& CommonUtils.toString(object.get("PARAMID").toString()).equals("1000000002")) {
						dto.setConvertToCustomerMobileEnable(object.get("PARAMVALUE").toString());
					}
					if (object.get("PARAMID") != null
							&& CommonUtils.toString(object.get("PARAMID").toString()).equals("1000000003")) {
						dto.setConvertToCustomerWebEnable(object.get("PARAMVALUE").toString());
					}
				}
			}

			/*
			 * if (list1.size() > 0) { Object obj = (Object) list1.get(0);
			 * dto.setVerificationMsg(obj == null ? "Y" : obj + ""); }else{
			 * dto.setVerificationMsg("Y"); }
			 */

		} catch (Exception e) {
			logger.error("ProductDaoImpl | getApplicantId() | :- error:::::" + e.getMessage());
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		logger.info("ProductDaoImpl | getApplicantId() | :- END:::");
		return dto;
	}

	/*public String convertLeadToCustomerService(ConvertToCustomerDto dto, int userId, Session session, String branchId,
			String entityType) {
		JSONObject jsonObject_NewEnroll = new JSONObject();
		String finalMessage = "";
		try {
			// GST

			jsonObject_NewEnroll.put("GSTIN_NO", CommonUtils.toString(""));
			jsonObject_NewEnroll.put("OFF_GSTIN", CommonUtils.toString(""));
			jsonObject_NewEnroll.put("PERM_GSTIN", CommonUtils.toString(""));
			jsonObject_NewEnroll.put("DESTINATION_ADDRESS", CommonUtils.toString(""));

			// Loan Detail
			jsonObject_NewEnroll.put("PRODUCT_ID", CommonUtils.toString(dto.getProduct()));
			jsonObject_NewEnroll.put("LOANAMOUNT", CommonUtils.toString(dto.getLoanAmount()));
			jsonObject_NewEnroll.put("USERID", "1000000023");
			jsonObject_NewEnroll.put("BRANCH", branchId);
			jsonObject_NewEnroll.put("FORMNUMBER", "");
			jsonObject_NewEnroll.put("PURPOSEOFLOAN", CommonUtils.toString(dto.getPurposeofLoan()));
			jsonObject_NewEnroll.put("RELATIONSHIP_MGR1", CommonUtils.toString(""));
			jsonObject_NewEnroll.put("TENOR", CommonUtils.toString(dto.getLoanTenure()));
			jsonObject_NewEnroll.put("AFFORD_EMI", CommonUtils.toString(""));
			jsonObject_NewEnroll.put("NATURE_OF_LOAN", CommonUtils.toString("1000000001"));
			jsonObject_NewEnroll.put("SCHEME", CommonUtils.toString(dto.getSchemeId()));

			// Personal Detail
			jsonObject_NewEnroll.put("CUSTOMERID", CommonUtils.toString(""));
			jsonObject_NewEnroll.put("CUSTCATEGORY", CommonUtils.toString("1000000001"));
			jsonObject_NewEnroll.put("GENDER", CommonUtils.toString(dto.getGender()));
			jsonObject_NewEnroll.put("ADHAAR_CARD_NO", CommonUtils.toString(dto.getAddhar_card_no()));
			jsonObject_NewEnroll.put("CUST_CODE", CommonUtils.toString(""));
			jsonObject_NewEnroll.put("INDUSTRYTYPE", CommonUtils.toString(""));
			jsonObject_NewEnroll.put("TITLE", CommonUtils.toString(""));
			jsonObject_NewEnroll.put("CUSTENTITYTYPE", CommonUtils.toString(dto.getCustentityType()));
			jsonObject_NewEnroll.put("NICK_NAME", CommonUtils.toString("Lead"));
			jsonObject_NewEnroll.put("APPLICANT_AGE", CommonUtils.toString(""));
			jsonObject_NewEnroll.put("NO_OF_OTHER_DEPENDENTS", CommonUtils.toString(""));
			jsonObject_NewEnroll.put("TYPE_OF_BUSINESS", CommonUtils.toString(dto.getTypeOfBusiness()));
			jsonObject_NewEnroll.put("INDUSTRY", CommonUtils.toString(dto.getIndustryType()));
			jsonObject_NewEnroll.put("CLUSTER_VAL", CommonUtils.toString(dto.getCluster()));
			jsonObject_NewEnroll.put("SPOUSE_FNAME", CommonUtils.toString(""));
			jsonObject_NewEnroll.put("SPOUSE_MNAME", CommonUtils.toString(""));
			jsonObject_NewEnroll.put("SPOUSE_LNAME", CommonUtils.toString(""));
			jsonObject_NewEnroll.put("MARITALSTATUS", CommonUtils.toString(dto.getMaritalStatus()));
			jsonObject_NewEnroll.put("CASTE_CATEGORY", CommonUtils.toString(""));
			jsonObject_NewEnroll.put("NOOF_DEP_CHILD", CommonUtils.toString(""));
			jsonObject_NewEnroll.put("EDUQULIFICATION", CommonUtils.toString(""));
			jsonObject_NewEnroll.put("OLD_ADDRESS", CommonUtils.toString(""));
			jsonObject_NewEnroll.put("LANDLORD_NO", CommonUtils.toString(""));
			jsonObject_NewEnroll.put("CONSTITUTION", CommonUtils.toString(dto.getOccupation()));
			if (entityType.equals("1000000001")) {
				jsonObject_NewEnroll.put("FATHER_FNAME", CommonUtils.toString(""));
				jsonObject_NewEnroll.put("FATHER_MNAME", CommonUtils.toString(""));
				jsonObject_NewEnroll.put("FATHER_LNAME", CommonUtils.toString(dto.getFatherLname()));
				jsonObject_NewEnroll.put("CUSTOMER_NAME",
						CommonUtils.toString(dto.getFname() + " " + dto.getMname() + " " + dto.getLname()));
				jsonObject_NewEnroll.put("PANNO", CommonUtils.toString(dto.getPanno()));
				jsonObject_NewEnroll.put("DATEOFBIRTH", CommonUtils.toString(dto.getDateofBirth()));
			} else if (entityType.equals("1000000002")) {
				jsonObject_NewEnroll.put("FATHER_FNAME", CommonUtils.toString(dto.getFatherFname()));
				jsonObject_NewEnroll.put("FATHER_MNAME", CommonUtils.toString(dto.getFatherMname()));
				jsonObject_NewEnroll.put("FATHER_LNAME", CommonUtils.toString(dto.getFatherLname()));
				jsonObject_NewEnroll.put("CUSTOMER_NAME", dto.getFname());
				jsonObject_NewEnroll.put("PANNO", CommonUtils.toString(dto.getPanno()));
				jsonObject_NewEnroll.put("DATEOFBIRTH", CommonUtils.toString(dto.getDateofBirth()));
			}

			// Residence Address
			jsonObject_NewEnroll.put("RESILANDMARK", CommonUtils.toString(dto.getResiLandmark()));
			jsonObject_NewEnroll.put("RESICITY", CommonUtils.toString(dto.getResiCity()));
			jsonObject_NewEnroll.put("RESISTATE", CommonUtils.toString(dto.getResiState()));
			jsonObject_NewEnroll.put("RESIMOBILE", CommonUtils.toString(dto.getResimob1()));
			jsonObject_NewEnroll.put("RESIDISTRICT", "");
			jsonObject_NewEnroll.put("RESIPINCODE", CommonUtils.toString(dto.getResiZip()));
			jsonObject_NewEnroll.put("YEARSATRESI", CommonUtils.toString(""));
			jsonObject_NewEnroll.put("MONTHSATRESI", CommonUtils.toString(""));
			jsonObject_NewEnroll.put("RES_YROFSTAYATCURRAREA", CommonUtils.toString(dto.getYearsatresi()));
			jsonObject_NewEnroll.put("RESIACCOMODATIONTYPE", CommonUtils.toString(dto.getResiaAccomodationType()));
			jsonObject_NewEnroll.put("RESIHOUSENO", CommonUtils.toString("House 312"));
			jsonObject_NewEnroll.put("RESIROADNAME", CommonUtils.toString(""));
			jsonObject_NewEnroll.put("RESIAREANAME", CommonUtils.toString("Area"));
			jsonObject_NewEnroll.put("RESIPHONE", CommonUtils.toString(dto.getResiPhone()));
			jsonObject_NewEnroll.put("RESIPHONE2", CommonUtils.toString(""));
			jsonObject_NewEnroll.put("RESIEMAIL", CommonUtils.toString(""));
			jsonObject_NewEnroll.put("RESIOWNERSHIP", CommonUtils.toString(dto.getResiaAccomodationType()));
			jsonObject_NewEnroll.put("RESIMOBILE2", CommonUtils.toString(dto.getResimob2()));
			jsonObject_NewEnroll.put("RESI_MARKET_VALUE", CommonUtils.toString(""));

			// Office Address
			jsonObject_NewEnroll.put("OFFHOUSENO", CommonUtils.toString("House 312"));
			jsonObject_NewEnroll.put("OFFROADNAME", CommonUtils.toString("Are permanent"));
			jsonObject_NewEnroll.put("OFFAREANAME", CommonUtils.toString(""));
			jsonObject_NewEnroll.put("OFFLANDMARK", CommonUtils.toString(dto.getOffLandMark()));
			jsonObject_NewEnroll.put("OFFDISTRICT", CommonUtils.toString(""));
			jsonObject_NewEnroll.put("OFFSTATE", CommonUtils.toString(dto.getOffstate()));
			jsonObject_NewEnroll.put("OFFCITY", CommonUtils.toString(dto.getOffCity()));
			jsonObject_NewEnroll.put("OFFPINCODE", CommonUtils.toString(dto.getOffZip()));
			jsonObject_NewEnroll.put("OFFMOBILE", CommonUtils.toString(dto.getOffMobile1()));
			jsonObject_NewEnroll.put("YEARSATOFFICE", CommonUtils.toString(""));
			jsonObject_NewEnroll.put("MONTHSATOFFICE", CommonUtils.toString(""));
			jsonObject_NewEnroll.put("OFFACCOMODATIONTYPE", CommonUtils.toString(dto.getOffAccomodationType()));
			jsonObject_NewEnroll.put("OFFPHONE", CommonUtils.toString(dto.getOffPhone()));
			jsonObject_NewEnroll.put("OFFEMAIL", CommonUtils.toString(dto.getOffEmail()));
			jsonObject_NewEnroll.put("OFFCOMPANYNAME", CommonUtils.toString(
					CommonUtils.toString(dto.getOffCompanyName()).equals("") ? "Qualtech" : dto.getOffCompanyName()));
			jsonObject_NewEnroll.put("OFFCOWNERSHIP", CommonUtils.toString(dto.getOffAccomodationType()));
			jsonObject_NewEnroll.put("OFFC_MARKET_VALUE", CommonUtils.toString(""));
			jsonObject_NewEnroll.put("OFFCOLD_ADD", CommonUtils.toString(""));
			jsonObject_NewEnroll.put("OFFC_YEAROFBUS_EST", CommonUtils.toString(dto.getBussinessestbyr()));
			jsonObject_NewEnroll.put("OFF_YROFSTAYATCURRAREA", CommonUtils.toString(dto.getOffYearSatersi()));

			// Permanent Address
			jsonObject_NewEnroll.put("PERMROADNAME", CommonUtils.toString("House"));
			jsonObject_NewEnroll.put("PERMHOUSENO", CommonUtils.toString("House"));
			jsonObject_NewEnroll.put("PERMAREANAME", CommonUtils.toString(""));
			jsonObject_NewEnroll.put("PERMLANDMARK", CommonUtils.toString(dto.getPermLandMark()));
			jsonObject_NewEnroll.put("PERMDISTRICT", CommonUtils.toString(""));
			jsonObject_NewEnroll.put("PERMSTATE", CommonUtils.toString(dto.getPermState()));
			jsonObject_NewEnroll.put("PERMCITY", CommonUtils.toString(dto.getPermCity()));
			jsonObject_NewEnroll.put("PERMPINCODE", CommonUtils.toString(dto.getPermZip()));
			jsonObject_NewEnroll.put("PERMMOBILE", CommonUtils.toString(dto.getPermMobile1()));
			jsonObject_NewEnroll.put("YEARSATPERM", CommonUtils.toString(""));
			jsonObject_NewEnroll.put("MONTHSATPERM", CommonUtils.toString(""));
			jsonObject_NewEnroll.put("PERMACCOMODATIONTYPE", CommonUtils.toString(dto.getPermacComodationType()));
			jsonObject_NewEnroll.put("PERMOWNERSHIP", CommonUtils.toString(dto.getPermacComodationType()));

			// Other Detail
			jsonObject_NewEnroll.put("APPDATE", CommonUtils.toString(""));
			jsonObject_NewEnroll.put("SYSTEM_DATE", CommonUtils.toString(""));
			jsonObject_NewEnroll.put("DEVICE_ID", CommonUtils.toString(""));
			jsonObject_NewEnroll.put("APP_NAME", CommonUtils.toString("LeadMaagement"));
			jsonObject_NewEnroll.put("APP_PASSWORD", "xyz");
			jsonObject_NewEnroll.put("REQUEST_ID", CommonUtils.toString(""));
			jsonObject_NewEnroll.put("LONGITUDE", CommonUtils.toString(""));
			jsonObject_NewEnroll.put("LATITUDE", CommonUtils.toString(""));
			jsonObject_NewEnroll.put("LOCATION_NETWORK_TYPE", CommonUtils.toString(""));
			jsonObject_NewEnroll.put("LOCATION_DATETIME", CommonUtils.toString(""));
			jsonObject_NewEnroll.put("OLD_APPLICATIONID", CommonUtils.toString(""));
			jsonObject_NewEnroll.put("CIN", CommonUtils.toString(""));
			jsonObject_NewEnroll.put("RBI_REGISTRATION_NO", CommonUtils.toString(""));
			jsonObject_NewEnroll.put("TINNO", CommonUtils.toString(""));
			jsonObject_NewEnroll.put("KEY_CONTACT_PERSON", CommonUtils.toString(""));
			jsonObject_NewEnroll.put("AUTHORIZED_CAPITAL", CommonUtils.toString(""));
			jsonObject_NewEnroll.put("CODEOFGROUP", CommonUtils.toString(""));
			jsonObject_NewEnroll.put("PERM_PHONE", CommonUtils.toString(""));
			jsonObject_NewEnroll.put("AUTH_SIGN_DETAILS", CommonUtils.toString(""));

			// String appversion =
			// CommonUtils.toString(resource.getString("miFINServiceAppVersion")).trim();
			String appversion = getSysConfigurationParamValueByName("MOBILE_APP_VERSION");
			jsonObject_NewEnroll.put("APP_VERSION", CommonUtils.toString("1.0.0.9.9").trim());

			String miFinWebIP = CommonUtils.toString(resource.getString("miFINServiceIP")).trim();
			String NewEnroll_Result = SimpleHttpConnection
					.sendJsonByPost(miFinWebIP + "/MifinHLService/qc/mifinhl/newLoan", jsonObject_NewEnroll);

			JSONObject json = new JSONObject(NewEnroll_Result);
			JSONObject RECEIVE = (JSONObject) json.get("RECEIVE");

			String status = (String) RECEIVE.get("STATUS");
			String message = (String) RECEIVE.get("MESSAGE");
			String PROSPECT_NO = "", PROSPECT_ID = "", APPLICANT_ID = "";

			if (status.equals("S")) {
				PROSPECT_NO = RECEIVE.get("PROSPECT_NO") + "";
				PROSPECT_ID = RECEIVE.get("PROSPECT_ID") + "";
				APPLICANT_ID = RECEIVE.get("APPLICANT_ID") + "";

				PROSPECT_NO = PROSPECT_NO + "";
				PROSPECT_ID = PROSPECT_ID + "";
				APPLICANT_ID = APPLICANT_ID + "";

				finalMessage = status + "~" + PROSPECT_ID + "~" + PROSPECT_NO + "~" + APPLICANT_ID + "~" + message;
			} else {
				message = "There is some problem while converting to Loan Message from Server : " + message;
				finalMessage = status + "~" + message;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return finalMessage;
	}*/

	@Override
	public boolean addActionForConvertToCustomerLead(ContactDto contactDto) {
		Session session = null;
		String update = "";
		String currActionId = null;
		boolean isSuccess = false;
		try {
			logger.info("ActionDaoImpl | saveCaseAction() | :- START  ::: WITH Request ContactDto::" + contactDto);
			// UserEntity userEntity = (UserEntity)
			// httpSession.getAttribute("UserDetails");
			session = sessionFactory.openSession();
			String sql = "INSERT INTO  QC_PROSPECT.QT_CASE_USER_ACTION (CASEUSERACTION_ID, CASE_ID,CASEXUSER_ID,ACTION_ID,REMARKS,CREATED_DATE,CREATED_SYS_DATE)"
					+ " VALUES(QC_PROSPECT.SEQ_CASE_ACTION.NEXTVAL ,:caseId,:userId,:actionId,:remark,SYSDATE,SYSDATE)";
			Query query = session.createSQLQuery(sql).setParameter("caseId", Integer.parseInt(contactDto.getCaseId()))
					.setParameter("userId", Integer.parseInt(contactDto.getUserId()))
					.setParameter("actionId", contactDto.getActionId()).setParameter("remark", contactDto.getRemarks());
			update = update + query.executeUpdate();
			String actionId = contactDto.getActionId();
			try {
				List list = session.createSQLQuery("select max(CASEUSERACTION_ID) from QC_PROSPECT.QT_CASE_USER_ACTION")
						.list();
				currActionId = list.size() > 0 ? list.get(0) + "" : null;
			} catch (Exception e) {
				e.printStackTrace();
			}

			query = null;
			String sqlupdate = "UPDATE QC_PROSPECT.QT_CASE QC SET QC.DISPOSITION_ID=:dispositionId,QC.ACTION_ID=:actionId, QC.UPDATED_SYS_DATE= SYSDATE WHERE QC.CASE_ID =:caseId";
			query = session.createSQLQuery(sqlupdate).setParameter("dispositionId", actionId)
					.setParameter("actionId", actionId).setParameter("caseId", contactDto.getCaseId());
			update = update + query.executeUpdate();

		} catch (Exception e) {
			logger.error("ActionDaoImpl | saveCaseAction() | :- error:::" + e.getMessage());
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		logger.info("ActionDaoImpl | saveCaseAction() | :- END :::");
		return (!CommonUtils.toString(update).equals("")) ? true : false;
	}

	@Override
	public boolean isLeadConvertToCustomer(String leadid) {
		Session session = null;
		boolean isLeadConverted = false;
		try {
			String prospectid = "", applicantid = "";
			session = sessionFactory.openSession();
			String sql = "select APPLICANTID,PROSPECTID from qc_prospect.qt_case where case_id=" + leadid;
			SQLQuery query = session.createSQLQuery(sql);
			List list = query.list();
			if (list.size() > 0) {
				Object[] obj = (Object[]) list.get(0);
				applicantid = obj[0] == null ? "" : obj[0] + "";
				prospectid = obj[1] == null ? "" : obj[1] + "";

				if (!CommonUtils.toString(applicantid).equals("") && !CommonUtils.toString(prospectid).equals("")) {
					isLeadConverted = true;
				}
				// dto.setApplicantId(obj[0] == null ? "" : obj[0] + "");
				// dto.setProspectId(obj[1] == null ? "" : obj[1] + "");
			} else {
				isLeadConverted = false;
			}
		} catch (Exception e) {
			logger.error("ProductDaoImpl | getApplicantId() | :- error:::::" + e.getMessage());
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		logger.info("ProductDaoImpl | getApplicantId() | :- END:::");
		return isLeadConverted;
	}

	@Transactional
	public String getSysConfigurationParamValueByName(String paramName) {
		logger.info(
				"CustomerDaoImpl | getSysConfigurationParamValueByName() | :- START  ::: WITH paramName::" + paramName);
		Session session = null;
		String paramValue = "";
		try {
			session = sessionFactory.openSession();
			String sqlquery = "SELECT SC.PARAMVALUE FROM QC_PROSPECT_MASTER.QM_SYS_CONFIGURATION SC WHERE SC.PARAMNAME = '"
					+ paramName + "'";
			Query query = session.createSQLQuery(sqlquery);
			List list = query.list();
			if (list.size() > 0)
				paramValue = list.get(0).toString();
		} catch (Exception e) {
			logger.error("CustomerDaoImpl | getCompanyNameById() | :- Error::" + e.getMessage());
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		logger.info("CustomerDaoImpl | getCompanyNameById() | :- END::");
		return paramValue;
	}
}

