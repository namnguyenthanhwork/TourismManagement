function getBillInfo() {
    let path = window.location.href + '/chi-tiet';
    fetch(path).then(res => {
        if (res.status !== 200)
            return res.status
        return res.json()
    }).then(data => {
        if (data === 204) {
            alert('thông tin trống')
            return
        }
        let accInfo = `
            <p>Mã tài khoản: ${data['accId']}</p>
            <p>Tên tài khoản: ${data['accUsername']}</p>
            <p>Tên người dùng: ${data['accLastName']} ${data['accFirstName']}</p>
            <p>Ngày sinh: ${data['accLastName']}</p>
            <p>Mã căn cước công dân: ${data['accLastName']}</p>
            <p>Số điện thoại: ${data['accLastName']}</p>
        `

        let paymentTypeInfo = `
             <p>Mã hình thức thanh toán: ${data['paytId']}</p>
             <p>Tên hình thức thanh toán: ${data['paytName']}</p>
        `
        let billDepartureDate = new Date(data['billDepartureDate']).toLocaleDateString()
        let billIsPaid = data['billIsPaid'] ? "Đã thanh toán" : "Chưa thanh toán"
        let billShipDate = new Date(data['billShipDate']).toLocaleDateString()
        let billShipCity = data['billShipCity'] === null ? "Không có" : data['billShipCity']
        let billShipDistrict = data['billShipDistrict'] === null ? "Không có" : data['billShipDistrict']
        let billShipAddress = data['billShipAddress'] === null ? "Không có" : data['billShipAddress']

        let billInfo = `
            <p>Mã hóa đơn: ${data['billId']}</p>
            <p>Tổng tiền giảm giá: ${data['billTotalSaleMoney']} VNĐ</p>
            <p>Tổng tiền: ${data['billTotalMoney']}</p>
            <p>Ngày khởi hành: ${billDepartureDate}</p>
            <div>
                <h3>Địa điểm giao hàng</h3>
                <p>Thành phố: ${billShipCity}</p>
                <p>Quận/Huyện: ${billShipDistrict}</p>
                <p>Địa chỉ: ${billShipAddress}</p>
                <p>Thời gian giao hàng: ${billShipDate}</p>
            </div>
            <p>Trạng thái hóa đơn: ${billIsPaid}</p>
        `

        let tourInfo = `
            <p>Mã tour du lịch: ${data['postId']}</p>
            <p>Tên tour du lịch: ${data['postTitle']}</p>
            <p>Mức đánh giá: ${data['tourAverageRating']}</p>
        `

        let tourPrices = ``

        for (let i = 0; i < data['tourPrices'].length; i++)
            tourPrices += `
                 <p>Đối tượng phục vụ: ${data['tourPrices'][i]['svoName']}</p>
                 <p>Số lượng đặt: ${data['tourPrices'][i]['tourAmount']}</p>
                 <p>Giá tour / đối tượng: ${data['tourPrices'][i]['tourPrice']}</p>
                 <br><br>
            `
        $('#accountInfo').html(accInfo)
        $('#paymentTypeInfo').html(paymentTypeInfo)
        $('#tourInfo').html(tourInfo)
        $('#billInfo').html(billInfo)
        $('#billPriceInfo').html(tourPrices)
    })
}

$(document).ready(function(){
    getBillInfo()
})