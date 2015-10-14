<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>

	<%@ include file="header.jsp" %>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

	<script>
	 	var xhr = new XMLHttpRequest();
	    xhr.onreadystatechange = function() {
	        if (xhr.readyState == 4) {
	            var data = xhr.responseText;
	        }
	    }
	    xhr.open('POST', 'ProductServlet', true);
	    xhr.send(null);
	</script>

</head>

<body>

	<jsp:include page="header.jsp"></jsp:include>
    <!-- Page Content -->
    <div class="container">
        <div class="row">		
        	<%@ include file="leftMenu.jsp" %>	
            <div class="col-md-9">				 
               <p class="lead">Add</p>   
            <form action="${shop}/ProductServlet" method='post'>
				<h3>Product Name</h3><input type="text" name="name"/><br/>
				<h3>Price $</h3><input type="number" name="price" /> <br/>
				<h3>Picture</h3><input type="file" name="pic" size="50" /><br/>
				<h3>Remark</h3><input type="text" name="remark"/><br/>
				<input type="hidden" name="status" value="save">
				<input type="submit" value="Submit" />
			</form>                                                                     
            </div>

        </div>

    </div>
    <!-- /.container -->

    <%@ include file="footer.jsp" %>	
</body>

</html>
