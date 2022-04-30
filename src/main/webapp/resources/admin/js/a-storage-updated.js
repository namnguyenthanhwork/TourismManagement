function getRoleInfo() {
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
        $("#storName").val(data['storName']);
    })
}

function redirectPageAfterUpdate() {
    fetch(window.location.href).then(res => {
        return res.status
    }).then(data => {
        window.location.href = '/TourismManagement/quan-tri-vien/kho-chua'
    })
}


$(document).ready(function () {
    $('#storageUpdatedForm').attr('action', window.location.href);
    getRoleInfo()
    $('#storageUpdatedBtn').click(() => redirectPageAfterUpdate())
})