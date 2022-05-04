<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div >
	<h5 >Tạo mới tài khoản</h5>
	<p >Vui lòng nhập đầy đủ thông tin bên dưới</p>
	<div >
		<form action="<c:url value='/dang-ki-tai-khoan' />" method="post" enctype="multipart/form-data">
				<div class="form-group">
					<label for="accUsername">Tên tài khoản (*)</label>
					<input type="text" id="accUsername" name="accUsername"
					       placeholder="Nhập tên tài khoản...">
				</div>
				<div class="form-group">
					<label for="accPassword">Mật khẩu (*)</label>
					<input type="password" id="accPassword"
					       name="accPassword" placeholder="Nhập mật khẩu...">
				</div>
				<div class="form-group">
					<label for="accFirstName">Tên (*)</label>
					<input type="text"  id="accFirstName" name="accFirstName"
					       placeholder="Nhập tên...">
				</div>
				<div class="form-group">
					<label for="accLastName">Họ và tên lót (*)</label>
					<input type="text"  id="accLastName" name="accLastName"
					       placeholder="Nhập họ và tên lót...">
				</div>
				<div class="form-group">
					<label for="accSex">Nhập giới tính (*)</label>
					<select name="accSex" id="accSex">
						<option value="1">Nam</option>
						<option value="0">Nữ</option>
					</select>
				</div>
				<div class="form-group">
					<label for="accPhoneNumber">Số điện thoại (*)</label>
					<input type="text" id="accPhoneNumber"
					       name="accPhoneNumber" placeholder="Nhập số điện thoại...">
				</div>

				<div class="form-group">
					<label for="accIdCard">Mã căn cước công dân (*)</label>
					<input type="text" id="accIdCard"
					       name="accIdCard" placeholder="Nhập mã căn cước công dân..">
				</div>

				<div class="form-group">
					<label for="accDateOfBirth">Nhập ngày sinh (*)</label>
					<input type="date" name="accDateOfBirth" id="accDateOfBirth"
					       placeholder="Please select start date">
				</div>
				<div class="form-group">
					<label for="accAvatar">Upload hình</label>
					<input type="file" name="accAvatar" class="form-control" id="accAvatar">
				</div>
				<div id="otpConfirmArea">
					<h5>Xác nhận tạo tài khoản</h5>
					<div  class="form-group" id="sendOTPArea" >
						<label for="cusEmail">Nhập email của bạn để nhận mã OTP</label>
						<input type="email" id="cusEmail" placeholder="Nhập email ...">
						<button id="otpSendBtn" type="button">Gửi mã OTP</button>
					</div>
					<div  class="form-group" id="receiveOTPArea">
						<label for="otpInp">Mã OTP</label>
						<input type="text" id="otpInp" placeholder="Nhập mã OTP ...">
					</div>
				</div>
				<div class="form-group" id="signUpConfirmArea">
					<button id="signUpConfirmBtn" type="submit" title="Thêm">Tạo mới</button>
				</div>
				<div class="lds-ripple" id="loading"><div></div><div></div></div>
		</form>
	</div>
</div>