function getFeatureInfo() {
    let href = window.location.href
    if (href.includes('?'))
        href = href.substring(0, href.indexOf('?'))
    let path = href + '/chinh-sua'
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

$(document).ready(function () {
    $('#loading').hide()
    $('#featureUpdatedForm').attr('action', window.location.href);
    getFeatureInfo()
    $('#featureUpdatedBtn').click(function () {
        alert("Xác nhận cập nhật đặc điểm khởi hành mới mới")
        $(this).hide()
        $('#loading').show()
    })
})