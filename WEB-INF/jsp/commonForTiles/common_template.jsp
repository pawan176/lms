<%@ include file="/WEB-INF/includes/taglibs.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
 <link href="resources/css/style.css" rel="stylesheet">   
	 <script src="resources/js/jquery.min.js" type="text/javascript"></script> 
    <script src="resources/js/jquery.hashchange.min.js" type="text/javascript"></script>
    <script src="resources/js/jquery.easytabs.min.js" type="text/javascript"></script>



<title><tiles:insertAttribute name="title"/></title>   
<!--[if lt IE 9]>

	<style type="text/css">

.worklist-searchbtn{padding: 0 !important; width: 4% !important}
	 .add-phoneno, .add-emaillid {
    background-position: left 1px !important
}
	select{padding-right: 0 !important}
	</style>
<![endif]-->
</head>
<body>
<article class="wrapper">

	
     <tiles:insertAttribute name="header" />	
       <tiles:insertAttribute name="menu" /> 
	 <tiles:insertAttribute name="body" />
   
     
</article> 
</body>
</html>         
