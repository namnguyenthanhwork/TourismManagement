<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="vnt-content">

    <div class="vnt-main-top">

        <div id="vnt-navation" class="breadcrumb">
            <div class="wrapper">
                <div class="navation">
                    <ul>
                        <li><a title="Trang chủ" href="<c:url value='/' />"><span>Trang
                                    chủ</span></a>
                        </li>
                        <li><a href="/tin-tuc" title="Tin tức"><span>Tin tức</span></a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>

    <div class="wrapCont">
        <div class="wrapper">
            <div class="mda-archive">
                <h1 itemprop="name" class="mda-archive-title" id="newsTitle"></h1>

                <div class="mda-archive-content" itemprop="description">
                    <p><span style="font-size:16px;"><span style="font-family:Arial,Helvetica,sans-serif;"
                                id="newsDescription"></span></span></p>
                </div>
            </div>
            <div class="the-content desc" id="newsContent">
            </div>
            <div class="mb-4">
                <span class="mr-2">Lượt thích</span><i class="fa-solid fa-thumbs-up"></i><span id="likeAmount"
                    class="ml-1 text-danger"></span>
                <c:choose>
                    <c:when test="${pageContext.request.userPrincipal.name != null}">
                        <button type="button" class="ml-3" id="likeBtn">
                            Thích <i class="fa-solid fa-thumbs-up" onload="getLikeStatus()"></i></button>

                    </c:when>
                </c:choose>
            </div>
            <div class="mod-content horizontal-border" >
                <h3 class="mt-3">Bình luận khách hàng</h3>
                <div id="cusCommentInput" class="row m-2">
                    <c:choose>
                        <c:when test="${pageContext.request.userPrincipal.name != null}">
                            <textarea rows="2" cols="140" placeholder="Nhập nội dung..." id="cmtContent"
                                class="col-12 mt-3"></textarea>
                            <button id="addComment" class="btn btn-primary mt-3">Thêm bình luận mới</button>
                        </c:when>
                        <c:when test="${pageContext.request.userPrincipal.name == null}">
                            <p class="col-12 text-center text-danger">Bạn phải đăng nhập để có thể bình luận</p>
                        </c:when>
                    </c:choose>
                </div>
            </div>
            <div class="block-comment">
                <div id="cusCommentDetail" class="row p-4"></div>
            </div>
            <div class="box_mid">
                <div class="mid-title">
                    <div class="titleL">
                        <h3>Tin tức liên quan</h3>
                    </div>
                    <div class="titleR"></div>
                </div>
                <div class="mid-content width1 owl-carousel" id="relevantNews">
                </div>
            </div>
        </div>
    </div>

</div>