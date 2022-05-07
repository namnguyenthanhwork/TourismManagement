<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="card">
    <div class="card-header pb-0 px-3">
        <h5 class="mb-0">Cập nhật ghi chú</h5>
    </div>
    <div class="card-body pt-4 p-3">
        <form id="noteUpdatedForm" method="post">
            <div class="form-group">
                <label for="noteTitle">Tiêu đề <span class="required">(*)</span></label>
                <input type="text" class="form-control" id="noteTitle" name="noteTitle" placeholder="Nhập tiêu đề..."
                    required>
            </div>

            <div class="form-group">
                <label for="editor">Nội dung <span class="required">(*)</span></label>
                <textarea name="noteContent" id="editor" required></textarea>
            </div>

            <div class="form-group">
                <button type="submit" id="noteUpdatedBtn" class="btn btn-primary">Cập nhật</button>
                <div id="overlayLoading"></div>
                <div class="lds-roller" id="loading">
                    <div></div>
                    <div></div>
                    <div></div>
                    <div></div>
                    <div></div>
                    <div></div>
                    <div></div>
                    <div></div>
                </div>
            </div>
        </form>
        <script src="<c:url value = '/resources/ckeditor/build/ckeditor.js' />"></script>
        <script>
            ClassicEditor.create(document.querySelector('#editor')).then(editor => getNoteInfo(editor))
        </script>
    </div>
</div>