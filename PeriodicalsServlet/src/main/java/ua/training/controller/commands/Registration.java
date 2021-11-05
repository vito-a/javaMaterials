package ua.training.controller.commands;

import ua.training.model.entity.User;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.model.service.BCryptService;
import ua.training.model.service.UserService;

/**
 * The Registration command.
 */
public class Registration implements Command {
    private final UserService userService;
    private static final BCryptService bcrypt = new BCryptService(10);
    private final Logger logger = LogManager.getLogger(Registration.class.getName());

    /**
     * Instantiates a new Registration command.
     *
     * @param userService the user service
     */
    public Registration(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if( username == null || username.equals("") || password == null || password.equals("")  ){
            logger.info("Registration form opened");
            return "/registration.jsp";
        }

        User user = new User();
        user.setFirstname(request.getParameter("firstname"));
        user.setLastname(request.getParameter("lastname"));
        user.setEmail(request.getParameter("email"));
        user.setUsername(username);
        user.setPassword(bcrypt.hash(password));

        logger.info("User registration request : " + "(" + request.getParameterMap().toString() + ")");
        int result = userService.createUser(user);
        if (result < 1) {
            RuntimeException runtimeException = new RuntimeException("User exists");
            request.setAttribute("exception", runtimeException);
            return "/WEB-INF/error.jsp";
        }

        logger.info("User registration (name, email, password) : "
                + "(" + user.getUsername() + ", " + user.getEmail() + ", " + user.getPassword() + ")");

        return "redirect:/app/login";
    }
}
