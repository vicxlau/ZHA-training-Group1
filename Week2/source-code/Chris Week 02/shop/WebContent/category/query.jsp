<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Query</title>
</head>
<body>
	<form action="/shop/CategoryServlet" method='post'>
			KeyWord: <input type="text" name="type" value="${requestScope.keyword}"/><br/>
		<input type="hidden" name="status" value="query">
			  <input type="submit" value="Search" />
	</form>
	
	<c:if test="${not empty requestScope.categoryList}">	
	<table border = 1 width = 500px>
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
			<td>${category.hot}</td>
			<td><a href="/shop/CategoryServlet?status=getById&id=${category.id}">Update</a></td>
			<td><a href="/shop/CategoryServlet?status=delete&id=${category.id}">Delete</a></td> 
		</tr>	
		</c:forEach>
		
	</tbody>
	</table>
	</c:if>
	
</body>
</html>