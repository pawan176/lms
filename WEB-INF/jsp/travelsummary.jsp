<link rel="stylesheet" href="resources/css/base/jquery.ui.all.css">
<link href="resources/css/style.css" rel="stylesheet">
<script src="resources/js/ui/jquery.ui.core.js"></script>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<script src="resources/js/ui/jquery.ui.datepicker.js"></script>
<link href="resources/css/user-detail.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css"	href="resources/css/jquery.dataTables.min.css">
<script type="text/javascript" language="javascript"	src="resources/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" language="javascript"	src="resources/js/dataTables.responsive.min.js"></script>
<script type="text/javascript" language="javascript"	src="resources/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" language="javascript"	src="resources/js/dataTables.responsive.min.js"></script>

<!-- New Page Added by Anuj on 18-jan-2017 -->
<style>
.mainHeading{
padding-top:9px;
}
.pageHeading{
line-height: 1;
font-weight: normal;

}
.contactHeader {
    border-bottom: 5px solid #00698c  !important;
}
.leadstatus label {

width: 30% !important;
}
.worklist-datatable{
width: 100% !important; 
overflow: visible;
 margin-top: 35px;
 margin-left:0px;
}
.glyphicon-calendar{
position: absolute;
 top: 3px;
  right:30px !important;
}
</style>

	  <script>
	  jQuery(document).ready(function($) {
		  //alert("hello");
			enableDatePicker();
		});
  $(function() {
    /* $( "#datepicker1" ).datepicker(); */
    $( "#datepicker11" ).datepicker({
    });
  });
  
  function enableDatePicker(){
	  $(".docDate1").datepicker({
		  changeMonth : true,
			changeYear : true,
			yearRange : "1990:2099",
			dateFormat : 'dd-mm-yy'
		 });  
  } 
  $("#datepicker3").datepicker({
		changeMonth : true,
		changeYear : true,
		yearRange : "1990:2099",
		dateFormat : 'dd-mm-yy'
	});
  
  </script>
  
<div class="container-fluid">	
<input type="hidden" name="abc" value="" id="abc"/>
	<div class="padding-top-container col-lg-12 col-md-12 col-sm-12 col-xs-12 leadstatus dashbrd" style="padding-top: 10px;" >
	 <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
        <label  ><spring:message code="label.fromDate"/></label>
        <input type="text" class="docDate1" id="datepicker1"	placeholder="From Date" name="fromDate"> 
        <span class="dobCalc glyphicon glyphicon-calendar" for="datepicker" aria-hidden="true" ></span>
        
      </div>
      <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
        <label  ><spring:message code="label.toDate"/></label>
        <input type="text" class="docDate1" id="datepicker3"	placeholder="To Date" name="toDate" onchange="return compareDate();"> 
        <span class="dobCalc glyphicon glyphicon-calendar" for="datepicker" aria-hidden="true" ></span>
      </div>
      <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
        <label><spring:message code="label.actionName"/></label>
       <select name="actionName" id="actionName"  class="validField">
						<!-- <option value="">Select</option>
						<option value="daily">Daily</option> -->
						<option value="monthly">Monthly</option>
      </select></div>
      <div class="searchfieldss col-lg-3 col-md-4 col-sm-4 col-xs-12 worklist-searchbtn">
				<input type="submit" value="Search" class="saveBtn1 btn btn-primary" onclick="getSummary();">
							</div>
      <!--  <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 mobile-srch">
	 <input type="submit" value="Search" class="searchbtn1 saveBtn1 btn btn-primary" onclick="getSummary()" >	
	</div> -->
	</div>			
	</div>
	<div class="row list-content table-responsive marg-pad-0 worklist-datatable" >
			<div id="resultTableDiv" class="summary-block pad active">
				<table id="resultTableId" cellspacing="0" width="100%">
				</table>
			</div>
		</div>
	
<script>

function compareDate() {
         //alert("Date  ");
            var dateEntered1 = document.getElementById("datepicker1").value; 
            var date1 = dateEntered1.substring(0, 2);
            var month1 = dateEntered1.substring(3, 5);
            var year1 = dateEntered1.substring(6, 10);
            var dateEntered2 = document.getElementById("datepicker3").value; 
            var date2 = dateEntered2.substring(0, 2);
            var month2 = dateEntered2.substring(3, 5);
            var year2 = dateEntered2.substring(6, 10);
         
            var dateToCompare1 = new Date(year1, month1 - 1, date1);
            var dateToCompare2 = new Date(year2, month2 - 1, date2);
         /*    var currentDate = new Date(); */
         
            if (dateToCompare1 > dateToCompare2) {
                alert("To Date Can't be Smaller than From Date ");
                        $('#datepicker3').val("");
            }
           
        }
function getSummary(){
	var fromDate=$('#datepicker1').val();
	if(fromDate==""){
		alert("Please select from Date");
		return false;
	}
	var toDate=$('#datepicker3').val();
	if(toDate==""){
		alert("Please select to Date");
		return false;
	}
	var actionName=$('#actionName').val();
	if(actionName===""){
		alert("Please select Action name");
		return false;
	}
   var resultTable=null;
	if(resultTable!=null){
		resultTable.destroy();
	}
	$('#resultTableId').children('tbody').remove();
	globalCounter = parseInt("0");
	var dataString = "fromDate="+fromDate +"&toDate=" + toDate+"&actionName="+actionName  ;
	$.ajax({
		async : true,
		type : "post",
		url : "getSummary.do",
		dataType:"json", 
		context: document.body,
		data : dataString ,
		success : function(response) {	
			//alert(response);
try{			
    $('#resultTableId').show();
    
    resultTable = $('#resultTableId').DataTable( {
      responsive: true,
      filter:false,
      data: response,
      columnDefs: [ {
      className: 'control',
      orderable: false,
      targets:   0
  } ], 
   columns: [
        { data:"userName",label:"Client Name","title": "Agent Name" ,"bSortable": true}, 
        { data:"distanceTravelled",label:"Client Name","title": "Distance" ,"bSortable": true},
        { data:"actionMonth",label:"Client Name","title": "ACTION MONTH" ,"bSortable": true},
       ],
        pagingType: "full_numbers",
       // recordType: ['Lead ID', 'bank Name', 'Queue']
    });  
   	
}catch(e){
//alert(e);	
}
		}});			
	}


</script>
