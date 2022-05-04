
$(document).ready(function () {
    $('#loading').hide()
    $('#servingObjectCreatedBtn').click( function () {
        alert("Xác nhận tạo đối tượng phục vụ mới")
        $(this).hide()
        $('#loading').show()
    })
})