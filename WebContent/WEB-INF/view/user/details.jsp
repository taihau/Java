<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<head>
	<title>Chi tiết thành viên</title>
</head>

<div class="container-fluid">
    <div class="row bg-title">
        <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
            <h4 class="page-title">Chi tiết thành viên</h4>
        </div>
    </div>
    <!-- /.row -->
    <!-- .row -->
    <div class="row">
        <div class="col-md-4 col-xs-12">
            <div class="white-box">
                <div class="user-bg"> <img width="100%" alt="user" src='<c:url value="/static/plugins/images/users/${ user.avatar }"></c:url>'>
                    <div class="overlay-box">
                        <div class="user-content">
                            <a href="javascript:void(0)"><img src='<c:url value="/static/plugins/images/users/${ user.avatar }"></c:url>'
                                    class="thumb-lg img-circle" alt="img"></a>
                            <h4 class="text-white">${ user.fullname }</h4>
                            <h5 class="text-white">${ user.email }</h5>
                        </div>
                    </div>
                </div>
                <div class="user-btm-box">
                <c:forEach items="${ statusPercent }" var="item">
                	<div class="col-md-4 col-sm-4 text-center">
                        <p class="text-purple"><i class="ti-facebook"></i></p>
                        <h4><c:out value="${ item.percent}"/>%</h4>
                        <h6>${ item.statusName }</h6>
                    </div>
                </c:forEach>
                </div>
            </div>
        </div>
        <div class="col-md-8 col-xs-12">
            <div class="white-box">
                <form class="form-horizontal form-material">
                    <div class="form-group">
                        <label class="col-md-3">Họ và Tên</label><p class="col-md-9">${ user.fullname }</p>
                    </div>
                    <div class="form-group">
                        <label for="example-email" class="col-md-3">Email</label><p class="col-md-9">${ user.email }</p>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3">Cấp Bậc</label>
                        <p class="col-md-9">
                        <c:forEach items="${ roles }" var="item">
                        	${ user.roleID == item.id ? item.description : "" }
                        </c:forEach>
                        </p>
                    </div>
                </form>
            </div>
        </div>
    </div><br />
    <!-- /.row -->
    <!-- BEGIN DANH SÁCH CÔNG VIỆC -->
    <h4>DANH SÁCH CÔNG VIỆC</h4>
    <div class="row">
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