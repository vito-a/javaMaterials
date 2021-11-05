<%@ page import="java.util.*, java.text.*" %>

<jsp:include page="/WEB-INF/fragments/head.jsp"/>
<jsp:include page="/WEB-INF/fragments/header.jsp"/>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="WEB-INF/tags.tld" prefix="m" %>
<%@ taglib prefix="message" tagdir="/WEB-INF/tags" %>
<%@ page isELIgnored="false"%>
<%@ page import="java.util.*, java.text.*" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<div style="float:right"><m:today/></div>
<message:messageError bgcolor="#FF0000" title="errorTable"/>

    <div class="container text-center">
        <div>
            <h1>
                <fmt:message key="menu.greeting" />
            </h1>
        </div>
    </div>

<jsp:include page="/WEB-INF/fragments/footer.jsp"/>