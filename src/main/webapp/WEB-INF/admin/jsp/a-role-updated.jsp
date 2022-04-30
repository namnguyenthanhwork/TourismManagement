<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="card">
	<div class="card-header pb-0 px-3">
		<h5 class="mb-0">Cập nhật vai trò</h5>
	</div>
	<div class="card-body pt-4 p-3">
		<form id="roleUpdateForm" method="post">
			<div class="form-group">
				<label for="roleName">Tên vai trò</label>
				<input type="text" class="form-control" id="roleName" name="roleName" placeholder="Nhập tên vai trò...">
			</div>
			<button type="submit" id="roleUpdatedBtn" class="btn btn-primary">Chỉnh sửa</button>
		</form>
	</div>
</div>