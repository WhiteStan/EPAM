<%@ page import="by.bsu.example.entity.DepositUrgent" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${lang}" scope="session" />
<fmt:setBundle basename="resources.pagecontent" var="rb" />
<html><head><title>${parserType}</title></head>
<body>
<h1><b>${parserType}</b></h1>
<table style="border-style: solid; border-style: 2px;">
    <tr>
        <td>Name</td>
        <td>Country</td>
        <td>Depositor</td>
        <td>Account Id</td>
        <td>Deposit Amount</td>
        <td>Profitability</td>
        <td>Type</td>
        <td>Check Account</td>
        <td>Deposit Time</td>
    </tr>
    <c:forEach var="elem" items="${ lst }">
        <c:choose>
            <c:when test=" ${ elem.type eq 'urgent' } ">
                <tr>
                    <td><c:out value="${ elem.name }" /></td>
                    <td><c:out value="${ elem.country }" /></td>
                    <td><c:out value="${ elem.depositor }" /></td>
                    <td><c:out value="${ elem.accountId }" /></td>
                    <td><c:out value="${ elem.depositAmount }" /></td>
                    <td><c:out value="${ elem.profitability }" /></td>
                    <td><c:out value="${ elem.type }" /></td>
                    <td>-</td>
                    <td><c:out value="${ elem.depositTime }" /></td>
                </tr>
            </c:when>
            <c:when test="${ elem.type eq 'onDemand' } ">
                <tr>
                    <td><c:out value="${ elem.name }" /></td>
                    <td><c:out value="${ elem.country }" /></td>
                    <td><c:out value="${ elem.depositor }" /></td>
                    <td><c:out value="${ elem.accountId }" /></td>
                    <td><c:out value="${ elem.depositAmount }" /></td>
                    <td><c:out value="${ elem.profitability }" /></td>
                    <td><c:out value="${ elem.type }" /></td>
                    <td><c:out value="${ elem.checkAccount }" /></td>
                    <td>-</td>
                </tr>
            </c:when>
            <c:otherwise>
                <tr>
                    <td><c:out value="${ elem.name }" /></td>
                    <td><c:out value="${ elem.country }" /></td>
                    <td><c:out value="${ elem.depositor }" /></td>
                    <td><c:out value="${ elem.accountId }" /></td>
                    <td><c:out value="${ elem.depositAmount }" /></td>
                    <td><c:out value="${ elem.profitability }" /></td>
                    <td><c:out value="${ elem.type }" /></td>
                    <td>-</td>
                    <td>-</td>
                </tr>
            </c:otherwise>
        </c:choose>
    </c:forEach>
</table>
<a href="controller?command=return"><fmt:message key="label.logout" bundle="${ rb }" /></a>
</body></html>