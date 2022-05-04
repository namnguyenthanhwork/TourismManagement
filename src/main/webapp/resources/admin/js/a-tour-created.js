function getCategoryInfo(){
    fetch("/TourismManagement/quan-tri-vien/loai-tour/thong-tin")
        .then(res => {
            if (res.status != 200)
                return res.status
            return res.json()
        }).then(data => {
        if (data == 204) {
            alert("thông tin trống")
            return
        }
        let options = '';
        for (let i = 0; i < data.length; i++) {
            options += `
                     <option value="${data[i]['catSlug']}">${data[i]['catName']}</option>
                `
        }
        document.getElementById('catSlug').innerHTML = options
    })
}
function getSaleInfo(){
    fetch("/TourismManagement/quan-tri-vien/giam-gia/thong-tin")
        .then(res => {
            if (res.status != 200)
                return res.status
            return res.json()
        }).then(data => {
        if (data == 204) {
            alert("thông tin trống")
            return
        }
        let options = '<option value="-1">Không giảm giá</option>';
        for (let i = 0; i < data.length; i++) {
            let saleFromDate = new Date(data[i]['saleFromDate']).toLocaleDateString()
            let saleToDate = new Date(data[i]['saleToDate']).toLocaleDateString()
            options += `
                     <option value="${data[i]['saleId']}">${data[i]['sperPercent']}% (${saleFromDate} - ${saleToDate})</option>
                `
        }
        document.getElementById('saleId').innerHTML = options
    })
}

function getServingObjectInfo (){
    fetch("/TourismManagement/quan-tri-vien/doi-tuong-phuc-vu/thong-tin")
        .then(res => {
            if (res.status != 200)
                return res.status
            return res.json()
        }).then(data => {
        if (data == 204) {
            alert("thông tin trống")
            return
        }
        let options = ''
        let prices = ''
        for (let i = 0; i < data.length; i++) {
            options += `
                     <option value="${data[i]['svoSlug']}">${data[i]['svoName']}
                     </option>
                `
            prices+=`
                <div class="form-group">
                    <input type="number" name="${data[i]['svoSlug']}Price" min="0" value="0"><span> VNĐ</span>
                </div>
            `
        }
        document.getElementById('svoSlugs').innerHTML = options
        document.getElementById('prices').innerHTML = prices
    })
}

function getServiceInfo(){
    fetch("/TourismManagement/quan-tri-vien/dich-vu/thong-tin")
        .then(res => {
            if (res.status != 200)
                return res.status
            return res.json()
        }).then(data => {
        if (data == 204) {
            alert("thông tin trống")
            return
        }
        let options = '';
        for (let i = 0; i < data.length; i++) {
            options += `
                     <option value="${data[i]['servSlug']}">${data[i]['servTitle']}</option>
                `
        }
        document.getElementById('servSlugs').innerHTML = options
    })
}

function getDepartureDateInfo() {
    fetch("/TourismManagement/quan-tri-vien/ngay-khoi-hanh/thong-tin")
        .then(res => {
            if (res.status != 200)
                return res.status
            return res.json()
        }).then(data => {
        if (data == 204) {
            alert("thông tin trống")
            return
        }
        let options = ''
        let tourAmount = ''
        for (let i = 0; i < data.length; i++) {
            options += `
                     <option value="${data[i]['dptId']}">${new Date(data[i]['dptDate']).toLocaleDateString()}</option>
                `
            tourAmount += `
                <div class="form-group">
                    <input type="number" name="${data[i]['dptId']}Amount" min="0" value="0">
                </div>
            `
        }
        document.getElementById('dptIds').innerHTML = options
        document.getElementById('tourAmounts').innerHTML = tourAmount
    })
}

function getTransportInfo(){
    fetch("/TourismManagement/quan-tri-vien/phuong-tien-di-chuyen/thong-tin")
        .then(res => {
            if (res.status != 200)
                return res.status
            return res.json()
        }).then(data => {
        if (data == 204) {
            alert("thông tin trống")
            return
        }
        let options = '';
        for (let i = 0; i < data.length; i++) {
            options += `
                     <option value="${data[i]['tranSlug']}">${data[i]['tranName']}</option>
                `
        }
        document.getElementById('tranSlugs').innerHTML = options
    })
}
function getNoteInfo(){
    fetch("/TourismManagement/quan-tri-vien/ghi-chu/thong-tin")
        .then(res => {
            if (res.status != 200)
                return res.status
            return res.json()
        }).then(data => {
        if (data == 204) {
            alert("thông tin trống")
            return
        }
        let options = '';
        for (let i = 0; i < data.length; i++) {
            options += `
                     <option value="${data[i]['noteSlug']}" ${i===0?'selected':''}>${data[i]['noteTitle']}</option>
                `
        }
        document.getElementById('noteSlugs').innerHTML = options
    })
}

$(document).ready(function () {
    $('#loading').hide()
    getCategoryInfo()
    getSaleInfo()
    getServingObjectInfo()
    getServiceInfo()
    getDepartureDateInfo()
    getTransportInfo()
    getNoteInfo()
    $('#tourCreatedBtn').click(function (){
        alert("Xác nhận tạo tour du lịch mới?")
        $(this).hide()
        $('#loading').show()
    })
})