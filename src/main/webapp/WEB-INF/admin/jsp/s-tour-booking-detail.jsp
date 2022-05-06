<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="card">
	<div class="card-header pb-0 px-3">
		<h5 class="mb-0">Đặt tour du lịch</h5>
	</div>
	<div class="card-body pt-4 p-3">
		<div class="row">
			<div class="col">
				<div id="tourImage"></div>
				<div id="thumbnailImage"></div>
			</div>
			<div class="col">
				<div id="tourInfo"></div>
			</div>
		</div>
		<div class="row">
			<p>Các khoản phí phát sinh (nếu có) như: phụ thu dành cho khách nước ngoài, việt kiều; phụ thu phòng đơn; phụ thu chênh lệch giá tour… Nhân viên Du Lịch Việt sẽ gọi điện thoại tư vấn cho quý khách ngay sau khi có phiếu xác nhận booking. (Trong giờ hành chính)</p>
			<p>Trường hợp quý khách không đồng ý các khoản phát sinh, phiếu xác nhận booking của quý khách sẽ không có hiệu lực.</p>
		</div>
		<div class="row">
			<h5>BẢNG GIÁ TOUR CHI TIẾT</h5>
			<table id="servingObjectDetail"></table>
		</div>
		<div class="row">
			<form id="tourBookingForm" method="post">
				<h5>THÔNG TIN ĐẶT TOUR</h5>
				<div class="form-group">
					<label for="phoneNumber">Số điện thoại (*)</label>
					<input type="text" id="phoneNumber" name="phoneNumber"
					       placeholder="Nhập số điện thoại.." value="+84982482975" disabled>
				</div>
				<p>Ngày khởi hành</p>
				<div id="dptTourBooking">

				</div>
				<p>Thông tin loại khách hàng</p>
				<div id="svoTourBooking">

				</div>
				<h5>PHƯƠNG THỨC THANH TOÁN</h5>
				<div id="paymentTourBooking">

				</div>
				<div class="form-group">
					<button type="submit" id="tourBookingdBtn" class="btn btn-primary">Đặt tour</button>
					<div class="lds-ripple" id="loading"><div></div><div></div></div>
				</div>
			</form>
		</div>
		<div class="row">
		</div>
	</div>
</div>