<%@ page import="java.util.*, java.text.*" %>

<jsp:include page="/WEB-INF/fragments/head.jsp"/>
<jsp:include page="/WEB-INF/admin/admin_header.jsp"/>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false"%>
<%@ page import="java.util.*, java.text.*" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

    <div class="container text-center">
        <div>
            <h1><fmt:message key="subscriptions.title" /></h1>
        </div>
        <div>
            <table class="table table-striped table-bordered">
                <thead class="thead-dark">
                    <tr>
                        <th><fmt:message key="subscriptions.id" /></th>
                        <th><fmt:message key="subscriptions.user.id" /></th>
                        <th><fmt:message key="subscriptions.periodical.id" /></th>
                        <th><fmt:message key="subscriptions.startdate" /></th>
                        <th><fmt:message key="subscriptions.enddate" /></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="subscription" items="${subscriptions}">
                        <tr>
                            <td>${subscription.subId}</td>
                            <td>${subscription.userId}</td>
                            <td>${subscription.periodicalId}</td>
                            <td>${subscription.startDate}</td>
                            <td>${subscription.endDate}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

<jsp:include page="/WEB-INF/fragments/footer.jsp"/>
