function getScheduleInfo(editor) {
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
        $('#scheTitle').val(data['scheTitle']);
        $('#tourSlug').val(data['tourSlug']);
        editor.setData(data['scheContent']);
    })
}

function getTourInfor(editor){
    fetch('/TourismManagement/quan-tri-vien/tour-du-lich/thong-tin/tong-quan')
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
                     <option value="${data[i]['tourSlug']}">${data[i]['tourTitle']}</option>
                `
        document.getElementById('tourSlug').innerHTML = options
        getScheduleInfo(editor)
    })
}


$(document).ready(function () {
    $('#loading').hide()
    $('#scheduleUpdatedForm').attr('action', window.location.href);
    $('#scheduleUpdatedBtn').click(function () {
        alert("Xác nhận cập nhật lịch trình mới")
        $(this).hide()
        $('#loading').show()
    })
})