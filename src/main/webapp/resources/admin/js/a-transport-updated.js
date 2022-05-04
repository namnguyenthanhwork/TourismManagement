function getTransportInfo() {
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
        $("#tranName").val(data['tranName']);
    })
}

$(document).ready(function () {
    $('#loading').hide()
    $('#transportUpdatedForm').attr('action', window.location.href);
    getTransportInfo()
    $('#transportUpdatedBtn').click(function () {
        alert("Xác nhận cập nhật phương tiện di chuyển")
        $(this).hide()
        $('#loading').show()
    })
})