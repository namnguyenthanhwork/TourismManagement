<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Sidebar -->
<aside class="sidenav navbar navbar-vertical navbar-expand-xs border-0 border-radius-xl my-3 fixed-start ms-3"
    id="sidenav-main">
    <div class="sidenav-header">
        <i class="fas fa-times p-3 cursor-pointer text-secondary opacity-5 position-absolute end-0 top-0 d-none d-xl-none"
            aria-hidden="true" id="iconSidenav"></i>
        <a class="navbar-brand m-0" href="<c:url value='/nhan-vien/trang-chu'/>">
            <img src="../resources/common/img/logo-ct.png" class="navbar-brand-img h-100" alt="main_logo">
            <span class="ms-1 font-weight-bold">Du lịch OU</span>
        </a>
    </div>
    <hr class="horizontal dark mt-0">
    <div class="collapse navbar-collapse w-auto max-height-vh-100 h-100" id="sidenav-collapse-main">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a data-bs-toggle="collapse" href="#dashboardsExamples" class="nav-link" aria-expanded="false"
                    aria-controls="dashboardsExamples" role="button">
                    <div
                        class="icon icon-shape icon-sm shadow border-radius-md bg-white text-center d-flex align-items-center justify-content-center me-2">
                        <i class="fas fa-house-user"></i>
                    </div>
                    <span class="nav-link-text ms-1">Quản lý du lịch</span>
                </a>
                <div class="collapse" id="dashboardsExamples">
                    <ul class="nav ms-4 ps-3">
                        <li class="nav-item">
                            <a class="nav-link" href="<c:url value='/nhan-vien/dat-tour'/>">
                                <span class="sidenav-normal">Đặt tour</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="<c:url value='/nhan-vien/thanh-toan'/>">
                                <span class="sidenav-normal">Thanh toán</span>
                            </a>
                        </li>
                    </ul>
                </div>
            </li>

        </ul>
    </div>
</aside>
<!-- End Sidebar -->