<script type="text/javascript" src="resources/js/jquery.min.js"></script>
	<div class="container well">
<div class="padding-top-container col-lg-12 col-md-12 col-sm-12 col-xs-12">
	<!-- <div>
		<font color="red">This is Error page</font></div>
	</div> -->
	<!-- <div id="pageBody" class="wrapper-full"> -->
			<div style="padding-top: 140px;height: 250px;font-weight: bold;">
		      	<div style="width: 50px; vertical-align: middle;float:left;">
		          	<img alt="Aviva Life Insurance" src="https://instalife.avivaindia.com/InstaLife/assets/images/ico_information.gif">
		      	</div>
		      	<div id="textMessage" style="vertical-align: middle;float:left; font-size: 16px;height:79px;">
		          	<a href="login.do" style="text-decoration: none;color:red;"> OOPS! Unable to process your request right now, Seems like some error has been occurred</a> 
		      	</div>
		    </div>
         <!-- </div> -->
</div>
<script>

$('.prev-lead-icon').parent('.subheader-btn').hover(function(){
//alert('1');
	$(this).children('.prevlead-tooltip').fadeToggle(150);
});
$('.next-lead-icon').parent('.subheader-btn').hover(function(){
//alert('1');
	$(this).children('.nextlead-tooltip').fadeToggle(150);
});
$('.savee-icon').parent('.subheader-btn').hover(function(){
//alert('1');
	$(this).children('.savee-tooltip').fadeToggle(150);
});
$('.saveandexit-icon').parent('.subheader-btn').hover(function(){
//alert('1');
	$(this).children('.saveandexit-tooltip').fadeToggle(150);
});
	$('.usermenu-mobile').click(function(){
		$('.header-rightpanel').animate({"top":"0"});
	});
	$('.close-header-rightpanel').click(function(){
		$('.header-rightpanel').animate({"top":"-260px"});
	});
	</script>
</body>
</html>

