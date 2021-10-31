package ua.training.controller.filters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.model.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AuthenticationFilter implements Filter {
    List<String> anonPath = new ArrayList<>();
    private final Logger logger = LogManager.getLogger(AuthenticationFilter.class.getName());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        anonPath.add("logout");
        anonPath.add("login");
        anonPath.add("register");
        anonPath.add("register-process");
        anonPath.add("exception");
        anonPath.add("locale");
        anonPath.add("access-denied");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession currentSession = req.getSession(false);
        User currentUser = (User) req.getSession().getAttribute("user");

        boolean isLoggedIn = currentSession != null && currentSession.getAttribute("user") != null;
        String path = req.getRequestURI().replaceAll(".*/app/" , "");

        if (path.equals("/") || path.isEmpty() || anonPath.contains(path) || isLoggedIn) {
            chain.doFilter(request, response);
        } else {
            logger.info("AuthenticationFilter triggered (path, currentUser) : "
                    + "(" + path + ", " + currentUser + ")");
            resp.sendRedirect("/app/access-denied");
        }
    }

    @Override
    public void destroy() { }
}
