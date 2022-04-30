<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="card">
    <div class="card-header pb-0 px-3">
        <h5 class="mb-0">Thêm phương tiện</h5>
    </div>
    <div class="card-body pt-4 p-3">
        <form action="<c:url value='/quan-tri-vien/phuong-tien-di-chuyen' />" method="post">
            <div class="form-group">
                <label for="tranName">Tên phương tiện</label>
                <input type="text" class="form-control" id="tranName" name="tranName"
                    placeholder="Nhập tên phương tiện...">
            </div>
            <button id="transportCreatedBtn" type="submit" class="btn btn-primary">Tạo mới</button>
        </form>
    </div>
</div>