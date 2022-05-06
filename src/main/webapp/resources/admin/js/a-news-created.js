function validateCreateNews() {
    let newsTitle = $('#newsTitle').val()
    let newsContent = $('#editor').val()
    if (newsTitle == '' || newsContent == '') {
        Swal.fire({
            title: 'Thông báo !',
            text: "Vui lòng kiểm tra lại thông tin còn thiếu trước khi tạo!",
            icon: 'warning',
            confirmButtonColor: '#3085d6',
            confirmButtonText: 'Ok'
        })
        return false
    }
    return true
}

$(document).ready(function () {
    // validate
    $("#newsCreatedForm").validate({
        rules: {
            newsTitle: {
                required: true,
                minlength: 10,
                maxlength: 50
            },
            newsContent: {
                required: true,
                minlength: 10
            }
        },
        messages: {
            newsTitle: {
                required: "Tiêu đề không được để trống",
                minlength: "Độ dài phải từ 10 ký tự",
                maxlength: "Độ dài không vượt quá 50 ký tự"
            }
        }
    });

    $('#loading').hide()
    $('#newsCreatedBtn').click(function () {
        if (validateCreateNews()) {
            Swal.fire({
                title: 'Thông báo !',
                text: "Tạo thành công",
                icon: 'success',
                confirmButtonColor: '#3085d6'
            })
            $(this).hide()
            $('#loading').show()
        }
    })
})