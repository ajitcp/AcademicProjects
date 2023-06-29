<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

<meta name="viewport" content="width=device-width, initial-scale=1.0" />


<title>View Cart</title>
</head>
<body>
	<c:set var="Cpath"
		value="${ pageContext.request.contextPath}/showcustomerproductsinstore" />
	<div class="page-top"
		style="width: 100%; height: 20px; background-color: #f46b42"></div>
	<c:set var="cart" value="${sessionScope.cart}" />
	<p align="center">View Your Cart</p>
	<form action="Cart" method="post">
		<table border=1 align="center">
			<tr>
				<th>Select item to Remove</th>
				<th>Product</th>
				<th>Quantity</th>
				<th>StoreName</th>
			</tr>
			<c:if test="${cart != null}">
				<c:forEach var="item" items="${cart.cart}">
					<tr>
						<td><input type="checkbox" name="remove" value="${item.name}" /></td>
						<td>${item.name}</td>
						<td>${item.count}</td>
						<td>${item.storeName}</td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
	<!-- - --	<label>Confirm Order:</label><input type="checkbox" name="finalcheck"
			value="Y" />--> <br />
		<div align="center">
			<input type="submit" name="Submit" value="Remove Item" /> <input
				type="hidden" name="page" value="remove" />
		</div>
	</form>
	<br>
	<br>
	<br>
	<br>
	<br>
	<button type="button" onclick="ajaxPrice()" align="center">Get
		Cart Price</button>
	<br>

    <form action="checkoutReport.pdf" method="post">
	<p>CartPrice:
	<div id="CartPrice" name="cartPrice"/></div>
	$
	<input type="hidden" name="cartPrice" id="cartPriceInput">
	</p>
	
	<br>
	<br>
	<input type="submit" name="checkout" Value="PlaceOrder"/>
	</form>
	<br>
	<br>
	<br>
	<button type="button" class="btn" align="center">
		<a href="/GroceryDeliveryApplication/homestorepurchase">Goback to
			Store</a>
	</button>
	<br>
	<br>
	<br>

	<button type="button" class="btn" align="center">
		<a href="/GroceryDeliveryApplication/backtohome">Goback to Home</a>
	</button>
	<script>
		function ajaxPrice() {

			var xmlHttp = new XMLHttpRequest();

			xmlHttp.onreadystatechange = function() {
				if (xmlHttp.readyState === 4 && xmlHttp.status === 200)

					var divElement = document.getElementById("CartPrice");
				divElement.innerHTML = xmlHttp.responseText;

			}
			xmlHttp.open("GET",
					"/GroceryDeliveryApplication/getCartPrice?user=Ajit", true);
			xmlHttp.send();

			//DOM Manipulation

		}
		
		
	
	</script>
</body>
</html>