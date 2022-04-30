function getDepartureDateInfo() {
    fetch("/TourismManagement/quan-tri-vien/ngay-khoi-hanh/thong-tin")
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
                    <span class="text-secondary text-xs font-weight-bold">${data[i]['dptId']}</span>
                </td>
                <td>
                    <span class="text-secondary text-xs font-weight-bold">${new Date(data[i]['dptDate']).toLocaleDateString()}</span>
                </td>
                <td class="align-middle text-center">
                    <p class="text-xs font-weight-bold mb-0">${data[i]['feaName']}</p>
                </td>
                <td class="text-center">
                    <a href="/TourismManagement/quan-tri-vien/ngay-khoi-hanh/${data[i]['dptId']}" 
                    class="badge badge-success text-capitalize">Chỉnh sửa</a>
                    <a href="javascript:;" class="badge badge-danger text-capitalize"
                       onclick="deleteDepartureDate('${data[i]['dptId']}')">Xoá</a>
                </td>
            </tr>  
                `
            }
            document.getElementById('departureDateInfo').innerHTML = rows
        })
}

function deleteDepartureDate(dptId) {
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
            fetch(`/TourismManagement/quan-tri-vien/ngay-khoi-hanh/${dptId}`, {
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
                getDepartureDateInfo()
            })
        }
    })
}

$(document).ready(function () {
    getDepartureDateInfo()
})