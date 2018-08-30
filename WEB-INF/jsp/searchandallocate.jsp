
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<link href="resources/css/user-detail.css" rel="stylesheet"
	type="text/css" />
<link rel="stylesheet" type="text/css"
	href="resources/css/jquery.dataTables.min.css">

<script type="text/javascript"
	src="resources/js/jquery.dataTables.min.js"></script>
<script type="text/javascript"
	src="resources/js/dataTables.responsive.min.js"></script>

<link rel="stylesheet" href="resources/css/base/jquery.ui.all.css">

<script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="resources/js/bootstrap-multiselect.min.js"></script>
<link rel="stylesheet" href="resources/css/bootstrap-multiselect.css"
	type="text/css">
<style>

.searchallo-datatable #resultTable_length {
    top: -50px;
    position: absolute;
    left: 28% !important;
}
.dataTables_wrapper {
    
    top: -35px;
}
</style>
<script>
	$(document).ready(function(){
	
	$(document).bind("copy paste",function(e) {
	     e.preventDefault();
	 });
	
    $(document).on("contextmenu",function(e){       
             e.preventDefault();
     });
 });
$('document').ready(function(){
	getCases('alloted', '');	
});
var allcases = [];
var allotedpe5rsons = [];
var counter=parseInt("1");
var id="";
var caseno="",subqid="",sourceid="",campainid="", queid="",dn="",flag2="0",cse="",queue="";
function allotcases()
{

	var personid=document.getElementById("personid").value;
	$.ajax({
		async : true,
		type : "post",
		  url: "allocatecases.do",
		      data: { 
			  case_no: caseno,
			  subId: subqid,
			  sourceId:sourceid,
			  comid:campainid,
			  qid:queid,
			  person_id:personid,
			  dnd:dn,
			  casetype:cse
			  } 
		});
	flag2="0";
	location.reload();
	}
	var no_of_casesglobal=parseInt("0");
function calculate(param , no_of_cases,textId){
	
	/* var alloted_no="";
	
	if(param.value=="")
		{
			alloted_no=parseInt("0");
		}
	
	else{
			alloted_no=parseInt(param.value);
	}

	var no_of_cases=parseInt(no_of_cases);
	caseno=alloted_no;
	if((no_of_cases-alloted_no)<0)
		{
			alert('Alloted no cannot be greater than the cases');			
			$('#'+textId).val('');
			/* $('#'+id).text(''); 
			return false;
		}
	else{
			var no=no_of_casesglobal-alloted_no;
		
			no_of_casesglobal=no;
		
			$('#'+id).text(no);
	} */
	var cnt = 0;
	var finalVal=0;
	$("#allocateAgent tbody tr").each(function() {
		var this_row = $(this);
		var val = $.trim(this_row.find('td:eq(5) input').val());
		//alert(val);
		if (cnt==0) {
			if(val!=''){
			finalVal = finalVal + parseInt(val);
			}
		} else {
			finalVal = parseInt(val);
		}
			
			
	});
	var s=no_of_cases - finalVal;
	//alert("s=="+s);
	//alert("no_of_cases"+no_of_cases);
	//alert("finalVal-"+finalVal);
	if (no_of_cases - finalVal < 0) {
		alert('Alloted no cannot be greater than the cases');
		$('#' + textId).val('');
	} else {
		
		$('#' + id).text(no_of_cases-finalVal);
	}
}

function addSubRow(obj, case_no,divid,subqueId,subque,dnd,source,sourceID,campainId,campain,qid,queue){
	if($(obj).parent().prev().prev().children().val()==""){
		alert("Please specify the no. of cases to allocate.");
	}else{
		id=divid+"s";	
		var s=$('#'+id).text();
		//alert(s);
		 if(s!=0){
		jQuery(function(){			
		        //event.preventDefault();	     
		     /*    var newRow = jQuery('  <tr>	<td><select><option value='+subqueId+'>'+subque+'</option></select></td> <td>'+dnd+'</td><td><select><option value='+sourceID+'>'+source+'</option></select></td><td><select><option value='+campainId+'>'+campain+'</option></select></td><td><input type="text" id="'+counter+'" placeholder="0" value="" onblur="calculate(this,'+case_no+','+counter+');"></td><td><select name="personid" id="personid"> <c:forEach var="peereportee" items="${peerAndreportee}"><option value="${peereportee.userId}">${peereportee.userName}</option></c:forEach></select> </td> <td><input type="Button" value="Save" onclick="allotcases();" class="btn btn-danger"></td></tr><tr>'); */
		     var newRow = jQuery(' <tr><td><select name="searchAndAllocateDto['+globalcounter+'].queueId" ><option value='+qid+'>'+queue+'</option></select></td><td><select name="searchAndAllocateDto['+globalcounter+'].subQueueId" ><option value='+subqueId+'>'+subque+'</option></select></td> <td><input type="text" name="searchAndAllocateDto['+globalcounter+'].dnd" value="'+dnd+'"></td><td><select name="searchAndAllocateDto['+globalcounter+'].sourceId" ><option value='+sourceID+'>'+source+'</option></select></td><td><select name="searchAndAllocateDto['+globalcounter+'].campaignId"><option value='+campainId+'>'+campain+'</option></select></td><td><input type="text" id="'+counter+'" name="searchAndAllocateDto['+globalcounter+'].noOfCase" placeholder="0" value="" onblur="calculate(this,'+no_of_casesglobal+','+counter+');"></td><td><select name="searchAndAllocateDto['+globalcounter+'].allocate" id="personid"> <c:forEach var="peereportee" items="${peerAndreportee}"><option value="${peereportee.userId}">${peereportee.userName}</option></c:forEach></select> </td> <td><button type="button"  onclick="addSubRow(this, '+case_no+','+divid+','+subqueId+','+"'"+subque+"'"+','+"'"+dnd+"'"+','+"'"+source+"'"+','+sourceID+','+campainId+','+"'"+campain+"'"+','+"'"+qid+"'"+','+"'"+queue+"'"+');" class="btn btn-danger">Add</button></td></tr>');
		     counter++;
		        jQuery('#allocateAgent').append(newRow);	  
		});
		 }
		 else{
			 alert('NO CASES LEFT TO ALLOCATE');
		 }
		globalcounter++;
			
	}
	
}
var globalcounter=parseInt("0");
function addRow(case_no,divid,subqueId,subque,dnd,source,sourceID,campainId,campain,qid,queue){
	var allocatedFrm=document.getElementById("allocateList").value;
	caseno=case_no;
	subqid=subqueId;
	sourceid=sourceID;
	campainid=campainId;	
	no_of_casesglobal=parseInt(caseno);
	queid=qid;
	dn=dnd;
	id=divid+"s";	
	if(flag2=="0")
		{
		flag2="1";
	jQuery(function(){			
	     //   event.preventDefault();	     
	     /*    var newRow = jQuery('  <tr>	<td><select><option value='+subqueId+'>'+subque+'</option></select></td> <td>'+dnd+'</td><td><select><option value='+sourceID+'>'+source+'</option></select></td><td><select><option value='+campainId+'>'+campain+'</option></select></td><td><input type="text" id="'+counter+'" placeholder="0" value="" onblur="calculate(this,'+case_no+','+counter+');"></td><td><select name="personid" id="personid"> <c:forEach var="peereportee" items="${peerAndreportee}"><option value="${peereportee.userId}">${peereportee.userName}</option></c:forEach></select> </td> <td><input type="Button" value="Save" onclick="allotcases();" class="btn btn-danger"></td></tr><tr>'); */
	     var newRow = jQuery(' <tr><td><select name="searchAndAllocateDto['+globalcounter+'].queueId" ><option value='+qid+'>'+queue+'</option></select></td><td><select name="searchAndAllocateDto['+globalcounter+'].subQueueId" ><option value='+subqueId+'>'+subque+'</option></select></td> <td><input type="text" name="searchAndAllocateDto['+globalcounter+'].dnd" value="'+dnd+'"></td><td><select name="searchAndAllocateDto['+globalcounter+'].sourceId" ><option value='+sourceID+'>'+source+'</option></select></td><td><select name="searchAndAllocateDto['+globalcounter+'].campaignId"><option value='+campainId+'>'+campain+'</option></select></td><td><input type="text" id="'+counter+'" name="searchAndAllocateDto['+globalcounter+'].noOfCase" placeholder="0" value="" onblur="calculate(this,'+case_no+','+counter+');"></td><td><select name="searchAndAllocateDto['+globalcounter+'].allocate" id="personid"> <c:forEach var="peereportee" items="${peerAndreportee}"><option value="${peereportee.userId}">${peereportee.userName}</option></c:forEach></select> </td> <td><button type="button" onclick="addSubRow(this, '+case_no+','+divid+','+subqueId+','+"'"+subque+"'"+','+"'"+dnd+"'"+','+"'"+source+"'"+','+sourceID+','+campainId+','+"'"+campain+"'"+','+"'"+qid+"'"+','+"'"+queue+"'"+');" class="btn btn-danger">Add</button></td><td><input type="hidden" readonly name="searchAndAllocateDto['+globalcounter+'].allotedList" value="'+allocatedFrm+'"></td></tr>');
	     // var newRow = jQuery('  <tr>	<td><select name="searchAndAllocateDto['+globalcounter+'].subQueueId" ><option value='+subqueId+'>'+subque+'</option></select></td> <td><input type="text name="searchAndAllocateDto['+globalcounter+'].dnd" value="'+dnd+'"></td><td><select name="searchAndAllocateDto['+globalcounter+'].sourceId" ><option value='+sourceID+'>'+source+'</option></select></td><td><select name="searchAndAllocateDto['+globalcounter+'].campaignId"><option value='+campainId+'>'+campain+'</option></select></td><td><input type="text" id="'+counter+'" name="searchAndAllocateDto['+globalcounter+'].noOfCase placeholder="0" value="" onblur="calculate(this,'+case_no+','+counter+');"></td><td><select name="searchAndAllocateDto['+globalcounter+'].allocate id="personid"> <c:forEach var="peereportee" items="${peerAndreportee}"><option value="${peereportee.userId}">${peereportee.userName}</option></c:forEach></select> </td> <td><input type="Button" value="Add" onclick="addSubRow('+case_no+','+divid+','+subqueId+','+"'"+subque+"'"+','+"'"+dnd+"'"+','+"'"+source+"'"+','+sourceID+','+campainId+','+"'"+campain+"'"+','+"'"+qid+"'"+');" class="btn btn-danger"></td></tr><tr>');
	     counter++;
	        jQuery('#allocateAgent').append(newRow);	  
	});
	globalcounter++;
	}
	else{
		alert("Please save the row that has been displayed first");
		
	}
}
var resultTable;
/* function getCases(casetype)
{
	if(casetype=="Unalloted")
		{
		document.getElementById("flag").value="U";
		}
	else
	{
		document.getElementById("flag").value="A";
	}
	
	if(resultTable!=null){
		resultTable.destroy();
	}
	
	$('#allocateAgent').children('tbody').remove();
	globalCounter = parseInt("0");
	flag2 = '0';
	var dataString = "casetype=" + casetype ;
	$.ajax({
		async : true,
		type : "post",
		url : "getcases.do",
		dataType:"json", 
		context: document.body,
		data : dataString ,
		success : function(response) {		
try{			
    $('#resultTable').show();
    resultTable = $('#resultTable').DataTable( {
      responsive: true,
      filter:false,
      data: response,
      columnDefs: [ {
      className: 'control',
      orderable: false,
      targets:   0
  } ], 
   columns: [
         { data:"temp",label:"Client Name","": "" ,"bSortable": true}, 
       	{ data:"queue",label:"Client Name","title": "Queue" ,"bSortable": true}, 
        { data:"subQueue",label:"Client Name","title": "Sub-Queue" ,"bSortable": true},
        { data:"dnd",label:"Client Name","title": "DND" ,"bSortable": true},
        { data:"source",label:"Client Name","title": "Source" ,"bSortable": true},
        { data:"campaign",label:"Client Name","title": "Campaing" ,"bSortable": true},
        { data:"noOfCase", label:"",title: "No of Case	","bSortable": true},
        { data:"allocate",label:"Client Name","title": "Allocate" ,"bSortable": true, render:function (data, type, full) {
          return '<input type="button"  class="btn btn-success" value="Allocate" onclick="addRow('+full.noOfCase+','+full.rowNo+','+full.subQueueId+','+"'"+full.subQueue+"'"+','+"'"+full.dnd+"'"+','+"'"+full.source+"'"+','+"'"+full.sourceId+"'"+','+"'"+full.campaignId+"'"+','+"'"+full.campaign+"'"+','+"'"+full.queueId+"'"+','+"'"+full.queue+"'"+');" >';
        },}
        ,
        { data:"leftToAllocate",label:"Client Name","title": "Left to Allocate" ,"bSortable": true ,render:function (data, type, full) {
                    return '<div id="'+full.rowNo+'s" >';
        }
        	
        },
        
        ],
        pagingType: "full_numbers",
        recordType: ['Lead ID', 'bank Name', 'Queue']
    });  
   	
}catch(e){
//alert(e);	
}
		}});			
	} */
function getCases(casetype, value)
{
	if(casetype=="Unalloted")
		{
		document.getElementById("flag").value="U";
		}
	else
	{
		document.getElementById("flag").value="A";
	}
	
	if(resultTable!=null){
		resultTable.destroy();
	}
	
	$('#allocateAgent').children('tbody').remove();
	globalCounter = parseInt("0");
	flag2 = '0';
	//alert(value);
	var dataString;
	if(value==='')dataString = "userId="+"" +"&casetype=" + casetype  ;
	else dataString = "userId="+value +"&casetype=" + casetype ;
	//alert(dataString);
	$.ajax({
		async : true,
		type : "post",
		url : "getcases.do",
		dataType:"json", 
		context: document.body,
		data : dataString ,
		success : function(response) {		
try{			
    $('#resultTable').show();
    resultTable = $('#resultTable').DataTable( {
      responsive: true,
      filter:false,
      data: response,
      columnDefs: [ {
      className: 'control',
      orderable: false,
      targets:   0
  } ], 
   columns: [
         { data:"temp",label:"Client Name","": "" ,"bSortable": true}, 
       	{ data:"queue",label:"Client Name","title": "Queue" ,"bSortable": true}, 
        { data:"subQueue",label:"Client Name","title": "Sub-Queue" ,"bSortable": true},
        { data:"dnd",label:"Client Name","title": "DND" ,"bSortable": true},
        { data:"source",label:"Client Name","title": "Source" ,"bSortable": true},
        { data:"campaign",label:"Client Name","title": "Campaign" ,"bSortable": true},
        { data:"noOfCase", label:"",title: "No of Case	","bSortable": true},
        { data:"allocate",label:"Client Name","title": "Allocate" ,"bSortable": true, render:function (data, type, full) {
          return '<input type="button"  class="btn btn-success" value="Allocate" onclick="addRow('+full.noOfCase+','+full.rowNo+','+full.subQueueId+','+"'"+full.subQueue+"'"+','+"'"+full.dnd+"'"+','+"'"+full.source+"'"+','+"'"+full.sourceId+"'"+','+"'"+full.campaignId+"'"+','+"'"+full.campaign+"'"+','+"'"+full.queueId+"'"+','+"'"+full.queue+"'"+');" >';
        },}
        ,
        { data:"leftToAllocate",label:"Client Name","title": "Left to Allocate" ,"bSortable": true ,render:function (data, type, full) {
                    return '<div id="'+full.rowNo+'s" >';
        }
        	
        },
        
        ],
        pagingType: "full_numbers",
        recordType: ['Lead ID', 'bank Name', 'Queue']
    });  
   	
}catch(e){
//alert(e);	
}
		}});			
	}
</script>
<div class="container-fluid" >
<div class="padding-top-container col-lg-12 col-md-12 col-sm-12 col-xs-12">
 <div class="row buttonHeading" >
   <div class="col-lg-9 col-md-9 col-sm-12 col-xs-12" id="content" style="padding-top:13px;">  
     
    <ul id="tabs" class="nav nav-tabs" data-tabs="tabs">
        
        <li class="active"><a class="heading" href="#orange" data-toggle="tab" onclick="getCases('Alloted','')">Allocated Cases</a></li>
        <li ><a class="heading" href="#red" data-toggle="tab" onclick="getCases('Unalloted','')">Un-Allocated Cases</a></li>
        
    </ul>

  </div>
  <!-- <div class="col-lg-3 col-md-3 col-sm-3 col-xs-2 btnPosition"> <a class="btn-primary  toggle-list"> <span class="toggle-icon minus-icon1 "></span> </a> </div> -->
</div>
  <!-- <div class="row contactList leadstatus searchArea"> 
   
    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12"> <label><input type="button" value="Un Alloted cases" onclick="getCases('Unalloted')"></label>
     
    </div>
    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12"> <label ><input type="button" value="Alloted cases" onclick="getCases('Alloted')"></label>
    
    </div>
  </div> -->
  <div class="row contactList leadstatus">
    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" id="content"> 
      <div id="my-tab-content" class="tab-content">
      <div class="row buttonHeading">
    <div class="col-lg-9 col-md-9 col-sm-9 col-xs-10">
      <h2 class="heading lineHeightCstm">Case Details</h2>
    </div>
    <div class="col-lg-3 col-md-3 col-sm-3 col-xs-2 btnPosition" id="clicktodown"> <a class=" btn-primary toggle-list "> <span class="toggle-icon minus-icon1 "></span> </a> </div>
  </div>
        <div class="tab-pane " id="red">
         
        </div>
        <div class="tab-pane active" id="orange">
             <spring:message code="label.allocateTo"/> :<select name="allotedList" id="allocateList" onchange="getCases('Alloted', this.value)"> 
             <c:forEach var="alloted" items="${allocatedList }">
             	<option value="${alloted.userId }">${alloted.userName}</option>
             </c:forEach></select><!-- <button class="btn btn-primary" >Submit</button> -->
        </div>
        
      </div>
    </div>
  </div>
  
  <div class="row list-content searchallo-datatable" id="showtop">
    <div id="resultTableDiv" class="summary-block pad active">
			<table id="resultTable" cellspacing="0" width="100%" class="setrowtablewidth">
			</table>
		</div>
	</div>
  <div class="row buttonHeading">
    <div class="col-lg-9 col-md-9 col-sm-9 col-xs-10">
      <h2 class="heading lineHeightCstm">Allocate To</h2>
    </div>
    <div class="col-lg-3 col-md-3 col-sm-3 col-xs-2 btnPosition"> <a class=" btn-primary toggle-list"> <span class="toggle-icon minus-icon1 "></span> </a> </div>
  </div>
  <div class="row">
    <div class="bs-example">
    <form action="<c:url value="${requestScope.contextPath}/saveAllotedcases.do"/>"  id="searchallocate" method="post">
    <input type="hidden" name="flag" id="flag">
      <table class="table table-striped" id="allocateAgent">
        <thead>
          <tr>
            <th>Queue</th> 
            <th>Sub-Queue</th>
            <th>DND</th>
            <th>Source</th>
            <th>Campaign</th>
            <th>No of Case</th>
            <th>Allocate To</th>
            <th></th>
          </tr>
        </thead>        
      </table>
      </form>
    </div>
  </div>
</div>
</div>

<script>

$('.toggle-list').click(function(){
    
    $(this).parent('.worklistbtn').parent().parent().next('.row').slideToggle(150);
    //$(this).parent('.list-header').next('.list-content').slideToggle(150);
    
    if($(this).children('span').hasClass('minus-icon1')){
      $(this).parent().parent('.tablular-list').css({'margin-bottom':'5px'});
      $(this).parent('.list-header').css({'border-radius':'5px'});
      
      
      $(this).children('span').removeClass('plus-icon1');
      $(this).children('span').addClass('minus-icon1');
    }
    else{
    $(this).parent().parent('.tablular-list').css({'margin-bottom':'0px'});
      $(this).parent('.list-header').css({'border-radius':'5px 5px 0 0'});
    $(this).parent('.tablular-list').css('margin-bottom', '0px');
    $(this).children('span').removeClass('minus-icon1');
      $(this).children('span').addClass('plus-icon1');
    }
  });


$('.prev-lead-icon').parent('.subheader-btn').hover(function(){
//alert('1');
	$(this).children('.prevlead-tooltip').fadeToggle(150);
});


$('.next-lead-icon').parent('.subheader-btn').hover(function(){
//alert('1');
	$(this).children('.nextlead-tooltip').fadeToggle(150);
});

$('.savee-icon').parent('.subheader-btn').hover(function(){
	$(this).children('.savee-tooltip').fadeToggle(150);	
});

$('.savee-icon').click(function(){
	document.getElementById("searchallocate").action = "saveAllotedcases.do";
	document.getElementById("searchallocate").submit();
});
$('.saveandexit-icon').click(function(){
	document.getElementById("searchallocate").action = "sesaveAllotedcases.do";
	document.getElementById("searchallocate").submit();
});

$('.saveandexit-icon').parent('.subheader-btn').hover(function(){
//alert('1');
	$(this).children('.saveandexit-tooltip').fadeToggle(150);
});

	$('.usermenu-mobile').click(function(){
	//	alert('fs');
		$('.header-rightpanel').animate({"top":"0"});
	});
	$('.close-header-rightpanel').click(function(){
	
		$('.header-rightpanel').animate({"top":"-260px"});
	
	});
	$(document).ready(function ($) {
	    $('#tabs').tab();
	});
</script>
<style>
.saveBtnList ul li a {
border: 0px solid black !important;
}
.contactHeader{
border-bottom: solid 1px #DDD !important;
}
.row.list-content.searchallo-datatable{
border: 0px solid black !important;
}
.searchList {
margin-top: 8px !important;
}
.tab-pane.active#orange{
margin-left: -13px;
}
</style>
<script>

$("#clicktodown  a >  .toggle-icon").click(function(){$("#showtop").toggle();});
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
</script>
<style>
.contactHeader {
border-bottom: 5px solid #00698C !important;
padding-bottom: 3PX;
}
</style>
