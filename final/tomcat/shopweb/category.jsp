<!--A Design by W3layouts
Author: W3layout
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="header.jsp" %>
<title>${requestScope.category.type}</title>
</head>
<body>
<%@ include file="top-menu.jsp" %>
<div class="product-model">	 
	 <div class="container">
		<ol class="breadcrumb">
			<li><a href="${shop}/home">Home</a></li>
			<li class="active">${requestScope.category.type}</li>
		</ol>
		<h2>${requestScope.category.type}</h2>			
		 <div class="col-md-9 product-model-sec">
<!-- 		 <div class="col-md-11 product-model-sec"> -->
			<c:forEach items="${requestScope.cateResults}" var="product" varStatus="num">
					 <div class="product-grid love-grid">
						<a href="${shop}/retrievalServlet?action=product&id=${product.id}">
							<div class="more-product"><span> </span></div>						
							<div class="product-img b-link-stripe b-animate-go  thickbox">
									<img src="${shop}/product_pic/${product.pic}" class="img-responsive" alt="${product.name}"/>
								<div class="b-wrapper">
								<h4 class="b-animate b-from-left  b-delay03">							
								<button class="btns"><span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>Quick View</button>
								</h4>
								</div>
							</div>
						</a>						
						<div class="product-info simpleCart_shelfItem">
							<div class="product-info-cust prt_name">
								<h4>${product.name}</h4>
<!-- 								<p>ID: SR4598</p> -->
								<span class="item_price">$ ${product.price}</span>
								<form action="${shop}/ItemServlet" method="post">								
									<input type="text" class="item_quantity" name="number" value="1" />
									<input type="submit" class="item_add items" value="ADD">
									<input type="hidden" name="id" value="${product.id}" />
								</form>
							</div>													
							<div class="clearfix"> </div>
						</div>
					</div>	
			</c:forEach>
		</div>
		<div class="rsidebar span_1_of_left">
<!-- 			 <section  class="sky-form"> -->
<!-- 				 <div class="product_right"> -->
<!-- 					 <h4 class="m_2"><span class="glyphicon glyphicon-minus" aria-hidden="true"></span>Categories</h4> -->
<%-- 					 <c:forEach items="${requestScope.categoryList}" var="category" varStatus="num"> --%>
<%-- 						 <div class="tab${num.count}"> --%>
<!-- 							 <ul class="place">								 -->
<%-- 								 <li class="sort">${category.type}</li> --%>
<!-- 								 <div class="clearfix"> </div> -->
<!-- 							  </ul> -->
<!-- 					     </div> -->
<%-- 					 </c:forEach> --%>
<!-- 					  <div class="tab2"> -->
<!-- 						 <ul class="place">								 -->
<!-- 							 <li class="sort">Decor</li> -->
<!-- 								<div class="clearfix"> </div> -->
<!-- 						  </ul> -->
<!-- 				      </div>	 -->
<!-- 			      </div>		  -->
<!-- 			 </section> -->
				<section  class="sky-form">
					 <h4><span class="glyphicon glyphicon-minus" aria-hidden="true"></span>DISCOUNTS</h4>
					 <div class="row row1 scroll-pane">
						 <div class="col col-4">
								<label class="checkbox"><input type="checkbox" name="checkbox" checked=""><i></i>Upto - 10% (20)</label>
						 </div>
						 <div class="col col-4">
								<label class="checkbox"><input type="checkbox" name="checkbox"><i></i>40% - 50% (5)</label>
								<label class="checkbox"><input type="checkbox" name="checkbox"><i></i>30% - 20% (7)</label>
								<label class="checkbox"><input type="checkbox" name="checkbox"><i></i>10% - 5% (2)</label>
								<label class="checkbox"><input type="checkbox" name="checkbox"><i></i>Other(50)</label>
						 </div>
					 </div>
				 </section> 	
		 </div>				 

		<!-- Pagination -->
		<div class="row text-center">
			<div class="col-lg-12">
				<ul class="pagination">
					<li><a
						href="${shop}/retrievalServlet?action=category&id=${requestScope.category.id}&pageNum=1">&laquo;</a>
					</li>
					<c:forEach begin="1" end="${requestScope.pageCount}"
						varStatus="loop">
						<c:if test="${loop.index == requestScope.pageNum}">
							<li class="active"><a
								href="${shop}/retrievalServlet?action=category&id=${requestScope.category.id}&pageNum=${loop.index}">${loop.index}</a>
							</li>
						</c:if>
						<c:if test="${loop.index != requestScope.pageNum}">
							<li><a
								href="${shop}/retrievalServlet?action=category&id=${requestScope.category.id}&pageNum=${loop.index}">${loop.index}</a>
							</li>
						</c:if>
					</c:forEach>
					<li><a
						href="${shop}/retrievalServlet?action=category&id=${requestScope.category.id}&pageNum=${requestScope.pageCount}">&raquo;</a>
					</li>
				</ul>
				
<!-- 				<ul class="pagination"> -->
<!-- 					<li><a href="#">&laquo;</a></li> -->
<!-- 					<li class="active"><a href="#">1</a></li> -->
<!-- 					<li><a href="#">2</a></li> -->
<!-- 					<li><a href="#">3</a></li> -->
<!-- 					<li><a href="#">4</a></li> -->
<!-- 					<li><a href="#">5</a></li> -->
<!-- 					<li><a href="#">&raquo;</a></li> -->
<!-- 				</ul> -->
			</div>
		</div>
		<!-- Pagination -->
		
		
     </div>
</div>	
<%@ include file="footer.jsp" %>
</body>
</html>