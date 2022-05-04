let gChoiceInfo = {
    statisticType: 'doanh-thu',
    statisticCondition: 'thang',
    fromTime: null,
    toTime: null,
    flagTimeInput: null
}

let gChartInfo = {
    chart: null,
    type: null,
    data: null,
    backgroundColor: '#fff',
    label: null,
    labels: null
}

function getStatisticData(choiceInfo, chartInfo) {
    let params = '';
    setStatisticInput(choiceInfo)
    choiceInfo.statisticType = $('#statisticType').val()
    choiceInfo.statisticCondition = $('#statisticCondition').val()
    if (choiceInfo.statisticCondition === 'thang' ||
        choiceInfo.statisticCondition === 'quy' ||
        choiceInfo.statisticCondition === 'nam') {
        params = `loai=${choiceInfo.statisticType}&thoi-gian=${choiceInfo.statisticCondition}`
    } else if (choiceInfo.statisticCondition.includes('thang-den-thang') ||
        choiceInfo.statisticCondition.includes('quy-den-quy') ||
        choiceInfo.statisticCondition.includes('nam-den-nam')) {
        let leftTime = $('#leftTime').val()
        let rightTime = $('#rightTime').val()
        params = `loai=${choiceInfo.statisticType}&thoi-gian=${choiceInfo.statisticCondition}&bat-dau=${leftTime}&ket-thuc=${rightTime}`
    }
    fetch(`/TourismManagement/quan-tri-vien/thong-ke/thong-tin/?${params}`).then(res => res.json()).then(data => {

        $('#statisticTable').show()

        setStatisticDataTable(data)
        setChartData(data, chartInfo)
        chartInfo.label = $("#statisticType option:selected").text()
        chartInfo.type = $('#chartType').val()
        setChart(chartInfo)
    })
}
// Thiết lập label header cho bảng kết quả thống kê
function setLabelHeaderTable() {
    var conditionSelect = $('#statisticCondition').val()
    if (conditionSelect.includes('thang'))
        return 'Tháng'

    if (conditionSelect.includes('quy'))
        return 'Quý'
    return 'Năm'
}

// Thiết lập dữ liệu bảng kết quả thống kê
function setStatisticDataTable(statisticData) {
    var row = ''
    var header = ''
    if ($('#statisticType').val().includes('doanh-thu'))
        header += `<tr>
                    <th class="text-center text-uppercase text-dark text-xxs font-weight-bolder opacity-7">${'STT'}</th>
                    <th class="text-center text-uppercase text-dark text-xxs font-weight-bolder opacity-7">${setLabelHeaderTable()}</th>
                    <th class="text-center text-uppercase text-dark text-xxs font-weight-bolder opacity-7">Tổng doanh thu</th>
                    <th class="text-center text-uppercase text-dark text-xxs font-weight-bolder opacity-7">Tổng khuyến mãi</th>
                </tr>`
    else
        header += `<tr>
                    <th class="text-center text-uppercase text-dark text-xxs font-weight-bolder opacity-7">${'STT'}</th>
                    <th class="text-center text-uppercase text-dark text-xxs font-weight-bolder opacity-7">${setLabelHeaderTable()}</th>
                    <th class="text-center text-uppercase text-dark text-xxs font-weight-bolder opacity-7">Tổng số lượng tour</th>
                </tr>`
    for (let i = 0; i < statisticData.length; i++)
        if ($('#statisticType').val().includes('doanh-thu'))
            row += `<tr> 
                        <td class="align-middle text-center">
                                <span class="text-secondary text-xs font-weight-bold">${i + 1}</span>
                        </td>
                        <td class="align-middle text-center">
                                <span class="text-secondary text-xs font-weight-bold">${statisticData[i]['time']}</span>
                        </td>
                        <td class="align-middle text-center">
                                <span class="text-secondary text-xs font-weight-bold">${statisticData[i]['billTotalMoney'].toLocaleString('it-IT', {style : 'currency', currency : 'VND'})}</span>
                        </td>
                        <td class="align-middle text-center">
                                <span class="text-secondary text-xs font-weight-bold">${statisticData[i]['billTotalSaleMoney'].toLocaleString('it-IT', {style : 'currency', currency : 'VND'})}</span>
                        </td>
                    </tr>`
    else
        row += `<tr> 
                    <td class="align-middle text-center">
                        <span class="text-secondary text-xs font-weight-bold">${i + 1}</span>
                    </td>
                    <td class="align-middle text-center">
                        <span class="text-secondary text-xs font-weight-bold">${statisticData[i]['time']}</span>
                    </td>
                    <td class="align-middle text-center">
                        <span class="text-secondary text-xs font-weight-bold">${statisticData[i]['tourAmount']}</span>
                    </td>
                </tr>`

    $('#titleStatisticTable').html(header)
    $('#dataStatisticTable').html(row)
}

// Thiết lập dữ liệu input cho chức năng thống kê
function setStatisticInput(choiceInfo) {
    var conditionSelect = $('#statisticCondition').val()
    var timeAreaInput = $('#timeInput')

    timeAreaInput.css('display', 'none')
    if (conditionSelect.includes('thang-den-thang') ||
        conditionSelect.includes('quy-den-quy') ||
        conditionSelect.includes('nam-den-nam')) {
        var minVal = null
        var maxVal = null
        if (conditionSelect.includes('thang-den-thang')) {
            minVal = 1
            maxVal = 12
        }

        if (conditionSelect.includes('quy-den-quy')) {
            minVal = 1
            maxVal = 4
        }

        if (conditionSelect.includes('nam-den-nam')) {
            minVal = 2018
            maxVal = 2100
        }

        timeAreaInput.css('display', 'flex')
        var selection = ''
        for (let i = minVal; i <= maxVal; i++)
            selection += `<option value=${i}>${i}</option>`

        if (!choiceInfo.flagTimeInput) {
            $('#leftTime').html(selection)
            $('#rightTime').html(selection)
            choiceInfo.flagTimeInput = true
            choiceInfo.fromTime = choiceInfo.toTime = minVal
        }
    }
}

// Thiết lập chart
function setChart(chartInfo) {
    var backgroundColor = [];
    var borderColor = [];
    if (chartInfo.data != null)
        for (let i = 0; i < chartInfo.data.length; i++) {
            r = Math.floor(Math.random() * 255 + 1);
            g = Math.floor(Math.random() * 255 + 1);
            b = Math.floor(Math.random() * 255 + 1);
            backgroundColor.push(`rgba(${r},${g}, ${b}, 0.7)`);
            borderColor.push(`rgba(${r},${g}, ${b}, 1)`);
        }
    else
        return
    const ctx = document.getElementById('chart').getContext('2d');

    if (chartInfo.chart != null)
        chartInfo.chart.destroy();

    const bgColorPDF = {
        id: 'bgColorPDF',
        beforeDraw: (chart) => {
            const {
                ctx,
                width,
                height
            } = chart
            ctx.fillStyle = chartInfo.backgroundColor
            ctx.fillRect(0, 0, width, height)
            ctx.restore()
        }
    }
    chartInfo.chart = new Chart(ctx, {
        type: chartInfo.type,
        data: {
            labels: chartInfo.labels,
            datasets: [{
                label: chartInfo.label,
                data: chartInfo.data,
                backgroundColor: backgroundColor,
                borderColor: borderColor,
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        },
        plugins: [bgColorPDF]
    });
}


//  Thiết lập dữ liệu cho chart
function setChartData(statisticData, chartInfo) {
    chartInfo.data = [];
    chartInfo.labels = [];
    var resultKey = $('#statisticType').val().includes('doanh-thu') ? 'billTotalMoney' : 'tourAmount'
    for (let i = 0; i < statisticData.length; i++) {
        chartInfo.labels.push(statisticData[i]['time'])
        data = statisticData[i][resultKey]
        chartInfo.data.push(data)
    }
}

// Thiết lập dữ liệu cho pdf và xuất pdf
function setPdfExport() {
    const canvas = document.getElementById('chart')
    canvas.toDataURL('image/jpeg', 1.0)

    const pdf = new jsPDF({
        orientation: 'portrait',
        format: 'a4',
        putOnlyUsedFonts: true,
        floatPrecision: 16
    })
    pdf.setFontSize(13)
    pdf.text(`Bieu do thong ke ${$('#statisticType option:selected').val()}`.toUpperCase(), 10, 15)
    pdf.addImage({
        imageData: canvas,
        format: 'JPEG',
        x: 30,
        y: 45,
        width: 250,
        height: 200
    })
    pdf.addPage({
        format: 'a4',
        orientation: 'portrait'
    })
    pdf.autoTable({
        html: '#statisticTable',
        theme: 'grid',
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

    pdf.text('@CopyRight: Open University', 77, pdf.lastAutoTable.finalY + 10)

    pdf.autoPrint({
        variant: 'non-conform'
    });
    pdf.save('statistic.pdf')

    Swal.fire({
        title: 'Xuất phiếu thành công',
        icon: 'success',
        confirmButtonColor: '#3085d6',
        confirmButtonText: 'Ok',
    })
}
$(document).ready(function () {
    getStatisticData(gChoiceInfo, gChartInfo)
    $('#timeInput').hide()
    $('#statisticType').change(() => {
        getStatisticData(gChoiceInfo, gChartInfo)
    })

    $('#chartType').change(() => {
        gChartInfo.type = $('#chartType').val()
        getStatisticData(gChoiceInfo, gChartInfo)
    })

    $('#statisticCondition').change(function () {
        gChoiceInfo.flagTimeInput = false
        getStatisticData(gChoiceInfo, gChartInfo)
    })

    $('#leftTime').data('lastSelectedIndex', 0)

    $('#leftTime').click(function () {
        $(this).data('lastSelectedIndex', this.selectedIndex)
    })
    $('#rightTime').data('lastSelectedIndex', 0)

    $('#rightTime').click(function () {
        $(this).data('lastSelectedIndex', this.selectedIndex)
    })

    $('#leftTime').change(function () {
        if (parseInt($(this).val()) > parseInt($('#rightTime').val())) {
            this.selectedIndex = $(this).data('lastSelectedIndex')
        } else {
            gChoiceInfo.fromTime = parseInt($(this).val())
            getStatisticData(gChoiceInfo, gChartInfo)
        }
    })

    $('#rightTime').change(function () {
        if (parseInt($(this).val()) < parseInt($('#leftTime').val())) {
            this.selectedIndex = $(this).data('lastSelectedIndex')
        } else {
            gChoiceInfo.toTime = parseInt($(this).val())
            getStatisticData(gChoiceInfo, gChartInfo)
        }
    })

    $('#pdfChart').click(function () {
        setPdfExport()
    })
})