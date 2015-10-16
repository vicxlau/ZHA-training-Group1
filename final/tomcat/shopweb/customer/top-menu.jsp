<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- color header -->
<div class="top_bg">
	<div class="container">
		<div class="header_top-sec">
			<div class="top_right">
				<ul>
					<li><a href="#">help</a></li>|
					<li><a href="contact.html">Contact</a></li>|
					<li><a href="${shop}/login.jsp">Track Order</a></li>
				</ul>
			</div>
			<div class="top_left">
				<ul>
					<li class="top_link">Email:<a href="mailto@example.com">info(at)Mobilya.com</a></li>|
					<li class="top_link"><a href="${shop}/login.jsp">My Account</a></li>|					
				</ul>
				<div class="social">
					<ul>
						<li><a href="#"><i class="facebook"></i></a></li>
						<li><a href="#"><i class="twitter"></i></a></li>
						<li><a href="#"><i class="dribble"></i></a></li>	
						<li><a href="#"><i class="google"></i></a></li>										
					</ul>
				</div>
			</div>
				<div class="clearfix"> </div>
		</div>
	</div>
</div>
<!-- color header end -->

<div class="header_top">
	 <div class="container">
		 <div class="logo">
		 	<a href="${shop}/home"><img src="${shop}/images/logo.png" alt=""/></a>			 
		 </div>
		 <div class="header_right">	
			 <div class="login">
				 <a href="${shop}/login.jsp">LOGIN</a>
			 </div>
			 <div class="cart box_1">
				<a href="${shop}/cart.jsp">
					<h3> <span>￥ 
							<c:choose>
							    <c:when test="${empty sessionScope.cart.total}">
							        0.00
							    </c:when>
							    <c:otherwise>
							        ${sessionScope.cart.total}
							    </c:otherwise>
							</c:choose>

					</span> (<span id="" class="">${fn:length(sessionScope.cart.itemList)}</span> items)<img src="${shop}/images/bag.png" alt=""></h3>
<%-- 					<h3> <span class="simpleCart_total">${sessionScope.cart.total}</span> (<span id="simpleCart_quantity" class="simpleCart_quantity"></span> items)<img src="${shop}/images/bag.png" alt=""></h3> --%>
				</a>	
<!-- 				<p><a href="javascript:;" class="simpleCart_empty">Empty cart</a></p> -->
				<div class="clearfix"> </div>
			 </div>				 
		 </div>
		  <div class="clearfix"></div>	
	 </div>
</div>
<!--cart-->
	 
<!-- item menu ---->
<div class="mega_nav">
	 <div class="container">
		 <div class="menu_sec">
		 <!-- start header menu -->
		 <ul class="megamenu skyblue">
			 <li class="active grid"><a class="color1" href="${shop}/home">Home</a></li>
			 
			 <c:forEach items="${requestScope.categoryList}" var="cate" varStatus="num">
				 <li><a class="color${num.count+1}" href="${shop}/retrievalServlet?action=category&id=${cate.id}&pageNum=1">${cate.type}</a></li>
			 </c:forEach>
			 
			 
<%-- 			 <li class="grid"><a class="color2" href="${shop}/#">furniture</a> --%>
<!-- 				<div class="megapanel"> -->
<!-- 					<div class="row"> -->
<!-- 						<div class="col1"> -->
<!-- 							<div class="h_nav"> -->
<%-- 								<a href="${shop}/products.html"><h4>Seating</h4></a> --%>
<!-- 								<h4>Sofas</h4> -->
<!-- 								<ul> -->
<%-- 									<li><a href="${shop}/products.html">All Sofas</a></li> --%>
<%-- 									<li><a href="${shop}/products.html">Fabric Sofas</a></li> --%>
<%-- 									<li><a href="${shop}/products.html">Leather Sofas</a></li> --%>
<%-- 									<li><a href="${shop}/products.html">L-shaped Sofas</a></li> --%>
<%-- 									<li><a href="${shop}/products.html">Love Seats</a></li>									 --%>
<%-- 									<li><a href="${shop}/products.html">Wooden Sofas</a></li> --%>
<!-- 								</ul>	 -->
<!-- 							</div>							 -->
<!-- 							<div class="h_nav"> -->
<%-- 								<a href="${shop}/products.html"><h4>Seating</h4></a> --%>
<!-- 							</div>							 -->
<!-- 						</div> -->
<!-- 						<div class="col1"> -->
<!-- 							<div class="h_nav"> -->
<%-- 								<a href="${shop}/products.html"><h4>Seating</h4></a> --%>
<!-- 							</div>												 -->
<!-- 						</div> -->
<!-- 						<div class="col1"> -->
<!-- 							<div class="h_nav"> -->
<%-- 								<a href="${shop}/products.html"><h4>Seating</h4></a> --%>
<!-- 							</div>						 -->
<!-- 						</div> -->
<!-- 						<div class="col1"> -->
<!-- 							<div class="h_nav"> -->
<!-- 								<a href="${shop}/products.html"><h4>Seating</h4></a> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 					<div class="row"> -->
<!-- 						<div class="col2"></div> -->
<!-- 						<div class="col1"></div> -->
<!-- 						<div class="col1"></div> -->
<!-- 						<div class="col1"></div> -->
<!-- 						<div class="col1"></div> -->
<!-- 					</div> -->
<!--     				</div> -->
<!-- 				</li> -->
			
<!-- 				//default template -->
<!-- 				<li><a class="color8" href="#">kids</a> -->
<!-- 				<div class="megapanel"> -->
<!-- 					<div class="row"> -->
<!-- 						<div class="col1"> -->
<!-- 							<div class="h_nav"> -->
<!-- 								<h4>Sofas</h4> -->
<!-- 								<ul> -->
<!-- 									<li><a href="products.html">All Sofas</a></li> -->
<!-- 									<li><a href="products.html">Fabric Sofas</a></li> -->
<!-- 									<li><a href="products.html">Leather Sofas</a></li> -->
<!-- 									<li><a href="products.html">L-shaped Sofas</a></li> -->
<!-- 									<li><a href="products.html">Love Seats</a></li>									 -->
<!-- 									<li><a href="products.html">Wooden Sofas</a></li> -->
<!-- 								</ul>	 -->
<!-- 							</div>							 -->
<!-- 						</div> -->
<!-- 						<div class="col1"> -->
<!-- 							<div class="h_nav"> -->
<!-- 								<h4>Tables</h4> -->
<!-- 								<ul> -->
<!-- 									<li><a href="products.html">Coffee Tables</a></li> -->
<!-- 									<li><a href="products.html">Dinning Tables</a></li> -->
<!-- 									<li><a href="products.html">Study Tables</a></li> -->
<!-- 									<li><a href="products.html">Wooden Tables</a></li> -->
<!-- 									<li><a href="products.html">Study Tables</a></li> -->
<!-- 									<li><a href="products.html">Bar & Unit Stools</a></li> -->
<!-- 								</ul>	 -->
<!-- 							</div>							 -->
<!-- 						</div> -->
<!-- 						<div class="col1"> -->
<!-- 							<div class="h_nav"> -->
<!-- 								<h4>Beds</h4> -->
<!-- 								<ul> -->
<!-- 									<li><a href="products.html">Single Bed</a></li> -->
<!-- 									<li><a href="products.html">Poster Bed</a></li> -->
<!-- 									<li><a href="products.html">Sofa Cum Bed</a></li> -->
<!-- 									<li><a href="products.html">Bunk Bed</a></li> -->
<!-- 									<li><a href="products.html">King Size Bed</a></li> -->
<!-- 									<li><a href="products.html">Metal Bed</a></li> -->
<!-- 								</ul>	 -->
<!-- 							</div>												 -->
<!-- 						</div> -->
<!-- 						<div class="col1"> -->
<!-- 							<div class="h_nav"> -->
<!-- 								<h4>Seating</h4> -->
<!-- 								<ul> -->
<!-- 									<li><a href="products.html">Wing Chair</a></li> -->
<!-- 									<li><a href="products.html">Accent Chair</a></li> -->
<!-- 									<li><a href="products.html">Arm Chair</a></li> -->
<!-- 									<li><a href="products.html">Mettal Chair</a></li> -->
<!-- 									<li><a href="products.html">Folding Chair</a></li> -->
<!-- 									<li><a href="products.html">Bean Bags</a></li> -->
<!-- 								</ul>	 -->
<!-- 							</div>						 -->
<!-- 						</div> -->
<!-- 						<div class="col1"> -->
<!-- 							<div class="h_nav"> -->
<!-- 								<h4>Solid Woods</h4> -->
<!-- 								<ul> -->
<!-- 									<li><a href="products.html">Side Tables</a></li> -->
<!-- 									<li><a href="products.html">T.v Units</a></li> -->
<!-- 									<li><a href="products.html">Dressing Tables</a></li> -->
<!-- 									<li><a href="products.html">Wardrobes</a></li> -->
<!-- 									<li><a href="products.html">Shoe Racks</a></li> -->
<!-- 									<li><a href="products.html">Console Tables</a></li> -->
<!-- 								</ul>	 -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 					<div class="row"> -->
<!-- 						<div class="col2"></div> -->
<!-- 						<div class="col1"></div> -->
<!-- 						<div class="col1"></div> -->
<!-- 						<div class="col1"></div> -->
<!-- 						<div class="col1"></div> -->
<!-- 					</div> -->
<!--     				</div> -->
<!-- 				</li>				 -->
			   </ul> 
			   <div class="search">
				 <form action="${shop}/searchProductServlet" method="get">
					<input type="text" value="" placeholder="Search...">
					<input type="hidden" name="pageNum" value="1"> 
<!-- 					<input type="hidden" name="pageSize" value="6"> -->
					<input type="submit" value="">
					</form>
			 </div>
			 <div class="clearfix"></div>
		 </div>
	  </div>
</div>
<!-- item menu end ---->