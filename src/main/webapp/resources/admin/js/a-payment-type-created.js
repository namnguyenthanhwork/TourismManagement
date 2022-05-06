function validateCreatedPaymentType() {
    let paytName = $('#paytName').val()

    if (paytName == '') {
        Swal.fire({
            title: 'Thông báo !',
            text: "Vui lòng kiểm tra lại thông tin còn thiếu trước khi tạo!",
            icon: 'warning',
            confirmButtonColor: '#3085d6',
            confirmButtonText: 'Ok'
        })
        return false
    }
    if (paytName.length < 5) {
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
    $("#paymentTypeCreatedForm").validate({
        rules: {
            paytName: {
                required: true,
                minlength: 5,
                maxlength: 100
            }
        },
        messages: {
            paytName: {
                required: "Tiêu đề không được để trống",
                minlength: "Độ dài phải từ 5 ký tự",
                maxlength: "Độ dài không vượt quá 100 ký tự"
            }
        }
    });
    $('#loading').hide()
    $('#paymentTypeCreatedBtn').click(function () {
        if (validateCreatedPaymentType()) {
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