function getSalePercentInfo() {
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
        $("#sperPercent").val(data['sperPercent']);
    })
}

function redirectPageAfterUpdate() {
    fetch(window.location.href).then(res => {
        return res.status
    }).then(data => {
        window.location.href = '/TourismManagement/quan-tri-vien/phan-tram-giam-gia'
    })
}


$(document).ready(function () {
    $('#salePercentUpdatedForm').attr('action', window.location.href);
    getSalePercentInfo()
    $('#salePercentUpdatedBtn').click(() => redirectPageAfterUpdate())
})