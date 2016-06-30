<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="resources.pagecontent" var="rb"/>
<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 05.05.2016
  Time: 6:07
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/drug.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
    <title><fmt:message key="label.title" bundle="${ rb }" /></title>
</head>
<body>
<%@ include file="common/header.jsp" %>
<table class="drugTable" style="border-style: solid; border-style: 2px;">
    <tr>
        <th><fmt:message key="label.name" bundle="${ rb }"/></th>
        <th><fmt:message key="label.price" bundle="${ rb }"/></th>
        <th></th>
    </tr>
    <c:forEach var="elem" items="${ drugList }">
        <form name="basketForm" method="POST" action="controller">
            <input type="hidden" name="command" value="addToBasket"/>
            <tr>
                <td><a href="controller?command=openDrugPage&drugName=${ elem.name }">
                    <c:out value="${ elem.name }"/></a>
                </td>
                <td><c:out value="${ elem.price }"/></td>
                <td>
                    <button type="submit" name="drugToBasket"
                            value="${elem.name}">
                        <fmt:message key="label.addToBasket" bundle="${ rb }"/>
                    </button>
                </td>
            </tr>
        </form>
    </c:forEach>
</table>
<c:set var="pages" value="${fn:length(lst) / 10}"/>
<nav class="pageNumsNav">
    <c:forEach begin="0" end="${pages}" var="curPage">
        <span>
            <a class="pageNumsHref" href="controller?command=druglist&page=/jsp/registration.jsp&pageNum=${curPage + 1}">
                    ${curPage + 1}</a>
        </span>
    </c:forEach>
</nav>
${errorDataBase}
<%@ include file="common/footer.jsp" %>
</body>
</html>
