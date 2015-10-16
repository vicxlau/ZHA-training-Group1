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
    <!-- Page Content -->
    <div class="container">
        <div class="row">				
        	<%@ include file="leftMenu.jsp" %>	
            <div class="col-md-9">				 
               <p class="lead">Add</p>   
            <form action="${shop}/CategoryServlet" method='post'>
				<h4>Type</h4><input type="text" name="type"/>
				<h4>Hot</h4>
					<p>Yes<input type="radio" name="hot" value="true"/>No<input type="radio" name="hot" value="false"/></p><br/>
				<input type="hidden" name="action" value="add">
					  <input type="submit" value="Submit" />
		
			</form>                                                                    
            </div>

        </div>

    </div>
    <!-- /.container -->

    <%@ include file="footer.jsp" %>	

</body>

</html>
