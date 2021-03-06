package ua.training.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.controller.commands.*;
import ua.training.controller.commands.Locale;
import ua.training.controller.commands.Registration;
import ua.training.controller.commands.Exception;
import ua.training.controller.commands.admin.*;
import ua.training.controller.commands.user.*;
import ua.training.model.entity.User;
import ua.training.model.service.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class Servlet extends HttpServlet {
    private static final String regexPatch = ".*/app/";
    Map<String, Command> commands = new HashMap<>();

    private final Logger logger = LogManager.getLogger(Servlet.class.getName());

    public void init(ServletConfig servletConfig) {

        servletConfig.getServletContext()
                .setAttribute("loggedUsers", new HashSet<String>());

        commands.put("admin/users", new UsersList(new UserService()));
        commands.put("admin/categories", new CategoriesList(new CategoriesService()));
        commands.put("admin/periodicals", new AdminPeriodicalsList(new PeriodicalsService()));
        commands.put("admin/add/periodical", new AddPeriodical(new PeriodicalsService()));
        commands.put("admin/edit/periodical", new EditPeriodical(new PeriodicalsService()));
        commands.put("admin/delete/periodical", new DeletePeriodical(new PeriodicalsService()));
        commands.put("admin/enabled/user", new UserSetEnabled(new UserService()));
        commands.put("admin/subscriptions", new SubscriptionsList(new SubscriptionsService()));
        commands.put("admin/access-denied", new AdminAccessDenied());
        commands.put("user/my-subscriptions", new MySubscriptions(new SubscriptionsService(), new UserService()));
        commands.put("user/replenish-account", new ReplenishAccount(new UserService()));
        commands.put("user/periodicals", new UserPeriodicalsList(new PeriodicalsService()));
        commands.put("user/categories", new UserCategories(new CategoriesService()));
        commands.put("user/category/periodicals", new CategoryPeriodicals(new PeriodicalsService()));
        commands.put("user/search/periodicals", new SearchPeriodicals(new PeriodicalsService()));
        commands.put("user/subscribe/periodical", new UserSubscribePeriodical(new UserService(), new PeriodicalsService()));
        commands.put("user/access-denied", new UserAccessDenied());
        commands.put("access-denied", new AccessDenied());
        commands.put("logout", new LogOut());
        commands.put("login", new Login(new UserService()));
        commands.put("register", new Registration(new UserService()));
        commands.put("exception" , new Exception());
        commands.put("locale", new Locale());
    }
    
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        String path = request.getRequestURI();
        path = path.replaceAll( regexPatch, "");
        path = path.replaceAll(".*/api/" , "");
        Command command = commands.getOrDefault(path, (r) -> "/index.jsp");
        String page = command.execute(request);
        if (page.contains("redirect:")) {
            response.sendRedirect(page.replace("redirect:/", "/"));
        } else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}
