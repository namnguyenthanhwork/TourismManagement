function getBillInfo() {
    fetch("/TourismManagement/quan-tri-vien/hoa-don/thong-tin")
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
                <td class="align-middle text-center">
                    <span class="text-secondary text-xs font-weight-bold">${data[i]['accId']}</span>
                </td>
                <td>
                    <p class="text-xs font-weight-bold mb-0">${data[i]['accUsername']}</p>
                </td>
                <td class="align-middle text-center">
                    <span class="text-secondary text-xs font-weight-bold">${data[i]['accFirstName']}</span>
                </td>
                <td class="align-middle text-center">
                    <span class="text-secondary text-xs font-weight-bold">${data[i]['accLastName']}</span>
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
                    <span class="text-secondary text-xs font-weight-bold">${data[i]['paytId']}</span>
                </td>
                <td class="align-middle text-center">
                    <span class="text-secondary text-xs font-weight-bold">${data[i]['paytName']}</span>
                </td>
                    <td class="align-middle text-center">
                    <span class="text-secondary text-xs font-weight-bold">${data[i]['postTitle']}</span>
                </td>
                 <td class="align-middle text-center">
                    <span class="text-secondary text-xs font-weight-bold">${data[i]['postId']}</span>
                </td>
                 <td class="align-middle text-center">
                    <span class="text-secondary text-xs font-weight-bold">${data[i]['tourAverageRating']}</span>
                </td>
                 <td class="align-middle text-center">
                    <span class="text-secondary text-xs font-weight-bold">${data[i]['billId']}</span>
                </td>
                 <td class="align-middle text-center">
                    <span class="text-secondary text-xs font-weight-bold">${new Date(data[i]['billCreatedDate']).toISOString().split('T')[0]}</span>
                </td>
                 <td class="align-middle text-center">
                    <span class="text-secondary text-xs font-weight-bold">${data[i]['billTotalMoney']}</span>
                </td>
                 <td class="align-middle text-center">
                    <span class="text-secondary text-xs font-weight-bold">${data[i]['billTotalSaleMoney']}</span>
                </td>
                 <td class="align-middle text-center">
                    <span class="text-secondary text-xs font-weight-bold">${new Date(data[i]['billShipDate']).toISOString().split('T')[0]}</span>
                </td>
                 <td class="align-middle text-center">
                    <span class="text-secondary text-xs font-weight-bold">${data[i]['billShipAddress']}</span>
                </td>
                 <td class="align-middle text-center">
                    <span class="text-secondary text-xs font-weight-bold">${data[i]['billShipDistrict']}</span>
                </td>
                 <td class="align-middle text-center">
                    <span class="text-secondary text-xs font-weight-bold">${data[i]['billShipCity']}</span>
                </td>
            </tr>  
                `
            }
            document.getElementById('billInfo').innerHTML = rows
        })

}

$(document).ready(function () {
    getBillInfo()
})