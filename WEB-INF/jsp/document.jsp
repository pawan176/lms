<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<script src="resources/js/globalValidation.js" type="text/javascript"></script>
<script src="resources/js/myValidation.js" type="text/javascript"></script>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
<style>
.docu-tablee tr td:nth-child(9) input, .docu-tablee tr td:nth-child(10) input, .docu-tablee tr td:nth-child(5) input {
width: 100% !important;
}
</style>
<!--[if IE 8]>
<style type="text/css">
.dashbrd button{background: url("../img/select-bg.png") no-repeat right 1px !important}
</style>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->

<div class="container-fluid">
	<div
		class="padding-top-container contact-top scol-lg-12 col-md-12 col-sm-12 col-xs-12 fourtabs-toppadding">
		<div class="row contactList" id="collapse-div">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 topstaticinfo">
				<ul class="listOne">
					<li><label><spring:message code="staticHeader.leadId"/></label></li>
					<li><span>${leadHeaderDetail.leadCode}</span></li>
					<li><label><spring:message code="staticHeader.generationDate"/></label></li>
					<li><span>${leadHeaderDetail.generationDate}</span></li>
					<li><label><span>${leadHeaderDetail.allocatedTo}</span></label></li>
					<li class="allocayteblock"><span> ${leadHeaderDetail.allocatedTo}</span></li>
					<li><label>Product</label></li>
					<li class="productblock"><span>${leadHeaderDetail.queue}<span class="tooltiptext">${leadHeaderDetail.queue}</span></span></li>
				</ul>
				<ul class="listOne">
					<li><label><spring:message code="staticHeader.queue"/></label></li>
					<li class="queueblock"><span>${leadHeaderDetail.queue}<span class="tooltiptext">${leadHeaderDetail.queue}</span></span></li>
					<li><label><spring:message code="staticHeader.subQueue"/></label></li>
					<li><span> ${leadHeaderDetail.subQueue}</span></li>
					<li><label><spring:message code="staticHeader.source"/></label></li>
					<li><span>${leadHeaderDetail.source} </span></li>
					<li><label><spring:message code="staticHeader.campaign"/></label></li>
					<li><span>${leadHeaderDetail.campaign} </span></li>
				</ul>
				<ul class="listOne">
					<li><label><spring:message code="staticHeader.facilityRequested"/></label></li>
					<li class="facilityblock"><span> ${leadHeaderDetail.facilityReq}<span class="tooltiptext">${leadHeaderDetail.facilityReq}</span></span></li>
					<li><label><spring:message code="staticHeader.amount"/> </label></li>
					<li><span> ${leadHeaderDetail.amount}</span></li>
					<li><label><spring:message code="staticHeader.bank"/></label></li>
					<li><span>${leadHeaderDetail.bankName}</span></li>
					<li><label><spring:message code="staticHeader.roi"/></label></li>
					<li><span>${leadHeaderDetail.roi}</span></li>
				</ul>
				<ul class="listOne">
					<li><label><spring:message code="staticHeader.custName"/></label></li>
					<li><span>${leadHeaderDetail.customerName}</span></li>
					<li><label><spring:message code="staticHeader.custMobile"/> </label></li>
					<li><span>${leadHeaderDetail.customerMobile}</span></li>
					<li><label><spring:message code="staticHeader.custCity"/></label></li>
					<li><span>${leadHeaderDetail.customerResCity} </span></li>
					<li><label><spring:message code="staticHeader.constitution"/></label></li>
					<li><span>${leadHeaderDetail.occuType} </span></li>
				</ul>
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
		</div>
		<form action="" id="docForm" method="POST"
			enctype="multipart/form-data">
			<div class="row buttonHeading">
			<div class="col-lg-9 col-md-9 col-sm-6 col-xs-6">
				<h2 class="heading">Document</h2>
			</div>
			<div class="col-lg-3 col-md-3 col-sm-6 col-xs-6 btnPosition">
				<a class="toggle-list btn-primary"> <span
					class="toggle-icon minus-icon1 "></span>
				</a>
				<!-- <button type="button" class="btn btn-primary"
					onclick="updateXSell();">Save</button> -->
				<button type="button" class="btn btn-primary"
					onclick="return deleteXSell();">Delete</button>
				<button type="button" class="btn btn-primary add-list-docreffereal" id="documentSaveBtn"
					value="${documentForm.documentList eq null ? '0' : fn:length(documentForm.documentList)}">Add
				</button>
			</div>
		</div>
			<%-- <div class="row buttonHeading">
				<div class="col-lg-9 col-md-9 col-sm-12 col-xs-12">
					<h2 class="heading">Document</h2>
				</div>
				<div class="col-lg-3 col-md-3 col-sm-12 col-xs-12 btnPosition">
					<button type="button" class="btn btn-default"
						onclick="updateXSell();" style="color:white;">Save</button>
					<button type="button" class="btn btn-default"
						onclick="return deleteXSell();" style="color:white;">Delete</button>
					<button type="button" class="btn btn-default add-list-docreffereal"
					value="${documentForm.documentList eq null ? '0' : fn:length(documentForm.documentList)}" style="color:white;">Add</button>
					
					<a class="btn-primary toggle-list"> <span
						class="toggle-icon minus-icon1"></span></a>
				</div>
			</div> --%>
			<div class="row documentTablee">
				<div class="bs-example overCover table-document">
					<table class="table table-striped docu-tablee tr-padding-zero padding-bottom-table">
						<thead>
							<tr>
								<th>&nbsp;</th>
								<th>Active</th>
								<th>Doc Type</th>
								<th>Doc Name</th>
								<th>Remarks</th>
								<th>Status</th>
								<th>Received Date</th>
								<th>Target Date</th>
								<th>Created By</th>
								<th>Created Date</th>
								<th>Select Doc</th>
								<th></th>
							</tr>
						</thead>
						<tbody class="tdbackground tr-center">
							<c:forEach var="document" items="${documentForm.documentList}" varStatus="status"> 
          <tr>
          	<td>${status.index+1}</td>
          	
            <td><input type="checkbox" value="${document.docId}" name="documentList[${status.index}].docId" id="documentList[${status.index}].docId" onclick="checkedIt(this)"/></td>
            
            <td>
            	<span class="sel-ttip" >			
           			<select	name="documentList[${status.index}].documentTypeId" id="documentList[${status.index}].documentTypeId" onchange="getDocumentName(this)" style="width: 83px; position: relative; top: 7px;">
				<option value="-1">Select</option>
				<c:forEach var="doc" items="${docmentTypeMaster}">
				<option value="${doc.documentTypeId}"
				${doc.documentTypeId==document.documentTypeId?'Selected':'' }>${doc.documentTypeName}</option>
				</c:forEach>
				</select></span>
				<span class="select-tooltip" style="margin-top: -15px; background: #fff;  border: solid 1px #ccc"></span>
			</td>
			
			<td>
				<span class="sel-ttip" >
					<select name="documentList[${status.index}].documentId" style="width: 80px; position: relative; top: 7px;">
					<option value="${document.documentId==''?'-1':document.documentId}">
						${document.documentName==''?'Select':document.documentName}
					</option>
				</select>
				</span>
				<span class="select-tooltip" style="margin-top: -15px; background: #fff;  border: solid 1px #ccc"></span>
			</td>
			
			<td><input type="text" value="${document.remarks}" name="documentList[${status.index }].remarks" style="width: 64px"></td>
			
			<td>
				<span class="sel-ttip" >
				<select name="documentList[${status.index}].docStatusId" id="documentList[${status.index }].docStatusId" style="width: 61px;  position: relative; top: 7px;">
				<option value="-1">Select</option>
				<c:forEach var="facility1" items="${documentStatusList}">
				<option value="${facility1.docStatusId}" 
				${facility1.docStatusId==document.docStatusId?"Selected":"" }>${facility1.docStatusName}</option>
				</c:forEach>
				</select>
				</span>
				<span class="select-tooltip" style="margin-top: -15px; background: #fff;  border: solid 1px #ccc"></span>
			</td>
							
            <td class="calc" style="position: relative"><input type="text" id=""
            		value="${document.receivingDate}" name="documentList[${status.index}].receivingDate" class="docDate3" style="width: 107px">
       		 <span class="dobCalc glyphicon glyphicon-calendar" style="right: 19px; top: 8px;left: auto !important;position: absolute;" for="datepicker" aria-hidden="true"></span> </td>
       
            <td class="calc" style="position: relative"><input type="text" style="width: 107px" 
            		value="${document.targetDate}" name="documentList[${status.index}].targetDate" class="docDate2">
            <span class="dobCalc glyphicon glyphicon-calendar" style="right: 16px; top: 10px;left: auto !important;position: absolute;" for="datepicker" aria-hidden="true"></span> </td>
      
            <td>
            <input type="text" value="${document.docCreatedByName}" name="documentList[${status.index }].docCreatedByName" readonly style="width: 70px;">
            <%-- <input type="hidden" value="${document.docCreatedById}" name="documentList[${status.index}].docCreatedById"> --%>
		            <%-- <select name="documentList[${status.index}].docCreatedById">
		            <option value="-1">Select</option>
		            <c:forEach var="facility1" items="${documentCreatedByList}">
		            <option value="${facility1.docCreatedById}" ${facility1.docCreatedById==documentList.docCreatedById?"Selected":"" }>${facility1.docCreatedByName}
		            </option>
		            </c:forEach>
		            </select> --%>
            </td>
            
            <td class="calc"><input type="text" readonly
            	value="${document.createdDate}" name="documentList[${status.index}].createdDate" style="width: 70px;">
      		</td>
      
           <%--  <td>
            <input type="text" value="${document.docUpdatedByName}" name="documentList[${status.index }].docUpdatedByName" readonly>
            <input type="hidden" value="${document.docUpdatedById}" name="documentList[${status.index}].docUpdatedById">
		            <select name="documentList[${status.index}].docUpdatedById">
		            <option value="-1">Select</option>
		            <c:forEach var="facility1" items="${documentUpdatedByList}">
		            <option value="${facility1.docUpdatedById}" ${facility1.docUpdatedById==documentList.docUpdatedById?"Selected":"" }>${facility1.docUpdatedByName}</option>
		            </c:forEach>
		            </select>
            </td> --%>
            
            <%-- <td class="calc"><input type="text" readonly
            		value="${document.updatedDate}" name="documentList[0].updatedDate">
      	     </td> --%>
        
            <td><label for="attfile1"><spring:message code="label.attach"/></label><input type="file" id="attfile1" name="documentList[${status.index}].documentBlob" style="width: 159px"></td>
            <%-- value="${document.documentName}" --%>
            
        <%--    <td><a id="viewDoc12" value="2" href='<%=request.getContextPath()%>//file//Hibernate.pdf' target="_blank">View</a> --%>
        
           <%-- getDocumentBlob.do?docId=${document.docId} --%>
           <%-- <%=request.getContextPath()%>/ --%>
                     
        
            <%--  <td><button type="button" class="btn btn-primary" value="${document.docId }" name="documentList[${status.index}].viewDoc" onclick="showDocument(this)" id="documentList[${status.index}].viewDoc">
		      <span class="glyphicon glyphicon-floppy-open"/>View Document</button></td> --%>
	
		 
			<%-- name="documentList[${status.index}].viewDoc" onclick="showDocument(this)" id="documentList[${status.index}" --%>
		  <!-- viewDocument.do?docId=${document.docId}&docTypeId=${document.documentTypeId} -->
	<%-- 	   <button type="button" class="btn btn-primary" value="${document.docId }" name="documentList[${status.index}].viewDoc" onclick="showDocument(this)" id="documentList[${status.index}].viewDoc">
		      <span class="glyphicon glyphicon-floppy-open"/>View Document</button>  --%>
		      
			  <!-- <a href='#' class="btn btn-sm btn-primary" style="text-dexoration:none;" target="_blank">
			  <span class="glyphicon glyphicon-floppy-open"></span>&nbsp; View Document
			  </a> -->
			  <!-- http://localhost:8080/miFinLeadManagement//file//Hibernate.pdf -->
		       
		      
          <td>
	          <a href="downloadDocument.do?docId=${document.docId}&docTypeId=${document.documentTypeId}" class="btn btn-primary button-topspace" style="text-dexoration:none;">View</a>
          </td> 
        
        <!-- <span class="glyphicon glyphicon-download-alt"></span>&nbsp;  -->
        
          </tr>
          </c:forEach>  
						</tbody>
					</table>
				</div>
			</div>
		</form>
	</div>
</div>
<script>
	$(document).ready(function() {
		
		if("${docError}"=="NoDocFound"){
			alert("No Document Found!!");
		}		
		if("${fn:length(documentForm.documentList)}"<"${fn:length(docmentTypeMaster)}"){
			var count=parseInt("${fn:length(documentForm.documentList)}");
			var srNo=parseInt("${fn:length(documentForm.documentList)}")+1;
			var loop=parseInt("${fn:length(docmentTypeMaster)}")-parseInt("${fn:length(documentForm.documentList)}");
			while(loop>0){
							$('.add-list-docreffereal').parent('.btnPosition').parent()
								.next('.row')
								.find('tbody')
								/* .append('<tr><td>1</td><td><input type="checkbox" name="documentList[0].docId" value="insert" checked></td><td><select name="documentList[0].documentTypeId" id="documentList[0].documentTypeId" onchange="getDocumentName(this)"><option value="-1">Select</option><c:forEach var="facility1" items="${docmentTypeMaster}"><option value="${facility1.documentTypeId}" ${facility1.documentTypeId==documentList.documentType?"Selected":"" }>${facility1.documentTypeName}</option></c:forEach></select></td><td><select name="documentList[0].documentId" id="documentList[0].documentId"><option value="-1">--Select--</option></select></td><td><input type="text" value="" name="documentList[0].remarks"></td><td><select name="documentList[0].docStatusId"><option value="-1">Select</option><c:forEach var="facility1" items="${documentStatusList}"><option value="${facility1.docStatusId}"${facility1.docStatusId==documentList.docStatusId?"Selected":"" }>${facility1.docStatusName}</option></c:forEach></select></td><td class="calc"><input type="text" name="documentList[0].receivingDate" onclick="showCalendar(this);" class="docDate"><span class="dobCalc glyphicon glyphicon-calendar" for="datepicker" aria-hidden="true"></span> </td><td class="calc"><input type="text" name="documentList[0].targetDate" class="docDate"><span class="dobCalc glyphicon glyphicon-calendar" for="datepicker" aria-hidden="true"></span> </td><td><input type="text" value="" name="documentList[0].docCreatedByName" readonly></td><td class="calc"><input type="text" readonly name="documentList[0].createdDate"></td><td><input type="text" value="" name="documentList[0].docUpdatedByName" readonly></td><td class="calc"><input type="text" readonly name="documentList[0].updatedDate"></td><td><input type="file" name="documentList[0].documentBlob"></td></tr>'); */
								.append('<tr><td>'+srNo+'</td><td><input type="checkbox" name="documentList['+count+'].docId" id="documentList['+count+'].docId" value="insert" checked></td><td><select name="documentList['+count+'].documentTypeId" id="documentList['+count+'].documentTypeId" onchange="getDocumentName(this)" style="width: 83px"><option value="-1">Select</option><c:forEach var="facility1" items="${docmentTypeMaster}"><option value="${facility1.documentTypeId}" ${facility1.documentTypeId==documentList.documentType?"Selected":"" }>${facility1.documentTypeName}</option></c:forEach></select></td><td><select name="documentList['+count+'].documentId" id="documentList['+count+'].documentId" style="width: 80px"><option value="-1">--Select--</option></select></td><td><input type="text" value="" name="documentList['+count+'].remarks" style="width: 64px"></td><td><select name="documentList['+count+'].docStatusId" style="width: 61px"><option value="-1">Select</option><c:forEach var="facility1" items="${documentStatusList}"><option value="${facility1.docStatusId}"${facility1.docStatusId==documentList.docStatusId?"Selected":"" }>${facility1.docStatusName}</option></c:forEach></select></td><td class="calc"><input type="text" name="documentList['+count+'].receivingDate" onclick="showCalendar(this);" class="docDate3" style="width: 107px"><span class="dobCalc glyphicon glyphicon-calendar" for="datepicker" aria-hidden="true"></span> </td><td class="calc"><input type="text" name="documentList['+count+'].targetDate" class="docDate2" style="width: 107px"><span class="dobCalc glyphicon glyphicon-calendar" for="datepicker" aria-hidden="true"></span> </td><td><input type="text" value="" name="documentList['+count+'].docCreatedByName" style="width: 70px;" readonly></td><td class="calc"><input type="text" readonly name="documentList['+count+'].createdDate" style="width: 70px;"></td><td><label for="attfile1">Attach</label><input id="attfile1" type="file" name="documentList['+count+'].documentBlob" style="width: 159px"></td></tr>');
							/* <td><input type="button" name="documentList[0].viewDoc" class="btn btn-primary" value="View Document"></td> */
							count=count+1;
							srNo=srNo+1;
							loop=loop-1;
			}	
			$('.add-list-docreffereal').val(count);
		}
						enableDatePicker();
					});
	var flag = true;
	var flag1 = true;
	$('.etabs .tab').mouseenter(function(e) {
		$('.etabs .tab a:nth-child(2)').hide();
		$(this).find('a:nth-child(2)').show();
	});
	$('.etabs .tab').mouseleave(function(e) {
		$('.etabs .tab a:nth-child(2)').hide();
	});
	function deleteXSell() {
		var loopCount=$('#documentSaveBtn').val();
		var checkedCount=0;
		for(var i=0;i<loopCount;i++){
			if(document.getElementById('documentList['+i+'].docId').checked){
				checkedCount=checkedCount+1;
			}
		}
		if(checkedCount==0){
			alert("Please Select at least one document to delete");
			return false;
		}else if(checkedCount>=0){
			document.getElementById("docForm").action = "deleteDocument.do";
			document.getElementById("docForm").submit();
		}	
		/* document.getElementById("docForm").action = "deleteDocument.do";
		document.getElementById("docForm").submit(); */		
	}
	
	function updateXSell() {
		var loopCount=$('#documentSaveBtn').val();
		var checkedCount=0;
		var unselectedDocTypeCount=0;
		for(var i=0;i<loopCount;i++){
			if(document.getElementById('documentList['+i+'].docId').checked){
				if(document.getElementById("documentList["+i+"].documentTypeId").value==-1){
					alert("Please Select document type");
					document.getElementById("documentList["+i+"].documentTypeId").focus();
					document.getElementById("documentList["+i+"].documentTypeId").select();
					unselectedDocTypeCount=unselectedDocTypeCount+1;
					return false;
			}
				checkedCount=checkedCount+1;
			}
		}
		if(checkedCount==0){
			alert("Please Select at least one document to save or update");
			return false;
		}else if(checkedCount>=0 && unselectedDocTypeCount==0){
			document.getElementById("docForm").action = "documentUpload.do";
			document.getElementById("docForm").Methods = "POST";
			document.getElementById("docForm").submit();
		}
		/* document.getElementById("docForm").action = "documentUpload.do";
		document.getElementById("docForm").Methods = "POST";
		document.getElementById("docForm").submit(); */
	}
	$('.prev-lead-icon').parent('.subheader-btn').hover(function() {
		//alert('1');
		$(this).children('.prevlead-tooltip').fadeToggle(150);
	});
	$('.next-lead-icon').parent('.subheader-btn').hover(function() {
		//alert('1');
		$(this).children('.nextlead-tooltip').fadeToggle(150);
	});
	$('.savee-icon').parent('.subheader-btn').click(function() {
		var loopCount=$('#documentSaveBtn').val();
		var checkedCount=0;
		var unselectedDocTypeCount=0;
		for(var i=0;i<loopCount;i++){
			if(document.getElementById('documentList['+i+'].docId').checked){
				if(document.getElementById("documentList["+i+"].documentTypeId").value==-1){
					alert("Please Select document type");
					document.getElementById("documentList["+i+"].documentTypeId").focus();
					document.getElementById("documentList["+i+"].documentTypeId").select();
					unselectedDocTypeCount=unselectedDocTypeCount+1;
					return false;
			}
				checkedCount=checkedCount+1;
			}
		}
		if(checkedCount==0){
			alert("Please Select at least one document to save or update");
			return false;
		}else if(checkedCount>=0 && unselectedDocTypeCount==0){
			document.getElementById("docForm").action = "documentUpload.do";
			document.getElementById("docForm").Methods = "POST";
			document.getElementById("docForm").submit();
		}
		/* document.getElementById("docForm").action = "documentUpload.do";
		document.getElementById("docForm").Methods = "POST";
		document.getElementById("docForm").submit(); */			
		//alert('1');
		$(this).children('.savee-tooltip').fadeToggle(150);
	});
	$('.saveandexit-icon').parent('.subheader-btn').click(function() {
		var loopCount=$('#documentSaveBtn').val();
		var checkedCount=0;
		var unselectedDocTypeCount=0;
		for(var i=0;i<loopCount;i++){
			if(document.getElementById('documentList['+i+'].docId').checked){
				if(document.getElementById("documentList["+i+"].documentTypeId").value==-1){
					alert("Please Select document type");
					document.getElementById("documentList["+i+"].documentTypeId").focus();
					document.getElementById("documentList["+i+"].documentTypeId").select();
					unselectedDocTypeCount=unselectedDocTypeCount+1;
					return false;
			}
				checkedCount=checkedCount+1;
			}
		}
		if(checkedCount==0){
			alert("Please Select at least one document to save or update");
			return false;
		}else if(checkedCount>=0 && unselectedDocTypeCount==0){
			document.getElementById("docForm").action = "sedocumentUpload.do";
			document.getElementById("docForm").Methods = "POST";
			document.getElementById("docForm").submit();
		}
	});
	$('.saveandexit-icon').parent('.subheader-btn').hover(function() {
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
	$('.add-list-docreffereal')
			.click(
					function(e) {
						e.preventDefault();
						var cnti = parseInt(this.value);
						var srNo = cnti + 1;
						$(this).parent('.btnPosition').parent().next('.row').find('tbody')
						/* .append('<tr><td>'+srNo+'</td><td><input type="checkbox" checked name="documentList['+cnti+'].docId" value="insert"></td><td><select name="documentList['+ cnti+ '].documentTypeId" id="documentList['+ cnti+'].documentTypeId" onchange="getDocumentName(this)"><option value="-1">Select</option><c:forEach var="facility1" items="${docmentTypeMaster}"><option value="${facility1.documentTypeId}" ${facility1.documentTypeId==documentList.documentTypeId?"Selected":"" }>${facility1.documentTypeName}</option></c:forEach></select></td><td><select name="documentList['+cnti+'].documentId"><option value="-1">--Select--</option></select></td><td><input type="text" value="" name="documentList['+cnti+'].remarks"></td><td><select name="documentList['+cnti+'].docStatusId"><option value="-1">Select</option><c:forEach var="facility1" items="${documentStatusList}"><option value="${facility1.docStatusId}" ${facility1.docStatusId==documentList.docStatusId?"Selected":"" }>${facility1.docStatusName}</option></c:forEach></select></td><td class="calc"><input type="text" name="documentList['+cnti+'].receivingDate" id="documentList['+cnti+'].receivingDate"  class="docDate"><span class="dobCalc glyphicon glyphicon-calendar" for="datepicker" aria-hidden="true"></span> </td><td class="calc"><input type="text" name="documentList['+cnti+'].targetDate" class="docDate"><span class="dobCalc glyphicon glyphicon-calendar" for="datepicker" aria-hidden="true"></span> </td><td><select name="documentList['+cnti+'].docCreatedById"><option value="-1">Select</option><c:forEach var="facility1" items="${documentCreatedByList}"><option value="${facility1.docCreatedById}" ${facility1.docCreatedById==documentList.docCreatedById?"Selected":"" }>${facility1.docCreatedByName}</option></c:forEach></select></td><td class="calc"><input type="text" name="documentList['+cnti+'].createdDate" class="docDate"><span class="dobCalc glyphicon glyphicon-calendar" for="datepicker" aria-hidden="true"></span> </td><td><select name="documentList['+cnti+'].docUpdatedById"><option value="-1">Select</option><c:forEach var="facility1" items="${documentUpdatedByList}"><option value="${facility1.docUpdatedById}" ${facility1.docUpdatedById==documentList.docUpdatedById?"Selected":"" }>${facility1.docUpdatedByName}</option></c:forEach></select></td><td class="calc"><input type="text" name="documentList['+cnti+'].updatedDate" class="docDate"><span class="dobCalc glyphicon glyphicon-calendar" for="datepicker" aria-hidden="true"></span> </td><td><input type="file" name="documentList['+cnti+'].documentBlob"></td></tr>'); */
						.append('<tr><td>'+srNo+'</td><td><input type="checkbox" checked name="documentList['+cnti+'].docId" id="documentList['+cnti+'].docId" value="insert"></td><td><select name="documentList['+ cnti+ '].documentTypeId" id="documentList['+ cnti+'].documentTypeId" onchange="getDocumentName(this)" style="width: 83px"><option value="-1">Select</option><c:forEach var="facility1" items="${docmentTypeMaster}"><option value="${facility1.documentTypeId}" ${facility1.documentTypeId==documentList.documentTypeId?"Selected":"" }>${facility1.documentTypeName}</option></c:forEach></select></td><td><select name="documentList['+cnti+'].documentId"><option value="-1">--Select--</option></select></td><td><input type="text" value="" name="documentList['+cnti+'].remarks" style="width:64px"></td><td><select name="documentList['+cnti+'].docStatusId" style="width: 61px"><option value="-1">Select</option><c:forEach var="facility1" items="${documentStatusList}"><option value="${facility1.docStatusId}" ${facility1.docStatusId==documentList.docStatusId?"Selected":"" }>${facility1.docStatusName}</option></c:forEach></select></td><td class="calc"><input type="text" name="documentList['+cnti+'].receivingDate" id="documentList['+cnti+'].receivingDate"  class="docDate3" style="width: 107px"><span class="dobCalc glyphicon glyphicon-calendar" for="datepicker" aria-hidden="true"></span></td><td class="calc"><input type="text" name="documentList['+cnti+'].targetDate" class="docDate2" style="width: 107px"><span class="dobCalc glyphicon glyphicon-calendar" for="datepicker" aria-hidden="true"></span> </td><td><input type="text" value="" name="documentList['+cnti+'].docCreatedByName" readonly style="width:70px"></td><td><input type="text" name="documentList['+cnti+'].createdDate" readonly style="width: 70px"></td><td><label for="attfile1">Attach</label><input id="attfile1" type="file" name="documentList['+cnti+'].documentBlob"></td></tr>');
						/*<td><input type="text" value="" name="documentList['+cnti+'].docUpdatedByName" readonly ></td><td class="calc"><input type="text" readonly name="documentList['+cnti+'].updatedDate"></td>  */
						this.value = cnti + 1;
						enableDatePicker();
					});

	function getDocumentName(val) {
		$.ajax({
			type : "post",
			url : "getDocumentName.do",
			data : "docType=" + val.value,
			ontext : document.body,
			success : function(response) {
				 var docTypeId = val.id;
				var leftNameIndex = docTypeId.indexOf("[");
				var rightNameIndex = docTypeId.indexOf("]");
				var indexnumber = docTypeId.substring(leftNameIndex + 1,rightNameIndex);
				leftName = docTypeId.substring(0, leftNameIndex);
				var select = $('[name="' + leftName + '[' + indexnumber	+ '].documentId"]');
				select.find('option').remove();
				$('<option>').val("-1").text("Select").appendTo(select);
				$($.parseJSON(response)).map(function() {
					$('<option>').val(this.documentId).text(this.documentName).appendTo(select);
				});
			} 
		});
	}
	
	function showDocument(val) {
		$.ajax({
			type : "post",
			url : "viewDocument.do",
			data : "docId=" + val.value,
			ontext : document.body,
			success : function(response) {
				alert(response);
				window.open('<%=request.getContextPath()%>'+response,'_blank');				
			}
		});
	}

	$(document).ready(function() {
		$(".topMenu li:nth-child(4) a").addClass("active");
					 });
	
	function enableDatePicker(){
		$(".docDate").datepicker({
			 changeMonth : true,
			 changeYear : true,
			 yearRange : "1900:2015",
			 dateFormat : 'dd-M-yy'
			 });
		$(".docDate2").datepicker({
			changeMonth : true,
			changeYear : true,
			minDate : 0,
			dateFormat : 'dd-M-yy'
			 });
		$(".docDate3").datepicker({
			changeMonth : true,
			changeYear : true,
			minDate : 0,
			maxDate : 0,
			dateFormat : 'dd-M-yy'
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
</script>
<script>
$(".down-div").css("display","none");
$(".uparrow-div").css("display","block");
$('#collapse-div').css('height', '32px');
$('.contactList .listOne ').css('min-height', '46px');
$('ul.listOne li:nth-child(3),ul.listOne li:nth-child(4),ul.listOne li:nth-child(5),ul.listOne li:nth-child(6),ul.listOne li:nth-child(7),ul.listOne li:nth-child(8)').css('display', 'none');
$(".taggs").css("display","none");



function uparrow() {
$(".uparrow-div").css("display","block");
$(".down-div").css("display","none");
/*$('#collapse-div').css('height', '53px');*/
$('.contactList .listOne ').css('min-height', '46px');
$('#collapse-div').animate({height:32},250);
$('ul.listOne li:nth-child(3),ul.listOne li:nth-child(4),ul.listOne li:nth-child(5),ul.listOne li:nth-child(6),ul.listOne li:nth-child(7),ul.listOne li:nth-child(8)').css('display', 'none');
$(".taggs").css("display","none");
}

function down() {
$(".uparrow-div").css("display","none");
$(".down-div").css("display","block");
/*$('#collapse-div').css('height', 'auto');*/
$('#collapse-div').animate({height:'114'},250);
$('ul.listOne li:nth-child(3),ul.listOne li:nth-child(4),ul.listOne li:nth-child(5),ul.listOne li:nth-child(6),ul.listOne li:nth-child(7),ul.listOne li:nth-child(8)').css('display', 'block');
$('.contactList .listOne ').css('height', 'auto');
$(".taggs").css("display","block");
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
.topstaticinfo .listOne{
    min-height: 28px !important;
    height: 100% !important;
}
.topstaticinfo{
height:100%;
}

</style>

<!-- </body>
</html> -->