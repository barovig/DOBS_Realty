<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
