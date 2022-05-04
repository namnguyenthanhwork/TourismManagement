function getNewsInfo(editor) {
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
        $("#newsTitle").val(data['newsTitle']);
        editor.setData(data['newsContent']);
    })
}

$(document).ready(function () {
    $('#loading').hide()
    $('#newsUpdatedForm').attr('action', window.location.href);
    $('#newsUpdatedBtn').click(function (){
        alert("Xác nhận cập nhật tin tức du lịch")
        $(this).hide()
        $('#loading').show()
    })
})