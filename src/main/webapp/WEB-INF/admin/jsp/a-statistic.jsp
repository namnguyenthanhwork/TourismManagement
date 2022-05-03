<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row justify-content-center">
    <div class="col-md-10">
        <div class="card mb-4">
            <div class="card-header pb-0 d-flex justify-content-between align-items-center">
                <h5>Lựa chọn thống kê</h5>
            </div>
            <div class="card-body px-0 pt-0 pb-2">
                <div class="stats-choice mb-2">
                    <form>
                        <div class="row justify-content-center">
                            <div class="col-12 col-md-3">
                                <label for="statisticType">Loại thống kê</label>
                                <select name="loai" class="form-control" id="statisticType">
                                    <option value="doanh-thu">Thống kê doanh thu</option>
                                    <option value="so-luong-tour">Số lượng tour</option>
                                </select>
                            </div>
                            <div class="col-12 col-md-3">
                                <label for="chartType">Loại biểu đồ</label>
                                <select name="chartType" class="form-control" id="chartType">
                                    <option value="pie">Biểu đồ tròn</option>
                                    <option value="line">Biểu đồ đường</option>
                                    <option value="bar">Biểu đồ cột</option>
                                    <option value="doughnut">Biểu đồ doughnut</option>
                                    <option value="polarArea">Biểu đồ vùng cực</option>
                                </select>
                            </div>
                            <div class="col-12 col-md-5">
                                <label for="statisticCondition">Điều kiện thống kê</label>
                                <select class="form-control" name="thoi-gian" id="statisticCondition">
                                    <option value="thang">Thống kê theo tháng</option>
                                    <option value="quy">Thống kê theo quý</option>
                                    <option value="nam">Thống kê theo năm</option>
                                    <option value="thang-den-thang">Thống kê theo khoảng thời gian giữa 2 tháng
                                    </option>
                                    <option value="quy-den-quy">Thống kê theo khoảng thơì gian giữa 2 quý
                                    </option>
                                    <option value="nam-den-nam">Thống kê theo khoảng thời gian giữa 2 năm
                                    </option>
                                </select>
                                <div id="timeInput" class="mt-3">
                                    <div class="col-12 col-md-6">
                                        <label for="leftTime">Bắt đầu</label>
                                        <select name="bat-dau" class="form-control" id="leftTime"></select>
                                    </div>
                                    <div class="col-12 col-md-6">
                                        <label for="rightTime">Kết thúc</label>
                                        <select name="ket-thuc" class="form-control" id="rightTime"></select>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-lg-6 mb-3">
        <div class="card">
            <div class="card-header pb-0 d-flex justify-content-between align-items-center">
                <h5>Bảng dữ liệu</h5>
            </div>
            <div class="card-body stats-scroll scrollbar scrollbar-inner">
                <div class="table-responsive">
                    <table class="table align-items-center mb-0" id="statisticTable">
                        <thead id="titleStatisticTable">
                        </thead>
                        <tbody id="dataStatisticTable">
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <div class="col-lg-6">
        <div class="card" style="transition: unset;">
            <div class="card-header pb-0 d-flex justify-content-between align-items-center">
                <h5>Biểu đồ thống kê</h5>
                <a href="javascript:;" class="badge badge-success p-2 text-capitalize mb-2" id="pdfChart"> <span
                        class="btn-label">
                        <i class="fa fa-pencil"></i>
                    </span>
                    Lưu PDF</a>
            </div>
            <div class="card-body">
                <div class="col-md-10" style="margin: auto;">
                    <canvas id="chart"></canvas>
                </div>
            </div>
        </div>
    </div>
</div>