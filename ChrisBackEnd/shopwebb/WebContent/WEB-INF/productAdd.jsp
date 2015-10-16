<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/public/public.jspf" %>
<!DOCTYPE html>
<html lang="en">

<head>
	
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Shop</title>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/shop-homepage.css" rel="stylesheet">

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
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script type="text/javascript">
	$(document).ready(function() {
		$.post("${shop}/CategoryServlet",{status:'getAll'}, function(data){
			console.log(data);
			var temp = data.split("|");
			var numbers = temp[0];
			var names = temp[1];
			numbers = JSON.parse(numbers);
			names = names.split(",");
			
			for (var i=0;i<numbers.length;i++){
			   $('<option/>').val(numbers[i]).html(names[i]).appendTo('#cat_id');
			}
		});
	});
	</script>

</head>

<body>

    <!-- Navigation -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="${shop}/AccountServlet?url=index.jsp">Shop</a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li>
<!--                         <a href="#">Add Product</a> -->
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>

    <!-- Page Content -->
    <div class="container">
        <div class="row">		
            <div class="col-md-3">
                <div class="list-group">
                	<p class="lead">Category</p>
                		<a href="${shop}/AccountServlet?url=categoryAdd.jsp">Add</a><br/>
                		<a href="${shop}/AccountServlet?url=categoryUpdate.jsp">Update</a>
                </div>
                <div class="list-group">
                	<p class="lead">Product</p>
                		<a href="${shop}/AccountServlet?url=productAdd.jsp">Add</a><br/>
                		<a href="${shop}/AccountServlet?url=productUpdate.jsp">Update</a>
                </div>
                <div class="list-group">
                	<p class="lead">System</p>
                		<a href="${shop}/AccountServlet?url=refreshHomePage.jsp">HomePage refresh</a><br/>
                </div>
            </div>



            <div class="col-md-9">				 
               <p class="lead">Add</p>   
            <form action="/shopwebb/ProductServlet" method='post'>
				<h3>Product Name</h3><input type="text" name="name"/><br/>
				<h3>Price $</h3><input type="number" name="price" /> <br/>
				<h3>Picture</h3><input type="file" name="pic" size="50" /><br/>
				<h3>Remark</h3><input type="text" name="remark"/><br/>
				<h3>Advertisement $</h3><input type="text" name="adv"/><br/>
				<h3>Discount %</h3><input type="text" name="discount"/><br/>
<!-- 				<h3>Category</h3><input type="text" name="category" value=""/><br/> -->
				<h3>Category</h3><select id="cat_id" name="category"></select>
				<input type="hidden" name="status" value="save">
				<input type="submit" value="Submit" />
			</form>                                                                     
            </div>

        </div>

    </div>
    <!-- /.container -->

    <div class="container">

        <hr>

        <!-- Footer -->
        <footer>
            <div class="row">
                <div class="col-lg-12">
                    <p>Copyright &copy; Your Website 2014</p>
                </div>
            </div>
        </footer>

    </div>
    <!-- /.container -->

    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

</body>

</html>
