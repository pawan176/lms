<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/includes/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="resources/css/login.css" rel="stylesheet"/>
<link rel="stylesheet" href="resources/css/custom.css" type="text/css" />
<title>Login</title>
<script type="text/javascript" src="resources/js/jquery.min.js"></script>
<script type="text/javascript" src="resources/js/custom.js"></script>
<script type="text/javascript">
			$('document').ready(function(){
				var changePass="${ChangePassMsg}";
				if(changePass!=''){
					alert(changePass);
				}
				var requestNewSession = "${errors}";				
				if(requestNewSession === 'RequestNewSession' ){
					if (confirm('You are already logged in from another system/device. Press OK to start a new session - old session will be killed automatically!')) {
					    window.location.href = 'openNewSession.do';
					} else {
						window.location.href = 'login.do';
					}
					
				}
				else if(requestNewSession === 'UserName or Password do not Match' ){
					if (confirm('UserName or Password do not Match!')) {
					    window.location.href = 'openNewSession.do';
					} else {
						window.location.href = 'login.do';
					}
					
				}
				
			});
		</script>
	<!--  
<script type="text/javascript">

$(function(){
$(".logininputfull").focus(function(){  
  $(this).prev(".beanimated").animate({"bottom":"36px"});
  $(this).parent(".loginfield").animate({"margin-top":"25px"});
});

  
  $(".logininputfull").blur(function(){
 
  if($(this).val()==""){
    $(this).prev(".beanimated").animate({"bottom":"7px"});
  $(this).parent(".loginfield").animate({"margin-top":"0px"});
  }

});

$(".beanimated").click(function(){

$(this).next(".logininputfull").trigger("focus");
});
 
/*
  $(".beanimated").blur(function(){
 
  if($(this).next(".logininputfull").val()==""){alert("dff");
  $(this).animate({"bottom":"5px"});
  $(this).parent(".loginfield").animate({"margin-top":"0px"});
  }

});*/
});


</script>-->

</head>
<body>
<div class="bgimage"><img src="resources/images/5.jpg" alt="bgimage"/></div>
<div class="loginareaa">
<div class="logintext">miFIN<sup class="suptm">TM</sup>&nbsp&nbspLeadManagement</div>
<form:form action="loginAutentication.do" method="post"  >
<input name="foilautofill" style="display: none;" type="password" />
<input type="text" id="userName" style="display:none" value="fakeInput" />                 
<div class="logintitle">
Login to enter<br>
	<c:if test="${not empty errors}">
		<label style="color: red;font-size:11px">${errors} </label>
	</c:if>
</div>					
<div class="loginfield image-position">
<!-- <label class="beanimated">Username</label> -->
<img alt="login_input" src="resources/images/username.png">
<input type="text" class="logininputfull" name="userName"
 onselectstart="return false" onpaste="return false;" onCopy="return false" 
 onCut="return false" onDrag="return false" onDrop="return false" autocomplete="off" id="textfield1" />
</div>

<div class="loginfield image-position loginfield-two">
<!-- <label class="beanimated">Password</label> -->
<img alt="login_input" src="resources/images/pass.png">
<input type="password" class="logininputfull" 
name="password"
onselectstart="return false" onpaste="return false;" onCopy="return false" 
onCut="return false" onDrag="return false" onDrop="return false" autocomplete="off" id="textfield2" />
</div>

<div class="loginfield border-nonelogin">
<!--  <img alt="login_input" src="resources/images/login.png">-->
<input type="submit" value="Login" type="loginbtn" onclick="myFunction();" class="login" />

<input type="reset" value="Reset" class="reset" onclick="clearFields()">
</div>

<!-- <div class="loginfield customchkbox">

<input type="checkbox" id="remem"/><label for="remem">Remember</label>
</div> -->
	<div class="madeby"><span class="qualtech-left">QUALTECH CONSULTANTS PVT LTD</span><span class="version-right" style="float: right;">VERSION 3.0.0.255</span></div>


	<c:if test="${not empty errors}">
		<div class="madeby"><span class="qualtech-left">${errors}</span></span></div>
	</c:if>
	


</form:form>
</div>
<script type="text/javascript">
function clearFields() {
    document.getElementById("textfield1").value=""
    document.getElementById("textfield2").value=""
}
</script>
<style>
.reset {
color: #333 !important;
}
</style>
</body>

</html>
