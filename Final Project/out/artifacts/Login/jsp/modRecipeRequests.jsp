<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 16.06.2016
  Time: 17:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="resources.pagecontent" var="rb"/>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/drug.css">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.10.2.js"></script>
    <script type="text/javascript" src="http://digitalbush.com/wp-content/uploads/2013/01/jquery.maskedinput-1.3.1.min_.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/maskInput.js"></script>
    <title><fmt:message key="label.title" bundle="${ rb }"/></title>
</head>
<body>
<%@ include file="common/header.jsp" %>
<table style="border-style: solid; border-style: 2px;">
    <tr>
        <th><fmt:message key="label.patientName" bundle="${ rb }"/></th>
        <th><fmt:message key="label.drugName" bundle="${ rb }"/></th>
        <th><fmt:message key="label.amount" bundle="${ rb }"/></th>
        <th><fmt:message key="label.term" bundle="${ rb }"/></th>
        <th></th>
    </tr>
    <c:forEach var="elem" items="${ lst }">
        <form name="basketForm" method="POST" action="controller">
            <input type="hidden" name="command" value="confirmRecipeRequest"/>
            <tr>
                <td><c:out value="${ elem.login }"/></td>
                <td><c:out value="${ elem.drugName }"/></td>
                <td><input type="text" name="${Integer.toString(elem.id).concat('Amount')}"
                           value="<c:out value="${ elem.amount }" />" pattern="[0-9]*" required/></td>
                <td><input type="text" name="${Integer.toString(elem.id).concat('Term')}"
                           value="<c:out value="${ elem.term }"/>" class="termInput" required /></td>
                <td>
                    <button type="submit" name="requestRecipe"
                            value="${elem.id}">
                        <fmt:message key="label.confirm" bundle="${ rb }"/>
                    </button>
                </td>
            </tr>
        </form>
    </c:forEach>

</table>
<%@ include file="common/footer.jsp" %>
${errorDataBase}
</body>
</html>