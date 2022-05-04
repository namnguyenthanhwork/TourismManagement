<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="card">
    <div class="card-header pb-0 px-3">
        <h5 class="mb-0">Tạo mới phần trăm giảm giá</h5>
    </div>
    <div class="card-body pt-4 p-3">
        <form action="<c:url value='/quan-tri-vien/phan-tram-giam-gia' />" method="post">
            <div class="form-group">
                <label for="sperPercent">Phần trăm giảm giá (*)</label>
                <input type="number" class="form-control" id="sperPercent" name="sperPercent"
                    placeholder="Nhập phần trăm giảm giá...">
            </div>
            <div class="form-group">
                <button id="salePercentCreatedBtn" type="submit" class="btn btn-primary">Tạo mới</button>
                <div class="lds-ripple" id="loading"><div></div><div></div></div>
            </div>
        </form>
    </div>
</div>