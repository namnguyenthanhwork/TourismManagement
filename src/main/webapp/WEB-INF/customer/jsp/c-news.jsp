<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="vnt-content">
    <div class="vnt-main-top">
        <div id="vnt-navation" class="breadcrumb">
            <div class="wrapper">
                <div class="navation">
                    <ul itemscope="" itemtype="https://schema.org/BreadcrumbList">
                        <li itemprop="itemListElement" itemscope="" itemtype="https://schema.org/ListItem"><a
                                itemprop="item" title="Trang chủ" href="<c:url value='/' />"><span itemprop="name">Trang
                                    chủ</span></a>
                            <meta itemprop="position" content="1">
                        </li>
                        <li itemprop="itemListElement" itemscope="" itemtype="https://schema.org/ListItem"><a
                                itemprop="item" href="/tin-tuc" title="Tin tức"><span itemprop="name">Tin tức</span></a>
                            <meta itemprop="position" content="2">
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>

    <div class="wrapCont">
        <div class="wrapper">
            <div class="mda-archive">
                <h1 class="mda-archive-title"><a title="Tin tức" href="https://dulichviet.com.vn/tin-tuc">Tin tức</a>
                </h1>
                <div class="mda-archive-content">
                    <div style="text-align: justify;">
                        <div style="text-align: justify;"><a href="https://dulichviet.com.vn/tin-tuc"><strong>Tin tức Du
                                    lịch</strong></a> - Tin tức<strong> </strong>Du lịch 2022 cung cấp các
                            <em><strong>thông tin Du lịch Việt Nam, Thông tin Du lịch Thế Giới</strong></em>, các Sự
                            kiện Du lịch diễn ra trong ngày. Thông qua các bài viết được cập nhật liên tục, du khách có
                            thể nắm bắt thêm được nhiều thông tin hữu ích về du lịch.</div>
                    </div>
                </div>
            </div>
            <div class="vnt-news">
                <div class="row" id="ext_news">
                </div>
            </div>
            <div class="mda-more-box">
                <ul class="pagination" id ="pagination">
                </ul>
            </div>
        </div>
    </div>
</div>