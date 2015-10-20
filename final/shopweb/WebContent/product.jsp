<!--A Design by W3layouts
Author: W3layout
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<!DOCTYPE HTML>
<html>
<head>

<%@ include file="header.jsp"%>

<title> ${requestScope.product.name} </title>

</head>
<body>

	<%@ include file="top-menu.jsp"%>
    <div id="fb-root"></div>
    <script>
        window.fbAsyncInit = function() {
            FB.init({
                appId : '1629513223989520',
                xfbml : true,
                version : 'v2.5'
            });
        };

        (function(d, s, id) {
            var js, fjs = d.getElementsByTagName(s)[0];
            if (d.getElementById(id)) {
                return;
            }
            js = d.createElement(s);
            js.id = id;
            js.src = "//connect.facebook.net/en_US/sdk.js";
            fjs.parentNode.insertBefore(js, fjs);
        }(document, 'script', 'facebook-jssdk'));
    </script>

	<div class="single-sec">
		<div class="container">
			<ol class="breadcrumb">
				<li><a href="index.html">Home</a></li>
				<li class="active">Products</li>
			</ol>
			<!-- start content -->
<!-- 			<div class="col-md-9 det"> -->	<!-- ori -->
			<div class="col-md-11 det">
				<div class="single_left">
					<div class="grid images_3_of_2">
						<ul id="etalage">
							<li><a href="optionallink.html"> <img
									class="etalage_thumb_image" src="${shop}/product_pic/${requestScope.product.pic}"
									class="img-responsive" /> <img class="etalage_source_image"
									src="${shop}/product_pic/${requestScope.product.pic}" class="img-responsive" title="${requestScope.product.name}" />
							</a></li>
<!-- 							<li><img class="etalage_thumb_image" src="images/ss2.jpg" -->
<!-- 								class="img-responsive" /> <img class="etalage_source_image" -->
<!-- 								src="images/ss2.jpg" class="img-responsive" title="" /></li> -->
<!-- 							<li><img class="etalage_thumb_image" src="images/ss4.jpg" -->
<!-- 								class="img-responsive" /> <img class="etalage_source_image" -->
<!-- 								src="images/ss4.jpg" class="img-responsive" /></li> -->
						</ul>
						<div class="clearfix"></div>
					</div>
				</div>
				<div class="single-right">
					<h3>${requestScope.product.name}</h3>
<!-- 					<div class="id"> -->
<!-- 						<h4>ID: SB2379</h4> -->
<!-- 					</div> -->
					<form action="" class="sky-form">
						<fieldset>
							<section>
								<div class="rating">
									<input type="radio" name="stars-rating" id="stars-rating-5">
									<label for="stars-rating-5"><i class="icon-star"></i></label> <input
										type="radio" name="stars-rating" id="stars-rating-4">
									<label for="stars-rating-4"><i class="icon-star"></i></label> <input
										type="radio" name="stars-rating" id="stars-rating-3">
									<label for="stars-rating-3"><i class="icon-star"></i></label> <input
										type="radio" name="stars-rating" id="stars-rating-2">
									<label for="stars-rating-2"><i class="icon-star"></i></label> <input
										type="radio" name="stars-rating" id="stars-rating-1">
									<label for="stars-rating-1"><i class="icon-star"></i></label>
									<div class="clearfix"></div>
								</div>
							</section>
						</fieldset>
					</form>
					<div class="cost">
						<div class="prdt-cost">
							<form action="${shop}/ItemServlet" method="post">
								<ul>
	<!-- 								<li>MRP: <del>Rs 55000</del></li> -->
									<li>Sellling Price:</li>
									<li class="active">$ ${requestScope.product.price}</li>
									<li>Discount:</li>
									<li class="active">$ ${requestScope.product.discount} % off</li>
									<input type="hidden" name="id" value="${requestScope.product.id}"/>
									<li>
										<input type="text" name="number" value="1" width="2"/>
										<input type="submit" class="btn buy-now" value="BUY NOW"/>
									</li>
	<!-- 								<a href="">BUY NOW</a> -->
								</ul>
							</form>
                            <div class="fb-share-button" 
                                data-href="http://working.java.jspee.cn/shopweb/retrievalServlet?action=product&id=${requestScope.product.id}" 
                                data-layout="button_count">
                            </div>
						</div>
<!-- 						<div class="check"> -->
<!-- 							<p> -->
<!-- 								<span class="glyphicon glyphicon-map-marker" aria-hidden="true"></span>Enter -->
<!-- 								pin code for delivery & availability -->
<!-- 							</p> -->
<!-- 							<form class="navbar-form navbar-left" role="search"> -->
<!-- 								<div class="form-group"> -->
<!-- 									<input type="text" class="form-control" -->
<!-- 										placeholder="Enter Pin code"> -->
<!-- 								</div> -->
<!-- 								<button type="submit" class="btn btn-default">Verify</button> -->
<!-- 							</form> -->
<!-- 						</div> -->
						<div class="clearfix"></div>
					</div>
					<div class="item-list">
						<ul>
							<li>Material: Solid Wood</li>
							<li>Color: Brown</li>
							<li>Style: Solid Wood</li>
							<li>Brand: Corelle</li>
							<li><a href="#">Click here for more details</a></li>
						</ul>
					</div>
					<div class="single-bottom1">
						<h6>Details</h6>
						<p class="prod-desc">Lorem ipsum dolor sit amet, consectetuer
							adipiscing elit, sed diam nonummy nibh euismod tincidunt ut
							laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim
							veniam, ex ea commodo consequat. Duis autem vel eum iriure dolor
							in hendrerit in vulputate velit esse molestie consequat.</p>
					</div>
				</div>
				<div class="clearfix"></div>
	
				<c:if test="${not empty requestScope.prodRecommendList }">
					<div class="new">
						<div class="container">
							<h3>Other customers also look at:</h3>
								<div class="new-products">
								
									<div class="new-items">
										<c:forEach items="${requestScope.prodRecommendList}" var="prod" varStatus="num">
											<c:if test="${num.count%3 == 1}">
												<div class="item${num.count}">
													<a href="${shop}/retrievalServlet?action=product&id=${prod.id}"><img src="${shop}/product_pic/${prod.pic}" alt="${prod.name}" /></a>
													<div class="item-info${num.count}">
														<h4>
															<a href="${shop}/retrievalServlet?action=product&id=${prod.id}">${prod.name}</a>
														</h4>
														<span>$ ${prod.price}</span> <a href="${shop}/retrievalServlet?action=product&id=${prod.id}">Buy Now</a>
													</div>
												</div>
											</c:if>
										</c:forEach>
					
									</div>
									<div class="new-items new_middle">
										<c:forEach items="${requestScope.prodRecommendList}" var="prod" varStatus="num">
											<c:if test="${num.count%3 == 2}">
												<div class="item${num.count}">
													<a href="${shop}/retrievalServlet?action=product&id=${prod.id}">
														<img src="${shop}/product_pic/${prod.pic}" alt="${prod.name}" />
													</a>
													<div class="item-info${num.count}">
														<h4>
															<a href="${shop}/retrievalServlet?action=product&id=${prod.id}">${prod.name}</a>
														</h4>
														<span>$ ${prod.price}</span> <a href="${shop}/retrievalServlet?action=product&id=${prod.id}">Buy Now</a>
													</div>
												</div>
											</c:if>
										</c:forEach>
									</div>
									<div class="new-items new_last">
										<c:forEach items="${requestScope.prodRecommendList}" var="prod" varStatus="num">
											<c:if test="${num.count%3 == 0}">
												<div class="item${num.count}">
													<a href="${shop}/retrievalServlet?action=product&id=${prod.id}"><img src="${shop}/product_pic/${prod.pic}" alt="${prod.name}" /></a>
													<div class="item-info${num.count}">
														<h4>
															<a href="${shop}/retrievalServlet?action=product&id=${prod.id}">${prod.name}</a>
														</h4>
														<span>$ ${prod.price}</span> <a href="${shop}/retrievalServlet?action=product&id=${prod.id}">Buy Now</a>
													</div>
												</div>
											</c:if>
										</c:forEach>
									</div>
								
									<div class="clearfix"></div>
								</div>
						</div>
					</div>
				</c:if>
			</div>
<!-- 			<div class="rsidebar span_1_of_left"> -->
<!-- 				<section class="sky-form"> -->
<!-- 					<div class="product_right"> -->
<!-- 						<h4 class="m_2"> -->
<!-- 							<span class="glyphicon glyphicon-minus" aria-hidden="true"></span>Categories -->
<!-- 						</h4> -->
<!-- 						<div class="tab1"> -->
<!-- 							<ul class="place"> -->
<!-- 								<li class="sort">Furniture</li> -->
<!-- 								<li class="by"><img src="images/do.png" alt=""></li> -->
<!-- 								<div class="clearfix"></div> -->
<!-- 							</ul> -->
<!-- 							<div class="single-bottom"> -->
<!-- 								<a href="#"><p>Sofas</p></a> <a href="#"><p>Fabric Sofas</p></a> -->
<!-- 								<a href="#"><p>Love Seats</p></a> <a href="#"><p>Dinning -->
<!-- 										Sets</p></a> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 						<div class="tab2"> -->
<!-- 							<ul class="place"> -->
<!-- 								<li class="sort">Decor</li> -->
<!-- 								<li class="by"><img src="images/do.png" alt=""></li> -->
<!-- 								<div class="clearfix"></div> -->
<!-- 							</ul> -->
<!-- 							<div class="single-bottom"> -->
<!-- 								<a href="#"><p>Shelves</p></a> <a href="#"><p>Wall Racks</p></a> -->
<!-- 								<a href="#"><p>Curios</p></a> <a href="#"><p>Ash Trays</p></a> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 						<div class="tab3"> -->
<!-- 							<ul class="place"> -->
<!-- 								<li class="sort">Lighting</li> -->
<!-- 								<li class="by"><img src="images/do.png" alt=""></li> -->
<!-- 								<div class="clearfix"></div> -->
<!-- 							</ul> -->
<!-- 							<div class="single-bottom"> -->
<!-- 								<a href="#"><p>Table Lamps</p></a> <a href="#"><p>Tube -->
<!-- 										Lights</p></a> <a href="#"><p>Study Lamps</p></a> <a href="#"><p>Usb -->
<!-- 										Lamps</p></a> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 						<div class="tab4"> -->
<!-- 							<ul class="place"> -->
<!-- 								<li class="sort">Bed & Bath</li> -->
<!-- 								<li class="by"><img src="images/do.png" alt=""></li> -->
<!-- 								<div class="clearfix"></div> -->
<!-- 							</ul> -->
<!-- 							<div class="single-bottom"> -->
<!-- 								<a href="#"><p>Towels</p></a> <a href="#"><p>Bath Roles</p></a> -->
<!-- 								<a href="#"><p>Mirrors</p></a> <a href="#"><p>Soap -->
<!-- 										Stands</p></a> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 						<div class="tab5"> -->
<!-- 							<ul class="place"> -->
<!-- 								<li class="sort">Fabric</li> -->
<!-- 								<li class="by"><img src="images/do.png" alt=""></li> -->
<!-- 								<div class="clearfix"></div> -->
<!-- 							</ul> -->
<!-- 							<div class="single-bottom"> -->
<!-- 								<a href="#"><p>Sofas</p></a> <a href="#"><p>Fabric Sofas</p></a> -->
<!-- 								<a href="#"><p>Beds</p></a> <a href="#"><p>Relax Chairs</p></a> -->
<!-- 							</div> -->
<!-- 						</div> -->

<!-- 						script -->
<!-- 						<script>
// 							$(document).ready(function() {
// 								$(".tab1 .single-bottom").hide();
// 								$(".tab2 .single-bottom").hide();
// 								$(".tab3 .single-bottom").hide();
// 								$(".tab4 .single-bottom").hide();
// 								$(".tab5 .single-bottom").hide();

// 								$(".tab1 ul").click(function() {
// 									$(".tab1 .single-bottom").slideToggle(300);
// 									$(".tab2 .single-bottom").hide();
// 									$(".tab3 .single-bottom").hide();
// 									$(".tab4 .single-bottom").hide();
// 									$(".tab5 .single-bottom").hide();
// 								})
// 								$(".tab2 ul").click(function() {
// 									$(".tab2 .single-bottom").slideToggle(300);
// 									$(".tab1 .single-bottom").hide();
// 									$(".tab3 .single-bottom").hide();
// 									$(".tab4 .single-bottom").hide();
// 									$(".tab5 .single-bottom").hide();
// 								})
// 								$(".tab3 ul").click(function() {
// 									$(".tab3 .single-bottom").slideToggle(300);
// 									$(".tab4 .single-bottom").hide();
// 									$(".tab5 .single-bottom").hide();
// 									$(".tab2 .single-bottom").hide();
// 									$(".tab1 .single-bottom").hide();
// 								})
// 								$(".tab4 ul").click(function() {
// 									$(".tab4 .single-bottom").slideToggle(300);
// 									$(".tab5 .single-bottom").hide();
// 									$(".tab3 .single-bottom").hide();
// 									$(".tab2 .single-bottom").hide();
// 									$(".tab1 .single-bottom").hide();
// 								})
// 								$(".tab5 ul").click(function() {
// 									$(".tab5 .single-bottom").slideToggle(300);
// 									$(".tab4 .single-bottom").hide();
// 									$(".tab3 .single-bottom").hide();
// 									$(".tab2 .single-bottom").hide();
// 									$(".tab1 .single-bottom").hide();
// 								})
// 							});
 						</script> -->
<!-- 						script -->
<!-- 				</section> -->
<!-- 				<section class="sky-form"> -->
<!-- 					<h4> -->
<!-- 						<span class="glyphicon glyphicon-minus" aria-hidden="true"></span>DISCOUNTS -->
<!-- 					</h4> -->
<!-- 					<div class="row row1 scroll-pane"> -->
<!-- 						<div class="col col-4"> -->
<!-- 							<label class="checkbox"><input type="checkbox" -->
<!-- 								name="checkbox" checked=""><i></i>Upto - 10% (20)</label> -->
<!-- 						</div> -->
<!-- 						<div class="col col-4"> -->
<!-- 							<label class="checkbox"><input type="checkbox" -->
<!-- 								name="checkbox"><i></i>40% - 50% (5)</label> <label -->
<!-- 								class="checkbox"><input type="checkbox" name="checkbox"><i></i>30% -->
<!-- 								- 20% (7)</label> <label class="checkbox"><input type="checkbox" -->
<!-- 								name="checkbox"><i></i>10% - 5% (2)</label> <label -->
<!-- 								class="checkbox"><input type="checkbox" name="checkbox"><i></i>Other(50)</label> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 				</section> -->
<!-- 				<section class="sky-form"> -->
<!-- 					<h4> -->
<!-- 						<span class="glyphicon glyphicon-minus" aria-hidden="true"></span>Price -->
<!-- 					</h4> -->
<!-- 					<ul class="dropdown-menu1"> -->
<!-- 						<li><a href=""> -->
<!-- 								<div id="slider-range"></div> <input type="text" id="amount" -->
<!-- 								style="border: 0; font-weight: NORMAL; font-family: 'Arimo', sans-serif;" /> -->
<!-- 						</a></li> -->
<!-- 					</ul> -->
<!-- 				</section> -->
<!-- 				 -->
<!-- 				<script type="text/javascript" src="js/jquery-ui.min.js"></script> -->
<!-- 				<link rel="stylesheet" type="text/css" href="css/jquery-ui.css"> -->
<!-- 				<script type='text/javascript'>
// 					//<![CDATA[ 
// 					$(window)
// 							.load(
// 									function() {
// 										$("#slider-range")
// 												.slider(
// 														{
// 															range : true,
// 															min : 0,
// 															max : 400000,
// 															values : [ 2500,
// 																	350000 ],
// 															slide : function(
// 																	event, ui) {
// 																$("#amount")
// 																		.val(
// 																				"$"
// 																						+ ui.values[0]
// 																						+ " - $"
// 																						+ ui.values[1]);
// 															}
// 														});
// 										$("#amount")
// 												.val(
// 														"$"
// 																+ $(
// 																		"#slider-range")
// 																		.slider(
// 																				"values",
// 																				0)
// 																+ " - $"
// 																+ $(
// 																		"#slider-range")
// 																		.slider(
// 																				"values",
// 																				1));

// 									});//]]>
 				</script> -->
<!-- 				<section class="sky-form"> -->
<!-- 					<h4> -->
<!-- 						<span class="glyphicon glyphicon-minus" aria-hidden="true"></span>Type -->
<!-- 					</h4> -->
<!-- 					<div class="row row1 scroll-pane"> -->
<!-- 						<div class="col col-4"> -->
<!-- 							<label class="checkbox"><input type="checkbox" -->
<!-- 								name="checkbox" checked=""><i></i>Sofa Cum Beds (30)</label> -->
<!-- 						</div> -->
<!-- 						<div class="col col-4"> -->
<!-- 							<label class="checkbox"><input type="checkbox" -->
<!-- 								name="checkbox"><i></i>Dinner Sets (30)</label> <label -->
<!-- 								class="checkbox"><input type="checkbox" name="checkbox"><i></i>3+1+1 -->
<!-- 								Sofa Sets (30)</label> <label class="checkbox"><input -->
<!-- 								type="checkbox" name="checkbox"><i></i>Sofa Chairs (30)</label> -->
<!-- 							<label class="checkbox"><input type="checkbox" -->
<!-- 								name="checkbox"><i></i>2 Seater Sofas (30)</label> <label -->
<!-- 								class="checkbox"><input type="checkbox" name="checkbox"><i></i>Display -->
<!-- 								Units (30)</label> <label class="checkbox"><input -->
<!-- 								type="checkbox" name="checkbox"><i></i>L-shaped Sofas -->
<!-- 								(30)</label> <label class="checkbox"><input type="checkbox" -->
<!-- 								name="checkbox"><i></i>3 Seater Sofas (30)</label> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 				</section> -->
<!-- 				<section class="sky-form"> -->
<!-- 					<h4> -->
<!-- 						<span class="glyphicon glyphicon-minus" aria-hidden="true"></span>Brand -->
<!-- 					</h4> -->
<!-- 					<div class="row row1 scroll-pane"> -->
<!-- 						<div class="col col-4"> -->
<!-- 							<label class="checkbox"><input type="checkbox" -->
<!-- 								name="checkbox" checked=""><i></i>Roadstar</label> -->
<!-- 						</div> -->
<!-- 						<div class="col col-4"> -->
<!-- 							<label class="checkbox"><input type="checkbox" -->
<!-- 								name="checkbox"><i></i>Tornado</label> <label class="checkbox"><input -->
<!-- 								type="checkbox" name="checkbox"><i></i>Kissan</label> <label -->
<!-- 								class="checkbox"><input type="checkbox" name="checkbox"><i></i>Oakley</label> -->
<!-- 							<label class="checkbox"><input type="checkbox" -->
<!-- 								name="checkbox"><i></i>Manga</label> <label class="checkbox"><input -->
<!-- 								type="checkbox" name="checkbox"><i></i>Wega</label> <label -->
<!-- 								class="checkbox"><input type="checkbox" name="checkbox"><i></i>Kings</label> -->
<!-- 							<label class="checkbox"><input type="checkbox" -->
<!-- 								name="checkbox"><i></i>Zumba</label> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 				</section> -->
<!-- 			</div> -->			<!-- rsidebar span_1_of_left -->
		</div>
		<div class="clearfix"></div>
	</div>

	<%@ include file="footer.jsp"%>

</body>
</html>