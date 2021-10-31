<%@ page import="java.util.*, java.text.*" %>

<jsp:include page="/WEB-INF/fragments/head.jsp"/>
<jsp:include page="/WEB-INF/user/user_header.jsp"/>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false"%>
<%@ page import="java.util.*, java.text.*" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

    <div class="container text-center">
        <div>
            <h1>
                <fmt:message key="menu.profile.balance" /> <br/>
            </h1>

            <form action="${pageContext.request.contextPath}/app/user/replenish-account" method="post">
                <input type="hidden" name="command" value="replenish_account">
                <input type="hidden" name="userId" value="${userId}">
                <div class="mb-3">
                    <label for="inputBalance" class="form-label"><fmt:message key="menu.profile.balance"/></label>
                    <input type="text" name="balance" class="form-control" id="inputBalance" required>
                </div>
                <div class="mb-3">
                    <input type="submit" class="btn btn-success" value="<fmt:message key="actions.submit"/>">
                </div>
            </form>
        </div>
    </div>

<jsp:include page="/WEB-INF/fragments/footer.jsp"/>