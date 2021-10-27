<%@ page import="java.util.*, java.text.*" %>

<jsp:include page="/WEB-INF/fragments/head.jsp"/>
<jsp:include page="/WEB-INF/fragments/header.jsp"/>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false"%>
<%@ page import="java.util.*, java.text.*" %>

<c:set var="lang" value="${not empty param.lang ? param.lang : not empty language ? language : not empty sessionScope.lang ? sessionScope.lang : not empty cookie['lang'].value ? cookie['lang'].value : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${lang}" />
<fmt:setBundle basename="messages" />

    <h2>
        <fmt:message key="menu.greeting" /> <br/>
    </h2>

    <h1>Login</h1><br/>
    <form method="get" action="${pageContext.request.contextPath}/app/login">
        <input type="text" name="name"><br/>
        <input type="password" name="pass"><br/><br/>
        <input class="button" type="submit" value="Login">
    </form>
    <br/>
    <a href="${pageContext.request.contextPath}/app/logout">Logout</a>

<jsp:include page="/WEB-INF/fragments/footer.jsp"/>