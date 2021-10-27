package ua.training.controller.commands;

import ua.training.controller.commands.Command;
import ua.training.controller.commands.Exception;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
