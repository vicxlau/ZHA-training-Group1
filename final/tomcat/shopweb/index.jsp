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
<title>Furnyish Store a Ecommerce Category Flat Bootstarp
	Responsive Website Template | Home :: w3layouts</title>
</head>
<body>
	<%@ include file="top-menu.jsp"%>
	<!-- //adv items  -->
	<div class="content">
		<div class="container">
			<div class="slider">
				<ul class="rslides" id="slider1">
					<c:forEach items="${requestScope.advList}" var="advProd" varStatus="num">
						<li><img src="${shop}/product_pic/${advProd.pic}" alt="${advProd.name}"></li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>
	<!-- //adv items end -->
	<!---->
	<!-- <div class="bottom_content"> -->
	<!-- 	 <div class="container"> -->
	<!-- 		 <div class="sofas"> -->
	<!-- 			 <div class="col-md-6 sofa-grid"> -->
	<!-- 				 <img src="images/t1.jpg" alt=""/> -->
	<!-- 				 <h3>IMPORTED DINING SETS</h3> -->
	<!-- 				 <h4><a href="products.html">SPECIAL ACCENTS OFFER</a></h4> -->
	<!-- 			 </div> -->
	<!-- 			 <div class="col-md-6 sofa-grid sofs"> -->
	<!-- 				 <img src="images/t2.jpg" alt=""/> -->
	<!-- 				 <h3>SPECIAL DESIGN SOFAS</h3> -->
	<!-- 				 <h4><a href="products.html">FABFURNISHING MELA</a></h4> -->
	<!-- 			 </div> -->
	<!-- 			 <div class="clearfix"></div> -->
	<!-- 		 </div> -->
	<!-- 	 </div> -->
	<!-- </div> -->
	<!---->
	<div class="new">
		<div class="container">
			<h3>specially designed for Furnyish</h3>
			<div class="new-products">
				<div class="new-items">
					<c:forEach items="${requestScope.productList}" var="prod" varStatus="num">
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
<!-- 					<div class="item1"> -->
<!-- 						<a href="products.html"><img src="images/s1.jpg" alt="" /></a> -->
<!-- 						<div class="item-info"> -->
<!-- 							<h4> -->
<!-- 								<a href="products.html">Brown Furny Seater</a> -->
<!-- 							</h4> -->
<!-- 							<span>ID: SR5421</span> <a href="single.html">Buy Now</a> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 					<div class="item4"> -->
<!-- 						<a href="products.html"><img src="images/s4.jpg" alt="" /></a> -->
<!-- 						<div class="item-info4"> -->
<!-- 							<h4> -->
<!-- 								<a href="products.html">Dream Furniture Bed</a> -->
<!-- 							</h4> -->
<!-- 							<span>ID: SR5421</span> <a href="single.html">Buy Now</a> -->
<!-- 						</div> -->
<!-- 					</div> -->
				</div>
				<div class="new-items new_middle">
					<c:forEach items="${requestScope.productList}" var="prod" varStatus="num">
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
					<c:forEach items="${requestScope.productList}" var="prod" varStatus="num">
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
	<!---->
	<div class="top-sellers">
		<div class="container">
			<h3>TOP - SELLERS</h3>
			<div class="seller-grids">
				<div class="col-md-3 seller-grid">
					<a href="products.html"><img src="images/ts2.jpg" alt="" /></a>
					<h4>
						<a href="products.html">Carnival Doublecot Bed</a>
					</h4>
					<span>ID: DB4790</span>
					<p>Rs. 25000/-</p>
				</div>
				<div class="col-md-3 seller-grid">
					<a href="products.html"><img src="images/ts11.jpg" alt="" /></a>
					<h4>
						<a href="products.html">Home Bar Furniture</a>
					</h4>
					<span>ID: BR4822</span>
					<p>Rs. 5000/-</p>
				</div>
				<div class="col-md-3 seller-grid">
					<a href="products.html"><img src="images/ts3.jpg" alt="" /></a>
					<h4>
						<a href="products.html">L-shaped Fabric Sofa set</a>
					</h4>
					<span>ID: LF8560</span>
					<p>Rs. 45000/-</p>
				</div>
				<div class="col-md-3 seller-grid">
					<a href="products.html"><img src="images/ts4.jpg" alt="" /></a>
					<h4>
						<a href="products.html">Ritz Glass Dinning Table </a>
					</h4>
					<span>ID: DB4790</span>
					<p>Rs. 18000/-</p>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	<!---->
	<div class="recommendation">
		<div class="container">
			<div class="recmnd-head">
				<h3>RECOMMENDATIONS FOR YOU</h3>
			</div>
			<div class="bikes-grids">
				<ul id="flexiselDemo1">
					<li><a href="products.html"><img src="images/ts1.jpg"
							alt="" /></a>
						<h4>
							<a href="products.html">King Size Bed</a>
						</h4>
						<p>ID: KS3989</p></li>
					<li><a href="products.html"><img src="images/r2.jpg"
							alt="" /></a>
						<h4>
							<a href="products.html">Elite Diwan Seater</a>
						</h4>
						<p>ID: KS3989</p></li>
					<li><a href="products.html"><img src="images/r3.jpg"
							alt="" /></a>
						<h4>
							<a href="products.html">Dior Corner Sofa</a>
						</h4>
						<p>ID: KS3989</p></li>
					<li><a href="products.html"><img src="images/r4.jpg"
							alt="" /></a>
						<h4>
							<a href="products.html">Alia Modular Sofa</a>
						</h4>
						<p>ID: KS3989</p></li>
					<li><a href="products.html"><img src="images/r5.jpg"
							alt="" /></a>
						<h4>
							<a href="products.html">King Size Bed</a>
						</h4>
						<p>ID: KS3989</p></li>
				</ul>
				<script type="text/javascript">
					$(window).load(function() {
						$("#flexiselDemo1").flexisel({
							visibleItems : 5,
							animationSpeed : 1000,
							autoPlay : true,
							autoPlaySpeed : 3000,
							pauseOnHover : true,
							enableResponsiveBreakpoints : true,
							responsiveBreakpoints : {
								portrait : {
									changePoint : 480,
									visibleItems : 1
								},
								landscape : {
									changePoint : 640,
									visibleItems : 2
								},
								tablet : {
									changePoint : 768,
									visibleItems : 3
								}
							}
						});
					});
				</script>
				<script type="text/javascript" src="js/jquery.flexisel.js"></script>
			</div>
		</div>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>