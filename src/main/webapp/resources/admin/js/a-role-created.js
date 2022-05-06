function validateCreateRole() {
    let roleName = $('#roleName').val()
    if (roleName == '') {
        Swal.fire({
            title: 'Thông báo !',
            text: "Vui lòng kiểm tra lại thông tin còn thiếu trước khi tạo!",
            icon: 'warning',
            confirmButtonColor: '#3085d6',
            confirmButtonText: 'Ok'
        })
        return false
    }

    if (roleName.length < 3) {
        Swal.fire({
            title: 'Thông báo !',
            text: "Độ dài không đạt yêu cầu!",
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
    $("#createRoleForm").validate({
        rules: {
            roleName: {
                required: true,
                minlength: 3,
                maxlength: 30
            }
        },
        messages: {
            roleName: {
                required: "Vai trò không được để trống",
                minlength: "Độ dài phải từ 3 ký tự",
                maxlength: "Độ dài không vượt quá 30 ký tự"
            }
        }
    });

    $('#loading').hide()
    $('#roleCreatedBtn').click(function () {
        if (validateCreateRole()) {
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