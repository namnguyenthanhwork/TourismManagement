<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<main class="main-content mt-0">
    <div class="page-header">
        <div class="container">
            <div class="row">
                <div class="col-xl-4 col-lg-5 col-md-6 d-flex flex-column mx-auto">
                    <div class="card card-plain mt-8">
                        <div class="card-header pb-0 text-left bg-transparent">
                            <h4 class="font-weight-bolder text-info text-gradient">
                                Quản lý du lịch - Đăng nhập
                            </h4>
                        </div>
                        <div class="card-body">
                            <form action="<c:url value='/auth/dang-nhap' />" method="post">
                            <label for="username">Tên đăng nhập</label>
                            <div class="mb-3">
                                <input type="text" class="form-control" name="username" id="username"
                                    placeholder="Nhập tài khoản" value="username1"></div>
                            <label for="password">Mật khẩu</label>
                            <div class="mb-3 position-relative">
                                <input type="password" class="form-control" name="password" id="password"
                                    placeholder="Nhập mật khẩu" value="12345678">
                                <div class="show-password">
                                    <i class="far fa-eye"></i>
                                </div>
                            </div>
                            <div class="form-check form-switch">
                                <input class="form-check-input" type="checkbox" id="rememberMe">
                                <label class="form-check-label" for="rememberMe">Ghi nhớ đăng nhập</label>
                            </div>
                            <div class=" text-center">
                                <button type="submit" class="btn bg-gradient-info w-100 mt-4 mb-0" id="signin">Đăng
                                    nhập
                                </button>
                            </div>
                            </form>
                        </div>
                        <div class="card-footer text-center pt-0 px-lg-2 px-1">
                            <p class="mb-4 text-sm mx-auto"> Chưa có tài khoản? <a
                                    href="<c:url value='/dang-ki-tai-khoan'/>"
                                    class="text-info text-gradient font-weight-bold">Đăng ký</a> </p>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="oblique position-absolute top-0 h-100 d-md-block d-none me-n4">
                        <div class="oblique-image bg-cover position-absolute fixed-top ms-auto h-100 z-index-0 ms-n6"
                            style="background-image:url('https://res.cloudinary.com/ou-project/image/upload/v1651914422/Homepage%20Admin/curved6_ziqjvo.jpg')"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <footer class="footer py-5">
        <div class="container">
            <div class="row">
                <div class="col-12 mb-4 mx-auto text-center">
                    <a href="javascript:;" class="text-secondary me-xl-5 me-3 mb-sm-0 mb-2">
                        Dự án quản lý du lịch - Lập trình Java
                    </a>
                </div>
                <div class="col-lg-8 mx-auto text-center mb-4 mt-2">
                    <a href="javascript:;" class="text-secondary me-xl-4 me-4">
                        <span class="text-lg fab fa-dribbble"></span>
                    </a>
                    <a href="javascript:;" class="text-secondary me-xl-4 me-4">
                        <span class="text-lg fab fa-twitter"></span>
                    </a>
                    <a href="javascript:;" class="text-secondary me-xl-4 me-4">
                        <span class="text-lg fab fa-instagram"></span>
                    </a>
                    <a href="javascript:;" class="text-secondary me-xl-4 me-4">
                        <span class="text-lg fab fa-pinterest"></span>
                    </a>
                    <a href="javascript:;" class="text-secondary me-xl-4 me-4">
                        <span class="text-lg fab fa-github"></span>
                    </a>
                </div>
            </div>
            <div class="row">
                <div class="col-8 mx-auto text-center mt-1">
                    <p class="mb-0 text-secondary">
                        © <a href="javascript:;">Trường Đại học Mở Tp.HCM</a>
                        - coded by Trung Kiên & Thành Nam.
                    </p>
                </div>
            </div>
        </div>
    </footer>
</main>