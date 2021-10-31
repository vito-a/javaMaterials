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

public class UserAuthorizationFilter implements Filter {

    List<String> userPath = new ArrayList<>();

    private final Logger logger = LogManager.getLogger(UserAuthorizationFilter.class.getName());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        userPath.add("user/*");
        userPath.add("logout");
        userPath.add("user/access-denied");
        userPath.add("user/my-subscriptions");
        userPath.add("user/replenish-account");
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
        boolean isUser = currentSession.getAttribute("role") == User.ROLE.USER;

        String path = req.getRequestURI().replaceAll(".*/app/" , "");

        if (isUser) {
            if (userPath.contains(path)) {
                chain.doFilter(request, response);
            } else {
                logger.info("UserAuthorizationFilter triggered (path, currentUser, role) : "
                        + "(" + path + ", " + currentUser + ", " + currentSession.getAttribute("role") + ")");
                resp.sendRedirect("/app/user/access-denied");
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {}
}
