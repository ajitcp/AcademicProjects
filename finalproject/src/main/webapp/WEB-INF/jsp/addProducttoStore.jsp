<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Associate Product to Store</title>

</head>
<body>
	<div class="page-top"
		style="width: 100%; height: 20px; background-color: #f46b42"></div>
	Store Id:  <p align="left">${requestScope.store.sId}</p>	
	Store Name:<p align="left">${requestScope.store.sName}</p>		
	<h3 align="center">Products List</h3>
	<form action="addProductstoStore" method="POST">
		<table border="1" align="center">
			<thead>
				<tr>
					<th>Select</th>
					<th>Product Id</th>
					<th>Product Name</th>
					<th>Product Price</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${requestScope.availableProducts}" var="products">
					<tr>
						<td><input type="checkbox" name="select"
							value="${products.pID}" /></td>
						<td style="text-align: center">${products.pID}</td>
						<td style="text-align: center">${products.productName}</td>
						<td style="text-align: center">${products.pPrice}</td>													
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<input type="hidden" name="storeid" value="${requestScope.store.sId}"/>
		<input type="hidden" name="storeName" value="${requestScope.store.sName}" />
		<input type="submit" name="addPrdstoStore" value="AddProductstoStore"/>
	</form>
	
	<br>
	<br>
	<button type="button" class="btn">
		<a href="/GroceryDeliveryApplication/backtohome">Goback to Home</a>
	</button>

</body>
</html>