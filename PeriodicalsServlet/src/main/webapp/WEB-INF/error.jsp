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

    <h2>
       Error Page<br/>
        <i>Error <%= exception %></i>
    </h2>
    <a href="${pageContext.request.contextPath}/index.jsp">Home</a>

<jsp:include page="/WEB-INF/fragments/footer.jsp"/>