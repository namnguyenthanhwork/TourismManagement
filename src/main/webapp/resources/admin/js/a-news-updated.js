let gEditor
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
        $("#newsDescription").val(data['newsDescription'])
        editor.setData(data['newsContent']);
    })
}

function validateUpdateNews() {
    let newsTitle = $('#newsTitle').val()
    let newsDescription = $('#newsDescription').val()
    let newsContent = gEditor.getData()
    if (newsTitle === '' || newsDescription === '' || newsContent === '') {
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
    let error = new URLSearchParams(window.location.search).get('error')
    if(error!==null && parseInt(error)===1) {
        Swal.fire({
            title: 'Cập nhật tin tức thất bại',
            icon: 'error',
            confirmButtonColor: '#3085d6',
            confirmButtonText: 'Ok',
        })
    }

    // validate
    $("#newsUpdatedForm").validate({
        rules: {
            newsTitle: {
                required: true,
                minlength: 10,
                maxlength: 50
            },
            newsDescription:{
                required: true
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
            },
            newsDescription:{
                required: "Mô tả tin tức không được để trống"
            }
        }
    });
    $('#loading').hide()
    $('#newsUpdatedForm').attr('action', window.location.href);
    $('#newsUpdatedBtn').click(function () {
        if (validateUpdateNews()) {
            $('#overlayLoading').addClass('overlay-loading')
            $(this).hide()
            $('#loading').show()
        }
    })
})