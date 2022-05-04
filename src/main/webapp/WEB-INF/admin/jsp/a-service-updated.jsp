<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="card">
    <div class="card-header pb-0 px-3">
        <h5 class="mb-0">Cập nhật đặc điểm</h5>
    </div>
    <div class="card-body pt-4 p-3">
        <form id="serviceUpdatedForm"  method="post">
            <div class="form-group">
                <label for="servTitle">Tiêu đề (*)</label>
                <input type="text" class="form-control" id="servTitle" name="servTitle" placeholder="Nhập tiêu đề...">
            </div>

            <div class="form-group">
                <label for="editor">Nội dung (*)</label>
                <textarea name="servContent" id="editor"></textarea>
            </div>

            <div class="form-group">
                <button type="submit" id="serviceUpdatedBtn" class="btn btn-primary">Cập nhật</button>
                <div class="lds-ripple" id="loading"><div></div><div></div></div>
            </div>
        </form>
        <script src="<c:url value = '/resources/ckeditor/build/ckeditor.js' />"></script>
        <script>
            ClassicEditor.create(document.querySelector('#editor')).then(editor=>getServiceInfo(editor))
        </script>
    </div>
</div>
