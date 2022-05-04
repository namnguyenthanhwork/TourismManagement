function getPaymentTypeInfo() {
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
        $("#paytName").val(data['paytName']);
    })
}

$(document).ready(function () {
    $('#loading').hide()
    $('#paymentTypeUpdatedForm').attr('action', window.location.href);
    getPaymentTypeInfo()
    $('#paymentTypeUpdatedBtn').click(function (){
        alert("Xác nhận cập nhật hình thức thanh toán?")
        $(this).hide()
        $('#loading').show()
    })
})