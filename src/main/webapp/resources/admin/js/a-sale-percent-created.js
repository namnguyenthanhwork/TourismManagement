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
        if (validateCreatedSalePercent()) {
            Swal.fire({
                title: 'Thông báo !',
                text: "Tạo thành công",
                icon: 'success',
                confirmButtonColor: '#3085d6'
            })
            $(this).hide()
            $('#loading').show()
        }
    })
})