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
            <h1><fmt:message key="users.title" /></h1>
        </div>
        <div>
        <div class="container text-center">
            <div>
                <table class="table table-striped table-bordered">
                    <thead class="thead-dark">
                        <tr>
                            <th>
                                <a href="${pageContext.request.contextPath}/app/admin/users?page=1&${sortedUserIdColumn}=user_id">
                                    <fmt:message key="users.id" />
                                </a>
                            </th>
                            <th>
                                <a href="${pageContext.request.contextPath}/app/admin/users?page=1&${sortedUsernameColumn}=username">
                                    <fmt:message key="users.username" />
                                </a>
                            </th>
                            <th>
                                <a href="${pageContext.request.contextPath}/app/admin/users?page=1&${sortedFirstnameColumn}=firstname">
                                    <fmt:message key="users.firstname" />
                                </a>
                            </th>
                            <th>
                                <a href="${pageContext.request.contextPath}/app/admin/users?page=1&${sortedLastnameColumn}=lastname">
                                    <fmt:message key="users.lastname" />
                                </a>
                            </th>
                            <th>
                                <a href="${pageContext.request.contextPath}/app/admin/users?page=1&${sortedEmailColumn}=email">
                                    <fmt:message key="users.email" />
                                </a>
                            </th>
                            <th>
                                <a href="${pageContext.request.contextPath}/app/admin/users?page=1&${sortedEnabledColumn}=enabled">
                                    <fmt:message key="users.status" />
                                </a>
                            </th>
                            <th class="p-3"><span class="nav-link"><fmt:message key="users.status" /></span></th>
                        </tr>
                    </thead>
                    <c:forEach var="user" items="${users}">
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.username}</td>
                            <td>${user.firstname}</td>
                            <td>${user.lastname}</td>
                            <td>${user.email}</td>
                            <c:if test="${!user.enabled}">
                                <td><fmt:message key="users.disabled" /></td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/app/admin/enabled/user?user_id=${user.id}&enabled=true"><fmt:message key="user.enable" /></a>
                                </td>
                            </c:if>
                            <c:if test="${user.enabled}">
                                <td><fmt:message key="users.enabled" /></td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/app/admin/enabled/user?user_id=${user.id}&enabled=false"><fmt:message key="user.disable" /></a>
                                </td>
                            </c:if>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>

        <div class="text-center">
            <c:if test="${numberOfPages > 1}">
                <div id="controls">
                    <span id="previous">
                        <!--For displaying previous link except for the 1st page -->
                        <c:if test="${currentPage != 1}">
                            <td><a href="${pageContext.request.contextPath}/app/admin/users?page=${currentPage - 1}&${activeSortingWay}=${activeSortingType}">Previous</a></td>
                        </c:if>
                    </span>

                    <span id="page-numbers">
                        <!-- For displaying Page numbers. The when condition does not display a link for the current page -->
                        <c:forEach begin="1" end="${numberOfPages}" var="i">
                            <c:choose>
                                <c:when test="${currentPage eq i}">
                                    <span>${i}&nbsp;</span>
                                </c:when>
                                <c:otherwise>
                                    <td><a href="${pageContext.request.contextPath}/app/admin/users?page=${i}&${activeSortingWay}=${activeSortingType}">${i}</a></td>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </span>

                    <span id="next">
                        <!-- For displaying Next link except for the last page. -->
                        <c:if test="${currentPage lt numberOfPages}">
                            <td><a href="${pageContext.request.contextPath}/app/admin/users?page=${currentPage + 1}&${activeSortingWay}=${activeSortingType}">Next</a></td>
                        </c:if>
                    </span>
                </div>
            </c:if>

        </div>
    </div>

<jsp:include page="/WEB-INF/fragments/footer.jsp"/>