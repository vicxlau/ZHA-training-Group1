<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/public/public.jspf" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Shop</title>

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

	<script>
	 	var xhr = new XMLHttpRequest();
	    xhr.onreadystatechange = function() {
	        if (xhr.readyState == 4) {
	            var data = xhr.responseText;
	        }
	    }
	    xhr.open('POST', 'ProductServlet', true);
	    xhr.send(null);
	</script>

</head>

<body>

    <!-- Navigation -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="${shop}/AccountServlet?url=index.jsp">Shop</a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li>
<!--                         <a href="#">Add Product</a> -->
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>

    <!-- Page Content -->
    <div class="container">
        <div class="row">		
            <div class="col-md-3">
                <div class="list-group">
                	<p class="lead">Category</p>
                		<a href="${shop}/AccountServlet?url=categoryAdd.jsp">Add</a><br/>
                		<a href="${shop}/AccountServlet?url=categoryUpdate.jsp">Update</a>
                </div>
                <div class="list-group">
                	<p class="lead">Product</p>
                		<a href="${shop}/AccountServlet?url=productAdd.jsp">Add</a><br/>
                		<a href="${shop}/AccountServlet?url=productUpdate.jsp">Update</a>
                </div>
                
                <div class="list-group">
                	<p class="lead">System</p>
                		<a href="${shop}/AccountServlet?url=refreshHomePage.jsp">HomePage refresh</a><br/>
                </div>
            </div>



            <div class="col-md-9">
<!--             	<p class="lead">Hot Items</p> -->
<!-- 				 <div class="row"> -->
<%-- 					<c:forEach items="${applicationScope.bigList}" var="productList"> --%>
<!-- 					<div class="products_list products_slider clear"> -->
<%-- 						<h2 class="sub_title">· ${productList[0].category.type}</h2> --%>
<!-- 						<ul id="first-carousel" class="first-and-second-carousel jcarousel-skin-tango"> -->
<!-- 							有多少商品则循环多少次 -->
<%-- 							<c:forEach items="${productList}" var="product"> --%>
<%-- 								<img src="${shop}/image/${product.pic}" width="400" /> --%>
<!-- 									 <span class="list-hover"></span> -->
<!-- 								<div class="product_info"> -->
<%-- 									<p>${product.remark}</p> --%>
<!-- 								</div> -->
<!-- 								<div class="price_info"> -->

<%-- 										<span class="pr_price">￥${product.price}</span><br/> --%>

<!-- 									<button> -->
<!-- 										<span class="pr_add">放入购物车</span> -->
<!-- 									</button> -->
<!-- 								</div> -->
<%-- 							</c:forEach> --%>
<!-- 						</ul> -->
<!-- 					</div> -->
<!-- 					产品列表结束  -->
<%-- 					</c:forEach> --%>
<!--                 </div> -->
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
