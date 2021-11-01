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
            <div id="div1">
                <table class="table table-striped table-bordered">
                    <tr>
                        <td><fmt:message key="user.role" /></td>
                        <c:forEach var="role" items="${rolesList}">
                           <td><a href="${pageContext.request.contextPath}/app/admin/users?page=1&role=${role.roleId}">${role.name}</a></td>
                        </c:forEach>
                    </tr>
                </table>
            </div>
            <div id="div2">
                <table class="table table-striped table-bordered">
                    <thead class="thead-dark">
                        <tr>
                            <th>
                                <a href="${pageContext.request.contextPath}/app/admin/users?page=1&role=${activeRoleId}&${sortedUserIdColumn}=user_id">
                                    <fmt:message key="users.id" />
                                </a>
                            </th>
                            <th>
                                <a href="${pageContext.request.contextPath}/app/admin/users?page=1&role=${activeRoleId}&${sortedUsernameColumn}=username">
                                    <fmt:message key="users.username" />
                                </a>
                            </th>
                            <th>
                                <a href="${pageContext.request.contextPath}/app/admin/users?page=1&role=${activeRoleId}&${sortedFirstnameColumn}=firstname">
                                    <fmt:message key="users.firstname" />
                                </a>
                            </th>
                            <th>
                                <a href="${pageContext.request.contextPath}/app/admin/users?page=1&role=${activeRoleId}&${sortedLastnameColumn}=lastname">
                                    <fmt:message key="users.lastname" />
                                </a>
                            </th>
                            <th>
                                <a href="${pageContext.request.contextPath}/app/admin/users?page=1&role=${activeRoleId}&${sortedEmailColumn}=email">
                                    <fmt:message key="users.email" />
                                </a>
                            </th>
                            <th>
                                <a href="${pageContext.request.contextPath}/app/admin/users?page=1&role=${activeRoleId}&${sortedEnabledColumn}=enabled">
                                    <fmt:message key="users.status" />
                                </a>
                            </th>
                        </tr>
                    </thead>
                    <c:forEach var="user" items="${users}">
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.username}</td>
                            <td>${user.firstname}</td>
                            <td>${user.lastname}</td>
                            <td>${user.email}</td>
                            <td>${user.enabled}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>

        <div>
            <c:if test="${numberOfPages > 1}">
                <div style="float: left;">
                    <!--For displaying previous link except for the 1st page -->
                    <c:if test="${currentPage != 1}">
                        <td><a href="${pageContext.request.contextPath}/app/admin/users?page=${currentPage - 1}&role=${activeRoleId}&${activeSortingWay}=${activeSortingType}">Previous</a></td>
                    </c:if>
                </div>

                <div id="div3" style="float: left;">
                    <!-- For displaying Page numbers. The when condition does not display a link for the current page -->
                    <table border="1" cellpadding="3" cellspacing="0">
                        <tr>
                            <c:forEach begin="1" end="${numberOfPages}" var="i">
                                <c:choose>
                                    <c:when test="${currentPage eq i}">
                                        <td>${i}</td>
                                    </c:when>
                                    <c:otherwise>
                                        <td><a href="${pageContext.request.contextPath}/app/admin/users?page=${i}&role=${activeRoleId}&${activeSortingWay}=${activeSortingType}">${i}</a></td>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </tr>
                    </table>
                </div>

                <div style="float: left;">
                    <!-- For displaying Next link except for the last page. -->
                    <c:if test="${currentPage lt numberOfPages}">
                        <td><a href="${pageContext.request.contextPath}/app/admin/users?page=${currentPage + 1}&role=${activeRoleId}&${activeSortingWay}=${activeSortingType}">Next</a></td>
                    </c:if>
                </div>
            </c:if>

        </div>
    </div>

<jsp:include page="/WEB-INF/fragments/footer.jsp"/>