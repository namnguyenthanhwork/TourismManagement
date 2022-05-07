function validateCreatedNote() {
    let noteTitle = $('#noteTitle').val()

    if (noteTitle == '') {
        Swal.fire({
            title: 'Thông báo !',
            text: "Vui lòng kiểm tra lại thông tin còn thiếu trước khi tạo!",
            icon: 'warning',
            confirmButtonColor: '#3085d6',
            confirmButtonText: 'Ok'
        })
        return false
    }
    if (noteTitle.length < 5) {
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
            title: 'Tạo ghi chú thất bại',
            icon: 'error',
            confirmButtonColor: '#3085d6',
            confirmButtonText: 'Ok',
        })
    }
    // validate
    $("#noteCreatedForm").validate({
        rules: {
            noteTitle: {
                required: true,
                minlength: 5,
                maxlength: 100
            }
        },
        messages: {
            noteTitle: {
                required: "Tiêu đề không được để trống",
                minlength: "Độ dài phải từ 5 ký tự",
                maxlength: "Độ dài không vượt quá 100 ký tự"
            }
        }
    });
    $('#loading').hide()
    $('#noteCreatedBtn').click(function () {
        $('body').css("opacity", "50%")
        if (validateCreatedNote()) {
            $(this).hide()
            $('#loading').show()
        }
    })
})