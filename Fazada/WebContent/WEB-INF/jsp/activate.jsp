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
<link href="<c:url value='/Resources/css/main.css' />" rel="stylesheet" />
<link
	href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css"
	rel="stylesheet">
<!-- Custom javascript file (mostly validation) -->
<script type="text/javascript"
	src='<c:url value="/Resources/js/custom.js"/>'></script>
<title>Active</title>
</head>
<body>
	<div class="center-block">
		<a href="main"><img
			src="<c:url value='/Resources/pic/'/>FazadaGroupLogo.jpg"
			style="width: 300px; padding-left: 60px" /></a>
	</div>
	<!-- Container div -->
	<div style="flex: 1; margin-bottom: 20px; margin-top: 20px">
		<div class="container">
			<c:if test="${statusCode eq 200}">
				<div class="alert alert-success">${activateMessage}</div>
			</c:if>
			<c:if test="${statusCode ne 200}">
				<div class="alert alert-danger">${activateMessage}</div>
			</c:if>
			<a href="/fazada/main" class="btn btn-link">Click here to return
				to main page</a>
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