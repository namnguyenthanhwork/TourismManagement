function redirectPageAfterCreate(){
    fetch(window.location.href).then(res => {
        return res.status
    }).then(data => {
        window.location.href = '/TourismManagement/quan-tri-vien/vai-tro'
    })
}

$(document).ready(function () {
    $('#roleCreatedBtn').click( () =>redirectPageAfterCreate())
})