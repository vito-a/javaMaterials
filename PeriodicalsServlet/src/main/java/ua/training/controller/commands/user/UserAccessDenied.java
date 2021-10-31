package ua.training.controller.commands.user;

import ua.training.controller.commands.Command;

import javax.servlet.http.HttpServletRequest;

public class UserAccessDenied implements Command {
    @Override
    public String execute(HttpServletRequest request) { return "/WEB-INF/user/access_denied.jsp"; }
}
