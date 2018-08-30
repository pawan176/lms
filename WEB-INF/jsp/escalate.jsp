<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<link href="css/style.css" rel="stylesheet">
<link rel="stylesheet" href="css/pickmeup.css" type="text/css" />
<!--   <script src="js/jquery.js" type="text/javascript"></script>  -->
  <script type="text/javascript" src="js/jquery.pickmeup.js"></script>
  <script src="js/jquery.easytabs.min.js" type="text/javascript"></script>
  <script type="text/javascript">
  function cancelHelp(){
	  document.getElementById("escalate").action = "cescalate.do";
	  document.getElementById("escalate").submit();
  }
  function validate(){
	  var initiatedTo=document.getElementById("initiatedTo").value;
	  var remark=document.getElementById("remarks").value;
	  if(initiatedTo==-1){
		  alert("PLEASE SELECT TO WHOM");
		  return false;
	  }
	  if(remark==""){
		  alert("PLEASE ENTER REMARK");
		  return false; 
	  }
	  return true;
  }
  </script>       
<div class="popup-leadstatus mainpopup" style="display:block">
	<div class="popup-content">
		<div class="popup-title">
			<h1>Escalate</h1>
		</div>
		<div class="popup-text">
		<form action="referCase.do" id="escalate">
			<div class="userCommentBox">
				<fieldset>
				   <ul>
				    <li>
				     <label for="ToWhom"><spring:message code="label.toWhom"/></label>
					     <select class="span_6_of_12 fl" name="initiatedTo" id="initiatedTo">
					     	 <option value="-1">Select</option>
							 <c:forEach var="manager" items="${managerList}">
								<option value="${manager.userId}">${manager.userName}</option>
							</c:forEach> 																
						 </select>					     
				    </li>
				    <li>
				    	<label for="Remarks"><spring:message code="label.remarks"/></label>
				    	<textarea id="remarks" name="remarks" placeholder="Remarks"></textarea>
				    	<input type="hidden" name="helpType" value="escalate"/>				    
				    </li>
				    <li>
				    	<input type="submit" value="Submit" style="align :right;" onclick="return validate();"/>
				    	<input type="button" value="Cancel" onclick="javascript:cancelHelp();" style="align :right;"/>
				    </li>
				   </ul>
				  </fieldset>					
			</div>
		</form>
		</div>
	</div>
</div>
<script>
	$('.close-popup .close-white').click(function(){
	
		$('.mainpopup').fadeOut(100);
		
	});
</script>