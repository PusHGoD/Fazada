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
	rel="stylesheet" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.0/extensions/toolbar/bootstrap-table-toolbar.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.0/extensions/flat-json/bootstrap-table-flat-json.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.0/extensions/filter-control/bootstrap-table-filter-control.js">
	
</script>
<!-- Custom javascript file (mostly validation) -->
<script type="text/javascript"
	src='<c:url value="/Resources/js/custom.js"/>'></script>
<title>My Order</title>
</head>
<body id="my-order-page">
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
			<c:if test="${empty accountInfo or empty role or role ne 'user'}">
				<c:redirect url="/main"></c:redirect>
			</c:if>
			<c:if test="${not empty accountInfo}">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" style="font-size: 18px"><img
						src="<c:url value='/Resources/pic/'/>user.png" height="30px"
						width="30px" /><span id="session_userName"> ${accountInfo}</span><span
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

			<li class="dropdown"><a href="/fazada/product">Sản phẩm<span class="caret"></span></a></li>
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
			<h3>
				<strong>Đơn hàng của tôi</strong>
			</h3>
			<div id="ajaxMessage"></div>
			<!-- Div conatining information -->
			<div>
				<!-- Toolbar -->
				<div id="toolbar" class="btn-group">
					<span id="items">0</span> đơn hàng trong vòng <select
						id="my-order-select">
						<option value="All">Tất cả</option>
						<option value="In 15 days">15 ngày</option>
						<option value="In 30 days">30 ngày</option>
						<option value="In 3 months">3 tháng</option>
						<option value="In 6 months">6 tháng</option>
					</select>

				</div>
				<!-- JSON auto-generated table -->
				<table id="table" class="table" data-method="POST"
					data-show-toggle="true" data-toolbar="#toolbar"
					data-pagination="true" data-page-size="5"
					data-page-list="[5,10,20,50,100,200]" data-detail-view="true"
					data-detail-formatter="detailFormatter">
					<thead>
						<tr>
							<th data-field="orderId" data-sortable="true">Order No.</th>
							<th data-field="account.userName" data-sortable="true">Order
								user</th>
							<th data-field="dateTime" data-formatter="dateFormatter"
								data-sortable="true">Order date</th>
							<th data-field="total" data-formatter="priceFormatter"
								data-sortable="true">Total</th>
							<th data-field="orderstatus" data-formatter="statusFormatter"
								data-sortable="true">Status</th>
						</tr>
					</thead>
				</table>
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