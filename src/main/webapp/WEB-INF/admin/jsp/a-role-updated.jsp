<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form id="roleUpdateForm" method="post">
	<div class="form-group">
		<label for="roleName">Tên vai trò</label>
		<input type="text" class="form-control" id="roleName" name="roleName" placeholder="Tên vai trò">
	</div>
	<button type="submit" id="roleUpdatedBtn" class="btn btn-primary">Chỉnh sửa</button>
</form>