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
            alert("thông tin trống")
            return
        }
        // tour info
        $('#tourImage').html(`<img src="${data['tourCoverPage']}" alt="Tour image" style="width: 400px; height: 400px" />`)
        getThumbnails(data['tourId'])
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
            <h5>${data['tourTitle']}</h5>
            <div>
                <p>Mã tour: ${data['tourId']}</p>
                <p>Loại tour: ${data['catName']}</p>
                <p>Lịch trình: ${schedules.length > 0 ? schedules : "Chưa cập nhật"}</p>
                <p>Phương tiện di chuyển : ${transports.length > 0 ? transports : "Chưa cập nhật"}</p>
            </div>
        `
        $('#tourInfo').html(tourInfo)

        // serving object info
        let servingObjectsTitle = ''
        let servingObjectsContent = ''
        for (let i = 0; i < data['servingObjects'].length; i++) {
            servingObjectsTitle += `
                <th>${data['servingObjects'][i]['svoName']}</th>
            `
            servingObjectsContent += `
                <td>${data['servingObjects'][i]['tourPrice']} VNĐ</td>
            `
        }
        let servingObjectsHtml = `
            <thead>
                  <tr>
                        <th>Loại giá\\Độ tuổi</th>
                        ${servingObjectsTitle}
                  </tr>
            </thead>
            <tbody>
                <tr>
                    <td>Giá</td>
                    ${servingObjectsContent}
                </tr>
            </tbody>
        `
        $('#servingObjectDetail').html(servingObjectsHtml)

        let svoTourBooking = ''
        for (let i = 0; i < data['servingObjects'].length; i++) {
            svoTourBooking += `
                <div class="form-group">
                    <label for="${data['servingObjects'][i]['svoSlug']}">${data['servingObjects'][i]['svoName']}</label>
                    <input type="number" id="${data['servingObjects'][i]['svoSlug']}"name="${data['servingObjects'][i]['svoSlug']}"
                           placeholder="Nhập số lượng..">
                </div>
            `
        }
        $('#svoTourBooking').html(svoTourBooking)

        // departure date
        let departureDates = ''
        for (let i = 0; i < data['departureDates'].length; i++) {
            let dptId = data['departureDates'][i]['dptId']
            let dptDate = new Date(data['departureDates'][i]['dptDate']).toLocaleDateString()
            departureDates += `
                 <div class="form-group">
                   <label for="${dptId}">${dptDate}</label>
                    <input type="radio" id="${dptId}" name="dptId" value="${dptId}">
               </div>
            `
        }
        $('#dptTourBooking').html(departureDates)

        // payment type
        getPaymentTypes(data['tourId'])


    })
}
function getThumbnails(tourId) {
    fetch(`/TourismManagement/nhan-vien/dat-tour/${tourId}/hinh-thu-nho`).then(res => {
        if (res.status !== 200)
            return res.status
        return res.json()
    }).then(data => {
        let thumbnails = ''
        for (let i = 0; i < data.length; i++)
            thumbnails += `
                <img src="${data[i]['thumImage']}" alt="Thumbnail"  style="width: 100px; height: 100px">
            `
        $('#thumbnailImage').html(thumbnails)
    })
}

function getPaymentTypes(tourId) {
    fetch(`/TourismManagement/nhan-vien/dat-tour/${tourId}/hinh-thuc-thanh-toan`).then(res => {
        if (res.status !== 200)
            return res.status
        return res.json()
    }).then(data => {
        let paymentTypes = ''
        for (let i = 0; i < data.length; i++)
            paymentTypes += `
               <div class="form-group">
                   <label for="${data[i]['paytSlug']}">${data[i]['paytName']}</label>
                    <input type="radio" id="${data[i]['paytSlug']}" name="paytSlug" value="${data[i]['paytSlug']}">
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


$(document).ready(function (){
    $('#loading').hide()
    $('#tourBookingForm').attr('action', window.location.href+'/thanh-toan');
    getTourInfo()
    checkPaymentResult()
    $('#tourBookingdBtn').click(function (){
        alert("Xác nhận đặt tour?")
        $(this).hide()
        $('#loading').show()
    })
})