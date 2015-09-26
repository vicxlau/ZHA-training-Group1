<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <!-- jsp page need to import JSTL tag -->
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix= "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Category ${requestScope.catName}</title>
    

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/3-col-portfolio.css" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>

 <!-- Navigation -->
    
	<jsp:include page="header.jsp"></jsp:include>

    <!-- Page Content -->
    <div class="container">
    	<div>
            <form class="form-inline" role="form" action = "/shopweb/searchProductServlet" method="get">
					  <div class="form-group">
					    <label for="type">搜尋: </label>
					    <input type="text" class="form-control" name="keyword"  }>
					  </div>
		
					  <input type = "hidden" name= "pageNum" value = "1">
					  <input type = "hidden" name= "pageSize" value = "6">
					  <button type="submit" class="btn btn-default" >確認</button>
					  
				</form>
            </div>		

        <!-- Page Header -->
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">
                   " ${requestScope.catName}"
                    	 <small>類別</small>
                </h1>
            </div>
        </div>
        <!-- /.row -->

        <!-- Projects Row -->

        <div class="row">

        <c:forEach items= "${requestScope.cateResults}" var="product" varStatus="num">
        <div class="col-md-4 portfolio-item">
                <a href="#">
                    <img class="img-responsive" src="/shopweb/product_pic/${product.pic}" alt="">
                </a>
                <h3>
                    <a href="/shopweb/retrievalServlet?action=product&id=${product.id}">${product.name}</a>
                </h3>
                <p>${product.remark }</p>
            </div>
        </c:forEach>
            

        </div>
        <!-- /.row -->

                <hr>

        <!-- Pagination -->
        <div class="row text-center">
            <div class="col-lg-12">
                <ul class="pagination">
                    <li>
                        <a href="#">&laquo;</a>
                    </li>
                    <li class="active">
                        <a href="#">1</a>
                    </li>
                    <li>
                        <a href="#">2</a>
                    </li>
                    <li>
                        <a href="#">3</a>
                    </li>
                    <li>
                        <a href="#">4</a>
                    </li>
                    <li>
                        <a href="#">5</a>
                    </li>
                    <li>
                        <a href="#">&raquo;</a>
                    </li>
                </ul>
            </div>
        </div>
        <!-- /.row -->

        <hr>

        <!-- Footer -->
        <footer>
            <div class="row">
                <div class="col-lg-12">
                    <p>Copyright &copy; Your Website 2014</p>
                </div>
            </div>
            <!-- /.row -->
        </footer>

    </div>
    <!-- /.container -->

    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>


</body>
</html>