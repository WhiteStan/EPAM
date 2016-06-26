<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 10.06.2016
  Time: 10:25
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
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <title><fmt:message key="label.title" bundle="${ rb }" /></title>
</head>
<body>
<%@ include file="common/header.jsp"%>
<form name="deleteDrugForm" method="POST" action="controller">
    <input type="hidden" name="command" value="deleteDrug" />
    <div class="login-field">
        <label class = "login-label">
            <fmt:message key="label.name" bundle="${ rb }" />
        </label>
        <input type="text" name="name" value=""/>
    </div>
    <br/>
    <input type="submit" name="command" value="<fmt:message key="label.submit_delete" bundle="${ rb }" />"/>
</form>
${errorDataBase}
<%@ include file="common/footer.jsp" %>
</body></html>