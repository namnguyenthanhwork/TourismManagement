function getPaymentTypeInfo() {
    let path = window.location.href + '/chinh-sua';
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

function redirectPageAfterUpdate() {
    fetch(window.location.href).then(res => {
        return res.status
    }).then(data => {
        window.location.href = '/TourismManagement/quan-tri-vien/hinh-thuc-thanh-toan'
    })
}


$(document).ready(function () {
    $('#paymentTypeUpdatedForm').attr('action', window.location.href);
    getPaymentTypeInfo()
    $('#paymentTypeUpdatedBtn').click(() => redirectPageAfterUpdate())
})