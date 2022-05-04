
$(document).ready(function () {
    $('#loading').hide()
    $('#storageCreatedBtn').click(function () {
        alert('Xác nhận tạo kho chứa mới')
        $(this).hide()
        $('#loading').show()
    })
})