<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 10.06.2016
  Time: 9:22
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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/registration.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <title>Title</title>
</head>
<body>
<%@ include file="common/header.jsp"%>
<form name="registationForm" method="POST" action="controller">
    <input type="hidden" name="command" value="addDrug" />
    <div class="regist-field">
        <label class="regist-label">
            <fmt:message key="label.drugName" bundle="${ rb }" />
        </label>
        <input type="text" name="name" class="regist-input" value="" required/>
    </div><br/>
    <div class="regist-field">
        <label class="regist-label">
            <fmt:message key="label.internName" bundle="${ rb }" />
        </label>
        <input type="text" id="password" class="regist-input" name="internName" value=""/>
    </div><br/>
    <div class="regist-field">
        <label class="regist-label">
            <fmt:message key="label.price" bundle="${ rb }" />
        </label>
        <input type="text" id="password_confirm" class="regist-input" name="price" pattern="[0-9]*" required/>
    </div><br/>
    <div class="regist-field">
        <label class="regist-label">
            <fmt:message key="label.inStock" bundle="${ rb }" />
        </label>
        <input type="text" name="inStock" pattern="[0-9]*" class="regist-input" required/>
    </div><br/>
    <div class="regist-field">
        <label class="regist-label">
            <fmt:message key="label.description" bundle="${ rb }" />
        </label>
        <input type="text" class="regist-input" name="description" />
    </div><br/>
    <div class="regist-field">
        <label class="regist-label">
            <fmt:message key="label.recipeNeed" bundle="${ rb }" />
        </label>
        <span>
            <input type="radio" name="recipeNeed" value="Male" checked />
            <b><fmt:message key="label.yes" bundle="${ rb }" /></b>
        </span>
        <span>
            <input type="radio" name="recipeNeed" value="Female" />
            <b><fmt:message key="label.no" bundle="${ rb }" /></b>
        </span>
    </div><br/>
    <br/>
    <div class="regist-field">
        <label class="regist-label">
            <fmt:message key="label.measurmUnit" bundle="${ rb }" />
        </label>
        <select name="measurmUnit">
            <option value="1"><fmt:message key="label.pills" bundle="${ rb }" /></option>
            <option value="2"><fmt:message key="label.ml" bundle="${ rb }" /></option>
            <option value="3"><fmt:message key="label.mg" bundle="${ rb }" /></option>
        </select>
    </div><br/><br/>
    <input type="submit" name="command" id="regist-submit" value="<fmt:message key="label.addDrug" bundle="${ rb }" />"/>
    <br/>
</form>
${errorDataBase}
<%@ include file="common/footer.jsp" %>
</body>
</html>
