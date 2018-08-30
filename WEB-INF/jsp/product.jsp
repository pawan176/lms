<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
<script src="resources/js/globalValidation.js" type="text/javascript"></script>
<style type="text/css">
.selectBank {
	background: #fff
}

.selectBank option {
	display: none;
}

.exis-facil tbody select {
	top: 5px;
	font-size: 14px;
	text-transform: uppercase !important;
	margin-top: 1px;
}
</style>
<script type="text/javascript">
$(document).ready(function(){
	//document.getElementById('facilityRequestedReCalEmi').style.borderColor='#C7C7C7';
	//document.getElementById('facilityRequestedLoanAmount').style.borderColor='#C7C7C7';
	$(".topMenu li:nth-child(3) a").addClass("active");
	
	//getSchemeName(document.getElementById('selectfacility'));
	
	//added  by tripti 
	var msg="${msgForConvertToCust}";
	if(msg!=""){
		alert(msg);
	}
	<c:if test="${not empty sessionScope.msgForConvertToCust}">   
	   <c:remove var="msgForConvertToCust" scope="session"/>  
	</c:if> 
});


function validate(){
	//alert("1");
	var branchid=document.getElementById("branch").value;
	//alert("2"+b);
	if(branchid==-1){
		alert("Please select branch");
		return false;
	}
	document.getElementById("branchId1").value = branchid;
	document.getElementById("convertToCust").action = "convertToCustomer.do";
	document.getElementById("convertToCust").submit();
}

function validateLoanAmount(val){
	var currentLoan = parseInt(val.value);
	var schemeValidationMinLoan = parseInt(document.getElementById("schemeValidationMinLoan").value);
	var schemeValidationMaxLoan = parseInt(document.getElementById("schemeValidationMaxLoan").value);
	
	if(!(typeof(currentLoan) == 'undefined') && !(currentLoan === null)){		
		if(currentLoan<schemeValidationMinLoan || currentLoan>schemeValidationMaxLoan){
			alert('Loan Amount should be in the range '+schemeValidationMinLoan+' and '+schemeValidationMaxLoan);
			val.value = "";
		}
	}
}

function validateLoanTenure(val){	
	var currentTenure =  parseInt(val.value);
	var schemeValidationMinTenure = parseInt(document.getElementById("schemeValidationMinTenure").value);
	var schemeValidationMaxTenure = parseInt(document.getElementById("schemeValidationMaxTenure").value);
	
	if(!(typeof(currentTenure) == 'undefined') && !(currentTenure === null)){		
		if(currentTenure<schemeValidationMinTenure || currentTenure>schemeValidationMaxTenure){
			alert('Loan Tenure should be in the range '+schemeValidationMinTenure+' and '+schemeValidationMaxTenure);
			val.value = "";
		}
	}	
}


	</script>
<form id="convertToCust" name="convertToCust" method="post">
	<input type="hidden" id="branchId1" name="branchId1">
</form>
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
					<li><label><spring:message
								code="staticHeader.disposition" /></label></li>
					<li><span>${leadHeaderDetail.disposition}</span></li>
					<li><label><spring:message code="staticHeader.amount" /></label></li>
					<li><span>${leadHeaderDetail.amount}</span></li>
					<li><label><spring:message
								code="staticHeader.leadStage" /></label></li>
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
					<li><label><spring:message
								code="staticHeader.custCity" /></label></li>
					<li><span>${leadHeaderDetail.customerResCity}</span></li>
					<li><label><spring:message
								code="staticHeader.constitution" /></label></li>
					<li><span> ${leadHeaderDetail.occuType}</span></li>
				</ul>
				<div class="ImageIcon">
					<img src="resources/images/image-user.jpg" alt="UserLogoImage" />
				</div>
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
						data-backdrop="static" data-keyboard="false" onclick="setData()"><img
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
						data-backdrop="static" data-keyboard="false" onclick="setData()"><img
						src="./image/edit_img.png" alt="" class="icon-image"></a> <input
						type="text" value="${leadHeaderDetail.tagB}"
						class="col-lg-12 col-md-12 col-sm-12 col-xs-12" />
				</div>
			</div>

		</div>
		<form action="" id="productForm" method="post">
			<!-- <div class="row buttonHeading">
				<div class="col-lg-8 col-md-8 col-sm-6 col-xs-5">
					<h2 class="heading">Facility Requested</h2>
				</div>

				<div class="col-lg-4 col-md-4 col-sm-6 col-xs-7 btnPosition">
					<a class="toggle-list btn-primary"> <span
						class="toggle-icon minus-icon1 "></span>
					</a>
					<button type="button"
						class="btn btn-primary calc-eligibility facility-req-recal">Calc.
						Eligibility</button>
					<button type="button" class="btn btn-primary recalcuate"
						onclick="return recalculateEmiCalculate();">Recalculate
						EMI</button>
					<button type="button" class="btn btn-primary " onclick="return saveFacilityRequested();">Save</button>
				</div>
			</div> -->
			<%-- <div class="row">
				<div class="bs-example facilityreq-table">
					<table class="table table-striped" id="subtble">
						<thead>
							<tr>
								<th>Facility</th>
								<th>Bank</th>
								<th>Scheme/Card</th>
								<th>Eligible Amt</th>
								<th>Eligible EMI</th>
								<th>ROI(%)</th>
								<th>EMI Applicable</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<tr class="heightandblock">
								<td></td>
							</tr>
							<tr>
								<td><span class="sel-ttip"> <select
										name="facilityRequested" id="selectfacility"
										onchange="hideText(this)"
										style="width: 145px; margin-top: 7px">
											<!-- <option value="-1">select</option> -->
											<c:forEach var="facility"
												items="${productMasterDetail.productMaster }">
												<option value="${facility.prodId}"
													${facility.prodName==leadHeaderDetail.queue?'Selected':'' }>${facility.prodName}</option>
											</c:forEach>
									</select>
								</span> <span class="select-tooltip"
									style="margin-top: -15px; background: #fff; border: solid 1px #ccc"></span>
								</td>
								<td><span class="sel-ttip"> <select
										name="facilityRequestedBank" id="facilityRequestedBank"
										style="width: 175px; margin-top: 7px">
											<option value="-1"></option>
											<c:forEach var="bank"
												items="${productMasterDetail.bankMaster}">
												<option value="${bank.bankId}"
													${bank.bankId==leadHeaderDetail.bankId?"selected":'' }>${bank.bankName}</option>
											</c:forEach>
									</select>
								</span> <span class="select-tooltip"
									style="margin-top: -15px; background: #fff; border: solid 1px #ccc"></span>
								</td>
								<td><input type="text" class="rgtAlign"
									value="${productDetail.cardName}" readonly="readonly"
									name="facilityRequestedSchemeName"
									id="facilityRequestedSchemeName" /></td>
								<td><input type="text" class="rgtAlign"
									value="${productDetail.maxLoanAmount}" name="maxLoanAmount"
									id="maxLoanAmount" readonly="readonly" /></td>
								<td><input type="text" class="rgtAlign"
									value="${productDetail.facilityRequestedEmi}"
									readonly="readonly" name="facilityRequestedEmi"
									id="facilityRequestedEmi"
									onchange="return checkAfter8DigitDecimals(this);"
									maxlength="11" /> <input type="hidden" name="hiddenLeadId"
									id="hiddenLeadId" value="${productDetail.hiddenLeadId}" /> <input
									type="hidden" id="dobForLeadId" value="${productDetail.dob}" />
									<input type="hidden" id="grossMonthlyIncomeId"
									value="${productDetail.grossMonthlyIncome}" /> <input
									type="hidden" id="netMonthlyincomeId"
									value="${productDetail.netMonthlyincome}" /> <input
									type="hidden" id="yearInjobId"
									value="${productDetail.yearInjob}" /> <input type="hidden"
									id="workingExpId" value="${productDetail.workingExp}" /> <input
									type="hidden" id="corpSalaryAcId"
									value="${productDetail.corpSalaryAc}" /> <input type="hidden"
									id="termCond" name="termCond" value="${productDetail.termCond}" />
									<input type="hidden" name="eligibilityId" id="eligibilyCalId"
									value="${productDetail.eligibilityId}" /> <input type="hidden"
									id="minLoanAmount" value="" /> <input type="hidden"
									id="maxTenor" value="" /> <input type="hidden" id="minTenor"
									value="" /> <input type="hidden" id="limitLoanAmount" value="" />
									<input type="hidden" id="productCategory"
									name="productCategory" value="${productCategory}" /> <input
									type="hidden" id="facilitySelectedId"
									value="${leadHeaderDetail.facilityReqId}" /> <input
									type="hidden" id="occupationType"
									value="${productDetail.occupationType}" /> <input
									type="hidden" id="grossProfit"
									value="${productDetail.grossProfit}" /> <input type="hidden"
									id="netProfitAftertax"
									value="${productDetail.netProfitAftertax}" /> <input
									type="hidden" id="annualSales"
									value="${productDetail.annualSales}" /> <input type="hidden"
									id="homeLoanValidation"
									value="${productDetail.homeLoanValidation}" /> <input
									id="popupFlag" type="hidden" value="Y" /></td>
								<td><input type="text" class="rgtAlign"
									value="${productDetail.facilityRequestedLoanAmount}"
									name="facilityRequestedLoanAmount"
									id="facilityRequestedLoanAmount"
									onkeypress="return onlyNumeric(event);" maxlength="10" /></td>
								<td><input type="text" class="rgtAlign"
									value="${productDetail.facilityRequestedTenor}"
									name="facilityRequestedTenor" style="width: 40px"
									id="facilityRequestedTenor"
									onkeypress="return onlyNumeric(event);" /></td>
								<td><input type="text" class="rgtAlign"
									value="${productDetail.facilityRequestedRateOfIntrest}"
									name="facilityRequestedRateOfIntrest"
									id="facilityRequestedRateOfIntrest"
									onchange="return checkDecimals(this);" style="width: 40px"
									readonly="readonly" /></td>
								<td><input type="text" class="rgtAlign"
									value="${productDetail.facilityRequestedReCalEmi}"
									name="facilityRequestedReCalEmi" id="facilityRequestedReCalEmi"
									onchange="return checkDecimals(this);" maxlength="6"
									readonly="readonly" style="width: 115%;" /></td>			
							</tr>
							<tr>								
						</tbody>
					</table>
				</div>
			</div> --%>
			<%-- <div class="row buttonHeading exis-fac-row">
				<div class="col-lg-9 col-md-9 col-sm-6 col-xs-5">
					<h2 class="heading">Existing Facilities</h2>
				</div>
				<div class="col-lg-3 col-md-3 col-sm-6 col-xs-7 btnPosition">
					<a class="toggle-list btn-primary"> <span
						class="toggle-icon minus-icon1 "></span>
					</a>
					<!-- <button type="button" class="btn btn-primary"
					onclick="uExFacility();">Save</button> -->
					<button type="button" class="btn btn-primary"
						onclick="dExFacility();">Delete</button>
					<button type="button"
						class="btn btn-primary add-list-existingFacility"
						value="${productDetail.existingFacilityHistory eq null ? '0' : fn:length(productDetail.existingFacilityHistory)}">Add</button>

				</div>
			</div>
			<div class="row exis-facil">
				<div class="bs-example">

					<table
						class="table table-striped tr-padding-zero margin-table-bottom">
						<thead>
							<tr>

								<th></th>
								<th>Facility</th>
								<th>Bank</th>
								<th>Loan Amount Or CC Limit</th>
								<th>EMI Or CC O/s</th>
								<th>Balance Tenor (Months)</th>
								<!-- <th>"Remarks/Property Details/Card Type/Car Make"</th>-->
								<th>Remarks</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td></td>
							</tr>
							<c:forEach var="facility"
								items="${productDetail.existingFacilityHistory}"
								varStatus="status">
								<tr>
									<td><input type="checkbox"
										name="existingFacilityHistory[${status.index}].checkRow"
										value="${facility.exitingFacId}"></td>
									<td><span class="sel-ttip"> <select
											name="existingFacilityHistory[${status.index}].prodId"
											style="width: 140px; margin-top: 6px;">
												<option value="-1">select</option>
												<c:forEach var="fac"
													items="${productMasterDetail.productMaster}">
													<option value="${fac.prodId}"
														${fac.prodId==facility.prodId?'selected':''}>${fac.prodName}
													</option>
												</c:forEach>
										</select></span> <span class="select-tooltip"
										style="margin-top: -15px; background: #fff; border: solid 1px #ccc"></span>
									</td>
									<td><span class="sel-ttip"> <select
											name="existingFacilityHistory[${status.index}].bankId"
											style="width: 140px; margin-top: 6px;">
												<option value="-1">SELECT</option>
												<c:forEach var="bank"
													items="${productMasterDetail.bankMaster}">
													<option value="${bank.bankId}"
														${bank.bankId==facility.bankId?"selected":'' }>${bank.bankName}</option>
												</c:forEach>
										</select></span> <span class="select-tooltip"
										style="margin-top: -15px; background: #fff; border: solid 1px #ccc"></span>
									</td>
									<td><input
										name="existingFacilityHistory[${status.index}].loanCcAmt"
										class="rgtAlign" value="${facility.loanCcAmt}"
										onkeypress="return onlyNumeric(event);" maxlength="10" /></td>
									<td><input
										name="existingFacilityHistory[${status.index}].emiCcOutstanding"
										class="rgtAlign" value="${facility.emiCcOutstanding}"
										onchange="return checkAfter8DigitDecimals(this);"
										maxlength="11" /></td>
									<td><input
										name="existingFacilityHistory[${status.index}].tenorBalMonths"
										class="rgtAlign" value="${facility.tenorBalMonths}"
										onkeypress="return onlyNumeric(event);" maxlength="3" /></td>
									<td><input
										name="existingFacilityHistory[${status.index}].remarks"
										value="${facility.remarks}" /></td>
								</tr>
								<input type="hidden" name="personalDtlId"
									value="${personalDtlId}" />
								<input type="hidden" name="caseId" value="${leadId}" />
							</c:forEach>
						</tbody>
					</table>

				</div>

			</div> --%>

			<c:if
				test="${dto.convertToCustomerEnable=='Y' && dto.convertToCustomerWebEnable=='Y' }">
				<div class="row buttonHeading exis-fac-row">
					<div class="col-lg-9 col-md-9 col-sm-6 col-xs-5">
						<h2 class="heading">Convert to Customer</h2>
					</div>
					<div class="col-lg-3 col-md-3 col-sm-6 col-xs-7 btnPosition">
						<!-- <a class="toggle-list btn-primary"> <span
							class="toggle-icon minus-icon1 "></span>
						</a> -->
					</div>
				</div>

				<div class="row exis-facil">
					<div class="bs-example">

						<table class="table table-striped margin-table-bottom-space">
							<c:if test="${empty dto.prospectId}">
								<thead>
									<tr>
										<th class="width-set"></th>
										<th>Branch</th>
										<th>Product</th>
										<th>Scheme</th>
										<th>Purpose Of Loan</th>
										<th>Loan Amount</th>
										<th>Tenure</th>
									   <th>Affordable EMI</th>
										<th></th>
										<th></th>
										<th></th>
									</tr>
								</thead>
							</c:if>
							<c:if test="${not empty dto.prospectId}">
								<thead>
									<tr>
										<th></th>
										<th>Branch</th>
										<th>Product</th>
										<th>Scheme</th>
										<th>Purpose Of Loan</th>
										<th>Loan Amount</th>
										<th>Tenure</th>
										 <th>Affordable EMI</th>
										<th>Prospect Id</th>
										<th>Applicant Id</th>
										<th></th>
									</tr>
								</thead>
							</c:if>
							<tbody>
								<tr>
									<td></td>
									<td><span class="sel-ttip"> <select id="branch" class="validField"
											name="branchId" style="width: 140px; margin-top: 6px;" onchange="getSchemeName(this)">
												<option value="-1">select</option>
												<c:forEach var="obj" items="${productMasterDetail.branchMaster}">
													<option value="${obj.geoId}"
														${obj.geoId==productDetail.branchId?"selected":'' }>${obj.geoName}</option>
												</c:forEach>
										</select></span></td>
										
										
										<input type="hidden" id="businessDate" name="businessDate" value="${businessDate}">


									<td><span class="sel-ttip"> <select class="validField"
											name="facilityRequested" id="selectfacility"
											onchange="getSchemeName(this)" style="width: 145px; margin-top: 7px">
												<option value="-1">select</option>
												<c:forEach var="facility"
													items="${productMasterDetail.productMaster }">
													<c:if test="${facility.prodTypeId=='1000000001'}">
														<option value="${facility.prodId}" ${facility.prodId==leadHeaderDetail.queueId?'Selected':'' }>${facility.prodName}</option>													
													</c:if>
												</c:forEach>
										</select>
									</span> <span class="select-tooltip"
										style="margin-top: -15px; background: #fff; border: solid 1px #ccc"></span>
									</td>
									
									<c:forEach var="facility" items="${productMasterDetail.productMaster}">									
										<c:if test="${facility.prodId==productDetail.schemeId}">
											<input type="hidden" id="schemeValidationMinLoan" value="${facility.minLoanvalue}">								
											<input type="hidden" id="schemeValidationMaxLoan" value="${facility.maxLoanvalue}">
											<input type="hidden" id="schemeValidationMinTenure"  value="${facility.minLoanTenure}">
											<input type="hidden" id="schemeValidationMaxTenure"  value="${facility.maxLoanTenure}"/>
										</c:if>
									</c:forEach>																						
																		
									<td><span class="sel-ttip"> <select name="schemeId" class="validField"
											id="selectfacility" onchange="getPurposeOfLoan(this);"
											style="margin-top :7px; width: 145px;margin-top: 7px">
											<option value="-1">select</option>
												<c:forEach var="facility"
													items="${productMasterDetail.productMaster }">
													<c:if test="${facility.prodTypeId=='1000000002'}">
														<option value="${facility.prodId}"
															${facility.prodId==productDetail.schemeId?'Selected':'' }>${facility.prodName}</option>
													</c:if>
												</c:forEach>
										</select>
									</span> <span class="select-tooltip"
										style="margin-top: -15px; background: #fff; border: solid 1px #ccc"></span>
									</td>
									

									<td><span class="sel-ttip"> <select class="validField"
											name="purposeOfLoanId" id="purposeOfLoan"
											style="width: 145px; margin-top: 7px">
												<option value="-1">select</option>
												<c:forEach var="obj"
													items="${productMasterDetail.purposeOfLoanMaster}">
													<option value="${obj.purposeOfLoanId}"
														${obj.purposeOfLoanId==productDetail.purposeOfLoanId?'Selected':''}>${obj.purposeOfLoanName}</option>
												</c:forEach>
										</select>
									</span> <span class="select-tooltip"
										style="margin-top: -15px; background: #fff; border: solid 1px #ccc"></span>
									</td>

										<td><input type="text" 
									class="validField"
										value="${productDetail.facilityRequestedLoanAmount}"
										name="facilityRequestedLoanAmount"
										id="facilityRequestedLoanAmount"
										style="width: 145px; margin-top: 7px"
										onkeypress="return onlyNumeric(event);"
										onchange="validateLoanAmount(this);" maxlength="10" /> <input
										type="hidden" name="hiddenLeadId" id="hiddenLeadId"
										value="${productDetail.hiddenLeadId}" /></td>


									<td><input type="text"  class="validField"
										value="${productDetail.facilityRequestedTenor}"
										style="width: 145px; margin-top: 7px"
										name="facilityRequestedTenor" style="width: 40px"
										id="facilityRequestedTenor"
										onkeypress="return onlyNumeric(event);" onchange="validateLoanTenure(this);"/></td>
										
										<td><input type="text"  class="validField"
										value="${productDetail.facilityRequestedEmi}"
										style="width: 145px; margin-top: 7px"
										name="facilityRequestedEmi" style="width: 40px"
										id="facilityRequestedEmi"
										onkeypress="return onlyNumeric(event);" /></td>
										
								
									
									<td style="width: 145px; margin-top: 7px">${dto.prospectId}</td>

									<td style="width: 145px; margin-top: 7px">${dto.applicantId}</td>

									<td><c:if test="${not empty dto.prospectId}">
											<button type="button"
												class="btn btn-primary convertbtn_prodrefferral"
												disabled="disabled"">Convert to Customer</button>
										</c:if> <c:if test="${empty dto.prospectId}">
											<button type="button"
												class="btn btn-primary convertbtn_prodrefferral"
												onclick="javascript:validate();">Convert to
												Customer</button>
										</c:if> <!-- <a class="list-content-btn fr convertbtn_prodrefferral"
										href="" onclick="return validate()">Convert to Customer</a> --></td>
								</tr>
							</tbody>
						</table>

					</div>

				</div>
			</c:if>
		<%-- 	<div class="row buttonHeading prod-reff">
				<div class="col-lg-9 col-md-9 col-sm-6 col-xs-5">
					<h2 class="heading">Product Referral</h2>
				</div>
				<div class="col-lg-3 col-md-3 col-sm-6 col-xs-7 btnPosition">
					<button type="button" class="btn btn-primary"
						onclick="deleteXSell();">Delete</button>
					<button type="button"
						class="btn btn-primary add-list-prodreffereal" id="prodreffereal"
						value="	${xSellList eq null ? '0' : fn:length(xSellList)}">Add</button>

				</div>
			</div>
			<div class="row">
				<div class="bs-example productreferral-table">

					<table class="table table-striped tr-padding-zero">
						<thead>
							<tr>
								<th>&nbsp;</th>
								<th>Facility</th>
								<th>Bank</th>
								<th>Loan Amount</th>
								<th>Remarks</th>
								<th></th>
								<th style="min-width: 151px !important;">New Lead ID</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td></td>
							</tr>
							<c:forEach var="xSellData" items="${xSellList}"
								varStatus="xSellStatus">
								<tr>
									<td><input type="checkbox" value="${xSellData.xsellId }"
										${xSellData.isXsold=='Y'?'disabled':'' }
										name="listXSell[${xSellStatus.index }].xsellId"
										id="listXSell[${xSellStatus.index }].xsellId" /></td>
									<td style="position: relative;"><select
										name="listXSell[${xSellStatus.index }].facilityReqId"
										id="listXSell[${xSellStatus.index }].facilityReqId"
										${xSellData.isXsold=='Y'?'disabled':'' } style="width: 260px">
											<option value="-1">Select</option>
											<c:forEach var="facility1"
												items="${productMasterDetail.productMaster }">
												<option value="${facility1.prodId }"
													${facility1.prodId==xSellData.facilityReqId?'Selected':'' }>${facility1.prodName}</option>
											</c:forEach>
									</select></td>
									<td><select name="listXSell[${xSellStatus.index }].bankId"
										class="selectBank" ${xSellData.isXsold=='Y'?'disabled':'' }
										style="width: 260px">
											<option value="-1"></option>
											<c:forEach var="bank1"
												items="${productMasterDetail.bankMaster }">
												<option value="${bank1.bankId }"
													${bank1.bankId==xSellData.bankId?'Selected':'' }>${bank1.bankName
													}</option>
											</c:forEach>
									</select></td>
									<td><input type="text" value="${xSellData.amount }"
										maxlength="10" onkeypress="return onlyNumeric(event);"
										name="listXSell[${xSellStatus.index }].amount"
										class="rgtAlign" ${xSellData.isXsold=='Y'?'disabled':'' } /></td>
									<td><input type="text"
										name="listXSell[${xSellStatus.index }].remarks"
										value="${xSellData.remarks }"
										${xSellData.isXsold=='Y'?'disabled':'' } /></td>
									<td><a
										class="list-content-btn fr convertbtn_prodrefferral"
										href='<c:if test="${xSellData.isXsold=='Y'}">javascript:void(0)</c:if>
							<c:if test="${xSellData.isXsold=='N'}">convertXLead.do?sellCode=${xSellData.xsellId}</c:if>'>Convert</a></td>
									<td>${xSellData.caseId!=null?xSellData.caseId:'' }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>

				</div>
			</div> --%>

		</form>
	</div>

</div>
<!--credit card popup-->
<div class="creditcardpop mainpopup">
	<div class="popup-content">
		<div class="popup-title">
			Credit Card
			<div class="close-popup">
				<div class="close-white"></div>
			</div>
		</div>
		<div class="popup-text">
			<!-- <div class="loanpop-stats">
	<div class="loanpop-stats">
	<div class="loanamountt">
		<div>Credit Limit</div>
		<div><input type="text" id="popupCreditTextId" value="" style="background: #56C8FF;width: 100%;font-size: 14px;border:none" readonly="readonly"></div>
		
	</div>
	
	<div class="tenor-months">
		<div>Credit (days)</div>
		<div><input type="text" id="popupDayTextId" value="" style="background: #56C8FF;width: 100%;font-size: 14px;border:none" readonly="readonly"></div>
	</div>
</div>
</div> -->
			<div
				style="overflow-x: hidden; overflow-y: auto; max-height: 300px; width: 100%;">
				<table class="table" width="100%" cellpadding="0" cellspacing="0"
					border="0" id="creditTable">

					<thead>
						<tr>
							<th>Bank</th>
							<th>Card Type</th>
							<th>Joining fee<br>(INR)
							</th>
							<th>Annual Fee<br>(INR)
							</th>
							<th>Renewal Fee<br>(INR)
							</th>
							<th>Select</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
			<div class="text-center submit-credit" style="clear: both">
				<input class="btn btn-primary" type="submit" value="Submit" />
			</div>
		</div>
	</div>
</div>
<!--For personal loan and business loan  -->
<div class="loanpop mainpopup">
	<div class="popup-content">
		<div class="popup-title">
			Loan
			<div class="close-popup">
				<div class="close-white"></div>
			</div>
		</div>
		<div class="popup-text">
			<div
				style="overflow-x: auto; overflow-y: auto; max-height: 300px; width: 100%;">
				<table class="table" width="100%" cellpadding="0" cellspacing="0"
					border="0" id="loanTable">
					<thead>
						<tr>
							<!-- <th>Bank</th>
	<th>Max Eligible<br/> Loan Amount<br/>(INR)</th>	
	<th>Tenure (Months)</th>	
	<th>ROI(%)</th>
	<th>Processing Fee(INR)</th>
	<th>Pre-payment Charges(INR)</th>
	<th>Partpayment - Y/N</th>
	<th>EMI(INR)</th>
	<th>Select</th> -->
							<th>Bank</th>
							<th>Max Eligible<br /> Loan Amount<br />(INR)
							</th>
							<th>Max Eligible <br />EMI<br />(INR)
							</th>
							<th>Max Tenure <br />(Month)
							</th>
							<th>ROI<br />(%)
							</th>
							<th>Processing<br /> Fee (%)
							</th>
							<th>Pre-payment<br />Charges<br />(%)
							</th>
							<th>Partpayment <br />Yes/No
							</th>
							<th>Select</th>
						</tr>
					</thead>

					<tbody>

					</tbody>

				</table>
			</div>
			<div class="text-center submit-loan" style="clear: both">
				<input class="btn btn-primary" type="submit" value="Submit" />
			</div>

		</div>
	</div>
</div>
<!-- for home loan and LAP -->
<div class="homeloanpop mainpopup">
	<div class="popup-content">
		<div class="popup-title">
			Loan
			<div class="close-popup">
				<div class="close-white"></div>
			</div>
		</div>
		<div class="popup-text">
			<div
				style="overflow-x: auto; overflow-y: auto; max-height: 300px; width: 100%;">
				<table class="table" width="100%" cellpadding="0" cellspacing="0"
					border="0" id="homeloanTable">

					<thead>
						<tr>

							<th>Bank</th>
							<th>Eligible Loan Amount(INR)</th>
							<th>Tenure (Months)</th>
							<th>ROI(%)</th>
							<th>Fixed/Floating/Semi</th>
							<th>Application Fee</th>
							<th>Processing Fee(%)</th>
							<th>Pre-payment Charges(%)</th>
							<th>EMI(INR)</th>
							<th>LTV(%)</th>
							<th>Select</th>
						</tr>
					</thead>

					<tbody>

					</tbody>

				</table>
			</div>
			<div class="text-center submit-loan" style="clear: both">
				<input class="btn btn-primary" type="submit" value="Submit" />
			</div>

		</div>
	</div>
</div>
<!-- Modal -->
<div class="modal fade" id="myModal1" role="dialog"
	style="display: none;" aria-hidden="true">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header model-border-none">
				<button type="button" class="close" data-dismiss="modal"
					onclick="setData()">×</button>
				<h4 class="modal-title"></h4>
			</div>
			<div class="modal-body">
				<textarea class="modal-textarea" name="remark" id="modalRemark"></textarea>
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
<div class="modal fade" id="myModal2" role="dialog"
	style="display: none;" aria-hidden="true">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header model-border-none">
				<button type="button" class="close" data-dismiss="modal"
					onclick="setData()">×</button>
				<h4 class="modal-title"></h4>
			</div>
			<div class="modal-body">
				<textarea class="modal-textarea" name="remark" id="modalRemark"></textarea>
			</div>
			<div class="modal-footer model-border-none">
				<button type="button" class="btn btn-default" data-dismiss="modal"
					onclick="setData()">Close</button>
			</div>
		</div>

	</div>
</div>
<!-- Modal -->
<script>
	
	
	
	$('.prev-lead-icon').parent('.subheader-btn').hover(function() {		
		$(this).children('.prevlead-tooltip').fadeToggle(150);
	});
	$('.next-lead-icon').parent('.subheader-btn').hover(function() {		
		$(this).children('.nextlead-tooltip').fadeToggle(150);
	});
	$('.savee-icon').parent('.subheader-btn').hover(function() {		
		$(this).children('.savee-tooltip').fadeToggle(150);
	});	
	
	$('.saveandexit-icon').parent('.subheader-btn').click(function(){
		var loopCount=$('#prodreffereal').val();		
		var checkedCount=0;
		var unselectedCount=0;
		for(var i=0;i<loopCount;i++){
			if(document.getElementById('listXSell['+i+'].xsellId').checked){
				if(document.getElementById("listXSell["+i+"].facilityReqId").value==-1){
					alert("Please Select Facility in Product Referral Section");
					document.getElementById("listXSell["+i+"].facilityReqId").focus();
					document.getElementById("listXSell["+i+"].facilityReqId").select();
					unselectedCount=unselectedCount+1;
					return false;
			}
				checkedCount=checkedCount+1;
			}
		}
		
		document.getElementById("productForm").action = "productSaveAndExit.do?action=saveAndExit";
		document.getElementById("productForm").submit();
		alert("PRODUCTS UPDATED"); 
		 	
		 	//var commaxEmi=document.getElementById("facilityRequestedEmi").value;
		 	
		 	//var maxEmi=parseInt(commaxEmi.replace(new RegExp(',', 'g'), ''));
		 
			//var comcalEmi=document.getElementById("facilityRequestedReCalEmi").value;
			
			//var calEmi = parseInt(comcalEmi.replace(new RegExp(',', 'g'), ''));
			
			/* if(calEmi !== null && calEmi !== 0 && calEmi!== '' )
			{				
				if(maxEmi!="" && calEmi!="" && maxEmi<calEmi){
					alert("APPLICABLE EMI CAN NOT BE GREATER THAN MAXIMUM ELLIGIBLE EMI:"+maxEmi);
					return false;
				}
				else {
		    		document.getElementById("productForm").action = "productSaveAndExit.do?action=saveAndExit";
					document.getElementById("productForm").submit();		
				}
			}
			else
			{
				alert("PLEASE RECALCULATE YOUR EMI, IT SHOULD BE LESS THAN MAX ELLIGIBLE EMI");
			} */
	});
	
	$('.savee-icon').parent('.subheader-btn').click(function(){
		
		
		var loopCount=$('#prodreffereal').val();		
		var checkedCount=0;
		var unselectedCount=0;
		for(var i=0;i<loopCount;i++){
			if(document.getElementById('listXSell['+i+'].xsellId').checked){
				if(document.getElementById("listXSell["+i+"].facilityReqId").value==-1){
					alert("Please Select Facility in Product Referral Section");
					document.getElementById("listXSell["+i+"].facilityReqId").focus();
					document.getElementById("listXSell["+i+"].facilityReqId").select();
					unselectedCount=unselectedCount+1;
					return false;
				}
				checkedCount=checkedCount+1;
			}
		}
		document.getElementById("productForm").action = "productSaveAndExit.do?action=save";
		document.getElementById("productForm").submit();
		 alert("PRODUCTS UPDATED"); 
		
			/* var commaxEmi=document.getElementById("facilityRequestedEmi").value;
		 	
		 	var maxEmi=parseInt(commaxEmi.replace(new RegExp(',', 'g'), ''));
		 
			var comcalEmi=document.getElementById("facilityRequestedReCalEmi").value;
			
			var calEmi = parseInt(comcalEmi.replace(new RegExp(',', 'g'), ''));
			//alert(calEmi);
			if(calEmi !== null && calEmi !== 0 && calEmi!== '' )
			{	
				if(maxEmi!="" && calEmi!="" && maxEmi<calEmi){
					alert("APPLICABLE EMI CAN NOT BE GREATER THAN MAXIMUM ELLIGIBLE EMI:"+maxEmi);
					return false;
				}
				else {
				    document.getElementById("productForm").action = "productSaveAndExit.do?action=save";
					document.getElementById("productForm").submit();		
				}
			}
			else
			{
				alert("PLEASE RECALCULATE YOUR EMI, IT SHOULD BE LESS THAN MAX ELLIGIBLE EMI");
			}	 */
			
	});
	 
	$('.saveandexit-icon').parent('.subheader-btn').hover(function() {		
		$(this).children('.saveandexit-tooltip').fadeToggle(150);
	});

	$('.usermenu-mobile').click(function() {		
		$('.header-rightpanel').animate({
			"top" : "0"
		});
	});
	$('.close-header-rightpanel').click(function() {

		$('.header-rightpanel').animate({
			"top" : "-260px"
		});

	});
	
	
	
	function getSchemeName(val) {		
		var sendData = {
			    "idColumnName" : "PRODUCTID",
			    "valueColumnName" :"PRODNAME",
			    "dependentTableName" :"QM_PRODUCT",
			    "crossTableName" :"QM_PRODUCT",
			    "crossTableDependentId" :"PRODUCTID",
			    "crossTableMasterId" :"PARENTPRODID",
			    "masterValue" : val.value
		}		
		$.ajax({
			type : "post",
			url : "getDependentMaster.do",
			data : sendData,		
			ontext : document.body,
			success : function(response) {
 				 var docTypeId = val.id;
				var leftNameIndex = docTypeId.indexOf("[");
				var rightNameIndex = docTypeId.indexOf("]");
				var indexnumber = docTypeId.substring(leftNameIndex + 1,rightNameIndex);
				leftName = docTypeId.substring(0, leftNameIndex);							
				var select = $('[name="schemeId"]');				
				select.find('option').remove();
				$('<option>').val("-1").text("Select").appendTo(select);
				$($.parseJSON(response)).map(function() {
					$('<option>').val(this.id).text(this.value).appendTo(select);
				}); 
			}
		});
	}
	
	
	function getPurposeOfLoan(val) {		
		var sendData = {
			    "idColumnName" : "PURPOSEOFLOANID",
			    "valueColumnName" :"PURPOSEOFLOANNAME",
			    "dependentTableName" :"QM_PURPOSEOFLOAN",
			    "crossTableName" :"QM_PURPOSEOFLOAN",
			    "crossTableDependentId" :"PURPOSEOFLOANID",
			    "crossTableMasterId" :"PRODUCTID",
			    "masterValue" : val.value
		}		
		$.ajax({
			type : "post",
			url : "getDependentMaster.do",
			data : sendData,		
			ontext : document.body,
			success : function(response) {
 				 var docTypeId = val.id;
				var leftNameIndex = docTypeId.indexOf("[");
				var rightNameIndex = docTypeId.indexOf("]");
				var indexnumber = docTypeId.substring(leftNameIndex + 1,rightNameIndex);
				leftName = docTypeId.substring(0, leftNameIndex);			
				
				var select = $('[name="purposeOfLoanId"]');
				
				select.find('option').remove();
				$('<option>').val("-1").text("Select").appendTo(select);
				$($.parseJSON(response)).map(function() {
					$('<option>').val(this.id).text(this.value).appendTo(select);
				}); 
			}
		});
	}
	
	
	
	$('.add-list-prodreffereal')
			.click(
					function(e) {
						e.preventDefault();
						var cnti = parseInt(this.value);
						this.value = cnti + 1;
						$(this)
								.parent('.btnPosition')
								.parent()
								.next('.row')
								.find('tbody')
								.children('tr')
								.each(
								 		function() {
											//$(this).parent('tbody').append('<tr><td><select><option></option></select></td><td><select><option></option></select></td><td><input type="text"/></td><td><input type="text"/></td><td><a class="list-content-btn fr convertbtn_prodrefferral">Convert</a></td><td><input type="text"/></td></tr>').length(1);
											$(this)
													.parent('tbody')
													.append(
													'<tr><td><input type="checkbox" name="listXSell['+cnti+'].xsellId" id="listXSell['+cnti+'].xsellId" value="insert" checked/></td><td style="position: relative;"><span class="sel-ttip"><select name="listXSell['+cnti+'].facilityReqId" id="listXSell['+cnti+'].facilityReqId"} style="width:260px"> <option value="-1">Select</option><c:forEach var="facility1" items="${productMasterDetail.productMaster }"><option value="${facility1.prodId }">${facility1.prodName}</option></c:forEach></select></span><span class="select-tooltip" style="margin-top: -15px; background-color: rgb(255, 255, 255); border: 1px solid rgb(204, 204, 204); display: none; background-position: initial initial; background-repeat: initial initial; ">select</span></td><td style="position: relative"><span class=""><select name="listXSell['+cnti+'].bankId" } class="selectBank" style="width:260px"><option value="-1"></option></select></span></td><td><input type="text" class="rgtAlign" name="listXSell['+cnti+'].amount"} onkeypress="return onlyNumeric(event);" maxlength="10" /></td><td><input type="text" name="listXSell['+cnti+'].remarks" " } /></td><td></td><td>${xSellData.caseId!=null?xSellData.caseId:"" }</td></tr>')
													.length(1);
										
										
										}); 
										
										

					});

	$('.add-list-existingFacility')
			.click(
					function(e) {
						e.preventDefault();
						var cnti = parseInt(this.value);
						this.value = cnti + 1;
						$(this)
								.parent('.btnPosition')
								.parent()
								.next('.row')
								.find('tbody')
								.children('tr')
								.each(
										function() {
											//$(this).parent('tbody').append('<tr><td><select><option></option></select></td><td><select><option></option></select></td><td><input type="text"/></td><td><input type="text"/></td><td><a class="list-content-btn fr convertbtn_prodrefferral">Convert</a></td><td><input type="text"/></td></tr>').length(1);
											$(this)
													.parent('tbody')
													.append(
															'<tr><td><input type="checkbox" name="existingFacilityHistory['+cnti+'].checkRow" value="insert" checked></td><td style="position: relative"><span class="sel-ttip"><select name="existingFacilityHistory['+cnti+'].prodId" style="width: 140px"><option value="-1">select</option><c:forEach var="fac" items="${productMasterDetail.productMaster}"><option value="${fac.prodId}" ${fac.prodId==facility.prodId?"selected":""}>${fac.prodName}</option></c:forEach></select></span><span class="select-tooltip" style="margin-top: -15px; background-color: rgb(255, 255, 255); border: 1px solid rgb(204, 204, 204); display: none; "></span></td><td style="position: relative;"><span class="sel-ttip"><select name="existingFacilityHistory['+cnti+'].bankId" style="width: 140px"><option value="-1" >select</option><c:forEach var="bank" items="${productMasterDetail.bankMaster}"><option value="${bank.bankId}" ${bank.bankId==facility.bankId?"selected":"" }>${bank.bankName}</option></c:forEach></select></span><span class="select-tooltip" style="margin-top: -15px; background-color: rgb(255, 255, 255); border: 1px solid rgb(204, 204, 204); display: none; "></span></td><td><input name="existingFacilityHistory['
																	+ cnti
																	+ '].loanCcAmt" class="rgtAlign" onkeypress="return onlyNumeric(event);" value="" maxlength="10" /></td><td><input name="existingFacilityHistory['
																	+ cnti
																	+ '].emiCcOutstanding" class="rgtAlign" onchange="return checkAfter8DigitDecimals(this);"  maxlength="11" value="" /></td><td><input name="existingFacilityHistory['
																	+ cnti
																	+ '].tenorBalMonths" class="rgtAlign" onkeypress="return onlyNumeric(event);" value="" maxlength="3" /></td><td><input name="existingFacilityHistory['+cnti+'].remarks" value="" maxlength="200"/></td></tr>')
													.length(1);
										});
					});

	$('.add-list-btn')
			.click(
					function(e) {
						e.preventDefault();
						$(this)
								.parent('.btnPosition')
								.parent()
								.next('.row')
								.find('tbody')
								.children('tr')
								.each(
										function() {

											$(this)
													.parent('tbody')
													.append(
															'<tr><td><input type="text"/></td><td><input type="text"/></td><td><input type="text"/></td><td><input type="text"/></td><td><input type="text"/></td><td></td><td></td></tr>')
													.length(1);

										});
					});
</script>
<script type="text/javascript">
/* function saveFacilityRequested(){


			var commaxEmi=document.getElementById("facilityRequestedEmi").value;
		 	
		 	var maxEmi=parseInt(commaxEmi.replace(new RegExp(',', 'g'), ''));
		 
			var comcalEmi=document.getElementById("facilityRequestedReCalEmi").value;
			
			var calEmi = parseInt(comcalEmi.replace(new RegExp(',', 'g'), ''));
			
			if(calEmi !== null && calEmi !== 0 && calEmi!== '' )
			{				
				if(maxEmi!="" && calEmi!="" && maxEmi<calEmi){
					alert("APPLICABLE EMI CAN NOT BE GREATER THAN MAXIMUM ELLIGIBLE EMI:"+maxEmi);
					return false;
				}
				else 
				{
					document.getElementById("productForm").action = "updateFacilityRequested.do";
					document.getElementById("productForm").submit();
				}
			}
			else
			{
				alert("PLEASE RECALCULATE YOUR EMI, IT SHOULD BE LESS THAN MAX ELLIGIBLE EMI");
			}
		
} */
	
	function updateXSell() {
		document.getElementById("productForm").action = "updateXSell.do";
		document.getElementById("productForm").submit();
	}

	function deleteXSell() {
		document.getElementById("productForm").action = "deleteXSell.do";
		document.getElementById("productForm").submit();
	}
	function aExFacility() {
		document.getElementById("productForm").action = "aExFacility.do";
		document.getElementById("productForm").submit();
	}
	function dExFacility() {
		document.getElementById("productForm").action = "dExFacility.do";
		document.getElementById("productForm").submit();
	}
	function uExFacility() {
		document.getElementById("productForm").action = "uExFacility.do";
		document.getElementById("productForm").submit();
	}	
	

function recalculateEmiCalculate() {	
		var r=parseFloat(document.getElementById('facilityRequestedRateOfIntrest').value);
		var pr=document.getElementById('facilityRequestedLoanAmount').value;
		alert("pr" + pr);
		var p=parseInt(pr.replace(new RegExp(',', 'g'), ''));
		var n=parseInt(document.getElementById('facilityRequestedTenor').value);
		
		var minLoan=parseInt(document.getElementById('minLoanAmount').value);
		var commaxLoan=document.getElementById('maxLoanAmount').value;
		
		var maxLoan=parseInt(commaxLoan.replace(new RegExp(',', 'g'), ''));
		
		var minTenor=parseInt(document.getElementById('minTenor').value);			
		var maxTenor=parseInt(document.getElementById('maxTenor').value);
		//var limitLoanAmount=parseInt(document.getElementById('limitLoanAmount').value);	
		var selectedFacility=parseInt(document.getElementById('selectfacility').value);
				
		//alert("-"+selectedFacility);
       	
       	
		if(selectedFacility!=6){
		if(parseInt(p)<parseInt(minLoan)){
			alert('PLEASE ENTER LOAN AMOUNT GREATER THEN '+minLoan );
			return false;
		}
		
		if(n<minTenor){
			alert('PLEASE ENTER TENOR GREATER THEN '+minTenor );
			return false;
		}
		if(n>maxTenor){
			alert('PLEASE ENTER TENOR LESS THAN '+maxTenor );
			return false;
		}
		/* if(p>limitLoanAmount){
			alert('YOU ARE NOT ELIGIBLE TO LOAN GREATER THAN '+limitLoanAmount );
			return false;
		} */
		
		if(p=="" || p==null){
			alert('PLEASE ENTER LOAN AMOUNT');
			return false;
		}		
		if(n=="" || n==null){
			alert('PLEASE ENTER TENURE');
			return false;
		}
		
		var R = (r / 12) / 100;    
		
        var e = (p * R * (Math.pow((1 + R), n)) / ((Math.pow((1 + R), n)) - 1));  
       
        var totalInt = Math.round((e * n) - p);  
        
        var intPerMonth = Math.round(totalInt / n);   
       
       	var s=(Math.round((e) - intPerMonth)+Math.round(intPerMonth));
       	
       	if(!isNaN(s)){
       		document.getElementById('facilityRequestedReCalEmi').value=s;
       	}
       
       	//var maxEmi=parseFloat(document.getElementById("facilityRequestedEmi").value);
 
       
       	var commaxEmi=document.getElementById('facilityRequestedEmi').value;
       	
		var maxEmi=parseInt(commaxEmi.replace(new RegExp(',', 'g'), ''));
	
		
		var comcalEmi=document.getElementprodrefferealById("facilityRequestedReCalEmi").value;
      	
      	var calEmi = parseInt(comcalEmi.replace(new RegExp(',', 'g'), ''));
    
		if(maxEmi!="" && calEmi!="" && maxEmi<calEmi){

				document.getElementById('facilityRequestedReCalEmi').style.borderColor='#e52213';
				alert("Applicable EMI cannot be greater than Maximum Eligible EMI:"+maxEmi);
				
				if(p>maxLoan){
					document.getElementById('facilityRequestedLoanAmount').style.borderColor='#e52213';
					alert('PLEASE ENTER LOAN AMOUNT LESS THAN '+maxLoan );
				}
				else{
					document.getElementById('facilityRequestedLoanAmount').style.borderColor='#C7C7C7';
				}
						
				return false;
		}else{
			document.getElementById('facilityRequestedReCalEmi').style.borderColor='#C7C7C7';
		}
       	
		if(p>maxLoan){
			document.getElementById('facilityRequestedLoanAmount').style.borderColor='#e52213';
			alert('PLEASE ENTER LOAN AMOUNT LESS THAN '+maxLoan );
			
			if(maxEmi!="" && calEmi!="" && maxEmi<calEmi){
				document.getElementById('facilityRequestedReCalEmi').style.borderColor='#e52213';
				alert("Applicable EMI cannot be greater than Maximum Eligible EMI:"+maxEmi);
			}
			else{
				document.getElementById('facilityRequestedReCalEmi').style.borderColor='#C7C7C7';
			}
			return false;
		}
		else{
			document.getElementById('facilityRequestedLoanAmount').style.borderColor='#C7C7C7';
		}

		return true;
		}
    	
}

$(".facility-req-recal").click(function(){
	
	document.getElementById('facilityRequestedReCalEmi').style.borderColor='#C7C7C7';
	document.getElementById('facilityRequestedLoanAmount').style.borderColor='#C7C7C7';
		
	
	$("#term").text("");
	document.getElementById("popupFlag").value='Y';
	$(".creditcardpop table tr").removeClass("pop-selectedrow");
	$(".creditcardpop table tr input[type='radio']").removeAttr("checked");

	var pr=document.getElementById('facilityRequestedLoanAmount').value;
	alert("pr"+pr);
	if(pr==""){
		var p="";
	}else{
		var p=parseInt(pr.replace(new RegExp(',', 'g'), ''));
	}
	var n=document.getElementById('facilityRequestedTenor').value;	
	if(n==0){
		n="";
	}
	var facilityId=document.getElementById('selectfacility').value;
	
	if(facilityId==-1){
		alert('PLEASE SELECT FACILITY');
		return false;
	}
	
	
	
 	 var dob=document.getElementById('dobForLeadId').value; 	
	 var grossSalary=document.getElementById('grossMonthlyIncomeId').value;	
	 var netSalary=document.getElementById('netMonthlyincomeId').value;	
	 var yrJob=document.getElementById('yearInjobId').value;	 
	 var workingExp=document.getElementById('workingExpId').value;	 
	 var corparateAc=document.getElementById('corpSalaryAcId').value;	 
	 var leadId=document.getElementById('hiddenLeadId').value;	
	 var occupation=document.getElementById('occupationType').value;
	 // alert(">>>>>>"+occupation);
	if(occupation=="SELF EMPLOYED"){
		var grossProfit=document.getElementById('grossProfit').value;
		var netProfitAftertax=document.getElementById('netProfitAftertax').value;
		var annualSales=document.getElementById('annualSales').value;
		//alert("grossProfit-:->"+grossProfit+" | netProfitAftertax-:->"+netProfitAftertax+" | annualSales-:->"+annualSales);
		if(grossProfit==null || grossProfit==""){
			alert("TO CHECK ELIGIBILITY PERSONAL DETAILS AND SALARY DETAILS ARE MANDATORY FIELDS");
			return false;
		}
		if(netProfitAftertax==null || netProfitAftertax==""){
			alert("TO CHECK ELIGIBILITY PERSONAL DETAILS AND SALARY DETAILS ARE MANDATORY FIELDS");
			return false;
		}
		if(annualSales==null || annualSales==""){
			alert("TO CHECK ELIGIBILITY PERSONAL DETAILS AND SALARY DETAILS ARE MANDATORY FIELDS");
			return false;
		}
	}else{
		//alert("Else>>>>>>");
	if(dob==null || dob==""){
		alert("TO CHECK ELIGIBILITY PERSONAL DETAILS AND SALARY DETAILS ARE MANDATORY FIELDS");
		return false;
	}
	if(netSalary==null || netSalary==""){
		alert("TO CHECK ELIGIBILITY PERSONAL DETAILS AND SALARY DETAILS ARE MANDATORY FIELDS");
		return false;
	}
	if(grossSalary==null || grossSalary==""){
		alert("TO CHECK ELIGIBILITY PERSONAL DETAILS AND SALARY DETAILS ARE MANDATORY FIELDS");
		return false;
	}
	}
	// for home loan validation (property details is present or not)
	var s=document.getElementById("productCategory").value;	
	
	if(s==1000000002){
		var homeLoanVal=document.getElementById("homeLoanValidation").value;			
		if(homeLoanVal=='N'){
			alert('PLEASE COMPLETE PROPERTY DETAILS FIRST');
			return false;
		}
		
	}
	var  dataString = "loanAmount=" + p + '&tenor='+ n +'&dob='+ dob +'&grossSalary='+ grossSalary +'&netSalary='+ netSalary +'&yrJob='+ yrJob +'&workingExp='+ workingExp +'&corparateAc='+ corparateAc +'&leadId='+leadId+'&facilityId='+facilityId;	    
    var trdata="";
	    $.ajax({  
 		type : "post",   
 		url : "calcEligibility.do",   
 		data : dataString ,
 		ontext: document.body,
 		dataType: "json",
 		success : function(response) { 		
 			//if($("#selectfacility").children("option:selected").val()=='6'){ 	
 			 if($("#productCategory").val()=='1000000001'){ 				 
 				//$('#creditTable').find('tbody').children('tr').remove(); 
 				$('#loanTable').find('tbody').children('tr').remove(); 
 			 }//else if($("#selectfacility").children("option:selected").val()=='23' || $("#selectfacility").children("option:selected").val()=='3'){ 
 				else if($("#productCategory").val()=='1000000002'){ 
 				$('#homeloanTable').find('tbody').children('tr').remove(); 
 				}else{
 					//$('#loanTable').find('tbody').children('tr').remove(); 
 					$('#creditTable').find('tbody').children('tr').remove(); 
 				}
            var count=0;
 			for (var i = 0, len = response.length; i < len; i++) { 			
			     var facilityObj = response[i];
			    
			    if(facilityObj.msg==""){			    	
			 	var radioId='';
			    // if($("#selectfacility").children("option:selected").val()=='6'){		
			    	 if($("#productCategory").val()=='1000000001'){
			    	// radioId= 'cc_radio_'+count;
			    		 radioId= 'l_radio_'+count;
				     }//else if($("#selectfacility").children("option:selected").val()=='23' || $("#selectfacility").children("option:selected").val()=='3'){
				    	 else if($("#productCategory").val()=='1000000002'){	 
				    	 radioId= 'hl_radio_'+count;
				     }
			    	 else{
				    	// radioId= 'l_radio_'+count;
			    		 radioId= 'cc_radio_'+count;
				     }
			 	
			    // if($("#selectfacility").children("option:selected").val()=='6'){
			    	 if($("#productCategory").val()=='1000000001'){ 
			    		 
			         	/* if(i%2 === 0)
			    		trdata='<tr style="background: #eee"><td>'+facilityObj.facilityRequestedBank+'</td> <td>'+facilityObj.schemeName+'</td><td>'+facilityObj.prePaymentFee+'</td><td>' +facilityObj.processingFee+'</td><td>' +facilityObj.renewalFee+'</td><td><input type="radio" id='+radioId+'/><label></td><td style="display:none"> <input type="hidden" value='+facilityObj.eligibilityId+'></td><td style="display:none"> <input type="hidden" value='+facilityObj.maxTenor+'></td><td style="display:none"> <input type="hidden" value='+facilityObj.minTenor+'></td><td style="display:none"> <input type="hidden" value='+facilityObj.maxLoanAmount+'></td><td style="display:none"> <input type="hidden" value='+facilityObj.minLoanAmount+'></td><td style="display:none"> <input type="hidden" value='+facilityObj.obligationAmt+'></td><td style="display:none"> <input type="hidden" value='+facilityObj.facilitySelectedId+'></td><td style="display:none"> <input type="hidden" value='+facilityObj.bankId+'></td><td style="display:none"> <input type="hidden" value='+facilityObj.facilityRequestedTenor+'></td><td style="display:none"> <input type="hidden" value='+facilityObj.facilityRequestedLoanAmount+'></td><td style="display:none">'+facilityObj.text+'></td></tr><tr style="display:none" class="extraCcRow"><td colspan="10">'+facilityObj.text+'</td></tr>';
			    	else
			    		trdata='<tr style="background: #c9c9c9"><td>'+facilityObj.facilityRequestedBank+'</td> <td>'+facilityObj.schemeName+'</td><td>'+facilityObj.prePaymentFee+'</td><td>' +facilityObj.processingFee+'</td><td>' +facilityObj.renewalFee+'</td><td><input type="radio" id='+radioId+'/><label></td><td style="display:none"> <input type="hidden" value='+facilityObj.eligibilityId+'></td><td style="display:none"> <input type="hidden" value='+facilityObj.maxTenor+'></td><td style="display:none"> <input type="hidden" value='+facilityObj.minTenor+'></td><td style="display:none"> <input type="hidden" value='+facilityObj.maxLoanAmount+'></td><td style="display:none"> <input type="hidden" value='+facilityObj.minLoanAmount+'></td><td style="display:none"> <input type="hidden" value='+facilityObj.obligationAmt+'></td><td style="display:none"> <input type="hidden" value='+facilityObj.facilitySelectedId+'></td><td style="display:none"> <input type="hidden" value='+facilityObj.bankId+'></td><td style="display:none"> <input type="hidden" value='+facilityObj.facilityRequestedTenor+'></td><td style="display:none"> <input type="hidden" value='+facilityObj.facilityRequestedLoanAmount+'></td><td style="display:none">'+facilityObj.text+'></td></tr><tr style="display:none" class="extraCcRow"><td colspan="10">'+facilityObj.text+'</td></tr>';
			    		
			     $('#creditTable').find('tbody').append(trdata); */
			    		 if(i%2 === 0)
					    	 	trdata='<tr style="background: #eee"><td>'+facilityObj.facilityRequestedBank+'</td><td>'+facilityObj.obligationAmt+'</td><td>'+facilityObj.facilityRequestedEmi+'</td><td>' +facilityObj.facilityRequestedTenor+'</td><td>'+facilityObj.facilityRequestedRateOfIntrest+'</td><td>' +facilityObj.processingFee+'</td><td>' +facilityObj.prePaymentFee+'</td><td>' +facilityObj.renewalFee+'</td><td><input type="radio" id='+radioId+'/><label></td><td style="display:none"> <input type="hidden" value='+facilityObj.eligibilityId+'></td><td style="display:none"> <input type="hidden" value='+facilityObj.maxTenor+'></td><td style="display:none"> <input type="hidden" value='+facilityObj.minTenor+'></td><td style="display:none"> <input type="hidden" value='+facilityObj.maxLoanAmount+'></td><td style="display:none"> <input type="hidden" value='+facilityObj.minLoanAmount+'></td><td style="display:none"> <input type="hidden" value='+facilityObj.facilityRequestedLoanAmount+'></td><td style="display:none"> <input type="hidden" value='+facilityObj.facilitySelectedId+'></td><td style="display:none"> <input type="hidden" value='+facilityObj.schemeName+'></td><td style="display:none"> <input type="hidden" value='+facilityObj.bankId+'></td><td style="display:none"> '+facilityObj.text+'></td></tr><tr class="extraLoanRow" style="display:none"><td colspan="10"> '+facilityObj.text+'</td></tr>';
					    	 else
					    	 	trdata='<tr style="background: #c9c9c9"><td>'+facilityObj.facilityRequestedBank+'</td> <td>'+facilityObj.obligationAmt+'</td><td>'+facilityObj.facilityRequestedEmi+'</td><td>' +facilityObj.facilityRequestedTenor+'</td><td>'+facilityObj.facilityRequestedRateOfIntrest+'</td><td>' +facilityObj.processingFee+'</td><td>' +facilityObj.prePaymentFee+'</td><td>' +facilityObj.renewalFee+'</td><td><input type="radio" id='+radioId+'/><label></td><td style="display:none"> <input type="hidden" value='+facilityObj.eligibilityId+'></td><td style="display:none"> <input type="hidden" value='+facilityObj.maxTenor+'></td><td style="display:none"> <input type="hidden" value='+facilityObj.minTenor+'></td><td style="display:none"> <input type="hidden" value='+facilityObj.maxLoanAmount+'></td><td style="display:none"> <input type="hidden" value='+facilityObj.minLoanAmount+'></td><td style="display:none"> <input type="hidden" value='+facilityObj.facilityRequestedLoanAmount+'></td><td style="display:none"> <input type="hidden" value='+facilityObj.facilitySelectedId+'></td><td style="display:none"> <input type="hidden" value='+facilityObj.schemeName+'></td><td style="display:none"> <input type="hidden" value='+facilityObj.bankId+'></td><td style="display:none"> '+facilityObj.text+'></td></tr><tr class="extraLoanRow" style="display:none"><td colspan="10"> '+facilityObj.text+'</td></tr>';
							 
					    	 $('#loanTable').find('tbody').append(trdata); 
			     
			     }//else if($("#selectfacility").children("option:selected").val()=='23' || $("#selectfacility").children("option:selected").val()=='3'){
			    	 else if($("#productCategory").val()=='1000000002'){
			    	 if(i%2 === 0)
				    	 	trdata='<tr style="background: #eee"><td>'+facilityObj.facilityRequestedBank+'</td> <td>'+facilityObj.obligationAmt+'</td><td>' +facilityObj.facilityRequestedTenor+'</td><td>'+facilityObj.facilityRequestedRateOfIntrest+'</td><td>' +facilityObj.fixedFloatingSemi+'</td><td>' +facilityObj.applicationFee+'</td><td>' +facilityObj.processingFee+'</td><td>' +facilityObj.prePaymentFee+'</td><td>'+facilityObj.facilityRequestedEmi+'</td><td> '+facilityObj.ltv+'</td><td><input type="radio" id='+radioId+'/><label></td><td style="display:none"> <input type="hidden" value='+facilityObj.eligibilityId+'></td><td style="display:none"> <input type="hidden" value='+facilityObj.maxTenor+'></td><td style="display:none"> <input type="hidden" value='+facilityObj.minTenor+'></td><td style="display:none"> <input type="hidden" value='+facilityObj.maxLoanAmount+'></td><td style="display:none"> <input type="hidden" value='+facilityObj.minLoanAmount+'></td><td style="display:none"> <input type="hidden" value='+facilityObj.facilityRequestedLoanAmount+'></td><td style="display:none"> <input type="hidden" value='+facilityObj.facilitySelectedId+'></td><td style="display:none"> <input type="hidden" value='+facilityObj.schemeName+'></td><td style="display:none"> <input type="hidden" value='+facilityObj.bankId+'></td><td style="display:none"> '+facilityObj.text+'></td></tr><tr class="extraLoanRow" style="display:none"><td colspan="10"> '+facilityObj.text+'</td></tr>';
				    	 else
				    	 	trdata='<tr style="background: #c9c9c9"><td>'+facilityObj.facilityRequestedBank+'</td> <td>'+facilityObj.obligationAmt+'</td><td>' +facilityObj.facilityRequestedTenor+'</td><td>'+facilityObj.facilityRequestedRateOfIntrest+'</td><td>' +facilityObj.fixedFloatingSemi+'</td><td>' +facilityObj.applicationFee+'</td><td>' +facilityObj.processingFee+'</td><td>' +facilityObj.prePaymentFee+'</td><td>'+facilityObj.facilityRequestedEmi+'</td><td> '+facilityObj.ltv+'</td><td><input type="radio" id='+radioId+'/><label></td><td style="display:none"> <input type="hidden" value='+facilityObj.eligibilityId+'></td><td style="display:none"> <input type="hidden" value='+facilityObj.maxTenor+'></td><td style="display:none"> <input type="hidden" value='+facilityObj.minTenor+'></td><td style="display:none"> <input type="hidden" value='+facilityObj.maxLoanAmount+'></td><td style="display:none"> <input type="hidden" value='+facilityObj.minLoanAmount+'></td><td style="display:none"> <input type="hidden" value='+facilityObj.facilityRequestedLoanAmount+'></td><td style="display:none"> <input type="hidden" value='+facilityObj.facilitySelectedId+'></td><td style="display:none"> <input type="hidden" value='+facilityObj.schemeName+'></td><td style="display:none"> <input type="hidden" value='+facilityObj.bankId+'></td><td style="display:none"> '+facilityObj.text+'></td></tr><tr class="extraLoanRow" style="display:none"><td colspan="10"> '+facilityObj.text+'</td></tr>';
				     $('#homeloanTable').find('tbody').append(trdata);
			     }
			     else{	
			    	 if(i%2 === 0)
				    		trdata='<tr style="background: #eee"><td>'+facilityObj.facilityRequestedBank+'</td> <td>'+facilityObj.schemeName+'</td><td>'+facilityObj.prePaymentFee+'</td><td>' +facilityObj.processingFee+'</td><td>' +facilityObj.renewalFee+'</td><td><input type="radio" id='+radioId+'/><label></td><td style="display:none"> <input type="hidden" value='+facilityObj.eligibilityId+'></td><td style="display:none"> <input type="hidden" value='+facilityObj.maxTenor+'></td><td style="display:none"> <input type="hidden" value='+facilityObj.minTenor+'></td><td style="display:none"> <input type="hidden" value='+facilityObj.maxLoanAmount+'></td><td style="display:none"> <input type="hidden" value='+facilityObj.minLoanAmount+'></td><td style="display:none"> <input type="hidden" value='+facilityObj.obligationAmt+'></td><td style="display:none"> <input type="hidden" value='+facilityObj.facilitySelectedId+'></td><td style="display:none"> <input type="hidden" value='+facilityObj.bankId+'></td><td style="display:none"> <input type="hidden" value='+facilityObj.facilityRequestedTenor+'></td><td style="display:none"> <input type="hidden" value='+facilityObj.facilityRequestedLoanAmount+'></td><td style="display:none">'+facilityObj.text+'></td></tr><tr style="display:none" class="extraCcRow"><td colspan="10">'+facilityObj.text+'</td></tr>';
				    	else
				    		trdata='<tr style="background: #c9c9c9"><td>'+facilityObj.facilityRequestedBank+'</td> <td>'+facilityObj.schemeName+'</td><td>'+facilityObj.prePaymentFee+'</td><td>' +facilityObj.processingFee+'</td><td>' +facilityObj.renewalFee+'</td><td><input type="radio" id='+radioId+'/><label></td><td style="display:none"> <input type="hidden" value='+facilityObj.eligibilityId+'></td><td style="display:none"> <input type="hidden" value='+facilityObj.maxTenor+'></td><td style="display:none"> <input type="hidden" value='+facilityObj.minTenor+'></td><td style="display:none"> <input type="hidden" value='+facilityObj.maxLoanAmount+'></td><td style="display:none"> <input type="hidden" value='+facilityObj.minLoanAmount+'></td><td style="display:none"> <input type="hidden" value='+facilityObj.obligationAmt+'></td><td style="display:none"> <input type="hidden" value='+facilityObj.facilitySelectedId+'></td><td style="display:none"> <input type="hidden" value='+facilityObj.bankId+'></td><td style="display:none"> <input type="hidden" value='+facilityObj.facilityRequestedTenor+'></td><td style="display:none"> <input type="hidden" value='+facilityObj.facilityRequestedLoanAmount+'></td><td style="display:none">'+facilityObj.text+'></td></tr><tr style="display:none" class="extraCcRow"><td colspan="10">'+facilityObj.text+'</td></tr>';
				    		
				     $('#creditTable').find('tbody').append(trdata);
			    	 
			    	 
			    	 		
			    	/*  if(i%2 === 0)
			    	 	trdata='<tr style="background: #eee"><td>'+facilityObj.facilityRequestedBank+'</td><td>'+facilityObj.obligationAmt+'</td><td>'+facilityObj.facilityRequestedEmi+'</td><td>' +facilityObj.facilityRequestedTenor+'</td><td>'+facilityObj.facilityRequestedRateOfIntrest+'</td><td>' +facilityObj.processingFee+'</td><td>' +facilityObj.prePaymentFee+'</td><td>' +facilityObj.renewalFee+'</td><td><input type="radio" id='+radioId+'/><label></td><td style="display:none"> <input type="hidden" value='+facilityObj.eligibilityId+'></td><td style="display:none"> <input type="hidden" value='+facilityObj.maxTenor+'></td><td style="display:none"> <input type="hidden" value='+facilityObj.minTenor+'></td><td style="display:none"> <input type="hidden" value='+facilityObj.maxLoanAmount+'></td><td style="display:none"> <input type="hidden" value='+facilityObj.minLoanAmount+'></td><td style="display:none"> <input type="hidden" value='+facilityObj.facilityRequestedLoanAmount+'></td><td style="display:none"> <input type="hidden" value='+facilityObj.facilitySelectedId+'></td><td style="display:none"> <input type="hidden" value='+facilityObj.schemeName+'></td><td style="display:none"> <input type="hidden" value='+facilityObj.bankId+'></td><td style="display:none"> '+facilityObj.text+'></td></tr><tr class="extraLoanRow" style="display:none"><td colspan="10"> '+facilityObj.text+'</td></tr>';
			    	 else
			    	 	trdata='<tr style="background: #c9c9c9"><td>'+facilityObj.facilityRequestedBank+'</td> <td>'+facilityObj.obligationAmt+'</td><td>'+facilityObj.facilityRequestedEmi+'</td><td>' +facilityObj.facilityRequestedTenor+'</td><td>'+facilityObj.facilityRequestedRateOfIntrest+'</td><td>' +facilityObj.processingFee+'</td><td>' +facilityObj.prePaymentFee+'</td><td>' +facilityObj.renewalFee+'</td><td><input type="radio" id='+radioId+'/><label></td><td style="display:none"> <input type="hidden" value='+facilityObj.eligibilityId+'></td><td style="display:none"> <input type="hidden" value='+facilityObj.maxTenor+'></td><td style="display:none"> <input type="hidden" value='+facilityObj.minTenor+'></td><td style="display:none"> <input type="hidden" value='+facilityObj.maxLoanAmount+'></td><td style="display:none"> <input type="hidden" value='+facilityObj.minLoanAmount+'></td><td style="display:none"> <input type="hidden" value='+facilityObj.facilityRequestedLoanAmount+'></td><td style="display:none"> <input type="hidden" value='+facilityObj.facilitySelectedId+'></td><td style="display:none"> <input type="hidden" value='+facilityObj.schemeName+'></td><td style="display:none"> <input type="hidden" value='+facilityObj.bankId+'></td><td style="display:none"> '+facilityObj.text+'></td></tr><tr class="extraLoanRow" style="display:none"><td colspan="10"> '+facilityObj.text+'</td></tr>';
					 
			    	 $('#loanTable').find('tbody').append(trdata);  */
			    	 
			     }
			    
		    	 
			     count++;
 			}else{
 				
 				document.getElementById("popupFlag").value='N';
 				alert(facilityObj.msg);
 				
 			}
 			}
 			var flag=document.getElementById("popupFlag").value;
 			if(flag!='N'){
 			//if($("#selectfacility").children("option:selected").val()=='6'){
 				if($("#productCategory").val()=='1000000001'){	
 				//$(".creditcardpop").fadeIn(80);
 					$(".loanpop").fadeIn(80);
 				}
 			//else if($("#selectfacility").children("option:selected").val()=='23' || $("#selectfacility").children("option:selected").val()=='3'){
 		    else if($("#productCategory").val()=='1000000002'){
 				$(".homeloanpop").fadeIn(80);
 			}
 			else{
 				//$(".loanpop").fadeIn(80);
 				$(".creditcardpop").fadeIn(80);
 				}	
 			}
 		}
	    }); 
	  
	
});

$(document).on("click",".close-popup",function(){
	$(this).parentsUntil(".mainpopup").parent(".mainpopup").fadeOut(120);
});


	$(".creditcardpop input[type='submit']").click(function(){
	$("#creditTable").find(".pop-selectedrow").next(".extraCcRow").clone().appendTo("#subtble");
	$("#facilityRequestedLoanAmount").val($("#creditTable tbody tr.pop-selectedrow td:nth-child(16) input").val());	
	$("#facilityRequestedSchemeName").val($("#creditTable tr.pop-selectedrow td:nth-child(2)").text());
	$("#facilityRequestedTenor").val($("#creditTable tbody tr.pop-selectedrow td:nth-child(15) input").val());	
	$("#facilityRequestedEmi").val('');
	$("#facilityRequestedRateOfIntrest").val('');
	
	document.getElementById('eligibilyCalId').value=$("#creditTable tbody tr.pop-selectedrow td:nth-child(7) input").val();
	document.getElementById('minLoanAmount').value=$("#creditTable tbody tr.pop-selectedrow td:nth-child(11) input").val();
	document.getElementById('maxLoanAmount').value=$("#creditTable tbody tr.pop-selectedrow td:nth-child(12) input").val();
	document.getElementById('minTenor').value=$("#creditTable tbody tr.pop-selectedrow td:nth-child(9) input").val();		
	document.getElementById('maxTenor').value=$("#creditTable tbody tr.pop-selectedrow td:nth-child(8) input").val();
	document.getElementById('limitLoanAmount').value=$("#creditTable tbody tr.pop-selectedrow td:nth-child(12) input").val();	
	document.getElementById('facilitySelectedId').value=$("#creditTable tbody tr.pop-selectedrow td:nth-child(13) input").val();
	document.getElementById('facilityRequestedBank').value=$("#creditTable tbody tr.pop-selectedrow td:nth-child(14) input").val();
	document.getElementById('termCond').value=$("#creditTable tbody tr.pop-selectedrow td:nth-child(17)").text();
	
	
 $(".creditcardpop").fadeOut(120);
});

$(".loanpop input[type='submit']").click(function(){
	//alert("cc-g");
	$("#loanTable").find(".pop-selectedrow").next(".extraLoanRow").clone().appendTo("#subtble");
	$(".extrainfo-loan").removeClass("extrainfo-loan-cc-hide");
	$(".extrainfo-cc").addClass("extrainfo-loan-cc-hide");
	$("#facilityRequestedLoanAmount").val($("#loanTable tr.pop-selectedrow td:nth-child(15) input").val());
	$("#facilityRequestedSchemeName").val();
	$("#facilityRequestedTenor").val($("#loanTable tr.pop-selectedrow td:nth-child(4)").text());
	/*  */
	$("#facilityRequestedRateOfIntrest").val($("#loanTable tr.pop-selectedrow td:nth-child(5)").text());
	$("#facilityRequestedEmi").val($("#loanTable tr.pop-selectedrow td:nth-child(3)").text());
	
	document.getElementById('eligibilyCalId').value=$("#loanTable tbody tr.pop-selectedrow td:nth-child(10) input").val();		
 	document.getElementById('minLoanAmount').value=$("#loanTable tbody tr.pop-selectedrow td:nth-child(14) input").val(); 	
	document.getElementById('maxLoanAmount').value=$("#loanTable tbody tr.pop-selectedrow td:nth-child(2) ").text();	
	document.getElementById('minTenor').value=$("#loanTable tbody tr.pop-selectedrow td:nth-child(12) input").val();			
	document.getElementById('maxTenor').value=$("#loanTable tbody tr.pop-selectedrow td:nth-child(11) input").val();
	document.getElementById('limitLoanAmount').value=$("#loanTable tbody tr.pop-selectedrow td:nth-child(2) ").text();	
	document.getElementById('facilitySelectedId').value=$("#loanTable tbody tr.pop-selectedrow td:nth-child(16) input").val();  
	document.getElementById('facilityRequestedBank').value=$("#loanTable tbody tr.pop-selectedrow td:nth-child(18) input").val();
	document.getElementById('termCond').value=$("#loanTable tbody tr.pop-selectedrow td:nth-child(19)").text();
	$(".loanpop").fadeOut(120);
	});
	
$(".homeloanpop input[type='submit']").click(function(){
	$("#homeloanTable").find(".pop-selectedrow").next(".extraLoanRow").clone().appendTo("#subtble");
	$(".extrainfo-loan").removeClass("extrainfo-loan-cc-hide");
	$(".extrainfo-cc").addClass("extrainfo-loan-cc-hide");
	$("#facilityRequestedLoanAmount").val($("#homeloanTable tr.pop-selectedrow td:nth-child(17) input").val());
	$("#facilityRequestedSchemeName").val();
	$("#facilityRequestedTenor").val($("#homeloanTable tr.pop-selectedrow td:nth-child(3)").text());
	$("#facilityRequestedRateOfIntrest").val($("#homeloanTable tr.pop-selectedrow td:nth-child(4)").text());
	$("#facilityRequestedEmi").val($("#homeloanTable tr.pop-selectedrow td:nth-child(9)").text());			
	document.getElementById('eligibilyCalId').value=$("#homeloanTable tbody tr.pop-selectedrow td:nth-child(12) input").val();		
 	document.getElementById('minLoanAmount').value=$("#homeloanTable tbody tr.pop-selectedrow td:nth-child(16) input").val(); 	
	document.getElementById('maxLoanAmount').value=$("#homeloanTable tbody tr.pop-selectedrow td:nth-child(2) ").text();	
	document.getElementById('minTenor').value=$("#homeloanTable tbody tr.pop-selectedrow td:nth-child(14) input").val();			
	document.getElementById('maxTenor').value=$("#homeloanTable tbody tr.pop-selectedrow td:nth-child(13) input").val();
	document.getElementById('limitLoanAmount').value=$("#homeloanTable tbody tr.pop-selectedrow td:nth-child(2) ").text();	
	document.getElementById('facilitySelectedId').value=$("#homeloanTable tbody tr.pop-selectedrow td:nth-child(18) input").val();  
	document.getElementById('facilityRequestedBank').value=$("#homeloanTable tbody tr.pop-selectedrow td:nth-child(20) input").val();
	document.getElementById('termCond').value=$("#homeloanTable tbody tr.pop-selectedrow td:nth-child(21)").text();
	$(".homeloanpop").fadeOut(120);
	});



$(document).on("click",".creditcardpop table tr input[type='radio']+label", function(){

	$(this).attr("for", $(this).prev("input[type='radio']").attr("id")).addClass("dsd"); 
	});
	
$(document).on("click",".loanpop table tr input[type='radio']+label", function(){

	$(this).attr("for", $(this).prev("input[type='radio']").attr("id")).addClass("dsd"); 
	});
	
$(document).on("click",".homeloanpop table tr input[type='radio']+label", function(){

	$(this).attr("for", $(this).prev("input[type='radio']").attr("id")).addClass("dsd"); 
	});

$(document).on("click",".creditcardpop table tr",function(){
	//alert("df");/	
	$(".creditcardpop table tr input[type='radio']").removeAttr("checked");
	$(".creditcardpop table tr").removeClass("pop-selectedrow");	
//	$("#creditTable tr td:nth-child(3)").addClass("greybg");
	
	
	$(this).find("input[type='radio']").prop("checked","true");
	$(this).addClass("pop-selectedrow");
//	$(this).find("td:nth-child(3)").removeClass("greybg");
	
	});
	
$(".close-white, .loanpop .btn.btn-primary, .creditcardpop .btn.btn-primary").click(function(){
		//alert("ghg");
		$("#loanTable .extrainfo-loan, #creditTable .extrainfo-cc").remove();
		
	});
//$(".extrainfo-loan").clone().appendTo("#loanTable tbody").removeClass("extrainfo-loan-cc-hide");
$(document).on("click",".loanpop table tr input[type='radio']",function(){
	$(".extraLoanRow").css("display","none");
$(this).parent("td").parent("tr").next(".extraLoanRow").css("display","table-row");
});
$(document).on("click",".homeloanpop table tr input[type='radio']",function(){
	$(".extraLoanRow").css("display","none");
$(this).parent("td").parent("tr").next(".extraLoanRow").css("display","table-row");
});

$(document).on("click",".creditcardpop table tr input[type='radio']",function(){
	$(".extraCcRow").css("display","none");
$(this).parent("td").parent("tr").next(".extraCcRow").css("display","table-row");
});


$(document).on("click",".loanpop table tr input[type='radio']",function(){
	
	$(".loanpop table tr input[type='radio']").removeAttr("checked");
	$(".loanpop table tr").removeClass("pop-selectedrow");	
	//$("#loanTable tr td:nth-child(3)").addClass("greybg");
	$(this).prop("checked","true");
	$(this).parent("td").parent("tr").addClass("pop-selectedrow");
	//$(this).find("td:nth-child(3), td:nth-child(4), td:nth-child(5)").removeClass("greybg");	
    
	});
	
$(document).on("click",".homeloanpop table tr input[type='radio']",function(){
	
	$(".homeloanpop table tr input[type='radio']").removeAttr("checked");
	$(".homeloanpop table tr").removeClass("pop-selectedrow");	
	//$("#homeloanTable tr td:nth-child(3)").addClass("greybg");
	$(this).prop("checked","true");
	$(this).parent("td").parent("tr").addClass("pop-selectedrow");
	//$(this).find("td:nth-child(3), td:nth-child(4), td:nth-child(5)").removeClass("greybg");	
    
	});
	function hideText(val){
		var productId=val.value;
		$(".extraCcRow, .extraLoanRow").css("display","none");
		$("#facilityRequestedEmi").val('');
		$("#facilityRequestedRateOfIntrest").val('');
		$("#maxLoanAmount").val('');
		$("#termCond").val("");
		$("#term").text("");
		document.getElementById("facilityRequestedLoanAmount").value="";
		document.getElementById("facilityRequestedTenor").value="";
		document.getElementById('minLoanAmount').value="";
		document.getElementById('minTenor').value="";		
		document.getElementById('maxTenor').value="";
		document.getElementById('limitLoanAmount').value="";
		document.getElementById('facilityRequestedSchemeName').value="";
		document.getElementById('facilityRequestedBank').value=-1;
		$("#facilityRequestedReCalEmi").val('');
		//------add by deepak on 05 march-2016-------
		$.ajax({  
			type : "post",   
			url : "getProductCategory.do",   
			data : "productId=" +productId,
			ontext: document.body,			
			success : function(response) {			
			   document.getElementById('productCategory').value=response;
	  	     }			
		});
		
			
	}
	
	
	
	$(".select-tooltip").each(function(){
		//var selttip = $(this);
		$(this).text($(this).prev("span").find("select").find("option:selected").text());
		$(".sel-ttip").mouseenter(function(){		
		
		$(this).next(".select-tooltip").fadeIn();
	
		});
		$(".sel-ttip").mouseleave(function(){		
		//alert("leave");
		$(this).next(".select-tooltip").fadeOut();
		});

	});
	 
	 $(".add-list-existingFacility").click(function(){
	 $(".select-tooltip").each(function(){
		//var selttip = $(this);
		$(this).text($(this).prev("span").find("select").find("option:selected").text());
		$(".sel-ttip").mouseenter(function(){		
		
		$(this).next(".select-tooltip").fadeIn();
	
		});
		$(".sel-ttip").mouseleave(function(){		
		//alert("leave");
		$(this).next(".select-tooltip").fadeOut();
		});

	});
	 });
	
	 $(document).on("click", ".add-list-prodreffereal", function(){
		$(this).parents(".prod-reff").next(".row").find("table tbody tr td .sel-ttip").mouseenter(function(){
		//alert("enter");
		$(this).next(".select-tooltip").text($(this).find("option:selected").text()).fadeIn();
		});
		
		$(this).parents(".prod-reff").next(".row").find("table tbody tr td .sel-ttip").mouseleave(function(){		
		$(this).next(".select-tooltip").fadeOut();
		});
		
	 });
	 

$(document).on("click", ".add-list-existingFacility", function(){
		
		$(this).parents(".exis-fac-row").next(".row").find("table tbody tr td .sel-ttip").mouseenter(function(){
		//alert("enter");
		$(this).next(".select-tooltip").text($(this).find("option:selected").text()).fadeIn();
		});
		
		$(this).parents(".exis-fac-row").next(".row").find("table tbody tr td .sel-ttip").mouseleave(function(){		
		$(this).next(".select-tooltip").fadeOut();
		});
		
	 });
	/* $(document).on("mouseup", ".add-list-prodreffereal", function(){

	 //alert("sdf");
	 
	alert($(this).parents(".prod-reff").next(".row").find("table tbody tr td .sel-ttip").length);
	 });*/
$(document).ready(function(){
	$(".sel-ttip").mouseenter(function(){		
		$(this).next(".select-tooltip").fadeIn();
		var ttt1 = $(this).find("select").find("option:selected").text();
		$(this).next(".select-tooltip").text(ttt1);
		});
		$(".select-tooltip").each(function(){	
		var seltt = $(this);
		var ttt = seltt.prev("span").find("select").find("option:selected").text();
		seltt.text(ttt);
		
		$(".sel-ttip").find("select").change(function(){
		seltt.text(seltt.prev("span").find("select").find("option:selected").text());
		});
		$(".sel-ttip").mouseenter(function(){	
		$(this).next(".select-tooltip").fadeIn();
		});
		$(".sel-ttip").mouseleave(function(){	
		$(this).next(".select-tooltip").fadeOut();
		});
	});
	
	});
	 
$("#facilityRequestedBank").mousedown(function(event){
		//alert("df")
		$(this).find("option").css("display","none")

		})
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
<style>
.topstaticinfo .listOne {
	min-height: 28px !important;
	height: 100% !important;
}

.topstaticinfo {
	height: 100%;
}
</style>