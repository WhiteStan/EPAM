<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 16.06.2016
  Time: 20:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${lang}" scope="session" />
<fmt:setBundle basename="resources.pagecontent" var="rb" />
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
<%@ include file="common/header.jsp"%>
<div>
    <form name="searchForm" method="POST" action="controller">
        <input type="hidden" name="command" value="searchOrder"/>
        <input type="text" name="orderId" pattern="[0-9]*" required/>
        <input type="submit" value="<fmt:message key="label.search" bundle="${ rb }" />" />
    </form>
</div>
${errorDataBase}
<%@ include file="common/footer.jsp" %>
</body>
</html>
