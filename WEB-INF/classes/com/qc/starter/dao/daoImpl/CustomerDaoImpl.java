package com.qc.starter.dao.daoImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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

import com.qc.starter.basic.CommonUtils;
import com.qc.starter.dao.CustomerDao;
import com.qc.starter.dto.CustomerDto;
import com.qc.starter.dto.DocumentDto;
import com.qc.starter.dto.LovDto;
import com.qc.starter.entity.CityEntity;
import com.qc.starter.entity.CustomerEntity;
import com.qc.starter.entity.UserEntity;

@Repository
public class CustomerDaoImpl implements CustomerDao {

	private static final Logger logger = Logger.getLogger(CustomerDaoImpl.class.getName());
	@Autowired HttpSession httpSession;
	@Autowired	SessionFactory sessionFactory;
	/*	
	@Transactional
	public CustomerEntity getCustomerFromLeadId(Integer leadId) {
		logger.info("CustomerDaoImpl | CustomerEntity() | :- START  ::: WITH Request leadId::"+leadId);
		List<CustomerEntity> list =null;
		Session session=null;
		try{
		session = sessionFactory.openSession();
		list = session.createQuery("from CustomerEntity where caseId = :caseId")
				.setParameter("caseId", leadId.toString()).list();
		}
		catch(Exception e){
			logger.error("CustomerDaoImpl | CustomerEntity() | :- error::"+e.getMessage());
			e.printStackTrace();
		}finally {
			 if(session!=null)
				session.close();
		}
		logger.info("CustomerDaoImpl | CustomerEntity() | :- END");
		if (list.size() != 0)
			return (CustomerEntity) list.get(0);
		else
			return null;
	}*/
	@Transactional
	public CustomerEntity getCustomerFromLeadId(Integer leadId) {
		logger.info("CustomerDaoImpl | getCustomerFromLeadId() | :- START  :Time "+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()));
		CustomerEntity entity = null;
		Session session =null;
		try{
			session = sessionFactory.openSession();		
			String sql = "SELECT PERSONAL_DTL_ID,CASE_ID,TITLE,FNAME,MNAME,LNAME,DOB,MARITAL_STATUS,GENDER,NATIONALITY," +
					"NOOFDEPENDENTS,PAN,ADHAAR_NO,VOTERSID,DRIVINGLICENSENO,PASSPORTNO,OCCUPATION_TYPE,FATHERTITLE,FATHERFNAME," +
					"FATHERMNAME,FATHERLNAME,MOTHERTITLE,MOTHERFNAME,MOTHERMNAME,MOTHERLNAME,SPOUSETITLE,SPOUSEFNAME,SPOUSEMNAME," +
					"SPOUSELNAME,SPOUSEOCCUPATION,SPOUSECOMPANYID,CONSTITUTION,CUSTCATEGORY,EDUQUALIFICATION,HIGHESTQUALIFICATION," +
					"DEGREE,COLLEGELASTATTENDED,COMPANY_TYPE,CASE_COMPANY_ID,OTHER_COMPANY_NAME,DESIG,SALARY_MODE," +
					"YEARS_CURRENT_JOB,WORK_EXP,BASIC,DA,HRA,SPALLOWANCES,MEDICAL_LTA,TO_CHAR(BONUS_INCENTIVE,'99,99,99,99,99,999') BONUS_INCENTIVE,OTHERS," +
					"TO_CHAR(ANNUAL_INCOME,'99,99,99,99,99,999') ANNUAL_INCOME,TO_CHAR(GROSS_MONTHLY_INC,'99,99,99,99,99,999') GROSS_MONTHLY_INC" +
					",TO_CHAR(GROSS_ANNUAL_INC,'99,99,99,99,99,999') GROSS_ANNUAL_INC,TO_CHAR(NET_MONTHLY_INC,'99,99,99,99,99,999') NET_MONTHLY_INC," +
					"TO_CHAR(OTHER_MONTHLY_INC,'99,99,99,99,99,999') OTHER_MONTHLY_INC,TO_CHAR(OTHER_ANNUAL_INC,'99,99,99,99,99,999') OTHER_ANNUAL_INC," +
					"TO_CHAR(MONTHLY_RENTAL_INC,'99,99,99,99,99,999') MONTHLY_RENTAL_INC," +
					"TO_CHAR(ANNUAL_SALES,'99,99,99,99,99,999') ANNUAL_SALES,TO_CHAR(GROSS_PROFIT,'99,99,99,99,99,999') GROSS_PROFIT," +
					"TO_CHAR(NET_PROFIT_AFTR_TAX,'99,99,99,99,99,999') NET_PROFIT_AFTR_TAX,TO_CHAR(ANNUAL_RENTAL_INC,'99,99,99,99,99,999') ANNUAL_RENTAL_INC," +
					"INCORPERATION_DT,TO_CHAR(NET_WORTH,'99,99,99,99,99,999') NET_WORTH,CORPERATE_SAL_ACC,TO_CHAR(DIRECTOR_SALARY,'99,99,99,99,99,999') DIRECTOR_SALARY," +
					"TO_CHAR(DEPRECIATION,'99,99,99,99,99,999') DEPRECIATION,TO_CHAR(INT_PAID_ON_LOAN,'99,99,99,99,99,999') INT_PAID_ON_LOAN" +
					 ",CUSTENTITYTYPEID ,AUTHSIGNATORYFNAME,AUTHSIGNATORYMNAME,AUTHSIGNATORYLNAME,INDUSTRYID,STAGE_ID,SECTOR_ID,TYPEOFBUSINESS,CLUSTERCASE " +
					 " FROM QC_PROSPECT.QT_PERSONAL_DETAILS WHERE CASE_ID ="+leadId.toString()+"";
			SQLQuery query=session.createSQLQuery(sql);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List result = query.list();                                                        
			Iterator it =result.iterator();
			if(result != null && result.size() > 0){
				for(Object o : result){				
					Map rs = (Map) o;
					entity = new CustomerEntity();
					entity.setPersonalDetailId(rs.get("PERSONAL_DTL_ID")==null?"":rs.get("PERSONAL_DTL_ID")+"");
					entity.setCaseId(rs.get("CASE_ID")==null?"":rs.get("CASE_ID")+"");
					entity.setTitle(rs.get("TITLE")==null?"":rs.get("TITLE")+"");
					entity.setfName(rs.get("FNAME")!=null?rs.get("FNAME")+"":"");
					entity.setmName(rs.get("MNAME")!=null?rs.get("MNAME")+"":"");
					entity.setlName(rs.get("LNAME")!=null?rs.get("LNAME")+"":"");
					entity.setDob((Date)rs.get("DOB"));
					entity.setMaritalStatus(rs.get("MARITAL_STATUS")!=null?rs.get("MARITAL_STATUS")+"":"");
					entity.setGender(rs.get("GENDER")!=null?rs.get("GENDER")+"":"");
					entity.setNationality(rs.get("NATIONALITY")!=null?rs.get("NATIONALITY")+"":"");
					entity.setNoOfDependents(rs.get("NOOFDEPENDENTS")!=null?rs.get("NOOFDEPENDENTS")+"":"");
					
					entity.setAdhaarNumber(rs.get("ADHAAR_NO")!=null?rs.get("ADHAAR_NO")+"":"");
					entity.setVotersId(rs.get("VOTERSID")!=null?rs.get("VOTERSID")+"":"");
					entity.setDrivingLicenseNo(rs.get("DRIVINGLICENSENO")!=null?rs.get("DRIVINGLICENSENO")+"":"");
					entity.setPassportNo(rs.get("PASSPORTNO")!=null?rs.get("PASSPORTNO")+"":"");
					entity.setOccupationType(rs.get("OCCUPATION_TYPE")!=null?rs.get("OCCUPATION_TYPE")+"":"");
					entity.setFatherTitle(rs.get("FATHERTITLE")!=null?rs.get("FATHERTITLE")+"":"");
					entity.setFatherFName(rs.get("FATHERFNAME")!=null?rs.get("FATHERFNAME")+"":"");
					entity.setFatherMName(rs.get("FATHERMNAME")!=null?rs.get("FATHERMNAME")+"":"");
					entity.setFatherLName(rs.get("FATHERLNAME")!=null?rs.get("FATHERLNAME")+"":"");
					entity.setMotherTitle(rs.get("MOTHERTITLE")!=null?rs.get("MOTHERTITLE")+"":"");
					entity.setMotherFName(rs.get("MOTHERFNAME")!=null?rs.get("MOTHERFNAME")+"":"");
					entity.setMotherMName(rs.get("MOTHERMNAME")!=null?rs.get("MOTHERMNAME")+"":"");
					entity.setMotherLName(rs.get("MOTHERLNAME")!=null?rs.get("MOTHERLNAME")+"":"");
					entity.setSpouseTitle(rs.get("SPOUSETITLE")!=null?rs.get("SPOUSETITLE")+"":"");
					entity.setSpouseFName(rs.get("SPOUSEFNAME")!=null?rs.get("SPOUSEFNAME")+"":"");
					entity.setSpouseMName(rs.get("SPOUSEMNAME")!=null?rs.get("SPOUSEMNAME")+"":"");
					entity.setSpouseLName(rs.get("SPOUSELNAME")!=null?rs.get("SPOUSELNAME")+"":"");
					entity.setSpouseOccupation(rs.get("SPOUSEOCCUPATION")!=null?rs.get("SPOUSEOCCUPATION")+"":"");
					entity.setSpouseCompanyId(rs.get("SPOUSECOMPANYID")!=null?rs.get("SPOUSECOMPANYID")+"":"");
					entity.setConstitution(rs.get("CONSTITUTION")!=null?rs.get("CONSTITUTION")+"":"");
					entity.setCustCategory(rs.get("CUSTCATEGORY")!=null?rs.get("CUSTCATEGORY")+"":"");
					entity.setEduQualification(rs.get("EDUQUALIFICATION")!=null?rs.get("EDUQUALIFICATION")+"":"");
					entity.setHighestQualification(rs.get("HIGHESTQUALIFICATION")!=null?rs.get("HIGHESTQUALIFICATION")+"":"");
					entity.setDegree(rs.get("DEGREE")!=null?rs.get("DEGREE")+"":"");
					entity.setCollegeLastAttended(rs.get("COLLEGELASTATTENDED")!=null?rs.get("COLLEGELASTATTENDED")+"":"");
					entity.setCompanyType(rs.get("COMPANY_TYPE")!=null?rs.get("COMPANY_TYPE")+"":"");
					//entity.setCompanyId(rs.get("CASE_COMPANY_ID")!=null?rs.get("CASE_COMPANY_ID")+"":"");
					
					/*String companyName = rs.get("COMPANY_NAME")+"";
					
					if(companyName!=null)
						entity.setCompanyId(companyName);
					else
						entity.setCompanyId("");*/
					
					entity.setCompanyId( rs.get("OTHER_COMPANY_NAME") !=null ? rs.get("OTHER_COMPANY_NAME")+"" :"" );
					
					
					entity.setOtherCompanyName(rs.get("OTHER_COMPANY_NAME")!=null?rs.get("OTHER_COMPANY_NAME")+"":"");
					entity.setDesig(rs.get("DESIG")!=null?rs.get("DESIG")+"":"");
					entity.setSalaryMode(rs.get("SALARY_MODE")!=null?rs.get("SALARY_MODE")+"":"");
					entity.setYearsCurrentJob(rs.get("YEARS_CURRENT_JOB")!=null?rs.get("YEARS_CURRENT_JOB")+"":"");
					entity.setWorkExp(rs.get("WORK_EXP")!=null?rs.get("WORK_EXP")+"":"");
					entity.setBasic(rs.get("BASIC")!=null?rs.get("BASIC")+"":"");
					entity.setDa(rs.get("DA")!=null?rs.get("DA")+"":"");
					entity.setHra(rs.get("HRA")!=null?rs.get("HRA")+"":"");
					entity.setSpAllowances(rs.get("SPALLOWANCES")!=null?rs.get("SPALLOWANCES")+"":"");
					entity.setMedicalLta(rs.get("MEDICAL_LTA")!=null?rs.get("MEDICAL_LTA")+"":"");
					entity.setBonusIncentive(rs.get("BONUS_INCENTIVE")==null?"":rs.get("BONUS_INCENTIVE")+"");
					entity.setOthers(rs.get("OTHERS")!=null?rs.get("OTHERS")+"":"");
					entity.setAnnualIncome(rs.get("ANNUAL_INCOME")==null?"":rs.get("ANNUAL_INCOME")+"".trim());
					entity.setAnnualIncome(entity.getAnnualIncome().trim());
					entity.setGrossMonthlyInc(rs.get("GROSS_MONTHLY_INC")==null?"":rs.get("GROSS_MONTHLY_INC")+"".trim());
					entity.setGrossMonthlyInc(entity.getGrossMonthlyInc().trim());
					entity.setGrossAnnualInc(rs.get("GROSS_ANNUAL_INC")==null?"":rs.get("GROSS_ANNUAL_INC")+"".trim());
					entity.setGrossAnnualInc(entity.getGrossAnnualInc().trim());
					entity.setNetMonthlyInc(rs.get("NET_MONTHLY_INC")==null?"":rs.get("NET_MONTHLY_INC")+"".trim());
					entity.setNetMonthlyInc(entity.getNetMonthlyInc().trim());
					entity.setOtherMonthlyInc(rs.get("OTHER_MONTHLY_INC")==null?"":rs.get("OTHER_MONTHLY_INC")+"".trim());
					entity.setOtherMonthlyInc(entity.getOtherMonthlyInc().trim());
					entity.setOtherAnnualInc(rs.get("OTHER_ANNUAL_INC")==null?"":rs.get("OTHER_ANNUAL_INC")+"".trim());
					entity.setOtherAnnualInc(entity.getOtherAnnualInc().trim());
					entity.setMothlyRentalInc(rs.get("MONTHLY_RENTAL_INC")==null?"":rs.get("MONTHLY_RENTAL_INC")+"".trim());
					entity.setMothlyRentalInc(entity.getMothlyRentalInc().trim());
					entity.setAnnualSales(rs.get("ANNUAL_SALES")==null?"":rs.get("ANNUAL_SALES")+"".trim());
					entity.setAnnualSales(entity.getAnnualSales().trim());
					entity.setGrossProfit(rs.get("GROSS_PROFIT")==null?"":rs.get("GROSS_PROFIT")+"".trim());
					entity.setGrossProfit(entity.getGrossProfit().trim());
					entity.setNetProfitAfterTax(rs.get("NET_PROFIT_AFTR_TAX")==null?"":rs.get("NET_PROFIT_AFTR_TAX")+"".trim());
					entity.setNetProfitAfterTax(entity.getNetProfitAfterTax().trim());
					entity.setAnnualRentalInc(rs.get("ANNUAL_RENTAL_INC")==null?"":rs.get("ANNUAL_RENTAL_INC")+"".trim());
					entity.setAnnualRentalInc(entity.getAnnualRentalInc().trim());
					entity.setIncorperationDate((Date) rs.get("INCORPERATION_DT"));
					entity.setNetWorth(rs.get("NET_WORTH")==null?"":rs.get("NET_WORTH")+"".trim());
					entity.setNetWorth(entity.getNetWorth().trim());
					entity.setCorporateSalAcc(rs.get("CORPERATE_SAL_ACC")+"");
					//-----------added by deepak on 01 march-2016----
					entity.setDirectorSalary(rs.get("DIRECTOR_SALARY")==null?"":rs.get("DIRECTOR_SALARY")+"");
					entity.setDepreciation(rs.get("DEPRECIATION")==null?"":rs.get("DEPRECIATION")+"");
					//-----------added by deepak on 16 march-2016----
					entity.setInteresrPaidOnLoan(rs.get("INT_PAID_ON_LOAN")==null?"":rs.get("INT_PAID_ON_LOAN")+"");
					//--added ny tripti 29Sep16
					
					entity.setCustEntityTypeId(rs.get("CUSTENTITYTYPEID")==null?"":rs.get("CUSTENTITYTYPEID")+"");
					entity.setAuthSignatoryFName(rs.get("AUTHSIGNATORYFNAME")==null?"":rs.get("AUTHSIGNATORYFNAME")+"");
					entity.setAuthSignatoryMName(rs.get("AUTHSIGNATORYMNAME")==null?"":rs.get("AUTHSIGNATORYMNAME")+"");
					entity.setAuthSignatoryLName(rs.get("AUTHSIGNATORYLNAME")==null?"":rs.get("AUTHSIGNATORYLNAME")+"");
					if(entity.getCustEntityTypeId().equalsIgnoreCase("1000000001")){
						entity.setIndustryId(rs.get("INDUSTRYID")==null?"":rs.get("INDUSTRYID")+"");
						entity.setPan(rs.get("PAN")!=null?rs.get("PAN")+"":"");
					entity.setSectorId(rs.get("SECTOR_ID")==null?"":rs.get("SECTOR_ID")+"");
					entity.setStageId(rs.get("STAGE_ID")==null?"":rs.get("STAGE_ID")+"");
					
					entity.setTypeOfBusiness(rs.get("TYPEOFBUSINESS")==null?"":rs.get("TYPEOFBUSINESS")+"");
					entity.setClusterCase(rs.get("CLUSTERCASE")==null?"":rs.get("CLUSTERCASE")+"");
					
					
					}
					else if(entity.getCustEntityTypeId().equalsIgnoreCase("1000000002")){
						entity.setIndustryForNI(rs.get("INDUSTRYID")==null?"":rs.get("INDUSTRYID")+"");
						entity.setCompanyPanNo(rs.get("PAN")!=null?rs.get("PAN")+"":"");
						entity.setSectorForNI(rs.get("SECTOR_ID")==null?"":rs.get("SECTOR_ID")+"");
						entity.setStageForNI(rs.get("STAGE_ID")==null?"":rs.get("STAGE_ID")+"");						
						entity.setTypeOfBusinessForNI(rs.get("TYPEOFBUSINESS")==null?"":rs.get("TYPEOFBUSINESS")+"");
						entity.setClusterForNI(rs.get("CLUSTERCASE")==null?"":rs.get("CLUSTERCASE")+"");
						
					}
					logger.info("CustomerDaoImpl | getCustomerFromLeadId() | :- END  :Time "+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()));
					return entity;
				}
			}else{
				return null;
			}
		}catch(Exception e){
			//e.printStackTrace();
			logger.error("CustomerDaoImpl || getCustomerFromLeadId() || "+e.getMessage()+" || :-END ");
		}finally{
			session.close();
		}
		return null;

	}


	/*public boolean updateCustomerInfo(CustomerDto customerDto) {
		logger.info("CustomerDaoImpl | updateCustomerInfo() | :- START  ::: WITH Request CustomerEntity::"+customerDto);
		Connection con = null;
		PreparedStatement psmt = null;
		try{
			Map prospectMap=ConnectionUtil.dbConnectionMapProspect;
			con = DBConnection.getConnection(prospectMap);		
			String sql = "UPDATE QC_PROSPECT.QT_PERSONAL_DETAILS SET FNAME = ?, MNAME = ?, LNAME = ?, MARITAL_STATUS=?, DOB=?, " +
					" GENDER = ?, NATIONALITY = ?, NOOFDEPENDENTS = ?, PAN=?, ADHAAR_NO=? WHERE CASE_ID = ? ";
			psmt = con.prepareStatement(sql);
			int i =1;
			psmt.setString(i++, customerDto.getfName()==null?"":customerDto.getfName());
			psmt.setString(i++, customerDto.getmName()==null?"":customerDto.getmName());
			psmt.setString(i++, customerDto.getlName()==null?"":customerDto.getlName());
			psmt.setString(i++, customerDto.getMaritalStatus().equalsIgnoreCase("-1")?"":customerDto.getMaritalStatus());
			if(customerDto.getDob()==null){
				psmt.setNull(i++, java.sql.Types.DATE);
			}else{
				psmt.setDate(i++, new java.sql.Date(customerDto.getDob().getTime()));
			}
			psmt.setString(i++, customerDto.getGender().equalsIgnoreCase("-1")?"":customerDto.getGender());
			psmt.setString(i++, customerDto.getNationality().equals("-1")?"":customerDto.getNationality());
			psmt.setString(i++, customerDto.getNoOfDependents()==null?"":customerDto.getNoOfDependents());
			psmt.setString(i++, customerDto.getPan()==null?"":customerDto.getPan());
			psmt.setString(i++, customerDto.getAdhaarNumber()==null?"":customerDto.getAdhaarNumber());
			psmt.setString(i++, customerDto.getCaseId());
			psmt.executeUpdate();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			logger.error("Error in catch block due to::::->"+e.getMessage());
			return false;
		}finally{
			try{
				psmt.close();
				con.close();
			}catch(SQLException e){
				logger.error("Error in catch block due to::::->"+e.getMessage());
				return false;
			}

		}*/
	/*Query query = session.createQuery("update CustomerEntity set fName=:fName, "
				+ "mName=:mName, lName=:lName, maritalStatus=:maritalStatus, dob=:dob,"
				+ " gender=:gender,nationality=:nationality, noOfDependents=:noOfDependents,"
				+ " pan=:pan, adhaarNumber=:adhaarNumber where caseId=:caseId");
		query.setParameter("fName", customerDto.getfName()).setParameter("mName", customerDto.getmName())
				.setParameter("lName", customerDto.getlName())
				.setParameter("maritalStatus", customerDto.getMaritalStatus())
				.setDate("dob", customerDto.getDob()).setParameter("gender", customerDto.getGender())
				.setParameter("nationality", customerDto.getNationality())
				.setParameter("noOfDependents", customerDto.getNoOfDependents())
				.setParameter("pan", customerDto.getPan())
				.setParameter("adhaarNumber", customerDto.getAdhaarNumber())
				.setParameter("caseId", customerDto.getCaseId());

		int status = 0;

		try {
			status = query.executeUpdate();
		} catch (HibernateException e) {
			logger.info("Error in catch block due to::::->"+e.getMessage());
			e.printStackTrace();
		}
		logger.info("CustomerDaoImpl | updateCustomerInfo() | :- END");
		if (status > 0)
			return true;
		else
			return false;*/
	//}


	@Transactional
	public boolean updateCustomerInfo(CustomerDto customerDto) {
		logger.info("CustomerDaoImpl | updateCustomerInfo() | :- START  ::: WITH Request CustomerEntity::"+customerDto);
		Session session = sessionFactory.openSession();
		//modify by tripti
		Query query = session.createQuery("update CustomerEntity set fName=:fName, "
				+ "mName=:mName, lName=:lName, maritalStatus=:maritalStatus, dob=:dob,"
				+ " gender=:gender,nationality=:nationality, noOfDependents=:noOfDependents,"
				+ " pan=:pan, adhaarNumber=:adhaarNumber, custEntityTypeId=:custEntityTypeId, " +
				" authSignatoryFName=:authSignatoryFName,authSignatoryMName=:authSignatoryMName," +
				" authSignatoryLName=:authSignatoryLName,industryId=:industryId," +
				" typeOfBusiness=:typeOfBusiness,clusterCase=:clusterCase, custCategory=:custCategory,sectorId=:sectorId,stageId=:stageId,title=:title,updatedDate=:updatedDate,updatedSystemDate=:updatedDate,createdDate=:updatedDate,createdSystemDate=:updatedDate where caseId=:caseId");
		
		if(customerDto.getCustEntityTypeId().equals("1000000001") ){
			query.setParameter("fName", customerDto.getfName()).setParameter("mName", customerDto.getmName())
			.setParameter("lName", customerDto.getlName())
			.setParameter("maritalStatus", customerDto.getMaritalStatus())
			.setDate("dob", customerDto.getDob())
			.setParameter("gender", customerDto.getGender())
			.setParameter("nationality", customerDto.getNationality())
			.setParameter("noOfDependents", customerDto.getNoOfDependents())
			.setParameter("pan", customerDto.getPan())
			.setParameter("adhaarNumber", customerDto.getAdhaarNumber())
			.setParameter("custEntityTypeId", customerDto.getCustEntityTypeId())
			.setParameter("authSignatoryFName", customerDto.getAuthSignatoryFName())
			.setParameter("authSignatoryMName", customerDto.getAuthSignatoryMName())
			.setParameter("authSignatoryLName", customerDto.getAuthSignatoryLName())
			.setParameter("industryId", customerDto.getIndustryId())
			.setParameter("custCategory", customerDto.getCustCategory())
			.setParameter("sectorId",customerDto.getSectorId())
			.setParameter("stageId",customerDto.getStageId() )
			.setParameter("title",customerDto.getTitle())
			.setParameter("caseId", customerDto.getCaseId())
			.setParameter("typeOfBusiness", customerDto.getTypeOfBusiness())
			.setParameter("updatedDate",new Date())
			.setParameter("clusterCase", customerDto.getCluster());	
		}else{
			query.setParameter("fName", customerDto.getfName()).setParameter("mName", customerDto.getmName())
			.setParameter("lName", customerDto.getlName())
			.setParameter("maritalStatus", customerDto.getMaritalStatus())
			.setDate("dob", customerDto.getDob())
			.setParameter("gender", customerDto.getGender())
			.setParameter("nationality", customerDto.getNationality())
			.setParameter("noOfDependents", customerDto.getNoOfDependents())
			.setParameter("pan", customerDto.getPan())
			.setParameter("adhaarNumber", customerDto.getAdhaarNumber())
			.setParameter("custEntityTypeId", customerDto.getCustEntityTypeId())
			.setParameter("authSignatoryFName", customerDto.getAuthSignatoryFName())
			.setParameter("authSignatoryMName", customerDto.getAuthSignatoryMName())
			.setParameter("authSignatoryLName", customerDto.getAuthSignatoryLName())
			.setParameter("industryId", customerDto.getIndustryForNI())
			.setParameter("custCategory", customerDto.getCustCategory())
			.setParameter("sectorId",customerDto.getSectorForNI())
			.setParameter("stageId",customerDto.getStageId() )
			.setParameter("title",customerDto.getTitle())
			.setParameter("caseId", customerDto.getCaseId())
			.setParameter("typeOfBusiness", customerDto.getTypeOfBusinessForNI())
			.setParameter("updatedDate",new Date())
			.setParameter("clusterCase", customerDto.getClusterForNI());
		}
		
		
		
		int status = 0;
		try {
			status = query.executeUpdate();
			System.out.println("After update contact detail "+status);
		} catch (Exception e) {
			System.out.println("Inside catch block ");
			logger.error("Error in catch block due to::::->"+e.getMessage());
			e.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
		logger.info("CustomerDaoImpl | updateCustomerInfo() | :- END");
		if (status > 0)
			return true;
		else
			return false;
	}

	@Transactional
	public String getPersonalDetailId(String caseId){
		logger.info("CustomerDaoImpl | getPersonalDetailId() | :- START  ::: WITH Request caseId::"+caseId);
		String id ="";
		Session session=null;
		try{
			session = sessionFactory.openSession();
			Query query = session.createSQLQuery("select DISTINCT PERSONAL_DTL_ID from QC_PROSPECT.QT_PERSONAL_DETAILS WHERE CASE_ID = "+caseId);
			List list = query.list();
			id = list.get(0).toString();
		}catch(Exception ex){
			logger.error("CustomerDaoImpl | getPersonalDetailId() | :- error:::::"+ex.getMessage());
			ex.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
		logger.info("CustomerDaoImpl | getPersonalDetailId() | :- END  ::: WITH Response id::"+id);
		return id;
	}

	/*public String getPersonalDetailId(String caseId){
		logger.info("CustomerDaoImpl | getPersonalDetailId() | :- START  ::: WITH Request caseId::"+caseId);
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try{
			Map prospectMap=ConnectionUtil.dbConnectionMapProspect;
			con = DBConnection.getConnection(prospectMap);		
			String sql = "select DISTINCT PERSONAL_DTL_ID AS PDI from QC_PROSPECT.QT_PERSONAL_DETAILS WHERE CASE_ID = ?";
			psmt = con.prepareStatement(sql);
			psmt.setString(1, caseId);
			rs = psmt.executeQuery();
			if(rs.next()){
				String id = rs.getString("PDI");
				return id;
			}else{
				return null;
			}
		}catch(Exception e){
			logger.error("CustomerDaoImpl || getPersonalDetailId() || "+e.getMessage()+" || :-END");
			return null;
		}finally{
			try{
				rs.close();
				psmt.close();
				con.close();
			}catch(SQLException e){
				logger.error("CustomerDaoImpl || getPersonalDetailId() || "+e.getMessage()+" || :-END");
				return null;
			}
		}
		Query query = session.createSQLQuery("select DISTINCT PERSONAL_DTL_ID from QC_PROSPECT.QT_PERSONAL_DETAILS WHERE CASE_ID = "+caseId);
		List list = query.list();
		String id = list.get(0).toString();
		logger.info("CustomerDaoImpl | getPersonalDetailId() | :- END  ::: WITH Response id::"+id);
		return id;
	}*/


	@Transactional
	public String getCompanyNameById(String companyId){
		logger.info("CustomerDaoImpl | getCompanyNameById() | :- START  ::: WITH companyId::"+companyId);
		Session session = null;
		String company = ""; 
		try{			
			session = sessionFactory.openSession();
			String sqlquery = "select COMPANY_NAME from QC_PROSPECT_MASTER.QM_CASE_COMPANY where case_company_id  ='"+companyId+"'";
			Query query = session.createSQLQuery(sqlquery);
			List list = query.list();
			if(list.size()>0 )
				company = list.get(0).toString();
		}
		
		catch(Exception e){
			logger.error("CustomerDaoImpl | getCompanyNameById() | :- Error::"+e.getMessage());
			e.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
		logger.info("CustomerDaoImpl | getCompanyNameById() | :- END::");
		return company;
	}


	@Transactional
	public String getCompanyIdByName(String companyName){
		logger.info("CustomerDaoImpl | getCompanyIdByName() | :- START::");
		Session session = null;
		String companyid = "";  		
		try{			
			session = sessionFactory.openSession();
			String sqlquery = "select distinct case_company_id from QC_PROSPECT_MASTER.QM_CASE_COMPANY where company_name like upper('"+companyName.trim()+"')";
			Query query = session.createSQLQuery(sqlquery);
			List list = query.list();
			if(list.size()>0){
				companyid = list.get(0).toString();
			}
			
			if(companyid==null || "".equals(companyid)  || companyid==" " || companyid==""){
				String sqlquery1 = "select distinct case_company_id from QC_PROSPECT_MASTER.QM_CASE_COMPANY where company_name like upper('others')";
				Query query1 = session.createSQLQuery(sqlquery1);
				List list1 = query1.list();
				if(list1.size()>0){
				
				}
					companyid = list1.get(0).toString();
				}
			
			
			
			
		}catch(Exception e){
			logger.error("CustomerDaoImpl | getCompanyIdByName() | :- error::"+e.getMessage());
			e.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
		logger.info("CustomerDaoImpl | getCompanyIdByName() | :- END::");
		return companyid;
	}


	@Transactional
	public List<String> getCompanyList(String query){		
		logger.info("CustomerDaoImpl | getCompanyList() | :- START::");
		Session session = sessionFactory.openSession();
		List<String> companies = new ArrayList<String>();  		
		try{			
			query = query.trim();
			String sqlquery = "select COMPANY_NAME from QC_PROSPECT_MASTER.QM_CASE_COMPANY where active = 'A' and COMPANY_NAME like upper('"+query+"%')";
			Query nativesql = session.createSQLQuery(sqlquery);
			List companiesList  = nativesql.list(); 
			for(Object object : companiesList){
				String company = object.toString();
				companies.add(company);
			}
		}catch(Exception e){
			logger.error("CustomerDaoImpl | getCompanyList() | :- error::"+e.getMessage());
			e.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
		logger.info("CustomerDaoImpl | getCompanyList() | :- END::");
		return companies;
	}

	@Transactional
	public List<CityEntity> getCitiesByState(String stateId){
		logger.info("CustomerDaoImpl | getCitiesByState() | :- START::");
		List<CityEntity> cities = new ArrayList<CityEntity>();
		Session session = sessionFactory.openSession();
		try{
			String sql = " from CityEntity where active='A' and stateId = "+stateId +"order by cityMasterName ";
			Query query = session.createQuery(sql);
			cities = query.list();
		}catch(Exception e){
			logger.error("CustomerDaoImpl | getCitiesByState() | :- error::"+e.getMessage());
			e.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
		logger.info("CustomerDaoImpl | getCitiesByState() | :- END::");
		return cities;
	}

	@Transactional
	public boolean updateOccupationInfo(CustomerDto customerDto) {
		//---------Modifyed by deepak on 01 march 2016------------

		logger.info("CustomerDaoImpl | updateOccupationInfo() | :- START  ::: WITH Request CustomerEntity::"+customerDto);
		Session session = sessionFactory.openSession();
		UserEntity userEntity = (UserEntity) httpSession.getAttribute("UserDetails");
		if(userEntity==null){
			userEntity=new UserEntity();
			userEntity.setUserid(customerDto.getUserId());
		}
		
		/*String companyId = getCompanyIdByName(customerDto.getCompanyName());
        String name=getCompanyNameById(companyId);
	     
	     if(name.equals("") || name.equals("OTHERS")){
	    	 customerDto.setOtherCompanyName(customerDto.getCompanyName());
	    	  customerDto.setCompanyName("OTHERS") ;
	     }*/
		
		
		
		String sql = "UPDATE QC_PROSPECT.QT_PERSONAL_DETAILS SET CONSTITUTION =:constitution , OCCUPATION_TYPE = :occupationType, COMPANY_TYPE=:companyType, OTHER_COMPANY_NAME = '"+customerDto.getCompanyName()+"' ,CASE_COMPANY_ID=" +
				" (SELECT CASE_COMPANY_ID FROM QC_PROSPECT_MASTER.QM_CASE_COMPANY WHERE COMPANY_NAME = '"+customerDto.getCompanyName().trim()+"') , DESIG=:desig," +
				" SALARY_MODE=:salaryMode, YEARS_CURRENT_JOB=:yearsCurrentJob, WORK_EXP=:workExp, ANNUAL_INCOME=:annualIncome, GROSS_MONTHLY_INC=:grossMonthlyInc, NET_MONTHLY_INC=:netMonthlyInc, OTHER_MONTHLY_INC=:otherMonthlyInc," +
				" MONTHLY_RENTAL_INC=:mothlyRentalInc, ANNUAL_SALES=:annualSales, GROSS_PROFIT=:grossProfit, NET_PROFIT_AFTR_TAX=:netProfitAfterTax, ANNUAL_RENTAL_INC=:annualRentalInc, OTHER_ANNUAL_INC=:otherAnnualInc, " +
				" INCORPERATION_DT=:incorperationDate, NET_WORTH=:netWorth, CORPERATE_SAL_ACC=:corporateSalAcc, UPDATED_BY="+userEntity.getUserid().toString()+", UPDATED_DATE=SYSDATE, UPDATED_SYS_DATE=SYSDATE ," +
				" BONUS_INCENTIVE=:insentive,DIRECTOR_SALARY=:drSalary,DEPRECIATION=:depreciation,INT_PAID_ON_LOAN=:interesrPaidOnLoan  WHERE CASE_ID=:caseId";
		Query query = session.createSQLQuery(sql);

		
		query.setParameter("constitution", CommonUtils.toStringDatabaseNull(customerDto.getConstitution()))
		 .setParameter("occupationType", CommonUtils.toStringDatabaseNull(customerDto.getOccupationType()))
		.setParameter("companyType", CommonUtils.toStringDatabaseNull(customerDto.getCompanyType()))
		.setParameter("desig", customerDto.getDesig())
		.setParameter("salaryMode", CommonUtils.toStringDatabaseNull(customerDto.getSalaryMode()))
		.setParameter("yearsCurrentJob", customerDto.getYearsCurrentJob())
		.setParameter("workExp", customerDto.getWorkExp())

		.setParameter("occupationType", customerDto.getOccupationType()==null?"":customerDto.getOccupationType())
		.setParameter("companyType",customerDto.getCompanyType()==null?"":customerDto.getCompanyType())
		.setParameter("desig", customerDto.getDesig()==null?"":customerDto.getDesig())
		.setParameter("salaryMode", customerDto.getSalaryMode()==null?"":customerDto.getSalaryMode())
		.setParameter("yearsCurrentJob", customerDto.getYearsCurrentJob()==null?"":customerDto.getYearsCurrentJob())
		.setParameter("workExp", customerDto.getWorkExp()==null?"":customerDto.getWorkExp())

		.setParameter("annualIncome", customerDto.getAnnualIncome()==null?"":customerDto.getAnnualIncome().replaceAll(",", ""))
		.setParameter("grossMonthlyInc",customerDto.getGrossMonthlyIncome()==null?"":customerDto.getGrossMonthlyIncome().replaceAll(",", ""))
		.setParameter("netMonthlyInc",customerDto.getNetMonthlyIncome()==null?"":customerDto.getNetMonthlyIncome().replaceAll(",", ""))
		.setParameter("insentive",customerDto.getBonusIncentive()==null?"":customerDto.getBonusIncentive().replaceAll(",", ""))
		.setParameter("otherMonthlyInc", customerDto.getOtherMonthlyIncome()==null?"":customerDto.getOtherMonthlyIncome().replaceAll(",", ""))
		.setParameter("mothlyRentalInc",customerDto.getMonthlyRentalIncome()==null?"":customerDto.getMonthlyRentalIncome().replaceAll(",", ""))
		.setParameter("annualSales",customerDto.getAnnualSalesTurnover()==null?"":customerDto.getAnnualSalesTurnover().replaceAll(",", ""))
		.setParameter("grossProfit",customerDto.getGrossProfit()==null?"":customerDto.getGrossProfit().replaceAll(",", ""))
		.setParameter("netProfitAfterTax",customerDto.getNetProfitAfterTax()==null?"":customerDto.getNetProfitAfterTax().replaceAll(",", ""))
		.setParameter("annualRentalInc", customerDto.getAnnualRentalIncome()==null?"":customerDto.getAnnualRentalIncome().replaceAll(",", ""))
		.setParameter("otherAnnualInc", customerDto.getOtherAnnualIncome()==null?"":customerDto.getOtherAnnualIncome().replaceAll(",", ""))
		.setDate("incorperationDate", customerDto.getIncorporationDate())
		.setParameter("netWorth",customerDto.getNetWorth()==null?"":customerDto.getNetWorth().replaceAll(",", ""))
		.setParameter("corporateSalAcc", customerDto.getCorporateSalaryAccount()==null?"":customerDto.getCorporateSalaryAccount())
		.setParameter("drSalary",customerDto.getDirectorSalary()==null?"":customerDto.getDirectorSalary().replaceAll(",", ""))
		.setParameter("depreciation",customerDto.getDepreciation()==null?"":customerDto.getDepreciation().replaceAll(",", ""))
		.setParameter("interesrPaidOnLoan", customerDto.getInteresrPaidOnLoan()==null?"":customerDto.getInteresrPaidOnLoan().replaceAll(",",""))
		.setParameter("caseId", customerDto.getCaseId());
		int status = 0;
		try {
			status = query.executeUpdate();
		} catch (Exception e) {
			logger.error("Error in catch block due to::::->"+e.getMessage());
			e.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
		logger.info("CustomerDaoImpl | updateOccupationInfo() | :- END ");
		if (status > 0)
			return true;
		else
			return false;
	}
	
	public List<LovDto> getMasterList(String idColumnName,String valueColumnName,String dependentTableName,String crossTableName,String crossTableDependentId,String crossTableMasterId,String masterValue){
		logger.info("DocumentDaoImpl || getDocumentName() || :-START");
		UserEntity userEntity = (UserEntity) httpSession.getAttribute("UserDetails");
		LovDto entity=null;
		List<LovDto> list=new ArrayList<LovDto>();
		Session session=null;
		SQLQuery query=null;
		try{
			session=sessionFactory.openSession();
			String sql = "select "+idColumnName+" as ID , "+valueColumnName+" AS VALUE from QC_PROSPECT_MASTER."+dependentTableName+" where "+idColumnName+" in (select "+crossTableDependentId+" from QC_PROSPECT_MASTER."+crossTableName+" where "+crossTableMasterId+" = '"+masterValue+"')";
			
			
			
			query=session.createSQLQuery(sql);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List result=query.list();
			for(Object o:result){
				Map map=(Map) o;
				entity=new LovDto();
				entity.setId(map.get("ID")!=null?map.get("ID").toString():"");
				entity.setValue(map.get("VALUE")!=null?map.get("VALUE").toString():"");						
				list.add(entity);
			}
		}catch(Exception e){
			logger.error("DocumentDaoImpl || getDocumentName() || :-error::::"+e.getMessage());
		}finally {
			if(session!=null)
				session.close();
		}
		logger.info("DocumentDaoImpl || getDocumentName() || :-end");
		return list;
	}


	@Override
	public boolean updateLeadDetails(CustomerDto customerDto) {
		
			logger.info("CustomerDaoImpl | updateLeadDetails() | :- START  ::: WITH Request caseId::"+customerDto.getCaseId());
			String id ="";
			Session session=null;
	        session = sessionFactory.openSession();
				String sql = "update QC_PROSPECT.QT_CASE set  SOURCE_ID=:source,REFERENCE_NAME=:referencename, REFERENCE_NUMBER=:referencenumber  WHERE CASE_ID = "+customerDto.getCaseId();
				Query query = session.createSQLQuery(sql);
				query.setParameter("source", customerDto.getSource()==null?"":customerDto.getSource())
				.setParameter("referencename",customerDto.getReferenceName()==null?"":customerDto.getReferenceName())
				.setParameter("referencenumber", customerDto.getReferenceNumber()==null?"":customerDto.getReferenceNumber());
     	        int status=0;
				try {
					status = query.executeUpdate();
				} catch (Exception e) {
					logger.error("Error in catch block due to::::->"+e.getMessage());
					e.printStackTrace();
				}finally {
					if(session!=null)
						session.close();
				}
				logger.info("CustomerDaoImpl | updateOccupationInfo() | :- END ");
				if (status > 0)
					return true;
				else
					return false;
		
			

	}
}