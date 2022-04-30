function getFeatureInfo() {
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
        $("#feaName").val(data['feaName']);
    })
}

function redirectPageAfterUpdate() {
    fetch(window.location.href).then(res => {
        return res.status
    }).then(data => {
        window.location.href = '/TourismManagement/quan-tri-vien/dac-diem-ngay-khoi-hanh'
    })
}


$(document).ready(function () {
    $('#featureUpdatedForm').attr('action', window.location.href);
    getFeatureInfo()
    $('#featureUpdatedBtn').click(() => redirectPageAfterUpdate())
})