<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/includes/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="en"> 
<head>
  
  
    <title>miFIN</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
	
	
<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="css/qcllm.css" rel="stylesheet" type="text/css">
	<link href="css/newlos.css" rel="stylesheet" type="text/css">
	<link href="css/bootstrap.css" rel="stylesheet" type="text/css">
	<link href="css/font-awesome.css" rel="stylesheet">
	<link href="css/responsive.css" rel="stylesheet">
	<link rel="stylesheet" href="resources/css/loginnew.css" type="text/css" />
	<script language="javascript">
	/* changes for the vertical menu State Persistence of  after click on any menu ITEM date 1 Jube 2012 by Ambar Gupta */
	var Browser = {
  Version: function() {
    var version = 999; // we assume a sane browser
    if (navigator.appVersion.indexOf("MSIE") != -1)
      // bah, IE again, lets downgrade version number
      version = parseFloat(navigator.appVersion.split("MSIE")[1]);
    return version;
  }
}


if (Browser.Version() >= 8) 
{

	   if(window.sessionStorage && sessionStorage.setparentID )
	   {
	   		sessionStorage.setparentID=""; 
	   		  
	   }
	   if(window.sessionStorage && sessionStorage.setActionID)
		     {    
		        sessionStorage.setActionID= "";       		     
		     }
		if(window.sessionStorage && sessionStorage.setXcoodinate)
		{    
		     sessionStorage.setXcoodinate = "";       		     
		}
		if(window.sessionStorage && sessionStorage.setYcoodinate)
		{    
		     sessionStorage.setYcoodinate = "";       		     
		}
	   
}	   



	</script>
  </head>
  
  <!--  <script src="js/html5.js"></script>
   <script src="js/jquery.js"></script>
   <script src="js/jqueryUi.js"></script>
	<script src="js/bootstrap.min.js"></script> -->
   <body  id="loginPage_id" >

   <div style="background: rgb(255, 255, 255); text-align: center; color: rgb(23, 74, 117); font-size: 39px; position: absolute; opacity: 0.8;display:none;">miFIN</div>
   <%-- <jsp:include page="../common/header.jsp">
   <jsp:param name="sessionAlert" value="N"/>
  		</jsp:include> --%>
<!--       	<table class="text_header" width="100%" border="0"  cellspacing="0" cellspadding="0">
			<tr height="10">
				<td class="text_header" width="35%">
				</td>
				<td class="text_header" width="30%">
				</td>
				<td align="right" class="text_header" width="35%">
				</td>						
			</tr>
		</table> -->
		<form action ="" method = "post" focus="userId">

	<%-- 	<div class="logintext">miFIN  &trade;</div>
		<table autocomplete="off"  width="20%" class="right-pan login_tab" border="0" cellspacing="0" cellspadding="0">
						<tbody>
						<tr><td height="5" colspan="4"><font color="red" size="2">
						</font></td></tr>
						<tr>
							<!-- <td height="5" style="width: 10%;"></td> -->
												
							<td align="right"  style="width: 35%;" colspan="5">
								<div class="user_td">
									<img alt="login_input" src="images/username.png">
									<input name="userId" type="text" maxlength="16" value="Username"  autocomplete="off">
								</div>
							
							<!-- <td height="5" style="width: 10%;"></td> -->
							<DIV  class="pass_td">
							<img alt="login_input" src="images/pass.png">
							<input name="password" type="password" maxlength="16" value="" autocomplete="off">
							</DIV>
							</td>
							<td height="5" style="width: 10%;"></td>
						</tr>
						
						
						
						
							
							<tr>
							
							<!-- <td height="5" style="width: 10%;"></td><td height="5" style="width: 35%;"></td><td height="5" style="width: 10%;"></td> -->					
							<td align="center" style="width: 20%;" colspan="5">
								
								<div class="login_input"  onclick="loginform()">
								<img alt="login_input" src="images/login.png">
								<input name="login" type="button" value="Login">
								</div><div class="reset_input">
								<img alt="login_input" src="images/reset.png">
								<input onclick="resetValue();" type="button" value="Reset">
								</div>

							</td>
								
								
							</td>
						</tr>
						<!-- <tr><td height="5" colspan="4"></td></tr> -->
						<tr >
						
						<td class="MENU_FOOTER" height="5" colspan="6"><span style="padding-left: 5%; float: left;">QUALTECH CONSULTANTS PVT LTD</span><span style="padding-right: 4%; float: right;"> VERSION 3.0.0.1.10</span></td>
						
						</tr>
					</tbody></table> --%>
					  <DIV class="imglogo"><img src="images/mifin1.png" /></div>
					<div class="main_divlogin">
					 
					    <div class="row-centered">
					 
					        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 col-centered">
					        	
					        	<div class="login_inputBox">
						        	<div class="user_td">
										<i class="fa fa-user" aria-hidden="true"></i>

										<input name="userId" type="text" maxlength="16" value="Username"  autocomplete="off">
									</div>
									<DIV class="pass_td" >
										<i class="fa fa-lock" aria-hidden="true"></i>

										<input name="password" type="password" maxlength="16" value="" autocomplete="off">
									</DIV>
					        	
								</div>
								<div class="login_inputButtons">
						        	<div>
										<i class="glyphicon glyphicon-repeat"></i><!-- <i class="fa fa-sign-in" aria-hidden="true"></i> -->
										<button type="button" class="btn btn-primary" onclick="resetValue()">RESET</button>
										</div>
										<div>
										<i class="fa fa-sign-in" aria-hidden="true"></i>
										<button type="button" class="btn btn-primary" onclick="loginform()">LOGIN</button>
										</div>
								
										
									
								</div>
					        	<div class="login_footer">
						        	<span >QUALTECH CONSULTANTS PVT LTD</span>
						        	<span > VERSION 3.0.0.1.10</span>
					        	</div>
					        
					        </div>
					      
					    </div>
					</div>
	</form>
	<%-- <jsp:include page="../common/footer.jsp" flush="false" /> --%>
	

  </body> 
</html>
