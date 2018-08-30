package com.qc.starter.dao.daoImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.qc.starter.dao.PropertyDao;
import com.qc.starter.entity.ProjectEntity;
import com.qc.starter.entity.PropertyEntity;

@Repository
public class PropertyDaoImpl implements PropertyDao {
	private static final Logger logger = Logger.getLogger(PropertyDaoImpl.class
			.getName());
	@Autowired
	SessionFactory sessionFactory;

	@Transactional
	public List<PropertyEntity> getListOfProperty(String personalDetailId,String companyId) {
		logger.info("PropertyDaoImpl || getListOfProperty() || :-START");
		Session session = null;
		List<PropertyEntity > list=null;
		try{
			session = sessionFactory.openSession();
			list = new ArrayList<PropertyEntity>();
			String sql = "SELECT PROPERTY_ID,PROP_TYPE_ID,DEVELOPER_ID,PROJECT_ID, " +
					   " (SELECT DEVELOPER_NAME  FROM QC_PROSPECT_MASTER.QM_DEVELOPER QD  WHERE QD.COMPANY_ID =1000000001  AND QD.DEVELOPER_ID =a.DEVELOPER_ID) DEVELOPER_NAME," +
					   " (SELECT QP.PROJECT_NAME FROM QC_PROSPECT_MASTER.QM_PROJECT QP  WHERE QP.COMPANY_ID ="+companyId+"  AND  QP.PROJECT_ID = a.PROJECT_ID) PROJECT_NAME, "+
					   " TO_CHAR(ESTIMATED_VALUE,'99,99,99,99,99,999') ESTIMATED_VALUE,ADDRESS,LAND_MARK,STATE," +
					   " CITY, (select c.citymastername from QC_PROSPECT_MASTER.qm_citymaster c where " +
					   " c.citymasterid = CITY) as cityName, ZIPCODE,PROP_STATUS,REMARKS,OTHER_DEVELOPER_NAME,OTHER_PROJECT_NAME,a.OCCUPANCY_STATUS FROM QC_PROSPECT.QT_PROPERTY a WHERE PERSONAL_DTL_ID = "+personalDetailId;
			Query query = session.createSQLQuery(sql);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List listf = query.list();
			int count=0;
			if(listf != null && listf.size() > 0){
				for(Object o : listf){	
					if(count==1){
						break;
					}
					Map rs = (Map) o;
					PropertyEntity prop = new PropertyEntity();
					prop.setPropertyId(rs.get("PROPERTY_ID")!=null?rs.get("PROPERTY_ID")+"":"");
					prop.setPropTypeId(rs.get("PROP_TYPE_ID")!=null?rs.get("PROP_TYPE_ID")+"":"");
					prop.setDevloperId(rs.get("DEVELOPER_ID")==null?"":rs.get("DEVELOPER_ID")+"");
					prop.setDeveloperName(rs.get("DEVELOPER_NAME")!=null?rs.get("DEVELOPER_NAME")+"":"");
					prop.setProjectId(rs.get("PROJECT_ID")!=null?rs.get("PROJECT_ID")+"":"");
					prop.setProjectName(rs.get("PROJECT_NAME")!=null?rs.get("PROJECT_NAME")+"":"");
					prop.setEstimatedValue((rs.get("ESTIMATED_VALUE")==null?"":rs.get("ESTIMATED_VALUE")+"").trim());
					prop.setAddress(rs.get("ADDRESS")!=null?rs.get("ADDRESS")+"":"");
					prop.setLandMark(rs.get("LAND_MARK")!=null?rs.get("LAND_MARK")+"":"");
					prop.setState(rs.get("STATE")!=null?rs.get("STATE")+"":"");
					prop.setCity(rs.get("CITY")!=null?rs.get("CITY")+"":"");
					prop.setCityName(rs.get("CITYNAME")!=null?rs.get("CITYNAME")+"":"");
					prop.setZipcode(rs.get("ZIPCODE")!=null?rs.get("ZIPCODE")+"":"");
					prop.setPropStatus(rs.get("PROP_STATUS")!=null?rs.get("PROP_STATUS")+"":"");
					prop.setRemarks(rs.get("REMARKS")!=null?rs.get("REMARKS")+"":"");
					prop.setOtherDeploperName(rs.get("OTHER_DEVELOPER_NAME")!=null?rs.get("OTHER_DEVELOPER_NAME")+"":"");
					prop.setOtherProjectName(rs.get("OTHER_PROJECT_NAME")!=null?rs.get("OTHER_PROJECT_NAME")+"":"");
					prop.setOccupancyStatus(rs.get("OCCUPANCY_STATUS")!=null?rs.get("OCCUPANCY_STATUS")+"":"");
					list.add(prop);
					count++;
					prop = null;
				}
			}
		}catch(Exception e){
			logger.error("PropertyDaoImpl || getListOfProperty() || "+e.getMessage()+" || :-END");
			return null;
		}finally {
			if(session!=null)
				session.close();
		}
		return list;
	}

	@Transactional
	public boolean addProperty(PropertyEntity propertyEntity) {
		return false;
	}

	@Transactional
	public int deleteProperty(List<PropertyEntity> list) {
		logger.info("PropertyDaoImpl | deleteProperty() | :-START");
		Session session = sessionFactory.openSession();
		int pass = 0;
		try{
		Query query = session
				.createQuery("delete from PropertyEntity where propertyId = :propertyId");
		for (PropertyEntity p : list) {
			try {
				pass += query.setParameter("propertyId", p.getPropertyId())
						.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("PropertyDaoImpl | deleteProperty() | "
						+ e.getMessage() );
			}
		}
	}catch(Exception e){
		e.printStackTrace();
		logger.error("PropertyDaoImpl | deleteProperty() | "+ e.getMessage() + " | :-END");
	}finally {
		if(session!=null)
			session.close();
	}
		logger.info("PropertyDaoImpl | deleteProperty() | :-END");
		return pass;
	}

	@Transactional
	public boolean updateProperty(List<PropertyEntity> list) {
		return false;
	}

	@Transactional
	public int addPropertyList(List<PropertyEntity> toInsert) {
		logger.info("PropertyDaoImpl | addPropertyList() | :-START");
		Session session =null;
		int pass = 0;
		try {


			session=sessionFactory.openSession();

			Query query = session
					.createSQLQuery("INSERT INTO QC_PROSPECT.QT_PROPERTY"
							+ "(PROPERTY_ID,PERSONAL_DTL_ID,CASE_ID,PROP_TYPE_ID,DEVELOPER_ID,PROJECT_ID, ESTIMATED_VALUE, ADDRESS, LAND_MARK,CITY,STATE,ZIPCODE, PROP_STATUS,REMARKS,CREATED_BY,CREATED_DATE, CREATED_SYS_DATE,OTHER_DEVELOPER_NAME ,OTHER_PROJECT_NAME) VALUES "
							+ "(QC_PROSPECT.SEQ_PROPERTY.NEXTVAL,:personalDtlId,:caseId,:propTypeId,:developerId,:projectId,:estimatedValue,:address,:landmark,:city,:state,:zipCode,:propStatus,:remarks,:createdBy,:createdDate,:createdSysDate,:otherDevloperName,:otherprojectName)");
			for (PropertyEntity prop : toInsert) {
				query.setParameter("personalDtlId", prop.getPersonalDtlId())
				.setParameter("caseId", prop.getCaseId())
				.setParameter("propTypeId",prop.getPropTypeId().equals("-1") ? "" : prop.getPropTypeId())
				.setParameter("developerId",prop.getDevloperId() == null ? "" : prop.getDevloperId())
				.setParameter("state",prop.getState() == null ? "" : prop.getState())
				.setParameter("projectId",prop.getProjectId() == null ? "" : prop.getProjectId())
				.setParameter("estimatedValue",prop.getEstimatedValue() == null ? "" : prop.getEstimatedValue())
				.setParameter("address",prop.getAddress() == null ? "" : prop.getAddress())
				.setParameter("landmark",prop.getLandMark() == null ? "" : prop.getLandMark())
				.setParameter("propStatus",prop.getPropStatus().equals("-1") ? "" : prop.getPropStatus())
				.setParameter("remarks",prop.getRemarks() == null ? "" : prop.getRemarks())
				.setParameter("city",prop.getCity()==null ? "" : prop.getCity())
				.setParameter("zipCode",prop.getZipcode() == null ? "" : prop.getZipcode())
				.setParameter("createdBy", prop.getCreatedBy())
				.setDate("createdDate", prop.getCreatedDate())
				.setDate("createdSysDate", prop.getCreatedSysDate())
				.setParameter("otherDevloperName", prop.getOtherDeploperName()==null?"":prop.getOtherDeploperName())
				.setParameter("otherprojectName", prop.getOtherProjectName()==null?"":prop.getOtherProjectName());

				try {
					pass += query.executeUpdate();
				} catch (Exception e) {
					logger.info("PropertyDaoImpl | addPropertyList() | "
							+ e.getMessage() + " | :-END");
				}
			}
		}
		catch (Exception e) {
			logger.info("PropertyDaoImpl | addPropertyList() | "+ e.getMessage() + " | :-END");
			e.printStackTrace();
		}
		finally {
			if(session!=null)
				session.close();
		}

		logger.info("PropertyDaoImpl | addPropertyList() | :-START");
		return pass;
	}

	@Transactional
	public int updatePropertyList(List<PropertyEntity> toUpdate) {
		logger.info("PropertyDaoImpl | updatePropertyList() | :-START");
		Session session=null;
		int pass = 0;
		try{
			session = sessionFactory.openSession();
		Query query = session
				.createQuery("update PropertyEntity set propTypeId=:propTypeId,devloperId=:devloperId, projectId=:projectId,"
						+ "estimatedValue=:estimatedValue, address=:address, landMark=:landMark, propStatus=:propStatus,occupancyStatus=:occupancyStatus,remarks=:remarks," +
						" city=:city, zipcode=:zipcode,state=:state,"
						+ "updatedBy=:updatedBy, updatedDate=:updatedDate, updatedSysDate=:updatedSysDate,otherDeploperName=:otherDeploperName,otherProjectName=:otherProjectName "
						+ "where propertyId=:propertyId");
		for (PropertyEntity prop : toUpdate) {
			query.setParameter("propTypeId",prop.getPropTypeId().equals("-1") ? "" : prop.getPropTypeId())
			.setParameter("devloperId",prop.getDevloperId() == null ? "" : prop.getDevloperId())
			.setParameter("projectId",prop.getProjectId() == null ? "" : prop.getProjectId())
			.setParameter("estimatedValue",prop.getEstimatedValue() == null ? "" : prop.getEstimatedValue().replaceAll(",", ""))
			.setParameter("address",prop.getAddress() == null ? "" : prop.getAddress())
			.setParameter("landMark",prop.getLandMark() == null ? "" : prop.getLandMark())
			.setParameter("propStatus",prop.getPropStatus().equals("-1") ? "" : prop.getPropStatus())
			.setParameter("occupancyStatus", prop.getOccupancyStatus().equals("-1") ? "" : prop.getOccupancyStatus())
			.setParameter("remarks",prop.getRemarks() == null ? "" : prop.getRemarks())
			.setParameter("city",prop.getCity().equals("-1") ? "" : prop.getCity())
			.setParameter("zipcode",prop.getZipcode() == null ? "" : prop.getZipcode())
			.setParameter("state",prop.getState().equals("-1") ? "" : prop.getState())
			.setParameter("updatedBy", prop.getUpdatedBy())
			.setDate("updatedDate", prop.getUpdatedDate())
			.setDate("updatedSysDate", prop.getUpdatedSysDate())
			.setParameter("otherDeploperName", prop.getOtherDeploperName())
			.setParameter("otherProjectName", prop.getOtherProjectName())
			.setParameter("propertyId", prop.getPropertyId());
			try {
				pass += query.executeUpdate();
			} catch (Exception e) {
				logger.error("PropertyDaoImpl | updatePropertyList() | "
						+ e.getMessage() );
			}
		}
		}catch(Exception e){
			logger.error("PropertyDaoImpl | updatePropertyList() | "	+ e.getMessage() + " | :-END");
		}finally{
			if(session!=null)
				session.close();
		}
		logger.info("PropertyDaoImpl | updatePropertyList() | :-END");
		return pass;
	}
	//--------Added by Deepak on 03-march-2016-----------
	@Transactional
	public List<ProjectEntity> getListOfProject(String devloperId,String companyId) {
		logger.info("PropertyDaoImpl | getListOfProject() | :-START");
		List<ProjectEntity> projectList=null;
		Session session =null;
		try {
			String sql="SELECT PROJECT_ID, PROJECT_NAME FROM QC_PROSPECT_MASTER.QM_PROJECT WHERE ACTIVE ='A' " +
					" AND DEVELOPER_ID =:devloperId	AND COMPANY_ID = :companyId  ";
			projectList=new ArrayList<ProjectEntity>();
			session = sessionFactory.openSession();
			SQLQuery query=session.createSQLQuery(sql);
			query.setParameter("devloperId", devloperId);
			query.setParameter("companyId", companyId);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List result = query.list();	
			if(result != null && result.size() > 0){
				for(Object o : result){				
					Map projecMap = (Map) o;
					ProjectEntity projectEntity=new ProjectEntity();
					projectEntity.setProjectId(projecMap.get("PROJECT_ID")==null?"":projecMap.get("PROJECT_ID")+"");
					projectEntity.setProjectName(projecMap.get("PROJECT_NAME")==null?"":projecMap.get("PROJECT_NAME")+"");
					projectList.add(projectEntity);
					projectEntity=null;
				}
			}
		} catch (Exception e) {
			logger.info("Error in PropertyDaoImpl | getListOfProject() |DUE to--->>>>"+e.getMessage());
			e.printStackTrace();
		}finally{
			if(session!=null){
				session.close();
				session=null;
			}
		}
		logger.info("PropertyDaoImpl | getListOfProject() | :-END");
		return projectList;
	}

}
