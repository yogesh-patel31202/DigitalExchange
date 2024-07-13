<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.mysql.jdbc.exceptions.DeadlockTimeoutRollbackMarker"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.dto.*"%>
<%@page import="com.service.*"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.NumberFormat"%>

<%@page import="java.io.File"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "https://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
 
 <head>
	<!-- Meta Tag -->
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name='copyright' content=''>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<!-- Title Tag  -->
	<title>Digital-Exchange</title>
	<!-- Favicon -->
	<link rel="icon" type="image/png" href="images/favicon.png">
	<!-- Web Font -->
	<link
		href="https://fonts.googleapis.com/css?family=Poppins:200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i&display=swap"
		rel="stylesheet">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<!-- StyleSheet -->

	<!-- Bootstrap -->
	<link rel="stylesheet" href="css/bootstrap.css">
	
	<!-- Magnific Popup -->
	<link rel="stylesheet" href="css/magnific-popup.min.css">
	<!-- Font Awesome -->
	<link rel="stylesheet" href="css/font-awesome.css">
	<!-- Fancybox -->
	<link rel="stylesheet" href="css/jquery.fancybox.min.css">
	<!-- Themify Icons -->
	<link rel="stylesheet" href="css/themify-icons.css">
	<!-- Nice Select CSS -->
	<link rel="stylesheet" href="css/niceselect.css">
	<!-- Animate CSS -->
	<link rel="stylesheet" href="css/animate.css">
	<!-- Flex Slider CSS -->
	<link rel="stylesheet" href="css/flex-slider.min.css">
	<!-- Owl Carousel -->
	<link rel="stylesheet" href="css/owl-carousel.css">
	<!-- Slicknav -->
	<link rel="stylesheet" href="css/slicknav.min.css">
	
	<link href="assets/vendor/bootstrap-icons/bootstrap-icons.css"	rel="stylesheet">

	<!-- Eshop StyleSheet -->
	<link rel="stylesheet" href="css/reset.css">
	<link rel="stylesheet" href="style.css">
	<link rel="stylesheet" href="css/responsive.css">
	<link rel="stylesheet" href="css/footer.css">
	 
	
	<!-- <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css"> -->
	 
	
	<style>
		/* Custom CSS for controlling image size */
		.carousel-item img {
			width: 100%;
			/* Adjust the width as needed */
			max-height: 500px;
			/* Adjust the maximum height as needed */
			object-fit: cover;
			/* Ensure the image covers the entire container */
		}

		.img-border-st{
		border: 2px solid #f7941d;
		border-radius: 10px;
		
		}
		
	</style>
	
	<link rel="stylesheet" type="text/css" href="assets/vendor/data table/css/jquery.dataTables.min.css">
	<link rel="stylesheet" type="text/css" href="assets/vendor/data table/css/buttons.dataTables.min.css">
	
	<%
	String current_date = LogFileService.changeFormateOfCurrentDate("yyyy-MM-dd");
	int admin_id = 1;
	%>

</head>

<body class="js">

	<!-- Preloader -->
	<div class="preloader">
		<div class="preloader-inner">
			<div class="preloader-icon">
				<span></span>
				<span></span>
			</div>
		</div>
	</div>
	<!-- End Preloader -->


	<!-- Header -->
	<header class="header shop">
		<!-- Topbar -->
		<div class="topbar">
			<div class="container">
				<div class="row">
					<div class="col-lg-4 col-md-12 col-12">
						<!-- Top Left -->
						<div class="top-left">
							<ul class="list-main">
								<li><i class="ti-headphone-alt"></i> +91 XXXXXXX00</li>
								<li><i class="ti-email"></i>dlx000@gmail.com</li>
							</ul>
						</div>
						<!--/ End Top Left -->
					</div>
					<div class="col-lg-8 col-md-12 col-12">
						<!-- Top Right -->
						<div class="right-content">
							<ul class="list-main">
								<li><i class="ti-user"></i> <a href="reg.jsp">My account</a></li>
								<li><i class="ti-power-off"></i><a href="login.jsp">Login</a></li>
							</ul>
						</div>
						<!-- End Top Right -->
					</div>
				</div>
			</div>
		</div>
		<!-- End Topbar -->
		<div class="middle-inner">
			<div class="container">
				<div class="row">
					<div class="col-lg-2 col-md-2 col-12">
						<!-- Logo -->
						<div class="logo mb-2" style="width: 300px;">
							<a href="index.jsp"
								style=" font-size: 30px; font-style: normal; font-weight: 500;">Digital Exchange</a>
						</div>
						<!--/ End Logo -->
						<!-- Search Form -->
						<div class="search-top">
							<div class="top-search"><a href="#0"><i class="ti-search"></i></a></div>
							<!-- Search Form -->
							<div class="search-top">
								<form class="search-form">
									<input type="text" placeholder="Search here..." name="search">
									<button value="search" type="submit"><i class="ti-search"></i></button>
								</form>
							</div>
							<!--/ End Search Form -->
						</div>
						<!--/ End Search Form -->
						<div class="mobile-nav"></div>
					</div>
				</div>
			</div>
		</div>
		<!-- Header Inner -->
		<div class="header-inner">
			<div class="container">
				<div class="cat-nav-head">
					<div class="row">
						
						<div class="col-lg-9 col-12">
							<div class="menu-area">
								<!-- Main Menu -->
								<nav class="navbar navbar-expand-lg">
									<div class="navbar-collapse">
										<div class="nav-inner">
											<ul class="nav main-menu menu navbar-nav">
												<li class="active"><a href="index_admin.jsp">Home</a></li>
												<li><a href="manage_product.jsp">Product</a></li>
												<li><a href="manage_user.jsp">Users</a></li>
											 
												<li><a href="#">Report<i class="ti-angle-down"></i>
												<!-- <span class="new">New</span> --></a>
													<ul class="dropdown">
														<li><a href="manage_home_slider.jsp">Slider</a></li>
														<li><a href="manage_not_approve_product.jsp">Pending Product</a></li>
														<li><a href="manage_approve_product.jsp">Approved Product</a></li>
														<li><a href="manage_latest_product.jsp">Latest Product</a></li>
														<li><a href="manage_special_product.jsp">Special Product</a></li>
														<li><a href="manage_offer_product.jsp">Offer Product</a></li>
														<li><a href="manage_item_category.jsp">Product Category</a></li>
														<li><a href="manage_admin.jsp">Admin</a></li>
													</ul>
												</li>
												
												<li><a href="#">Add<i class="ti-angle-down"></i>
												<!-- <span class="new">New</span> --></a>
													<ul class="dropdown">
														<li><a href="add_home_slider.jsp">Slider</a></li>
														<li><a href="add_item_category.jsp">Product Category</a></li>
														<li><a href="add_admin.jsp">Admin</a></li>
													</ul>
												</li>
												
												<li><a href="index.jsp">Digital Exchange Portal</a></li>
											</ul>
										</div>
									</div>
								</nav>
								<!--/ End Main Menu -->
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!--/ End Header Inner -->
	</header>
	<!--/ End Header -->
 