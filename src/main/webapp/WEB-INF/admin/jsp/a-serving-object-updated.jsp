<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="card">
    <div class="card-header pb-0 px-3">
        <h5 class="mb-0">Cập nhật đối tượng</h5>
    </div>
    <div class="card-body pt-4 p-3">
        <form id="servingObjectUpdatedForm" method="post">
            <div class="form-group">
                <label for="svoName">Tên đối tượng</label>
                <input type="text" class="form-control" id="svoName" name="svoName" placeholder="Nhập tên đối tượng...">
            </div>
            <button type="submit" id="servingObjectUpdatedBtn" class="btn btn-primary">Chỉnh sửa</button>
        </form>
    </div>
</div>