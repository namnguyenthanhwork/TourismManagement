function getScheduleInfo() {
    fetch("/TourismManagement/quan-tri-vien/lich-trinh/thong-tin")
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
                        <span class="text-secondary text-xs font-weight-bold">${data[i]['scheId']}</span>
                    </td>
                    <td>
                        <p class="text-xs font-weight-bold mb-0">${data[i]['scheTitle']}</p>
                    </td>
                    <td class="align-middle text-center">
                        <span class="text-secondary text-xs font-weight-bold">${data[i]['scheContent']}</span>
                    </td>
                    <td class="text-center">
                        <a href="/TourismManagement/quan-tri-vien/lich-trinh/${data[i]['scheSlug']}" 
                        class="badge badge-success text-capitalize">Chỉnh sửa</a>
                        <a href="javascript:;" class="badge badge-danger text-capitalize"
                           onclick="deleteSchedule('${data[i]['scheSlug']}')">Xoá</a>
                    </td>
                </tr>     
                `
            }
            document.getElementById('scheduleInfo').innerHTML = rows
        })

}

function deleteSchedule(scheSlug) {
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
            fetch(`/TourismManagement/quan-tri-vien/lich-trinh/${scheSlug}`, {
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
                getScheduleInfo()
            })
        }
    })
}

$(document).ready(function () {
    getScheduleInfo()
})