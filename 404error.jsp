<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="resources/js/globalValidation.js" type="text/javascript"></script>
<link href="resources/css/style.css" rel="stylesheet" type="text/css">
<header> </header>
<body>
	<div class="container well">
		<div class="mainheader">
			<img src="resources/img/logo.jpg" alt="Qualtech Logo"
				class="pageHeading" />
			<h1 class="pageHeading">
				<span class="">miFIN</span> LEAD MANAGEMENT SYSTEM
			</h1>
			<div class="error-header-rightpanel">
				<jsp:useBean id="today" class="java.util.Date" scope="page" />
				<div class="error-currentdate">
					<fmt:formatDate value="${today}" pattern="E dd MMM, yyyy" />
				</div>
			</div>
		</div>
		<div class="contactHeader">
			<div class="mainHeading">
				<h1 style="text-align:center;">Error 404</h1>
			</div>			
		</div>
		<!-- insert aviva code -->
		
		<div id="pageBody" class="wrapper-full">
			<div style="padding-top: 250px;font-weight: bold;">
		      	<div style="width: 50px; vertical-align: middle;float:left;">
		          	<img alt="Aviva Life Insurance" src="https://instalife.avivaindia.com/InstaLife/assets/images/ico_information.gif">
		      	</div>
		      	<div id="textMessage" style="vertical-align: middle;float:left; font-size: 16px;height:79px; margin-top: 8px;">
		          	<a href="login.do" style="text-decoration: none;color:red;"> OOPS! Unable to process your request right now, URI which is requested does not exist on the server.</a> 
		      	</div>
		    </div>
         </div>
		<!-- end -->
	</div>
</body>