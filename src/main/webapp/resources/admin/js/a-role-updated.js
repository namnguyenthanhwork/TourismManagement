function getRoleInfo(){
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
        $("#roleName").val(data['roleName']);
    })
}
$(document).ready(function () {
    $('#loading').hide()
    $('#roleUpdateForm').attr('action', window.location.href);
    getRoleInfo()
    $('#roleUpdatedBtn').click(function (){
        alert("Xác nhận cập nhật vai trò?")
        $(this).hide()
        $('#loading').show()
    })
})