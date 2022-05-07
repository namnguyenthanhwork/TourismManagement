let gEditor
function getCategoryInfo() {
    fetch("/TourismManagement/quan-tri-vien/loai-tour/thong-tin")
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
                     <option value="${data[i]['catSlug']}">${data[i]['catName']}</option>
                `
            }
            document.getElementById('catSlug').innerHTML = options
        })
}

function getSaleInfo() {
    fetch("/TourismManagement/quan-tri-vien/giam-gia/thong-tin")
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
            let options = '<option value="-1">Không giảm giá</option>';
            for (let i = 0; i < data.length; i++) {
                let saleFromDate = new Date(data[i]['saleFromDate']).toISOString().split('T')[0]
                let saleToDate = new Date(data[i]['saleToDate']).toISOString().split('T')[0]
                options += `
                     <option value="${data[i]['saleId']}">${data[i]['sperPercent']}% (${saleFromDate} - ${saleToDate})</option>
                `
            }
            document.getElementById('saleId').innerHTML = options
        })
}

function getServingObjectInfo() {
    fetch("/TourismManagement/quan-tri-vien/doi-tuong-phuc-vu/thong-tin")
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
            let options = ''
            let prices = ''
            for (let i = 0; i < data.length; i++) {
                options += `
                     <option value="${data[i]['svoSlug']}">${data[i]['svoName']}
                     </option>
                `
                prices += `
                <div class="form-group d-flex align-items-center flex-wrap">
                    <label for="${data[i]['svoSlug']}Price"  style="margin-bottom:unset;">${data[i]['svoName']}: </label>
                    <input type="number" name="${data[i]['svoSlug']}Price" id="${data[i]['svoSlug']}Price" class="form-control ml-2" style="width:150px;" min="0" value="0"><label  style="margin-bottom:unset;"> VNĐ</label>
                </div>
            `
            }
            document.getElementById('svoSlugs').innerHTML = options
            document.getElementById('prices').innerHTML = prices
        })
}

function getServiceInfo() {
    fetch("/TourismManagement/quan-tri-vien/dich-vu/thong-tin")
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
                     <option value="${data[i]['servSlug']}">${data[i]['servTitle']}</option>
                `
            }
            document.getElementById('servSlugs').innerHTML = options
        })
}

function getDepartureDateInfo() {
    fetch("/TourismManagement/quan-tri-vien/ngay-khoi-hanh/thong-tin")
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
            let options = ''
            let tourAmount = ''
            for (let i = 0; i < data.length; i++) {
                options += `
                     <option value="${data[i]['dptId']}">${new Date(data[i]['dptDate']).toISOString().split('T')[0]}</option>
                `
                tourAmount += `
                <div class="form-group d-flex align-items-center flex-wrap">
                <label for="${data[i]['dptId']}Amount"  style="margin-bottom:unset;">Ngày ${new Date(data[i]['dptDate']).toISOString().split('T')[0]}: </label>
                <input type="number" name="${data[i]['dptId']}Amount" id="${data[i]['dptId']}Amount" class="form-control ml-2" style="width:70px;    padding: 5px 15px;" min="0" value="0"><label  style="margin-bottom:unset;"> Tour</label>
            </div>
            `
            }
            document.getElementById('dptIds').innerHTML = options
            document.getElementById('tourAmounts').innerHTML = tourAmount
        })
}

function getTransportInfo() {
    fetch("/TourismManagement/quan-tri-vien/phuong-tien-di-chuyen/thong-tin")
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
                     <option value="${data[i]['tranSlug']}">${data[i]['tranName']}</option>
                `
            }
            document.getElementById('tranSlugs').innerHTML = options
        })
}

function getNoteInfo() {
    fetch("/TourismManagement/quan-tri-vien/ghi-chu/thong-tin")
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
                     <option value="${data[i]['noteSlug']}" ${i===0?'selected':''}>${data[i]['noteTitle']}</option>
                `
            }
            document.getElementById('noteSlugs').innerHTML = options
        })
}

function validateCreateTour() {
    let tourTitle = $('#tourTitle').val()
    let tourContent = gEditor.getData()
    if (tourTitle === '' || tourContent==='') {
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
            title: 'Tạo tour du lịch thất bại',
            icon: 'error',
            confirmButtonColor: '#3085d6',
            confirmButtonText: 'Ok',
        })
    }
    // validate
    $("#createdTourForm").validate({
        rules: {
            tourTitle: {
                required: true,
                minlength: 10,
                maxlength: 50
            },
            tourContent: {
                required: true,
                minlength: 1
            }
        },
        messages: {
            tourTitle: {
                required: "Tiêu đề không được để trống",
                minlength: "Độ dài phải từ 10 ký tự",
                maxlength: "Độ dài không vượt quá 50 ký tự"
            },
            tourContent: {
                required: "Nội dung không được để trống",
            }
        }
    });

    $('#loading').hide()
    getCategoryInfo()
    getSaleInfo()
    getServingObjectInfo()
    getServiceInfo()
    getDepartureDateInfo()
    getTransportInfo()
    getNoteInfo()
    $('#tourCreatedBtn').click(function () {
        $('body').css("opacity", "50%")
        if (validateCreateTour()) {
            $(this).hide()
            $('#loading').show()
        }
    })
})