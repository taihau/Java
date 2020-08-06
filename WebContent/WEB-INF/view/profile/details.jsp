	<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    
<div class="container-fluid">
    <div class="row bg-title">
        <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
            <h4 class="page-title">Chi tiết công việc </h4>
        </div>
        <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
            <ol class="breadcrumb">
                <li><a href="#">Dashboard</a></li>
                <li class="active">Blank Page</li>
            </ol>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- BEGIN THỐNG KÊ -->
    <div class="row">
    	<c:forEach items="${ statusPercent }" var="item">
	    	<!--col -->
	        <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
	            <div class="white-box">
	                <div class="col-in row">
	                    <div class="col-md-6 col-sm-6 col-xs-6"> <i data-icon="E"
	                            class="linea-icon linea-basic"></i>
	                        <h5 class="text-muted vb">${ item.statusName }</h5>
	                    </div>
	                    <div class="col-md-6 col-sm-6 col-xs-6">
	                        <h3 class="counter text-right m-t-15 text-danger">${ item.percent }%</h3>
	                    </div>
	                    <div class="col-md-12 col-sm-12 col-xs-12">
	                        <div class="progress">
	                            <div class="progress-bar progress-bar-danger" role="progressbar"
	                                aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: ${ item.percent }%">
	                            </div>
	                        </div>
	                    </div>
	                </div>
	            </div>
	        </div>
	        <!-- /.col -->
    	</c:forEach>
    </div>
    <!-- END THỐNG KÊ -->

    <!-- BEGIN DANH SÁCH CÔNG VIỆC -->
    <div class="row">
        <div class="col-xs-12">
            <a href="#" class="group-title">
                <img width="30" src='<c:url value="/static/plugins/images/users/${ user.avatar }"></c:url>' class="img-circle" />
                <span>${ user.fullname }</span>
            </a>
        </div>
        <div class="col-md-4">
            <div class="white-box">
                <h3 class="box-title">Chưa thực hiện</h3>
                <div class="message-center">
                  <c:forEach items="${ tasks }" var="item">
							<c:set var="id" value="${ item.user_id }"></c:set>
							<c:set var="userID" value="${ user.id }"></c:set>
							<c:set var="statusID" value="${ item.status_id }"></c:set>
							<c:if test="${ id == userID && statusID == 1  }">
							<a href="#">
								<div class="mail-contnet">
									<h5>${ item.name }</h5>
									<span class="mail-desc"></span>
									<span class="time">Bắt đầu: <fmt:formatDate pattern = "dd-MM-yyyy" value="${ item.startDate }"/> </span>
									<span class="time">Kết thúc: <fmt:formatDate pattern = "dd-MM-yyyy" value="${ item.endDate }"/></span>
								</div>
							</a>
							</c:if>
					</c:forEach>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="white-box">
                <h3 class="box-title">Đang thực hiện</h3>
                <div class="message-center">
                	<c:forEach items="${ tasks }" var="item">
							<c:set var="id" value="${ item.user_id }"></c:set>
							<c:set var="userID" value="${ user.id }"></c:set>
							<c:set var="statusID" value="${ item.status_id }"></c:set>
							<c:if test="${ id == userID && statusID == 2  }">
							<a href="#">
								<div class="mail-contnet">
									<h5>${ item.name }</h5>
									<span class="mail-desc"></span>
									<span class="time">Bắt đầu: <fmt:formatDate pattern = "dd-MM-yyyy" value="${ item.startDate }"/> </span>
									<span class="time">Kết thúc: <fmt:formatDate pattern = "dd-MM-yyyy" value="${ item.endDate }"/></span>
								</div>
							</a>
							</c:if>
					</c:forEach>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="white-box">
                <h3 class="box-title">Đã hoàn thành</h3>
                <div class="message-center">
                   <c:forEach items="${ tasks }" var="item">
							<c:set var="id" value="${ item.user_id }"></c:set>
							<c:set var="userID" value="${ user.id }"></c:set>
							<c:set var="statusID" value="${ item.status_id }"></c:set>
							<c:if test="${ id == userID && statusID == 3  }">
							<a href="#">
								<div class="mail-contnet">
									<h5>${ item.name }</h5>
									<span class="mail-desc"></span>
									<span class="time">Bắt đầu: <fmt:formatDate pattern = "dd-MM-yyyy" value="${ item.startDate }"/> </span>
									<span class="time">Kết thúc: <fmt:formatDate pattern = "dd-MM-yyyy" value="${ item.endDate }"/></span>
								</div>
							</a>
							</c:if>
					</c:forEach>
                </div>
            </div>
        </div>
    </div>
    <!-- END DANH SÁCH CÔNG VIỆC -->
</div>