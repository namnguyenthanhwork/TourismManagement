<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="card multisteps-form__panel p-3 border-radius-xl bg-white">
    <h5 class="font-weight-bolder mb-0">Thêm tài khoản</h5>
    <p class="mb-0 text-sm">Vui lòng nhập đầy đủ thông tin bên dưới</p>
    <div class="multisteps-form__content">
        <form action="<c:url value='/quan-tri-vien/tai-khoan' />" method="post">
            <div class="row mt-3">
                <div class="col-12 col-sm-6">
                    <label for="accUsername">Tên tài khoản</label>
                    <input class="multisteps-form__input form-control" type="text" id="accUsername" name="accUsername"
                        placeholder="Nhập tên tài khoản...">
                </div>
                <div class="col-12 col-sm-6 mt-3 mt-sm-0">
                    <label for="accPassword">Mật khẩu</label>
                    <input type="password" class="multisteps-form__input form-control" id="accPassword"
                        name="accPassword" placeholder="Nhập mật khẩu...">
                </div>
            </div>
            <div class="row mt-3">
                <div class="col-12 col-sm-6">
                    <label for="accFirstName">Tên</label>
                    <input type="text" class="multisteps-form__input form-control" id="accFirstName" name="accFirstName"
                        placeholder="Nhập tên...">
                </div>
                <div class="col-12 col-sm-6 mt-3 mt-sm-0">
                    <label for="accLastName">Họ và tên lót</label>
                    <input type="text" class="multisteps-form__input form-control" id="accLastName" name="accLastName"
                        placeholder="Nhập họ và tên lót...">
                </div>
            </div>
            <div class="row mt-3">
                <div class="col-12 col-sm-6 col-md-4">
                    <label for="accSex">Nhập giới tính</label>
                    <select name="accSex" id="accSex" class="multisteps-form__input form-control">
                        <option value="1">Nam</option>
                        <option value="0">Nữ</option>
                    </select>
                </div>
                <div class="col-12 col-sm-6 col-md-4 mt-3 mt-sm-0">
                    <label for="accPhoneNumber">Số điện thoại</label>
                    <input type="text" class="multisteps-form__input form-control" id="accPhoneNumber"
                        name="accPhoneNumber" placeholder="Nhập số điện thoại...">
                </div>
                <div class="col-12 col-sm-6 col-md-4">
                    <label for="accDateOfBirth">Nhập ngày sinh</label>
                    <input class="form-control flatpickr-input" type="date" name="accDateOfBirth" id="accDateOfBirth"
                           placeholder="Please select start date">
                </div>
            </div>
            <div class="row mt-3">
                <div class="col-12 col-sm-6 col-md-4 mt-3 mt-sm-0">
                    <label for="accAvatar">Upload hình</label>
                    <input type="file" name="accAvatar" class="form-control" id="accAvatar">
                </div>
                <div class="col-12 col-sm-6 col-md-4 mt-3 mt-sm-0">
                    <label for="roleName">Vai trò</label>
                    <select name="roleName" id="roleName" class="multisteps-form__input form-control">
                    </select>
                </div>
            </div>
            <div class="button-row d-flex mt-4">
                <button id="accountCreatedBtn" type="submit" class="btn bg-gradient-dark ms-auto mb-0 js-btn-next"
                    type="button" title="Thêm">Thêm</button>
            </div>
        </form>
    </div>
</div>