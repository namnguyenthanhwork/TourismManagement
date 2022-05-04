function getServingObjectInfo() {
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
        $("#svoName").val(data['svoName']);
    })
}

$(document).ready(function () {
    $('#loading').hide()
    $('#servingObjectUpdatedForm').attr('action', window.location.href);
    getServingObjectInfo()
    $('#servingObjectUpdatedBtn').click(function () {
        alert("Xác nhận cập nhật đối tượng phục vụ")
        $(this).hide()
        $('#loading').show()
    })
})