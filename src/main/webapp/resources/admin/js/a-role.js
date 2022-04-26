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
                rows += `<tr>
                    <td>${data[i]['roleId']}</td>
                    <td>${data[i]['roleName']}</td>
                    <td>${data[i]['roleSlug']}</td>
                    <td>
                    <button><a href="/TourismManagement/quan-tri-vien/vai-tro/${data[i]['roleSlug']}">Chỉnh sửa</a></button>
                    <button onclick="deleteRole('${data[i]['roleSlug']}')">Xoá</button>
                    </td>
                </tr>`
            }

            document.getElementById('roleInfo').innerHTML = rows
        })
        
}


function deleteRole(roleSlug) {
    alert("Bạn có thực sự muốn xóa?")
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

$(document).ready(function (){
    getRoleInfo()
})
