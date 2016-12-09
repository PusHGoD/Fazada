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
<!-- Datepicker (CSS and JS) -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/css/bootstrap-datepicker3.min.css" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/js/bootstrap-datepicker.min.js"></script>
<!-- Bootstrap table -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.0/bootstrap-table.css" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.0/bootstrap-table.js"></script>
<link href="<c:url value='/Resources/css/main.css' />" rel="stylesheet" />
<link
	href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css"
	rel="stylesheet"/>
<!-- Custom javascript file (mostly validation) -->
<script type="text/javascript"
	src='<c:url value="/Resources/js/custom.js"/>'></script>
<title>User Management</title>
</head>
<body id="user-management-page">
	<div class="center-block">
		<a href="main"><img
			src="<c:url value='/Resources/pic/'/>FazadaGroupLogo.jpg"
			style="width: 300px; padding-left: 60px" /></a>
	</div>
	<div id="wrapper">
		<!-- Sidebar -->
		<div class="overlay" style="z-index: 3"></div>
		<nav class="navbar navbar-inverse navbar-fixed-top"
			id="sidebar-wrapper" role="navigation">
		<ul class="nav sidebar-nav">
			<c:if test="${empty accountInfo or empty role or role ne 'admin'}">
				<c:redirect url="main"></c:redirect>
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
				<strong>User list</strong>
			</h3>
			<div id="ajaxMessage"></div>
			<!-- Div conatining information -->
			<div>
				<!-- Toolbar -->
				<div id="toolbar" class="btn-group">
					<!-- Add button -->
					<button type="button" id="addButton" class="btn btn-default"
						data-toggle="modal" data-target="#addModal">
						<i class="glyphicon glyphicon-plus"></i>
					</button>
					<!-- Edit button -->
					<button type="button" id="editButton" class="btn btn-default"
						data-toggle="modal" data-target="#editModal" disabled="disabled">
						<i class="glyphicon glyphicon-edit"></i>
					</button>
				</div>
				<!-- JSON auto-generated table -->
				<table id="table" class="table" data-method="POST"
					data-show-toggle="true" data-toolbar="#toolbar"
					data-pagination="true" data-page-size="5"
					data-page-list="[5,10,20,50,100,200]">
					<thead>
						<tr>
							<th data-field="id" data-sortable="true">ID</th>
							<th data-field="userName" data-sortable="true">Username</th>
							<th data-field="firstName" data-sortable="true">First name</th>
							<th data-field="lastName" data-sortable="true">Last name</th>
							<th data-field="dateOfBirth" data-formatter="dateFormatter"
								data-sortable="true">Date of birth</th>
							<th data-field="email" data-sortable="true">Email</th>
							<th data-field="active" data-formatter="activeFormatter"
								data-sortable="true">Active</th>
							<th data-field="role" data-sortable="true">Role</th>
							<th data-field="action" data-formatter="actionFormatter"
								data-events="actionEvents">Action</th>
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
	<!-- Add modal form -->
	<div class="modal fade" id="addModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<form action="add" id="addForm">
					<!-- Modal header -->
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">×</button>
						<h4 class="modal-title">Add new staff</h4>
					</div>
					<!-- Modal body -->
					<div class="modal-body">
						<!-- User name -->
						<div class="form-group">
							User name: <input id="userName" name="userName" type="text"
								class="form-control" value="" placeholder="User name" />
							<div id="username_error" class="text-danger"></div>
						</div>
						<!-- First name -->
						<div class="form-group">
							First name: <input id="firstName" name="firstName" type="text"
								class="form-control" value="" placeholder="First name" />
							<div id="firstname_error" class="text-danger"></div>
						</div>
						<!-- Last name -->
						<div class="form-group">
							Last name: <input id="lastName" name="lastName" type="text"
								class="form-control" value="" placeholder="Last name" />
							<div id="lastname_error" class="text-danger"></div>
						</div>
						<!-- Date of birth -->
						<div class="form-group">
							Date of birth:
							<!-- Date picker -->
							<input id="dateOfBirth" name="dateOfBirth" type="text"
								data-provide="datepicker" class="form-control"
								data-date-format="dd/mm/yyyy" value="" placeholder="dd/mm/yyyy" autocomplete='off'/>
							<div id="dob_error" class="text-danger"></div>
						</div>
						<!-- Email -->
						<div class="form-group">
							Email: <input id="email" name="email" type="text"
								class="form-control" value="" placeholder="Email" />
							<div id="email_error" class="text-danger"></div>
						</div>
					</div>

					<!-- Modal footer -->
					<div class="modal-footer">
						<button type="button" class="btn btn-link" data-dismiss="modal">Cancel</button>
						<button type="button" class="btn btn-primary" id="manager-add-btn">Add</button>
					</div>
				</form>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- Edit modal form -->
	<div class="modal fade" id="editModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<form action="edit" id="editForm">
					<!-- Modal header -->
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">×</button>
						<h4 class="modal-title">Edit user</h4>
					</div>
					<!-- Modal body -->
					<div class="modal-body">
						<input type="hidden" id="id" name="id" /> <input type="hidden"
							id="userName" name="userName" />
						<!-- First name -->
						<div class="form-group">
							First name: <input id="firstName" name="firstName" type="text"
								class="form-control" value="" placeholder="First name" />
							<div id="firstname_error" class="text-danger"></div>
						</div>
						<!-- Last name -->
						<div class="form-group">
							Last name: <input id="lastName" name="lastName" type="text"
								class="form-control" value="" placeholder="Last name" />
							<div id="lastname_error" class="text-danger"></div>
						</div>
						<!-- Date of birth -->
						<div class="form-group">
							Date of birth:
							<!-- Date picker -->
							<input id="dateOfBirth" name="dateOfBirth" type="text"
								data-provide="datepicker" class="form-control"
								data-date-format="dd/mm/yyyy" value="" placeholder="dd/mm/yyyy" autocomplete='off'/>
							<div id="dob_error" class="text-danger"></div>
						</div>
						<!-- Email -->
						<div class="form-group">
							Email: <input id="email" name="email" type="text"
								class="form-control" value="" placeholder="Email" />
							<div id="email_error" class="text-danger"></div>
						</div>
						<!-- Active -->
						<div class="form-group">
							Active: <input type="radio" name="active" value="true" /> <strong
								class="text-success">Active</strong> <input type="radio"
								name="active" value="false" /> <strong class="text-danger">Inactive</strong>
						</div>
					</div>
					<!-- Modal footer -->
					<div class="modal-footer">
						<button type="button" class="btn btn-link" data-dismiss="modal">Cancel</button>
						<button type="button" class="btn btn-primary"
							id="manager-edit-btn">Update</button>
					</div>
				</form>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
</body>
</html>