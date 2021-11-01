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
            <h1><fmt:message key="categories.title" /></h1>
        </div>
        <div>
            <table class="table table-striped table-bordered">
                <thead class="thead-dark">
                    <tr>
                        <th><fmt:message key="categories.id" /></th>
                        <th><fmt:message key="categories.name" /></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="category" items="${categories}">
                        <tr>
                            <td>${category.catId}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/app/user/category/periodicals?category=${category.catId}">
                                    ${category.name}
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

<jsp:include page="/WEB-INF/fragments/footer.jsp"/>
