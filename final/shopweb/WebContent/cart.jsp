<!--A Design by W3layouts
Author: W3layout
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>Shopping Cart</title>
<%@ include file="header.jsp" %>  
</head>
<body>
<%@ include file="top-menu.jsp" %>
<div class="cart_main">
	 <div class="container">
			 <ol class="breadcrumb">
		  <li><a href="${shop}/home">Home</a></li>
		  <li class="active">Cart</li>
		 </ol>			
		 <div class="cart-items">
			<h2>My Shopping Cart </h2>
			<c:forEach items="${sessionScope.cart.itemList}" var="item" varStatus="num"> 
				<script>$(document).ready(function(c) {
					$('#close${num.count}').on('click', function(c){
						$('#cart-header${num.count}').fadeOut('slow', function(c){
							$('#cart-header').remove();
						});
						});	  
					});
				</script>
				<div class="cart-header" id="cart-header${num.count}">
					<div class="close1" id="close${num.count}"> </div>
					<div class="cart-sec">
						<div class="cart-item cyc">
							 <img src="${shop}/product_pic/${item.pic}"/>
						</div>
						<div class="cart-item-info">
							<h3><a href="${shop}/retrievalServlet?action=product&id=${item.id}">
							${item.name}</a>
							<span>Model No: 3578</span></h3>
							<h4><span>￥</span>${item.price}</h4>
							<p class="qty">Qty ::</p>
							<input min="1" type="number" id="quantity" name="quantity" value="${item.number}" class="form-control input-small">
						</div>
						<div class="clearfix"></div>
<!-- 						<div class="delivery"> -->
<!-- 							<p>Service Charges:: Rs.50.00</p>							  -->
<!-- 						</div>						 -->
					</div>
				 </div>
			</c:forEach>		
		 </div>
		 
		 <div class="cart-total">
			 <a class="continue" href="#">Continue to basket</a>
			 <div class="price-details">
				 <h3>Price Details</h3>
				 <span>Total</span>
				 <span class="total">￥${sessionScope.cart.total}</span>
				 <span>Discount</span>
				 <span class="total">---</span>
				 <span>Delivery Charges</span>
				 <span class="total">￥0.00</span>
				 <div class="clearfix"></div>				 
			 </div>	
			 <h4 class="last-price">TOTAL</h4>
			 <span class="total final">￥${sessionScope.cart.total}</span>
			 <div class="clearfix"></div>
			 <a class="order" href="${shop}/customer/order.jsp">Place Order</a>
			 <div class="total-item">
				 <h3>OPTIONS</h3>
				 <h4>COUPONS</h4>
				 <a class="cpns" href="#">Apply Coupons</a>
				 <p><a href="#">Log In</a> to use accounts - linked coupons</p>
			 </div>
			</div>
	 </div>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>
