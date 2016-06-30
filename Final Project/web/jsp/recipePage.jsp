<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 11.06.2016
  Time: 16:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setLocale value="${lang}" scope="session" />
<fmt:setBundle basename="resources.pagecontent" var="rb" />

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
<c:if test="${not empty lst}">
<table class="drugTable" style="border-style: solid; border-style: 2px;">
    <tr>
        <th><fmt:message key="label.drugName" bundle="${ rb }"/></th>
        <th><fmt:message key="label.amountOfDrug" bundle="${ rb }"/></th>
        <th><fmt:message key="label.term" bundle="${ rb }"/></th>
        <th></th>
    </tr>
    <c:forEach var="elem" items="${ lst }">
        <form name="basketForm" method="POST" action="controller">
            <input type="hidden" name="command" value="requestRecipe"/>
            <tr>
                <td><a href="controller?command=openDrugPage&drugName=${ elem.drugName }">
                    <c:out value="${ elem.drugName }"/></a>
                </td>
                <td><c:out value="${ elem.amount }"/></td>
                <td><c:out value="${ elem.term }"/></td>
                <td>
                    <button type="submit" name="requestRecipe"
                            value="${elem.id}">
                        <fmt:message key="label.makeOrder" bundle="${ rb }"/>
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
</c:if>
<c:if test="${empty lst}">
    <p><fmt:message key="label.noRecipes" bundle="${ rb }"/></p>
</c:if>
${errorDataBase}
<%@ include file="common/footer.jsp" %>
</body>

</html>
