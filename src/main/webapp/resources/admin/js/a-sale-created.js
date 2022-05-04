function getSalePercentInfo(){
    fetch("/TourismManagement/quan-tri-vien/phan-tram-giam-gia/thong-tin")
        .then(res => {
            if (res.status != 200)
                return res.status
            return res.json()
        }).then(data => {
        if (data == 204) {
            alert("thông tin trống")
            return
        }
        let options = '';
        for (let i = 0; i < data.length; i++) {
            options += `
                     <option value="${data[i]['sperId']}">${data[i]['sperPercent']}</option>
                `
        }
        document.getElementById('sperId').innerHTML = options
    })
}

$(document).ready(function () {
    $('#loading').hide()
    getSalePercentInfo()
    $('#saleCreatedBtn').click(function () {
        alert("Xác nhận tạo giảm giá mới")
        $(this).hide()
        $('#loading').show()
    })
})