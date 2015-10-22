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
    
    <style type="text/css">
    	form label{
    		color: red;
    		background-image: url("image/error.png");
    		padding-left: 20px;
    		background-repeat: no-repeat;
    	}
    	
    </style>

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
	<!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
  	<script type="text/javascript" src="js/jquery.validate.js"></script>
<!-- 	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script> -->
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
	
	$(function(){
	     $.validator.addMethod('photo',function(value, element){   
	          return /^\S*\.(?:jpg|png|jpeg)$/.test(value);
	     },'只接受 .jpg, .png, .jpeg');
	     
	   	 $.validator.addMethod('percent',function(value, element){   
        	return /^(?:[0-9]|[1-9][0-9])$/.test(value);
   	 	 },'只接受  0 - 99');
	     
	      $("#addProduct").validate({
	  		   debug:false,   // 无论是否验证成功都不会真正提交到目标地址 
	  		   /* 用来配置存储错误消息的标签 */
	  		   errorElement:'label',
	  		   rules: {
	  			  name:{
	  		   	  	required:true
	  		   	  },
	  		      price:{
	  		    	required:true
	  		   	  },
	  		      pic:{
	  		   	  	photo:[1,2]
	  		   	  },
	  		      remark:{
	  		    	required:true
	  		   	  },
	  		      adv:{
		    		required:true
	 		   	  },
	 		      discount:{
		  		    percent:[1,2]
		  		  }
	  		   },
	  		   messages:{
	  				name:{
	  		   	  		required:'產品名不能为空'
//	  		   	  		rangelength:$.validator.format('用户名必须是{0}~{1}之间')
	  		   	  	},
	  		   		price:{
	  		   			required:'價錢不能为空'
	  		   	  	},
// 	  		   		pic:{
// 	  		   	  		required:'圖片必選'
// 	  		   	  	},
	  		   		remark:{
	  		   			required:'備註不能为空'
	  		   	  	},
	 	  		   	adv:{
		  		    	required:'廣告費不能为空'
		  		   	}
// 	  		   	  	,
// 		  		    discount:{
// 		 	  		    required:'折扣不能为空'
// 		 	  		}
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
                <a class="navbar-brand" href="${shop}/StatisticServlet?url=index.jsp">Shop</a>
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



            <div class="col-md-4">
               <p class="lead">Add</p>   
            <form id="addProduct" action="/shopwebb/ProductServlet" method='post'>
				<h3>Product Name</h3><input id="addProductForm_productName" type="text" name="name"/><br/>
				<h3>Price $</h3><input type="number" name="price" /> <br/>
				<h3>Picture</h3><input type="file" name="pic" size="50" /><br/>
				<h3>Remark</h3><input type="text" name="remark"/><br/>
				<h3>Advertisement $</h3><input type="number" name="adv"/><br/>
				<h3>Discount %</h3><input type="number" name="discount"/><br/>
<!--<h3>Category</h3><input type="text" name="category" value=""/><br/> -->
				<h3>Category</h3><select id="cat_id" name="category"></select>
				<input type="hidden" name="status" value="save">
				<input type="submit" value="Submit" />
			</form>                                                                     
            </div>

			<div class="col-md-4" style="display:none;" id="priceRecom_loading">
				<h4>Price Reference from Tao Bao</h4>
				<img src="/shopwebb/image/loading.gif" width="200px" height="200px"/>
			</div>

			<div class="col-md-4" style="display:none;" id="priceRecom">
				<h4>Price Reference from Tao Bao</h4>
				
				<p>Max: ¥<span id="priceRecom_max"></span></p>
				<p>Min: ¥<span id="priceRecom_min"></span></p>
				<p>Average: ¥<span id="priceRecom_avg"></span></p>
				<p>Median: ¥<span id="priceRecom_median"></span></p>
				<hr/>
				<div style="overflow:scroll;  max-height: 600px;" id="priceRecom_items">
				</div>
			</div>
			
			<script>
			$(function() {
				$('#addProductForm_productName').change(function() {
					
					var url = "/shopwebb/recommendPrice?keyword=" + encodeURIComponent($(this).val());
					$('#priceRecom').hide();
					$('#priceRecom_loading').show();
					
					$.get(url, function(data) {
						if (data != "") {
							var priceRecom = JSON.parse(data);

							$("#priceRecom_max").text(priceRecom.maxPrice);
							$("#priceRecom_min").text(priceRecom.minPrice);
							$("#priceRecom_avg").text(priceRecom.averagePrice);
							$("#priceRecom_median").text(priceRecom.medianPrice);

							$('#priceRecom_items').empty();
							
							var itemList = priceRecom.productRefList;
							for (item of itemList) {
								var html= '<div>'
										+ '<img src="' + item.image + '" width="150px" height="150px" style="margin-left: 80px;"/>'
										+ '<p>' + item.name + '</p>'
										+ '<p>¥' + item.price + '</p>'
										+ '<p>' + item.location + ' | ' + item.shop + '</p><hr/>';
								
								$('#priceRecom_items').append(html);
							}
							
							$('#priceRecom_loading').hide();
							$('#priceRecom').show();
						}
					});
				});
			});
			</script>

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

<!--     jQuery -->
<!--     <script src="js/jquery.js"></script> -->

<!--     Bootstrap Core JavaScript -->
<!--     <script src="js/bootstrap.min.js"></script> -->

</body>

</html>
