function getScheduleInfo() {
    let path = window.location.href + '/chinh-sua';
    fetch(path).then(res => {
        if (res.status !== 200)
            return res.status
        return res.json()
    }).then(data => {
        if (data === 204) {
            alert("thông tin trống")
            return
        }
        $("#scheTitle").val(data['scheTitle']);
        $("#scheContent").val(data['scheContent']);
    })
}

function redirectPageAfterUpdate() {
    fetch(window.location.href).then(res => {
        return res.status
    }).then(data => {
        window.location.href = '/TourismManagement/quan-tri-vien/lich-trinh'
    })
}


$(document).ready(function () {
    $('#scheduleUpdatedForm').attr('action', window.location.href);
    getScheduleInfo()
    $('#scheduleUpdatedBtn').click(() => redirectPageAfterUpdate())
})