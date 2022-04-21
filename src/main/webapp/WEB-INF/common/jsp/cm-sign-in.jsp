<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form action="<c:url value="/auth/dang-nhap" />" method ="post">
  <div class="form-group">
    <label for="username">Tên đăng nhập</label>
    <input type="text" class="form-control" name="username" id="username" placeholder="Nhập tài khoản">
  </div>
  <div class="form-group">
    <label for="password">Mật khẩu</label>
    <input type="password" class="form-control" name="password" id="password" placeholder="Nhập mật khẩu">
  </div>
  <button type="submit" class="btn btn-primary">Đăng nhập</button>
</form>