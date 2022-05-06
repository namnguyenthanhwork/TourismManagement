let gTourInfo = null;

function getTourInfo(editor) {
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
        gTourInfo = data
        $('#tourTitle').val(data['tourTitle'])
        editor.setData(data['tourContent'])
        getCategoryInfo()
        getSaleInfo()
        getServingObjectInfo()
        getServiceInfo()
        getDepartureDateInfo()
        getTransportInfo()
        getNoteInfo()
    })
}

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
            $('#catSlug').html(options)
            $('#catSlug').val(gTourInfo['catSlug'])
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
                let saleFromDate = new Date(data[i]['saleFromDate']).toLocaleDateString()
                let saleToDate = new Date(data[i]['saleToDate']).toLocaleDateString()
                options += `
                     <option value="${data[i]['saleId']}">${data[i]['sperPercent']}% (${saleFromDate} - ${saleToDate})</option>
                `
            }
            $('#saleId').html(options)
            $('#saleId').val(gTourInfo['saleId'] == null ? -1 : gTourInfo['saleId'])
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
                <div class="form-group">
                    <input type="number" name="${data[i]['svoSlug']}Price" id="${data[i]['svoSlug']}Price" min="0" value="0"><span> VNĐ</span>
                </div>
            `
            }
            $('#svoSlugs').html(options)
            $('#prices').html(prices)

            let servingObjects = []
            for (let i = 0; i < gTourInfo['servingObjects'].length; i++) {
                let svoSlug = gTourInfo['servingObjects'][i]['svoSlug']
                let svoPrice = gTourInfo['servingObjects'][i]['tourPrice']
                servingObjects.push(svoSlug)
                $(`#${svoSlug}Price`).val(svoPrice)
            }
            if (servingObjects.length > 0)
                $('#svoSlugs').val(servingObjects)

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
            $('#servSlugs').html(options)
            let services = []
            for (let i = 0; i < gTourInfo['services'].length; i++)
                services.push(gTourInfo['services'][i]['servSlug'])

            if (services.length > 0)
                $('#servSlugs').val(services)
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
                     <option value="${data[i]['dptId']}">${new Date(data[i]['dptDate']).toLocaleDateString()}</option>
                `
                tourAmount += `
                <div class="form-group">
                    <input type="number" name="${data[i]['dptId']}Amount" id ="${data[i]['dptId']}Amount" min="0" value="0">
                </div>
            `
            }
            $('#dptIds').html(options)
            $('#tourAmounts').html(tourAmount)

            let departureDateIds = []
            for (let i = 0; i < gTourInfo['departureDates'].length; i++) {
                let dptId = gTourInfo['departureDates'][i]['dptId']
                let tourAmount = gTourInfo['departureDates'][i]['tourAmount']
                departureDateIds.push(dptId)
                $(`#${dptId}Amount`).val(tourAmount)
            }
            if (departureDateIds.length > 0)
                $('#dptIds').val(departureDateIds)
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

            $('#tranSlugs').html(options)
            let transports = []
            for (let i = 0; i < gTourInfo['transports'].length; i++)
                transports.push(gTourInfo['transports'][i]['tranSlug'])

            if (transports.length > 0)
                $('#tranSlugs').val(transports)
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
                     <option value="${data[i]['noteSlug']}">${data[i]['noteTitle']}</option>
                `
            }
            $('#noteSlugs').html(options)
            let notes = []
            for (let i = 0; i < gTourInfo['notes'].length; i++)
                notes.push(gTourInfo['notes'][i]['noteSlug'])

            if (notes.length > 0)
                $('#noteSlugs').val(notes)
        })
}

function validateUpdateTour() {
    let tourTitle = $('#tourTitle').val()
    if (tourTitle == '') {
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
    // validate
    $("#tourUpdatedForm").validate({
        rules: {
            tourTitle: {
                required: true,
                minlength: 10,
                maxlength: 50
            }
        },
        messages: {
            tourTitle: {
                required: "Tiêu đề không được để trống",
                minlength: "Độ dài phải từ 10 ký tự",
                maxlength: "Độ dài không vượt quá 50 ký tự"
            }
        }
    });
    $('#loading').hide()
    $('#tourUpdatedForm').attr('action', window.location.href);
    $('#tourUpdatedBtn').click(function () {
        if (validateUpdateTour()) {
            Swal.fire({
                title: 'Thông báo !',
                text: "Cập nhật thành công",
                icon: 'success',
                confirmButtonColor: '#3085d6'
            })
            $(this).hide()
            $('#loading').show()
        }
    })
})