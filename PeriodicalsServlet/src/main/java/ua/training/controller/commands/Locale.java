package ua.training.controller.commands;

import javax.servlet.http.HttpServletRequest;

/**
 * The Locale command.
 *
 * Does the localization and the language switching.
 */
public class Locale implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        String lang = request.getParameter("lang");
        if (lang != null) {
            request.getSession().setAttribute("lang", lang);
            request.setAttribute("lang", lang);
        }

        return "redirect:/index.jsp";
    }
}
