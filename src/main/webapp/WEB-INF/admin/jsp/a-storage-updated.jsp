<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="card">
    <div class="card-header pb-0 px-3">
        <h5 class="mb-0">Cập nhật kho chứa</h5>
    </div>
    <div class="card-body pt-4 p-3">
        <form id="storageUpdatedForm" method="post">
            <div class="form-group">
                <label for="storName">Tên kho chứa <span class="required">(*)</span></label>
                <input type="text" class="form-control" id="storName" name="storName"
                    placeholder="Nhập tên kho chứa..." required>
            </div>
            <div class="form-group">
                <button type="submit" id="storageUpdatedBtn" class="btn btn-primary">Cập nhật</button>
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