<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="dec"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" type="image/png" sizes="16x16"
	href='<c:url value="static/plugins/images/favicon.png"></c:url>'>
<title><dec:title></dec:title></title>
<!-- HEADER -->
<jsp:include page="/WEB-INF/view/decorators/header.jsp"></jsp:include>
</head>

<body>
	<!-- Preloader -->
	<div class="preloader">
		<div class="cssload-speeding-wheel"></div>
	</div>
	<div id="wrapper">
		<!-- NAVIGATION -->
		<jsp:include page="/WEB-INF/view/decorators/navbar.jsp"></jsp:include>
		<!-- Left navbar-header -->
		<jsp:include page="/WEB-INF/view/decorators/sidebar.jsp"></jsp:include>
		<!-- Left navbar-header end -->
		<!-- Page Content -->
		<div id="page-wrapper">
			<dec:body></dec:body>
		</div>
		<!-- /.container-fluid -->
		<footer class="footer text-center"> 2018 &copy; myclass.com </footer>
	</div>
	<!-- /#page-wrapper -->
	<!-- /#wrapper -->

	<jsp:include page="/WEB-INF/view/decorators/footer.jsp"></jsp:include>


	<dec:getProperty property="page.script">
	</dec:getProperty>
</body>

</html>