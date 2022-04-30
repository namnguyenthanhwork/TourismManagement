function redirectPageAfterCreate(){
    fetch(window.location.href).then(res => {
        return res.status
    }).then(data => {
        window.location.href = '/TourismManagement/quan-tri-vien/phuong-tien-di-chuyen'
    })
}

$(document).ready(function () {
    $('#transportCreatedBtn').click( () =>redirectPageAfterCreate())
})