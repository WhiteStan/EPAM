<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${lang}" scope="session" />
<fmt:setBundle basename="resources.pagecontent" var="rb" />
<html><head><title>Welcome</title></head>
<body>
<form name="xmlParser" method="POST" action="controller">
    <input type="hidden" name="command" value="parse" />
    <select name="parser">
        <option value="sax">SAX</option>
        <option value="dom">DOM</option>
        <option value="stax">StAX</option>
    </select>
    <br><br>
    <input type="submit">
</form>
<form name="langForm" method="POST" action="controller">
    <input type="hidden" name="command" value="locale" />
    <input type="hidden" name="page" value="path.page.main" />
    <br/>
    <input type="submit" name="english" value="<fmt:message key="label.en_lang" bundle="${ rb }" />"/>
    <br/>
    <input type="submit" name="russian" value="<fmt:message key="label.ru_lang" bundle="${ rb }" />"/>
</form>
<a href="controller?command=logout"><fmt:message key="label.logout" bundle="${ rb }" /></a>
</body></html>