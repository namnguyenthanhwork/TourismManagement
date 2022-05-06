
function convertHref(){
    let href = window.location.href
    if (href.includes('?'))
        href = href.substring(0, href.indexOf('?'))
    if (href.includes('#'))
        href = href.substring(0, href.indexOf('#'))
    return href
}
function getNewsInfo(){
    let path = convertHref() + '/thong-tin'
    fetch(path).then(res=>res.json()).then(data=> {

        $('#newsTitle').text(data['newsTitle'])
        $('#newsDescription').text(data['newsDescription'])
        $('#newsContent').html(data['newsContent'] +
            `<p style="text-align:right">
                    <em id="newsCreatedDate">
                    Đăng ngày: ${new Date(data['newsCreatedDate']).toLocaleDateString()}
                    </em>

             </p>`)

    })
}


function  getAllNews(){
    fetch('/TourismManagement/tin-tuc/thong-tin').then(res=>res.json()).then(data=>{
        let news = ''
        for(let i=0; i<data.length; i++)
            news+=`
                 <div>
                    <div class="item">
                        <div class="news">
                            <div class="img"><a
                                    href="/TourismManagement/tin-tuc/${data[i]['newsSlug']}"
                                    tabindex="-1"><img
                                        src="${data[i]['newsCoverPage']}"
                                        alt="${data[i]['newsTitle']}"
                                        class="lazy"></a></div>
                            <div class="tend">
                                <h3><a href="/TourismManagement/tin-tuc/${data[i]['newsSlug']}"
                                        tabindex="-1">${data[i]['newsTitle']}</a></h3>
                            </div>
                            <div class="tools">
                                <div class="be"><span class="fa-calendar">${new Date(data[i]['newsCreatedDate']).toLocaleDateString()}</span></div>
                                <div class="be"><span class="fa-thumbs-up">${data[i]['newsLikeAmount']}</span></div>
                            </div>
                            <div class="des">
                                <p><span style="font-size:16px;"><span
                                            style="font-family:Arial,Helvetica,sans-serif;">${data[i]['newsDescription']}</span></span><br>
                                    &nbsp;</p>
                            </div>
                        </div>
                    </div>
                 </div>
            `
        $('#relevantNews').html(news)
        $('.mid-content').owlCarousel({
            loop: true,
            margin: 10,
            nav: true,
            autoplay: true,
            responsive: {
                0: {
                    items: 1,
                    nav: true
                },
                500: {
                    items: 2
                },
                770: {
                    items: 3
                },
                1200: {
                    items: 4
                }
            }
        })
    })
}

function getComments(){
    let path = convertHref() + '/binh-luan'
    fetch(path).then(res=>res.json()).then(data=>{
        let cmtDetails = ''
        for(let i=0; i<data.length; i++)
            cmtDetails += `
                <div class="col-12">
                    <div >
                        <h5>${data[i]['accName']}</h5>
                        <img src="${data[i]['accAvatar']}" style="width: 50px; height: 50px">
                    </div>
                    <div >
                        <p>${data[i]['commentContent']}</p>
                        <p>Thời gian: ${moment(new Date(data[i]['postCreatedDate'])).locale('vi').fromNow()}</p>
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
        alert("Đã xảy ra lỗi trong quá trình thêm. Vui lòng thử lại!")

    })
}
//like

function likeNews(){
    fetch(convertHref()+'/luot-thich', {
        method: 'post',
        body: JSON.stringify({}),
        headers:{
            'Content-Type': 'application/json'
        }
    }).then(res => res.status).then(data => {
        getLikeDetail()
        getLikeStatus()
    })
}
function getLikeDetail(){
    fetch(convertHref()+'/luot-thich').then(res=>res.json()).then(data=>{
        $('#likeAmount').text(data['newsLikeAmount'])
    })
}

function getLikeStatus() {
    fetch(convertHref() + '/trang-thai-thich').then(res => res.json()).then(data => {
        let likeBtn = $('#likeBtn')
        if (likeBtn != null)
            if (data) {
                likeBtn.removeClass('btn btn-info')
                likeBtn.addClass('btn-primary')
            } else {
                likeBtn.removeClass('btn-primary')
                likeBtn.addClass('btn btn-info')

            }
    })
}

$(document).ready(function () {
    getNewsInfo()
    getAllNews()
    getLikeDetail()
    getComments()
    getLikeStatus()

    $('#addComment').click(function () {
        let cmtContent = $('#cmtContent').val()
        if (cmtContent == null || cmtContent.trim().length == 0) {
            alert("Thông tin bình luận không được để trống")
            return
        }
        addComment(cmtContent)
        $('#cmtContent').val('')
    })

    $('#likeBtn').click(function () {
        likeNews()
    })
})
