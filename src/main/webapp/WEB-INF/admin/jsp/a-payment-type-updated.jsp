<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="card">
	<div class="card-header pb-0 px-3">
		<h5 class="mb-0">Cập nhật loại thanh toán</h5>
	</div>
	<div class="card-body pt-4 p-3">
		<form id="paymentTypeUpdatedForm" method="post">
			<div class="form-group">
				<label for="paytName">Tên hình thức thanh toán (*)</label>
				<input type="text" class="form-control" id="paytName" name="paytName" placeholder="Nhập tên hình thức thanh toán...">
			</div>
			<div><
				<button type="submit" id="paymentTypeUpdatedBtn" class="btn btn-primary">Cập nhật</button>
			</div>
		</form>
	</div>
</div>