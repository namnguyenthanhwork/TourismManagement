function getTourInfo() {
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
        })
}

function validateCreatedNote() {
    let thumImage = $('#thumImage').val()

    if (thumImage == '') {
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
            title: 'Tạo hình thu nhỏ thất bại',
            icon: 'error',
            confirmButtonColor: '#3085d6',
            confirmButtonText: 'Ok',
        })
    }
    // validate
    $("#thumbnailCreatedForm").validate({
        rules: {
            thumImage: {
                required: true
            }
        },
        messages: {
            thumImage: {
                required: "Chưa upload hình"
            }
        }
    });
    $('#loading').hide()
    getTourInfo()
    $('#thumbnailCreatedBtn').click(function () {
        if (validateCreatedNote()) {
            $('#overlayLoading').addClass('overlay-loading')
            $(this).hide()
            $('#loading').show()
        }
    })
})