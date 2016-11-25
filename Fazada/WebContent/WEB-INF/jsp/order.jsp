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
<!-- Bootstrap table -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.0/bootstrap-table.css" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.0/bootstrap-table.js"></script>
<link href="<c:url value='/Resources/css/main.css' />" rel="stylesheet" />
<link
	href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css"
	rel="stylesheet">
<!-- Custom javascript file (mostly validation) -->
<script type="text/javascript"
	src='<c:url value="/Resources/js/custom.js"/>'></script>
<title>Order Management</title>
</head>
<body id="order-management-page">
	<div>
		<a href="main"><img
			src="<c:url value='/Resources/pic/'/>FazadaGroupLogo.jpg"
			style="width: 300px; padding-left: 60px" /></a>
	</div>
	<div id="wrapper">
		<!-- Sidebar -->
		<div class="overlay"></div>
		<nav class="navbar navbar-inverse navbar-fixed-top"
			id="sidebar-wrapper" role="navigation">
		<ul class="nav sidebar-nav">
			<c:if test="${empty accountInfo or empty role or role ne 'staff'}">
				<c:redirect url="main"></c:redirect>
			</c:if>
			<c:if test="${not empty accountInfo}">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" style="font-size: 18px"><img
						src="<c:url value='/Resources/pic/'/>user.png" height="30px"
						width="30px" /><span> ${accountInfo}</span><span class="caret"></span></a>
					<ul class="dropdown-menu" role="menu">
						<li class="dropdown-header"></li>
						<c:if test="${role eq 'staff'}">
							<li><a href="#"><small>Quản lý đơn hàng</small></a></li>
						</c:if>
						<li><a href="logout"><small>Đăng xuất</small></a></li>
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

			<li><a href="#"><img
					src="<c:url value='/Resources/pic/'/>icon.png" height="30px"
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

	<!-- Container div -->
	<div style="flex: 1; margin-bottom: 10px">
		<!-- Employee list panel -->
		<div class="container">
			<h3>
				<strong>Order list</strong>
			</h3>
			<div id="ajaxMessage"></div>
			<!-- Div conatining information -->
			<div>
				<!-- Toolbar -->
				<div id="toolbar" class="btn-group">
					<span id="items">0</span> orders in <select id="order-select">
						<option>All</option>
						<option>In 15 days</option>
						<option>In 30 days</option>
						<option>In 3 months</option>
						<option>In 6 months</option>
					</select>
				</div>
				<!-- JSON auto-generated table -->
				<table id="table" class="table" data-method="POST"
					data-show-toggle="true" data-toolbar="#toolbar" data-search="true">
					<thead>
						<tr>
							<th data-field="orderId">ID</th>
							<th data-field="account.userName">Order user</th>
							<th data-field="dateTime" data-formatter="dateFormatter"
								data-sortable="true">Order date</th>
							<th data-field="total" data-formatter="priceFormatter">Total</th>
							<th data-field="orderstatus" data-formatter="statusFormatter">Status</th>
						</tr>
					</thead>
				</table>
			</div>
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
</body>
</html>