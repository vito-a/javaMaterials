package ua.training.controller.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The No cache filter.
 *
 * Adds the headers preventing the pages caching in browsers.
 * This is needed, for example, to prevent the users from seeing
 * the outdated pages when they are clicking the back button in
 * browser.
 *
 */
public class NoCacheFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;

        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        chain.doFilter(req, res);
    }
    public void destroy() {}
    public void init(FilterConfig arg0) throws ServletException {}
}