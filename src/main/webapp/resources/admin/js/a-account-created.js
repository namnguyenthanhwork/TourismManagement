function redirectPageAfterCreate() {
    fetch(window.location.href).then(res => {
        return res.status
    }).then(data => {
        window.location.href = '/TourismManagement/quan-tri-vien/tai-khoan'

    })
}

$(document).ready(function () {
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

    $('#accountCreatedBtn').click(() => redirectPageAfterCreate())
})