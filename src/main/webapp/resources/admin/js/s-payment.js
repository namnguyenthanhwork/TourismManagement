function searchBillInfo(billId) {
    fetch('/TourismManagement/nhan-vien/thanh-toan', {
        method: 'post',
        body: JSON.stringify({
            'billId': billId
        }),
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(res => res.json()).then(data => {
        if (jQuery.isEmptyObject(data)) {
            $('#billDetail').hide()
            Swal.fire({
                title: 'Thông báo !',
                text: "Không tìm thấy hoá đơn!",
                icon: 'error',
                confirmButtonColor: '#3085d6',
                confirmButtonText: 'Ok'
            })
            return
        }
        $('#billDetail').show()
        console.info(data)
        let accInfo = `
        <p class="text-sm text-justify p-0 my-2"><soan class="font-weight-bolder">Mã tài khoản:</soan> ${data['accId']}</p>
        <p class="text-sm text-justify p-0 my-2"><span class="font-weight-bolder">Tên tài khoản:</span> ${data['accUsername']}</p>
        <p class="text-sm text-justify p-0 my-2"><span class="font-weight-bolder">Tên người dùng:</span> ${data['accLastName']} ${data['accFirstName']}</p>
        <p class="text-sm text-justify p-0 my-2"><span class="font-weight-bolder">Ngày sinh:</span> ${data['accLastName']}</p>
        <p class="text-sm text-justify p-0 my-2"><span class="font-weight-bolder">Mã căn cước công dân:</span> ${data['accLastName']}</p>
        <p class="text-sm text-justify p-0 my-2"><span class="font-weight-bolder"
        >Số điện thoại:</span> ${data['accLastName']}</p>
        `

        let paymentTypeInfo = `
        <p class="text-sm text-justify p-0 my-2"><span class="font-weight-bolder">Mã hình thức thanh toán:</span> ${data['paytId']}</p>
        <p class="text-sm text-justify p-0 my-2"><span class="font-weight-bolder">Tên hình thức thanh toán:</span> ${data['paytName']}</p>
        `
        let billDepartureDate = new Date(data['billDepartureDate']).toLocaleDateString()
        let billIsPaid = data['billIsPaid'] ? "Đã thanh toán" : "Chưa thanh toán"
        let billShipDate = new Date(data['billShipDate']).toLocaleDateString()
        let billShipCity = data['billShipCity'] === null ? "Không có" : data['billShipCity']
        let billShipDistrict = data['billShipDistrict'] === null ? "Không có" : data['billShipDistrict']
        let billShipAddress = data['billShipAddress'] === null ? "Không có" : data['billShipAddress']

        let billInfo = `
        <p class="text-sm text-justify p-0 my-2"><span class="font-weight-bolder">Mã hóa đơn:</span> ${data['billId']}</p>
        <p class="text-sm text-justify p-0 my-2"><span class="font-weight-bolder">Tổng tiền giảm giá:</span> ${data['billTotalSaleMoney'].toLocaleString('it-IT', {style : 'currency', currency : 'VND'})}</p>
        <p class="text-sm text-justify p-0 my-2"><span class="font-weight-bolder">Tổng tiền:</span> ${data['billTotalMoney'].toLocaleString('it-IT', {style : 'currency', currency : 'VND'})}</p>
        <p class="text-sm text-justify p-0 my-2"><span class="font-weight-bolder">Ngày khởi hành:</span> ${billDepartureDate}</p>
        <div>
            <h5 class="font-weight-bolder mt-4">Địa điểm giao hàng</h5>
            <p class="text-sm text-justify p-0 my-2"><span class="font-weight-bolder">Thành phố:</span> ${billShipCity}</p>
            <p class="text-sm text-justify p-0 my-2"><span class="font-weight-bolder">Quận/Huyện:</span> ${billShipDistrict}</p>
            <p class="text-sm text-justify p-0 my-2"><span class="font-weight-bolder">Địa chỉ:</span> ${billShipAddress}</p>
            <p class="text-sm text-justify p-0 my-2"><span class="font-weight-bolder">Thời gian giao hàng:</span> ${billShipDate}</p>
        </div>
        <p class="text-sm text-justify p-0 my-2"><span class="font-weight-bolder">Trạng thái hóa đơn:</span> ${billIsPaid}</p>
        `

        let tourInfo = `
        <p class="text-sm text-justify p-0 my-2"><span class="font-weight-bolder">Mã tour du lịch:</span> ${data['postId']}</p>
        <p class="text-sm text-justify p-0 my-2"><span class="font-weight-bolder">Tên tour du lịch:</span> ${data['postTitle']}</p>
        <p class="text-sm text-justify p-0 my-2"><span class="font-weight-bolder">Mức đánh giá:</span> ${data['tourAverageRating']}</p>
        `

        let tourPrices = ``

        for (let i = 0; i < data['tourPrices'].length; i++)
            tourPrices += `
            <p class="text-sm text-justify p-0 my-2"><span class="font-weight-bolder">Đối tượng phục vụ:</span> ${data['tourPrices'][i]['svoName']}</p>
            <p class="text-sm text-justify p-0 my-2"><span class="font-weight-bolder">Số lượng đặt</span>: ${data['tourPrices'][i]['tourAmount']}</p>
            <p class="text-sm text-justify p-0 my-2"><span class="font-weight-bolder">Giá tour / đối tượng:</span> ${data['tourPrices'][i]['tourPrice'].toLocaleString('it-IT', {style : 'currency', currency : 'VND'})}</p>
            <p>----------------</p>
            `
        $('#accountInfo').html(accInfo)
        $('#paymentTypeInfo').html(paymentTypeInfo)
        $('#tourInfo').html(tourInfo)
        $('#billInfo').html(billInfo)
        $('#billPriceInfo').html(tourPrices)
        $('#billDetail').html($('#billDetail').html() +
            (!data['billIsPaid'] ?
                `<button onclick="pay(${data['billId']})" class="btn btn-primary mt-4 ml-1">Thanh toán</button>` :
                `<button onclick="location.reload()" class="btn btn-primary mt-4 ml-1">Trở về</button>`))
    })
}

function pay(billId) {
    fetch('/TourismManagement/nhan-vien/thanh-toan/cap-nhat', {
        method: 'post',
        body: JSON.stringify({
            "billId": billId
        }),
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(res => res.status).then(data => {
        if (data == 200) {
            Swal.fire({
                title: 'Thông báo !',
                text: "Thanh toán thành công!",
                icon: 'success',
                confirmButtonColor: '#3085d6',
                confirmButtonText: 'Ok'
            })
            location.reload()
            return
        }
        Swal.fire({
            title: 'Thông báo !',
            text: "Thanh toán thất bại!",
            icon: 'error',
            confirmButtonColor: '#3085d6',
            confirmButtonText: 'Ok'
        })
    })
}

$(document).ready(function () {
    //  active sidebar
    const currentLocation = location.href;
    const menuItem = document.querySelectorAll('.nav-link');
    for (let i = 0; i < menuItem.length; i++) {
        if (menuItem[i].href === currentLocation) {
            menuItem[i].classList.add('active');
        }
    }

    $('#billDetail').hide()
    $('#searchBtn').click(function () {
        let kw = $('#search').val()
        if (kw == null || kw.trim().length == 0) {
            Swal.fire({
                title: 'Thông báo !',
                text: "Dữ liệu không hợp lệ !",
                icon: 'error',
                confirmButtonColor: '#3085d6',
                confirmButtonText: 'Ok'
            })
            return
        }
        searchBillInfo(kw.trim())
    })
})