function getSaleInfo() {
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
        $("#saleFromDate").val(new Date(data['saleFromDate']).toISOString().split('T')[0]);
        $("#saleToDate").val(new Date(data['saleToDate']).toISOString().split('T')[0]);
    })
}

function redirectPageAfterUpdate() {
    fetch(window.location.href).then(res => {
        return res.status
    }).then(data => {
        window.location.href = '/TourismManagement/quan-tri-vien/giam-gia'
    })
}


$(document).ready(function () {
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
                <option value="${data[i]['sperPercent']}">${data[i]['sperPercent']}</option>
                `
            }
            document.getElementById('sperPercent').innerHTML = options
        })
    $('#saleUpdatedForm').attr('action', window.location.href);
    getSaleInfo()
    $('#saleUpdatedBtn').click(() => redirectPageAfterUpdate())
})