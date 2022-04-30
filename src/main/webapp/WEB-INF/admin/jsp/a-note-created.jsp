<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="card">
    <div class="card-header pb-0 px-3">
        <h5 class="mb-0">Thêm ghi chú</h5>
    </div>
    <div class="card-body pt-4 p-3">
        <form action="<c:url value='/quan-tri-vien/ghi-chu' />" method="post">
            <div class="form-group">
                <label for="noteTitle">Tiêu đề</label>
                <input type="text" class="form-control" id="noteTitle" name="noteTitle" placeholder="Nhập tiêu đề...">
            </div>
            <div class="form-group">
                <label for="noteContent">Nội dung</label>
                <input type="text" class="form-control" id="noteContent" name="noteContent"
                    placeholder="Nhập nội dung...">
            </div>
            <button id="noteCreatedBtn" type="submit" class="btn btn-primary">Tạo mới</button>
        </form>
    </div>
</div>