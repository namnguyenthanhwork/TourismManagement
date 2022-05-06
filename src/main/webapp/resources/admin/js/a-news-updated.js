function getNewsInfo(editor) {
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
        $("#newsTitle").val(data['newsTitle']);
        editor.setData(data['newsContent']);
    })
}

function validateUpdateNews() {
    let newsTitle = $('#newsTitle').val()
    let newsContent = $('#editor').val()
    if (newsTitle == '' || newsContent == '') {
        Swal.fire({
            title: 'Thông báo !',
            text: "Vui lòng kiểm tra lại thông tin còn thiếu trước khi cập nhật!",
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
    $("#newsUpdatedForm").validate({
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
    $('#newsUpdatedForm').attr('action', window.location.href);
    $('#newsUpdatedBtn').click(function () {
        if (validateUpdateNews()) {
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