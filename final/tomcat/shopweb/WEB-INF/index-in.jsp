<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- HTML Codes by Quackit.com -->
<title>
</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style type="text/css">
body {background-color:FFFFFF;background-image:url(http://images2.houstonpress.com/imager/u/original/6392230/chicken_200.jpg);background-repeat:no-repeat;background-position:center center;background-attachment:fixed;}
h1{text-align:center;font-family:Cursive;letter-spacing:5px;color:EFEDF8;background-color:1C5777;}
p {text-align:center;font-family:Cursive;font-size:18px;font-style:normal;font-weight:normal;letter-spacing:3px;color:000000;background-color:FFFFFF;}
.center {text-align: center; width: 40%; margin: auto;}
</style>
</head>
<body>
<h1>歡迎  <span style="color:red;">${sessionScope.customer.getUser().getName()} </span>  來到   咪走雞</h1>
<p>仲等緊咩?仲唔快D入去執平野</p>
<div class="center">
<a href="/shopweb/retrievalServlet?action=home">快速進入</a></div>

</body>
</html>
