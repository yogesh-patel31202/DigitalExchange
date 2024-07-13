
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
							<li class="active"><a href="#">Add Item Category</a></li>
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
						<h2 class="p-2 text-center mb-4 h4" id="formHeading">Add Item Category</h2>
						<form method="post" action="ItemCategoryServlet" >
						
							<div class="form-group mt-3">
								<label class="mb-2" for="name">Name</label>
								<input class="form-control" id="name" name="Name" type="text"/>
								<input id="admin_id_fk" name="Admin_id_fk" type="hidden" value="<%=admin_id%>"/>
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
 