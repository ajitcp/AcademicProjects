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
	<form action="manageproducts" method="GET">
		<table border="1" align="center">
			<thead>
				<tr>
					<th>Select</th>
					<th>Product Id</th>
					<th>Name</th>
					<th>Price</th>
					<th>Image</th>
					<th>Delete Product</th>
					<th>Update Product</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${requestScope.availableProducts}" var="products">
					<tr>
						<td><input type="checkbox" name="select"
							value="${products.pID}" /></td>
						<td>${products.pID}</td>
						<td>${products.productName}</td>
						<td>${products.pPrice}</td>
						<td><img src="data:image/png;base64,${products.base64}"
							width="200" height="200" /></td>
						<td style="text-align: center"><input  type="submit" name="actionValue"
							value="DELETE" /></td>
						<td style="text-align: center"><input  type="submit" name="actionValue"
							value="UPDATE"/>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<input type="hidden" name="actionValue" value="delete"/>
	</form>

	<button type="button" class="btn">
		<a href="/GroceryDeliveryApplication/backtohome">Back to HomePage</a>
	</button>

</body>
</html>