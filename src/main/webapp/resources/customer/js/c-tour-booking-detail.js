let locationInfos = new Map()

function getTourInfo() {
    let href = window.location.href
    if (window.location.href.includes('?'))
        href = href.substring(0, window.location.href.indexOf('?'))
    let path = href + '/thong-tin';
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
        // tour info
        $('#tourImage').html(`<img src="${data['tourCoverPage']}" alt="Tour image" class="w-100 border-radius-lg shadow-lg mx-auto" />`)
        getThumbnails(data['tourSlug'])
        let schedules = ''
        for (let i = 0; i < data['schedules'].length; i++) {
            schedules += `${data['schedules'][i]['scheTitle']}`
            if (i !== (data['schedules'].length - 1))
                schedules += ' - '
        }
        let transports = ''
        for (let i = 0; i < data['transports'].length; i++) {
            transports += `${data['transports'][i]['tranName']}`
            if (i !== (data['transports'].length - 1))
                transports += ' - '
        }
        let tourInfo = `
            <h3 class="mt-lg-0 mt-4">${data['tourTitle']}</h3>
            <label class="mt-4">Thông tin tour:</label>
            <ul style="list-style: disc; list-style-position: inside;" class="ml-3">
                <li><span class="text-sm">Mã tour:</span> ${data['tourId']}</li>
                <li><span class="text-sm">Loại tour: ${data['catName']}</span></li>
                <li><span class="text-sm">Lịch trình: ${schedules.length > 0 ? schedules : "Chưa cập nhật"}</span></li>
                <li><span class="text-sm">Phương tiện di chuyển : ${transports.length > 0 ? transports : "Chưa cập nhật"}</span></l>
            </ul>
        `
        $('#tourInfo').html(tourInfo)

        // serving object info
        let servingObjectsTitle = ''
        let servingObjectsContent = ''
        for (let i = 0; i < data['servingObjects'].length; i++) {
            servingObjectsTitle += `
                <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">${data['servingObjects'][i]['svoName']}</th>
                `
            servingObjectsContent += `
                <td class="align-middle text-center">
                        <span class="text-secondary text-sm">${data['servingObjects'][i]['tourPrice'].toLocaleString('it-IT', {
                    style: 'currency',
                    currency: 'VND'
                })}</span>
                    </td>
            `
        }
        let servingObjectsHtml = `
        <thead>
                <tr>
                    <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Loại giá\\Độ tuổi</th>
                    ${servingObjectsTitle}
                </tr>
        </thead>
        <tbody>
            <tr>
                <td class="align-middle text-center">
                    <span class="text-secondary text-sm">Giá</span>
                </td>
                ${servingObjectsContent}
            </tr>
        </tbody>
        `
        $('#servingObjectDetail').html(servingObjectsHtml)

        let svoTourBooking = ''
        for (let i = 0; i < data['servingObjects'].length; i++) {
            svoTourBooking += `
                <div class="col-12 col-sm-6">
                    <label for="${data['servingObjects'][i]['svoSlug']}">${data['servingObjects'][i]['svoName']}:</label>
                    <input type="number" id="${data['servingObjects'][i]['svoSlug']}"name="${data['servingObjects'][i]['svoSlug']}"
                        placeholder="Nhập số lượng..." class="form-control" value="${i === 0 ? 1 : 0}">
                </div>
            `
        }
        $('#svoTourBooking').html(svoTourBooking)

        // departure date
        let departureDates = ''
        for (let i = 0; i < data['departureDates'].length; i++) {
            let dptId = data['departureDates'][i]['dptId']
            let dptDate = new Date(data['departureDates'][i]['dptDate']).toISOString().split('T')[0]
            let emptySlot = data['departureDates'][i]['tourEmptySlot']
            departureDates += `
                 <div class="form-group ml-3">
                   <label for="${dptId}">${dptDate} (Còn: <span class="text-danger">${emptySlot} vé</span>)</label>
                    <input type="radio" id="${dptId}" name="dptId" value="${dptId}" ${i===0?'checked':''}>
               </div>
            `
        }
        $('#dptTourBooking').html(departureDates)

        // payment type
        getPaymentTypes()
    })
}

function getThumbnails(tourSlug) {
    fetch(`/TourismManagement/tour-du-lich/${tourSlug}/hinh-thu-nho`).then(res => {
        if (res.status !== 200)
            return res.status
        return res.json()
    }).then(data => {
        let thumbnails = ''
        for (let i = 0; i < data.length; i++)
            thumbnails += `
            <figure>
                <img src="${data[i]['thumImage']}" alt="Thumbnail" class="w-75 min-height-100 max-height-100 border-radius-lg shadow tourImgCover">
            </figure>
            `
        $('#thumbnailImage').html(thumbnails)
    })
}

function getPaymentTypes() {
    fetch('/TourismManagement/tour-du-lich/hinh-thuc-thanh-toan').then(res => {
        if (res.status !== 200)
            return res.status
        return res.json()
    }).then(data => {
        let paymentTypes = ''
        for (let i = 0; i < data.length; i++)
            paymentTypes += `
               <div class="col-12 col-sm-6">
                   <label for="${data[i]['paytSlug']}">${data[i]['paytName']}</label>
                    <input type="radio" id="${data[i]['paytSlug']}" name="paytSlug" 
                    value="${data[i]['paytSlug']}" ${i===0?'checked':''}>
               </div>
            `
        $('#paymentTourBooking').html(paymentTypes)
    })
}

function checkPaymentResult() {
    let params = new URLSearchParams(window.location.search)
    const resultCode = params.get("resultCode");
    const billId = params.get("billId")
    if (resultCode != null) {
        if (parseInt(resultCode) == 1004) {
            Swal.fire({
                title: 'Đặt hàng thất bại do số tiền thanh toán vượt quá hạn mức thanh toán của bạn',
                icon: 'warning',
                confirmButtonColor: '#3085d6',
                confirmButtonText: 'Ok',
            })
        } else if (parseInt(resultCode) == 1005) {
            Swal.fire({
                title: 'Đặt hàng thất bại do url hoặc QR code đã hết hạn',
                icon: 'warning',
                confirmButtonColor: '#3085d6',
                confirmButtonText: 'Ok',
            })
        } else if (parseInt(resultCode) == 1006) {
            Swal.fire({
                title: 'Đặt hàng thất bại do bạn đã huỷ giao dịch',
                text: 'Vui lòng thử lại sau',
                icon: 'warning',
                confirmButtonColor: '#3085d6',
                confirmButtonText: 'Ok',
            })
        } else if (parseInt(resultCode) != 0) {
            Swal.fire({
                title: 'Đặt hàng thất bại do lỗi hệ thống',
                icon: 'warning',
                confirmButtonColor: '#3085d6',
                confirmButtonText: 'Ok',
            })
        } else {
            fetch('/TourismManagement//nhan-vien/dat-tour/thanh-toan/cap-nhat', {
                method: 'post',
                body: JSON.stringify({
                    'billId': billId
                }),
                headers: {
                    'Content-Type': 'application/json'
                }
            }).then(res => res.status).then(result => {
                if (result == 200) {
                    Swal.fire({
                        title: 'Thanh toán thành công !',
                        icon: 'success',
                        confirmButtonColor: '#3085d6',
                        confirmButtonText: 'Ok',
                    })
                } else {
                    Swal.fire({
                        title: 'Thanh toán thất bại !',
                        icon: 'error',
                        confirmButtonColor: '#3085d6',
                        confirmButtonText: 'Ok',
                    })
                }
            })

        }
    }
}

function getLocations() {
    fetch("https://provinces.open-api.vn/api/?depth=2").then(res => res.json())
        .then(data => {
            for (let i = 0; i < data.length; i++) {
                let districts = []
                for (let j = 0; j < data[i]['districts'].length; j++) {
                    districts.push({
                        'districtName': data[i]['districts'][j]['name']
                    })
                }
                locationInfos.set(data[i]['name'], {
                    'cityName': data[i]['name'],
                    'districts': districts
                })
            }


        })
}

function setDistricts() {
    let districts = ''
    let dData = locationInfos.get($('#billShipCity').val())['districts']
    for (let i = 0; i < dData.length; i++)
        districts += `
               <option value="${dData[i]['districtName']}">${dData[i]['districtName']}</option>
            `
    $('#billShipDistrict').html(districts)
}

function setCities() {
    let cities = ''
    for (const [key, value] of locationInfos)
        cities += `
               <option value="${key}">${value['cityName']}</option>
        `

    $('#billShipCity').html(cities)
}

function setLocations() {
    let htmlLocation = `
        <div class="form-group">
                    <label for="billShipDate">Ngày nhận hàng (*)</label>
                    <input type="date" id="billShipDate" name="billShipDate">
                </div>
                <div class="form-group">
                    <label for="billShipCity">Thành phố (*)</label>
                    <select id="billShipCity" name="billShipCity">
                    
                    </select>
                </div>
                <div class="form-group">
                    <label for="billShipDistrict">Quận huyện (*)</label>
                    <select  id="billShipDistrict" name="billShipDistrict">
                    
                    </select>
                </div>
                <div class="form-group">
                    <label for="billShipAddress">Địa chỉ (*)</label>
                    <input type="text" id="billShipAddress" name="billShipAddress">
                </div>
    `
    $('#billShip').html(htmlLocation)

    setCities()
    setDistricts()
    $('#billShipCity').change(function () {
        setDistricts()
    })
}

$(document).ready(function () {

    if (new URLSearchParams(window.location.search).get("error") == 1)
        Swal.fire({
            title: 'Thông báo !',
            text: "Đặt vé không thành công do số lượng vé vượt quá giới hạn!",
            icon: 'error',
            confirmButtonColor: '#3085d6',
            confirmButtonText: 'Ok'
        })

    getLocations()
    $('#loading').hide()
    $('#tourBookingForm').attr('action', window.location.href);
    getTourInfo()
    checkPaymentResult()
    $('#tourBookingdBtn').click(function () {
        alert("Xác nhận đặt tour?")
        $(this).hide()
        $('#loading').show()
    })
    $("input[type=radio][name='shipAddress']").change(function () {
        if ($("input[name='shipAddress']:checked").val() === 'ship2')
            setLocations()
        else
            $('#billShip').html('')

    })
})