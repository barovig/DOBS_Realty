<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="assets/jsp/links.jsp" %>
        <title></title>
    </head>
    <body>
        <div class="main-content">
        <%@ include file="assets/jsp/header.jsp" %>
        <div class="content">
            <h1>An error has occurred: ${msg}</h1>
        </div>
        <%@ include file="assets/jsp/footer.jsp" %>
        </div>
    </body>
</html>
