<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Những thứ dùng chung cho tất cả các trang sau khi login phía nhân viên -->
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
    <link rel="stylesheet"
        href="https://cdnjs.cloudflare.com/ajax/libs/jquery-datetimepicker/2.5.20/jquery.datetimepicker.min.css"
        integrity="sha512-f0tzWhCwVFS3WeYaofoLWkTP62ObhewQ1EZn65oSYDZUg1+CyywGKkWzm8BxaJj5HGKI72PnMH9jYyIFz+GH7g=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />
    <!-- -->
    <%-- Google Font --%>
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700" rel="stylesheet" />
    <%-- Main css  --%>
    <link href="<c:url value = '/resources/common/css/tourism.css' />" rel="stylesheet" />
    <!-- Nucleo Icons -->
    <link href="<c:url value = '/resources/common/css/nucleo-icons.css'/>" rel="stylesheet" />
    <link href="<c:url value = '/resources/common/css/nucleo-svg.css'/>" rel="stylesheet" />
    <link href="<c:url value = '/resources/common/css/loading.css'/>" rel="stylesheet" />
    <link href="<c:url value = '/resources/ckeditor/ckeditor.css'/>" rel="stylesheet" />
    <!-- Font Awesome Icons -->
    <script src="https://kit.fontawesome.com/42d5adcbca.js" crossorigin="anonymous"></script>
    <link href="<c:url value = '/resources/common/css/nucleo-svg.css'/>" rel="stylesheet" />
    <tiles:insertAttribute name="a-ref" />
</head>

<body class="g-sidenav-show  bg-gray-100">
    <%-- Sidebar --%>
    <tiles:insertAttribute name="a-sidebar" />
    <main class="main-content position-relative max-height-vh-100 h-100 mt-1 border-radius-lg ">
        <tiles:insertAttribute name="a-header" />
        <div class="container-fluid py-4">
            <tiles:insertAttribute name="a-content" />
            <tiles:insertAttribute name="a-footer" />
        </div>
    </main>

    <script src="<c:url value = '/resources/common/js/plugins/chartjs.min.js'/>"></script>
    <!--   Core JS Files   -->
    <script src="<c:url value = '/resources/common/js/core/popper.min.js'/>"></script>
    <script src="<c:url value = '/resources/common/js/core/bootstrap.min.js'/>"></script>
    <script src="<c:url value = '/resources/common/js/plugins/perfect-scrollbar.min.js'/>"></script>
    <script src="<c:url value = '/resources/common/js/plugins/smooth-scrollbar.min.js'/>"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-datetimepicker/2.5.20/jquery.datetimepicker.full.min.js"
        integrity="sha512-AIOTidJAcHBH2G/oZv9viEGXRqDNmfdPVPYOYKGy3fti0xIplnlgMHUGfuNRzC6FkzIo0iIxgFnr9RikFxK+sw=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <!-- Validate -->
    <script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.1/jquery.validate.min.js">
    </script>
    <!-- Page Specific JS Files   -->
    <script src="<c:url value = '/resources/common/js/plugins/choices.min.js'/>"></script>
    <script src="<c:url value = '/resources/common/js/plugins/fullcalendar.min.js'/>"></script>
    <%-- SweetAlert --%>
    <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script src="<c:url value ='/resources/admin/js/admin.js'/>"></script>

</body>

</html>