let currentPageIndex = 1

function getTours(pageIndex = null, type = null, kw = null) {
    let path = '/TourismManagement/tour-du-lich/thong-tin'
    if (type != null && kw != null)
        path += `?loai=${type}&kw=${kw}`
    if (pageIndex != null)
        path += `${path.includes('?') ? '&' : '?'}trang=${pageIndex}`
    fetch(path)
        .then(res => res.json()).then(data => {
            let tours = ''
            for (let i = 0; i < data.length; i++) {
                let emptySlot = data[i]['tourEmptySlot']
                let tourDepartureDate = data[i]['tourDepartureDate']
                if (tourDepartureDate == null)
                    tourDepartureDate = ' - '
                else
                    tourDepartureDate = new Date(tourDepartureDate).toISOString().split('T')[0]
                let schedules = data[i]['schedules']
                let schedule = ''
                for (let j = 0; j < schedules.length; j++) {
                    schedule += `${schedules[j]['scheTitle']}`
                    if (j !== schedules.length - 1)
                        schedule += ' - '
                }
                tours += `
                <div class="col-sm-6 col-lg-4 mb-4">
                <div class="mda-box-type">
                    <div class="mda-img-box">
                        <a
                            href="/TourismManagement/tour-du-lich/${data[i]['tourSlug']}">
                            <img alt="${data[i]['tourTitle']}"
                                src="${data[i]['tourCoverPage']}"
                                style="display: inline-block;">
                            <div class="mda-box-lb">${schedules[0]['scheTitle']}</div>
                            <div class="mda-box-time">
                                <p>
                                    <img class="mda-icon-clock" src="https://res.cloudinary.com/ou-project/image/upload/v1651832046/Icon%20Homepage%20Client/i-clock-y_gmdu5x.png"
                                        style="display: inline-block;"> <span class="mda-lb">${emptySlot !== 0 ? 'Còn đến' : 'Hết'}</span>
                                    <span class="mda-time mda-time-down" data-time="${tourDepartureDate}">${tourDepartureDate}</span>
                                </p>
                            </div>
                        </a>
                    </div>
                    <div class="mda-img-box-info clearfix">
                        <h3 class="mda-name"><a
                                title="${data[i]['tourTitle']}"
                                href="/TourismManagement/tour-du-lich/${data[i]['tourSlug']}">${data[i]['tourTitle']}</a></h3>
                        <div class="mda-img-box-wrap">
                            <p class="mda-time">${schedule}</p>
                            <p class="mda-schedule">Khởi hành: ${tourDepartureDate}</p>
                            <p class="mda-place">Số chỗ còn nhận: ${emptySlot}</p>
                            <p class="mda-price mda-distcoun">
                                <span class="mda-dis">Giá tour: ${data[i]['tourPrice'].toLocaleString('it-IT', {style : 'currency', currency : 'VND'})}</span> </p>
                        </div>
                    </div>
                </div>
            </div>
            `
            }
            if (data.length == 0) {
                tours += `<p class="col-12 text-center text-danger my-4 font-weight-bold">Không tìm thấy tour !</p>`
                $('#tourHomePageTitle').css('display', 'none');
                $('#pagination').css('display', 'none');
            } else {
                $('#tourHomePageTitle').css('display', 'block');
                $('#pagination').css('display', 'block');
            }
            $('#tourHomePageContent').html(tours);
        })
}

function getPageAmount() {
    fetch('/TourismManagement/tour-du-lich/so-trang')
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
        getTours(currentPageIndex)
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
    getTours(currentPageIndex)

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
        getTours(currentPageIndex)
    }
    if (currentPageIndex == pageAmount)
        $('#nextBtn').hide()
    if (currentPageIndex != pageAmount)
        $('#nextBtn').show()
    if (currentPageIndex != 1)
        $('#preBtn').show()
}

$(document).ready(function () {
    getTours(currentPageIndex)
    getPageAmount()
    $('#search').keyup(function () {
        let type = $('#searchType').val()
        let kw = $(this).val().length > 0 ? $(this).val().trim() : null
        getTours(currentPageIndex, type, kw)
    })
    $('#searchType').change(function () {
        switch ($('#searchType').val()) {
            case "ten":
                $("#search").prop("type", "text");
                break
            case "gia":
                $("#search").prop("type", "number");
                break
            case "lich-trinh":
                $("#search").prop("type", "text");
                break
        }
        $('#search').val('')
    })
})