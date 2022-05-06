<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="card">
    <div class="card-header pb-0 px-3">
        <h5 class="mb-0">Cập nhật mã giảm giá</h5>
    </div>
    <div class="card-body pt-4 p-3">
        <form id="saleUpdatedForm" method="post">
            <div class="form-group">
                <label for="saleFromDate">Ngày bắt đầu <span class="required">(*)</span></label>
                <input type="date" class="form-control" id="saleFromDate" name="saleFromDate"
                    placeholder="Nhập ngày bắt đầu..." required>
            </div>
            <div class="form-group">
                <label for="saleToDate">Ngày kết thúc <span class="required">(*)</span></label>
                <input type="date" class="form-control" id="saleToDate" name="saleToDate"
                    placeholder="Nhập ngày kết thúc..." required>
            </div>
            <div class="form-group">
                <label for="sperId">Phần trăm giảm giá <span class="required">(*)</span></label>
                <select name="sperId" id="sperId" class="multisteps-form__input form-control" required>
                </select>
            </div>
            <div class="form-group">
                <button type="submit" id="saleUpdatedBtn" class="btn btn-primary">Cập nhật</button>
                <div class="lds-ripple" id="loading">
                    <div></div>
                    <div></div>
                </div>
            </div>
        </form>
    </div>
</div>