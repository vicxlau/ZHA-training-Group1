<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>

    <meta charset="utf-8">
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
                <div class="list-group">
                	<p class="lead">Category</p>
                		<a href="categoryAdd.jsp">Add</a><br/>
                		<a href="categoryUpdate.jsp">Update</a>
                </div>
                <div class="list-group">
                	<p class="lead">Product</p>
                		<a href="productAdd.jsp">Add</a><br/>
                		<a href="productUpdate.jsp">Update</a>
                </div>
            </div>
            
           



            <div class="col-md-9">      	
            	 <form action="/shop/CategoryServlet" method='post'>
					<p class="lead">KeyWord</p> <input type="text" name="type" value="${requestScope.keyword}"/><br/>
					<input type="hidden" name="status" value="query">
					  <input type="submit" value="Search" />
				</form><br/>
				 <div class="row">
				 <c:if test="${not empty requestScope.categoryList}">	
					<table border = 2 width = 800px>
					<thead>
						<tr>
							<th>Id</th>
							<th>Name</th>
							<th>Hot</th>
							<th>Update</th>
							<th>Delete</th>
						</tr>
					</thead>
					<tbody> 
						<!--1. for loop set or array, session.getAttribute("key ") = ${sessionScope.categoryList} 
							2. forEach get value from items pass to var
						-->
						<%--<c:forEach items="${sessionScope.categoryList}" var = "category"> --%>
						<c:forEach items="${requestScope.categoryList}" var="category" varStatus="num">
						<tr>
							<!-- using getter/setter, not use private parameter -->
							<td ><!-- ${category.id} --> ${num.count}</td>
							<td>${category.type}</td>
							<c:choose>
							<c:when test="${category.hot}">
								<td>Yes</td>
							</c:when>
							<c:otherwise>
								<td>No</td>
							</c:otherwise>
							</c:choose>
							<td><a href="/shop/CategoryServlet?status=getById&id=${category.id}">Update</a></td>
							<td><a href="/shop/CategoryServlet?status=delete&id=${category.id}">Delete</a></td> 
						</tr>	
						</c:forEach>
						
					</tbody>
					</table>
					</c:if>
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
