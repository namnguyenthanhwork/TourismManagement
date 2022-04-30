<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="card">
    <div class="card-header pb-0 px-3">
        <h5 class="mb-0">Thêm ngày khởi hành</h5>
    </div>
    <div class="card-body pt-4 p-3">
        <form action="<c:url value='/quan-tri-vien/ngay-khoi-hanh' />" method="post">
            <div class="form-group">
                <label for="dptDate">Ngày khởi hành</label>
                <input type="date" class="form-control" id="dptDate" name="dptDate" placeholder="Nhập ngày khởi hành...">
            </div>
            <div class="form-group">
                <label for="feaSlug">Đặc điểm</label>
                <select name="feaSlug" id="feaSlug" class="multisteps-form__input form-control">
                </select>
            </div>
            <button id="departureDateCreatedBtn" type="submit" class="btn btn-primary">Tạo mới</button>
        </form>
    </div>
</div>