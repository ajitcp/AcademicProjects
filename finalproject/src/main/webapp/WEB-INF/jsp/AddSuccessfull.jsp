<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ"
      crossorigin="anonymous"
    />
        
        <title>Add Items to Cart</title>
    </head>
    <body>
     <div
      class="page-top"
      style="width: 100%; height: 20px; background-color: #f46b42"
    ></div>
        <c:set var="items" value="${sessionScope.items}"/>
        <c:choose>
            <c:when test="${items != null}">
                <p>The following item has been added to your shopping cart successfully</p>
                <c:forEach var="name" items="${items}">
                    <p>- ${name}</p>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <h2>Please add some item to your cart</h2>  
            </c:otherwise>
        </c:choose>
        <p>
            [<a href="/GroceryDeliveryApplication/ViewCart">View Cart</a>] 
           
        </p>
    </body>
</html>
