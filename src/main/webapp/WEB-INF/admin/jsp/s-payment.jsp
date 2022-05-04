<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="row">
	<div class="col-12">
		<div class="card mb-4">
			<div class="card-header pb-0 d-flex justify-content-between align-items-center">
				<h5>Quản lý thanh toán</h5>
				<input id="search" type="number" placeholder="Nhập tên tour ...">
				<button id="searchBtn">Tìm kiếm</button>
			</div>
			<div class="card-body px-0 pt-0 pb-2">
			</div>
		</div>
	</div>
</div>


<div class="row"  >
	<div class="col-12">
		<div class="card mb-4">
			<div class="card-header pb-0 d-flex justify-content-between align-items-center">
				<h5>Thông tin hóa đơn cần thanh toán</h5>
			</div>
			<div class="card-body px-0 pt-0 pb-2" id="billDetail">
				<div>
					<h1>Tài khoản</h1>
					<div id="accountInfo"></div>
				</div>
				<div>
					<h1>Hình thức thanh toán</h1>
					<div id ="paymentTypeInfo"></div>
				</div>
				<div>
					<h1>Chi tiết tour du lịch đã đặt </h1>
					<div id="tourInfo"></div>
				</div>
				<div>
					<h1>Chi tiết hóa đơn</h1>
					<div id="billInfo"></div>
				</div>

				<div>
					<h1>Chi tiết giá </h1>
					<div id="billPriceInfo"></div>
				</div>
			</div>
		</div>
	</div>
</div>