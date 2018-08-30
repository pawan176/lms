package com.qc.starter.dao.daoImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.qc.starter.basic.ConnectionFromSession;
import com.qc.starter.dao.SearchAndAllocateDao;
import com.qc.starter.dto.SearchAndAllocateDto;
import com.qc.starter.entity.UserEntity;

@Repository
public class SearchAndAllocateDaoImpl implements SearchAndAllocateDao{
	private static final Logger logger = Logger.getLogger(SearchAndAllocateDaoImpl.class.getName());
	@Autowired SessionFactory sessionFactory;

	@Transactional
	public List<SearchAndAllocateDto> getLeadsToAllocate(UserEntity userEntity,String type,String userId) {
		logger.info("SearchAndAllocateDaoImpl | getLeadsToAllocate() | :- START : With request Param:: UserEntity ::"+userEntity +" type ::"+type);
		String sql="";
		Session session = sessionFactory.openSession();		
		List<SearchAndAllocateDto> leadList = new ArrayList<SearchAndAllocateDto>();
		try{
			if(type.equals(""))
			{
				sql = "SELECT ROW_NUMBER() OVER(ORDER BY CP.PRODNAME) RN,C.QUEUE_ID,CP.PRODNAME PRODUCT,C.SUB_QUEUE_ID,SQ.SUB_QUEUE,   " +
						"NVL(CMOB.DND_FLAG,'N') DND_FLAG,C.SOURCE_ID,SRC.SOURCE_NAME SOURCE,C.CAMPAIGN_ID,CMP.CAMPAIGN_NAME ,   COUNT(C.CASE_ID) " +
						"CNT FROM QC_PROSPECT.QT_CASE C,   QC_PROSPECT_MASTER.QM_PRODUCT  CP,   QC_PROSPECT_MASTER.QM_SUB_QUEUE SQ,   " +
						"QC_PROSPECT.QT_CASE_MOBILE CMOB,   QC_PROSPECT_MASTER.QM_SOURCE SRC,   QC_PROSPECT_MASTER.QM_CAMPAIGN CMP WHERE C.QUEUE_ID = CP.PRODUCTID  " +
						"AND C.SUB_QUEUE_ID = SQ.SUB_QUEUE_ID  AND C.CASE_ID = CMOB.CASE_ID AND CMOB.PRIMARY_CONTACT = 'Y'  " +
						"AND C.SOURCE_ID = SRC.CASE_SOURCE_ID  AND C.CAMPAIGN_ID = CMP.CAMPAIGN_ID  AND C.ALLOCATED_TO = "+userEntity.getUserid()+"  " +
						"AND C.COMPANY_ID = "+userEntity.getCompanyId()+" GROUP BY C.QUEUE_ID,CP.PRODNAME,C.SUB_QUEUE_ID,SQ.SUB_QUEUE,NVL(CMOB.DND_FLAG,'N')," +
						"  C.SOURCE_ID,SRC.SOURCE_NAME,C.CAMPAIGN_ID,CMP.CAMPAIGN_NAME ORDER BY " +
						"CP.PRODNAME,SQ.SUB_QUEUE,NVL(CMOB.DND_FLAG,'N'),SRC.SOURCE_NAME,CMP.CAMPAIGN_NAME";
			}
			else if(type.equalsIgnoreCase("alloted"))
			{	
				String userID="";
				if(userId!=null && !userId.equalsIgnoreCase("")){
					userID=userId;
				}
				else{
					userID=userEntity.getUserid().toString();
				}
				sql="SELECT ROW_NUMBER() OVER(ORDER BY PRODUCT) RN ,QUEUE_ID, PRODUCT,  SUB_QUEUE_ID , SUB_QUEUE, DND_FLAG, SOURCE_ID, " +
						" SOURCE, CAMPAIGN_ID,CAMPAIGN_NAME, COUNT(1) CNT FROM (SELECT QC.QUEUE_ID,  (SELECT PRODNAME  " +
						" FROM QC_PROSPECT_MASTER.QM_PRODUCT    P  WHERE P.COMPANY_ID  = "+userEntity.getCompanyId()+ " " +
						" AND P.PRODTYPEID  = (SELECT PRODUCTTYPEID FROM QC_PROSPECT_MASTER.QM_PRODUCTTYPE" +
						" WHERE PRODUCTTYPE='Product' AND COMPANY_ID ="+userEntity.getCompanyId()+ ") " +
						" AND P.ACTIVE  = 'A'  AND PRODUCTID = QC.QUEUE_ID) PRODUCT," +
						" QC.SUB_QUEUE_ID, (SELECT SQ.SUB_QUEUE   FROM QC_PROSPECT_MASTER.QM_SUB_QUEUE SQ  WHERE SQ.ACTIVE ='A' AND   " +
						" SQ.COMPANY_ID   = "+userEntity.getCompanyId()+ "  AND   SQ.SUB_QUEUE_ID = QC.SUB_QUEUE_ID) SUB_QUEUE," +
						" CB.DND_FLAG,QC.SOURCE_ID, (SELECT SRC.SOURCE_NAME   FROM  QC_PROSPECT_MASTER.QM_SOURCE SRC " +
						" WHERE SRC.ACTIVE         ='A' AND   SRC.COMPANY_ID     = "+userEntity.getCompanyId()+ "  AND " +
						"  SRC.CASE_SOURCE_ID = QC.SOURCE_ID) SOURCE, QC.CAMPAIGN_ID, (SELECT CMP.CAMPAIGN_NAME " +
						" FROM QC_PROSPECT_MASTER.QM_CAMPAIGN CMP WHERE CMP.ACTIVE = 'A' AND   CMP.COMPANY_ID = "+userEntity.getCompanyId()+ "" +
						" AND   CMP.CAMPAIGN_ID = QC.CAMPAIGN_ID ) CAMPAIGN_NAME,  CB.CASE_ID FROM  QC_PROSPECT.QT_CASE QC ," +
						" QC_PROSPECT.QT_CASE_MOBILE CB , QC_PROSPECT.QT_CASE_X_USER CXU WHERE CB.PRIMARY_CONTACT  ='Y' AND " +
						"  QC.CASE_ID = CB.CASE_ID AND   QC.CASE_ID = CXU.CASE_ID AND   CXU.ALLOCATED_TO = "+userID+ 
						"  and CXU.ALLOCATED_END_DATE is null AND   QC.COMPANY_ID	  = "+userEntity.getCompanyId()+ ") GROUP BY QUEUE_ID, PRODUCT,  SUB_QUEUE_ID ,SUB_QUEUE, DND_FLAG, SOURCE_ID, SOURCE, CAMPAIGN_ID,CAMPAIGN_NAME ORDER BY PRODUCT";

			}
			else if(type.equalsIgnoreCase("unalloted"))
			{
				sql=" SELECT ROW_NUMBER() OVER(ORDER BY PRODUCT) RN ,QUEUE_ID, PRODUCT,  SUB_QUEUE_ID , SUB_QUEUE, DND_FLAG," +
						" SOURCE_ID, SOURCE, CAMPAIGN_ID,CAMPAIGN_NAME, COUNT(1) CNT FROM (SELECT QC.QUEUE_ID,  (SELECT PRODNAME " +
						"  FROM QC_PROSPECT_MASTER.QM_PRODUCT    P  WHERE P.COMPANY_ID  = "+userEntity.getCompanyId()+ "" +
						" AND P.PRODTYPEID  = (SELECT PRODUCTTYPEID FROM QC_PROSPECT_MASTER.QM_PRODUCTTYPE" +
						" WHERE PRODUCTTYPE='Scheme' AND COMPANY_ID ="+userEntity.getCompanyId()+ ")  AND P.ACTIVE        = 'A' AND PRODUCTID = QC.QUEUE_ID) PRODUCT, " +
						"  QC.SUB_QUEUE_ID, (SELECT SQ.SUB_QUEUE  FROM QC_PROSPECT_MASTER.QM_SUB_QUEUE SQ  WHERE SQ.ACTIVE ='A'" +
						" AND SQ.COMPANY_ID   = "+userEntity.getCompanyId()+ "  AND   SQ.SUB_QUEUE_ID = QC.SUB_QUEUE_ID) SUB_QUEUE," +
						" CB.DND_FLAG,   QC.SOURCE_ID, (SELECT SRC.SOURCE_NAME  FROM  QC_PROSPECT_MASTER.QM_SOURCE SRC" +
						" WHERE SRC.ACTIVE  ='A' AND   SRC.COMPANY_ID     = "+userEntity.getCompanyId()+ " AND " +
						"  SRC.CASE_SOURCE_ID = QC.SOURCE_ID) SOURCE, QC.CAMPAIGN_ID,  (SELECT CMP.CAMPAIGN_NAME" +
						"  FROM QC_PROSPECT_MASTER.QM_CAMPAIGN CMP WHERE CMP.ACTIVE = 'A' AND   CMP.COMPANY_ID  = "+userEntity.getCompanyId()+ "" +
						" AND   CMP.CAMPAIGN_ID = QC.CAMPAIGN_ID ) CAMPAIGN_NAME, CB.CASE_ID FROM  QC_PROSPECT.QT_CASE QC ," +
						" QC_PROSPECT.QT_CASE_MOBILE CB WHERE QC.STATUS = 1 AND   CB.PRIMARY_CONTACT  ='Y' AND " +
						" QC.CASE_ID  = CB.CASE_ID AND   QC.ALLOCATED_TO     = "+userEntity.getUserid()+ " AND " +
						"  QC.COMPANY_ID	  = "+userEntity.getCompanyId()+ ") GROUP BY QUEUE_ID, PRODUCT,  SUB_QUEUE_ID ," +
						" SUB_QUEUE, DND_FLAG, SOURCE_ID, SOURCE, CAMPAIGN_ID,CAMPAIGN_NAME ORDER BY PRODUCT";
			}
			Query query = session.createSQLQuery(sql);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List leadstoallocate = query.list();
			if(leadstoallocate != null && leadstoallocate.size() > 0){
				for(Object o : leadstoallocate){				
					Map rt = (Map) o;
					SearchAndAllocateDto searchAndAllocateDto = new SearchAndAllocateDto();
					searchAndAllocateDto.setTemp("");
					searchAndAllocateDto.setRowNo(rt.get("RN")!=null? rt.get("RN").toString():" ");
					searchAndAllocateDto.setQueueId(rt.get("QUEUE_ID")!=null? rt.get("QUEUE_ID").toString(): " ");
					searchAndAllocateDto.setQueue(rt.get("PRODUCT")!=null? rt.get("PRODUCT").toString():" ");				
					searchAndAllocateDto.setSubQueueId( rt.get("SUB_QUEUE_ID")!=null? rt.get("SUB_QUEUE_ID").toString():" ");
					searchAndAllocateDto.setSubQueue(rt.get("SUB_QUEUE")!=null? rt.get("SUB_QUEUE").toString():" ");
					searchAndAllocateDto.setDnd(rt.get("DND_FLAG")!=null? rt.get("DND_FLAG").toString():" ");
					searchAndAllocateDto.setSourceId(rt.get("SOURCE_ID")!=null? rt.get("SOURCE_ID").toString():" ");
					searchAndAllocateDto.setSource(rt.get("SOURCE")!=null? rt.get("SOURCE").toString():" ");
					searchAndAllocateDto.setCampaignId(rt.get("CAMPAIGN_ID")!=null? rt.get("CAMPAIGN_ID").toString():" ");
					searchAndAllocateDto.setCampaign(rt.get("CAMPAIGN_NAME")!=null? rt.get("CAMPAIGN_NAME").toString():" ");
					searchAndAllocateDto.setNoOfCase(rt.get("CNT")!=null? rt.get("CNT").toString():" ");
					leadList.add(searchAndAllocateDto);
					searchAndAllocateDto=null;
				}
			}
		}catch(Exception e){
			logger.error("Error in catch block due to ::: --> "+ e.getMessage());
			e.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
		logger.info("SearchAndAllocateDaoImpl | getLeadsToAllocate() | :- END : With Respons Param:: List<SearchAndAllocateDto> ::"+leadList );
		return leadList;
	}

	@Transactional
	public void allotcases(List<SearchAndAllocateDto> s,UserEntity userEntity,String flag) {
		logger.info("SearchAndAllocateDaoImpl | allotcases() | :- START : With request Param::List<SearchAndAllocateDto> ::"+s +" flag ::"+flag);	
		Connection con=null;
		CallableStatement cstmt = null;
		Session session=null;
		try {
			session = sessionFactory.openSession();		
			con = ConnectionFromSession.getJavaSqlConnectionFromHibernateSession(session);		
			cstmt=con.prepareCall("{call  QC_PROSPECT.PR_CASE_ALLOCATE(?,?,?,?,?,?,?,?,?,?,?)}");	
			logger.info("{call  QC_PROSPECT.PR_CASE_ALLOCATE(?,?,?,?,?,?,?,?,?,?,?)}");
			cstmt.setString("P_ALLOCATION_FLAG", flag);	
			logger.info("P_ALLOCATION_FLAG type=in value="+flag);
			if(s.get(0).getQueueId()!=null){
				cstmt.setInt("P_QUEUE_ID",s.get(0).getQueueId()==""?0:(Integer.parseInt(s.get(0).getQueueId())));
				logger.info("P_QUEUE_ID type=in value="+s.get(0).getQueueId());}
			else{
				cstmt.setNull("P_QUEUE_ID",java.sql.Types.INTEGER);
				logger.info("P_QUEUE_ID type=in value=null");
			}
			if(s.get(0).getSubQueueId()!=null){
				cstmt.setInt("P_SUB_QUEUE_ID",s.get(0).getSubQueueId()==""?0:(Integer.parseInt(s.get(0).getSubQueueId())));
				logger.info("P_SUB_QUEUE_ID type=in value="+s.get(0).getSubQueueId());}
			else{
				cstmt.setNull("P_SUB_QUEUE_ID",java.sql.Types.INTEGER);
				logger.info("P_SUB_QUEUE_ID type=in value=null");
			}
			cstmt.setString("P_DND_FLAG",s.get(0).getDnd()==""?"": s.get(0).getDnd());
			if(s.get(0).getSourceId()!=null && (!s.get(0).getSourceId().equalsIgnoreCase(""))){
				cstmt.setInt("P_SOURCE_ID",Integer.parseInt(s.get(0).getSourceId()));
				logger.info("P_SOURCE_ID type=in value="+s.get(0).getSourceId());
			}else{
			cstmt.setNull("P_SOURCE_ID",java.sql.Types.INTEGER );
			logger.info("P_SOURCE_ID type=in value=null");
			}
			if(s.get(0).getCampaignId()!=null && (!s.get(0).getCampaignId().equalsIgnoreCase(""))){
				logger.info("P_SUB_QUEUE_ID type=in value="+s.get(0).getCampaignId());
				cstmt.setInt("P_CAMPAIGN_ID",Integer.parseInt(s.get(0).getCampaignId()));
			}else{
			cstmt.setNull("P_CAMPAIGN_ID",java.sql.Types.INTEGER );
			logger.info("P_CAMPAIGN_ID type=in value=null");}
			
			cstmt.setInt("P_ALLOCATED_FROM", s.get(0).getAllotedList()!=""?Integer.parseInt(s.get(0).getAllotedList()):userEntity.getUserid());
			logger.info("P_ALLOCATED_FROM type=in value="+s.get(0).getAllotedList());
			String alt="";
			int size=s.size();
			for(SearchAndAllocateDto st:s)
			{
				alt=alt+st.getAllocate()+"~"+st.getNoOfCase();
				size--;
				if(size!=0)
				{
					alt=alt	+"!";
				}
			}			
			cstmt.setString("P_QTY_ALLOCATED_TO",alt);
			logger.info("P_QTY_ALLOCATED_TO type=in value="+alt);
			cstmt.setInt("P_COMPANY_ID", Integer.parseInt(userEntity.getCompanyId()));
			logger.info("P_COMPANY_ID type=in value="+userEntity.getCompanyId());
			logger.info("out");
			cstmt.registerOutParameter("P_EXEC_STATUS", java.sql.Types.VARCHAR);
			logger.info("out");
			cstmt.registerOutParameter("P_MESSAGE", java.sql.Types.VARCHAR);			
			cstmt.executeUpdate();
			String status = cstmt.getString("P_EXEC_STATUS");
			String message = cstmt.getString("P_MESSAGE");

		}catch (SQLException e) {	
			logger.error("Error in catch block due to ::: --> "+ e.getMessage());
			e.printStackTrace();
		}
		finally{
			try {
				if(session!=null){
					session.close();
				}
				if(cstmt!=null){
					cstmt.close();
					cstmt=null;
				}
				if(con!=null){
					con.close();
					con=null;
				}
			}catch (Exception e2) {
				logger.error("Error in catch block due to ::: --> "+ e2.getMessage());
				e2.printStackTrace();
			}	
		}
		logger.info("SearchAndAllocateDaoImpl | allotcases() | :- END ");
	}

}
