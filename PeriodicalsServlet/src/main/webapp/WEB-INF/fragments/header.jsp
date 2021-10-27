<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="/">Periodicals</a>
    <div class="collapse navbar-collapse" id="navbarColor01">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active"><a class="nav-link" href="/">Main<span class="sr-only">(current)</span></a></li>
            <li class="nav-item"><a class="nav-link" href="/users">Users</a></li>
            <li class="nav-item"><a class="nav-link" href="/periodicals">Periodicals</a></li>
            <li class="nav-item"><a class="nav-link" href="/categories">Categories</a></li>
            <li class="nav-item"><a class="nav-link" href="/subscriptions">Subscriptions</a></li>
            <li class="nav-item"><a class="nav-link" href="/user/my-subscriptions">My subscriptions</a></li>
            <li class="nav-item"><a class="nav-link" href="/user/replenish_account">Replenish account</a></li>
            <li class="nav-item"><a class="nav-link" href="/about">About</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li>
                <div class="mr-2">
                    <form class="form-inline" action="/search/periodicals">
                        <input class="form-control mr-sm-2" type="search" id="keyword" name="keyword" placeholder="Search" aria-label="Search">
                        <button class="btn btn-primary my-2 my-sm-0" type="submit">Search</button>
                    </form>
                </div>
            </li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li>
                <div class="dropdown">
                    <button class="btn btn-secondary" type="button" onclick="$('#language-dropdown').toggle();">
                        Language
                    </button>
                    <div class="dropdown-menu" id="language-dropdown">
                        <button class="dropdown-item" type="button">
                            <a href="/?lang=en">EN</a>
                        </button>
                        <button class="dropdown-item" type="button">
                            <a href="/?lang=fr">FR</a>
                        </button>
                        <button class="dropdown-item" type="button">
                            <a href="/?lang=ru">RU</a>
                        </button>
                        <button class="dropdown-item" type="button">
                            <a href="/?lang=ua">UA</a>
                        </button>
                    </div>
                </div>
            </li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li class="nav-item">
                <a class="nav-link" href="/register">Register</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/login">
                    <span class="glyphicon glyphicon-log-in" aria-hidden="true"></span>&nbsp;Login
                </a>
            </li>
            <li class="nav-item">
                <div>
                    <div class="nav-link float-left ml-2 text-white">
                        Hi!
                    </div>
                    <a class="nav-link float-left" href="/logout" onclick="$('#logout-form').submit();">Logout</a>
                </div>
                <form class="d-none invisible" id="logout-form" method="post" action="#" action="/logout">
                    <input class="nav-link" type="submit" value="Logout" />
                </form>
            </li>
        </ul>
    </div>
</nav>