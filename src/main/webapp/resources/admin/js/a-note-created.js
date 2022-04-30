function redirectPageAfterCreate(){
    fetch(window.location.href).then(res => {
        return res.status
    }).then(data => {
        window.location.href = '/TourismManagement/quan-tri-vien/ghi-chu'
    })
}

$(document).ready(function () {
    $('#noteCreatedBtn').click( () =>redirectPageAfterCreate())
})