<%@ page import="java.util.*, java.text.*" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false"%>
<%@ page import="java.util.*, java.text.*" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="/"><fmt:message key="pages.periodicals" /></a>
    <div class="collapse navbar-collapse" id="navbarColor01">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item"><a class="nav-link" href="/app/about"><fmt:message key="pages.about" /></a></li>
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
                <a class="nav-link" href="/app/register"><fmt:message key="registration.form.title" /></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/app/login">
                    <span class="glyphicon glyphicon-log-in" aria-hidden="true"></span>&nbsp;<fmt:message key="login.title" />
                </a>
            </li>
        </ul>
    </div>
</nav>