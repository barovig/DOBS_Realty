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
        <%@ include file="assets/html/header.html" %>
        <div class="main-content">
        <div class="content">
            <table id="propertyTable">
                <thead>
                <tr>
                    <th></th>
                    <th>Price</th>
                    <th>City</th>
                    <th>Bedrooms</th>
                    <th>Bathrooms</th>
                    <th>Square feet</th>
                </tr>
                </thead>
            <c:forEach var="prop" items="${list}" >
                <tr>
                    <td><a href="/PropertyController?action=details&id=${prop.id}"><img src="/assets/img/properties/thumbs/${prop.photo}"/></a></td>
                    <td>${prop.price}</td>
                    <td>${prop.city}</td>
                    <td>${prop.bedrooms}</td>
                    <td>${prop.bathrooms}</td>
                    <td>${prop.squarefeet}</td> 
                </tr>
            </c:forEach>
            </table>
        </div>
        <div class="dashboard">
            <ul class="cycle-slideshow vertical" 
                        data-cycle-fx="carousel" 
                        data-cycle-timeout="2000"
                        data-cycle-pager="#pager"
                        data-cycle-carousel-visible=3
                        data-cycle-carousel-vertical=true
                        data-cycle-slides="> li">
                
                <c:forEach var="prop" items="${lastAdditions}" >
                    <li>
                        <div class="prop_recent">
                            <a href="/PropertyController?action=details&id=${prop.id}">
                                <img src="/assets/img/properties/thumbs/${prop.photo}"/>
                            </a>
                            <p>${prop.price}</p>
                            <p>${prop.city}</p>
                        </div>
                    </li>
                </c:forEach>
            </ul>
            <div class="cycle-pager" id=pager></div>
        </div>
        </div>
        <%@ include file="assets/html/footer.html" %>
    </body>
</html>
