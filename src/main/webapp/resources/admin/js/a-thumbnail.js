function getThumbnailInfo() {
    fetch("/TourismManagement/quan-tri-vien/hinh-thu-nho/thong-tin")
        .then(res => {
            if (res.status != 200)
                return res.status
            return res.json()
        }).then(data => {
            if (data == 204) {
                alert("thông tin trống")
                return
            }
            let rows = ''
            for (let i = 0; i < data.length; i++) {
                rows += `
                <tr>
                    <td class="align-middle text-center">
                    <span class="text-secondary text-xs font-weight-bold">${data[i]['thumId']}</span>
                </td>
                <td class="align-middle text-center">
                    <div> 
                    <img src="${data[i]['thumImage']}" class="avatar avatar-sm me-3" alt="user1"> 
                    </div>
                </td>
                <td class="text-center">
                    <a href="/TourismManagement/quan-tri-vien/hinh-thu-nho/${data[i]['thumId']}" 
                    class="badge badge-success text-capitalize">Chỉnh sửa</a>
                    <a href="javascript:;" class="badge badge-danger text-capitalize"
                       onclick="deleteThumbnail('${data[i]['thumId']}')">Xoá</a>
                </td>
                </tr>     
                `
            }
            document.getElementById('thumbnailInfo').innerHTML = rows
        })

}

function deleteThumbnail(thumId) {
    Swal.fire({
        title: 'Bạn có thực sự muốn xoá ?',
        text: "Dữ liệu sẽ bị mất nếu bạn xoá nó!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Ok'
    }).then((result) => {
        if (result.isConfirmed) {
            fetch(`/TourismManagement/quan-tri-vien/hinh-thu-nho/${thumId}`, {
                method: 'delete'
            }).then(res => {
                return res.status
            }).then(data => {
                if (data == 409) {
                    Swal.fire(
                        'Xoá thất bại!',
                        'Vui lòng kiểm tra lại.',
                        'warning'
                    )
                    return
                }
                Swal.fire(
                    'Đã xoá',
                    'Dữ liệu đã được xoá thành công.',
                    'success'
                )
                getThumbnailInfo()
            })
        }
    })
}

$(document).ready(function () {
    getThumbnailInfo()
})