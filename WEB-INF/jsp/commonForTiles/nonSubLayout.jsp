<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%-- <title><tiles:insertAttribute name="title"/></title> --%>

<!-- Bootstrap -->
<link href="resources/css/style.css" rel="stylesheet">
<link href="resources/css/bootstrap.css" rel="stylesheet">
<link href="resources/css/bootstrap-theme.min.css" rel="stylesheet">
<link rel="stylesheet" href="resources/css/pickmeup.css" type="text/css" />
<link rel="stylesheet" href="resources/css/custom.css" type="text/css" />
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
<link rel="stylesheet" href="resources/css/base/jquery.ui.all.css">
  <script src="resources/js/jquery.min.js" type="text/javascript"></script>
<!--   <script src="resources/js/ui/jquery.ui.core.js"></script>
  <script src="resources/js/ui/jquery.ui.widget.js"></script> -->
  <script src="resources/js/jquery.ui.datepicker.min.js"></script>
<style>
.contactHeader .mainHeading h1 {
color: #333 !important;
font-size:14px !important;
}


</style>
   <script>
   $(function() {
		$("#datepicker2").datepicker({
			changeMonth : true,
			changeYear : true,
			yearRange : "1900:2099",
			dateFormat : 'dd-mm-yy'
		});
		$("#datepicker1").datepicker({
			changeMonth : true,
			changeYear : true,
			yearRange : "1900:2099",
			dateFormat : 'dd-mm-yy'
		});
		$("#datepicker").datepicker({
			/* showOn: "button",
			 buttonImage: "images/calendar.gif",
			 buttonImageOnly: true*/
			changeMonth : true,
			changeYear : true,
			yearRange : "1900:2099",
			dateFormat : 'dd-mm-yy'

		});
	});
 
  </script>
</head>
<body>
<tiles:insertAttribute name="header"/>
<div class="contactHeader">
  <div class="mainHeading"> 
    <h1 ><tiles:insertAttribute name="pageHeader"/></h1>
  </div>
    <div class="searchList">
        <div class="searchBox">
          <div class="searcharea1 fr search-width-margin">
            <input type="text" placeholder="Lead Id" id="leadCode" />
					<button class="btn btn-success" onclick="searchFromHeader()">Search</button>
          </div>
        </div> 
      </div>
</div>
</header>
<footer>
    Qualtech Consultants Pvt. Ltd. version 3.0.0.246
</footer>	
<tiles:insertAttribute name="leftMenu"/>
<tiles:insertAttribute name="body"/>
<script>
function searchFromHeader(){
	var leadCode = $('#leadCode').val();
	$.ajax({
		async : true,
		type : "get",
		url : "checkLeadAvail.do",
		//dataType:"json", 
		context: document.body,
		data : "caseId="+leadCode ,
		success : function(response){
			if(response=='false'){
				alert("You have entered a wrong leadId");
			}else{
				window.location.href = 'leadDetailCode.do?caseCode='+leadCode;
			}
		}
	});
}
var iCnt = 1;
var flag= true;
	var flag1= true;
$('.rightpanel-more').click(function(e){
    e.preventDefault();
    $('.profilePanel').toggle();
    $('.userProfile').hide();
   });
$('.circleimg, .headerarrowadjust').click(function(e){
    e.preventDefault();
    $('.userProfile').toggle();
     $('.profilePanel').hide();
   });
  
  $('#callIcon').on('click', function(){
  $(this).parent('li').remove();
  });
  $('#followupIcon').on('click', function(){
  $(this).parent('li').remove();
  });
  $('#calcIcon').on('click', function(){
  $(this).parent('li').remove();
  });
  $('#docIcon').on('click', function(){
  $(this).parent('li').remove();
  });

$('.toggle-list').click(function(){
		$(this).parent('.btnPosition').parent().next('.row').slideToggle(150);
		//$(this).parent('.list-header').next('.list-content').slideToggle(150);
		if($(this).children('span').hasClass('minus-icon1')){
			$(this).parent().parent('.tablular-list').css({'margin-bottom':'5px'});
			$(this).parent('.list-header').css({'border-radius':'5px'});
			
			
			$(this).children('span').removeClass('minus-icon1');
			$(this).children('span').addClass('plus-icon1');
		}
		else{
		$(this).parent().parent('.tablular-list').css({'margin-bottom':'0px'});
			$(this).parent('.list-header').css({'border-radius':'5px 5px 0 0'});
		$(this).parent('.tablular-list').css('margin-bottom', '0px');
		$(this).children('span').removeClass('plus-icon1');
			$(this).children('span').addClass('minus-icon1');
		}
	});


$('.mainmenuimg').click(function(e){
  e.preventDefault();
  if(flag1){
  //alert('dddd');
    $('.padding-top-container').css({"float":"right"});
    $('.padding-top-container').animate({"width":"80%"});
    $('.mainmenu').css("display","block");
  
    if(document.documentElement.clientWidth>900){
    $('.mainmenu').animate({"width":"20%","left":"0%"});
    }
    
  
    if(document.documentElement.clientWidth<900){
    	//alert(document.documentElement.clientWidth);
    $('.mainmenu').animate({"width":"88%","left":"0%"});
    }
    
    }
    
    else{
    	 $('.mainmenu').css("display","block");
    	 $('.padding-top-container').animate({"width":"100%"});
    	if(document.documentElement.clientWidth>900){
  //    $('.content').css({"float":"left"});
      
      $('.mainmenu').animate({"width":"50%","left":"-60%"});
     
    	}
    //  
      if(document.documentElement.clientWidth<900){
      	//alert(document.documentElement.clientWidth);
      $('.mainmenu').animate({"width":"50%","left":"-60%"});
    
      }
    }
    flag1 =!flag1;
  
  });


</script>
<style>
footer{
background-color: #333 !important;
background-color: #333 !important;
position: fixed;
bottom: 0;
width: 100%;
text-align: center;
font-size: 14px;
font-weight: bold;
padding: 3px 0;
color: white;
z-index:9999;
}
body{
position: relative;
padding-bottom: 56px;
}
</style>
</body>
</html>
