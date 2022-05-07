let currentPageIndex = 1;

function getNewsInfo(pageIndex = null) {
    let path = '/TourismManagement/tin-tuc/thong-tin'
    if (pageIndex != null)
        path += `?trang=${pageIndex}`
    fetch(path).then(res => {
        if (res.status !== 200)
            return res.status
        return res.json()
    }).then(data => {
        console.info(data)
        let rows = ''
        for (let i = 0; i < data.length; i++)
            rows += `
               <div class="col-lg-4 col-md-4 col-sm-6 col-xs-6">
                    <div class="news">
                        <div class="img"><a href="/TourismManagement/tin-tuc/${data[i]['newsSlug']}"><img
                                    src="${data[i]['newsCoverPage']}"
                                    alt="${data[i]['newsTitle']}"
                                    class="lazy"></a></div>
                        <div class="tend">
                            <h3><a href="/TourismManagement/tin-tuc/${data[i]['newsSlug']}">${data[i]['newsTitle']}</a></h3>
                        </div>
                        <div class="tools">
                            <div class="be"><span class="fa-calendar"> ${new Date(data[i]['newsCreatedDate']).toISOString().split('T')[0]}</span></div>
                            <div class="be"><span class="fa-thumbs-up">${data[i]['newsLikeAmount']}</span></div>
                        </div>
                        <div class="des">
                            <p><span style="font-size:16px;"><span
                                        style="font-family:Arial,Helvetica,sans-serif;">${data[i]['newsDescription']}</span></span><br>
                                &nbsp;</p>
                        </div>
                    </div>
                </div>
            `
        $('#ext_news').html(rows)
    })
}

function getPageAmount() {
    fetch('/TourismManagement/tin-tuc/so-trang')
        .then(res => res.json()).then(data => {
            let pageAmount = data['pageAmount']
            if (pageAmount == 1)
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
})