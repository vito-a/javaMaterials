package ua.training.controller.commands;

import javax.servlet.http.HttpServletRequest;

public class AccessDenied implements Command {
    @Override
    public String execute(HttpServletRequest request) { return "/WEB-INF/access_denied.jsp"; }
}
