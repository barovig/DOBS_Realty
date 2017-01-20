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
        <div class="main-content">
        <div class="content">
            <div class="searches">
                <form action="/PropertyController?action=search" method="post" id="search_form">
                City:
                <select name="city" form="search_form">
                    <option value="any">Any</option>
                    <c:forEach var="city" items="${cities}">
                        <option value="${city}" ${city == selectCity ? "selected" : ""}>${city}</option>
                    </c:forEach>
                </select>
                Price:<input type="text" name="min_price" /> -
                <input type="text" name="max_price" />
                <input type="submit" value="Search"/>
                </form>
            </div>
            <c:choose>
            <c:when test="${msg != null}">
                <h2 class="error_msg">${msg}</h2>
            </c:when>
            <c:otherwise>
            <table id="propertyTable">
                <thead>
                <tr>
                    <th></th>
                    <th>Price</th>
                    <th>City</th>
                    <th>Bedrooms</th>
                    <th>Bathrooms</th>
                    <th>Square feet</th>
                    <th></th>
                </tr>
                </thead>
            <c:forEach var="prop" items="${list}" >
                <tr>
                    <td><a href="/PropertyController?action=details&id=${prop.id}"><img src="/assets/img/properties/thumbs/${prop.photo}"/></a></td>
                    <fmt:setLocale value="en_IE" />
                    <fmt:formatNumber var="price" type="currency" value="${prop.price}" />
                    <td>${price}</td>
                    <td>${prop.city}</td>
                    <td>${prop.bedrooms}</td>
                    <td>${prop.bathrooms}</td>
                    <td>${prop.squarefeet}</td>
                    <td>
                        <a href="/PropertyController?action=setfav&id=${prop.id}">Favourite</a>
                    </td>
                </tr>
            </c:forEach>
            </table>
            </c:otherwise>
        </c:choose>
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
                            <fmt:setLocale value="en_IE" />
                            <fmt:formatNumber var="price" type="currency" value="${prop.price}" />
                            <p>${price}</p>
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
