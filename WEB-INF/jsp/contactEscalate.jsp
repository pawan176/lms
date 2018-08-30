<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!-- <link href="resources/css/bootstrap.css" rel="stylesheet"> -->
<!-- <link href="resources/css/bootstrap-theme.css" rel="stylesheet"> -->
<link rel="stylesheet" href="resources/css/pickmeup.css" type="text/css" />
 <!-- <script src="resources/js/jquery.min.js" type="text/javascript"></script> -->
<script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
<style>
.icon-image {
top: 8px;
}
.listOne:nth-child(1) li:nth-child(5) {
top: 2px !important;
}
.listOne:nth-child(1) li:nth-child(3), .listOne:nth-child(1) li:nth-child(4), .listOne:nth-child(1) li:nth-child(8) {
top: 3px;
}
.listOne:nth-child(4) li:nth-child(5) {
top: 1px !important;
}
.leadStatusContactesclate li:last-child {
margin: 5px 0 5px;
}
ul.leadStatusContactesclate.div-top-margin-lead li {
    width: 33% !important;
    text-align: left !important;
    margin: 7px 0px;
    /* float: left; */
}
ul.leadStatusContactesclate.div-top-margin-lead li input {
    float: left;
}
img.icon-image {
    right: 9%;
}
.leadStatusContactesclate li:last-child label {
    width: 33%;
    float: left;
}
</style> 
<script type="text/javascript">
	function saveEscalation(){
		document.getElementById("leadEscalation").action = "leadEscalation.do";
		document.getElementById("leadEscalation").submit();
	}
	function openHelp(type){
		document.getElementById("helpType").value = type;
		document.getElementById("help").action = "help.do";
		document.getElementById("help").submit();
	}
</script>
<div class="container-fluid">
<form action="help.do" id="help" name="help">
	<input type="hidden" id="helpType" name="helpType">
</form>
<div class="padding-top-container contact-top col-lg-12 col-md-12 col-sm-12 col-xs-12">
		<div class="row contactList" id="collapse-div">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 topstaticinfo">
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
					<li><span>${leadHeaderDetail.customerName}</span></li>
					<li><label><spring:message code="staticHeader.custMobile"/></label></li>
					<li><span>${leadHeaderDetail.customerMobile}</span></li>
					<li><label> <spring:message code="staticHeader.custCity"/></label></li>
					<li><span>${leadHeaderDetail.customerResCity}</span></li>
					<li><label><spring:message code="staticHeader.constitution"/></label></li>
					<li><span>${leadHeaderDetail.occuType}</span></li>
				</ul>
				<div class="ImageIcon"><img src="resources/images/image-user.jpg" alt="UserLogoImage" /></div>
			</div>
			<div class="tgl_nbtn tggl tglestactic hiu uparrow-div" style="" onclick="down()">
	                    <!--   <span></span><span></span> -->
	                      <span style="position: relative;top: -3px;"></span>
	                      <!--  triangle-up	<div><img src="images/arrdown.gif"/></div> -->
	                      	 </div>
			<div class="tgl_nbtn tggl tglestactic hiu down-div" style="" onclick="uparrow()">
	                    <!--   <span></span><span></span> -->
	                      <span style="position: relative;top: -3px;"></span>
	                      <!--  triangle-up	<div><img src="images/arrdown.gif"/></div> -->
	                      	 </div>
			<!-- <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 taggs">
				<label class="col-lg-3 col-md-6 col-sm-4 col-xs-6">Tag A</label> <textarea type="text"
					class="textwidth col-lg-9 col-md-9 col-sm-8 col-xs-6 taga_textarea" disabled>${leadHeaderDetail.tagA}</textarea>
			</div>
			<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 taggs tagb_div">
				<label class="col-lg-3 col-md-3 col-sm-4 col-xs-6">Tag B</label> <textarea
					 class="textwidth col-lg-9 col-md-9 col-sm-8 col-xs-6 tagb_textarea" disabled>${leadHeaderDetail.tagB}</textarea>

			</div>-->
			<div class="col-lg-6 col-md-6 col-sm-3 col-xs-12 taggs tags-margin">
				<label class="col-lg-2 col-md-3 col-sm-3 col-xs-6" style="left: -6px;"><spring:message code="staticHeader.tagA"/></label> 
				<!--  
				<textarea type="text"
					class="textwidth col-lg-9 col-md-9 col-sm-8 col-xs-6 taga_textarea" disabled>${leadHeaderDetail.tagA}</textarea>-->
					<div class="col-lg-9 col-md-6 col-sm-6 col-xs-6 ">
                   <a href="" data-toggle="modal" data-target="#myModal1" data-backdrop="static" data-keyboard="false" onclick="setData()"><img src="./image/edit_img.png" alt="" class="icon-image"></a>
					<input type="text" value="${leadHeaderDetail.tagA}" class="col-lg-12 col-md-12 col-sm-12 col-xs-12"/>
					</div>
			</div>
			<div class="col-lg-6 col-md-6 col-sm-3 col-xs-12 taggs tags-margin">
				<label class="col-lg-2 col-md-3 col-sm-3 col-xs-6" style="left: -11px;"><spring:message code="staticHeader.tagB"/></label> <!--  <textarea
					 class="textwidth col-lg-9 col-md-9 col-sm-8 col-xs-6 tagb_textarea" disabled>${leadHeaderDetail.tagB}</textarea>
					 -->
					 <div class="col-lg-9 col-md-6 col-sm-6 col-xs-6">
                   <a href="" data-toggle="modal" data-target="#myModal2" data-backdrop="static" data-keyboard="false" onclick="setData()"><img src="./image/edit_img.png" alt="" class="icon-image"></a>
					 <input type="text" value="${leadHeaderDetail.tagB}" class="col-lg-12 col-md-12 col-sm-12 col-xs-12"/>
			         </div>
			</div>
		</div>
   <form method="post" id="leadEscalation" name="leadEscalation" action="<c:url value="${requestScope.contextPath}/leadEscalation.do"/>" >
	  <div class="row buttonHeading">
	    <div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
	      <h2 class="heading">Lead Status</h2>
	    </div>
	    <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 btnPosition" align="right">
	      <a class="btn-primary toggle-list"> <span class="toggle-icon minus-icon1 "></span> </a> </div>
	  </div>
	  <div class="row contactList leadstatus padding-top-div-lead">    
	    <ul class="leadStatusContactesclate div-top-margin-lead">
					<li>
	      <label>${contactDetail.actionName}</label>
		     <input type="text" name="actionName" readonly="readonly"  value="${contactDetail.actionName}"/>		     
	    </li>
	    <li>
	      <label><spring:message code="label.initiatedBy"/></label>
	      	<input type="text" name="initiatedBy"  readonly="readonly"  value="${contactDetail.initiatedBy}"/>
	    </li>
	    <li>
	      		<label><spring:message code="label.initiatedDateTime"/></label>
	      		<input type="text" name="initiatedDateTime"  readonly="readonly"  value="${contactDetail.initiatedDateTime}"/>
	    </li>
	    <li>
	      <label><spring:message code="label.initiationRemarks"/></label>
	      <!--  <textarea name ="initialRemarks" class="textClass"> ${contactDetail.initialRemarks}</textarea>-->
	      	<input type="hidden" value="${contactDetail.caseRefEscId}" name="caseRefEscId" id="caseRefEscId" maxlength="1000"/>
	         <a href="" data-toggle="modal" data-target="#myModal4" data-backdrop="static" data-keyboard="false" onclick="setData()"><img src="./image/edit_img.png" alt="" class="icon-image"></a>
			<input type="text" name ="initialRemarks" maxlength="1000"  readonly="readonly" class="col-lg-12 col-md-12 col-sm-12 col-xs-12 float-right" value="${contactDetail.initialRemarks}"/>
	    </li>
	    <li>
	      	<label style="margin-top: 2px;"><spring:message code="label.resolved"/></label>
	    	<input type="checkbox" name="resolutionCheck" style="margin-top: 6px;" class="span_5_of_12 fl date1" value="resolve"/>
	    	</li>
	    <li>
	    	<label><spring:message code="label.resolution"/><spring:message code="label.remarks"/></label>
	     	<!-- <textarea name ="resolvedRemarks" class="fl span_10_of_12" maxlength="1000"></textarea> -->    	
	        <a href="" data-toggle="modal" data-target="#myModal3" data-backdrop="static" data-keyboard="false" onclick="setData()"><img src="./image/edit_img.png" alt="" class="icon-image"></a>
			<input type="text" name ="resolvedRemarks" maxlength="1000" class="col-lg-12 col-md-12 col-sm-12 col-xs-12 float-right"/>
	   </li>
	    </ul>            
	  </div>
  </form>
 <div class="row buttonHeading">
			<div class="col-lg-9 col-md-9 col-sm-10 col-xs-10" id="content">

				<ul id="tabs" class="nav nav-tabs" data-tabs="tabs">
					<li class="active"><a class="heading" href="#actionhis"
						data-toggle="tab">Action History</a></li>
					<li><a class="heading" href="#escalationhis" data-toggle="tab">Escalation/Referral
							History</a></li>
					<li><a class="heading" href="#coallocationHis" data-toggle="tab">Allocation History
						</a></li>
				</ul>

			</div>
			<div class="col-lg-3 col-md-3 col-sm-2 col-xs-2 btnPosition">
				<a class="btn-primary  toggle-list"> <span
					class="toggle-icon minus-icon1 "></span>
				</a>
			</div>
		</div>
		
		
		<div class="row contactList leadstatus paddibg-top-tabs" >
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" id="content">
				<div id="my-tab-content" class="tab-content">
					<div class="tab-pane active" id="actionhis">
						<div class="bs-example">
							<table class="table table-striped">
								<thead>
									<tr>
										<th>#</th>
										<th>Action</th>
										<th>Action By</th>
										<th>Action Date</th>
										<th>Remarks</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="action"
										items="${contactDetail.caseActionHistory}" varStatus="index">
										<tr>
											<td>${index.index}</td>
											<td>${action.action}</td>
											<td>${action.actionBy}</td>
											<td>${action.actionDate}</td>
											<td>${action.remarks}</td>
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
				</div>
			</div>
		</div>
</div>
</div>
	<!-- Modal -->
								<div class="modal fade" id="myModal1" role="dialog" style="display: none; " aria-hidden="true">
									<div class="modal-dialog">

										<!-- Modal content-->
										<div class="modal-content">
											<div class="modal-header model-border-none">
												<button type="button" class="close" data-dismiss="modal" onclick="setData()">×</button>
												<h4 class="modal-title"></h4>
											</div>
											<div class="modal-body">
												<textarea class="modal-textarea" name="remark" id="modalRemark"></textarea>
											</div>
											<div class="modal-footer model-border-none">
												<button type="button" class="btn btn-default" data-dismiss="modal" onclick="setData()">Close</button>
											</div>
										</div>

									</div>
								</div>
<!-- Modal -->
<!-- Modal -->
								<div class="modal fade" id="myModal2" role="dialog" style="display: none; " aria-hidden="true">
									<div class="modal-dialog">

										<!-- Modal content-->
										<div class="modal-content">
											<div class="modal-header model-border-none">
												<button type="button" class="close" data-dismiss="modal" onclick="setData()">×</button>
												<h4 class="modal-title"></h4>
											</div>
											<div class="modal-body">
												<textarea class="modal-textarea" name="remark" id="modalRemark"></textarea>
											</div>
											<div class="modal-footer model-border-none">
												<button type="button" class="btn btn-default" data-dismiss="modal" onclick="setData()">Close</button>
											</div>
										</div>

									</div>
								</div>
<!-- Modal -->
<!-- Modal -->
<div class="modal fade" id="myModal4" role="dialog" style="display: none; " aria-hidden="true">
									<div class="modal-dialog">

										<!-- Modal content-->
										<div class="modal-content">
											<div class="modal-header model-border-none">
												<button type="button" class="close" data-dismiss="modal" onclick="setData()">×</button>
												<h4 class="modal-title"></h4>
											</div>
											<div class="modal-body">
												<textarea class="modal-textarea" name="remark" id="modalRemark"></textarea>
											</div>
											<div class="modal-footer model-border-none">
												<button type="button" class="btn btn-default" data-dismiss="modal" onclick="setData()">Close</button>
											</div>
										</div>

									</div>
								</div>
<!-- Modal -->	
<script>


$('.prev-lead-icon').parent('.subheader-btn').hover(function(){
//alert('1');
	$(this).children('.prevlead-tooltip').fadeToggle(150);
});


$('.next-lead-icon').parent('.subheader-btn').hover(function(){
//alert('1');
	$(this).children('.nextlead-tooltip').fadeToggle(150);
});

$('.savee-icon').parent('.subheader-btn').click(function(){
	document.getElementById("leadEscalation").action = "leadEscalation.do";
	document.getElementById("leadEscalation").submit();
});
$('.saveandexit-icon').parent('.subheader-btn').click(function(){
	document.getElementById("leadEscalation").action = "seleadEscalation.do";
	document.getElementById("leadEscalation").submit();
});

	$('.usermenu-mobile').click(function(){
	//	alert('fs');
		$('.header-rightpanel').animate({"top":"0"});
	});
	$('.close-header-rightpanel').click(function(){
	
		$('.header-rightpanel').animate({"top":"-260px"});
	
	});
	$(document).ready(function(){
		$(".topMenu li:nth-child(1) a").addClass("active");
	 });
	
</script>

<script>
$(".down-div").css("display","none");
$(".uparrow-div").css("display","block");
$('#collapse-div').css('height', '58px');
$('.contactList .listOne ').css('min-height', '46px');
$('ul.listOne li:nth-child(3),ul.listOne li:nth-child(4),ul.listOne li:nth-child(5),ul.listOne li:nth-child(6),ul.listOne li:nth-child(7),ul.listOne li:nth-child(8)').css('display', 'none');
$(".taggs").css("display","none");



function uparrow() {
$(".uparrow-div").css("display","block");
$(".down-div").css("display","none");
/*$('#collapse-div').css('height', '53px');*/
$('.contactList .listOne ').css('min-height', '46px');
$('#collapse-div').animate({height:58},250);
$('ul.listOne li:nth-child(3),ul.listOne li:nth-child(4),ul.listOne li:nth-child(5),ul.listOne li:nth-child(6),ul.listOne li:nth-child(7),ul.listOne li:nth-child(8)').css('display', 'none');
$(".taggs").css("display","none");
}

function down() {
$(".uparrow-div").css("display","none");
$(".down-div").css("display","block");
/*$('#collapse-div').css('height', 'auto');*/
$('#collapse-div').animate({height:'145'},250);

$('ul.listOne li:nth-child(3),ul.listOne li:nth-child(4),ul.listOne li:nth-child(5),ul.listOne li:nth-child(6),ul.listOne li:nth-child(7),ul.listOne li:nth-child(8)').css('display', 'block');
$('.contactList .listOne ').css('height', 'auto');
$(".taggs").css("display","block");
}
</script>
<!--[if IE 8]>
	
	<style type="text/css">
	.taggs{width: 25% !important}
	</style>
	
<![endif]--> 
<style>
.padding-top-div-lead {
padding-bottom: 17px;
}
</style>