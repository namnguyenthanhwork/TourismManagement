function redirectPageAfterCreate() {
    fetch(window.location.href).then(res => {
        return res.status
    }).then(data => {
        window.location.href = '/TourismManagement/quan-tri-vien/kho-chua'
    })
}

$(document).ready(function () {
    $('#storageCreatedBtn').click(() => redirectPageAfterCreate())
})