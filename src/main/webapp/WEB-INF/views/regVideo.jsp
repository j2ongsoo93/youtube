<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>영상 등록</title>
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
    <script src="${pageContext.request.contextPath}/resources/js/regVideo.js"></script>

</head>
<body>
<jsp:include page="navbar.jsp"/>
<div class="py-4">
    <div class="row d-flex">
        <div class="col col-xl-3 col-lg-3 col-md-3 col-sm-3 col-12 mr-auto ml-auto">
            <div class="row">
                <img class="ml-auto mr-auto" src="${pageContext.request.contextPath}/resources/img/icon/youtube-logo-png-transparent-16.png" alt="" style="width: 100px"><br>
            </div>
            <h3 class="py-4 font-weight-bold" style="text-align: center">영상 등록</h3>

            <form action="${pageContext.request.contextPath}/youtube/reg" method="post" id="regForm">
                <input type="hidden" name="memId" value="${l.getMem_id()}">

<%--                <label for="ytbTitle">영상제목 *</label><br>--%>
<%--                <input name="ytbTitle" id="ytbTitle" type="text" class="form-control" style="width:100%">--%>
<%--                <p class="mt-1" id="dupCheck"></p>--%>
<%--                <br>--%>

                <label for="ytbUrl">URL *</label><br>
                <input name="ytbUrl" id="ytbUrl" type="text" class="form-control" style="width:100%">
                <br><br>

<%--                <label for="ytbInfo">영상설명 *</label><br>--%>
<%--                <input name="ytbInfo" id="ytbInfo" type="text" class="form-control" style="width:100%">--%>
<%--                <br><br>--%>

<%--                <label for="ytbChannelName">채널명 *</label><br>--%>
<%--                <input name="ytbChannelName" id="ytbChannelName" type="text" class="form-control" style="width:100%" >--%>
<%--                <br><br>--%>

<%--                <label for="ytbChannelUrl">채널 URL *</label><br>--%>
<%--                <input name="ytbChannelUrl" id="ytbChannelUrl" type="text" class="form-control" style="width:100%" >--%>
<%--                <br><br>--%>

                <div class="row">
                    <button id="btnCancel" type="button" class="form-control ml-auto mr-2" style="width: 25%">취소</button>
                    <button id="btnReg" type="button" class="form-control" style="width: 25%">등록</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
