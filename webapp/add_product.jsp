
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
							<li class="active"><a href="#">Add Product</a></li>
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
						<h2 class="p-2 text-center mb-4 h4" id="formHeading">Upload Product Details</h2>
						<form action="ProductServlet" enctype="multipart/form-data" method="post">
							<div class="form-group mt-3">
								<label class="mb-2" for="name">Category</label>
								<input class="form-control" id="category" type="text" list="category_list" oninput="getCategoryId();" required/>
								<input id="seller_id_fk" name="Seller_id_fk" type="hidden" value="1"/>
								<input id="category_id_fk" name="Category_id_fk" type="hidden" value="0"/>
								<input id="flag" name="Flag" type="hidden" value="Insert"/>
							</div>
							
							<datalist id="category_list">
								<%
								ItemCategoryService ic_service = new ItemCategoryService();
								ArrayList<ItemCategoryDto> ic_list = ic_service.getActiveCategoryInfo(config, request);
								for (ItemCategoryDto it_dto : ic_list) {
								%>
								<option data-id="<%=it_dto.getId()%>" value="<%=it_dto.getName()%>"><%=it_dto.getName()%></option>
								<%
								}
								%>
							</datalist>
							
							<div class="form-group mt-3">
								<label class="mb-2" for="title">Title</label>
								<input class="form-control" id="title" name="Title" type="text" />
							</div>
							
							<div class="form-group mt-3">
								<label class="mb-2" for="description">Description</label>
								<textarea id="description" name="Description" placeholder=""></textarea>
							</div>

							<div class="form-group mt-3">
								<label class="mb-2" for="price">Price</label>
								<input class="form-control" id="price" name="Price" type="number" step="0.01"/>
							</div>
							
							<div class="form-group mt-3">
								<label class="mb-2">Select Product Images</label>
							</div>
							
							
							<div class="d-flex justify-content-center">
									<div class="col-md-4">
										<div class="control form-floating">
											<div class="file-form" style="padding: 0.5%;">
												<div class="d-flex justify-content-center pt-1">
													<img src="" id="preview-selected-image"
														onerror="this.src='assets/img/dummy.png'" class="preview"
														alt="" style="height: 100px; width: 100px;">
												</div>
												<div class="d-flex justify-content-center">
													<div class="pt-1 pb-1">
														<div style="display: none;">
															<input name="File" onchange="previewImage(event);"
																type="file" id="file" multiple>
														</div>
														<label for="file" id="file-label"> <a
															class="btn btn-primary btn-sm"
															title="Upload new profile image"> <i
																class="bi bi-upload"></i>
														</a>
														</label>
													</div>
													
												</div>
											</div>
										</div>
									</div>
								</div>
							
							<button class="btn btn-success btn-lg w-100 mt-4" type="submit">Submit</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

<script>
function previewImage(event) {
	let reader = new FileReader();
	reader.onload = function() {
		let element = document.getElementById('preview-selected-image');
		element.src = reader.result;
	}
	reader.readAsDataURL(event.target.files[0]);
} 
</script>

<script>
    function getCategoryId() {
        var inputElement = document.getElementById("category");
        var selectedOption = document.querySelector('#category_list option[value="' + inputElement.value + '"]');
        
        if (selectedOption) {
            var id = selectedOption.getAttribute('data-id');
            document.getElementById("category_id_fk").value = id;
        } else {
        	document.getElementById("category_id_fk").value = 0;
        }
    }
</script>
	
 	<!-- ======= Header ======= -->
	<%@include file="include/footer.jsp"%>
	<!-- ======= Header end======= -->
