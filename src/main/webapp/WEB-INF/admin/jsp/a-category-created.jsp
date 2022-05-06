<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="card">
    <div class="card-header pb-0 px-3">
        <h5 class="mb-0">Tạo mới loại tour</h5>
    </div>
    <div class="card-body pt-4 p-3">
        <form action="<c:url value='/quan-tri-vien/loai-tour' />" method="post" id="categoryCreatedForm">
            <div class="form-group">
                <label for="catName">Tên loại tour <span class="required">(*)</span></label>
                <input type="text" class="form-control" id="catName" name="catName" placeholder="Nhập tên loại tour..."
                    required>
            </div>
            <div class="form-group">
                <label for="storSlug">Loại kho <span class="required">(*)</span></label>
                <select name="storSlug" id="storSlug" class="multisteps-form__input form-control" required>
                </select>
            </div>
            <div class="form-group">
                <button id="categoryCreatedBtn" type="submit" class="btn btn-primary">Tạo mới</button>
                <div class="lds-ripple" id="loading">
                    <div></div>
                    <div></div>
                </div>
            </div>
        </form>
    </div>
</div>