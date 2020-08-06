<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="com.myclass.util.UrlConstants" %>

<head>
	<title>Danh sách đầu việc</title>
</head>

<div class="container-fluid">
	<div class="row bg-title">
		<div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
			<h4 class="page-title">Danh sách đầu việc</h4>
		</div>
		<div class="col-lg-9 col-sm-8 col-md-8 col-xs-12 text-right">
			<a href='<c:url value="${ UrlConstants.TASK_ADD }"></c:url>' class="btn btn-sm btn-success">Thêm mới</a>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /row -->
	<div class="row">
		<div class="col-sm-12">
			<div class="white-box">
				<div class="table-responsive">
					<table class="table" id="example">
						<thead>
							<tr>
								<th>ID</th>
								<th>Tên Đầu Việc</th>
								<th>Ngày Bắt Đầu</th>
								<th>Ngày Kết Thúc</th>
								<th>Công việc</th>
								<th>Người thực hiện</th>
								<th>Trạng thái</th>
								<th>Hành Động</th>
							</tr>
						</thead>
						<tbody>
							<% int count = 0; %>
							<c:forEach items="${ tasks }" var="item">
								<tr>
									<td><%= ++count %></td>
									<td>${ item.name }</td>
									<td><fmt:formatDate pattern = "dd-MM-yyyy" value ="${ item.startDate }"/></td>
									<td><fmt:formatDate pattern = "dd-MM-yyyy" value ="${ item.endDate }"/></td>
									<td>${ item.jobName }</td>
									<td>${ item.userName }</td>
									<td>${ item.statusName }</td>
									<td>
										<a href='<c:url value="${ UrlConstants.TASK_EDIT }?id=${ item.id }" />' class="btn btn-sm btn-primary">Sửa</a> 
										<a href='<c:url value="${ UrlConstants.TASK_DELETE }?id=${ item.id }" />' class="btn btn-sm btn-danger">Xóa</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<!-- /.row -->
</div>

