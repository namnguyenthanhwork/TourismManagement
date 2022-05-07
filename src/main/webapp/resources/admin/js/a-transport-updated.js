function getTransportInfo() {
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
        $("#tranName").val(data['tranName']);
    })
}

function validateUpdatedTransport() {
    let tranName = $('#tranName').val()

    if (tranName == '') {
        Swal.fire({
            title: 'Thông báo !',
            text: "Vui lòng kiểm tra lại thông tin còn thiếu trước khi cập nhật!",
            icon: 'warning',
            confirmButtonColor: '#3085d6',
            confirmButtonText: 'Ok'
        })
        return false
    }
    if (tranName.length < 5) {
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
    let error = new URLSearchParams(window.location.search).get('error')
    if(error!==null && parseInt(error)===1) {
        Swal.fire({
            title: 'Cập nhật phương tiện di chuyển thất bại',
            icon: 'error',
            confirmButtonColor: '#3085d6',
            confirmButtonText: 'Ok',
        })
    }
    // validate
    $("#transportUpdatedForm").validate({
        rules: {
            tranName: {
                required: true,
                minlength: 5,
                maxlength: 100
            }
        },
        messages: {
            tranName: {
                required: "Tên phương tiện không được để trống",
                minlength: "Độ dài phải từ 5 ký tự",
                maxlength: "Độ dài không vượt quá 100 ký tự"
            }
        }
    });
    $('#loading').hide()
    $('#transportUpdatedForm').attr('action', window.location.href);
    getTransportInfo()
    $('#transportUpdatedBtn').click(function () {
        $('body').css("opacity", "50%")
        if (validateUpdatedTransport()) {
            $(this).hide()
            $('#loading').show()
        }
    })
})