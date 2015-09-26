
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>

<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>~~~~咪走雞~~~~~~</title>

<!-- Bootstrap Core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="css/shop-homepage.css" rel="stylesheet">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

	<jsp:include page="header.jsp"></jsp:include>

	<!-- Page Content -->
	<div class="container">

		<div class="row">

			<div class="col-md-3">
				<div>
					<form class="form-inline" role="form"
						action="/shopweb/searchProductServlet" method="get">
						<div class="form-group">
							<input type="text"	class="form-control" name="keyword">
						</div>

						<input type="hidden" name="pageNum" value="1"> 
						<input
							type="hidden" name="pageSize" value="6">
						<button type="submit" class="btn btn-default">確認</button>

					</form>
					<br>
				</div>
				<div class="list-group">

					<c:forEach items="${requestScope.categoryList}" var="cate">
						<a href="/shopweb/retrievalServlet?action=category&id=${cate.id}"
							class="list-group-item"> ${cate.type}</a>
					</c:forEach>

				</div>
			</div>

			<div class="col-md-9">

				<div class="row carousel-holder">

					<div class="col-md-12">
						<div id="carousel-example-generic" class="carousel slide"
							data-ride="carousel">
							<ol class="carousel-indicators">
								<li data-target="#carousel-example-generic" data-slide-to="0"
									class="active"></li>
								<li data-target="#carousel-example-generic" data-slide-to="1"></li>
								<li data-target="#carousel-example-generic" data-slide-to="2"></li>
							</ol>
							<div class="carousel-inner">
							
								<c:forEach items="${requestScope.advList}" var="advProd" varStatus="num">
									<c:if test="${num.count == 1 }">							
									 	<div class="item active">
									 </c:if>
									 <c:if test="${num.count != 1 }">		
										<div class="item">
									 </c:if>
										<img class="slide-image" src="/shopweb/product_pic/${advProd.pic}"
											alt="${advProd.name }">
									</div>
								</c:forEach>
								
							</div>
							<a class="left carousel-control" href="#carousel-example-generic"
								data-slide="prev"> <span
								class="glyphicon glyphicon-chevron-left"></span>
							</a> <a class="right carousel-control"
								href="#carousel-example-generic" data-slide="next"> <span
								class="glyphicon glyphicon-chevron-right"></span>
							</a>
						</div>
					</div>

				</div>

				<div class="row">

					<c:forEach items="${requestScope.productList}" var="prod">
						<div class="col-sm-4 col-lg-4 col-md-4">
							<div class="thumbnail">
								<img src="/shopweb/product_pic/${prod.pic}" alt="${prod.name}">
								<div class="caption">
									<h4 class="pull-right">${prod.price}</h4>
									<h4>
										<a href="/shopweb/retrievalServlet?action=product&id=${prod.id}">${prod.name}</a>
									</h4>
									<p>${prod.remark}</p>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>

			</div>

		</div>

	</div>
	<!-- /.container -->

	<div class="container">

		<hr>

		<!-- Footer -->
		<footer>
		<div class="row">
			<div class="col-lg-12">
				<p>Copyright &copy; Your Website 2014</p>
			</div>
		</div>
		</footer>

	</div>
	<!-- /.container -->

	<!-- jQuery -->
	<script src="js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>

</body>

</html>
