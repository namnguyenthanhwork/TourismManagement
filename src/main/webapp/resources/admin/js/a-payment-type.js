var currentPageIndex = 1;

function getPaymentTypeInfo(pageIndex = null) {
    let path = "/TourismManagement/quan-tri-vien/hinh-thuc-thanh-toan/thong-tin"
    if (pageIndex != null)
        path += `?trang=${pageIndex}`
    fetch(path)
        .then(res => {
            if (res.status != 200)
                return res.status
            return res.json()
        }).then(data => {
        let rows = ''
        for (let i = 0; i < data.length; i++) {
            rows += `
                <tr>
                     <td class="text-center">
                        <a href="/TourismManagement/quan-tri-vien/hinh-thuc-thanh-toan/${data[i]['paytSlug']}" 
                        class="badge badge-success text-capitalize">Chỉnh sửa</a>
                        <a href="javascript:;" class="badge badge-danger text-capitalize"
                           onclick="deletePaymentType('${data[i]['paytSlug']}')">Xoá</a>
                    </td>
                    <td class="align-middle text-center">
                        <span class="text-secondary text-xs font-weight-bold">${data[i]['paytId']}</span>
                    </td>
                    <td>
                        <p class="text-xs font-weight-bold mb-0">${data[i]['paytName']}</p>
                    </td>
                    <td class="align-middle text-center">
                        <span class="text-secondary text-xs font-weight-bold">${data[i]['paytSlug']}</span>
                    </td>

                </tr>     
                `
        }
        document.getElementById('paymentTypeInfo').innerHTML = rows
    })

}


function deletePaymentType(paytSlug) {
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
            fetch(`/TourismManagement/quan-tri-vien/hinh-thuc-thanh-toan/${paytSlug}`, {
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
                currentPageIndex=1
                getPageAmount()
                getPaymentTypeInfo(currentPageIndex)
            })
        }
    })
}

function getPageAmount() {
    fetch('/TourismManagement/quan-tri-vien/hinh-thuc-thanh-toan/so-trang')
        .then(res => res.json()).then(data => {
        let pageAmount = data['pageAmount']
        let rows = ''
        for (let i = 1; i <= pageAmount; i++)
            rows += `
                 <li class="page-item" onclick="changePage(${i}, ${pageAmount})"><a class="page-link" href="javascript:;">${i}</a></li>
            `
        if (pageAmount > 1) {
            let preBtn = ` <li class="page-item" onclick="getPreviousPage(${pageAmount})" id="preBtn">
                                    <a class="page-link" href="javascript:;"><</a></li>`
            let nextBtn = ` <li class="page-item" onclick="getNextPage(${pageAmount})" id="nextBtn">
                                        <a class="page-link" href="javascript:;">></a></li>`
            rows = preBtn + rows
            rows += nextBtn
        }
        $('#pagination').html(pageAmount !== 1 ? rows : '')
        if (pageAmount !== 1)
            $(`#pagination li:nth-child(2)`).addClass('active')
        if (currentPageIndex === 1)
            $('#preBtn').hide()
    })
}

function getPreviousPage(pageAmount) {
    if (currentPageIndex > 1) {
        $(`#pagination li:nth-child(${currentPageIndex + 1})`).removeClass('active')
        currentPageIndex--;
        $(`#pagination li:nth-child(${currentPageIndex + 1})`).addClass('active')
        getPaymentTypeInfo(currentPageIndex)
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
    getPaymentTypeInfo(currentPageIndex)

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
        getPaymentTypeInfo(currentPageIndex)
    }
    if (currentPageIndex == pageAmount)
        $('#nextBtn').hide()
    if (currentPageIndex != pageAmount)
        $('#nextBtn').show()
    if (currentPageIndex != 1)
        $('#preBtn').show()
}


$(document).ready(function () {
    getPaymentTypeInfo(currentPageIndex)
    getPageAmount()
})