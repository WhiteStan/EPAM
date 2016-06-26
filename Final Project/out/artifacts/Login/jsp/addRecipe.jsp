<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 16.06.2016
  Time: 19:49
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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/registration.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.10.2.js"></script>
    <script type="text/javascript" src="http://digitalbush.com/wp-content/uploads/2013/01/jquery.maskedinput-1.3.1.min_.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/maskInput.js"></script>
    <title>Title</title>
</head>
<body>
<%@ include file="common/header.jsp"%>
<form name="registationForm" method="POST" action="controller">
    <input type="hidden" name="command" value="addRecipe" />
    <div class="regist-field">
        <label class="regist-label">
            <fmt:message key="label.drugName" bundle="${ rb }" />
        </label>
        <input type="text" name="drugName" class="regist-input"  required/>
    </div><br/>
    <div class="regist-field">
        <label class="regist-label">
            <fmt:message key="label.login" bundle="${ rb }" />
        </label>
        <input type="text" name="login" class="regist-input"  required/>
    </div><br/>
    <div class="regist-field">
        <label class="regist-label">
            <fmt:message key="label.term" bundle="${ rb }" />
        </label>
        <input type="text" name="term" class="regist-input"  class="termInput" required/>
    </div><br/>
    <div class="regist-field">
        <label class="regist-label">
            <fmt:message key="label.amount" bundle="${ rb }" />
        </label>
        <input type="text" name="amountOfDrug" class="regist-input" pattern="[0-9]*" required/>
    </div><br/>
    <div class="regist-field">
        <label class="regist-label">
            <fmt:message key="label.measurmUnit" bundle="${ rb }" />
        </label>
        <select name="measurmUnit">
            <option value="1"><fmt:message key="label.pills" bundle="${ rb }" /></option>
            <option value="2"><fmt:message key="label.ml" bundle="${ rb }" /></option>
            <option value="3"><fmt:message key="label.mg" bundle="${ rb }" /></option>
        </select>
    </div><br/>
    <input type="submit" name="command" id="regist-submit" value="<fmt:message key="label.addRecipe" bundle="${ rb }" />"/>
    <a href="controller?command=openPage&page=/jsp/main.jsp" ><fmt:message key="label.main" bundle="${ rb }" /></a>
    <br/>
</form>
${errorDataBase}
<%@ include file="common/footer.jsp" %>
</body>
</html>
