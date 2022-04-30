function redirectPageAfterCreate(){
    fetch(window.location.href).then(res => {
        return res.status
    }).then(data => {
        window.location.href = '/TourismManagement/quan-tri-vien/dac-diem-ngay-khoi-hanh'
    })
}

$(document).ready(function () {
    $('#featureCreatedBtn').click( () =>redirectPageAfterCreate())
})