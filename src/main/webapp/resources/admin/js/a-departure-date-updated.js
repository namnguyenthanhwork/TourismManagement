function getDepartureDateInfo() {
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
        $('#dptDate').val(new Date(data['dptDate']).toISOString().split('T')[0]);
        $('#feaSlug').val(data['feaSlug'])
    })
}

function getFeatureInfo() {
    fetch("/TourismManagement/quan-tri-vien/dac-diem-ngay-khoi-hanh/thong-tin")
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
            for (let i = 0; i < data.length; i++)
                options += `
                     <option value="${data[i]['feaSlug']}">${data[i]['feaName']}</option>
                `

            document.getElementById('feaSlug').innerHTML = options
            getDepartureDateInfo()
        })
}


function validateUpdatedDepartureDate() {
    let dptDate = $('#dptDate').val()

    if (dptDate == '') {
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
            title: 'Cập nhật ngày khởi hành thất bại',
            icon: 'error',
            confirmButtonColor: '#3085d6',
            confirmButtonText: 'Ok',
        })
    }
    // validate
    $("#departureDateUpdatedForm").validate({
        rules: {
            dptDate: {
                required: true,
                date: true
            },
        },
        messages: {
            dptDate: {
                required: "Ngày khởi hành không được để trống",
                data: "Ngày khởi hành không được để trống",
            },
        }
    });

    $('#loading').hide()
    $('#departureDateUpdatedForm').attr('action', window.location.href);
    getFeatureInfo()
    $('#departureDateUpdatedBtn').click(function () {
        if (validateUpdatedDepartureDate()) {
            $('#overlayLoading').addClass('overlay-loading')
            $(this).hide()
            $('#loading').show()
        }
    })
})