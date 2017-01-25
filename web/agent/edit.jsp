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
            <form action="/AgentController?action=edit" method="post">
                <table>
                    <tr>
                        <td>Listing Number:</td>
                        <td><input type="text" name="listingNum" value="${prop.listingNum}" readonly/></td>
                    </tr>
                    <tr>
                        <td>Date Added:</td>
						<td><fmt:formatDate type="both" dateStyle="long" timeStyle="long" 
								value="${prop.dateAdded}" /></td>
                    </tr>
                    <tr>
                        <td>Street:</td>
                        <td><input type="text" name="street" value="${prop.street}" />
                    </tr>
                    <tr>
                        <td>City:</td>
                        <td><input type="text" name="city" value="${prop.city}" />
                    </tr>
                    <tr>
                        <td>Price:</td>
                        <td><input type="text" name="price" value="${prop.price}" />
                    </tr>
                    <tr>
                        <td>Type:</td>
                        <td><input type="text" name="typeId" value="${prop.typeId.getPType()}" />
                    </tr>
                    <tr>
                        <td>Style:</td>
                        <td><input type="text" name="styleId" value="${prop.styleId.getPStyle()}" />
                    </tr>
                    <tr>
                        <td>Number of bedrooms:</td>
                        <td><input type="number" name="bedrooms" value="${prop.bedrooms}" />
                    </tr>
                    <tr>
                        <td>Number of bathrooms</td>
                        <td><input type="number" name="bathrooms" value="${prop.bathrooms}" />
                    </tr>
                    <tr>
                        <td>Garage Type:</td>
                        <td><input type="text" name="garageId" value="${prop.garageId.getGType()}" />
                    </tr>
                    <tr>
                        <td>Garage Size:</td>
                        <td><input type="text" name="garageSize" value="${prop.garagesize}" />
                    </tr>
					<tr>
                        <td>Lot Size:</td>
                        <td><input type="text" name="lotSize" value="${prop.lotsize}" />
                    </tr>
                    <tr>
                        <td>BER Rating</td>
                        <td><input type="text" name="berRating" value="${prop.berRating}" />
                    </tr>					
                    <tr>
                        <td></td>
                        <td><input type="submit" value="Update" />
                    </tr>                    
                </table>
            </form>
        </div>
        <%@ include file="../assets/jsp/footer.jsp" %>
        </div>
    </body>
</html>
