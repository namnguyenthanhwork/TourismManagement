function getNoteInfo(editor) {
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
        $("#noteTitle").val(data['noteTitle'])
        editor.setData(data['noteContent'])
    })
}

$(document).ready(function () {
    $('#loading').hide()
    $('#noteUpdatedForm').attr('action', window.location.href)
    $('#noteUpdatedBtn').click(function (){
        alert("Xác nhận cập nhật ghi chú")
        $(this).hide()
        $('#loading').show()
    })
})