function getStoreInfo(){
    fetch("/TourismManagement/quan-tri-vien/kho-chua/thong-tin")
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
                     <option value="${data[i]['storSlug']}">${data[i]['storName']}</option>
                `
        }
        document.getElementById('storSlug').innerHTML = options
    })
}

$(document).ready(function () {
    $('#loading').hide()
    getStoreInfo()
    $('#categoryCreatedBtn').click(function () {
        alert("Xác nhận tạo loại tour mới")
        $(this).hide()
        $('#loading').show()
    })
})