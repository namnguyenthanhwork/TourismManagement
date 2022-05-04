
var currentPageIndex = 1;
function getBillInfo(pageIndex=null) {
    let path = "/TourismManagement/quan-tri-vien/hoa-don/thong-tin"
    if (pageIndex != null)
        path += `?trang=${pageIndex}`
    fetch(path)
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
                let billStatus = data[i]['billIsPaid'];
                billStatus? billStatus = 'Đã thanh toán' : billStatus = 'Chưa thanh toán';
                rows += `
                <tr>
                  <td class="text-center">
                    <a href="/TourismManagement/quan-tri-vien/hoa-don/${data[i]['billId']}" 
                    class="badge badge-success text-capitalize">Xem chi tiết</a>
                </td>
                <td class="align-middle text-center">
                    <span class="text-secondary text-xs font-weight-bold">${data[i]['billId']}</span>
                </td>
                <td class="align-middle text-center">
                    <span class="text-secondary text-xs font-weight-bold">${new Date(data[i]['billCreatedDate']).toLocaleDateString()}</span>
                </td>
          
                <td class="align-middle text-center">
                    <span class="text-secondary text-xs font-weight-bold">${data[i]['accUsername']}</span>
                </td>
                <td class="align-middle text-center">
                    <span class="text-secondary text-xs font-weight-bold">${data[i]['paymentType']}</span>
                </td>
                 <td class="align-middle text-center">
                    <span class="text-secondary text-xs font-weight-bold">${data[i]['billTotalSaleMoney']}</span>
                </td>
                <td class="align-middle text-center">
                    <span class="text-secondary text-xs font-weight-bold">${data[i]['billTotalMoney']}</span>
                </td>
                <td class="align-middle text-center">
                    <span class="text-secondary text-xs font-weight-bold">${new Date(data[i]['billDepartureDate']).toLocaleDateString()}</span>
                </td>
                <td>
                    <p class="text-xs font-weight-bold mb-0">${billStatus}</p>
                </td>
            </tr>  
                `
            }
            document.getElementById('billInfo').innerHTML = rows
        })

}

function getPageAmount() {
    fetch('/TourismManagement/quan-tri-vien/hoa-don/so-trang')
        .then(res => res.json()).then(data => {
        let pageAmount = data['pageAmount']
        if(pageAmount==1)
            return
        let rows = ''
        for (let i = 1; i <= pageAmount; i++)
            rows += `
                 <li class="page-item" onclick="changePage(${i}, ${pageAmount})"><a class="page-link" href="javascript:;">${i}</a></li>
            `
        if (pageAmount > 1) {
            let preBtn = ` <li class="page-item" onclick="getPreviousPage(${pageAmount})" id="preBtn">
                                    <a class="page-link" href="javascript:;">Trước</a></li>`
            let nextBtn = ` <li class="page-item" onclick="getNextPage(${pageAmount})" id="nextBtn">
                                <a class="page-link" href="javascript:;">Sau</a></li>`
            rows = preBtn + rows
            rows += nextBtn
        }
        $('#pagination').html(rows)
        $(`#pagination li:nth-child(${pageAmount > 1 ? 2 : 1})`).addClass('active')
        if (currentPageIndex == 1)
            $('#preBtn').hide()
    })
}
function getPreviousPage(pageAmount) {
    if (currentPageIndex > 1) {
        $(`#pagination li:nth-child(${currentPageIndex + 1})`).removeClass('active')
        currentPageIndex--;
        $(`#pagination li:nth-child(${currentPageIndex + 1})`).addClass('active')
        getBillInfo(currentPageIndex)
    }
    if (currentPageIndex == 1)
        $('#preBtn').hide()
    if (currentPageIndex != 1)
        $('#preBtn').show()
    if (currentPageIndex != pageAmount)
        $('#nextBtn').show()
}

function changePage(pageIndex, pageAmount) {
    $(`#pagination li:nth-child(${currentPageIndex + 1})`).removeClass('active')
    currentPageIndex = pageIndex
    $(`#pagination li:nth-child(${currentPageIndex + 1})`).addClass('active')
    getBillInfo(currentPageIndex)

    if (pageIndex == 1) {
        $('#preBtn').hide()
        $('#nextBtn').show()
    }
    if (pageIndex == pageAmount) {
        $('#nextBtn').hide()
        $('#preBtn').show()
    }

    if (pageIndex != 1 && pageIndex != pageAmount) {
        $('#preBtn').show()
        $('#nextBtn').show()
    }
}

function getNextPage(pageAmount) {
    if (currentPageIndex < pageAmount) {
        $(`#pagination li:nth-child(${currentPageIndex + 1})`).removeClass('active')
        currentPageIndex++;
        $(`#pagination li:nth-child(${currentPageIndex + 1})`).addClass('active')
        getBillInfo(currentPageIndex)
    }
    if (currentPageIndex == pageAmount)
        $('#nextBtn').hide()
    if (currentPageIndex != pageAmount)
        $('#nextBtn').show()
    if (currentPageIndex != 1)
        $('#preBtn').show()
}

$(document).ready(function () {
    getBillInfo(currentPageIndex)
    getPageAmount()
})