<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="header">
    <div class="header-left">
		<c:set var="user" value="${sessionScope.user}" />
		<c:choose>
			<c:when test="${user == null}">
				<c:set var="homeLink" value="${context}/PropertyController"></c:set>
			</c:when>
			<c:otherwise>
				<c:set var="homeLink" value="${context}/AgentController"></c:set>
			</c:otherwise>
		</c:choose>
        <p><a href="${homeLink}" ><img src="${context}/assets/img/site/logo.gif"/>LIT Realty</a></p>
    </div>
    <div class="header-right">
		<div class="user_bar">
		<c:set var="user" value="${sessionScope.user}" />
		<c:choose>
			<c:when test="${user == null}">
            <div class="dropdown">
                <button class="dropbtn" >Favourites <span id="fav-count">${favs.size()}</span></button>
                <div class="dropdown-content">
                    <c:forEach var="f" items="${favs}">
                        <div class="fav-item" id="fav-item-${f.id}">
                            <table class="fav-tbl">
                                <tr>
                                    <td rowspan="2">
                                        <a href="${context}/PropertyController?action=details&id=${f.id}">
                                        <img class="fav-img" src="${context}/assets/img/properties/thumbs/${f.photo}" />
                                        </a>
                                    </td>
                                    <td>${f.city}</td>
                                    <td rowspan="2">
                                        <button class="fav-del-btn" onclick="removeFavourite(${f.id})">
                                            X
                                        </button>
                                    </td>
                                </tr>
                                <tr>
                                    <td><fmt:formatNumber var="price" type="currency" value="${f.price}" maxFractionDigits="0" />
                                        ${price}
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </c:forEach>
                </div>
            </div>
			</c:when>
			<c:otherwise>
				<div>
					<p class="greeting">Hello, ${sessionScope.user.getName()}<p/>
					<p><a href="${context}/AgentController?action=logout">Logout</a></p>
				</div>
			</c:otherwise>
			</c:choose>
			</div>
    </div>
</div>