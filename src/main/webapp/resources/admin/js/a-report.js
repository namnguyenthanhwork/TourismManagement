// Thông tin các loại lựa chọn báo cáo
let gChoiceInfo = {
    reportType: 'doanh-thu',
    month: null,
    quarter: null,
    year: null,
}

function getReportData(choiceInfo) {
    let query = '';
    choiceInfo.reportType = $('#reportType').val()
    choiceInfo.reportCondition = $('#reportCondition').val()

    let timeChoice = '';
    if (choiceInfo.reportCondition === 'thang')
        timeChoice = $('#monthInput').val()
    else if (choiceInfo.reportCondition === 'quy')
        timeChoice = $('#quarterInput').val()
    let year = $('#yearInput').val()

    if (choiceInfo.reportCondition === 'thang' || choiceInfo.reportCondition === 'quy')
        query = `loai=${choiceInfo.reportType}&thoi-gian=${choiceInfo.reportCondition}&tg1=${timeChoice}&tg2=${year}`
    else
        query = `loai=${choiceInfo.reportType}&thoi-gian=${choiceInfo.reportCondition}&tg1=${year}`

    fetch(`/TourismManagement/quan-tri-vien/bao-cao/thong-tin/?${query}`).then(res => res.json()).then(data => {
        setReportDataTable(data)
    })
}

// Gửi các thông tin cần thiết lên server để lấy thông tin report cho việc xuất pdf
function getExportReportData(choiceInfo) {
    let query = '';
    choiceInfo.reportType = $('#reportType').val()
    choiceInfo.reportCondition = $('#reportCondition').val()

    let timeChoice = '';
    if (choiceInfo.reportCondition === 'thang')
        timeChoice = $('#monthInput').val()
    else if (choiceInfo.reportCondition === 'quy')
        timeChoice = $('#quarterInput').val()
    let year = $('#yearInput').val()

    if (choiceInfo.reportCondition === 'thang' || choiceInfo.reportCondition === 'quy')
        query = `loai=${choiceInfo.reportType}&thoi-gian=${choiceInfo.reportCondition}&tg1=${timeChoice}&tg2=${year}`
    else
        query = `loai=${choiceInfo.reportType}&thoi-gian=${choiceInfo.reportCondition}&tg1=${year}`

    fetch(`/TourismManagement/quan-tri-vien/bao-cao/thong-tin/?${query}`).then(res => res.json()).then(reportDatas => {
        if (reportDatas.length <= 0) {
            Swal.fire({
                title: 'Dữ liệu trống không thể xuất pdf. Vui lòng thử lại sau',
                icon: 'error',
                confirmButtonColor: '#3085d6',
                confirmButtonText: 'Ok',
            })
            return
        }
        getPdf(reportDatas)
    })
}
// Thiết lập dữ liệu các điều kiện tháng, quý, năm
function setTimeSelections() {
    var options = ''
    for (let i = 1; i <= 12; i++)
        options += `<option value=${i}>${i}</option>`
    $('#monthInput').html(options)
    options = ''
    for (let i = 1; i <= 4; i++)
        options += `<option value=${i}>${i}</option>`
    $('#quarterInput').html(options)
    options = ''
    for (let i = 2022; i <= 2100; i++)
        options += `<option value=${i}>${i}</option>`
    $('#yearInput').html(options)
}

// Thiết lập dữ liệu bảng kết quả thống kê
function setReportDataTable(reportData) {
    var row = ''
    var header = ''
    if ($('#reportType').val().includes('doanh-thu'))
        header += `<tr>
                    <th class="text-center text-uppercase text-dark text-xxs font-weight-bolder opacity-7">${'STT'}</th>
                    <th class="text-center text-uppercase text-dark text-xxs font-weight-bolder opacity-7">Ngày tạo</th>
                    <th class="text-center text-uppercase text-dark text-xxs font-weight-bolder opacity-7">Tổng doanh thu</th>
                    <th class="text-center text-uppercase text-dark text-xxs font-weight-bolder opacity-7">Tổng khuyến mãi</th>
                </tr>`
    else
        header += `<tr>
                    <th class="text-center text-uppercase text-dark text-xxs font-weight-bolder opacity-7">${'STT'}</th>
                    <th class="text-center text-uppercase text-dark text-xxs font-weight-bolder opacity-7">Ngày tạo</th>
                    <th class="text-center text-uppercase text-dark text-xxs font-weight-bolder opacity-7">Tổng số lượng tour</th>
                </tr>`
    for (let i = 0; i < reportData.length; i++)
        if ($('#reportType').val().includes('doanh-thu'))
            row += `<tr> 
                        <td class="align-middle text-center">
                                <span class="text-secondary text-xs font-weight-bold">${i + 1}</span>
                        </td>
                        <td class="align-middle text-center">
                                <span class="text-secondary text-xs font-weight-bold">${new Date(reportData[i]['billCreatedDate']).toISOString().split('T')[0]}</span>
                        </td>
                        <td class="align-middle text-center">
                                <span class="text-secondary text-xs font-weight-bold">${reportData[i]['billTotalMoney'].toLocaleString('it-IT', {style : 'currency', currency : 'VND'})}</span>
                        </td>
                        <td class="align-middle text-center">
                                <span class="text-secondary text-xs font-weight-bold">${reportData[i]['billTotalSaleMoney'].toLocaleString('it-IT', {style : 'currency', currency : 'VND'})}</span>
                        </td>
                    </tr>`
    else
        row += `<tr> 
                    <td class="align-middle text-center">
                        <span class="text-secondary text-xs font-weight-bold">${i + 1}</span>
                    </td>
                    <td class="align-middle text-center">
                        <span class="text-secondary text-xs font-weight-bold">${new Date(reportData[i]['billCreatedDate']).toISOString().split('T')[0]}</span>
                    </td>
                    <td class="align-middle text-center">
                        <span class="text-secondary text-xs font-weight-bold">${reportData[i]['tourAmount']}</span>
                    </td>
                </tr>`

    $('#titleReportTable').html(header)
    $('#dataReportTable').html(row)
}

// Thiết lập giá trị khởi tạo lúc load trang lần đầu
function setInitialData(choiceInfo) {
    setTimeSelections()
    choiceInfo.month = parseInt($('#monthInput').val())
    choiceInfo.year = parseInt($('#yearInput').val())
}

// Thiết lập dữ liệu cho pdf và xuất pdf
// Xuất pdf
function getPdf(reportDatas) {
    const pdf = new jsPDF({
        orientation: 'landscape',
        format: 'a4',
        putOnlyUsedFonts: true,
        floatPrecision: 16
    })

    pdf.setFontSize(13)
    var head = []
    var body = []
    var foot = ''
    var sum = 0

    if ($('#reportType option:selected').val() == 'doanh-thu') {
        head = ['STT', 'Ngay ban', 'Tong doanh thu', 'Tong khuyen mai']
        for (let i = 0; i < reportDatas.length; i++) {
            temp = []
            temp.push(i + 1)
            temp.push(new Date(reportDatas[i]['billCreatedDate']).toISOString().split('T')[0])
            temp.push(reportDatas[i]['billTotalMoney'].toLocaleString('it-IT', {
                style: 'currency',
                currency: 'VND'
            }))
            temp.push(reportDatas[i]['billTotalSaleMoney'].toLocaleString('it-IT', {
                style: 'currency',
                currency: 'VND'
            }))
            body.push(temp)
            sum = reportDatas[i]['billTotalMoney'].toLocaleString('it-IT', {
                style: 'currency',
                currency: 'VND'
            })
        }
        foot = `Tong doanh thu: ${sum}`
    }

    if ($('#reportType option:selected').val() == 'so-luong-tour') {
        head = ['STT', 'Ngay tao', 'So luong']
        for (let i = 0; i < reportDatas.length; i++) {
            temp = []
            temp.push(i + 1)
            temp.push(reportDatas[i]['billCreatedDate'])
            temp.push(reportDatas[i]['tourAmount'] + ' tour')
            body.push(temp)
            sum = reportDatas[i]['tourAmount']
        }
        foot = `Tong so tour: ${sum}`
    }
    var time = ''
    if ($('#reportCondition').val().includes('thang'))
        time = 'THANG'
    else
    if ($('#reportCondition').val().includes('quy'))
        time = 'QUY'
    else
        time = 'NAM'
    if ($('#reportType').val().includes('doanh-thu'))
        pdf.text(`BAO CAO DOANH THU THEO ${time}`, 110, 10)
    else
        pdf.text('BAO CAO SO LUONG TOUR', 110, 10)

    pdf.autoTable({
        head: [head],
        body: body,
        startY: 30,
        theme: 'grid',
        styles: {
            font: 'Arial',
            fontStyle: 'normal',
        },
        headStyles: {
            fontStyle: 'bold',
            halign: 'center',
            valign: 'middle',
            fontSize: 13,
            cellWidth: 'auto',
            minCellHeight: 15,
            lineWidth: 1,
            lineColor: [4, 41, 58]
        },
        bodyStyles: {
            halign: 'center',
            valign: 'center',
            lineColor: [4, 41, 58],
            cellPadding: {
                bottom: 5,
                top: 5
            }
        }
    })
    pdf.setFontSize(15)

    pdf.text(foot, 20, pdf.lastAutoTable.finalY + 15)

    pdf.autoPrint({
        variant: 'non-conform'
    });

    pdf.save('report.pdf')
    Swal.fire({
        title: 'Xuất phiếu thành công',
        icon: 'success',
        confirmButtonColor: '#3085d6',
        confirmButtonText: 'Ok',
    })
}

$(document).ready(function () {
    setInitialData(gChoiceInfo)
    getReportData(gChoiceInfo)
    $('#quarterSelection').hide()
    $('#reportType').change(function () {
        getReportData(gChoiceInfo)
    })

    $('#reportCondition').change(function () {
        if ($(this).val() == 'thang') {
            $('#monthSelection').show()
            $('#quarterSelection').hide()
            gChoiceInfo.month = parseInt($('#monthInput').val())
            gChoiceInfo.quarter = null
        }

        if ($(this).val() == 'quy') {
            $('#monthSelection').hide()
            $('#quarterSelection').show()
            gChoiceInfo.month = null
            gChoiceInfo.quarter = parseInt($('#quarterInput').val())
        }

        if ($(this).val() == 'nam') {
            $('#monthSelection').hide()
            $('#quarterSelection').hide()
            gChoiceInfo.month = null
            gChoiceInfo.quarter = null
        }

        gChoiceInfo.year = parseInt($('#yearInput').val())
        getReportData(gChoiceInfo)
    })

    $('#monthInput').change(function () {
        gChoiceInfo.month = parseInt($(this).val())
        getReportData(gChoiceInfo)
    })

    $('#quarterInput').change(function () {
        gChoiceInfo.quarter = parseInt($(this).val())
        getReportData(gChoiceInfo)

    })

    $('#yearInput').change(function () {
        gChoiceInfo.year = parseInt($(this).val())
        getReportData(gChoiceInfo)
    })
    $('#exportPdf').click(function () {
        getExportReportData(gChoiceInfo)
    })
})