<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Những thứ dùng chung cho tất cả các trang sau khi login phía khách hàng -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
        integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous">
    </script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous">
    </script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous">
    </script>

    <!-- Carousel -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.carousel.min.css"
        integrity="sha512-tS3S5qG0BlhnQROyJXvNjeEM4UpMXHrQfTGmbQ1gKmelCxlSEBUaxhRBj/EFTzpbP4RVSrpEikbmdJobCvhE3g=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.8.1/slick-theme.css"
        integrity="sha512-6lLUdeQ5uheMFbWm3CP271l14RsX1xtx+J5x2yeIDkkiBpeVTNhTqijME7GgRKKi6hCqovwCoBTlRBEC20M8Mg=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />

    <!-- Fontawesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
        integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/js/all.min.js"
        integrity="sha512-6PM0qYu5KExuNcKt5bURAoT6KCThUmHRewN3zUFNaoI6Di7XJPTMoT6K0nsagZKk2OB4L7E3q1uQKHNHd4stIQ=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.3/moment-with-locales.min.js"></script>
    <link href="<c:url value = '/resources/customer/css/customer.css' />" rel="stylesheet" />
    <script src="<c:url value='/resources/customer/js/c-header.js'/> "></script>

</head>

<body>
    <div id="vnt-wrapper">
        <div id="vnt-container">
            <tiles:insertAttribute name="c-header" />
            <tiles:insertAttribute name="c-content" />
            <tiles:insertAttribute name="c-footer" />
        </div>
    </div>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/owl.carousel.min.js"
        integrity="sha512-bPs7Ae6pVvhOSiIcyUClR7/q2OAsRiovw4vAkX+zJbw3ShAeeqezq50RIIcIURq7Oa20rW2n2q+fyXBNcU9lrw=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.8.1/slick.min.js"
        integrity="sha512-XtmMtDEcNz2j7ekrtHvOVR4iwwaD6o/FUJe6+Zq+HgcCsk3kj4uSQQR8weQ2QVj1o0Pk6PwYLohm206ZzNfubg=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script src="<c:url value ='/resources/customer/js/customer.js'/>"></script>
    <tiles:insertAttribute name="c-ref" />


<%--    <!-- Messenger Chat Plugin Code -->--%>
<%--    <div id="fb-root"></div>--%>

<%--    <!-- Your Chat Plugin code -->--%>
<%--    <div id="fb-customer-chat" class="fb-customerchat"></div>--%>

<%--    <script>--%>
<%--        var chatbox = document.getElementById('fb-customer-chat');--%>
<%--        chatbox.setAttribute("page_id", "105825651245887");--%>
<%--        chatbox.setAttribute("attribution", "biz_inbox");--%>
<%--    </script>--%>

<%--    <!-- Your SDK code -->--%>
<%--    <script>--%>
<%--        window.fbAsyncInit = function () {--%>
<%--            FB.init({--%>
<%--                xfbml: true,--%>
<%--                version: 'v12.0'--%>
<%--            });--%>
<%--        };--%>

<%--        (function (d, s, id) {--%>
<%--            var js, fjs = d.getElementsByTagName(s)[0];--%>
<%--            if (d.getElementById(id)) return;--%>
<%--            js = d.createElement(s);--%>
<%--            js.id = id;--%>
<%--            js.src = 'https://connect.facebook.net/vi_VN/sdk/xfbml.customerchat.js';--%>
<%--            fjs.parentNode.insertBefore(js, fjs);--%>
<%--        }(document, 'script', 'facebook-jssdk'));--%>
<%--    </script>--%>

</body>

</html>