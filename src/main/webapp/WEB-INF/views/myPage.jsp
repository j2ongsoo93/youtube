<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>마이페이지</title>
    <!-- Slick Slider -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/vendor/slick/slick.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/vendor/slick/slick-theme.min.css">
    <!-- Feather Icon-->
    <link href="${pageContext.request.contextPath}/resources/vendor/icons/feather.css" rel="stylesheet" type="text/css">
    <!-- BootStrap CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/bootstrap.min.css">
    <!-- Custom styles -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
    <!-- JQuery -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <!-- JavaScript -->
    <script src="${pageContext.request.contextPath}/resources/js/myPage.js"></script>

</head>
<body>
<jsp:include page="navbar.jsp"/>
<div class="py-4">
    <div style="width: 80%;  padding-right: 15px;  padding-left: 15px;  margin-right: auto;  margin-left: auto;">
        <div class="col col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
            <div class="row">
                <div class="d-flex ml-3">
                    <c:if test="${l.getImg_file_name() eq null}">
                        <img class="rounded-circle" src="${pageContext.request.contextPath}/resources/upload/profileImg/img.png" style="width: 80px;height: 80px;">
                    </c:if>
                    <c:if test="${l.getImg_file_name() != null}">
                        <img class="rounded-circle" src="${pageContext.request.contextPath}/resources/upload/profileImg/${l.getImg_file_name()}" style="width: 80px;height: 80px;">
                    </c:if>
                    <h2 class="mt-2 ml-3 font-weight-bold">${l.getMem_name()}<br><span><h6 class="mt-2">${l.getMem_id()}</h6></span></h2>
                </div>
            </div>
        </div>
        <div class="col col-xl-4 col-lg-4 col-md-4 col-sm-4 col-12">
            <div class="box shadow-sm border rounded bg-white mt-5 osahan-share-post">
                <ul class="nav nav-justified border-bottom osahan-line-tab" id="myTab" role="tablist">
                    <li class="nav-item" id="btnMyVideos">
                        <a class="nav-link active" id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile" aria-selected="true"><i class="feather-play-circle"></i> 내 영상</a>
                    </li>
                    <li class="nav-item" id="btnPlayList">
                        <a class="nav-link" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="false"><i class="feather-list"></i> 재생목록</a>
                    </li>
                </ul>
            </div>
        </div>
        <hr>
        <div class="col col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
            <div class="row pb-2">
                <div id="btnContainer">
                    <button id="btnRegVideo" class="btn btn-light" style="border:none">영상 등록</button>
                    <button id="btnDeleteVideo" class="btn btn-light ml-2" style="border:none">선택 삭제</button>
                </div>
            </div>
            <div class="row">
                <div class="box shadow-sm border rounded bg-white mb-3 osahan-share-post">
                    <div class="row" id="myVideosContainer">

                    </div>

                    <div class="row" id="playListContainer">

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
