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

public class AdminAuthorizationFilter implements Filter {

    List<String> adminPath = new ArrayList<>();

    private final Logger logger = LogManager.getLogger(AdminAuthorizationFilter.class.getName());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
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

        String path = req.getRequestURI().replaceAll(".*/app/" , "");
        logger.info("AdminAuthorizationFilter (path, currentUser, role) : " + "(" + path + ", " + currentUser + ", " + currentUser.getRoleEnum() + ")");

        if (path.equals("/") || path.isEmpty() || isAdmin || !adminPath.contains(path)) {
            chain.doFilter(request, response);
        } else {
            resp.sendRedirect("/app/access-denied");
        }
    }

    @Override
    public void destroy() {}
}
