<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加类别</title>
</head>
<body>
    <form action="/shop/CategoryServlet" method="post">
    	类别名称:<input type="text" name="type" /><br />
    	热点: 是:<input type="radio" name="hot" value="true" />
             否:<input type="radio" name="hot" value="false" /><br />
        <input type="submit" value="提交" />
    </form>
</body>
</html>