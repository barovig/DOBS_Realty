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
            <form action="${context}/AgentController?action=edit_agent" method="post" id="agent_form" 
				  enctype="multipart/form-data" multiple="true">
                <table>
                    <tr>
                        <td>Name:</td>
                        <td><input type="text" name="name" value="${agent.name}" required/></td>
                    </tr>
                    <tr>
                        <td>Phone:</td>
						<td><input type="text" name="phone" value="${agent.phone}" required/></td>
                    </tr>
                    <tr>
                        <td>Fax:</td>
                        <td><input type="text" name="fax" value="${agent.fax}" required/>
                    </tr>
                    <tr>
                        <td>Email:</td>
                        <td><input type="text" name="email" value="${agent.email}" required/>
                    </tr>
					<tr>
						<td></td>
                        <td><input type="submit" value="Update" />
                    </tr>
                </table>
            </form>
        </div>
		<div class="dashboard">
			<a href="AgentController?action=agent_home">Back</a>
		</div>
        <%@ include file="../assets/jsp/footer.jsp" %>
        </div>
    </body>
</html>
