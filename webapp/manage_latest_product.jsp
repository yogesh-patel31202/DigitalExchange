
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
							<li class="active"><a href="#">Latest Product Report</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- End Breadcrumbs -->
  
	<!-- Start Contact -->
	<section id="contact-us" class="contact-us section">
		<div class="container">
				<div class="contact-head">
					<div class="row">
						<div class="col-lg-12 col-12">
							<div class="form-main">
								<div class="title text-center">
									<h4>Latest Product Report</h4>
								</div>
								
								<div class="container table-responsive col-12 mt-5">
								    <table id="large_simple_table" class="table table-striped table-bordered">
								        <thead>
								            <tr>
								                <th style="width: 10%;">SN</th>
												<th style="width: 20%;">Title</th>
												<th style="width: 10%;">Description</th>
												<th style="width: 20%;">Price</th>
												<th style="width: 10%;">Posting Date</th>
												<th style="width: 25%;">Status</th>
												<th style="width: 10%;">Seller</th>
												<th style="width: 10%;">Mobile</th>
												<th style="width: 10%;">Email</th>
												<th style="width: 10%;">Admin</th>
												<th style="width: 10%;">Mobile</th>
												<th style="width: 10%;">Edit</th>
												<th style="width: 10%;">Delete</th>
								            </tr>
								        </thead>
								        <tbody>
								        
								<%
								ProductService service = new ProductService();
								ArrayList<ProductDto> list = service.getLatestProductInfo(config, request);

								for (ProductDto dto : list) {
								%>
								
								<tr>

									<td id="name-td"><%=list.indexOf(dto) + 1%></td>
									<td class="main-color"><%=dto.getTitle()%></td>
									<td><%=dto.getDescription()%></td>
									<td><%=dto.getPrice()%></td>
									<td><%=dto.getPosting_date()%></td>
									<td><%=dto.getStatus()%></td>
									<td><%=dto.getSeller_name()%></td>
									<td><%=dto.getSeller_mobile()%></td>
									<td><%=dto.getSeller_email()%></td>
									<td><%=dto.getAdmin_name()%></td>
									<td><%=dto.getAdmin_mobile()%></td>

									<td><a class="main-color"
										href="edit_product.jsp?id=<%=dto.getId()%>"><i
											class="bi bi-pencil-square" style="margin-left: 10px;"></i></a></td>

									<td><a class="main-color"
										onclick="return confirm('Are you sure? You want to delete')"
										href="AjaxService/AjaxDelete.jsp?admin_id=<%=dto.getId()%>">
											<i class="bi bi-trash"></i>
									</a></td>

								</tr>
								
								<%} %>
								        </tbody>
								    </table>
								</div>
								
							</div>
						</div>
					</div>
				</div>
			</div>
	</section>
	<!--/ End Contact -->
	
	<!-- Map Section -->
	<div class="map-section">
		<div id="myMap"></div>
	</div>
	<!--/ End Map Section -->
	
 	<!-- ======= Header ======= -->
	<%@include file="include/footer.jsp"%>
	<!-- ======= Header end======= -->
	
		<!-- ======= datatable_script ======= -->
	<%@include file="include/datatable_script_footer.jsp"%>
	<!-- End datatable_script --> 
	

