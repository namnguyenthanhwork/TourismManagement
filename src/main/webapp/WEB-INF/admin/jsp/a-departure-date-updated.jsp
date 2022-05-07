<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="card">
    <div class="card-header pb-0 px-3">
        <h5 class="mb-0">Cập nhật ngày khởi hành</h5>
    </div>
    <div class="card-body pt-4 p-3">
        <form id="departureDateUpdatedForm" method="post">
            <div class="form-group">
                <label for="dptDate">Ngày khởi hành <span class="required">(*)</span></label>
                <input type="date" class="form-control" id="dptDate" name="dptDate" placeholder="Nhập ngày khởi hành..."
                    required>
            </div>
            <div class="form-group">
                <label for="feaSlug">Đặc điểm <span class="required">(*)</span></label>
                <select name="feaSlug" id="feaSlug" class="multisteps-form__input form-control" required>
                </select>
            </div>
            <div>
                <button type="submit" id="departureDateUpdatedBtn" class="btn btn-primary">Cập nhật</button>
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