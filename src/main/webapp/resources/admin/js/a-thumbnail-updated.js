function getThumbnailInfo() {
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
        // $("#storName").val(data['storName']);
    })
}

function redirectPageAfterUpdate() {
    fetch(window.location.href).then(res => {
        return res.status
    }).then(data => {
        window.location.href = '/TourismManagement/quan-tri-vien/hinh-thu-nho'
    })
}


$(document).ready(function () {
    $('#thumbnailUpdatedForm').attr('action', window.location.href);
    getThumbnailInfo()
    $('#thumbnailUpdatedBtn').click(() => redirectPageAfterUpdate())
})