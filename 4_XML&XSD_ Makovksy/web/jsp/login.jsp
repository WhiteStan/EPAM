<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${lang}" scope="session" />
<fmt:setBundle basename="resources.pagecontent" var="rb" />
<html><head><title><fmt:message key="label.title" bundle="${ rb }" /></title></head>
<body>
<form name="loginForm" method="POST" action="controller">
    <input type="hidden" name="command" value="login" />
    <fmt:message key="label.login" bundle="${ rb }" /><br/>
    <input type="text" name="login" value=""/>
    <br/><fmt:message key="label.password" bundle="${ rb }" /><br/>
    <input type="password" name="password" value=""/>
    <br/>
    ${errorLoginPassMessage}
    <br/>
    ${wrongAction}
    <br/>
    ${nullPage}
    <br/>
    <input type="submit" name="command" value="<fmt:message key="label.submit_login" bundle="${ rb }" />"/>
</form>
<form name="langForm" method="POST" action="controller">
    <input type="hidden" name="command" value="locale" />
    <input type="hidden" name="page" value="path.page.index" />
    <br/>
    <input type="submit" name="english" value="<fmt:message key="label.en_lang" bundle="${ rb }" />"/>
    <br/>
    <input type="submit" name="russian" value="<fmt:message key="label.ru_lang" bundle="${ rb }" />"/>
</form>
<hr/>
</body></html>