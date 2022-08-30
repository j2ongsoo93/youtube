<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>로그인</title>
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
</head>
<body>
<!-- navbar -->
<jsp:include page="navbar.jsp"/>
<div class="py-4">
    <div class="row d-flex">
        <div class="col col-xl-3 col-lg-3 col-md-3 col-sm-3 col-12 mr-auto ml-auto">
            <div class="mt-5 row">
                <img class="ml-auto mr-auto" src="${pageContext.request.contextPath}/resources/img/icon/topiaCI.png" alt="" style="height: 80px; width: 240px"><br>
            </div>
            <h3 class="py-4 font-weight-bold" style="text-align: center">로그인</h3>
            <h5 class="py-2 font-weight-bold" style="text-align: center">TOPIA 계정 사용</h5>

            <form action="${pageContext.request.contextPath}/member/login/check" method="post">
                <label for="memId">아이디</label><br>
                <input name="memId" id="memId" type="text" class="form-control" style="width:100%" placeholder="아이디를 입력하세요">
                <br><br>
                <label for="password">비밀번호</label><br>
                <input name="password" id="password" type="password" class="form-control" style="width:100%" placeholder="비밀번호를 입력하세요">
                <br><br>
                <input type="submit" class="form-control ml-auto" style="width: 25%" value="로그인">
            </form>
            <br>
            <a href="${pageContext.request.contextPath}/member/sign-up">계정생성</a>
            <a href=""></a>

        </div>
    </div>
</div>
</body>
</html>
