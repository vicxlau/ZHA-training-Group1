<!--A Design by W3layouts
Author: W3layout
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<!DOCTYPE HTML>
<html>
<head>
<title>Login</title>
<%@ include file="header.jsp"%>
</head>
<body>
	<%@ page pageEncoding="UTF-8" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%-- 	<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" /> --%>
	<c:set var="language" value="${not empty sessionScope.customer.locale?sessionScope.customer.locale:en}" />
<%-- 	<c:set var="language" value="en" scope="session" /> --%>

	<fmt:requestEncoding value="UTF-8" />
	<fmt:setLocale value="${language}" />
	<fmt:setBundle basename="com.oocl.shopwebdemo.i18n.locale" />
<%-- 	<fmt:setBundle basename="myapp" var="lang"/>  --%>
  
	<%@ include file="top-menu.jsp"%>
	<div class="login_sec">
		<div class="container">
			<ol class="breadcrumb">
				<li><a href="${shop}/home">Home</a></li>
				<li class="active">
					<fmt:message key="top-menu-login-btn" />
				</li>
			</ol>
			<h2>Login</h2>
			<div class="col-md-6 log">
				<p>
					Welcome, please enter the folling to continue.
				</p>
				<p>
					If you have previously Login with us, <span>click here</span>
				</p>
				<form action="AccountServlet" method="post">
					<h5>ID:</h5>
					<input type="text" name="inputEmail" value="">
					<h5>Password:</h5>
					<input type="password" name="inputPassword" value="">
					<div class="checkbox">
						<label> <input type="checkbox" value="remember-me">
							Remember me
						</label>
					</div>
					<input type="submit" value="Login"> 
<!-- 					<a href="#">Forgot Password ?</a> -->
					${requestScope.error}
				</form>
			</div>
			<div class="col-md-6 login-right">
				<h3>NEW REGISTRATION</h3>
				<p>By creating an account with our store, you will be able to
					move through the checkout process faster, store multiple shipping
					addresses, view and track your orders in your account and more.</p>
				<a class="acount-btn" href="account.html">Create an Account</a>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>