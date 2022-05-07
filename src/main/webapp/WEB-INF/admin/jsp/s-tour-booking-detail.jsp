<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row">
	<div class="col-12">
		<div class="card">
			<div class="card-body">
				<h5 class="mb-4">Đặt tour du lịch</h5>
				<hr class="horizontal dark my-3">
				<div class="row">
					<div class="col-xl-5 col-lg-6 text-center">
						<div id="tourImage"></div>
						<div class="d-flex mt-4 pt-2" id="thumbnailImage"></div>
					</div>
					<div class="col-lg-5 mx-auto" id="tourInfo"></div>
				</div>
				<div class="row justify-content-center mt-3">
					<div class="col-10"></div>
					<p class="text-sm text-justify required">Các khoản phí phát sinh (nếu có) như: phụ thu dành cho khách nước
						ngoài, việt kiều; phụ thu phòng đơn; phụ thu chênh lệch giá tour… Nhân viên Du Lịch Việt sẽ gọi
						điện thoại tư vấn cho quý khách ngay sau khi có phiếu xác nhận booking. (Trong giờ hành chính)
					</p>
					<p class="text-sm text-justify required">Trường hợp quý khách không đồng ý các khoản phát sinh, phiếu xác
						nhận booking của quý khách sẽ không có hiệu lực.</p>
				</div>
				<hr class="horizontal dark my-3">
				<div class="row">
					<div class="col-12">
						<h5 class="ms-3">Bảng giá tour chi tiết</h5>
						<div class="table table-responsive">
							<table class="table align-items-center mb-0" id="servingObjectDetail"></table>
						</div>
					</div>
				</div>
				<div class="row">
					<form id="tourBookingForm" method="post">
						<h5 class="ms-3">Thông tin đặt tour</h5>
						<div class="row">
							<div class="form-group col-12 col-md-4">
								<label for="phoneNumber">Số điện thoại <span class="required">(*)</span></label>
								<input type="text" id="phoneNumber" class="form-control" name="phoneNumber"
									placeholder="Nhập số điện thoại.." value="+84982482975" disabled>
							</div>
							<div class="form-group col-12 col-md-8">
								<label for="phoneNumber">Ngày khởi hành</label>
								<div id="dptTourBooking"
									class="d-flex flex-wrap align-items-center justify-content-center"></div>
							</div>
						</div>
						<div class="form-group">
							<h5 class="ms-3">Thông tin loại khách hàng</h5>
							<div id="svoTourBooking"></div>
						</div>
						<div class="form-group">
							<h5 class="ms-3">Phương thức thanh toán</h5>
							<div id="paymentTourBooking" class="row flex-wrap align-item-center"></div>
						</div>
						<div class="form-group">
							<button type="submit" id="tourBookingdBtn" class="btn btn-primary">Đặt tour</button>
							<div class="lds-ripple" id="loading">
								<div></div>
								<div></div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>