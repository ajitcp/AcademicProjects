<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Products under Store</title>

</head>
<body>
	<div class="page-top"
		style="width: 100%; height: 20px; background-color: #f46b42"></div>
	<h3 align="center">Products in Store ${requestScope.store.sName}</h3>
	<h3 align="center">Available Products</h3>
	<form action="addCart" method="Post" >
	<input type="hidden" name="storeName" value="${requestScope.store.sName}" />
		<table border="1" align="center">
			<thead>
				<tr>
					<th>Select</th>
					<th>Product Name</th>
					<th>Product Price</th>
					<th>Product Photo</th>		
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${requestScope.productsUnderStore}" var="products">
					<tr>
						<td><input type="checkbox" name="select"
							value="${products.pID}" /></td>
						<td style="text-align: center">${products.productName}</td>
						<td style="text-align: center">${products.pPrice}</td>
						<td><img src="data:image/png;base64,${products.base64}"
							width="200" height="200" /></td>
						
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<input type="submit" name="addtoCart" Value="ADDTOCART"/>
		<input type="hidden" name="page"  value="add"/>
	</form>

	<br>
	<br>
	<button type="button" class="btn">
		<a href="/GroceryDeliveryApplication/backtohome">Goback to Home</a>
	</button>

</body>
</html>