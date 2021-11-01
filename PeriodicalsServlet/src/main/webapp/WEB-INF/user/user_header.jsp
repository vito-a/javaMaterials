<%@ page import="java.util.*, java.text.*" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false"%>
<%@ page import="java.util.*, java.text.*" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <span class="navbar-brand"><fmt:message key="pages.periodicals" /></span>
    <div class="collapse navbar-collapse" id="navbarColor01">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item"><a class="nav-link" href="/app/user/periodicals"><fmt:message key="pages.periodicals" /></a></li>
            <li class="nav-item"><a class="nav-link" href="/app/user/my-subscriptions"><fmt:message key="subscriptions.my.title" /></a></li>
            <li class="nav-item"><a class="nav-link" href="/app/user/categories"><fmt:message key="pages.categories" /></a></li>
            <li class="nav-item"><a class="nav-link" href="/app/user/replenish-account"><fmt:message key="user.replenish.account" /></a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li>
                <div class="mr-2">
                    <form class="form-inline" action="${pageContext.request.contextPath}/app/user/search/periodicals">
                        <input class="form-control mr-sm-2" type="search" id="keyword" name="keyword" placeholder="<fmt:message key="actions.search" />" aria-label="<fmt:message key="actions.search" />">
                        <button class="btn btn-primary my-2 my-sm-0" type="submit"><fmt:message key="actions.search" /></button>
                    </form>
                </div>
            </li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li>
                <div class="dropdown">
                    <button class="btn btn-secondary" type="button" onclick="$('#language-dropdown').toggle();">
                        <fmt:message key="lang.locale" />
                    </button>
                    <div class="dropdown-menu" id="language-dropdown">
                        <button class="dropdown-item" type="button">
                            <a href="${pageContext.request.contextPath}?lang=en"><fmt:message key="lang.EN" /></a>
                        </button>
                        <button class="dropdown-item" type="button">
                            <a href="${pageContext.request.contextPath}?lang=fr"><fmt:message key="lang.FR" /></a>
                        </button>
                        <button class="dropdown-item" type="button">
                            <a href="${pageContext.request.contextPath}?lang=ru"><fmt:message key="lang.RU" /></a>
                        </button>
                        <button class="dropdown-item" type="button">
                            <a href="${pageContext.request.contextPath}?lang=ua"><fmt:message key="lang.UA" /></a>
                        </button>
                    </div>
                </div>
            </li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li class="nav-item">
                <div>
                    <div class="nav-link float-left ml-2 text-white">
                        <fmt:message key="menu.greeting" />
                    </div>
                    <a class="nav-link float-left" href="/app/logout" onclick="$('#logout-form').submit();"><fmt:message key="user.logout" /></a>
                </div>
                <form class="d-none invisible" id="logout-form" method="post" action="#" action="/logout">
                    <input class="nav-link" type="submit" value="<fmt:message key="user.logout" />" />
                </form>
            </li>
        </ul>
    </div>
</nav>