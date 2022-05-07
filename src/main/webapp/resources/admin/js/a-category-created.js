function getStoreInfo() {
    fetch("/TourismManagement/quan-tri-vien/kho-chua/thong-tin")
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
                     <option value="${data[i]['storSlug']}">${data[i]['storName']}</option>
                `
            }
            document.getElementById('storSlug').innerHTML = options
        })
}

function validateCreatedCategory() {
    let catName = $('#catName').val()

    if (catName == '') {
        Swal.fire({
            title: 'Thông báo !',
            text: "Vui lòng kiểm tra lại thông tin còn thiếu trước khi tạo!",
            icon: 'warning',
            confirmButtonColor: '#3085d6',
            confirmButtonText: 'Ok'
        })
        return false
    }
    if (catName.length < 5) {
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
            title: 'Tạo danh mục thất bại',
            icon: 'error',
            confirmButtonColor: '#3085d6',
            confirmButtonText: 'Ok',
        })
    }

    // validate
    $("#categoryCreatedForm").validate({
        rules: {
            catName: {
                required: true,
                minlength: 5,
                maxlength: 100
            }
        },
        messages: {
            catName: {
                required: "Loại tour không được để trống",
                minlength: "Độ dài phải từ 5 ký tự",
                maxlength: "Độ dài không vượt quá 100 ký tự"
            }
        }
    });
    $('#loading').hide()
    getStoreInfo()
    $('#categoryCreatedBtn').click(function () {
        $('body').css("opacity", "50%")
        if (validateCreatedCategory()) {
            $(this).hide()
            $('#loading').show()
        }
    })
})