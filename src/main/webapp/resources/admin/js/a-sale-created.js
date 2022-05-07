function getSalePercentInfo() {
    fetch("/TourismManagement/quan-tri-vien/phan-tram-giam-gia/thong-tin")
        .then(res => {
            if (res.status != 200)
                return res.status
            return res.json()
        }).then(data => {
            if (data == 204) {
                Swal.fire({
                    title: 'Thông báo !',
                    text: "Thông tin trống!",
                    icon: 'warning',
                    confirmButtonColor: '#3085d6',
                    confirmButtonText: 'Ok'
                })
                return
            }
            let options = '';
            for (let i = 0; i < data.length; i++) {
                options += `
                     <option value="${data[i]['sperId']}">${data[i]['sperPercent']}</option>
                `
            }
            document.getElementById('sperId').innerHTML = options
        })
}

function validateCreatedSale() {
    let saleFromDate = $('#saleFromDate').val()
    let saleToDate = $('#saleToDate').val()
    let sperId = $('#sperId').val()

    if (saleFromDate == '' || saleToDate == '' || sperId == '') {
        Swal.fire({
            title: 'Thông báo !',
            text: "Vui lòng kiểm tra lại thông tin còn thiếu trước khi tạo!",
            icon: 'warning',
            confirmButtonColor: '#3085d6',
            confirmButtonText: 'Ok'
        })
        return false
    }
    if (sperId < 0 || sperId > 100) {
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
            title: 'Tạo giảm giá thất bại',
            icon: 'error',
            confirmButtonColor: '#3085d6',
            confirmButtonText: 'Ok',
        })
    }
    // validate
    $("#saleCreatedForm").validate({
        rules: {
            saleFromDate: {
                required: true,
                date: true
            },
            saleToDate: {
                required: true,
                date: true
            },
            sperId: {
                required: true,
                min: 0,
                max: 100
            },
        },
        messages: {
            saleFromDate: {
                required: "Ngày bắt đầu không được để trống",
                date: "Ngày bắt đầu không được để trống"
            },
            saleToDate: {
                required: "Ngày kết thúc không được để trống",
                date: "Ngày kết thúc không được để trống"
            },
            sperId: {
                required: "Phần trăm giảm giá không được để trống",
                min: "Giá trị nhỏ nhất từ 0",
                max: "Giá trị lớn nhất không quá 100"
            },
        }
    });
    $('#loading').hide()
    getSalePercentInfo()
    $('#saleCreatedBtn').click(function () {
        if (validateCreatedSale()) {
            $(this).hide()
            $('#loading').show()
        }
    })
})