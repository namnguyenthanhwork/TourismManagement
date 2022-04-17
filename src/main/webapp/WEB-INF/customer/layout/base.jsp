
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
    </head>

    <body >
         <tiles:insertAttribute name="header" />
         <tiles:insertAttribute name="sidebar" />
         <tiles:insertAttribute name="content" />
         <tiles:insertAttribute name="footer" />
    </body>

</html>