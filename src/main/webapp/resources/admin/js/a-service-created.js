function redirectPageAfterCreate(){
    fetch(window.location.href).then(res => {
        return res.status
    }).then(data => {
        window.location.href = '/TourismManagement/quan-tri-vien/dich-vu'
    })
}

$(document).ready(function () {
    $('#serviceCreatedBtn').click( () =>redirectPageAfterCreate())
})