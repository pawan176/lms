<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="resources/js/globalValidation.js" type="text/javascript"></script>
<script src="resources/js/myValidation.js" type="text/javascript"></script> 
<script type="text/javascript">
$(document).ready(function(){
	
	$(document).bind("copy paste",function(e) {
	     e.preventDefault();
	 });
	
    $(document).on("contextmenu",function(e){       
             e.preventDefault();
     });
 }); 
function showNotificationList(notificationid){
	
	var action = "";
	if(notificationid=='All')
		{
		action = "dismissAllnotification.do";
		
		}
	else if(notificationid == 'shownotification'){
		action = "getNotififactionDetails.do";
		dataString = "notificationId=show";
	}else{
		action = "dismissnotification.do";
		dataString = "notificationId="+notificationid;
	}
	
	var countID=$('#countID').val();
	var lidata="";
	  $.ajax({
		async : true,
		type : "post",
		url : action,
		dataType:"json" ,
		data : dataString, 
		context: document.body,
		success : function(response) {
			try{
				var c=0;
				for (var i = 0, len = response.length; i < len; ++i) {
				     var notificationObj = response[i];
				     if(notificationObj.flag=='today')
				     {
				    	 lidata=lidata+'<li><p><a href="contact.do?caseId='+notificationObj.caseid+'" >'+notificationObj.caseCode+'</a>';
					     lidata=lidata+" "+notificationObj.notification+" "+notificationObj.customerName+" "+notificationObj.caseCode+" at "+notificationObj.NotificationTime+"</p></li>";
				    }else{
				    	if(c==0){
				    		 lidata=lidata+'<hr>';c=1;
				    	}
				    	lidata=lidata+'<li><p><a href="contact.do?caseId='+notificationObj.caseid+'" >'+notificationObj.caseCode+'</a>';
					    lidata=lidata+" "+notificationObj.notification+" "+notificationObj.customerName+" "+notificationObj.notificationDate+" at "+notificationObj.NotificationTime+"</p></li>"; 
					
				    	 
				    }
				     }
				if($("ul.appendLi").children("li").length==0){
				$(".appendLi").append(lidata).stop();
				}
				lidata="";
			}catch(err){
				alert(err);
			}
		}});
	 


		
}

/* $('document').ready(function(){
	showNotificationList('shownotification');	
}); */

function validateField(){
	var firstName = $('#firstName').val();	
	var product = $('#product').val();
	var mobileNo = $('#mobileNo').val();
	//var subqueue = $('#queue').val();
	var source = $('#source').val();
	var campaign = $('#campaign').val();
	var allocate = $('#allocate').val();
	var msg="";
	var status=true;
	if (firstName == "") {   	
	      msg=msg+"FirstName,";
	      status=false;
	    }	
	if (mobileNo == "") {   
		 msg=msg+"Mobile No,";
	      status=false;
	    }
	 if(product==-1){
		 msg=msg+"QUEUE,";
	      status=false;
	}
	 /*if(subqueue==-1){
		 msg=msg+"Sub-Queue,";
	      status=false;
	} */
	if(source==-1){
		 msg=msg+"Source,";
	      status=false;
	}
	if(campaign==-1){
		 msg=msg+"Campaign,";
	      status=false;
	}
	if(allocate==-1){
		 msg=msg+"Allocate To,";
	      status=false;
	}
	if(status==false){
		msg = msg.substring(0, msg.length - 1);
		msg=msg+" must be specified";
		alert(msg);
		return status;
	}
	return status;
}

</script>
<header>
  <div class="mainheader"> <img src="resources/img/menu_img.png" alt="main-menu" class="mainmenuimg"/> <img style="height: 50px;" src="resources/img/logo.jpg" alt="Qualtech Logo" class="pageHeading"/>
    <h1 class="pageHeading"><span class="">miFIN</span> LEAD MANAGEMENT SYSTEM</h1>
    <div class="header-rightpanel">
    
      <div class="userspanel">
      <jsp:useBean id="today" class="java.util.Date" scope="page" />

        <div class="currentdate"><fmt:formatDate value="${today}" pattern="E dd MMM, yyyy" /></div>
        <div class="userpic"> <img class="circleimg" src="resources/img/pic.png" /><span class="downarrow headerarrowadjust"></span>

          <div class="userProfile">
            <ul>
              <li class="usr">${UserDetails.userfname} ${UserDetails.userlname}</li>
              <li class="changepass"><a href="changepwd.do">Change Password</a></li>
              <li class="logout"><a href="logout.do">Logout</a></li>
            
            </ul>
          </div>
         </div>
        <div class="rightpanel-more-outer">
          <div class="rightpanel-more"></div>
          <div class="profilePanel">
            <h3>Quick Create</h3>
            <fieldset>
           <form method="post" action="manageNewLead.do">
           <ul>
            <li>            
             <label for="firstName"><spring:message code="label.fName"/><span>*</span></label>
             <input type="text" id="firstName" name="firstName" placeholder="First Name" onkeypress="return onlyCarector(event);" class="validField"/>
            </li>
            <li>
             <label for="lastName"><spring:message code="label.lName"/></label>
             <input type="text" id="lastName" name="lastName" placeholder="Last Name" />
             <input type="hidden" name="listMobile[0].mobileContactTypeId" value="1">
             <input type="hidden" name="listMobile[0].primaryContact" value="Y">
             <input type="hidden" name="newleadType" value="quicklead" >
             <input type="hidden" name="requestType" value="quicklead" >  
            </li>
            <li>
             <label for="email"><spring:message code="label.email"/></label>
             <input type="text" id="listEmail[0].email" name="listEmail[0].email" placeholder="Email" />
            </li>
            <li>
             <label for="mobile"><spring:message code="label.mobile"/><span>*</span></label>
             <input type="text" id="mobileNo"  maxlength="10"
              onkeypress="return onlyNumeric(event);" name="listMobile[0].contactNo" onchange="validateMobile(this);"
              placeholder="Mobile" class="validField" />
            </li>
            <li>
             <label for="queue"><spring:message code="label.queue"/><span>*</span></label>
                <select name="productId" id="product" class="validField">
						<option value="-1">select</option>
	             	<c:forEach var="product" items="${quickLeadMasters.productMaster}">
	             		<c:if test="${product.prodTypeId=='1000000001'}">
								<option value="${product.prodId }">${product.prodName}</option>
						</c:if>
					</c:forEach>
				</select>
            </li>
            <li>
             <label for="subqueue"><spring:message code="label.subQueue"/></label>
             	<select name="queueId"
						id="queue" class="validField" >
						<option value="1000000002">WARM</option>
						<%-- <c:forEach var="sub"
							items="${quickLeadMasters.subQueueMaster}">
							<option value="${sub.subQueueId}">${sub.subQueue}</option>
						</c:forEach> --%>
				</select>
            </li>
            <li>
             <label for="source"><spring:message code="label.source"/><span>*</span></label>
              		<select name="source"	id="source" class="validField">
							<option value="-1">select</option>
								<c:forEach var="prod" items="${quickLeadMasters.sourceMaster}">
									<option value="${prod.caseSourceId }">${prod.sourceName}</option>
								</c:forEach>
					</select> 
            </li>
            <li>
             <label for="campaign"><spring:message code="label.campaign"/><span>*</span></label>
              		<select name="campaign"
						id="campaign" class="validField">
						<option value="-1">select</option>
						<c:forEach var="campaign"
							items="${quickLeadMasters.campaignMaster }">
							<option value="${campaign.campaignId }">${campaign.campaignName}</option>
						</c:forEach>
					</select>
            </li>
            <li>
             <label for="allocateto"><spring:message code="label.allocateTo"/><span>*</span></label>
              		<select name="allocateTo" id="allocate" class="validField">
						<option value="-1">Select</option>
						<c:forEach var="allocate" items="${allocatedToNewLead}">
							<option value="${allocate.userId }">${allocate.userName }</option>
						</c:forEach>
					</select>
            </li>
            <li>&nbsp;</li>
            <li>
              <div class="loginButton"><input type="submit" value="Save" name="Save" onclick="return validateField();"></div></li>
           </ul>
           </form>
          </fieldset> 
          </div>
        </div>
      </div>
      <div class="alertt1" onclick="showNotificationList('shownotification');"> 
       <a><span class="notifs">${notificationCount}</span><span class="bell-icon"></span></a>
        <div class="alert-flyoutmenu">
          <div class="notificationTab"><span>Notification</span></div>
          <ul class="appendLi">          
          </ul>
        </div>
      </div>          
      <a class="close-header-rightpanel">Close <img src="resources/img/close-grey.png" class="close-header-rightpanel-icon"/></a> </div>
    <div class="header-rightpanel-mobile"> <a href="#" class="usermenu-mobile"><img src="resources/img/usermenu-mobile.png" alt="user menu"/></a> </div>
  </div>
  <script>

	
	$(".bell-icon").click(function(){
			  if(!$(".alert-flyoutmenu").is(":visible")){
		  	  $(".alert-flyoutmenu").fadeIn(70); 
		  	//abc=false;	
		  }else{ 
			//  alert("2");
			  $(".alert-flyoutmenu").fadeOut(70);
		  	//abc=true;	
		  }
	});
	$(document).click(function (ss) {
	    if (!$(ss.target).closest(".alert-flyoutmenu, .alertt1,.notificationTab, ul.appendLi").length) {
	        $(".alert-flyoutmenu").fadeOut(150);
	       		     
	    }
	});
	
</script>

<!--[if IE 8]>
	
	<style type="text/css">
	.worklist-searchbtn{padding: 0 !important; width: 4% !important}
	.worklist-searchbtn input{right: -25px !important; position: relative !important}
	select{padding-right: 0 !important}
	.searchfields{max-width: 20% !important}
	select::-ms-expand{display: none}
	</style>
	
<![endif]--> 