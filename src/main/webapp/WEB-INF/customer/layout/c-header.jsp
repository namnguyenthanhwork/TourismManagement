<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="vnt-header-top">
    <div id="vnt-header">
        <div class="topHead">
            <div class="wrapper">
                <div class="hotlineTop">
                    <div class="icon">
                        <span>Hotline: <strong class="eff">19001177</strong></span>
                    </div>
                    <div class="popup">
                        <div class="tableHotline">
                            <p>
                                Hotline Hỗ Trợ (1.000đ/phút)
                                <a href="tel:19001177">19001177</a>
                            </p>
                            <p>
                                Hotline Hỗ Trợ
                                <a href="tel:02873056789">02873056789</a>
                            </p>
                            <p>
                                Tour du lịch
                                <a href="tel:0903933788">090393 3788</a>
                            </p>
                            <p>
                                Tour khách đoàn
                                <a href="tel:0934146588">093414 6588</a>
                            </p>
                            <p>
                                Thuê xe
                                <a href="tel:0902976588">090297 6588</a>
                            </p>
                            <p>
                                Dịch vụ visa
                                <a href="tel:0903933788">090393 3788</a>
                            </p>
                            <p>
                                Vé máy bay
                                <a href="tel:0934094988">093409 4988</a>
                            </p>
                            <p>
                                Tour từ Hà Nội
                                <a href="tel:0932659588">093265 9588</a>
                            </p>
                        </div>
                    </div>
                </div>
                <div class="menuNav">
                    <c:choose>
                        <c:when test="${pageContext.request.userPrincipal.name != null}">
                            <ul>
                                <li class="text-warning font-weight-bold mr-3">
                                    <i class="fa-solid fa-user"></i> ${pageContext.request.userPrincipal.name}
                                </li>
                                <li>
                                    <a href="<c:url value='/auth/dang-xuat' />"
                                        class="text-gradient font-weight-bold">Đăng
                                        xuất <i class="fa-solid fa-arrow-right-from-bracket"></i></a>
                                </li>
                            </ul>
                        </c:when>

                        <c:when test="${pageContext.request.userPrincipal.name == null}">
                            <ul>
                                <li>
                                    <a href="<c:url value='/auth/dang-nhap' />"
                                        class="text-gradient font-weight-bold"><i
                                            class="fa-solid fa-arrow-right-to-bracket"></i> Đăng nhập</a>
                                </li>
                                <li>
                                    <a href="<c:url value='/dang-ki-tai-khoan' />"
                                        class="text-gradient font-weight-bold"><i
                                            class="fa-solid fa-arrow-right-from-bracket"></i> Đăng ký</a>
                                </li>
                            </ul>
                        </c:when>
                    </c:choose>

                </div>
            </div>
        </div>
        <div class="mainHead">
            <div class="wrapper">
                <div class="wrapPoss">
                    <div class="logo">
                        <a href="<c:url value='/' />" title="Mạng bán TOUR DU LỊCH trực tuyến hàng đầu Việt nam">
                            <img alt="Tour Du Lịch Việt"
                                src="https://res.cloudinary.com/ou-project/image/upload/v1651826932/Icon%20Homepage%20Client/logoHp_fjyom7.png" />
                        </a>
                    </div>
                    <div class="header-tools">
                        <div class="menuTop hidden-sm hidden-xs">
                            <ul>
                                <li class="nopos current">
                                    <a href="javascript:;">Du lịch</a>
                                    <div class="megaMenu">
                                        <div class="grid">
                                            <div class="col1">
                                                <div class="menuNa">
                                                    <ul>
                                                        <li data-menu="menu1">
                                                            <a href="javascript:;">Du lịch trong nước</a>
                                                        </li>
                                                        <li data-menu="menu2">
                                                            <a href="javascript:;">Du lịch nước ngoài</a>
                                                        </li>
                                                        <li data-menu="menu3">
                                                            <a href="javascript:;">Du lịch khách đoàn</a>
                                                        </li>
                                                    </ul>
                                                </div>
                                            </div>
                                            <div class="col2">
                                                <div class="menuFo active" data-menu="menu1">
                                                    <div class="wrap">
                                                        <ul>
                                                            <li>
                                                                <a href="javascript:;">Du lịch Miền Bắc</a>
                                                                <ul>
                                                                    <li>
                                                                        <a href="javascript:;">Du lịch Mộc Châu</a>
                                                                    </li>
                                                                    <li>
                                                                        <a href="javascript:;">Du lịch Ninh Bình</a>
                                                                    </li>
                                                                    <li>
                                                                        <a href="du-lich-tay-bac">Du lịch Tây Bắc</a>
                                                                    </li>
                                                                </ul>
                                                            </li>
                                                            <li>
                                                                <a href="/du-lich-mien-trung">Du lịch Miền Trung</a>
                                                                <ul>
                                                                    <li>
                                                                        <a href="du-lich-da-nang">Du lịch Đà Nẵng</a>
                                                                    </li>
                                                                    <li>
                                                                        <a href="du-lich-hoi-an">Du lịch Hội An</a>
                                                                    </li>
                                                                    <li><a href="du-lich-hue">Du lịch Huế</a></li>
                                                                </ul>
                                                            </li>
                                                            <li>
                                                                <a href="javascript:;">Du lịch Miền Nam</a>
                                                                <ul>
                                                                    <li>
                                                                        <a href="du-lich-tay-ninh">Du lịch Tây Ninh</a>
                                                                    </li>
                                                                    <li>
                                                                        <a href="du-lich-nam-du">Du lịch Nam Du</a>
                                                                    </li>
                                                                    <li>
                                                                        <a href="du-lich-mien-tay">Du lịch Miền Tây</a>
                                                                    </li>
                                                                </ul>
                                                            </li>
                                                            <li>
                                                                <a href="javascript:;">Chùm tour nghỉ lễ</a>
                                                                <ul>
                                                                    <li>
                                                                        <a href="tour-trong-nuoc-le-2-9"><span
                                                                                style="text-align: justify;">Tour lễ
                                                                                2/9</span></a>
                                                                    </li>
                                                                    <li>
                                                                        <a href="chum-tour-mua-thu-trong-nuoc">Tour du
                                                                            lịch mùa Thu</a>
                                                                    </li>
                                                                    <li>
                                                                        <a href="tour-du-lich-mua-lua-chin">Tour du lịch
                                                                            mùa lúa chín</a>
                                                                    </li>
                                                                    <li>
                                                                        <a href="tour-du-lich-mua-hoa-tam-giac-mach">Tour
                                                                            du lịch mùa hoa Tam Giác Mạch</a>
                                                                    </li>
                                                                </ul>
                                                            </li>
                                                        </ul>
                                                    </div>
                                                    <div class="linkAllMenu">
                                                        <a href="javascript:;"><span>Tất cả tour trong nước</span></a>
                                                    </div>
                                                </div>
                                                <div class="menuFo" data-menu="menu2">
                                                    <div class="wrap">
                                                        <ul class="v2">
                                                            <li>
                                                                <a href="javascript:;">Du lịch Châu Á</a>
                                                                <ul>
                                                                    <li>
                                                                        <a href="du-lich-nhat-ban">Du lịch Nhật Bản</a>
                                                                    </li>
                                                                    <li>
                                                                        <a href="du-lich-han-quoc">Du lịch Hàn Quốc</a>
                                                                    </li>
                                                                    <li>
                                                                        <a href="du-lich-dai-loan">Du lịch Đài Loan</a>
                                                                    </li>
                                                                </ul>
                                                            </li>
                                                            <li>
                                                                <a href="javascript:;">Du lịch Châu Âu</a>
                                                                <ul>
                                                                    <li>
                                                                        <a href="du-lich-tay-au">Du lịch Tây Âu</a>
                                                                    </li>
                                                                    <li>
                                                                        <a href="du-lich-phap">Du lịch Pháp</a>
                                                                    </li>
                                                                </ul>
                                                            </li>
                                                            <li>
                                                                <a href="javascript:;">Du lịch Châu Mỹ</a>
                                                                <ul>
                                                                    <li><a href="du-lich-my">Du lịch Mỹ</a></li>
                                                                    <li>
                                                                        <a href="du-lich-canada">Du lịch Canada</a>
                                                                    </li>
                                                                    <li></li>
                                                                </ul>
                                                            </li>
                                                            <li>
                                                                <a href="javascript:;">Du lịch Châu Úc</a>
                                                                <ul>
                                                                    <li><a href="du-lich-uc">Du lịch Úc</a></li>
                                                                    <li>
                                                                        <a href="du-lich-new-zealand">Du lịch New
                                                                            Zealand</a>
                                                                    </li>
                                                                </ul>
                                                            </li>
                                                            <li>
                                                                <a href="javascript:;">Du lịch Châu Phi</a>
                                                                <ul>
                                                                    <li>
                                                                        <a href="du-lich-maroc">Du lịch Maroc</a>
                                                                    </li>
                                                                    <li>
                                                                        <a href="du-lich-nam-phi">Du lịch Nam Phi</a>
                                                                    </li>
                                                                </ul>
                                                            </li>
                                                        </ul>
                                                    </div>
                                                    <div class="linkAllMenu">
                                                        <a href="javascript:;"><span>Tất cả tour nước ngoài </span></a>
                                                    </div>
                                                </div>
                                                <div class="menuFo" data-menu="menu3">
                                                    <div class="wrap">
                                                        <ul>
                                                            <li>
                                                                <a href="/to-chuc-su-kien">Tổ chức sự kiện</a>
                                                            </li>
                                                            <li><a href="/tour-mice">Tour Mice</a></li>
                                                            <li>
                                                                <a href="/to-chuc-teambuilding">Tổ chức Teambuilding</a>
                                                            </li>
                                                            <li>
                                                                <a href="/tour-theo-doan-the">Tour đoàn thể</a>
                                                            </li>
                                                            <li>
                                                                <a href="/tour-chuyen-de">Tour chuyên đề</a>
                                                            </li>
                                                            <li>
                                                                <a href="/tour-gia-dinh">Tour gia đình</a>
                                                            </li>
                                                            <li><a href="/tour-moi-la">Tour mới lạ</a></li>
                                                            <li><a href="/tour-caravan">Tour Caravan</a></li>
                                                        </ul>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                                <li class=""><a href="javascript:;">Vé máy bay</a></li>
                                <li class=""><a href="javascript:;">Khách sạn</a></li>
                                <li class=""><a href="javascript:;">Dịch vụ visa</a></li>
                                <li class=""><a href="javascript:;">Thuê xe</a></li>
                                <li class="">
                                    <a href="<c:url value='/tin-tuc' />">Tin tức</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>