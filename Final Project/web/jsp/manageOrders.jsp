<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 14.06.2016
  Time: 16:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${lang}" scope="session" />
<fmt:setBundle basename="resources.pagecontent" var="rb" />
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/drug.css">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.10.2.js"></script>
    <script type="text/javascript" src="http://digitalbush.com/wp-content/uploads/2013/01/jquery.maskedinput-1.3.1.min_.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/maskInput.js"></script>
    <title><fmt:message key="label.title" bundle="${ rb }" /></title>
</head>
<body>
<%@ include file="common/header.jsp"%>
<table style="border-style: solid; border-style: 2px;">
    <tr>
        <th></th>
        <th><fmt:message key="label.totalPrice" bundle="${ rb }" /></th>
        <th><fmt:message key="label.login" bundle="${ rb }" /></th>
        <th><fmt:message key="label.status" bundle="${ rb }" /></th>
        <th><fmt:message key="label.timeOfDelivery" bundle="${ rb }" /></th>
    </tr>
    <form name="basketForm" method="POST" action="controller">
        <input type="hidden" name="command" value="confirmOrders" />
        <c:forEach var="elem" items="${ orders }">
            <tr>
                <td>
                    <input type="checkbox" name="${Integer.toString(elem.id).concat('valid')}" value="true"
                    <c:if test="${elem.valid eq true}"> checked </c:if>
                </td>
                <td><c:out value="${ elem.totalPrice }" /></td>
                <td><c:out value="${ elem.login }" /></td>
                <td><input type="text" name="${Integer.toString(elem.id).concat('Status')}"
                           value="<c:out value="${ elem.status }" />" /> </td>
                <td> <input type="text" name="${Integer.toString(elem.id).concat('TimeOfDelivery')}"
                           value="<c:out value="${ elem.timeOfDelivery }"/>" class="termInput" required/> </td>
            </tr>
        </c:forEach>

        <button type="submit" >Press me!</button>
    </form>
</table>
<%@ include file="common/footer.jsp" %>
${errorDataBase}
</body></html>