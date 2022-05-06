function validateCreatedServiceObject() {
    let svoName = $('#svoName').val()

    if (svoName == '') {
        Swal.fire({
            title: 'Thông báo !',
            text: "Vui lòng kiểm tra lại thông tin còn thiếu trước khi tạo!",
            icon: 'warning',
            confirmButtonColor: '#3085d6',
            confirmButtonText: 'Ok'
        })
        return false
    }
    if (svoName.length < 5) {
        Swal.fire({
            title: 'Thông báo !',
            text: "Không đủ độ dài yêu cầu!",
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
    $("#servingObjectCreatedForm").validate({
        rules: {
            svoName: {
                required: true,
                minlength: 5,
                maxlength: 100
            }
        },
        messages: {
            svoName: {
                required: "Tên đối tượng không được để trống",
                minlength: "Độ dài phải từ 5 ký tự",
                maxlength: "Độ dài không vượt quá 100 ký tự"
            }
        }
    });
    $('#loading').hide()
    $('#servingObjectCreatedBtn').click(function () {
        if (validateCreatedServiceObject()) {
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