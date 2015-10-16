<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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

</head>

<body>

	<jsp:include page="header.jsp"></jsp:include>
    <!-- Page Content -->
    <div class="container">
        <div class="row">		
        	<%@ include file="leftMenu.jsp" %>	
            <div class="col-md-9">      	
            	<form action="${shop}/ProductServlet" method='post'>
					<p class="lead">Keyword</p><input type="text" name="name" value="${requestScope.keyword}"/>
					<input type="hidden" name="status" value="query">
						  <input type="submit" value="Search" />
				</form><br/>
				 <div class="row">
				 <c:forEach items="${requestScope.productList}" var="product" varStatus="num">
                    <div class="col-sm-4 col-lg-4 col-md-4">
                        <div class="thumbnail">
                            <img src="${product.pic }" alt="">
                            <div class="caption">
                                <h4 class="pull-right">${product.price }</h4>
                                <h4><a href="#">${product.name }</a></h4>
                                <p>${product.remark }</p>
                                <a href="${shop}/ProductServlet?status=getById&id=${product.id}">Update</a><br/>
								<a href="${shop}/ProductServlet?status=delete&id=${product.id}">Delete</a>
                            </div>
                            
                        </div>
                    </div>
                   </c:forEach>
                </div>
                <form action="${shop}/ProductServlet" method='post'>
 			        <input type="hidden" name="status" value="query2">	
 			        <input type="hidden" id="pageId" name="pageNum" value="${requestScope.page}">
					<input type="submit" onclick="prev()" value="Previous" /><input type="submit" onclick="next()" value="Next" /> 
				</form>
				
            </div>
			
        </div>

    </div>
    <!-- /.container -->
    
    <%@ include file="footer.jsp" %>	
    
<script>
function next() {
    var num = parseInt(document.getElementById("pageId").value);
    num = num + 1;
    document.getElementById("pageId").value = num;
}
function prev() {
    var num = parseInt(document.getElementById("pageId").value);
    if (num == 1){}
    else{
    	num = num - 1;
    }
    document.getElementById("pageId").value = num;
}

</script>


</body>

</html>
