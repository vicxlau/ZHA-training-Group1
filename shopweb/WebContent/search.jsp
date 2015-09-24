<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<title>Search / Edit Products</title>
	<link rel="stylesheet" href="/shopweb/css/common.css"/>
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"/>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.3.14/angular.min.js"></script>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>

	<h4 class="centralize">Search / Edit / Delete Products</h4>

	
	<div style="width: 500px;" class="centralize">
		<form role="form" action="/shopweb/searchProductServlet" method="get">
			<div class="form-group">
				<input class="form-control" id="keyword" name="keyword" placeholder="Enter keyword" value="${param.keyword}">
			</div>

			<input type="hidden" id="pageSize" name="pageSize" value="10">
			<input type="hidden" id="pageNum" name="pageNum" value="1">

			<div class="centralize">
				<button type="submit" class="btn btn-default" style="margin-bottom: 10px;"><img src="/shopweb/pictures/search-icon.png" width=30 height=30 /></button>
			</div>
		</form>
	</div>
	
	
	<c:if test="${requestScope.searchSuccess}">
		
		<table class="table table-striped table-hover">
			<thead>
				<tr>
					<th></th>
					<th>Name</th>
					<th>Price</th>
					<th>Pic Path</th>
					<th>Remark</th>
				</tr>
			</thead>
			<tbody>

			<c:forEach items="${requestScope.pageResults}" var="product" varStatus="num">

				<tr>
					<td>${num.count + (requestScope.pageNum-1)*requestScope.pageSize}</td>
					<td>${product.name}</td>
					<td>${product.price}</td>
					<td>${product.pic}</td>
					<td>${product.remark}</td>
				</tr>

			</c:forEach>

			</tbody>
		</table>

		<div class="centralize">
			<ul class="pagination">
				
			<c:forEach begin="1" end="${requestScope.pageCount}" varStatus="loop">
				<c:if test="${loop.index == requestScope.pageNum}">
					<li><a style="background-color: yellow;" href="/shopweb/searchProductServlet?keyword=${param.keyword}&pageSize=${requestScope.pageSize}&pageNum=${loop.index}">${loop.index}</a></li>
				</c:if>
				<c:if test="${loop.index != requestScope.pageNum}">
					<li><a href="/shopweb/searchProductServlet?keyword=${param.keyword}&pageSize=${requestScope.pageSize}&pageNum=${loop.index}">${loop.index}</a></li>
				</c:if>
			</c:forEach>

			</ul>
		</div>

	</c:if>


	<p class="centralize home-btn"><a href="/shopweb/home.jsp">Return to home page</a></p>

	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>

<!--  for ajax search

<div ng-app="searchApp">
	<div id="searchResult"></div>
	<div id="paging"></div>
</div>

<script>
	var appname = 'shopweb';

	$.ajax({
		url: 'http://' + window.location.host + '/' + appname + '/searchProductServlet',
		type: 'POST',
		data: {
			'keyword': 'es',
			'pageSize': 3,
			'pageNum': 2
		},
		datatype: 'json',
	})
	.done(function(jsonResponse) {
		var response = JSON.parse(jsonResponse);
		
		if (response.success) {
			
			var searchResult = response.dataObj;
			
			console.log('Total records found: ' + searchResult.totalResultCount);
			console.log('Records per page: ' + searchResult.pageSize);
			console.log('Page Count: ' + searchResult.pageCount);
			console.log('Current page: ' + searchResult.pageNum);
			
			var pageResults = searchResult.pageResults;
			
			for (var i=0; i<pageResults.length; i++) {
				
				var t_product = pageResults[i];
				
				console.log('===================');
				console.log('ID: ' + t_product.id);
				console.log('Name: ' + t_product.name);
				console.log('Price: ' + t_product.price);
				console.log('Picture path: ' + t_product.pic);
				console.log('Remark: ' + t_product.remark);
			}
			
		} else {
			alert(response.error_msg);	
		}
	})
	.fail(function(jqXHR, textStatus, errorThrown) {
		alert('fail.');
		console.log(textStatus);
		console.log(errorThrown);
	})
	.always(function() {
		alert('Ajax completed.');
	});
</script>

 -->