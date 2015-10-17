<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="shop" value="${pageContext.request.contextPath}"></c:set>
<c:set var="shopBackend" value="${pageContext.request.contextPath}/WEB-INF/backend"></c:set>
    
    <script src="${shop}/js/jquery.js"></script>						<!-- jQuery -->
    <script src="${shop}/js/bootstrap.min.js"></script>					<!-- Bootstrap Core JavaScript -->
    <link href="${shop}/css/bootstrap.min.css" rel="stylesheet">		<!-- Bootstrap Core CSS -->
    <link href="${shop}/css/shop-homepage.css" rel="stylesheet">		<!-- Custom CSS -->

	<title>~~~~咪走雞~~~~~~</title>

	<!-- Navigation -->
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="container">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="${shop}/home">咪走雞</a>
		</div>
		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="#">About</a></li>
				<li><a href="#">Services</a></li>
				<li><a href="#">Contact</a></li>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container --> </nav>
	