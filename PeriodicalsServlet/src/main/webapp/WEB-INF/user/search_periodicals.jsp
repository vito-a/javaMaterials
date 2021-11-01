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
            <h1><fmt:message key="periodicals.title" /></h1>
        </div>
        <div>
        <div class="container text-center">
            <div>
                <table class="table table-striped table-bordered">
                    <thead class="thead-dark">
                        <tr>
                            <th><fmt:message key="periodicals.id" /></th>
                            <th><fmt:message key="periodicals.name" /></th>
                            <th><fmt:message key="periodicals.description" /></th>
                            <th><fmt:message key="periodicals.category" /></a></th>
                            <th><fmt:message key="periodicals.price" /></a></th>
                        </tr>
                    </thead>
                    <c:forEach var="periodical" items="${periodicals}">
                        <tr>
                            <td>${periodical.id}</td>
                            <td>${periodical.name}</td>
                            <td>${periodical.description}</td>
                            <td>${periodical.catId}</td>
                            <td>${periodical.price}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>

<jsp:include page="/WEB-INF/fragments/footer.jsp"/>