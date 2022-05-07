function validateCreatedSalePercent() {
    let sperPercent = $('#sperPercent').val()

    if (sperPercent == '') {
        Swal.fire({
            title: 'Thông báo !',
            text: "Vui lòng kiểm tra lại thông tin còn thiếu trước khi tạo!",
            icon: 'warning',
            confirmButtonColor: '#3085d6',
            confirmButtonText: 'Ok'
        })
        return false
    }
    if (sperPercent < 0 || sperPercent > 100) {
        Swal.fire({
            title: 'Thông báo !',
            text: "Phần trăm giảm giá không hợp lệ!",
            icon: 'error',
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
            title: 'Tạo phần trăm giảm giá thất bại',
            icon: 'error',
            confirmButtonColor: '#3085d6',
            confirmButtonText: 'Ok',
        })
    }
    // validate
    $("#salePercentCreatedForm").validate({
        rules: {
            sperPercent: {
                required: true,
                min: 0,
                max: 100
            },
        },
        messages: {
            sperPercent: {
                required: "Phần trăm giảm giá không được để trống",
                min: "Giá trị nhỏ nhất từ 0",
                max: "Giá trị lớn nhất không quá 100",
                number: "Vui lòng nhập đúng định dạng số"
            },
        }
    });
    $('#loading').hide()
    $('#salePercentCreatedBtn').click(function () {
        $('body').css("opacity", "50%")
        if (validateCreatedSalePercent()) {
            $(this).hide()
            $('#loading').show()
        }
    })
})