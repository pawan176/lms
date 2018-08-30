<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script src="resources/js/globalValidation.js" type="text/javascript"></script>
<script src="resources/js/myValidation.js" type="text/javascript"></script>
<script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
<style type="text/css">
.active_state {
	border: 2px solid #61AAC2 !important;
	border: solid 1px #CCC !important;
	background-color: #D8FFFF;
}

.highlight_only {
	border: 2px solid #61AAC2 !important;
}
</style>
<!--[if IE 8]>
	
	<style type="text/css">
	.addmobilenodiv, .emailbtndiv{top: 0 !important}
	.add-phoneno, .add-emaillid {
    background-position: left 1px !important
	}
	#completRow{margin-top: 7px}
	.row.contactAddres.mobile input[type="button"]{height: 25px !important}
	
	</style>
	
<![endif]-->

<div class="container-fluid">
	<div
		class="padding-top-container contact-top col-lg-12 col-md-12 col-sm-12 col-xs-12 fourtabs-toppadding">
		<div class="row contactList" id="collapse-div">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 topstaticinfo">
				<ul class="listOne">
					<li><label><spring:message code="staticHeader.leadId" /></label></li>
					<li><span>${leadHeaderDetail.leadCode}</span></li>
					<li><label><spring:message
								code="staticHeader.generationDate" /></label></li>
					<li><span>${leadHeaderDetail.generationDate}</span></li>
					<li><label><spring:message
								code="staticHeader.allocatedTo" /></label></li>
					<li><span>${leadHeaderDetail.allocatedTo}</span></li>
					<li><label><spring:message code="staticHeader.product" /></label></li>
					<li><span>${leadHeaderDetail.queue}</span></li>
				</ul>
				<ul class="listOne">
					<li><label><spring:message code="staticHeader.queue" /></label></li>
					<li><span>${leadHeaderDetail.queue}</span></li>
					<li><label><spring:message
								code="staticHeader.subQueue" /></label></li>
					<li><span>${leadHeaderDetail.subQueue}</span></li>
					<li><label><spring:message code="staticHeader.source" /></label></li>
					<li><span>${leadHeaderDetail.source }</span></li>
					<li><label><spring:message
								code="staticHeader.campaign" /></label></li>
					<li><span>${leadHeaderDetail.campaign}</span></li>
				</ul>
				<ul class="listOne">
					<li><label><spring:message code="staticHeader.disposition" /></label></li>
					<li><span>${leadHeaderDetail.disposition}</span></li>
					<li><label><spring:message code="staticHeader.amount" /></label></li>
					<li><span>${leadHeaderDetail.amount}</span></li>
					<li><label><spring:message code="staticHeader.leadStage" /></label></li>
					<li><span>${leadHeaderDetail.leadStage}</span></li>
					<%-- <li><label><spring:message code="staticHeader.roi" /></label></li> --%>
					<%-- <li><span>${leadHeaderDetail.roi}</span></li> --%>
				</ul>
				<ul class="listOne">
					<li><label><spring:message
								code="staticHeader.custName" /></label></li>
					<li><span>${leadHeaderDetail.customerName}</span></li>
					<li><label><spring:message
								code="staticHeader.custMobile" /> </label></li>
					<li><span>${leadHeaderDetail.customerMobile}</span></li>
					<li><label> <spring:message
								code="staticHeader.custCity" /></label></li>
					<li><span>${leadHeaderDetail.customerResCity}</span></li>
					<li><label><spring:message
								code="staticHeader.constitution" /></label></li>
					<li><span>${leadHeaderDetail.occuType}</span></li>
				</ul>
				<div class="ImageIcon"><img src="resources/images/image-user.jpg" alt="UserLogoImage" /></div>
			</div>
			<div class="tgl_nbtn tggl tglestactic hiu uparrow-div" style=""
				onclick="down()">
				<!--   <span></span><span></span> -->
				<span style="position: relative; top: -3px;"></span>
				<!--  triangle-up	<div><img src="images/arrdown.gif"/></div> -->
			</div>
			<div class="tgl_nbtn tggl tglestactic hiu down-div" style=""
				onclick="uparrow()">
				<!--   <span></span><span></span> -->
				<span style="position: relative; top: -3px;"></span>
				<!--  triangle-up	<div><img src="images/arrdown.gif"/></div> -->
			</div>
			<div class="col-lg-6 col-md-6 col-sm-3 col-xs-12 taggs tags-margin">
				<label class="col-lg-2 col-md-3 col-sm-3 col-xs-6"
					style="left: -6px;"><spring:message
						code="staticHeader.tagA" /></label>
				<!--  
				<textarea type="text"
					class="textwidth col-lg-9 col-md-9 col-sm-8 col-xs-6 taga_textarea" disabled>${leadHeaderDetail.tagA}</textarea>-->
				<div class="col-lg-9 col-md-6 col-sm-6 col-xs-6  padding-left-img">
					<a href="" data-toggle="modal" data-target="#myModal1"
						data-backdrop="static" data-keyboard="false"><img
						src="./image/edit_img.png" alt="" class="icon-image"></a> <input
						type="text" value="${leadHeaderDetail.tagA}"
						class="col-lg-12 col-md-12 col-sm-12 col-xs-12" />
				</div>
			</div>
			<div class="col-lg-6 col-md-6 col-sm-3 col-xs-12 taggs tags-margin">
				<label class="col-lg-2 col-md-3 col-sm-3 col-xs-6"
					style="left: -11px;"><spring:message
						code="staticHeader.tagB" /></label>
				<!--  <textarea
					 class="textwidth col-lg-9 col-md-9 col-sm-8 col-xs-6 tagb_textarea" disabled>${leadHeaderDetail.tagB}</textarea>
					 -->
				<div class="col-lg-9 col-md-6 col-sm-6 col-xs-6  padding-left-img">
					<a href="" data-toggle="modal" data-target="#myModal2"
						data-backdrop="static" data-keyboard="false"><img
						src="./image/edit_img.png" alt="" class="icon-image"></a> <input
						type="text" value="${leadHeaderDetail.tagB}"
						class="col-lg-12 col-md-12 col-sm-12 col-xs-12" />
				</div>
			</div>
		</div>
		<form method="post" action="" id="customerForm">
			<input type="hidden" name="caseId" value="${customerDetail.caseId }" />
			<input type="hidden" name="personalDetailId"
				value="${customerDetail.personalDetailId }"> <input
				type="hidden" name="requestType" id="requestType" value="" />
		<!-- 	<div class="row buttonHeading">
				<div class="col-lg-9 col-md-9 col-sm-9 col-xs-10">
					<h2 class="heading">Contact Details</h2>
				</div>
				<div class="col-lg-3 col-md-3 col-sm-3 col-xs-2 btnPosition">
					 <a class="toggle-list btn-primary"> <span
						class="toggle-icon minus-icon1 "></span>
					</a> 
				</div>
			</div> -->
	<%-- 		<div class="row padd-top-contact-upup ">
				<div class="col-lg-5 col-md-12 col-sm-12 col-xs-12 add-phoneno ">
					<div id="rowContactMobile" class="searchdiv-padding-zero">
						<c:forEach var="mobile" varStatus="mobileStatus"
							items="${customerDetail.listMobile }">
							<div class="row contactAddres mobile">
								<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
									<input type="checkbox" placeholder="Address" id="completRow"
										name="listMobile[${mobileStatus.index }].caseMobileId"
										value="${mobile.caseMobileId }" disabled="disabled" /> <label>Type</label> <select
										disabled="disabled" name="listMobile[${mobileStatus.index }].mobileContactTypeId"
										id="mobileType" class="validField">
										<option value="-1">Select</option>
										<c:forEach var="contact3"
											items="${customerMasterDetail.contactTypeMobile }">
											<option value="${contact3.contactTypeId}"
												${contact3.contactTypeId==mobile.mobileContactTypeId?'selected':''}>${contact3.contactTypeName}</option>
										</c:forEach>
									</select> <label class="mobileno2"><spring:message
											code="label.mobile" />
										${mobile.primaryContact==null||mobile.primaryContact=='N'?'':'<span
										style="position: relative; display: inline-block"
										class="aster"><span>*</span></span>'}</label><input
										type="text" disabled="disabled" class="rgtAlign"
										onkeypress="return onlyNumeric(event);"
										value="${mobile.contactNo}"
										name="listMobile[${mobileStatus.index }].contactNo"
										id="contactNo"
										${mobile.primaryContact==null||mobile.primaryContact=='N'?'':'class="validField"'}
										${mobile.dndFlag=='Y'?'style="background : #ff0000 none repeat scroll 0 0;";':'' }
										maxlength="10" onchange="validateMobile(this);" /> <input
										type="button"
										value="${mobile.primaryContact==null?'N':mobile.primaryContact}" />
									<input type="hidden"
										name="listMobile[${mobileStatus.index }].primaryContact"
										value="${mobile.primaryContact==null?'N':mobile.primaryContact}" disabled="disabled"/>

								</div>
							</div>
						</c:forEach>
						<div class="addmobilenodiv top-add-del">
							<button name="Delete" type="button" value="1"
								class="delrow-contacts btn-primary" style="float: right"
								id="mobileDel" onclick="deleteMobile()" disabled="disabled">Delete</button>
							<button name="add" type="button"
								value="${customerDetail.listMobile eq null ? '0' : fn:length(customerDetail.listMobile)}"
								class="newrow-contacts btn-primary" style="float: right"
								id="mobileAdd" disabled="disabled">Add</button>
						</div>
					</div>

				</div>
				<div class="col-lg-2 col-md-12 col-sm-12 col-xs-12 "></div>
				<div class="col-lg-5 col-md-12 col-sm-12 col-xs-12 add-emaillid">						
					<div class="row contBtn">
						<!-- <input type="button" name="Set as Default" value="Set as Default" class="btn-primary"> -->
						<!-- <button name="Save" class="btn-primary" style="float: right"
							 onclick="saveEmail()" type="button">Save</button> -->

					</div>
					<div id="rowContactEmail" class="searchdiv-padding-zero">
						<c:forEach var="eMail" varStatus="emailStatus"
							items="${customerDetail.listEmail }">
							<div class="row contactAddres email">
								<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
									<input type="checkbox" placeholder="" id="emailRow"
										value="${eMail.caseEmailId }"
										name="listEmail[${emailStatus.index }].caseEmailId" disabled="disabled"/> <label><spring:message
											code="label.type" /></label> <select
										class="emailsel margin-left-inputbox" disabled="disabled"
										name="listEmail[${emailStatus.index }].emailContactTypeId">
										<option value="-1">select</option>
										<c:forEach var="contact4"
											items="${customerMasterDetail.contactTypeEmail }">
											<option value="${contact4.contactTypeId }"
												${contact4.contactTypeId==eMail.emailContactTypeId?'selected':'' }>${contact4.contactTypeName
												}</option>
										</c:forEach>
									</select> <label><spring:message code="label.email" /></label> <input
										type="text" class="margin-left-inputbox"
										value="${eMail.email }" maxlength="100"
										onchange="validateEmail(this);"
										name="listEmail[${emailStatus.index }].email" disabled="disabled"/><input
										value="${eMail.primaryEmail==null?'N':eMail.primaryEmail}"
										type="button" /> <input type="hidden"
										value="${eMail.primaryEmail==null?'N':eMail.primaryEmail}"
										name="listEmail[${emailStatus.index }].primaryEmail" disabled="disabled"/>

								</div>
							</div>
						</c:forEach>
						<div class="emailbtndiv top-add-del">
							<button name="Delete" value="Delete" type="button"
								class="delrow-email btn-primary" id="emailDel"
								style="float: right" value="1" onclick="deleteEmail()" disabled="disabled">Delete</button>
							<button name="add"
								value="${customerDetail.listEmail eq null ? '0' : fn:length(customerDetail.listEmail)}"
								class="newrow-email btn-primary" style="float: right"
								id="emailAdd" type="button" disabled="disabled">Add</button>
						</div>
					</div>

				</div>
			</div>
 --%>


			<div class="row buttonHeading">
				<div class="col-lg-9 col-md-9 col-sm-9 col-xs-10">
					<h2 class="heading">Personal Details</h2>
				</div>
				<!-- <div class="col-lg-3 col-md-3 col-sm-3 col-xs-2 btnPosition">
					<a class="toggle-list btn-primary"><span
						class="toggle-icon minus-icon1 "></span></a>
					<button class="btn btn-primary save-personal-details"
						onclick="savePersonal();">Save</button>
				</div> -->
			</div>
			<div class="row contactList leadstatus padd-top-contact-up"
				style="margin-bottom: 4px; padding: 4px 0 -2px 0 !important;">
				<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12 ">
					<label><spring:message code="label.entityType" /></label> <select
						name="custEntityTypeId" id="entityType" disabled="disabled"
						onchange="incomeDetailsfields()" class="validField">
						<option value="-1">select</option>
						<c:forEach var="entityObj"
							items="${customerMasterDetail.entityTypeMaster}">
							<option value="${entityObj.custEntityTypeId}"
								${entityObj.custEntityTypeId==customerDetail.custEntityTypeId?'Selected':'' }>${entityObj.displayName}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div
				class="row contactList leadstatus personalDetailForNonIndividual">

				<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
					<label><spring:message code="label.constitution" /></label> <select
						name="constitution" disabled="disabled" class="validField"
						onchange="madatoryForEligibility();incomeDetailsfields();"
						id="constitution">
						<option value="-1">select</option>
						<c:forEach var="occupation"
							items="${customerMasterDetail.occupationType}">
							<option value="${occupation.occupationid }"
								${occupation.occupationid==customerDetail.occupationType?'Selected':'' }>${occupation.occupationname}</option>
						</c:forEach>
					</select>
				</div>
<%-- 
				<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
						<label class="LabelHover">Customer Cate ...<span style="display:none;"><spring:message code="label.custCategory" /></span></label> 
						 <select
						name="custCategory" disabled="disabled">
						<option value="-1">select</option>
						<c:forEach var="obj"
							items="${customerMasterDetail.customerCategryMaster}">
							<option value="${obj.customercategortId }"
								${obj.customercategortId==customerDetail.custCategory?'Selected':'' }>${obj.displayName}</option>
						</c:forEach>
					</select>
				</div>		 --%>				

				<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
					<label><spring:message code="label.authF" /></label> <input
						type="text" disabled="disabled" value="${customerDetail.authSignatoryFName }"
						name="authSignatoryFName" value="">
				</div>
				<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
					<label><spring:message code="label.authM" /></label> <input
						type="text" disabled="disabled" value="${customerDetail.authSignatoryMName }"
						name="authSignatoryMName" value="">
				</div>
				<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
					<label><spring:message code="label.authL" /></label> <input
						type="text" disabled="disabled" value="${customerDetail.authSignatoryLName }"
						name="authSignatoryLName" value="">
				</div>
				<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
					<label><spring:message code="label.dateInc" /></label> <input
						type="text" placeholder="" id="datepicker1"
						onchange="validateDateIncorporation(this)"
						value="<fmt:formatDate type="date" value="${customerDetail.incorporationDate }" pattern="dd-MM-yyyy"/>"
						name="incorporationDate" disabled="disabled"/><span
						class="dobCalc glyphicon glyphicon-calendar" for="datepicker1"
						aria-hidden="true"></span>
				</div>
				<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
					<label><spring:message code="label.pan" /></label> <input
						type="text" disabled="disabled" placeholder="PAN Card No" name="companyPanNo"
						value="${customerDetail.companyPanNo}"
						onchange="return checkPAN(this);" maxlength="10">

				</div>
				<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
					<label><spring:message code="label.industry" /></label> <select
						name="industryForNI" disabled="disabled" onchange=getTypeOfBusinessName(this,"NI")>
						<option value="-1">SELECT</option>
						<c:forEach var="obj"
							items="${customerMasterDetail.industryMaster}">
							<option value="${obj.industryId }"
								${obj.industryId==customerDetail.industryForNI?'Selected':'' }>${obj.displayName}</option>
						</c:forEach>
					</select>
				</div>
				<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
						<label class="LabelHover">Type Of ...<span style="display:none;"><spring:message code="label.typeOfBusiness" /></span></label> 
					 <select
						name="typeOfBusinessForNI" disabled="disabled" id="typeOfBusinessForNI"
						onchange=getClusterName(this,"NI")>
						<option value="-1">select</option>
						<c:forEach var="obj" items="${customerMasterDetail.typeOfBusinessMaster}">
							<option value="${obj.id}"
							${obj.id==customerDetail.typeOfBusinessForNI?'Selected':''}>${obj.typeOfBusiness}</option>
						</c:forEach>
					</select>
				</div>
				<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
					<label><spring:message code="label.cluster" /></label> <select
						name="clusterForNI" disabled="disabled">
						<option value="-1">select</option>
						<c:forEach var="obj" items="${customerMasterDetail.clusterMaster}">
							<option value="${obj.clusterId}"
							${obj.clusterId==customerDetail.clusterForNI?'Selected':''}>${obj.clusterName}</option>
						</c:forEach>
					</select>
				</div>
				<!-- Added by Anuj on 30/12/2016 -->
				<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
					<label><spring:message code="label.sector" /></label> <select
						name="sectorForNI" disabled="disabled">
						<option value="-1">SELECT</option>
						<c:forEach var="obj" items="${customerMasterDetail.sectorMaster}">
							<option value="${obj.sectorId }"
								${obj.sectorId==customerDetail.sectorForNI?'Selected':'' }>${obj.sectorName}</option>
						</c:forEach>
					</select>
				</div>
				<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
					<label><spring:message code="label.stage" /></label> <select
						name="stageForNI" disabled="disabled">
						<option value="-1">SELECT</option>
						<c:forEach var="obj" items="${customerMasterDetail.stageMaster}">
							<option value="${obj.stageId }"
								${obj.stageId==customerDetail.stageForNI?'Selected':'' }>${obj.stageName}</option>
						</c:forEach>
					</select>
				</div>
				<!-- End -->
			</div>
			<div class="row contactList leadstatus personalDetailForIndividual">
				<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
					<label><spring:message code="label.constitution" /></label> <select
						name="occupationType" disabled="disabled" class="validField"
						onchange="incomeDetailsfields();madatoryForEligibility();"
						id="occupationTypeId">
						<option value="-1">SELECT</option>
						<c:forEach var="occupation"
							items="${customerMasterDetail.occupationType}">
							<option value="${occupation.occupationid }"
								${occupation.occupationid==customerDetail.occupationType?'Selected':'' }>${occupation.occupationname}</option>
						</c:forEach>
					</select>
				</div>
				<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
					<label><spring:message code="label.custCategory" /></label> <span
						class="sel-ttip"> <select name="companyType" disabled="disabled">
							<option value="-1">SELECT</option>
							<c:forEach var="companyType"
								items="${customerMasterDetail.companyType}">
								<option value="${companyType.compTypeId }"
									${companyType.compTypeId==customerDetail.companyType?'Selected':'' }>${companyType.companyType}</option>
							</c:forEach>
					</select>

					</span> <span class="select-tooltip"
						style="margin-top: -15px; background: #fff; border: solid 1px #ccc"></span>
				</div>
				
				
				<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
					<label><spring:message code="label.title" /></label> <select
						name="title" disabled="disabled">
						<option value="-1">SELECT</option>
						<c:forEach var="title" items="${customerMasterDetail.titleMaster}">
							<option value="${title.titleId }"
								${title.titleId==customerDetail.title?"selected":'' }>${title.titleName}</option>
						</c:forEach>
					</select>
				</div>	


				<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
					<label><spring:message code="label.fName" /></label> <input
						type="text" disabled="disabled" value="${customerDetail.fName }" name="fName"
						placeholder="First Name" onkeypress="return onlyCarector(event);"
						maxlength="50">
				</div>
				<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
					<label><spring:message code="label.mName" /></label> <input
						type="text" disabled="disabled" value="${customerDetail.mName }" name="mName"
						placeholder="Middle Name" onkeypress="return onlyCarector(event);"
						maxlength="50">
				</div>
				<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
					<label><spring:message code="label.lName" /> </label> <input
						type="text" disabled="disabled" value="${customerDetail.lName }" name="lName"
						placeholder="Last Name" onkeypress="return onlyCarector(event);"
						maxlength="50">
				</div>
				<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
					<label><spring:message code="label.marital" /></label> <select
						name="maritalStatus" disabled="disabled">
						<option value="-1">SELECT</option>
						<c:forEach var="marital"
							items="${customerMasterDetail.maritalStatus }">
							<option value="${marital.maritalStatusid }"
								${marital.maritalStatusid==customerDetail.maritalStatus?"selected":'' }>
								${marital.maritalStatusname}</option>
						</c:forEach>
					</select>
				</div>
				<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
					<label class="highlighted-eligibility"><spring:message
							code="label.dob" /></label> <input type="text"
						value="<fmt:formatDate type="date" value="${customerDetail.dob }" pattern="dd-MM-yyyy"/>"
						id="datepicker2" onchange="validateBirthDate(this)" name="dob"
						placeholder="Date of Birth"
						style="background-color: #D8FFFF; border: solid 1px #CCC !important;" disabled="disabled"><span
						class="dobCalc glyphicon glyphicon-calendar" for="datepicker2"
						aria-hidden="true"></span>
				</div>
				<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
					<label><spring:message code="label.gender" /></label> <select
						name="gender" disabled="disabled">
						<option value="-1">SELECT</option>
						<c:forEach var="gender" items="${customerMasterDetail.gender }">
							<option value="${gender.genderId }"
								${gender.genderId ==customerDetail.gender?"selected":'' }>${gender.genderName
								}</option>
						</c:forEach>
					</select>
				</div>
				<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
					<label><spring:message code="label.nationality" /></label> <select
						name="nationality" disabled="disabled">
						<option value="-1">SELECT</option>
						<c:forEach var="nationality"
							items="${customerMasterDetail.nationality }">
							<option value="${nationality.nationalityId }"
								${nationality.nationalityId ==customerDetail.nationality?"selected":'' }>${nationality.nationName}</option>
						</c:forEach>
					</select>
				</div>
				<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
					<label><spring:message code="label.dependents" /></label> <input
						type="text" name="noOfDependents" disabled="disabled" class="rgtAlign"
						value="${customerDetail.noOfDependents}" maxlength="2"
						onkeypress="return onlyNumeric(event);" />
				</div>
				<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
					<label><spring:message code="label.pan" /></label> <input
						type="text" disabled="disabled" value="${customerDetail.pan }" name="pan"
						placeholder="PAN Card No" maxlength="10"
						onchange="return checkPAN(this);">
				</div>
				<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
					<label><spring:message code="label.aadhar" /></label> <input
						type="text" disabled="disabled" class="rgtAlign"
						value="${customerDetail.adhaarNumber }" name="adhaarNumber"
						placeholder="Aadhar No." maxlength="12"
						onkeypress="return onlyNumeric(event);">
				</div>
				<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
					<label><spring:message code="label.industry" /></label> <select
						name="industryId" disabled="disabled" onchange=getTypeOfBusinessName(this,"I")>
						<option value="-1">SELECT</option>
						<c:forEach var="obj"
							items="${customerMasterDetail.industryMaster}">
							<option value="${obj.industryId }"
								${obj.industryId==customerDetail.industryId?'Selected':'' }>${obj.displayName}</option>
						</c:forEach>
					</select>
				</div>				
				<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
					<label><spring:message code="label.typeOfBusiness" /></label> <select
						name="typeOfBusiness" disabled="disabled" onchange=getClusterName(this,"I")>
						<option value="-1">select</option>
						<c:forEach var="obj"
							items="${customerMasterDetail.typeOfBusinessMaster}">
							<option value="${obj.id}" 
							${obj.id==customerDetail.typeOfBusiness?'Selected':''} >${obj.typeOfBusiness}</option>
						</c:forEach>
					</select>
				</div>
				<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
					<label><spring:message code="label.cluster" /></label> <select
						name="cluster" disabled="disabled">
						<option value="-1">select</option>
						<c:forEach var="obj" items="${customerMasterDetail.clusterMaster}">
							<option value="${obj.clusterId }"
							${obj.clusterId==customerDetail.cluster?'Selected':''}>${obj.clusterName}</option>
						</c:forEach>
					</select>
				</div>				
				<!-- Added by Anuj on 30/12/2016 -->
				<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
					<label><spring:message code="label.sector" /></label> <select
						name="sectorId" disabled="disabled">
						<option value="-1">select</option>
						<c:forEach var="obj" items="${customerMasterDetail.sectorMaster}">
							<option value="${obj.sectorId }"
								${obj.sectorId==customerDetail.sectorId?'Selected':'' }>${obj.sectorName}</option>
						</c:forEach>
					</select>
				</div>
				<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
					<label><spring:message code="label.stage" /></label> <select
						name="stageId" disabled="disabled">
						<option value="-1">SELECT</option>
						<c:forEach var="obj" items="${customerMasterDetail.stageMaster}">
							<option value="${obj.stageId }"
								${obj.stageId==customerDetail.stageId?'Selected':'' }>${obj.stageName}</option>
						</c:forEach>
					</select>
				</div>
				<!-- End -->

			</div>
			<div class="incomedetails hidden">
				<div class="row buttonHeading">
					<div class="col-lg-9 col-md-9 col-sm-9 col-xs-10">
						<h2 class="lineHeightCstm heading">Income Details</h2>
					</div>
					<div class="col-lg-3 col-md-3 col-sm-3 col-xs-2 btnPosition">
						<a class="toggle-list btn-primary"> <span
							class="toggle-icon minus-icon1 "></span></a>
						<!-- <button class="btn btn-primary save-occupational-details"
						onclick="saveOccupational()">Save</button> -->
					</div>
				</div>
				<div class="row contactList leadstatus occ-dtls">


					<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12 companyClass"
						style="position: relative">
						<label class="highlighted-eligibility"><spring:message
								code="label.company" /></label> <span class="sel-ttip"> <input
							type="text" disabled="disabled" id="companyName" name="companyName"
							value="${customerDetail.companyName} " />
						</span>
						<%-- <select name="companyName">
						<option value="-1">select</option>
						<c:forEach var="company" items="${customerMasterDetail.companyName}">
							<option value="${company.caseCompanyId }"
								${company.caseCompanyId==customerDetail.companyName?'Selected':'' }>${company.displayName}</option>
						</c:forEach>
					</select> --%>
					</div>

					<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12 companyClass">
						<label><spring:message code="label.otherCompanyName" /></label> <input
							type="text" disabled="disabled" value="${customerDetail.otherCompanyName}"
							name="otherCompanyName">
					</div>

					<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12 field1">
						<label><spring:message code="label.designation" /></label><input
							type="text" name="desig" disabled="disabled" value="${customerDetail.desig}">
					</div>
					<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12 field1">
						<label><spring:message code="label.modeOfSalary" /></label> <select
							name="salaryMode" disabled="disabled">
							<option value="-1">select</option>
							<c:forEach var="salary"
								items="${customerMasterDetail.salaryMode}">
								<option value="${salary.salaryModeId }"
									${salary.salaryModeId==customerDetail.salaryMode?'Selected':'' }>${salary.salaryMode}</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12 field1">
						<label class="highlighted-eligibility"><spring:message
								code="label.years" /></label> <input type="text"
							disabled="disabled" placeholder="Years in Current Job"
							value="${customerDetail.yearsCurrentJob }" name="yearsCurrentJob"
							onkeypress="return onlyNumeric(event);" maxlength="2"
							class="rgtAlign" />
					</div>
					<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12 field1">
						<label class="highlighted-eligibility"><spring:message
								code="label.work" /></label> <input type="text"
							placeholder="Work Experience" disabled="disabled" value="${customerDetail.workExp }"
							name="workExp" class="rgtAlign"
							onkeypress="return onlyNumeric(event);" maxlength="2" />
					</div>
					<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12 field2">
						<label><spring:message code="label.ctc" /></label> <input
							type="text" placeholder="" disabled="disabled"
							value="${customerDetail.annualIncome }" name="annualIncome"
							class="rgtAlign" maxlength="13"
							onchange="return checkAfter10DigitDecimals(this);" />
					</div>

					<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12 field2">
						<label><spring:message code="label.gross" /></label> <input
							type="text" placeholder="" disabled="disabled"
							value="${customerDetail.grossMonthlyIncome }"
							name="grossMonthlyIncome" maxlength="13" class="rgtAlign"
							onchange="return checkAfter10DigitDecimals(this);" />
					</div>
					<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12 field2">
						<label><spring:message code="label.netMIn" /></label> <input
							type="text" placeholder="" disabled="disabled"
							value="${customerDetail.netMonthlyIncome }" class="rgtAlign"
							name="netMonthlyIncome" maxlength="13"
							onchange="return checkAfter10DigitDecimals(this);" />
					</div>
					<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12 field1">
						<label><spring:message code="label.bonus" /></label> <input
							type="text" placeholder="" disabled="disabled"
							value="${customerDetail.bonusIncentive }" name="bonusIncentive"
							class="rgtAlign" maxlength="13"
							onchange="return checkAfter10DigitDecimals(this);" />
					</div>
					<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
						<label><spring:message code="label.rental" /></label> <input
							type="text" placeholder="" disabled="disabled"
							value="${customerDetail.monthlyRentalIncome }"
							name="monthlyRentalIncome" maxlength="13"
							onchange="return checkAfter10DigitDecimals(this);"
							class="rgtAlign" />
					</div>
					<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12 field3">
						<label><spring:message code="label.annualSale" /></label> <input
							type="text" placeholder="" disabled="disabled"
							value="${customerDetail.annualSalesTurnover }"
							name="annualSalesTurnover" maxlength="13"
							onchange="return checkAfter10DigitDecimals(this);"
							class="rgtAlign" />
					</div>
					<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12 field3">
						<label><spring:message code="label.profit" /></label> <input
							type="text" placeholder="" disabled="disabled" value="${customerDetail.grossProfit }"
							name="grossProfit" maxlength="13"
							onchange="return checkAfter10DigitDecimals(this);"
							class="rgtAlign" />
					</div>
					<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12 field3">
						<label><spring:message code="label.netProfitTax" /></label> <input
							type="text" placeholder="" disabled="disabled"
							value="${customerDetail.netProfitAfterTax }"
							name="netProfitAfterTax" maxlength="13"
							onchange="return checkAfter10DigitDecimals(this);"
							class="rgtAlign" />
					</div>
					<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12 field3">
						<label><spring:message code="label.deprication" /></label> <input
							type="text" placeholder="" disabled="disabled" value="${customerDetail.depreciation}"
							name="depreciation" maxlength="13"
							onchange="return checkAfter10DigitDecimals(this);"
							class="rgtAlign" />
					</div>
					<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
						<label><spring:message code="label.otherAnnual" /></label> <input
							type="text" placeholder="" disabled="disabled"
							value="${customerDetail.otherAnnualIncome }"
							name="otherAnnualIncome" maxlength="13"
							onchange="return checkAfter10DigitDecimals(this);"
							class="rgtAlign" />
					</div>
					<%-- <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
					<label>Date of Incorporation</label> <input type="text"
						placeholder="" id="datepicker1"
						onchange="validateDateIncorporation(this)"
						value="<fmt:formatDate type="date" value="${customerDetail.incorporationDate }" pattern="dd-MM-yyyy"/>"
						name="incorporationDate" /><span
						class="dobCalc glyphicon glyphicon-calendar" for="datepicker1"
						aria-hidden="true"></span>
				</div> --%>
					<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12 field3">
						<label><spring:message code="label.worth" /></label> <input
							type="text" placeholder="Net Worth" disabled="disabled"
							value="${customerDetail.netWorth }" name="netWorth"
							maxlength="13" onchange="return checkAfter10DigitDecimals(this);"
							class="rgtAlign" />
					</div>
					<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12 field1">
						<label class="highlighted-eligibility"><spring:message
								code="label.corp" /></label> <select name="corporateSalaryAccount" disabled="disabled">
							<option value="Y"
								${customerDetail.corporateSalaryAccount=="Y"?'selected':'' }>Yes</option>
							<option value="N"
								${customerDetail.corporateSalaryAccount=="N"?'selected':'' }>No</option>
						</select>
					</div>

					<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12 field3">
						<label><spring:message code="label.directorSalary" /></label> <input
							type="text" placeholder="" disabled="disabled"
							value="${customerDetail.directorSalary }" name="directorSalary"
							maxlength="13" onchange="return checkAfter10DigitDecimals(this);"
							class="rgtAlign" />
					</div>
					<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
						<label><spring:message code="label.intrestPaid" /></label> <input
							type="text" placeholder="" disabled="disabled"
							value="${customerDetail.interesrPaidOnLoan }"
							name="interesrPaidOnLoan" maxlength="13"
							onchange="return checkAfter10DigitDecimals(this);"
							class="rgtAlign" />
					</div>

				</div>
			</div>
			<div id="residenceAddress" style="margin-top: 16px">
				<div class="row buttonHeading" style="border:none;">
					<div class="col-lg-9 col-md-9 col-sm-9 col-xs-6">
						<h2 class="heading">Address Details</h2>
					</div>
					<div class="col-lg-3 col-md-3 col-sm-3 col-xs-6 btnPosition">
						<a class="toggle-list btn-primary"> <span
							class="toggle-icon minus-icon1 "></span></a>
						<!-- <button class="btn btn-primary save-address-details"
							onclick="saveAddress();">Save</button> -->
						<button type="button" class="btn btn-primary delrow-detail "
							onclick="deleteAddress()" disabled="disabled">Delete</button>
						<button type="button" class="btn btn-primary addrow-detail"
							value="${customerDetail.listAddress eq null ? '0' : fn:length(customerDetail.listAddress)}" disabled="disabled">Add</button>
					</div>
				</div>
				<div class="row contactList leadstatus top-one-margin">
					<c:forEach var="addressDetail"
						items="${customerDetail.listAddress}" varStatus="addressStatus">
							<div class="deletchk">
								<input type="checkbox" placeholder="checkbox"
									value="${addressDetail.addressId}" disabled="disabled"
									name="listAddress[${addressStatus.index }].addressId" />
							</div>
							<div class="mailingAddressDiv">						
							<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
								<label><spring:message code="label.addressType" /></label> <span
									class="sel-ttip"> <select
									name="listAddress[${addressStatus.index }].addressType" disabled="disabled">
										<option value="-1">Select</option>
										<c:forEach var="addressType"
											items="${customerMasterDetail.addressType }">
											<option value="${addressType.addressTypeId }"
												${addressType.addressTypeId==addressDetail.addressType?'Selected':'' }>
												${addressType.addressTypeDisplayName}</option>
										</c:forEach>
								</select>
								</span> <span class="select-tooltip"
									style="margin-top: -15px; background: #fff; border: solid 1px #ccc"></span>
							</div>
							<div
								class="col-lg-4 col-md-3 col-sm-6 col-xs-12 addressField addresscustomer">
								<label class="minWidth labelcustomerwidth">Home Address</label>
								<!--
								 <textarea name="listAddress[${addressStatus.index }].address"
									maxlength="50" placeholder="Address">${addressDetail.address }</textarea>
							      -->
								<div class="col-lg-4 col-md-3 col-sm-3 col-xs-3 padding-left-img padding-left-margin">
									<input disabled="disabled" type="text" name="listAddress[${addressStatus.index }].address" placeholder="Address" value="${addressDetail.address }" id="addressInput">
								</div>
							</div>
							<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
								<label><spring:message code="label.mailingAddress" /></label> <input
									type="checkbox" placeholder="checkbox" disabled="disabled"
									name="listAddress[${addressStatus.index }].mailingAddress"
									${addressDetail.mailingAddress=="Y"?'Checked':'' } />
							</div>
                                
                               <div
								class="col-lg-4 col-md-3 col-sm-6 col-xs-12 addressField areaname">
								<label id="listAddress[${addressStatus.index }].floorNo"
									class="minWidth labelcustomerwidth">Area Name</label>

								<div
									class="col-lg-4 col-md-3 col-sm-3 col-xs-3 padding-left-img padding-left-margin">
									<input type="text"
										name="listAddress[${addressStatus.index }].floorNo"
										placeholder="Address" disabled="disabled"  value="${addressDetail.floorNo }"
										id="listAddress[${addressStatus.index }].floorNo">
								</div>
							</div>
                                
                                
                                
							<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
								<label><spring:message code="label.state" /></label> <span
									class="sel-ttip"> <select
									name="listAddress[${addressStatus.index }].state"
									disabled="disabled" id="listAddress[${addressStatus.index }].state"
									onchange='getCitiesByState(this);'>
										<option value="-1">Select</option>
										<c:forEach var="state"
											items="${customerMasterDetail.stateList}">
											<option value="${state.stateMasterId }"
												${state.stateMasterId==addressDetail.state?'Selected':'' }>${state.displayName}</option>
										</c:forEach>
								</select>
								</span> <span class="select-tooltip"
									style="margin-top: -15px; background: #fff; border: solid 1px #ccc; z-index: 10000;"></span>
							</div>
							
							
							<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
								<label><spring:message code="label.city" /></label> <select
									name="listAddress[${addressStatus.index }].city"
									id="listAddress[${addressStatus.index }].city" disabled="disabled">
									<%-- <option value="-1">select</option>
									<c:forEach var="city" items="${customerMasterDetail.city }"> --%>
									<option value="${addressDetail.city}">${addressDetail.cityName}</option>
									<%-- </c:forEach> --%>
								</select>
							</div>
							<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
								<label><spring:message code="label.pin" /></label> <input
									type="text" class="rgtAlign" disabled="disabled" value="${addressDetail.zipcode }"
									name="listAddress[${addressStatus.index }].zipcode"
									maxlength="6" onchange="validatePinCode(this);"
									onkeypress="return onlyNumeric(event);" />
							</div>
							
								<div
								class="col-lg-4 col-md-3 col-sm-6 col-xs-12 addressField district ">
								<label class="minWidth labelcustomerwidth">District</label>


								<div
									class="col-lg-4 col-md-3 col-sm-3 col-xs-3 padding-left-img padding-left-margin">
									<input type="text"
										name="listAddress[${addressStatus.index }].locality"
										placeholder="Address"  disabled="disabled" value="${addressDetail.locality }"
										id="addressInput">
								</div>
							</div>
							<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
								<label><spring:message code="label.landmark" /></label> <input
									type="text" placeholder="Landmark" disabled="disabled"
									value="${addressDetail.landmark }"
									name="listAddress[${addressStatus.index }].landmark"
									maxlength="50" />
							</div>
							
							<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
								<label>Mobile1</label> <input onchange="validateMobile(this);"
									id="contactNores1" type="text" class="rgtAlign"
									placeholder="Landline" disabled="disabled"
									name="listAddress[${addressStatus.index }].mobile_no1"
									name="listAddress[${addressStatus.index }].mobile_no1"
									value="${addressDetail.mobile_no1}"
									class=" set-input-width rgtAlign" maxlength="10"
									onkeypress="return onlyNumeric(event);" maxlength="12"
									style="left: -1px; position: relative; width: 59.5% !important;" />
							</div>


							<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
								<label>Mobile2</label> <input onchange="validateMobile(this);"
									id="contactNores2" type="text" class="rgtAlign"
									placeholder="Landline" disabled="disabled"
									name="listAddress[${addressStatus.index }].mobile_no2"
									name="listAddress[${addressStatus.index }].mobile_no2"
									value="${addressDetail.mobile_no2}"
									class=" set-input-width rgtAlign" maxlength="10"
									onkeypress="return onlyNumeric(event);" maxlength="12"
									style="left: -1px; position: relative; width: 59.5% !important;" />
							</div>

							<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
								<label>LANDLINE1</label> <input
									type="text" class="rgtAlign" placeholder="Landline"
									value="${addressDetail.phone1 }" disabled="disabled"
									name="listAddress[${addressStatus.index }].phone1"
									onkeypress="return onlyNumeric(event);" maxlength="12" />
							</div>
							
							
							
							<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12 landline2">
								<label id="listAddress[${addressStatus.index }].phone2_label">Landline2</label>
								<input type="text" class="rgtAlign"
									name="listAddress[${addressStatus.index }].phone2" disabled="disabled"
									id="listAddress[${addressStatus.index }].phone2"
									placeholder="Landline" value="${addressDetail.phone2}"
									onkeypress="return onlyNumeric(event);" maxlength="12" />
							</div>
							
							<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12 fax">
								<label id="listAddress[${addressStatus.index }].fax_label">Fax</label>
								<input type="text" class="rgtAlign" placeholder="Fax" disabled="disabled"
									value="${addressDetail.fax }"
									name="listAddress[${addressStatus.index }].fax"
									onkeypress="return onlyNumeric(event);" maxlength="5"
									id="listAddress[${addressStatus.index }].fax" />
							</div>
							<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12 hidden">
								<label><spring:message code="label.extension" /></label> <input
									type="text" class="rgtAlign" placeholder="Extension" disabled="disabled"
									value="${addressDetail.ext1 }"
									name="listAddress[${addressStatus.index }].ext1"
									onkeypress="return onlyNumeric(event);" maxlength="5" />
							</div>
							<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
								 <label><spring:message code="label.livingSince" /></label> <%--<input
									type="text" class="minWidth widthcstm set-width rgtAlign" disabled="disabled"
									value="${addressDetail.occupancyMm }" maxlength="15" list="monthList"
									placeholder="Months" name="listAddress[${addressStatus.index }].occupancyMm" onchange="validationForMonth(this)" /> 									 --%>
									
									<input type="text"
									class="minWidth widthcstm set-width rgtAlign" disabled="disabled"
									value="${addressDetail.occupancyYr }" maxlength="4"
									placeholder="Years" onchange="validateYear(this)"
									name="listAddress[${addressStatus.index }].occupancyYr"
									onkeypress="return onlyNumeric(event);" list="yearList" />
									
								<datalist id="monthList">
									<option value="1">January</option>
									<option value="2" >February</option>
									<option value="3" >March</option>
									<option value="4" >April</option>
									<option value="5" >May</option>
									<option value="6" >June</option>
									<option value="7" >July</option>
									<option value="8" >August</option>
									<option value="9" >September</option>
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
							<div
								class="col-lg-4 col-md-3 col-sm-6 col-xs-12 bussinessestablishment">
								<label
									id="listAddress[${addressStatus.index }].bussinessestbyr_label">Yrs.Bussiness
									Estblishment Year</label> <input type="text"
									class="minWidth set-width widthcstm rgtAlign"
									value="${addressDetail.bussinessestbyr }" maxlength="4" disabled="disabled"
									placeholder="Years" onchange="validateYear(this)"
									name="listAddress[${addressStatus.index }].bussinessestbyr"
									onkeypress="return onlyNumeric(event);" list="yearList"
									id="listAddress[${addressStatus.index }].bussinessestbyr" />


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
						<%-- 	<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
								
								<label class="LabelHover">Occupancy St...<span style="display:none;"><spring:message code="label.occupation" /></span></label>
								
								<label><spring:message code="label.occupation" /></label> <span
									class="sel-ttip"> <select
									name="listAddress[${addressStatus.index }].occupancyStatus" disabled="disabled">
										<option value="-1">select</option>
										<c:forEach var="status"
											items="${customerMasterDetail.occupancyStatus }">
											<option value="${status.occupancyStId }"
												${status.occupancyStId==addressDetail.occupancyStatus?'selected':'' }>${status.occupancyStName}</option>
										</c:forEach>
								</select>
								</span> <span class="select-tooltip"
									style="margin-top: -15px; background: #fff; border: solid 1px #ccc;"></span>
							</div> --%>
						</div>
					</c:forEach>
				</div>
			</div>
			<div class="row buttonHeading">
				<div class="col-lg-9 col-md-9 col-sm-9 col-xs-5">
					<h2 class="heading lineHeightCstm">Key Contacts</h2>
				</div>
				<div class="col-lg-3 col-md-3 col-sm-3 col-xs-7 btnPosition">
					<!-- <a class="btn-primary toggle-list"> <span
						class="toggle-icon minus-icon1 "></span>
					</a> -->
					<!-- <button class="btn btn-primary save-key-contacts"
						onclick="auKeyContact()">Save</button> -->
					<button type="button" class="btn btn-primary delrow "
						onclick="delKeyContact()" disabled="disabled">Delete</button>
					<button type="button" class="btn btn-primary addrow-keycontacts"
						value="${customerDetail.keyContacts eq null ? '0' : fn:length(customerDetail.keyContacts)}" disabled="disabled">Add</button>
				</div>
			</div>
			<div class="row keycontt">
				<div class="bs-example">
					<table class="table table-striped tr-padding-zero">
						<thead>
							<tr>
								<th>&nbsp;</th>
								<th><spring:message code="label.contactType" /></th>
								<th>Name</th>
								<th>Firm Name</th>
								<th>Mobile</th>
								<th>Email ID</th>
								<th>Address
							</tr>
						</thead>
						<tbody>
							<c:forEach var="key" items="${customerDetail.keyContacts }"
								varStatus="status">
								<tr>
									<td width="1%"><input type="checkbox" disabled="disabled"
										value="${key.keyContactId }"
										name="keyContacts[${status.index}].keyContactId"
										style="margin-top: 6px" /></td>
									<td class="sel-ttip-set"><span class="sel-ttip"> <select
											name="keyContacts[${status.index}].contactTypeId"
											style="width: 130px" disabled="disabled">
												<option value="-1">select</option>
												<c:forEach var="contact"
													items="${customerMasterDetail.contact }">
													<option value="${contact.contactTypeId }"
														${contact.contactTypeId==key.contactTypeId?'selected':'' }>${contact.contactType
														}</option>
												</c:forEach>
										</select>
									</span> <span class="select-tooltip"
										style="margin-top: -15px; background: #fff; border: solid 1px #ccc"></span>
									</td>
									<td><input type="text"
										value="${key.fname } ${key.mname} ${key.lname}" disabled="disabled"
										name="keyContacts[${status.index}].fname" id="fullName"
										onkeypress="return onlyCarector(event);"
										onchange="splitUpdatedFullName(this,${status.index})"
										maxlength="50" style="width: 130px" /></td>

									<td><input type="text" value="${key.firmName }" disabled="disabled"
										name="keyContacts[${status.index}].firmName" maxlength="50"
										style="width: 130px" /></td>
									<td><input type="text" value="${key.mobile }" disabled="disabled"
										name="keyContacts[${status.index}].mobile" maxlength="10"
										class="rgtAlign" onkeypress="return onlyNumeric(event);"
										onchange="validateMobile(this);" style="width: 130px" /></td>
									<td><input type="text" value="${key.email}" disabled="disabled"
										onchange="validateEmail(this);"
										name="keyContacts[${status.index}].email" maxlength="100"
										style="width: 130px" /></td>
									<td><input value="${key.address }" type="text" disabled="disabled"
										name="keyContacts[${status.index}].address" maxlength="200" /></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>

			<div>
				<div class="row buttonHeading">
					<div class="col-lg-9 col-md-9 col-sm-9 col-xs-6">
						<h2 class="heading">Property/Collateral Details</h2>
					</div>
					<div class="col-lg-3 col-md-3 col-sm-6 col-xs-6 btnPosition">
						<a class="toggle-list btn-primary"> <span
							class="toggle-icon minus-icon1 "></span>
						</a>
						<!-- <button class="btn btn-primary save-property-details"
						onclick="auProperty();">Save</button> -->

						<!-- As pr sussation of Ashish Sir Add and Delete Button will remove only one property details will save  -->
						<!--  <button type="button" class="btn btn-primary delrow "
						onclick="delProperty();">Delete</button> -->
						<button type="button" class="btn btn-primary addrow-prop-details"
							style="display: none;"
							value="${customerDetail.listProperty eq null ? '0' : fn:length(customerDetail.listProperty)}" disabled="disabled">Add</button>
						<!-- As pr sussation of Ashish Sir Add and Delete Button will remove only one property details will save  -->
					</div>
				</div>
				<div class="row contactList leadstatus">
					<c:forEach var="property" items="${customerDetail.listProperty }"
						varStatus="propStatus">
						<div class="row propertyCheckbox">
							<input type="checkbox" value="${property.propertyId }"
								checked="checked" style="display: none"
								name="listProperty[${propStatus.index }].propertyId" disabled="disabled"/>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
							<label><spring:message code="label.propertyType" /></label> <span
								class="sel-ttip"> <select
								disabled="disabled"
								name="listProperty[${propStatus.index }].propTypeId">
									<option value="-1">select</option>
									<c:forEach var="prop" items="${customerMasterDetail.propType }">
										<option value="${prop.propTypeId }"
											${prop.propTypeId==property.propTypeId?'selected':'' }>${prop.propTypeName
											}</option>
									</c:forEach>
							</select>
							</span> <span class="select-tooltip"
								style="margin-top: -15px; background: #fff; border: solid 1px #ccc"></span>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
							<label><spring:message code="label.propertyStatus" /></label> <select
								name="listProperty[${propStatus.index }].propStatus" disabled="disabled">
								<option value="-1">select</option>
								<c:forEach var="status1"
									items="${customerMasterDetail.propStatus }">
									<option value="${status1.propStatusId }"
										${status1.propStatusId==property.propStatus?'selected':'' }>${status1.propStatusName
										}</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
							<label><spring:message code="label.developerName" /></label> <select
								id="listProperty[${propStatus.index }].devloperId"
								name="listProperty[${propStatus.index }].devloperId"
								onchange="populateProjectName(this);" disabled="disabled">
								<option value="-1">select</option>
								<c:forEach var="devloper"
									items="${customerMasterDetail.devloperMasters }">
									<option value="${devloper.devloperId }"
										${property.devloperId==devloper.devloperId?'selected':'' }>${devloper.devloperName}</option>

								</c:forEach>
							</select>
						</div>

						<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
							<label><spring:message code="label.otherDeveloperName" /></label>
							<span class="sel-ttip"> <input type="text" disabled="disabled"
								value="${property.otherDeploperName}" maxlength="50"
								name="listProperty[${propStatus.index }].otherDeploperName" />
							</span> <span class="select-tooltip"
								style="margin-top: -15px; background: #fff; border: solid 1px #ccc; z-index: 10000;"></span>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
							<label><spring:message code="label.projectName" /></label> <select
								name="listProperty[${propStatus.index}].projectId" disabled="disabled">
								<option value="${property.projectId}" selected>${property.projectName}</option>
							</select>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
							<label><spring:message code="label.otherProjectName" /></label> <input
								type="text" value="${property.otherProjectName }"
								maxlength="100" disabled="disabled"
								name="listProperty[${propStatus.index }].otherProjectName" />
						</div>
						<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
							<label><spring:message code="label.estimatedValue" /></label> <input
								type="text" value="${property.estimatedValue }" maxlength="13" disabled="disabled"
								name="listProperty[${propStatus.index }].estimatedValue"
								onkeypress="return onlyNumeric(event);"
								onchange="testNumeric(this,'Estimated value')" />
						</div>
						<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
							<label><spring:message code="label.propertyAddress" /></label> <input
								type="text" disabled="disabled" value="${property.address }" maxlength="200"
								name="listProperty[${propStatus.index }].address" />
						</div>

						<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
							<label><spring:message code="label.landmark" /></label> <input
								type="text" disabled="disabled" value="${property.landMark }"
								name="listProperty[${propStatus.index }].landMark"
								maxlength="50" />
						</div>
						<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
							<label><spring:message code="label.state" /></label> <select
								name="listProperty[${propStatus.index }].state"
								disabled="disabled" id="listProperty[${propStatus.index}].state"
								onchange='getCitiesByState(this);'>
								<option value="-1">select</option>
								<c:forEach var="state1"
									items="${customerMasterDetail.stateList}">
									<option value="${state1.stateMasterId }"
										${state1.stateMasterId==property.state?'selected':'' }>${state1.displayName
										}</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
							<label><spring:message code="label.city" /></label> <select
								name="listProperty[${propStatus.index }].city" disabled="disabled">
								<%-- <option value="-1">select</option>
									<c:forEach var="city" items="${customerMasterDetail.city}"> --%>
								<option value="${property.city }">${property.cityName}</option>
								<%-- </c:forEach> --%>
							</select>
						</div>

						<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
							<label><spring:message code="label.pin" /></label> <input
								type="text" disabled="disabled" value="${property.zipcode }" class="rgtAlign"
								name="listProperty[${propStatus.index }].zipcode"
								onchange="validatePinCode(this);"
								onkeypress="return onlyNumeric(event);" maxlength="6"
								onchange="validatePinCode(this);" />
						</div>
						<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">							
							<label class="LabelHover">Occupacy ...<span style="display:none;"><spring:message code="label.occupation" /></span></label> 
								<select
								name="listProperty[${propStatus.index }].occupancyStatus" disabled="disabled">
								<option value="-1">select</option>
								<c:forEach var="status1"
									items="${OccupancyStatusForProperty.occupancyStatus }">
									<option value="${status1.occupancyStId }"
										${status1.occupancyStId==property.occupancyStatus?'selected':'' }>${status1.occupancyStName
										}</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
							<label><spring:message code="label.remarks" /></label> <input
								type="text" disabled="disabled" value="${property.remarks }"
								name="listProperty[${propStatus.index }].remarks" maxlength="50" />
						</div>

					</c:forEach>
				</div>
			</div>
		</form>
	</div>
	<!-- Modal -->
	<div class="modal fade" id="myModal1" role="dialog"
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
					<textarea class="modal-textarea" name="remark"></textarea>
				</div>
				<div class="modal-footer model-border-none">
					<button type="button" class="btn btn-default" data-dismiss="modal"
						onclick="setData1()" disabled="disabled">Close</button>
				</div>
			</div>

		</div>
	</div>
	<!-- Modal -->
	<!-- Modal -->
	<div class="modal fade" id="myModal2" role="dialog"
		style="display: none;" aria-hidden="true">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header model-border-none">
					<button type="button" class="close" data-dismiss="modal"
						onclick="setData()" disabled="disabled">×</button>
					<h4 class="modal-title"></h4>
				</div>
				<div class="modal-body">
					<textarea class="modal-textarea" name="remark" disabled="disabled"></textarea>
				</div>
				<div class="modal-footer model-border-none">
					<button type="button" class="btn btn-default" data-dismiss="modal"
						onclick="setData()">Close</button>
				</div>
			</div>

		</div>
	</div>
	<!-- Modal -->
	<!-- Modal -->
	<div class="modal fade" id="myModal4" role="dialog"
		style="display: none;" aria-hidden="true">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header model-border-none">
					<button type="button" class="close" data-dismiss="modal"
						onclick="setData1()" disabled="disabled">×</button>
					<h4 class="modal-title"></h4>
				</div>
				<div class="modal-body">
					<textarea class="modal-textarea" name="remark" id="modalAddress" disabled="disabled"></textarea>
				</div>
				<div class="modal-footer model-border-none">
					<button type="button" class="btn btn-default" data-dismiss="modal"
						onclick="setData1()" disabled="disabled">Close</button>
				</div>
			</div>

		</div>
	</div>
	<!-- Modal -->
	<c:if test="${not empty lead}">
		<input type="hidden" name="error" id="error" value="${lead}" />
	</c:if>
</div>

<!-- <link rel="stylesheet" type="text/css" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" /> -->
<script type="text/javascript" src="resources/js/jquery.min.js"></script>
<script type="text/javascript" src="resources/js//jquery-ui.min.js"></script>


<script>

//added by tripti
$("#entityType").change(function() {

	if ($(this).find("option:selected").val() == "1000000001") {
		//alert("PTPP");
		$(".personalDetailForNonIndividual").addClass("hidden");
		$(".personalDetailForIndividual").removeClass("hidden");

	} else {
		//alert("noptp");
		$(".personalDetailForIndividual").addClass("hidden");
		$(".personalDetailForNonIndividual").removeClass("hidden");
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
	$('.field2').css('display','none');
	$('.field3').css('display','block');
	$('.field1').css('display','none');
	$('.companyClass').css('display','block');	
	$('.incomedetails').removeClass('hidden');
	}
}

}
$(document).ready(function(){
	
	if ($("#entityType").val() == "1000000001") {
		
		$(".personalDetailForNonIndividual").addClass("hidden");
		$(".personalDetailForIndividual").removeClass("hidden");
		$('.incomedetails').removeClass('hidden');

	} else if($("#entityType").val() == "1000000002"){
		
		$(".personalDetailForIndividual").addClass("hidden");
		$(".personalDetailForNonIndividual").removeClass("hidden");
		$('.incomedetails').removeClass('hidden');
	}
	else{
		$(".personalDetailForIndividual").addClass("hidden");
		$(".personalDetailForNonIndividual").addClass("hidden");
	}
	incomeDetailsfields();		
});

function madatoryForEligibility(){
	//alert('jk');
	//var appType=document.getElementById('occupationTypeId').value;
		var appType=$("#occupationTypeId option:selected").text();
	if(appType=='SALARIED')
		{
		$("[name='depreciation']").removeClass("highlight_only");
		$("[name='directorSalary']").removeClass("highlight_only");
		$("[name='otherAnnualIncome']").removeClass("highlight_only");
		$("[name='interesrPaidOnLoan']").removeClass("highlight_only");
		$("[name='grossProfit']").removeClass("active_state");
		$("[name='netProfitAfterTax']").removeClass("active_state");
		$("[name='annualSalesTurnover']").removeClass("active_state");
			$("[name='netMonthlyIncome']").addClass("active_state");
			$("[name='grossMonthlyIncome']").addClass("active_state");
			$("[name='yearsCurrentJob']").addClass("active_state");
			$("[name='workExp']").addClass("active_state");
			$("[name='companyName']").addClass("active_state");
			$("[name='monthlyRentalIncome']").addClass("highlight_only");
		}
	else if(appType=='SELF EMPLOYED')
	{
		$("[name='netMonthlyIncome']").removeClass("active_state");
		$("[name='grossMonthlyIncome']").removeClass("active_state");
		$("[name='yearsCurrentJob']").removeClass("active_state");
		$("[name='workExp']").removeClass("active_state");
		$("[name='companyName']").removeClass("active_state");
		//$("[name='monthlyRentalIncome']").removeClass("highlight_only");	
			$("[name='grossProfit']").addClass("active_state");
			$("[name='netProfitAfterTax']").addClass("active_state");
			$("[name='annualSalesTurnover']").addClass("active_state");
			$("[name='depreciation']").addClass("highlight_only");
			$("[name='directorSalary']").addClass("highlight_only");
			$("[name='otherAnnualIncome']").addClass("highlight_only");
			$("[name='interesrPaidOnLoan']").addClass("highlight_only");
			$("[name='monthlyRentalIncome']").addClass("highlight_only");
	}
	else if(appType=='-1'){
		$("[name='netMonthlyIncome']").removeClass();
		$("[name='grossMonthlyIncome']").removeClass();
		$("[name='yearsCurrentJob']").removeClass();
		$("[name='workExp']").removeClass();
		$("[name='companyName']").removeClass();
		$("[name='monthlyRentalIncome']").removeClass();	
			$("[name='grossProfit']").removeClass();
			$("[name='netProfitAfterTax']").removeClass();
			$("[name='annualSalesTurnover']").removeClass();
			$("[name='depreciation']").removeClass();
			$("[name='directorSalary']").removeClass();
			$("[name='otherAnnualIncome']").removeClass();
			$("[name='interesrPaidOnLoan']").removeClass();
			$("[name='monthlyRentalIncome']").removeClass();
	}else{
		$("[name='yearsCurrentJob']").addClass("highlight_only");
		$("[name='workExp']").addClass("highlight_only");
		$("[name='netMonthlyIncome']").removeClass();
		$("[name='grossMonthlyIncome']").removeClass();
		$("[name='companyName']").removeClass();
		$("[name='monthlyRentalIncome']").removeClass();	
			$("[name='grossProfit']").removeClass();
			$("[name='netProfitAfterTax']").removeClass();
			$("[name='annualSalesTurnover']").removeClass();
			$("[name='depreciation']").removeClass();
			$("[name='directorSalary']").removeClass();
			$("[name='otherAnnualIncome']").removeClass();
			$("[name='interesrPaidOnLoan']").removeClass();
			$("[name='monthlyRentalIncome']").removeClass();
	}
	
}
$(document).ready(function() {
	var appType=$("#occupationTypeId option:selected").text();
	//alert(appType);
	
	if(appType=='SALARIED')
		{
		$("[name='depreciation']").removeClass("highlight_only");
		$("[name='directorSalary']").removeClass("highlight_only");
		$("[name='otherAnnualIncome']").removeClass("highlight_only");
		$("[name='interesrPaidOnLoan']").removeClass("highlight_only");
		$("[name='grossProfit']").removeClass("active_state");
		$("[name='netProfitAfterTax']").removeClass("active_state");
		$("[name='annualSalesTurnover']").removeClass("active_state");
		$("[name='netMonthlyIncome']").addClass("active_state");
		$("[name='grossMonthlyIncome']").addClass("active_state");
		$("[name='yearsCurrentJob']").addClass("active_state");
		$("[name='workExp']").addClass("active_state");
		$("[name='companyName']").addClass("active_state");
		$("[name='monthlyRentalIncome']").addClass("highlight_only");
		}
	else if(appType=='SELF EMPLOYED')
	{
		$("[name='netMonthlyIncome']").removeClass("active_state");
		$("[name='grossMonthlyIncome']").removeClass("active_state");
		$("[name='yearsCurrentJob']").removeClass("active_state");
		$("[name='workExp']").removeClass("active_state");
		$("[name='companyName']").removeClass("active_state");
		//$("[name='monthlyRentalIncome']").removeClass("highlight_only");	
			$("[name='grossProfit']").addClass("active_state");
			$("[name='netProfitAfterTax']").addClass("active_state");
			$("[name='annualSalesTurnover']").addClass("active_state");
			$("[name='depreciation']").addClass("highlight_only");
			$("[name='directorSalary']").addClass("highlight_only");
			$("[name='otherAnnualIncome']").addClass("highlight_only");
			$("[name='interesrPaidOnLoan']").addClass("highlight_only");
			$("[name='monthlyRentalIncome']").addClass("highlight_only");
	}
	else if(appType=='-1'){
		//alert("d");
		$("[name='yearsCurrentJob']").removeClass("active_state");
		$("[name='workExp']").removeClass("active_state");
	}
	else{
		$("[name='yearsCurrentJob']").addClass("highlight_only");
		$("[name='workExp']").addClass("highlight_only");
	}
	
	/*function split(val) {
	    return val.split(/,\s*//*);
	/*}
	/*function extractLast(term) {
	    return split(term).pop();
	}*/
	
	/* $( "#companyName" ).autocomplete({
			source: 'get_country_list.do'
	}); */
	if("${fn:length(customerDetail.listProperty)}"==0){	
		
		//$('.addrow-prop-details').parent('.btnPosition').parent().next('.row').find('tbody').append("<tr><td width='1%'><input type='checkbox' name='listProperty[0].propertyId' value='active' checked/></td><td><select name='listProperty[0].propTypeId'><option value='-1'>select</option><c:forEach var='prop' items='${customerMasterDetail.propType }'><option value='${prop.propTypeId }' >${prop.propTypeName}</option></c:forEach></select></td><td><select name='listProperty[0].propStatus'><option value='-1'>select</option><c:forEach var='status1' items='${customerMasterDetail.propStatus }'><option value='${status1.propStatusId }' >${status1.propStatusName}</option></c:forEach></select></td><td><select id='listProperty[0].devloperId' name='listProperty[0].devloperId' onchange='populateProjectName(this);'><option value='-1'>select</option><c:forEach var='devloper' items='${customerMasterDetail.devloperMasters }'><option value='${devloper.devloperId }' ${prop.developerId==devloper.devloperId?'selected':'' }>${devloper.devloperName}</option></c:forEach></select></td><td><input type='text' value='${property.otherDeploperName }'	maxlength='50'	name='listProperty[0].otherDeploperName' /></td><td><select  name='listProperty[0].projectId'><option value='-1'>Select</option></select></td><td><input type='text' value='${property.otherProjectName}' maxlength='100' name='listProperty[0].otherProjectName'/></td><td><input type='text'  name='listProperty[0].estimatedValue' maxlength='13' onkeypress='return onlyNumeric(event);' onchange=\"testNumeric(this,'Estimated value');\" /></td><td><input type='text'  name='listProperty[0].address' maxlength='200'/></td><td><input type='text'  name='listProperty[0].landMark' maxlength='50'/></td><td><select name='listProperty[0].state' id='listProperty[0].state' onchange='getCitiesByState(this);'><option value='-1'>select</option><c:forEach var='state1' items='${customerMasterDetail.stateList}'><option value='${state1.stateMasterId }'>${state1.displayName}</option></c:forEach></select></td><td><select name='listProperty[0].city'><option value='-1'>select</option><c:forEach var='city2' items='${customerMasterDetail.city}'><option value='${city2.cityMasterId}'>${city2.displayName}</option></c:forEach></select></td><td><input type='text'  name='listProperty[0].zipcode' onchange='validatePinCode(this);' onkeypress='return onlyNumeric(event);' maxlength='6' /></td><td><select	name='listProperty[0].occupancyStatus'><option value='-1'>select</option><c:forEach var='status1' items='${OccupancyStatusForProperty.occupancyStatus }'><option value='${status1.occupancyStId }'>${status1.occupancyStName}</option></c:forEach></select></td><td><input type='text' name='listProperty[0].remarks' maxlength='50' /></td></tr>");
		
		$('.addrow-prop-details').parent('.btnPosition').parent('.row').next('.row.contactList').append("<div class='row propertyCheckbox'><input type='checkbox' style='display:none' checked='checked' value='active' name='listProperty[0].propertyId' /></div>	<div><div class='col-lg-4 col-md-3 col-sm-6 col-xs-12'><label>Property Type</label> <span class='sel-ttip'> <select name='listProperty[0].propTypeId'> <option value='-1'>select</option> <c:forEach var='prop' items='${customerMasterDetail.propType }'> <option value='${prop.propTypeId }' ${prop.propTypeId==property.propTypeId?'selected':'' }> ${prop.propTypeName}</option></c:forEach></select></span> <span class='select-tooltip'	style='margin-top: -15px; background: #fff; border: solid 1px #ccc'></span>	</div> <div class='col-lg-4 col-md-3 col-sm-6 col-xs-12'> <label >Property Status</label> <select	name='listProperty[0].propStatus'>	<option value='-1'>select</option>	<c:forEach var='status1' items='${customerMasterDetail.propStatus }'>	<option value='${status1.propStatusId}' ${status1.propStatusId==property.propStatus?'selected':'' }>${status1.propStatusName}</option>	</c:forEach></select></div>	<div class='col-lg-4 col-md-3 col-sm-6 col-xs-12'>	<label class='LabelHover'>Developer...<span style='display:none;''>Developer Name</span></label>  <select id='listProperty[0].devloperId' name='listProperty[0].devloperId'	onchange='populateProjectName(this);'>	<option value='-1'>select</option>	<c:forEach var='devloper'	items='${customerMasterDetail.devloperMasters }'>	<option value='${devloper.devloperId }'	 ${property.devloperId==devloper.devloperId?'selected':'' }>${devloper.devloperName}</option>	</c:forEach>	</select>	</div>		<div class='col-lg-4 col-md-3 col-sm-6 col-xs-12'>	<label>Other Developer Name </label> <span class='sel-ttip'> <input type='text' value='${property.otherDeploperName}'	maxlength='50' name='listProperty[0].otherDeploperName' /></span> <span class='select-tooltip' style='margin-top: -15px; background: #fff; border: solid 1px #ccc; z-index: 10000;'></span>	</div>	<div class='col-lg-4 col-md-3 col-sm-6 col-xs-12'>	<label>Project Name</label> <select  name='listProperty[0].projectId'>	<option value='${property.projectId}' selected>${property.projectName}</option>	</select></div>	<div class='col-lg-4 col-md-3 col-sm-6 col-xs-12'>	<label>Other Project Name</label> <input type='text' value='${property.otherProjectName}' maxlength='100'	name='listProperty[0].otherProjectName'/></div><div class='col-lg-4 col-md-3 col-sm-6 col-xs-12'><label>Estimated Value</label> <input type='text' class='rgtAlign' value='${property.estimatedValue }' maxlength='13' name='listProperty[0].estimatedValue' onkeypress='return onlyNumeric(event);' onchange='testNumeric(this,'Estimated value')'/>	</div>	<div class='col-lg-4 col-md-3 col-sm-6 col-xs-12'>	<label>Property Add</label> <input type='text' value='${property.address}'	maxlength='200'	name='listProperty[0].address' /></div>	<div class='col-lg-4 col-md-3 col-sm-6 col-xs-12'>	<label>Landmark </label> <input type='text' value='${property.landMark }' name='listProperty[0].landMark'	maxlength='50' /></div>	<div class='col-lg-4 col-md-3 col-sm-6 col-xs-12'>	<label>State</label> <select name='listProperty[0].state' id='listProperty[0].state'	onchange='getCitiesByState(this);'>	<option value='-1'>select</option><c:forEach var='state1' items='${customerMasterDetail.stateList}'><option value='${state1.stateMasterId }' ${state1.stateMasterId==property.state?'selected':'' }>${state1.displayName}</option>	</c:forEach></select></div>	<div class='col-lg-4 col-md-3 col-sm-6 col-xs-12'>	<label>City</label> <select name='listProperty[0].city'><option value='-1'>select</option><c:forEach var='city' items='${customerMasterDetail.city}'><option value='${city.cityMasterId }' ${city.cityMasterId==property.city?'selected':'' }>${city.displayName}</option>	</c:forEach></select></div>	<div class='col-lg-4 col-md-3 col-sm-6 col-xs-12'><label>Pin Code</label> <input type='text' class='rgtAlign' value='${property.zipcode }' name='listProperty[0].zipcode'	onchange='validatePinCode(this);' onkeypress='return onlyNumeric(event);' maxlength='6'	onchange='validatePinCode(this);' /></div>	<div class='col-lg-4 col-md-3 col-sm-6 col-xs-12'>	<label class='LabelHover'>Occupacy ...<span style='display:none;'><spring:message code='label.occupation' /></span></label>  <select		name='listProperty[0].occupancyStatus'>	<option value='-1'>select</option>	<c:forEach var='status1' items='${OccupancyStatusForProperty.occupancyStatus }'><option value='${status1.occupancyStId }'		 ${status1.occupancyStId==property.occupancyStatus?'selected':'' }>${status1.occupancyStName}</option>	</c:forEach>	</select>	</div>	<div class='col-lg-4 col-md-3 col-sm-6 col-xs-12'>	<label>Remarks</label> <input type='text' value='${property.remarks}' name='listProperty[0].remarks' maxlength='50' /></div></div></div>");
		
		//$('.addrow-prop-details').parent('.btnPosition').parent('.row').next('.row.contactList').append("<div class='row propertyCheckbox'><input disabled='disabled' type='checkbox' style='display:none' checked='checked' value='active' name='listProperty[0].propertyId' /></div>	<div class='col-lg-4 col-md-3 col-sm-6 col-xs-12'><label>Property Type</label> <span class='sel-ttip'> <select disabled='disabled' name='listProperty[0].propTypeId'> <option value='-1'>select</option> <c:forEach var='prop' items='${customerMasterDetail.propType }'> <option value='${prop.propTypeId }' ${prop.propTypeId==property.propTypeId?'selected':'' }> ${prop.propTypeName}</option></c:forEach></select></span> <span class='select-tooltip'	style='margin-top: -15px; background: #fff; border: solid 1px #ccc'></span>	</div> <div class='col-lg-4 col-md-3 col-sm-6 col-xs-12'> <label >Property Status</label> <select	disabled='disabled' name='listProperty[0].propStatus'>	<option value='-1'>select</option>	<c:forEach var='status1' items='${customerMasterDetail.propStatus }'>	<option value='${status1.propStatusId}' ${status1.propStatusId==property.propStatus?'selected':'' }>${status1.propStatusName}</option>	</c:forEach></select></div>	<div class='col-lg-4 col-md-3 col-sm-6 col-xs-12'>		<label>Developer Name</label> <select disabled='disabled' id='listProperty[0].devloperId' name='listProperty[0].devloperId'	onchange='populateProjectName(this);'>	<option value='-1'>select</option>	<c:forEach var='devloper'	items='${customerMasterDetail.devloperMasters }'>	<option value='${devloper.devloperId }'	 ${property.devloperId==devloper.devloperId?'selected':'' }>${devloper.devloperName}</option>	</c:forEach>	</select>	</div>		<div class='col-lg-4 col-md-3 col-sm-6 col-xs-12'>	<label>Other Developer Name </label> <span class='sel-ttip'> <input disabled='disabled' type='text' value='${property.otherDeploperName}'	maxlength='50' name='listProperty[0].otherDeploperName' /></span> <span class='select-tooltip' style='margin-top: -15px; background: #fff; border: solid 1px #ccc; z-index: 10000;'></span>	</div>	<div class='col-lg-4 col-md-3 col-sm-6 col-xs-12'>	<label>Project Name</label> <select disabled='disabled' name='listProperty[0].projectId'>	<option value='${property.projectId}' selected>${property.projectName}</option>	</select></div>	<div class='col-lg-4 col-md-3 col-sm-6 col-xs-12'>	<label>Other Project Name</label> <input disabled='disabled' type='text' value='${property.otherProjectName}' maxlength='100'	name='listProperty[0].otherProjectName'/></div><div class='col-lg-4 col-md-3 col-sm-6 col-xs-12'><label>Estimated Value</label> <input disabled='disabled' type='text' class='rgtAlign' value='${property.estimatedValue }' maxlength='13' name='listProperty[0].estimatedValue' onkeypress='return onlyNumeric(event);' onchange='testNumeric(this,'Estimated value')'/>	</div>	<div class='col-lg-4 col-md-3 col-sm-6 col-xs-12'>	<label>Property Address</label> <input disabled='disabled' type='text' value='${property.address}'	maxlength='200'	name='listProperty[0].address' /></div>	<div class='col-lg-4 col-md-3 col-sm-6 col-xs-12'>	<label>Landmark </label> <input disabled='disabled' type='text' value='${property.landMark }' name='listProperty[0].landMark'	maxlength='50' /></div>	<div class='col-lg-4 col-md-3 col-sm-6 col-xs-12'>	<label>State</label> <select disabled='disabled' name='listProperty[0].state' id='listProperty[0].state'	onchange='getCitiesByState(this);'>	<option value='-1'>select</option><c:forEach var='state1' items='${customerMasterDetail.stateList}'><option value='${state1.stateMasterId }' ${state1.stateMasterId==property.state?'selected':'' }>${state1.displayName}</option>	</c:forEach></select></div>	<div class='col-lg-4 col-md-3 col-sm-6 col-xs-12'>	<label>City</label> <select disabled='disabled' name='listProperty[0].city'><option value='-1'>select</option><c:forEach var='city' items='${customerMasterDetail.city}'><option value='${city.cityMasterId }' ${city.cityMasterId==property.city?'selected':'' }>${city.displayName}</option>	</c:forEach></select></div>	<div class='col-lg-4 col-md-3 col-sm-6 col-xs-12'><label>Pin Code</label> <input disabled='disabled' type='text' class='rgtAlign' value='${property.zipcode }' name='listProperty[0].zipcode'	onchange='validatePinCode(this);' onkeypress='return onlyNumeric(event);' maxlength='6'	onchange='validatePinCode(this);' /></div>	<div class='col-lg-4 col-md-3 col-sm-6 col-xs-12'>	<label>Occupancy Status</label> <select	disabled='disabled'	name='listProperty[0].occupancyStatus'>	<option value='-1'>select</option>	<c:forEach var='status1' items='${OccupancyStatusForProperty.occupancyStatus }'><option value='${status1.occupancyStId }'		 ${status1.occupancyStId==property.occupancyStatus?'selected':'' }>${status1.occupancyStName}</option>	</c:forEach>	</select>	</div>	<div class='col-lg-4 col-md-3 col-sm-6 col-xs-12'>	<label>Remarks</label> <input disabled='disabled' type='text' value='${property.remarks}' name='listProperty[0].remarks' maxlength='50' /></div></div>");
   		<%-- $('.addrow-prop-details').parent('.btnPosition').parent('.row').next('.row.contactList').append("<div><input type='checkbox' value='${property.propertyId }' name='listProperty[0].propertyId' /></div> <div class='col-lg-3 col-md-3 col-sm-6 col-xs-12'><label>Property Type</label> <span class='sel-ttip'> <select name='listProperty[0].propTypeId'> <option value='-1'>select</option> <c:forEach var='prop' items='${customerMasterDetail.propType }'> <option value='${prop.propTypeId }' ${prop.propTypeId==property.propTypeId?'selected':'' }> ${prop.propTypeName }</option></c:forEach></select></span><span class='select-tooltip'    style='margin-top: -15px; background: #fff; border: solid 1px #ccc'></span> </div> <div class='col-lg-3 col-md-3 col-sm-6 col-xs-12'> <label >Property Status</label> <select   name='listProperty[0].propStatus'>  <option value='-1'>select</option>  <c:forEach var='status1' items='${customerMasterDetail.propStatus }'>   <option value='${status1.propStatusId}' ${status1.propStatusId==property.propStatus?'selected':'' }>${status1.propStatusName}</option>  </c:forEach></select></div> <div class='col-lg-3 col-md-3 col-sm-6 col-xs-12'>      <label>Developer Name</label> <select id='listProperty[0].devloperId' name='listProperty[0].devloperId' onchange='populateProjectName(this);'> <option value='-1'>select</option> <c:forEach var='devloper' items='${customerMasterDetail.devloperMasters }'> <option value='${devloper.devloperId }' ${property.devloperId==devloper.devloperId?'selected':'' }>${devloper.devloperName}</option> </c:forEach> </select> </div> <div class='clearfix'></div> <div class='col-lg-3 col-md-3 col-sm-6 col-xs-12'> <label>Other Developer Name </label> <span class='sel-ttip'> <input type='text' value='${property.otherDeploperName}' maxlength='50' name='listProperty[0].otherDeploperName' /> </span> <span class='select-tooltip' style='margin-top: -15px; background: #fff; border: solid 1px #ccc; z-index: 10000;'></span> </div> <div class='col-lg-3 col-md-3 col-sm-6 col-xs-12'> <label>Project Name</label> <select name='listProperty[${propStatus.index}].projectId'> <option value='${property.projectId}' selected>${property.projectName}</option> </select> </div> <div class='col-lg-3 col-md-3 col-sm-6 col-xs-12'> <label>Other Project Name</label> <input type='text' value='${property.otherProjectName }' maxlength='100' name='listProperty[0].otherProjectName' /> </div> <div class='col-lg-3 col-md-3 col-sm-6 col-xs-12'> <label>Estimated Value</label> <input type='text' value='${property.estimatedValue }' maxlength='13' name='listProperty[0].estimatedValue' onkeypress='return onlyNumeric(event);' onchange='testNumeric(this,'Estimated value')' /> </div> <div class='col-lg-3 col-md-3 col-sm-6 col-xs-12'> <label>Property Address</label> <input type='text' value='${property.address }' maxlength='200' name='listProperty[0].address' /> </div> <div class='col-lg-3 col-md-3 col-sm-6 col-xs-12'> <label>Landmark </label> <input type='text' value='${property.landMark }' name='listProperty[0].landMark' maxlength='50' /> </div> <div class='col-lg-3 col-md-3 col-sm-6 col-xs-12'> <label>State</label> <select name='listProperty[0].state' id='listProperty[${propStatus.index}].state' onchange='getCitiesByState(this);'> <option value='-1'>select</option> <c:forEach var='state1' items='${customerMasterDetail.stateList}'> <option value='${state1.stateMasterId }' ${state1.stateMasterId==property.state?'selected':'' }>${state1.displayName }</option> </c:forEach> </select> </div> <div class='col-lg-3 col-md-3 col-sm-6 col-xs-12'> <label>City</label> <select name='listProperty[0].city'> <option value='-1'>select</option> <c:forEach var='city' items='${customerMasterDetail.city}'> <option value='${property.city }'>${property.cityName}</option> </c:forEach> </select> </div> <div class='clearfix'></div> <div class='col-lg-3 col-md-3 col-sm-6 col-xs-12'> <label>Pin Code</label> <input type='text' value='${property.zipcode }' name='listProperty[0].zipcode' onchange='validatePinCode(this);' onkeypress='return onlyNumeric(event);' maxlength='6' onchange='validatePinCode(this);' /> </div> <div class='col-lg-3 col-md-3 col-sm-6 col-xs-12'> <label>Occupancy Status</label> <select name='listProperty[0].occupancyStatus'> <option value='-1'>select</option> <c:forEach var='status1' items='${OccupancyStatusForProperty.occupancyStatus }'> <option value='${status1.occupancyStId }' ${status1.occupancyStId==property.occupancyStatus?'selected':'' }>${status1.occupancyStName }</option> </c:forEach> </select> </div> <div class='col-lg-3 col-md-3 col-sm-6 col-xs-12'> <label>Remarks</label> <input type='text' value='${property.remarks }' name='listProperty[0].remarks' maxlength='50' /> </div> </c:forEach> </div>"); --%>
		$('.addrow-prop-details').val(1);
	}
	if("${fn:length(customerDetail.keyContacts)}"==0){		
		$('.addrow-keycontacts').parent('.btnPosition').parent().next('.row').find('tbody').append('<tr><td width="1%"><input disabled="disabled" type="checkbox" value="Active" name="keyContacts[0].keyContactId" checked/></td><td><select disabled="disabled" name="keyContacts[0].contactTypeId"><option value="-1" style="width: 130px;">SELECT</option><c:forEach var="contact" items="${customerMasterDetail.contact }"><option value="${contact.contactTypeId }">${contact.contactType}</option></c:forEach></select></td><td><input disabled="disabled" type="text" value="" id="fullName" onkeypress="return onlyCarector(event);" maxlength="50" onchange="splitUpdatedFullName(this,0)" style="width: 130px;"/> <input disabled="disabled"  type="hidden" value="" name="keyContacts[0].fname" id="keyContacts.fname[0]" /> <input disabled="disabled" type="hidden" value="" name="keyContacts[0].mname" id="keyContacts.mname[0]" style="width: 130px;"/> <input type="hidden" value="" name="keyContacts[0].lname" id="keyContacts.lname[0]" style="width: 130px;" /></td> <td><input disabled="disabled" type="text" value="" name="keyContacts[0].firmName" maxlength="50" /></td><td><input disabled="disabled" type="text" value="" name="keyContacts[0].mobile" class="rgtAlign" maxlength="10" onkeypress="return onlyNumeric(event);" style="width: 130px;"/></td> <td><input disabled="disabled" type="text" value="" name="keyContacts[0].email" onchange="validateEmail(this);" maxlength="100" style="width: 130px;" /></td><td><input disabled="disabled" value="" type="text" name="keyContacts[0].address" maxlength="200" /></td></tr>');
		$('.addrow-keycontacts').val(1);
	}
	if("${fn:length(customerDetail.listAddress)}"==0){
		$('.addrow-detail').parent('.btnPosition').parent('.row').next('.row.contactList').append('<div class="deletchk"><input type="checkbox" value="Active" placeholder="checkbox" name="listAddress[0].addressId" Checked/></div><div class="addressDeletee mailingAddressDiv jquerymargin"><div class="col-lg-4 col-md-3 col-sm-3 col-xs-12"><label>Address Type</label> <select name="listAddress[0].addressType"><option value="-1">SELECT</option><c:forEach var="addressType" items="${customerMasterDetail.addressType }"><option value="${addressType.addressTypeId }">${addressType.addressTypeDisplayName}</option></c:forEach></select></div><div class="col-lg-4 col-md-3 col-sm-6 col-xs-12 addressField"><label class="minWidth">Address</label><input type="text" value="" name="listAddress[0].address" placeholder="Address"	class="col-lg-4 col-md-3 col-sm-3 col-xs-3 addressinput inputmytop" /></div><div class="col-lg-4 col-md-3 col-sm-3 col-xs-12"><label>Mailing Address </label> <input type="checkbox" placeholder="checkbox" value="Y" name="listAddress[0].mailingAddress" /></div><div class="col-lg-4 col-md-3 col-sm-3 col-xs-12"><label>State </label> <select name="listAddress[0].state" id="listAddress[0].state" onchange="getCitiesByState(this);"><option value="-1">SELECT</option><c:forEach var="state" items="${customerMasterDetail.stateList }"><option value="${state.stateMasterId }">${state.displayName}</option></c:forEach></select></div><div class="col-lg-4 col-md-3 col-sm-3 col-xs-12"><label>City</label> <select name="listAddress[0].city"><option value="-1">SELECT</option><option value="7830">BARGARH SADAR</option><option value="7831">BARIL</option><option value="7832">BARKOTE</option><option value="7897">CHHATRAPUR</option><option value="400">MUMBAI</option><option value="110">NEW DELHI</option><option value="9955">NOIDA</option></select></div><div class="col-lg-4 col-md-3 col-sm-3 col-xs-12"><label>Pin</label> <input type="text" name="listAddress[0].zipcode" onchange="validatePinCode(this);" maxlength="6" onkeypress="return onlyNumeric(event);" /> </div><div class="col-lg-4 col-md-3 col-sm-3 col-xs-12"><label>Landmark </label> <input type="text" placeholder="Landmark" name="listAddress[0].landmark" maxlength="50" /></div><div class="col-lg-4 col-md-3 col-sm-3 col-xs-12"><label>Landline</label> <input type="text" placeholder="Landline" name="listAddress[0].phone1" onkeypress="return onlyNumeric(event);" maxlength="12" /></div><div class="col-lg-4 col-md-3 col-sm-3 col-xs-12"><label>Extension</label> <input type="text"	placeholder="Extension" name="listAddress[0].ext1" maxlength="5" onkeypress="return onlyNumeric(event);" /> </div><div class="col-lg-4 col-md-3 col-sm-3 col-xs-12">	<label>Living Here Since</label> <input type="text"	class="minWidth widthcstm" maxlength="2" onchange="validationForMonth(this)" placeholder="Months" name="listAddress[0].occupancyMm" list="monthList" /> <input type="text" class="minWidth widthcstm" maxlength="4" onchange="validateYear(this)"  placeholder="Years" name="listAddress[0].occupancyYr" onkeypress="return onlyNumeric(event);" list="yearList"  /></div><div class="col-lg-4 col-md-3 col-sm-3 col-xs-12">	<label class="LabelHover">Occupacy...<span style="display:none;">Occupancy Status</span></label> <select name="listAddress[0].occupancyStatus"><option value="-1">SELECT</option><c:forEach var="status" items="${customerMasterDetail.occupancyStatus }"><option value="${status.occupancyStId }" >${status.occupancyStName}</option></c:forEach></select></div></div>');
		//$('.addrow-detail').parent('.btnPosition').parent('.row').next('.row.contactList').append('<div class="addressDeletee mailingAddressDiv jquerymargin"><div class="deletchk"><input type="checkbox" disabled="disabled" value="Active" placeholder="checkbox" name="listAddress[0].addressId" Checked/></div><div class="col-lg-4 col-md-3 col-sm-3 col-xs-12"><label>Address Type</label> <select disabled="disabled" name="listAddress[0].addressType"><option value="-1">SELECT</option><c:forEach var="addressType" items="${customerMasterDetail.addressType }"><option value="${addressType.addressTypeId }">${addressType.addressTypeDisplayName}</option></c:forEach></select></div><div class="col-lg-4 col-md-3 col-sm-6 col-xs-12 addressField"><label class="minWidth">Address</label><input disabled="disabled" type="text" value="" name="listAddress[0].address" placeholder="Address"	class="col-lg-4 col-md-3 col-sm-3 col-xs-3 addressinput inputmytop" /></div><div class="col-lg-4 col-md-3 col-sm-3 col-xs-12"><label>Mailing Address </label> <input disabled="disabled" type="checkbox" placeholder="checkbox" value="Y" name="listAddress[0].mailingAddress" /></div><div class="col-lg-4 col-md-3 col-sm-3 col-xs-12"><label>State </label> <select disabled="disabled" name="listAddress[0].state" id="listAddress[0].state" onchange="getCitiesByState(this);"><option value="-1">SELECT</option><c:forEach var="state" items="${customerMasterDetail.stateList }"><option value="${state.stateMasterId }">${state.displayName}</option></c:forEach></select></div><div class="col-lg-4 col-md-3 col-sm-3 col-xs-12"><label>City</label> <select disabled="disabled" name="listAddress[0].city"><option value="-1">SELECT</option><option value="7830">BARGARH SADAR</option><option value="7831">BARIL</option><option value="7832">BARKOTE</option><option value="7897">CHHATRAPUR</option><option value="400">MUMBAI</option><option value="110">NEW DELHI</option><option value="9955">NOIDA</option></select></div><div class="col-lg-4 col-md-3 col-sm-3 col-xs-12"><label>Pin</label> <input disabled="disabled" type="text" name="listAddress[0].zipcode" onchange="validatePinCode(this);" maxlength="6" onkeypress="return onlyNumeric(event);" /> </div><div class="col-lg-4 col-md-3 col-sm-3 col-xs-12"><label>Landmark </label> <input disabled="disabled"  type="text" placeholder="Landmark" name="listAddress[0].landmark" maxlength="50" /></div><div class="col-lg-4 col-md-3 col-sm-3 col-xs-12"><label>Landline</label> <input disabled="disabled" type="text" placeholder="Landline" name="listAddress[0].phone1" onkeypress="return onlyNumeric(event);" maxlength="12" /></div><div class="col-lg-4 col-md-3 col-sm-3 col-xs-12"><label>Extension</label> <input disabled="disabled" type="text"	placeholder="Extension" name="listAddress[0].ext1" maxlength="5" onkeypress="return onlyNumeric(event);" /> </div><div class="col-lg-4 col-md-3 col-sm-3 col-xs-12">	<label>Living Here Since</label> <input disabled="disabled" type="text"	class="minWidth" maxlength="2" onchange="validationForMonth(this)" placeholder="Months" name="listAddress[0].occupancyMm" list="monthList" /> <input disabled="disabled" type="text" class="minWidth" maxlength="4" onchange="validateYear(this)"  placeholder="Years" name="listAddress[0].occupancyYr" onkeypress="return onlyNumeric(event);" list="yearList"  /></div><div class="col-lg-4 col-md-3 col-sm-3 col-xs-12"><label>Occupancy Status</label> <select disabled="disabled" name="listAddress[0].occupancyStatus"><option value="-1">SELECT</option><c:forEach var="status" items="${customerMasterDetail.occupancyStatus }"><option value="${status.occupancyStId }" >${status.occupancyStName}</option></c:forEach></select></div></div>');		
		$('.addrow-detail').val(1);
	}
	
	
});

function splitUpdatedFullName(obj, val){
	var name=obj.value;
	name = name.replace(/\s+/g, " ");
	var res = name.split(" ");
	if(res.length>=3){
		document.getElementById("keyContacts.fname["+val+"]").value = res[0];
		document.getElementById("keyContacts.mname["+val+"]").value = res[1];
		document.getElementById("keyContacts.lname["+val+"]").value = res[2];
	};
	if(res.length==2){
		document.getElementById("keyContacts.fname["+val+"]").value = res[0];
		document.getElementById("keyContacts.lname["+val+"]").value = res[1];
	};
	if(res.length==1){
		document.getElementById("keyContacts.fname["+val+"]").value = res[0];	
	};
}

function splitNewValues(obj){
	var name=obj.value;
	name = name.replace(/\s+/g, " ");
	var res = name.split(" ");
	if(res.length>=3){
		document.getElementById("keyFname").value = res[0];
		document.getElementById("keyMname").value = res[1];
		document.getElementById("keyLname").value = res[2];
	};
	if(res.length==2){
		document.getElementById("keyFname").value = res[0];
		document.getElementById("keyMname").value = res[1];
	};
	if(res.length==1){
		document.getElementById("keyFname").value = res[0];
	};
}
	

$('.prev-lead-icon').parent('.subheader-btn').hover(function(){
//alert('1');
	$(this).children('.prevlead-tooltip').fadeToggle(150);
});


$('.next-lead-icon').parent('.subheader-btn').hover(function(){
//alert('1');
	$(this).children('.nextlead-tooltip').fadeToggle(150);
});

$('.savee-icon').parent('.subheader-btn').hover(function(){
//alert('1');
	$(this).children('.savee-tooltip').fadeToggle(150);
});

$('.saveandexit-icon').parent('.subheader-btn').hover(function(e){
e.preventDefault();
	$(this).children('.saveandexit-tooltip').fadeToggle(150);
});
//----------Deepak--------
$('.savee-icon').click(function(){
	document.getElementById("requestType").value="save";
	document.getElementById("customerForm").action = "sCustomer.do";
	if(checkValidation()){
	document.getElementById("customerForm").submit();
	}
});
$('.saveandexit-icon').click(function(){
	document.getElementById("requestType").value="save&Exit";
	document.getElementById("customerForm").action = "seCustomer.do";
	if(checkValidation()){
	document.getElementById("customerForm").submit();
	}
});

function checkValidation() {
	var alertMsg = "";
	var status = true;
	
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
	
	if(entityType == "1000000002"){
		 var companyNam=$("#companyName").val();
		if (companyNam === null || companyNam === '' || companyNam === undefined) {
			alertMsg+="COMPANY NAME,";
			status = false;
		}
	}
		
	if(status === false){
		
		alertMsg = alertMsg.substring(0, alertMsg.length - 1);
		alertMsg+=" MUST BE SPECIFIED";
		alert(alertMsg);
		return false;
	}else return true;
}

$('.usermenu-mobile').click(function(e){
	e.preventDefault();
		$('.header-rightpanel').animate({"top":"0"});
	});
	$('.close-header-rightpanel').click(function(e){
		e.preventDefault();
		$('.header-rightpanel').animate({"top":"-260px"});
	
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
	
	
	
$('.addrow-detail').click(function(e){
    e.preventDefault();
    var cnti = parseInt(this.value);
    /* $(this).parent('.btnPosition').parent('.row').next('.row.contactList').append('<div class="deletchk"><input type="checkbox" value="Active" placeholder="checkbox" name="listAddress['+cnti+'].addressId" Checked/></div><div class="col-lg-3 col-md-3 col-sm-3 col-xs-12"><label>Address Type</label> <select name="listAddress['+cnti+'].addressType"><option value="-1">Select</option><c:forEach var="addressType" items="${customerMasterDetail.addressType }"><option value="${addressType.addressTypeId }">${addressType.addressTypeDisplayName}</option></c:forEach></select></div><div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 addressField"><label class="minWidth">Address</label><textarea name="listAddress['+cnti+'].address" maxlength="1000" placeholder="Address"></textarea></div><div class="col-lg-3 col-md-3 col-sm-3 col-xs-12"><label>Mailing Address </label> <input type="checkbox" placeholder="checkbox" value="Y" name="listAddress['+cnti+'].mailingAddress" /></div><div class="clearfix"></div><div class="col-lg-3 col-md-3 col-sm-3 col-xs-12"><label>State </label> <select name="listAddress['+cnti+'].state"><option value="-1">Select</option><c:forEach var="state" items="${customerMasterDetail.stateList }"><option value="${state.stateMasterId }">${state.displayName}</option></c:forEach></select></div><div class="col-lg-3 col-md-3 col-sm-3 col-xs-12"><label>City</label> <select name="listAddress['+cnti+'].city"><option value="-1">Select</option><c:forEach var="city" items="${customerMasterDetail.city }"><option value="${city.cityMasterId }" >${city.displayName	}</option></c:forEach></select></div><div class="col-lg-3 col-md-3 col-sm-3 col-xs-12"><label>Pin</label> <input type="text" name="listAddress['+cnti+'].zipcode" maxlength="6" onkeypress="return onlyNumeric(event);" /> </div><div class="col-lg-3 col-md-3 col-sm-3 col-xs-12"><label>Landmark </label> <input type="text" placeholder="Landmark" name="listAddress['+cnti+'].landmark" maxlength="50" /></div><div class="clearfix"></div><div class="col-lg-3 col-md-3 col-sm-3 col-xs-12"><label>Landline</label> <input type="text" placeholder="Landline" name="listAddress['+cnti+'].phone1" onkeypress="return onlyNumeric(event);" maxlength="12" /></div><div class="col-lg-3 col-md-3 col-sm-3 col-xs-12"><label>Extension</label> <input type="text"	placeholder="Extension" name="listAddress['+cnti+'].ext1" maxlength="5" onkeypress="return onlyNumeric(event);" /> </div><div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">	<label>Living Here Since</label> <input type="text"	class="minWidth" maxlength="2" placeholder="MM" name="listAddress['+cnti+'].occupancyMm" onkeypress="return onlyNumeric(event);" /> <input type="text" class="minWidth" maxlength="4" placeholder="YYYY" name="listAddress['+cnti+'].occupancyYr" onkeypress="return onlyNumeric(event);" /></div><div class="col-lg-3 col-md-3 col-sm-3 col-xs-12"><label>Occupancy Status</label> <select name="listAddress['+cnti+'].occupancyStatus"><option value="-1">select</option><c:forEach var="status" items="${customerMasterDetail.occupancyStatus }"><option value="${status.occupancyStId }" >${status.occupancyStName}</option></c:forEach></select></div>'); */
    //$(this).parent('.btnPosition').parent('.row').next('.row.contactList').append("<div class='mailingAddressDiv'><div class='deletchk'><input type='checkbox' value='Active' placeholder='checkbox' name='listAddress["+cnti+"].addressId' Checked/></div><div class='col-lg-4 col-md-3 col-sm-3 col-xs-12'><label>Address Type</label> <select name='listAddress["+cnti+"].addressType'><option value='-1'>Select</option><c:forEach var='addressType' items='${customerMasterDetail.addressType }'><option value='${addressType.addressTypeId }'>${addressType.addressTypeDisplayName}</option></c:forEach></select></div><div class='col-lg-4 col-md-3 col-sm-6 col-xs-12 addressField'><label class='minWidth'>Address</label><input type='text' name='listAddress["+cnti+"].address' placeholder='Address'/></div><div class='col-lg-4 col-md-3 col-sm-3 col-xs-12'><label>Mailing Address </label> <input type='checkbox' placeholder='checkbox' value='Y' name='listAddress["+cnti+"].mailingAddress' /></div><div class='col-lg-4 col-md-3 col-sm-3 col-xs-12'><label>State </label> <select name='listAddress["+cnti+"].state' id='listAddress["+cnti+"].state' onchange='getCitiesByState(this);'><option value='-1'>Select</option><c:forEach var='state' items='${customerMasterDetail.stateList }'><option value='${state.stateMasterId }'>${state.displayName}</option></c:forEach></select></div><div class='col-lg-4 col-md-3 col-sm-3 col-xs-12'><label>City</label> <select name='listAddress["+cnti+"].city'><option value='-1'>select</option><c:forEach var='city2' items='${customerMasterDetail.city}'><option value='${city2.cityMasterId}'>${city2.displayName}</option></c:forEach></select></div><div class='col-lg-4 col-md-3 col-sm-3 col-xs-12'><label>Pin</label> <input type='text' name='listAddress["+cnti+"].zipcode' onchange='validatePinCode(this);' maxlength='6' onkeypress='return onlyNumeric(event);' /> </div><div class='col-lg-4 col-md-3 col-sm-3 col-xs-12'><label>Landmark </label> <input type='text' placeholder='Landmark' name='listAddress["+cnti+"].landmark' maxlength='50' /></div><div class='col-lg-4 col-md-3 col-sm-3 col-xs-12'><label>Landline</label> <input type='text' placeholder='Landline' name='listAddress["+cnti+"].phone1' onkeypress='return onlyNumeric(event);' maxlength='12' /></div><div class='col-lg-4 col-md-3 col-sm-3 col-xs-12'><label>Extension</label> <input type='text'	placeholder='Extension' name='listAddress["+cnti+"].ext1' maxlength='5' onkeypress='return onlyNumeric(event);' /> </div><div class='col-lg-4 col-md-3 col-sm-3 col-xs-12'>	<label>Living Here Since</label> <input type='text'	class='minWidth' maxlength='2' placeholder='MM' name='listAddress["+cnti+"].occupancyMm' onkeypress='return onlyNumeric(event);' onchange='validationForMonth(this)' /> <input type='text' class='minWidth' onchange='validateYear(this)' maxlength='4' placeholder='YYYY' name='listAddress["+cnti+"].occupancyYr' onkeypress='return onlyNumeric(event);' /></div><div class='col-lg-4 col-md-3 col-sm-3 col-xs-12'><label>Occupancy Status</label> <select name='listAddress["+cnti+"].occupancyStatus'><option value='-1'>select</option><c:forEach var='status' items='${customerMasterDetail.occupancyStatus }'><option value='${status.occupancyStId }' >${status.occupancyStName}</option></c:forEach></select></div></div>");
    //$(this).parent('.btnPosition').parent('.row').next('.row.contactList').append('<div class="addressDeletee mailingAddressDiv jquerymargin"><div class="deletchk"><input type="checkbox" value="Active" placeholder="checkbox" name="listAddress['+cnti+'].addressId" Checked/></div><div class="col-lg-4 col-md-3 col-sm-3 col-xs-12"><label>Address Type</label> <select name="listAddress['+cnti+'].addressType"><option value="-1">SELECT</option><c:forEach var="addressType" items="${customerMasterDetail.addressType }"><option value="${addressType.addressTypeId }">${addressType.addressTypeDisplayName}</option></c:forEach></select></div><div class="col-lg-4 col-md-3 col-sm-6 col-xs-12 addressField"><label class="minWidth">Address</label><a href="" data-toggle="modal" data-target="#myModal" data-backdrop="static" data-keyboard="false" onclick="setData()"><img src="./image/edit_img.png" alt="" class="icon-image"></a><input type="text" value="" name="listAddress['+cnti+'].address" placeholder="Address"	class="col-lg-4 col-md-3 col-sm-3 col-xs-3 addressinput inputmytop" /></div><div class="col-lg-4 col-md-3 col-sm-3 col-xs-12"><label>Mailing Address </label> <input type="checkbox" placeholder="checkbox" value="Y" name="listAddress['+cnti+'].mailingAddress" /></div><div class="col-lg-4 col-md-3 col-sm-3 col-xs-12"><label>State </label> <select name="listAddress['+cnti+'].state" id="listAddress['+cnti+'].state" onchange="getCitiesByState(this);"><option value="-1">SELECT</option><c:forEach var="state" items="${customerMasterDetail.stateList }"><option value="${state.stateMasterId }">${state.displayName}</option></c:forEach></select></div><div class="col-lg-4 col-md-3 col-sm-3 col-xs-12"><label>City</label> <select name="listAddress['+cnti+'].city"><option value="-1">SELECT</option><option value="7830">BARGARH SADAR</option><option value="7831">BARIL</option><option value="7832">BARKOTE</option><option value="7897">CHHATRAPUR</option><option value="400">MUMBAI</option><option value="110">NEW DELHI</option><option value="9955">NOIDA</option></select></div><div class="col-lg-4 col-md-3 col-sm-3 col-xs-12"><label>Pin</label> <input type="text" name="listAddress['+cnti+'].zipcode" onchange="validatePinCode(this);" maxlength="6" onkeypress="return onlyNumeric(event);" /> </div><div class="col-lg-4 col-md-3 col-sm-3 col-xs-12"><label>Landmark </label> <input type="text" placeholder="Landmark" name="listAddress['+cnti+'].landmark" maxlength="50" /></div><div class="col-lg-4 col-md-3 col-sm-3 col-xs-12"><label>Landline</label> <input type="text" placeholder="Landline" name="listAddress['+cnti+'].phone1" onkeypress="return onlyNumeric(event);" maxlength="12" /></div><div class="col-lg-4 col-md-3 col-sm-3 col-xs-12"><label>Extension</label> <input type="text"	placeholder="Extension" name="listAddress['+cnti+'].ext1" maxlength="5" onkeypress="return onlyNumeric(event);" /> </div><div class="col-lg-4 col-md-3 col-sm-3 col-xs-12">	<label>Living Here Since</label> <input type="text"	class="minWidth" maxlength="2" onchange="validationForMonth(this)" placeholder="Months" name="listAddress['+cnti+'].occupancyMm" list="monthList" /> <input type="text" class="minWidth" maxlength="4" onchange="validateYear(this)"  placeholder="Years" name="listAddress['+cnti+'].occupancyYr" onkeypress="return onlyNumeric(event);" list="yearList"  /></div><div class="col-lg-4 col-md-3 col-sm-3 col-xs-12"><label>Occupancy Status</label> <select name="listAddress['+cnti+'].occupancyStatus"><option value="-1">SELECT</option><c:forEach var="status" items="${customerMasterDetail.occupancyStatus }"><option value="${status.occupancyStId }" >${status.occupancyStName}</option></c:forEach></select></div></div>');
   
    //new Lead
    //$(this).parent('.btnPosition').parent('.row').next('.row.contactList').append('<div class="addressDeletee mailingAddressDiv jquerymargin"><div class="deletchk"><input type="checkbox" value="Active" placeholder="checkbox" name="listAddress['+cnti+'].addressId" Checked/></div><div class="col-lg-4 col-md-3 col-sm-3 col-xs-12"><label>Address Type</label> <select name="listAddress['+cnti+'].addressType"><option value="-1">SELECT</option><c:forEach var="addressType" items="${customerMasterDetail.addressType }"><option value="${addressType.addressTypeId }">${addressType.addressTypeDisplayName}</option></c:forEach></select></div><div class="col-lg-4 col-md-3 col-sm-6 col-xs-12 addressField"><label class="minWidth">Address</label><input type="text" value="" name="listAddress['+cnti+'].address" placeholder="Address"	class="col-lg-4 col-md-3 col-sm-3 col-xs-3 addressinput inputmytop" /></div><div class="col-lg-4 col-md-3 col-sm-3 col-xs-12"><label>Mailing Address </label> <input type="checkbox" placeholder="checkbox" value="Y" name="listAddress['+cnti+'].mailingAddress" /></div><div class="col-lg-4 col-md-3 col-sm-3 col-xs-12"><label>State </label> <select name="listAddress['+cnti+'].state" id="listAddress['+cnti+'].state" onchange="getCitiesByState(this);"><option value="-1">SELECT</option><c:forEach var="state" items="${customerMasterDetail.stateList }"><option value="${state.stateMasterId }">${state.displayName}</option></c:forEach></select></div><div class="col-lg-4 col-md-3 col-sm-3 col-xs-12"><label>City</label> <select name="listAddress['+cnti+'].city"><option value="-1">SELECT</option><option value="7830">BARGARH SADAR</option><option value="7831">BARIL</option><option value="7832">BARKOTE</option><option value="7897">CHHATRAPUR</option><option value="400">MUMBAI</option><option value="110">NEW DELHI</option><option value="9955">NOIDA</option></select></div><div class="col-lg-4 col-md-3 col-sm-3 col-xs-12"><label>Pin</label> <input type="text" name="listAddress['+cnti+'].zipcode" onchange="validatePinCode(this);" maxlength="6" onkeypress="return onlyNumeric(event);" /> </div><div class="col-lg-4 col-md-3 col-sm-3 col-xs-12"><label>Landmark </label> <input type="text" placeholder="Landmark" name="listAddress['+cnti+'].landmark" maxlength="50" /></div><div class="col-lg-4 col-md-3 col-sm-3 col-xs-12"><label>Landline</label> <input type="text" placeholder="Landline" name="listAddress['+cnti+'].phone1" onkeypress="return onlyNumeric(event);" maxlength="12" /></div><div class="col-lg-4 col-md-3 col-sm-3 col-xs-12"><label>Extension</label> <input type="text"	placeholder="Extension" name="listAddress['+cnti+'].ext1" maxlength="5" onkeypress="return onlyNumeric(event);" /> </div><div class="col-lg-4 col-md-3 col-sm-3 col-xs-12">	<label>Living Here Since</label> <input type="text"	class="minWidth" maxlength="2" onchange="validationForMonth(this)" placeholder="Months" name="listAddress['+cnti+'].occupancyMm" list="monthList" /> <input type="text" class="minWidth" maxlength="4" onchange="validateYear(this)"  placeholder="Years" name="listAddress['+cnti+'].occupancyYr" onkeypress="return onlyNumeric(event);" list="yearList"  /></div><div class="col-lg-4 col-md-3 col-sm-3 col-xs-12"><label>Occupancy Status</label> <select name="listAddress['+cnti+'].occupancyStatus"><option value="-1">SELECT</option><c:forEach var="status" items="${customerMasterDetail.occupancyStatus }"><option value="${status.occupancyStId }" >${status.occupancyStName}</option></c:forEach></select></div></div>');
    
    $(this).parent('.btnPosition').parent('.row').next('.row.contactList').append('<div class="deletchk"><input type="checkbox" value="Active" placeholder="checkbox" name="listAddress['+cnti+'].addressId" Checked/></div><div class="addressDeletee mailingAddressDiv jquerymargin"><div class="col-lg-4 col-md-3 col-sm-3 col-xs-12"><label>Address Type</label> <select name="listAddress['+cnti+'].addressType"><option value="-1">SELECT</option><c:forEach var="addressType" items="${customerMasterDetail.addressType }"><option value="${addressType.addressTypeId }">${addressType.addressTypeDisplayName}</option></c:forEach></select></div><div class="col-lg-4 col-md-3 col-sm-6 col-xs-12 addressField"><label class="minWidth">Address</label><input type="text" value="" name="listAddress['+cnti+'].address" placeholder="Address"	class="col-lg-4 col-md-3 col-sm-3 col-xs-3 addressinput inputmytop" /></div><div class="col-lg-4 col-md-3 col-sm-3 col-xs-12"><label>Mailing Address </label> <input type="checkbox" placeholder="checkbox" value="Y" name="listAddress['+cnti+'].mailingAddress" /></div><div class="col-lg-4 col-md-3 col-sm-3 col-xs-12"><label>State </label> <select name="listAddress['+cnti+'].state" id="listAddress['+cnti+'].state" onchange="getCitiesByState(this);"><option value="-1">SELECT</option><c:forEach var="state" items="${customerMasterDetail.stateList }"><option value="${state.stateMasterId }">${state.displayName}</option></c:forEach></select></div><div class="col-lg-4 col-md-3 col-sm-3 col-xs-12"><label>City</label> <select name="listAddress['+cnti+'].city"><option value="-1">SELECT</option><option value="7830">BARGARH SADAR</option><option value="7831">BARIL</option><option value="7832">BARKOTE</option><option value="7897">CHHATRAPUR</option><option value="400">MUMBAI</option><option value="110">NEW DELHI</option><option value="9955">NOIDA</option></select></div><div class="col-lg-4 col-md-3 col-sm-3 col-xs-12"><label>Pin</label> <input type="text" name="listAddress['+cnti+'].zipcode" onchange="validatePinCode(this);" maxlength="6" onkeypress="return onlyNumeric(event);" /> </div><div class="col-lg-4 col-md-3 col-sm-3 col-xs-12"><label>Landmark </label> <input type="text" placeholder="Landmark" name="listAddress['+cnti+'].landmark" maxlength="50" /></div><div class="col-lg-4 col-md-3 col-sm-3 col-xs-12"><label>Landline</label> <input type="text" placeholder="Landline" name="listAddress['+cnti+'].phone1" onkeypress="return onlyNumeric(event);" maxlength="12" /></div><div class="col-lg-4 col-md-3 col-sm-3 col-xs-12"><label>Extension</label> <input type="text"	placeholder="Extension" name="listAddress['+cnti+'].ext1" maxlength="5" onkeypress="return onlyNumeric(event);" /> </div><div class="col-lg-4 col-md-3 col-sm-3 col-xs-12">	<label>Living Here Since</label> <input type="text"	class="widthcstm minWidth" maxlength="2" onchange="validationForMonth(this)" placeholder="Months" name="listAddress['+cnti+'].occupancyMm" list="monthList" /> <input type="text" class="minWidth widthcstm" maxlength="4" onchange="validateYear(this)"  placeholder="Years" name="listAddress['+cnti+'].occupancyYr" onkeypress="return onlyNumeric(event);" list="yearList"  /></div><div class="col-lg-4 col-md-3 col-sm-3 col-xs-12"><label>Occupancy Status</label> <select name="listAddress['+cnti+'].occupancyStatus"><option value="-1">SELECT</option><c:forEach var="status" items="${customerMasterDetail.occupancyStatus }"><option value="${status.occupancyStId }" >${status.occupancyStName}</option></c:forEach></select></div></div>');
    
    //$(this).parent('.btnPosition').parent('.row').next('.row.contactList').append('<div class="addressDeletee mailingAddressDiv jquerymargin"><div class="deletchk"><input type="checkbox" value="Active" placeholder="checkbox" name="listAddress['+cnti+'].addressId" Checked/></div><div class="col-lg-4 col-md-3 col-sm-3 col-xs-12"><label>Address Type</label> <select name="listAddress['+cnti+'].addressType"><option value="-1">SELECT</option><c:forEach var="addressType" items="${customerMasterDetail.addressType }"><option value="${addressType.addressTypeId }">${addressType.addressTypeDisplayName}</option></c:forEach></select></div><div class="col-lg-4 col-md-3 col-sm-6 col-xs-12 addressField"><label class="minWidth">Address</label><input type="text" value="" name="listAddress['+cnti+'].address" placeholder="Address"	class="col-lg-4 col-md-3 col-sm-3 col-xs-3 addressinput inputmytop" /></div><div class="col-lg-4 col-md-3 col-sm-3 col-xs-12"><label>Mailing Address </label> <input type="checkbox" placeholder="checkbox" value="Y" name="listAddress['+cnti+'].mailingAddress" /></div><div class="col-lg-4 col-md-3 col-sm-3 col-xs-12"><label>State </label> <select name="listAddress['+cnti+'].state" id="listAddress['+cnti+'].state" onchange="getCitiesByState(this);"><option value="-1">SELECT</option><c:forEach var="state" items="${customerMasterDetail.stateList }"><option value="${state.stateMasterId }">${state.displayName}</option></c:forEach></select></div><div class="col-lg-4 col-md-3 col-sm-3 col-xs-12"><label>City</label> <select name="listAddress['+cnti+'].city"><option value="-1">SELECT</option><option value="7830">BARGARH SADAR</option><option value="7831">BARIL</option><option value="7832">BARKOTE</option><option value="7897">CHHATRAPUR</option><option value="400">MUMBAI</option><option value="110">NEW DELHI</option><option value="9955">NOIDA</option></select></div><div class="col-lg-4 col-md-3 col-sm-3 col-xs-12"><label>Pin</label> <input type="text" name="listAddress['+cnti+'].zipcode" onchange="validatePinCode(this);" maxlength="6" onkeypress="return onlyNumeric(event);" /> </div><div class="col-lg-4 col-md-3 col-sm-3 col-xs-12"><label>Landmark </label> <input type="text" placeholder="Landmark" name="listAddress['+cnti+'].landmark" maxlength="50" /></div><div class="col-lg-4 col-md-3 col-sm-3 col-xs-12"><label>Landline</label> <input type="text" placeholder="Landline" name="listAddress['+cnti+'].phone1" onkeypress="return onlyNumeric(event);" maxlength="12" /></div><div class="col-lg-4 col-md-3 col-sm-3 col-xs-12"><label>Extension</label> <input type="text"	placeholder="Extension" name="listAddress['+cnti+'].ext1" maxlength="5" onkeypress="return onlyNumeric(event);" /> </div><div class="col-lg-4 col-md-3 col-sm-3 col-xs-12">	<label>Living Here Since</label> <input type="text"	class="minWidth" maxlength="2" onchange="validationForMonth(this)" placeholder="Months" name="listAddress['+cnti+'].occupancyMm" list="monthList" /> <input type="text" class="minWidth" maxlength="4" onchange="validateYear(this)"  placeholder="Years" name="listAddress['+cnti+'].occupancyYr" onkeypress="return onlyNumeric(event);" list="yearList"  /></div><div class="col-lg-4 col-md-3 col-sm-3 col-xs-12"><label>Occupancy Status</label> <select name="listAddress['+cnti+'].occupancyStatus"><option value="-1">SELECT</option><c:forEach var="status" items="${customerMasterDetail.occupancyStatus }"><option value="${status.occupancyStId }" >${status.occupancyStName}</option></c:forEach></select></div></div>');
    
  	this.value=cnti+1;
  	setTimeout(function(){
$('.mailingAddressDiv .col-md-3:nth-child(2)').attr('id', 'second');
$('.mailingAddressDiv .col-md-3:nth-child(3)').attr('id', 'third');
$('.mailingAddressDiv .col-md-3:nth-child(4)').attr('id', 'forth');
$(".col-lg-4.col-md-3.col-sm-3.col-xs-3.padding-left-img.padding-left-margin").removeAttr("id");
}, 50);
  });
  
$('.delrow-detail').click(function(e) {
  e.preventDefault();
  //fire when the button is clicked
        $('.row.contactAddres input:checkbox').each(function() {
          var checkbox = $(this); 
         // alert(checkbox);
         if(checkbox.is(':checked')) 
         {$(this).parent().parent('.row.contactList').remove();};
         });
    });
    
$('.newrow-contacts')
.click(
		function(e) {
			e.preventDefault();
			var cnti = parseInt(this.value);
			$(this)
					.parent()
					.parent()
					.append(
							'<div class="row contactAddres mobile"><div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"><input type="checkbox" checked placeholder="Address" id="completRow" value="Active" name="listMobile['+cnti+'].caseMobileId"/><label>Type</label> <select id="mobileType" name="listMobile['+cnti+'].mobileContactTypeId" ><option value="-1" >Select</option><c:forEach var="contact3"	items="${customerMasterDetail.contactTypeMobile }"><option value="${contact3.contactTypeId}" }>${contact3.contactTypeName}</option></c:forEach></select> <label>Mobile No.<span> </span></label><input type="text" name="listMobile['+cnti+'].contactNo" maxlength="10" onchange="validateMobile(this);" onkeypress="return onlyNumeric(event);" style="margin-left: 3px;"/> <input type="button" value="N" /><input type="hidden" name="listMobile['+cnti+'].primaryContact" value="N"/></div></div>');
			this.value = cnti + 1;

			//document.getElementById("mobileDel").value = parseInt(document.getElementById("mobileDel").value)+1;
			//alert(document.getElementById("mobileDel").value);
		});
    
$('.newrow-email')
.click(
		function(e) {
			e.preventDefault();
			var cnti = parseInt(this.value);
			$("#rowContactEmail")
					.append('<div class="row contactAddres email"><div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"><input type="checkbox" checked value="Active" placeholder="Address" id="emailRow" name="listEmail['+cnti+'].caseEmailId"/> <label>Type</label><select class="emailsel" style="margin-left:5.5%;" name="listEmail['+cnti+'].emailContactTypeId"><option value="-1" >select</option><c:forEach var="contact4" items="${customerMasterDetail.contactTypeEmail }"><option value="${contact4.contactTypeId }">${contact4.contactTypeName}</option></c:forEach></select> <label>Email Id</label> <input type="text" value="" name="listEmail['+cnti+'].email" onchange="validateEmail(this);" class="addinput-left" style="margin-left:5%;" maxlength="100"/><input value="N" type="button" /><input type="hidden" name="listEmail['+cnti+'].primaryEmail" value="N"/></div></div>');
					//.append('<div class="row contactAddres email"><div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"><input type="checkbox" checked value="Active" placeholder="Address" id="emailRow" name="listEmail['+cnti+'].caseEmailId"/> <label>Type</label><select class="emailsel" name="listEmail['+cnti+'].emailContactTypeId"><option value="-1" >select</option><c:forEach var="contact4" items="${customerMasterDetail.contactTypeEmail }"><option value="${contact4.contactTypeId }">${contact4.contactTypeName}</option></c:forEach></select> <label>Email Id</label> <input type="text" value="" name="listEmail['+cnti+'].email" onchange="validateEmail(this);" class="addinput-left" maxlength="100"/><input value="N" type="button" /><input type="hidden" name="listEmail['+cnti+'].primaryEmail" value="N"/></div></div>');
			this.value = cnti + 1;

			//$('.delrow-email').val = parseInt($('.delrow-email').val)+1;

		});
    
	 /* $('.addrow-prop-details').click(function(e){
		e.preventDefault();
		var cnti = parseInt(this.value);
		// $(this).parent('.btnPosition').parent().next('.row').find('tbody').append('<tr><td width="1%"><input type="checkbox" name="listProperty['+cnti+'].propertyId" value="active" checked/></td><td><select name="listProperty['+cnti+'].propTypeId"><option value="-1">select</option><c:forEach var="prop" items="${customerMasterDetail.propType }"><option value="${prop.propTypeId }" >${prop.propTypeName}</option></c:forEach></select></td><td><input type="text" name="listProperty['+cnti+'].developerName" maxlength="50" /></td><td><input type="text"  name="listProperty['+cnti+'].projectName" maxlength="50" /></td><td><input type="text"  name="listProperty['+cnti+'].estimatedValue" maxlength="13" onkeypress="return onlyNumeric(event);" /></td><td><input type="text"  name="listProperty['+cnti+'].address" maxlength="200"/></td><td><input type="text"  name="listProperty['+cnti+'].landMark" maxlength="50"/></td><td><select name="listProperty['+cnti+'].state"><option value="-1">select</option><c:forEach var="state1" items="${customerMasterDetail.stateList}"><option value="${state1.stateMasterId }">${state1.displayName}</option></c:forEach></select></td><td><select name="listProperty['+cnti+'].city"><option value="-1">select</option><c:forEach var="city" items="${customerMasterDetail.city}"><option value="${city.cityMasterId }" >${city.displayName}</option></c:forEach></select></td><td><input type="text"  name="listProperty['+cnti+'].zipcode" onkeypress="return onlyNumeric(event);" maxlength="6" /></td><td><select name="listProperty['+cnti+'].propStatus"><option value="-1">select</option><c:forEach var="status1" items="${customerMasterDetail.propStatus }"><option value="${status1.propStatusId }" >${status1.propStatusName}</option></c:forEach></select></td><td><input type="text" name="listProperty['+cnti+'].remarks" maxlength="50" /></td></tr>'); 
		$(this).parent('.btnPosition').parent().next('.row').find('tbody').append("<tr><td width='1%'><input type='checkbox' name='listProperty["+cnti+"].propertyId' value='active' checked/></td><td><select name='listProperty["+cnti+"].propTypeId'><option value='-1'>select</option><c:forEach var='prop' items='${customerMasterDetail.propType }'><option value='${prop.propTypeId }' >${prop.propTypeName}</option></c:forEach></select></td><td><select id='listProperty["+cnti+"].devloperId' name='listProperty["+cnti+"].devloperId' onchange='populateProjectName(this);'><option value='-1'>select</option><c:forEach var='devloper' items='${customerMasterDetail.devloperMasters }'><option value='${devloper.devloperId }' ${prop.developerId==devloper.devloperId?'selected':'' }>${devloper.devloperName}</option></c:forEach></select></td><td><select  name='listProperty["+cnti+"].projectId'></select></td><td><input type='text' value='${property.otherDeploperName }'	maxlength='50'	name='listProperty["+cnti+"].otherDeploperName' /></td><td><input type='text' value='${property.otherProjectName }' maxlength='100' name='listProperty["+cnti+"].otherProjectName'/></td><td><input type='text'  name='listProperty["+cnti+"].estimatedValue' maxlength='13' onkeypress='return onlyNumeric(event);' onchange=\"testNumeric(this,'Estimated value');\" /></td><td><input type='text'  name='listProperty["+cnti+"].address' maxlength='200'/></td><td><input type='text'  name='listProperty["+cnti+"].landMark' maxlength='50'/></td><td><select name='listProperty["+cnti+"].state' id='listProperty["+cnti+"].state' onchange='getCitiesByState(this);'><option value='-1'>select</option><c:forEach var='state1' items='${customerMasterDetail.stateList}'><option value='${state1.stateMasterId }'>${state1.displayName}</option></c:forEach></select></td><td><select name='listProperty["+cnti+"].city'><option value='-1'>select</option><c:forEach var='city1' items='${customerMasterDetail.city}'><option value='${city1.cityMasterId}'>${city1.displayName}</option></c:forEach></select></td><td><input type='text'  name='listProperty["+cnti+"].zipcode' onchange='validatePinCode(this);' onkeypress='return onlyNumeric(event);' maxlength='6' /></td><td><select name='listProperty["+cnti+"].propStatus'><option value='-1'>select</option><c:forEach var='status1' items='${customerMasterDetail.propStatus }'><option value='${status1.propStatusId }' >${status1.propStatusName}</option></c:forEach></select></td><td><input type='text' name='listProperty["+cnti+"].remarks' maxlength='50' /></td></tr>");
		this.value=cnti+1;
	
	}); */  

	$('.delrow').click(function(e){
		e.preventDefault();
		$(this).parent('.btnPosition').parent().next('.row').find('tr').each(function(){
				if($(this).children('td:nth-child(1)').children('input[type="checkbox"]').is(':checked')){
			
				$(this).children('td:nth-child(1)').children('input[type="checkbox"]:checked').parent('td').parent('tr').hide();
				}
			});
	});

	$('.addrow-keycontacts').click(function(e){
		e.preventDefault();
		var cnti = parseInt(this.value);
		$(this).parent('.btnPosition').parent().next('.row').find('tbody').append('<tr><td width="1%"><input type="checkbox" value="Active" name="keyContacts['+cnti+'].keyContactId" checked/></td><td><select name="keyContacts['+cnti+'].contactTypeId"><option value="-1">select</option><c:forEach var="contact" items="${customerMasterDetail.contact }"><option value="${contact.contactTypeId }">${contact.contactType}</option></c:forEach></select></td><td><input type="text" value="" id="fullName" onkeypress="return onlyCarector(event);" maxlength="50" onchange="splitUpdatedFullName(this,'+cnti+')" /> <input type="hidden" value="" name="keyContacts['+cnti+'].fname" id="keyContacts.fname['+cnti+']" /> <input type="hidden" value="" name="keyContacts['+cnti+'].mname" id="keyContacts.mname['+cnti+']" /> <input type="hidden" value="" name="keyContacts['+cnti+'].lname" id="keyContacts.lname['+cnti+']" /></td> <td><input type="text" value="" name="keyContacts['+cnti+'].firmName" maxlength="50" /></td><td><input type="text" value="" name="keyContacts['+cnti+'].mobile" onchange="validateMobile(this);" maxlength="10" onkeypress="return onlyNumeric(event);" /></td> <td><input type="text" value="" name="keyContacts['+cnti+'].email" onchange="validateEmail(this);" maxlength="100" /></td><td><input value="" type="text" name="keyContacts['+cnti+'].address" maxlength="200" /></td></tr>');
		this.value = cnti + 1;
	
	});
	
	function savePersonal(){
		var dob=document.getElementById("datepicker2").value;
		document.getElementById("customerForm").action = "updateCustomerInfo.do";
		document.getElementById("customerForm").submit();
	}
	
	function saveOccupational(){
		document.getElementById("customerForm").action = "updateOccupationInfo.do";
		document.getElementById("customerForm").submit();
	}
	
	function saveAddress(){
		document.getElementById("customerForm").action = "auAddress.do";
		document.getElementById("customerForm").submit();
	}
	function deleteAddress(){
		
			document.getElementById("customerForm").action = "delAddress.do";
			document.getElementById("customerForm").submit();	
			
	}
	function auKeyContact(){

		document.getElementById("customerForm").action = "auKeyContact.do";
		document.getElementById("customerForm").submit();
	}
	function delKeyContact(){
		document.getElementById("customerForm").action = "delKeyContact.do";
		document.getElementById("customerForm").submit();
	}
	function auProperty(){
		document.getElementById("customerForm").action = "auProperty.do";
		document.getElementById("customerForm").submit();
	}
	function delProperty(){
		document.getElementById("customerForm").action = "delProperty.do";
		document.getElementById("customerForm").submit();
	}
	function saveEmail(){
		document.getElementById("requestType").value="email";
		document.getElementById("customerForm").action = "auContact.do";
		document.getElementById("customerForm").submit();
	}
	function saveMobile(){
		document.getElementById("requestType").value="mobile";
		document.getElementById("customerForm").action = "auContact.do";
		document.getElementById("customerForm").submit();
	}
	function deleteMobile(){
		document.getElementById("requestType").value="mobile";
		document.getElementById("customerForm").action = "deleteContact.do";
		document.getElementById("customerForm").submit();
	}
	function deleteEmail(){
		document.getElementById("requestType").value="email";
		document.getElementById("customerForm").action = "deleteContact.do";
		document.getElementById("customerForm").submit();
	}
	
	$(document).ready(function(){
		$(document).on("click", ".row.contactAddres.mobile input[type='button']", function(){
			$(".row.contactAddres.mobile input[type='button']").attr("value","N");
			$(".row.contactAddres.mobile input[type='hidden']").attr("value","N");
			$(".row.contactAddres.mobile input[type='checkbox']").each(function(){
				$(this).prop('checked', true);
			});
		$(this).attr("value","Y");
		$(this).next("input[type='hidden']").attr("value", "Y");
		});
		$(document).on("click", ".row.contactAddres.email input[type='button']", function(){
			$(".row.contactAddres.email input[type='button']").attr("value","N");
			$(".row.contactAddres.email input[type='hidden']").attr("value","N");
			$(".row.contactAddres.email input[type='checkbox']").each(function(){
				$(this).prop('checked', true);
			});
		$(this).attr("value","Y");
		$(this).next("input[type='hidden']").attr("value", "Y");
		});
		$(".topMenu li:nth-child(2) a").addClass("active");
		
		});

	$(function(){		
			
		
			$(document).on("click",".mailingAddressDiv input[type='checkbox']:nth-child(2)", function(){
			
				$(".mailingAddressDiv input[type='checkbox']:nth-child(2)").removeAttr("checked");
				$(this).prop("checked",'checked');
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
	
	
	
	$(".select-tooltip").each(function(){	
		//var selttip = $(this);
		$(this).text($(this).prev("span").find("input[type='text']").val());
		$(this).text($(this).prev("span").find("select").find("option:selected").text());
		$(".sel-ttip").mouseenter(function(){		
		
		$(this).next(".select-tooltip").fadeIn();
	
		});
		$(".sel-ttip").mouseleave(function(){		
		//alert("leave");
		$(this).next(".select-tooltip").fadeOut();
		});

	});
	
	function populateProjectName(val){
		$.ajax({  
			type : "post",   
			url : "getProjectNameList.do",   
			data : "developerId=" + val.value ,
			ontext: document.body,			
			success : function(response) {
					var docTypeId = val.id;					
					var leftNameIndex = docTypeId.indexOf("[");
					var rightNameIndex = docTypeId.indexOf("]");
					var indexnumber = docTypeId.substring(leftNameIndex + 1,rightNameIndex);					
					leftName = docTypeId.substring(0, leftNameIndex);					
					var select = $('[name="' + leftName + '[' + indexnumber	+ '].projectId"]');					
					select.find('option').remove();
					$('<option>').val("-1").text("Select").appendTo(select);
					$($.parseJSON(response)).map(function() {
						$('<option>').val(this.projectId).text(this.projectName).appendTo(select);
					});
			}
		});
		
	}
	//$("#companyName")
	</script>
<script>
$(".down-div").css("display","none");
$(".uparrow-div").css("display","block");
$('#collapse-div').css('height', '32px');
$('.contactList .listOne ').css('min-height', '46px');
$(".topstaticinfo").css("height","100%");
$('ul.listOne li:nth-child(3),ul.listOne li:nth-child(4),ul.listOne li:nth-child(5),ul.listOne li:nth-child(6),ul.listOne li:nth-child(7),ul.listOne li:nth-child(8)').css('display', 'none');
$(".taggs").css("display","none");



function uparrow() {
$(".uparrow-div").css("display","block");
$(".down-div").css("display","none");
/*$('#collapse-div').css('height', '53px');*/
$('.contactList .listOne ').css('min-height', '46px');
$('#collapse-div').animate({height:32},250);
$(".topstaticinfo").css("height","100%");
$('ul.listOne li:nth-child(3),ul.listOne li:nth-child(4),ul.listOne li:nth-child(5),ul.listOne li:nth-child(6),ul.listOne li:nth-child(7),ul.listOne li:nth-child(8)').css('display', 'none');
$(".taggs").css("display","none");
}
			
function down() {
$(".uparrow-div").css("display","none");
$(".down-div").css("display","block");
/*$('#collapse-div').css('height', 'auto');*/
$('#collapse-div').animate({height:'140'},250);

$('ul.listOne li:nth-child(3),ul.listOne li:nth-child(4),ul.listOne li:nth-child(5),ul.listOne li:nth-child(6),ul.listOne li:nth-child(7),ul.listOne li:nth-child(8)').css('display', 'block');
$('.contactList .listOne ').css('height', 'auto');
$(".taggs").css("display","block");
$(".topstaticinfo").css("height","auto");
}
</script>
<script>
$(".topstaticinfo ul>li").each(function(){
		if($(this).text().trim().length>=12) 
			{ 
			var originText1=$(this).text();   
			var cropText1=originText1.substring(0,12); 
			$(this).text(cropText1+"..."); 
			$(this).append("<span class='hover_dashyy' style='display:none;'>"+originText1+"</span>");
			}
		});
</script>
<script>
setTimeout(function(){
$(".row.contactList.leadstatus div label").each(function(){
		if($(this).text().trim().length>=18) 
			{
			$(".row.contactList.leadstatus div .sel-ttip").removeAttr("class"); 
			var originText1=$(this).text();   
			var cropText1=originText1.substring(0,12); 
			$(this).text(cropText1+"..."); 
			$(this).append("<span class='hover_dashyy' style='display:none;'>"+originText1+"</span>");
			}
});
}, 1);


function setData(){
	var data=$('#addressInput').val();
	$('#modalAddress').val(data);	
}
function setData1(){
	var data=$('#modalAddress').val();
	$('#addressInput').val(data);	
}

</script>
<script>
setTimeout(function(){
$('.mailingAddressDiv .col-md-3:nth-child(2)').attr('id', 'second');
$('.mailingAddressDiv .col-md-3:nth-child(3)').attr('id', 'third');
$('.mailingAddressDiv .col-md-3:nth-child(4)').attr('id', 'forth');
$(".col-lg-4.col-md-3.col-sm-3.col-xs-3.padding-left-img.padding-left-margin").removeAttr("id");
}, 50);
</script>
<style>

.tr-padding-zero tbody tr td {
	padding-top: 13px !important;
	padding-bottom: 0px !important;
}

.highlight_only {
	border: 1px solid #C7C7C7 !important;
}

.topstaticinfo .listOne {
	min-height: 28px !important;
	height: 100% !important;
}

.topstaticinfo {
	height: 100%;
}

.top-add-del {
    top: 2px !important;
    right: -8px;
}

</style>
</body>