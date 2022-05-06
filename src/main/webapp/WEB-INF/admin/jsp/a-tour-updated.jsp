<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="card">
	<div class="card-header pb-0 px-3">
		<h5 class="mb-0">Cập nhật tour du lịch</h5>
	</div>
	<div class="card-body pt-4 p-3">
		<form id="tourUpdatedForm" method="post" enctype="multipart/form-data">
			<div class="form-group">
				<label for="tourTitle">Tiêu đề <span class="required">(*)</span></label>
				<input type="text" class="form-control" id="tourTitle" name="tourTitle" placeholder="Nhập tiêu đề..."
					required>
			</div>
			<div class="form-group">
				<label for="tourCoverPage">Ảnh bìa</label>
				<input type="file" name="tourCoverPage" class="form-control" id="tourCoverPage">
			</div>
			<div class="form-group">
				<label for="editor">Nội dung <span class="required">(*)</span></label>
				<textarea name="tourContent" id="editor" required></textarea>
			</div>
			<div class="form-group">
				<label for="catSlug">Loại tour <span class="required">(*)</span></label>
				<select name="catSlug" id="catSlug" class="multisteps-form__input form-control" required>
				</select>
			</div>
			<div class="form-group">
				<label for="saleId">Giảm giá <span class="required">(*)</span></label>
				<select name="saleId" id="saleId" class="multisteps-form__input form-control" required>
				</select>
			</div>
			<div class="row">
				<div class="col">
					<div class="form-group">
						<label for="svoSlugs">Các đối tượng phục vụ</label>
						<select name="svoSlugs" id="svoSlugs" class="multisteps-form__input form-control" multiple>
						</select>
					</div>
				</div>
				<div class="col">
					<div>Đơn giá</div>
					<div id="prices">
					</div>
				</div>
			</div>
			<div class="form-group">
				<label for="servSlugs">Các dịch vụ</label>
				<select name="servSlugs" id="servSlugs" class="multisteps-form__input form-control" multiple>
				</select>
			</div>
			<div class="row">
				<div class="col">
					<div class="form-group">
						<label for="dptIds">Các ngày khởi hành</label>
						<select name="dptIds" id="dptIds" class="multisteps-form__input form-control" multiple>
						</select>
					</div>
				</div>
				<div class="col">
					<div>Số lượng</div>
					<div id="tourAmounts">
					</div>
				</div>
			</div>
			<div class="form-group">
				<label for="tranSlugs">Các loại phương tiện di chuyển</label>
				<select name="tranSlugs" id="tranSlugs" class="multisteps-form__input form-control" multiple>
				</select>
			</div>
			<div class="form-group">
				<label for="noteSlugs">Các ghi chú</label>
				<select name="noteSlugs" id="noteSlugs" class="multisteps-form__input form-control" multiple>
				</select>
			</div>
			<div class="form-group">
				<button id="tourUpdatedBtn" type="submit" class="btn btn-primary">Cập nhật</button>
				<div class="lds-ripple" id="loading">
					<div></div>
					<div></div>
				</div>
			</div>
		</form>
		<script src="<c:url value = '/resources/ckeditor/build/ckeditor.js' />"></script>
		<script>
			ClassicEditor.create(document.querySelector('#editor')).then(editor => getTourInfo(editor))
		</script>
	</div>
</div>