<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Available Store</title>

</head>
<body>
	<div class="page-top"
		style="width: 100%; height: 20px; background-color: #f46b42"></div>
	<h3 align="center">Products List</h3>
	<form action="assciateStoretoProducts" method="POST">
		<table border="1" align="center">
			<thead>
				<tr>
					<th>Select</th>
					<th>Store Id</th>
					<th>Store Name</th>
					<th>Associate Products</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${requestScope.availableStores}" var="store">
					<tr>
						<td><input type="checkbox" name="select"
							value="${store.sId}" /></td>
						<td style="text-align: center">${store.sId}</td>
						<td style="text-align: center">${store.sName}</td>						
						<!-- - --<td style="text-align: center"><input  type="submit" name="actionValue"
							value="DELETE" /></td>
						<td style="text-align: center"><input  type="submit" name="actionValue"
							value="UPDATE"/></td>--->
						<td style="text-align: center"><input  type="submit" name="actionValue"
							value="AssociateProducts"/></td>	
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