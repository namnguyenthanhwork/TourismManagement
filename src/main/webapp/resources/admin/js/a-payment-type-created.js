function redirectPageAfterCreate() {
    fetch(window.location.href).then(res => {
        return res.status
    }).then(data => {
        window.location.href = '/TourismManagement/quan-tri-vien/hinh-thuc-thanh-toan'
    })
}

$(document).ready(function () {
    $('#paymentTypeCreatedBtn').click(() => redirectPageAfterCreate())
})