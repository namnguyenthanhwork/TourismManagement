// var prevLocation = window.location.href
// fetch(window.location.href+='/chinh-sua')
//     .then(res => {
//         if (res.status != 200)
//             return res.status
//         return res.json()
//     }).then(data => {
//     if (data == 204) {
//         alert("thông tin trống")
//         return
//     }
//     // window.location.href = prevLocation
//     document.getElementById('roleName').value = data['roleName'];
// })
$(document).ready(function () {
    var path = window.location.href + '/chinh-sua';
    $('#roleUpdateForm').attr('action', '/TourismManagement/quan-tri-vien/vai-tro/71');

    fetch(path).then(res => {
        if (res.status != 200)
            return res.status
        return res.json()
    }).then(data => {
        if (data == 204) {
            alert("thông tin trống")
            return
        }
        // window.location.href = prevLocation
        document.getElementById('roleName').value = data['roleName'];
    })

    $('#btnRoleUpdate').click(function () {
        fetch(window.location.href).then(res => {
            return res.status
        }).then(data => {
            console.info(data)
            if (data == 409) {
                window.location.href = window.location.href
                alert("chỉnh sửa k thành công")
                return
            }
            window.location.href = '/TourismManagement/quan-tri-vien/vai-tro'
        })
    })
})