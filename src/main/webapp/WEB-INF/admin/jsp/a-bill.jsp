<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="row">
    <div class="col-12">
        <div class="card mb-4">
            <div class="card-header pb-0 d-flex justify-content-between align-items-center">
                <h5>Quản lý hoá đơn</h5>
            </div>
            <div class="card-body px-0 pt-0 pb-2">
                <div class="table-responsive p-0">
                    <table class="table align-items-center mb-0">
                        <thead>
                            <tr>
                                <th class="text-center text-uppercase text-dark text-xxs font-weight-bolder opacity-7">
                                    Tuỳ chỉnh</th>
                                <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Mã hóa
                                    đơn
                                </th>
                                <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Ngày tạo
                                </th>
                                <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Tài
                                    khoản tạo
                                </th>
                                <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Hình
                                    thức thanh toán
                                </th>
                                <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Tổng
                                    tiền giảm giá
                                </th>
                                <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Tổng
                                    tiền
                                </th>
                                <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Ngày
                                    khởi hành
                                </th>
                                <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Trạng
                                    thái thanh toán
                                </th>
                            </tr>
                        </thead>
                        <tbody id="billInfo">
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <ul class="pagination" id="pagination">
        </ul>
    </div>
</div>