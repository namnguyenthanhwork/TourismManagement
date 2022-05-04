function getSalePercentInfo() {
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
        $("#sperPercent").val(data['sperPercent']);
    })
}

$(document).ready(function () {
    $('#loading').hide()
    $('#salePercentUpdatedForm').attr('action', window.location.href);
    getSalePercentInfo()
    $('#salePercentUpdatedBtn').click(function () {
        alert("Xác nhận cập nhật phần trăm giảm giá mới")
        $(this).hide()
        $('#loading').show()
    })
})