package ua.training.controller.commands;

import ua.training.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogOut implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        session.invalidate();
        CommandUtility.setUserRole(request, User.ROLE.UNKNOWN, "Guest");
        return "redirect:/app/login";
    }
}
