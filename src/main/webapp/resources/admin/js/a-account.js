function getAccountInfo() {
    fetch("/TourismManagement/quan-tri-vien/tai-khoan")
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