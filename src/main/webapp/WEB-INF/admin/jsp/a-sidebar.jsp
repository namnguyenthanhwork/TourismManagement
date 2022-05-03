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
                <hr class="horizontal light">
                <h6 class="ps-4  ms-2 text-uppercase text-xs font-weight-bolder opacity-6">Quản lý tài khoản</h6>
            </li>
            <li class="nav-item">
                <a data-bs-toggle="collapse" href="#dashboardsExamples" class="nav-link" aria-expanded="false"
                    aria-controls="dashboardsExamples" role="button">
                    <div
                        class="icon icon-shape icon-sm shadow border-radius-md bg-white text-center d-flex align-items-center justify-content-center me-2">
                        <i class="fas fa-house-user"></i>
                    </div>
                    <span class="nav-link-text ms-1">Tài khoản chung</span>
                </a>
                <div class="collapse" id="dashboardsExamples">
                    <ul class="nav ms-4 ps-3">
                        <li class="nav-item">
                            <a class="nav-link" href="<c:url value='/quan-tri-vien/tai-khoan'/>">
                                <span class="sidenav-normal">Tài khoản</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="<c:url value='/quan-tri-vien/vai-tro'/>">
                                <span class="sidenav-normal">Vai trò</span>
                            </a>
                        </li>
                    </ul>
                </div>
            </li>
            <li class="nav-item">
                <hr class="horizontal light">
                <h6 class="ps-4  ms-2 text-uppercase text-xs font-weight-bolder opacity-6">Quản lý thanh toán</h6>
            </li>
            <li class="nav-item">
                <a data-bs-toggle="collapse" href="#dashboardsExamples1" class="nav-link" aria-expanded="false"
                    aria-controls="dashboardsExamples" role="button">
                    <div
                        class="icon icon-shape icon-sm shadow border-radius-md bg-white text-center d-flex align-items-center justify-content-center me-2">
                        <i class="fas fa-house-user"></i>
                    </div>
                    <span class="nav-link-text ms-1">Thanh toán</span>
                </a>
                <div class="collapse" id="dashboardsExamples1">
                    <ul class="nav ms-4 ps-3">
                        <li class="nav-item">
                            <a class="nav-link" href="<c:url value='/quan-tri-vien/hoa-don'/>">
                                <span class="sidenav-normal">Hoá đơn</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="<c:url value='/quan-tri-vien/hinh-thuc-thanh-toan'/>">
                                <span class="sidenav-normal">Loại thanh toán</span>
                            </a>
                        </li>
                    </ul>
                </div>
            </li>
            <li class="nav-item mt-3">
                <h6 class="ps-4 ms-2 text-uppercase text-xs font-weight-bolder opacity-6">Quản lý bài viết</h6>
            </li>
            <li class="nav-item"><a data-bs-toggle="collapse" href="#ecommerceExamples" class="nav-link "
                    aria-expanded="false" aria-controls="ecommerceExamples" role="button">
                    <div
                        class="icon icon-shape icon-sm shadow border-radius-md bg-white text-center d-flex align-items-center justify-content-center  me-2">
                        <i class="fas fa-house-user"></i>
                    </div>
                    <span class="nav-link-text ms-1">Bài viết</span>
                </a>
                <div class="collapse false" id="ecommerceExamples">
                    <ul class="nav ms-4 ps-3">
                        <li class="nav-item ">
                            <a class="nav-link" href="<c:url value='/quan-tri-vien/tin-tuc'/>">
                                <span class="sidenav-normal">Tin tức (x)</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" data-bs-toggle="collapse" aria-expanded="false" href="#productsExample">
                                <span class="sidenav-normal">Tour du lịch<b class="caret"></b></span>
                            </a>
                            <div class="collapse" id="productsExample">
                                <ul class="nav nav-sm flex-column">
                                    <li class="nav-item">
                                        <a class="nav-link" href="<c:url value='/quan-tri-vien/tour-du-lich'/>">
                                            <span class="sidenav-normal">Tour du lịch (x)</span>
                                        </a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" href="<c:url value='/quan-tri-vien/kho-chua'/>">
                                            <span class="sidenav-normal">Kho chứa</span>
                                        </a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" href="<c:url value='/quan-tri-vien/loai-tour'/>">
                                            <span class="sidenav-normal">Loại tour</span>
                                        </a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" href="<c:url value='/quan-tri-vien/ngay-khoi-hanh'/>">
                                            <span class="sidenav-normal">Ngày khởi hành</span>
                                        </a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link"
                                            href="<c:url value='/quan-tri-vien/dac-diem-ngay-khoi-hanh'/>">
                                            <span class="sidenav-normal">Đặc điểm</span>
                                        </a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" href="<c:url value='/quan-tri-vien/ghi-chu'/>">
                                            <span class="sidenav-normal">Ghi chú</span>
                                        </a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" href="<c:url value='/quan-tri-vien/lich-trinh'/>">
                                            <span class="sidenav-normal">Lịch trình</span>
                                        </a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" href="<c:url value='/quan-tri-vien/dich-vu'/>">
                                            <span class="sidenav-normal">Dịch vụ</span>
                                        </a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" href="<c:url value='/quan-tri-vien/doi-tuong-phuc-vu'/>">
                                            <span class="sidenav-normal">Đối tượng phục vụ</span>
                                        </a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" href="<c:url value='/quan-tri-vien/hinh-thu-nho'/>">
                                            <span class="sidenav-normal">Hình thu nhỏ</span>
                                        </a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link"
                                            href="<c:url value='/quan-tri-vien/phuong-tien-di-chuyen'/>">
                                            <span class="sidenav-normal">Phương tiện</span>
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
                <h6 class="ps-4  ms-2 text-uppercase text-xs font-weight-bolder opacity-6">Quản lý khuyến mãi</h6>
            </li>
            <li class="nav-item">
                <a data-bs-toggle="collapse" href="#dashboardsExamples2" class="nav-link" aria-expanded="false"
                    aria-controls="dashboardsExamples" role="button">
                    <div
                        class="icon icon-shape icon-sm shadow border-radius-md bg-white text-center d-flex align-items-center justify-content-center me-2">
                        <i class="fas fa-house-user"></i>
                    </div>
                    <span class="nav-link-text ms-1">Khuyến mãi</span>
                </a>
                <div class="collapse" id="dashboardsExamples2">
                    <ul class="nav ms-4 ps-3">
                        <li class="nav-item">
                            <a class="nav-link" href="<c:url value='/quan-tri-vien/giam-gia'/>">
                                <span class="sidenav-normal">Giảm giá</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="<c:url value='/quan-tri-vien/phan-tram-giam-gia'/>">
                                <span class="sidenav-normal">Phầm trăm giảm giá</span>
                            </a>
                        </li>
                    </ul>
                </div>
            </li>
            <li class="nav-item">
                <hr class="horizontal light">
                <h6 class="ps-4  ms-2 text-uppercase text-xs font-weight-bolder opacity-6">Thống kê - Báo cáo</h6>
            </li>
            <li class="nav-item">
                <a data-bs-toggle="collapse" href="#dashboardsExamples4" class="nav-link" aria-expanded="false"
                    aria-controls="dashboardsExamples" role="button">
                    <div
                        class="icon icon-shape icon-sm shadow border-radius-md bg-white text-center d-flex align-items-center justify-content-center me-2">
                        <i class="fas fa-house-user"></i>
                    </div>
                    <span class="nav-link-text ms-1">Dữ liệu</span>
                </a>
                <div class="collapse" id="dashboardsExamples4">
                    <ul class="nav ms-4 ps-3">
                        <li class="nav-item">
                            <a class="nav-link" href="<c:url value='/quan-tri-vien/thong-ke'/>">
                                <span class="sidenav-normal">Thống kê</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="<c:url value='/quan-tri-vien/bao-cao'/>">
                                <span class="sidenav-normal">Báo cáo</span>
                            </a>
                        </li>
                    </ul>
                </div>
            </li>
        </ul>
    </div>
</aside>
<!-- End Sidebar -->