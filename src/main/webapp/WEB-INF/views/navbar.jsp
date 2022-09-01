<%@ page import="topia.com.myApp.dto.MemberDTO" %>
<%@ page import="topia.com.myApp.dto.LoginInfoDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html>
<head>
    <title>Title</title>
    <!-- JQuery -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <!-- JavaScript -->
    <script src="${pageContext.request.contextPath}/resources/js/navbar.js"/>"></script>
</head>
<body>
<%
    LoginInfoDTO l = (LoginInfoDTO) session.getAttribute("l");
%>
<!-- navBar -->
<nav class="navbar navbar-expand navbar-dark bg-dark osahan-nav-top p-0">
    <div class="container">
        <a class="navbar-brand" href="http://125.7.234.18:8000/topiadev/"><img src="${pageContext.request.contextPath}/resources/img/icon/topiaCI.png" alt="" style="height: 50px; width: 150px"></a>
        <a class="navbar-brand mr-auto" href="${pageContext.request.contextPath}/youtube/home"><img src="${pageContext.request.contextPath}/resources/img/icon/logo.png" alt="" style="height: 55px; width: 55px"></a>
        <input type="hidden" id="contextPath" value="${pageContext.request.contextPath}">
        <input type="hidden" id="profileImgFile" value="${l.getImg_file_name()}">
        <!-- 검색창 -->
        <input id="keyword" type="text" class="form-control shadow-none" placeholder="검색어를 입력하세요..." style="width: 350px; color: #00c9a7">
        <button id="btnSearch" class="btn btn-light" type="button">검색</button>
        <!-- 검색창 end -->
        <ul class="navbar-nav ml-auto d-flex align-items-center">


            <c:choose>
                <c:when test="${l.getMem_id() eq null}">
                    <button id="btnToLoginPage" class="btn btn-light">로그인</button>
                </c:when>
                <c:otherwise>
                    <!-- Nav Item 계정 정보 -->
                    <input type="hidden" id="memId" value="${l.getMem_id()}">
                    <input type="hidden" id="auth" value="${l.getAuthority()}">
                    <li class="nav-item dropdown no-arrow ml-1 osahan-profile-dropdown">
                        <a class="nav-link dropdown-toggle pr-0" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <c:if test="${l.getImg_file_name() eq null}">
                                <img class="rounded-circle" src="${pageContext.request.contextPath}/resources/upload/profileImg/img.png" style="width: 40px;height: 40px;">
                            </c:if>
                            <c:if test="${l.getImg_file_name() != null}">
                                <img class="rounded-circle" src="${pageContext.request.contextPath}/resources/upload/profileImg/${l.getImg_file_name()}" style="width: 40px;height: 40px;">
                            </c:if>
                        </a>
                        <!-- 계정 정보 Dropdown -->
                        <div class="dropdown-menu dropdown-menu-right shadow-sm">
                            <div class="p-3 d-flex align-items-center">
                                <div class="dropdown-list-image mr-3">
                                    <c:if test="${l.getImg_file_name() eq null}">
                                        <img class="rounded-circle" src="${pageContext.request.contextPath}/resources/upload/profileImg/img.png" style="width: 40px;height: 40px;">
                                    </c:if>
                                    <c:if test="${l.getImg_file_name() != null}">
                                        <img class="rounded-circle" src="${pageContext.request.contextPath}/resources/upload/profileImg/${l.getImg_file_name()}" style="width: 40px;height: 40px;">
                                    </c:if>
                                    <div class="status-indicator bg-success"></div>
                                </div>
                                <div class="font-weight-bold">
                                    <div class="text-truncate">${l.getMem_name()}</div>
                                    <div class="small text-gray-500">${l.getAuthority()}</div>
                                </div>
                            </div>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="#"><i class="feather-edit mr-1"></i> 계정설정</a>
                            <c:if test="${l.getAuthority() eq 'master' or l.getAuthority() eq 'manager'}">
                                <a class="dropdown-item" href="${pageContext.request.contextPath}/youtube/member/admin"><i class="feather-edit mr-1"></i> 회원관리</a>
                            </c:if>
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/youtube/myPage"><i class="feather-user mr-1"></i> 마이페이지</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/logout"><i class="feather-log-out mr-1"></i> 로그아웃</a>
                        </div>
                        <!-- 계정 정보 Dropdown end -->

                    </li>
                    <!-- Nav Item 계정 정보 -->
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
</nav>
<!-- navbar end -->

<!-- Bootstrap core JavaScript -->
<script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- slick Slider JS-->
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/vendor/slick/slick.min.js"></script>

</body>
</html>
