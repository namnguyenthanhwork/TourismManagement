function redirectPageAfterCreate(){
    fetch(window.location.href).then(res => {
        return res.status
    }).then(data => {
        window.location.href = '/TourismManagement/quan-tri-vien/doi-tuong-phuc-vu'
    })
}

$(document).ready(function () {
    $('#servingObjectCreatedBtn').click( () =>redirectPageAfterCreate())
})