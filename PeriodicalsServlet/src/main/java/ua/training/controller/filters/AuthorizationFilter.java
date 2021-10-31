package ua.training.controller.filters;


import ua.training.model.entity.User;

import javax.servlet.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AuthorizationFilter implements Filter {

    List<String> adminPath = new ArrayList<>();

    private final Logger logger = LogManager.getLogger(AuthorizationFilter.class.getName());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("AuthorizationFilter started");
        adminPath.add("admin/*");
        adminPath.add("logout");
        adminPath.add("admin/users");
        adminPath.add("admin/categories");
        adminPath.add("admin/periodicals");
        adminPath.add("admin/subscriptions");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession currentSession = req.getSession(false);
        if (currentSession == null || currentSession.getAttribute("user") == null) {
            chain.doFilter(request, response);
        } else {
            authorize(request, response, chain);
        }
    }

    private void authorize(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession currentSession = req.getSession(false);
        User currentUser = (User) currentSession.getAttribute("user");
        boolean isAdmin = currentSession.getAttribute("role") == User.ROLE.ADMIN;

        logger.info("AuthorizationFilter request : " + "(" + request.getParameterMap().toString() + ")");

        String path = req.getRequestURI().replaceAll(".*/app/" , "");
        logger.info("AuthorizationFilter filter path: " + path);
        logger.info("AuthorizationFilter filter loggedUser: " + currentUser);
        logger.info("AuthorizationFilter filter isAdmin: " + isAdmin);
        logger.info("AuthorizationFilter filter role: " + currentUser.getRoleEnum());

        if (path.equals("/") || path.isEmpty() || isAdmin || !adminPath.contains(path)) {
            chain.doFilter(request, response);
        } else {
            // resp.sendError(HttpServletResponse.SC_FORBIDDEN);
            resp.sendRedirect("/app/access-denied");
        }
    }

    @Override
    public void destroy() {

    }
}
