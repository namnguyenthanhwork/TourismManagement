
$(document).ready(function () {
    $('#loading').hide()
    $('#newsCreatedBtn').click(function (){
        alert("Xác nhận tạo tin tức du lịch mới?")
        $(this).hide()
        $('#loading').show()
    })
})