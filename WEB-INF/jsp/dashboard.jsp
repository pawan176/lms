<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="com.qc.starter.entity.UserEntity" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
 	
	
	<script src="resources/js/highcharts/highcharts.js"></script>
	<script src="resources/js/highcharts/funnel.js"></script>
	<script src="resources/js/highcharts/module/exporting.js"></script>	
	
		<script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="resources/js/bootstrap-multiselect.min.js"></script>
	<link rel="stylesheet" href="resources/css/bootstrap-multiselect.css" type="text/css"/>
	
	<script type="text/javascript" src="resources/js/jqxcore.js"></script>
	<script type="text/javascript" src="resources/js/jqxdatetimeinput.js"></script>
	<script type="text/javascript" src="resources/js/jqxcalendar.js"></script>
	<script type="text/javascript" src="resources/js/globalize.js"></script>	
	<link rel="stylesheet" href="resources/css/pickmeup.css" type="text/css" />  
	 	

	<style>
		.list-content{width:45%}
		#highcharts-0 {
top: 0;
height: 0px !important;
}
/* .searchbtn1.saveBtn1.btn.btn-primary{
padding: 2px 5px !important;
} */
	</style>
	  <script type="text/javascript">
	  $('document').ready(function(){
		  getinformation();
	  });
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
        
		function getinformation(){
        	
        	var queue=multipleValues(document.getElementById('queue121'));
        	var subqueue=multipleValues(document.getElementById('subqueue121'));  
        	var source=multipleValues(document.getElementById('source121')); 
        	var team=multipleValues(document.getElementById('teamListId'));   
        	var compaign=multipleValues(document.getElementById('compaign'));
        		
          		  
        		    var requestType='DASHBOARD';
        		    dataString = "requestType=" + requestType + '&source='+ source 
        		    +'&subqueue='+ subqueue +'&queue='+ queue
        		    + '&campaign='+ compaign + '&team='+ team;
        	
        		    $.ajax({  
        	    		type : "post",   
        	    		url : "setData.do",   
        	    		data : dataString ,
        	    		ontext: document.body,
        	    		//dataType: "json",
        	    		success : function(response) {
        	    			
        	    		}
        		    });
        		    
    		$.ajax({  
    		type : "post",   
    		url : "getMyleadListState1.do",   
    		data : dataString ,
    		ontext: document.body,
    		//dataType: "json",
    		success : function(response) { 
    			var urlvalue="getLeadsDetailsFromDashboard.do?id=";
    			var res = response.split("~"); 
            	var policyChartData = [];      
            	var jsonArg =null;
            	           	
            	for(var i=0;i<res.length;i++){
          		 jsonArg = new Object();  
          		 var actionName=res[i].split("(");
          		 var urlFnlValue=urlvalue+actionName[0];
          		jsonArg = {name: res[i], y: 10, url: urlFnlValue};
          		policyChartData.push(jsonArg);	
            	}
            	
          
    			$('#container').highcharts({
    		        chart: {
    		            type: 'funnel',
    		            marginRight: 100
    		        },
    		        title: {
    		            text: '',
    		            x: -50
    		        },
    		        credits: {
    		            enabled: false
    		        },
    		        tooltip: {
    		            enabled: false
    		        },
    		        plotOptions: {
    		            series: {
    		                dataLabels: {
    		                    enabled: true,
    		                    format: '<b>{point.name}</b>',
    		                    color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black',
    		                    softConnector: true
    		                },
    		                neckWidth: '30%',
    		                neckHeight: '25%'    		              
    		            }
    		        },
    		        legend: {
    		            enabled: false
    		        },
    		        series: [{
    		            name: 'Unique users',
    		            data: policyChartData,
    		             point: {
    					              events: {
    					                  click: function(e) {//alert("111111111111111111")
    					                      location.href = e.point.url;
    					                     e.preventDefault();
    					                  }
    					              }
    					          }
    		        }]
    		    });
          
    		}
    		});
        	}
                     
        
	</script>
<div class="container-fluid">	
<input type="hidden" name="abc" value="" id="abc"/>
	<div class="padding-top-container col-lg-12 col-md-12 col-sm-12 col-xs-12 leadstatus dashbrd" style="top: 0px;" >
	<div class="row borderBox">
	 <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
        <label  ><spring:message code="label.queue"/></label>
        <select  name="queue121" id="queue121" MULTIPLE="multiple">
								
									<c:forEach var="queue" items="${masterDetail.productMaster}">
										<option value="${queue.prodId }">${queue.prodName}</option>
								</c:forEach>
							</select>
      </div>
      <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
        <label  ><spring:message code="label.subQueue"/></label>
        <select  name="subqueue121" id="subqueue121" MULTIPLE>
								
									<c:forEach var="subQueue" items="${masterDetail.subQueueMaster }">
										<option value="${subQueue.subQueueId}">${subQueue.subQueue}</option>
									</c:forEach>
							</select>
      </div>
      <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
        <label  ><spring:message code="label.source"/></label>
       <select  name="source121" id="source121" MULTIPLE>
								
							<c:forEach var="prod" items="${masterDetail.sourceMaster}" >
									<option value="${prod.caseSourceId }">${prod.sourceName}</option>
								</c:forEach>
							</select>
      </div>
       <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
					<label ><spring:message code="label.campaign"/></label> 
						<select name="compaign" id="compaign"	MULTIPLE>
						
						<c:forEach var="myTeamLst" items="${companignList}">
							<option value="${myTeamLst.campaignId}"	>
								${myTeamLst.campaignName}</option>
						</c:forEach>
				</select>
	</div>
     <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
					<label ><spring:message code="label.teamMember"/></label> 
						<select name="teamList" id="teamListId"	MULTIPLE>
						
						<c:forEach var="myTeamLst" items="${myTeamList}">
							<option value="${myTeamLst.userid}">
								${myTeamLst.userfname}</option>
						</c:forEach>
				</select>
	</div>
      <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 mobile-srch">
	 <input type="submit" value="Search" class="searchbtn1 saveBtn1 btn btn-primary" onclick="getinformation()" >	
	</div>
			</div>			
			
	</div>
	
        


<div id="container" style="min-width: 410px; max-width: 600px; height: 400px; margin: 0 auto"></div>
</div>


<!-- <script type="text/javascript" src="resources/js/jquery-ui-1.8.13.custom.min.js">	</script>
<script type="text/javascript" src="resources/js/ui.dropdownchecklist.js">	</script> -->



<script type="text/javascript">
		
	function getProductLisyByTeam(){
		var teamid=document.getElementById("teamListId").value;
		
		$.ajax({  
			type : "post",   
			url : "getMyTeamProductList.do",   
			data : "teamId=" + teamid ,
			ontext: document.body,			
			success : function(response) { 
				var select  = $('#teamprodId');
	  	     	select.find('option').remove();	
	  	     	$('<option>').val("0").text("Select").appendTo(select);
    			 $($.parseJSON(response)).map(function () {
    				$('<option>').val(this.productId).text(this.prodName).appendTo(select);    			  
  			   });	        
	  	     }			
		});
	}
	
	function getDayDataForDashboard(){
		var str="j";
		$.ajax({  
			type : "post",   
			url : "getDayDataForDashboard.do",  
			dataType: "json",
			data : "date=" +str ,
			ontext: document.body,			
			success : function(response) { 
				//alert(response);			
			
		}
	});
	}
	function getMonthDataForDashboard(){
		var str="j";
		$.ajax({  
			type : "post",   
			url : "getDayDataForDashboard.do",  
			dataType: "json",
			data : "date=" +str ,
			ontext: document.body,			
			success : function(response) { 
				//alert(response);			
			
		}
	});
	}
	function getWeekDataForDashboard(){	
		var str="j";
		$.ajax({  
			type : "post",   
			url : "getDayDataForDashboard.do",  
			dataType: "json",
			data : "date=" +str ,
			ontext: document.body,			
			success : function(response) { 
				//alert(response);			
			
		}
	});
	}
	
	</script>
	<script type="text/javascript">
	$('document').ready(function(){
		
		 $('#queue121').multiselect({includeSelectAllOption: true,buttonWidth: '143px' });
		 $('#subqueue121').multiselect({includeSelectAllOption: true,buttonWidth: '143px' });
		 $('#source121').multiselect({includeSelectAllOption: true,buttonWidth: '143px' });
		 $('#teamListId').multiselect({includeSelectAllOption: true,buttonWidth: '143px' });
		 $('#compaign').multiselect({includeSelectAllOption: true,buttonWidth: '143px' });

		
		
	});
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
				//	alert("response>>>>"+response)
				}
				});	
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
<style>
.contactHeader{
border-bottom: solid 1px #DDD !important;
}
.leadstatus span.multiselect-selected-text {
line-height: 30px;
}
.searchList {
margin-top: 8px !important;
}
.row.borderBox div.col-lg-4{
margin-top: 4px;
}
.dropdown-menu{
padding-top:8px !important;
}
.contactHeader {
border-bottom: 5px solid #00698C !important;
padding-bottom: 3PX;
}
</style>