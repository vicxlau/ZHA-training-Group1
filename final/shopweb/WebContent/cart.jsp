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
								var id = parseInt($('#cart-header${num.count}').attr('lang'));
								var num = parseInt($('#quantity${num.count}').val());
								$.post("${shop}/ItemServlet",{id:id,num:num,action:"deleteItem"},function(total){
									$('#total').html(total);
									$('#cart-summary-total').html(total);
									},'text').done(function(){
										$('#cart-header${num.count}').fadeOut('slow', function(c){
											$('#cart-header').remove();
										});	
									});
							});	
							$('#quantity${num.count}').change(function(){
								//validation
								var num = this.value
								if(/^[1-9]\d*$/.test(num)){
									$(this).attr('lang',num);
									var id = $(this).closest(".cart-header").attr('lang');
									$.post("${shop}/ItemServlet",{id:id,num:num,action:"updateItem"},function(total){
										$('#total').html(total);
										$('#cart-summary-total').html(total);
									},'text').done(function(){
										var price = parseFloat($('#price${num.count}').html());
										var num = parseInt($('#quantity${num.count}').val());
										$('#total${num.count}').html(price*num);
	// 									$('#total${num.count}').innerHTML();
// 										$('#total${num.count}').html(parseFloat($('#price${num.count}').html())*parseInt($('#quantity${num.count}').val()));
									});
								}
							});
						});
				</script>
				<div class="cart-header" id="cart-header${num.count}" lang="${item.id}">
					<div class="close1" id="close${num.count}"> </div>
					<div class="cart-sec">
						<div class="cart-item cyc">
							 <img src="${shop}/product_pic/${item.pic}"/>
						</div>
						<div class="cart-item-info">
							<h3><a href="${shop}/retrievalServlet?action=product&id=${item.id}">
							${item.name}</a>
							<span>Model No: 3578</span></h3>
							<h4><span>￥</span><span id="price${num.count}">${item.price}</span></h4>
							<p class="qty">Qty ::</p>
							<input min="1" type="number" id="quantity${num.count}" name="quantity" value="${item.number}" class="form-control input-small">
							<p> Item Total:  <span>￥</span><span id="total${num.count}">${item.number * item.price}</span></p>
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
<!-- 			 <a class="continue" href="#">Continue to basket</a> -->
			 <div class="price-details">
<!-- 				 <h3>Price Details</h3> -->
<!-- 				 <span>Total</span> -->
<!-- 
<%-- 				 <span class="total">￥ <span class="total" id="total">${sessionScope.cart.total}</span></span> --%>
-->
<!-- 				 <span>Discount</span> -->
<!-- 				 <span class="total">---</span> -->
<!-- 				 <span>Delivery Charges</span> -->
<!-- 				 <span class="total">￥0.00</span> -->
<!-- 				 <div class="clearfix"></div>				  -->
			 </div>	
			 <h4 class="last-price">TOTAL</h4>
			 <span class="total final">￥<span id="total">${sessionScope.cart.total}</span></span>
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
