<!--A Design by W3layouts
Author: W3layout
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="header.jsp" %>
<title>搜尋 "${requestScope.keyword}" 結果</title>
<script>
$(document).ready(function() {
	var targetPageNum;
	var clickAction;
	var clickSortEvent = function(e){
		var keyword="${requestScope.keyword}";
		console.log("keyword: "+keyword+", pageNum: "+targetPageNum);
		$.ajax({
			url : '/shopweb/retrievalServlet',

        	data : {action:clickAction,keyword:keyword,pageNum:targetPageNum},
        	type:"post",
			dataType:"json",
        	success : function(response) {
            	console.info(response);
        		var json = response.pageResults;
            	$('div.product-model-sec').html("");
            	$('ul.pagination').html("");
            	$('<li><a href="#" id="'+clickAction+'-1">&laquo;</a></li>').appendTo($("ul.pagination"));
            	for(var i = 1; i <= response.pageCount; i++) {
            		if (response.pageNum == i) {
            			$('<li class="active"><a href="#"'+' id="'+clickAction+'-'+i+'">'+i+'</a></li>').appendTo($("ul.pagination"));
                	} else {    	                		
                		$('<li><a href="#" id="'+clickAction+'-'+i+'">'+i+'</a></li>').appendTo($("ul.pagination"));
                	}
            	}
            	$('<li><a href="#" id="'+clickAction+'-'+response.pageCount+'">&raquo;</a></li>').appendTo($("ul.pagination"));
            	
            	for(var i = 0; i < json.length; i++) {
            	    var obj =json[i];
            	    var target = $("<div class='product-grid love-grid'></div>").appendTo($('div.product-model-sec'));
            		$(target).append("<a href='/shopweb/retrievalServlet?action=product&id="+obj.id+"'>"+					
							"<div class='product-img b-link-stripe b-animate-go  thickbox'>"+
							"<img src='${shop}/product_pic/"+obj.pic+"' class='img-responsive' alt='"+obj.name+"'/>"+
								"<div class='b-wrapper'>"+
									"<h4 class='b-animate b-from-left  b-delay03'>"+							
										"<button class='btns'><span class='glyphicon glyphicon-eye-open' aria-hidden='true'></span>Quick View</button>"+
									"</h4>"+
								"</div>"+
						"</div>"+
            		"</a>");
            		$(target).append("<div class='product-info simpleCart_shelfItem'>"+
  						"<div class='product-info-cust prt_name'>"+
								"<h4>"+obj.name+"</h4>"+
	   						"<div><span class='item_price'>$ "+obj.price.toFixed(1)+"</span></div>"+
	   						"<span class='item_discount' style='color:red'> "+obj.discount+" % off</span>"+
	   						"<form action='${shop}/ItemServlet' method='post'>"+								
	   							"<input type='text' class='item_quantity' name='number' value='1' />"+
	   							"<input type='submit' class='item_add items' value='ADD'>"+
	   							"<input type='hidden' name='id' value='"+obj.id+"' />"+
	   						"</form>"+
	   					"</div>"+													
	   					"<div class='clearfix'> </div>"+
	   				"</div>");
            	}
        	},
        	error: function(e) {
        		console.error(e);
        	}
		});
	}
	$(document).on("click", "input[name=sort_type]", function(e){
		var status = $(e.target).val();
		//console.log("status: "+status);
		if (status == "volumn") {
			clickAction = "categoryByVolumn";
		} else if (status == "viewTime") {
			clickAction = "categoryByVisit"
		}
		targetPageNum = "1";
		clickSortEvent(e);
	});
	$(document).on("click", "ul.pagination li a", function(e){
		clickAction = e.target.id.split("-")[0];
		targetPageNum = e.target.id.split("-")[1];
		clickAction = (clickAction == "categoryByVolumn" || clickAction == "categoryByVisit") ? clickAction : "categoryByVolumn";
		//console.info("targetPageNum: "+targetPageNum);
	    clickSortEvent(e);
	});
});
</script>
</head>
<body>
<%@ include file="top-menu.jsp" %>
<div class="product-model">	 
	 <div class="container">
		<ol class="breadcrumb">
			<li><a href="${shop}/home">Home</a></li>
			<li class="active">搜尋 "${requestScope.keyword}" 結果</li>
		</ol>
<!--<%-- 		<h2>搜尋 "${requestScope.keyword}" 結果</h2>			 --%>-->
<!-- 		 <div class="col-md-9 product-model-sec"> -->
		<div class="rsidebar span_1_of_left">
			<section  class="sky-form">
				<div class="row row1 scroll-pane">
					<input type="radio" name="sort_type" value="volumn"/>Sort by volumn
					<input type="radio" name="sort_type" value="viewTime"/>Sort by view
				</div>
			</section>
		</div>

		 <div class="col-md-11 product-model-sec">
			 <c:if test="${not empty requestScope.pageResults}">
				<c:forEach items="${requestScope.pageResults}" var="product" varStatus="num">
						 <div class="product-grid love-grid">
							<a href="${shop}/retrievalServlet?action=product&id=${product.id}">
								<div class="more-product"><span> </span></div>						
								<div class="product-img b-link-stripe b-animate-go thickbox">
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
			</c:if>
		</div>
		
<!-- 		<div class="rsidebar span_1_of_left"> -->
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
<!-- 		 </div>				  -->

		<!-- Pagination -->
		<div class="row text-center">
			<div class="col-lg-12">
				<ul class="pagination">
					<li><a
						href="${shop}/searchProductServlet?keyword=${requestScope.keyword}&pageNum=1">&laquo;</a>
					</li>
					<c:forEach begin="1" end="${requestScope.pageCount}"
						varStatus="loop">
						<c:if test="${loop.index == requestScope.pageNum}">
							<li class="active"><a
								href="${shop}/searchProductServlet?keyword=${requestScope.keyword}&pageNum=${loop.index}">${loop.index}</a>
							</li>
						</c:if>
						<c:if test="${loop.index != requestScope.pageNum}">
							<li><a
								href="${shop}/searchProductServlet?keyword=${requestScope.keyword}&pageNum=${loop.index}">${loop.index}</a>
							</li>
						</c:if>
					</c:forEach>
					<li><a
						href="${shop}/searchProductServlet?keyword=${requestScope.keyword}&pageNum=${requestScope.pageCount}">&raquo;</a>
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