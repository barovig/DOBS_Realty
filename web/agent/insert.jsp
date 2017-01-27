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
            <form action="${context}/AgentController?action=add" method="post" id="add_form" 
				  enctype="multipart/form-data" multiple="true">
                <table>
                    <tr>
                        <td>Listing Number:</td>
                        <td><input type="text" name="listingNum" required/></td>
                    </tr>
                    <tr>
                        <td>Date Added:</td>
						<td><fmt:formatDate type="both" dateStyle="long" timeStyle="long" 
								value="${date}" /></td>
                    </tr>
                    <tr>
                        <td>Street:</td>
                        <td><input type="text" name="street" required/>
                    </tr>
                    <tr>
                        <td>City:</td>
                        <td><input type="text" name="city" required/>
                    </tr>
                    <tr>
                        <td>Price:</td>
                        <td><input type="text" name="price"  required/>
                    </tr>
                    <tr>
                        <td>Type:</td>
                        <td>
							<select name="typeId" form="add_form">
								<c:forEach var="type" items="${pTypes}">
									<option value="${type.typeId}">${type.getPType()}</option>
								</c:forEach>
							</select>
						</td>
                    </tr>
                    <tr>
                        <td>Style:</td>
                        <td>
							<select name="styleId" form="add_form">
							<c:forEach var="style" items="${styles}">
								<option value="${style.styleId}" >${style.getPStyle()}</option>
							</c:forEach>
							</select>
						</td>
                    </tr>
                    <tr>
                        <td>Number of bedrooms:</td>
                        <td><input type="number" name="bedrooms"  required/>
                    </tr>
                    <tr>
                        <td>Number of bathrooms</td>
                        <td><input type="text" name="bathrooms"  required/>
                    </tr>
                    <tr>
                        <td>Garage Type:</td>
                        <td>
							<select name="garageId" form="add_form">
							<c:forEach var="garage" items="${garages}">
								<option value="${garage.garageId}">${garage.getGType()}</option>
							</c:forEach>
							</select>
						</td>
                    </tr>
                    <tr>
                        <td>Garage Size:</td>
                        <td><input type="text" name="garageSize"  required/>
                    </tr>
                    <tr>
                        <td>Square Feet:</td>
                        <td><input type="text" name="squarefeet"  required/>
                    </tr>
                    <tr>
                        <td>Description:</td>
                        <td><textarea rows="10" cols="100" name="description" required></textarea>
                    </tr>						
					<tr>
                        <td>Lot Size:</td>
                        <td><input type="text" name="lotSize"  required/>
                    </tr>
                    <tr>
                        <td>BER Rating</td>
                        <td>
							<select name="berRating" form="add_form">
							<c:forEach var="ber" items="${bers}">
								<option value="${ber}" >${ber}</option>
							</c:forEach>
							</select>
						</td>
                    </tr>					
					<tr>
						<td>
								<input type="text" name="uplDesc" value="Upload more (jpeg) photos:"/>
								<input type="file" name="file" accept=".jpg,.JPG,image/jpeg" multiple="multiple" required/>
							</form>
						</td>
                        <td><input type="submit" value="Update" />
                    </tr>
                </table>
            </form>
        </div>
        <%@ include file="../assets/jsp/footer.jsp" %>
        </div>
    </body>
</html>
