<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>회원 관리</title>
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
    <script src="${pageContext.request.contextPath}/resources/js/memberAdmin.js"></script>

</head>
<body class="modal-open">
<jsp:include page="navbar.jsp"/>
<div class="py-4">
    <div style="width: 80%;  padding-right: 15px;  padding-left: 15px;  margin-right: auto;  margin-left: auto;">
        <div class="col-12">
            <div class="row">
                <div class="d-flex ml-3">

                    <h2 class="mt-2 ml-3 font-weight-bold">회원 관리<br><span><h6 class="mt-2">접속계정: ${l.getMem_id()} 권한:${l.getAuthority()}</h6></span></h2>
                </div>
            </div>
        </div>

        <div id="memListContainer" class="col-12 py-2">
<%--            <table class="table table-dark">--%>
<%--                <thead id="memListHead">--%>
<%--                    <tr>--%>
<%--                        <th>ID</th>--%>
<%--                        <th>이름</th>--%>
<%--                        <th>가입일</th>--%>
<%--                        <th>수정일</th>--%>
<%--                        <th>가입 IP</th>--%>
<%--                        <th>수정 IP</th>--%>
<%--                        <th>email</th>--%>
<%--                        <th>상태</th>--%>
<%--                        <th>권한</th>--%>
<%--                        <th>프로필</th>--%>
<%--                    </tr>--%>
<%--                </thead>--%>
<%--                <tbody id="memListBody">--%>
<%--                    <tr>--%>
<%--                        <td>1</td>--%>
<%--                        <td>2</td>--%>
<%--                        <td>3</td>--%>
<%--                        <td>4</td>--%>
<%--                        <td>5</td>--%>
<%--                        <td>6</td>--%>
<%--                        <td>7</td>--%>
<%--                        <td>8</td>--%>
<%--                        <td>9</td>--%>
<%--                        <td>10</td>--%>
<%--                    </tr>--%>
<%--                </tbody>--%>
            </table>
        </div>
    </div>

</div>
</body>
</html>
