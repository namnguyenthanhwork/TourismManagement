
$(document).ready(function () {
    $('#loading').hide()
    $('#salePercentCreatedBtn').click(function () {
        alert("Xác nhận tạo phần trăm giảm giá mới")
        $(this).hide()
        $('#loading').show()
    })
})