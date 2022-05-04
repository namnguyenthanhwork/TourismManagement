
$(document).ready(function () {
    $('#loading').hide()
    $('#featureCreatedBtn').click( function () {
        alert("Xác nhận tạo đặc điểm khởi hành mới mới")
        $(this).hide()
        $('#loading').show()
    })
})