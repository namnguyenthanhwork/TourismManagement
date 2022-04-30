function getNoteInfo() {
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
        $("#noteTitle").val(data['noteTitle']);
        $("#noteContent").val(data['noteContent']);
    })
}

function redirectPageAfterUpdate() {
    fetch(window.location.href).then(res => {
        return res.status
    }).then(data => {
        window.location.href = '/TourismManagement/quan-tri-vien/ghi-chu'
    })
}


$(document).ready(function () {
    $('#noteUpdatedForm').attr('action', window.location.href);
    getNoteInfo()
    $('#noteUpdatedBtn').click(() => redirectPageAfterUpdate())
})