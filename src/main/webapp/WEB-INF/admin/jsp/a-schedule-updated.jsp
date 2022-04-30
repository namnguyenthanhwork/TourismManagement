<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="card">
    <div class="card-header pb-0 px-3">
        <h5 class="mb-0">Cập nhật lịch trình</h5>
    </div>
    <div class="card-body pt-4 p-3">
        <form id="scheduleUpdatedForm" method="post">
            <div class="form-group">
                <label for="scheTitle">Tiêu đề</label>
                <input type="text" class="form-control" id="scheTitle" name="scheTitle" placeholder="Nhập tiêu đề...">
            </div>
            <div class="form-group">
                <label for="scheContent">Nội dung</label>
                <input type="text" class="form-control" id="scheContent" name="scheContent"
                    placeholder="Nhập nội dung...">
            </div>
            <button type="submit" id="scheduleUpdatedBtn" class="btn btn-primary">Chỉnh sửa</button>
        </form>
    </div>
</div>