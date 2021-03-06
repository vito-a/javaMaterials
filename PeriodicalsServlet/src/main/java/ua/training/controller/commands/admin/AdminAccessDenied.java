package ua.training.controller.commands.admin;

import ua.training.controller.commands.Command;

import javax.servlet.http.HttpServletRequest;

/**
 * Admin access denied command.
 */
public class AdminAccessDenied implements Command {
    @Override
    public String execute(HttpServletRequest request) { return "/WEB-INF/admin/access_denied.jsp"; }
}
