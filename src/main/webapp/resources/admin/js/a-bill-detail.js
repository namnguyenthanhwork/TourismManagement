function getBillInfo() {
    let path = window.location.href + '/chi-tiet';
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
        let billDepartureDate = new Date(data['billDepartureDate']).toISOString().split('T')[0]
        let billIsPaid = data['billIsPaid'] ? "Đã thanh toán" : "Chưa thanh toán"
        let billShipDate = new Date(data['billShipDate']).toISOString().split('T')[0]
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
    })
}

$(document).ready(function () {
    getBillInfo()
})