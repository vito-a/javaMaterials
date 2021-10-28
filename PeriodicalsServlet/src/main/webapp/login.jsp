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
        <fmt:message key="login.title" /> <br/>
    </h2>

    <form action="${pageContext.request.contextPath}/app/login" method="post">
        <input type="hidden" name="command" value="login">
        <div class="mb-3">
            <label for="inputLogin" class="form-label"><fmt:message key="login.username"/></label>
            <input type="text" name="name" class="form-control" id="inputLogin"required>
        </div>
        <div class="mb-3">
            <label for="inputPassword" class="form-label"><fmt:message key="login.password"/></label>
            <input type="password" name="pass" class="form-control" id="inputPassword" required>
        </div>
        <div class="mb-3">
            <input type="submit" class="btn btn-success" value="<fmt:message key="login.form.signin"/>">
        </div>
    </form>

<jsp:include page="/WEB-INF/fragments/footer.jsp"/>