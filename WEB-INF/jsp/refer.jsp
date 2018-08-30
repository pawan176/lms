<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="css/style.css" rel="stylesheet">
<link rel="stylesheet" href="css/pickmeup.css" type="text/css" />
  <script src="js/jquery.min.js" type="text/javascript"></script> 
  <script type="text/javascript" src="js/jquery.pickmeup.js"></script>
  <script src="js/jquery.easytabs.min.js" type="text/javascript"></script>
  <script type="text/javascript">
  function cancelHelp(){
	  	document.getElementById("refer").action = "crefer.do";
		document.getElementById("refer").submit();
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
			<h1>Refer</h1>
		</div>
		<div class="popup-text">
		<form action="referCase.do" id="refer">
			<div class="userCommentBox">
				<fieldset>
				   <ul>
				    <li>
				     <label for="ToWhom">To Whom </label>
					     <select class="span_6_of_12 fl" name="initiatedTo" id="initiatedTo">
					     	 <option value="-1">Select</option>
							 <c:forEach var="peereportee" items="${peerAndreportee}">
								<option value="${peereportee.userId}">${peereportee.userName}</option>
							</c:forEach> 																
						 </select>					     
				    </li>
				    <li>
				    	<label for="Remarks">Remarks</label>
				    	<textarea id="remarks" name="remarks" placeholder="Remarks"></textarea>
				    	<input type="hidden" name="helpType" value="refer"/>				    
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