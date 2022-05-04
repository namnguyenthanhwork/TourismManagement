function getStorageInfo() {
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
        $("#storName").val(data['storName']);
    })
}

$(document).ready(function () {
    $('#loading').hide()
    $('#storageUpdatedForm').attr('action', window.location.href);
    getStorageInfo()
    $('#storageUpdatedBtn').click(function () {
        alert('Xác nhận cập nhật kho chứa mới')
        $(this).hide()
        $('#loading').show()
    })
})