<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Shop Web</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
</head>
<body>

<script>
	var appname = 'shopweb';

	$.ajax({
		url: 'http://' + window.location.host + '/' + appname + '/searchProduct',
		type: 'POST',
		data: {
			'keyword': 'es',
			'pageSize': 3,
			'pageNum': 2
		},
		datatype: 'json',
	})
	.done(function(jsonResponse) {
		var response = JSON.parse(jsonResponse);
		
		if (response.success) {
			
			var searchResult = response.dataObj;
			
			console.log('Total records found: ' + searchResult.totalResultCount);
			console.log('Records per page: ' + searchResult.pageSize);
			console.log('Page Count: ' + searchResult.pageCount);
			console.log('Current page: ' + searchResult.pageNum);
			
			var pageResults = searchResult.pageResults;
			
			for (var i=0; i<pageResults.length; i++) {
				
				var t_product = pageResults[i];
				
				console.log('===================');
				console.log('ID: ' + t_product.id);
				console.log('Name: ' + t_product.name);
				console.log('Price: ' + t_product.price);
				console.log('Picture path: ' + t_product.pic);
				console.log('Remark: ' + t_product.remark);
			}
			
		} else {
			alert(response.error_msg);	
		}
	})
	.fail(function(jqXHR, textStatus, errorThrown) {
		alert('fail.');
		console.log(textStatus);
		console.log(errorThrown);
	})
	.always(function() {
		alert('Ajax completed.');
	});
</script>

</body>
</html>