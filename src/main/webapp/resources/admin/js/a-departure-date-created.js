function redirectPageAfterCreate() {
    fetch(window.location.href).then(res => {
        return res.status
    }).then(data => {
        window.location.href = '/TourismManagement/quan-tri-vien/ngay-khoi-hanh'

    })
}

$(document).ready(function () {
    fetch("/TourismManagement/quan-tri-vien/dac-diem-ngay-khoi-hanh/thong-tin")
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
                     <option value="${data[i]['feaSlug']}">${data[i]['feaName']}</option>
                `
            }
            document.getElementById('feaSlug').innerHTML = options
        })

    $('#departureDateCreatedBtn').click(() => redirectPageAfterCreate())
})