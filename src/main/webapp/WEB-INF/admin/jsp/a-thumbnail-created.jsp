<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="card">
    <div class="card-header pb-0 px-3">
        <h5 class="mb-0">Tạo mới hình thu nhỏ</h5>
    </div>
    <div class="card-body pt-4 p-3">
        <form action="<c:url value='/quan-tri-vien/hinh-thu-nho' />" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="thumImage">Upload hình (*)</label>
                <input type="file" name="thumImage" class="form-control" id="thumImage">
            </div>
            <div class="form-group">
                <label for="tourSlug">Tour du lịch (*)</label>
                <select name="tourSlug" id="tourSlug" class="multisteps-form__input form-control">
                </select>
            </div>
            <div class="form-group">
                <button id="thumbnailCreatedBtn" type="submit" class="btn btn-primary">Tạo mới</button>
                <div class="lds-ripple" id="loading"><div></div><div></div></div>
            </div>
        </form>
    </div>
</div>