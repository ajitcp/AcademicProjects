<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Available Stores</title>

</head>
<body>
	<div class="page-top"
		style="width: 100%; height: 20px; background-color: #f46b42"></div>
	<h3 align="center">Stores List</h3>
	<form>
		<table border="1" align="center">
			<thead>
				<tr>
					<th>Store Name</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${requestScope.availableStores}" var="stores">
					<tr>
						<td>${stores.sName}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</form>

	<button type="button" class="btn">
		<a href="/GroceryDeliveryApplication/backtohome">Goback to HomePage</a>
	</button>

</body>
</html>