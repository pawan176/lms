<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/includes/taglibs.jsp"%>

<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>MMM</title>
<link href="resources/css/login/reset.css"  rel="stylesheet" type="text/css"/>
<link href="resources/css/login/manage-list.css"  rel="stylesheet" type="text/css"/>
<link href="resources/css/login/top-link.css"  rel="stylesheet" type="text/css"/>
<link href="resources/css/login/winning-agent.css"  rel="stylesheet" type="text/css"/>
<link href="resources/css/login/gearing-up.css"  rel="stylesheet" type="text/css"/>
<link href="resources/css/login/login-sec.css"  rel="stylesheet" type="text/css"/>
<link href="resources/css/login/footer.css"  rel="stylesheet" type="text/css"/>
<link href="resources/css/login/font.css"  rel="stylesheet" type="text/css"/>
<link href="resources/css/login/jquery.bxslider.css"  rel="stylesheet" type="text/css"/>
<script src="resources/js/jquery.js" type="text/javascript"></script>
<script type="text/javascript" src="resources/js/login/html5shiv.min.js"></script>
<script type="text/javascript" src="resources/js/login/jquery.bxslider.min.js"></script>
<script type="text/javascript">
			$('document').ready(function(){
				var requestNewSession = "${errors}";				
				if(requestNewSession === 'RequestNewSession' ){
					if (confirm('You are already logged in from another system/device. Press OK to start a new session - old session will be killed automatically!')) {
					    window.location.href = 'openNewSession.do';
					} else {
						window.location.href = 'login.do';
					}
				}
			});
</script>
<script type="text/javascript">
$('document').ready(function(){
$('.bxslider').bxSlider({
        mode: 'fade',
        auto:'true',
        pager:'false'
	});
});
</script>
<script type="text/javascript">
$('document').ready(function(){
if ($(window).width() < 767) {
  $('.login-link').click(function(){
     var win = window.open("mobile-login.html", '_self');
     win.focus();
  });
  }
  $('.go-btn').click(function(){
   window.open("index.html",'_self');
  });
});
$('document').ready(function(){
if (jQuery(window).width() < 767 ) {
window.scrollTo(50,158);
}
});
</script>
</head>
<body>
  <article class="wrapper">
    <!--START - banner -->
    <article class="login-banner">
      <article class="shade">
        <a href="index.html" class="login-logo"><font size="+4" color="#556699" face="" style="font-family: times new roman">miFIN</font></a>
        <article class="right-banner">
         <ul class="login-social-icon">
          <li><a href="#" class="facebook"></a></li>
          <li><a href="#" class="twitter"></a></li>
         </ul>
        <a href="#" class="login-call"><span></span>1800 180 2333</a>
        <!-- START login slider 1 and login slider 2  -->
        <article class="login-slides">
          <ul class="bxslider">
            <!-- START login slider 1 (Our winning agents) -->
            <!-- END login slider 1 (Our winning agents) -->
            <!-- START login slider 2 (Gearing up) -->
            <li>
              <div class="gearing-up">
                <h2>Gearing up for Success</h2>
                <strong>Joining hands for the journey</strong>
                <ul class="bxslider2">
                  <li>
                    <div>
                      <figure><img src="resources/images/login/aparna-sharma.jpg" width="98" height="97" alt="Aparna Sharma" title="Aparna Sharma"/></figure>
                      <div class="gearing-up-success">
                        <strong>Aparna Sharma</strong>
                        <p>Senior Vice Precident</p>
                         <c:if test="${not empty svpmessage}">
							<p style="color: #66FF33">${svpmessage}</p>
						</c:if>
                      </div>
                    </div>
                  </li>
                </ul>
              </div>
            </li>
            <!-- END login slider 1 (Gearing up) -->
           <li>
              <div class="gearing-up">
                <h2>Gearing up for Success</h2>
                <strong>Joining hands for the journey</strong>
                <ul class="bxslider2">
                  <li>
                    <div>
                      <figure><img src="resources/images/login/rahul-soota.png" alt="rahul-soota" title="rahul-soota"/></figure>
                      <div class="gearing-up-success">
                        <strong>Rahul Soota</strong>
                        <p>CEO</p>
                        <c:if test="${not empty ceomessage}">
							<p style="color: #66FF33">${ceomessage}</p>
						</c:if>
                      </div>
                    </div>
                  </li>
                </ul>
              </div>
            </li>
		  <!-- END login slider 2 (Gearing up) -->
		  </ul>
        </article>
        <!-- END login slider 1 and login slider 2  -->
        <!-- START login block -->
        <div class="login-block">
        <a class="login-link" id="login-link">Login<em></em></a>
        <c:if test="${not empty errors}">
				<span class="err"><label style="color: red;font-size:14px;">  ${errors} </label></span>
		</c:if>
        <!--<a class="new-user" href="registration.html"><em></em>New User</a>-->
          <form action="loginAutentication.do" method="post"  >          
            <input name="foilautofill" style="display: none;" type="password" />
            <input type="text" id="userName" style="display:none" value="fakeInput" />                 
            <ul class="login-content">             	
              <li class="user-name">
                <div>
                <input type="text" name="userName" id="userName" placeholder="Username"
                onselectstart="return false" onpaste="return false;" onCopy="return false" 
                onCut="return false" onDrag="return false" onDrop="return false" autocomplete="off">
                <span></span>
              </div>
            </li>
              <li class="password"><div>
                <input type="password" name="password" placeholder="Password"
                onselectstart="return false" onpaste="return false;" onCopy="return false" 
                onCut="return false" onDrag="return false" onDrop="return false" autocomplete="off">
                <span></span>
              </div></li>
              <li class="go"><input type="submit" class="go-btn" value="Go" name="" ></li>
              <li class="reset"><input type="button" class="reset" value="Reset" name=""></li>
            </ul>
          </form>
        </div>
      </article>
    </article>
    </article>
    <!--END - banner -->
    <!-- START Manage it Smoothly container -->
    <!-- END Manage it Smoothly container -->
    <!-- START footer -->
    <footer>
      <section class="footer">
         <!-- START copyright -->
        <article class="left-footer">
          <p class="copy"><img src="resources/images/login/qc-logo.png" style="vertical-align: middle"/>&copy; Qualtech Consultants, All rights reserved.</p>
        </article>
         <!-- END copyright -->
        <!-- START 1. social icons 2. contat details -->
        <article class="right-footer">
          <!-- START 1. social icons -->
          <!-- END 1. social icons -->
          <!-- START 2. contact details (a. toll free b.email )-->
          <ul class="footer-info">
            <li><em class="call"></em><a href="tel:1800 3628 1839" >1800 3628 1839</a></li>
            <li><em class="mail"></em><a href="#">sales@abc.com</a></li>
          </ul>
          <!-- END 2. contact details (a. toll free b.email )-->
        </article>
        <!-- END 1. social icons 2. contat details -->
      </section>
    </footer>
    <!-- END footer  -->
</article>
<script>
$(function(){
	$(".reset").click(function(){
		$("li.password input, li.user-name input").val("");
	});
});
</script>
</body>
</html>
