<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="row">
    <div class="col-12">
        <div class="card mb-4">
            <div class="card-header pb-0 d-flex justify-content-between align-items-center">
                <h5>Quản lý tài khoản</h5>
                <a href="/TourismManagement/quan-tri-vien/tai-khoan/tao-moi"
                    class="badge badge-success p-2 text-capitalize mb-2">Thêm tài khoản</a>
            </div>
            <div class="card-body px-0 pt-0 pb-2">
                <div class="table-responsive p-0">
                    <table class="table align-items-center mb-0">
                        <thead>
                            <tr>
                                <th class="text-center text-uppercase text-dark text-xxs font-weight-bolder opacity-7">
                                    Tuỳ chỉnh</th>
                                <th class="text-uppercase text-dark text-xxs font-weight-bolder opacity-7">ID
                                </th>
                                <th class="text-uppercase text-dark text-xxs font-weight-bolder opacity-7 ps-2">
                                    Tên tài khoản</th>
                                <th class="text-center text-uppercase text-dark text-xxs font-weight-bolder opacity-7">
                                    Mật khẩu</th>
                                <th class="text-center text-uppercase text-dark text-xxs font-weight-bolder opacity-7">
                                    Tên</th>
                                <th class="text-center text-uppercase text-dark text-xxs font-weight-bolder opacity-7">
                                    Họ và tên lót</th>
                                <th class="text-center text-uppercase text-dark text-xxs font-weight-bolder opacity-7">
                                    Giới tính</th>
                                <th class="text-center text-uppercase text-dark text-xxs font-weight-bolder opacity-7">
                                    Mã nhân viên</th>
                                <th class="text-center text-uppercase text-dark text-xxs font-weight-bolder opacity-7">
                                    Số điện thoại</th>
                                <th class="text-center text-uppercase text-dark text-xxs font-weight-bolder opacity-7">
                                    Ngày sinh</th>
                                <th class="text-center text-uppercase text-dark text-xxs font-weight-bolder opacity-7">
                                    Ngày tham gia</th>
                                <th class="text-center text-uppercase text-dark text-xxs font-weight-bolder opacity-7">
                                    Ảnh đại diện</th>
                                <th class="text-center text-uppercase text-dark text-xxs font-weight-bolder opacity-7">
                                    Truy cập lần cuối</th>
                                <th class="text-center text-uppercase text-dark text-xxs font-weight-bolder opacity-7">
                                    Vai trò</th>
                            </tr>
                        </thead>
                        <tbody id="accountInfo">

                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>