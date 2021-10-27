package ua.training.controller.commands;

import javax.servlet.http.HttpServletRequest;

public class Registration implements Command {
    @Override
    public String execute(HttpServletRequest request) {

        //ToDo Service Registration Form
        return "/WEB-INF/registration.jsp";
    }
}
