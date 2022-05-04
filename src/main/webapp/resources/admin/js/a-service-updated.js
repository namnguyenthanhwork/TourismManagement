function getServiceInfo(editor) {
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
        $("#servTitle").val(data['servTitle']);
        editor.setData(data['servContent']);
    })
}

$(document).ready(function () {
    $('#loading').hide()
    $('#serviceUpdatedForm').attr('action', window.location.href);
    $('#serviceUpdatedBtn').click(function () {
        alert("Xác nhận cập nhật lịch trình")
        $(this).hide()
        $('#loading').show()
    })
})