package ua.training.controller.commands;

import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.training.model.entity.Role;
import ua.training.model.entity.User;
import ua.training.model.service.BCryptService;
import ua.training.model.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;


public class Login implements Command {

    private UserService userService;

    private final Logger logger = LogManager.getLogger(Login.class.getName());

    private static final BCryptService bcrypt = new BCryptService(10);
    private String[] mutableHash = new String[1];
    Function<String, Boolean> update = hash -> { mutableHash[0] = hash; return true; };

    public static boolean verifyAndUpdateHash(String password, String hash, Function<String, Boolean> updateFunc) {
        return bcrypt.verifyAndUpdateHash(password, hash, updateFunc);
    }

    public Login(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String name = request.getParameter("name");
        String pass = request.getParameter("pass");

        if( name == null || name.equals("") || pass == null || pass.equals("")  ){
            logger.info("Login form opened");
            return "/login.jsp";
        }

        // Check if user already logged in
        if (CommandUtility.checkUserIsLogged(request, name)) {
            logger.info("Already logged in (name, pass) : " + "(" + name + ", " + pass + ")");
            return "/WEB-INF/already_logged.jsp";
        }

        // Checking login with DB and BCrypt
        Optional<User> user = userService.login(name);
        String newHash = bcrypt.hash(pass);
        logger.info("Login form sent with the hash : " + newHash);
        if (user.isPresent() && bcrypt.verifyAndUpdateHash(pass, user.get().getPassword(), update)) {
            request.getSession().setAttribute("user" , user.get());
            logger.info("Login success (name, pass) : " + "(" + name + ", " + pass + ")");
            logger.info("User " + name + " is logged in.");
        } else {
            logger.info("Invalid attempt of user login:'" + name + "'");
            return "/login.jsp";
        }

        // Check loaded user's roles
        List<String> rolesList = user.get().getRoles().stream().map(Role::getName).collect(Collectors.toList());
        if (rolesList.contains("ADMIN")) {
            CommandUtility.setUserRole(request, User.ROLE.ADMIN, name);
            return "redirect:/app/admin/users";
        } else if(rolesList.contains("USER")) {
            CommandUtility.setUserRole(request, User.ROLE.USER, name);
            return "redirect:/app/user/my-subscriptions";
        } else {
            CommandUtility.setUserRole(request, User.ROLE.UNKNOWN, name);
            return "/login.jsp";
        }
    }

}