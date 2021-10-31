<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="/">Periodicals</a>
    <div class="collapse navbar-collapse" id="navbarColor01">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active"><a class="nav-link" href="/">Main<span class="sr-only">(current)</span></a></li>
            <li class="nav-item"><a class="nav-link" href="/app/about">About</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li>
                <div class="dropdown">
                    <button class="btn btn-secondary" type="button" onclick="$('#language-dropdown').toggle();">
                        Language
                    </button>
                    <div class="dropdown-menu" id="language-dropdown">
                        <button class="dropdown-item" type="button">
                            <a href="${pageContext.request.contextPath}?lang=en"><fmt:message key="lang.EN" />EN</a>
                        </button>
                        <button class="dropdown-item" type="button">
                            <a href="${pageContext.request.contextPath}?lang=fr"><fmt:message key="lang.FR" />FR</a>
                        </button>
                        <button class="dropdown-item" type="button">
                            <a href="${pageContext.request.contextPath}?lang=ru"><fmt:message key="lang.RU" />RU</a>
                        </button>
                        <button class="dropdown-item" type="button">
                            <a href="${pageContext.request.contextPath}?lang=ua"><fmt:message key="lang.UA" />UA</a>
                        </button>
                    </div>
                </div>
            </li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li class="nav-item">
                <a class="nav-link" href="/app/register">Register</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/app/login">
                    <span class="glyphicon glyphicon-log-in" aria-hidden="true"></span>&nbsp;Login
                </a>
            </li>
        </ul>
    </div>
</nav>