package ua.training.controller.commands.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.controller.commands.Command;
import ua.training.controller.commands.Registration;
import ua.training.model.entity.Periodical;
import ua.training.model.entity.User;
import ua.training.model.service.PeriodicalsService;
import ua.training.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UserSetEnabled implements Command {

    private final UserService userService;
    private final Logger logger = LogManager.getLogger(Registration.class.getName());

    public UserSetEnabled(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {

        String userId = request.getParameter("user_id");
        String enabled = request.getParameter("enabled");

        if (userId != null && !userId.equals("") && enabled != null && !enabled.equals("")) {
            userService.setEnabled(Long.parseLong(userId), Boolean.parseBoolean(enabled));
            return "redirect:/app/admin/users";
        }

        return "/WEB-INF/error.jsp";
    }
}
