<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 11.06.2016
  Time: 18:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${lang}" scope="session" />
<fmt:setBundle basename="resources.pagecontent" var="rb" />
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/drug.css">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <title><fmt:message key="label.title" bundle="${ rb }" /></title>
</head>
<body>
<%@ include file="common/header.jsp"%>
<table style="border-style: solid; border-style: 2px;">
    <tr>
        <th></th>
        <th><fmt:message key="label.patientName" bundle="${ rb }" /></th>
        <th><fmt:message key="label.drugName" bundle="${ rb }" /></th>
        <th><fmt:message key="label.amount" bundle="${ rb }" /></th>
    </tr>
    <form name="basketForm" method="POST" action="controller">
        <input type="hidden" name="command" value="confirmRecipes" />
    <c:forEach var="elem" items="${ patients }">
            <tr>
                <td>
                    <input type="checkbox" name="${elem.id}" value="true" <c:if test="${elem.valid eq true}">checked</c:if>/>
                </td>
                <td><c:out value="${ elem.login }" /></td>
                <td><c:out value="${ elem.drugName }" /></td>
                <td><c:out value="${ elem.amount }" /></td>
            </tr>
    </c:forEach>
    <button type="submit">Press me!</button>
    </form>
</table>
<%@ include file="common/footer.jsp" %>
${errorDataBase}
</body></html>