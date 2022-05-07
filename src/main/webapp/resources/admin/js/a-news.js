let currentPageIndex = 1;

function getNewsInfo(pageIndex = null, kw = null) {
    let path = '/TourismManagement/quan-tri-vien/tin-tuc/thong-tin'
    if (kw != null)
        path += `?kw=${kw}`
    if (pageIndex != null)
        path += `${path.includes('?') ? '&' : '?'}trang=${pageIndex}`
    fetch(path)
        .then(res => {
            if (res.status != 200)
                return res.status
            return res.json()
        }).then(data => {
        let rows = ''
        for (let i = 0; i < data.length; i++)
            rows += `
                 <tr>
                    <td class="text-center">
                        <a href="/TourismManagement/quan-tri-vien/tin-tuc/${data[i]['newsSlug']}" 
                        class="badge badge-success text-capitalize">Chỉnh sửa</a>
                        <a href="javascript:;" class="badge badge-danger text-capitalize"
                           onclick="deleteNews('${data[i]['newsSlug']}')">Xoá</a>
                    </td>
                    <td class="align-middle text-center">
                        <span class="text-secondary text-xs font-weight-bold">${data[i]['newsId']}</span>
                    </td>
                    <td class="align-middle text-center">
                        <span class="text-secondary text-xs font-weight-bold">${data[i]['newsTitle']}</span>
                    </td>
                    <td class="align-middle text-center">
                        <span class="text-secondary text-xs font-weight-bold">${data[i]['newsSlug']}</span>
                    </td>
                    <td class="align-middle text-center">
                        <span class="text-secondary text-xs font-weight-bold">${new Date(data[i]['newsCreatedDate']).toISOString().split('T')[0]}</span>
                    </td>
                    <td class="align-middle text-center">
                        <span class="text-secondary text-xs font-weight-bold">${data[i]['newsLikeAmount']}</span>
                    </td>
                     <td class="align-middle text-center">
                        <div> 
                          <img src="${data[i]['newsCoverPage']}" class="avatar avatar-sm me-3" alt="news cover page"> 
                        </div>
                    </td>
                 </tr>
            `
        $('#newsInfo').html(rows)
    })
}

function deleteNews(newsSlug) {
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
            fetch(`/TourismManagement/quan-tri-vien/tin-tuc/${newsSlug}`, {
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
                $('#search').val('')
                getPageAmount()
                getNewsInfo(currentPageIndex)
            })
        }
    })
}


function getPageAmount(kw =null) {
    let path = '/TourismManagement/quan-tri-vien/tin-tuc/so-trang'
    if(kw!==null)
        path+=`?kw=${kw}`
    fetch(path)
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
    $('#search').val('')

    if (currentPageIndex > 1) {
        $(`#pagination li:nth-child(${currentPageIndex + 1})`).removeClass('active')
        currentPageIndex--;
        $(`#pagination li:nth-child(${currentPageIndex + 1})`).addClass('active')
        getNewsInfo(currentPageIndex)
    }
    if (currentPageIndex == 1)
        $('#preBtn').hide()
    if (currentPageIndex != 1)
        $('#preBtn').show()
    if (currentPageIndex != pageAmount)
        $('#nextBtn').show()
}

function changePage(pageIndex, pageAmount) {

    $('#search').val('')
    $(`#pagination li:nth-child(${currentPageIndex + 1})`).removeClass('active')
    currentPageIndex = pageIndex
    $(`#pagination li:nth-child(${currentPageIndex + 1})`).addClass('active')
    getNewsInfo(currentPageIndex)

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
    $('#search').val('')
    if (currentPageIndex < pageAmount) {
        $(`#pagination li:nth-child(${currentPageIndex + 1})`).removeClass('active')
        currentPageIndex++;
        $(`#pagination li:nth-child(${currentPageIndex + 1})`).addClass('active')
        getNewsInfo(currentPageIndex)
    }
    if (currentPageIndex == pageAmount)
        $('#nextBtn').hide()
    if (currentPageIndex != pageAmount)
        $('#nextBtn').show()
    if (currentPageIndex != 1)
        $('#preBtn').show()
}

$(document).ready(function () {
    getNewsInfo(currentPageIndex)
    getPageAmount()
    $('#search').keyup(function () {
        getNewsInfo(currentPageIndex, $(this).val().length > 0 ? $(this).val().trim() : null)
        getPageAmount($(this).val().length > 0 ? $(this).val().trim() : null)
    })
})