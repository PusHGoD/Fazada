<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link
	href="//cdnjs.cloudflare.com/ajax/libs/x-editable/1.5.1/bootstrap3-editable/css/bootstrap-editable.css"
	rel="stylesheet" />
<script
	src="//cdnjs.cloudflare.com/ajax/libs/x-editable/1.5.1/bootstrap3-editable/js/bootstrap-editable.js"></script>
<link href="<c:url value='/Resources/css/main.css'/>" rel="stylesheet" />
<link
	href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css"
	rel="stylesheet" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.17.0/moment.js"></script>
<!-- Custom javascript file (mostly validation) -->
<script type="text/javascript"
	src='<c:url value="/Resources/js/custom.js"/>'></script>
<script type="text/javascript"
	src='<c:url value="/Resources/js/editable.js"/>'></script>
<title>Account Info</title>
</head>
<body id="account-info-page">
	<div class="center-block">
		<a href="/fazada/main"><img
			src="<c:url value='/Resources/pic/'/>FazadaGroupLogo.jpg"
			style="width: 300px; padding-left: 60px" /></a>
	</div>
	<div id="wrapper">
		<!-- Sidebar -->
		<div class="overlay" style="z-index: 3"></div>
		<nav class="navbar navbar-inverse navbar-fixed-top"
			id="sidebar-wrapper" role="navigation">
		<ul class="nav sidebar-nav">
			<c:if test="${empty accountInfo or empty role}">
				<c:redirect url="/main"></c:redirect>
			</c:if>
			<c:if test="${not empty accountInfo}">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" style="font-size: 18px"><img
						src="<c:url value='/Resources/pic/'/>user.png" height="30px"
						width="30px" /><span id="accountInfo">${accountInfo}</span><span
						class="caret"></span></a>
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
				<form class="navbar-form navbar-left">
					<div class="form-group">
						<input class="form-control" type="text"
							placeholder="&#xF002; Search"
							style="font-family: Arial, FontAwesome" />
					</div>
				</form>
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

	<!-- Container div -->
	<div style="flex: 1; margin-bottom: 20px; margin-top: 20px">
		<!-- Employee list panel -->
		<div class="container">
			<div class="col-md-3 col-lg-3">
				<ul class="nav nav-pills nav-stacked" data-spy="affix"
					data-offset-top="205">
					<li><div id="submenu" class="list-group">
							<a href="#editInfo" data-toggle="tab"
								class="list-group-item submenu active">Chỉnh sửa thông tin</a> <a
								href="#updatePassword" data-toggle="tab"
								class="list-group-item submenu">Đổi mật khẩu</a>
						</div></li>
				</ul>
			</div>
			<div class="col-md-9 col-lg-9 well well-white tab-content">
				<div id="ajaxMessage"></div>
				<div id="editInfo" class="tab-pane fade clearfix in active">
					<h3>
						<strong>Account Info</strong>
					</h3>
					<br />
					<!-- Div conatining information -->
					<div id="infoDiv">
						<div class="form-group">
							<label>ID:</label> <span id="id"></span>
						</div>
						<div class="form-group">
							<label>User name:</label> <span id="userName"></span>
						</div>
						<div class="form-group">
							<label>First name:</label> <a href="#" id="firstName"
								data-pk="${accountInfo}" data-type="text" data-value=""></a>
						</div>
						<div class="form-group">
							<label>Last name:</label> <a href="#" id="lastName"
								data-pk="${accountInfo}" data-type="text" data-value=""></a>
						</div>
						<div class="form-group">
							<label>Date of birth:</label> <a href="#" id="dateOfBirth"
								data-pk="${accountInfo}" data-type="combodate" data-value=""
								data-format="YYYY-MM-DD" data-viewformat="DD/MM/YYYY"
								data-template="D / MMMM / YYYY"></a>
						</div>
						<div class="form-group">
							<label>Email:</label> <a href="#" id="email"
								data-pk="${accountInfo}" data-type="text" data-value=""></a>
						</div>
					</div>
				</div>
				<div id="updatePassword" class="tab-pane fade clearfix">
					<h3>
						<strong>Change password</strong>
					</h3>
					<br />
					<form id="changePassForm">
						<input type="hidden" id="userName" name="userName"
							value="${accountInfo}" />
						<div class="form-group">
							Old Password: <input type="password" class="form-control"
								value="" placeholder="Password" id="old_password"
								name="oldPassword" />
							<div id="old_password_error" class="text-danger"></div>
						</div>
						<div class="form-group">
							New Password: <input type="password" class="form-control"
								value="" placeholder="Password" id="new_password"
								name="password" />
							<div id="new_password_error" class="text-danger"></div>
						</div>
						<div class="form-group">
							Confirm password: <input type="password" class="form-control"
								value="" placeholder="Confirm password" id="confirm" />
							<div id="password_confirm_error" class="text-danger"></div>
						</div>
						<div class="form-group pull-right">
							<button type="button" id="password-change-btn"
								class="btn btn-primary">Update password</button>
						</div>
					</form>
				</div>
			</div>
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
			class="fa fa-linkedin fa-3x fa-fw"></i></a>
	</div>
</body>
</html>