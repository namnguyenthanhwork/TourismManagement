function getRoleInfo(){
    fetch("/TourismManagement/quan-tri-vien/vai-tro/thong-tin")
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
                     <option value="${data[i]['roleSlug']}">${data[i]['roleName']}</option>
                `
        }
        document.getElementById('roleName').innerHTML = options
    })

}
$(document).ready(function () {
    $('#loading').hide()
    getRoleInfo()
    $('#accountCreatedBtn').click(function (){
        alert("Xác nhận tạo tài khoản?")
        $(this).hide()
        $('#loading').show()
    })
})