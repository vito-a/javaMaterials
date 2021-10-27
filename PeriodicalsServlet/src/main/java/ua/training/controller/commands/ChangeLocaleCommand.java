package ua.training.controller.commands;

import ua.training.controller.commands.Command;
import ua.training.controller.commands.Exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeLocaleCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {

        //String locale = request.getParameter("locale");
        String lang = request.getParameter("lang");

        //request.getSession().setAttribute("locale", locale);

        request.getSession().setAttribute("lang", lang);
        request.setAttribute("lang", lang);

        return "redirect:/index.jsp";
    }
}
