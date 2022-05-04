function getAccountInfo() {
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
            alert("thông tin trống")
            return
        }
        $("#accUsername").val(data['accUsername']);
        $("#accPassword").val(data['accPassword']);
        $("#accFirstName").val(data['accFirstName']);
        $("#accLastName").val(data['accLastName']);
        $("#accSex").val(data['accSex']);
        $("#accIdCard").val(data['accIdCard']);
        $("#accPhoneNumber").val(data['accPhoneNumber']);
        $("#accDateOfBirth").val(new Date(data['accDateOfBirth']).toISOString().split('T')[0]);
        $("#roleName").val(data['roleSlug']);

    })
}

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
        getAccountInfo()
    })
}

$(document).ready(function () {
    $('#loading').hide()
    $('#accountUpdateForm').attr('action', window.location.href);
    getRoleInfo()
    $('#accountUpdateBtn').click(function (){
        alert("Xác nhận cập nhật tài khoản?")
        $(this).hide()
        $('#loading').show()
    })
})