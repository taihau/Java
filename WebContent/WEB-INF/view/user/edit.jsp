<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.myclass.util.UrlConstants" %>

<head>
	<title>Sửa Thành Viên</title>
</head>

<div class="container-fluid">
    <div class="row bg-title">
        <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
            <h4 class="page-title">Sửa thành viên</h4>
        </div>
    </div>
    <!-- /.row -->
    <!-- .row -->
    <div class="row">
        <div class="col-md-2 col-12"></div>
        <div class="col-md-8 col-xs-12">
            <div class="white-box">
                <form class="form-horizontal form-material" 
                action='<c:url value="${ UrlConstants.USER_EDIT }"></c:url>' method="post" enctype="multipart/form-data">
                	<div class="form-group">
                        <div class="col-md-12">
                            <input type="hidden" placeholder="Johnathan Doe"
                                class="form-control form-control-line text-left" name="id" value="${ user.id }"> </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-12">Full Name</label>
                        <div class="col-md-12">
                            <input type="text" placeholder="Johnathan Doe"
                                class="form-control form-control-line" name="fullname" value="${ user.fullname }"> </div>
                    </div>
                    <div class="form-group">
                        <label for="example-email" class="col-md-12">Email</label>
                        <div class="col-md-12">
                            <input type="email" placeholder="johnathan@admin.com"
                                class="form-control form-control-line" name="email"
                                id="example-email" value="${ user.email }"> </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-12">Password</label>
                        <div class="col-md-12">
                            <input type="password" value="" class="form-control form-control-line" name="password">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-12">Avatar</label>
                        <div class="col-md-12">
                        	<input type="hidden" name="oldAvatar" value="${ user.avatar }">
                            <input type="file" placeholder="name***.png"
                                class="form-control form-control-line" name="avatar" value=""> </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-12">Select Country</label>
                        <div class="col-sm-12">
                            <select class="form-control form-control-line" name="roleID">
                            	<c:forEach items="${ roles }" var="item">
                            		<option value="${ item.id }" ${ user.roleID == item.id ? "selected" : "" }>
                            			${ item.description }
                            		</option>
                            	</c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-12">
                            <button type="submit" class="btn btn-success">Add User</button>
                            <a href='<c:url value="${ UrlConstants.USER_ALL }"></c:url>' class="btn btn-primary">Quay lại</a>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <div class="col-md-2 col-12"></div>
    </div>
</div>