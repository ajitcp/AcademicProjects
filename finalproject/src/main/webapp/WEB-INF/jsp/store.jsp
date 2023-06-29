
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

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

    <title>Create New Store</title>
  </head>

  <body>
    <div
      class="page-top"
      style="width: 100%; height: 20px; background-color: #f46b42"
    ></div>
    <ul class="nav nav-tabs" id="myTab" role="tablist">
      <li class="nav-item" role="presentation">
        <button
          class="nav-link active"
          id="home-tab"
          data-bs-toggle="tab"
          data-bs-target="#home-tab-pane"
          type="button"
          role="tab"
          aria-controls="home-tab-pane"
          aria-selected="true"
        >
          Create New Store
        </button>
      </li>
     
    </ul>
    <div class="tab-content" id="myTabContent">
      <div
        class="tab-pane fade show active"
        id="home-tab-pane"
        role="tabpanel"
        aria-labelledby="home-tab"
        tabindex="0"
      >
           <form:form action="addstore" modelAttribute="store" method="post" >
          <div class="form-group">
            <label for="newProduct">* Store Name: </label>&nbsp;<span
              style="color: red"
            ></span>
            <form:input path="sName"
              
              type="text"
              class="form-control"
              id="storename"
              name="storename"
              style="width: 50%; font-size: small"
            /><font color="red"><form:errors path="sName"/></font>
          </div>

          
         
          <input type="hidden" name="action" value="add">
          <button
            type="submit"
            class="btn btn-primary"
            style="margin-top: 10px"
          >
            Create new Store
          </button>
        </form:form>
      </div>
      
      
    </div>

    <button type="button" class="btn">
      <a href="/GroceryDeliveryApplication/backtohome">Back to Home</a>
    </button>

    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.min.js"
      integrity="sha384-Y4oOpwW3duJdCWv5ly8SCFYWqFDsfob/3GkgExXKV4idmbt98QcxXYs9UoXAB7BZ"
      crossorigin="anonymous"
    ></script>
  </body>
</html>
