<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="card multisteps-form__panel p-3 border-radius-xl bg-white">
    <h5 class="font-weight-bolder mb-0">Tạo mới tài khoản</h5>
    <p class="mb-0 text-sm">Vui lòng nhập đầy đủ thông tin bên dưới</p>
    <div class="multisteps-form__content">
        <form action="<c:url value='/quan-tri-vien/tai-khoan' />" method="post" enctype="multipart/form-data"
            id="formCreateAccount">
            <div class="row mt-3">
                <div class="col-12 col-sm-6">
                    <label for="accUsername">Tên tài khoản <span class="required">(*)</span></label>
                    <input class="multisteps-form__input form-control" type="text" id="accUsername" name="accUsername"
                        placeholder="Nhập tên tài khoản..." required>
                </div>
                <div class="col-12 col-sm-6 mt-3 mt-sm-0">
                    <label for="accPassword">Mật khẩu <span class="required">(*)</span></label>
                    <input type="password" class="multisteps-form__input form-control" id="accPassword"
                        name="accPassword" placeholder="Nhập mật khẩu..." required>
                </div>
            </div>
            <div class="row mt-3">
                <div class="col-12 col-sm-6">
                    <label for="accFirstName">Tên <span class="required">(*)</span></label>
                    <input type="text" class="multisteps-form__input form-control" id="accFirstName" name="accFirstName"
                        placeholder="Nhập tên..." required>
                </div>
                <div class="col-12 col-sm-6 mt-3 mt-sm-0">
                    <label for="accLastName">Họ và tên lót <span class="required">(*)</span></label>
                    <input type="text" class="multisteps-form__input form-control" id="accLastName" name="accLastName"
                        placeholder="Nhập họ và tên lót..." required>
                </div>
            </div>
            <div class="row mt-3">
                <div class="col-12 col-sm-6 col-md-4">
                    <label for="accSex">Nhập giới tính <span class="required">(*)</span></label>
                    <select name="accSex" id="accSex" class="multisteps-form__input form-control" required>
                        <option value="1">Nam</option>
                        <option value="0">Nữ</option>
                    </select>
                </div>
                <div class="col-12 col-sm-6 col-md-4 mt-3 mt-sm-0">
                    <label for="accPhoneNumber">Số điện thoại <span class="required">(*)</span></label>
                    <input type="text" class="multisteps-form__input form-control" id="accPhoneNumber"
                        name="accPhoneNumber" placeholder="Nhập số điện thoại..." required>
                </div>

                <div class="col-12 col-sm-6 col-md-4 mt-3 mt-sm-0">
                    <label for="accIdCard">Mã căn cước công dân <span class="required">(*)</span></label>
                    <input type="text" class="multisteps-form__input form-control" id="accIdCard" name="accIdCard"
                        placeholder="Nhập mã căn cước công dân.." required>
                </div>

            </div>
            <div class="row mt-3">
                <div class="col-12 col-sm-6 col-md-4">
                    <label for="accDateOfBirth">Nhập ngày sinh <span class="required">(*)</span></label>
                    <input class="form-control flatpickr-input" type="date" name="accDateOfBirth" id="accDateOfBirth"
                        placeholder="Please select start date" required>
                </div>
                <div class="col-12 col-sm-6 col-md-4 mt-3 mt-sm-0">
                    <label for="accAvatar">Upload hình</label>
                    <input type="file" name="accAvatar" class="form-control" id="accAvatar" required>
                </div>
                <div class="col-12 col-sm-6 col-md-4 mt-3 mt-sm-0">
                    <label for="roleName">Vai trò <span class="required">(*)</span></label>
                    <select name="roleSlug" id="roleName" class="multisteps-form__input form-control" required>
                    </select>
                </div>
            </div>
            <div class="button-row d-flex mt-4">
                <button id="accountCreatedBtn" type="submit" class="btn bg-gradient-dark ms-auto mb-0 js-btn-next"
                    type="button" title="Thêm">Tạo mới</button>
                <div id="overlayLoading"></div>
                <div class="lds-roller" id="loading">
                    <div></div>
                    <div></div>
                    <div></div>
                    <div></div>
                    <div></div>
                    <div></div>
                    <div></div>
                    <div></div>
                </div>
            </div>
        </form>
    </div>
</div>