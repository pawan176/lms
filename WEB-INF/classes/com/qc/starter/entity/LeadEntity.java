package com.qc.starter.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="QT_CASE",schema="QC_PROSPECT")
public class LeadEntity implements Serializable {
		
	/**
	 * 
	 */
	@Transient
	private static final long serialVersionUID = 1L;

	@Id 
	@Column(name="CASE_ID")
	private String caseId;
	
	@Column(name="CASE_CODE")
	private String	caseCode;
	
	@Column(name="QUEUE_ID")
	private String queueId;
	
	@Column(name="SUB_QUEUE_ID")
	private String subQueueId;
	
	@Column(name="PROD_ID")
	private String productId;
	
	@Column(name="GENERATION_DT")
	private String generationDate;
	
	@Column(name="DISPOSITION_ID")
	private String dispositionId;
	
	@Column(name="ACTION_ID")
	private String action;
	
	private String amount;
	
	@Column(name="ALLOCATED_TO")
	private String allocatedTo;
	
	
	@Column(name="FACILITY_REQ_ID")
	private String facilityRequiredId;
	
	@Column(name="SOURCE_ID")
	private String sourceId;
	
	@Column(name="BANK_ID")
	private String bankId;
	
	@Column(name="CAMPAIGN_ID")
	private String campaignId;
	
	private String ROI;
	
	@Column(name="TENOR_YEAR")
	private String tenorYear;
	
	@Column(name="TENOR_MONTH")
	private String tenorMonth;
	
	private String EMI;
	
	@Column(name="TAG_INFO_A")
	private String tagInfoA;
	
	@Column(name="TAG_INFO_B")
	private String tagInfoB;
	
	@Column(name="COMPANY_ID")
	private String companyId;
	
	@Column(name="XSELL_CASE_ID")
	private String xsellCaseId;
	
	private String remarks;
	
	@Column(name="CREATED_BY")
	private String createdBy;
	
	@Column(name="CREATED_DATE")
	private String createdDate;
	
	@Column(name="CREATED_SYS_DATE")
	private String createdSystemDate;
	
	@Column(name="UPDATED_BY")
	private String updatedBy;
	
	@Column(name="UPDATED_DATE")
	private String updateDate;
	
	@Column(name="CO_ALLOCATE")
	private String coAllocate;

	@Column(name="REFER")
	private String refer;
	
	@Column(name="ESCALATE")
	private String escalate;
	
	@Column(name="STATUS")
	private String status;
	
	@Column(name="XSELL_BY")
	private String xsellBy;	
	///------------added by Deepak on 20 oct-2016 for make json of customer details into lead details
		@Column(name="PURPOSEOFLOANID")
		private String puposeOfLoan;
		//@Transient
		//private String applicantType;
		//@Transient
		//private	String constitution;
		@Transient
		private String stage;
		@Transient
		private String sector;
		//@Transient
		//private String custEntityTypeId;
		//@Transient
		//private	String custCategory;
		//@Transient
		//private String authSignatoryFName;
		//@Transient
		//private String authSignatoryMName;
		//@Transient
		//private String authSignatoryLName;
		//@Transient
		//private	String pan;
		//@Transient
		//private String industryId;
		//@Transient
		//private	Date incorperationDate;
		
		private String branchId;
		
		@Column(name="SCHEME_ID")
		private String schemeId;
		
		@Column(name="LOAN_AMOUNT")
		private String loanAmount;
		
		@Column(name="LOAN_TENURE")
		private String loanTenure;
		
		private String prospectId;

		private String applicantId;

		

		
		@Column(name="REFERENCE_NAME")
		private String referenceName;
		
		@Column(name="REFERENCE_NUMBER")
		private String referenceNum;


				
		public String getReferenceName() {
			return referenceName;
		}

		public void setReferenceName(String referenceName) {
			this.referenceName = referenceName;
		}

		public String getReferenceNum() {
			return referenceNum;
		}

		public void setReferenceNum(String referenceNum) {
			this.referenceNum = referenceNum;
		}

		public String getAction() {
			return action;
		}

		public void setAction(String action) {
			this.action = action;
		}

		public String getProspectId() {
			return prospectId;
		}

		public void setProspectId(String prospectId) {
			this.prospectId = prospectId;
		}

		public String getApplicantId() {
			return applicantId;
		}

		public void setApplicantId(String applicantId) {
			this.applicantId = applicantId;
		}

		public String getBranchId() {
			return branchId;
		}

		public void setBranchId(String branchId) {
			this.branchId = branchId;
		}

		public String getSchemeId() {
			return schemeId;
		}

		public void setSchemeId(String schemeId) {
			this.schemeId = schemeId;
		}

		public String getLoanAmount() {
			return loanAmount;
		}

		public void setLoanAmount(String loanAmount) {
			this.loanAmount = loanAmount;
		}

		public String getLoanTenure() {
			return loanTenure;
		}

		public void setLoanTenure(String loanTenure) {
			this.loanTenure = loanTenure;
		}

		public String getXsellBy() {
			return xsellBy;
		}

		public void setXsellBy(String xsellBy) {
			this.xsellBy = xsellBy;
		}

		public String getPuposeOfLoan() {
			return puposeOfLoan;
		}

		public void setPuposeOfLoan(String puposeOfLoan) {
			this.puposeOfLoan = puposeOfLoan;
		}

		/*public String getApplicantType() {
			return applicantType;
		}

		public void setApplicantType(String applicantType) {
			this.applicantType = applicantType;
		}

		public String getConstitution() {
			return constitution;
		}

		public void setConstitution(String constitution) {
			this.constitution = constitution;
		}*/

		public String getStage() {
			return stage;
		}

		public void setStage(String stage) {
			this.stage = stage;
		}

		public String getSector() {
			return sector;
		}

		public void setSector(String sector) {
			this.sector = sector;
		}

		/*public String getCustEntityTypeId() {
			return custEntityTypeId;
		}

		public void setCustEntityTypeId(String custEntityTypeId) {
			this.custEntityTypeId = custEntityTypeId;
		}

		public String getCustCategory() {
			return custCategory;
		}

		public void setCustCategory(String custCategory) {
			this.custCategory = custCategory;
		}

		public String getAuthSignatoryFName() {
			return authSignatoryFName;
		}

		public void setAuthSignatoryFName(String authSignatoryFName) {
			this.authSignatoryFName = authSignatoryFName;
		}

		public String getAuthSignatoryMName() {
			return authSignatoryMName;
		}

		public void setAuthSignatoryMName(String authSignatoryMName) {
			this.authSignatoryMName = authSignatoryMName;
		}

		public String getAuthSignatoryLName() {
			return authSignatoryLName;
		}

		public void setAuthSignatoryLName(String authSignatoryLName) {
			this.authSignatoryLName = authSignatoryLName;
		}

		public String getPan() {
			return pan;
		}

		public void setPan(String pan) {
			this.pan = pan;
		}

		public String getIndustryId() {
			return industryId;
		}

		public void setIndustryId(String industryId) {
			this.industryId = industryId;
		}

		public Date getIncorperationDate() {
			return incorperationDate;
		}

		public void setIncorperationDate(Date incorperationDate) {
			this.incorperationDate = incorperationDate;
		}*/

		public static long getSerialversionuid() {
			return serialVersionUID;
		}

	//--------------------------------------------
	public String getXsellCaseId() {
		return xsellCaseId;
	}

	public void setXsellCaseId(String xsellCaseId) {
		this.xsellCaseId = xsellCaseId;
	}

	public String getCoAllocate() {
		return coAllocate;
	}

	public void setCoAllocate(String coAllocate) {
		this.coAllocate = coAllocate;
	}

	public String getRefer() {
		return refer;
	}

	public void setRefer(String refer) {
		this.refer = refer;
	}

	public String getEscalate() {
		return escalate;
	}

	public void setEscalate(String escalate) {
		this.escalate = escalate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getXsellby() {
		return xsellBy;
	}

	public void setXsellby(String xsellBy) {
		this.xsellBy = xsellBy;
	}

	/**
	 * @return the caseId
	 */
	public String getCaseId() {
		return caseId;
	}

	/**
	 * @param caseId the caseId to set
	 */
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	/**
	 * @return the caseCode
	 */
	public String getCaseCode() {
		return caseCode;
	}

	/**
	 * @param caseCode the caseCode to set
	 */
	public void setCaseCode(String caseCode) {
		this.caseCode = caseCode;
	}

	
	
	

	/**
	 * @return the queueId
	 */
	public String getQueueId() {
		return queueId;
	}

	/**
	 * @param queueId the queueId to set
	 */
	public void setQueueId(String queueId) {
		this.queueId = queueId;
	}

	/**
	 * @return the subQueueId
	 */
	public String getSubQueueId() {
		return subQueueId;
	}

	/**
	 * @param subQueueId the subQueueId to set
	 */
	public void setSubQueueId(String subQueueId) {
		this.subQueueId = subQueueId;
	}

	/**
	 * @return the productId
	 */
	public String getProductId() {
		return productId;
	}

	/**
	 * @param productId the productId to set
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}

	
	/**
	 * @return the generationDate
	 */
	public String getGenerationDate() {
		return generationDate;
	}

	/**
	 * @param generationDate the generationDate to set
	 */
	public void setGenerationDate(String generationDate) {
		this.generationDate = generationDate;
	}

	/**
	 * @return the dispositionId
	 */
	public String getDispositionId() {
		return dispositionId;
	}

	/**
	 * @param dispositionId the dispositionId to set
	 */
	public void setDispositionId(String dispositionId) {
		this.dispositionId = dispositionId;
	}

	/**
	 * @return the amount
	 */
	public String getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(String amount) {
		this.amount = amount;
	}

	/**
	 * @return the allocatedTo
	 */
	public String getAllocatedTo() {
		return allocatedTo;
	}

	/**
	 * @param allocatedTo the allocatedTo to set
	 */
	public void setAllocatedTo(String allocatedTo) {
		this.allocatedTo = allocatedTo;
	}

	/**
	 * @return the facilityRequiredId
	 */
	public String getFacilityRequiredId() {
		return facilityRequiredId;
	}

	/**
	 * @param facilityRequiredId the facilityRequiredId to set
	 */
	public void setFacilityRequiredId(String facilityRequiredId) {
		this.facilityRequiredId = facilityRequiredId;
	}

	/**
	 * @return the sourceId
	 */
	public String getSourceId() {
		return sourceId;
	}

	/**
	 * @param sourceId the sourceId to set
	 */
	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	/**
	 * @return the bankId
	 */
	public String getBankId() {
		return bankId;
	}

	/**
	 * @param bankId the bankId to set
	 */
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	/**
	 * @return the campaignId
	 */
	public String getCampaignId() {
		return campaignId;
	}

	/**
	 * @param campaignId the campaignId to set
	 */
	public void setCampaignId(String campaignId) {
		this.campaignId = campaignId;
	}

	/**
	 * @return the rOI
	 */
	public String getROI() {
		return ROI;
	}

	/**
	 * @param rOI the rOI to set
	 */
	public void setROI(String rOI) {
		ROI = rOI;
	}

	/**
	 * @return the tenorYear
	 */
	public String getTenorYear() {
		return tenorYear;
	}

	/**
	 * @param tenorYear the tenorYear to set
	 */
	public void setTenorYear(String tenorYear) {
		this.tenorYear = tenorYear;
	}

	/**
	 * @return the tenorMonth
	 */
	public String getTenorMonth() {
		return tenorMonth;
	}

	/**
	 * @param tenorMonth the tenorMonth to set
	 */
	public void setTenorMonth(String tenorMonth) {
		this.tenorMonth = tenorMonth;
	}

	/**
	 * @return the eMI
	 */
	public String getEMI() {
		return EMI;
	}

	/**
	 * @param eMI the eMI to set
	 */
	public void setEMI(String eMI) {
		EMI = eMI;
	}

	/**
	 * @return the tagInfoA
	 */
	public String getTagInfoA() {
		return tagInfoA;
	}

	/**
	 * @param tagInfoA the tagInfoA to set
	 */
	public void setTagInfoA(String tagInfoA) {
		this.tagInfoA = tagInfoA;
	}

	/**
	 * @return the tagInfoB
	 */
	public String getTagInfoB() {
		return tagInfoB;
	}

	/**
	 * @param tagInfoB the tagInfoB to set
	 */
	public void setTagInfoB(String tagInfoB) {
		this.tagInfoB = tagInfoB;
	}

	/**
	 * @return the companyId
	 */
	public String getCompanyId() {
		return companyId;
	}

	/**
	 * @param companyId the companyId to set
	 */
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	/**
	 * @return the referCaseId
	 */
	public String getReferCaseId() {
		return xsellCaseId;
	}

	/**
	 * @param referCaseId the referCaseId to set
	 */
	public void setReferCaseId(String xsellCaseId) {
		this.xsellCaseId = xsellCaseId;
	}

	/**
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the createdDate
	 */
	public String getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the createdSystemDate
	 */
	public String getCreatedSystemDate() {
		return createdSystemDate;
	}

	/**
	 * @param createdSystemDate the createdSystemDate to set
	 */
	public void setCreatedSystemDate(String createdSystemDate) {
		this.createdSystemDate = createdSystemDate;
	}

	/**
	 * @return the updatedBy
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}

	/**
	 * @param updatedBy the updatedBy to set
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	/**
	 * @return the updateDate
	 */
	public String getUpdateDate() {
		return updateDate;
	}

	/**
	 * @param updateDate the updateDate to set
	 */
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}	
}
