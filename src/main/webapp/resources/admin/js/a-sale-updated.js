function getSaleInfo() {
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
        $("#saleFromDate").val(new Date(data['saleFromDate']).toISOString().split('T')[0]);
        $("#saleToDate").val(new Date(data['saleToDate']).toISOString().split('T')[0]);
        $('#sperId').val(data['sperId'])
    })
}

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
            getSaleInfo()
        })
}

function validateUpdatedSale() {
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
            title: 'Cập nhật giảm giá thất bại',
            icon: 'error',
            confirmButtonColor: '#3085d6',
            confirmButtonText: 'Ok',
        })
    }
    // validate
    $("#saleUpdatedForm").validate({
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
    $('#saleUpdatedForm').attr('action', window.location.href);
    getSalePercentInfo()

    $('#saleUpdatedBtn').click(function () {
        if (validateUpdatedSale()) {
            $('#overlayLoading').addClass('overlay-loading')
            $(this).hide()
            $('#loading').show()
        }
    })
})