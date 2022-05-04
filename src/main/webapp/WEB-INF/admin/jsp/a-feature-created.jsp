<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="card">
	<div class="card-header pb-0 px-3">
		<h5 class="mb-0">Tạo mới đặc điểm</h5>
	</div>
	<div class="card-body pt-4 p-3">
		<form action="<c:url value='/quan-tri-vien/dac-diem-ngay-khoi-hanh' />" method="post">
			<div class="form-group">
				<label for="feaName">Tên đặc điểm (*)</label>
				<input type="text" class="form-control" id="feaName" name="feaName" placeholder="Nhập tên đặc điểm...">
			</div>
			<div class="form-group">
				<button id="featureCreatedBtn" type="submit" class="btn btn-primary">Tạo mới</button>
				<div class="lds-ripple" id="loading"><div></div><div></div></div>
			</div>
		</form>
	</div>
</div>