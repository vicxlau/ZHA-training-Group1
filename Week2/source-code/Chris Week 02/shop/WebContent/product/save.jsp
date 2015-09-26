<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增產品</title>
</head>
<body>
	<form action="/shop/ProductServlet" method='post'>
		產品名稱: <input type="text" name="name"/><br/>
		價錢: <input type="number" name="price" step="10.00" min="0.99" max="999999" /> <br/>
		圖片: <input type="file" name="pic" size="50" /><br/>
		備註: <input type="text" name="remark"/><br/>
		<input type="hidden" name="status" value="save">
			  <input type="submit" value="提交" />

	</form>
</body>
</html>