
$(document).ready(function () {
    $('#loading').hide()
    $('#transportCreatedBtn').click( function () {
        alert("Xác nhận tạo phương tiện di chuyển mới")
        $(this).hide()
        $('#loading').show()
    })
})