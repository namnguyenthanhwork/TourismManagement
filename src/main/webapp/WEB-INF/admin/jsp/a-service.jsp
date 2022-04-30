<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="row">
    <div class="col-12">
        <div class="card mb-4">
            <div class="card-header pb-0 d-flex justify-content-between align-items-center">
                <h5>Quản lý dịch vụ</h5>
                <a href="/TourismManagement/quan-tri-vien/dich-vu/tao-moi"
                    class="badge badge-success p-2 text-capitalize mb-2">Thêm dịch vụ</a>
            </div>
            <div class="card-body px-0 pt-0 pb-2">
                <div class="table-responsive p-0">
                    <table class="table align-items-center mb-0">
                        <thead>
                            <tr>
                                <th
                                    class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">
                                    Mã dịch vụ
                                </th>
                                <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">
                                    Tiêu đề</th>
                                <th
                                    class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">
                                    Nội dung</th>
                                <th
                                    class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">
                                    Tuỳ chỉnh</th>
                            </tr>
                        </thead>
                        <tbody id="serviceInfo">

                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>