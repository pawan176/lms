package com.qc.starter.dao.daoImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
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

import com.qc.starter.basic.CommonUtils;
import com.qc.starter.dao.MasterDao;
import com.qc.starter.dto.MasterDto;
import com.qc.starter.entity.AddressTypeEntity;
import com.qc.starter.entity.BankEntity;
import com.qc.starter.entity.BranchEntity;
import com.qc.starter.entity.CampaignEntity;
import com.qc.starter.entity.CaseActionEntity;
import com.qc.starter.entity.CityEntity;
import com.qc.starter.entity.ClusterEntity;
import com.qc.starter.entity.CompanyEntity;
import com.qc.starter.entity.CompanyTypeEntity;
import com.qc.starter.entity.ContactEntity;
import com.qc.starter.entity.ContactTypeEntity;
import com.qc.starter.entity.CustomerCategryEntity;
import com.qc.starter.entity.DevloperEntity;
import com.qc.starter.entity.DocumentEntity;
import com.qc.starter.entity.EntityTypeEntity;
import com.qc.starter.entity.GenderEntity;
import com.qc.starter.entity.IndustryEntity;
import com.qc.starter.entity.MaritalStatusEntity;
import com.qc.starter.entity.NationalityEntity;
import com.qc.starter.entity.NextActionEntity;
import com.qc.starter.entity.OccupancyStatusEntity;
import com.qc.starter.entity.OccupationEntity;
import com.qc.starter.entity.PinCodeEntity;
import com.qc.starter.entity.ProductMasterEntity;
import com.qc.starter.entity.PropertyStatusEntity;
import com.qc.starter.entity.PropertyTypeEntity;
import com.qc.starter.entity.PurposeEntity;
import com.qc.starter.entity.PurposeOfLoan;
import com.qc.starter.entity.RejectReasonEntity;
import com.qc.starter.entity.SalaryModeEntity;
import com.qc.starter.entity.SectorEntity;
import com.qc.starter.entity.SourceEntity;
import com.qc.starter.entity.StageEntity;
import com.qc.starter.entity.StateMasterEntity;
import com.qc.starter.entity.SubQueueEntity;
import com.qc.starter.entity.TitleEntity;
import com.qc.starter.entity.TypeOfBusinessEntity;
import com.qc.starter.entity.UserEntity;

@Repository
public class MasterDaoImpl implements MasterDao {
	private static final Logger logger = Logger.getLogger(MasterDaoImpl.class.getName());
	@Autowired
	HttpSession httpSession;
	@Autowired
	SessionFactory sessionFactory;

	@Transactional
	public List<List<Object>> getMasters() {
		return null;
	}

	@Transactional
	public MasterDto getMasters(String value) {
		logger.info("MasterDaoImpl | getMasters() | :- START with Request param:::value:::"+value);
		Session session = sessionFactory.openSession();
		String strQuery=null;
		UserEntity userEntity = new UserEntity();
		MasterDto masterDto = new MasterDto();
		String[] list = value.split("~");
		try {
			for (String val : list) {
				String rest = null;
				String first = null;
				if (val.contains("-") && val.contains("$")) {
					String[] newVal = val.split("-");
					val = newVal[0];
					rest = newVal[1];
					if (rest.contains("$")) {
						newVal = rest.split("$");
						rest = newVal[0];
						first = newVal[1];
					}
				} else if (val.contains("-") && !val.contains("$")) {
					String[] newVal = val.split("-");
					val = newVal[0];
					rest = newVal[1];
				} else if (val.contains("$") && !val.contains("-")) {
					String[] newVal = val.split("$");
					val = newVal[0];
					first = newVal[1];
				}
				switch (entityList.valueOf(val)) {
				case AddressTypeEntity:
					logger.info("MasterDaoImpl | AddressTypeEntity START | :-  ");
					if (rest != null){
						strQuery="Select * from QC_PROSPECT_MASTER.QM_ADDRESSTYPE where COMPANY_ID="+userEntity.getCompanyId()+" AND ACTIVE='A'"+ rest;	
					}else{
						strQuery="Select * from QC_PROSPECT_MASTER.QM_ADDRESSTYPE where COMPANY_ID="+userEntity.getCompanyId()+" AND ACTIVE='A'";
					}
					try{
						List<AddressTypeEntity> addressTypeList=new ArrayList<AddressTypeEntity>();
						SQLQuery query=session.createSQLQuery(strQuery);
						query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
						List result = query.list();	
						if(result != null && result.size() > 0){
							for(Object o : result){				
								Map add = (Map) o;
								AddressTypeEntity addressEntity=new AddressTypeEntity();
								addressEntity.setAddressTypeId(add.get("ADDRESSTYPEID")!=null?add.get("ADDRESSTYPEID").toString():"");
								addressEntity.setAddressTypeCode(add.get("ADDRESSTYPECODE").toString());
								addressEntity.setAddressTypeName(add.get("ADDRESSTYPENAME").toString());
								addressEntity.setAddressTypeDisplayName(add.get("ADDRESSTYPEDISPLAYNAME")!=null?add.get("ADDRESSTYPEDISPLAYNAME").toString():"");
								addressTypeList.add(addressEntity);
								addressEntity=null;
							}
						}
						masterDto.setAddressType(addressTypeList);
						addressTypeList=null;
						logger.info("MasterDaoImpl | AddressTypeEntity END | ");
					}
					catch(Exception e){
						e.printStackTrace();
						logger.error("MasterDaoImpl | AddressTypeEntity  | :- error " + e.getMessage() + "| :-END");	

					}
					break;
				case BankEntity:
					logger.info("MasterDaoImpl | BankEntity START| :-  ");
					if (rest != null){
						strQuery="SELECT BANK_ID,BANK_CODE,BANK_NAME,BANK_TYPE,COMPANY_ID,BANKIDNUM FROM QC_PROSPECT_MASTER.QM_BANK WHERE ACTIVE='A' AND "+rest;
					}
					else{
						strQuery="SELECT BANK_ID,BANK_CODE,BANK_NAME,BANK_TYPE,COMPANY_ID,BANKIDNUM FROM QC_PROSPECT_MASTER.QM_BANK WHERE ACTIVE='A'";
					}
					try{
						List<BankEntity> bankMaster=new ArrayList<BankEntity>();
						SQLQuery query=session.createSQLQuery(strQuery);
						query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
						List result = query.list();	
						if(result != null && result.size() > 0){
							for(Object o : result){				
								Map rt = (Map) o;
								BankEntity bankEntity=new BankEntity();
								bankEntity.setBankId(rt.get("BANK_ID")!=null?rt.get("BANK_ID").toString():"");
								bankEntity.setBankCode(rt.get("BANK_CODE")!=null?rt.get("BANK_CODE").toString():"");
								bankEntity.setBankName(rt.get("BANK_NAME")!=null?rt.get("BANK_NAME").toString():"");
								bankMaster.add(bankEntity);
								bankEntity=null;
							}
						}
						masterDto.setBankMaster(bankMaster);   
						bankMaster=null;
						logger.info("MasterDaoImpl | BankEntity END|  ");
					}catch(Exception e){
						e.printStackTrace();
						logger.error("MasterDaoImpl | BankEntity | :- error " + e.getMessage() + "| :-END");
					}
					break;
				case CampaignEntity:
					logger.info("MasterDaoImpl | CampaignEntity START | ");
					if (rest != null){
						strQuery="select CAMPAIGN_ID,CAMPAIGN_CD,CAMPAIGN_NAME from QC_PROSPECT_MASTER.QM_CAMPAIGN where COMPANY_ID="+userEntity.getCompanyId()+" AND ACTIVE='A' AND "+rest;
					}else{
						strQuery="select CAMPAIGN_ID,CAMPAIGN_CD,CAMPAIGN_NAME from QC_PROSPECT_MASTER.QM_CAMPAIGN where COMPANY_ID="+userEntity.getCompanyId()+" AND ACTIVE='A'";
					}
					try{
						List<CampaignEntity> campaignMaster=new ArrayList<CampaignEntity>();
						SQLQuery query=session.createSQLQuery(strQuery);
						query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
						List result = query.list();	
						if(result != null && result.size() > 0){
							for(Object o : result){				
								Map rt = (Map) o;
								CampaignEntity campaignEntity=new CampaignEntity();
								campaignEntity.setCampaignId(rt.get("CAMPAIGN_ID").toString());
								campaignEntity.setCampaignCd(rt.get("CAMPAIGN_CD").toString());
								campaignEntity.setCampaignName(rt.get("CAMPAIGN_NAME").toString());
								campaignMaster.add(campaignEntity);
								campaignEntity=null;
							}
						}
						masterDto.setCampaignMaster(campaignMaster);
						campaignMaster=null;
						logger.info("MasterDaoImpl | CampaignEntity end | ");
					}catch(Exception e){
						e.printStackTrace();
						logger.error("MasterDaoImpl | CampaignEntity | :- error " + e.getMessage() + "| :-END");
					}
					break;
				case CaseActionEntity:
					logger.info("MasterDaoImpl | CaseActionEntity START | ");
					try{
						if (rest != null){
							strQuery="select * from QC_PROSPECT_MASTER.QM_CASE_ACTION where company_id="+userEntity.getCompanyId()+" AND ACTIVE='A' and "+rest;
						}
						else{
							strQuery="select * from QC_PROSPECT_MASTER.QM_CASE_ACTION where company_id="+userEntity.getCompanyId()+" " +
									"and ACTION_TYPE in (select ACTION_TYPE_ID from QC_PROSPECT_MASTER.QM_CASE_ACTION_TYPE" +
									" WHERE ACTION_TYPE_NAME ='DISPOSITION' AND COMPANY_ID ="+userEntity.getCompanyId()+" )" +
									" AND ACTION_NAME <>'NEW LEAD' AND ACTIVE='A' ORDER BY ACTION_NAME";
						}
						List<CaseActionEntity> caseActionMaster=new ArrayList<CaseActionEntity>();
						SQLQuery query=session.createSQLQuery(strQuery);
						query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
						List result = query.list();	
						if(result != null && result.size() > 0){
							for(Object o : result){				
								Map rt = (Map) o;
								CaseActionEntity actionEntity=new CaseActionEntity();
								actionEntity.setActionId(rt.get("ACTION_ID").toString());
								actionEntity.setActionCode(rt.get("ACTION_CODE").toString());
								actionEntity.setActionName(rt.get("ACTION_NAME").toString());
								caseActionMaster.add(actionEntity);
								actionEntity=null;
							}
						}
						masterDto.setCaseActionMaster(caseActionMaster);
						caseActionMaster=null;
						logger.info("MasterDaoImpl | CaseActionEntity END | ");
					}catch(Exception e){
						e.printStackTrace();
						logger.error("MasterDaoImpl | CaseActionEntity | :- error " + e.getMessage() + "| :-END");
					}
					break;
				case CaseContactEntity:
					logger.info("MasterDaoImpl | CaseContactEntity START | ");
					if (rest != null){
						strQuery="select CONTACT_TYPE_ID,CONTACT_TYPE_CODE,CONTACT_TYPE from QC_PROSPECT_MASTER.QM_CASE_CONTACT_TYPE where COMPANY_ID="+userEntity.getCompanyId()+" AND ACTIVE='A' AND "+rest;	
					}else{
						strQuery="select CONTACT_TYPE_ID,CONTACT_TYPE_CODE,CONTACT_TYPE from QC_PROSPECT_MASTER.QM_CASE_CONTACT_TYPE where COMPANY_ID="+userEntity.getCompanyId()+" AND ACTIVE='A'";	
					}
					try{
						List<ContactEntity> contact=new ArrayList<ContactEntity>();
						SQLQuery query=session.createSQLQuery(strQuery);
						query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
						List result = query.list();	
						if(result != null && result.size() > 0){
							for(Object o : result){				
								Map rt = (Map) o;
								ContactEntity contactEntity=new ContactEntity();	
								contactEntity.setContactTypeId(Integer.parseInt(rt.get("CONTACT_TYPE_ID").toString()));
								contactEntity.setContactTypeCode(rt.get("CONTACT_TYPE_CODE").toString());
								contactEntity.setContactType(rt.get("CONTACT_TYPE").toString());
								contact.add(contactEntity);
								contactEntity=null;
							}
						}
						masterDto.setContact(contact);
						contact=null;
						logger.info("MasterDaoImpl | CaseContactEntity END | ");
					}catch(Exception e){
						e.printStackTrace();
						logger.error("MasterDaoImpl | CaseContactEntity | :- error " + e.getMessage() + "| :-END");
					}
					break;
				case CityEntity:
					logger.info("MasterDaoImpl | CityEntity START | ");
					if (rest != null){
						strQuery="select CITYMASTERID,CITYMASTERCODE,CITYMASTERNAME,DISPLAYNAME,STATEID from QC_PROSPECT_MASTER.QM_CITYMASTER where ACTIVE='A' and "+rest;
					}else{
						strQuery="select CITYMASTERID,CITYMASTERCODE,CITYMASTERNAME,DISPLAYNAME,STATEID from QC_PROSPECT_MASTER.QM_CITYMASTER WHERE ACTIVE='A'";
					}
					try{
						List<CityEntity> city=new ArrayList<CityEntity>();
						SQLQuery query=session.createSQLQuery(strQuery);
						query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
						List result = query.list();	
						if(result != null && result.size() > 0){
							for(Object o : result){				
								Map rt = (Map) o;
								CityEntity cityEntity=new CityEntity();
								cityEntity.setCityMasterId(rt.get("CITYMASTERID").toString());
								cityEntity.setCityMasterCode(rt.get("CITYMASTERCODE").toString());
								cityEntity.setCityMasterName(rt.get("CITYMASTERNAME").toString());
								cityEntity.setDisplayName(rt.get("DISPLAYNAME")+"");
								cityEntity.setStateId(rt.get("STATEID")!=null?rt.get("STATEID").toString():"");
								city.add(cityEntity);
								cityEntity=null;
							}
						}
						masterDto.setCity(city);
						logger.info("MasterDaoImpl | CityEntity END | ");
						city=null;
					}catch(Exception e){
						e.printStackTrace();
						logger.error("MasterDaoImpl | CityEntity | :- error " + e.getMessage() + "| :-END");
					}
					break;
				case CompanyEntity:
					logger.info("MasterDaoImpl | CompanyEntity START | ");
					if (rest != null){
						strQuery="select * from QC_PROSPECT_MASTER.QM_CASE_COMPANY where "+rest;
					}else{
						strQuery="select * from QC_PROSPECT_MASTER.QM_CASE_COMPANY WHERE ACTIVE='A'";
					}
					try{
						List<CompanyEntity> companyName=new ArrayList<CompanyEntity>();
						SQLQuery query=session.createSQLQuery(strQuery);
						query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
						List result = query.list();	
						if(result != null && result.size() > 0){
							for(Object o : result){				
								Map rt = (Map) o;
								CompanyEntity companyEntity=new CompanyEntity();
								companyEntity.setCaseCompanyId(rt.get("CASE_COMPANY_ID")+"");
								companyEntity.setCompanyCode(rt.get("COMPANY_CODE")+""); 
								companyEntity.setCompanyName(rt.get("COMPANY_NAME")+"");  
								companyEntity.setDisplayName(rt.get("DISPLAY_NAME")!=null?rt.get("DISPLAY_NAME").toString():"");
								companyName.add(companyEntity);
								companyEntity=null;
							}
						}
						masterDto.setCompanyName(companyName); 
						companyName=null;
						logger.info("MasterDaoImpl | CompanyEntity END | ");
					}catch(Exception e){
						e.printStackTrace();
						logger.error("MasterDaoImpl | CompanyEntity | :- error " + e.getMessage() + "| :-END");
					}
					break;
				case CompanyTypeEntity:
					logger.info("MasterDaoImpl | CompanyTypeEntity START | ");
					if (rest != null){
						strQuery="select * from QC_PROSPECT_MASTER.QM_COMPANY_TYPE where COMPANY_ID="+userEntity.getCompanyId()+" AND ACTIVE='A' AND "+rest;
					}else{
						strQuery="select * from QC_PROSPECT_MASTER.QM_COMPANY_TYPE WHERE COMPANY_ID="+userEntity.getCompanyId()+" AND ACTIVE='A'";
					}
					try{
						List<CompanyTypeEntity> companyType=new ArrayList<CompanyTypeEntity>();
						SQLQuery query=session.createSQLQuery(strQuery);
						query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
						List result = query.list();	
						if(result != null && result.size() > 0){
							for(Object o : result){				
								Map rt = (Map) o;
								CompanyTypeEntity companyTypeEntity=new CompanyTypeEntity();
								companyTypeEntity.setCompTypeId(rt.get("COMP_TYPE_ID")!=null?rt.get("COMP_TYPE_ID").toString():""); 
								companyTypeEntity.setCompanyTypeCode(rt.get("COMPANY_TYPE_CODE")!=null?rt.get("COMPANY_TYPE_CODE").toString():""); 
								companyTypeEntity.setCompanyType(rt.get("COMPANY_TYPE")!=null?rt.get("COMPANY_TYPE").toString():""); 
								companyTypeEntity.setCompanyId(rt.get("COMPANY_ID")!=null?rt.get("COMPANY_ID").toString():""); 
								companyType.add(companyTypeEntity);
								companyTypeEntity=null;
							}
						}
						masterDto.setCompanyType(companyType);
						companyType=null;
						logger.info("MasterDaoImpl | CompanyTypeEntity END | ");
					}catch(Exception e){
						e.printStackTrace();
						logger.error("MasterDaoImpl | CompanyTypeEntity | :- error " + e.getMessage() + "| :-END");
					}
					break;
				case ContactTypeEntity:
					logger.info("MasterDaoImpl | ContactTypeEntity START | ");
					if (rest != null){
						strQuery="SELECT * from QC_PROSPECT_MASTER.QM_CONTACT_TYPE where COMPANY_ID="+userEntity.getCompanyId()+" AND "+rest;
					}else{
						strQuery="SELECT * from QC_PROSPECT_MASTER.QM_CONTACT_TYPE where COMPANY_ID="+userEntity.getCompanyId()+" AND ACTIVE='A'";
					}
					try{
						List<ContactTypeEntity> contactTypeEmail=new ArrayList<ContactTypeEntity>();
						SQLQuery query=session.createSQLQuery(strQuery);
						query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
						List result = query.list();	
						if(result != null && result.size() > 0){
							for(Object o : result){				
								Map rt = (Map) o;
								ContactTypeEntity contactTypeEntity=new ContactTypeEntity();
								contactTypeEntity.setContactTypeId(Integer.parseInt(rt.get("CONTACT_TYPE_ID").toString()));  
								contactTypeEntity.setContactTypeCode(rt.get("CONTACT_TYPE_CODE").toString()); 
								contactTypeEntity.setContactTypeName(rt.get("CONTACT_TYPE_NAME").toString()); 
								contactTypeEntity.setContactCategory(rt.get("CONTACT_CATEGORY")!=null?rt.get("CONTACT_CATEGORY").toString():""); 
								contactTypeEmail.add(contactTypeEntity);
								contactTypeEntity=null;
							}
						}
						if(rest.equalsIgnoreCase("CONTACT_CATEGORY='EMAIL'")){
							masterDto.setContactTypeEmail(contactTypeEmail);
							contactTypeEmail=null;
						}else{
							masterDto.setContactTypeMobile(contactTypeEmail);
							contactTypeEmail=null;
						}
						logger.info("MasterDaoImpl | ContactTypeEntity END | ");
					}catch(Exception e){
						e.printStackTrace();
						logger.error("MasterDaoImpl | ContactTypeEntity | :- error " + e.getMessage() + "| :-END");
					}
					break;

				case GenderEntity:

					if (rest != null){
						strQuery="select * from QC_PROSPECT_MASTER.QM_GENDER where "+rest;
					}else{
						strQuery="select * from QC_PROSPECT_MASTER.QM_GENDER WHERE ACTIVE='A'";
					}
					try{
						List<GenderEntity> gender=new ArrayList<GenderEntity>();
						SQLQuery query=session.createSQLQuery(strQuery);
						query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
						List result = query.list();	
						if(result != null && result.size() > 0){
							for(Object o : result){				
								Map rt = (Map) o;
								GenderEntity genderEntity=new GenderEntity();
								genderEntity.setGenderId(rt.get("GENDERID").toString()) ;
								genderEntity.setGenderCode(rt.get("GENDERCODE").toString()) ;
								genderEntity.setGenderName(rt.get("GENDERNAME").toString()) ;
								genderEntity.setDisplayName(rt.get("DISPLAYNAME")!=null?rt.get("DISPLAYNAME").toString():"") ;
								genderEntity.setDescription(rt.get("DESCRIPTION")!=null?rt.get("DESCRIPTION").toString():"") ;
								gender.add(genderEntity);
								genderEntity=null;
							}
						}
						masterDto.setGender(gender);
						gender=null;
					}catch(Exception e){
						e.printStackTrace();
						logger.error("MasterDaoImpl | GenderEntity | :- error " + e.getMessage() + "| :-END");
					}
					break;
					
					
				case TitleEntity:

					if (rest != null){
						strQuery="select * from QC_PROSPECT_MASTER.QM_TITLE where "+rest;
					}else{
						strQuery="select * from QC_PROSPECT_MASTER.QM_TITLE WHERE ACTIVE='A'";
					}
					try{
						List<TitleEntity> title=new ArrayList<TitleEntity>();
						SQLQuery query=session.createSQLQuery(strQuery);
						query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
						List result = query.list();	
						if(result != null && result.size() > 0){
							for(Object o : result){				
								Map rt = (Map) o;
								TitleEntity titleEntity=new TitleEntity();
								titleEntity.setTitleId(CommonUtils.toString(rt.get("TITLEID"))) ;
								titleEntity.setTitleCode(CommonUtils.toString(rt.get("TITLECODE"))) ;
								titleEntity.setTitleName(CommonUtils.toString(rt.get("TITLENAME"))) ;
								titleEntity.setDisplayName(CommonUtils.toString(rt.get("DISPLAYNAME"))) ;
								titleEntity.setDescription(CommonUtils.toString(rt.get("DESCRIPTION"))) ;
								titleEntity.setGenderid(CommonUtils.toString(rt.get("GENDER_ID"))) ;							
								title.add(titleEntity);
								titleEntity=null;
							}
						}
						masterDto.setTitleMaster(title);
						title=null;
					}catch(Exception e){
						e.printStackTrace();
						logger.error("MasterDaoImpl | GenderEntity | :- error " + e.getMessage() + "| :-END");
					}
					break;
				case MaritalStatusEntity:
					if (rest != null){
						strQuery="select * from QC_PROSPECT_MASTER.QM_MARITALSTATUS where ACTIVE='A' AND "+rest;
					}else{
						strQuery="select * from QC_PROSPECT_MASTER.QM_MARITALSTATUS where ACTIVE='A'";
					}
					try{
						List<MaritalStatusEntity> maritalStatus=new ArrayList<MaritalStatusEntity>();
						SQLQuery query=session.createSQLQuery(strQuery);
						query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
						List result = query.list();	
						if(result != null && result.size() > 0){
							for(Object o : result){				
								Map rt = (Map) o;
								MaritalStatusEntity maritalStatusEntity=new MaritalStatusEntity();
								maritalStatusEntity.setMaritalStatusid(rt.get("MARITALSTATUSID").toString()) ;
								maritalStatusEntity.setMaritalStatuscode(rt.get("MARITALSTATUSCODE").toString()) ;
								maritalStatusEntity.setMaritalStatusname(rt.get("MARITALSTATUSNAME")!=null?rt.get("MARITALSTATUSNAME").toString():"") ;
								maritalStatusEntity.setDisplayName(rt.get("DISPLAYNAME")!=null?rt.get("DISPLAYNAME").toString():"") ;
								maritalStatus.add(maritalStatusEntity);
								maritalStatusEntity=null;
							}
						}
						masterDto.setMaritalStatus(maritalStatus);
						maritalStatus=null;
					}catch(Exception e){
						e.printStackTrace();
						logger.error("MasterDaoImpl | MaritalStatusEntity | :- error " + e.getMessage() + "| :-END");
					}
					break;

				case NationalityEntity:
					if (rest != null){
						strQuery="select * from QC_PROSPECT_MASTER.QM_NATIONALITY where COMPANY_ID="+userEntity.getCompanyId()+" AND ACTIVE='A' and "+rest;
					}else{ 
						strQuery="select * from QC_PROSPECT_MASTER.QM_NATIONALITY where COMPANY_ID="+userEntity.getCompanyId()+" and ACTIVE='A'";
					}
					try{
						List<NationalityEntity> nationality=new ArrayList<NationalityEntity>();
						SQLQuery query=session.createSQLQuery(strQuery);
						query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
						List result = query.list();	
						if(result != null && result.size() > 0){
							for(Object o : result){				
								Map rt = (Map) o;
								NationalityEntity nationalityEntity=new NationalityEntity();
								nationalityEntity.setNationalityId(rt.get("NATIONALITY_ID").toString()); 
								nationalityEntity.setNationalityCode(rt.get("NATIONALITY_CODE").toString()) ;
								nationalityEntity.setNationName(rt.get("NATION_NAME").toString()); 
								nationality.add(nationalityEntity);
								nationalityEntity=null;
							}
						}
						masterDto.setNationality(nationality);
						nationality=null;
					}catch(Exception e){
						e.printStackTrace();
						logger.error("MasterDaoImpl | NationalityEntity | :- error " + e.getMessage() + "| :-END");
					}
					break;

				case OccupancyStatusEntity:
					logger.info("MasterDaoImpl | OccupancyStatusEntity START | ");
					if (rest != null)
					{
						strQuery="select * from QC_PROSPECT_MASTER.QM_RESITYPE where COMPANY_ID="+userEntity.getCompanyId()+" AND ACTIVE='A' and "+rest;
					}else{
						strQuery="select * from QC_PROSPECT_MASTER.QM_RESITYPE where COMPANY_ID="+userEntity.getCompanyId()+" and ACTIVE='A'";
					}
					try{
						List<OccupancyStatusEntity> occupancyStatus=new ArrayList<OccupancyStatusEntity>();
						SQLQuery query=session.createSQLQuery(strQuery);
						query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
						List result = query.list();	
						if(result != null && result.size() > 0){
							for(Object o : result){				
								Map rt = (Map) o;
								OccupancyStatusEntity occupancyStatusEntity=new OccupancyStatusEntity();
								occupancyStatusEntity.setOccupancyStId(rt.get("RESITYPEID").toString());
								occupancyStatusEntity.setOccupancyStCode(rt.get("RESITYPECODE").toString());
								occupancyStatusEntity.setOccupancyStName(rt.get("DISPLAYNAME").toString());
								occupancyStatus.add(occupancyStatusEntity);
								occupancyStatusEntity=null;
							}
						}
						masterDto.setOccupancyStatus(occupancyStatus);
						occupancyStatus=null;
						logger.info("MasterDaoImpl | OccupancyStatusEntity END | ");
					}catch(Exception e){
						e.printStackTrace();
						logger.error("MasterDaoImpl | OccupancyStatusEntity | :- error " + e.getMessage() + "| :-END");
					}
					break;

				case OccupationEntity:
					logger.info("MasterDaoImpl | OccupationEntity START | ");
					if (rest != null)
					{
						strQuery="select * from QC_MASTER.QM_CONSTITUTION where ACTIVE='A' and "+rest;
					}else{
						strQuery="select * from QC_MASTER.QM_CONSTITUTION where ACTIVE='A'";
					}
					try{
						List<OccupationEntity> occupationType=new ArrayList<OccupationEntity>();
						SQLQuery query=session.createSQLQuery(strQuery);
						query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
						List result = query.list();	
						if(result != null && result.size() > 0){
							for(Object o : result){				
								Map rt = (Map) o;
								OccupationEntity occupationEntity=new OccupationEntity();
								occupationEntity.setOccupationid(Integer.parseInt(rt.get("CONSTITUTIONID").toString()));
								occupationEntity.setOccupationcode(rt.get("CONSTITUTIONCODE").toString()); 
								occupationEntity.setOccupationname(rt.get("CONSTITUTIONNAME").toString()) ;
								occupationEntity.setDisplayname(rt.get("DISPLAYNAME").toString()); 
								occupationType.add(occupationEntity);
								occupationEntity=null;
							}
						}
						masterDto.setOccupationType(occupationType);
						occupationType=null;
						logger.info("MasterDaoImpl | OccupationEntity END | ");
					}catch(Exception e){
						e.printStackTrace();
						logger.error("MasterDaoImpl | OccupationEntity | :- error " + e.getMessage() + "| :-END");
					}
					break;

				case existingFacility:
					logger.info("MasterDaoImpl | existingFacility START | ");
					if (rest != null)
					{
						strQuery="select * from QC_PROSPECT_MASTER.QM_PRODUCT  where COMPANY_ID="+userEntity.getCompanyId()+"  and ACTIVE='A'" +
								" and PRODTYPEID IN (select PRODTYPEID from QC_PROSPECT_MASTER.QM_PRODUCTTYPE where ACTIVE='A' and" +
								" COMPANY_ID ="+userEntity.getCompanyId()+ " and PRODUCTTYPE='AVAILED PRODUCT')  AND "+rest;
					}else{
						strQuery="select * from QC_PROSPECT_MASTER.QM_PRODUCT  where COMPANY_ID="+userEntity.getCompanyId()+" and ACTIVE='A'" +
								" and PRODTYPEID IN (select PRODTYPEID from QC_PROSPECT_MASTER.QM_PRODUCTTYPE where ACTIVE='A' and" +
								" COMPANY_ID ="+userEntity.getCompanyId()+ " and PRODUCTTYPE='AVAILED PRODUCT')";
					}
					try{
						List<ProductMasterEntity> existingFacility=new ArrayList<ProductMasterEntity>();
						SQLQuery query=session.createSQLQuery(strQuery);
						query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
						List result = query.list();	
						if(result != null && result.size() > 0){
							for(Object o : result){				
								Map rt = (Map) o;
								ProductMasterEntity productMasterEntity=new ProductMasterEntity();
								productMasterEntity.setProdId(rt.get("PRODUCTID").toString()); 
								productMasterEntity.setProdCode(rt.get("PRODCODE").toString()); 
								productMasterEntity.setProdName(rt.get("PRODNAME").toString()); 
								productMasterEntity.setParentProdId(rt.get("PARENTPRODID")!=null?rt.get("PARENTPRODID").toString():"");
								productMasterEntity.setProdTypeId(rt.get("PRODTYPEID")!=null?rt.get("PRODTYPEID").toString():""); 
								existingFacility.add(productMasterEntity);
								productMasterEntity=null;
							}
						}
						masterDto.setExistingFacility(existingFacility);
						existingFacility=null;
						logger.info("MasterDaoImpl | existingFacility END | ");
					}catch(Exception e){
						e.printStackTrace();
						logger.error("MasterDaoImpl | existingFacility | :- error " + e.getMessage() + "| :-END");
					}
					break;
				case ProductMasterEntity:
					logger.info("MasterDaoImpl | ProductMasterEntity START | ");
					if (rest != null)
					{
					/*	strQuery="select  PRODUCTID, CASE WHEN CP.PRODNAME ='CREDIT CARD' THEN 1 WHEN CP.PRODNAME ='PERSONAL LOAN' " +
								" THEN 2 WHEN CP.PRODNAME ='HOME LOAN' THEN 3 WHEN CP.PRODNAME ='BUSINESS LOAN' THEN 4 ELSE 5 END SEQ1," +
								" CP.PRODNAME,CP.PARENTPRODID,  PRODTYPEID from QC_PROSPECT_MASTER.QM_PRODUCT  CP where COMPANY_ID="+userEntity.getCompanyId()+ " " +
								" and ACTIVE='A' and PRODTYPEID IN (select PRODUCTTYPEID from QC_PROSPECT_MASTER.QM_PRODUCTTYPE  " +
								" where ACTIVE='A' and COMPANY_ID ="+userEntity.getCompanyId()+ " and PRODUCTTYPE='SCHEME') "+ rest;
						*/
						strQuery="select  PRODUCTID," +
								" CP.PRODNAME,CP.PARENTPRODID,  PRODTYPEID from QC_PROSPECT_MASTER.QM_PRODUCT  CP where COMPANY_ID="+userEntity.getCompanyId()+ " " +
								" and ACTIVE='A' ";
					
					}else{
						/*strQuery="select  PRODUCTID, CASE WHEN CP.PRODNAME ='CREDIT CARD' THEN 1 WHEN CP.PRODNAME ='PERSONAL LOAN' " +
								" THEN 2 WHEN CP.PRODNAME ='HOME LOAN' THEN 3 WHEN CP.PRODNAME ='BUSINESS LOAN' THEN 4 ELSE 5 END SEQ1," +
								" CP.PRODNAME,CP.PARENTPRODID,  PRODTYPEID from QC_PROSPECT_MASTER.QM_PRODUCT  CP where COMPANY_ID="+userEntity.getCompanyId()+ " " +
								" and ACTIVE='A' and PRODTYPEID IN (select PRODUCTTYPEID from QC_PROSPECT_MASTER.QM_PRODUCTTYPE  " +
								" where ACTIVE='A' and COMPANY_ID ="+userEntity.getCompanyId()+ " and PRODUCTTYPE='SCHEME') ";*/
						/*strQuery="select  PRODUCTID," +*/
								/*" PRODNAME,PARENTPRODID,PRODTYPEID,MIN_LOAN_TENURE,MAX_LOAN_TENURE,MIN_LOANTOVALUE,MAX_LOANTOVALUE from QC_PROSPECT_MASTER.QM_PRODUCT where COMPANY_ID="+userEntity.getCompanyId()+ " " +		" and ACTIVE='A' ";*/
					
						strQuery="select PRODUCTID,PRODNAME,PARENTPRODID,PRODTYPEID, MIN_LOAN_TENURE,MAX_LOAN_TENURE,MIN_LOANVALUE,MAX_LOANVALUE  from QC_PROSPECT_MASTER.QM_PRODUCT 	where COMPANY_ID="+userEntity.getCompanyId()+ " " +    "and ACTIVE = 'A' ";
						
						
						
						
						
						
					}
					strQuery=strQuery+" ORDER BY 2,3";
					try{
						List<ProductMasterEntity> productMaster=new ArrayList<ProductMasterEntity>();
						SQLQuery query=session.createSQLQuery(strQuery);
						query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
						List result = query.list();	
						if(result != null && result.size() > 0){
							for(Object o : result){				
								Map rt = (Map) o;
								ProductMasterEntity productMasterEntity=new ProductMasterEntity();
								productMasterEntity.setProdId(rt.get("PRODUCTID").toString()); 
								productMasterEntity.setProdName(rt.get("PRODNAME").toString()); 
								productMasterEntity.setParentProdId(rt.get("PARENTPRODID")!=null?rt.get("PARENTPRODID").toString():"");
								productMasterEntity.setProdTypeId(rt.get("PRODTYPEID")!=null?rt.get("PRODTYPEID").toString():"");								
								productMasterEntity.setMinLoanTenure(rt.get("MIN_LOAN_TENURE")!=null?rt.get("MIN_LOAN_TENURE").toString():"");								
								productMasterEntity.setMaxLoanTenure(rt.get("MAX_LOAN_TENURE")!=null?rt.get("MAX_LOAN_TENURE").toString():"");
								productMasterEntity.setMinLoanvalue(rt.get("MIN_LOANVALUE")!=null?rt.get("MIN_LOANVALUE").toString():"");
								productMasterEntity.setMaxLoanvalue(rt.get("MAX_LOANVALUE")!=null?rt.get("MAX_LOANVALUE").toString():"");								
								productMaster.add(productMasterEntity);
								productMasterEntity=null;
							}
						}
						masterDto.setProductMaster(productMaster);
						productMaster=null;
						logger.info("MasterDaoImpl | ProductMasterEntity END | ");
					}catch(Exception e){
						e.printStackTrace();
						logger.error("MasterDaoImpl | ProductMasterEntity | :- error " + e.getMessage() + "| :-END");
					}
					break;
				case PropertyStatusEntity:
					logger.info("MasterDaoImpl | PropertyStatusEntity START | ");
					if (rest != null)
					{
						strQuery="select * from QC_PROSPECT_MASTER.QM_PROPERTY_STATUS where ACTIVE='A' and COMPANY_ID="+userEntity.getCompanyId()+" AND "+rest;
					}else{
						strQuery="select * from QC_PROSPECT_MASTER.QM_PROPERTY_STATUS where ACTIVE='A' and COMPANY_ID="+userEntity.getCompanyId()+"";
					}
					try{
						List<PropertyStatusEntity> propStatus=new ArrayList<PropertyStatusEntity>();
						SQLQuery query=session.createSQLQuery(strQuery);
						query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
						List result = query.list();	
						if(result != null && result.size() > 0){
							for(Object o : result){				
								Map rt = (Map) o;
								PropertyStatusEntity propertyStatusEntity=new PropertyStatusEntity();
								propertyStatusEntity.setPropStatusId(Integer.parseInt(rt.get("PROP_STATUS_ID").toString())); 
								propertyStatusEntity.setPropStatusCode(rt.get("PROP_STATUS_CODE").toString()); 
								propertyStatusEntity.setPropStatusName(rt.get("PROP_STATUS_NAME").toString()); 
								propStatus.add(propertyStatusEntity);
								propertyStatusEntity=null;
							}
						}
						masterDto.setPropStatus(propStatus);
						propStatus=null;
						logger.info("MasterDaoImpl | PropertyStatusEntity END | ");
					}catch(Exception e){
						e.printStackTrace();
						logger.error("MasterDaoImpl | PropertyStatusEntity | :- error " + e.getMessage() + "| :-END");
					}
					break;

				case PropertyTypeEntity:
					logger.info("MasterDaoImpl | PropertyTypeEntity START | ");
					if (rest != null){

						strQuery="select * from QC_PROSPECT_MASTER.QM_PROPERTY_TYPE where ACTIVE='A' and COMPANY_ID="+userEntity.getCompanyId()+" AND "+rest;
					}else{
						strQuery="select * from QC_PROSPECT_MASTER.QM_PROPERTY_TYPE where ACTIVE='A' and COMPANY_ID="+userEntity.getCompanyId()+" ";
					}
					try{
						List<PropertyTypeEntity> propType=new ArrayList<PropertyTypeEntity>();
						SQLQuery query=session.createSQLQuery(strQuery);
						query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
						List result = query.list();	
						if(result != null && result.size() > 0){
							for(Object o : result){				
								Map rt = (Map) o;
								PropertyTypeEntity propertyTypeEntity=new PropertyTypeEntity();
								propertyTypeEntity.setPropTypeId(Integer.parseInt(rt.get("PROP_TYPE_ID").toString())); 
								propertyTypeEntity.setPropTypeCode(rt.get("PROP_TYPE_CODE").toString()) ;
								propertyTypeEntity.setPropTypeName(rt.get("PROP_TYPE_NAME").toString()) ;
								propType.add(propertyTypeEntity);
								propertyTypeEntity=null;
							}
						}
						masterDto.setPropType(propType);
						propType=null;
						logger.info("MasterDaoImpl | PropertyTypeEntity END | ");
					}catch(Exception e){
						e.printStackTrace();
						logger.error("MasterDaoImpl | PropertyTypeEntity | :- error :=" + e.getMessage() + "| :-END");
					}
					break;
				case SalaryModeEntity:
					logger.info("MasterDaoImpl | SalaryModeEntity START | ");
					if (rest != null && first != null)
					{
						strQuery="select * from QC_PROSPECT_MASTER.QM_SALARY_MODE where ACTIVE='A' and COMPANY_ID="+userEntity.getCompanyId()+" AND "+rest;
					}else{
						strQuery="select * from QC_PROSPECT_MASTER.QM_SALARY_MODE where ACTIVE='A' and COMPANY_ID="+userEntity.getCompanyId()+"";
					}
					try{
						List<SalaryModeEntity> salaryMode=new ArrayList<SalaryModeEntity>();
						SQLQuery query=session.createSQLQuery(strQuery);
						query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
						List result = query.list();	
						if(result != null && result.size() > 0){
							for(Object o : result){				
								Map rt = (Map) o;
								SalaryModeEntity salaryModeEntity=new SalaryModeEntity();
								salaryModeEntity.setSalaryModeId(rt.get("SALARY_MODE_ID").toString()); 
								salaryModeEntity.setSalaryModeCode(rt.get("SALARY_MODE_CODE").toString()); 
								salaryModeEntity.setSalaryMode(rt.get("SALARY_MODE").toString()); 
								salaryMode.add(salaryModeEntity);
								salaryModeEntity=null;
							}
						}
						masterDto.setSalaryMode(salaryMode);
						salaryMode=null;
						logger.info("MasterDaoImpl | SalaryModeEntity END | ");
					}catch(Exception e){
						e.printStackTrace();
						logger.error("MasterDaoImpl | SalaryModeEntity | :- error " + e.getMessage() + "| :-END");
					}
					break;

				case SourceEntity:
					logger.info("MasterDaoImpl | SourceEntity START | ");
					if(rest!=null)strQuery = "SELECT * FROM QC_PROSPECT_MASTER.QM_SOURCE WHERE ACTIVE='A' and COMPANY_ID = "+userEntity.getCompanyId()+" AND "+rest;
					else strQuery = "SELECT * FROM QC_PROSPECT_MASTER.QM_SOURCE WHERE ACTIVE='A' and COMPANY_ID = "+userEntity.getCompanyId()+" ";
					try{
						List<SourceEntity> caseActionMaster=new ArrayList<SourceEntity>();
						SQLQuery query=session.createSQLQuery(strQuery);
						query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
						List result = query.list();	
						if(result != null && result.size() > 0){
							for(Object o : result){				
								Map rt = (Map) o;
								SourceEntity entity=new SourceEntity();
								entity.setCaseSourceId(rt.get("CASE_SOURCE_ID").toString());
								entity.setSourceName(rt.get("SOURCE_NAME").toString());
								caseActionMaster.add(entity);
								entity=null;
							}
						}
						masterDto.setSourceMaster(caseActionMaster);
						logger.info("MasterDaoImpl | SourceEntity END | ");
					}catch(Exception e){
						e.printStackTrace();
						logger.error("MasterDaoImpl | SourceEntity | :- error " + e.getMessage() + "| :-END");
					}
					break;

				case StateMasterEntity:
					logger.info("MasterDaoImpl | StateMasterEntity START | ");
					if(rest!=null)strQuery = "select * from QC_PROSPECT_MASTER.QM_STATEMASTER WHERE ACTIVE='A' AND "+rest+" order by DISPLAYNAME ";
					else strQuery = "select * from QC_PROSPECT_MASTER.QM_STATEMASTER WHERE ACTIVE='A' order by DISPLAYNAME  ";
					try{
						List<StateMasterEntity> caseActionMaster=new ArrayList<StateMasterEntity>();
						SQLQuery query=session.createSQLQuery(strQuery);
						query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
						List result = query.list();	
						if(result != null && result.size() > 0){
							for(Object o : result){				
								Map rt = (Map) o;
								StateMasterEntity entity=new StateMasterEntity();
								entity.setStateMasterId(rt.get("STATEMASTERID").toString());
								entity.setDisplayName(rt.get("DISPLAYNAME")==null?"":rt.get("DISPLAYNAME").toString());
								caseActionMaster.add(entity);
								entity=null;
							}
						}
						masterDto.setStateList(caseActionMaster);
						logger.info("MasterDaoImpl | StateMasterEntity END | ");
					}catch(Exception e){
						e.printStackTrace();
						logger.error("MasterDaoImpl | StateMasterEntity | :- error " + e.getMessage() + "| :-END");
					}
					break;

				case SubQueueEntity:
					logger.info("MasterDaoImpl | SubQueueEntity START | ");
					if(rest!=null)
						strQuery = "select * from QC_PROSPECT_MASTER.QM_SUB_QUEUE WHERE ACTIVE='A' and PARENT_SUB_QUEUE IS NULL and COMPANY_ID = "+userEntity.getCompanyId()+" AND "+rest;
					else
						strQuery = "select * from QC_PROSPECT_MASTER.QM_SUB_QUEUE WHERE ACTIVE='A' and PARENT_SUB_QUEUE IS NULL and COMPANY_ID = "+userEntity.getCompanyId()+"";
					try{
						List<SubQueueEntity> caseActionMaster=new ArrayList<SubQueueEntity>();
						SQLQuery query=session.createSQLQuery(strQuery);
						query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
						List result = query.list();	
						if(result != null && result.size() > 0){
							for(Object o : result){				
								Map rt = (Map) o;
								SubQueueEntity entity=new SubQueueEntity();
								entity.setSubQueueId(rt.get("SUB_QUEUE_ID").toString());
								entity.setSubQueueCode(rt.get("SUB_QUEUE_CODE").toString());
								entity.setSubQueue(rt.get("SUB_QUEUE").toString());
								entity.setSubQueueDnd(rt.get("PARENT_SUB_QUEUE")!=null?rt.get("PARENT_SUB_QUEUE").toString():"");
								caseActionMaster.add(entity);
								entity=null;
							}
						}
						masterDto.setSubQueueMaster(caseActionMaster);
						logger.info("MasterDaoImpl | SubQueueEntity END | ");
					}catch(Exception e){
						e.printStackTrace();
						logger.error("MasterDaoImpl | SubQueueEntity | :- error =" + e.getMessage() + "| :-END");
					}
					break;
					
				/*case QueueEntity:
					logger.info("MasterDaoImpl | SubQueueEntity START | ");
					if(rest!=null)
						strQuery = "select * from QC_PROSPECT_MASTER.QM_SUB_QUEUE WHERE ACTIVE='A' and PARENT_SUB_QUEUE IS NULL and COMPANY_ID = "+userEntity.getCompanyId()+" AND "+rest;
					else
						strQuery = "select * from QC_PROSPECT_MASTER.QM_SUB_QUEUE WHERE ACTIVE='A' and PARENT_SUB_QUEUE IS NULL and COMPANY_ID = "+userEntity.getCompanyId()+"";
					try{
						List<SubQueueEntity> caseActionMaster=new ArrayList<SubQueueEntity>();
						SQLQuery query=session.createSQLQuery(strQuery);
						query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
						List result = query.list();	
						if(result != null && result.size() > 0){
							for(Object o : result){				
								Map rt = (Map) o;
								SubQueueEntity entity=new SubQueueEntity();
								entity.setSubQueueId(rt.get("SUB_QUEUE_ID").toString());
								entity.setSubQueueCode(rt.get("SUB_QUEUE_CODE").toString());
								entity.setSubQueue(rt.get("SUB_QUEUE").toString());
								entity.setSubQueueDnd(rt.get("PARENT_SUB_QUEUE")!=null?rt.get("PARENT_SUB_QUEUE").toString():"");
								caseActionMaster.add(entity);
								entity=null;
							}
						}
						masterDto.setSubQueueMaster(caseActionMaster);
						logger.info("MasterDaoImpl | SubQueueEntity END | ");
					}catch(Exception e){
						e.printStackTrace();
						logger.error("MasterDaoImpl | SubQueueEntity | :- error =" + e.getMessage() + "| :-END");
					}
					break;*/

				case DispositionMaster:
					logger.info("MasterDaoImpl | DispositionMaster START | ");
					strQuery = "select DISTINCT ACTION_STAGE from QC_PROSPECT_MASTER.QM_CASE_ACTION where ACTIVE='A' and company_id="+userEntity.getCompanyId()+" and " +
							" ACTION_TYPE IN (select ACTION_TYPE_ID from QC_PROSPECT_MASTER.QM_CASE_ACTION_TYPE WHERE" +
							" ACTION_TYPE_NAME ='DISPOSITION' AND COMPANY_ID ="+userEntity.getCompanyId()+" )";
					try{
						List<CaseActionEntity> caseActionMaster=new ArrayList<CaseActionEntity>();
						SQLQuery query=session.createSQLQuery(strQuery);
						query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
						List result = query.list();	
						if(result != null && result.size() > 0){
							for(Object o : result){				
								Map rt = (Map) o;
								CaseActionEntity actionEntity=new CaseActionEntity();
								actionEntity.setActionStage(rt.get("ACTION_STAGE")!=null?rt.get("ACTION_STAGE").toString():"");
								caseActionMaster.add(actionEntity);
								actionEntity=null;
							}
						}
						masterDto.setDispositionMaster(caseActionMaster);
						logger.info("MasterDaoImpl | DispositionMaster END | ");
					}catch(Exception e){
						e.printStackTrace();
						logger.error("MasterDaoImpl | DispositionMaster | :- error -" + e.getMessage() + "| :-END");
					}
					break;


				case ActionMaster:
					logger.info("MasterDaoImpl | ActionMaster START | ");
					if(rest!=null) strQuery = "select ACTION_ID,ACTION_CODE,ACTION_NAME,ACTION_TYPE,WEIGHT,ACTION,DAYS,ACTION_STAGE" +
							" from QC_PROSPECT_MASTER.QM_CASE_ACTION where ACTIVE='A' and company_id=? and ACTION_TYPE='2' AND  "+rest;
					else strQuery = "select ACTION_ID,ACTION_CODE,ACTION_NAME,ACTION_TYPE,WEIGHT,ACTION,DAYS,ACTION_STAGE" +
							" from QC_PROSPECT_MASTER.QM_CASE_ACTION where ACTIVE='A' and company_id="+userEntity.getCompanyId()+" and ACTION_TYPE='2' ";
					try{
						List<CaseActionEntity> caseActionMaster=new ArrayList<CaseActionEntity>();
						SQLQuery query=session.createSQLQuery(strQuery);
						query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
						List result = query.list();	
						if(result != null && result.size() > 0){
							for(Object o : result){				
								Map rt = (Map) o;
								CaseActionEntity actionEntity=new CaseActionEntity();
								actionEntity.setActionId(rt.get("ACTION_ID")!=null?rt.get("ACTION_ID").toString():"");
								actionEntity.setActionCode(rt.get("ACTION_CODE")!=null?rt.get("ACTION_CODE").toString():"");
								actionEntity.setActionName(rt.get("ACTION_NAME")!=null?rt.get("ACTION_NAME").toString():"");
								actionEntity.setActionType(rt.get("ACTION_TYPE")!=null?rt.get("ACTION_TYPE").toString():"");
								actionEntity.setWeight(rt.get("WEIGHT")!=null?rt.get("WEIGHT").toString():"");
								actionEntity.setAction(rt.get("ACTION")!=null?rt.get("ACTION").toString():"");
								actionEntity.setDays(rt.get("DAYS")!=null?rt.get("DAYS").toString():"");
								actionEntity.setActionStage(rt.get("ACTION_STAGE")!=null?rt.get("ACTION_STAGE").toString():"");
								caseActionMaster.add(actionEntity);
								actionEntity=null;
							}
						}
						masterDto.setActionMaster(caseActionMaster);
						logger.info("MasterDaoImpl | ActionMaster END | ");
					}catch(Exception e){
						e.printStackTrace();
						logger.error("MasterDaoImpl | ActionMaster | :- error -" + e.getMessage() + "| :-END");
					}
					break;
					//-----------Added by Deepak on 01 march-2016-------------	
				case DevloperEntity:
					logger.info("MasterDaoImpl | DispositionMaster START |--DevloperEntity ");
					strQuery="SELECT DEVELOPER_ID, DEVELOPER_NAME FROM QC_PROSPECT_MASTER.QM_DEVELOPER WHERE ACTIVE ='A' AND COMPANY_ID ="+userEntity.getCompanyId();
					try{
						List<DevloperEntity> devloperMasterList=new ArrayList<DevloperEntity>();
						SQLQuery query=session.createSQLQuery(strQuery);
						query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
						List result = query.list();	
						if(result != null && result.size() > 0){
							for(Object o : result){				
								Map rt = (Map) o;
								DevloperEntity devloperEntity=new DevloperEntity();
								devloperEntity.setDevloperId(rt.get("DEVELOPER_ID")!=null?rt.get("DEVELOPER_ID").toString():"");
								devloperEntity.setDevloperName(rt.get("DEVELOPER_NAME")!=null?rt.get("DEVELOPER_NAME").toString():"");
								devloperMasterList.add(devloperEntity);
								devloperEntity=null;
							}
						}
						masterDto.setDevloperMasters(devloperMasterList);
						logger.info("MasterDaoImpl | DispositionMaster END |--DevloperEntity ");
					}catch(Exception e){
						e.printStackTrace();
						logger.error("MasterDaoImpl | DispositionMaster | :- error -" + e.getMessage() + "| :-END");
					}
					break;
				case EntityType:
					logger.info("MasterDaoImpl | EntityTypeMaster START |--EntityType ");
					strQuery="SELECT CUSTENTITYTYPEID,CUSTENTITYTYPECODE,CUSTENTITYTYPENAME,DISPLAYNAME" +
							" FROM QC_PROSPECT_MASTER.QM_CUSTENTITYTYPE WHERE ACTIVE ='A'";
					try{
						List<EntityTypeEntity> entityMasterList=new ArrayList<EntityTypeEntity>();
						SQLQuery query=session.createSQLQuery(strQuery);
						query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
						List result = query.list();	
						if(result != null && result.size() > 0){
							for(Object o : result){				
								Map rt = (Map) o;
								EntityTypeEntity entity=new EntityTypeEntity();
								entity.setCustEntityTypeId(rt.get("CUSTENTITYTYPEID")!=null?rt.get("CUSTENTITYTYPEID").toString():"");
								entity.setDisplayName(rt.get("DISPLAYNAME")!=null?rt.get("DISPLAYNAME").toString():"");
								entity.setCustEntityTypeCode(rt.get("CUSTENTITYTYPECODE")!=null?rt.get("CUSTENTITYTYPECODE").toString():"");
								entityMasterList.add(entity);
								entity=null;
							}
						}
						masterDto.setEntityTypeMaster(entityMasterList);
						logger.info("MasterDaoImpl |EntityTypeMaster END |--EntityTypeEntity ");
					}catch(Exception e){
						e.printStackTrace();
						logger.error("MasterDaoImpl | EntityTypeMaster | :- error -" + e.getMessage() + "| :-END");
					}
					break;
				case CustomerCategory:
					logger.info("MasterDaoImpl | CustomerCategoryMaster START |--CustomerCategory ");
					strQuery="SELECT CUSTENTITYTYPEID,CUSTENTITYTYPECODE,CUSTENTITYTYPENAME,DISPLAYNAME" +
							" FROM QC_PROSPECT_MASTER.QM_CUSTENTITYTYPE WHERE ACTIVE ='A'";
					try{
						List<CustomerCategryEntity> entityMasterList=new ArrayList<CustomerCategryEntity>();
						SQLQuery query=session.createSQLQuery(strQuery);
						query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
						List result = query.list();	
						if(result != null && result.size() > 0){
							for(Object o : result){				
								Map rt = (Map) o;
								CustomerCategryEntity entity=new CustomerCategryEntity();
								entity.setCustomercategortId(rt.get("CUSTENTITYTYPEID")!=null?rt.get("CUSTENTITYTYPEID").toString():"");
								entity.setDisplayName(rt.get("CUSTENTITYTYPENAME")!=null?rt.get("CUSTENTITYTYPENAME").toString():"");
								entity.setCustomercategortCode(rt.get("CUSTENTITYTYPECODE")!=null?rt.get("CUSTENTITYTYPECODE").toString():"");
								entityMasterList.add(entity);
								entity=null;
							}
						}
						masterDto.setCustomerCategryMaster(entityMasterList);
						logger.info("MasterDaoImpl |CustomerCategoryMaster END |--CustomerCategory ");
					}catch(Exception e){
						e.printStackTrace();
						logger.error("MasterDaoImpl | CustomerCategoryMaster | :- error -" + e.getMessage() + "| :-END");
					}
					break;
				case Industry:
					logger.info("MasterDaoImpl | IndustryMaster START |--Industry ");
					strQuery="SELECT INDUSTRYID,INDUSTRYCODE,INDUSTRYNAME" +
							" FROM QC_PROSPECT_MASTER.QM_INDUSTRY WHERE ACTIVE ='A'";
					try{
						List<IndustryEntity> entityMasterList=new ArrayList<IndustryEntity>();
						SQLQuery query=session.createSQLQuery(strQuery);
						query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
						List result = query.list();	
						if(result != null && result.size() > 0){
							for(Object o : result){				
								Map rt = (Map) o;
								IndustryEntity entity=new IndustryEntity();
								entity.setIndustryId(rt.get("INDUSTRYID")!=null?rt.get("INDUSTRYID").toString():"");
								entity.setDisplayName(rt.get("INDUSTRYNAME")!=null?rt.get("INDUSTRYNAME").toString():"");
								entity.setIndustryCode(rt.get("INDUSTRYCODE")!=null?rt.get("INDUSTRYCODE").toString():"");
								entityMasterList.add(entity);
								entity=null;
							}
						}
						masterDto.setIndustryMaster(entityMasterList);
						logger.info("MasterDaoImpl | IndustryMaster END |--Industry ");
					}catch(Exception e){
						e.printStackTrace();
						logger.error("MasterDaoImpl | IndustryMaster | :- error -" + e.getMessage() + "| :-END");
					}
					break;
					/*Added by Anuj on 30-dec-2016*/
				case SectorEntity:
					logger.info("MasterDaoImpl | SectorEntityMaster START |--SectorEntity ");
					strQuery="SELECT SECTOR_ID,SECTOR_NAME FROM QC_PROSPECT_MASTER.QM_CASE_SECTOR WHERE ACTIVE ='A'";
					try{
						List<SectorEntity> entityMasterList=new ArrayList<SectorEntity>();
						SQLQuery query=session.createSQLQuery(strQuery);
						query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
						List result = query.list();	
						if(result != null && result.size() > 0){
							for(Object o : result){				
								Map rt = (Map) o;
								SectorEntity entity=new SectorEntity();
								entity.setSectorId(rt.get("SECTOR_ID")!=null?rt.get("SECTOR_ID").toString():"");
								entity.setSectorName(rt.get("SECTOR_NAME")!=null?rt.get("SECTOR_NAME").toString():"");
								entityMasterList.add(entity);
								entity=null;
							}
						}
						masterDto.setSectorMaster(entityMasterList);
					logger.info("MasterDaoImpl | SectorEntityMaster END |--SectorEntity ");
					}catch(Exception e){
						e.printStackTrace();
						logger.error("MasterDaoImpl | SectorEntityMaster | :- error -" + e.getMessage() + "| :-END");
					}
					break;
				case StageEntity:
					logger.info("MasterDaoImpl | StageEntityMaster START |--StageEntity ");
					strQuery="SELECT STAGE_ID,STAGE_NAME FROM QC_PROSPECT_MASTER.QM_CASE_STAGE WHERE ACTIVE ='A'";
					try{
						List<StageEntity> entityMasterList=new ArrayList<StageEntity>();
						SQLQuery query=session.createSQLQuery(strQuery);
						query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
						List result = query.list();	
						if(result != null && result.size() > 0){
							for(Object o : result){				
								Map rt = (Map) o;
								StageEntity entity=new StageEntity();
								entity.setStageId(rt.get("STAGE_ID")!=null?rt.get("STAGE_ID").toString():"");
								entity.setStageName(rt.get("STAGE_NAME")!=null?rt.get("STAGE_NAME").toString():"");
								entityMasterList.add(entity);
								entity=null;
							}
						}
						masterDto.setStageMaster(entityMasterList);
						logger.info("MasterDaoImpl | StageEntityMaster END |--StageEntity ");
					}catch(Exception e){
						e.printStackTrace();
						logger.error("MasterDaoImpl | StageEntityMaster | :- error -" + e.getMessage() + "| :-END");
					}
					break;
					
					/*-------------------End--------------------------*/
				case ClusterEntity:
					logger.info("MasterDaoImpl | BranchMaster START |--Branch ");
					strQuery="SELECT CLUSTERID,CLUSTERCODE,CLUSTERNAME" +
							" FROM QC_PROSPECT_MASTER.QM_CLUSTER WHERE ACTIVE ='A'";
					try{
						List<ClusterEntity> entityMasterList=new ArrayList<ClusterEntity>();
						SQLQuery query=session.createSQLQuery(strQuery);
						query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
						List result = query.list();	
						if(result != null && result.size() > 0){
							for(Object o : result){				
								Map rt = (Map) o;
								ClusterEntity entity=new ClusterEntity();
								entity.setClusterId(rt.get("CLUSTERID")!=null?rt.get("CLUSTERID").toString():"");
								entity.setClusterCode(rt.get("CLUSTERCODE")!=null?rt.get("CLUSTERCODE").toString():"");
								entity.setClusterName(rt.get("CLUSTERNAME")!=null?rt.get("CLUSTERNAME").toString():"");
								entityMasterList.add(entity);
								entity=null;
							}
						}
						masterDto.setClusterMaster(entityMasterList);
						logger.info("MasterDaoImpl | BranchMaster END |--Branch ");
					}catch(Exception e){
						e.printStackTrace();
						logger.error("MasterDaoImpl | BranchMaster | :- error -" + e.getMessage() + "| :-END");
					}
					break;
				case TypeOfBusinessEntity:
					logger.info("MasterDaoImpl | BranchMaster START |--Branch ");
					strQuery="SELECT ID,CODE,TYPE_OF_BUSINESS" +
							" FROM QC_PROSPECT_MASTER.QM_TYPE_OF_BUSINESS WHERE ACTIVE ='A'";
					try{
						List<TypeOfBusinessEntity> entityMasterList=new ArrayList<TypeOfBusinessEntity>();
						SQLQuery query=session.createSQLQuery(strQuery);
						query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
						List result = query.list();	
						if(result != null && result.size() > 0){
							for(Object o : result){				
								Map rt = (Map) o;
								TypeOfBusinessEntity entity=new TypeOfBusinessEntity();
								entity.setId(rt.get("ID")!=null?rt.get("ID").toString():"");
								entity.setCode(rt.get("CODE")!=null?rt.get("CODE").toString():"");
								entity.setTypeOfBusiness(rt.get("TYPE_OF_BUSINESS")!=null?rt.get("TYPE_OF_BUSINESS").toString():"");
								entityMasterList.add(entity);
								entity=null;
							}
						}
						masterDto.setTypeOfBusinessMaster(entityMasterList);
						logger.info("MasterDaoImpl | BranchMaster END |--Branch ");
					}catch(Exception e){
						e.printStackTrace();
						logger.error("MasterDaoImpl | BranchMaster | :- error -" + e.getMessage() + "| :-END");
					}
					break;
				case BranchMaster:
					logger.info("MasterDaoImpl | BranchMaster START |--Branch ");
					strQuery="SELECT GEOID,GEOCODE,GEONAME" +
							" FROM QC_PROSPECT_MASTER.QM_GEO WHERE ACTIVE ='A' AND GEOTYPEID=1000000006 order by GEONAME";
					try{
						List<BranchEntity> entityMasterList=new ArrayList<BranchEntity>();
						SQLQuery query=session.createSQLQuery(strQuery);
						query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
						List result = query.list();	
						if(result != null && result.size() > 0){
							for(Object o : result){				
								Map rt = (Map) o;
								BranchEntity entity=new BranchEntity();
								entity.setGeoId(rt.get("GEOID")!=null?rt.get("GEOID").toString():"");
								entity.setGeoName(rt.get("GEONAME")!=null?rt.get("GEONAME").toString():"");
								entity.setGeoCode(rt.get("GEOCODE")!=null?rt.get("GEOCODE").toString():"");
								entityMasterList.add(entity);
								entity=null;
							}
						}
						masterDto.setBranchMaster(entityMasterList);
						logger.info("MasterDaoImpl | BranchMaster END |--Branch ");
					}catch(Exception e){
						e.printStackTrace();
						logger.error("MasterDaoImpl | BranchMaster | :- error -" + e.getMessage() + "| :-END");
					}
					break;
					
					
				case NextActionEntity:
					logger.info("MasterDaoImpl | NextActionEntity START |--NextActionEntity ");
					strQuery="SELECT NEXTACTION_ID,NEXTACTION_CODE,NEXTACTION_NAME" +
							" FROM QC_PROSPECT_MASTER.QM_CASE_NEXT_ACTION WHERE ACTIVE ='A' ";
					try{
						List<NextActionEntity> nextActionMasterList=new ArrayList<NextActionEntity>();
						SQLQuery query=session.createSQLQuery(strQuery);
						query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
						List result = query.list();	
						if(result != null && result.size() > 0){
							for(Object o : result){				
								Map rt = (Map) o;
								NextActionEntity entity=new NextActionEntity();
								entity.setNextActionId(rt.get("NEXTACTION_ID")!=null?rt.get("NEXTACTION_ID").toString():"");
								entity.setNextActionName(rt.get("NEXTACTION_NAME")!=null?rt.get("NEXTACTION_NAME").toString():"");
								entity.setNextActionCode(rt.get("NEXTACTION_CODE")!=null?rt.get("NEXTACTION_CODE").toString():"");
								nextActionMasterList.add(entity);
								entity=null;
							}
						}
						masterDto.setNextActionEntity(nextActionMasterList);
						logger.info("MasterDaoImpl | BranchMaster END |--Branch ");
					}catch(Exception e){
						e.printStackTrace();
						logger.error("MasterDaoImpl | BranchMaster | :- error -" + e.getMessage() + "| :-END");
					}
					break;
					
				case PurposeOfLoan:
					logger.info("MasterDaoImpl | PurposeOfLoan START |--Branch ");
					strQuery="select PURPOSEOFLOANID,PURPOSEOFLOANCODE,PURPOSEOFLOANNAME,DISPLAYNAME,DESCRIPTION,PRODUCTID" +
							" from QC_PROSPECT_MASTER.QM_PURPOSEOFLOAN where active='A'";
					try{
						List<PurposeOfLoan> entityMasterList=new ArrayList<PurposeOfLoan>();
						SQLQuery query=session.createSQLQuery(strQuery);
						query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
						List result = query.list();	
						if(result != null && result.size() > 0){
							for(Object o : result){				
								Map rt = (Map) o;
								PurposeOfLoan entity=new PurposeOfLoan();
								entity.setPurposeOfLoanId(rt.get("PURPOSEOFLOANID")!=null?rt.get("PURPOSEOFLOANID")+"":"");
								entity.setPurposeOfLoanCode(rt.get("PURPOSEOFLOANCODE")!=null?rt.get("PURPOSEOFLOANCODE")+"":"");
								entity.setPurposeOfLoanName(rt.get("PURPOSEOFLOANNAME")!=null?rt.get("PURPOSEOFLOANNAME")+"":"");
								entityMasterList.add(entity);
								entity=null;
							}
						}
						masterDto.setPurposeOfLoanMaster(entityMasterList);
						logger.info("MasterDaoImpl | PurposeOfLoan END |--PurposeOfLoan ");
					}catch(Exception e){
						e.printStackTrace();
						logger.error("MasterDaoImpl | PurposeOfLoan | :- error -" + e.getMessage() + "| :-END");
					}
					break;
					
					
					
					
				case PurposeEntity:
					logger.info("MasterDaoImpl | PurposeOfLoan START |--Branch ");
					strQuery="select PURPOSE_ID,PURPOSE_CODE,PURPOSE_NAME,DISPLAY_NAME from QC_PROSPECT_MASTER.QM_PURPOSE where active='A'";
					try{
						List<PurposeEntity> entityMasterList=new ArrayList<PurposeEntity>();
						SQLQuery query=session.createSQLQuery(strQuery);
						query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
						List result = query.list();	
						if(result != null && result.size() > 0){
							for(Object o : result){				
								Map rt = (Map) o;
								PurposeEntity entity=new PurposeEntity();
								entity.setPurposeId(rt.get("PURPOSE_ID")!=null?rt.get("PURPOSE_ID")+"":"");
								entity.setPurposeCode(rt.get("PURPOSE_CODE")!=null?rt.get("PURPOSE_CODE")+"":"");
								entity.setPurposeName(rt.get("PURPOSE_NAME")!=null?rt.get("PURPOSE_NAME")+"":"");
								entity.setDisplayName(rt.get("DISPLAY_NAME")!=null?rt.get("DISPLAY_NAME")+"":"");
								entityMasterList.add(entity);
								entity=null;
							}
						}
						masterDto.setPurposeEntity(entityMasterList);
						logger.info("MasterDaoImpl | Purpose END |--Purpose ");
					}catch(Exception e){
						e.printStackTrace();
						logger.error("MasterDaoImpl | Purpose | :- error -" + e.getMessage() + "| :-END");
					}
					break;
				case RejectReasonEntity:
				logger.info("MasterDaoImpl | PurposeOfLoan START |--Branch ");
				strQuery="SELECT REJECT_REGION_ID,REJECT_REGION_CODE,REJECT_REGION_NAME, DISPLAY_NAME FROM QC_PROSPECT_MASTER.QM_REJECT_REASON where active='A'";
				try{
					List<RejectReasonEntity> entityMasterList=new ArrayList<RejectReasonEntity>();
					SQLQuery query=session.createSQLQuery(strQuery);
					query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
					List result = query.list();	
					if(result != null && result.size() > 0){
						for(Object o : result){				
							Map rt = (Map) o;
							RejectReasonEntity entity=new RejectReasonEntity();
							entity.setRejectreasonId(rt.get("REJECT_REGION_ID")!=null?rt.get("REJECT_REGION_ID")+"":"");
							entity.setRejectreasonCode(rt.get("REJECT_REGION_CODE")!=null?rt.get("REJECT_REGION_CODE")+"":"");
							entity.setRejectreasonName(rt.get("REJECT_REGION_NAME")!=null?rt.get("REJECT_REGION_NAME")+"":"");
							entity.setDisplayName(rt.get("DISPLAY_NAME")!=null?rt.get("DISPLAY_NAME")+"":"");
							entityMasterList.add(entity);
							entity=null;
						}
					}
					masterDto.setRejectreasonEntity(entityMasterList);
					logger.info("MasterDaoImpl | REJECT_REASON END |--REJECT_REASON ");
				}catch(Exception e){
					e.printStackTrace();
					logger.error("MasterDaoImpl | REJECT_REASON | :- error -" + e.getMessage() + "| :-END");
				}
				break;
					//-------------------------------
				default:
					break;
				}				
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.info("MasterDaoImpl  :- error ");
		}finally {
			if(session!=null)
				session.close();
		}
		logger.info("MasterDaoImpl | getMasters() | :- END with Response param :::masterDto:::"+masterDto);
		return masterDto;
	}


	@Transactional
	private List<String> getActionType(String string) {
		UserEntity userEntity = (UserEntity) httpSession.getAttribute("UserDetails");
		List<String> list=null;
		Session session=null;
		try{
			session = sessionFactory.openSession();
			SQLQuery query = session.createSQLQuery("select ACTION_TYPE_ID from QC_PROSPECT_MASTER.QM_CASE_ACTION_TYPE" +
					" WHERE COMPANY_ID = 1000000001 AND ACTIVE ='A' AND ACTION_TYPE_NAME =:actionName");
			query.setParameter("actionName", string);
			List result=query.list();	        
			list=new ArrayList<String>();			
			Iterator it =result.iterator();
			while(it.hasNext())
			{
				BigDecimal obj = (BigDecimal)it.next();
				String actionType=obj==null?"":obj+"";					
				list.add(actionType);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
		return list;
	}

	enum entityList {
		MaritalStatusEntity, GenderEntity, NationalityEntity, OccupationEntity, CompanyTypeEntity, CompanyEntity, SalaryModeEntity, CityEntity, OccupancyStatusEntity, CaseContactEntity, PropertyTypeEntity, PropertyStatusEntity, ProductMasterEntity, BankEntity, CampaignEntity, SourceEntity, SubQueueEntity, ContactTypeEntity, CaseActionEntity, AddressTypeEntity, StateMasterEntity, DispositionMaster, ActionMaster, existingFacility,DevloperEntity,EntityType,Industry,CustomerCategory,BranchMaster,PurposeOfLoan, SectorEntity, StageEntity,ClusterEntity,TypeOfBusinessEntity,QueueEntity,TitleEntity,NextActionEntity,PurposeEntity,RejectReasonEntity,
	}
	
	@Transactional
	public List<String> getProdTypeMasterIdList(String prodTypeName) {
		UserEntity userEntity = (UserEntity) httpSession.getAttribute("UserDetails");
		Session session = sessionFactory.openSession();
		List<String>  list=new ArrayList<String>();
		try{
			String sql="select PRODTYPEID from QC_PROSPECT_MASTER.QM_PRODUCTTYPE where ACTIVE='A' and " +
					"COMPANY_ID = "+userEntity.getCompanyId()+" and PRODUCTTYPE="+prodTypeName;
			SQLQuery query = session.createSQLQuery(sql);
			List result=query.list();	        
			List<String> list1=new ArrayList<String>();			
			Iterator it =result.iterator();
			while(it.hasNext())
			{
				BigDecimal obj = (BigDecimal)it.next();
				String actionType=obj==null?"":obj+"";					
				list.add(actionType);
			}
		}catch(Exception e){
			e.printStackTrace();
		}	finally {
			if(session!=null)
				session.close();
		}	
		return list;
	}

	@Transactional
	public List<DocumentEntity> getDocumentTypeList(int companyId,String productId){
		Session session = sessionFactory.openSession();
		DocumentEntity entity=null;
		List<DocumentEntity> list=new ArrayList<DocumentEntity>();
		try{
			String sql = "SELECT QD.DOCUMENTTYPEID, QD.DISPLAYNAME FROM QC_PROSPECT_MASTER.QM_DOCUMENTTYPE QD " +
					"WHERE QD.ACTIVE ='A' AND QD.COMPANY_ID  ="+companyId+" AND   QD.DOCUMENTTYPEID IN " +
					" (SELECT DOCUMENTTYPEID FROM  QC_PROSPECT_MASTER.QM_SCHEME_X_DOCUMENTTYPE  QS WHERE QS.ACTIVE  = 'A'" +
					" AND   QS.COMPANY_ID  = "+companyId+" AND   QS.PRODUCTID ="+productId+") ORDER BY DISPLAYNAME";
			SQLQuery query = session.createSQLQuery(sql);
			List result=query.list();
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List<DocumentEntity> documentList=new ArrayList<DocumentEntity>();
			Iterator it =result.iterator();
			while(it.hasNext())
			{
				DocumentEntity documentObj=new DocumentEntity();	
				Object obj[] = (Object[])it.next();
				documentObj.setDocumentTypeId(obj[0]+"");
				documentObj.setDocumentTypeName(obj[1]+"");			
				list.add(documentObj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
		return list;
	}


	@Transactional
	public List<DocumentEntity> getDocumentStatusList(){
		UserEntity userEntity = (UserEntity) httpSession.getAttribute("UserDetails");
		Session session = sessionFactory.openSession();
		List<DocumentEntity> list=new ArrayList<DocumentEntity>();
		try{
			String sql = "select QDS.DOCUMENTSTATUSID, QDS.DOCUMENTSTATUSNAME from QC_PROSPECT_MASTER.QM_DOCUMENTSTATUS QDS " +
					"WHERE QDS.ACTIVE='A' AND   QDS.COMPANY_ID="+userEntity.getCompanyId();
			SQLQuery query = session.createSQLQuery(sql);
			List result=query.list();
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List<DocumentEntity> documentList=new ArrayList<DocumentEntity>();
			Iterator it =result.iterator();
			while(it.hasNext())
			{
				DocumentEntity documentObj=new DocumentEntity();	
				Object obj[] = (Object[])it.next();
				documentObj.setDocStatusId(obj[0]+"");
				documentObj.setDocStatusName(obj[1]+"");			
				list.add(documentObj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
		return list;
	}
	@Transactional
	public List<DocumentEntity> getDocumentCreatedByList(){
		List<DocumentEntity> documentList=new ArrayList<DocumentEntity>();
		List<DocumentEntity> list=new ArrayList<DocumentEntity>();
		return list;
	}

	@Transactional
	public List<DocumentEntity> getDocumentUpdatedByList(){
		List<DocumentEntity> documentList=new ArrayList<DocumentEntity>();
		List<DocumentEntity> list=new ArrayList<DocumentEntity>();
		return list;
	}

	@Override
	public List<PinCodeEntity> getPinCode(String cityId) {
		UserEntity userEntity = (UserEntity) httpSession.getAttribute("UserDetails");
		Session session = sessionFactory.openSession();
		List<PinCodeEntity> list=new ArrayList<PinCodeEntity>();
		if(cityId==null ||  cityId=="")
		{
			cityId="930";
		}
		try{
			//String sql="SELECT DISTINCT (PD.PINCODE) PINCODE, PD.DISTRICT_NAME AS DIVISIONNAME FROM QC_PROSPECT_MASTER.QM_PINCODEMASTER PD WHERE CITYID = '"+cityId+"'";
			
			String sql = "SELECT DISTINCT PM.PINCODE AS PINCODE,CM.CITYMASTERNAME AS DIVISIONNAME FROM QC_PROSPECT_MASTER.QM_PINCODEMASTER PM, QC_PROSPECT_MASTER.QM_CITYMASTER CM "
					+ "WHERE PM.CITYID = CM.CITYMASTERID AND CM.CITYMASTERID ='"+cityId+"'";
			
			SQLQuery query = session.createSQLQuery(sql);
			List result=query.list();
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			PinCodeEntity pincodeentity =null;
			Iterator it =result.iterator();
			while(it.hasNext())
			{
				pincodeentity =new PinCodeEntity();	
				Object obj[] = (Object[])it.next();				
				pincodeentity.setPincode(obj[0]+"");
				pincodeentity.setDivisionName(obj[1]+"");	
				list.add(pincodeentity);
				pincodeentity=null;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
		return list;
	}


}