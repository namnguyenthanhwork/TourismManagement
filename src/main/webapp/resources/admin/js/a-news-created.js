let gEditor

function validateCreateNews() {
    let newsTitle = $('#newsTitle').val()
    let newsDescription = $('#newsDescription').val()
    let newsContent = gEditor.getData()
    if (newsTitle === '' || newsDescription === '' || newsContent === '') {
        Swal.fire({
            title: 'Thông báo !',
            text: "Vui lòng kiểm tra lại thông tin còn thiếu trước khi tạo!",
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
            title: 'Tạo tin tức thất bại',
            icon: 'error',
            confirmButtonColor: '#3085d6',
            confirmButtonText: 'Ok',
        })
    }
    // validate
    $("#newsCreatedForm").validate({
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
                minlength: 1
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
    $('#newsCreatedBtn').click(function () {
        if (validateCreateNews()) {
            $('#overlayLoading').addClass('overlay-loading')
            $(this).hide()
            $('#loading').show()
        }
    })
})