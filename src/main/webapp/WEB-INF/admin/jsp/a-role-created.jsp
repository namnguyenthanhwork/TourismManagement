<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form action="<c:url value='/quan-tri-vien/vai-tro' />" method="post">
	<div class="form-group">
		<label for="roleName">Tên vai trò</label>
		<input type="text" class="form-control" id="roleName" name="roleName" placeholder="Tên vai trò" >
	</div>
	<button id="roleCreatedBtn" type="submit" class="btn btn-primary">Tạo mới</button>
</form>