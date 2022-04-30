function redirectPageAfterCreate() {
    fetch(window.location.href).then(res => {
        return res.status
    }).then(data => {
        window.location.href = '/TourismManagement/quan-tri-vien/hinh-thu-nho'
    })
}

$(document).ready(function () {
    $('#thumbnailCreatedBtn').click(() => redirectPageAfterCreate())
})