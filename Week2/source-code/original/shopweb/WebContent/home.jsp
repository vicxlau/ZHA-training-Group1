<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Shop Web Management System</title>
	<link rel="stylesheet" href="/shopweb/css/common.css"/>
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"/>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.3.14/angular.min.js"></script>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>

	<h4 class="centralize">Welcome!</h4>

	<ul class="centralize">
		<li><a href="/shopweb/addproduct.jsp">Add Products</a></li>
		<li><a href="/shopweb/search.jsp">Search / Edit / Delete Products</a></li>
	</ul>

	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>