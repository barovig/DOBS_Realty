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
        <div class="user_bar">
            <ul>
                <li>${new_fav}</li>
                <c:forEach var="c" items="${cookie}">
                    <c:if test="${c.key ne 'JSESSIONID'}">
                    <li>${c.value.value}</li>
                    </c:if>
                </c:forEach>
            </ul>
        </div>        
        <div class="content">
        <table id="property_details">
            <tr>
                <td><a href="/PropertyController?action=details&id=${prop.id}"><img src="/assets/img/properties/thumbs/${prop.photo}"/></a></td>
                <td>${prop.street}</td>
                <td>${prop.city}</td>
                <td>${prop.listingNum}</td>
                <td>${prop.styleId.getPStyle()}</td>
                <td>${prop.typeId.getPType()}</td>
                <td>${prop.bedrooms}</td>
                <td>${prop.bathrooms}</td>
                <td>${prop.squarefeet}</td>
                <td>${prop.description}</td>
                <td>${prop.lotsize}</td>
                <td>${prop.garagesize}</td>
                <td>${prop.garageId.getGType()}</td>
                <td>${prop.agentId.getName()}</td>
                <td>${prop.price}</td>
            </tr>
        </table>
        </div>
        <%@ include file="assets/html/footer.html" %>
        </div>
    </body>
</html>
