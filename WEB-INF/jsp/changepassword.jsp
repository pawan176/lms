<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="css/style.css" rel="stylesheet">
<link rel="stylesheet" href="css/pickmeup.css" type="text/css" />
  <script src="js/jquery.min.js" type="text/javascript"></script> 
  <script type="text/javascript" src="js/jquery.pickmeup.js"></script>
  <script src="js/jquery.easytabs.min.js" type="text/javascript"></script>
  <script type="text/javascript">
  $(document).ready(
			function() {
				var msg = '${Failure}';
				//alert(msg);
				if(msg!=""){
					alert(msg);
				}
			});
  
  function toggle_password(){
	  
	    var d1 = document.getElementById("currentpwd");
	    var d2 = document.getElementById("newpwd");
	    var d3 = document.getElementById("confirmnewpwd");	    
	    var tag2 = document.getElementById("showhide");
	    	    
	    if (tag2.innerHTML == 'Show'){
	    	d1.setAttribute('type', 'text');
	    	d2.setAttribute('type', 'text');
	    	d3.setAttribute('type', 'text');	    	
	        tag2.innerHTML = 'Hide';

	    } else {
	        d1.setAttribute('type', 'password');
	    	d2.setAttribute('type', 'password');
	    	d3.setAttribute('type', 'password');	    	
	        tag2.innerHTML = 'Show';
	    }
   }
  
  
  
  function cancelPassword(){
	  document.getElementById("changepwd").action = "workList.do";
	  document.getElementById("changepwd").submit();
  }
  
  function validate(){
	  
	  document.getElementById("currentpwd").style.border = '1px solid #888888';
	  document.getElementById("newpwd").style.border = '1px solid #888888';
	  document.getElementById("confirmnewpwd").style.border = '1px solid #888888';
	  
	  var currnetpassword = document.getElementById("currentpwd").value;
	  var newpassword = document.getElementById("newpwd").value;
	  var confirmnewpassword = document.getElementById("confirmnewpwd").value;
	
	 if(currnetpassword == ''){
		  alert("Please Enter Current Password");
		  document.getElementById("currentpwd").style.border = '1px solid #980000';
		  return false;
	  }
	  
	 if(newpassword == ''){		 
		  alert("Please Enter New Password");
		  document.getElementById("newpwd").style.border = '1px solid #980000';
		  return false;
	 }
	  
	 if(confirmnewpassword == ''){
		  alert("Please Enter Confirm New Password");
		  document.getElementById("confirmnewpwd").style.border = '1px solid #980000';
		  return false;
	  }
	  
	  
	  if(newpassword != confirmnewpassword){
		  alert("Password and Confirm Password don't match");
		  document.getElementById("newpwd").style.border = '1px solid #980000';
		  document.getElementById("confirmnewpwd").style.border = '1px solid #980000';
		  return false;
	  }
	/*   re = /[0-9]/;
      if(!re.test(newpassword)) {
        alert("Password must contain at least one number");
        return false;
      }
      re = /[a-z]/;
      if(!re.test(newpassword)) {
        alert("Password must contain at least one lowercase letter");        
        return false;
      }
      re = /[A-Z]/;
      if(!re.test(newpassword)) {
        alert("Password must contain at least one uppercase letter");       
        return false;
      }
      re=/[!@#$%~]/;
      if(!re.test(newpassword)) {
          alert("Password must contain at least one Special character (!@#$%~)");       
          return false;
        } */
	  return true;
  }
  
  
  </script>       
<div class="popup-leadstatus mainpopup" style="display:block">
	<div class="popup-content">
		<div class="popup-title">
			<h1>Change Password</h1>
		</div>
		<div class="popup-text">
		<form action="changepwdaction.do" id="changepwd">
			<div class="userCommentBox">
				<fieldset>
				   <ul>
				    <li>
				     <label for="ToWhom">Current Password</label>
				      <input name="foilautofill" style="display: none;" type="password" />
				       <input type="Password" name="currentpwd" id="currentpwd" 
					    style="align :right;border: 1px solid #C7C7C7;border-radius: 3px;padding: 2px 5px;" 
					     onselectstart="return false" onpaste="return false;" onCopy="return false" 
               			 onCut="return false" onDrag="return false" onDrop="return false" autocomplete="off"/>					     					    
				    </li>
				    <li>
				    	<label for="ToWhom">New Password</label>
				    	<input type="Password" name="newpwd" id="newpwd" 
				    	style="align :right;border: 1px solid #C7C7C7;border-radius: 3px;padding: 2px 5px;"
				    	 onselectstart="return false" onpaste="return false;" onCopy="return false" 
                		onCut="return false" onDrag="return false" onDrop="return false" autocomplete="off" />
				    </li>
				    
				    <li>
				    	<label for="ToWhom">Confirm New Password</label>
				    	<input type="Password" name="confirmnewpwd" id="confirmnewpwd" 
				    	style="align :right;border: 1px solid #C7C7C7;border-radius: 3px;padding: 2px 5px;"
				    	 onselectstart="return false" onpaste="return false;" onCopy="return false" 
               			 onCut="return false" onDrag="return false" onDrop="return false" autocomplete="off" />
				    </li>
				    
				    <li>
				    	<input type="submit" value="Submit" style="align :right;" onclick="return validate();"/>
				    	<input type="button" value="Cancel" onclick="javascript:cancelPassword();" style="align :right;border: 1px solid #C7C7C7;border-radius: 3px;padding: 2px 5px;"/>
				    	<a href="#" onclick="toggle_password();" id="showhide">Show</a>
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