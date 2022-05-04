
$(document).ready(function () {
    $('#loading').hide()
    $('#paymentTypeCreatedBtn').click(function () {
        alert("Xác nhận tạo hình thức thanh toán mới")
        $(this).hide()
        $('#loading').show()
    })
})