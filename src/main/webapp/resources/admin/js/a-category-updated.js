function getCategoryInfo() {
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
            alert('thông tin trống')
            return
        }
        $('#catName').val(data['catName']);
        $('#storSlug').val(data['storSlug'])
    })
}

function  getStorageInfo(){
    fetch("/TourismManagement/quan-tri-vien/kho-chua/thong-tin")
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
                <option value="${data[i]['storSlug']}">${data[i]['storName']}</option>
                `
        }
        document.getElementById('storSlug').innerHTML = options
        getCategoryInfo()
    })
}

$(document).ready(function () {
    $('#loading').hide()
    $('#categoryUpdatedForm').attr('action', window.location.href);
    getStorageInfo()
    $('#categoryUpdatedBtn').click(function (){
        alert("Xác nhận cập nhật loại tour?")
        $(this).hide()
        $('#loading').show()
    })
})