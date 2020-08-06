<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.myclass.util.UrlConstants" %>
    
<heade>
	<title>Hồ Sơ Cá Nhân</title>
</heade>
<div class="container-fluid">
    <div class="row bg-title">
        <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
            <h4 class="page-title">Hồ sơ cá nhân</h4> </div>
        <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
            <ol class="breadcrumb">
                <li><a href="#">Dashboard</a></li>
                <li class="active">Hồ sơ cá nhân</li>
            </ol>
        </div>
    </div>
    <!-- /.row -->
    <!-- .row -->
    <div class="row">
        <div class="col-md-4 col-xs-12">
            <div class="white-box">
                <div class="user-bg"> <img width="100%" alt="user" src='<c:url value="/static/plugins/images/users/${ LoginDto.avatar }"></c:url>'>
                    <div class="overlay-box">
                        <div class="user-content">
                            <a href="javascript:void(0)"><img src='<c:url value="/static/plugins/images/users/${ LoginDto.avatar }"></c:url>' class="thumb-lg img-circle" alt="img"></a>
                            <h4 class="text-white">${ LoginDto.fullname }</h4>
                            <h5 class="text-white">${ LoginDto.email }</h5> </div>
                    </div>
                </div>
                <div class="user-btm-box">
                <c:forEach items="${ statusPercent }" var="item">
               		<div class="col-md-4 col-sm-4 text-center">
                        <p class="text-purple"><i class="ti-facebook"></i></p>
                        <h4><c:out value="${ item.percent }"/>%</h4>
                        <h6>${ item.statusName }</h6>
                    </div>
                </c:forEach>
                   
                </div>
            </div>
        </div>
        <div class="col-md-8 col-xs-12">
            <div class="white-box">
                <form class="form-horizontal form-material" action='<c:url value="${ UrlConstants.PROFILE_EDIT }"></c:url>' method="post">
                <input type="hidden" placeholder="Johnathan Doe"
                                class="form-control form-control-line text-left" name="id" value="${ LoginDto.id }">
                    <div class="form-group">
                        <label class="col-md-12">Full Name</label>
                        <div class="col-md-12">
                            <input type="text" placeholder="Johnathan Doe" name="fullname" value="${ LoginDto.fullname }" class="form-control form-control-line"> </div>
                    </div>
                    <div class="form-group">
                        <label for="example-email" class="col-md-12">Email</label>
                        <div class="col-md-12">
                            <input type="email" placeholder="johnathan@admin.com" name="email" value="${ LoginDto.email }" class="form-control form-control-line" name="example-email" id="example-email"> </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-12">Password
						</label>
                        <div class="col-md-12">
                            <input type="password" name="password" value="" class="form-control form-control-line"> </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-12">
                            <button class="btn btn-success">Update Profile</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <!-- /.row -->
</div>