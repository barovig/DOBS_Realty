<div class="header">
    <div class="header-left">
        <p><a href="${context}/PropertyController" >This is header</a></p>
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