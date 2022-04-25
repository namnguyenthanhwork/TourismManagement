<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Sidebar -->
<aside class="sidenav navbar navbar-vertical navbar-expand-xs border-0 border-radius-xl my-3 fixed-start ms-3"
       id="sidenav-main">
    <div class="sidenav-header">
        <i class="fas fa-times p-3 cursor-pointer text-secondary opacity-5 position-absolute end-0 top-0 d-none d-xl-none"
           aria-hidden="true" id="iconSidenav"></i>
        <a class="navbar-brand m-0" href="/TourismManagement/quan-tri-vien/trang-chu">
            <img src="../resources/common/img/logo-ct.png" class="navbar-brand-img h-100" alt="main_logo">
            <span class="ms-1 font-weight-bold">Du lịch OU</span>
        </a>
    </div>
    <hr class="horizontal dark mt-0">
    <div class="collapse navbar-collapse w-auto max-height-vh-100 h-100" id="sidenav-collapse-main">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a data-bs-toggle="collapse" href="#dashboardsExamples" class="nav-link"
                   aria-expanded="false" aria-controls="dashboardsExamples" role="button">
                    <div class="icon icon-shape icon-sm shadow border-radius-md bg-white text-center d-flex align-items-center justify-content-center me-2">
                        <i class="fas fa-house-user"></i>
                    </div>
                    <span class="nav-link-text ms-1">Cates 1</span>
                </a>
                <div class="collapse" id="dashboardsExamples">
                    <ul class="nav ms-4 ps-3">
                        <li class="nav-item">
                            <a class="nav-link active" href="javascript:;">
                                <span class="sidenav-normal">Item 1</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="javascript:;">
                                <span class="sidenav-normal">Item 2</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="javascript:;">
                                <span class="sidenav-normal">Item 3</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" data-bs-toggle="collapse" aria-expanded="false"
                               href="#vrExamples">
                                <span class="sidenav-normal">List Item 4<b class="caret"></b></span>
                            </a>
                            <div class="collapse" id="vrExamples">
                                <ul class="nav nav-sm flex-column">
                                    <li class="nav-item">
                                        <a class="nav-link" href="javascript:;">
                                            <span class="sidenav-normal">Sub Item 1</span>
                                        </a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" href="javascript:;">
                                            <span class="sidenav-normal">Sub Item 2</span>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </li>
                    </ul>
                </div>
            </li>
            <li class="nav-item mt-3">
                <h6 class="ps-4 ms-2 text-uppercase text-xs font-weight-bolder opacity-6">Title cates</h6>
            </li>
            <li class="nav-item">
                <a data-bs-toggle="collapse" href="#applicationsExamples" class="nav-link "
                   aria-expanded="false" aria-controls="applicationsExamples" role="button">
                    <div class="icon icon-shape icon-sm shadow border-radius-md bg-white text-center d-flex align-items-center justify-content-center  me-2">
                        <i class="fas fa-house-user"></i>
                    </div>
                    <span class="nav-link-text ms-1">Cates 3</span>
                </a>
                <div class="collapse" id="applicationsExamples">
                    <ul class="nav ms-4 ps-3">
                        <li class="nav-item">
                            <a class="nav-link " href="javascript:;">
                                <span class="sidenav-normal">Item 1</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="javascript:;">
                                <span class="sidenav-normal">Item 2</span>
                            </a>
                        </li>
                    </ul>
                </div>
            </li>
            <li class="nav-item"><a data-bs-toggle="collapse" href="#ecommerceExamples" class="nav-link "
                                    aria-expanded="false" aria-controls="ecommerceExamples" role="button">
                <div class="icon icon-shape icon-sm shadow border-radius-md bg-white text-center d-flex align-items-center justify-content-center  me-2">
                    <i class="fas fa-house-user"></i>
                </div>
                <span class="nav-link-text ms-1">Cates 4</span>
            </a>
                <div class="collapse false" id="ecommerceExamples">
                    <ul class="nav ms-4 ps-3">
                        <li class="nav-item ">
                            <a class="nav-link" href="javascript:;">
                                <span class="sidenav-normal">Item 1</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" data-bs-toggle="collapse" aria-expanded="false"
                               href="#productsExample">
                                <span class="sidenav-normal">List Item 2<b class="caret"></b></span>
                            </a>
                            <div class="collapse" id="productsExample">
                                <ul class="nav nav-sm flex-column">
                                    <li class="nav-item">
                                        <a class="nav-link" href="javascript:;">
                                            <span class="sidenav-normal">Sub Item 1</span>
                                        </a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" href="javascript:;">
                                            <span class="sidenav-normal">Sub Item 2</span>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </li>
                    </ul>
                </div>
            </li>
            <li class="nav-item"><a data-bs-toggle="collapse" href="#authExamples" class="nav-link "
                                    aria-controls="authExamples" role="button" aria-expanded="false">
                <div class="icon icon-shape icon-sm shadow border-radius-md bg-white text-center d-flex align-items-center justify-content-center  me-2">
                    <i class="fas fa-house-user"></i>
                </div>
                <span class="nav-link-text ms-1">Cates 5</span>
            </a>
                <div class="collapse" id="authExamples">
                    <ul class="nav ms-4 ps-3">
                        <li class="nav-item ">
                            <a class="nav-link" data-bs-toggle="collapse" aria-expanded="false"
                               href="#signinExample">
                                <span class="sidenav-normal">List Item 1<b class="caret"></b></span>
                            </a>
                            <div class="collapse" id="signinExample">
                                <ul class="nav nav-sm flex-column">
                                    <li class="nav-item">
                                        <a class="nav-link" href="javascript:;">
                                            <span class="sidenav-normal">Sub Item 1</span>
                                        </a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link " href="javascript:;">
                                            <span class="sidenav-normal">Sub Item 2</span>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </li>
                    </ul>
                </div>
            </li>
            <li class="nav-item">
                <hr class="horizontal light">
                <h6 class="ps-4  ms-2 text-uppercase text-xs font-weight-bolder opacity-6">Title cates</h6>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="javascript:;">
                    <div class="icon icon-shape icon-sm shadow border-radius-md bg-white text-center  me-2 d-flex align-items-center justify-content-center">
                        <i class="fas fa-house-user"></i>
                    </div>
                    <span class="nav-link-text ms-1">Item 1</span>
                </a>
            </li>
        </ul>
    </div>
</aside>
<!-- End Sidebar -->