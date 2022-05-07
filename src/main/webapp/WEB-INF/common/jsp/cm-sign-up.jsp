<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<main class="main-content mt-0">
	<div class="page-header">
		<div class="container">
			<div class="row">
				<div class="col-md-6 d-flex flex-column mx-auto">
					<div class="card card-plain mt-2">
						<div class="card-header pb-0 text-left bg-transparent">
							<h4 class="font-weight-bolder text-info text-gradient">
								Tạo mới tài khoản
							</h4>
							<p class="mb-0">
								Vui lòng nhập đầy đủ thông tin bên dưới
							</p>
						</div>
						<div class="card-body">
							<form action="<c:url value='/dang-ki-tai-khoan' />" method="post"
								enctype="multipart/form-data" id="form-signup">
								<div class="row mt-3">
									<div class="col-12 col-sm-6">
										<label for="accUsername">Tên tài khoản <span class="required">(*)</span></label>
										<input type="text" id="accUsername" class="form-control" name="accUsername"
											placeholder="Nhập tên tài khoản..." required>
									</div>
									<div class="col-12 col-sm-6 mt-3 mt-sm-0">
										<label for="accPassword">Mật khẩu <span class="required">(*)</span></label>
										<input type="password" id="accPassword" class="form-control" name="accPassword"
											placeholder="Nhập mật khẩu..." required>
									</div>
								</div>
								<div class="row mt-3">
									<div class="col-12 col-sm-6">
										<label for="accFirstName">Tên <span class="required">(*)</span></label>
										<input type="text" id="accFirstName" class="form-control" name="accFirstName"
											placeholder="Nhập tên..." required>
									</div>
									<div class="col-12 col-sm-6 mt-3 mt-sm-0">
										<label for="accLastName">Họ và tên lót <span class="required">(*)</span></label>
										<input type="text" id="accLastName" class="form-control" name="accLastName"
											placeholder="Nhập họ và tên lót..." required>
									</div>
								</div>
								<div class="row mt-3">
									<div class="col-12 d-flex align-items-center">
										<label for="accSex" class="mr-4" style="white-space: nowrap;">Nhập giới tính
											<span class="required">(*)</span></label>
										<select name="accSex" id="accSex" class="form-control" required>
											<option value="1">Nam</option>
											<option value="0">Nữ</option>
										</select>
									</div>
								</div>
								<div class="row mt-3">
									<div class="col-12 col-sm-6">
										<label for="accPhoneNumber">Số điện thoại <span
												class="required">(*)</span></label>
										<input type="text" id="accPhoneNumber" class="form-control"
											name="accPhoneNumber" placeholder="Nhập số điện thoại..." required>
									</div>
									<div class="col-12 col-sm-6 mt-3 mt-sm-0">
										<label for="accIdCard">Mã căn cước công dân <span
												class="required">(*)</span></label>
										<input type="text" id="accIdCard" class="form-control" name="accIdCard"
											placeholder="Nhập căn cước công dân..." required>
									</div>
								</div>
								<div class="row mt-3">
									<div class="col-12 col-sm-6">
										<label for="accDateOfBirth">Nhập ngày sinh <span
												class="required">(*)</span></label>
										<input type="date" name="accDateOfBirth" id="accDateOfBirth"
											class="form-control" placeholder="Nhập ngày sinh..." required>
									</div>
									<div class="col-12 col-sm-6 mt-3 mt-sm-0">
										<label for="accAvatar">Ảnh đại diện</label>
										<input type="file" name="accAvatar" class="form-control" id="accAvatar">
									</div>
								</div>
								<div id="otpConfirmArea" class="mt-4">
									<h5 class="font-weight-bolder text-info text-gradient">
										Xác nhận tạo tài khoản
									</h5>
									<div class="form-group" id="sendOTPArea">
										<label for="cusEmail">Nhập email của bạn để nhận mã OTP <span
												class="required">(*)</span></label>
										<input type="email" id="cusEmail" class="form-control"
											placeholder="Nhập email ..." required>
										<button class="btn bg-gradient-info w-100 mt-4 mb-0" id="otpSendBtn"
											type="button">Gửi mã
											OTP</button>
									</div>
								</div>
								<div class="text-center" id="signUpConfirmArea">

									<button id="signUpConfirmBtn" type="submit" title="Thêm"
										class="btn bg-gradient-info w-100 mt-4 mb-0">Tạo mới</button>
								</div>
							</form>
						</div>
						<div class="card-footer text-center pt-0 px-lg-2 px-1">
							<p class="mb-4 text-sm mx-auto">
								Đã có tài khoản ?
								<a href="<c:url value='/auth/dang-nhap' />"
									class="text-info text-gradient font-weight-bold">Đăng nhập</a>
							</p>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="oblique position-absolute top-0 h-100 d-md-block d-none me-n4">
						<div class="oblique-image bg-cover position-absolute fixed-top ms-auto h-100 z-index-0 ms-n6"
							style="background-image:url('https://res.cloudinary.com/ou-project/image/upload/v1651914422/Homepage%20Admin/curved6_ziqjvo.jpg')">
						</div>
					</div>
				</div>
				<div class="lds-ripple" id="loading">
					<div></div>
					<div></div>
				</div>
			</div>
		</div>
	</div>
</main>

<!-- Modal -->
<div class="modal fade" id="otpModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
	aria-hidden="true">
	<div class="modal-dialog modal-dialog modal-dialog-centered" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h6 class="modal-title font-weight-bolder text-info text-gradient" id="exampleModalCenterTitle">Xác thực
					OTP</h6>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="form-group">
					<h6 class="text-center">Nhập mã OTP</h6>
					<input type="text" id="otpInp" class="form-control" placeholder="Nhập mã OTP ...">
				</div>
			</div>
		</div>
	</div>
</div>