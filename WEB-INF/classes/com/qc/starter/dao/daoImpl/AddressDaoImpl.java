package com.qc.starter.dao.daoImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
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

import com.qc.starter.basic.ConnectionFromSession;
import com.qc.starter.dao.AddressDao;
import com.qc.starter.entity.AddressEntity;
import com.qc.starter.entity.PropertyEntity;
import com.qc.starter.entity.UserEntity;

@Repository
public class AddressDaoImpl implements AddressDao {
	private static final Logger logger = Logger.getLogger(AddressDaoImpl.class
			.getName());
	@Autowired	SessionFactory sessionFactory;
	@Autowired HttpSession httpSession;

	@Transactional
	public AddressEntity getResAddress(String personalDetailId) {
		List<AddressEntity> list=null;
		Session session =null;
		try{
			session = sessionFactory.openSession();
			list= session.createQuery("from AddressEntity where addressType = :addressType and personalDtlId = :personalDetailId")
					.setParameter("addressType", "1000000001")
					.setParameter("personalDetailId", personalDetailId ).list();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
		if(list.size()!=0)
			return (AddressEntity)list.get(0);
		else return null;
	}

	@Transactional
	public AddressEntity getOffAddress(String personalDetailId) {
		return null;
	}

	@Transactional
	public boolean updateResAddress(AddressEntity addressEntity) {			
		return true;
	}

	@Transactional
	public List<AddressEntity> listAddress(String personalDetailId) {
		logger.info("AddressDaoImpl | listAddress() | :- START  ::: WITH Request personalDetailId::"
				+ personalDetailId);
		List<AddressEntity> list = null;
		Session session =null;
		try{
			session = sessionFactory.openSession();
			list = new ArrayList<AddressEntity>();
			String sql = "select ADDRESS_ID,PERSONAL_DTL_ID,CASE_ID,ADDRESS_TYPE,ADDRESS,FLATNO,FLOORNO,BUILDINGNAME," +
					     " LOCALITY,COMPANY_NAME,YRS_IN_AREA,OLD_ADDRESS,BUSINESS_EST_YR,PROP_MARKET_VALUE,GSTIN_NO,LANDMARK,EMAIL,MOBILE1, MOBILE2,IS_DEST_ADD,CITY, (select c.citymastername from QC_PROSPECT_MASTER.qm_citymaster c where " +
					     " c.citymasterid = CITY) as cityName,STATE,ZIPCODE,STD_ISD,PHONE1,EXT1,PHONE2,EXT2,FAX,MAILINGADDRESS," +
					     "OCCUPANCY_MM,OCCUPANCY_YR,OCCUPANCY_STATUS,SAMEAS,ACTIVE from QC_PROSPECT.QT_ADDRESS" +
					     " where PERSONAL_DTL_ID= "+personalDetailId;
			Query query = session.createSQLQuery(sql);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List listf = query.list();
			if(listf != null && listf.size() > 0){
				for(Object o : listf){						
					Map rs = (Map) o;
					AddressEntity addObj = new AddressEntity();
					addObj.setAddressId(rs.get("ADDRESS_ID")!=null?rs.get("ADDRESS_ID")+"":"");
					addObj.setPersonalDtlId(rs.get("PERSONAL_DTL_ID")!=null?rs.get("PERSONAL_DTL_ID")+"":"");
					addObj.setCaseId(rs.get("CASE_ID")==null?"":rs.get("CASE_ID")+"");
					addObj.setAddressType(rs.get("ADDRESS_TYPE")!=null?rs.get("ADDRESS_TYPE")+"":"");
					addObj.setAddress(rs.get("ADDRESS")!=null?rs.get("ADDRESS")+"":"");
					addObj.setFlatNo(rs.get("FLATNO")!=null?rs.get("FLATNO")+"":"");
					addObj.setFloorNo((rs.get("FLOORNO")==null?"":rs.get("FLOORNO")+"").trim());
					addObj.setBuildingName(rs.get("BUILDINGNAME")!=null?rs.get("BUILDINGNAME")+"":"");
					addObj.setLocality(rs.get("LOCALITY")!=null?rs.get("LOCALITY")+"":"");
					addObj.setLandmark(rs.get("LANDMARK")!=null?rs.get("LANDMARK")+"":"");
					addObj.setState(rs.get("STATE")!=null?rs.get("STATE")+"":"");
					addObj.setCity(rs.get("CITY")!=null?rs.get("CITY")+"":"");
					addObj.setCityName(rs.get("CITYNAME")!=null?rs.get("CITYNAME")+"":"");
					addObj.setZipcode(rs.get("ZIPCODE")!=null?rs.get("ZIPCODE")+"":"");
					addObj.setStdIsd(rs.get("STD_ISD")!=null?rs.get("STD_ISD")+"":"");
					addObj.setPhone1(rs.get("PHONE1")!=null?rs.get("PHONE1")+"":"");
					addObj.setExt1(rs.get("EXT1")!=null?rs.get("EXT1")+"":"");
					addObj.setPhone2(rs.get("PHONE2")!=null?rs.get("PHONE2")+"":"");
					addObj.setExt2(rs.get("EXT2")!=null?rs.get("EXT2")+"":"");
					addObj.setFax(rs.get("FAX")!=null?rs.get("FAX")+"":"");
					addObj.setMailingAddress(rs.get("MAILINGADDRESS")!=null?rs.get("MAILINGADDRESS")+"":"");
					addObj.setDestinationAddress(rs.get("IS_DEST_ADD")!=null?rs.get("IS_DEST_ADD")+"":"");
					addObj.setOccupancyMm(rs.get("OCCUPANCY_MM")!=null?rs.get("OCCUPANCY_MM")+"":"");
					addObj.setOccupancyYr(rs.get("OCCUPANCY_YR")!=null?rs.get("OCCUPANCY_YR")+"":"");
					addObj.setOccupancyStatus(rs.get("OCCUPANCY_STATUS")==null?"":rs.get("OCCUPANCY_STATUS")+"");
					addObj.setSameas(rs.get("SAMEAS")!=null?rs.get("SAMEAS")+"":"");
					addObj.setActive(rs.get("ACTIVE")!=null?rs.get("ACTIVE")+"":"");
					addObj.setCompany_name(rs.get("COMPANY_NAME")!=null?rs.get("COMPANY_NAME")+"":"");
					addObj.setCurrentareaYr(rs.get("YRS_IN_AREA")!=null?rs.get("YRS_IN_AREA")+"":"");
					addObj.setOldaddress(rs.get("OLD_ADDRESS")!=null?rs.get("OLD_ADDRESS")+"":"");
					addObj.setBussinessestbyr(rs.get("BUSINESS_EST_YR")!=null?rs.get("BUSINESS_EST_YR")+"":"");
					addObj.setMarketvalue(rs.get("PROP_MARKET_VALUE")!=null?rs.get("PROP_MARKET_VALUE")+"":"");
					addObj.setGstinno(rs.get("GSTIN_NO")!=null?rs.get("GSTIN_NO")+"":"");	
					addObj.setMobile_no1(rs.get("MOBILE1")!=null?rs.get("MOBILE1")+"":"");	
					addObj.setMobile_no2(rs.get("MOBILE2")!=null?rs.get("MOBILE2")+"":"");	
					addObj.setEmail(rs.get("EMAIL")!=null?rs.get("EMAIL")+"":"");	
				
					list.add(addObj);
					
					addObj = null;
				}
			}
			/*list = session .createQuery("from AddressEntity where personalDtlId = :personalDetailId")
					.setParameter("personalDetailId", personalDetailId).list();*/
		}catch (Exception e) {
			logger.error("AddressDaoImpl | getResAddress() " + e.getMessage()+ "| :- END ");
		}finally {
			if(session!=null)
				session.close();
		}
		logger.info("AddressDaoImpl | listAddress() | :- END  ::: WITH Response List<AddressEntity>::"+ list);
		if (list.size() != 0)
			return list;
		else
			return null;
	}

	@Transactional
	public int updateAddressList(List<AddressEntity> toUpdate) {
		logger.info("AddressDaoImpl | updateAddressList() | :- START  ::: WITH Request List<AddressEntity>::"+ toUpdate);
		Session session = sessionFactory.openSession();
		boolean mailFlag = false;
		int pass = 0;
		UserEntity userEntity = (UserEntity) httpSession.getAttribute("UserDetails");	
		try 
		{Query query = session.createQuery("update AddressEntity set addressType=:addressType, address=:address,landmark=:landmark,mailingAddress=:mailingAddress, "
				+ "state=:state, city=:city, zipcode=:zipcode, phone1=:phone1, ext1=:ext1, occupancyMm=:occupancyMm, occupancyYr=:occupancyYr,occupancyStatus=:occupancyStatus,"
				+ " updatedBy=:updatedBy,updatedDate=:updatedDate, updatedSysDate=:updatedSysDate, COMPANY_NAME=:company_name,FLATNO=:flatNo,FLOORNO=:floorNo,LOCALITY=:locality,GSTIN_NO=:gstinno,OLD_ADDRESS=:oldaddress,PROP_MARKET_VALUE=:marketvalue,PHONE2=:phone2,BUSINESS_EST_YR=:bussinessestbyr,MOBILE1=:mob1,MOBILE2=:mob2,EMAIL=:email,IS_DEST_ADD=:dest    where addressId=:addressId");
			for (AddressEntity add : toUpdate) 
			{			
				if (add.getMailingAddress() != null) {
					Query query2 = session
							.createQuery("update AddressEntity set mailingAddress=:mailingAddress where caseId=:caseId and personalDtlId=:personalDtlId");
					query2.setParameter("mailingAddress", "N")
					.setParameter("caseId", add.getCaseId())
					.setParameter("personalDtlId",
							add.getPersonalDtlId()).executeUpdate();
				}
				if (add.getAddressType() == null)
					query.setParameter("addressType", "1000000001");
				else if (add.getAddressType().equals("-1"))
					query.setParameter("addressType", "1000000010");
				else
					query.setParameter("addressType", add.getAddressType());
				query.setParameter("address", add.getAddress()).setParameter(
						"landmark", add.getLandmark());
				if (mailFlag == false && add.getMailingAddress() != null) {
					query.setParameter("mailingAddress", "Y");
					mailFlag = true;
				} else {
					query.setParameter("mailingAddress", "N");
				}
				query.setParameter("state",add.getState().equals("-1") ? "" : add.getState())
				.setParameter("city",add.getCity().equals("-1") ? "" : add.getCity())
				.setParameter("zipcode",add.getZipcode() == "" ? null:add.getZipcode())
				.setParameter("phone1",add.getPhone1() == ""? null: add.getPhone1())
				.setParameter("ext1",add.getExt1() == "" ?null : add.getExt1())
				.setParameter("occupancyMm",add.getOccupancyMm() == null ? "" : add.getOccupancyMm())
				.setParameter("occupancyYr",add.getOccupancyYr() == null ? "" : add.getOccupancyYr())
				.setParameter("occupancyStatus",add.getOccupancyStatus().equals("-1") ? "": add.getOccupancyStatus())
				.setParameter("updatedBy", add.getUpdatedBy())
				.setDate("updatedDate", new Date())
				.setDate("updatedSysDate", new Date())
				.setParameter("company_name",add.getCompany_name() == ""? null: add.getCompany_name())
				.setParameter("flatNo",add.getFlatNo() == ""? null: add.getFlatNo())
				.setParameter("floorNo",add.getFloorNo() == ""? null: add.getFloorNo())
				.setParameter("locality",add.getLocality() == ""? null: add.getLocality())
				.setParameter("gstinno",add.getGstinno() == ""? null: add.getGstinno())
				.setParameter("oldaddress",add.getOldaddress() == ""? null: add.getOldaddress())
				.setParameter("marketvalue",add.getMarketvalue() == ""? null: add.getMarketvalue())
				.setParameter("phone2",add.getPhone2() == ""? null: add.getPhone2())	
				.setParameter("bussinessestbyr",add.getBussinessestbyr() == ""? null: add.getBussinessestbyr())	
				.setParameter("mob1",add.getMobile_no1() == ""? null: add.getMobile_no1())	
				.setParameter("mob2",add.getMobile_no2() == ""? null: add.getMobile_no2())	
				.setParameter("email",add.getEmail()== ""? null: add.getEmail())	
				.setParameter("dest",add.getDestinationAddress()== ""? null: add.getDestinationAddress())
				
				.setParameter("addressId", add.getAddressId());
				pass += query.executeUpdate();
			}
		}
		catch (Exception e) 
		{
			logger.error("Error in catch block due to::::->"+ e.getMessage());
			e.printStackTrace();
		}
		logger.info("AddressDaoImpl | updateAddressList() | :- END  ::: WITH Response int::"+ pass);
		if(session!=null)
			session.close();
		return pass;
	}

	@Transactional
	public int insertAddressList(List<AddressEntity> toInsert) {
		logger.info("AddressDaoImpl | insertAddressList() | :- START  ::: WITH Request List<AddressEntity>::"+toInsert);
		Session session = sessionFactory.openSession();
		int pass = 0;
		SQLQuery query = session.createSQLQuery("insert into QC_PROSPECT.QT_ADDRESS "
				+ "(ADDRESS_ID, PERSONAL_DTL_ID, CASE_ID, ADDRESS_TYPE, address,company_name,FLATNO,FLOORNO,PROP_MARKET_VALUE,LOCALITY,GSTIN_NO,YRS_IN_AREA,BUSINESS_EST_YR,fax,PHONE2,OLD_ADDRESS,MOBILE1,MOBILE2,EMAIL,IS_DEST_ADD,landmark, city, state, zipcode, phone1, ext1,"
				+ " mailingAddress, OCCUPANCY_MM, OCCUPANCY_YR, OCCUPANCY_STATUS, active, CREATED_BY, CREATED_DATE, CREATED_SYS_DATE) "
				+ "values(QC_PROSPECT.SEQ_CASE_ADDRESS.NEXTVAL,:pdi,:ci,:at,:add,:cmpny,:flatno,:floorno,:marketvalue,:locality,:gstno,:crtareayr,:bussestyr,:fax,:phn2,:oldadd,:mob1,:mob2,:email,:dest,:land,:city,:state,:zip,:phn,:ext,:mail,:omm,:oyr,:ostat,:active,:cby,:cdate,:csysdate )");
		for(AddressEntity add:toInsert){
			if(add!=null){
				try{
					if(add.getMailingAddress()!=null){
						Query query2 = session.createQuery("update AddressEntity set mailingAddress=:mailingAddress where caseId=:caseId and personalDtlId=:personalDtlId");
						query2.setParameter("mailingAddress", "N").setParameter("caseId", add.getCaseId()).setParameter("personalDtlId", add.getPersonalDtlId()).executeUpdate();
					}
					query.setParameter("pdi", add.getPersonalDtlId())
					.setParameter("ci", add.getCaseId())
					.setParameter("at", add.getAddressType().equals("-1")?"1000000010":add.getAddressType())
					.setParameter("add", add.getAddress().equals("")?"":add.getAddress())
					.setParameter("cmpny", add.getCompany_name()==null?"":add.getCompany_name())
					.setParameter("flatno", add.getFlatNo()==null?"":add.getFlatNo())
					.setParameter("floorno", add.getFloorNo()==null?"":add.getFloorNo())
					.setParameter("marketvalue", add.getMarketvalue()==null?"":add.getMarketvalue())
					.setParameter("locality", add.getLocality()==null?"":add.getLocality())
					.setParameter("gstno", add.getGstinno()==null?"":add.getGstinno())
					.setParameter("crtareayr", add.getCurrentareaYr()==null?"":add.getCurrentareaYr())
					.setParameter("bussestyr", add.getBussinessestbyr()==null?"":add.getBussinessestbyr())
					.setParameter("fax", add.getFax()==null?"":add.getFax())
				    .setParameter("phn2", add.getPhone2()==null?"":add.getPhone2())
				    .setParameter("oldadd", add.getOldaddress()==null?"":add.getOldaddress())
				    .setParameter("mob1", add.getMobile_no1()==null?"":add.getMobile_no1())
				    .setParameter("mob2", add.getMobile_no2()==null?"":add.getMobile_no2())
				    .setParameter("email", add.getEmail()==null?"":add.getEmail())
				    .setParameter("dest", add.getDestinationAddress()==null?"":add.getDestinationAddress())
					.setParameter("land", add.getLandmark()==null?"":add.getLandmark())
					.setParameter("city", add.getCity().equals("-1")?"-1":add.getCity())
					.setParameter("state", add.getState().equals("-1")?"-1":add.getState())
					.setParameter("zip", add.getZipcode()==null?"":add.getZipcode())
					.setParameter("phn", add.getPhone1()==null?"":add.getPhone1())
					.setParameter("ext", add.getExt1()==null?"":add.getExt1())
					.setParameter("mail", add.getMailingAddress()==null?"N":"Y")
					.setParameter("omm", add.getOccupancyMm()==null?"":add.getOccupancyMm())
					.setParameter("oyr", add.getOccupancyYr()==null?"":add.getOccupancyYr())
					.setParameter("ostat", add.getOccupancyStatus().equals("-1")?"-1":add.getOccupancyStatus())
					.setParameter("active", "Y")
					.setParameter("cby", add.getCreatedBy()==null?"1000000467":"1000000467")
					.setDate("cdate", new Date())
					.setDate("csysdate", new Date());
					pass += query.executeUpdate();
				}
				catch(Exception e){
					logger.error("Error in catch block due to::::->"+e.getMessage());
					e.printStackTrace();
				}			
			}
		}
		if(session!=null)
			session.close();
		logger.info("AddressDaoImpl | insertAddressList() | :- END  ::: WITH Response int::"+pass);
		return pass;
	}

	@Transactional
	public int deleteAddressList(List<AddressEntity> toDelete) {
		logger.info("AddressDaoImpl | deleteAddressList() | :- START  ::: WITH Request List<AddressEntity>::"+toDelete);
		Session session =null;
		int pass = 0 ;
		try{
			session = sessionFactory.openSession();
			Query query = session.createQuery("delete from AddressEntity where addressId=:addressId");
			for(AddressEntity add : toDelete){
				pass += query.setParameter("addressId", add.getAddressId()).executeUpdate();
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
		logger.info("AddressDaoImpl | deleteAddressList() | :- END  ::: WITH WITH Response int::"+pass);
		return pass;
	}


	@Transactional
	public boolean updateAllAddressList(String caseId) {
		logger.info("AddressDaoImpl | updateAllAddressList() | :- START  ::");
		Session session = sessionFactory.openSession();
		boolean flage=false;
		try{
			String sql = "update AddressEntity SET mailingAddress='N' WHERE caseId = :caseId";
			Query query = session.createQuery(sql);
			query.setParameter("caseId",caseId);
			int update = query.executeUpdate();
			if(update>0){
				flage=true;	
			}
		}catch(Exception e){
			logger.error("AddressDaoImpl | updateAllAddressList() | "+e.getMessage()+" | :- END");
		}finally {
			if(session!=null)
				session.close();
		}
		logger.info("AddressDaoImpl | deleteAddressList() | :- END  :::");
		return flage;
	}

	@Override
	public List<AddressEntity>  addressListdetail(String caseId) {
		logger.info("AddressDaoImpl | addressListdetail() | :- START  ::: WITH Request caseId::");
		List<AddressEntity> list = null;
		Session session =null;
		try{
			session = sessionFactory.openSession();
			list = new ArrayList<AddressEntity>();
			String sql = "select ADDRESS_ID,ADDRESS_TYPE,CASE_ID,MOBILE1_ID,MOBILE2_ID,EMAIL_ID from QC_PROSPECT.QT_ADDRESS" +
					     " where CASE_ID= "+caseId;
			Query query = session.createSQLQuery(sql);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List listf = query.list();
			if(listf != null && listf.size() > 0){
				for(Object o : listf){						
					Map rs = (Map) o;
					AddressEntity addObj = new AddressEntity();
					addObj.setAddressId(rs.get("ADDRESS_ID")!=null?rs.get("ADDRESS_ID")+"":"");
					addObj.setAddressType(rs.get("ADDRESS_TYPE")!=null?rs.get("ADDRESS_TYPE")+"":"");
					addObj.setMobile1_Id1(rs.get("MOBILE1_ID")!=null?rs.get("MOBILE1_ID")+"":"");				
					addObj.setMobile1_Id2(rs.get("MOBILE2_ID")!=null?rs.get("MOBILE2_ID")+"":"");		
					addObj.setEmail_Id(rs.get("EMAIL_ID")!=null?rs.get("EMAIL_ID")+"":"");			
					list.add(addObj);			
					addObj = null;
				}
			}
			/*list = session .createQuery("from AddressEntity where personalDtlId = :personalDetailId")
					.setParameter("personalDetailId", personalDetailId).list();*/
		}catch (Exception e) {
			logger.error("AddressDaoImpl | getResAddress() " + e.getMessage()+ "| :- END ");
		}finally {
			if(session!=null)
				session.close();
		}
		logger.info("AddressDaoImpl | addressListdetail() | :- END  ::: WITH Response List<AddressEntity>::"+ list);
		if (list.size() != 0)
			return list;
		else
			return null;
	}
	
	
	
	
	
	
	public List<AddressEntity> saveAddressFromProcedure(String insertString,String updateString, String caseId){
		logger.info("AddressDaoImpl | saveAddressFromProcedure() | :- START param "+insertString);
		Session session = sessionFactory.openSession();		
		Connection con = null;
		CallableStatement cstmt = null;
		
		String addressIdResponse = "";
		String out ="";
		String message ="";
		String executionStatus="";
		
		
		if(!insertString.equals("")){
			try {
				con = ConnectionFromSession.getJavaSqlConnectionFromHibernateSession(session);
				cstmt  = con.prepareCall("{call QC_PROSPECT.PR_LEAD_SAVE_ADDRESS(?,?,?,?,?,?)}");
				logger.info("{call QC_PROSPECT.PR_LEAD_SAVE_ADDRESS(?,?,?,?)}");
				
				cstmt.setString(1,caseId);
				logger.info("1 type=in value="+caseId);
				
				cstmt.setString(2,insertString);
				logger.info("2 type=in for insertAddress value="+insertString);
							
				cstmt.registerOutParameter(3, java.sql.Types.CLOB);
				logger.info("3 type=out");
				
				cstmt.registerOutParameter(4, java.sql.Types.CLOB);
				logger.info("4 type=out");
				
				cstmt.registerOutParameter(5, java.sql.Types.CLOB);
				logger.info("5 type=out");
							
				cstmt.registerOutParameter(6, java.sql.Types.VARCHAR);
				logger.info("6 type=out");
			
				cstmt.executeUpdate();			
				addressIdResponse = cstmt.getString(3);
				out = cstmt.getString(4);
				message = cstmt.getString(5);
				executionStatus = cstmt.getString(6);
					
				//leadid=(leadCode!=null?leadCode.replaceAll("PR","10"):"");
							
			} catch (SQLException e) {		
				logger.error("AddressDaoImpl | saveAddressFromProcedure() | :- ERROR::::"+e.getMessage());
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
					logger.error("AddressDaoImpl | saveAddressFromProcedure() | :- ERROR::::"+e.getMessage());
					e.printStackTrace();
				}
			}
		}
		
		
		session = sessionFactory.openSession();
		
		if(!updateString.equals("")){
			try {
				con = ConnectionFromSession.getJavaSqlConnectionFromHibernateSession(session);
				cstmt  = con.prepareCall("{call QC_PROSPECT.PR_LEAD_SAVE_ADDRESS(?,?,?,?,?,?)}");
				logger.info("{call QC_PROSPECT.PR_LEAD_SAVE_ADDRESS(?,?,?,?)}");
				
				cstmt.setString(1,caseId);
				logger.info("1 type=in value="+caseId);
				
				cstmt.setString(2,updateString);
				logger.info("2 type=in for updateAddress value="+updateString);
							
				cstmt.registerOutParameter(3, java.sql.Types.CLOB);
				logger.info("3 type=out");
				
				cstmt.registerOutParameter(4, java.sql.Types.CLOB);
				logger.info("4 type=out");
				
				cstmt.registerOutParameter(5, java.sql.Types.CLOB);
				logger.info("5 type=out");
							
				cstmt.registerOutParameter(6, java.sql.Types.VARCHAR);
				logger.info("6 type=out");			
			
				cstmt.executeUpdate();			
				addressIdResponse = cstmt.getString(3);
				out = cstmt.getString(4);
				message = cstmt.getString(5);
				executionStatus = cstmt.getString(6);
				
				
				
				//leadid=(leadCode!=null?leadCode.replaceAll("PR","10"):"");
							
			} catch (SQLException e) {		
				logger.error("AddressDaoImpl | saveAddressFromProcedure() | :- ERROR::::"+e.getMessage());
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
					logger.error("AddressDaoImpl | saveAddressFromProcedure() | :- ERROR::::"+e.getMessage());
					e.printStackTrace();
				}
			}
		}
		
		logger.info("AddressDaoImpl | createLead() | :- END::::");
		return addressListdetail(caseId);
	}



}