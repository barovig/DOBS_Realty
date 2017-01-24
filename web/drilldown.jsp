<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="context" value="${pageContext.request.contextPath}" />
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
            <div class="drilldown">
                <div id="drilldown-listnum"><p>Listing number: ${prop.listingNum}</p></div>
                <img id="dd-pic-large" src="${context}/assets/img/properties/large/${img_folder}/${img_files[0].getName()}"/>
                <div id="drilldown-pics">
                    <c:set value="0" var="i" scope="page" />
                    <c:forEach var="img" items="${img_files}">
                        <img 
                            id="dd-pic-${i}" 
                            class="dd-thumb-pics" 
                            src="${context}/assets/img/properties/large/${img_folder}/${img.getName()}"
                            onclick="ddSetLargePic(${i});"
                             />
                        <c:set value="${i + 1}" var="i" scope="page" />
                    </c:forEach>
                </div>
            </div>
            <table class="dd-table">
                <tr>
                  <th class="tg-031e" id="dd-street"colspan="4">${prop.street}<br></th>
                </tr>
                <tr>
                  <td class="tg-e3zv">City:<br</td>
                  <td class="tg-031e">${prop.city}</td>
                  <td class="tg-e3zv">Price:</td>
                  <fmt:setLocale value="en_IE" />
                  <fmt:formatNumber var="price" type="currency" value="${prop.price}" maxFractionDigits="0"/>
                  <td class="tg-yw4l">${price}</td>
                </tr>
                <tr>
                  <td class="tg-e3zv">Property Style:<br></td>
                  <td class="tg-031e">${prop.styleId.getPStyle()}</td>
                  <td class="tg-e3zv">Type:</td>
                  <td class="tg-yw4l">${prop.typeId.getPType()}</td>
                </tr>
                <tr>
                  <td class="tg-e3zv">Bedrooms:</td>
                  <td class="tg-yw4l">${prop.bedrooms}</td>
                  <td class="tg-e3zv">Bathrooms:</td>
                  <td class="tg-yw4l">${prop.bathrooms}</td>
                </tr>
                <tr>
                  <td class="tg-e3zv">Squarefeet:</td>
                  <td class="tg-yw4l">${prop.squarefeet}</td>
                  <td class="tg-e3zv">BER Rating:</td>
                  <td class="tg-e3zv" id="dd-ber"><img src="${context}/assets/img/BER/${prop.berRating}.png"/><br></td>
                </tr>
                <tr>
                  <td class="tg-e3zv">Garage Type:<br></td>
                  <td class="tg-yw4l">${prop.garageId.getGType()}</td>
                  <td class="tg-e3zv">Garage Size:<br></td>
                  <td class="tg-yw4l">${prop.garagesize}</td>
                </tr>
                <tr>
                    <td colspan="2"></td>
                    <td colspan="2">
                        <a href="${context}/PropertyController?action=setfav&id=${prop.id}">Add to Favourites</a>
                    </td>
                    
                </tr>
              </table>
        </div>
        <div class="dashboard">
            <%@include file="assets/html/agent.html" %>
        </div>
        <%@ include file="assets/html/footer.html" %>
        </div>
    </body>
</html>
