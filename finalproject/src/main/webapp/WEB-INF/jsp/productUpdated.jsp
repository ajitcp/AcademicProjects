<!DOCTYPE html>
<html lang="en">
 <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />

    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ"
      crossorigin="anonymous"
    />

    <title> Product Updated</title>
  </head>
<body>
 <div
      class="page-top"
      style="width: 100%; height: 20px; background-color: #f46b42"
    ></div>
<fieldset>
      <p> Product Updated</p>
     Product ID   : ${requestScope.updatePrd.pID}<br>
     ProductName  : ${requestScope.updatePrd.productName }<br>
     ProductPrice : ${requestScope.updatePrd.pPrice }
     
</fieldset>

  <button type="button" class="btn">
      <a href="/GroceryDeliveryApplication/products">Goback to Manage Products</a>
    </button> <br>
  <button type="button" class="btn">
      <a href="/GroceryDeliveryApplication/backtohome">Goback to Home</a>
    </button> 
 <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.min.js"
      integrity="sha384-Y4oOpwW3duJdCWv5ly8SCFYWqFDsfob/3GkgExXKV4idmbt98QcxXYs9UoXAB7BZ"
      crossorigin="anonymous"
    ></script>

</body>
</html>