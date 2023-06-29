<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ"
	crossorigin="anonymous" />

<title>My Profile</title>
</head>

<body>
	<div class="page-top"
		style="width: 100%; height: 20px; background-color: #f46b42"></div>
	<ul class="nav nav-tabs" id="myTab" role="tablist">
		<li class="nav-item" role="presentation">
			<button class="nav-link active" id="home-tab" data-bs-toggle="tab"
				data-bs-target="#home-tab-pane" type="button" role="tab"
				aria-controls="home-tab-pane" aria-selected="true">
				MyProfile</button>
		</li>


	</ul>
	<div class="tab-content" id="myTabContent">
		<div class="tab-pane fade show active" id="home-tab-pane"
			role="tabpanel" aria-labelledby="home-tab" tabindex="0">
			<form:form modelAttribute="users">
				<div class="form-group">
					
					UserName:<form:input path="username" type="text"
						class="form-control" id="username" name="username"
						style="width: 50%; font-size: small" />
				</div>
				<div id="UserName" name="UserName" /></div>

				<div class="form-group">
					
					Email:<form:input path="email"  type="text"
						class="form-control" id="email" name="email"
						style="width: 50%; font-size: small" />
				</div>

				<div class="form-group">
					
					FirstName:<form:input path="firstname"  type="text"
						class="form-control" id="firstname" name="firstname"
						style="width: 50%; font-size: small" />
				</div>

				<div class="form-group">
					
					LastName:<form:input path="lastname" type="text"
						class="form-control" id="lastname" name="lastname"
						style="width: 50%; font-size: small" />
				</div>
				<div class="form-group">
					
					Password:<form:input path="password"  type="text"
						class="form-control" id="password" name="password"
						style="width: 50%; font-size: small" />
				</div>
				<div class="form-group">
					
					Role:<form:select path="userrole" 
						class="form-control" id="role" name="role"
						style="width: 50%; font-size: small">
						<option value="">--Select Role--</option>
						<option value="USER">USER</option>
						<option value="ADMIN">ADMIN</option>
						</select>
				</div>

				<div class="form-group">
				
					AptNo:<form:input path="aptNo"  type="text"
						class="form-control" id="apt" name="apt"
						style="width: 50%; font-size: small" />
				</div>
				<div class="form-group">
					
					City<form:input path="city"  type="text"
						class="form-control" id="city" name="city"
						style="width: 50%; font-size: small" />
				</div>
				<div class="form-group">
					
					State<form:input path="state" type="text"
						class="form-control" id="state" name="state"
						style="width: 50%; font-size: small" />
				</div>
				<div class="form-group">
					
					Street<form:input path="street" type="text"
						class="form-control" id="street" name="street"
						style="width: 50%; font-size: small" />
				</div>
				<div class="form-group">
					
					ZipCode<form:input path="zipCode" required="required" type="text"
						class="form-control" id="zipCode" name="zipCode"
						style="width: 50%; font-size: small" />
				</div>
				<input type="hidden" name="action" value="add">
				<button type="submit" class="btn btn-primary"
					style="margin-top: 10px">Create new account</button>
				</form:form>
		</div>



		<button type="submit" class="btn btn-primary">Submit</button>
		</form>
	</div>
	</div>

	<button type="button" class="btn">
		<a href="/GroceryDeliveryApplication/">Goback to Home</a>
	</button>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.min.js"
		integrity="sha384-Y4oOpwW3duJdCWv5ly8SCFYWqFDsfob/3GkgExXKV4idmbt98QcxXYs9UoXAB7BZ"
		crossorigin="anonymous"></script>
</body>
</html>
