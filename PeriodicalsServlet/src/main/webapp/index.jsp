<%@ page import="java.util.*, java.text.*" %>

<jsp:include page="/WEB-INF/fragments/head.jsp"/>
<jsp:include page="/WEB-INF/fragments/header.jsp"/>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false"%>
<%@ page import="java.util.*, java.text.*" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

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

<jsp:include page="/WEB-INF/fragments/footer.jsp"/>