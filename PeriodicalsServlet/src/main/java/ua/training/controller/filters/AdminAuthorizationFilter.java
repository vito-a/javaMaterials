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
        adminPath.add("admin/access-denied");
        adminPath.add("admin/add/periodical");
        adminPath.add("admin/edit/periodical");
        adminPath.add("admin/delete/periodical");
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

        if (isAdmin) {
            if (adminPath.contains(path)) {
                chain.doFilter(request, response);
            } else {
                logger.info("AdminAuthorizationFilter triggered (path, currentUser, role) : "
                        + "(" + path + ", " + currentUser + ", " + currentSession.getAttribute("role") + ")");
                resp.sendRedirect("/app/admin/access-denied");
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {}
}
