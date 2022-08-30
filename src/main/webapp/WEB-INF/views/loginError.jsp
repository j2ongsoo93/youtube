<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>로그인</title>
    <!-- Slick Slider -->
    <link rel="stylesheet" type="text/css" href="<c:url value="${pageContext.request.contextPath}/resources/vendor/slick/slick.min.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="${pageContext.request.contextPath}/resources/vendor/slick/slick-theme.min.css"/>"/>
    <!-- Feather Icon-->
    <link href="<c:url value="${pageContext.request.contextPath}/resources/vendor/icons/feather.css"/>" rel="stylesheet" type="text/css">
    <!-- BootStrap CSS -->
    <link rel="stylesheet" href="<c:url value="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/bootstrap.min.css"/>">
    <!-- Custom styles -->
    <link rel="stylesheet" href="<c:url value="${pageContext.request.contextPath}/resources/css/style.css"/>">
    <!-- JQuery -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <!-- JavaScript -->
</head>
<body>
<!-- navBar --><!-- navbar -->
<jsp:include page="navbar.jsp"/>
<!-- navbar end -->
<h2>Error</h2>

<!-- Bootstrap core JavaScript -->
<script src="<c:url value="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"/>"></script>
<script src="<c:url value="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"/>"></script>
<!-- slick Slider JS-->
<script type="text/javascript" src="<c:url value="${pageContext.request.contextPath}/resources/vendor/slick/slick.min.js"/>"></script>
</body>
</html>
