function getServingObjectInfo() {
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
        $("#svoName").val(data['svoName']);
    })
}

function redirectPageAfterUpdate() {
    fetch(window.location.href).then(res => {
        return res.status
    }).then(data => {
        window.location.href = '/TourismManagement/quan-tri-vien/doi-tuong-phuc-vu'
    })
}


$(document).ready(function () {
    $('#servingObjectUpdatedForm').attr('action', window.location.href);
    getServingObjectInfo()
    $('#servingObjectUpdatedBtn').click(() => redirectPageAfterUpdate())
})