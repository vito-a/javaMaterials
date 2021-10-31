package ua.training.controller.commands;

import ua.training.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.model.service.PeriodicalsService;
import ua.training.model.service.UserService;

public class ProcessRegistration implements Command {

    private final UserService userService;
    private final Logger logger = LogManager.getLogger(ProcessRegistration.class.getName());

    public ProcessRegistration(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        User user = new User();
        user.setFirstname(request.getParameter("firstName"));
        user.setLastname(request.getParameter("lastName"));
        user.setUsername(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));
        user.setEmail(request.getParameter("email"));

        userService.createUser(user);
        logger.info("User registration (name, email) ==> " + "(" + user.getUsername() + ", " + user.getEmail() + ")");

        return "redirect:/app/login";
    }
}
