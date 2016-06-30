<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 17.06.2016
  Time: 14:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="resources.pagecontent" var="rb"/>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/drug.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/priceCalc.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <title><fmt:message key="label.title" bundle="${ rb }" /></title>
</head>
<body>
<%@ include file="common/header.jsp" %>
<c:if test="${not empty drugsOrdered}">
    <form name="orderForm" method="POST" action="controller">
        <input type="hidden" name="command" value="checkoutOrder"/>
        <table style="border-style: solid; border-style: 2px;">
            <tr>
                <th><fmt:message key="label.name" bundle="${ rb }"/></th>
                <th><fmt:message key="label.amount" bundle="${ rb }"/></th>
                <th><fmt:message key="label.recipeIfRequired" bundle="${ rb }"/></th>
            </tr>
            <c:forEach var="elem" items="${ drugsOrdered }">
                <tr>
                    <td><c:out value="${ elem.name }"/></td>
                    <td>
                        <c:out value="${ elem.amount }"/>
                    </td>
                    <c:if test="${elem.recipeNeed eq true}">
                        <td>
                            <c:out value="${ elem.recipe }"/>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>
        <div class="clearfix">
            <strong style="display: inline-block"; >Total price: ${ order.totalPrice }</strong>
        </div>
        <button type="submit"><fmt:message key="label.checkoutOrder" bundle="${ rb }"/></button>
    </form>
</c:if>
<c:if test="${empty drugsOrdered}">
    <p>Order is not found</p>
</c:if>
${errorDataBase}
<%@ include file="common/footer.jsp" %>
</body>
</html>
