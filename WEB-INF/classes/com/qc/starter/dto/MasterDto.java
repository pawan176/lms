package com.qc.starter.dto;


import java.util.List;


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
import com.qc.starter.entity.EntityTypeEntity;
import com.qc.starter.entity.GenderEntity;
import com.qc.starter.entity.IndustryEntity;
import com.qc.starter.entity.MaritalStatusEntity;
import com.qc.starter.entity.NationalityEntity;
import com.qc.starter.entity.NextActionEntity;
import com.qc.starter.entity.OccupancyStatusEntity;
import com.qc.starter.entity.OccupationEntity;
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


public class MasterDto {


	private List<MaritalStatusEntity> maritalStatus;
	private	List<GenderEntity>	gender;
	private	List<NationalityEntity>	nationality;
	private	List<OccupationEntity>	occupationType;
	private List<CompanyTypeEntity> companyType;
	private List<CompanyEntity> companyName;
	private List<SalaryModeEntity> salaryMode;
	private List<CityEntity> city;
	private List<OccupancyStatusEntity> occupancyStatus;
	private List<ContactEntity> contact;
	private List<PropertyTypeEntity> propType;
	private List<PropertyStatusEntity> propStatus;
	private List<ProductMasterEntity> productMaster;
	private List<BankEntity> bankMaster;	
	private List<CampaignEntity> campaignMaster;
	private List<SourceEntity> sourceMaster;
	private List<SubQueueEntity> subQueueMaster;
	private List<ContactTypeEntity> contactTypeMobile;
	private List<ContactTypeEntity> contactTypeEmail;
	private List<CaseActionEntity> caseActionMaster;
	private List<CaseActionEntity> dispositionMaster;
	private List<CaseActionEntity> actionMaster;
	private List<AddressTypeEntity> addressType;
	private List<StateMasterEntity> stateList;
	private List<ProductMasterEntity> existingFacility;
	//-----------------Added by deepak on 01 march 2016---------
	private List<DevloperEntity> devloperMasters;
	//------------------Added by Tripti on 29-Sep-16
	private List<EntityTypeEntity> entityTypeMaster;
	private List<IndustryEntity> industryMaster;
	private List<CustomerCategryEntity> customerCategryMaster;
	private List<BranchEntity> branchMaster;
	private List<PurposeOfLoan> purposeOfLoanMaster;
	//-------------Added by Anuj on 30-dec-2016
    private List<SectorEntity> sectorMaster;
    private List<StageEntity> stageMaster;
    private List<TypeOfBusinessEntity> typeOfBusinessMaster;
    private List<ClusterEntity> clusterMaster;
    private List<TitleEntity> titleMaster;
    
    private List<NextActionEntity> nextActionEntity;
    private List<PurposeEntity> purposeEntity;
    private List<RejectReasonEntity> rejectreasonEntity;
    
    
    
    
    
	public List<PurposeEntity> getPurposeEntity() {
		return purposeEntity;
	}
	public void setPurposeEntity(List<PurposeEntity> purposeEntity) {
		this.purposeEntity = purposeEntity;
	}
	public List<RejectReasonEntity> getRejectreasonEntity() {
		return rejectreasonEntity;
	}
	public void setRejectreasonEntity(List<RejectReasonEntity> rejectreasonEntity) {
		this.rejectreasonEntity = rejectreasonEntity;
	}
	public List<NextActionEntity> getNextActionEntity() {
		return nextActionEntity;
	}
	public void setNextActionEntity(List<NextActionEntity> nextActionEntity) {
		this.nextActionEntity = nextActionEntity;
	}
	public List<TitleEntity> getTitleMaster() {
		return titleMaster;
	}
	public void setTitleMaster(List<TitleEntity> titleMaster) {
		this.titleMaster = titleMaster;
	}
	public List<TypeOfBusinessEntity> getTypeOfBusinessMaster() {
		return typeOfBusinessMaster;
	}
	public void setTypeOfBusinessMaster(List<TypeOfBusinessEntity> typeOfBusinessMaster) {
		this.typeOfBusinessMaster = typeOfBusinessMaster;
	}
	public List<ClusterEntity> getClusterMaster() {
		return clusterMaster;
	}
	public void setClusterMaster(List<ClusterEntity> clusterMaster) {
		this.clusterMaster = clusterMaster;
	}
	public List<SectorEntity> getSectorMaster() {
		return sectorMaster;
	}
	public void setSectorMaster(List<SectorEntity> sectorMaster) {
		this.sectorMaster = sectorMaster;
	}
	public List<StageEntity> getStageMaster() {
		return stageMaster;
	}
	public void setStageMaster(List<StageEntity> stageMaster) {
		this.stageMaster = stageMaster;
	}
	public List<PurposeOfLoan> getPurposeOfLoanMaster() {
		return purposeOfLoanMaster;
	}
	public void setPurposeOfLoanMaster(List<PurposeOfLoan> purposeOfLoanMaster) {
		this.purposeOfLoanMaster = purposeOfLoanMaster;
	}
	public List<BranchEntity> getBranchMaster() {
		return branchMaster;
	}
	public void setBranchMaster(List<BranchEntity> branchMaster) {
		this.branchMaster = branchMaster;
	}
	public List<IndustryEntity> getIndustryMaster() {
		return industryMaster;
	}
	public void setIndustryMaster(List<IndustryEntity> industryMaster) {
		this.industryMaster = industryMaster;
	}
	public List<CustomerCategryEntity> getCustomerCategryMaster() {
		return customerCategryMaster;
	}
	public void setCustomerCategryMaster(
			List<CustomerCategryEntity> customerCategryMaster) {
		this.customerCategryMaster = customerCategryMaster;
	}
	public List<EntityTypeEntity> getEntityTypeMaster() {
		return entityTypeMaster;
	}
	public void setEntityTypeMaster(List<EntityTypeEntity> entityTypeMaster) {
		this.entityTypeMaster = entityTypeMaster;
	}
	public List<DevloperEntity> getDevloperMasters() {
		return devloperMasters;
	}
	public void setDevloperMasters(List<DevloperEntity> devloperMasters) {
		this.devloperMasters = devloperMasters;
	}
	public List<ProductMasterEntity> getExistingFacility() {
		return existingFacility;
	}
	public void setExistingFacility(List<ProductMasterEntity> existingFacility) {
		this.existingFacility = existingFacility;
	}
	/**
	 * @return the stateList
	 */
	public List<StateMasterEntity> getStateList() {
		return stateList;
	}
	/**
	 * @param stateList the stateList to set
	 */
	public void setStateList(List<StateMasterEntity> stateList) {
		this.stateList = stateList;
	}
	/**
	 * @return the addressType
	 */
	public List<AddressTypeEntity> getAddressType() {
		return addressType;
	}
	/**
	 * @param addressType the addressType to set
	 */
	public void setAddressType(List<AddressTypeEntity> addressType) {
		this.addressType = addressType;
	}
	public List<CaseActionEntity> getActionMaster() {
		return actionMaster;
	}
	public void setActionMaster(List<CaseActionEntity> actionMaster) {
		this.actionMaster = actionMaster;
	}
	public List<CaseActionEntity> getDispositionMaster() {
		return dispositionMaster;
	}
	public void setDispositionMaster(List<CaseActionEntity> dispositionMaster) {
		this.dispositionMaster = dispositionMaster;
	}
	public List<CaseActionEntity> getCaseActionMaster() {
		return caseActionMaster;
	}
	public void setCaseActionMaster(List<CaseActionEntity> caseActionMaster) {
		this.caseActionMaster = caseActionMaster;
	}
	public List<ContactTypeEntity> getContactTypeMobile() {
		return contactTypeMobile;
	}
	public void setContactTypeMobile(List<ContactTypeEntity> contactTypeMobile) {
		this.contactTypeMobile = contactTypeMobile;
	}
	public List<ContactTypeEntity> getContactTypeEmail() {
		return contactTypeEmail;
	}
	public void setContactTypeEmail(List<ContactTypeEntity> contactTypeEmail) {
		this.contactTypeEmail = contactTypeEmail;
	}
	public List<CampaignEntity> getCampaignMaster() {
		return campaignMaster;
	}
	public void setCampaignMaster(List<CampaignEntity> campaignMaster) {
		this.campaignMaster = campaignMaster;
	}
	public List<SourceEntity> getSourceMaster() {
		return sourceMaster;
	}
	public void setSourceMaster(List<SourceEntity> sourceMaster) {
		this.sourceMaster = sourceMaster;
	}
	public List<SubQueueEntity> getSubQueueMaster() {
		return subQueueMaster;
	}
	public void setSubQueueMaster(List<SubQueueEntity> subQueueMaster) {
		this.subQueueMaster = subQueueMaster;
	}
	public List<BankEntity> getBankMaster() {
		return bankMaster;
	}
	public void setBankMaster(List<BankEntity> bankMaster) {
		this.bankMaster = bankMaster;
	}
	public List<ProductMasterEntity> getProductMaster() {
		return productMaster;
	}
	public void setProductMaster(List<ProductMasterEntity> productMaster) {
		this.productMaster = productMaster;
	}
	/**
	 * @return the propStatus
	 */
	public List<PropertyStatusEntity> getPropStatus() {
		return propStatus;
	}
	/**
	 * @param propStatus the propStatus to set
	 */
	public void setPropStatus(List<PropertyStatusEntity> propStatus) {
		this.propStatus = propStatus;
	}
	/**
	 * @return the propType
	 */
	public List<PropertyTypeEntity> getPropType() {
		return propType;
	}
	/**
	 * @param propType the propType to set
	 */
	public void setPropType(List<PropertyTypeEntity> propType) {
		this.propType = propType;
	}
	/**
	 * @return the contact
	 */
	public List<ContactEntity> getContact() {
		return contact;
	}
	/**
	 * @param contact the contact to set
	 */
	public void setContact(List<ContactEntity> contact) {
		this.contact = contact;
	}
	/**
	 * @return the maritalStatus
	 */
	public List<MaritalStatusEntity> getMaritalStatus() {
		return maritalStatus;
	}
	/**
	 * @param maritalStatus the maritalStatus to set
	 */
	public void setMaritalStatus(List<MaritalStatusEntity> maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	/**
	 * @return the gender
	 */
	public List<GenderEntity> getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(List<GenderEntity> gender) {
		this.gender = gender;
	}
	/**
	 * @return the nationality
	 */
	public List<NationalityEntity> getNationality() {
		return nationality;
	}
	/**
	 * @param nationality the nationality to set
	 */
	public void setNationality(List<NationalityEntity> nationality) {
		this.nationality = nationality;
	}
	/**
	 * @return the occupationType
	 */
	public List<OccupationEntity> getOccupationType() {
		return occupationType;
	}
	/**
	 * @param occupationType the occupationType to set
	 */
	public void setOccupationType(List<OccupationEntity> occupationType) {
		this.occupationType = occupationType;
	}
	/**
	 * @return the companyType
	 */
	public List<CompanyTypeEntity> getCompanyType() {
		return companyType;
	}
	/**
	 * @param companyType the companyType to set
	 */
	public void setCompanyType(List<CompanyTypeEntity> companyType) {
		this.companyType = companyType;
	}
	/**
	 * @return the companyName
	 */
	public List<CompanyEntity> getCompanyName() {
		return companyName;
	}
	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(List<CompanyEntity> companyName) {
		this.companyName = companyName;
	}
	/**
	 * @return the salaryMode
	 */
	public List<SalaryModeEntity> getSalaryMode() {
		return salaryMode;
	}
	/**
	 * @param salaryMode the salaryMode to set
	 */
	public void setSalaryMode(List<SalaryModeEntity> salaryMode) {
		this.salaryMode = salaryMode;
	}
	/**
	 * @return the city
	 */
	public List<CityEntity> getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(List<CityEntity> city) {
		this.city = city;
	}
	/**
	 * @return the occupancyStatus
	 */
	public List<OccupancyStatusEntity> getOccupancyStatus() {
		return occupancyStatus;
	}
	/**
	 * @param occupancyStatus the occupancyStatus to set
	 */
	public void setOccupancyStatus(List<OccupancyStatusEntity> occupancyStatus) {
		this.occupancyStatus = occupancyStatus;
	}


}
