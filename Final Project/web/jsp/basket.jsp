<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 10.06.2016
  Time: 14:41
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
<c:if test="${not empty basket}">
    <form name="orderForm" method="POST" action="controller">
        <input type="hidden" name="command" value="makeOrder"/>
        <table style="border-style: solid; border-style: 2px;">
            <tr>
                <th><fmt:message key="label.name" bundle="${ rb }"/></th>
                <th><fmt:message key="label.amount" bundle="${ rb }"/></th>
                <th><fmt:message key="label.price" bundle="${ rb }"/></th>
                <th><fmt:message key="label.recipeIfRequired" bundle="${ rb }"/></th>
            </tr>
            <c:forEach var="elem" items="${ drugs }">
                <tr>
                    <td><c:out value="${ elem.name }"/></td>
                    <td>
                        <input id="${elem.name.trim()}" type="text"
                               name="${elem.name.concat('Amount')}" c
                               onkeyup="calcPrice(this.value, this.id)" pattern="[0-9]*" required>
                    </td>
                    <td value="${ elem.price}" class="priceDrugs"><c:out value="${ elem.price}"/></td>
                    <c:if test="${elem.recipeNeed eq true}">
                        <td>
                            <input type="text" name="${elem.name.concat('RecipeId')}" pattern="[0-9]*" required>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>
        <div class="clearfix">
            <strong style="display: inline-block; float:left;"><fmt:message key="label.totalPrice"
                                                                            bundle="${ rb }"/>: </strong>
            <p style="float:left;" class="totalPrice"></p>
        </div>
        <button type="submit"><fmt:message key="label.makeOrder" bundle="${ rb }"/></button>
    </form>
</c:if>

<c:if test="${empty basket}">
    <p><fmt:message key="label.basketEmpty" bundle="${ rb }"/></p>
</c:if>
${errorRegistrationMessage}
<%@ include file="common/footer.jsp" %>
</body>
</html>
