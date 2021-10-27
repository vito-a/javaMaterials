<jsp:include page="/WEB-INF/fragments/head.jsp"/>
<jsp:include page="/WEB-INF/fragments/header.jsp"/>
    <h2>
        List Students <br/>
    </h2>
    <table>
    <tr><th>Name</th><th>Group</th></tr>
    <c:forEach var="i" items="${students}">
        <tr><td>${i.name}<c:out value="${i.name}"/></td><td>${i.groupe}</td>
    </c:forEach>
    </table>
    <br>
    <br>
    <%=request.getAttribute("students")%>
    <br>
    <c:out value="${students}"/>
    <br/>
    <a href="./index.jsp">index</a>
    <br/>
    <a href="${pageContext.request.contextPath}/index.jsp">index</a>
<jsp:include page="/WEB-INF/fragments/footer.jsp"/>
