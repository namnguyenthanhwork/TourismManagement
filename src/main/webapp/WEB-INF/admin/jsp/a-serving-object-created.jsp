<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="card">
    <div class="card-header pb-0 px-3">
        <h5 class="mb-0">Tạo mới đối tượng</h5>
    </div>
    <div class="card-body pt-4 p-3">
        <form action="<c:url value='/quan-tri-vien/doi-tuong-phuc-vu' />" method="post">
            <div class="form-group">
                <label for="svoName">Tên đối tượng (*)</label>
                <input type="text" class="form-control" id="svoName" name="svoName" placeholder="Nhập tên đối tượng...">
            </div>
            <div class="form-group">
                <button id="servingObjectCreatedBtn" type="submit" class="btn btn-primary">Tạo mới</button>
                <div class="lds-ripple" id="loading"><div></div><div></div></div>
            </div>
        </form>
    </div>
</div>