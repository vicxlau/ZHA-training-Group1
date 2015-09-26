<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Update</title>
</head>
<body>
	<!-- data form servlet transfer, and it is in the request -->
	<form action="/shop/CategoryServlet" method='post'>
		Category Name: <input type="text" name="type" value="${requestScope.categoryUpdate.type}"/><br/>
		Hot: 
		<!-- seems if else -->
		<c:choose>
			<c:when test="${requestScope.category.hot}">
				Yes:<input type="radio" name="hot" value="true" checked="checked"/>
				No:<input type="radio" name="hot" value="false"/><br/>
			</c:when>
			<c:otherwise>
				Yes:<input type="radio" name="hot" value="true" />
				No:<input type="radio" name="hot" value="false" checked="checked"/><br/>
			</c:otherwise>
		</c:choose>
		<input type="hidden" name="status" value="update">
		<input type="hidden" name="id" value="${requestScope.categoryUpdate.id}">
			  <input type="submit" value="Update" />
	</form>
</body>
</html>