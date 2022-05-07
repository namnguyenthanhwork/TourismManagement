<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="vnt-content">
	<div class="vnt-main-top">
		<div id="vnt-navation" class="breadcrumb">
			<div class="wrapper">
				<div class="navation">
					<ul>
						<li><a title="Trang chủ" href="<c:url value='/' />"><span>Trang
									chủ</span></a>
						</li>
						<li><a href="javascipt:;" title="Tin tức"><span>Tour du lịch/Thanh toán</span></a>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<div class="wrapCont">
		<div class="wrapper">
			<div class="tourTitle">
				<h3>Đặt tour du lịch</h3>
			</div>
			<div class="mod-content">
				<div class="row">
					<div class="col-lg-5 col-md-6 text-center">
						<div id="tourImage"></div>
						<div id="thumbnailImage" class="d-flex mt-4 pt-2"></div>
					</div>
					<div class="col-lg-5 col-md-6 mx-auto">
						<div id="tourInfo"></div>
					</div>
				</div>
				<div class="row mt-3">
					<p class="text-justify text-danger">Các khoản phí phát sinh (nếu có) như: phụ thu dành cho khách
						nước ngoài,
						việt kiều;
						phụ thu
						phòng
						đơn; phụ thu chênh lệch giá tour… Nhân viên Du Lịch Việt sẽ gọi điện thoại tư vấn
						cho quý
						khách
						ngay sau khi có phiếu xác nhận booking. (Trong giờ hành chính)</p>
					<p class="text-justify text-danger">Trường hợp quý khách không đồng ý các khoản phát sinh, phiếu xác
						nhận
						booking của quý
						khách
						sẽ
						không có hiệu lực.</p>
				</div>
				<hr class="horizontal dark my-3">
				<div class="row justify-content-around">
					<div class="col-12 col-lg-5 boxDesign1 align-self-md-start">
						<h4 class="mb-4 col-12 name">Bảng giá tour chi tiết</h4>
						<table id="servingObjectDetail" class="table col-12"></table>
					</div>
					<div class="col-12 col-lg-6 boxDesign1">
						<form id="tourBookingForm" method="post">
							<h5 class="ms-3 name">Thông tin đặt tour</h5>
							<div class="row attr">
								<div class="form-group col-12 col-md-4">
									<label for="phoneNumber">Số điện thoại <span class="required">(*)</span></label>
									<input type="text" id="phoneNumber" class="form-control" name="phoneNumber"
										placeholder="Nhập số điện thoại.." required>
								</div>
								<div class="form-group col-12 col-md-8">
									<label for="phoneNumber">Ngày khởi hành</label>
									<div id="dptTourBooking"
										class="d-flex flex-wrap align-items-center justify-content-center"></div>
								</div>
							</div>

							<div class="form-group">
								<h5 class="ms-3 name">Thông tin loại khách hàng</h5>
								<div id="svoTourBooking" class="d-flex flex-wrap"></div>
							</div>
							<div class="form-group">
								<h5 class="ms-3 name">Phương thức thanh toán</h5>
								<div id="paymentTourBooking" class="row flex-wrap align-item-center"></div>
							</div>
							<div class="form-group">
								<h5 class="ms-3 name">Địa điểm giao hàng</h5>
								<div class="row d-flex flex-wrap">
									<div class="col-6">
										<label for="ship1">Trực tiếp tại quầy</label>
										<input type="radio" id="ship1" name="shipAddress" value="ship1" checked>
									</div>
									<div class="col-6">
										<label for="ship2">Khác</label>
										<input type="radio" id="ship2" name="shipAddress" value="ship2">
									</div>
									<div id="billShip" class="col-12"></div>
								</div>
							</div>
							<div class="form-group text-center">
								<c:choose>
									<c:when test="${pageContext.request.userPrincipal.name != null}">
										<button type="submit" id="tourBookingdBtn" class="btn btn-primary">Đặt
											tour</button>
										<div class="lds-ripple" id="loading">
											<div></div>
											<div></div>
										</div>
									</c:when>

									<c:when test="${pageContext.request.userPrincipal.name == null}">
										<p class="col-12 text-center text-danger">Bạn phải đăng nhập để có thể tiến hành
											thanh toán </p>
										<a href="<c:url value='/auth/dang-nhap' /> " class="btn btn-primary">Đăng
											nhập tại đây</a>
									</c:when>
								</c:choose>
							</div>
						</form>
					</div>
				</div>

			</div>
		</div>
	</div>
</div>