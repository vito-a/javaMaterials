package ua.training.controller.commands;

import ua.training.model.entity.User;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;

/**
 * The general Command utility.
 */
public class CommandUtility {
    /**
     * Sets user role.
     *
     * @param request the request
     * @param role    the role
     * @param name    the name
     */
    static void setUserRole(HttpServletRequest request,
                     User.ROLE role, String name) {
        HttpSession session = request.getSession();
        ServletContext context = request.getServletContext();
        context.setAttribute("userName", name);
        context.setAttribute("role", role);
        session.setAttribute("role", role);
        session.setAttribute("userName", name);
    }

    /**
     * Checks if user is logged in.
     *
     * @param request  the request
     * @param userName the user name
     * @return boolean the user status
     */
    static boolean checkUserIsLogged(HttpServletRequest request, String userName){
        HashSet<String> loggedUsers = (HashSet<String>) request.getSession().getServletContext()
                .getAttribute("loggedUsers");

        if ((loggedUsers != null) && loggedUsers.stream().anyMatch(userName::equals)) {
            return true;
        }

        loggedUsers.add(userName);
        request.getSession().getServletContext()
                .setAttribute("loggedUsers", loggedUsers);
        return false;
    }
}
