<%@ page import="java.util.*, java.text.*" %>

<jsp:include page="/WEB-INF/fragments/head.jsp"/>
<jsp:include page="/WEB-INF/fragments/header.jsp"/>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false"%>
<%@ page import="java.util.*, java.text.*" %>

<%@ page isErrorPage="true" %>
<%@ page import="java.util.*, java.text.*" %>

<c:set var="lang" value="${not empty param.lang ? param.lang : not empty language ? language : not empty sessionScope.lang ? sessionScope.lang : not empty cookie['lang'].value ? cookie['lang'].value : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${lang}" />
<fmt:setBundle basename="messages" />

    <div class="container text-center">
        <div>
            <h1>Access denied</h2>
            <br/>
            <h2><fmt:message key="general.accessdenied" /></h2>
        </div>
    </div>

<jsp:include page="/WEB-INF/fragments/footer.jsp"/>