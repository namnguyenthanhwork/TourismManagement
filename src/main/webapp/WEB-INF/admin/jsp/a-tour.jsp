<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="row">
	<div class="col-12">
		<div class="card mb-4">
			<div class="card-header pb-0 d-flex justify-content-between align-items-center">
				<h5>Quản lý tour du lịch</h5>
				<input id="search" placeholder="Nhập tên tour ...">
				<a href="<c:url value ='/quan-tri-vien/tour-du-lich/tao-moi'/>"
				   class="badge badge-success p-2 text-capitalize mb-2">Thêm tour</a>
			</div>
			<div class="card-body px-0 pt-0 pb-2">
				<div class="table-responsive p-0">
					<table class="table align-items-center mb-0">
						<thead>
						<tr>
							<th
									class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">
								Tuỳ chỉnh</th>
							<th
									class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">
								Mã tour</th>
							<th
									class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">
								Tiêu đề</th>
							<th
									class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">
								Slug</th>
							<th
									class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">
								Loại tour</th>
							<th
									class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">
								Ngày bắt đầu giảm giá</th>
							<th
									class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">
								Kết thúc giảm giá</th>
							<th
									class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">
								Tỉ lệ đánh giá</th>
							<th
									class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">
								Ảnh bìa</th>

						</tr>
						</thead>
						<tbody id="tourInfo">

						</tbody>
					</table>
				</div>
			</div>
		</div>
		<ul class="pagination" id ="pagination">
		</ul>
	</div>
</div>