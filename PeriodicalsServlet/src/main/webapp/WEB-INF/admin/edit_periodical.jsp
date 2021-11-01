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
            <h1>
                <fmt:message key="actions.add" />
            </h1>

            <form action="${pageContext.request.contextPath}/app/admin/edit/periodical" method="post" style="max-width: 800px; margin: 0 auto;">
                <div class="m-3">
                    <input type="hidden" name="periodical_id" value="${periodical_id}">
                    <div class="form-group row">
                        <label class="col-4 col-form-label" for="name"><fmt:message key="periodicals.name"/></label>
                        <div class="col-8">
                            <input id="name" class="form-control" name="name" type="text" th:pattern="${regexp.name}" value="${name}" th:field="*{name}" required>
                        </div>
                        <c:if test="${fields.hasErrors('name')}"><div class="alert alert-warning" th:errors="*{name}"></div></c:if>
                        <c:if test="${nameExists}"><div class="alert alert-warning" th:if="${nameExists}" th:text="${form.alert.name.exists}"></div></c:if>
                    </div>

                    <div class="form-group row">
                        <label class="col-4 col-form-label" for="description"><fmt:message key="periodicals.description"/></label>
                        <div class="col-8">
                            <input id="description" class="form-control" name="description" type="description" value="${description}" th:field="*{description}" required>
                        </div>
                        <c:if test="${fields.hasErrors('description')}">
                            <div class="alert alert-warning" th:errors="*{description}"></div>
                        </c:if>
                    </div>
                    
                    <div class="form-group row">
                        <label class="col-4 col-form-label" for="category"><fmt:message key="periodicals.category"/></label>
                        <div class="col-8">
                            <input id="category" class="form-control" name="category" type="text" value="${category}" th:pattern="${regexp.category}" th:field="*{category}" required>
                        </div>
                        <c:if test="${fields.hasErrors('category')}">
                            <div class="alert alert-warning" th:errors="*{category}"></div>
                        </c:if>
                    </div>

                    <div class="form-group row">
                        <label class="col-4 col-form-label" for="price"><fmt:message key="periodicals.price"/></label>
                        <div class="col-8">
                            <input id="price" class="form-control" name="price" type="text" value="${price}" th:pattern="${regexp.price}" th:field="*{price}" required>
                        </div>
                        <c:if test="${fields.hasErrors('price')}">
                            <div class="alert alert-warning" th:errors="*{price}"></div>
                        </c:if>
                    </div>

                    <button type="submit" class="btn btn-primary"><fmt:message key="actions.submit"/></button>
                </div>
            </form>

        </div>
    </div>

<jsp:include page="/WEB-INF/fragments/footer.jsp"/>