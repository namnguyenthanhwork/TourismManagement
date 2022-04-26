function getRoleInfo(){
    let path = window.location.href + '/chinh-sua';
    fetch(path).then(res => {
        if (res.status !== 200)
            return res.status
        return res.json()
    }).then(data => {
        if (data === 204) {
            alert("thông tin trống")
            return
        }
        document.getElementById('roleName').value = data['roleName'];
    })
}

function redirectPageAfterUpdate(){
    fetch(window.location.href).then(res => {
        return res.status
    }).then(data => {
        window.location.href = '/TourismManagement/quan-tri-vien/vai-tro'
    })
}


$(document).ready(function () {
    $('#roleUpdateForm').attr('action', window.location.href);
    getRoleInfo()
    $('#roleUpdatedBtn').click( () =>redirectPageAfterUpdate())
})