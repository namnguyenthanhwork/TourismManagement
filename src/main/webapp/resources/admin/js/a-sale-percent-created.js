function redirectPageAfterCreate() {
    fetch(window.location.href).then(res => {
        return res.status
    }).then(data => {
        window.location.href = '/TourismManagement/quan-tri-vien/phan-tram-giam-gia'
    })
}

$(document).ready(function () {
    $('#salePercentCreatedBtn').click(() => redirectPageAfterCreate())
})