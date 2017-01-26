<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="context" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="../assets/jsp/links.jsp" %>
        <title></title>
    </head>
    <body>
        <div class="main-content">
        <%@ include file="../assets/jsp/header.jsp" %>
        <div class="content">
            <h2>Your Properties List</h2>
			<a href="${context}/AgentController?action=insert">Add New Property</a>
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
					<th></th>
                </tr>
                </thead>
            <c:forEach var="prop" items="${list}" >
                <tr>
                    <td><a href="${context}/PropertyController?action=details&id=${prop.id}"><img src="${context}/assets/img/properties/thumbs/${prop.photo}"/></a></td>
                    <fmt:setLocale value="en_IE" />
                    <fmt:formatNumber var="price" type="currency" value="${prop.price}" maxFractionDigits="0" />
                    <td>${price}</td>
                    <td>${prop.city}</td>
                    <td>${prop.bedrooms}</td>
                    <td>${prop.bathrooms}</td>
                    <td>${prop.squarefeet}</td>
					<td><a href="${context}/AgentController?action=manage&id=${prop.id}">Manage</a></td>
					<td><a href="${context}/AgentController?action=delete&id=${prop.id}" onclick="return confirmDelete();">Delete</a></td>
                </tr>
            </c:forEach>
            </table>
        </div>
        <%@ include file="../assets/jsp/footer.jsp" %>
        </div>
    </body>
</html>
