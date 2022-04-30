function redirectPageAfterCreate(){
    fetch(window.location.href).then(res => {
        return res.status
    }).then(data => {
        window.location.href = '/TourismManagement/quan-tri-vien/lich-trinh'
    })
}

$(document).ready(function () {
    $('#scheduleCreatedBtn').click( () =>redirectPageAfterCreate())
})