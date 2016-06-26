<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 04.05.2016
  Time: 9:15
  To change this template use File | Settings | File Templates.
--%>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li>
                    <a href="/medicines">
                        <fmt:message key="label.menuMedicines" bundle="${ rb }" />
                    </a>
                </li>
                <li>
                    <a href="/neworder">
                        <fmt:message key="label.newOrder" bundle="${ rb }" />
                    </a>
                </li>
            </ul>
            <div class="auth_area">
                <a href="controller?command=openPage&page=/jsp/registration.jsp" ><fmt:message key="label.regist" bundle="${ rb }" /></a>
                <br/>
                <a href="controller?command=openPage&page=/jsp/login.jsp" ><fmt:message key="label.submit_login" bundle="${ rb }" /></a>
            </div>
            <div class="lang_area">
                <form name="langForm" method="POST" action="controller">
                    <input type="hidden" name="command" value="locale" />
                    <input type="submit" name="english" value="<fmt:message key="label.en_lang" bundle="${ rb }" />"/>
                    <input type="submit" name="russian" value="<fmt:message key="label.ru_lang" bundle="${ rb }" />"/>
                </form>
            </div>
        </div>

    </div>
</nav>
