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

<title>My Account</title>
</head>

<body>
	<div class="page-top"
		style="width: 100%; height: 20px; background-color: #f46b42"></div>
	<ul class="nav nav-tabs" id="myTab" role="tablist">
		<li class="nav-item" role="presentation">
			<button class="nav-link active" id="home-tab" data-bs-toggle="tab"
				data-bs-target="#home-tab-pane" type="button" role="tab"
				aria-controls="home-tab-pane" aria-selected="true">
				Create New Account</button>
		</li>
		<li class="nav-item" role="presentation">
			<button class="nav-link" id="profile-tab" data-bs-toggle="tab"
				data-bs-target="#profile-tab-pane" type="button" role="tab"
				aria-controls="profile-tab-pane" aria-selected="false">
				Login</button>
		</li>

	</ul>
	<div class="tab-content" id="myTabContent">
		<div class="tab-pane fade show active" id="home-tab-pane"
			role="tabpanel" aria-labelledby="home-tab" tabindex="0">
			<form action="signup" method="post">
				<div class="form-group">
					<label for="newUsername">* Username: </label>&nbsp;<span
						style="color: red"></span> <input required="required"
						type="text" class="form-control" id="username" name="username"
						placeholder="username" style="width: 50%; font-size: small" />
				</div>
				<div id="UserName" name="UserName" /></div>

				<div class="form-group">
					<label for="email">* Email Address: </label><span
						style="color: red"></span> <input required="required" type="text"
						class="form-control" id="email" name="email" placeholder="email"
						style="width: 50%; font-size: small" />
				</div>

				<div class="form-group">
					<label for="email">* FirstName: </label><span style="color: red"></span>
					<input required="required" type="text" class="form-control"
						id="firstname" name="firstname" placeholder="FirstName"
						style="width: 50%; font-size: small" />
				</div>

				<div class="form-group">
					<label for="email">* LastName: </label><span style="color: red"></span>
					<input required="required" type="text" class="form-control"
						id="lastname" name="lastname" placeholder="LastName"
						style="width: 50%; font-size: small" />
				</div>
				<div class="form-group">
					<label for="email">* Password: </label><span style="color: red"></span>
					<input required="required" type="text" class="form-control"
						id="password" name="password" placeholder="Password"
						style="width: 50%; font-size: small" />
				</div>
				<div class="form-group">
					<label for="role">*Role:</label><span style="color: red">*</span> <select
						required="required" class="form-control" id="role" name="role"
						style="width: 50%; font-size: small">
						<option value="">--Select Role--</option>
						<option value="USER">USER</option>
						<option value="ADMIN">ADMIN</option>
					</select>
				</div>

				<div class="form-group">
					<label for="email">*AptNo: </label><span style="color: red"></span>
					<input required="required" type="text" class="form-control"
						id="apt" name="apt" placeholder="aptno"
						style="width: 50%; font-size: small" />
				</div>
				<div class="form-group">
					<label for="email">*City: </label><span style="color: red"></span>
					<input required="required" type="text" class="form-control"
						id="city" name="city" placeholder="city"
						style="width: 50%; font-size: small" />
				</div>
				<div class="form-group">
					<label for="email">*State: </label><span style="color: red"></span>
					<input required="required" type="text" class="form-control"
						id="state" name="state" placeholder="state"
						style="width: 50%; font-size: small" />
				</div>
				<div class="form-group">
					<label for="email">*Street: </label><span style="color: red"></span>
					<input required="required" type="text" class="form-control"
						id="street" name="street" placeholder="street"
						style="width: 50%; font-size: small" />
				</div>
				<div class="form-group">
					<label for="email">*Zipcode: </label><span style="color: red"></span>
					<input required="required" type="text" class="form-control"
						id="zipCode" name="zipCode" placeholder="zipcode"
						style="width: 50%; font-size: small" />
				</div>
				<input type="hidden" name="action" value="add">
				<button type="submit" class="btn btn-primary"
					style="margin-top: 10px">Create new account</button>
			</form>
		</div>
		<div class="tab-pane fade" id="profile-tab-pane" role="tabpanel"
			aria-labelledby="profile-tab" tabindex="0">
			<form action="login" method="post">
				<div class="form-group">
					<label for="username">* Username: </label> <input
						required="required" type="text" class="form-control" id="username"
						name="username" />
					<p style="color: #828282">Enter your username here.</p>
				</div>

				<div class="form-group">
					<label for="password">* Password: </label> <input
						required="required" type="password" class="form-control"
						id="password" name="password" />
					<p style="color: #828282">Enter the password that accompanies
						your username</p>
				</div>

				<button type="submit" class="btn btn-primary">Login</button>
			</form>
		</div>
		<div class="tab-pane fade" id="contact-tab-pane" role="tabpanel"
			aria-labelledby="contact-tab" tabindex="0">
			<form>
				<div class="form-group">
					<label for="recoverEmail">* Your Email: </label> <input
						required="required" type="text" class="form-control"
						id="recoverEmail" name="email" />
					<p style="color: #828282">Enter your registered email address
						here.</p>
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
