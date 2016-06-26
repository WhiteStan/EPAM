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
<form name="loginForm" method="POST" action="controller">
    <input type="hidden" name="command" value="login" />
    <div class="login-field">
        <label class = "login-label">
            <fmt:message key="label.login" bundle="${ rb }" />
        </label>
        <input type="text" name="login" value=""/>
    </div>
    <br/>
    <div class ="login-field">
        <label class = "login-label">
            <fmt:message key="label.password" bundle="${ rb }" />
        </label>
        <input type="password" name="password" value=""/>
    </div>
    <br/>
    <input type="submit" name="command" value="<fmt:message key="label.submit_login" bundle="${ rb }" />"/>
</form>
${errorLoginPassMessage}
<%@ include file="common/footer.jsp" %>
</body></html>