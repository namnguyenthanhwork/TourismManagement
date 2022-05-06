function getOTP() {
    let email = $('#cusEmail').val()
    if (email == null || email.trim().length == 0) {
        Swal.fire({
            title: 'Thông báo !',
            text: "Vui lòng nhập email để xác nhận tạo tài khoản!",
            icon: 'warning',
            confirmButtonColor: '#3085d6',
            confirmButtonText: 'Ok'
        })
        return
    }
    fetch('/TourismManagement/dang-ki-tai-khoan/otp', {
        method: 'post',
        body: JSON.stringify({
            "email": email
        }),
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(res => res.json()).then(data => {
        if (validateSignUp()) {
            $('#accUsername').attr("readonly", true);
            $('#accPassword').attr("readonly", true)
            $('#accFirstName').attr("readonly", true)
            $('#accLastName').attr("readonly", true)
            $('#accSex').attr("readonly", true)
            $('#accPhoneNumber').attr("readonly", true)
            $('#accIdCard').attr("readonly", true)
            $('#accDateOfBirth').attr("readonly", true)
            $('#accAvatar').attr("readonly", true)
            $('#cusEmail').attr("readonly", true)
            $('#otpModal').modal('show')
            confirmOTP(data['otp'])
        }

    })
}

function confirmOTP(otp) {
    $('#otpInp').keyup(function () {
        if ($(this).val() === otp) {
            $('#otpModal').modal('hide')
            $('#otpConfirmArea').hide()
            $('#signUpConfirmArea').show()
        }
    })
}

function validateSignUp() {
    let accUsername = $('#accUsername').val()
    let accPassword = $('#accPassword').val()
    let accFirstName = $('#accFirstName').val()
    let accLastName = $('#accLastName').val()
    let accPhoneNumber = $('#accPhoneNumber').val()
    let accIdCard = $('#accIdCard').val()
    let accDateOfBirth = $('#accDateOfBirth').val()
    let cusEmail = $('#cusEmail').val()

    if (accUsername == '' || accPassword == '' || accFirstName == '' ||
        accLastName == '' || accPhoneNumber == '' || accIdCard == '' ||
        accDateOfBirth == '') {
        Swal.fire({
            title: 'Thông báo !',
            text: "Vui lòng kiểm tra lại thông tin còn thiếu trước khi gửi!",
            icon: 'warning',
            confirmButtonColor: '#3085d6',
            confirmButtonText: 'Ok'
        })
        return false
    }

    if (accUsername == accPassword) {
        Swal.fire({
            title: 'Thông báo !',
            text: "Mật khẩu chứa thông tin trùng với tài khoản!",
            icon: 'warning',
            confirmButtonColor: '#3085d6',
            confirmButtonText: 'Ok'
        })
        return false
    }

    if (!cusEmail.match(
            /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
        )) {
        Swal.fire({
            title: 'Thông báo !',
            text: "Email không hợp lệ!",
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
    $("#form-signup").validate({
        rules: {
            accUsername: {
                required: true,
                minlength: 5,
                maxlength: 10
            },
            accPassword: {
                required: true,
                minlength: 6,
                maxlength: 10
            },
            accFirstName: {
                required: true,
                minlength: 2,
                maxlength: 20
            },
            accLastName: {
                required: true,
                minlength: 3,
                maxlength: 30
            },
            accPhoneNumber: {
                required: true,
                minlength: 10,
                maxlength: 10,
            },
            accIdCard: {
                required: true,
                minlength: 12,
                maxlength: 12,
            },
            accDateOfBirth: {
                required: true,
                date: true
            },
            cusEmail: {
                required: true,
                email: true
            }
        },
        messages: {
            accUsername: {
                required: "Tài khoản không được để trống",
                minlength: "Độ dài phải từ 5 ký tự",
                maxlength: "Độ dài không vượt quá 10 ký tự"
            },
            accPassword: {
                required: "Mật khẩu không được để trống",
                minlength: "Độ dài phải từ 6 ký tự",
                maxlength: "Độ dài không vượt quá 10 ký tự"
            },
            accFirstName: {
                required: "Tên không được để trống",
                minlength: "Độ dài phải từ 2 ký tự",
                maxlength: "Độ dài không vượt quá 20 ký tự"
            },
            accLastName: {
                required: "Họ và tên lót không được để trống",
                minlength: "Độ dài phải từ 3 ký tự",
                maxlength: "Độ dài không vượt quá 30 ký tự"
            },
            accPhoneNumber: {
                required: "Số điện thoại không được để trống",
                minlength: "Số máy quý khách vừa nhập là số không có thực",
                maxlength: "Vui lòng nhập đúng 10 ký tự"
            },
            accIdCard: {
                required: "CCCD không được để trống",
                minlength: "CCCD vừa nhập là số không có thực",
                maxlength: "Vui lòng nhập đúng 12 ký tự"
            },
            accDateOfBirth: {
                required: 'Ngày sinh không được để trống',
                date: 'Vui lòng nhập ít nhất 6 kí tự'
            },
            cusEmail: {
                required: 'Vui lòng kiểm tra lại định dạng email',
                email: 'Vui lòng kiểm tra lại định dạng email'
            }
        }
    });
    $('#loading').hide()
    $('#signUpConfirmArea').hide()
    $('#otpSendBtn').click(function () {
        if (validateSignUp())
            getOTP()
    })

    if (new URLSearchParams(window.location.search).get("dangky"))
        Swal.fire({
            title: 'Thông báo !',
            text: "Đăng ký thất bại!",
            icon: 'error',
            confirmButtonColor: '#3085d6',
            confirmButtonText: 'Ok'
        })
    $('#signUpConfirmBtn').click(function () {
        $(this).hide()
        $('#loading').show()
    })
})