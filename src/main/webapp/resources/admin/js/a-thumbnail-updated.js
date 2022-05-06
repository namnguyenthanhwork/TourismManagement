function getThumbnailInfo() {
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
            Swal.fire({
                title: 'Thông báo !',
                text: "Thông tin trống!",
                icon: 'warning',
                confirmButtonColor: '#3085d6',
                confirmButtonText: 'Ok'
            })
            return
        }
        $('#tourSlug').val(data['tourSlug'])
    })
}

function getTourInfo() {
    fetch('/TourismManagement/quan-tri-vien/tour-du-lich/thong-tin/tong-quan')
        .then(res => {
            if (res.status != 200)
                return res.status
            return res.json()
        }).then(data => {
            if (data == 204) {
                Swal.fire({
                    title: 'Thông báo !',
                    text: "Thông tin trống!",
                    icon: 'warning',
                    confirmButtonColor: '#3085d6',
                    confirmButtonText: 'Ok'
                })
                return
            }
            let options = '';
            for (let i = 0; i < data.length; i++)
                options += `
                     <option value="${data[i]['tourSlug']}">${data[i]['tourTitle']}</option>
                `
            document.getElementById('tourSlug').innerHTML = options
            getThumbnailInfo()

        })
}
$(document).ready(function () {
    $('#loading').hide()
    $('#thumbnailUpdatedForm').attr('action', window.location.href);
    getTourInfo()
    $('#thumbnailUpdatedBtn').click(function () {
        Swal.fire({
            title: 'Thông báo !',
            text: "Cập nhật thành công",
            icon: 'success',
            confirmButtonColor: '#3085d6'
        })
        $(this).hide()
        $('#loading').show()
    }) 
})