<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 20.06.2016
  Time: 14:29
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
    <title><fmt:message key="label.title" bundle="${ rb }" /></title>
</head>
<body>
<%@ include file="common/header.jsp"%>
<form name="registationForm" method="POST" action="controller">
    <input type="hidden" name="command" value="addProduct" />
    <div class="regist-field">
        <label class="regist-label">
            <fmt:message key="label.drugName" bundle="${ rb }" />
        </label>
        <input type="text" name="name" class="regist-input" value="" required/>
    </div><br/>
    <div class="regist-field">
        <label class="regist-label">
            <fmt:message key="label.amount" bundle="${ rb }" />
        </label>
        <input type="text" name="amount" pattern="[0-9]*" class="regist-input" required/>
    </div><br/>
    <input type="submit" name="command" id="regist-submit" value="<fmt:message key="label.addDrug" bundle="${ rb }" />"/>
    <br/>
</form>
${errorDataBase}
<%@ include file="common/footer.jsp" %>
</body>
</html>
