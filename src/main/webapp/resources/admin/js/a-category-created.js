function redirectPageAfterCreate() {
    fetch(window.location.href).then(res => {
        return res.status
    }).then(data => {
        window.location.href = '/TourismManagement/quan-tri-vien/loai-tour'

    })
}

$(document).ready(function () {
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

    $('#categoryCreatedBtn').click(() => redirectPageAfterCreate())
})