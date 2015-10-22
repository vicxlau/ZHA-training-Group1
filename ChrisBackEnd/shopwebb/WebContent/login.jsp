<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- 只能引入工程内部的资源,自动添加工程名 -->
<%@ include file="/public/public.jspf" %>

<link href="/shopweb/css/bootstrap.css" rel='stylesheet' type='text/css' />
<script type='text/javascript' src="/shopweb/js/jquery-1.11.1.min.js"></script>				<!-- jQuery (necessary JavaScript plugins) -->			
<link href="/shopweb/css/style.css" rel='stylesheet' type='text/css' />						<!-- Custom Theme files -->
<link href="/shopweb/css/animated-notifications.css" rel='stylesheet' type='text/css' />		<!-- animated-notifications.css -->
<link href="/shopweb/css/animate.css" rel='stylesheet' type='text/css' />					<!-- animate.css -->
<link href="/shopweb/css/toastr8.min.css" rel='stylesheet' type='text/css' />					<!-- toastr8.min.css -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Furnyish Store Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<link href='http://fonts.googleapis.com/css?family=Montserrat|Raleway:400,200,300,500,600,700,800,900,100' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Playfair+Display:400,700,900' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Aladin' rel='stylesheet' type='text/css'>
<link href="/shopweb/css/megamenu.css" rel="stylesheet" type="text/css" media="all" />		<!-- start menu -->
<script type="text/javascript" src="/shopweb/js/megamenu.js"></script>
<script>$(document).ready(function(){$(".megamenu").megamenu();});</script>
<script src="/shopweb/js/menu_jquery.js"></script>
<script src="/shopweb/js/simpleCart.min.js"> </script>
<script src="/shopweb/js/responsiveslides.min.js"></script>
<script src="/shopweb/js/jquery.sticky.js"></script>
<script src="/shopweb/js/animated-notifications.js"></script>
<script src="/shopweb/js/toastr8.min.js"></script>
<script src="/shopweb/js/notificationSocket.js"></script>
<script>
    // You can also use "$(window).load(function() {"
    $(function () {
      // Slideshow 1
      $("#slider1").responsiveSlides({
         auto: true,
		 nav: true,
		 speed: 500,
		 namespace: "callbacks",
      });
    });
</script>

<!-- // for single.jsp -->

<link href="/shopweb/css/form.css" rel="stylesheet" type="text/css" media="all" />			<!--etalage-->
<link rel="stylesheet" href="/shopweb/css/etalage.css">
<script src="/shopweb/js/jquery.etalage.min.js"></script>
<script>
			jQuery(document).ready(function($){

				$('#etalage').etalage({
					thumb_image_width: 300,
					thumb_image_height: 400,
					source_image_width: 900,
					source_image_height: 1200,
					show_hint: true,
					click_callback: function(image_anchor, instance_id){
						alert('Callback example:\nYou clicked on an image with the anchor: "'+image_anchor+'"\n(in Etalage instance: "'+instance_id+'")');
					}
				});

			});
</script><!-- //etalage-->


<title>Login</title>
</head>
<body>
<!-- color header -->
<div class="top_bg">
	<div class="container">
		<div class="header_top-sec">
			<div class="top_left">
				<ul>
					<li class="top_link">Email:<a href="mailto@example.com">info(at)Mobilya.com</a></li>|
					<li class="top_link"><a href="/shopweb/login.jsp">My Account</a></li>|	
					<li class="top_link" id="lang-en"><a href="/shopweb/AccountServlet?locale=en">EN</a></li>|
					<li class="top_link" id="lang-cn"><a href="/shopweb/AccountServlet?locale=zhcn">中</a></li>								
				</ul>
				<div class="social">
					<ul>
						<li><a href="#"><i class="facebook"></i></a></li>
						<li><a href="#"><i class="twitter"></i></a></li>
						<li><a href="#"><i class="dribble"></i></a></li>	
						<li><a href="#"><i class="google"></i></a></li>										
					</ul>
				</div>
			</div>
				<div class="clearfix"> </div>
		</div>
	</div>
</div>
<!-- color header end -->

  
  
  
<div class="header_top">
	 <div class="container">
		 <div class="logo">
		 	<a href="/shopweb/home"><img src="/shopweb/images/logo.png" alt=""/></a>			 
		 </div>
		 <div class="header_right">	
			 <div class="login">
				 <a href="/shopweb/login.jsp">Login</a>
			 </div>
			 <div class="cart box_1">
				<a href="/shopweb/cart.jsp">
					<h3> ￥<span id="cart-summary-total"> 
							
							    
							        0.00
							    
							    
							

					</span> (<span id="cart-summary-num" class="">0</span> items)<img src="/shopweb/images/bag.png" alt=""></h3>

				</a>	
<!-- 				<p><a href="javascript:;" class="simpleCart_empty">Empty cart</a></p> -->
				<div class="clearfix"> </div>
			 </div>				 
		 </div>
		  <div class="clearfix"></div>	
	 </div>
</div>
<!--cart-->
	 
<!-- item menu ---->
<div class="mega_nav">
	 <div class="container">
		 <div class="menu_sec">
		 <!-- start header menu -->
		 <ul class="megamenu skyblue">
			 <li class="active grid"><a class="color1" href="/shopweb/home">Home</a></li>
			 
			 
				 <li><a class="color2" href="/shopweb/retrievalServlet?action=category&id=1&pageNum=1">衣服</a></li>
			 
				 <li><a class="color3" href="/shopweb/retrievalServlet?action=category&id=2&pageNum=1">電話</a></li>
			 
				 <li><a class="color4" href="/shopweb/retrievalServlet?action=category&id=3&pageNum=1">鞋</a></li>
			 
				 <li><a class="color5" href="/shopweb/retrievalServlet?action=category&id=4&pageNum=1">電腦</a></li>

			   </ul> 
			   <div class="search">
				 <form action="/shopweb/searchProductServlet" method="get">
					<input type="text" name="keyword" id="keyword" list="search-suggest-list" placeholder=Search...>
					<datalist id="search-suggest-list"></datalist>
					
					<input type="hidden" name="pageNum" value="1"> 
<!-- 					<input type="hidden" name="pageSize" value="6"> -->
					<input type="submit" value="">
					</form>
			 	</div>
			 	
			 	<script>
					$(function() {
				 		$('#keyword').keyup(function() {

				 			var keyword = $(this).val();
				 			
				 			$.post("/shopweb/searchProductServlet", { "keyword":keyword }, function(data) {
				 				var suggestions = JSON.parse(data);

				 				$('#search-suggest-list').empty();
				 				for (var item of suggestions) {
				 					$('#search-suggest-list').append(new Option(item));
				 				}
				 				
				 			});
				 		});
				 		
					});
			 	</script>
			 	
			 <div class="clearfix"></div>
		 </div>
	  </div>
</div>
<!-- item menu end ---->
	<div class="login_sec">
		<div class="container">
			<ol class="breadcrumb">
				<li><a href="/shopweb/home">Home</a></li>
				<li class="active">
					Login
				</li>
			</ol>
<!---->
			<div class="col-md-6 log">
				<p>
					Welcome, please enter the folling to continue.
				</p>
				<p>
					If you have previously Login with us, <span>Click here</span>
				</p>
				<form action="${shop}/StatisticServlet" method="post">
					<h5>ID :</h5>
					<input type="text" name="name" value="">
					<h5>Password : </h5>
					<input type="password" name="pass" value="">
					<div class="checkbox">
						<label> <input type="checkbox" value="remember-me">
							Remember me
						</label>
					</div>
					<input type="submit" value=Login> 
<!-- 					<a href="#">Forgot Password ?</a> -->
					
				</form>
	
			</div>
			<div class="col-md-6 login-right">
				<h3>NEW REGISTRATION</h3>
				<p>By creating an account with our store, you will be able to move through the checkout process faster, store multiple shipping addresses, view and track your orders in your account and more.</p>
				<a class="acount-btn" href="account.html">CREATE ACCOUNT</a>
			</div>
			<div class="clearfix"></div>  
		</div>
	</div>
    
</body>
</html>