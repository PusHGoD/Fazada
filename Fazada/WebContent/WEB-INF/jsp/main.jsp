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
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	type="text/javascript"></script>
<!-- Datepicker (CSS and JS) -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/css/bootstrap-datepicker3.min.css" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/js/bootstrap-datepicker.min.js"></script>
<link href="<c:url value='/Resources/css/main.css' />" rel="stylesheet" />
<link href="<c:url value='/Resources/css/full-slider.css' />"
	rel="stylesheet" />
<link
	href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css"
	rel="stylesheet">
<script type="text/javascript"
	src='<c:url value="/Resources/js/custom.js"/>'></script>
<title>Fazada</title>
</head>
<body>
	<div id="wrapper">
		<div class="overlay"></div>
		<!-- Sidebar -->
		<nav class="navbar navbar-inverse navbar-fixed-top"
			id="sidebar-wrapper" role="navigation">
		<ul class="nav sidebar-nav">
			<li><a href="#" style="font-size: 16px" data-toggle="modal"
				data-target="#myModal">Đăng nhập / Đăng ký</a></li>
			<li>
				<form class="navbar-form navbar-left">
					<div class="form-group">
						<input class="form-control" type="text"
							placeholder="&#xF002; Search"
							style="font-family: Arial, FontAwesome" />
					</div>
				</form>
			</li>
			<li><a href="#"><img
					src="<c:url value='Resources/pic/'/>icon.png" height="30px"
					width="30px" /></a></li>
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
	<div class="content">
		<!-- Full Page Image Background Carousel Header -->
		<div id="myCarousel" class="carousel slide">
			<!-- Indicators -->
			<ol class="carousel-indicators">
				<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
				<li data-target="#myCarousel" data-slide-to="1"></li>
				<li data-target="#myCarousel" data-slide-to="2"></li>
				<li data-target="#myCarousel" data-slide-to="3"></li>
			</ol>

			<!-- Wrapper for Slides -->
			<div class="carousel-inner">
				<div class="item active">
					<!-- Set the first background image using inline CSS below. -->
					<div class="fill"
						style="background-image: url('<c:url value="Resources/pic/"/>monkey.jpg');"></div>
					<div class="carousel-caption">
						<h2>UY TÍN HÀNG ĐẦU</h2>
					</div>
				</div>
				<div class="item">
					<!-- Set the second background image using inline CSS below. -->
					<div class="fill"
						style="background-image: url('<c:url value="Resources/pic/"/>elec.jpg');"></div>
					<div class="carousel-caption">
						<h2>SẢN PHẨM ĐA DẠNG</h2>
					</div>
				</div>
				<div class="item">
					<!-- Set the third background image using inline CSS below. -->
					<div class="fill"
						style="background-image: url('<c:url value="Resources/pic/"/>save.jpg');"></div>
					<div class="carousel-caption">
						<h2>GIÁ CẢ TỐT NHẤT</h2>
					</div>
				</div>
				<div class="item">
					<!-- Set the third background image using inline CSS below. -->
					<div class="fill"
						style="background-image: url('<c:url value="Resources/pic/"/>delivery.jpg');"></div>
					<div class="carousel-caption">
						<h2>VẬN CHUYỂN SIÊU TỐC</h2>
					</div>
				</div>
			</div>

			<!-- Controls -->
			<a class="left carousel-control" href="#myCarousel" data-slide="prev">
				<span class="icon-prev"></span>
			</a> <a class="right carousel-control" href="#myCarousel"
				data-slide="next"> <span class="icon-next"></span>
			</a>

		</div>
	</div>
	<div class="footer">
		<div class="footer-container">
			<a href='#'><i class="fa fa-twitch fa-3x fa-fw"></i></a> <a href='#'><i
				class="fa fa-facebook fa-3x fa-fw"></i></a> <a href='#'><i
				class="fa fa-twitter fa-3x fa-fw"></i></a> <a href='#'><i
				class="fa fa-youtube-play fa-3x fa-fw"></i></a> <a href='#'><i
				class="fa fa-rss fa-3x fa-fw"></i></a> <a href='#'><i
				class="fa fa-vine fa-3x fa-fw"></i></a> <a href='#'><i
				class="fa fa-flickr fa-3x fa-fw"></i></a> <a href='#'><i
				class="fa fa-linkedin fa-3x fa-fw"></i></a>
		</div>
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
					<!-- User name -->
					<div class="tab-content">
						<div id="login" class="tab-pane fade in active">
							<form id="loginForm"
								action="http://localhost:8080/fazadaws/login" method="POST">
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
								<div class="modal-footer">
									<a data-toggle="tab" href="#forget">Forgot your password? </a>
									<button type="button" id="login-btn" class="btn btn-primary"
										style="width: 100px">Sign in</button>
								</div>
							</form>
						</div>
						<div id="signup" class="tab-pane fade">
							<form id="signupForm" action="add.htm">
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
										placeholder="dd/mm/yyyy" id="dateOfBirth" name="dateOfBirth" />
									<div id="dob_error" class="text-danger"></div>
								</div>
								<!-- Email -->
								<div class="form-group">
									Email: <input type="text" class="form-control" value=""
										placeholder="Email" id="email" name="email" />
									<div id="email_error" class="text-danger"></div>
								</div>
								<!-- Modal footer -->
								<div class="modal-footer">
									<button type="button" class="btn btn-link" data-dismiss="modal">Cancel</button>
									<button type="button" class="btn btn-primary" id="signup-btn">Sign
										up</button>
								</div>
							</form>
						</div>
						<div id="forget" class="tab-pane fade">
							<form action="signup" id="forgetForm">
								<!-- User name -->
								<div class="form-group">
									Your Email Address: <input type="text" class="form-control"
										placeholder="Email" id="email" name="email" />
									<div id="email_error" class="text-danger"></div>
								</div>
								<!-- Password -->

								<div class="modal-footer">
									<button type="button" class="btn btn-default" a
										data-toggle="tab" href="#login">Back to login</button>
									<button type="button" id="forget-btn" class="btn btn-primary">Get
										password</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>