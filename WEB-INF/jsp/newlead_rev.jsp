<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script src="resources/js/globalValidation.js" type="text/javascript"></script>
<script src="resources/js/myValidation.js" type="text/javascript"></script>
<script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
<script type="text/javascript" src="resources/js/ui/custom.js"></script>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!--[if IE 8]>

<style type="text/css">
.addmobilenodiv, .emailbtndiv{top: 0 !important}
.add-phoneno, .add-emaillid {
    background-position: left 1px !important
}
select{padding-right: 0 !important}

input[type=text] , #datepicker2{
    border-color: #c7c7c7 !important;
    border-width: 1px !important;
    border-style: solid !important;
    border-radius: 3px;
    padding: 2px 5px 3px 5px;}
    
 .contactHeader {
margin: 35px 0 0 !important;
}
.contactHeader .mainHeading h1 {
padding: 15px 15px 0 15px;
}
#contactNo1{
	width: 21.5% !important
}
.saveBtnList ul li .subheader-btn {
border-right: 0px solid #0889C4 !important;
}
</style>

      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
<spring:message code="label.personalDetails" />
<form:form method="post" action="" modelAttribute="existingFacilityForm"
	id="newLeadForm" onsubmit="return checkValidation();">
	<input type="hidden" name="requestType" value="" id="requestType" />
	<div class="container-fluid">
		<div
			class="padding-top-container new-top col-lg-12 col-md-12 col-sm-12 col-xs-12 newleadtop">
			<div class="row buttonHeading ">
				<div class="col-lg-9 col-md-9 col-sm-9 col-xs-10">
					<h2 class="heading">
						<spring:message code="label.personalDetails" />
					</h2>
				</div>
				<div class="col-lg-3 col-md-3 col-sm-3 col-xs-2 btnPosition">
					<a class="toggle-list btn-primary"> <span
						class="toggle-icon minus-icon1 "></span>
					</a>
				</div>
			</div>
			<div class="row contactList leadstatus">
				<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12 ">
					<label><spring:message code="label.entityType" /></label><select
						name="entityType" id="entityType" onchange="incomeDetailsfields()"
						class="validField">
						<option value="-1">select</option>
						<c:forEach var="entityObj"
							items="${customerMasterDetail.entityTypeMaster}">
							<option value="${entityObj.custEntityTypeId}">${entityObj.displayName}</option>
						</c:forEach>
					</select>
				</div>

				<!-- <div
					class="row contactList leadstatus personalDetailForNonIndividual hidden"> -->
					<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12" id="personalDetailForNonIndividual">
						<label><spring:message code="label.constitution" /></label> <select
							name="constitution" id="constitution" class="validField"
							onchange="incomeDetailsfields();" id="constitution">
							<option value="-1">select</option>
							<c:forEach var="occupation"
								items="${customerMasterDetail.occupationType}">
								<option value="${occupation.occupationid }">${occupation.occupationname}</option>
							</c:forEach>
						</select>
					</div>

					<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
						<label><spring:message code="label.custCategory" /></label> <select
							name="customerCategory">
							<option value="-1">select</option>
							<c:forEach var="obj"
								items="${customerMasterDetail.customerCategryMaster}">
								<option value="${obj.customercategortId }">${obj.displayName}</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
						<label><spring:message code="label.authF" /></label> <input
							type="text" name="authSignatoryFName" value="">
					</div>
					<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
						<label><spring:message code="label.authM" /></label> <input
							type="text" name="authSignatoryMName" value="">
					</div>
					<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
						<label><spring:message code="label.authL" /></label> <input
							type="text" name="authSignatoryLName" value="">
					</div>
					<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
						<label><spring:message code="label.dateInc" /></label> <input
							type="text" placeholder="Incorporation Date"
							name="dateOfIncorparation" id="datepicker1"
							onchange="validateDateIncorporation(this)" /><span
							class="dobCalc glyphicon glyphicon-calendar" for="datepicker1"
							aria-hidden="true"></span>
					</div>
					<%-- <div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
					<label><spring:message code="label.pan"/></label> <input type="text" value=""
						placeholder="PAN Card No" name="companyPanNo" onchange="return checkPAN(this);" maxlength="10">
				</div> --%>

					<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
						<label><spring:message code="label.stage" /></label> <select
							name="stageForNI">
							<option value="-1">select</option>
							<c:forEach var="obj" items="${customerMasterDetail.stageMaster}">
								<option value="${obj.stageId }">${obj.stageName}</option>
							</c:forEach>
						</select>
					</div>
					<!-- Added by Anuj on 30/12/2016 -->
					<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
						<label><spring:message code="label.sector" /></label> <select
							name="sectorForNI">
							<option value="-1">select</option>
							<c:forEach var="obj" items="${customerMasterDetail.sectorMaster}">
								<option value="${obj.sectorId }">${obj.sectorName}</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
						<label><spring:message code="label.industry" /></label> <select
							name="industryForNI" onchange=getTypeOfBusinessName(this,"NI")>
							<option value="-1">select</option>
							<c:forEach var="obj"
								items="${customerMasterDetail.industryMaster}">
								<option value="${obj.industryId}">${obj.displayName}</option>
							</c:forEach>
						</select>
					</div>
					<!-- Added by sumit on 10-July-2017 -->
					<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
						<label><spring:message code="label.typeOfBusiness" /></label> <select
							name="typeOfBusinessForNI" id="typeOfBusinessForNI"
							onchange=getClusterName(this,"NI")>
							<option value="-1">select</option>
							<c:forEach var="obj"
								items="${customerMasterDetail.typeOfBusinessMaster}">
								<option value="${obj.id}">${obj.typeOfBusiness}</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
						<label><spring:message code="label.cluster" /></label> <select
							name="clusterForNI">
							<option value="-1">select</option>
							<c:forEach var="obj"
								items="${customerMasterDetail.clusterMaster}">
								<option value="${obj.clusterId}">${obj.clusterName}</option>
							</c:forEach>
						</select>
					</div>
					<!-- End -->
				<!-- </div> -->
				
				<!-- <div class="row leadstatus personalDetailForIndividual hidden"> -->
				<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12" id="personalDetailForIndividual">
					<label><spring:message code="label.constitution" /></label> <select
						name="occupationType" id="occupationTypeId" class="validField"
						onchange="incomeDetailsfields();">
						<option value="-1">select</option>
						<c:forEach var="occupation"
							items="${customerMasterDetail.occupationType}">
							<option value="${occupation.occupationid }">${occupation.occupationname}</option>
						</c:forEach>
					</select>
				</div>
				<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
					<label><spring:message code="label.applicatType" /></label> <select
						name="companyType">
						<option value="-1">select</option>
						<c:forEach var="companyType"
							items="${customerMasterDetail.companyType}">
							<option value="${companyType.compTypeId }">${companyType.companyType}</option>
						</c:forEach>
					</select>
				</div>
				<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
					<label><spring:message code="label.fName" /><span>*</span></label>
					<input type="text" name="firstName" placeholder="First Name"
						id="firstName1" class="validField"
						onkeypress="return onlyCarector(event);" maxlength="50" />

				</div>
				<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
					<label><spring:message code="label.mName" /></label> <input
						type="text" value="" placeholder="Middle Name" name="middleName"
						onkeypress="return onlyCarector(event);" maxlength="50" />
				</div>
				<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
					<label><spring:message code="label.lName" /> </label> <input
						type="text" value="" name="lastName" placeholder="Last Name"
						onkeypress="return onlyCarector(event);" maxlength="50">
				</div>
				<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
					<label><spring:message code="label.marital" /></label> <select
						name="maritalStatus">
						<option value="-1">select</option>
						<c:forEach var="marital"
							items="${customerMasterDetail.maritalStatus }">
							<option value="${marital.maritalStatusid }">${marital.maritalStatusname}</option>
						</c:forEach>
					</select>
				</div>
				<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
					<label><spring:message code="label.dob" /></label> <input
						type="text" name="dob" id="datepicker2"
						placeholder="Date of Birth" onchange="validateBirthDate(this)">
					<span style="top: 0px;"
						class="dobCalc glyphicon glyphicon-calendar" for="datepicker2"
						aria-hidden="true"></span>
				</div>
				<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
					<label><spring:message code="label.gender" /></label> <select
						name="gender">
						<option value="-1">Select</option>
						<c:forEach var="gender" items="${customerMasterDetail.gender }">
							<option value="${gender.genderId }">${gender.genderName	}</option>
						</c:forEach>
					</select>
				</div>
				<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
					<label><spring:message code="label.nationality" /></label> <select
						name="nationality">
						<option value="-1">Select</option>
						<c:forEach var="nationality"
							items="${customerMasterDetail.nationality }">
							<option value="${nationality.nationalityId }">${nationality.nationName
								}</option>
						</c:forEach>
					</select>
				</div>
				<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
					<label><spring:message code="label.dependents" /></label> <input
						type="text" name="noOfDependents" maxlength="2"
						onkeypress="return onlyNumeric(event);" />
				</div>

				<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
					<label><spring:message code="label.pan" /></label> <input
						type="text" value="" placeholder="PAN Card No" name="pan"
						onchange="return checkPAN(this);" maxlength="10">

				</div>
				<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
					<label><spring:message code="label.aadhar" /></label> <input
						type="text" value="" name="adhaarNumber"
						onchange="validateAdharNo(this);" placeholder="Aadhar No."
						onkeypress="return onlyNumeric(event);" maxlength="12">
				</div>
				<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
					<label><spring:message code="label.stage" /></label> <select
						name="stage">
						<option value="-1">select</option>
						<c:forEach var="obj" items="${customerMasterDetail.stageMaster}">
							<option value="${obj.stageId }">${obj.stageName}</option>
						</c:forEach>
					</select>
				</div>
				<!-- Added by Anuj on 30/12/2016 -->
				<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
					<label><spring:message code="label.sector" /></label> <select
						name="sector">
						<option value="-1">select</option>
						<c:forEach var="obj" items="${customerMasterDetail.sectorMaster}">
							<option value="${obj.sectorId }">${obj.sectorName}</option>
						</c:forEach>
					</select>
				</div>
				<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
					<label><spring:message code="label.industry" /></label> <select
						name="industry" onchange=getTypeOfBusinessName(this,"I")>
						<option value="-1">select</option>
						<c:forEach var="obj"
							items="${customerMasterDetail.industryMaster}">
							<option value="${obj.industryId }">${obj.displayName}</option>
						</c:forEach>
					</select>
				</div>
				<!-- Added by sumit on 10-July-2017 -->
				<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
					<label><spring:message code="label.typeOfBusiness" /></label> <select
						name="typeOfBusiness" onchange=getClusterName(this,"I")>
						<option value="-1">select</option>
						<c:forEach var="obj"
							items="${customerMasterDetail.typeOfBusinessMaster}">
							<option value="${obj.id}">${obj.typeOfBusiness}</option>
						</c:forEach>
					</select>
				</div>
				<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
					<label><spring:message code="label.cluster" /></label> <select
						name="cluster">
						<option value="-1">select</option>
						<c:forEach var="obj" items="${customerMasterDetail.clusterMaster}">
							<option value="${obj.clusterId }">${obj.clusterName}</option>
						</c:forEach>
					</select>
				</div>
				<!-- End -->
				<!-- <div class="col-lg-3 col-md-3 col-sm-3 col-xs-6">
					<label>Tag A</label> <input type="text" value=""
						placeholder="Tag A">
				</div>
				</div>
				<div class="col-lg-3 col-md-3 col-sm-3 col-xs-6">
					<label>Tag B</label> <input type="text" value=""
						placeholder="Tag B">
				</div> -->
				
			<!-- </div> -->


			</div>

			
			<div class="row buttonHeading">
				<div class="col-lg-9 col-md-9 col-sm-9 col-xs-10">
					<h2 class="heading">Contact Details</h2>
				</div>
				<div class="col-lg-3 col-md-3 col-sm-3 col-xs-2 btnPosition">
					<a class="toggle-list btn-primary"> <span
						class="toggle-icon minus-icon1 "></span>
					</a>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-6 col-md-12 col-sm-12 col-xs-12 add-phoneno">

					<div id="rowContactMobile">
						<div class="row contactAddres mobile contact-details-width">
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
								<input type="checkbox" placeholder="Address" id="completRow"
									checked /> <label><spring:message code="label.type" /></label>
								<select name="listMobile[0].mobileContactTypeId"
									id="mobileType1" class="validField">
									<option value="-1">Select</option>
									<c:forEach var="contact3"
										items="${customerMasterDetail.contactTypeMobile }">
										<option value="${contact3.contactTypeId}">${contact3.contactTypeName}</option>
									</c:forEach>
								</select> <label><spring:message code="label.mobile" /><span>*</span></label><input
									type="text" value="" name="listMobile[0].contactNo"
									onchange="validateMobile(this);" id="contactNo1"
									onkeypress="return onlyNumeric(event);"
									class="validField set-input-width rgtAlign" maxlength="10"
									style="left: -5px; position: relative; width: 21.5% !important;" />
								<input type="button" value="Y"
									style="position: relative; left: -5px;" /><input type="hidden"
									name="listMobile[0].primaryContact" value="Y" />

								<!-- <input type="button" name="Set as Default" value="Set as Default"
							class="btn-primary">  -->

								<button name="Delete" value="1"
									class="delrow-contacts btn-primary button-delete"
									style="float: right" id="mobileDel">Delete</button>
								<button name="add" value="1"
									class="newrow-contacts btn-primary button-add"
									style="float: right" id="mobileAdd">Add</button>
							</div>
						</div>
					</div>

				</div>
				<div class="col-lg-6 col-md-12 col-sm-12 col-xs-12 add-emaillid">
					<div id="rowContactEmail">
						<div class="row contactAddres email">
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
								<input type="checkbox" placeholder="Address" id="emailRow"
									checked /> <label><spring:message code="label.type" /></label>
								<select class="emailtype1"
									name="listEmail[0].emailContactTypeId">
									<option value="-1">select</option>
									<c:forEach var="contact4"
										items="${customerMasterDetail.contactTypeEmail }">
										<option value="${contact4.contactTypeId }">${contact4.contactTypeName}</option>
									</c:forEach>
								</select> <label><spring:message code="label.email" /></label> <input
									type="text" value="" style="width: 21.5% !important"
									maxlength="100" name="listEmail[0].email"
									onchange="validateEmail(this);" /><input value="Y"
									type="button"
									style="font-size: 14px; margin-left: -22px !important" /> <input
									type="hidden" name="listEmail[0].primaryEmail" value="Y" />
								<button name="Delete" value="Delete"
									class="delrow-email btn-primary button-delete" id="emailDel"
									style="float: right" value="1">Delete</button>
								<button name="add" value="1"
									class="newrow-email btn-primary button-add"
									style="float: right" id="emailAdd">Add</button>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="incomedetails hidden">
				<div class="row buttonHeading">
					<div class="col-lg-9 col-md-9 col-sm-9 col-xs-10">
						<h2 class="heading">Income Details</h2>
					</div>
					<div class="col-lg-3 col-md-3 col-sm-3 col-xs-2 btnPosition">
						<a class="btn-primary  toggle-list"> <span
							class="toggle-icon minus-icon1 "></span>
						</a>
					</div>
				</div>
				<div class="row contactList leadstatus mrgnLead">
					<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12 companyClass">
						<label><spring:message code="label.company" /></label> <input
							type="text" id="companyName" name="companyName" />
						<%-- <select name="comanpyName">
						<option value="-1">select</option>
						<c:forEach var="company"
							items="${customerMasterDetail.companyName}">
							<option value="${company.caseCompanyId }">${company.displayName}</option>
						</c:forEach>
					</select> --%>
						<div class="textHide ">
							<input type="text" placeholder="" name="otherCompanyName" />
						</div>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12 field1">
						<label><spring:message code="label.designation" /></label> <input
							type="text" name="designation">
						<!-- <select name="designation">
						<option>CEO</option>
					</select> -->
					</div>
					<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12 field1">
						<label><spring:message code="label.modeOfSalary" /></label> <select
							name="modeOfSalary">
							<option value="-1">select</option>
							<c:forEach var="salary"
								items="${customerMasterDetail.salaryMode}">
								<option value="${salary.salaryModeId }">${salary.salaryMode}</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12 field1">
						<label><spring:message code="label.years" /></label> <input
							type="text" placeholder="Years" name="yearOfCurrJob"
							class="rgtAlign" onkeypress="return onlyNumeric(event);"
							maxlength="2" />
					</div>
					<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12 field1">
						<label><spring:message code="label.work" /></label> <input
							type="text" placeholder="Years" name="workExperiance"
							class="rgtAlign" onkeypress="return onlyNumeric(event);"
							maxlength="2" />
					</div>
					<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12 field2">
						<label><spring:message code="label.ctc" /></label> <input
							type="text" class="rgtAlign" placeholder="CTC/Annual Income"
							name="annualIncome" onkeypress="return onlyNumeric(event);"
							maxlength="13" />
					</div>

					<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12 field2">
						<label><spring:message code="label.gross" /></label> <input
							type="text" class="rgtAlign" placeholder="Gross Monthly Income"
							name="grossMonthlyIncome" onkeypress="return onlyNumeric(event);"
							maxlength="13" />
					</div>
					<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12 field2">
						<label><spring:message code="label.netMIn" /></label> <input
							type="text" placeholder="Net Monthly Income"
							name="netMonthlyIncome" onkeypress="return onlyNumeric(event);"
							maxlength="13" class="rgtAlign" />
					</div>
					<!-- <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
					<label>Other Monthly Income</label> <input type="text"
						placeholder="Other Monthly Income" name="otherMonthlyIncome" onkeypress="return onlyNumeric(event);"/>
				</div> -->
					<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12 field1">
						<label><spring:message code="label.bonus" /></label> <input
							type="text" placeholder="Monthly Bonus" name="bonusIncentive"
							maxlength="13" onchange="return checkAfter10DigitDecimals(this);"
							class="rgtAlign" />
					</div>
					<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
						<label><spring:message code="label.rental" /></label> <input
							type="text" class="rgtAlign" placeholder="Monthly Rental Income"
							name="monthlyRentalIncome"
							onkeypress="return onlyNumeric(event);" maxlength="13" />
					</div>
					<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12 field3">
						<label><spring:message code="label.annualSale" /></label> <input
							type="text" class="rgtAlign" placeholder="Annual Sales Turnover"
							name="annualSalesTurnOver"
							onkeypress="return onlyNumeric(event);" maxlength="13" />
					</div>
					<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12 field3">
						<label><spring:message code="label.profit" /></label> <input
							type="text" placeholder="Gross Profit" name="grossProfit"
							onkeypress="return onlyNumeric(event);" maxlength="13"
							class="rgtAlign" />
					</div>
					<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12 field3">
						<label><spring:message code="label.netProfitTax" /></label> <input
							type="text" class="rgtAlign" placeholder="Net Profit After Tax"
							name="netProfitAtTax" onkeypress="return onlyNumeric(event);"
							maxlength="13" />
					</div>
					<!-- <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
					<label>Annual Rental Income</label> <input type="text"
						placeholder="Annual Rental Income" name="annualRentalIncome" onkeypress="return onlyNumeric(event);" maxlength="13"/>
				</div> -->
					<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12 field3">
						<label><spring:message code="label.deprication" /></label> <input
							type="text" placeholder="Depriciation" name="depreciation"
							maxlength="13" onchange="return checkAfter10DigitDecimals(this);"
							class="rgtAlign" />
					</div>
					<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
						<label><spring:message code="label.otherAnnual" /></label> <input
							type="text" class="rgtAlign"
							placeholder="Other Annual Addback Income"
							name="otherAnnualIncome" onkeypress="return onlyNumeric(event);"
							maxlength="13" />
					</div>
					<!-- <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
					<label>Date of Incorporation</label> <input type="text"
						placeholder="Incorporation Date" name="dateOfIncorparation" id="datepicker1" onchange="validateDateIncorporation(this)"/><span
						class="dobCalc glyphicon glyphicon-calendar" for="datepicker1"
						aria-hidden="true"></span>
				</div> -->
					<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12 field3">
						<label><spring:message code="label.worth" /></label> <input
							type="text" placeholder="Net Worth" name="netWorth"
							onkeypress="return onlyNumeric(event);" maxlength="13"
							class="rgtAlign" />
					</div>
					<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12 field1">
						<label><spring:message code="label.corp" /></label> <select
							name="corpSalaryAcount">
							<option value="Y">Yes</option>
							<option value="N">No</option>
						</select>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12 field3">
						<label><spring:message code="label.directorSalary" /></label> <input
							type="text" placeholder="Director Salary" name="directorSalary"
							maxlength="13" onchange="return checkAfter10DigitDecimals(this);"
							class="rgtAlign" />
					</div>
					<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
						<label><spring:message code="label.intrestPaid" /></label> <input
							type="text" placeholder="Interest Paid On Loan"
							name="interesrPaidOnLoan" maxlength="13"
							onchange="return checkAfter10DigitDecimals(this);"
							class="rgtAlign" />
					</div>
				</div>
			</div>
			<div id="residenceAddress">
				<div class="row buttonHeading">
					<div class="col-lg-9 col-md-9 col-sm-9 col-xs-6">
						<h2 class="heading">Address Details</h2>
					</div>
					<div class="col-lg-3 col-md-3 col-sm-3 col-xs-6 btnPosition">
						<a class="toggle-list btn-primary"> <span
							class="toggle-icon minus-icon1 "></span></a>
						<button type="button" class="btn btn-primary delrow-detail ">Delete</button>
						<button type="button" class="btn btn-primary addrow-detail"
							value="1">Add</button>
					</div>
				</div>
				<div class="row contactList leadstatus">
					<div class="AddressSection">
						<div class="mailingAddressDiv">
							<div class="deletchk">
								<input type="checkbox" placeholder="checkbox" value="default"
									name="listAddress[0].addressId" checked />
							</div>
							<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
								<label><spring:message code="label.addressType" /></label> <select
									name="listAddress[0].addressType">
									<option value="-1">Select</option>
									<c:forEach var="addressType"
										items="${customerMasterDetail.addressType }">
										<option value="${addressType.addressTypeId }">${addressType.addressTypeDisplayName}</option>
									</c:forEach>
								</select>
							</div>
							<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12 addressField ">
								<label class="minWidth"><spring:message
										code="label.address" /></label>
								<!-- <textarea name="listAddress[0].address"
								maxlength="50" placeholder="Address" class="textarea_addressdetails"></textarea>
								-->
								<input type="text" value="" name="listAddress[0].address"
									placeholder="Address"
									class="col-lg-3 col-md-3 col-sm-3 col-xs-3 addressinput inputmytop"
									id="addressInput" />
							</div>
							<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
								<label><spring:message code="label.mailingAddress" /></label> <input
									type="checkbox" placeholder="checkbox"
									name="listAddress[0].mailingAddress" />
							</div>
							<!-- <div class="clearfix"></div> -->
							<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
								<label><spring:message code="label.state" /></label> <select
									name="listAddress[0].state" id="listAddress[0].state"
									onchange="getCitiesByState(this);">
									<option value="-1">Select</option>
									<c:forEach var="state"
										items="${customerMasterDetail.stateList }">
										<option value="${state.stateMasterId }">${state.displayName}</option>
									</c:forEach>
								</select>
							</div>
							<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
								<label><spring:message code="label.city" /></label> <select
									name="listAddress[0].city">
									<option value="-1">select</option>
									<c:forEach var="city" items="${customerMasterDetail.city }">
										<option value="${city.cityMasterId }">${city.displayName}</option>
									</c:forEach>
								</select>
							</div>
							<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
								<label><spring:message code="label.pin" /></label> <input
									type="text" value="" placeholder="PIN"
									name="listAddress[0].zipcode" onchange="validatePinCode(this);"
									maxlength="6" onkeypress="return onlyNumeric(event);"
									class="rgtAlign" />
							</div>
							<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
								<label><spring:message code="label.landmark" /></label> <input
									type="text" placeholder="Landmark" value=""
									name="listAddress[0].landmark" maxlength="200" />
							</div>
							<!--<div class="clearfix"></div>-->
							<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
								<label><spring:message code="label.landline" /></label> <input
									type="text" placeholder="Landline" value=""
									name="listAddress[0].phone1"
									onkeypress="return onlyNumeric(event);" maxlength="12"
									class="rgtAlign" />
							</div>
							<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
								<label><spring:message code="label.extension" /></label> <input
									type="text" placeholder="Extension" value="" maxlength="5"
									name="listAddress[0].ext1"
									onkeypress="return onlyNumeric(event);" class="rgtAlign" />
							</div>
							<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
								<label><spring:message code="label.livingSince" /></label> <input
									type="text" class="minWidth rgtAlign" value=""
									placeholder="Months" maxlength="15" id="month"
									name="listAddress[0].occupancyMm" list="monthList"
									onchange="validationForMonth(this)" /> <input type="text"
									class="minWidth rgtAlign" value="" placeholder="Years"
									maxlength="4" onchange="validateYear(this)" id="year"
									name="listAddress[0].occupancyYr"
									onkeypress="return onlyNumeric(event);" list="yearList" />

								<datalist id="monthList">
									<option value="1">January</option>
									<option value="2">February</option>
									<option value="3">March</option>
									<option value="4">April</option>
									<option value="5">May</option>
									<option value="6">June</option>
									<option value="7">July</option>
									<option value="8">August</option>
									<option value="9">September</option>
									<option value="10">October</option>
									<option value="11">November</option>
									<option value="12">December</option>
								</datalist>

								<datalist id="yearList">
									<option value="1940" />
									<option value="1941" />
									<option value="1942" />
									<option value="1943" />
									<option value="1944" />
									<option value="1945" />
									<option value="1946" />
									<option value="1947" />
									<option value="1948" />
									<option value="1949" />
									<option value="1950" />
									<option value="1951" />
									<option value="1952" />
									<option value="1950" />
									<option value="1951" />
									<option value="1952" />
									<option value="1953" />
									<option value="1954" />
									<option value="1955" />
									<option value="1956" />
									<option value="1957" />
									<option value="1958" />
									<option value="1959" />
									<option value="1960" />
									<option value="1961" />
									<option value="1962" />
									<option value="1963" />
									<option value="1964" />
									<option value="1965" />
									<option value="1966" />
									<option value="1967" />
									<option value="1968" />
									<option value="1969" />
									<option value="1970" />
									<option value="1971" />
									<option value="1972" />
									<option value="1973" />
									<option value="1974" />
									<option value="1975" />
									<option value="1976" />
									<option value="1977" />
									<option value="1978" />
									<option value="1979" />
									<option value="1980" />
									<option value="1981" />
									<option value="1982" />
									<option value="1983" />
									<option value="1984" />
									<option value="1985" />
									<option value="1986" />
									<option value="1987" />
									<option value="1988" />
									<option value="1989" />
									<option value="1990" />
									<option value="1991" />
									<option value="1992" />
									<option value="1993" />
									<option value="1994" />
									<option value="1995" />
									<option value="1996" />
									<option value="1997" />
									<option value="1998" />
									<option value="1999" />
									<option value="2000" />
									<option value="2001" />
									<option value="2002" />
									<option value="2003" />
									<option value="2004" />
									<option value="2005" />
									<option value="2006" />
									<option value="2007" />
									<option value="2008" />
									<option value="2009" />
									<option value="2010" />
									<option value="2011" />
									<option value="2012" />
									<option value="2013" />
									<option value="2014" />
									<option value="2015" />
									<option value="2016" />
									<option value="2017" />
								</datalist>

							</div>
							<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
								<label><spring:message code="label.occupation" /></label> <select
									name="listAddress[0].occupancyStatus">
									<option value="-1">select</option>
									<c:forEach var="status"
										items="${customerMasterDetail.occupancyStatus }">
										<option value="${status.occupancyStId }">${status.occupancyStName}</option>
									</c:forEach>
								</select>
							</div>
							<hr />
						</div>
					</div>
				</div>
			</div>


			<div class="row buttonHeading">
				<div class="col-lg-9 col-md-9 col-sm-9 col-xs-6">
					<h2 class="heading">Key Contacts</h2>
				</div>
				<div class="col-lg-3 col-md-3 col-sm-3 col-xs-6 btnPosition">
					<a class="toggle-list btn-primary "> <span
						class="toggle-icon minus-icon1 "></span>
					</a>
					<button type="button" class="btn btn-primary delrow ">Delete</button>
					<button type="button" class="btn btn-primary addrow-keycontacts"
						value="1">Add</button>

				</div>
			</div>
			<div class="row keycontact_table">
				<div class="bs-example">
					<table class="table table-striped ">
						<thead>
							<tr>
								<th>#</th>
								<th style="min-width: 200px !important"><spring:message
										code="label.contactType" /></th>
								<th><spring:message code="label.name" /></th>
								<th><spring:message code="label.firmName" /></th>
								<th><spring:message code="label.mobile" /></th>
								<th><spring:message code="label.email" /></th>
								<th><spring:message code="label.address" /></th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td width="1%"><input type="checkbox" checked /></td>
								<td><select name="listKeyContact[0].contactTypeId">
										<option value="-1">select</option>
										<c:forEach var="contact"
											items="${customerMasterDetail.contact }">
											<option value="${contact.contactTypeId }">${contact.contactType}</option>
										</c:forEach>
								</select></td>
								<td><input type="text" value=""
									name="listKeyContact[0].fname"
									onkeypress="return onlyCarector(event);" maxlength="50" /></td>
								<td><input type="text" value=""
									name="listKeyContact[0].firmName"
									onkeypress="return onlyCarector(event);" maxlength="50" /></td>
								<td><input type="text" value="" class="rgtAlign"
									name="listKeyContact[0].mobile"
									onchange="validateMobile(this);"
									onkeypress="return onlyNumeric(event);" maxlength="10" /></td>
								<td><input type="text" value=""
									name="listKeyContact[0].email" onchange="validateEmail(this);"
									maxlength="100" /></td>
								<td><input type="text" name="listKeyContact[0].address"
									maxlength="200" /></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div class="row buttonHeading">
				<div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">
					<h2 class="heading">Lead Details</h2>
				</div>
			</div>
			<div class="row contactList leadstatus">

				<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
					<label><spring:message code="label.product" /><span>*</span></label>
					<select name="productId" id="product1" class="validField">
						<option value="-1">select</option>
						<c:forEach var="product"
							items="${customerMasterDetail.productMaster}">
							<c:if test="${product.prodTypeId=='1000000001'}">
								<option value="${product.prodId}">${product.prodName}</option>
							</c:if>
						</c:forEach>
					</select>
				</div>

				<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
					<label>Purpose Of Loan</label> <select name="purposeOfLoan"
						id="purposeOfLoan" class="validField">
						<option value="-1">select</option>
						<c:forEach var="purposeOfLoan"
							items="${customerMasterDetail.purposeOfLoanMaster}">
							<option value="${purposeOfLoan.purposeOfLoanId }">${purposeOfLoan.purposeOfLoanName}</option>
						</c:forEach>
					</select>
				</div>

				<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
					<label><spring:message code="label.subQueue" /></label><select
						name="queueId" id="queue1" class="">
						<option value="-1">select</option>
						<c:forEach var="sub"
							items="${customerMasterDetail.subQueueMaster }">
							<option value="${sub.subQueueId}">${sub.subQueue}</option>
						</c:forEach>
					</select>
				</div>

				<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
					<label><spring:message code="label.source" /></label> <select
						name="source" id="source1" class="">
						<option value="-1">select</option>
						<c:forEach var="prod" items="${customerMasterDetail.sourceMaster}">
							<option value="${prod.caseSourceId }">${prod.sourceName}</option>
						</c:forEach>
					</select>
				</div>

				<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
					<label><spring:message code="label.campaign" /></label> <select
						name="campaign" id="campaign1" class="">
						<option value="-1">select</option>
						<c:forEach var="campaign"
							items="${customerMasterDetail.campaignMaster }">
							<option value="${campaign.campaignId }">${campaign.campaignName}</option>
						</c:forEach>
					</select>
				</div>

				<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
					<label><spring:message code="label.branch" /></label> <select
						name="branch" id="branch" class="">
						<option value="-1">select</option>
						<c:forEach var="branch"
							items="${customerMasterDetail.branchMaster }">
							<option value="${branch.geoId }">${branch.geoName}</option>
						</c:forEach>
					</select>
				</div>


				<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
					<label><spring:message code="label.scheme" /></label> <select
						name="scheme" id="scheme" class="">
						<option value="-1">select</option>
						<c:forEach var="scheme"
							items="${customerMasterDetail.productMaster }">
							<c:if test="${scheme.prodTypeId=='1000000002'}">
								<option value="${scheme.prodId }">${scheme.prodName}</option>
							</c:if>
						</c:forEach>
					</select>
				</div>

				<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
					<label><spring:message code="label.allocateTo" /><span>*</span></label>
					<select name="allocateTo" id="allocate1" class="validField">
						<option value="-1">Select</option>
						<c:forEach var="allocate" items="${allocateList}">
							<option value="${allocate.userId }">${allocate.userName }</option>
						</c:forEach>
					</select>
				</div>

				<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
					<label><spring:message code="label.loanAmount" /></label> <input
						type="text" name="loanAmount" id="loanAmount" value=""
						onkeypress="return onlyNumeric(event);" list="datalist">
				</div>

				<datalist id="datalist"></datalist>

				<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
					<label><spring:message code="label.tenure" /></label> <input
						type="text" name="tenure" value=""
						onkeypress="return onlyNumeric(event);">
				</div>


			</div>


		</div>
	</div>

	<!-- Modal -->
	<div class="modal fade" id="myModal" role="dialog"
		style="display: none;" aria-hidden="true">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header model-border-none">
					<button type="button" class="close" data-dismiss="modal"
						onclick="setData1()">×</button>
					<h4 class="modal-title"></h4>
				</div>
				<div class="modal-body">
					<textarea class="modal-textarea" name="remark" id="modalAddress"></textarea>
				</div>
				<div class="modal-footer model-border-none">
					<button type="button" class="btn btn-default" data-dismiss="modal"
						onclick="setData1()">Close</button>
				</div>
			</div>

		</div>
	</div>
	<!-- Modal -->
</form:form>
<script type="text/javascript" src="resources/js/jquery.min.js"></script>
<script type="text/javascript" src="resources/js//jquery-ui.min.js"></script>
<script>





//added by tripti
$("#entityType").change(function() {
	if ($(this).find("option:selected").val() == "1000000001") {
		//alert("PTPP");
		//$(".personalDetailForNonIndividual").addClass("hidden");
		//$(".personalDetailForIndividual").removeClass("hidden");
		$('#personalDetailForNonIndividual').hide();
		$('#personalDetailForIndividual').show();
	} else {
		//alert("noptp");
		//$(".personalDetailForIndividual").addClass("hidden");
		//$(".personalDetailForNonIndividual").removeClass("hidden");
		$('#personalDetailForIndividual').hide();
		$('#personalDetailForNonIndividual').show();		
	}
});

function incomeDetailsfields(){
var entityType=$("#entityType").val();
var appType=$("#occupationTypeId option:Selected").val();
//alert(entityType+"--"+appType);
if(entityType=="1000000001"){
if(appType=='1000000001'){
	$('.field2').css('display','block');
	$('.field3').css('display','none');
	$('.field1').css('display','block');
	$('.companyClass').css('display','block');
	$('.incomedetails').removeClass('hidden');	
}
else /* if(appType=='1000000003' || appType=='1000000009') */{
	$('.field2').css('display','block');
	$('.field3').css('display','none');
	$('.field1').css('display','none');
	$('.companyClass').css('display','none');	
	$('.incomedetails').removeClass('hidden');
}
}else if(entityType=="1000000002"){
	var consti=$("#constitution").val();
	if(consti!=-1){
	$('.incomedetails').removeClass('hidden');
	$('.field2').css('display','none');
	$('.field3').css('display','block');
	$('.field1').css('display','none');
	$('.companyClass').css('display','block');		
	}
}
}


/* function myFunction() {
    var options = document.getElementById("monthlist").options;
    var result = false;
    for (var i = 0; i < options.length; i++) {
        if(document.getElementById("month").value == options[i].value) {
        	result = true;
      	}else{
      		
      	}        
    }
    document.getElementById("result").innerHTML = "Validate : " + result + ".";
} */


$(document).ready(function(){	
	//document.getElementById('entityType').selectedIndex = 1;
	if ($("#entityType").val() == "1000000001") {
		$(".personalDetailForNonIndividual").addClass("hidden");
		$(".personalDetailForIndividual").removeClass("hidden");
	} else if ($("#entityType").val() == "1000000002") {
		$(".personalDetailForIndividual").addClass("hidden");
		$(".personalDetailForNonIndividual").removeClass("hidden");
	}	
	incomeDetailsfields();		
});
	$(document).ready(
			function() {

				//document.getElementById('entityType').selectedIndex = 1;
				var leadid = '${sessionScope.leadid}';				
				var error = '${sessionScope.error}';
				var message = '${sessionScope.errorMessage}';
				if (leadid !== null && leadid !== undefined && leadid !== ''
						&& leadid !== 'null') {
					document.getElementById('entityType').selectedIndex = 1;
					alert('Your New Lead Id = ' + leadid);
					//window.location.assign("<%=request.getContextPath()%>/newlead.do");
				};
				if (error !== 'S' && error !== null && error !== undefined
						&& error !== '') {
					alert('Error : ' + error + "\nDescription : " + message);
					//window.location.assign("<%=request.getContextPath()%>/newlead.do");
				};
				<c:if test="${not empty sessionScope.leadid}">   
				   <c:remove var="leadid" scope="session"/>       
				</c:if>
				<c:if test="${not empty sessionScope.error}">   
				   <c:remove var="error" scope="session"/>       
				</c:if>
				<c:if test="${not empty sessionScope.errorMessage}">   
				   <c:remove var="errorMessage" scope="session"/>       
				</c:if>
			});
			
	$( "#companyName" ).autocomplete({
			source: 'get_country_list.do'
	});

	$('.prev-lead-icon').parent('.subheader-btn').hover(function() {
		//alert('1');
		$(this).children('.prevlead-tooltip').fadeToggle(150);
	});

	$('.next-lead-icon').parent('.subheader-btn').hover(function() {
		//alert('1');
		$(this).children('.nextlead-tooltip').fadeToggle(150);
	});

	$('.savee-icon').parent('.subheader-btn').hover(function() {
		//alert('1');
		$(this).children('.savee-tooltip').fadeToggle(150);
		
	});
	$('.savee-icon').parent('.subheader-btn').click(function(){
		document.getElementById("requestType").value = "Save";
		document.getElementById("newLeadForm").action="manageNewLead.do";
		
		if(checkValidation()){
			document.getElementById("newLeadForm").submit();
		}
	});

	$('.saveandexit-icon').parent('.subheader-btn').hover(function(e) {
		e.preventDefault();
		$(this).children('.saveandexit-tooltip').fadeToggle(150);
		
	});
	$('.saveandexit-icon').parent('.subheader-btn').click(function(){
		document.getElementById("requestType").value = "Save&Exit";
		document.getElementById("newLeadForm").action="seNewLead.do";
		if(checkValidation()){
			document.getElementById("newLeadForm").submit();
		}
	});

	$('.usermenu-mobile').click(function(e) {
		e.preventDefault();
		$('.header-rightpanel').animate({
			"top" : "0"
		});
	});
	$('.close-header-rightpanel').click(function(e) {
		e.preventDefault();
		$('.header-rightpanel').animate({
			"top" : "-260px"
		});

	});
	
	
function getClusterName(val,type) {		
		var sendData = {
			    "idColumnName" : "CLUSTERID",
			    "valueColumnName" :"CLUSTERNAME",
			    "dependentTableName" :"QM_CLUSTER",
			    "crossTableName" :"QM_IA_CLUSTER_X_IND",
			    "crossTableDependentId" :"QM_CLUSTER_ID",
			    "crossTableMasterId" :"QM_IA_INDUSTRY_TYPE_ID",
			    "masterValue" : val.value
		}		
		$.ajax({
			type : "post",
			url : "getDependentMaster.do",
			data : sendData,		
			ontext : document.body,
			success : function(response) {
/* 				 var docTypeId = val.id;
				var leftNameIndex = docTypeId.indexOf("[");
				var rightNameIndex = docTypeId.indexOf("]");
				var indexnumber = docTypeId.substring(leftNameIndex + 1,rightNameIndex);
				leftName = docTypeId.substring(0, leftNameIndex);*/
				if(type === 'NI'){
					var select = $('[name="clusterForNI"]');
				}else{
					var select = $('[name="cluster"]');
				}			
				select.find('option').remove();
				$('<option>').val("-1").text("Select").appendTo(select);
				$($.parseJSON(response)).map(function() {
					$('<option>').val(this.id).text(this.value).appendTo(select);
				}); 
			}
		});
	}
	
	
	/*function getTypeOfBusinessName(val,type) {		
		var sendData = {
			    "idColumnName" : "ID",
			    "valueColumnName" :"TYPE_OF_BUSINESS",
			    "dependentTableName" :"QM_TYPE_OF_BUSINESS",
			    "crossTableName" :"QM_INDUSTRY_X_TYPEOFBUS",
			    "crossTableDependentId" :"natureofbus_id",
			    "crossTableMasterId" :"industryid",
			    "masterValue" : val.value		  			  
		}		
		$.ajax({
			type : "post",
			url : "getDependentMaster.do",
			data : sendData,		
			ontext : document.body,
			success : function(data) {								
				$($.parseJSON(data)).map(function() {					
					$("#datalist").append("<option value='" + this.value + "'></option>");
				});

			}
		});
	}*/
	
	
	function getTypeOfBusinessName(val,type) {
		
		var sendData = {
			    "idColumnName" : "ID",
			    "valueColumnName" :"TYPE_OF_BUSINESS",
			    "dependentTableName" :"QM_TYPE_OF_BUSINESS",
			    "crossTableName" :"QM_INDUSTRY_X_TYPEOFBUS",
			    "crossTableDependentId" :"natureofbus_id",
			    "crossTableMasterId" :"industryid",
			    "masterValue" : val.value		  			  
		}
		
		$.ajax({
			type : "post",
			url : "getDependentMaster.do",
			data : sendData,		
			ontext : document.body,
			success : function(response) {
				/* 				 var docTypeId = val.id;
				var leftNameIndex = docTypeId.indexOf("[");
				var rightNameIndex = docTypeId.indexOf("]");
				var indexnumber = docTypeId.substring(leftNameIndex + 1,rightNameIndex);
				leftName = docTypeId.substring(0, leftNameIndex);*/
				if(type === 'NI'){
					var select = $('[name="typeOfBusinessForNI"]');
				}else{
					var select = $('[name="typeOfBusiness"]');
				}			
				select.find('option').remove();
				$('<option>').val("-1").text("Select").appendTo(select);
				$($.parseJSON(response)).map(function() {
					$('<option>').val(this.id).text(this.value).appendTo(select);
				}); 
			}
		});
	}
	

	$('.addrow-prop-details')
			.click(
					function(e) {

						e.preventDefault();

						if (iCnt <= 19) {
							iCnt = iCnt + 1;

							$(this)
									.parent('.btnPosition')
									.parent()
									.next('.row')
									.find('tbody')
									.append(
											'<tr><td width="1%"><input type="checkbox" name="checkbox'+iCnt+'" id="checkbox'+iCnt+'"></td><td><select> <option>Residential</option><option>Commercial</option><option>Industrial</option> <option>Plot</option><option>Not identified</option> </select></td> <td><input type="text" value="ABX Developers" name="dvl'+iCnt+'" id="dvl '+iCnt+'"></td><td><input type="text" value="Proj name" name="proj'+iCnt+'" id="proj '+iCnt+'"></td><td><input type="text" value="Rs 4,00,000" name="est'+iCnt+'" id="est'+iCnt+'" ></td><td><input type="text" value="G-11, ABC Estate" name="padd'+iCnt+'" id="padd'+iCnt+'"></td> <td><input type="text" value="Near Mandir" name="land'+iCnt+'" id="land'+iCnt+'"></td> <td><select><option>State</option><option>State1</option><option>State2</option><option>State3</option></select></td>            <td><select>                <option>city</option>                <optioncity1</option>                <option>city2</option>                <option>ity3</option>              </select></td>            <td><input type="text" value="110055" class="minWidthPin" name="pin'+iCnt+'" id="pin'+iCnt+'"></td>            <td><select>                <option>RENTED</option>                <option>Under Construction</option><option>Ready</option><option>Vacant</option><option>Self Occupied</option></select></td><td class="remarkProperty"><textarea name="remark'+iCnt+'" id="remark'+iCnt+'"></textarea></td></tr>');
						}
						;
					});

	$('.delrow')
			.click(
					function(e) {
						e.preventDefault();
						$(this)
								.parent('.btnPosition')
								.parent()
								.next('.row')
								.find('tr')
								.each(
										function() {
											if ($(this)
													.children('td:nth-child(1)')
													.children(
															'input[type="checkbox"]')
													.is(':checked')) {
												$(this)
														.children(
																'td:nth-child(1)')
														.children(
																'input[type="checkbox"]:checked')
														.parent('td').parent(
																'tr').remove();
											}
										});
					});

/* 	$('.delrow-detail').click(
			function(e) {
				e.preventDefault();

				$(this).parent('.btnPosition').parent('.row').next(
						'.row.contactList').next('.row.contactList').remove();
				//$('.addrow-detail').val = parseInt($('.addrow-detail').val)-1;
			}); */
	$('.delrow-detail').click(function(e) {
		  e.preventDefault();
		  //fire when the button is clicked
		        $('.row.contactList input:checkbox').each(function() {
		          var checkbox = $(this); 
		         // alert(checkbox);
		         if(checkbox.is(':checked') && checkbox.val()!=='default') 
		         {$(this).parent().parent('.addressDeletee').remove();};
		         });
		    });
	/* $('.addrow-detail').click(function(e){
	    e.preventDefault();
	    var cnti = parseInt(this.value);
	    $(this).parent('.btnPosition').parent('.row').next('.row.contactList').append('<hr/><div class="AddressSection"><div class="deletchk"><input type="checkbox" value="Active" placeholder="checkbox" name="listAddress['+cnti+'].addressId" Checked/></div><div class="col-lg-3 col-md-3 col-sm-3 col-xs-12"><label>Address Type</label> <select name="listAddress['+cnti+'].addressType"><option value="-1">Select</option><c:forEach var="addressType" items="${customerMasterDetail.addressType }"><option value="${addressType.addressTypeId }">${addressType.addressTypeDisplayName}</option></c:forEach></select></div><div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 addressField"><label class="minWidth">Address</label><textarea name="listAddress['+cnti+'].address" maxlength="1000" placeholder="Address"></textarea></div><div class="col-lg-3 col-md-3 col-sm-3 col-xs-12"><label>Mailing Address </label> <input type="checkbox" placeholder="checkbox" value="Y" name="listAddress['+cnti+'].mailingAddress" /></div><div class="clearfix"></div><div class="col-lg-3 col-md-3 col-sm-3 col-xs-12"><label>State </label> <select name="listAddress['+cnti+'].state"><option value="-1">Select</option><c:forEach var="state" items="${customerMasterDetail.stateList }"><option value="${state.stateMasterId }">${state.displayName}</option></c:forEach></select></div><div class="col-lg-3 col-md-3 col-sm-3 col-xs-12"><label>City</label> <select name="listAddress['+cnti+'].city"><option value="-1">Select</option><c:forEach var="city" items="${customerMasterDetail.city }"><option value="${city.cityMasterId }" >${city.displayName	}</option></c:forEach></select></div><div class="col-lg-3 col-md-3 col-sm-3 col-xs-12"><label>Pin</label> <input type="text" name="listAddress['+cnti+'].zipcode" maxlength="6" onkeypress="return onlyNumeric(event);" /> </div><div class="col-lg-3 col-md-3 col-sm-3 col-xs-12"><label>Landmark </label> <input type="text" placeholder="Landmark" name="listAddress['+cnti+'].landmark" maxlength="200" /></div><div class="clearfix"></div><div class="col-lg-3 col-md-3 col-sm-3 col-xs-12"><label>Landline</label> <input type="text" placeholder="Landline" name="listAddress['+cnti+'].phone1" onkeypress="return onlyNumeric(event);" maxlength="12"/></div><div class="col-lg-3 col-md-3 col-sm-3 col-xs-12"><label>Extension</label> <input type="text"	placeholder="Extension" name="listAddress['+cnti+'].ext1" onkeypress="return onlyNumeric(event);" maxlength="5"/> </div><div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">	<label>Living Here Since</label> <input type="text"	class="minWidth" maxlength="2" name="listAddress['+cnti+'].occupancyMm" onkeypress="return onlyNumeric(event);" placeholder="MM"/> <input type="text" class="minWidth" placeholder="YYYY" maxlength="4" name="listAddress['+cnti+'].occupancyYr" onkeypress="return onlyNumeric(event);" /></div><div class="col-lg-3 col-md-3 col-sm-3 col-xs-12"><label>Occupancy Status</label> <select name="listAddress['+cnti+'].occupancyStatus"><option value="-1">select</option><c:forEach var="status" items="${customerMasterDetail.occupancyStatus }"><option value="${status.occupancyStId }" >${status.occupancyStName}</option></c:forEach></select></div></div>'); 
	  	this.value=cnti+1;
	  });	
 */
 
	 $('.addrow-detail').click(function(e){
	    e.preventDefault();
	    var cnti = parseInt(this.value);
	    /* $(this).parent('.btnPosition').parent('.row').next('.row.contactList').append('<hr/><div class="deletchk"><input type="checkbox" value="Active" placeholder="checkbox" name="listAddress['+cnti+'].addressId" Checked/></div><div class="col-lg-3 col-md-3 col-sm-3 col-xs-12"><label>Address Type</label> <select name="listAddress['+cnti+'].addressType"><option value="-1">Select</option><c:forEach var="addressType" items="${customerMasterDetail.addressType }"><option value="${addressType.addressTypeId }">${addressType.addressTypeDisplayName}</option></c:forEach></select></div><div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 addressField"><label class="minWidth">Address</label><textarea name="listAddress['+cnti+'].address" maxlength="1000" placeholder="Address"></textarea></div><div class="col-lg-3 col-md-3 col-sm-3 col-xs-12"><label>Mailing Address </label> <input type="checkbox" placeholder="checkbox" value="Y" name="listAddress['+cnti+'].mailingAddress" /></div><div class="clearfix"></div><div class="col-lg-3 col-md-3 col-sm-3 col-xs-12"><label>State </label> <select name="listAddress['+cnti+'].state"><option value="-1">Select</option><c:forEach var="state" items="${customerMasterDetail.stateList }"><option value="${state.stateMasterId }">${state.displayName}</option></c:forEach></select></div><div class="col-lg-3 col-md-3 col-sm-3 col-xs-12"><label>City</label> <select name="listAddress['+cnti+'].city"><option value="-1">Select</option><c:forEach var="city" items="${customerMasterDetail.city }"><option value="${city.cityMasterId }" >${city.displayName	}</option></c:forEach></select></div><div class="col-lg-3 col-md-3 col-sm-3 col-xs-12"><label>Pin</label> <input type="text" name="listAddress['+cnti+'].zipcode" maxlength="6" onkeypress="return onlyNumeric(event);" /> </div><div class="col-lg-3 col-md-3 col-sm-3 col-xs-12"><label>Landmark </label> <input type="text" placeholder="Landmark" name="listAddress['+cnti+'].landmark" maxlength="50" /></div><div class="clearfix"></div><div class="col-lg-3 col-md-3 col-sm-3 col-xs-12"><label>Landline</label> <input type="text" placeholder="Landline" name="listAddress['+cnti+'].phone1" onkeypress="return onlyNumeric(event);" maxlength="12" /></div><div class="col-lg-3 col-md-3 col-sm-3 col-xs-12"><label>Extension</label> <input type="text"	placeholder="Extension" name="listAddress['+cnti+'].ext1" maxlength="5" onkeypress="return onlyNumeric(event);" /> </div><div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">	<label>Living Here Since</label> <input type="text"	class="minWidth" maxlength="2" placeholder="MM" name="listAddress['+cnti+'].occupancyMm" onkeypress="return onlyNumeric(event);" /> <input type="text" class="minWidth" maxlength="4" placeholder="YYYY" name="listAddress['+cnti+'].occupancyYr" onkeypress="return onlyNumeric(event);" /></div><div class="col-lg-3 col-md-3 col-sm-3 col-xs-12"><label>Occupancy Status</label> <select name="listAddress['+cnti+'].occupancyStatus"><option value="-1">select</option><c:forEach var="status" items="${customerMasterDetail.occupancyStatus }"><option value="${status.occupancyStId }" >${status.occupancyStName}</option></c:forEach></select></div>'); */
	    //$(this).parent('.btnPosition').parent('.row').next('.row.contactList').append('<div class="addressDeletee mailingAddressDiv jquerymargin"><div class="deletchk"><input type="checkbox" value="Active" placeholder="checkbox" name="listAddress['+cnti+'].addressId" Checked/></div><div class="col-lg-4 col-md-3 col-sm-3 col-xs-12"><label>Address Type</label> <select name="listAddress['+cnti+'].addressType"><option value="-1">SELECT</option><c:forEach var="addressType" items="${customerMasterDetail.addressType }"><option value="${addressType.addressTypeId }">${addressType.addressTypeDisplayName}</option></c:forEach></select></div><div class="col-lg-4 col-md-3 col-sm-6 col-xs-12 addressField"><label class="minWidth">Address</label><a href="" data-toggle="modal" data-target="#myModal" data-backdrop="static" data-keyboard="false" onclick="setData()"><img src="./image/edit_img.png" alt="" class="icon-image"></a><input type="text" value="" name="listAddress['+cnti+'].address" placeholder="Address"	class="col-lg-4 col-md-3 col-sm-3 col-xs-3 addressinput inputmytop" /></div><div class="col-lg-4 col-md-3 col-sm-3 col-xs-12"><label>Mailing Address </label> <input type="checkbox" placeholder="checkbox" value="Y" name="listAddress['+cnti+'].mailingAddress" /></div><div class="col-lg-4 col-md-3 col-sm-3 col-xs-12"><label>State </label> <select name="listAddress['+cnti+'].state" id="listAddress['+cnti+'].state" onchange="getCitiesByState(this);"><option value="-1">SELECT</option><c:forEach var="state" items="${customerMasterDetail.stateList }"><option value="${state.stateMasterId }">${state.displayName}</option></c:forEach></select></div><div class="col-lg-4 col-md-3 col-sm-3 col-xs-12"><label>City</label> <select name="listAddress['+cnti+'].city"><option value="-1">SELECT</option><option value="7830">BARGARH SADAR</option><option value="7831">BARIL</option><option value="7832">BARKOTE</option><option value="7897">CHHATRAPUR</option><option value="400">MUMBAI</option><option value="110">NEW DELHI</option><option value="9955">NOIDA</option></select></div><div class="col-lg-4 col-md-3 col-sm-3 col-xs-12"><label>Pin</label> <input type="text" name="listAddress['+cnti+'].zipcode" onchange="validatePinCode(this);" maxlength="6" onkeypress="return onlyNumeric(event);" /> </div><div class="col-lg-4 col-md-3 col-sm-3 col-xs-12"><label>Landmark </label> <input type="text" placeholder="Landmark" name="listAddress['+cnti+'].landmark" maxlength="50" /></div><div class="col-lg-4 col-md-3 col-sm-3 col-xs-12"><label>Landline</label> <input type="text" placeholder="Landline" name="listAddress['+cnti+'].phone1" onkeypress="return onlyNumeric(event);" maxlength="12" /></div><div class="col-lg-4 col-md-3 col-sm-3 col-xs-12"><label>Extension</label> <input type="text"	placeholder="Extension" name="listAddress['+cnti+'].ext1" maxlength="5" onkeypress="return onlyNumeric(event);" /> </div><div class="col-lg-4 col-md-3 col-sm-3 col-xs-12">	<label>Living Here Since</label> <input type="text"	class="minWidth" maxlength="2" onchange="validationForMonth(this)" placeholder="Months" name="listAddress['+cnti+'].occupancyMm" list="monthList" /> <input type="text" class="minWidth" maxlength="4" onchange="validateYear(this)"  placeholder="Years" name="listAddress['+cnti+'].occupancyYr" onkeypress="return onlyNumeric(event);" list="yearList"  /></div><div class="col-lg-4 col-md-3 col-sm-3 col-xs-12"><label>Occupancy Status</label> <select name="listAddress['+cnti+'].occupancyStatus"><option value="-1">SELECT</option><c:forEach var="status" items="${customerMasterDetail.occupancyStatus }"><option value="${status.occupancyStId }" >${status.occupancyStName}</option></c:forEach></select></div></div>');
	    $(this).parent('.btnPosition').parent('.row').next('.row.contactList').append('<div class="addressDeletee mailingAddressDiv jquerymargin"><div class="deletchk"><input type="checkbox" value="Active" placeholder="checkbox" name="listAddress['+cnti+'].addressId" Checked/></div><div class="col-lg-4 col-md-3 col-sm-3 col-xs-12"><label>Address Type</label> <select name="listAddress['+cnti+'].addressType"><option value="-1">SELECT</option><c:forEach var="addressType" items="${customerMasterDetail.addressType }"><option value="${addressType.addressTypeId }">${addressType.addressTypeDisplayName}</option></c:forEach></select></div><div class="col-lg-4 col-md-3 col-sm-6 col-xs-12 addressField"><label class="minWidth">Address</label><input type="text" value="" name="listAddress['+cnti+'].address" placeholder="Address"	class="col-lg-4 col-md-3 col-sm-3 col-xs-3 addressinput inputmytop" /></div><div class="col-lg-4 col-md-3 col-sm-3 col-xs-12"><label>Mailing Address </label> <input type="checkbox" placeholder="checkbox" value="Y" name="listAddress['+cnti+'].mailingAddress" /></div><div class="col-lg-4 col-md-3 col-sm-3 col-xs-12"><label>State </label> <select name="listAddress['+cnti+'].state" id="listAddress['+cnti+'].state" onchange="getCitiesByState(this);"><option value="-1">SELECT</option><c:forEach var="state" items="${customerMasterDetail.stateList }"><option value="${state.stateMasterId }">${state.displayName}</option></c:forEach></select></div><div class="col-lg-4 col-md-3 col-sm-3 col-xs-12"><label>City</label> <select name="listAddress['+cnti+'].city"><option value="-1">SELECT</option><option value="7830">BARGARH SADAR</option><option value="7831">BARIL</option><option value="7832">BARKOTE</option><option value="7897">CHHATRAPUR</option><option value="400">MUMBAI</option><option value="110">NEW DELHI</option><option value="9955">NOIDA</option></select></div><div class="col-lg-4 col-md-3 col-sm-3 col-xs-12"><label>Pin</label> <input type="text" name="listAddress['+cnti+'].zipcode" onchange="validatePinCode(this);" maxlength="6" onkeypress="return onlyNumeric(event);" /> </div><div class="col-lg-4 col-md-3 col-sm-3 col-xs-12"><label>Landmark </label> <input type="text" placeholder="Landmark" name="listAddress['+cnti+'].landmark" maxlength="50" /></div><div class="col-lg-4 col-md-3 col-sm-3 col-xs-12"><label>Landline</label> <input type="text" placeholder="Landline" name="listAddress['+cnti+'].phone1" onkeypress="return onlyNumeric(event);" maxlength="12" /></div><div class="col-lg-4 col-md-3 col-sm-3 col-xs-12"><label>Extension</label> <input type="text"	placeholder="Extension" name="listAddress['+cnti+'].ext1" maxlength="5" onkeypress="return onlyNumeric(event);" /> </div><div class="col-lg-4 col-md-3 col-sm-3 col-xs-12">	<label>Living Here Since</label> <input type="text"	class="minWidth" maxlength="2" onchange="validationForMonth(this)" placeholder="Months" name="listAddress['+cnti+'].occupancyMm" list="monthList" /> <input type="text" class="minWidth" maxlength="4" onchange="validateYear(this)"  placeholder="Years" name="listAddress['+cnti+'].occupancyYr" onkeypress="return onlyNumeric(event);" list="yearList"  /></div><div class="col-lg-4 col-md-3 col-sm-3 col-xs-12"><label>Occupancy Status</label> <select name="listAddress['+cnti+'].occupancyStatus"><option value="-1">SELECT</option><c:forEach var="status" items="${customerMasterDetail.occupancyStatus }"><option value="${status.occupancyStId }" >${status.occupancyStName}</option></c:forEach></select></div></div>');
	    this.value=cnti+1;
setTimeout(function(){
$('.jquerymargin .col-md-3:nth-child(2)').attr('id', 'second');
$('.jquerymargin .col-md-3:nth-child(3)').attr('id', 'third');
$('.jquerymargin .col-md-3:nth-child(4)').attr('id', 'forth');
$(".addressinput.inputmytop").removeAttr("id");
$('.mailingAddressDiv:nth-child(1) .col-md-3:nth-child(2)').remove('id', 'second');
$('.mailingAddressDiv:nth-child(1) .col-md-3:nth-child(3)').remove('id', 'third');
$('.mailingAddressDiv:nth-child(1) .col-md-3:nth-child(4)').remove('id', 'forth');
}, 10);
	  });
 
	$('.addrow-keycontacts')
			.click(
					function(e) {
						e.preventDefault();
						var cnti = parseInt(this.value);
						$(this)
								.parent('.btnPosition')
								.parent()
								.next('.row')
								.find('tbody')
								.append(
										'<tr><td width="1%"><input type="checkbox" checked/></td><td><select name="listKeyContact['+cnti+'].contactTypeId"><option value="-1">select</option><c:forEach var="contact" items="${customerMasterDetail.contact }"><option value="${contact.contactTypeId }">${contact.contactType}</option></c:forEach></select></td><td><input type="text" value="" name="listKeyContact['+cnti+'].fname" maxlength="50"/></td><td><input type="text" value=""	name="listKeyContact['+cnti+'].firmName" maxlength="50"/></td><td><input type="text" value="" name="listKeyContact['+cnti+'].mobile" onchange="validateMobile(this);" maxlength="10" onkeypress="return onlyNumeric(event);"/></td><td><input type="text" value=""	name="listKeyContact['+cnti+'].email" onchange="validateEmail(this);" maxlength="100"/></td><td><input type="text" name="listKeyContact['+cnti+'].address" maxlength="200"/></td></tr>');
						this.value = cnti + 1;
					});

	$('.newrow-contacts')
			.click(
					function(e) {
						e.preventDefault();
						var cnti = parseInt(this.value);
						$(this)
								.parent()								
								.append(
										'<div class="row contactAddres mobile margin-contactaddress-top"><div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"><input type="checkbox" placeholder="contactId" style="margin-right: 7px;top: 2px;position: relative;" checked/><label>Type</label> <select name="listMobile['+cnti+'].mobileContactTypeId" class="mobileType1"><option value="-1">Select</option><c:forEach var="contact3" items="${customerMasterDetail.contactTypeMobile }"><option value="${contact3.contactTypeId}">${contact3.contactTypeName}</option></c:forEach></select> <label>Mobile No.</label><input type="text" value="" name="listMobile['+cnti+'].contactNo" onchange="validateMobile(this);" maxlength="10" onkeypress="return onlyNumeric(event);"/> <input type="button" value="N"  /><input type="hidden" name="listMobile['+cnti+'].primaryContact" value="N" /></div></div>');
						this.value = cnti + 1;
$(".margin-contactaddress-top:first").addClass("top-it");
						//document.getElementById("mobileDel").value = parseInt(document.getElementById("mobileDel").value)+1;
						//alert(document.getElementById("mobileDel").value);
					});
	$('.newrow-email')
			.click(
					function(e) {
						e.preventDefault();
						var cnti = parseInt(this.value);
						$(this)	.parent()
								.append(
										'<div class="row contactAddres email"><div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"><input type="checkbox" placeholder="Address" checked/> <label>Type</label><select name="listEmail['+cnti+'].emailContactTypeId" class="emailtype1"><option value="-1">SELECT</option><c:forEach var="contact4" items="${customerMasterDetail.contactTypeEmail }"><option value="${contact4.contactTypeId }">${contact4.contactTypeName}</option></c:forEach></select> <label>Email Id</label> <input type="text" value="" name="listEmail['+cnti+'].email" onchange="validateEmail(this);" maxlength="100" style="width: 21.5% !important;margin-left: 16px;" class=""/><input value="N" type="button" style="margin-left: -22px !important;"/><input type="hidden" name="listEmail['+cnti+'].primaryEmail" value="N" /></div></div>');
						this.value = cnti + 1;

$("#rowContactEmail div div .row.contactAddres.email:first").addClass("top-it");
						//$('.delrow-email').val = parseInt($('.delrow-email').val)+1;

					});
	$('.delrow-contacts').click(
			function(e) {
				e.preventDefault();

				//fire when the button is clicked
				$('.row.contactAddres input:checkbox').each(
						function() {
							var checkbox = $(this);
							// alert(checkbox);
							if (checkbox.is(':checked')
									&& checkbox.attr('id') !== "completRow"
									&& checkbox.attr('id') !== "emailRow") {
								$(this).parent().parent(
										'.row.contactAddres.mobile').remove();
							}
						});

				//document.getElementById("mobileAdd").value = parseInt(document.getElementById("mobileAdd").value)-1;
				//alert(document.getElementById("mobileAdd").value);
			});
	$('.delrow-email').click(
			function(e) {
				e.preventDefault();
				//fire when the button is clicked
				$('.row.contactAddres input:checkbox').each(
						function() {
							var checkbox = $(this);
							// alert(checkbox);
							if (checkbox.is(':checked')
									&& checkbox.attr('id') !== "emailRow"
									&& checkbox.attr('id') !== "completRow") {
								$(this).parent().parent(
										'.row.contactAddres.email').remove();
							}
						});
				//document.getElementById("emailAdd").value = document.getElementById("emailAdd").value - 1;
				//$('.newrow-email').val = parseInt($('.newrow-email').val)-1;
			});

	$('#chkresidance input[type=checkbox]').on('change', function() {
		$('#residenceAddress').toggle();
	});
	$('#chkoffice input[type=checkbox]').on('change', function() {
		$('#officeAddress').toggle();
	});
	$('#chkother input[type=checkbox]').on('change', function() {
		$('#otherAddress').toggle();
	});

	function checkValidation() {
		var alertMsg = "";
		var status = true;
		var fname = $("#firstName1").val();/* document.getElementById("firstName").value; */
		var mobile = $("#contactNo1").val();/* document.getElementById("contactNo1").value; */
		var product = $('#product1').val();/* document.getElementById("product1").value; */
		//var queue = $('#queue1').val();/* document.getElementById("queue1").value; */
		//var source = $('#source1').val();/* document.getElementById("source1").value; */
		//var campaign = $('#campaign1').val();/* document.getElementById("campaign1").value; */
		var allocate = $('#allocate1').val();/* document.getElementById("allocate1").value; */
		var mobileType = $('#mobileType1').val();/* document.getElementById("mobileType1").value; */
		var entityType=$("#entityType").val();
		
		if (entityType == -1 || entityType == '' || entityType == undefined) {
			alertMsg+="ENTITY TYPE,";
			status = false;
		} 
		if (entityType == "1000000001") {
			var constitutn=$("#occupationTypeId").val();
			if(constitutn==-1 || constitutn === '' || constitutn === undefined){
				alertMsg+="CONSTITUTION,";
				status = false;
			}
			
		}
		else if(entityType == "1000000002") {
			var constitutn=$("#constitution").val();
			if(constitutn==-1 || constitutn === '' || constitutn === undefined){
				alertMsg+="CONSTITUTION,";
				status = false;
			}
		}
		
		if (entityType == "1000000001") {
		if (fname === null || fname === '' || fname === undefined) {
			alertMsg+="FIRST NAME,";
			status = false;
		} 
		}
		else if(entityType == "1000000002"){
			 var companyNam=$("#companyName").val();
			if (companyNam === null || companyNam === '' || companyNam === undefined) {
				alertMsg+="COMPANY NAME,";
				status = false;
			}
		}
		if (mobile === null || mobile === '' || mobile === undefined) {
			alertMsg+="MOBILE NUMBER,";
			status = false;
		}
		 if (product === '-1') {
			alertMsg+="PRODUCT,";
			status = false;
			
		}
		 /*if (queue === '-1') {
			alertMsg+="SUB-QUEUE,";
			status = false;
			
		} */
		/* if (source === '-1') {
			alertMsg+="SOURCE,";
			status = false;
			$( "#companyName" ).autocomplete({
			source: 'get_country_list.do'
	});
		}
		if (campaign === '-1') {
			alertMsg+="CAMPAIGN,";
			status = false;
			
		} */
		if (allocate === '-1') {
			alertMsg+="ALLOCATE TO,";
			status = false;
			
		}
		if (mobileType === '-1') {
			alertMsg+="MOBILE TYPE,";
			status = false;
		}
		
		if(status === false){
			
			alertMsg = alertMsg.substring(0, alertMsg.length - 1);
			alertMsg+=" MUST BE SPECIFIED";
			alert(alertMsg);
			return false;
		}else return true;
	}
	
	
	//alert("dgf");
	$(document).ready(function(){
		$(document).on("click", ".row.contactAddres.mobile input[type='button']", function(){
			$(".row.contactAddres.mobile input[type='button']").attr("value","N");
			$(".row.contactAddres.mobile input[type='hidden']").attr("value","N");
		$(this).attr("value","Y");
		$(this).next("input[type='hidden']").attr("value", "Y");
		});
		$(document).on("click", ".row.contactAddres.email input[type='button']", function(){
			$(".row.contactAddres.email input[type='button']").attr("value","N");
			$(".row.contactAddres.email input[type='hidden']").attr("value","N");
		$(this).attr("value","Y");
		$(this).next("input[type='hidden']").attr("value", "Y");
		});
		});
	
	
	function getCitiesByState(val){
		//var stateId=document.getElementById("listAddress[0].state").value;
		var stateId = val.value;		
		$.ajax({  
			type : "post",   
			url : "getCitiesByState.do",   
			data : "stateId=" + stateId ,
			ontext: document.body,			
			success : function(response) {			
				
				var selectId = val.id;
				
				var leftNameIndex = selectId.indexOf("[");
				
				var rightNameIndex = selectId.indexOf("]");
				
				var indexnumber = selectId.substring(leftNameIndex+1,rightNameIndex);
				
				leftName = selectId.substring(0,leftNameIndex);
				
				var select  = $('[name="'+leftName+'['+indexnumber+'].city"]');
				
				//var select  = $('[name="listAddress['+indexnumber+'].city"]');
				
	  	     	select.find('option').remove();	
	  	     	$('<option>').val("0").text("Select").appendTo(select);
				 $($.parseJSON(response)).map(function () {
					$('<option>').val(this.cityMasterId).text(this.cityMasterName).appendTo(select);    			  
				   });	        
	  	     }			
		});
	}
	 window.onbeforeunload = function (e) {}; 
		$('document').ready(function(){	
			//alert("Flushing lead loakink Start");
			$.ajax({
				async : true,
				type : "post",
				url: "flushLeadLock.do",
				context: document.body,
				cache: false,
				success : function(response) {
					//alert("response>>>>"+response)
				}
				});	
		});
	$(function(){		
		$(document).on("click",".mailingAddressDiv input[type='checkbox']:nth-child(2)", function(){
			$(".mailingAddressDiv input[type='checkbox']:nth-child(2)").removeAttr("checked");
			$(this).prop("checked",'checked');
		});
});
	function setData(){
		var data=$('#addressInput').val();
		$('#modalAddress').val(data);		
	}
	
	function setData1(){
		var data=$('#modalAddress').val();
		$('#addressInput').val(data);		
	}
</script>
<style>
.saveBtnList ul li .subheader-btn {
	border-right: 0px solid #0889C4 !important;
}

input[value="N"] {
	margin-left: -27px !important;
}

.contactHeader {
	border-bottom: 5px solid #00698C !important;
	padding-bottom: 3PX;
}

.deletchk input {
	margin: -1px 0 0 2px;
}

.icon-image {
	right: 50px;
	top: 6px !important;
}

.minWidth {
	width: 29.59% !important;
}

.delrow-contacts.btn-primary.button-delete, .newrow-contacts.btn-primary.button-add,
	.newrow-email.btn-primary.button-add, .delrow-email.btn-primary.button-delete
	{
	margin-top: 3px !important;
	font-size: 14px;
}

#second, #third, #forth {
	margin-top: 10px !important;
}

#third {
	margin-top: 9px !important;
}

.deletchk input {
	margin: 0px 0 0 2px !important;
}

.jquerymargin div input[type="checkbox"] {
	margin: 12px 0 0 2px !important;
}

#residenceAddress .leadstatus {
	margin-top: -15px !important;
}

#residenceAddress .leadstatus div .mailingAddressDiv {
	margin-top: -4px;
}

.row.contactAddres.mobile, .row.contactAddres.email {
	margin-bottom: -6px;
}

.top-it {
	margin-top: -6px !important;
}

.table.table-striped tbody tr td {
	padding-top: 0px !important;
}

.table.table-striped tbody tr:nth-child(1) td {
	padding-top: 10px !important;
}

.searchList {
	margin-top: 8px !important;
}

.row.leadstatus.personalDetailForIndividual {
	border: 0px solid #DDD;
}

.mailingAddressDiv div:nth-child(6) {
	padding-top: 1px !important;
}
</style>
