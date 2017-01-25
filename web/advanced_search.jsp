<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="assets/jsp/links.jsp" %>
        <title></title>
    </head>
    <body>
        <%@ include file="assets/jsp/header.jsp" %>
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
                </tr>
                </thead>
            <c:forEach var="prop" items="${list}" >
                <tr>
                    <td><a href="/PropertyController?action=details&id=${prop.id}"><img src="/assets/img/properties/thumbs/${prop.photo}"/></a></td>
                    <fmt:setLocale value="en_IE" />
                    <fmt:formatNumber var="price" type="currency" value="${prop.price}" maxFractionDigits="0" />
                    <td>${price}</td>
                    <td>${prop.city}</td>
                    <td>${prop.bedrooms}</td>
                    <td>${prop.bathrooms}</td>
                    <td>${prop.squarefeet}</td>
                </tr>
            </c:forEach>
            </table>
            </c:otherwise>
        </c:choose>
        </div>
        <div class="dashboard">
            <form action="/PropertyController?action=adv_search" 
                  method="post" id="advanced_search" name="adv_search">
            <table class="adv-search-table">
                <tr>
                    <td>Bedrooms:</td>
                    <td>
                        <select id="bedrooms">
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Bathrooms:</td>
                    <td>
                        <select id="bathrooms">
                        </select>
                    </td>
                </tr>
            </table>
            <input type="submit" value="Search" />
        </div>
        </div>
        <%@ include file="assets/jsp/footer.jsp" %>
    </body>
</html>
