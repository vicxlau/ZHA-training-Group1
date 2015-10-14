<!--A Design by W3layouts
Author: W3layout
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="shop" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE HTML>
<html>
<head>
<title>Shopping Cart</title>
<%@ include file="header.jsp"%>
</head>
<body>
	<%@ include file="top-menu.jsp"%>
	<div class="cart_main">
		<div class="container">
			<ol class="breadcrumb">
				<li><a href="${shop}/home">Home</a></li>
				<li class="active">Cart</li>
			</ol>


			<div class="cart-items">
				<div class="cart-header">
					<div class="cart-sec">
						<div class="bank-item-info">							
							<form name="yeepay" action='https://www.yeepay.com/app-merchant-proxy/node' method='POST' target="_blank">
								<input type='hidden' name='p0_Cmd'   value='${requestScope.p0_Cmd}'>
								<input type='hidden' name='p1_MerId' value='${requestScope.p1_MerId}'>
								<input type='hidden' name='p2_Order' value='${requestScope.p2_Order}'>
								<input type='hidden' name='p3_Amt'   value='${requestScope.p3_Amt}'>
								<input type='hidden' name='p4_Cur'   value='${requestScope.p4_Cur}'>
								<input type='hidden' name='p5_Pid'   value='${requestScope.p5_Pid}'>
								<input type='hidden' name='p6_Pcat'  value='${requestScope.p6_Pcat}'>
								<input type='hidden' name='p7_Pdesc' value='${requestScope.p7_Pdesc}'>
								<input type='hidden' name='p8_Url'   value='${requestScope.p8_Url}'>
								<input type='hidden' name='p9_SAF'   value='${requestScope.p9_SAF}'>
								<input type='hidden' name='pa_MP'    value='${requestScope.pa_MP}'>
								<input type='hidden' name='pd_FrpId' value='${requestScope.pd_FrpId}'>
								<input type="hidden" name="pr_NeedResponse"  value="${requestScope.pr_NeedResponse}">
								<input type='hidden' name='hmac' value='${requestScope.hmac}'>
								<input type='submit' class="order" />
							</form>
						</div>
						<div class="clearfix"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>
