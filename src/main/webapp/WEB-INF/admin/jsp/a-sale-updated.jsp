<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="card">
    <div class="card-header pb-0 px-3">
        <h5 class="mb-0">Cập nhật mã giảm giá</h5>
    </div>
    <div class="card-body pt-4 p-3">
        <form id="saleUpdatedForm" method="post">
            <div class="form-group">
                <label for="saleFromDate">Ngày bắt đầu</label>
                <input type="date" class="form-control" id="saleFromDate" name="saleFromDate"
                    placeholder="Nhập ngày bắt đầu...">
            </div>
            <div class="form-group">
                <label for="saleToDate">Ngày kết thúc</label>
                <input type="date" class="form-control" id="saleToDate" name="saleToDate"
                    placeholder="Nhập ngày kết thúc...">
            </div>
            <div class="form-group">
                <label for="sperPercent">Phần trăm giảm giá</label>
                <select name="sperPercent" id="sperPercent" class="multisteps-form__input form-control">
                </select>
            </div>
            <button type="submit" id="saleUpdatedBtn" class="btn btn-primary">Chỉnh sửa</button>
        </form>
    </div>
</div>