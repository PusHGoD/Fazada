<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"
	type="text/javascript"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	type="text/javascript"></script>
<link href="<c:url value='/Resources/css/main.css' />" rel="stylesheet" />
<link href="<c:url value='/Resources/css/product.css' />"
	rel="stylesheet" />
<script type="text/javascript"
	src='<c:url value="/Resources/js/custom.js"/>'></script>
<link href="<c:url value='/Resources/css/full-slider.css' />"
	rel="stylesheet" />
<link
	href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css"
	rel="stylesheet">
<title>Product</title>

</head>
<body id="product-page">
	<div class="center-block">
		<a href="/fazada/main"><img
			src="<c:url value='/Resources/pic/'/>FazadaGroupLogo.jpg"
			style="width: 300px; padding-left: 60px" /></a>
	</div>
	<div id="wrapper">
		<div class="overlay"></div>
		<!-- Sidebar -->
		<nav class="navbar navbar-inverse navbar-fixed-top"
			id="sidebar-wrapper" role="navigation">
		<ul class="nav sidebar-nav">
			<c:if test="${empty accountInfo}">
				<li><span data-toggle="modal" data-target="#myModal"
					style="font-weight: bold"> <a href="#login"
						data-toggle="tab">Đăng nhập</a><a href="#signup" data-toggle="tab">Đăng
							ký</a>
				</span></li>
			</c:if>
			<c:if test="${not empty accountInfo}">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" style="font-size: 18px"><img
						src="<c:url value='/Resources/pic/'/>user.png" height="30px"
						width="30px" /><span> ${accountInfo}</span><span class="caret"></span></a>
					<ul class="dropdown-menu" role="menu">
						<li class="dropdown-header"></li>
						<c:if test="${role eq 'admin'}">
							<li><a href="/fazada/account/info"><small>Quản
										lý tài khoản</small></a></li>
							<li><a href="/fazada/manager"><small>Quản lý
										danh sách tài khoản</small></a></li>
						</c:if>
						<c:if test="${role eq 'staff'}">
							<li><a href="/fazada/account/info"><small>Quản
										lý tài khoản</small></a></li>
							<li><a href="/fazada/order"><small>Quản lý danh
										sách đơn hàng</small></a></li>
						</c:if>
						<c:if test="${role eq 'user'}">
							<li><a href="/fazada/account/info"><small>Quản
										lý tài khoản</small></a></li>
							<li><a href="/fazada/account/order"><small>Đơn
										hàng của tôi</small></a></li>
						</c:if>
						<li><a href="/fazada/logout"><small>Đăng xuất</small></a></li>
					</ul></li>
			</c:if>
			<li>
				<div class="navbar-text">
					<input class="form-control" type="text"
						placeholder="&#xF002; Search"
						style="font-family: Arial, FontAwesome" />
				</div>
			</li>
			<li class="dropdown"><a href="/fazada/product">Sản phẩm</a></li>
			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown">Thực phẩm <span class="caret"></span></a>
				<ul class="dropdown-menu" role="menu">
					<li class="dropdown-header"></li>
					<li><a href="#">Mì gói</a></li>
					<li><a href="#">Đồ hộp</a></li>
					<li><a href="#">Thức uống</a></li>
					<li><a href="#">Phụ gia</a></li>
				</ul></li>
			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown">Điện tử <span class="caret"></span></a>
				<ul class="dropdown-menu" role="menu">
					<li class="dropdown-header"></li>
					<li><a href="#">Điện thoại</a></li>
					<li><a href="#">Máy tính bảng</a></li>
					<li><a href="#">Phụ kiện</a></li>
					<li><a href="#">Laptop</a></li>
				</ul></li>
			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown">Thời trang nam <span class="caret"></span></a>
				<ul class="dropdown-menu" role="menu">
					<li class="dropdown-header"></li>
					<li><a href="#">Áo nam</a></li>
					<li><a href="#">Quần nam</a></li>
					<li><a href="#">Giày dép nam</a></li>
					<li><a href="#">Áo khoác nam</a></li>
				</ul></li>
			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown">Thời trang nữ <span class="caret"></span></a>
				<ul class="dropdown-menu" role="menu">
					<li class="dropdown-header"></li>
					<li><a href="#">Áo nữ</a></li>
					<li><a href="#">Quần nữ</a></li>
					<li><a href="#">Giày dép nữ</a></li>
					<li><a href="#">Áo khoác nữ</a></li>
				</ul></li>
			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown">Thể thao <span class="caret"></span></a>
				<ul class="dropdown-menu" role="menu">
					<li class="dropdown-header"></li>
					<li><a href="#">Bóng đá</a></li>
					<li><a href="#">Bóng rổ</a></li>
					<li><a href="#">Bóng bầu dục</a></li>
					<li><a href="#">Bóng bàn</a></li>
				</ul></li>
		</ul>
		</nav>
		<!-- /#sidebar-wrapper -->

		<!-- Page Content -->
		<div id="page-content-wrapper">
			<button type="button" class="hamburger is-closed"
				data-toggle="offcanvas">
				<span class="hamb-top"></span> <span class="hamb-middle"></span> <span
					class="hamb-bottom"></span>
			</button>
		</div>
		<!-- /#page-content-wrapper -->
	</div>
	<!-- /#wrapper -->
	<div style="flex: 1; margin-bottom: 20px; margin-top: 20px">
		<div class="container">
			<div class="accordian">
				<ul>
					<li>
						<div class="image_title">
							<a href="#">OG388.67AGR-GL &emsp; &emsp; &emsp; &emsp; &emsp;
								&emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp;
								&emsp; &emsp; 1.735.000vnd</a>
						</div> <a href="#"> <img
							src="<c:url value='/Resources/pic/product/'/>w1.jpg" />
					</a>
					</li>
					<li>
						<div class="image_title">
							<a href="#">OG358.621AMR-GL &emsp; &emsp; &emsp; &emsp;
								&emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp;
								&emsp; &emsp; &emsp; 1.735.000vnd</a>
						</div> <a href="#"> <img
							src="<c:url value='/Resources/pic/product/'/>w2.jpg" />
					</a>
					</li>
					<li>
						<div class="image_title">
							<a href="#">OG358.61AGR-GL &emsp; &emsp; &emsp; &emsp; &emsp;
								&emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp;
								&emsp; &emsp; 1.735.000vnd</a>
						</div> <a href="#"> <img
							src="<c:url value='/Resources/pic/product/'/>w3.jpg" />
					</a>
					</li>
					<li>
						<div class="image_title">
							<a href="#">OG388.63AGR-GL &emsp; &emsp; &emsp; &emsp; &emsp;
								&emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp;
								&emsp; &emsp; 1.735.000vnd</a>
						</div> <a href="#"> <img
							src="<c:url value='/Resources/pic/product/'/>w4.jpg" />
					</a>
					</li>
					<li>
						<div class="image_title">
							<a href="#">OG388.61AGR-GL &emsp; &emsp; &emsp; &emsp; &emsp;
								&emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp;
								&emsp; &emsp; 1.735.000vnd</a>
						</div> <a href="#"> <img
							src="<c:url value='/Resources/pic/product/'/>w5.jpg" />
					</a>
					</li>
				</ul>
			</div>

			<!-- ================LIST DEV================== -->
			<center>
				<img src="https://www.xwatch.vn/home/img/assets/SPbanchaynhat.png" />
			</center>
			<input type='hidden' id='current_page' /> <input type='hidden'
				id='show_per_page' />
			<div id="content" class="container"></div>
			<center>
				<div id="page_navigation"></div>
			</center>
		</div>
	</div>
	<div class="footer footer-container">
		<a href='#'><i class="fa fa-twitch fa-3x fa-fw"></i></a> <a href='#'><i
			class="fa fa-facebook fa-3x fa-fw"></i></a> <a href='#'><i
			class="fa fa-twitter fa-3x fa-fw"></i></a> <a href='#'><i
			class="fa fa-youtube-play fa-3x fa-fw"></i></a> <a href='#'><i
			class="fa fa-rss fa-3x fa-fw"></i></a> <a href='#'><i
			class="fa fa-vine fa-3x fa-fw"></i></a> <a href='#'><i
			class="fa fa-flickr fa-3x fa-fw"></i></a> <a href='#'><i
			class="fa fa-linkedin fa-3x fa-fw"></i></a> </span>
	</div>
	<!-- Login Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-body">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<ul class="nav nav-tabs">
						<li class="active"><a data-toggle="tab" href="#login">Login</a></li>
						<li><a data-toggle="tab" href="#signup">Sign up</a></li>
					</ul>
					<div id="ajaxMessage"></div>
					<!-- User name -->
					<div class="tab-content">
						<div id="login"
							class="tab-pane fade clearfix in active well well-sm well-white">
							<form id="loginForm" action="login" method="POST">
								<!-- User name -->
								<div class="form-group">
									Username: <input type="text" class="form-control"
										placeholder="Username" id="username" name="userName" />
									<div id="username_error" class="text-danger"></div>
								</div>
								<!-- Password -->
								<div class="form-group">
									Password: <input type="password" class="form-control"
										placeholder="Password" id="password" name="password" />
									<div id="password_error" class="text-danger"></div>
								</div>
								<!-- Login button -->
								<div class="pull-right">
									<a data-toggle="tab" href="#forget">Forgot your password?</a> <span>
										or </span>
									<button type="button" id="login-btn" class="btn btn-primary"
										style="width: 100px">Sign in</button>
								</div>
							</form>
						</div>
						<div id="signup"
							class="tab-pane clearfix fade well well-sm well-white">
							<form id="signupForm" action="signup">
								<!-- User name -->
								<div class="form-group">
									User name: <input type="text" class="form-control" value=""
										placeholder="User name" id="userName" name="userName" />
									<div id="username_error" class="text-danger"></div>
								</div>
								<div class="form-group">
									Password: <input type="password" class="form-control" value=""
										placeholder="Password" id="password" name="password" />
									<div id="password_error" class="text-danger"></div>
								</div>
								<div class="form-group">
									Re-type password: <input type="password" class="form-control"
										value="" placeholder="Confirm password" id="confirm" />
									<div id="password_confirm_error" class="text-danger"></div>
								</div>
								<!-- First name -->
								<div class="form-group">
									First name: <input type="text" class="form-control" value=""
										placeholder="First name" id="firstName" name="firstName" />
									<div id="firstname_error" class="text-danger"></div>
								</div>
								<!-- Last name -->
								<div class="form-group">
									Last name: <input type="text" class="form-control" value=""
										placeholder="Last name" id="lastName" name="lastName" />
									<div id="lastname_error" class="text-danger"></div>
								</div>
								<!-- Date of birth -->
								<div class="form-group">
									Date of birth:
									<!-- Date picker -->
									<input type="text" data-provide="datepicker"
										class="form-control" data-date-format="dd/mm/yyyy" value=""
										placeholder="dd/mm/yyyy" id="dateOfBirth" name="dateOfBirth"
										autocomplete='off' />
									<div id="dob_error" class="text-danger"></div>
								</div>
								<!-- Email -->
								<div class="form-group">
									Email: <input type="text" class="form-control" value=""
										placeholder="Email" id="email" name="email" />
									<div id="email_error" class="text-danger"></div>
								</div>
								<!-- Modal footer -->
								<div class="pull-right">
									<button type="button" class="btn btn-link" data-dismiss="modal">Cancel</button>
									<button type="button" class="btn btn-primary" id="signup-btn">Sign
										up</button>
								</div>
							</form>
						</div>
						<div id="forget"
							class="tab-pane clearfix fade well well-sm well-white">
							<form action="signup" id="forgetForm">
								<!-- User name -->
								<div class="form-group">
									Your Email Address: <input type="text" class="form-control"
										placeholder="Email" id="email" name="email" />
									<div id="email_error" class="text-danger"></div>
								</div>
								<!-- Password -->
								<div class="pull-right">
									<a href="#login" class="btn btn-link" data-toggle="tab">Back
										to login</a>
									<button type="button" id="forget-btn" class="btn btn-primary">Get
										password</button>
								</div>
							</form>
						</div>
						<div id="success" class="tab-pane fade">
							<div id="message"></div>
							<button type="button" class="btn btn-default" a data-toggle="tab"
								href="#login" data-dismiss="modal">Back to login</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>