function getRoleInfo() {
    fetch("/TourismManagement/quan-tri-vien/vai-tro/thong-tin")
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
                    <td>
                        <div class="d-flex px-2 py-1">
                            <div class="d-flex flex-column justify-content-center">
                                <h6 class="mb-0 text-sm">${data[i]['roleId']}</h6>
                            </div>
                        </div>
                    </td>
                    <td>
                        <p class="text-xs font-weight-bold mb-0">${data[i]['roleName']}</p>
                    </td>
                    <td class="align-middle text-center">
                        <span class="text-secondary text-xs font-weight-bold">${data[i]['roleSlug']}</span>
                    </td>
                    <td class="text-center">
                        <a href="/TourismManagement/quan-tri-vien/vai-tro/${data[i]['roleSlug']}" 
                        class="badge badge-success text-capitalize">Chỉnh sửa</a>
                        <a href="javascript:;" class="badge badge-danger text-capitalize"
                           onclick="deleteRole('${data[i]['roleSlug']}')">Xoá</a>
                    </td>
                </tr>     
                `
            }
            document.getElementById('roleInfo').innerHTML = rows
        })

}


function deleteRole(roleSlug) {
    fetch(`/TourismManagement/quan-tri-vien/vai-tro/${roleSlug}`, {
        method: 'delete'
    }).then(res => {
        return res.status
    }).then(data => {
        if (data == 409) {
            alert("xoá thất bại")
            return
        }
        alert("xoá thành công")
        getRoleInfo()
    })
}
getRoleInfo()



// fetch("http://localhost:8080/TourismManagement/quan-tri-vien/tour-du-lich/thong-tin?page=1").then(res=>res.json()).then(data=>console.info(data))