<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.myclass.util.UrlConstants" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LOGIN</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<style>
	body{
		background: #edf1f5;
	}
	.login{
		padding: 30px;
		border: 1px solid #ccc;
		margin-top: 40%;
		background: #fff;
	}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-5 m-auto">
				<div class="login">
					<h4 class="text-center mb-3">ĐĂNG NHẬP HỆ THỐNG</h4>
					<form action='<c:url value="${ UrlConstants.LOGIN }"></c:url>' method="post">
						<div class="form-group">
							<label for="email">Email</label> 
							<input type="email" name="email" class="form-control" />
						</div>
						<div class="form-group">
							<label for="pwd">Mật khẩu</label> 
							<input type="password" name="password" class="form-control" />
						</div>
						<p class="text-warning">${ Message }</p>
						<button type="submit" class="btn btn-primary">Đăng nhập</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>