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
						<div class="cart-item-info">
							<h2>收货人信息</h2>
							<span>
								<div>
									<span>订单号： ${sessionScope.previousCart.id} </span>
								</div>
								<div>
									<span>姓名： ${sessionScope.previousCart.name}</span>
								</div>
								<div>
									<span>通讯地址：${sessionScope.previousCart.address}</span>
								</div>
								<div>
									<span>通讯电话：${sessionScope.previousCart.phone}</span>
								</div>
								<div>
									<span>支付金额：￥${sessionScope.previousCart.total}</span>
								</div>
								<div>
									<span>支付方式：
									<img src="${shop}/images/yibao.png"width="110" height="35" alt="" /></span>
								</div>
							</span>
						</div>
						<div class="clearfix"></div>
					</div>
				</div>
			</div>

			<div class="cart-items">
				<div class="cart-header">
					<div class="cart-sec">
						<div class="bank-item-info">
							<form action="${shop}/PaymentServlet" method="post">
								<h2>请选择支付银行</h2>
								<div class="bank-radio">
									<ul>
										<c:forEach items="${applicationScope.banks}" var="logo">
											<li>
												<input type="radio" name="pd_FrpId" value="${fn:substringBefore(logo,'.')}" />
												<img src="${shop}/images/bank/${logo}" alt="" />
											</li>
										</c:forEach>
									</ul>
								</div>	
<%-- 								<input type="hidden" name="p2_Order" value="${sessionScope.previousCart.id}"/>					 --%>
<%-- 								<input type="hidden" name="p3_Amt" value="${sessionScope.previousCart.total}"/>					 --%>
								<input type="hidden" name="status" value="pay"/>					
								<input type="submit" class="order" value="Place Order" /><br>
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
