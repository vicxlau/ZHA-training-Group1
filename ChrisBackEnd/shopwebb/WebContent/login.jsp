<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- 只能引入工程内部的资源,自动添加工程名 -->
<%@ include file="/public/public.jspf" %>
<title>后台管理登录页面</title>
</head>
<body>
    <!-- 模拟登录功能,登录成功才能进入页面的后台
        应该有一个account表, 里面有用户与密码,符合条件才能登录成功
        此处简单实现：如果是admin,admin则登录成功
     -->
    <form action="${shop}/StatisticServlet" method="post">
    	name:<input type="text" name="name" /><br />
    	 pw	:<input type="password" name="pass" /><br />
    	${requestScope.error}
    	<input type="submit" value="login" />
    </form>
    
    
</body>
</html>