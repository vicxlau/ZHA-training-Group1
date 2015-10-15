<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/public/public.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Product Update</title>

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
                <a class="navbar-brand" href="${shop}/AccountServlet?url=index.jsp">Chris - Shop</a>
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
            	<form action="/shopwebb/ProductServlet" method='post'>
					<p class="lead">Keyword</p><input type="text" name="name" value="${requestScope.keyword}"/>
					<input type="hidden" name="status" value="query">
						  <input type="submit" value="Search" />
				</form><br/>
				 <div class="row">
				 <c:forEach items="${requestScope.productList}" var="product" varStatus="num">
                    <div class="col-sm-4 col-lg-4 col-md-4">
                        <div class="thumbnail">
                            <img src="${shop}/image/${product.pic}" alt="" width="380">
                            <div class="caption">
                                <h5 class="pull-right">$${product.price}  [Discount % ${product.discount}]</h5>
                                <h4><a href="/shopwebb/ProductServlet?status=getById&id=${product.id}">${product.name }</a></h4>
                                <p>${product.remark }</p>
                                <p>Advertisement $ ${product.adv}</p>
								<a href="/shopwebb/ProductServlet?status=delete&id=${product.id}">Delete</a>
                            </div>
                            
                        </div>
                    </div>
                   </c:forEach>
                </div>
                <form action="/shopwebb/ProductServlet" method='post'>
 			        <input type="hidden" name="status" value="query2">	
 			        <input type="hidden" id="pageId" name="pageNum" value="${requestScope.page}">
					<input type="submit" onclick="prev()" value="Previous" /><input type="submit" onclick="next()" value="Next" /> 
				</form>
				
            </div>
			
        </div>

    </div>
    <!-- /.container -->
    
<script>
function next() {
    var num = parseInt(document.getElementById("pageId").value);
    num = num + 1;
    document.getElementById("pageId").value = num;
}
function prev() {
    var num = parseInt(document.getElementById("pageId").value);
    if (num == 1){}
    else{
    	num = num - 1;
    }
    document.getElementById("pageId").value = num;
}

</script>

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
