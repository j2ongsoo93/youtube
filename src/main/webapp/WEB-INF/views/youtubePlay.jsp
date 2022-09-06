<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>${y.ytbTitle}</title>
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
    <script src="${pageContext.request.contextPath}/resources/js/youtubePlay.js"></script>
</head>
<body>
<jsp:include page="navbar.jsp"/>
<div class="py-2">
    <div style="width: 85%;  padding-right: 15px;  padding-left: 15px;  margin-right: auto;  margin-left: auto;">
        <div class="row">

            <!-- 메인-->
            <main class="col col-xl-8 col-lg-8 col-md-8 col-sm-8 col-12">
                <div class="row">
                    <div class="col col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12 py-2">
                        <iframe style="width: 100%; height: 70vh;" src="${y.ytbEmbedUrl}" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
                    </div>

                    <div class="py-2 ml-2 mr-2 border-bottom" style="width: 100%">
                        <!-- 영상 설명 -->
                        <h5 class="text-dark font-weight-bold text-sm-left">${y.ytbTitle}</h5>
                        <div>
                            <span class="font-weight-bold">조회수 ${y.ytbHit}회</span>&nbsp;&nbsp;&nbsp;
                            <span class="font-weight-bold">${y.ytbRegDate}</span>
                        </div>
                        <div class="py-2" id="infoContainer">
                            <span id="ytbInfo" style="color: white; text-overflow:ellipsis;overflow:hidden; width: 100%;display: -webkit-box; -webkit-line-clamp: 2;-webkit-box-orient: vertical;">
                                ${y.ytbInfo}
                            </span>
                            <span id="moreInfo">더보기</span>
<%--                            <a href="#">더보기...</a>--%>
                        </div>
                        <!-- 영상 설명 end -->

                        <!-- 좋아요 싫어요 추가 -->
<%--                        <div class="py-2 d-flex">--%>
<%--                            <h5 class="d-flex"><i class="feather-thumbs-up mr-2"></i><h6>${y.ytbLike}</h6></h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
<%--                            <h5 class="d-flex"><i class="feather-thumbs-down mr-2"></i><h6>n</h6></h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
<%--                            <h5 class="d-flex"><i class="feather-list mr-2"></i><h6>재생목록에 추가</h6></h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
<%--                        </div>--%>
                        <!-- 좋아요 싫어요 추가 end -->

                        <!-- 채널 버튼 -->
                        <div id="subsInfoContainer" class="py-2 border d-flex" style="width: 50%">
                            <input type="hidden" id="channelName" channelName="${y.ytbChannelName}">
                            <input type="hidden" id="toChannel" url="${y.ytbChannelUrl}">
                            <img class="rounded-circle ml-2 toChannel" src="${y.ytbChannelThumb}" alt="" style="width: 40px; height: 40px; cursor:pointer">
                            <div class="ml-3">
                                <span><h6 class="font-weight-bold toChannel" style="cursor: pointer">${y.ytbChannelName}</h6></span>
                            </div>

                        </div>
                        <!-- 채널 버튼 end -->
                    </div>

                    <!-- 댓글 -->
                    <div class="py-2 ml-2 mr-2 border-bottom" style="width: 100%">
                        <div id="replyContainer">
                            <!-- 댓글 작성 form -->
                            <!-- 댓글 출력 form -->
<%--                            <div class="py-2 mb-2 d-flex" style="width: 100%">--%>
<%--                                <img class="rounded-circle ml-2" src="${pageContext.request.contextPath}/resources/upload/profileImg/img.png" alt="" style="width: 38px; height: 38px">--%>
<%--                                <div class="ml-3">--%>
<%--                                    <span class="font-weight-bold">작성자이름</span>--%>
<%--                                    <span class="ml-3" style="color: gray">n일 전</span><br>--%>
<%--                                    <span>--%>
<%--                                        댓글 내용댓글 내용댓글 내용댓글 내용댓글 내용댓글 내용댓글 내용댓글 내용댓글 내용댓글 내용댓글 내용댓글 내용댓글 내용댓글 내용댓글 내용댓글 내용댓글 내용댓글 내용--%>
<%--                                    </span>--%>
<%--                                    <span class="ml-3" style="color:cornflowerblue">답글</span>--%>

<%--                                    <!-- 대댓글 -->--%>
<%--                                    <div>--%>
<%--                                        <div id="reIdx1" class="accordion mt-2">--%>
<%--                                            <a class="btn btn-link" type="button" data-toggle="collapse" data-target="#collapseOne" aria-expanded="true" aria-controls="1">답글 보기(...)</a>--%>
<%--                                        </div>--%>
<%--                                        <div id="collapseOne" class="collapse" aria-labelledby="headingOne" data-parent="#reIdx1">--%>
<%--                                            <div class="ml-3">--%>
<%--                                                <span class="font-weight-bold">작성자이름</span><span class="ml-3" style="color: gray">n일 전</span><br>--%>
<%--                                                <span>@<span>아이디</span>&nbsp;&nbsp;답글 답글 답글 답글 답글 답글 답글 답글 답글 답글</span>--%>
<%--                                            </div>--%>
<%--                                        </div>--%>
<%--                                    </div>--%>
<%--                                </div>--%>
<%--                            </div>--%>
                            <!-- 댓글 출력 form end -->
                        </div>
                    </div>
                    <!-- 댓글 end -->
                </div>
            </main>
            <!-- 메인 end-->

            <!-- 추천 영상 -->
            <aside class="col col-xl-4 col-lg-4 col-md-4 col-sm-4 col-12">
                <div id="recommendContainer" class="row ml-3">

                </div>
            </aside>
        </div>
    </div>
</div>
</body>
</html>
