function getDepartureDateInfo() {
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
        $('#dptDate').val(new Date(data['dptDate']).toISOString().split('T')[0]);
        $('#feaSlug').val(data['feaSlug'])
    })
}

function getFeatureInfo () {
    fetch("/TourismManagement/quan-tri-vien/dac-diem-ngay-khoi-hanh/thong-tin")
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
        for (let i = 0; i < data.length; i++)
            options += `
                     <option value="${data[i]['feaSlug']}">${data[i]['feaName']}</option>
                `

        document.getElementById('feaSlug').innerHTML = options
        getDepartureDateInfo()
    })
}

$(document).ready(function () {
    $('#loading').hide()
    $('#departureDateUpdatedForm').attr('action', window.location.href);
    getFeatureInfo()
    $('#departureDateUpdatedBtn').click(function () {
        alert("Xác nhận cập nhật ngày khởi hành")
        $(this).hide()
        $('#loading').show()
    })
})