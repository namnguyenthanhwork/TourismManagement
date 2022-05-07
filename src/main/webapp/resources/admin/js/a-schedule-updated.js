function getScheduleInfo(editor) {
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
        $('#scheTitle').val(data['scheTitle']);
        $('#tourSlug').val(data['tourSlug']);
        editor.setData(data['scheContent']);
    })
}

function getTourInfor(editor) {
    fetch('/TourismManagement/quan-tri-vien/tour-du-lich/thong-tin/tong-quan')
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
                     <option value="${data[i]['tourSlug']}">${data[i]['tourTitle']}</option>
                `
            document.getElementById('tourSlug').innerHTML = options
            getScheduleInfo(editor)
        })
}

function validateUpdatedSchedule() {
    let scheTitle = $('#scheTitle').val()

    if (scheTitle == '') {
        Swal.fire({
            title: 'Thông báo !',
            text: "Vui lòng kiểm tra lại thông tin còn thiếu trước khi cập nhật!",
            icon: 'warning',
            confirmButtonColor: '#3085d6',
            confirmButtonText: 'Ok'
        })
        return false
    }
    if (scheTitle.length < 5) {
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
            title: 'Cập nhật lịch trình thất bại',
            icon: 'error',
            confirmButtonColor: '#3085d6',
            confirmButtonText: 'Ok',
        })
    }
    // validate
    $("#scheduleUpdatedForm").validate({
        rules: {
            scheTitle: {
                required: true,
                minlength: 5,
                maxlength: 100
            }
        },
        messages: {
            scheTitle: {
                required: "Tiêu đề không được để trống",
                minlength: "Độ dài phải từ 5 ký tự",
                maxlength: "Độ dài không vượt quá 100 ký tự"
            }
        }
    });
    $('#loading').hide()
    $('#scheduleUpdatedForm').attr('action', window.location.href);
    $('#scheduleUpdatedBtn').click(function () {
        if (validateUpdatedSchedule()) {
            $('#overlayLoading').addClass('overlay-loading')
            $(this).hide()
            $('#loading').show()
        }
    })
})