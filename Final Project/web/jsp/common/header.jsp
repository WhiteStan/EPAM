<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 04.05.2016
  Time: 9:15
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="collapse navbar-collapse">
            <ctg:write-menu role="${sessionScope.role}"/>
            <div class="auth_area">
                <a href="controller?command=openPage&page=/jsp/registration.jsp">
                    <fmt:message key="label.regist" bundle="${ rb }"/></a>
                <br/>
                <c:if test="${ sessionScope.role == 'GUEST'}">
                    <a href="controller?command=openPage&page=/jsp/login.jsp">
                        <fmt:message key="label.submit_login" bundle="${ rb }"/></a>
                </c:if>
                <c:if test="${ sessionScope.role != 'GUEST'}">
                    <a href="controller?command=logout">
                        <fmt:message key="label.submit_logout" bundle="${ rb }"/></a>
                </c:if>
            </div>
            <div class="lang_area">
                <form name="langForm" method="POST" action="controller">
                    <input type="hidden" name="command" value="locale"/>
                    <input type="submit" name="english" class="headerButton"
                           value="<fmt:message key="label.en_lang" bundle="${ rb }" />"/>
                    <input type="submit" name="russian" class="headerButton"
                           value="<fmt:message key="label.ru_lang" bundle="${ rb }" />"/>
                </form>
            </div>
            <c:if test="${ sessionScope.role == 'GUEST' || sessionScope.role == 'USER'}">
                <div class="lang_area">
                    <form name="searchForm" method="POST" action="controller">
                        <input type="hidden" name="command" value="searchDruggist"/>
                        <input type="text" name="drugName" required/>
                        <input type="submit" class="headerButton" style="margin-right: 30px"
                               value="<fmt:message key="label.search" bundle="${ rb }" />"/>
                    </form>
                </div>
            </c:if>
        </div>
    </div>
</nav>
