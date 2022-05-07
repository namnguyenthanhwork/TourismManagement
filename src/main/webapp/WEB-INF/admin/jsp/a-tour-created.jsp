<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="card">
	<div class="card-header pb-0 px-3">
		<h5 class="mb-0">Tạo mới tour du lịch</h5>
	</div>
	<div class="card-body pt-4 p-3">
		<form action="<c:url value='/quan-tri-vien/tour-du-lich' />" method="post" enctype="multipart/form-data"
			id="createdTourForm">
			<div class="row">
				<div class="col-12">
					<label for="tourTitle">Tiêu đề <span class="required">(*)</span></label>
					<input type="text" class="form-control" id="tourTitle" name="tourTitle"
						placeholder="Nhập tiêu đề..." required>
				</div>
			</div>
			<div class="row mt-3">
				<div class="col-12">
					<label for="tourCoverPage">Ảnh bìa</label>
					<input type="file" name="tourCoverPage" class="form-control" id="tourCoverPage">
				</div>
			</div>
			<div class="row mt-3">
				<div class="col-12">
					<label for="editor">Nội dung <span class="required">(*)</span></label>
					<textarea name="tourContent" id="editor" required></textarea>
				</div>
			</div>
			<div class="row mt-3">
				<div class="col-12 col-sm-6">
					<label for="catSlug">Loại tour <span class="required">(*)</span></label>
					<select name="catSlug" id="catSlug" class="multisteps-form__input form-control" required>
					</select>
				</div>
				<div class="col-12 col-sm-6 mt-3 mt-sm-0">
					<label for="saleId">Giảm giá <span class="required">(*)</span></label>
					<select name="saleId" id="saleId" class="multisteps-form__input form-control" required>
					</select>
				</div>
			</div>
			<div class="row mt-3">
				<div class="col-12 col-sm-6">
					<label for="svoSlugs">Các đối tượng phục vụ</label>
					<select name="svoSlugs" id="svoSlugs" class="multisteps-form__input form-control" multiple>
					</select>
				</div>
				<div class="col-12 col-sm-6 mt-3 mt-sm-0">
					<label>Đơn giá</label>
					<div id="prices">
					</div>
				</div>
			</div>
			<div class="row mt-3">
				<div class="col-12">
					<label for="servSlugs">Các dịch vụ</label>
					<select name="servSlugs" id="servSlugs" class="multisteps-form__input form-control" multiple>
					</select>
				</div>
			</div>
			<div class="row mt-3">
				<div class="col-12 col-sm-6">
					<label for="dptIds">Các ngày khởi hành</label>
					<select name="dptIds" id="dptIds" class="multisteps-form__input form-control" multiple>
					</select>
				</div>
				<div class="col-12 col-sm-6 mt-3 mt-sm-0">
					<label>Số lượng</label>
					<div id="tourAmounts">
					</div>
				</div>
			</div>
			<div class="row mt-3">
				<div class="col-12 col-sm-6">
					<label for="tranSlugs">Các loại phương tiện di chuyển</label>
					<select name="tranSlugs" id="tranSlugs" class="multisteps-form__input form-control" multiple>
					</select>
				</div>
				<div class="col-12 col-sm-6 mt-3 mt-sm-0">
					<label for="noteSlugs">Các ghi chú</label>
					<select name="noteSlugs" id="noteSlugs" class="multisteps-form__input form-control" multiple>
					</select>
				</div>
			</div>
			<div class="card-footer text-center mt-2">
				<button id="tourCreatedBtn" type="submit" class="btn btn-primary">Tạo mới</button>
				<div class="lds-ripple" id="loading">
					<div></div>
					<div></div>
				</div>
			</div>
		</form>
		<script src="<c:url value = '/resources/ckeditor/build/ckeditor.js' />"></script>
		<script>
			ClassicEditor.create(document.querySelector('#editor')).then(editor=>gEditor=editor)
		</script>
	</div>
</div>