<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<link rel="stylesheet" href="resources/css/base/jquery.ui.all.css">

<link href="resources/css/style.css" rel="stylesheet">
<link href="resources/css/bootstrap.css" rel="stylesheet">
<link href="resources/css/bootstrap-theme.css" rel="stylesheet">
<link rel="stylesheet" href="resources/css/pickmeup.css" type="text/css" />
<link rel="stylesheet" href="resources/css/base/jquery.ui.all.css">

<link rel="stylesheet" type="text/css" href="resources/css/bootstrap-clockpicker.min.css">

<!-- <script type="text/javascript" src="resources/js/jquery.min.js"></script>
<script type="text/javascript" src="resources/js/bootstrap.min.js"></script> -->


<script src="resources/js/globalValidation.js" type="text/javascript"></script>
<!-- <script src="resources/js/jquery.js" type="text/javascript"></script> -->
<script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
<script src="resources/js/ui/jquery.ui.core.js"></script>
<script src="resources/js/ui/jquery.ui.datepicker.js"></script>
<!-- <script src="resources/js/ui/jquery.ui.widget.js"></script> -->
<script src="resources/js/ui/jquery-ui-timepicker-addon.js"></script>
<script src="resources/js/ui/script.js"></script>
<style>
.searcharea1 {
	padding: 17px 0 0px 0px !important;
}
#leadCode+button {
	padding-bottom: 3px !important;
}

.leadstatus label {
	margin: 9px 0 0 0 !important;
	line-height: 13px;
}

.remarks-li label {
	margin: -5px 0 0 0 !important;
}
ul#setinwidth li:nth-of-type(3n-2) label{
  padding-left: 0px !important;
}
ul#setinwidth li label{
	padding-left:45px;
	
}
ul.leadStatusContactesclate li:nth-of-type(3n-2) label{
  padding-left: 0px !important;
}
ul.leadStatusContactesclate li label{
	padding-left:45px !important;
	
}
</style>
<script>
	$(window).load(function() {
		var s = document.getElementById("error").value;
		if (s == "next") {
			alert("You are already at last Lead");
		} else {
			alert("You are already at First Lead");
		}
	});
</script>
<script>
	/* function getSelectedLeadState() {
		var disposition = document.getElementById('actionId').value;
		$.ajax({
			async : true,
			type : "post",
			url : "getLeadStateValue.do",
			context : document.body,
			data : "disposition=" + disposition,
			success : function(response) {
				$("#disposition").find("option:selected").text(response);
			}
		});
	} */
	
	
	function getNextActionName(val) {		
		var sendData = {
			    "idColumnName" : "NEXTACTION_ID",
			    "valueColumnName" :"NEXTACTION_NAME",			    
			    "dependentTableName" :"QM_CASE_NEXT_ACTION",			    			    
			    "crossTableName" :"QM_CASE_NEXT_ACTION",			    
			    "crossTableDependentId" :"NEXTACTION_ID",
			    "crossTableMasterId" :"ACTION_ID",
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
				
				var select = $('[name="followupAction"]');
				
				select.find('option').remove();
				$('<option>').val("-1").text("Select").appendTo(select);
				$($.parseJSON(response)).map(function() {
					$('<option>').val(this.id).text(this.value).appendTo(select);
				}); 
			}
		});
	}
     
	/* $(function() {
	    $("#datepicker11").click(function() {
	        $(this).datepicker().datepicker( "show" );
	    });
	}); */
	
	
	$('[data-toggle=datepicker]').each(function() {
		  var target = $(this).data('target-name');
		  var t = $('input[name=' + target + ']');
		  t.datepicker({
		    dateFormat: 'yy-mm-dd',
		  });
		  $(this).on("click", function() {
		    t.datepicker("show");
		  });
		});
	
	
	 $(function() {
		 $( "#datepicker1" ).datepicker(); 
		/* $("#datepicker").datepicker({}); */
	}); 

	function enableDatePicker() {
		
		$(".docDate1").datepicker({
	        changeMonth : true,
			changeYear : true,
			minDate : 0,
			dateFormat : 'dd-mm-yy'
		});
	}
	
	function validateActionDateOnFollowupDate(){
		
		var date1 = document.getElementById("datepicker11").value; //'01-02-2016';  /*february 25th*/
		var date2 = document.getElementById("datepicker10").value;  //'01-01-2018';  /*february 26th*/

		if ($.datepicker.parseDate('dd-mm-yy', date2) > $.datepicker.parseDate('dd-mm-yy', date1)) {
		       alert('ACTION DATE MUST BE LESS THAN FOLLOWUP DATE');
		       document.getElementById("datepicker10").value = ''; 
		}
		
	}
	
	
</script>

<script type="text/javascript">
	function openHelp(type) {
		document.getElementById("helpType").value = type;
		
		if(type === "convertToCustomer"){
			document.getElementById("help").action = "convertToCustomer.do";	
		}else{
			document.getElementById("help").action = "help.do";
		}		
		document.getElementById("help").submit();
	}
</script>
<div class="container-fluid padding-left-right">
	<form action="help.do" id="help" name="help">
		<input type="hidden" id="helpType" name="helpType">
	</form>
	<div
		class="padding-top-container contact-top col-lg-12 col-md-12 col-sm-12 col-xs-12 fourtabs-toppadding">

		<div class="row contactList" id="collapse-div">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 topstaticinfo" >
				<ul class="listOne">
					<li><label><spring:message code="staticHeader.leadId"/></label></li>
					<li><span>${leadHeaderDetail.leadCode}</span></li>
					<li><label><spring:message code="staticHeader.generationDate"/></label></li>
					<li><span>${leadHeaderDetail.generationDate}</span></li>
					<li><label><spring:message code="staticHeader.allocatedTo"/></label></li>
					<li><span>${leadHeaderDetail.allocatedTo}</span></li>
					<li><label><spring:message code="staticHeader.product"/></label></li>
					<li><span>${leadHeaderDetail.queue}</span></li>
				</ul>
				<ul class="listOne">
					<li><label><spring:message code="staticHeader.queue"/></label></li>
					<li><span>${leadHeaderDetail.queue}</span></li>
					<li><label><spring:message code="staticHeader.subQueue"/></label></li>
					<li><span>${leadHeaderDetail.subQueue}</span></li>
					<li><label><spring:message code="staticHeader.source"/></label></li>
					<li><span>${leadHeaderDetail.source }</span></li>
					<li><label><spring:message code="staticHeader.campaign"/></label></li>
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
					<li><label><spring:message code="staticHeader.custName"/></label></li>
					<li><span
						style="line-height: 15px !important; margin-top: -10px;">${leadHeaderDetail.customerName}</span></li>
					<li><label><spring:message code="staticHeader.custMobile"/> </label></li>
					<li><span>${leadHeaderDetail.customerMobile}</span></li>
					<li><label> <spring:message code="staticHeader.custCity"/></label></li>
					<li><span>${leadHeaderDetail.customerResCity}</span></li>
					<li class="Constitution-margin"><label><spring:message code="staticHeader.constitution"/> </label></li>
					<li class="Constitution-margin"><span
						style="line-height: 13px !important;">${leadHeaderDetail.occuType}</span></li>
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
			<div
				class="col-lg-6 col-md-6 col-sm-3 col-xs-12 taggs tags-margin">
				<label class="col-lg-2 col-md-3 col-sm-3 col-xs-6"
					style="left: -6px;"><spring:message code="staticHeader.tagA"/></label>
				<!--  
				<textarea type="text"
					class="textwidth col-lg-9 col-md-9 col-sm-8 col-xs-6 taga_textarea" disabled>${leadHeaderDetail.tagA}</textarea>-->
				<div class="col-lg-9 col-md-6 col-sm-6 col-xs-6  padding-left-img">
					<a href="" data-toggle="modal" data-target="#myModal1"
						data-backdrop="static" data-keyboard="false" ><img
						src="./image/edit_img.png" alt="" class="icon-image"></a> <input
						type="text" value="${leadHeaderDetail.tagA}"
						class="col-lg-12 col-md-12 col-sm-12 col-xs-12" />
				</div>
			</div>
			<div
				class="col-lg-6 col-md-6 col-sm-3 col-xs-12 taggs tags-margin">
				<label class="col-lg-2 col-md-3 col-sm-3 col-xs-6"
					style="left: -11px;"><spring:message code="staticHeader.tagB"/></label>
				<!--  <textarea
					 class="textwidth col-lg-9 col-md-9 col-sm-8 col-xs-6 tagb_textarea" disabled>${leadHeaderDetail.tagB}</textarea>
					 -->
				<div class="col-lg-9 col-md-6 col-sm-6 col-xs-6  padding-left-img">
					<a href="" data-toggle="modal" data-target="#myModal2"
						data-backdrop="static" data-keyboard="false" ><img
						src="./image/edit_img.png" alt="" class="icon-image"></a> <input
						type="text" value="${leadHeaderDetail.tagB}"
						class="col-lg-12 col-md-12 col-sm-12 col-xs-12" />
				</div>
			</div>
		</div>
		<form class="form-group" method="post" id="leadAction"name="leadAction"
			action="<c:url value="${requestScope.contextPath}/leadAction.do"/>">
			
			
			<div class="row buttonHeading form-group">
				<div class="col-lg-8 col-md-8 col-sm-4 col-xs-4">
					<h2 class="heading heading-topmargin" id="leadstatus">Lead
						Status</h2>
				</div>
				<div class="col-lg-4 col-md-4 col-sm-8 col-xs-8 btnPosition">
					<a class=" btn-primary toggle-list top-it"> <span
						class="toggle-icon minus-icon1 "></span>
					</a>
					<button type="button" class="btn btn-primary set-paddingandheight"
						onclick="javascript:openHelp('refer');">Refer</button>
					<button type="button" class="btn btn-primary set-paddingandheight"
						onclick="javascript:openHelp('allocate');">Co-Allocate</button>
					<button type="button" class="btn btn-primary set-paddingandheight"
						onclick="javascript:openHelp('escalate');">Escalate</button>					
				</div>
			</div>
			
			
			<div
				class="row contactList leadstatus leadsta_resp padding-top-contactlist set-it-four">
				<ul class="leadStatusContact width-into-three" id="setinwidth">
					
					
					<li style="position: relative" class=""><label><spring:message code="label.disposition"/></label>
						<span class="sel-ttip"><select class="span_6_of_12 fl validField"
							name="actionId" id="actionId" onchange="getNextActionName(this)">
								<c:forEach var="leadstat"
									items="${masterDetail.caseActionMaster}">
									<option value="${leadstat.actionId}">${leadstat.actionName}</option>
								</c:forEach>
						</select></span> <span class="select-tooltip"
						style="margin-top: -15px; background: #fff; border: solid 1px #ccc"></span>
					</li>
					
					<li style="position: relative;"><label><spring:message code="label.actionDate"/>
						</label> <input type="text" class="docDate1" id="datepicker10"
						placeholder="Follow Up Date" name="actionDate" > <span
						class="dobCalc glyphicon glyphicon-calendar" for="datepicker"
						aria-hidden="true"
						style="position: absolute; top: 3px; right: 39px !important;"></span>
					</li>
					
					<li>
						<div>
							<label><spring:message code="label.actionTime"/></label> <input type="text"
								id="actionTime" name="actionTime" placeholder="Follow Up Time"
								class="clockpicker pull-left" />
						</div>
					</li>
					
										
					<li style="position: relative"><label><spring:message code="label.followUpAction"/></label> <span
						class="sel-ttip"><select name="followupAction"
							id="followupAction" draggable="false">
							<option value="-1">select</option>
								<%-- <c:forEach var="disposition"
									items="${masterDetail.dispositionMaster}">
									<option value="${disposition.actionStage}">${disposition.actionStage}</option>
								</c:forEach> --%>
						</select></span> <span class="select-tooltip"
						style="margin-top: -15px; background: #fff; border: solid 1px #ccc"></span>
					</li>
					
					
					<li style="position: relative;"><label><spring:message code="label.followUpDate"/>
						</label> <input type="text" class="docDate1" id="datepicker11" onchange="changePotential();validateActionDateOnFollowupDate();"
						placeholder="Follow Up Date" name="followupDate" readonly="readonly"> <span
						class="dobCalc glyphicon glyphicon-calendar" for="datepicker"
						aria-hidden="true"
						style="position: absolute; top: 3px; right: 39px !important;"></span>
					</li>
					
					<li>
						<div>
							<label><spring:message code="label.followUpTime"/></label> <input type="text"
								id="followupTime" name="followupTime" placeholder="Follow Up Time"
								class="clockpicker pull-left" />
						</div>
					</li>
					
					<%-- <li><label><spring:message code="label.lastUpdateDate"/></label> <input type="text"
						value="${contactDetail.lastUpdateDate}" readonly="readonly" /></li> --%>
						

					<li style="position: relative" class=""><label><spring:message code="label.leadStage"/></label>
						<span class="sel-ttip"><select
						name="leadStage" id="leadStage" >
						<option value="1000000007">INTERESTED</option>
						<c:forEach var="obj" items="${masterDetail.stageMaster}">
							<option value="${obj.stageId }">${obj.stageName}</option>
						</c:forEach>
					</select></span> <span class="select-tooltip"
						style="margin-top: -15px; background: #fff; border: solid 1px #ccc"></span>
					</li>
					<li style="position: relative" class=""><label><spring:message code="label.purpose"/></label>
						<span class="sel-ttip"><select name="purpose" id="purpose" class="">
						<option value="-1">select</option>
						<c:forEach var="purpose" items="${masterDetail.purposeEntity }">
							<option value="${purpose.purposeId}" ${purpose.purposeId == "1000000001" ? "selected" : '' } >${purpose.purposeName}</option>
						</c:forEach>
					</select></span> <span class="select-tooltip"
						style="margin-top: -15px; background: #fff; border: solid 1px #ccc"></span>
					</li>
					
					<li style="position: relative" class=""><label><spring:message code="label.reason"/></label>
						<span class="sel-ttip"><select
					name="rejectReason" id="rejectReason" class="">
					<option value="-1">select</option>
					<c:forEach var="reject"
						items="${masterDetail.rejectreasonEntity }">
						<option value="${reject.rejectreasonId}">${reject.rejectreasonName}</option>
					</c:forEach>
				</select></span> <span class="select-tooltip"
						style="margin-top: -15px; background: #fff; border: solid 1px #ccc"></span>
					</li>
																
					<li style="position: relative" class=""><label><spring:message code="label.potential"/></label>
						<span class="sel-ttip"><select  
					name="potential" id="potential" class="validField"  disabled="disabled" >
					<option value="1000000002" >WARM</option>
					<option value="1000000001" >HOT</option>
					<option value="1000000003" >COLD</option>
					<%-- <option value="-1">select</option>
					<c:forEach var="sub"
						items="${masterDetail.subQueueMaster }">
						<option value="${sub.subQueueId}">${sub.subQueue}</option>
					</c:forEach> --%>
				</select></span>
				
				<!-- <input type="hidden" name="potential" id="hdn_test"  value=""> -->
				 <span class="select-tooltip"
						style="margin-top: -15px; background: #fff; border: solid 1px #ccc"></span>
					</li>	
				
					
										
					
					<li class="remarks-li" style="position: relative;"><label><spring:message code="label.remarks"/></label>
						<!--  <textarea name="remarks" class="contact_remarks"></textarea>-->
						<a href="" data-toggle="modal" data-target="#myModal3"
						data-backdrop="static" data-keyboard="false" onclick="setData()"><img
							src="./image/edit_img.png" alt=""
							class="icon-image icon-new-image"></a> <input style="float:left;height:28px !important" type="text"
						id="remarks" value=""
						class="col-lg-3 col-md-3 col-sm-3 col-xs-3" name="remarks" />
					</li>
					
					
					
					<!-- <li style="position: relative;">	
						<button type="button" class="btn btn-primary set-paddingandheight"
						onclick="javascript:openHelp('convertToCustomer');">Convert To Customer</button>
					</li> -->
				</ul>
			</div>
		</form>
		<div class="row buttonHeading">
			<div class="col-lg-11 col-md-11 col-sm-11 col-xs-11" id="content">

				<ul id="tabs" class="nav nav-tabs contact_tabs" data-tabs="tabs">
					<li class="active"><a class="heading" href="#actionhis"
						data-toggle="tab">Action History</a></li>
					<li><a class="heading" href="#escalationhis" data-toggle="tab">Escalation/Referral
							History</a></li>
					<li><a class="heading" href="#coallocationHis"
						data-toggle="tab">Allocation History </a></li>
				</ul>

			</div>
			<div class="col-lg-1 col-md-1 col-sm-1 col-xs-1 btnPosition">
				<a class="btn-primary  toggle-list top-it"> <span
					class="toggle-icon minus-icon1 "></span>
				</a>
			</div>
		</div>
		<div class="row contactList leadstatus paddibg-top-tabs">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" id="content">
				<div id="my-tab-content" class="tab-content">
					<div class="tab-pane active" id="actionhis">
						<div class="bs-example">
							<table class="table table-striped">
								<thead>
									<tr>
										<th>#</th>
										<th>Action</th>										
										<th>Action DateTime</th>
										<th>Followup Action</th>
										<th>Followup DateTime</th>
										<th>LeadStage</th>
										<th>Potential</th>	
										<!-- <th>Purpose</th>	
										<th>Reject Reason</th> -->										
										<th>Remarks</th>										
										<th>Reject Reason</th>
										<th>Purpose</th>										
										<th>Action By</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="action"
										items="${contactDetail.caseActionHistory}" varStatus="index">
										<tr>
											<td>${index.index +1}</td>
											<td>${action.action}</td>											
											<td>${action.actionDate}</td>
											<td>${action.followupAction}</td>
											<td>${action.followupActionDate}</td>
											<td>${action.leadStage}</td>
											<td>${action.potential}</td>
											<%-- <td>${action.potential}</td>
											<td>${action.potential}</td> --%>
											<td>${action.remarks}</td>
											<td>${action.rejectReason}</td>
											<td>${action.purpose}</td>
											<td>${action.actionBy}</td>																				
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
					<div class="tab-pane" id="escalationhis">
						<div class="bs-example">
							<table class="table table-striped">
								<thead>
									<tr>
										<th>#</th>
										<th>Action</th>
										<th>Initiated By</th>
										<th>Initiated To</th>
										<th>Initiated DateTime</th>
										<th>Initial Remarks</th>
										<th>ResolvedBy</th>
										<th>Resolved DateTime</th>
										<th>Resolution Remarks</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="escalation"
										items="${contactDetail.caseEscalationHistory}"
										varStatus="index">
										<tr>
											<td>${index.index +1}</td>
											<td>${escalation.actionName}</td>
											<td>${escalation.initiatedBy}</td>
											<td>${escalation.initiatedTo}</td>
											<td>${escalation.initiatedDateTime}</td>
											<td>${escalation.initialRemarks}</td>
											<td>${escalation.resolvedBy}</td>
											<td>${escalation.resolveDtTime}</td>
											<td>${escalation.resolvedRemarks}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
					<!-- For co-allocate 30Nov-->
					<div class="tab-pane" id="coallocationHis">
						<div class="bs-example">
							<table class="table table-striped">
								<thead>
									<tr>
										<th>#</th>
										<th>Action</th>
										<th>Allocated By</th>
										<th>Allocated To</th>
										<th>Allocated Date</th>
										<th>Remarks</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="allocation"
										items="${contactDetail.caseAllocationHistory}"
										varStatus="index">
										<tr>
											<td>${index.index +1}</td>
											<td>${allocation.action}</td>
											<td>${allocation.allocatedBy}</td>
											<td>${allocation.allocatedTo}</td>
											<td>${allocation.allocatedDate}</td>
											<td>${allocation.remark}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
					<!-- End extra -->
				</div>
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
				<textarea class="modal-textarea" name="remark" ></textarea>
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
				<textarea class="modal-textarea" name="remark"></textarea>
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
<div class="modal fade" id="myModal3" role="dialog"
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
				<textarea class="modal-textarea" name="remark" id="modalRemark"></textarea>
			</div>
			<div class="modal-footer model-border-none">
				<button type="button" class="btn btn-default" data-dismiss="modal"
					onclick="setData1()">Close</button>
			</div>
		</div>

	</div>
</div>
<!-- Modal -->
<script>
	//alert("dxsf");
	jQuery(document).ready(function($) {
		$('#tabs').tab();
		enableDatePicker();
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

	$('.savee-icon').parent('.subheader-btn').click(function() {
		var s = validate();

		if (s) {
			document.getElementById("leadAction").action = "leadAction.do";
			document.getElementById("leadAction").submit();
			alert("CONTACT UPDATED");
		}
	});
	$('.saveandexit-icon').parent('.subheader-btn').click(function() {
		var s = validate();
		if (s) {
			document.getElementById("leadAction").action = "seContact.do";
			document.getElementById("leadAction").submit();
			alert("CONTACT UPDATED");
		}
	});

	$('.saveandexit-icon').parent('.subheader-btn').hover(function() {
		//alert('1');
		$(this).children('.saveandexit-tooltip').fadeToggle(150);
	});
	$('.usermenu-mobile').click(function() {
		//	alert('fs');
		$('.header-rightpanel').animate({
			"top" : "0"
		});
	});
	$('.close-header-rightpanel').click(function() {
		$('.header-rightpanel').animate({
			"top" : "-260px"
		});
	});
	$(document).ready(function() {
		$(".topMenu li:nth-child(1) a").addClass("active");

		var disposition = document.getElementById('actionId').value;
		$.ajax({
			async : true,
			type : "post",
			url : "getLeadStateValue.do",
			context : document.body,
			data : "disposition=" + disposition,
			success : function(response) {
				$("#disposition").find("option:selected").text(response);
			}
		});

	});

	$(document).ready(
			function() {
				$(".sel-ttip").mouseenter(
						function() {
							$(this).next(".select-tooltip").fadeIn();
							var ttt1 = $(this).find("select").find(
									"option:selected").text();
							$(this).next(".select-tooltip").text(ttt1);
						});
				$(".select-tooltip").each(
						function() {
							var seltt = $(this);
							var ttt = seltt.prev("span").find("select").find(
									"option:selected").text();
							seltt.text(ttt);

							$(".sel-ttip").find("select").change(
									function() {
										seltt.text(seltt.prev("span").find(
												"select").find(
												"option:selected").text());
									});
							$(".sel-ttip").mouseenter(function() {
								$(this).next(".select-tooltip").fadeIn();
							});
							$(".sel-ttip").mouseleave(function() {
								$(this).next(".select-tooltip").fadeOut();
							});
						});

			});
	$(document).ready(function() {
	    var date = new Date();
        var hours=date.getHours();    
        var hours1 = (hours+24-2)%24; 
        var mid='am';
        if(hours1==0){ 
        hours=12;
        }
        else if(hours>12)
        {
        hours=hours%12;
        mid='pm';
        }
        
        
        var minutes=date.getMinutes();    
        var day = date.getDate();
	    var month = date.getMonth() + 1;
	    var year = date.getFullYear();

	    if (month < 10) month = "0" + month;
	    if (day < 10) day = "0" + day;

	    var today = day + "-" + month + "-" + year;  
	    var time = hours + ":" + minutes+""+mid;  	 
	    $("#datepicker10").attr("value", today);
	    $("#actionTime").attr("value", time);
	});

	function validate() {
				
		var alertValidationMessage = "";		
		var date1 = document.getElementById("datepicker11").value; //'01-02-2016';  /*february 25th*/
		var date2 = document.getElementById("datepicker10").value;  //'01-01-2018';  /*february 26th*/ Action Date		
		if(!(date1==null || date1.trim() === '') ){		
			if ($.datepicker.parseDate('dd-mm-yy', date2) > $.datepicker.parseDate('dd-mm-yy', date1)) {			  
				  alertValidationMessage = alertValidationMessage + " ACTION DATE MUST BE LESS THAN FOLLOWUP DATE, ";
			      document.getElementById("datepicker10").value = '';
			      status = false;
			}			
		}
		
		alertValidationMessage = alertValidationMessage + " PLEASE FILL ";

		var status = true;
		$('#potential').attr('disabled',false);
		var da = document.getElementById('datepicker10').value;
		var actionId = document.getElementById('actionId').value;
		var actionTime = document.getElementById('actionTime').value;		
		var leadStage = document.getElementById('leadStage').value;
		var potential = document.getElementById('potential').value;
		var remarks = document.getElementById('remarks').value;		
		var followupActionId = document.getElementById('followupAction').value;
		var followupDate = document.getElementById('datepicker11').value;
		var followupTime = document.getElementById('followupTime').value;
		var purpose = document.getElementById('purpose').value;
		var rejectReason = document.getElementById('rejectReason').value;
		
		
		
					
		if(actionId == ""){
			alertValidationMessage = alertValidationMessage + " ACTION, ";
			status = false;
		}
		
		if(da == ""){
			alertValidationMessage = alertValidationMessage + " ACTION DATE, "; 
			status = false;
		}
		
		if(actionTime == ""){
			alertValidationMessage = alertValidationMessage + " ACTION TIME, ";
			status = false;
		}
		
		if(leadStage == "" || leadStage == "-1" ){
			alertValidationMessage = alertValidationMessage + " LEAD STAGE, ";
			status = false;
		}
		
		if(!(leadStage == "") && !(leadStage == "-1") && (leadStage == "1000000008")){
			if(rejectReason == "" || rejectReason == "-1"){
				alertValidationMessage = alertValidationMessage + " REJECT REASON, ";
				status = false;
			}
		}
		
		
		
		if(potential == "" || potential == "-1" ){
			alertValidationMessage = alertValidationMessage + " POTENTIAL, ";
			status = false;
		}
		
		if(remarks == "" ){			
			alertValidationMessage = alertValidationMessage + " REMARKS, ";
			status = false;
		} 
		
		/* if (actionId == 7 || actionId == 5 || actionId == 6 || actionId == 8
				|| actionId == 24) {
			if (da == "") {
				alert("PLEASE ENTER FOLLOWUP DATE");
				return false;
			}
			var time = document.getElementById('followupTime').value;

			if (time == "") {
				alert("PLEASE ENTER FOLLOWUP TIME");
				return false;
			}
		} */
				
		if(!(followupActionId == "") && !(followupActionId == "-1") ){
			if(followupDate == ""){
				alertValidationMessage = alertValidationMessage + " FOLLOWUP DATE, ";
				status = false;
			}
			if(followupTime == ""){
				alertValidationMessage = alertValidationMessage + " FOLLOWUP TIME, ";
				status = false;
			}
		}
		
		
		if(!(followupActionId == "") && !(followupActionId == "-1") ){
			if(purpose == "" || purpose == "-1"){
				alertValidationMessage = alertValidationMessage + " PURPOSE, ";
				status = false;
			}
		} 
		
		
		
		if(status){
			return status;
		}else{
			alert(alertValidationMessage);
			return false;
		}
		
	}

	$(".select-tooltip").each(
			function() {
				//var selttip = $(this);
				$(this).text(
						$(this).prev("span").find("select").find(
								"option:selected").text());
				$(".sel-ttip").mouseenter(function() {

					$(this).next(".select-tooltip").fadeIn();

				});
				$(".sel-ttip").mouseleave(function() {
					//alert("leave");
					$(this).next(".select-tooltip").fadeOut();
				});

			});
	function setData(){
		var data=$('#margin-set-left').val();
		$('#modalRemark').val(data);		
	}
	function setData1(){
		var data=$('#modalRemark').val();
		$('#margin-set-left').val(data);		
	}
	
	
	
	function changePotential() {
      
		var followUpDate = document.getElementById("datepicker11").value;
		var actionDate = document.getElementById("datepicker10").value;
		
		if(actionDate == ''){
			alert('PLEASE SELECT ACTION DATE FIRST');
			document.getElementById("datepicker11").value = '';
			document.getElementById("datepicker10").value = '';
		}
				
		var current_date = new Date();
		
		var date = current_date.getDate();
		var month = current_date.getMonth() + 1;
		var year = current_date.getFullYear();
		var days = 0;

		var current_full_date = '' + date + '-' + month + '-' + year;

		var arr1 = followUpDate.split('-');
		//var arr2 = current_full_date.split('-');
		var arr2 = actionDate.split('-');
		
		var newdateFollowupDate = new Date(arr1[2], arr1[1], arr1[0]);

		//var newdateCurrentDate = new Date(arr2[2], arr2[1], arr2[0]);
		var newdateCurrentDate = new Date(arr2[2], arr2[1], arr2[0]);

		var total_days = (newdateFollowupDate.getTime() - newdateCurrentDate.getTime()) / (1000 * 60 * 60 * 24);
		
		if (total_days <= 7) {
			document.getElementById("potential").selectedIndex = "1";
		}
		else if (total_days > 7 && total_days <= 15) {
			document.getElementById("potential").selectedIndex = "0";
		}
		else if (total_days > 15) {
			document.getElementById("potential").selectedIndex = "2";
		} 
		else {
			document.getElementById("potential").selectedIndex = "0";
		}

	}

	
	

	
	
	
	
</script>
<script type="text/javascript"
	src="resources/js/bootstrap-clockpicker.min.js"></script>
<script type="text/javascript">
	$('.clockpicker').clockpicker({
		donetext : "Set",
		placement : 'left',
		align : 'left',
		autoclose : true,
		twelvehour : true,
		'default' : 'now'
	}).find('input').change(function() {
		console.log(this.value);
	});
	var input = $('#single-input').clockpicker({

		placement : 'bottom',
		align : 'left',
		autoclose : true,
		'default' : 'now'
	});

	//alert("1")
	$("#tabs li a")
			.click(
					function() {
						//alert("tab clicked");
						if ($(this).parents("#content").next(".btnPosition")
								.find(".toggle-list").children("span")
								.hasClass("plus-icon1")) {
							//alert("toggle the scene")
							//this).parents("#content").parent(".buttonHeading").next(".leadstatus").css("display","block");
							$(this).parents("#content").next(".btnPosition")
									.find(".toggle-list").click();

						}
						//if($(this).children("span").hasClass("minus-icon1")){
					});
</script>

<!-- <script>
$(function() {
    $('.docDate1').datetimepicker();
});
</script> -->
<script>
	$(".down-div").css("display", "none");
	$(".uparrow-div").css("display", "block");
	$('#collapse-div').css('height', '32px');
	$('.contactList .listOne ').css('min-height', '46px');
	$(".topstaticinfo").css("height", "100%");
	$(
			'ul.listOne li:nth-child(3),ul.listOne li:nth-child(4),ul.listOne li:nth-child(5),ul.listOne li:nth-child(6),ul.listOne li:nth-child(7),ul.listOne li:nth-child(8)')
			.css('display', 'none');
	$(".taggs").css("display", "none");

	function uparrow() {
		$(".uparrow-div").css("display", "block");
		$(".down-div").css("display", "none");
		/*$('#collapse-div').css('height', '53px');*/
		$('.contactList .listOne ').css('min-height', '46px');
		$('#collapse-div').animate({
			height : 32
		}, 250);
		$(".topstaticinfo").css("height", "100%");
		$(
				'ul.listOne li:nth-child(3),ul.listOne li:nth-child(4),ul.listOne li:nth-child(5),ul.listOne li:nth-child(6),ul.listOne li:nth-child(7),ul.listOne li:nth-child(8)')
				.css('display', 'none');
		$(".taggs").css("display", "none");
	}

	function down() {
		$(".uparrow-div").css("display", "none");
		$(".down-div").css("display", "block");
		/*$('#collapse-div').css('height', 'auto');*/
		$('#collapse-div').animate({
			height : '140'
		}, 250);
		$(
				'ul.listOne li:nth-child(3),ul.listOne li:nth-child(4),ul.listOne li:nth-child(5),ul.listOne li:nth-child(6),ul.listOne li:nth-child(7),ul.listOne li:nth-child(8)')
				.css('display', 'block');
		$('.contactList .listOne ').css('height', 'auto');
		$(".topstaticinfo").css("height", "");
		$(".taggs").css("display", "block");
	}
</script>
<script>
	$(".topstaticinfo ul>li").each(
			function() {
				if ($(this).text().trim().length >= 12) {
					var originText1 = $(this).text();
					var cropText1 = originText1.substring(0, 12);
					$(this).text(cropText1 + "...");
					$(this).append(
							"<span class='hover_dashyy' style='display:none;'>"
									+ originText1 + "</span>");
				}
			});
	$(".row.contactList.leadstatus div>label").each(
			function() {
				if ($(this).text().trim().length >= 20) {
					$(".row.contactList.leadstatus div .sel-ttip").removeAttr(
							"class")
					var originText1 = $(this).text();
					var cropText1 = originText1.substring(0, 12);
					$(this).text(cropText1 + "...");
					$(this).append(
							"<span class='hover_dashyy' style='display:none;'>"
									+ originText1 + "</span>");
				}
			});
</script>
<style>
.top-it {
	height: 23px !important;
}

.contactList .listOne input[type="text"],.contactList .listOne select,.leadstatus input[type="text"],.leadstatus select,.leadstatus textarea
	{
	width: 60%;
}

.contact-top.padding-top-container {
	margin-top: 12px !important;
}

.contactHeader .topMenu {
	margin-top: 42px !important;
}

.searchList#searchlist {
	margin-top: 38px !important;
}

.contactList#collapse-div {
	margin-top: 107px !important;
}

.leadStatusContact li:last-child label {
	width: 33% !important;
}

.icon-image.icon-new-image {
	right: 40px !important;
	top: -2px !important;
}

.topstaticinfo .listOne {
	min-height: 28px !important;
	height: 100% !important;
}

.topstaticinfo {
	height: 100%;
}
</style>
<!--[if IE 8]>
	
	<style type="text/css">
	.taggs{width: 25% !important}
	</style>
	
<![endif]-->
