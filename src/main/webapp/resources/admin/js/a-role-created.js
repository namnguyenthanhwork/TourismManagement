
$(document).ready(function () {
    $('#loading').hide()
    $('#roleCreatedBtn').click( function () {
        alert("Xác nhận tạo vai trò mới")
        $(this).hide()
        $('#loading').show()
    })
})