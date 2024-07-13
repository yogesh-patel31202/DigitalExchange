
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
							<li class="active"><a href="#">Add Home Slider</a></li>
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
						<h2 class="p-2 text-center mb-4 h4" id="formHeading">Add Home Slider</h2>
						
						<form method="post" action="HomeSliderServlet" enctype="multipart/form-data" novalidate  >
									
							<div class="form-group mt-3">
								<label class="mb-2" for="name">Title</label>
								<input class="form-control" id="title" name="Title" type="text"/>
								<input id="admin_id_fk" name="Admin_id_fk" type="hidden" value="1"/>
							</div>
							
							<div class="form-group mt-3">
								<label class="mb-2" for="name">Link </label>
								<input class="form-control" id="link" name="Link" type="text"/>
							</div>
							
							<div class="d-flex justify-content-center">
									<div class="col-md-4">
										<div class="form-floating">
											<div class="file-form" style="padding: 0.5%;">
												<div class="d-flex justify-content-center pt-1">
													<img src="" id="preview-selected-image" onerror="this.src='assets/img/dummy.png'"
														 class="preview"
														alt="" style="height: 100px; width: 100px;">
												</div>
												<div class="d-flex justify-content-center">
													<div class="pt-1 pb-1">
														<div style="display: none;">
															<input name="File" onchange="previewImage(event);"
																type="file" id="file">
														</div>
														<label for="file" id="file-label"> <a
															class="btn btn-primary btn-sm"
															title="Upload new profile image"> <i
																class="bi bi-upload"></i>
														</a>
														</label>
													</div>
													<!-- <div class="pt-2" style="margin-left: 2px;">
														<a class="btn btn-primary btn-sm" title="download"
															id="download" download><i
															class="fa-solid fa-download"></i> </a>
													</div> -->
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
	
	<script type="text/javascript">
 
function previewImage(event) {
	let reader = new FileReader();
	reader.onload = function() {
		let element = document.getElementById('preview-selected-image');
		element.src = reader.result;
	}
	reader.readAsDataURL(event.target.files[0]);
} 
</script>
	
 	<!-- ======= Header ======= -->
	<%@include file="include/footer.jsp"%>
	<!-- ======= Header end======= -->
 