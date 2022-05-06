<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="row">
	<div class="col-12">
		<div class="card mb-4">
			<div class="card-header pb-0 d-flex justify-content-between align-items-center flex-wrap">
				<h5 class="mb-2">Quản lý thanh toán</h5>
				<div class="d-flex align-items-center flex-wrap justify-content-center">
					<div class="position-relative mb-2">
						<i class="fas fa-search icon-search"></i>
						<input type="text" class="form-control" id="search" type="number"
							placeholder="Nhập tên tour ...">
					</div>
					<button class="badge badge-success p-2 text-capitalize mb-2 ml-3" id="searchBtn">Tìm
						kiếm</button>
				</div>
			</div>
			<div class="card-body px-0 pt-0 pb-2">
			</div>
		</div>
	</div>
</div>


<div class="row">
	<div class="col-12">
		<div class="mb-4">
			<div class="px-0 pt-0 pb-2" id="billDetail">
				<div class="pb-0 d-flex justify-content-between align-items-center">
					<h5 class="font-weight-bolder">Thông tin hóa đơn cần thanh toán</h5>
				</div>
				<div class="row mt-4">
					<div class="col-sm-5">
						<div class="card">
							<div class="card-body">
								<h5 class="font-weight-bolder">Hình thức thanh toán</h5>
								<div id="paymentTypeInfo" class="mt-3"></div>
							</div>
						</div>
					</div>
					<div class="col-sm-7 mt-sm-0 mt-4">
						<div class="card">
							<div class="card-body">
								<div class="row">
									<h5 class="font-weight-bolder">Chi tiết tour du lịch đã đặt</h5>
									<div id="tourInfo"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="row mt-4">
					<div class="col-sm-6">
						<div class="card">
							<div class="card-body">
								<h5 class="font-weight-bolder">Chi tiết hóa đơn</h5>
								<div id="billInfo" class="mt-3"></div>
							</div>
						</div>
					</div>
					<div class="col-sm-6 mt-sm-0 mt-4">
						<div class="card">
							<div class="card-body">
								<div class="row">
									<h5 class="font-weight-bolder">Chi tiết giá</h5>
									<div id="billPriceInfo"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="row mt-4">
					<div class="col-sm-6">
						<div class="card">
							<div class="card-body">
								<h5 class="font-weight-bolder">Tài khoản</h5>
								<div id="accountInfo" class="mt-4"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>