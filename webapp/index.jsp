 
	<!-- ======= Header ======= -->
	<%@include file="include/header.jsp"%>
	<!-- ======= Header end======= -->

	<section class="hero-slider">


		<div class="col-12">
			<div class="row">
	
				<!-- Existing col-8 -->
				<div class="col-lg-12 col mt-2">
					<section class="hero-slider">
					<div id="myCarousel" class="carousel slide" data-ride="carousel">
						<!-- Indicators -->
						<ul class="carousel-indicators">
							<%
								HomeSliderService hs_service = new HomeSliderService();
								ArrayList<HomeSliderDto> hs_list = hs_service.getHomeSliderInfo(config, request);
								int j = 0;
								for (HomeSliderDto hs_dto : hs_list) {
								%>
							<li data-target="#myCarousel" data-slide-to="<%=j %>" class="<% if(j==0){out.print("active");}%>"></li>
							<%j++;} %>
						</ul>

						<!-- Wrapper for slides -->
						<div class="carousel-inner">
							<%
								int i = 0;
								for (HomeSliderDto hs_dto : hs_list) {
								%>
							<div class="carousel-item my-carousel-item <% if(i==0){out.print("active");}%>">
								<img src="HomeSliderImage/<%=hs_dto.getId() %>.jpg" alt="Slider Image 1" class="img-fluid">
								<div class="carousel-caption">
									<h1><%=hs_dto.getTitle() %></h1>
									<a href="<%=hs_dto.getLink() %>" class="btn btn-primary">Shop Now!</a>
								</div>
							</div>
							<%i++;} %>
						</div>

						<!-- Left and right controls -->
						<a class="carousel-control-prev" href="#myCarousel" data-slide="prev">
							<span class="carousel-control-prev-icon"></span>
						</a>
						<a class="carousel-control-next" href="#myCarousel" data-slide="next">
							<span class="carousel-control-next-icon"></span>
						</a>
					</div>
				</section>
			</div>


			
			 </div>
			 
		</div>

	</section>
<!-- Start Our Categories  -->
<section class="shop-blog section">
	<div class="container">
		<div class="row">
			<div class="col-12">
				<div class="section-title">
					<h2>Our Categories</h2>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-3 col-md-4 col-6">
				<!-- Start Single Blog  -->
				<div class="shop-single-blog">
					<img  src="images/newSell/fashion.png" alt="#" style="aspect-ratio: 7/7;border: 2px solid #f7941d;border-radius: 2px;"
					onmouseover="this.style.transform='scale(1.2)'"
						 onmouseout="this.style.transform='scale(1)'">
					<div class="content">
						<p class="date">Fashion</p>
						<a href="#" class="btn" style="border-radius: 30px;">Show All</a>
					</div>
				</div>
				<!-- End Single Blog  -->
			</div>
			<div class="col-lg-3 col-md-4 col-6">
				<!-- Start Single Blog  -->
				<div class="shop-single-blog">
					<img src="images/newSell/smart_phone.png" alt="#" style="border: 2px solid #f7941d;border-radius: 2px; aspect-ratio: 7/7;"
					onmouseover="this.style.transform='scale(1.2)'"
						 onmouseout="this.style.transform='scale(1)'" >
					<div class="content">
						<p class="date">Smart Phone</p>
						<a href="#" class="btn" style="border-radius: 30px;">Show All</a>
					</div>
				</div>
				<!-- End Single Blog  -->
			</div>
			<div class="col-lg-3 col-md-4 col-6">
				<!-- Start Single Blog  -->
				<div class="shop-single-blog">
					<img src="images/newSell/accessories_automobile.PNG" alt="#" 
					style="border: 2px solid #f7941d;border-radius: 2px;aspect-ratio: 7/7;"
						 onmouseover="this.style.transform='scale(1.2)'"
						 onmouseout="this.style.transform='scale(1)'">
					<div class="content">
						<p class="date">Automobile Accessories</p>
						<a href="#" class="btn" style="border-radius: 30px;">Show All</a>
					</div>
				</div>
				
				<!-- End Single Blog  -->
			</div>
			<div class="col-lg-3 col-md-4 col-6">
				<!-- Start Single Blog  -->
				<div class="shop-single-blog">
					<img src="images/newSell/home_appliances.png" alt="#" style="border: 2px solid #f7941d;border-radius: 2px;aspect-ratio: 7/7;"
					onmouseover="this.style.transform='scale(1.2)'"
						 onmouseout="this.style.transform='scale(1)'" 
						 
						 >
					<div class="content">
						<p class="date">Home Appliances</p>
						<!-- <a href="#" class="title">House Making Charanpaduka</a> -->
						<a href="#" class="btn" style="border-radius: 30px;">Show All</a>
					</div>
				</div>
				<!-- End Single Blog  -->
			</div>
		</div>
	</div>
</section>
<!-- End Our Categories  -->


	<!-- Start Most Popular -->
	<div class="product-area most-popular section">
		<div class="container">
			<div class="row">
				<div class="col-12">
					<div class="section-title">
						<h2>Offer Product</h2>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-12">
					<div class="owl-carousel popular-slider">
						<!-- Start Single Product -->
						<%
								ProductService p_service = new ProductService();
								ArrayList<ProductDto> op_list = p_service.getOfferProductInfo(config, request);
								for (ProductDto op_dto : op_list) {
									
									ProductImgDto oop_dto = p_service.getOneProductImgByProductId(op_dto.getId(), config, request);
								%>
								
						<div class="single-product">
							<div class="product-img">
								<a href="product-details.jsp">
									<img class="default-img" src="ProductImage/<%=oop_dto.getId() %>.jpg" alt="#">
									<img class="hover-img" src="ProductImage/<%=oop_dto.getId() %>.jpg" alt="#">
									<!-- <span class="out-of-stock">Hot</span> -->
								</a>
								<div class="button-head">
									<div class="product-action">
										<a data-toggle="modal" data-target="#exampleModal" title="Quick View"
											href="#"><i class=" ti-eye"></i><span>Quick View</span></a>

									</div>
									<div class="product-action-2">
										<a title="Add to cart" href="cart.jsp">Add to cart</a>
									</div>
								</div>
							</div>
							<div class="product-content">
								<h3><a href="product-details.jsp"><%=op_dto.getTitle() %></a></h3>
								<div class="product-price">
									<span>&#8377;<%=op_dto.getPrice() %></span>
								</div>
							</div>
						</div>
						<%} %>
						<!-- End Single Product -->
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- End Most Popular Area -->
	
	
	<!-- Start Letest Item -->
	<div class="product-area section">
		<div class="container">
			<div class="row">
				<div class="col-12">
					<div class="section-title">
						<h2>Special Item</h2>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-12">

						<div class="tab-content" id="myTabContent">
							<!-- Start Single Tab -->
							<div class="tab-pane fade show active" id="man" role="tabpanel">
								<div class="tab-single">
									<div class="row">
									
								<%
								ArrayList<ProductDto> sp_list = p_service.getSpecialProductInfo(config, request);
								for (ProductDto sp_dto : sp_list) {
									
									ProductImgDto osp_dto = p_service.getOneProductImgByProductId(sp_dto.getId(), config, request);
								%>
										   
										<div class="col-xl-3 col-lg-4 col-md-4 col-12">
											<div class="single-product">
												<div class="product-img">
													<a href="product-details.jsp">
														<img class="default-img"
															src="ProductImage/<%=osp_dto.getId() %>.jpg" alt="#">
														<img class="hover-img" src="ProductImage/<%=osp_dto.getId() %>.jpg"
															alt="#" style="aspect-ratio: 7/7;border: 2px solid #f7941d;border-radius: 2px;">
														<!-- <span class="new">New</span> -->
													</a>
													<div class="button-head">
														<div class="product-action">
															<a data-toggle="modal" data-target="#exampleModal"
																title="Quick View" href="#"><i
																	class=" ti-eye"></i><span>Quick View</span></a>

														</div>
														<div class="product-action-2">
															<a title="Add to cart" href="cart.jsp">Add to cart</a>
														</div>
													</div>
												</div>
												<div class="product-content">
													<h3><a href="product-details.jsp"><%=sp_dto.getTitle() %></a></h3>
													<div class="product-price">
														<span>&#8377;<%=sp_dto.getPrice() %></span>
													</div>
												</div>
											</div>
										</div>
										<%} %>
										   
									</div>
								</div>
							</div>
							<!--/ End Single Tab -->
						</div>
					</div>
				</div>
			</div>
		</div>

	<!-- End Product Area -->

	<!-- Start Most Popular -->
	<div class="product-area most-popular section">
		<div class="container">
			<div class="row">
				<div class="col-12">
					<div class="section-title">
						<h2>Latest Item</h2>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-12">
					<div class="owl-carousel popular-slider">
						<!-- Start Single Product -->
						<%
								ArrayList<ProductDto> lp_list = p_service.getLatestProductInfo(config, request);
								for (ProductDto lp_dto : lp_list) {
									
									ProductImgDto olp_dto = p_service.getOneProductImgByProductId(lp_dto.getId(), config, request);
								%>
								
						<div class="single-product">
							<div class="product-img">
								<a href="product-details.jsp">
									<img class="default-img" src="ProductImage/<%=olp_dto.getId() %>.jpg" alt="#">
									<img class="hover-img" src="ProductImage/<%=olp_dto.getId() %>.jpg" alt="#">
									<!-- <span class="out-of-stock">Hot</span> -->
								</a>
								<div class="button-head">
									<div class="product-action">
										<a data-toggle="modal" data-target="#exampleModal" title="Quick View"
											href="#"><i class=" ti-eye"></i><span>Quick View</span></a>

									</div>
									<div class="product-action-2">
										<a title="Add to cart" href="cart.jsp">Add to cart</a>
									</div>
								</div>
							</div>
							<div class="product-content">
								<h3><a href="product-details.jsp"><%=lp_dto.getTitle() %></a></h3>
								<div class="product-price">
									<span>&#8377;<%=lp_dto.getPrice() %></span>
								</div>
							</div>
						</div>
						<%} %>
						<!-- End Single Product -->
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- End Most Popular Area -->

	<!-- End Shop Newsletter -->

	<!-- Modal -->
	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span class="ti-close"
							aria-hidden="true"></span></button>
				</div>
				<div class="modal-body">
					<div class="row no-gutters">
						<div class="col-lg-6 col-md-12 col-sm-12 col-xs-12">
							<!-- Product Slider -->
							<div class="product-gallery">
								<div class="quickview-slider-active">
									<div class="single-slider">
										<img src="images/sell/watch.png" alt="#">
									</div>
									<div class="single-slider">
										<img src="images/sell/poco.png" alt="#">
									</div>
									<div class="single-slider">
										<img src="images/sell/watch.png" alt="#">
									</div>
									<div class="single-slider">
										<img src="images/sell/poco.png" alt="#">
									</div>
								</div>
							</div>
							<!-- End Product slider -->
						</div>
						<div class="col-lg-6 col-md-12 col-sm-12 col-xs-12">
							<div class="quickview-content">
								<h2>Best Sell</h2>
								<div class="quickview-ratting-review">
									<div class="quickview-ratting-wrap">
										<div class="quickview-ratting">
											<i class="yellow fa fa-star"></i>
											<i class="yellow fa fa-star"></i>
											<i class="yellow fa fa-star"></i>
											<i class="yellow fa fa-star"></i>
											<i class="fa fa-star"></i>
										</div>
										<a href="#"> (1 customer review)</a>
									</div>
									<div class="quickview-stock">
										<span><i class="fa fa-check-circle-o"></i> in stock</span>
									</div>
								</div>
								
								<h3>  &#8377;29.00</h3>
								<div class="quickview-peragraph">
									<p>This is Product description and paragraph this place to writen a description for product</p>
								</div>
								<div class="size">
									<div class="row">
										<div class="col-lg-6 col-12">
											<h5 class="title">Size</h5>
											<select>
												<option selected="selected">s</option>
												<option>m</option>
												<option>l</option>
												<option>xl</option>
											</select>
										</div>
										<div class="col-lg-6 col-12">
											<h5 class="title">Color</h5>
											<select>
												<option selected="selected">orange</option>
												<option>purple</option>
												<option>black</option>
												<option>pink</option>
											</select>
										</div>
									</div>
								</div>
								<div class="quantity">
									<!-- Input Order -->
									<div class="input-group">
										<div class="button minus">
											<button type="button" class="btn btn-primary btn-number" disabled="disabled"
												data-type="minus" data-field="quant[1]">
												<i class="ti-minus"></i>
											</button>
										</div>
										<input type="text" name="quant[1]" class="input-number" data-min="1"
											data-max="1000" value="1">
										<div class="button plus">
											<button type="button" class="btn btn-primary btn-number" data-type="plus"
												data-field="quant[1]">
												<i class="ti-plus"></i>
											</button>
										</div>
									</div>
									<!--/ End Input Order -->
								</div>
								<div class="add-to-cart">
									<a href="cart.jsp" class="btn">Add to cart</a>
									<!-- <a href="#" class="btn min"><i class="ti-heart"></i></a> -->
									<!-- <a href="#" class="btn min"><i class="fa fa-compress"></i></a> -->
								</div>
								<div class="default-social">
									<!-- <h4 class="share-now">Share:</h4> -->
									<ul>
										<li><a class="facebook" href="#"><i class="fa fa-facebook"></i></a></li>
										 
										 <li><a class="facebook" href="#"><i class="fa fa-instagram"></i></a></li>
										<li><a class="dribbble" href="#"><i class="fa fa-google-plus"></i></a></li>
									</ul>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal end -->

	<!-- Start Footer Area -->

	<!-- ======= Header ======= -->
	<%@include file="include/footer.jsp"%>
	<!-- ======= Header end======= -->

