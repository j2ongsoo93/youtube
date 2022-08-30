<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>YouTube</title>
    <!-- Slick Slider -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/vendor/slick/slick.min.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/vendor/slick/slick-theme.min.css"/>
    <!-- Feather Icon-->
    <link href="${pageContext.request.contextPath}/resources/vendor/icons/feather.css" rel="stylesheet" type="text/css">
    <!-- BootStrap CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/bootstrap.min.css">
    <!-- Custom styles -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
    <!-- JQuery -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <!-- JavaScript -->
    <script src="${pageContext.request.contextPath}/resources/js/youtubeMain.js"></script>
    <!-- CSS -->
    <style>
        .scrollbar-invisible{
            max-height: 720px;
            overflow-y: scroll;
        }

        .scrollbar-invisible::-webkit-scrollbar{
            display: none;
        }
    </style>
</head>
<body>
<jsp:include page="navbar.jsp"/>
<div class="py-4">
    <div class="row">
        <main class="col order-1 col-xl-10 col-lg-10 col-md-10 col-sm-10 col-12">
            <div id="managerBtnContainer" class="mb-2" style="width: 96%;  padding-right: 15px;  padding-left: 15px;  margin-right: auto;  margin-left: auto;">

            </div>
            <div style="width: 95%;  padding-right: 15px;  padding-left: 15px;  margin-right: auto;  margin-left: auto;">
                <div id="videoListContainer" class="row box shadow-sm border rounded bg-white mb-3 osahan-share-post">

                </div>
            </div>
        </main>
        <aside class="col order-0 col-xl-2 col-lg-2 col-md-2 col-sm-2 col-12">
            <div id="subsContainer" class="sticky-top scrollbar-invisible  py-2" style="width: 95%;  padding-right: 15px;  padding-left: 15px;  margin-right: auto;  margin-left: auto;">

            </div>
        </aside>
    </div>
</div>
</body>
</html>
