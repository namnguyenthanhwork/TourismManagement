function getSaleInfo() {
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
        $("#saleFromDate").val(new Date(data['saleFromDate']).toISOString().split('T')[0]);
        $("#saleToDate").val(new Date(data['saleToDate']).toISOString().split('T')[0]);
        $('#sperId').val(data['sperId'])
    })
}

function getSalePercentInfo(){
    fetch("/TourismManagement/quan-tri-vien/phan-tram-giam-gia/thong-tin")
        .then(res => {
            if (res.status != 200)
                return res.status
            return res.json()
        }).then(data => {
        if (data == 204) {
            alert("thông tin trống")
            return
        }
        let options = '';
        for (let i = 0; i < data.length; i++) {
            options += `
                <option value="${data[i]['sperId']}">${data[i]['sperPercent']}</option>
                `
        }
        document.getElementById('sperId').innerHTML = options
        getSaleInfo()
    })
}

$(document).ready(function () {
    $('#loading').hide()
    $('#saleUpdatedForm').attr('action', window.location.href);
    getSalePercentInfo()

    $('#saleUpdatedBtn').click(function () {
        alert("Xác nhận cập nhât giảm giá")
        $(this).hide()
        $('#loading').show()
    })
})