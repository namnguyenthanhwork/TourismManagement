function getTourInfor(){
    fetch('/TourismManagement/quan-tri-vien/tour-du-lich/thong-tin/tong-quan')
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
        for (let i = 0; i < data.length; i++)
            options += `
                     <option value="${data[i]['tourSlug']}">${data[i]['tourTitle']}</option>
                `
        document.getElementById('tourSlug').innerHTML = options
    })
}

$(document).ready(function () {
    $('#loading').hide()
    getTourInfor()
    $('#scheduleCreatedBtn').click( function () {
        alert("Xác nhận tạo lịch trình mới")
        $(this).hide()
        $('#loading').show()
    })
})