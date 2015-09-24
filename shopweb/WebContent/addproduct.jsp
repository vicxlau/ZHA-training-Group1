<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<title>Add Product</title>
	<link rel="stylesheet" href="/shopweb/css/common.css"/>
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"/>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.3.14/angular.min.js"></script>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	
	<h4 class="centralize">Add Product</h4>

	<div style="width: 500px;" class="centralize">
		<form role="form" action="/shopweb/addProductServlet" method="post">
			<div class="form-group">
				<label>Name:</label>
				<input class="form-control" id="pro_name" name="pro_name" placeholder="Enter product name"
					<c:if test="${ !requestScope.addProductSuccess}">value="${param.pro_name}"</c:if>
				>
			</div>

			<div class="form-group">
				<label>Price:</label>
				<input class="form-control" id="pro_price" name="pro_price" placeholder="Enter product price"
					<c:if test="${ !requestScope.addProductSuccess}">value="${param.pro_price}"</c:if>
				>
			</div>
			
			<div class="form-group">
				<label>Picture Path:</label>
				<input class="form-control" id="pro_pic" name="pro_pic" placeholder="Enter picture path"
					<c:if test="${ !requestScope.addProductSuccess}">value="${param.pro_pic}"</c:if>
				>
			</div>

			<div class="form-group">
				<label>Remark:</label>
				<input class="form-control" id="pro_remark" name="pro_remark" placeholder="Enter remark"
					<c:if test="${ !requestScope.addProductSuccess}">value="${param.pro_remark}"</c:if>
				>
			</div>

			<div class="centralize">
				<button type="submit" class="btn btn-default" style="margin-right: 10px;">Add</button>
				<button type="reset" class="btn btn-default">Reset</button>
			</div>
		</form>
	</div>


	<c:if test="${requestScope.addProductSuccess}">
		<p id="message" class="centralize smart-message">Product is added. You may continue to add next product.</p>
	</c:if>

	<c:if test="${ !requestScope.addProductSuccess && requestScope.showErrorMsg}">
		<p id="message" class="centralize smart-message">An error occurred. ${requestScope.errorMsg} <br/>Fail to add product.</p>
	</c:if>
	
	<script>$('#message').delay(4000).fadeOut(1000);</script>


	<p class="centralize home-btn"><a href="/shopweb/home.jsp">Return to home page</a></p>

	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>