
$(document).ready(function () {
    $('#loading').hide()
    $('#noteCreatedBtn').click(function (){
        alert("Xác nhận tạo mới ghi chú")
        $(this).hide()
        $('#loading').show()
    })
})