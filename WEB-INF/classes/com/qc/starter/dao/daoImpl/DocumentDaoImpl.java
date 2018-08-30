package com.qc.starter.dao.daoImpl;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.qc.starter.basic.ConnectionFromSession;
import com.qc.starter.dao.DocumentDao;
import com.qc.starter.dto.DocumentDto;
import com.qc.starter.entity.DocumentEntity;
import com.qc.starter.entity.UserEntity;

@Repository
public class DocumentDaoImpl implements DocumentDao {
	private static final Logger logger = Logger.getLogger(DocumentDaoImpl.class.getName());
	private static final int BUFFER_SIZE = 4096;
	private static ResourceBundle resource = ResourceBundle.getBundle("ApplicationResource");
	@Autowired	SessionFactory sessionFactory;
	@Autowired HttpSession httpSession;

	@Override
	@Transactional
	public List<DocumentDto> getDocumentHistory(String leadId) {
		//public List<DocumentDto> getDocumentHistory(String leadId,String syncDate) {
		logger.info("DocumentDaoImpl || getDocumentHistory() || :-START");
		Session session =null;
		SQLQuery query=null;
		DocumentDto entity=null;
		List<DocumentDto> list=new ArrayList<DocumentDto>();
		try{
			String lastSyncDate="";
			/*if(!syncDate.equalsIgnoreCase("")){
				lastSyncDate=" AND Qdc.Updated_Date >=TO_DATE('"+syncDate+"','dd-MM-yyyy')";	
			}*/
			session=sessionFactory.openSession();
			String sql = "SELECT  QDC.ID as DOCID,QDC.DOCUMENTTYPEID as DOCUMENTTYPEID,QDC.FILE_NAME FILENAME,(SELECT QD.DISPLAYNAME FROM QC_PROSPECT_MASTER.QM_DOCUMENTTYPE QD"
					+ " WHERE  QD.ACTIVE ='A'  /*  AND    QD.COMPANY_ID       = 1*/ "
					+ " AND QD.DOCUMENTTYPEID   = QDC.DOCUMENTTYPEID)  DOCUMENT_TYPE,QDC.DOCUMENTID,"
					+ " (SELECT QD.DISPLAYNAME FROM   QC_PROSPECT_MASTER.QM_DOCUMENT  QD WHERE  QD.ACTIVE='A'"
					+ " /* AND    QD.COMPANY_ID          = 1*/ AND DOCUMENTID=QDC.DOCUMENTID) DOCUMENT_NAME,"
					+ " QDC.REMARKS,QDC.DOCCOLLECTIONSTATUSID,(SELECT QDS.DOCUMENTSTATUSNAME "
					+ " FROM   QC_PROSPECT_MASTER.QM_DOCUMENTSTATUS QDS  WHERE  QDS.ACTIVE='A'"
					+ " /* AND    QDS.COMPANY_ID= 1*/ AND QDS.DOCUMENTSTATUSID = QDC.DOCCOLLECTIONSTATUSID)"
					+ " STATUS,TO_CHAR(QDC.RECIEVEDDATE, 'DD-Mon-YYYY') as RECIEVEDDATE,TO_CHAR(QDC.TARGETDATE, 'DD-Mon-YYYY') as TARGETDATE,"
					+ " (SELECT  TRIM(REPLACE(QU.USERFNAME||' '||QU.USERMNAME||' '||QU.USERLNAME,'  ',' ')) "
					+ " FROM QC_PROSPECT_MASTER.QM_USER QU  WHERE USERID=QDC.CREATED_BY) CREATED_BY,"
					+ " TO_CHAR(QDC.CREATED_SYS_DATE, 'DD-Mon-YYYY') as CREATED_SYS_DATE,(SELECT  TRIM(REPLACE(QU.USERFNAME||' '||QU.USERMNAME||' '||QU.USERLNAME,'  ',' '))"
					+ " FROM QC_PROSPECT_MASTER.QM_USER QU WHERE USERID=QDC.UPDATED_BY) UPDATED_BY,"
					+ " TO_CHAR(QDC.UPDATED_SYS_DATE, 'DD-Mon-YYYY') as UPDATED_SYS_DATE FROM QC_PROSPECT.QT_DOCUMENTCOLLECTION QDC"
					+ " WHERE QDC.CASE_ID="+leadId+" AND ACTIVE ='A' "+lastSyncDate ;
			query=session.createSQLQuery(sql);
			System.out.println(sql);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List result = query.list();
			for(Object o: result){	
				Map map = (Map) o;
				entity=new DocumentDto();
				entity.setDocId(map.get("DOCID")==null?"":map.get("DOCID").toString());
				entity.setDocumentTypeId(map.get("DOCUMENTTYPEID")==null?"":map.get("DOCUMENTTYPEID").toString());
				entity.setDocumentTypeName(map.get("DOCUMENT_TYPE")==null?"":map.get("DOCUMENT_TYPE").toString());
				entity.setDocumentId(map.get("DOCUMENTID")==null?"":map.get("DOCUMENTID").toString());
				entity.setDocumentName(map.get("DOCUMENT_NAME")==null?"":map.get("DOCUMENT_NAME").toString());
				entity.setRemarks(map.get("REMARKS")==null?"":map.get("REMARKS").toString());
				entity.setDocStatusId(map.get("DOCCOLLECTIONSTATUSID")==null?"":map.get("DOCCOLLECTIONSTATUSID").toString());
				entity.setDocStatusName(map.get("STATUS")==null?"":map.get("STATUS").toString());
				entity.setReceivingDate(map.get("RECIEVEDDATE")==null?"":map.get("RECIEVEDDATE").toString());
				entity.setTargetDate(map.get("TARGETDATE")==null?"":map.get("TARGETDATE").toString());
				entity.setDocCreatedByName(map.get("CREATED_BY")==null?"":map.get("CREATED_BY").toString());
				entity.setCreatedDate(map.get("CREATED_SYS_DATE")==null?"":map.get("CREATED_SYS_DATE").toString());
				entity.setDocUpdatedByName(map.get("UPDATED_BY")==null?"":map.get("UPDATED_BY").toString());
				entity.setUpdatedDate(map.get("UPDATED_SYS_DATE")==null?"":map.get("UPDATED_SYS_DATE").toString());
				entity.setDocumentName(map.get("DOCUMENT_NAME")==null?"":map.get("DOCUMENT_NAME").toString());
				entity.setFileName(map.get("FILENAME")==null?"":map.get("FILENAME").toString());
				list.add(entity);
			}
		}catch(Exception e){
			logger.error("DocumentDaoImpl || getDocumentHistory() || :-ERROR:::"+e.getMessage());
		}finally {
			if(session!=null)
				session.close();
		}
		logger.info("DocumentDaoImpl || getDocumentHistory() || :-END");
		return list;
	}

	@Transactional
	public List<DocumentDto> getDocumentName(String docType) {
		logger.info("DocumentDaoImpl || getDocumentName() || :-START");
		UserEntity userEntity = (UserEntity) httpSession.getAttribute("UserDetails");
		DocumentDto entity=null;
		List<DocumentDto> list=new ArrayList<DocumentDto>();
		Session session=null;
		SQLQuery query=null;
		try{
			session=sessionFactory.openSession();
			String sql = "SELECT DOCUMENTID, DISPLAYNAME FROM   QC_PROSPECT_MASTER.QM_DOCUMENT  QD"
					+ " WHERE  QD.ACTIVE  ='A' AND  QD.DOCUMENTTYPEID = "
					+ docType+" AND  QD.COMPANY_ID  = "+userEntity.getCompanyId()+" ORDER BY DISPLAYNAME";
			query=session.createSQLQuery(sql);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List result=query.list();
			for(Object o:result){
				Map map=(Map) o;
				entity=new DocumentDto();
				entity.setDocumentId(map.get("DOCUMENTID")!=null?map.get("DOCUMENTID").toString():"");
				entity.setDocumentName(map.get("DISPLAYNAME")!=null?map.get("DISPLAYNAME").toString():"");						
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

	@Transactional
	public List<DocumentDto> saveUpdateDocument(List<DocumentDto> list,int caseId) {
		logger.info("DocumentDaoImpl || saveUpdateDocument() || :-START");
		String status = "";
		String action="";
		String error = "";
		String message = "";
		String P_ID=null;
		Connection con = null;
		ResultSet rs = null;
		CallableStatement cstmt = null;
		Statement stmt = null;
		//DocumentDto entity=null;
		//UserEntity userEntity = (UserEntity) httpSession.getAttribute("UserDetails");
		Session session=null;
		try{
			session = sessionFactory.openSession();
			con = ConnectionFromSession.getJavaSqlConnectionFromHibernateSession(session);
			for (DocumentDto docList : list) {
				//String docIdQuery = "SELECT QC_PROSPECT.SEQ_DOCCOLLECTION.NEXTVAL DOCUMENTID FROM DUAL";
				String docIdQuery = "select MAX(ID)+1 DOCUMENTID from QC_PROSPECT.QT_DOCUMENTCOLLECTION";
				stmt = con.createStatement();
				rs = stmt.executeQuery(docIdQuery);
				String documentId = "" ;
				if(rs.next()){
					documentId = rs.getString("DOCUMENTID");
				}
				cstmt = con.prepareCall("{call QC_PROSPECT.PR_PROSP_DOC_SAVE(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
				if(docList.getDocId().equals("insert") && !(docList.getDocId().equals("")) && docList.getDocId()!= null){
					action="I";
					if(documentId==null){
						documentId="100000001";
					}
					cstmt.setInt(1,Integer.parseInt(documentId.trim()));
					docList.setDocCreatedById(""+docList.getMakerId());
					docList.setDocUpdatedById("0");
				}
				if(!(docList.getDocId().equals("insert")) && !(docList.getDocId().equals("")) && docList.getDocId()!= null){
					action="U";
					cstmt.setInt(1,Integer.parseInt(docList.getDocId().trim()));
					docList.setDocUpdatedById(""+docList.getMakerId());
					docList.setDocCreatedById("0");
				}
				if(docList.getDocumentId().equals("-1")){
					cstmt.setNull(2,java.sql.Types.INTEGER);
				}
				else
				{
					cstmt.setInt(2, Integer.parseInt(docList.getDocumentId()));
				}
				if(docList.getDocumentTypeId().equals("-1")){
					cstmt.setNull(3,java.sql.Types.INTEGER);
				}
				else{
					cstmt.setInt(3, Integer.parseInt(docList.getDocumentTypeId()));
				}
				if(docList.getDocStatusId().equals("-1"))
				{
					cstmt.setNull(4,java.sql.Types.INTEGER);
				}else{
					cstmt.setInt(4,Integer.parseInt(docList.getDocStatusId()));
				}

				if(docList.getReceivingDate()==null){
					cstmt.setNull(5,java.sql.Types.DATE);
				}
				else{
					cstmt.setString(5,docList.getReceivingDate().toString());
				}

				if(docList.getTargetDate()==null){
					cstmt.setNull(6,java.sql.Types.DATE);
				}
				else{
					cstmt.setString(6,docList.getTargetDate().toString());
				}

				if(docList.getRemarks()==null)
				{
					cstmt.setNull(7,java.sql.Types.VARCHAR);
				}
				else
				{
					cstmt.setString(7,docList.getRemarks());
				}

				cstmt.setInt(8,caseId);

				if(docList.getDocCreatedById().equals("0"))
				{
					cstmt.setNull(9,java.sql.Types.INTEGER);
				}
				else
				{
					cstmt.setInt(9,Integer.parseInt(docList.getDocCreatedById()));
				}				

				if(docList.getDocUpdatedById().equals("0"))
				{
					cstmt.setNull(10,java.sql.Types.INTEGER);
				}
				else
				{
					cstmt.setInt(10,Integer.parseInt(docList.getDocUpdatedById()));
				}
				cstmt.setString(11,action);
				String recdFileName = docList.getDocumentBlob().getOriginalFilename();
				String PID ="";
				if(recdFileName != null && !(recdFileName.trim().equals(""))){
					String[] fileExt=recdFileName.split("\\.");					
					PID = documentId == null ? "1111111111" : documentId;					
					String filePath=resource.getString("DOCPATH");
					String destination = filePath + PID +"."+ fileExt[1];
					InputStream initialStream=docList.getDocumentBlob().getInputStream();					
					FileOutputStream fos=new FileOutputStream(destination);
					byte[] buffer = new byte[initialStream.available()];					
					initialStream.read(buffer);
					fos.write(buffer);
					String fileName=PID+"."+fileExt[1];
					cstmt.setString(12,destination);
					cstmt.setString(13,fileName);
				}
				else
				{
					cstmt.setNull(12,java.sql.Types.VARCHAR);
					cstmt.setNull(13,java.sql.Types.VARCHAR);
				}

				cstmt.registerOutParameter(14,java.sql.Types.CLOB);
				cstmt.registerOutParameter(15,java.sql.Types.CLOB);
				cstmt.registerOutParameter(1,java.sql.Types.INTEGER);
				cstmt.executeUpdate();
				error = cstmt.getString(14);
				message = cstmt.getString(15);
				P_ID=cstmt.getInt(1)+"";
				docList.setPID(P_ID);
				if(error.equalsIgnoreCase("S")){
					status=error;
					error="";
				}
				status ="{\"status\":\""+status+"\",\"message\":\""+ message+"\",\"error\":\""+error+"\",\"docId\":\""+PID+"\"}";
				list.get(0).setStatus(status);
			}
		}  catch(Exception e){
			logger.error("DocumentDaoImpl || saveUpdateDocument() || :-ERROR:::"+e.getMessage());
		}finally{
			try{
				if(session!=null){
					session.close();
				}
				if(rs!=null){
					rs.close();
					rs=null;
				}
				if(cstmt!=null){
					cstmt.close();
					cstmt=null;
				}
				if(con!=null){
					con.close();
					con=null;
				}
			}catch(Exception e){
				e.printStackTrace();
			}	
		}
		logger.info("DocumentDaoImpl || saveUpdateDocument() || :-END");
		return list;
	}

	@Transactional
	public String deleteDocument(List<DocumentDto> list,int caseId){
		logger.info("DocumentDaoImpl || deleteDocument() || :-START");
		String status="";
		String message="";
		int x=0;
		Session session=null;
		SQLQuery query=null;
		try{
			session=sessionFactory.openSession();
			for(DocumentDto docList:list){
				String sql="UPDATE QC_PROSPECT.QT_DOCUMENTCOLLECTION SET ACTIVE = 'I' WHERE CASE_ID = "+caseId+"	AND ID = "+docList.getDocId();
				query=session.createSQLQuery(sql);
				x = query.executeUpdate();
				if(x>0){
					message="deleted successfully";
				}else{
					message="error occured for "+docList.getDocId();
				}
				status=status+" status::"+message;
				query=null;
			}
		}catch(Exception e){
			logger.error("DocumentDaoImpl || deleteDocument() || :-ERROR::::"+e.getMessage());
		}finally {
			if(session!=null)
				session.close();
		}
		logger.info("DocumentDaoImpl || deleteDocument() || :-END");
		return status;	
	}

	@Transactional
	public String deleteDocumentBlob(List<DocumentDto> list){
		logger.info("DocumentDaoImpl || deleteDocumentBlob() || :-START");
		Session session=null;
		SQLQuery query=null;
		String status="";
		String message="";
		int x=0;
		try{		
			session=sessionFactory.openSession();
			for(DocumentDto docList:list)
			{
				String sql="UPDATE QC_PROSPECT.QT_FILE_OBJECT FO SET FO.ACTIVE = 'I' " +
						"WHERE FO.OBJECT_ID = "+docList.getDocId()+" AND FO.OBJECT_TYPE = "+docList.getDocumentTypeId();
				query=session.createSQLQuery(sql);
				x=query.executeUpdate();
				if(x>0){
					message="deleted successfully";
				}else{
					message="error occured for "+docList.getDocId();
				}
				status=status+" status::"+message;
				query=null;
			}
		}catch(Exception e){
			logger.error("DocumentDaoImpl || deleteDocumentBlob() || :-ERROR:::"+e.getMessage());
			e.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
		logger.info("DocumentDaoImpl || deleteDocumentBlob() || :-END");
		return status;	
	}

	@Transactional
	public List<DocumentEntity> getDocumentBlob(String docId,String docTypeId){
		logger.info("DocumentDaoImpl || getDocumentBlob() || :-START");
		Session session=null;
		SQLQuery query=null;
		//String status="";
		docTypeId=docTypeId==null?"":docTypeId;
		//FileOutputStream fileOuputStream=null;
		DocumentEntity entity=null;
		List<DocumentEntity> list=new ArrayList<DocumentEntity>();
		try{
			session=sessionFactory.openSession();
			String sql = "select FILE_PATH FILEPATH, FILE_NAME FILENAME from QC_PROSPECT.QT_DOCUMENTCOLLECTION where id = "+docId;
			query=session.createSQLQuery(sql);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List result =query.list();
			if(result.size()>0){
				Map map=(Map) result.get(0);
				entity=new DocumentEntity();
				entity.setDocumentName(map.get("FILENAME").toString());
				entity.setDocumentCode(map.get("FILEPATH").toString());
				list.add(entity);
			}
		}  catch(Exception e){
			logger.error("DocumentDaoImpl || getDocumentBlob() || :-ERROR::::"+e.getMessage());
		}finally {
			if(session!=null)
				session.close();
		}
		logger.info("DocumentDaoImpl || getDocumentBlob() || :-END");
		return list;
	}

}