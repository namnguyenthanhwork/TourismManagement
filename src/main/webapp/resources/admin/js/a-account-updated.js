function getAccountInfo() {
    let href = window.location.href
    if (href.includes('?'))
        href = href.substring(0, href.indexOf('?'))
    let path = href + '/chinh-sua'
    fetch(path).then(res => {
        if (res.status !== 200)
            return res.status
        return res.json()
    }).then(data => {
        if (data === 204) {
            Swal.fire({
                title: 'Thông báo !',
                text: "Thông tin trống!",
                icon: 'warning',
                confirmButtonColor: '#3085d6',
                confirmButtonText: 'Ok'
            })
            return
        }

        $("#accUsername").val(data['accUsername']);
        $("#accPassword").val(data['accPassword']);
        $("#accFirstName").val(data['accFirstName']);
        $("#accLastName").val(data['accLastName']);
        $("#accSex").val(data['accSex']);
        $("#accIdCard").val(data['accIdCard']);
        $("#accPhoneNumber").val(data['accPhoneNumber']);
        $("#accDateOfBirth").val(new Date(data['accDateOfBirth']).toISOString().split('T')[0]);
        $("#roleName").val(data['roleSlug']);
    })
}

function getRoleInfo() {
    fetch("/TourismManagement/quan-tri-vien/vai-tro/thong-tin")
        .then(res => {
            if (res.status != 200)
                return res.status
            return res.json()
        }).then(data => {
            if (data == 204) {
                Swal.fire({
                    title: 'Thông báo !',
                    text: "Thông tin trống!",
                    icon: 'warning',
                    confirmButtonColor: '#3085d6',
                    confirmButtonText: 'Ok'
                })
                return
            }
            let options = '';
            for (let i = 0; i < data.length; i++) {
                options += `<option value="${data[i]['roleSlug']}">${data[i]['roleName']}</option>`
            }
            document.getElementById('roleName').innerHTML = options
            getAccountInfo()
        })
}

function validateUpdateAccount() {
    let accUsername = $('#accUsername').val()
    let accPassword = $('#accPassword').val()
    let accFirstName = $('#accFirstName').val()
    let accLastName = $('#accLastName').val()
    let accPhoneNumber = $('#accPhoneNumber').val()
    let accIdCard = $('#accIdCard').val()
    let accDateOfBirth = $('#accDateOfBirth').val()
    let accAvatar = $('#accAvatar').val()

    if (accUsername == '' || accPassword == '' || accFirstName == '' ||
        accLastName == '' || accPhoneNumber == '' || accIdCard == '' ||
        accDateOfBirth == '' || accAvatar == '') {
        Swal.fire({
            title: 'Thông báo !',
            text: "Vui lòng kiểm tra lại thông tin còn thiếu trước khi cập nhật!",
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
    return true
}

$(document).ready(function () {
    let error = new URLSearchParams(window.location.search).get('error')
    if(error!==null && parseInt(error)===1) {
        Swal.fire({
            title: 'Cập nhật tài khoản thất bại',
            icon: 'error',
            confirmButtonColor: '#3085d6',
            confirmButtonText: 'Ok',
        })
    }

    // validate
    $("#accountUpdateForm").validate({
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
            accAvatar: {
                required: true
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
            accAvatar: {
                required: 'Chưa upload file'
            }
        }
    });

    $('#loading').hide()
    $('#accountUpdateForm').attr('action', window.location.href);
    getRoleInfo()
    $('#accountUpdateBtn').click(function () {
        if (validateUpdateAccount()) {
            $('#overlayLoading').addClass('overlay-loading')
            $(this).hide()
            $('#loading').show()
        }
    })
})