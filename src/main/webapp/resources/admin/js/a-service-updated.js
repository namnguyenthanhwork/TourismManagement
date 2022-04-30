function getServiceInfo() {
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
        $("#servTitle").val(data['servTitle']);
        $("#servContent").val(data['servContent']);
    })
}

function redirectPageAfterUpdate() {
    fetch(window.location.href).then(res => {
        return res.status
    }).then(data => {
        window.location.href = '/TourismManagement/quan-tri-vien/dich-vu'
    })
}


$(document).ready(function () {
    $('#serviceUpdatedForm').attr('action', window.location.href);
    getServiceInfo()
    $('#serviceUpdatedBtn').click(() => redirectPageAfterUpdate())
})