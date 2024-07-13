
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
							<li class="active"><a href="#">Add Product</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- End Breadcrumbs -->
	
		  <%
							int id = Integer.parseInt(request.getParameter("id") == null ? "0" : request.getParameter("id"));
							ProductService service = new ProductService();

							ProductDto dto = service.getProductInfoByProductId(id, config, request);
		%>

	
		<div class="p-4">
		<div class="container">
			<div class="row">
				<div class="col-md-8 col-sm-12 col-lg-6 mx-auto">
					<div class="formContainer">
						<h2 class="p-2 text-center mb-4 h4" id="formHeading">Upload Product Details</h2>
						<form action="ProductServlet" enctype="multipart/form-data" method="post">
							<div class="form-group mt-3">
								<label class="mb-2" for="name">Category</label>
								<input class="form-control" id="category" type="text" list="category_list" oninput="getCategoryId();" value="<%=dto.getItem_category() %>" required/>
								<input id="seller_id_fk" name="Seller_id_fk" type="hidden" value="<%=dto.getSeller_id_fk() %>"/>
								<input id="admin_id_fk" name="Admin_id_fk" type="hidden" value="<%=admin_id %>"/>
								<input id="approve_date" name="Approve_date" type="hidden" value="<%=current_date%>"/>
								<input id="category_id_fk" name="Category_id_fk" type="hidden" value="<%=dto.getCategory_id_fk() %>"/>
								<input id="id" name="Id" type="hidden" value="<%=dto.getId() %>"/>
								<input id="remove_images_id" name="Remove_images_id" type="hidden" value=""/>
								<input id="flag" name="Flag" type="hidden" value="Approve"/>
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
								<input class="form-control" id="title" name="Title" type="text" value="<%=dto.getTitle() %>"/>
							</div>
							
							<div class="form-group mt-3">
								<label class="mb-2" for="description">Description</label>
								<textarea id="description" name="Description" placeholder=""><%=dto.getDescription() %></textarea>
							</div>

							<div class="form-group mt-3">
								<label class="mb-2" for="price">Price</label>
								<input class="form-control" id="price" name="Price" type="number" step="0.01" value="<%=dto.getPrice() %>"/>
							</div>
							
							<div class="form-group mt-3">
								<label class="mb-2">Images</label>
							</div>
							
							<div class="form-group mt-3">
								<div class="d-flex justify-content-center col-12">
								<%
								ArrayList<ProductImgDto> pi_list = service.getProductImgByProductId(id, config, request);
								for (ProductImgDto pi_dto : pi_list) {
								%>
								
									<div style="margin-left: 10px;" class="text-center col-4" id="image_div_<%=pi_dto.getId()%>">
													<img src="ProductImage/<%=pi_dto.getId() %>.jpg" 
														onerror="this.src='assets/img/dummy.png'" class="preview"
														alt="" style="height: 100px; width: 100px;">
												<br>
											
											<i class="bi bi-trash" onclick="hideImagesDiv('<%=pi_dto.getId()%>');"></i>
											
											<a href="ProductImage/<%=pi_dto.getId() %>.jpg" download style="margin-left: 20px;"><i class="bi bi-download" ></i></a>
									
									</div>
									<%} %>
									
								</div>
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
								
								<div class="form-group mt-3">
								<label class="mb-2" for="status">Status</label><br>
								<select name="Status" id="status" class="form-control">
											<option value="Approve"
												<%if (dto.getStatus().equals("Approve"))

												{
													out.print("selected='selected'");
												
												}%>>Approve</option>
												
												<option value="Block"
												<%if (dto.getStatus().equals("Block")) {
												out.print("selected='selected'");
												}%>>Block</option>
												
													<option value="Panding"
												<%if (dto.getStatus().equals("Panding")) {
												out.print("selected='selected'");
												}%>>Panding</option>

										</select>
								</div>
								
								<br><br>
								
								<div class="form-group">
								<label class="mb-2" for="status">Type</label><br>
								<select name="Type" id="type" class="form-control">
											<option value="" <%if (dto.getType().equals("")){out.print("selected='selected'");}%>>Select Type</option>
											<option value="Latest" <%if (dto.getType().equals("Latest")){out.print("selected='selected'");}%>>Latest</option>
											<option value="Special" <%if (dto.getType().equals("Special")){out.print("selected='selected'");}%>>Special</option>
											<option value="Offer" <%if (dto.getType().equals("Offer")){out.print("selected='selected'");}%>>Offer</option>
									</select>
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

<script>
function hideImagesDiv(image_id) {
    var image_div = document.getElementById('image_div_' + image_id);
    
    var confirmationMessage = 'Are you sure you want to remove this image?';

    var confirmed_status = window.confirm(confirmationMessage);
    if (confirmed_status) {
    	addImageId(image_id);
        image_div.style.display = 'none';
    }
}
		
</script>

<script >

var image_id_arr = [];

function addImageId(image_id_value) {

    if (image_id_arr.includes(image_id_value)) {
    	image_id_arr = image_id_arr.filter(num => num !== image_id_value);
    } else {
    	image_id_arr.push(image_id_value);
    }

	document.getElementById('remove_images_id').value = image_id_arr.join(',');
}

</script>
	
 	<!-- ======= Header ======= -->
	<%@include file="include/footer.jsp"%>
	<!-- ======= Header end======= -->
