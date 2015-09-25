<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
1: 404: source not fount, but tells us tomcat is running<br/>
2: page cannot display, tomcat did not start<br/>
3: 500: sources found and visit, but servers have error. e.g. 100/0 = error.<br/>
4: jsp update no need restart server<br/>
5: MVC: C: Servlet<br/>
6: web.xml -> 配置servlet<br/>
7: Servlet is to get data from front-end and send all to Service(does not have business logic)<br/>
8: JDK version should be consistant. <br/>
9: ExtJS: default Ajax <br/>
we do update and delete<br/>
10:	request: request object, session: one browser, one websit, one session(default 30 mins.) <br/>
11. Display using JSTL Tag
<ul>
	<li><a href="/shop/category/save.jsp">新增類別</li>
	<li><a href="/shop/category/query.jsp">查詢類別</li>
</ul>
</body>
</html>