<jsp:include page="/WEB-INF/fragments/head.jsp"/>
<jsp:include page="/WEB-INF/fragments/header.jsp"/>
    <h2>
        List Users <br/>
    </h2>
    <table>
    <tr><th>firstname</th><th>lastname</th></tr>
    <c:forEach var="i" items="${users}">
        <tr><td>${i.name}<c:out value="${i.firstname}"/></td><td>${i.lastname}</td>
    </c:forEach>
    </table>
    <br>
    <br>
    <%=request.getAttribute("users")%>
    <br>
    <c:out value="${users}"/>
    <br/>
    <a href="./index.jsp">index</a>
    <br/>
    <a href="${pageContext.request.contextPath}/index.jsp">index</a>
<jsp:include page="/WEB-INF/fragments/footer.jsp"/>
