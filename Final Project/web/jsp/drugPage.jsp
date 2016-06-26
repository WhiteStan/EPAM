<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 12.06.2016
  Time: 13:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="resources.pagecontent" var="rb"/>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <title>Title</title>
</head>
<body>
<%@ include file="common/header.jsp" %>
<c:if test="${not empty drug}">
    <div class="drugDescription">
        <h2>${drug.name}</h2>

        <p><fmt:message key="label.internName" bundle="${ rb }"/>: ${drug.internationalName}</p>

        <p><fmt:message key="label.price" bundle="${ rb }"/>: ${drug.price}</p>

        <p><fmt:message key="label.inStock" bundle="${ rb }"/>: ${drug.inStock} ${drug.measurementUnit}</p>

        <p><fmt:message key="label.recipeNeed" bundle="${ rb }"/>: ${drug.recipeNeed}</p>

        <p><fmt:message key="label.description" bundle="${ rb }"/>: ${drug.description}</p>

        <form name="basketForm" method="POST" action="controller">
            <input type="hidden" name="command" value="addToBasket"/>
            <button type="submit" name="drugToBasket"
                    value="${drug.name}"><fmt:message key="label.addToBasket" bundle="${ rb }"/>
            </button>
        </form>
    </div>
</c:if>
<c:if test="${empty drug}">
    <p><fmt:message key="label.nothingIsFound" bundle="${ rb }"/></p>
</c:if>
${errorDataBase}
<%@ include file="common/footer.jsp" %>
</body>
</html>
