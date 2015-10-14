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
	
	<%response.setHeader("cache-control", "no-store");%>
   <!-- 如果session中没有购物车,则自动跳转到首页 -->
   <c:if test="${sessionScope.cart==null}">
   	    <c:redirect url="../home" />
   </c:if>
   
   
	<div class="cart_main">
		<div class="container">
			<form action="${shop}/CartServlet" method="post">
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
									<span>姓名： 小小</span>
								</div>
								<div>
									<span>通讯地址：广州天河区中山大道123号棠下街1号</span>
								</div>
								<div>
									<span>通讯电话：010-1000000</span>
								</div>
								<div>
									<span>电子邮件：czbk@qq.com</span>
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
						<div class="cart-item-info">
							<h2>
								确认收货地址 <small>(如果未选择地址，我们将按您注册时的默认地址发货)</small>
							</h2>
							<span> <c:forEach
									items="${sessionScope.customer.addrList}" var="addr">
									<input type="radio" name="addr_id" value="${addr.addr_id}"
										id="" />
									<span>收货人： <b>${addr.recipient_name}</b>&nbsp;&nbsp; 电话：
										<b>${addr.recipient_phone}</b> &nbsp;&nbsp; 送货地址：<b>${addr.recipient_address}</b>&nbsp;&nbsp;
										邮政编码：<b>${addr.recipient_post}</b></br>
									</span>
								</c:forEach>
							</span>
						</div>
						<div class="clearfix"></div>
					</div>
				</div>
			</div>

			<div class="cart-items">
				<h2>My Shopping Cart</h2>
				<c:forEach items="${sessionScope.cart.itemList}" var="item">
					<div class="cart-header">
						<div class="cart-sec">
							<div class="cart-item cyc">
								<img src="${shop}/product_pic/${item.pic}" />
							</div>
							<div class="cart-item-info">
								<h3>
									<a href="${shop}/retrievalServlet?action=product&id=${item.id}">
										${item.name}</a> <span>Model No: 3578</span>
								</h3>
								<h4>
									<span>￥</span>${item.price}</h4>
								<p class="qty">Qty :: ${item.number}</p>
							</div>
							<div class="clearfix"></div>
							<!-- 						<div class="delivery"> -->
							<!-- 							<p>Service Charges:: Rs.50.00</p>							  -->
							<!-- 						</div>						 -->
						</div>
					</div>
				</c:forEach>
			</div>
			
			<!-- Remark -->			
			<div class="cart-items" style="float: left;">
				<div class="cart-header">
					<div class="cart-sec">
						<div class="cart-item-info">
							<h3>Remark</h3>
							<input type="text" name="remark"/> 

						</div>
						<div class="clearfix"></div>
					</div>
				</div>
			</div>

			<div class="cart-total">
				<div class="price-details">
					<h3>Price Details</h3>
					<span>Total</span> <span class="total">￥${sessionScope.cart.total}</span>
					<span>Discount</span> <span class="total">---</span> <span>Delivery
						Charges</span> <span class="total">￥0.00</span>
					<div class="clearfix"></div>
				</div>
				<h4 class="last-price">TOTAL</h4>
				<span class="total final">￥${sessionScope.cart.total}</span>
				<div class="clearfix"></div>
<!-- 				<a class="order" href="#">Place Order</a> <br> -->
				<input type="hidden" name="action" value="save" /><br>
				<input type="submit" class="order" value="Place Order" /><br>
				<br>
			</div>
			</form>
		</div>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>
