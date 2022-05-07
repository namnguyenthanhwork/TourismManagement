let tourSlug = ''

function convertHref() {
    let href = window.location.href
    if (href.includes('?'))
        href = href.substring(0, href.indexOf('?'))
    if (href.includes('#'))
        href = href.substring(0, href.indexOf('#'))
    return href
}

function getTourThumbnail() {
    let path = convertHref() + '/hinh-thu-nho'
    fetch(path).then(res => res.json()).then(data => {
        let thumbnails = ''
        for (let i = 0; i < data.length; i++)
            thumbnails += `
                <div>
                    <div class="item" style="width: 100%; display: inline-block;">
                        <div class="img"><img
                                src="${data[i]['thumImage']}"
                                alt="Hình thu nhỏ">
                        </div>
                    </div>
                </div>
          `
        $('#vnt-slide-thumbnail').html(thumbnails)
        $('#vnt-slide-thumbnail').slick({
            dots: false,
            infinite: true,
            autoplay: true,
            speed: 300,
            slidesToShow: 1,
            slidesToScroll: 1,
            nav: true
        });
        $(".service-more").click(function () {
            $(".service-more-content").toggle();
        });
        $(".note-more").click(function () {
            $(".note-more-content").toggle();
        });
    })
}

function getTourInfo() {
    let path = convertHref() + '/thong-tin'
    fetch(path).then(res => res.json()).then(data => {
        $('#tourTitle').text(data['tourTitle'])
        let schedules = ''
        for (let i = 0; i < data['schedules'].length; i++) {
            let schedule = data['schedules'][i]
            schedules += `
                 <div class="day active">
                    <div class="titDay"><span>NGÀY ${i + 1} |</span> ${schedule['scheTitle']}</div>
                    <div class="arrow-up"></div>
                    <div class="contDay" style="display: block;">
                        <div class="the-content desc">
                           ${schedule['scheContent']}
                        </div>
                    </div>
                </div>
            `
        }
        $('#scheduleList').html(schedules)

        let services = ''
        for (let i = 0; i < data['services'].length; i++) {
            let service = data['services'][i]
            services += `
                 <p><strong>${service['servTitle']}</strong><br> •&nbsp;&nbsp; &nbsp;
                 ${service['servContent']}
                               
            `
        }
        $('#serviceList').html(services)

        let notes = ''
        for (let i = 0; i < data['notes'].length; i++) {
            let note = data['notes'][i]
            notes += `
                 <p><strong>${note['noteTitle']}</strong><br> •&nbsp;&nbsp; &nbsp;
                 ${note['noteContent']}
                               
            `
        }
        $('#noteList').html(notes)

        let scheduleList = data['schedules']
        let sche = ''
        for (let i = 0; i < scheduleList.length; i++) {
            sche += `${scheduleList[i]['scheTitle']}`
            if (i !== scheduleList.length - 1)
                sche += ' - '
        }

        let dptList = data['departureDates']
        let dpt = ''
        for (let i = 0; i < dptList.length; i++) {
            dpt += `${new Date(dptList[i]['dptDate']).toISOString().split('T')[0]}`
            if (i !== dptList.length - 1)
                dpt += ' & '
        }
        let s = `
             <tr class="mb-2">
                    <td style="width: 20%;" class="py-2"><span style="color:#555555;"><strong>Hành
                    trình:</strong></span></td>
                    <td class="py-2"><span style="color:#555555;" class="ml-2">${sche}</span></td>
                </tr>
                <tr class="mb-2">
                    <td class="py-2"><span style="color:#555555;"><strong>Các ngày khởi hành:</strong></span></td>
                    <td class="py-2"><span style="color:#555555;" class="ml-2">${dpt}</span></td>
                </tr>
        `
        let tourContentDetail = `
            <table>
                <tbody>
                ${s}
                </tbody>
            </table>
            <p style="text-align: justify;"><em><a href="javascript:;"><span
                            style="color:#3498db;"><strong>${data['catName']} </strong></span></a>- 
                ${data['tourContent']}

        `
        $('#tourContentDetail').html(tourContentDetail)

        let dptDetailList = ''
        for (let i = 0; i < data['departureDates'].length; i++)
            dptDetailList += `
                <tr>
                    <td class="responsive sttt">${i+1}</td>
                    <td align="right">${new Date(data['departureDates'][i]['dptDate']).toISOString().split('T')[0]}</td>
                    <td class="responsive dd ">${data['departureDates'][i]['feaName']}</td>
                    <td align="right"><span>${data['departureDates'][i]['tourAmount']}</span>
                    </td>
                </tr>
            `
        $('#dptDetailList').html(dptDetailList)

    })
}

function getTourAverageRating() {
    fetch(convertHref() + '/ti-le-danh-gia/so-luong').then(res => res.json()).then(data => {
        let userRating = ''
        for (let i = 1; i <= 5; i++)
            userRating += `
                   <span class="fa fa-star ${i<=data['tourAverageRating']?'checked':''}"></span>              
                `
        $('#userRating').html(userRating)
    })
}

function getTourGeneralInfo() {
    let path = convertHref() + '/thong-tin-tong-quan'
    fetch(path).then(res => res.json()).then(data => {
        let transports = data['transports']
        let transport = ''
        for (let i = 0; i < transports.length; i++) {
            transport += `${transports[i]['tranName']}`
            if (i !== transports.length - 1)
                transport += ' - '
        }
        let info = `
                   <li>
                        <div class="at">Mã tour:</div>
                        <div class="as">${data['tourId']} &nbsp;</div>
                    </li>
                    <li>
                        <div class="at">Khởi hành:</div>
                        <div class="as">${new Date(data['tourDepartureDate']).toISOString().split('T')[0]} &nbsp;</div>
                    </li>
                    <li>
                        <div class="at">Vận Chuyển:</div>
                        <div class="as">
                            ${transport} &nbsp;
                        </div>
                    </li>
                    <li>
                        <div class="at">Xuất phát:</div>
                        <div class="as">Từ
                            ${data['schedules'][0]['scheTitle']} &nbsp;
                        </div>
                    </li>
        `
        $('#generalTourTitle').text(data['tourTitle'])
        $('#generalTourPrice').text(`${data['tourPrice']} Đ`)
        $('#generalTourInfo').html(info)
        $('#giactt').text(`${data['tourPrice'].toLocaleString('it-IT', {style : 'currency', currency : 'VND'})}`)
        $('#dDate').val(new Date(data['tourDepartureDate']).toISOString().split('T')[0])
        getTourRating()
    })
}

function getTourRating() {
    fetch(convertHref() + '/ti-le-danh-gia')
        .then(res => res.json()).then(data => {
            $('.bar-5').css('width', `${Math.floor(data['fiveStars'] / data['total'] *100)}%`)
            $('.bar-4').css('width', `${Math.floor(data['fourStars'] / data['total'] * 100)}%`)
            $('.bar-3').css('width', `${Math.floor(data['threeStars'] / data['total'] *100 )}%`)
            $('.bar-2').css('width', `${Math.floor(data['twoStars'] / data['total'] * 100)}%`)
            $('.bar-1').css('width', `${Math.floor(data['oneStar'] / data['total'] *100)}%`)
            $('#5a').text(data['fiveStars'])
            $('#4a').text(data['fourStars'])
            $('#3a').text(data['threeStars'])
            $('#2a').text(data['twoStars'])
            $('#1a').text(data['oneStar'])

        })
}

function getComments() {
    let path = convertHref() + '/binh-luan'
    fetch(path).then(res => res.json()).then(data => {
        let cmtDetails = ''
        for (let i = 0; i < data.length; i++)
            cmtDetails += `
                <div class="col-12 mb-4">
                    <div class="d-flex align-items-center">
                        <img src="${data[i]['accAvatar']}" style="width: 30px; height: 30px">
                        <h5 class="ml-2">${data[i]['accName']} </h5>
                        <span class="comment-time ml-3"><i class="fa-solid fa-clock-rotate-left"></i> ${moment(new Date(data[i]['postCreatedDate'])).locale('vi').fromNow()}</span>
                    </div>
                    <div class="block-cmt-content">
                        <p class="text-justify ml-4 py-3">${data[i]['commentContent']}</p>
                    </div>
                </div>
            `
        $('#cusCommentDetail').html(cmtDetails)
    })
}

function addComment(cmtContent) {
    let path = convertHref() + '/binh-luan'
    fetch(path, {
        method: 'post',
        body: JSON.stringify({
            'cmtContent': cmtContent
        }),
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(res => res.status).then(data => {
        if (data === 200) {
            getComments()
            return
        }
        Swal.fire({
            title: 'Thông báo !',
            text: "Đã xảy ra lỗi trong quá trình thêm. Vui lòng thử lại!",
            icon: 'error',
            confirmButtonColor: '#3085d6',
            confirmButtonText: 'Ok'
        })
    })
}

function rateTour(rateAmount) {
    let path = convertHref() + '/ti-le-danh-gia'
    fetch(path, {
        method: 'post',
        body: JSON.stringify({
            'rateAmount': rateAmount
        }),
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(res => res.status).then(data => {
        if (data === 200) {
            getTourRating()
            getTourAverageRating()
            Swal.fire({
                title: 'Đánh giá thành công !',
                text: "Cảm ơn bạn đã đánh giá!!",
                icon: 'success',
                confirmButtonColor: '#3085d6',
                confirmButtonText: 'Ok'
            })
            return
        }
        Swal.fire({
            title: 'Thông báo !',
            text: "Đã xảy ra lỗi trong quá trình thêm. Vui lòng thử lại!",
            icon: 'error',
            confirmButtonColor: '#3085d6',
            confirmButtonText: 'Ok'
        })
    })
}

$(document).ready(function () {
    getTourThumbnail()
    getTourInfo()
    getTourGeneralInfo()
    getComments()
    getTourAverageRating()
    $('#tourBookingBtn').click(function () {
        let href = window.location.href
        if (href.includes('?'))
            href = href.substring(0, href.indexOf('?'))
        if (href.includes('#'))
            href = href.substring(0, href.indexOf('#'))
        window.location.href = href + '/thanh-toan'
    })

    $('#addComment').click(function () {
        let cmtContent = $('#cmtContent').val()
        if (cmtContent == null || cmtContent.trim().length == 0) {
            Swal.fire({
                title: 'Thông báo !',
                text: "Thông tin bình luận không được để trống!",
                icon: 'warning',
                confirmButtonColor: '#3085d6',
                confirmButtonText: 'Ok'
            })
            return
        }
        addComment(cmtContent)
        $('#cmtContent').val('')
    })

    $('#confirmRatingBtn').click(function () {
        let rateAmount = $("#ratingPanel input[name='rate']:checked").val()
        rateTour(rateAmount)
    })
})