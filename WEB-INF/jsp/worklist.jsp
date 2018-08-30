<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link href="resources/css/user-detail.css" rel="stylesheet"
	type="text/css" />
<link rel="stylesheet" type="text/css"
	href="resources/css/jquery.dataTables.min.css">
<!-- <script type="text/javascript" src="resources/js/jquery.min.js"></script>  -->
<script type="text/javascript" language="javascript" src="resources/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" language="javascript"
	src="resources/js/dataTables.responsive.min.js"></script>
<script type="text/javascript" src="resources/js/ui/custom.js"></script>
<script src="resources/js/myValidation.js" type="text/javascript"></script>
<!-- <link rel="stylesheet" type="text/css" href="resources/css/loading.css"> -->

<!--For multiSelect checkbox  -->
<script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
<script type="text/javascript" src="resources/js/bootstrap-multiselect.min.js"></script>
<script src="resources/js/ui/jquery.ui.core.js"></script>
<script src="resources/js/ui/jquery.ui.datepicker.js"></script>

<link rel="stylesheet" href="resources/css/bootstrap-multiselect.css"
	type="text/css">
<style>
.search-width-margin {
	display: none !important;
}

#resultTable_filter {
	display: none;
}
.advsrch_select input[type="text"] {
    width: 49% !important;
}

#advancedSearch .col-lg-4.col-md-6.col-sm-12.col-xs-12:nth-of-type(3n-2) label{
  padding-left: 0px !important;
}
#advancedSearch .col-lg-4.col-md-6.col-sm-12.col-xs-12 label{
	padding-left:45px;
	
}


/*.contactList{
border:1px solid black !important;
}
*/
</style>
<script type="text/javascript">
/* $(document).ready(function(){
	
	$(document).bind("copy paste",function(e) {
	     e.preventDefault();
	 });
	
    $(document).on("contextmenu",function(e){       
             e.preventDefault();
     }); 
 }); */

 jQuery(document).ready(function($) {
		$('#tabs').tab();
		enableDatePicker();
	});
 
 
window.onbeforeunload = function (e) {}; 
$('document').ready(function() {
	var expireMsg="${ExpireMsg}";
	 if(expireMsg!=""){
	alert(expireMsg);} 
});


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
		yearRange : "1900:2099",
		//minDate : 0,
		dateFormat : 'dd-mm-yy'
	});
}

function validateFollowupFromToDate(){
	
	var date1 = document.getElementById("datepicker1").value; //'01-02-2016';  Followup From Date
	var date2 = document.getElementById("datepicker2").value;  //'01-01-2018'; Followup To Date

	if ($.datepicker.parseDate('dd-mm-yy', date1) > $.datepicker.parseDate('dd-mm-yy', date2)) {
	       alert('FOLLOWUP FROM DATE MUST BE LESS THAN FOLLOWUP TO DATE');
	       document.getElementById("datepicker1").value = ''; 
	}
	
}




	$('document').ready(function() {
		
		
				document.getElementById("dashboardData").value="${dataFromDashboard}";
				var s =document.getElementById("dashboardData").value;
				if(s=='Dashboard'){
					/* alert("111"); */
					getMyLeadDetails(s);
					/* alert("222"+s); */
				}
				else{
				getMyLeadDetails('myLead');
				}
				if ($("#sort1 option:selected").val() != -1) {
					$('#sort2').prop('disabled', false);
					if ($("#sort2 option:selected").val() != -1) {
						$('#sort3').prop('disabled', false);
					} else {
						$('#sort3').prop('disabled', 'disabled');
					}
				} else {
					$('#sort2').prop('disabled', 'disabled');
					$('#sort3').prop('disabled', 'disabled');
				}
				/* var leadmessage = $('#leadlock').val();
				if (leadmessage !== null && leadmessage !== undefined
						&& leadmessage !== '') {
					alert(leadmessage);
				} */
			});
	function checkAll(source) {

		var checkboxes = document.getElementsByName('leadId');
		for ( var i = 0, n = checkboxes.length; i < n; i++) {
			checkboxes[i].checked = source.checked;
		}
		//$('#loaderDivId').css("display","inline-block");

	}
	function multipleValues(team){
    	var multiple="";
	    c=0;
	    for (var i = 0; i < team.length; i++) {
        	   if (team.options[i].selected){        	        		  
        		   if(c===0){
        			   multiple=team.options[i].value; c++;
        		   }else
        			   multiple=multiple+","+team.options[i].value;     	       
        		  }       	        	   
        	}
	    return multiple;
    }
	function multipleValuesWithQuotes(team){
    	var multiple="";
	    c=0;
	    for (var i = 0; i < team.length; i++) {
        	   if (team.options[i].selected){        	        		  
        		   if(c===0){
        			   multiple="'"+team.options[i].value+"'"; c++;
        		   }else
        			   multiple=multiple+","+"'"+team.options[i].value+"'";     	       
        		  }       	        	   
        	}
	    return multiple;
    }
	var resultTable;
	function getMyLeadDetails(requestType) {
		//alert('dfd');
//debugger
		$('#loaderDivId').css("display","inline-block");

		if (requestType == 'myLead') {
		//alert(requestType.className);
		//alert("dfg");
			//$(".myld-esc-ref li:nth-child(1) button").css("display","none")
			//$(".myld-esc-ref li:nth-child(1) button").addClass("bluecoll");
			//$("#resultTable tbody td:nth-child(2)").css("display","block");
			document.getElementById("escalationRef").value = "MYLEAD";
		} else if (requestType == 'escalated') {

			$("#resultTable tbody td:nth-child(2)").css("display","none");
			document.getElementById("escalationRef").value = "ESCALATE";
		} else if (requestType == 'refered') {
			$("#resultTable tbody td:nth-child(2)").css("display","none");
			document.getElementById("escalationRef").value = "REFER";
		}
		var dataString = "";
		var mobile ="";
		var email ="";
		var name = "";
		var leadCode = "";
		var queue ="";
		var subqueue = "";   
		var disposition = "";
		var actionId = "";   
		var allocate = "";       
		var amountTo = "";
		var amountFrom = "";
		var campain= "";      
		var source = ""; 
		var sort1 = -1;
		var sort2 = -1;
		var sort3 =-1;
		var sortOrder =-1;
		var reqType= $('#escalationRef').val();
		//Added by Sumit on 11 April 2018
		var fromFollowupDate = "";
		var toFollowupDate = "";
		
		if (requestType == 'search') {
					
			var date1 = document.getElementById("datepicker1").value; //'01-02-2016';  From FollowupDate
			var date2 = document.getElementById("datepicker2").value;  //'01-01-2018';  To FollowupDate
			
			if(!(date2==null || date2.trim() === '') ){				
				if((date1==null || date1.trim() === '') ){
					alert('FOLLOWUP FROM DATE IS MANDATORY');
					return false;
				}				
			}
			
			if(!(date1==null || date1.trim() === '') ){				
				if( date2==null || date2.trim() === ''  ){				
					alert('FOLLOWUP TO DATE IS MANDATORY');
					return false;
				}							
			}
			
			if(!(date1==null || date1.trim() === '') && !(date1==null || date1.trim() === '') ){						
				if ($.datepicker.parseDate('dd-mm-yy', date1) > $.datepicker.parseDate('dd-mm-yy', date2)) {			  
					alert('FOLLOWUP FROM DATE SHOULD BE LESS THAN FOLLOWUP TO DATE');
					document.getElementById("datepicker1").value = '';
				    return false;
				}
			
			}
			
			
			 queue = multipleValues(document.getElementById('queue1'));
			 subqueue =multipleValues(document.getElementById('subqueue1'));    
			 disposition =multipleValues(document.getElementById('disposition1')); 
			 if(disposition!=""){
			 actionId =multipleValuesWithQuotes(document.getElementById('actionId1'));}    
			 allocate =multipleValues(document.getElementById('allocate1'));        
			 amountTo = $("#amountTo1").val();
			 amountFrom = $("#amountFrom1").val();
			 campain=multipleValues(document.getElementById('campaignId'));       
			 source = multipleValues(document.getElementById('source1')); 
			 sort1 = $("#sort11").val();
			 sort2 = $("#sort21").val();
			 sort3 = $("#sort31").val();
			 sortOrder = $("#sortOrder1").val();
			 
			 /*After same serach btn*/
			 mobile = $("#mobile").val();
			 email = $("#email").val();
			 name = $("#name").val();
			 leadCode = $("#caseCode").val();
			 
			 fromFollowupDate = $("#datepicker1").val();
			 toFollowupDate = $("#datepicker2").val();
			 
			 
			dataString = "requestType=" + requestType + '&mobile=' + mobile
					+ '&email=' + email + '&name=' + name + '&caseCode=' + leadCode
					+ '&queue=' + queue + '&subqueue=' + subqueue
					+ '&disposition=' + disposition + '&actionId=' + actionId
					+ '&allocate=' + allocate + '&amountTo=' + amountTo
					+ '&amountFrom=' + amountFrom + '&source=' + source
					+ '&sort1=' + sort1 + '&sort2=' + sort2 + '&sort3=' + sort3
					+ '&sortOrder=' + sortOrder + '&escalationRef=' + reqType + '&campaign=' + campain;

		} /* else if (requestType == 'search') {

			 mobile = $("#mobile").val();
			 email = $("#email").val();
			 name = $("#name").val();
			 leadCode = $("#caseCode").val();
				dataString = "requestType=" + requestType + '&mobile=' + mobile
					+ '&email=' + email + '&name=' + name + '&caseCode='
					+ leadCode + '&queue=' + '' + '&subqueue=' + ''
					+ '&disposition=' + '' + '&actionId=' + '' + '&allocate='
					+ '' + '&amountTo=' + '' + '&amountFrom=' + '' + '&source='
					+ '' + '&sort1=' + '' + '&sort2=' + '' + '&sort3=' + ''
					+ '&sortOrder=' + '' + '&escalationRef=' + reqType+ '&campaign=' + '';
			
		} */else if(requestType == 'Dashboard'){

			
			var url_string = window.location.href; // www.test.com?filename=test
		    var url = new URL(url_string);
		    var paramValue = url.searchParams.get("id");
		    
		    team = document.getElementById('actionId1');
		    
		    var obj=$('#actionId1');
		    
		    //var bla = $('#txt_name').val();
		    $('.toggle-icon').removeClass('plus-icon1');
			$('.toggle-icon').addClass('minus-icon1');


		    for (var i = 0; i < team.length; i++) {		    	
		    	if(paramValue === team.options[i].text){
		    		
		    		$("#actionId1 option[value='" + team.options[i].value + "']").attr("selected", 1);
		    		
		    		//obj.find('option:[value='+paramValue+']').attr('selected',1);
		    		// Login to make the box as selected
		    	}
		    }
		    /* var multiple="";
			c=0;
	    	for (var i = 0; i < team.length; i++) {
        	   if (team.options[i].selected){        	        		  
        		   if(c===0){
        			   multiple="'"+team.options[i].value+"'"; c++;
        		   }else
        			   multiple=multiple+","+"'"+team.options[i].value+"'";     	       
        		}       	        	   
        	}
			    //return multiple;
			    alert('multiple '+multiple); */
		    
		    
		    
		    
			requestType = 'advancesearch';
			dataString="${dashboardRequest}";			
			document.getElementById("dashboardData").value="";
			var dataString1="${dashboardRequest1}";
			//alert(dataString1);
			var dataSplit=dataString1.split("~");
			var data=dataSplit[0].split("=");
			//alert("--"+data[1]);
			if(data[1]!=-1){
				queue =data[1];	
			}
			data=dataSplit[1].split("=");
			//alert("--"+data[1]);
			
			if(data[1]!=-1){
				subqueue =data[1];	
			}
			data=dataSplit[2].split("=");
			//alert("--"+data[1]);
			
			if(data[1]!=-1){
				allocate =data[1];	
			}
			data=dataSplit[3].split("=");
			//alert("--"+data[1]);
			
			if(data[1]!=-1){
				source =data[1];	
			}
			data=dataSplit[4].split("=");
			//alert("--"+data[1]);
			
			if(data[1]!=-1){
				campain =data[1];	
			}
			data=dataSplit[5].split("=");	
			//alert("--"+data[1]);
			
				actionId =data[1];	
		  
			
		}
		 else {
			
			dataString = "requestType=" + requestType + '&mobile=' + ''
					+ '&email=' + '' + '&name=' + '' + '&caseCode=' + ''
					+ '&queue=' + '' + '&subqueue=' + '' + '&disposition=' + ''
					+ '&actionId=' + '' + '&allocate=' + '' + '&amountTo=' + ''
					+ '&amountFrom=' + '' + '&source=' + '' + '&sort1=' + ''
					+ '&sort2=' + '' + '&sort3=' + '' + '&sortOrder=' + ''
					+ '&escalationRef=' + reqType + '&campaign=' + '';
		}
		//alert(dataString);
		
		if (resultTable != null ) {
			resultTable.destroy();
		}
		//debugger
// 		$('#loaderDivId').css("display","inline-block");
		
		/* $
				.ajax({
					async : true,
					type : "post",
					url : "getLeadsDetails.do",
					dataType : "json",
					context : document.body,
					data : dataString,
					success : function(response) { */
						//$('#loaderDivId').hide();
						try {
							resultTable = $('#resultTable')
									.DataTable(
											{"responsive": true,
												"bProcesing" : true,
												   "bServerSide" : true,
												   "bLenthChange" : true,
												   "iDisplayLength" : 10,
												   "sAjaxSource" : "getLeadsDetails.do",
												   "aoColumns" : [												                  
												                  {												                	  
												                	  /* "sTitle" : '<input type="checkbox" checked id="leadid" onClick="checkAll(this,\'N\');" />', */
												                      "mData" : "checked",
												                      "mRender" : function(
																				data, type,
																				row) {												                    	
												                    	  //var td = '<input type="checkbox" name="leadId" value="' + row.leadId+ '~' + row.allocatedTo+ '">';
												                    	  	
												                    	  	//alert("customerName "+row.customerName);
												                    	  	//alert(row.followUp_actionId);
												                    	  	
												                    	  	
																			if (requestType == 'myLead') {
																				//return td+ " "+ row.imageTag;																				
																				if(row.prospectId =='Converted' ){	
																					return '<input disabled="disabled" type="checkbox" name="leadId" value="' + row.leadId+ '~' + row.allocatedTo+ '">' +" "+ row.imageTag; ;
																				}else{
																					return '<input type="checkbox" name="leadId" value="' + row.leadId+ '~' + row.allocatedTo+ '">' +" "+ row.imageTag; ;
																				}
																				
																			} else {
																				
																				/* alert('Hi');
																				return '<input disabled="disabled" type="checkbox" name="leadId" value="' + row.leadId+ '~' + row.allocatedTo+ '">'; */
																				
																				if(row.prospectId =='Converted' ){	
																					return '<input disabled="disabled" type="checkbox" name="leadId" value="' + row.leadId+ '~' + row.allocatedTo+ '">' +" "+ row.imageTag; ;
																				}else{
																					return '<input type="checkbox" name="leadId" value="' + row.leadId+ '~' + row.allocatedTo+ '">';
																				}
																				
																			}
																		}
												                     },
												                     //{												                	  
													                	  /* /* "sTitle" : '<input type="checkbox" checked id="leadid" onClick="checkAll(this,\'N\');" />', */
													                      /*"mData" : "convertToCustomer",
													                      "mRender" : function(data, type,row) {
													                    	  var td = '<button value="'+row.leadId+'">convertToCustomer</button>';// '<input type="button" name="button" value="'+row.leadId+'">';
													                    	  return td;
																			}*/
																			
																			/*"sTitle" :  "Convert to Customer",															                  
															                   "mRender" : function(data, type,row) {
																						//alert(type);																			
																						if (type === 'display') {
																							return '<a href="convertToCustomer.do?caseId='+row.leadId+'" >ConvertToCustomer</a>'
																						}
																						return data;
																				}																		
													                  },*/												                 
																	  {
														                   "sTitle" :  "Lead Id",
														                   "mData" : 'leadCode',
														                   "mRender" : function(
																					data, type,
																					row) {
																					//alert(data);																			
																					if (type === 'display') {
																						return '<a href="contact.do?caseId='
																								+ row.leadId
																								+ '" >'
																								+ data
																								+ '</a>'
																					}

																					return data;

																			}
																	},
																	{
														                   "sTitle" :  "Queue",
														                   "mData" : 'queue'
																	},
																	{
														                   "sTitle" :  "Sub Queue",
														                   "mData" : 'subQueue'
																	} ,
																	{
														                   "sTitle" :  "Customer Name",
														                   "mData" : 'customerName'
																	},
																	{
														                   "sTitle" :  "Amount",
														                   "mData" : 'amount'
																	},
																	{
														                   "sTitle" :  "Action Name",
														                   "mData" : 'actionName'
																	},
																	{
														                   "sTitle" :  "Follow Up Action",
														                   "mData" : 'followUp_actionId'
																	},
																	{
														                   "sTitle" :  "Follow Date",
														                   "mData" : 'followUpDate'
																	}
																	
																	
																	/* ,
																	{
														                   "sTitle" :  "Bank Name",
														                   "mData" : 'bankName'
																	}  */
													
														  		 ],
														  		 "fnServerParams" : function(aoData) {
														  			aoData.push({"name" : "mobile","value" : mobile});
														  			aoData.push({"name" : "requestType","value" :requestType});
														  			aoData.push({"name" : "email","value" :email });
														  			aoData.push({"name" : "name","value" :name });
														  			aoData.push({"name" : "caseCode","value" :leadCode });
														  			aoData.push({"name" : "queue","value" :queue });
														  			aoData.push({"name" : "subqueue","value" :subqueue });
														  			aoData.push({"name" : "disposition","value" :disposition });
														  			aoData.push({"name" : "actionId","value" :actionId });
														  			aoData.push({"name" : "allocate","value" : allocate});
														  			aoData.push({"name" : "amountTo","value" : amountTo});
														  			aoData.push({"name" : "amountFrom","value" :amountFrom });
														  			
														  			aoData.push({"name" : "fromFollowupDate","value" :fromFollowupDate });
														  			aoData.push({"name" : "toFollowupDate","value" :toFollowupDate });
														  			
														  			aoData.push({"name" : "source","value" :source });
														  			aoData.push({"name" : "sort1","value" :sort1 });
														  			aoData.push({"name" : "sort2","value" :sort2 });
														  			aoData.push({"name" : "sort3","value" : sort3});
														  			aoData.push({"name" : "sortOrder","value" :sortOrder });
														  			aoData.push({"name" : "escalationRef","value" :reqType });
														  			aoData.push({"name" : "campaign","value" :campain });

														  			
														  		}, 
															  "fnServerObjectToArray" : function(sSource, aoData, fnCallback) {
																  //alert(aoData);
															  
																    $.ajax({
																    	
																     "dataType" : 'json',
																     "type" : "GET",
																     "url" : sSource,
																     "data" : aoData,
																     "success" : fnCallback
																    });
																   },
																   "sPaginationType" : "full_numbers"

																  });
						} catch (e) {
							//alert(e);	
						}
					
						$('#loaderDivId').css("display","none");
	}
					
function getleadStatevalue() {
	var disposition =multipleValues(document.getElementById('disposition1')); 	
	$("#actionId1").next("div").find("ul input[type='checkbox']").removeAttr("checked");
	$.ajax({

		async : true,
		type : "post",
		url : "getLeadStateValue.do",		
		context : document.body,
		data : "disposition="+disposition,
		success : function(response) {			
			var leadStates=response.split("~");			
			
			for(var i=0;i<leadStates.length;i++){	
				
			$("[value='"+leadStates[i]+"']").trigger("click");
			$("#actionId1").next(".btn-group").find(".multiselect-selected-text").text($("#actionId1").next(".btn-group").find("ul input[type='checkbox']:checked").length + ' Selected ');
			if($("#actionId1").next(".btn-group").find("ul input[type='checkbox']:checked").length==0){$("#actionId1").next(".btn-group").find(".multiselect-selected-text").text("None selected");}
			}
			
		}
	});
	} 
	/* function showtd(){
	
	 setTimeout(function(){ 
	 $("#resultTable tbody td:nth-child(2)").css("display","block");
	 }, 1500);
	
	 }

	 function hidetd(){
	 setTimeOut(function(){ 
	 $("#resultTable tbody td:nth-child(2)").css({"display":"none"});
	 }, 1500);
	
	
	
	
	 } */
</script>
<style>
table.dataTable thead {
	background-color: #DAE1E3;
}
</style>
<!-- for loading div -->
<div id="loaderDivId" style="display: none;">
	<img src="image/ajax-loader.gif" class="ajax-loader" />
</div>

<%-- <c:if test="${not empty msgForleadLocking}">
	<div>
		<input type="text" id="leadlock" name="leadlock" value="${msgForleadLocking}">
	</div>
</c:if> --%>


<input type="hidden" id="dashboardData" />
<div class="container-fluid">
	<div
		class="padding-top-container col-lg-12 col-md-12 col-sm-12 col-xs-12">

		<div class="row ">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 buttonHeading"
				style="padding-bottom: 0px; border: none !important; ">
				<!--<h2 class="heading">Search</h2> -->

				<div class="worklistbtn-search">
					<a class="toggle-list btn-primary" style="background:none!important;"> <span
						class="toggle-icon plus-icon1 plus-icon-2"></span>
					</a>
				</div>

			</div>
			<div
				class="col-lg-12 col-md-12 col-sm-12 col-xs-12 contactList leadstatus searchArea search-togg align-center-div">
			<!-- 	<div class="searchfieldss col-xs-12">
					
				</div>
				<div class="searchfieldss col-xs-12">
					
				</div>
				<div class="searchfieldss col-xs-12">
					
				</div>
				<div class="searchfieldss col-xs-12">
					
				</div>
				<div class="searchfieldss col-xs-12 worklist-searchbtn">
					
				</div> -->

				<div class="col-lg-4 col-md-6 col-sm-12 col-xs-12 advsrch_select">
					<label><spring:message code="label.mobile" /></label> <input
						type="text" placeholder="Mobile" name="mobile" id="mobile"
						maxlength="10" onchange="validateMobile(this);" class="rgtAlign" />
				</div>
				<div class="col-lg-4 col-md-6 col-sm-12 col-xs-12 advsrch_select">
					<label style="padding-left: 13%;"><spring:message code="label.email" /></label> <input
						type="text" placeholder="Email" name="email" id="email"
						maxlength="100" onchange="validateEmail(this);" />
				</div>
				<div class="col-lg-4 col-md-6 col-sm-12 col-xs-12 advsrch_select">
					<label  style="padding-left: 13%;"><spring:message code="label.name" /></label> <input
						type="text" placeholder="Name" name="name" id="name"
						maxlength="390" />
				</div>
				<div class="col-lg-4 col-md-6 col-sm-12 col-xs-12 advsrch_select" style="margin-top:7px;">
					<label><spring:message code="label.leadId" /></label> <input
						type="text" placeholder="Lead Id" name="caseCode" id="caseCode"
						maxlength="10" />
				</div>
				
				
				<!-- <div class="col-lg-4 col-md-6 col-sm-12 col-xs-12 advsrch_select" style="margin-top:7px;">
					<label>Follow Up Date</label> <input type="text" class="docDate1" id="datepicker11"
						placeholder="Follow Up Date" name="followupDate"> <span
						class="dobCalc glyphicon glyphicon-calendar" for="datepicker"
						aria-hidden="true"
						style="position: absolute; top: 3px; right: 39px !important;"></span>
				</div> -->
				<%-- <div class="col-lg-4 col-md-6 col-sm-12 col-xs-12 advsrch_select" style="margin-top:7px;">
							<label><spring:message code="label.followUpTime"/></label> <input type="text"
								 style="width:50%;" id="followupTime" name="followupTime" placeholder="Follow Up Time"
								class="clockpicker pull-left" />
						</div> --%>
				
				
										
				<%-- 	<div style="position: relative"><label><spring:message code="label.followUpAction"/></label> <span
						class="sel-ttip"><select name="followupAction"
							id="followupAction" draggable="false">
							<option value="-1">select</option>
								<c:forEach var="disposition"
									items="${masterDetail.dispositionMaster}">
									<option value="${disposition.actionStage}">${disposition.actionStage}</option>
								</c:forEach>
						</select></span> <span class="select-tooltip"
						style="margin-top: -15px; background: #fff; border: solid 1px #ccc"></span>
					</div>
					
					
					<div style="position: relative;"><label><spring:message code="label.followUpDate"/>
						</label> <input type="text" class="docDate1" id="datepicker11"
						placeholder="Follow Up Date" name="followupDate"> <span
						class="dobCalc glyphicon glyphicon-calendar" for="datepicker"
						aria-hidden="true"
						style="position: absolute; top: 3px; right: 39px !important;"></span>
					</div>
					
					<div>
						<div>
							<label><spring:message code="label.followUpTime"/></label> <input type="text"
								id="followupTime" name="followupTime" placeholder="Follow Up Time"
								class="clockpicker pull-left" />
						</div>
					</div>
				 --%>
				
				
				<div class="col-lg-8 col-md-8 col-sm-12 col-xs-12 advsrch_select text-right">
					<input type="submit" value="Search"
						class="saveBtn1 btn btn-primary"
						onclick="getMyLeadDetails('search');">
				</div>

			</div>

		</div>
		<div class="row contactList leadstatus border-top leadstatusCng"
			id="advancedSearch">
			<!--<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 buttonHeading">
				<h2 class="heading">Advance Search</h2>
			</div>-->
			<div class="col-lg-4 col-md-6 col-sm-12 col-xs-12 advsrch_select">
				<label><spring:message code="label.queue" /></label> <select
					class="span_6_of_12 fl" name="queue" id="queue1"
					multiple="multiple">

					<c:forEach var="queue" items="${masterDetail.productMaster}">
						<option value="${queue.prodId }">${queue.prodName}</option>
					</c:forEach>
				</select>
			</div>
			<div
				class="col-lg-4 col-md-6 col-sm-12 col-xs-12 sortorder_div sortorder_div_tenin">
				<label><spring:message code="label.subQueue" /></label> <select
					class="span_6_of_12 fl" name="subqueue" id="subqueue1"
					multiple="multiple">

					<c:forEach var="subQueue" items="${masterDetail.subQueueMaster }">
						<option value="${subQueue.subQueueId}">${subQueue.subQueue}</option>
					</c:forEach>
				</select>
			</div>

			<div class="col-lg-4 col-md-6 col-sm-12 col-xs-12 advsrch_select">
					<label class="LabelHover">Desposition ...<span style="display:none;">Desposition Status</span></label> 
					 <select
					class="span_6_of_12 fl" name="disposition" id="disposition1"
					onchange="getleadStatevalue()" multiple="multiple">

					<c:forEach var="leadstat" items="${masterDetail.caseActionMaster}">
						<option value="${leadstat.actionId}">${leadstat.actionName}</option>
					</c:forEach>
					
				</select>
			</div>
			<div class="col-lg-4 col-md-6 col-sm-12 col-xs-12 advsrch_select">
				<label><spring:message code="label.leadState" /></label> <select
					class="span_6_of_12 fl" name="actionId" id="actionId1"
					multiple="multiple">

					<%-- <c:forEach var="disposition"
						items="${masterDetail.dispositionMaster}">
						<option value="${disposition.actionStage}">${disposition.actionStage}</option>
					</c:forEach> --%>
					
					<c:forEach var="obj" items="${masterDetail.stageMaster}">
							<option value="${obj.stageId }">${obj.stageName}</option>
					</c:forEach>
						
						
					
				</select>
			</div>
			
			<div class="col-lg-4 col-md-6 col-sm-12 col-xs-12 advsrch_select">
				<label><spring:message code="label.campaign" /></label> <select
					class="span_6_of_12 fl" name="campaign" id="campaignId"
					multiple="multiple">

					<c:forEach var="campaign" items="${masterDetail.campaignMaster}">
						<option value="${campaign.campaignId}">${campaign.campaignName}</option>
					</c:forEach>
				</select>
			</div>

			<div class="col-lg-4 col-md-6 col-sm-12 col-xs-12 advsrch_select">
				<label><spring:message code="label.allocatedTo" /></label> <select
					class="span_6_of_12" name="allocate" id="allocate1"
					multiple="multiple">
					<c:forEach var="myTeamLst" items="${myTeamList}">
						<option value="${myTeamLst.userid}">
							${myTeamLst.userfname}</option>
					</c:forEach>
				</select>
			</div>
			<div class="col-lg-4 col-md-6 col-sm-12 col-xs-12 advsrch_select">
				<label><spring:message code="label.amountFrom" /></label> <input
					type="text" name="amountFrom" id="amountFrom1" maxlength="12"
					style="width: 49.1% !important;" class="rgtAlign" />

			</div>
			<div class="col-lg-4 col-md-6 col-sm-12 col-xs-12 advsrch_select">
				<label><spring:message code="label.amountTo" /></label> <input
					type="text" name="amountTo" id="amountTo1" maxlength="12"
					style="width: 49.1% !important;" class="rgtAlign" />

			</div>
			
			
			<!--  Added by Sumit For Aye CR-->
			
			<div class="col-lg-4 col-md-6 col-sm-12 col-xs-12 advsrch_select">
				<label>Follow up from date</label> 				
				<input type="text" class="docDate1" id="datepicker1"
						placeholder="Follow Up Date" name="fromFollowupDate">					
				<!-- <input
					type="text" name="amountTo" id="amountTo1" maxlength="12"
					style="width: 49.1% !important;" class="rgtAlign" /> -->
			</div>
			
			<div class="col-lg-4 col-md-6 col-sm-12 col-xs-12 advsrch_select">
				<label>Follow up to date</label> 				
				<input type="text" class="docDate1" id="datepicker2"
						placeholder="Follow Up Date" name="toFollowupDate" onchange="validateFollowupFromToDate();">						
				<!-- <input
					type="text" name="amountTo" id="amountTo1" maxlength="12"
					style="width: 49.1% !important;" class="rgtAlign" /> -->
			</div>
			
			
			
			<div class="col-lg-4 col-md-6 col-sm-12 col-xs-12 advsrch_select">
				<label><spring:message code="label.source" /></label> <select
					class="span_6_of_12 fl" multiple="multiple" name="source"
					id="source1">
					<c:forEach var="prod" items="${masterDetail.sourceMaster}">
						<option value="${prod.caseSourceId }">${prod.sourceName}</option>
					</c:forEach>
				</select>
			</div>
			<!-- </div><div class="row borderBox"> -->
			<div class="col-lg-8 col-md-6 col-sm-12 col-xs-12 advsrch_select contactList leadstatus border-diplay-none" style="padding-left:15px !important;">
				<div class="">
					<label class="minWidthLabel sortby_label"><spring:message
							code="label.sortBy" /></label> <select class="minWidthSelect"
						name="sort1" id="sort11" onchange="enableSort();">
						<option value="-1" selected>Select</option>
						<option>Queue</option>
						<option>Sub-Queue</option>
						<option>Source</option>
						<option>Disposition status</option>
						<option>Lead State</option>
					</select> <select class="minWidthSelect" name="sort2" id="sort21"
						onchange="enableSort();">
						<option value="-1" selected>Select</option>
						<option>Queue</option>
						<option>Sub-Queue</option>
						<option>Source</option>
						<option>Disposition status</option>
						<option>Lead State</option>
					</select> <select class="minWidthSelect" name="sort3" id="sort31">
						<option value="-1" selected>Select</option>
						<option>Queue</option>
						<option>Sub-Queue</option>
						<option>Source</option>
						<option>Disposition status</option>
						<option>Lead State</option>
					</select>
				</div>

				<!-- <div class="col-lg-1 col-md-1 col-sm-2 col-xs-12 adv_search">
				<input type="submit" value="Search" style="display: block;"
						class="searchbtn1 saveBtn1 btn btn-primary pull-right"
						onclick="getMyLeadDetails('advancesearch');"> <input
						type="hidden" name="escalationRef" id="escalationRef"
						value="MYLEAD" />
				</div> -->
			</div>
			<div class="col-lg-4 col-md-3 col-sm-3 col-xs-12 leadstatus">
				<label style="     padding-left: 11%;"  class="orderByClass"><spring:message
						code="label.sortOrder" /></label> <select name="sortOrder" id="sortOrder1">
					<option value="-1" selected>Select</option>
					<option value="desc">Descending</option>
					<option value="asc">Ascending</option>
				</select> <input type="hidden" name="escalationRef" id="escalationRef"
					value="MYLEAD" />
			</div>
		</div>
		<div class="row shifttop">
			<div
				class="searchButton mylead_esc_ref col-lg-8 col-md-8 col-sm-8 col-xs-10 myld-esc-ref"
				style="padding-left: 0 !important">
				<ul class="tab-left">
					<li>
						<button type="button" class="btn makewhitebtn"
							onclick="getMyLeadDetails('myLead');">MY LEADS</button>
					</li>
					<li>
						<button type="button" class="btn makewhitebtn"
							onclick="getMyLeadDetails('escalated');">ESCALATED</button>
					</li>
					<li>
						<button type="button" class="btn makewhitebtn"
							onclick="getMyLeadDetails('refered');">REFERRED</button>
					</li>
				</ul>
			</div>
			<!-- <div class="col-lg-4 col-md-4 col-sm-4 col-xs-2 btnPosition">
				<a class=" btn-primary toggle-list">
					<span class="toggle-icon minus-icon1"></span>
				</a>
			</div> -->
		</div>

		<div
			class="row list-content table-responsive marg-pad-0 worklist-datatable"
			style="width: 102% !important; overflow: visible">
			<div id="resultTableDiv" class="summary-block pad active">
				<table id="resultTable" cellspacing="0" width="100%">
				</table>
			</div>
		</div>

		<div class="row buttonHeading" style="clear: both">
			<div class="col-lg-9 col-md-9 col-sm-9 col-xs-10">
				<h2 class="heading lineHeightCstm">Reallocation</h2>
			</div>
			<div class="col-lg-3 col-md-3 col-sm-3 col-xs-2 btnPosition">
				<a class="toggle-list btn-primary" style="background:none !important;"> <span
					class="toggle-icon minus-icon1 "></span>
				</a>
			</div>
		</div>
		<div class="row contactList leadstatus reallocattion">
			<div class="col-lg-3 col-md-3 col-sm-12 col-xs-12 remarksCol">
				<label class="minWidthLabel1"><spring:message
						code="label.toQueue" /></label> <select class="span_6_of_12 "
					id="queueIdforAllocate">
					<option value="-1" selected>Select</option>
					<c:forEach var="queue" items="${masterDetail.productMaster}">
						<option value="${queue.prodId }">${queue.prodName}</option>
					</c:forEach>
				</select>

			</div>
			<div class="col-lg-3 col-md-3 col-sm-12 col-xs-12 remarksCol">
				<label class="minWidthLabel1"><spring:message
						code="label.allocateTo" /></label> <select class="span_6_of_12 "
					id="AllocatesIdforAllocate">
					<option value="-1" selected>Select</option>
					<c:forEach var="peerAndreportee" items="${peerAndreportee}">
						<option value="${peerAndreportee.userId }">${peerAndreportee.userName}</option>
					</c:forEach>
				</select>

			</div>
			<div class="col-lg-3 col-md-3 col-sm-12 col-xs-12"
				style="margin-top: 10px;">
				<label class="minWidthLabel1"><spring:message
						code="label.remarks" /></label>
				<!-- 
				<textarea name="remark" id="remark" maxlength="10000"></textarea>
				 -->
				<div
					class="col-lg-7 col-md-3 col-sm-3 col-xs-12 padding-left-img worklist-padding-input">
					<a href="" data-toggle="modal" data-target="#myModal2"
						data-backdrop="static" data-keyboard="false" onclick="setData()"><img
						src="./image/edit_img.png" alt=""
						class="icon-image worklist-iconmodel"></a> <input type="text"
						name="remark" id="remark"
						class="col-lg-12 col-md-12 col-sm-12 col-xs-12 setitwidth" />
				</div>
			</div>


			<div
				class="col-lg-1 col-md-1 col-sm-12 col-xs-12 remarksCol pull-right">
				<input type="submit" class="saveBtn btn btn-success" value="Submit"
					onclick="allocateLead()">
			</div>
		</div>
	</div>
</div>
<!-- Modal -->
<div class="modal fade" id="myModal2" role="dialog"
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
	$(document).ready(
			function() {
				var leadid = '${sessionScope.leadid}';
				var error = '${sessionScope.error}';
				var message = '${sessionScope.errorMessage}';
				var msgForleadLocking='${msgForleadLocking}';
				var msgForEscalateBlock='${msgForEscalateBlock}';
				
				
				if (leadid !== null && leadid !== undefined && leadid !== '' && leadid !== 'null') {
					alert('Your New Lead Id = ' + leadid);
				}
				;
				if (error !== 'S' && error !== null && error !== undefined		&& error !== '' && message !== null	&& message !== undefined && message !== '') {
					alert('Error : ' + error + "\nDescription : " + message);
				}
				;
				if (error !== 'S' && error !== null && error !== undefined	&& message === null && message === undefined && message === '') {
					alert('Error : ' + error);
				}
				if(msgForleadLocking!=null && msgForleadLocking!=undefined && msgForleadLocking!='' && msgForleadLocking!='null' ){
					alert(msgForleadLocking);
				}
				if(msgForEscalateBlock!=null && msgForEscalateBlock!=undefined && msgForEscalateBlock!='' && msgForEscalateBlock!='null' ){
					alert(msgForEscalateBlock);
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

	$(document).ready(function() {
		$("#advancedSearch").hide();
		enableSort();
	});

	function enableSort() {

		if ($("#sort11 option:selected").val() != -1) {
			$('#sort21').prop('disabled', false);
			if ($("#sort21 option:selected").val() != -1) {
				$('#sort31').prop('disabled', false);
			} else {
				$('#sort31').prop('disabled', 'disabled');
				document.getElementById('sort31').value = -1;
			}
		} else {
			document.getElementById('sort21').value = -1;
			document.getElementById('sort31').value = -1;
			$('#sort21').prop('disabled', 'disabled');
			$('#sort31').prop('disabled', 'disabled');
		}
	}

	function allocateLead() {
		var allocId = document.getElementById('AllocatesIdforAllocate').value;
		var queueId = document.getElementById('queueIdforAllocate').value;
		var remark = document.getElementById('remark').value;
		
		if (allocId == -1) {
			alert('Please Select Allocated To');
			return false;
		}
		if (queueId == -1) {
			alert('Please Select Queue To');
			return false;
		}
		if (remark == "") {
			alert('Please Enter Remarks');
			return false;
		}

		//alert(remark);
		var selectedRec = [];
		$.each($("input[name='leadId']:checked"), function() {
			selectedRec.push($(this).val());
		});
		/*  for(var i=0;i<selectedRec.length;i++){	  
		 alert(selectedRec[i]);
		 } */

		$.ajax({
			async : true,
			type : "post",
			url : "saveAllocatedLead.do",
			//dataType:"json", 
			context : document.body,
			data : "myArray=" + selectedRec + "&allocatedId=" + allocId
					+ "&remark=" + remark + "&queueId=" + queueId,
			success : function(response) {

			}
		});
		getMyLeadDetails('myLead');
		document.getElementById('AllocatesIdforAllocate').value = -1;
		document.getElementById('queueIdforAllocate').value = -1;
		document.getElementById('remark').value = "";

	}

	$(".worklistbtn-search").click(function(){
	
	});
	
	
	$('.toggle-list').click(
			function() {

				$(this).parent('.worklistbtn').parent().parent().next('.row')
						.slideToggle(150);
				//$(this).parent('.list-header').next('.list-content').slideToggle(150);
//if()
//$("#content").next(".btnPosition")

				if ($(this).children('span').hasClass('minus-icon1')) {
			
				$(this).parent().parent().parent().next("#advancedSearch").slideUp(150);
					$(this).parent().parent('.tablular-list').css({
						'margin-bottom' : '5px'
					});
					$(this).parent('.list-header').css({
						'border-radius' : '5px'
					});

					$(this).children('span').removeClass('plus-icon1');
					$(this).children('span').addClass('minus-icon1');
				} else {
				$(this).parent().parent().parent().next("#advancedSearch").slideDown(150);
					$(this).parent().parent('.tablular-list').css({
						'margin-bottom' : '0px'
					});
					$(this).parent('.list-header').css({
						'border-radius' : '5px 5px 0 0'
					});
					$(this).parent('.tablular-list')
							.css('margin-bottom', '0px');
					$(this).children('span').removeClass('minus-icon1');
					$(this).children('span').addClass('plus-icon1');
					//alert("sdfs");
				}
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
</script>
<script type="text/javascript">
	$('document').ready(function(){		
		 $('#queue1').multiselect({includeSelectAllOption: true,buttonWidth: '143px' });
		 $('#subqueue1').multiselect({includeSelectAllOption: true,buttonWidth: '143px' });
		 $('#disposition1').multiselect({includeSelectAllOption: true,buttonWidth: '143px' });
		 $('#actionId1').multiselect({includeSelectAllOption: true,buttonWidth: '143px' });
		 $('#allocate1').multiselect({includeSelectAllOption: true,buttonWidth: '143px' });
		 $('#source1').multiselect({includeSelectAllOption: true,buttonWidth: '143px' });
		 $('#campaignId').multiselect({includeSelectAllOption: true,buttonWidth: '143px' });
	});
	
	
	/* $(window).load(function(){	
			$(document).on("click", "#leadid",function(){
		if($(this).is(":checked")){
			alert("1");
	    $("input[name='leadId']").prop("checked","true");
	    }else{
	    		alert("2");
	           $("input[name='leadId']").prop("checked","false");
	   
	    	}
		});
	}); */
	
	$(window).load(function(){
	//$("#resultTable_wrapper #resultTable_length").remove();
	//	$("#resultTable_length").appendTo($(".mylead_esc_ref"));
	});
	$(".myld-esc-ref li:nth-child(1) button").click(function(){
	
 $(".myld-esc-ref li button").not($(this)).addClass("makewhitebtn");

  $(this).addClass("myleadbg").removeClass("makewhitebtn");
});

$(".myld-esc-ref li:nth-child(2) button").click(function(){
 $(".myld-esc-ref li button").not($(this)).addClass("makewhitebtn");
  $(this).addClass("escbg").removeClass("makewhitebtn");

  });

$(".myld-esc-ref li:nth-child(3) button").click(function(){
 $(".myld-esc-ref li button").not($(this)).addClass("makewhitebtn");
  $(this).addClass("refbg").removeClass("makewhitebtn");

  });
  
  function setData(){
	var data=$('#remark').val();
	$('#modalRemark').val(data);  
  }
  function setData1(){
		var data=$('#modalRemark').val();
		$('#remark').val(data);  
	  }
 

</script>
<style>
#advancedSearch>div:nth-child(7) input, #advancedSearch>div:nth-child(8) input
	{
	width: 56.5% !important;
}

#advancedSearch .minWidthSelect {
	width: 27.2% !important;
}

#amountTo1, #amountFrom1 {
	width: 56.5% !important;
}

.contactHeader {
	border-bottom: solid 1px #DDD !important;
}

.worklist-padding-input {
	width: 62% !important;
}
/*#*/
#resultTable thead tr th:nth-last-child(1) {
	width: 82px !important;
}

#resultTable thead tr th:nth-last-child(2) {
	width: 96px !important;
}

.leadstatus span.multiselect-selected-text {
	line-height: 30px;
}

#advancedSearch .btn-group button, .row.borderBox .btn-group button {
	overflow: inherit !important;
}

.searchList {
	margin-top: 8px !important;
}

.col-xs-12.setitwidth#remark {
	margin-top: 3px !important;
}

.contactHeader {
	border-bottom: 5px solid #00698C !important;
	padding-bottom: 3PX;
}
</style>