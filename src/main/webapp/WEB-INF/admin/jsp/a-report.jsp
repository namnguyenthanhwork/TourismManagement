<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row justify-content-center">
    <div class="col-md-10">
        <div class="card mb-4">
            <div class="card-header pb-0 d-flex justify-content-between align-items-center">
                <h5>Lựa chọn báo cáo</h5>
            </div>
            <div class="card-body px-0 pt-0 pb-2">
                <div class="stats-choice">
                    <form>
                        <div class="row justify-content-center mb-2">
                            <div class="col-12 col-md-3">
                                <label for="reportType">Loại báo cáo</label>
                                <select name="bao-cao" class="form-control" id="reportType">
                                    <option value="doanh-thu">Báo cáo doanh thu</option>
                                    <option value="so-luong-tour">Số lượng tour</option>
                                </select>
                            </div>

                            <div class="col-12 col-md-4">
                                <label for="reportCondition">Điều kiện báo cáo</label>
                                <select class="form-control" name="thoi-gian" id="reportCondition">
                                    <option value="thang">Báo cáo theo tháng</option>
                                    <option value="quy">Báo cáo theo quý</option>
                                    <option value="nam">Báo cáo theo năm</option>
                                </select>
                            </div>
                            <div class="col-12 col-md-2" id='monthSelection'>
                                <label for='monthInput'>Tháng</label>
                                <select name='monthInput' class="form-control" id='monthInput'></select>
                            </div>
                            <div class="col-12 col-md-2" id='quarterSelection'>
                                <label for='quarterInput'>Quý</label>
                                <select class="form-control" name='quarterInput' id='quarterInput'></select>
                            </div>
                            <div class="col-12 col-md-2" id='yearSelection'>
                                <label for='yearInput'>Năm</label>
                                <select name='yearInput' class="form-control" id='yearInput'></select>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-12">
        <div class="card">
            <div class="card-header pb-0 d-flex justify-content-between align-items-center">
                <h5>Bảng dữ liệu</h5>
                <a href="javascript:;" class="badge badge-success p-2 text-capitalize mb-2" id="exportPdf"> <span
                        class="btn-label">
                        <i class="fa fa-pencil"></i>
                    </span>
                    Lưu PDF</a>
            </div>
            <div class="card-body stats-scroll scrollbar scrollbar-inner">
                <div class="table-responsive">
                    <table id='reportTable' class="table align-items-center mb-0">
                        <thead id='titleReportTable' class="bg-thead">
                        </thead>
                        <tbody id='dataReportTable'>
                        </tbody>
                    </table>
                    <div id='total'></div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-12">
        <ul class="pagination justify-content-center" id="pagination">
        </ul>
    </div>
</div>