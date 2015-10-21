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
<script>


$(document).ready(function(c) {

// 	$("#undo_btn").sticky({topSpacing:0});
	$('#undo-btn').on('click', function(c){
		$(".notifications-top-center").removeClass('animated bounce');
// 		$(".notifications-top-center").addClass('animated ' + $('#effects').val());
// 		$('.close').click(function(){$(this).parent().fadeOut(200);});
		// send undo request to server 
		$.post("${shop}/ItemServlet",{action:"undo"},function(total){
			$('#total').html(total);
			$('#cart-summary-total').html(total);
		},'text').done(function(){
			var undoPrio = $('.cart-item-info:hidden').length;
				$('.cart-header:hidden[undoPrio="'+undoPrio +'"]').fadeIn('slow', function(c){
					$(this).attr('undoPrio',0);

					//update undo text
					var undoItemNum = $('.cart-item-info:hidden').length;
					$('#undo-item-num').html(undoItemNum);
					if(undoItemNum ==0)	
						$('#undo-btn').fadeOut('slow',function(cc){});

					//update summary item number
					var currentSummaryNum = parseInt($('#cart-summary-num').html());
					var undoNum = parseInt($(this).find('[name="quantity"]').val());
					$('#cart-summary-num').html(currentSummaryNum + undoNum);
// 					$('#cart-summary-num').html(currentSummaryNum+parseInt($(this).children('[name="quantity"]').val()));
					
// 					var div_count = $('.cart-item-info:visible').length;
// 					var item_num = 0;
// 					for(var i=div_count;i>=1;i--){
// 						item_num += parseInt($('#quantity'+i).val());
// 					}
// 					$('#cart-summary-num').html(item_num);

		// 			$('.cart-header:hidden').show();
				});
		});
	});

	var cartCheckStatus = true;
	$('#select-all-checkbox').on('click',function(c){
		$('.cart-checkbox').prop('checked',cartCheckStatus);
		cartCheckStatus = !cartCheckStatus ; 
	});
});


function isValidCartSubmission()
{
	var checked_item = $('.cart-checkbox:checked').length;
	if (checked_item<=0){
		$(".notifications-top-center").addClass('animated bounce');
	    $("#warning-msg").html('No item is selected');
	    $("#warning").show();
	    $('#warning').fadeOut(2000);
  		return false;
	}else{ 
		return true;
	}
}

</script>
<!-- <div id="undo_btn" class=".notifications-top-center" style="display:none;" > Undo </div> -->
<!-- undo button -->
<div id="undo-btn" class="notifications-top-center" style="display:none;">
	<span class="iconb" data-icon="&#xe20e;"></span> 
		undo item deletion (<span id="undo-item-num">0</span>left ) 
	<div id="undo-btn_close" class="notifications-top-center-close" ><span class="iconb" data-icon="&#xe20e;"></span></div>
</div>

<div id="warning" class="notifications-top-center" style="display:none;">
	<span class="iconb" data-icon="&#xe20e;"></span> 
		<span id="warning-msg"></span>
	<div id="undo-btn_close" class="notifications-top-center-close" ><span class="iconb" data-icon="&#xe20e;"></span></div>
</div>

	<form action="${shop}/ItemServlet" method="post" onsubmit="return isValidCartSubmission();">
	 <div class="container">
			 <ol class="breadcrumb">
		  <li><a href="${shop}/home">Home</a></li>
		  <li class="active">Cart</li>
		  <li><a id="select-all-checkbox">select all</a></li>
		 </ol>			
		 <div class="cart-items">
<!-- 			<h2>My Shopping Cart </h2> -->
			<c:forEach items="${sessionScope.cart.itemList}" var="item" varStatus="num"> 
				<script>								
				$(document).ready(function(c) {
					
				function updatetCartSumItem() {
// 					var div_count = $('.cart-item-info').length;
// 						var item_num = 0;
// 						for(var i=div_count;i>=1;i--){
// 								item_num += $('#quantity'+i).val();
// 						}
// 						$('#cart-summary-num').html(item_num);
						$('#cart-summary-num').html(999);
				}
							$('#close${num.count}').on('click', function(c){
								var id = parseInt($('#cart-header${num.count}').attr('lang'));
								var num = parseInt($('#quantity${num.count}').val());
								$.post("${shop}/ItemServlet",{id:id,num:num,action:"deleteItem"},function(total){
									$('#total').html(total);
									$('#cart-summary-total').html(total);
									},'text').done(function(){
										$('#cart-header${num.count}').fadeOut('slow', function(c){
											$('#cart-header${num.count}').attr('undoPrio',$('.cart-item-info:hidden').length);
// 											$('#cart-header').remove();
// 											$('#cart-header${num.count}').remove();
// 											$('#cart-header${num.count}').html('');
											$('#cart-header${num.count} .cart-checkbox').prop('checked',false);
											// update cart summary item number
					 						$('#cart-summary-num').html(parseInt($('#cart-summary-num').html()) - parseInt($(this).find('[name="quantity"]').val()));
// 					 						var div_count = $('.cart-item-info:visible').length;
// 					 						var item_num = 0;
// 					 						for(var i=div_count;i>=1;i--){
// 													item_num += parseInt($('#quantity'+i).val());
// 					 						}
// 					 						$('#cart-summary-num').html(item_num);
// 					 						$('#undo_btn').show();
											
											// update text in undo button
											var undoItemNum = $('.cart-item-info:hidden').length;
											$('#undo-item-num').html(undoItemNum);
					

// 											var top = '<div id="undo-btn" class="notifications-top-center"><span class="iconb" data-icon="&#xe20e;"></span> Oups something went wrong !<div id="undo-btn_close" class="notifications-top-center-close" ><span class="iconb" data-icon="&#xe20e;"></span></div></div>';
// 					 					    $("#undo-btn").remove();
// 					 						$(".container").append(top);
												
											// show undo button
					 						$(".notifications-top-center").addClass('animated bounce');
					 					    $("#undo-btn").show();
					 						$('.notifications-top-center-close').click(function(){$(this).parent().fadeOut(200);});
// 					 						refresh_close();




// // 	$("#undo_btn").sticky({topSpacing:0});
// 	$('#undo_btn').on('click', function(c){
// 		$(".notifications-top-center").addClass('animated ' + $('#effects').val());
// 		$('#undo_btn').fadeOut('slow',function(cc){});
// 		$('.cart-header:hidden').fadeIn('slow', function(c){
// // 			$('.cart-header:hidden').show();
// 		});
// 	});
					 						
										});	
// 										updateCartSumItem();
// 										$("#cart-summary-num").updatetCartSumItem();
// 					 					$('#cart-summary-num').html(parseInt($('.cart-item-info').length));


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

// 					 					$('#cart-summary-num').html(parseInt($('.cart-item-info').length));

					 					var div_count = $('.cart-item-info').length;
					 						var item_num = 0;
					 						for(var i=div_count;i>=1;i--){
													item_num += parseInt($('#quantity'+i).val());
					 						}
					 						$('#cart-summary-num').html(item_num);

// 										updateCartSumItem();
// 										$("#cart-summary-num").updatetCartSumItem();
	// 									$('#total${num.count}').innerHTML();
// 										$('#total${num.count}').html(parseFloat($('#price${num.count}').html())*parseInt($('#quantity${num.count}').val()));
									});
								}else{
									$(this).val($(this).attr('lang'));
								}
							});
						});
				</script>
				<div class="cart-header" id="cart-header${num.count}" lang="${item.id}" undoPrio="">
					<div class="close1" id="close${num.count}"> </div>
					<div class="cart-checkbox-div" lang="${item.id}">
						<input type="checkbox" class="cart-checkbox" name="cart-checkbox[]" value="${item.id}" />
					</div>
					<div class="cart-sec">
						<div class="cart-item cyc">
							 <img src="${shop}/product_pic/${item.pic}"/>
						</div>
						<div class="cart-item-info">
								<h3><a href="${shop}/retrievalServlet?action=product&id=${item.id}">
								${item.name}</a>
								<span>Model No: 3578</span></h3>
								<h4><span>￥</span><span id="price${num.count}">${item.price}</span></h4>
								<div>
									<div style="position:relative;float:left;">
										<p class="qty">Qty ::</p>
										<input min="1" type="number" id="quantity${num.count}" name="quantity" value="${item.number}" class="form-control input-small" lang="${item.number}">
									</div>
									<div style="position:relative;float:right;">
										<p> Item Total:  <span>￥</span><span id="total${num.count}">${item.number * item.price}</span></p>
									</div>
								</div>
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
<!-- 			 Original work -->
<!--<%-- 			 <a class="order" href="${shop}/customer/order.jsp">Place Order</a> --%>-->
			 <input type="hidden" name="action" value="selectiveSubmit" />
			 <input type="submit" class="order" value="Place Order" />
			 <div class="total-item">
				 <h3>OPTIONS</h3>
				 <h4>COUPONS</h4>
				 <a class="cpns" href="#">Apply Coupons</a>
				 <p><a href="#">Log In</a> to use accounts - linked coupons</p>
			 </div>
			</div>
	 </div>
	</form>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>
