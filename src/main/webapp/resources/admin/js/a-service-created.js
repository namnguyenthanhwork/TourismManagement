
$(document).ready(function () {
    $('#loading').hide()
    $('#serviceCreatedBtn').click(function () {
        alert("Xác nhận tạo dịch vụ mới")
        $(this).hide()
        $('#loading').show()
    })
})