<link rel="stylesheet" href="resources/css/base/jquery.ui.all.css">
<link href="resources/css/style.css" rel="stylesheet">
<script src="resources/js/ui/jquery.ui.core.js"></script>
<link href="resources/css/user-detail.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css"	href="resources/css/jquery.dataTables.min.css">
<script type="text/javascript" language="javascript"	src="resources/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" language="javascript"	src="resources/js/dataTables.responsive.min.js"></script>
<script type="text/javascript" language="javascript"	src="resources/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" language="javascript"	src="resources/js/dataTables.responsive.min.js"></script>

<!-- New Page Added by Anuj on 19-jan-2017 -->
<style>
.mainHeading{
padding-top:9px;
color: #333;
}	
.pageHeading{
line-height: 1;
font-weight: normal;

}
.worklist-datatable{
width: 100% !important; 
overflow: visible;
 margin-top: 100px;
 margin-left:0px;
}
.contactHeader{
	text-transform: uppercase;
  background: #fff none repeat scroll 0 0;
    float: left;
    margin: 50px 0 0;
    width: 100%;
    border-bottom: 5px #00698c  solid !important;
    position: fixed;
	
}
</style>

<script>
$(document).ready(function() {
	//alert("hello");
	getDailyActicity();
});
function getDailyActicity(){
   var resultTable=null;
	if(resultTable!=null){
		resultTable.destroy();
	}
	$('#resultTableId').children('tbody').remove();
	globalCounter = parseInt("0");
	var dataString = "";
		$.ajax({
		async : true,
		type : "post",
		url : "getActivity.do",
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
		// return '<input type="button"  class="btn btn-success" value="'+data+'" onclick="findMap();" >';}},
   columns: [
        { data:"userId",label:"Client Name","": "" ,"bSortable": true,"visible": false}, 
       	{ data:"userName",label:"Client Name","title": "Agent Name" ,"bSortable": true,
        	render:function ( data, type, row ) {
            	  return '<a href="getMap.do?userId='+row.userId+'" >'+data+'</a>';}}, 
        { data:"noOfCustomer",label:"Client Name","title": "Customer Interaction" ,"bSortable": true},
        { data:"loginTime",label:"Client Name","title": "Login Time" ,"bSortable": true},
        { data:"logoutTime",label:"Client Name","title": "Logout Time" ,"bSortable": true}, 
        { data:"distanceTravelled",label:"Client Name","title": "Distance Travelled" ,"bSortable": true}, 
        { data:"actionDate",label:"Client Name","title": "Action Date" ,"bSortable": true},
        /* { data:"locationList",label:"Client Name","title": "Location" ,"bSortable": true}, */
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

<div class="container-fluid">
<div class="row list-content table-responsive marg-pad-0 worklist-datatable" >
			<div id="resultTableDiv" class="summary-block pad active">
				<table id="resultTableId" cellspacing="0" width="100%">
				</table>
			</div>
		</div>
</div>