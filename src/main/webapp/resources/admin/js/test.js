fetch("http://localhost:8080/TourismManagement/quan-tri-vien/tai-khoan/tim-kiem/thong-tin?kw=Họ người dùng 1 Tên người dùng 1")
    .then(res=>{
        if(res.status != 200)
            return res.status
        return res.json()
    }).then(data=>console.info(data))
// fetch("http://localhost:8080/TourismManagement/quan-tri-vien/tour-du-lich/thong-tin?page=1").then(res=>res.json()).then(data=>console.info(data))
