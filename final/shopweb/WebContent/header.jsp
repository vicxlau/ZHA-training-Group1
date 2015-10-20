<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="shop" value="${pageContext.request.contextPath}"></c:set>

<!-- <title>Furnyish Store a Ecommerce Category Flat Bootstarp Responsive Website Template | Home :: w3layouts</title> -->


<link href="${shop}/css/bootstrap.css" rel='stylesheet' type='text/css' />
<script type='text/javascript' src="${shop}/js/jquery-1.11.1.min.js"></script>				<!-- jQuery (necessary JavaScript plugins) -->			
<link href="${shop}/css/style.css" rel='stylesheet' type='text/css' />						<!-- Custom Theme files -->
<link href="${shop}/css/animated-notifications.css" rel='stylesheet' type='text/css' />		<!-- animated-notifications.css -->
<link href="${shop}/css/animate.css" rel='stylesheet' type='text/css' />					<!-- animate.css -->
<link href="${shop}/css/toastr8.min.css" rel='stylesheet' type='text/css' />					<!-- toastr8.min.css -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Furnyish Store Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<link href='http://fonts.googleapis.com/css?family=Montserrat|Raleway:400,200,300,500,600,700,800,900,100' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Playfair+Display:400,700,900' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Aladin' rel='stylesheet' type='text/css'>
<link href="${shop}/css/megamenu.css" rel="stylesheet" type="text/css" media="all" />		<!-- start menu -->
<script type="text/javascript" src="${shop}/js/megamenu.js"></script>
<script>$(document).ready(function(){$(".megamenu").megamenu();});</script>
<script src="${shop}/js/menu_jquery.js"></script>
<script src="${shop}/js/simpleCart.min.js"> </script>
<script src="${shop}/js/responsiveslides.min.js"></script>
<script src="${shop}/js/jquery.sticky.js"></script>
<script src="${shop}/js/animated-notifications.js"></script>
<script src="${shop}/js/toastr8.min.js"></script>
<script src="${shop}/js/notificationSocket.js"></script>
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

<link href="${shop}/css/form.css" rel="stylesheet" type="text/css" media="all" />			<!--etalage-->
<link rel="stylesheet" href="${shop}/css/etalage.css">
<script src="${shop}/js/jquery.etalage.min.js"></script>
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
