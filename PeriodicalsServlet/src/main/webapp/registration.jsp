<%@ page import="java.util.*, java.text.*" %>

<jsp:include page="/WEB-INF/fragments/head.jsp"/>
<jsp:include page="/WEB-INF/fragments/header.jsp"/>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false"%>
<%@ page import="java.util.*, java.text.*" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

    <div class="container text-center">
        <div>
            <h1>
                <fmt:message key="registration.form.title" />
            </h1>

            <form action="${pageContext.request.contextPath}/app/register" method="post" style="max-width: 800px; margin: 0 auto;">
                <div class="m-3">

                    <div class="form-group row">
                        <label class="col-4 col-form-label" for="email"><fmt:message key="registration.form.email"/></label>
                        <div class="col-8">
                            <input id="email" class="form-control" name="email" type="text" th:pattern="${regexp.email}" th:field="*{email}" required>
                        </div>
                        <c:if test="${fields.hasErrors('email')}"><div class="alert alert-warning" th:errors="*{email}"></div></c:if>
                    </div>

                    <div class="form-group row">
                        <label class="col-4 col-form-label" for="username"><fmt:message key="registration.form.name"/></label>
                        <div class="col-8">
                            <input id="username" class="form-control" name="username" type="text" th:pattern="${regexp.username}" th:field="*{username}" required>
                        </div>
                        <c:if test="${fields.hasErrors('username')}"><div class="alert alert-warning" th:errors="*{username}"></div></c:if>
                        <c:if test="${userExists}"><div class="alert alert-warning" th:if="${usernameExists}" th:text="${form.alert.user.exists}"></div></c:if>
                    </div>

                    <div class="form-group row">
                        <label class="col-4 col-form-label" for="password"><fmt:message key="registration.form.password"/></label>
                        <div class="col-8">
                            <input id="password" class="form-control" name="password" type="password" th:field="*{password}" required>
                        </div>
                        <c:if test="${fields.hasErrors('password')}">
                            <div class="alert alert-warning" th:errors="*{password}"></div>
                        </c:if>
                        <c:if test="${passwordMismatch}">
                            <div class="alert alert-warning" th:text="${form.alert.password_mismatch}"></div>
                        </c:if>
                    </div>
                    
                    <div class="form-group row">
                        <label class="col-4 col-form-label" for="firstname"><fmt:message key="registration.form.firstname"/></label>
                        <div class="col-8">
                            <input id="firstname" class="form-control" name="firstname" type="text" th:pattern="${regexp.firstname}" th:field="*{firstname}" required>
                        </div>
                        <c:if test="${fields.hasErrors('firstname')}">
                            <div class="alert alert-warning" th:errors="*{firstname}"></div>
                        </c:if>
                    </div>

                    <div class="form-group row">
                        <label class="col-4 col-form-label" for="lastname"><fmt:message key="registration.form.lastname"/></label>
                        <div class="col-8">
                            <input id="lastname" class="form-control" name="lastname" type="text" th:pattern="${regexp.lastname}" th:field="*{lastname}" required>
                        </div>
                        <c:if test="${fields.hasErrors('lastname')}">
                            <div class="alert alert-warning" th:errors="*{lastname}"></div>
                        </c:if>
                    </div>

                    <button type="submit" class="btn btn-primary"><fmt:message key="actions.submit"/></button>
                </div>
            </form>

        </div>
    </div>

<jsp:include page="/WEB-INF/fragments/footer.jsp"/>