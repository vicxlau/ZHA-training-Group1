<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增類別</title>
</head>
<body>
	<form action="/shop/CategoryServlet" method='post'>
		類別名稱: <input type="text" name="type"/><br/>
		熱點:是:<input type="radio" name="hot" value="true"/>
			  否:<input type="radio" name="hot" value="false"/><br/>
		<input type="hidden" name="status" value="save">
			  <input type="submit" value="提交" />

	</form>
</body>
</html>