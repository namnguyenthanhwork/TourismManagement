function getSalePercentInfo() {
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
        $("#sperPercent").val(data['sperPercent']);
    })
}

function validateUpdatedSalePercent() {
    let sperPercent = $('#sperPercent').val()

    if (sperPercent == '') {
        Swal.fire({
            title: 'Thông báo !',
            text: "Vui lòng kiểm tra lại thông tin còn thiếu trước khi cập nhật!",
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
            title: 'Cập nhật phần trăm giảm giá thất bại',
            icon: 'error',
            confirmButtonColor: '#3085d6',
            confirmButtonText: 'Ok',
        })
    }
    // validate
    $("#salePercentUpdatedForm").validate({
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
    $('#salePercentUpdatedForm').attr('action', window.location.href);
    getSalePercentInfo()
    $('#salePercentUpdatedBtn').click(function () {
        if (validateUpdatedSalePercent()) {
            $('#overlayLoading').addClass('overlay-loading')
            $(this).hide()
            $('#loading').show()
        }
    })
})