<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.myclass.util.UrlConstants" %>

<head>
<title>Thêm mới đầu việc</title>
</head>

<div class="container-fluid">
	<div class="row bg-title">
		<div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
			<h4 class="page-title">Thêm mới đầu việc</h4>
		</div>
	</div>
	<!-- /.row -->
	<!-- .row -->
	<div class="row">
		<div class="col-md-2 col-12"></div>
		<div class="col-md-8 col-xs-12">
			<div class="white-box">
				<form action='<c:url value="${ UrlConstants.TASK_ADD }"></c:url>' method="post" class="form-horizontal form-material">
					<div class="form-group">
						<label class="col-sm-12">Công việc</label>
						<div class="col-sm-12">
							<select class="form-control form-control-line" name="jobId">
							<c:forEach items="${ jobs }" var="item">
								<option value="${ item.id }">${ item.name  }</option>
							</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-12">Tên đầu việc</label>
						<div class="col-md-12">
							<input type="text" name="name" placeholder="Tên đầu việc"
								class="form-control form-control-line">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-12">Ngày bắt đầu</label>
						<div class="col-md-12">
							<input type="date" name="startDate" placeholder="dd/MM/yyyy"
								class="form-control form-control-line">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-12">Ngày kết thúc</label>
						<div class="col-md-12">
							<input type="date" name="endDate" placeholder="dd/MM/yyyy"
								class="form-control form-control-line">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-12">Người thực hiện</label>
						<div class="col-sm-12">
							<select class="form-control form-control-line" name="userId">
								<c:forEach items="${ users }" var='item'>
									<option value="${ item.id }">${ item.fullname }</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-12">Trạng thái</label>
						<div class="col-sm-12">
							<select class="form-control form-control-line" name="statusId">
								<c:forEach items="${ status }" var="item">
									<option value="${ item.id }">${ item.name }</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-12">
							<button type="submit" class="btn btn-success">Lưu lại</button>
							<a href='<c:url value="/task"></c:url>' class="btn btn-primary">Quay lại</a>
						</div>
					</div>
				</form>
			</div>
		</div>
		<div class="col-md-2 col-12"></div>
	</div>
	<!-- /.row -->
</div>