<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="vnt-content">

    <div class="vnt-main-top">

        <div id="vnt-navation" class="breadcrumb">
            <div class="wrapper">
                <div class="navation">
                    <ul itemscope="" itemtype="https://schema.org/BreadcrumbList">
                        <li itemprop="itemListElement" itemscope="" itemtype="https://schema.org/ListItem"><a
                                itemprop="item" title="Trang chủ" href="/"><span itemprop="name">Trang chủ</span></a>
                            <meta itemprop="position" content="1">
                        </li>
                        <li itemprop="itemListElement" itemscope="" itemtype="https://schema.org/ListItem"><a
                                itemprop="item" href="/tin-tuc"><span itemprop="name">Tin tức</span></a>
                            <meta itemprop="position" content="2">
                        </li>
                        <li itemprop="itemListElement" itemscope="" itemtype="https://schema.org/ListItem"><a
                                itemprop="item"
                                href="https://dulichviet.com.vn/tin-tuc/tham-quan-du-lich-nhat-ban-mua-he-vua-tiet-kiem-vua-soi-dong"><span
                                    itemprop="name">Tham quan du lịch Nhật Bản mùa hè vừa tiết kiệm vừa sôi
                                    động</span></a>
                            <meta itemprop="position" content="4">
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
                    <p><span style="font-size:16px;"><span style="font-family:Arial,Helvetica,sans-serif;" id="newsDescrip  tion"></span></span></p>
                </div>
            </div>
            <div class="the-content desc" id="newsContent">
            </div>

            <div>
                <i class="fa-solid fa-thumbs-up" ></i><span id="likeAmount"></span>
                <c:choose>
                    <c:when test="${pageContext.request.userPrincipal.name != null}">
                        <button type="button" class="btn" id="likeBtn">Thích</button>
                    </c:when>
                </c:choose>
            </div>

            <div class="mod-content row">
                <h1>Bình luận khách hàng</h1>
                <div id="cusCommentInput" class="row">
                    <c:choose>
                        <c:when test="${pageContext.request.userPrincipal.name != null}">
                            <textarea rows="3" cols="140" placeholder="Nội dung..." id="cmtContent"></textarea>
                            <button id="addComment">Thêm bình luận mới</button>
                        </c:when>
                        <c:when test="${pageContext.request.userPrincipal.name == null}">
                            <p>Bạn phải đăng nhập để có thể bình luận</p>
                        </c:when>
                    </c:choose>
                </div>
                <h1 >Thông tin các bình luận của khách hàng</h1>
                <div id="cusCommentDetail" class="row">
                </div>
            </div>

            <div class="clear"></div>
            <div class="box_mid">
                <div class="mid-title">
                    <div class="titleL">
                        <h2>TIN TỨC LIÊN QUAN</h2>
                    </div>
                    <div class="titleR"></div>
                </div>
                <div class="mid-content width1 owl-carousel" id="relevantNews">
                </div>
            </div>
        </div>
    </div>

</div>