function getTransportInfo() {
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
        $("#tranName").val(data['tranName']);
    })
}

function redirectPageAfterUpdate() {
    fetch(window.location.href).then(res => {
        return res.status
    }).then(data => {
        window.location.href = '/TourismManagement/quan-tri-vien/phuong-tien-di-chuyen'
    })
}


$(document).ready(function () {
    $('#transportUpdatedForm').attr('action', window.location.href);
    getTransportInfo()
    $('#transportUpdatedBtn').click(() => redirectPageAfterUpdate())
})