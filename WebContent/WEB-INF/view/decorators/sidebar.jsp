<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.myclass.util.UrlConstants" %>
<%@ page import="com.myclass.dto.LoginDto" %>
<% 
	LoginDto loginDto = (LoginDto) session.getAttribute("USER_LOGIN"); 
	String roleName = loginDto.getRolename();
%>
<div class="navbar-default sidebar" role="navigation">
    <div class="sidebar-nav navbar-collapse slimscrollsidebar">
        <ul class="nav" id="side-menu">
            <li style="padding: 10px 0 0;">
                <a href='<c:url value="${ UrlConstants.HOME }"></c:url>' class="waves-effect"><i class="fa fa-clock-o fa-fw"
                        aria-hidden="true"></i><span class="hide-menu">Dashboard</span></a>
            </li>
            <% if(roleName.equals("ROLE_ADMIN") || roleName.equals("ROLE_LEADER")){ %>
            <li>
                <a href='<c:url value="${ UrlConstants.USER_ALL }"></c:url>' class="waves-effect"><i class="fa fa-user fa-fw"
                        aria-hidden="true"></i><span class="hide-menu">Thành viên</span></a>
            </li>
            <% } %>
           <% if(roleName.equals("ROLE_ADMIN")){ %>
            <li>
                <a href='<c:url value="${ UrlConstants.ROLE_ALL }"></c:url>' class="waves-effect"><i class="fa fa-modx fa-fw"
                        aria-hidden="true"></i><span class="hide-menu">Quyền</span></a>
            </li>
            <% } %>
            <li>
                <a href='<c:url value="${ UrlConstants.JOB_ALL }"></c:url>' class="waves-effect"><i class="fa fa-table fa-fw"
                        aria-hidden="true"></i><span class="hide-menu">Công việc</span></a>
            </li>
            <li>
                <a href='<c:url value="${ UrlConstants.TASK_ALL }"></c:url>' class="waves-effect"><i class="fa fa-columns fa-fw"
                        aria-hidden="true"></i><span class="hide-menu">Đầu Việc</span></a>
            </li>
        </ul>
    </div>
</div>