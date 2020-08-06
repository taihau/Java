<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.myclass.util.UrlConstants" %>
<%@ page import="com.myclass.dto.LoginDto" %>

<nav class="navbar navbar-default navbar-static-top m-b-0">
    <div class="navbar-header"> <a class="navbar-toggle hidden-sm hidden-md hidden-lg "
            href="javascript:void(0)" data-toggle="collapse" data-target=".navbar-collapse"><iclass="fa fa-bars"></i></a>
        <div class="top-left-part"><a class="logo" href='<c:url value="${ UrlConstants.HOME }"></c:url>'><b><img 
        	src='<c:url value="/static/plugins/images/pixeladmin-logo.png"></c:url>' alt="home" /></b><span
                    class="hidden-xs"><img src='<c:url value="/static/plugins/images/pixeladmin-text.png"></c:url>' alt="home" /></span></a>
        </div>
        <ul class="nav navbar-top-links navbar-left m-l-20 hidden-xs">
            <li>
                <form role="search" class="app-search hidden-xs">
                    <input type="text" placeholder="Search..." class="form-control"> <a href=""><i
                            class="fa fa-search"></i></a>
                </form>
            </li>
        </ul>
        <ul class="nav navbar-top-links navbar-right pull-right">
            <li>
            <% 
            	LoginDto loginDto = (LoginDto) session.getAttribute("USER_LOGIN"); 
            	String avatar = loginDto.getAvatar();
            	String name = loginDto.getFullname();
            	
            %>
            	<a class="profile-pic dropdown-toggle" data-toggle="dropdown" href="#"> 
	                    <img src='<%= request.getContextPath() %>/static/plugins/images/users/<%= avatar %>' alt="user-img"
	            width="36" class="img-circle">
	                    <b class="hidden-xs"><%= name %></b> 
	            </a>
	                <ul class="dropdown-menu">
	                    <li><a href='<c:url value="${ UrlConstants.PROFILE_ALL }"></c:url>'>Thông tin cá nhân</a></li>
	                    <li><a href='<c:url value="${ UrlConstants.PROFILE_DETAILS }"></c:url>'>Thống kê công việc</a></li>
	                    <li class="divider"></li>
	                    <li><a href='<c:url value="${ UrlConstants.LOGOUT }"></c:url>'>Đăng xuất</a></li>
	                </ul>
	            </div>
            </li>
        </ul>
    </div>
    <!-- /.navbar-header -->
    <!-- /.navbar-top-links -->
    <!-- /.navbar-static-side -->
</nav>