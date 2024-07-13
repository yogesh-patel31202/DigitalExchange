
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
							<li><a href="index_admin.jsp">Home<i class="ti-arrow-right"></i></a></li>
							<li class="active"><a href="#">Update Item Category</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- End Breadcrumbs -->
	
	  <%
							int id = Integer.parseInt(request.getParameter("id") == null ? "0" : request.getParameter("id"));
							ItemCategoryService service = new ItemCategoryService();

							ItemCategoryDto dto = service.getCategoryInfoById(id, config, request);
%>
	
		<div class="p-4">
		<div class="container">
			<div class="row">
				<div class="col-md-6 col-sm-12 col-lg-5 mx-auto">
					<div class="formContainer">
						<h2 class="p-2 text-center mb-4 h4" id="formHeading">Update Item Category</h2>
						<form method="post" action="ItemCategoryServlet" >
							<div class="form-group mt-3">
								<label class="mb-2" for="name">Name</label>
								<input class="form-control" id="name" name="Name" type="text" value="<%=dto.getName()%>"/>
								<input id="id" name="Id" type="hidden" value="<%=dto.getId()%>"/>
								<input id="id" name="Admin_id_fk" type="hidden" value="<%=dto.getAdmin_id_fk()%>"/>
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
							
							<button class="btn btn-success btn-lg w-100 mt-4" type="submit">Submit</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	
 	<!-- ======= Header ======= -->
	<%@include file="include/footer.jsp"%>
	<!-- ======= Header end======= -->
 