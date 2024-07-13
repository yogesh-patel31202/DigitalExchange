
 	<!-- ======= Header ======= -->
	<%@include file="include/header.jsp"%>
	<!-- ======= Header end======= -->
 	
	<!-- Breadcrumbs -->
	<div class="breadcrumbs">
		<div class="container">
			<div class="row">
				<div class="col-12">
					<div class="bread-inner">
						<ul class="bread-list">
							<li><a href="index.jsp">Home<i class="ti-arrow-right"></i></a></li>
							<li class="active"><a href="#">Update User</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- End Breadcrumbs -->
	
	  <%
							int id = Integer.parseInt(request.getParameter("id") == null ? "0" : request.getParameter("id"));
							UserService service = new UserService();

							UserDto dto = service.getUserInfoById(id, config, request);
%>
	
		<div class="p-4">
		<div class="container">
			<div class="row">
				<div class="col-md-6 col-sm-12 col-lg-5 mx-auto">
					<div class="formContainer">
						<h2 class="p-2 text-center mb-4 h4" id="formHeading">Update User</h2>
						<form method="post" action="UserServlet" >
							<div class="form-group mt-3">
								<label class="mb-2" for="name">Name</label>
								<input class="form-control" id="name" name="Name" type="text" value="<%=dto.getName()%>"/>
								<input id="id" name="Id" type="hidden" value="<%=dto.getId()%>"/>
								<input id="flag" name="Flag" type="hidden" value="Update"/>
							</div>
							
							<div class="form-group mt-3">
								<label class="mb-2" for="mobile">Mobile</label>
								<input class="form-control" id="mobile" name="Mobile" type="number" value="<%=dto.getMobile()%>"/>
							</div>

							<div class="form-group mt-3">
								<label class="mb-2" for="email">Email ID </label>
								<input class="form-control" id="email" name="Email" type="email" value="<%=dto.getEmail()%>"/>
							</div>
							
							<div class="form-group mt-3">
								<label class="mb-2" for="password">Password</label>
								<input class="form-control" id="password" name="Password" type="password" value="<%=dto.getPassword()%>"/>
							</div>
							
							<div class="form-group mt-3">
								<label class="mb-2" for="re_password">Re-Password</label>
								<input class="form-control" id="re_password" type="password" value="<%=dto.getPassword()%>"/>
							</div>
							
							<div class="form-group mt-3">
								<label class="mb-2" for="address">Address</label>
								<textarea name="Address" placeholder=""><%=dto.getAddress()%></textarea>
							</div>
							
							<div class="form-group mt-3">
								<label class="mb-2" for="status">Status</label><br>
								<select name="Status" id="status" class="form-control">
													<option value="Active"
												<%if (dto.getStatus().equals("Active")) {
												out.print("selected='selected'");
												}%>>Active</option>

											<option value="Block"
												<%if (dto.getStatus().equals("Block"))

												{
													out.print("selected='selected'");
												
												}%>>Block</option>
												</select>
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
 