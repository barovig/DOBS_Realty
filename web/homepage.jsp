<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="assets/html/links.html" %>
        <title></title>
    </head>
    <body>
        <div class="main-content">
        <%@ include file="assets/html/header.html" %>
        <div class="content">
            <table>
            <c:forEach var="prop" items="${list}" >
                <tr>
                    <td>${prop.id}</td>
                    <td>${prop.street}</td>
                    <td>${prop.city}</td>
                    <td>${prop.listingNum}</td>
                    <td>${prop.styleId}</td>
                    <td>${prop.typeId}</td>
                    <td>${prop.bedrooms}</td>
                    <td>${prop.bathrooms}</td>
                    <td>${prop.squarefeet}</td>
                    <td>${prop.description}</td>
                    <td>${prop.lotsize}</td>
                    <td>${prop.garagesize}</td>
                    <td>${prop.garageId}</td>
                    <td>${prop.agentId}</td>
                    <td>${prop.photo}</td>
                    <td>${prop.price}</td>
                </tr>
            </c:forEach>
            </table>
        </div>
        <%@ include file="assets/html/footer.html" %>
        </div>
    </body>
</html>
