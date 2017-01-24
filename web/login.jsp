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
            <form action="j_security_check" method="post">
                <table>
                    <tr>
                        <td>Username:</td>
                        <td><input type="text" name="j_username" />
                    </tr>
                    <tr>
                        <td>Password:</td>
                        <td><input type="text" name="j_password" />
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="submit" value="Login" />
                    </tr>                    
                </table>
            </form>
        </div>
        <%@ include file="assets/html/footer.html" %>
        </div>
    </body>
</html>
