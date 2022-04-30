<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="card">
    <div class="card-header pb-0 px-3">
        <h5 class="mb-0">Cập nhật đặc điểm</h5>
    </div>
    <div class="card-body pt-4 p-3">
        <form id="featureUpdatedForm" method="post">
            <div class="form-group">
                <label for="feaName">Tên đặc điểm</label>
                <input type="text" class="form-control" id="feaName" name="feaName" placeholder="Nhập tên đặc điểm...">
            </div>
            <button type="submit" id="featureUpdatedBtn" class="btn btn-primary">Chỉnh sửa</button>
        </form>
    </div>
</div>