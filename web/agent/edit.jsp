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
            <form action="${context}/AgentController?action=edit" method="post" id="edit_form" 
				  enctype="multipart/form-data" multiple="true">
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
                        <td>
							<select name="typeId" form="edit_form">
								<c:forEach var="type" items="${pTypes}">
									<option value="${type.typeId}" ${prop.typeId.typeId == type.typeId ? "selected=selected" : ""}>${type.getPType()}</option>
								</c:forEach>
							</select>
						</td>
                    </tr>
                    <tr>
                        <td>Style:</td>
                        <td>
							<select name="styleId" form="edit_form">
							<c:forEach var="style" items="${styles}">
								<option value="${style.styleId}" ${prop.styleId.styleId == style.styleId ? "selected=selected" : ""}>${style.getPStyle()}</option>
							</c:forEach>
							</select>
						</td>
                    </tr>
                    <tr>
                        <td>Number of bedrooms:</td>
                        <td><input type="number" name="bedrooms" value="${prop.bedrooms}" />
                    </tr>
                    <tr>
                        <td>Number of bathrooms</td>
                        <td><input type="text" name="bathrooms" value="${prop.bathrooms}" />
                    </tr>
                    <tr>
                        <td>Garage Type:</td>
                        <td>
							<select name="garageId" form="edit_form">
							<c:forEach var="garage" items="${garages}">
								<option value="${garage.garageId}" ${prop.garageId.garageId == garage.garageId ? "selected=selected" : ""}>${garage.getGType()}</option>
							</c:forEach>
							</select>
						</td>
                    </tr>
                    <tr>
                        <td>Garage Size:</td>
                        <td><input type="text" name="garageSize" value="${prop.garagesize}" />
                    </tr>
                    <tr>
                        <td>Squarefeet:</td>
                        <td><input type="text" name="squarefeet" value="${prop.squarefeet}" />
                    </tr>
                    <tr>
                        <td>Description:</td>
                        <td><textarea rows="10" cols="100" name="description">${prop.description}</textarea>
                    </tr>						
					<tr>
                        <td>Lot Size:</td>
                        <td><input type="text" name="lotSize" value="${prop.lotsize}" />
                    </tr>
                    <tr>
                        <td>BER Rating</td>
                        <td>
							<select name="berRating" form="edit_form">
							<c:forEach var="ber" items="${bers}">
								<option value="${ber}" ${prop.berRating == ber ? "selected=selected" : ""}>${ber}</option>
							</c:forEach>
							</select>
						</td>
                    </tr>					
					<tr>
						<td>Remove images:</td>
						<td>
						<c:forEach var="img" items="${img_files}">
							<img 
								class="dd-thumb-pics" 
								src="${context}/assets/img/properties/large/${img_folder}/${img.getName()}"
								 />
							<input type="checkbox" name="delImgs" value="${img.getName()}" />
						</c:forEach>
						</td>
					</tr>
					<tr>
						<td>
								<input type="text" name="description" value="Upload more (jpeg) photos:"/>
								<input type="file" name="file" accept=".jpg,.JPG,image/jpeg" multiple="multiple"/>
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
