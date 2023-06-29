<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Available Products</title>

</head>
<body>
	<div class="page-top"
		style="width: 100%; height: 20px; background-color: #f46b42"></div>
	<h3 align="center">Products List</h3>
	<form>
		<table border="1" align="center">
			<thead>
				<tr>
					<th>Name</th>
					<th>Price</th>
					<th>Image</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${requestScope.availableProducts}" var="products">
					<tr>	
						<td>${products.productName}</td>
						<td>${products.pPrice}</td>
						<td><img src="data:image/png;base64,${products.base64}"
							width="200" height="200" /></td>
						
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</form>

	<button type="button" class="btn">
		<a href="/GroceryDeliveryApplication/backtohome">Goback to Home</a>
	</button>

</body>
</html>