function getTourMenu(){
    fetch('/TourismManagement/thanh-dieu-huong')
        .then(res=>res.json()).then(data=>{
            // console.info(data)
        // let storages = ''
        // let categories =''
        // for(let i=1; i<= data.length; i++){
        //     storages+=`
        //          <li data-menu="menu${i}"><a href="javascript:;">${data[i-1]['storName']}</a></li>
        //     `
        //     let catInfo = ''
        //     for(let j=0; j<data[i-1]['categories'].length; j++) {
        //         let cat = data[i-1]['categories'][j]
        //         catInfo += `
        //                <li><a href="javascript:;">${cat['catName']}</a></li>
        //         `
        //     }
        //     categories+=`
        //             <div class="menuFo ${i===1?'active':''}" data-menu="menu${i}">
        //                 <div class="wrap">
        //                     <ul>
        //                         ${catInfo}
        //                     </ul>
        //                 </div>
        //                 <div class="linkAllMenu"><ahref="javascript:;"><span>Tất cả tour</span></a></div>
        //             </div>
        //     `
        // }
        // $('#storages').html(storages)
        // $('#categories').html(categories)
    })
}
$(document).ready(function(){
    getTourMenu()
})