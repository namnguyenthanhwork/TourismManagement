function getAccountInfo() {
    fetch("/TourismManagement/quan-tri-vien/tai-khoan/thong-tin")
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
                let accSex = data[i]['accSex'];
                accSex == 1 ? accSex = 'Nam' : accSex = 'Nữ';
                rows += `
                <tr>
                <td class="text-center">
                    <a href="/TourismManagement/quan-tri-vien/tai-khoan/${data[i]['accUsername']}" 
                    class="badge badge-success text-capitalize">Chỉnh sửa</a>
                    <a href="javascript:;" class="badge badge-danger text-capitalize"
                       onclick="deleteAccount('${data[i]['accUsername']}')">Xoá</a>
                </td>
                <td class="align-middle text-center">
                    <span class="text-secondary text-xs font-weight-bold">${data[i]['accId']}</span>
                </td>
                <td>
                    <p class="text-xs font-weight-bold mb-0">${data[i]['accUsername']}</p>
                </td>
                <td class="align-middle text-center">
                    <span class="text-secondary text-xs font-weight-bold">${data[i]['accPassword']}</span>
                </td>
                <td class="align-middle text-center">
                    <span class="text-secondary text-xs font-weight-bold">${data[i]['accFirstName']}</span>
                </td>
                <td class="align-middle text-center">
                    <span class="text-secondary text-xs font-weight-bold">${data[i]['accLastName']}</span>
                </td>
                <td class="align-middle text-center">
                    <span class="text-secondary text-xs font-weight-bold">${accSex}</span>
                </td>
                <td class="align-middle text-center">
                    <span class="text-secondary text-xs font-weight-bold">${data[i]['accIdCard']}</span>
                </td>
                <td class="align-middle text-center">
                    <span class="text-secondary text-xs font-weight-bold">${data[i]['accPhoneNumber']}</span>
                </td>
                <td class="align-middle text-center">
                    <span class="text-secondary text-xs font-weight-bold">${new Date(data[i]['accDateOfBirth']).toISOString().split('T')[0]}</span>
                </td>
                <td class="align-middle text-center">
                    <span class="text-secondary text-xs font-weight-bold">${new Date(data[i]['accJoinedDate']).toISOString().split('T')[0]}</span>
                </td>
                <td class="align-middle text-center">
                <div> 
                  <img src="${data[i]['accAvatar']}" class="avatar avatar-sm me-3" alt="user1"> 
                </div>
                </td>
                <td class="align-middle text-center">
                    <span class="text-secondary text-xs font-weight-bold">${new Date(data[i]['accLastAccess']).toLocaleString()}</span>
                </td>
                <td class="align-middle text-center">
                    <span class="text-secondary text-xs font-weight-bold">${data[i]['roleName']}</span>
                </td>
            </tr>  
                `
            }
            document.getElementById('accountInfo').innerHTML = rows
        })
}

function deleteAccount(accUsername) {
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
            fetch(`/TourismManagement/quan-tri-vien/tai-khoan/${accUsername}`, {
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
                getAccountInfo()
            })
        }
    })
}

$(document).ready(function () {
    getAccountInfo()
})