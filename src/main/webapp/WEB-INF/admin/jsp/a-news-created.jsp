<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="card">
	<div class="card-header pb-0 px-3">
		<h5 class="mb-0">Tạo mới tin tức du lịch</h5>
	</div>
	<div class="card-body pt-4 p-3">
		<form action="<c:url value='/quan-tri-vien/tin-tuc' />" method="post" enctype="multipart/form-data"
			id="newsCreatedForm">
			<div class="form-group">
				<label for="newsTitle">Tiêu đề <span class="required">(*)</span></label>
				<input type="text" class="form-control" id="newsTitle" name="newsTitle" placeholder="Nhập tiêu đề..."
					required>
			</div>
			<div class="form-group">
				<label for="newsCoverPage">Ảnh bìa</label>
				<input type="file" name="newsCoverPage" class="form-control" id="newsCoverPage">
			</div>
			<div class="form-group">
				<label for="editor">Nội dung <span class="required">(*)</span></label>
				<textarea name="newsContent" id="editor" required></textarea>
			</div>
			<div class="form-group">
				<button id="newsCreatedBtn" type="submit" class="btn btn-primary">Tạo mới</button>
				<div class="lds-ripple" id="loading">
					<div></div>
					<div></div>
				</div>
			</div>
		</form>
		<script src="<c:url value = '/resources/ckeditor/build/ckeditor.js' />"></script>
		<script>
			ClassicEditor.create(document.querySelector('#editor'))
		</script>
	</div>
</div>