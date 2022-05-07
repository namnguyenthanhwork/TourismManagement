function getRoleInfo() {
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
        $("#roleName").val(data['roleName']);
    })
}

function validateUpdateRole() {
    let roleName = $('#roleName').val()
    if (roleName == '') {
        Swal.fire({
            title: 'Thông báo !',
            text: "Vui lòng kiểm tra lại thông tin còn thiếu trước khi cập nhật!",
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
    let error = new URLSearchParams(window.location.search).get('error')
    if(error!==null && parseInt(error)===1) {
        Swal.fire({
            title: 'Cập nhật vai trò thất bại',
            icon: 'error',
            confirmButtonColor: '#3085d6',
            confirmButtonText: 'Ok',
        })
    }
    // validate
    $("#roleUpdateForm").validate({
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
    $('#roleUpdateForm').attr('action', window.location.href);
    getRoleInfo()
    $('#roleUpdatedBtn').click(function () {
        if (validateUpdateRole()) {
            $('#overlayLoading').addClass('overlay-loading')
            $(this).hide()
            $('#loading').show()
        }
    })
})