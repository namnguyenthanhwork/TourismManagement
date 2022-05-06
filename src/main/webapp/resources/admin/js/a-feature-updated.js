function getFeatureInfo() {
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
        $("#feaName").val(data['feaName']);
    })
}

function validateUpdatedFeature() {
    let feaName = $('#feaName').val()

    if (feaName == '') {
        Swal.fire({
            title: 'Thông báo !',
            text: "Vui lòng kiểm tra lại thông tin còn thiếu trước khi cập nhật!",
            icon: 'warning',
            confirmButtonColor: '#3085d6',
            confirmButtonText: 'Ok'
        })
        return false
    }
    if (feaName.length < 5) {
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
    $("#featureUpdatedForm").validate({
        rules: {
            feaName: {
                required: true,
                minlength: 5,
                maxlength: 100
            }
        },
        messages: {
            feaName: {
                required: "Đặc điểm không được để trống",
                minlength: "Độ dài phải từ 5 ký tự",
                maxlength: "Độ dài không vượt quá 100 ký tự"
            }
        }
    });
    $('#loading').hide()
    $('#featureUpdatedForm').attr('action', window.location.href);
    getFeatureInfo()
    $('#featureUpdatedBtn').click(function () {
        if (validateUpdatedFeature()) {
            Swal.fire({
                title: 'Thông báo !',
                text: "Cập nhật thành công",
                icon: 'success',
                confirmButtonColor: '#3085d6'
            })
            $(this).hide()
            $('#loading').show()
        }
    })
})