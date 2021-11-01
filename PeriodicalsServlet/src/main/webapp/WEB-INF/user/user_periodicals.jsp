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
                            <th>
                                <a href="${pageContext.request.contextPath}/app/admin/periodicals?page=1&${sortedPeriodicalIdColumn}=periodical_id">
                                    <fmt:message key="periodicals.id" />
                                </a>
                            </th>
                            <th>
                                <a href="${pageContext.request.contextPath}/app/admin/periodicals?page=1&${sortedNameColumn}=name">
                                    <fmt:message key="periodicals.name" />
                                </a>
                            </th>
                            <th>
                                <a href="${pageContext.request.contextPath}/app/admin/periodicals?page=1&${sortedDescriptionColumn}=description">
                                    <fmt:message key="periodicals.description" />
                                </a>
                            </th>
                            <th>
                                <a href="${pageContext.request.contextPath}/app/admin/periodicals?page=1&${sortedCategoryColumn}=cat_id">
                                    <fmt:message key="periodicals.category" />
                                </a>
                            </th>
                            <th>
                                <a href="${pageContext.request.contextPath}/app/admin/periodicals?page=1&${sortedPriceColumn}=price">
                                    <fmt:message key="periodicals.price" />
                                </a>
                            </th>
                            <th class="p-3"><span class="nav-link"><fmt:message key="actions.actions" /></span></th>
                        </tr>
                    </thead>
                    <c:forEach var="periodical" items="${periodicals}">
                        <tr>
                            <td>${periodical.id}</td>
                            <td>${periodical.name}</td>
                            <td>${periodical.description}</td>
                            <td>${periodical.catId}</td>
                            <td>${periodical.price}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/app/user/subscribe/periodical?periodical_id=${periodical.id}"><fmt:message key="actions.subscribe" /></a>
                            </td>
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
                            <td><a href="${pageContext.request.contextPath}/app/admin/periodicals?page=${currentPage - 1}&${activeSortingWay}=${activeSortingType}">Previous</a></td>
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
                                    <td><a href="${pageContext.request.contextPath}/app/admin/periodicals?page=${i}&${activeSortingWay}=${activeSortingType}">${i}</a></td>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </span>

                    <span id="next">
                        <!-- For displaying Next link except for the last page. -->
                        <c:if test="${currentPage lt numberOfPages}">
                            <td><a href="${pageContext.request.contextPath}/app/admin/periodicals?page=${currentPage + 1}&${activeSortingWay}=${activeSortingType}">Next</a></td>
                        </c:if>
                    </span>
                </div>
            </c:if>

        </div>
    </div>

<jsp:include page="/WEB-INF/fragments/footer.jsp"/>