
 	<!-- ======= Header ======= -->
	<%@include file="include/header_admin.jsp"%>
	<!-- ======= Header end======= -->
 	
	<!-- Breadcrumbs -->
	<div class="breadcrumbs">
		<div class="container">
			<div class="row">
				<div class="col-12">
					<div class="bread-inner">
						<ul class="bread-list">
							<li><a href="index_admin.jsp">Home<i class="ti-arrow-right"></i></a></li>
							<li class="active"><a href="#">Admin Registration</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- End Breadcrumbs -->
	
		<div class="p-4">
		<div class="container">
			<div class="row">
				<div class="col-md-6 col-sm-12 col-lg-5 mx-auto">
					<div class="formContainer">
						<h2 class="p-2 text-center mb-4 h4" id="formHeading">Admin Registration</h2>
						<form method="post" action="AdminServlet" >
							<div class="form-group mt-3">
								<label class="mb-2" for="name">Name</label>
								<input class="form-control" id="name" name="Name" type="text"/>
								<input id="flag" name="Flag" type="hidden" value="Insert"/>
							</div>
							
							<div class="form-group mt-3">
								<label class="mb-2" for="mobile">Mobile</label>
								<input class="form-control" id="mobile" name="Mobile" type="number" />
							</div>

							<div class="form-group mt-3">
								<label class="mb-2" for="email">Email ID </label>
								<input class="form-control" id="email" name="Email" type="email" />
							</div>
							
							<div class="form-group mt-3">
								<label class="mb-2" for="password">Password</label>
								<input class="form-control" id="password" name="Password" type="password"/>
							</div>
							
							<div class="form-group mt-3">
								<label class="mb-2" for="re_password">Re-Password</label>
								<input class="form-control" id="re_password" type="password"/>
							</div>
							
							<div class="form-group mt-3">
								<label class="mb-2" for="address">Address</label>
								<textarea name="Address" placeholder=""></textarea>
							</div>
							
							<button class="btn btn-success btn-lg w-100 mt-4" type="submit">SignUp</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	
 	<!-- ======= Header ======= -->
	<%@include file="include/footer.jsp"%>
	<!-- ======= Header end======= -->
 