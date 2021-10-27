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

        <br/>
            <a href="${pageContext.request.contextPath}/app/login">Login</a>
        <br/>
            <a href="${pageContext.request.contextPath}/app/registration">Registration form</a>
        <br/>
            <a href="${pageContext.request.contextPath}/app/students">Students</a>
        <br/>
            <a href="${pageContext.request.contextPath}/app/exception">Exception</a>
        <br/><br/>
            <a href="${pageContext.request.contextPath}/?sessionLocale=en">Session Locale - EN</a>
        <br/>
            <a href="${pageContext.request.contextPath}/?sessionLocale=fr">Session Locale - FR</a>
        <br/>
            <a href="${pageContext.request.contextPath}/?sessionLocale=ru">Session Locale - RU</a>
        <br/>
            <a href="${pageContext.request.contextPath}/?sessionLocale=ua">Session Locale - UA</a>
        <br/><br/>
            <a href="${pageContext.request.contextPath}/?cookieLocale=en">Cookie Locale - EN</a>
        <br/>
            <a href="${pageContext.request.contextPath}/?cookieLocale=fr">Cookie Locale - FR</a>
        <br/>
            <a href="${pageContext.request.contextPath}/?cookieLocale=ru">Cookie Locale - RU</a>
        <br/>
            <a href="${pageContext.request.contextPath}/?cookieLocale=ua">Cookie Locale - UA</a>
        <br>
<jsp:include page="/WEB-INF/fragments/footer.jsp"/>